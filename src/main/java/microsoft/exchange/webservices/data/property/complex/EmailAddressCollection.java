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

import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.XmlElementNames;

import java.util.Iterator;

/**
 * Represents a collection of e-mail addresses.
 */
public final class EmailAddressCollection extends ComplexPropertyCollection<EmailAddress> {

  //XML element name
  private String collectionItemXmlElementName;

  /**
   * Initializes a new instance.
   */
  public EmailAddressCollection() {
    this(XmlElementNames.Mailbox);
  }

  /**
   * Initializes a new instance of the EmailAddressCollection class.
   *
   * @param collectionItemXmlElementName Name of the collection item XML element.
   */
  protected EmailAddressCollection(String collectionItemXmlElementName) {
    super();
    this.collectionItemXmlElementName = collectionItemXmlElementName;
  }

  /**
   * Adds an e-mail address to the collection.
   *
   * @param emailAddress The e-mail address to add.
   */
  public void add(EmailAddress emailAddress) {
    this.internalAdd(emailAddress);
  }

  /**
   * Adds multiple e-mail addresses to the collection.
   *
   * @param emailAddresses The e-mail addresses to add.
   */
  public void addEmailRange(Iterator<EmailAddress> emailAddresses) {
    if (null != emailAddresses) {
      while (emailAddresses.hasNext()) {
        this.add(emailAddresses.next());
      }
    }
  }

  /**
   * Adds an e-mail address to the collection.
   *
   * @param smtpAddress The SMTP address used to initialize the e-mail address.
   * @return An EmailAddress object initialized with the provided SMTP
   * address.
   */
  public EmailAddress add(String smtpAddress) {
    EmailAddress emailAddress = new EmailAddress(smtpAddress);
    this.add(emailAddress);
    return emailAddress;
  }

  /**
   * Adds multiple e-mail addresses to the collection.
   *
   * @param smtpAddresses The SMTP addresses used to initialize the e-mail addresses.
   */
  public void addSmtpAddressRange(Iterator<String> smtpAddresses) {
    if (null != smtpAddresses) {
      while (smtpAddresses.hasNext()) {
        this.add(smtpAddresses.next());
      }
    }
  }

  /**
   * Adds an e-mail address to the collection.
   *
   * @param name        The name used to initialize the e-mail address.
   * @param smtpAddress The SMTP address used to initialize the e-mail address.
   * @return An EmailAddress object initialized with the provided SMTP
   * address.
   */
  public EmailAddress add(String name, String smtpAddress) {
    EmailAddress emailAddress = new EmailAddress(name, smtpAddress);
    this.add(emailAddress);
    return emailAddress;
  }

  /**
   * Clears the collection.
   */
  public void clear() {
    this.internalClear();
  }

  /**
   * Removes an e-mail address from the collection.
   *
   * @param index The index of the e-mail address to remove.
   */
  public void removeAt(int index) {
    if (index < 0 || index >= this.getCount()) {
      throw new IllegalArgumentException(
          String.format("index %d is out of range [0..%d[.", index, this.getCount())
      );
    }

    this.internalRemoveAt(index);
  }

  /**
   * Removes an e-mail address from the collection.
   *
   * @param emailAddress The e-mail address to remove.
   * @return True if the email address was successfully removed from the
   * collection, false otherwise.
   * @throws Exception the exception
   */
  public boolean remove(EmailAddress emailAddress) throws Exception {
    EwsUtilities.validateParam(emailAddress, "emailAddress");
    return this.internalRemove(emailAddress);
  }

  /**
   * Creates an EmailAddress object from an XML element name.
   *
   * @param xmlElementName The XML element name from which to create the e-mail address.
   * @return An EmailAddress object.
   */
  @Override
  protected EmailAddress createComplexProperty(String xmlElementName) {
    if (xmlElementName.equals(this.collectionItemXmlElementName)) {
      return new EmailAddress();
    } else {
      return null;
    }
  }

  /**
   * Retrieves the XML element name corresponding to the provided EmailAddress
   * object.
   *
   * @param complexProperty The EmailAddress object from which to determine the XML
   *                        element name.
   * @return The XML element name corresponding to the provided EmailAddress
   * object.
   */
  @Override
  protected String getCollectionItemXmlElementName(
      EmailAddress complexProperty) {
    return this.collectionItemXmlElementName;
  }

  /**
   * Determine whether we should write collection to XML or not.
   *
   * @return Always true, even if the collection is empty.
   */
  @Override
  public boolean shouldWriteToXml() {
    return true;
  }
}
