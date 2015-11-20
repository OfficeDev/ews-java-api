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

package microsoft.exchange.webservices.data.autodiscover;

import microsoft.exchange.webservices.data.core.EwsXmlReader;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.security.XmlNodeType;

/**
 * Represents the URL of the Exchange web client.
 */
public final class WebClientUrl {

  /**
   * The authentication methods.
   */
  private String authenticationMethods;

  /**
   * The url.
   */
  private String url;

  /**
   * Initializes a new instance of the <see cref="WebClientUrl"/> class.
   */
  private WebClientUrl() {
  }

  /**
   * Initializes a new instance of the WebClientUrl class.
   *
   * @param authenticationMethods The authentication methods.
   * @param url                   The URL.
   */
  public WebClientUrl(String authenticationMethods, String url) {
    this.authenticationMethods = authenticationMethods;
    this.url = url;
  }


  /**
   * Loads WebClientUrl instance from XML.
   *
   * @param reader The reader.
   * @return WebClientUrl.
   * @throws Exception the exception
   */
  protected static WebClientUrl loadFromXml(EwsXmlReader reader)
      throws Exception {
    WebClientUrl webClientUrl = new WebClientUrl();

    do {
      reader.read();

      if (reader.getNodeType().getNodeType() == XmlNodeType.START_ELEMENT) {
        if (reader.getLocalName().equals(
            XmlElementNames.AuthenticationMethods)) {
          webClientUrl.setAuthenticationMethods(reader
              .readElementValue(String.class));
        } else if (reader.getLocalName().equals(XmlElementNames.Url)) {
          webClientUrl.setUrl(reader.readElementValue(String.class));
        }
      }
    } while (!reader.isEndElement(XmlNamespace.Autodiscover,
        XmlElementNames.WebClientUrl));

    return webClientUrl;
  }

  /**
   * Gets the authentication methods.
   *
   * @return the authentication methods
   */
  public String getAuthenticationMethods() {
    return this.authenticationMethods;
  }

  /**
   * Sets the authentication methods.
   *
   * @param value the new authentication methods
   */
  protected void setAuthenticationMethods(String value) {
    this.authenticationMethods = value;
  }

  /**
   * Gets the URL.
   *
   * @return the url
   */
  public String getUrl() {
    return this.url;
  }

  /**
   * Sets the url.
   *
   * @param value the new url
   */
  protected void setUrl(String value) {
    this.url = value;
  }

}
