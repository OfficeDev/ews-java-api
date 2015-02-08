package microsoft.exchange.webservices.data;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.ws.http.HTTPException;
import java.io.*;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * Defines the SimpleServiceRequestBase class.
 */
abstract class SimpleServiceRequestBase extends ServiceRequestBase {

  private static final Log log = LogFactory.getLog(SimpleServiceRequestBase.class);

  /**
   * Initializes a new instance of the SimpleServiceRequestBase class.
   */
  protected SimpleServiceRequestBase(ExchangeService service)
      throws Exception {
    super(service);
  }

  /**
   * Executes this request.
   *
   * @throws Exception
   * @throws microsoft.exchange.webservices.data.ServiceLocalException
   */
  protected Object internalExecute() throws ServiceLocalException, Exception {
    HttpWebRequest response = null;

    try {
      response = this.validateAndEmitRequest();
      return this.readResponse(response);
    } catch (IOException ex) {
      // Wrap exception.
      throw new ServiceRequestException(String.
          format(Strings.ServiceRequestFailed, ex.getMessage(), ex));
    } catch (Exception e) {
      if (response != null) {
        this.getService().processHttpResponseHeaders(TraceFlags.
            EwsResponseHttpHeaders, response);
      }

      throw new ServiceRequestException(String.format(Strings.ServiceRequestFailed, e.getMessage()), e);
    } finally {
      try {
        if (response != null) {
          response.close();
        }
      } catch (Exception e2) {
        response = null;
      }
    }
  }

  /**
   * Ends executing this async request.
   *
   * @param asyncResult The async result
   * @return Service response object.
   */
  protected Object endInternalExecute(IAsyncResult asyncResult) throws Exception {
    HttpWebRequest response = (HttpWebRequest) asyncResult.get();
    return this.readResponse(response);
  }

  /**
   * Begins executing this async request.
   *
   * @param callback The AsyncCallback delegate.
   * @param state    An object that contains state information for this request.
   * @return An IAsyncResult that references the asynchronous request.
   */
  protected AsyncRequestResult beginExecute(AsyncCallback callback, Object state) throws Exception {
    this.validate();

    HttpWebRequest request = this.buildEwsHttpWebRequest();

    WebAsyncCallStateAnchor wrappedState = new WebAsyncCallStateAnchor(
        this, request, callback /* user callback */, state /*user state*/);

    AsyncExecutor es = new AsyncExecutor();
    Callable<?> cl = new CallableMethod(request);
    Future<?> task = es.submit(cl, callback);
    es.shutdown();
    AsyncRequestResult ft = new AsyncRequestResult(this, request, task, null);

    // ct.setAsyncRequest();
    // webAsyncResult =
    // request.beginGetResponse(SimpleServiceRequestBase.webRequestAsyncCallback(webAsyncResult),
    // wrappedState);
    return ft;
    // return new AsyncRequestResult(this, request, webAsyncResult, state /*
    // user state */);
  }

  /**
   * Reads the response.
   *
   * @return serviceResponse
   * @throws Exception
   */
  private Object readResponse(HttpWebRequest response) throws Exception {
    Object serviceResponse;

    if (!response.getResponseContentType().startsWith("text/xml")) {
      String line = new BufferedReader(new InputStreamReader(ServiceRequestBase.getResponseStream(response)))
          .readLine();
      log.error("Response content type not XML; first line: '" + line + "'");
      throw new ServiceRequestException(Strings.ServiceResponseDoesNotContainXml);
    }

    /**
     * If tracing is enabled, we read the entire response into a
     * MemoryStream so that we can pass it along to the ITraceListener. Then
     * we parse the response from the MemoryStream.
     */

    try {
      this.getService().processHttpResponseHeaders(
          TraceFlags.EwsResponseHttpHeaders, response);

      if (this.getService().isTraceEnabledFor(TraceFlags.EwsResponse)) {
        ByteArrayOutputStream memoryStream = new ByteArrayOutputStream();
        InputStream serviceResponseStream = ServiceRequestBase
            .getResponseStream(response);
        while (true) {
          int data = serviceResponseStream.read();
          if (-1 == data) {
            break;
          } else {
            memoryStream.write(data);
          }
        }

        this.traceResponse(response, memoryStream);
        ByteArrayInputStream memoryStreamIn = new ByteArrayInputStream(
            memoryStream.toByteArray());
        EwsServiceXmlReader ewsXmlReader = new EwsServiceXmlReader(
            memoryStreamIn, this.getService());
        serviceResponse = this.readResponse(ewsXmlReader);
        serviceResponseStream.close();
        memoryStream.flush();
      } else {
        InputStream responseStream = ServiceRequestBase
            .getResponseStream(response);
        EwsServiceXmlReader ewsXmlReader = new EwsServiceXmlReader(
            responseStream, this.getService());
        serviceResponse = this.readResponse(ewsXmlReader);

      }
    } catch (HTTPException e) {
      if (e.getMessage() != null) {
        this.getService().processHttpResponseHeaders(
            TraceFlags.EwsResponseHttpHeaders, response);
      }

      throw new ServiceRequestException(String.format(
          Strings.ServiceRequestFailed, e.getMessage()), e);
    } catch (IOException e) {
      // Wrap exception.
      throw new ServiceRequestException(String.format(
          Strings.ServiceRequestFailed, e.getMessage()), e);
    } finally {
      if (response != null) {
        response.close();
      }
    }

    return serviceResponse;

  }

}
