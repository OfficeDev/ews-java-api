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

package microsoft.exchange.webservices.data.misc;

import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.request.HangingServiceRequestBase;
import microsoft.exchange.webservices.data.core.enumeration.misc.TraceFlags;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.stream.XMLStreamException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * A stream that traces everything it returns from its Read() call.
 * That trace may be retrieved at the end of the stream.
 */
public class HangingTraceStream extends InputStream {

  private static final Log LOG = LogFactory.getLog(HangingTraceStream.class);

  private final InputStream underlyingStream;
  private final ExchangeService service;
  private ByteArrayOutputStream responseCopy;

  /**
   * Initializes a new instance of the HangingTraceStream class.
   *
   * @param stream  The stream.
   * @param service the service.
   */
  public HangingTraceStream(final InputStream stream, final ExchangeService service) {
    this.underlyingStream = stream;
    this.service = service;
  }

  /**
   * Gets a value indicating whether the current stream supports reading.
   *
   * @return true
   */
  public boolean getCanRead() {
    return true;
  }

  /**
   * Gets a value indicating whether the current stream supports seeking.
   *
   * @return false
   */
  public boolean getCanSeek() {
    return false;
  }

  /**
   * Gets a value indicating whether the current stream supports writing.
   *
   * @return false
   */
  public boolean getCanWrite() {
    return false;
  }

  /**
   * When overridden in a derived class, clears all buffers
   * for this stream and causes any buffered data to be
   *  written to the underlying device.
   *@exception An I/O error occurs.
   */
        /*
	 * @Override public void close() { // no-op }
	 */

  /**
   * When overridden in a derived class, reads a sequence of
   * bytes from the current stream and advances the
   * position within the stream by the number of bytes read.
   *
   * @param buffer An array of bytes. When this method returns, the buffer
   *               contains the specified byte array with the values between
   * @param offset The zero-based byte offset in at which to
   *               begin storing the data read from the current stream.
   * @param count  The maximum number of bytes to be read from the current stream.
   * @return The total number of bytes read into the buffer.
   * This can be less than the number of bytes requested if that
   * many bytes are not currently available, or zero (0)
   * if the end of the stream has been reached.
   * @throws IOException The sum of offset and count is larger than the buffer length.
   */
  @Override
  public int read(byte[] buffer, int offset, int count) throws IOException {
    count = HangingServiceRequestBase.BUFFER_SIZE;
    final int retVal = underlyingStream.read(buffer, offset, count);

    if (HangingServiceRequestBase.isLogAllWireBytes()) {
      final String readString = new String(buffer, offset, count, "UTF-8");
      final String logMessage = String.format(
          "HangingTraceStream ID [%d] returned %d bytes. Bytes returned: [%s]",
          hashCode(), retVal, readString);

      try {
        service.traceMessage(TraceFlags.DebugMessage, logMessage);
      } catch (final XMLStreamException e) {
        LOG.error(e);
      }
    }

    if (responseCopy != null) {
      responseCopy.write(buffer, offset, retVal);
    }

    return retVal;
  }

  /**
   * Sets the response copy.
   *
   * @param responseCopy a copy of response
   */
  public void setResponseCopy(final ByteArrayOutputStream responseCopy) {
    this.responseCopy = responseCopy;
  }

  @Override
  public int read() throws IOException {
    return 0;
  }

}

