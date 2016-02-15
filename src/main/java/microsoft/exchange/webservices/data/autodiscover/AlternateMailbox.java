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
   * The SMTP address of alternate mailbox. It is only set if it is available
   * for the given type. E.g. type 'Delegate' has one type 'Archive' not.
   */
  private String smtpAddress;


  /**
   * The SMTP address of the owner of this alternate mailbox.
   */
  private String ownerSmtpAddress;

  /**
   * Initializes a new instance of the AlternateMailbox class.
   */
  private AlternateMailbox() {}

  /**
   * PLoads AlternateMailbox instance from XML.
   *
   * @param reader the reader
   * @return AlternateMailbox
   * @throws Exception the exception
   */
  public static AlternateMailbox loadFromXml(final EwsXmlReader reader)
      throws Exception {
    final AlternateMailbox altMailbox = new AlternateMailbox();

    do {
      reader.read();

      if (reader.getNodeType().getNodeType() == XmlNodeType.START_ELEMENT) {
        if (reader.getLocalName().equalsIgnoreCase(XmlElementNames.Type)) {
          altMailbox.setType(reader.readElementValue(String.class));
        } else if (reader.getLocalName()
            .equalsIgnoreCase(XmlElementNames.DisplayName)) {
          altMailbox.setDisplayName(reader.readElementValue(String.class));
        } else if (reader.getLocalName()
            .equalsIgnoreCase(XmlElementNames.LegacyDN)) {
          altMailbox.setLegacyDN(reader.readElementValue(String.class));
        } else
          if (reader.getLocalName().equalsIgnoreCase(XmlElementNames.Server)) {
          altMailbox.setServer(reader.readElementValue(String.class));
        } else if (reader.getLocalName()
            .equalsIgnoreCase(XmlElementNames.SmtpAddress)) {
          altMailbox.setSmtpAddress(reader.readElementValue(String.class));
        } else if (reader.getLocalName()
            .equalsIgnoreCase(XmlElementNames.OwnerSmtpAddress)) {
          altMailbox.setOwnerSmtpAddress(reader.readElementValue(String.class));
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
  protected void setType(final String type) {
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
  protected void setDisplayName(final String displayName) {
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
  protected void setLegacyDN(final String legacyDN) {
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
   * @param server the new server.
   */
  protected void setServer(final String server) {
    this.server = server;
  }

  /**
   * Gets the SMTP address.
   *
   * @return the SMTP address if available for the mailbox type otherwise null
   *         is returned.
   */
  public String getSmtpAddress() {
    return smtpAddress;
  }

  /**
   * Sets the SMTP address.
   *
   * @param smtpAddress the new SMTP address.
   */
  protected void setSmtpAddress(final String smtpAddress) {
    this.smtpAddress = smtpAddress;
  }

  /**
   * Gets the owner SMTP address.
   *
   * @return the SMTP address of the owner of this mailbox.
   */
  public String getOwnerSmtpAddress() {
    return ownerSmtpAddress;
  }

  /**
   * Sets the owner SMTP address.
   *
   * @param ownerSmtpAdress the new owner SMTP address
   */
  protected void setOwnerSmtpAddress(final String ownerSmtpAddress) {
    this.ownerSmtpAddress = ownerSmtpAddress;
  }

}
