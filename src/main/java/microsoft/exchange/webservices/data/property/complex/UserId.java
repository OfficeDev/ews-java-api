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

package microsoft.exchange.webservices.data.property.complex;

import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.property.StandardUser;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlSerializationException;

import javax.xml.stream.XMLStreamException;

/**
 * Represents the Id of a user.
 */
public class UserId extends ComplexProperty {

  /**
   * The s id.
   */
  private String sID;

  /**
   * The primary smtp address.
   */
  private String primarySmtpAddress;

  /**
   * The display name.
   */
  private String displayName;

  /**
   * The standard user.
   */
  private StandardUser standardUser;

  /**
   * Initializes a new instance.
   */
  public UserId() {
    super();
  }

  /**
   * Initializes a new instance.
   *
   * @param primarySmtpAddress the primary smtp address
   */
  public UserId(String primarySmtpAddress) {

    this.primarySmtpAddress = primarySmtpAddress;
  }

  /**
   * Initializes a new instance.
   *
   * @param standardUser the standard user
   */
  public UserId(StandardUser standardUser) {
    this();
    this.standardUser = standardUser;
  }

  /**
   * Determines whether this instance is valid.
   *
   * @return true, if this instance is valid. Else, false
   */
  protected boolean isValid() {
    return (this.standardUser != null ||
        !(this.primarySmtpAddress == null || this.primarySmtpAddress
            .isEmpty()) || !(this.sID == null ||
        this.sID.isEmpty()));
  }

  /**
   * Gets the SID of the user.
   *
   * @return the sID
   */
  public String getSID() {
    return this.sID;
  }

  /**
   * Sets the sID.
   *
   * @param sID the new sID
   */
  public void setSID(String sID) {
    if (this.canSetFieldValue(this.sID, sID)) {
      this.sID = sID;
      this.changed();
    }
  }

  /**
   * Gets the primary SMTP address or the user.
   *
   * @return the primary smtp address
   */
  public String getPrimarySmtpAddress() {
    return this.primarySmtpAddress;
  }

  /**
   * Sets the primary smtp address.
   *
   * @param primarySmtpAddress the new primary smtp address
   */
  public void setPrimarySmtpAddress(String primarySmtpAddress) {
    if (this.canSetFieldValue(this.primarySmtpAddress, primarySmtpAddress)) {
      this.primarySmtpAddress = primarySmtpAddress;
      this.changed();
    }

  }

  /**
   * Gets the display name of the user.
   *
   * @return the display name
   */
  public String getDisplayName() {
    return this.displayName;
  }

  /**
   * Sets the display name.
   *
   * @param displayName the new display name
   */
  public void setDisplayName(String displayName) {
    if (this.canSetFieldValue(this.displayName, displayName)) {
      this.displayName = displayName;
      this.changed();
    }
  }

  /**
   * Gets  a value indicating which standard user the user
   * represents.
   *
   * @return the standard user
   */
  public StandardUser getstandardUser() {
    return this.standardUser;
  }

  /**
   * Sets the standard user.
   *
   * @param standardUser the new standard user
   */
  public void setStandardUser(StandardUser standardUser) {
    if (this.canSetFieldValue(this.standardUser, standardUser)) {
      this.standardUser = standardUser;
      this.changed();
    }
  }

  /**
   * Implements an implicit conversion between a string representing a
   * primary SMTP address and UserId.
   *
   * @param primarySmtpAddress the primary smtp address
   * @return A UserId initialized with the specified primary SMTP address
   */
  public static UserId getUserId(String primarySmtpAddress) {
    return new UserId(primarySmtpAddress);
  }

  /**
   * Implements an implicit conversion between StandardUser and UserId.
   *
   * @param standardUser the standard user
   * @return A UserId initialized with the specified standard user value
   */
  public static UserId getUserIdFromStandardUser(StandardUser standardUser) {
    return new UserId(standardUser);
  }

  /**
   * Tries to read element from XML.
   *
   * @param reader the reader
   * @return True if element was read.
   * @throws Exception the exception
   */
  public boolean tryReadElementFromXml(EwsServiceXmlReader reader)
      throws Exception {
    if (reader.getLocalName().equals(XmlElementNames.SID)) {
      this.sID = reader.readValue();
      return true;
    } else if (reader.getLocalName().equals(
        XmlElementNames.PrimarySmtpAddress)) {
      this.primarySmtpAddress = reader.readValue();
      return true;
    } else if (reader.getLocalName().equals(XmlElementNames.DisplayName)) {
      this.displayName = reader.readValue();
      return true;
    } else if (reader.getLocalName().equals(
        XmlElementNames.DistinguishedUser)) {
      this.standardUser = reader.readValue(StandardUser.class);
      return true;
    } else {
      return false;
    }
  }

  /**
   * Writes elements to XML.
   *
   * @param writer the writer
   * @throws XMLStreamException the XML stream exception
   * @throws ServiceXmlSerializationException the service xml serialization exception
   */
  public void writeElementsToXml(EwsServiceXmlWriter writer)
      throws XMLStreamException, ServiceXmlSerializationException {
    writer.writeElementValue(XmlNamespace.Types, XmlElementNames.SID,
        this.sID);
    writer.writeElementValue(XmlNamespace.Types,
        XmlElementNames.PrimarySmtpAddress, this.primarySmtpAddress);
    writer.writeElementValue(XmlNamespace.Types,
        XmlElementNames.DisplayName, this.displayName);
    writer.writeElementValue(XmlNamespace.Types,
        XmlElementNames.DistinguishedUser, this.standardUser);
  }
}
