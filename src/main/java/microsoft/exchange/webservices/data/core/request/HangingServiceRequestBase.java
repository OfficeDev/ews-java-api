/*
 * The MIT License
 * Copyright (c) 2012 Microsoft Corporation
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package microsoft.exchange.webservices.data.core.request;

import microsoft.exchange.webservices.data.core.EwsServiceMultiResponseXmlReader;
import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.enumeration.misc.HangingRequestDisconnectReason;
import microsoft.exchange.webservices.data.core.enumeration.misc.TraceFlags;
import microsoft.exchange.webservices.data.core.exception.http.EWSHttpException;
import microsoft.exchange.webservices.data.core.exception.misc.ArgumentException;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceVersionException;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlDeserializationException;
import microsoft.exchange.webservices.data.core.exception.service.remote.ServiceRequestException;
import microsoft.exchange.webservices.data.core.exception.xml.XmlException;
import microsoft.exchange.webservices.data.misc.HangingTraceStream;
import microsoft.exchange.webservices.data.security.XmlNodeType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.stream.XMLStreamException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;
import java.net.SocketTimeoutException;
import java.net.UnknownServiceException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * Represents an abstract, hanging service request.
 */
public abstract class HangingServiceRequestBase<T> extends ServiceRequestBase<T> {

  private static final Log LOG = LogFactory.getLog(HangingServiceRequestBase.class);


  public interface IHandleResponseObject {

    /**
     * Callback delegate to handle asynchronous response.
     *
     * @param response Response received from the server
     * @throws ArgumentException
     */
    void handleResponseObject(Object response) throws ArgumentException;
  }


  public static final int BUFFER_SIZE = 4096;

  /**
   * Test switch to log all bytes that come across the wire.
   * Helpful when parsing fails before certain bytes hit the trace logs.
   */
  private static volatile boolean logAllWireBytes = false;

  /**
   * Callback delegate to handle response objects
   */
  private IHandleResponseObject responseHandler;

  /**
   * Response from the server.
   */
  private HttpWebRequest response;

  /**
   * Expected minimum frequency in response, in milliseconds.
   */
  protected int heartbeatFrequencyMilliseconds;


  public interface IHangingRequestDisconnectHandler {

    /**
     * Delegate method to handle a hanging request disconnection.
     *
     * @param sender the object invoking the delegate
     * @param args event data
     */
    void hangingRequestDisconnectHandler(Object sender,
        HangingRequestDisconnectEventArgs args);

  }


  public static boolean isLogAllWireBytes() {
    return logAllWireBytes;
  }

  public static void setLogAllWireBytes(final boolean logAllWireBytes) {
    HangingServiceRequestBase.logAllWireBytes = logAllWireBytes;
  }

  /**
   * Disconnect events Occur when the hanging request is disconnected.
   */
  private List<IHangingRequestDisconnectHandler> onDisconnectList =
      new ArrayList<IHangingRequestDisconnectHandler>();

  /**
   * Set event to happen when property disconnect.
   *
   * @param disconnect disconnect event
   */
  public void addOnDisconnectEvent(IHangingRequestDisconnectHandler disconnect) {
    onDisconnectList.add(disconnect);
  }

  /**
   * Remove the event from happening when property disconnect.
   *
   * @param disconnect disconnect event
   */
  protected void removeDisconnectEvent(
      IHangingRequestDisconnectHandler disconnect) {
    onDisconnectList.remove(disconnect);
  }

  /**
   * Clears disconnect events list.
   */
  protected void clearDisconnectEvents() {
    onDisconnectList.clear();
  }

  /**
   * Initializes a new instance of the HangingServiceRequestBase class.
   *
   * @param service            The service.
   * @param handler            Callback delegate to handle response objects
   * @param heartbeatFrequency Frequency at which we expect heartbeats, in milliseconds.
   */
  protected HangingServiceRequestBase(ExchangeService service,
      IHandleResponseObject handler, int heartbeatFrequency)
      throws ServiceVersionException {
    super(service);
    this.responseHandler = handler;
    this.heartbeatFrequencyMilliseconds = heartbeatFrequency;
  }

  /**
   * Exectures the request.
   */
  public void internalExecute() throws Exception {
    synchronized (this) {
      this.response = this.validateAndEmitRequest();
      this.internalOnConnect();
    }
  }

  /**
   * Parses the response.
   *
   */
  private void parseResponses() {
    HangingTraceStream tracingStream = null;
    ByteArrayOutputStream responseCopy = null;


    try {
      boolean traceEWSResponse = this.getService().isTraceEnabledFor(TraceFlags.EwsResponse);
      InputStream responseStream = this.response.getInputStream();
      tracingStream = new HangingTraceStream(responseStream,
          this.getService());
      //EWSServiceMultiResponseXmlReader. Create causes a read.

      if (traceEWSResponse) {
        responseCopy = new ByteArrayOutputStream();
        tracingStream.setResponseCopy(responseCopy);
      }

      while (this.isConnected()) {
        T responseObject;
        if (traceEWSResponse) {
          EwsServiceMultiResponseXmlReader ewsXmlReader =
              EwsServiceMultiResponseXmlReader.create(tracingStream, getService());
          responseObject = this.readResponse(ewsXmlReader);
          this.responseHandler.handleResponseObject(responseObject);

          // reset the stream collector.
          responseCopy.close();
          responseCopy = new ByteArrayOutputStream();
          tracingStream.setResponseCopy(responseCopy);

        } else {
          EwsServiceMultiResponseXmlReader ewsXmlReader =
              EwsServiceMultiResponseXmlReader.create(tracingStream, getService());
          responseObject = this.readResponse(ewsXmlReader);
          this.responseHandler.handleResponseObject(responseObject);
        }
      }
    } catch (SocketTimeoutException ex) {
      // The connection timed out.
      this.disconnect(HangingRequestDisconnectReason.Timeout, ex);
    } catch (UnknownServiceException ex) {
      // Stream is closed, so disconnect.
      this.disconnect(HangingRequestDisconnectReason.Exception, ex);
    } catch (ObjectStreamException ex) {
      // Stream is closed, so disconnect.
      this.disconnect(HangingRequestDisconnectReason.Exception, ex);
    } catch (IOException ex) {
      // Stream is closed, so disconnect.
      this.disconnect(HangingRequestDisconnectReason.Exception, ex);
    } catch (UnsupportedOperationException ex) {
      LOG.error(ex);
      // This is thrown if we close the stream during a
      //read operation due to a user method call.
      // Trying to delay closing until the read finishes
      //simply results in a long-running connection.
      this.disconnect(HangingRequestDisconnectReason.UserInitiated, null);
    } catch (Exception ex) {
      // Stream is closed, so disconnect.
      this.disconnect(HangingRequestDisconnectReason.Exception, ex);
    } finally {
      if (responseCopy != null) {
        try {
          responseCopy.close();
          responseCopy = null;
        } catch (Exception ex) {
          LOG.error(ex);
        }
      }
    }
  }

  private boolean isConnected;

  /**
   * Gets a value indicating whether this instance is connected.
   *
   * @return true, if this instance is connected; otherwise, false
   */
  public boolean isConnected() {
    return this.isConnected;
  }

  private void setIsConnected(boolean value) {
    this.isConnected = value;
  }

  /**
   * Disconnects the request.
   */
  public void disconnect() {
    synchronized (this) {
      try {
        this.response.close();
      } catch (IOException e) {
        // Ignore exception on disconnection
      }
      this.disconnect(HangingRequestDisconnectReason.UserInitiated, null);
    }
  }

  /**
   * Disconnects the request with the specified reason and exception.
   *
   * @param reason    The reason.
   * @param exception The exception.
   */
  public void disconnect(HangingRequestDisconnectReason reason, Exception exception) {
    if (this.isConnected()) {
      try {
        this.response.close();
      } catch (IOException e) {
        // Ignore exception on disconnection
      }
      this.internalOnDisconnect(reason, exception);
    }
  }

  /**
   * Perform any bookkeeping needed when we connect
   * @throws XMLStreamException the XML stream exception
   */
  private void internalOnConnect() throws XMLStreamException,
      IOException, EWSHttpException {
    if (!this.isConnected()) {
      this.isConnected = true;

      if (this.getService().isTraceEnabledFor(TraceFlags.EwsResponseHttpHeaders)) {
        // Trace Http headers
        this.getService().processHttpResponseHeaders(
            TraceFlags.EwsResponseHttpHeaders,
            this.response);
      }
      int poolSize = 1;

      int maxPoolSize = 1;

      long keepAliveTime = 10;

      final ArrayBlockingQueue<Runnable> queue =
          new ArrayBlockingQueue<Runnable>(
              1);
      ThreadPoolExecutor threadPool = new ThreadPoolExecutor(poolSize,
          maxPoolSize,
          keepAliveTime, TimeUnit.SECONDS, queue);
      threadPool.execute(new Runnable() {
        public void run() {
          parseResponses();
        }
      });
      threadPool.shutdown();
    }
  }

  /**
   * Perform any bookkeeping needed when we disconnect (cleanly or forcefully)
   *
   * @param reason    The reason.
   * @param exception The exception.
   */
  private void internalOnDisconnect(HangingRequestDisconnectReason reason,
      Exception exception) {
    if (this.isConnected()) {
      this.isConnected = false;
      for (IHangingRequestDisconnectHandler disconnect : onDisconnectList) {
        disconnect.hangingRequestDisconnectHandler(this,
            new HangingRequestDisconnectEventArgs(reason, exception));
      }
    }
  }

  /**
   * Reads any preamble data not part of the core response.
   *
   * @param ewsXmlReader The EwsServiceXmlReader.
   * @throws Exception
   */
  @Override
  protected void readPreamble(EwsServiceXmlReader ewsXmlReader)
      throws Exception {
    // Do nothing.
    try {
      ewsXmlReader.read(new XmlNodeType(XmlNodeType.START_DOCUMENT));
    } catch (XmlException ex) {
      throw new ServiceRequestException("The response received from the service didn't contain valid XML.", ex);
    } catch (ServiceXmlDeserializationException ex) {
      throw new ServiceRequestException("The response received from the service didn't contain valid XML.", ex);
    }
  }
}
