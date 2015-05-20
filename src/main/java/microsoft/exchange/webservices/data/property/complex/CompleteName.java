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
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;

/**
 * Represents the complete name of a contact.
 */
public final class CompleteName extends ComplexProperty {

  /**
   * The title.
   */
  private String title;

  /**
   * The given name.
   */
  private String givenName;

  /**
   * The middle name.
   */
  private String middleName;

  /**
   * The surname.
   */
  private String surname;

  /**
   * The suffix.
   */
  private String suffix;

  /**
   * The initials.
   */
  private String initials;

  /**
   * The full name.
   */
  private String fullName;

  /**
   * The nickname.
   */
  private String nickname;

  /**
   * The yomi given name.
   */
  private String yomiGivenName;

  /**
   * The yomi surname.
   */
  private String yomiSurname;

  /**
   * Gets the contact's title.
   *
   * @return the title
   */
  public String getTitle() {
    return title;
  }

  /**
   * Gets the given name (first name) of the contact.
   *
   * @return the givenName
   */
  public String getGivenName() {
    return givenName;
  }

  /**
   * Gets the middle name of the contact.
   *
   * @return the middleName
   */
  public String getMiddleName() {
    return middleName;
  }

  /**
   * Gets the surname (last name) of the contact.
   *
   * @return the surname
   */
  public String getSurname() {
    return surname;
  }

  /**
   * Gets the suffix of the contact.
   *
   * @return the suffix
   */
  public String getSuffix() {
    return suffix;
  }

  /**
   * Gets the initials of the contact.
   *
   * @return the initials
   */
  public String getInitials() {
    return initials;
  }

  /**
   * Gets the full name of the contact.
   *
   * @return the fullName
   */
  public String getFullName() {
    return fullName;
  }

  /**
   * Gets the nickname of the contact.
   *
   * @return the nickname
   */
  public String getNickname() {
    return nickname;
  }

  /**
   * Gets the Yomi given name (first name) of the contact.
   *
   * @return the yomiGivenName
   */
  public String getYomiGivenName() {
    return yomiGivenName;
  }

  /**
   * Gets the Yomi surname (last name) of the contact.
   *
   * @return the yomiSurname
   */
  public String getYomiSurname() {
    return yomiSurname;
  }

  /**
   * Tries to read element from XML.
   *
   * @param reader The reader.
   * @return True if element was read.
   * @throws Exception the exception
   */
  @Override
  public boolean tryReadElementFromXml(EwsServiceXmlReader reader)
      throws Exception {

    if (reader.getLocalName().equalsIgnoreCase(XmlElementNames.Title)) {
      this.title = reader.readElementValue();
      return true;
    } else if (reader.getLocalName().equalsIgnoreCase(
        XmlElementNames.FirstName)) {
      this.givenName = reader.readElementValue();
      return true;
    } else if (reader.getLocalName().equalsIgnoreCase(
        XmlElementNames.MiddleName)) {
      this.middleName = reader.readElementValue();
      return true;
    } else if (reader.getLocalName().equalsIgnoreCase(
        XmlElementNames.LastName)) {
      this.surname = reader.readElementValue();
      return true;
    } else if (reader.getLocalName().equalsIgnoreCase(
        XmlElementNames.Suffix)) {
      this.suffix = reader.readElementValue();
      return true;
    } else if (reader.getLocalName().equalsIgnoreCase(
        XmlElementNames.Initials)) {
      this.initials = reader.readElementValue();
      return true;
    } else if (reader.getLocalName().equalsIgnoreCase(
        XmlElementNames.FullName)) {
      this.fullName = reader.readElementValue();
      return true;
    } else if (reader.getLocalName().equalsIgnoreCase(
        XmlElementNames.NickName)) {
      this.nickname = reader.readElementValue();
      return true;
    } else if (reader.getLocalName().equalsIgnoreCase(
        XmlElementNames.YomiFirstName)) {
      this.yomiGivenName = reader.readElementValue();
      return true;
    } else if (reader.getLocalName().equalsIgnoreCase(
        XmlElementNames.YomiLastName)) {
      this.yomiSurname = reader.readElementValue();
      return true;
    } else {
      return false;
    }
  }

  /**
   * Writes the elements to XML.
   *
   * @param writer accepts EwsServiceXmlWriter
   * @throws Exception throws Exception
   */
  @Override
  public void writeElementsToXml(EwsServiceXmlWriter writer)
      throws Exception {
    writer.writeElementValue(XmlNamespace.Types, XmlElementNames.Title,
        this.title);
    writer.writeElementValue(XmlNamespace.Types, XmlElementNames.FirstName,
        this.givenName);
    writer.writeElementValue(XmlNamespace.Types,
        XmlElementNames.MiddleName, this.middleName);
    writer.writeElementValue(XmlNamespace.Types, XmlElementNames.LastName,
        this.surname);
    writer.writeElementValue(XmlNamespace.Types, XmlElementNames.Suffix,
        this.suffix);
    writer.writeElementValue(XmlNamespace.Types, XmlElementNames.Initials,
        this.initials);
    writer.writeElementValue(XmlNamespace.Types, XmlElementNames.FullName,
        this.fullName);
    writer.writeElementValue(XmlNamespace.Types, XmlElementNames.NickName,
        this.nickname);
    writer.writeElementValue(XmlNamespace.Types,
        XmlElementNames.YomiFirstName, this.yomiGivenName);
    writer.writeElementValue(XmlNamespace.Types,
        XmlElementNames.YomiLastName, this.yomiSurname);
  }
}
