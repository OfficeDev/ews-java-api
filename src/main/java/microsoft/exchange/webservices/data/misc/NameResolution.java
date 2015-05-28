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

package microsoft.exchange.webservices.data.misc;

import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.PropertySet;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.service.item.Contact;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.property.complex.EmailAddress;

/**
 * Represents a suggested name resolution.
 */
public final class NameResolution {

  /**
   * The owner.
   */
  private NameResolutionCollection owner;

  /**
   * The mailbox.
   */
  private EmailAddress mailbox = new EmailAddress();

  /**
   * The contact.
   */
  private Contact contact;

  /**
   * Initializes a new instance of the class.
   *
   * @param owner the owner
   */
  protected NameResolution(NameResolutionCollection owner) {
    EwsUtilities.ewsAssert(owner != null, "NameResolution.ctor", "owner is null.");

    this.owner = owner;
  }

  /**
   * Loads from XML.
   *
   * @param reader the reader
   * @throws Exception the exception
   */
  protected void loadFromXml(EwsServiceXmlReader reader) throws Exception {
    reader.readStartElement(XmlNamespace.Types, XmlElementNames.Resolution);
    reader.readStartElement(XmlNamespace.Types, XmlElementNames.Mailbox);
    this.mailbox.loadFromXml(reader, XmlElementNames.Mailbox);

    reader.read();
    if (reader.isStartElement(XmlNamespace.Types, XmlElementNames.Contact)) {
      this.contact = new Contact(this.owner.getSession());
      this.contact.loadFromXml(reader, true /* clearPropertyBag */,
          PropertySet.FirstClassProperties,
          false /* summaryPropertiesOnly */);

      reader.readEndElement(XmlNamespace.Types,
          XmlElementNames.Resolution);
    } else {
      reader.ensureCurrentNodeIsEndElement(XmlNamespace.Types,
          XmlElementNames.Resolution);
    }
  }

  /**
   * Gets the mailbox of the suggested resolved name.
   *
   * @return the mailbox
   */
  public EmailAddress getMailbox() {
    return this.mailbox;
  }

  /**
   * Gets the contact information of the suggested resolved name. This
   * property is only available when ResolveName is called with
   * returnContactDetails = true.
   *
   * @return the contact
   */
  public Contact getContact() {
    return this.contact;
  }
}
