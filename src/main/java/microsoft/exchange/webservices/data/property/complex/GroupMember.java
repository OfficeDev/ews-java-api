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

import microsoft.exchange.webservices.data.attribute.RequiredServerVersion;
import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.XmlAttributeNames;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.service.item.Contact;
import microsoft.exchange.webservices.data.core.enumeration.property.EmailAddressKey;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.property.MailboxType;
import microsoft.exchange.webservices.data.core.enumeration.property.MemberStatus;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceLocalException;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlSerializationException;

/**
 * Represents a group member.
 */
@RequiredServerVersion(version = ExchangeVersion.Exchange2010)
public class GroupMember extends ComplexProperty implements IComplexPropertyChangedDelegate {

  // AddressInformation field.
  /**
   * The address information.
   */
  private EmailAddress addressInformation;

  // Status field.

  /**
   * The status.
   */
  private MemberStatus status;

  // / Member key field.

  /**
   * The key.
   */
  private String key;

  /**
   * Initializes a new instance of the GroupMember class.
   */

  public GroupMember() {
    super();

    // Key is assigned by server
    this.key = null;

    // Member status is calculated by server
    this.status = MemberStatus.Unrecognized;
  }

  /**
   * Initializes a new instance of the GroupMember class.
   *
   * @param smtpAddress The SMTP address of the member
   */
  public GroupMember(String smtpAddress) {
    this();
    this.setAddressInformation(new EmailAddress(smtpAddress));
  }

  /**
   * Initializes a new instance of the GroupMember class.
   *
   * @param address     the address
   * @param routingType The routing type of the address.
   * @param mailboxType The mailbox type of the member.
   * @throws ServiceLocalException the service local exception
   */
  public GroupMember(String address, String routingType,
      MailboxType mailboxType) throws ServiceLocalException {
    this();

    switch (mailboxType) {
      case PublicGroup:
      case PublicFolder:
      case Mailbox:
      case Contact:
      case OneOff:
        this.setAddressInformation(new EmailAddress(null, address,
            routingType, mailboxType));
        break;

      default:
        throw new ServiceLocalException("The mailbox type isn't valid.");
    }
  }

  /**
   * Initializes a new instance of the GroupMember class.
   *
   * @param smtpAddress The SMTP address of the member
   * @param mailboxType The mailbox type of the member.
   * @throws ServiceLocalException the service local exception
   */
  public GroupMember(String smtpAddress, MailboxType mailboxType)
      throws ServiceLocalException {

    this(smtpAddress, EmailAddress.SmtpRoutingType, mailboxType);

  }

  /**
   * Initializes a new instance of the GroupMember class.
   *
   * @param name        The name of the one-off member.
   * @param address     the address
   * @param routingType The routing type of the address.
   */
  public GroupMember(String name, String address, String routingType) {
    this();

    this.setAddressInformation(new EmailAddress(name, address, routingType,
        MailboxType.OneOff));
  }

  /**
   * Initializes a new instance of the GroupMember class.
   *
   * @param name        The name of the one-off member.
   * @param smtpAddress The SMTP address of the member
   */
  public GroupMember(String name, String smtpAddress) {
    this(name, smtpAddress, EmailAddress.SmtpRoutingType);

  }

  /**
   * Initializes a new instance of the GroupMember class.
   *
   * @param contactGroupId The Id of the contact group to link the member to.
   */
  public GroupMember(ItemId contactGroupId) {
    this();

    this.setAddressInformation(new EmailAddress(null, null, null,
        MailboxType.ContactGroup, contactGroupId));
  }

  /**
   * Initializes a new instance of the GroupMember class.
   *
   * @param contactId     The Id of the contact member
   * @param addressToLink The Id of the contact to link the member to.
   */
  public GroupMember(ItemId contactId, String addressToLink) {
    this();

    this.setAddressInformation(new EmailAddress(null, addressToLink, null,
        MailboxType.Contact, contactId));
  }

  /**
   * Initializes a new instance of the GroupMember class.
   *
   * @param addressInformation The e-mail address of the member.
   * @throws Exception the exception
   */
  public GroupMember(EmailAddress addressInformation) throws Exception {
    this();

    this.setAddressInformation(new EmailAddress(addressInformation));
  }

  /**
   * Initializes a new instance of the GroupMember class.
   *
   * @param member GroupMember class instance to copy.
   * @throws Exception the exception
   */
  protected GroupMember(GroupMember member) throws Exception {
    this();

    EwsUtilities.validateParam(member, "member");
    this.setAddressInformation(new EmailAddress(member
        .getAddressInformation()));
  }

  /**
   * Initializes a new instance of the GroupMember class.
   *
   * @param contact         The contact to link to.
   * @param emailAddressKey The contact's e-mail address to link to.
   * @throws Exception the exception
   */
  public GroupMember(Contact contact, EmailAddressKey emailAddressKey)
      throws Exception {
    this();

    EwsUtilities.validateParam(contact, "contact");
    EmailAddress emailAddress = contact.getEmailAddresses()
        .getEmailAddress(emailAddressKey);
    this.setAddressInformation(new EmailAddress(emailAddress));
    this.getAddressInformation().setId(contact.getId());
  }

  /**
   * Gets the key of the member.
   *
   * @return the key
   */
  public String getKey() {

    return this.key;

  }

  /**
   * Gets the address information of the member.
   *
   * @return the address information
   */
  public EmailAddress getAddressInformation() {

    return this.addressInformation;
  }

  /**
   * Sets the address information.
   *
   * @param value the new address information
   */
  protected void setAddressInformation(EmailAddress value) {

    if (this.addressInformation != null) {

      this.addressInformation.removeChangeEvent(this);
    }

    this.addressInformation = value;

    if (this.addressInformation != null) {

      this.addressInformation.addOnChangeEvent(this);
    }
  }

  /**
   * Gets the status of the member.
   *
   * @return the status
   */

  public MemberStatus getStatus() {

    return this.status;

  }

  /**
   * Reads the member Key attribute from XML.
   *
   * @param reader the reader
   * @throws Exception the exception
   */
  public void readAttributesFromXml(EwsServiceXmlReader reader)
      throws Exception {
    this.key = reader.readAttributeValue(String.class,
        XmlAttributeNames.Key);
  }

  /**
   * Tries to read Status or Mailbox elements from XML.
   *
   * @param reader the reader
   * @return True if element was read.
   * @throws Exception the exception
   */
  public boolean tryReadElementFromXml(EwsServiceXmlReader reader)
      throws Exception {
    if (reader.getLocalName().equals(XmlElementNames.Status)) {

      this.status = EwsUtilities.parse(MemberStatus.class, reader
          .readElementValue());
      return true;
    } else if (reader.getLocalName().equals(XmlElementNames.Mailbox)) {

      this.setAddressInformation(new EmailAddress());
      this.getAddressInformation().loadFromXml(reader,
          reader.getLocalName());
      return true;
    } else {

      return false;
    }
  }

  /**
   * Writes the member key attribute to XML.
   *
   * @param writer the writer
   * @throws ServiceXmlSerializationException the service xml serialization exception
   */
  public void writeAttributesToXml(EwsServiceXmlWriter writer)
      throws ServiceXmlSerializationException {
    // if this.key is null or empty, writer skips the attribute
    writer.writeAttributeValue(XmlAttributeNames.Key, this.key);
  }

  /**
   * Writes elements to XML.
   *
   * @param writer the writer
   * @throws Exception the exception
   */
  public void writeElementsToXml(EwsServiceXmlWriter writer)
      throws Exception {
    // No need to write member Status back to server
    // Write only AddressInformation container element
    this.getAddressInformation().writeToXml(writer, XmlNamespace.Types,
        XmlElementNames.Mailbox);
  }

  /**
   * AddressInformation instance is changed.
   *
   * @param complexProperty Changed property.
   */
  private void addressInformationChanged(ComplexProperty complexProperty) {
    this.changed();
  }

  /**
   * Complex property changed.
   *
   * @param complexProperty accepts ComplexProperty
   */
  @Override
  public void complexPropertyChanged(ComplexProperty complexProperty) {

    this.addressInformationChanged(complexProperty);
  }
}
