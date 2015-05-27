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

import microsoft.exchange.webservices.data.attribute.EditorBrowsable;
import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.XmlAttributeNames;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.attribute.EditorBrowsableState;
import microsoft.exchange.webservices.data.core.enumeration.property.EmailAddressKey;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.property.MailboxType;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlSerializationException;

/**
 * Represents an entry of an EmailAddressDictionary.
 */
@EditorBrowsable(state = EditorBrowsableState.Never)
public final class EmailAddressEntry extends DictionaryEntryProperty<EmailAddressKey> implements
                                                                                      IComplexPropertyChangedDelegate {
  // / The email address.
  /**
   * The email address.
   */
  private EmailAddress emailAddress;

  /**
   * Initializes a new instance of the <see cref="EmailAddressEntry"/> class.
   */
  protected EmailAddressEntry() {
    super(EmailAddressKey.class);
    this.emailAddress = new EmailAddress();
    this.emailAddress.addOnChangeEvent(this);
  }

  /**
   * Initializes a new instance of the "EmailAddressEntry" class.
   *
   * @param key          The key.
   * @param emailAddress The email address.
   */
  protected EmailAddressEntry(EmailAddressKey key,
      EmailAddress emailAddress) {
    super(EmailAddressKey.class, key);
    this.emailAddress = emailAddress;
  }

  /**
   * Reads the attribute from XML.
   *
   * @param reader accepts EwsServiceXmlReader
   * @throws Exception throws Exception
   */
  @Override
  public void readAttributesFromXml(EwsServiceXmlReader reader)
      throws Exception {
    super.readAttributesFromXml(reader);
    this.getEmailAddress().setName(
        reader.readAttributeValue(XmlAttributeNames.Name));
    this
        .getEmailAddress()
        .setRoutingType(
            reader
                .readAttributeValue(XmlAttributeNames.
                    RoutingType));
    String mailboxTypeString = reader
        .readAttributeValue(XmlAttributeNames.MailboxType);
    if ((mailboxTypeString != null) && (!mailboxTypeString.isEmpty())) {
      this.getEmailAddress().setMailboxType(
          EwsUtilities.parse(MailboxType.class, mailboxTypeString));
    } else {
      this.getEmailAddress().setMailboxType(null);
    }
  }

  /**
   * Reads the text value from XML.
   *
   * @param reader accepts EwsServiceXmlReader
   * @throws Exception the exception
   */
  @Override
  public void readTextValueFromXml(EwsServiceXmlReader reader)
      throws Exception {
    this.getEmailAddress().setAddress(reader.readValue());
  }

  /**
   * Writes the attribute to XML.
   *
   * @param writer accepts EwsServiceXmlWriter
   * @throws ServiceXmlSerializationException throws ServiceXmlSerializationException
   */
  @Override
  public void writeAttributesToXml(EwsServiceXmlWriter writer)
      throws ServiceXmlSerializationException {
    super.writeAttributesToXml(writer);
    if (writer.getService().getRequestedServerVersion().ordinal() >
        ExchangeVersion.Exchange2007_SP1
            .ordinal()) {
      writer.writeAttributeValue(XmlAttributeNames.Name, this
          .getEmailAddress().getName());
      writer.writeAttributeValue(XmlAttributeNames.RoutingType, this
          .getEmailAddress().getRoutingType());
      if (this.getEmailAddress().getMailboxType() != MailboxType.Unknown) {
        writer.writeAttributeValue(XmlAttributeNames.MailboxType, this
            .getEmailAddress().getMailboxType());
      }
    }
  }

  /**
   * Writes elements to XML.
   *
   * @param writer accepts EwsServiceXmlWriter
   * @throws ServiceXmlSerializationException throws ServiceXmlSerializationException
   */
  @Override
  public void writeElementsToXml(EwsServiceXmlWriter writer)
      throws ServiceXmlSerializationException {
    writer.writeValue(this.getEmailAddress().getAddress(),
        XmlElementNames.EmailAddress);
  }

  /**
   * Gets the e-mail address of the entry.
   *
   * @return the email address
   */
  public EmailAddress getEmailAddress() {
    return this.emailAddress;
    // set { this.SetFieldValue<EmailAddress>(ref this.emailAddress, value);
    // }
  }

  /**
   * Sets the e-mail address of the entry.
   *
   * @param value the new email address
   */
  public void setEmailAddress(Object value) {
    //this.canSetFieldValue((EmailAddress) this.emailAddress, value);
    if (this.canSetFieldValue(this.emailAddress, value)) {
      this.emailAddress = (EmailAddress) value;
    }
  }

  /**
   * E-mail address was changed.
   *
   * @param complexProperty the complex property
   */
  private void emailAddressChanged(ComplexProperty complexProperty) {
    this.changed();
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * microsoft.exchange.webservices.ComplexPropertyChangedDelegateInterface
   * #complexPropertyChanged(microsoft.exchange.webservices.ComplexProperty)
   */
  @Override
  public void complexPropertyChanged(ComplexProperty complexProperty) {
    this.emailAddressChanged(complexProperty);

  }

}
