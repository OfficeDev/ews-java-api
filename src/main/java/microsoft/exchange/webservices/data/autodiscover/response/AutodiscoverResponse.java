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

package microsoft.exchange.webservices.data.autodiscover.response;

import microsoft.exchange.webservices.data.autodiscover.enumeration.AutodiscoverErrorCode;
import microsoft.exchange.webservices.data.core.EwsXmlReader;
import microsoft.exchange.webservices.data.core.XmlElementNames;

import java.net.URI;

/**
 * Represents the base class for all response returned by the Autodiscover
 * service.
 */
public abstract class AutodiscoverResponse {

  /**
   * The error code.
   */
  private AutodiscoverErrorCode errorCode;

  /**
   * The error message.
   */
  private String errorMessage;

  /**
   * The redirection url.
   */
  private URI redirectionUrl;

  /**
   * Initializes a new instance of the AutodiscoverResponse class.
   */
  public AutodiscoverResponse() {
    this.errorCode = AutodiscoverErrorCode.NoError;
  }

  /**
   * Initializes a new instance of the AutodiscoverResponse class.
   *
   * @param reader         the reader
   * @param endElementName the end element name
   * @throws Exception the exception
   */
  public void loadFromXml(EwsXmlReader reader, String endElementName)
      throws Exception {
    if (reader.getLocalName().equalsIgnoreCase(XmlElementNames.ErrorCode)) {
      this.errorCode = reader
          .readElementValue(AutodiscoverErrorCode.class);
    } else if (reader.getLocalName().equalsIgnoreCase(
        XmlElementNames.ErrorMessage)) {
      this.errorMessage = reader.readElementValue();
    }
  }

  /**
   * Gets  the error code that was returned by the service.
   *
   * @return the error code
   */
  public AutodiscoverErrorCode getErrorCode() {
    return errorCode;
  }

  /**
   * Sets the error code.
   *
   * @param errorCode the new error code
   */
  public void setErrorCode(AutodiscoverErrorCode errorCode) {
    this.errorCode = errorCode;
  }

  /**
   * Gets the error message that was returned by the service.
   *
   * @return the error message
   */
  public String getErrorMessage() {
    return errorMessage;
  }

  /**
   * Sets the error message.
   *
   * @param errorMessage the new error message
   */
  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  /**
   * Gets  the redirection URL.
   *
   * @return the redirection url
   */
  public URI getRedirectionUrl() {
    return redirectionUrl;
  }

  /**
   * Sets the redirection url.
   *
   * @param redirectionUrl the new redirection url
   */
  public void setRedirectionUrl(URI redirectionUrl) {
    this.redirectionUrl = redirectionUrl;
  }
}
