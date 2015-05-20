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
 * Defines the AlternateMailbox class.
 */
public final class AlternateMailbox {

  /**
   * The type.
   */
  private String type;

  /**
   * The display name.
   */
  private String displayName;

  /**
   * The legacy dn.
   */
  private String legacyDN;

  /**
   * The server.
   */
  private String server;

  /**
   * Initializes a new instance of the AlternateMailbox class.
   */
  private AlternateMailbox() {
  }

  /**
   * PLoads AlternateMailbox instance from XML.
   *
   * @param reader the reader
   * @return AlternateMailbox
   * @throws Exception the exception
   */
  public static AlternateMailbox loadFromXml(EwsXmlReader reader)
      throws Exception {
    AlternateMailbox altMailbox = new AlternateMailbox();

    do {
      reader.read();

      if (reader.getNodeType().getNodeType() == XmlNodeType.START_ELEMENT) {
        if (reader.getLocalName()
            .equalsIgnoreCase(XmlElementNames.Type)) {
          altMailbox.setType(reader.readElementValue(String.class));
        } else if (reader.getLocalName().equalsIgnoreCase(
            XmlElementNames.DisplayName)) {
          altMailbox.setDisplayName(reader
              .readElementValue(String.class));
        } else if (reader.getLocalName().equalsIgnoreCase(
            XmlElementNames.LegacyDN)) {
          altMailbox.setLegacyDN(reader
              .readElementValue(String.class));
        } else if (reader.getLocalName().equalsIgnoreCase(
            XmlElementNames.Server)) {
          altMailbox.setServer(reader.readElementValue(String.class));
        }
      }
    } while (!reader.isEndElement(XmlNamespace.Autodiscover,
        XmlElementNames.AlternateMailbox));

    return altMailbox;
  }

  /**
   * Gets the alternate mailbox type.
   *
   * @return the type
   */
  public String getType() {
    return type;
  }

  /**
   * Sets the type.
   *
   * @param type the new type
   */
  protected void setType(String type) {
    this.type = type;
  }

  /**
   * Gets the alternate mailbox display name.
   *
   * @return the display name
   */
  public String getDisplayName() {
    return displayName;
  }

  /**
   * Sets the display name.
   *
   * @param displayName the new display name
   */
  protected void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  /**
   * Gets the alternate mailbox legacy DN.
   *
   * @return the legacy dn
   */
  public String getLegacyDN() {
    return legacyDN;
  }

  /**
   * Sets the legacy dn.
   *
   * @param legacyDN the new legacy dn
   */
  protected void setLegacyDN(String legacyDN) {
    this.legacyDN = legacyDN;
  }

  /**
   * Gets the alernate mailbox server.
   *
   * @return the server
   */
  public String getServer() {
    return server;
  }

  /**
   * Sets the server.
   *
   * @param server the new server
   */
  protected void setServer(String server) {
    this.server = server;
  }

}
