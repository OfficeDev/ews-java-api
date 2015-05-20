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

package microsoft.exchange.webservices.data.autodiscover.exception.error;

import microsoft.exchange.webservices.data.attribute.EditorBrowsable;
import microsoft.exchange.webservices.data.core.EwsXmlReader;
import microsoft.exchange.webservices.data.core.XmlAttributeNames;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.attribute.EditorBrowsableState;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.security.XmlNodeType;

/**
 * Defines the AutodiscoverError class.
 */
@EditorBrowsable(state = EditorBrowsableState.Never)
public final class AutodiscoverError {

  /**
   * The time.
   */
  private String time;

  /**
   * The id.
   */
  private String id;

  /**
   * The error code.
   */
  private int errorCode;

  /**
   * The message.
   */
  private String message;

  /**
   * The debug data.
   */
  private String debugData;

  /**
   * Initializes a new instance of the AutodiscoverError class.
   */
  private AutodiscoverError() {
  }

  /**
   * Parses the XML through the specified reader and creates an Autodiscover
   * error.
   *
   * @param reader the reader
   * @return AutodiscoverError
   * @throws Exception the exception
   */
  public static AutodiscoverError parse(EwsXmlReader reader)
      throws Exception {
    AutodiscoverError error = new AutodiscoverError();
    error.time = reader.readAttributeValue(XmlAttributeNames.Time);
    error.id = reader.readAttributeValue(XmlAttributeNames.Id);

    do {
      reader.read();

      if (reader.getNodeType().getNodeType() == XmlNodeType.START_ELEMENT) {
        if (reader.getLocalName().equalsIgnoreCase(
            XmlElementNames.ErrorCode)) {
          error.errorCode = reader.readElementValue(Integer.class);
        } else if (reader.getLocalName().equalsIgnoreCase(
            XmlElementNames.Message)) {
          error.message = reader.readElementValue();
        } else if (reader.getLocalName().equalsIgnoreCase(
            XmlElementNames.DebugData)) {
          error.debugData = reader.readElementValue();
        } else {
          reader.skipCurrentElement();
        }
      }
    } while (!reader.isEndElement(XmlNamespace.NotSpecified,
        XmlElementNames.Error));

    return error;
  }

  /**
   * Gets the time when the error was returned.
   *
   * @return the time
   */
  public String getTime() {
    return time;
  }

  /**
   * Gets a hash of the name of the computer that is running Microsoft
   * Exchange Server that has the Client Access server role installed.
   *
   * @return the id
   */
  public String getId() {
    return id;
  }

  /**
   * Gets the error code.
   *
   * @return the error code
   */
  public int getErrorCode() {
    return errorCode;
  }

  /**
   * Gets the error message.
   *
   * @return the message
   */
  public String getMessage() {
    return message;
  }

  /**
   * Gets the debug data.
   *
   * @return the debug data
   */
  public String getDebugData() {
    return debugData;
  }

}
