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
 * Represents the email Protocol connection settings for pop/imap/smtp
 * protocols.
 */
public final class ProtocolConnection {

  /**
   * The encryption method.
   */
  private String encryptionMethod;

  /**
   * The hostname.
   */
  private String hostname;

  /**
   * The port.
   */
  private int port;

  /**
   * Initializes a new instance of the {@link ProtocolConnection} class.
   */

  protected ProtocolConnection() {
  }

  /**
   * Read user setting with ProtocolConnection value.
   *
   * @param reader EwsServiceXmlReader
   * @return the protocol connection
   * @throws Exception the exception
   */
  protected static ProtocolConnection loadFromXml(EwsXmlReader reader)
      throws Exception {
    ProtocolConnection connection = new ProtocolConnection();

    do {
      reader.read();

      if (reader.getNodeType().getNodeType() == XmlNodeType.START_ELEMENT) {
        if (reader.getLocalName().equals(
            XmlElementNames.EncryptionMethod)) {
          connection.setEncryptionMethod(reader
              .readElementValue(String.class));
        } else if (reader.getLocalName().equals(
            XmlElementNames.Hostname)) {
          connection.setHostname(reader
              .readElementValue(String.class));
        } else if (reader.getLocalName().equals(XmlElementNames.Port)) {
          connection.setPort(reader.readElementValue(int.class));
        }
      }
    } while (!reader.isEndElement(XmlNamespace.Autodiscover,
        XmlElementNames.ProtocolConnection));

    return connection;
  }

  /**
   * Initializes a new instance of the ProtocolConnection class.
   *
   * @param encryptionMethod The encryption method.
   * @param hostname         The hostname.
   * @param port             The port number to use for the portocol.
   */
  protected ProtocolConnection(String encryptionMethod, String hostname,
      int port) {
    this.encryptionMethod = encryptionMethod;
    this.hostname = hostname;
    this.port = port;
  }

  /**
   * Gets the encryption method.
   *
   * @return The encryption method.
   */
  public String getEncryptionMethod() {
    return this.encryptionMethod;
  }

  /**
   * Sets the encryption method.
   *
   * @param value the new encryption method
   */
  public void setEncryptionMethod(String value) {
    this.encryptionMethod = value;
  }

  /**
   * Gets the hostname.
   *
   * @return The hostname.
   */
  public String getHostname() {
    return this.hostname;

  }

  /**
   * Sets the hostname.
   *
   * @param value the new hostname
   */
  public void setHostname(String value) {
    this.hostname = value;
  }

  /**
   * Gets the port number.
   *
   * @return The port number.
   */
  public int getPort() {
    return this.port;
  }

  /**
   * Sets the port.
   *
   * @param value the new port
   */
  public void setPort(int value) {
    this.port = value;
  }
}
