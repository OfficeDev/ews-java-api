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

package microsoft.exchange.webservices.data.credential;

import microsoft.exchange.webservices.data.core.request.HttpWebRequest;
import microsoft.exchange.webservices.data.core.exception.misc.InvalidOperationException;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import java.io.ByteArrayOutputStream;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Base class of Exchange credential types.
 */
public abstract class ExchangeCredentials {

  /**
   * Performs an implicit conversion from <see
   * cref="System.Net.NetworkCredential"/> to <see
   * cref="Microsoft.Exchange.WebServices.Data.ExchangeCredentials"/>. This
   * allows a NetworkCredential object to be implictly converted to an
   * ExchangeCredential which is useful when setting credential on an
   * ExchangeService.
   *
   * @param userName Account user name.
   * @param password Account password.
   * @param domain   Account domain.
   * @return The result of the conversion.
   */
  public static ExchangeCredentials
  getExchangeCredentialsFromNetworkCredential(
      String userName, String password, String domain) {
    return new WebCredentials(userName, password, domain);
  }


  /**
   * Return the url without ws-security address.
   *
   * @param url The url
   * @return The absolute uri base.
   */
  protected static String getUriWithoutWSSecurity(URI url) {
    String absoluteUri = url.toString();
    int index = absoluteUri.indexOf("/wssecurity");

    if (index == -1) {
      return absoluteUri;
    } else {
      return absoluteUri.substring(0, index);
    }
  }

  /**
   * This method is called to pre-authenticate credential before a service
   * request is made.
   */
  public void preAuthenticate() {
    // do nothing by default.
  }

  /**
   * This method is called to apply credential to a service request before
   * the request is made.
   *
   * @param client The request.
   * @throws java.net.URISyntaxException the uRI syntax exception
   */
  public void prepareWebRequest(HttpWebRequest client)
      throws URISyntaxException {
    // do nothing by default.
  }

  /**
   * Emit any extra necessary namespace aliases for the SOAP:header block.
   *
   * @param writer the writer
   * @throws XMLStreamException the XML stream exception
   */
  public void emitExtraSoapHeaderNamespaceAliases(XMLStreamWriter writer)
      throws XMLStreamException {
    // do nothing by default.
  }

  /**
   * Serialize any extra necessary SOAP headers. This is used for
   * authentication schemes that rely on WS-Security, or for endpoints
   * requiring WS-Addressing.
   *
   * @param writer the writer
   * @param webMethodName the Web method being called
   * @throws XMLStreamException the XML stream exception
   */
  public void serializeExtraSoapHeaders(XMLStreamWriter writer, String webMethodName) throws XMLStreamException {
    // do nothing by default.
  }

  /**
   * Adjusts the URL endpoint based on the credential.
   *
   * @param url The URL.
   * @return Adjust URL.
   */
  public URI adjustUrl(URI url) throws URISyntaxException {
    return new URI(getUriWithoutWSSecurity(url));
  }

  /**
   * Gets the flag indicating whether any sign action need taken.
   */
  public boolean isNeedSignature() {
    return false;
  }

  /**
   * Add the signature element to the memory stream.
   *
   * @param memoryStream The memory stream.
   */
  public void sign(ByteArrayOutputStream memoryStream) throws Exception {
    throw new InvalidOperationException();
  }



  /**
   * Serialize SOAP headers used for authentication schemes that rely on WS-Security.
   *
   * @param writer the writer
   * @throws XMLStreamException the XML stream exception
   */
  public void serializeWSSecurityHeaders(XMLStreamWriter writer)
      throws XMLStreamException {
    // do nothing by default.
  }

}
