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
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.property.MailboxType;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Represents an e-mail address.
 */
public class EmailAddress extends ComplexProperty implements ISearchStringProvider {

  private static final Log LOG = LogFactory.getLog(EmailAddress.class);

  // SMTP routing type.
  /**
   * The Constant SmtpRoutingType.
   */
  protected final static String SmtpRoutingType = "SMTP";

  // / Display name.
  /**
   * The name.
   */
  private String name;

  // / Email address.
  /**
   * The address.
   */
  private String address;

  // / Routing type.
  /**
   * The routing type.
   */
  private String routingType;

  // / Mailbox type.
  /**
   * The mailbox type.
   */
  private MailboxType mailboxType;

  // / ItemId - Contact or PDL.
  /**
   * The id.
   */
  private ItemId id;

  /**
   * Initializes a new instance.
   */
  public EmailAddress() {
    super();
  }

  /**
   * Initializes a new instance.
   *
   * @param smtpAddress The SMTP address used to initialize the EmailAddress.
   */
  public EmailAddress(String smtpAddress) {
    this();
    this.address = smtpAddress;
  }

  /**
   * Initializes a new instance.
   *
   * @param name        The name used to initialize the EmailAddress.
   * @param smtpAddress The SMTP address used to initialize the EmailAddress.
   */
  public EmailAddress(String name, String smtpAddress) {
    this(smtpAddress);
    this.name = name;
  }

  /**
   * Initializes a new instance.
   *
   * @param name        The name used to initialize the EmailAddress.
   * @param address     The address used to initialize the EmailAddress.
   * @param routingType The routing type used to initialize the EmailAddress.
   */
  public EmailAddress(String name, String address, String routingType) {
    this(name, address);
    this.routingType = routingType;
  }

  /**
   * Initializes a new instance.
   *
   * @param name        The name used to initialize the EmailAddress.
   * @param address     The address used to initialize the EmailAddress.
   * @param routingType The routing type used to initialize the EmailAddress.
   * @param mailboxType Mailbox type of the participant.
   */
  protected EmailAddress(String name, String address, String routingType,
      MailboxType mailboxType) {
    this(name, address, routingType);
    this.mailboxType = mailboxType;
  }

  /**
   * Initializes a new instance.
   *
   * @param name        The name used to initialize the EmailAddress.
   * @param address     The address used to initialize the EmailAddress.
   * @param routingType The routing type used to initialize the EmailAddress.
   * @param mailboxType Mailbox type of the participant.
   * @param id          ItemId of a Contact or PDL.
   */
  protected EmailAddress(String name, String address, String routingType,
      MailboxType mailboxType, ItemId id) {
    this(name, address, routingType);
    this.mailboxType = mailboxType;
    this.id = id;
  }

  /**
   * Initializes a new instance from another EmailAddress instance.
   *
   * @param mailbox EMailAddress instance to copy.
   * @throws Exception the exception
   */
  protected EmailAddress(EmailAddress mailbox) throws Exception {
    this();
    EwsUtilities.validateParam(mailbox, "mailbox");
    this.name = mailbox.getName();
    this.address = mailbox.getAddress();
    this.routingType = mailbox.getRoutingType();
    this.mailboxType = mailbox.getMailboxType();
    this.setId(mailbox.getId());

  }

  /**
   * Gets the name associated with the e-mail address.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name associated with the e-mail address.
   *
   * @param name the new name
   */
  public void setName(String name) {
    if (this.canSetFieldValue(this.name, name)) {
      this.name = name;
      this.changed();
    }
  }

  /**
   * Gets the actual address associated with the e-mail address.
   *
   * @return address associated with the e-mail address.
   */
  public String getAddress() {
    return address;
  }

  /**
   * Sets the actual address associated with the e-mail address. The type of
   * the Address property must match the specified routing type. If
   * RoutingType is not set, Address is assumed to be an SMTP address.
   *
   * @param address address associated with the e-mail address.
   */
  public void setAddress(String address) {

    if (this.canSetFieldValue(this.address, address)) {
      this.address = address;
      this.changed();
    }

  }

  /**
   * Gets the routing type associated with the e-mail address.
   *
   * @return the routing type
   */
  public String getRoutingType() {
    return routingType;
  }

  /**
   * Sets the routing type associated with the e-mail address. If RoutingType
   * is not set, Address is assumed to be an SMTP address.
   *
   * @param routingType routing type associated with the e-mail address.
   */
  public void setRoutingType(String routingType) {
    if (this.canSetFieldValue(this.routingType, routingType)) {
      this.routingType = routingType;
      this.changed();
    }
  }

  /**
   * Gets the type of the e-mail address.
   *
   * @return type of the e-mail address.
   */
  public MailboxType getMailboxType() {
    return mailboxType;
  }

  /**
   * Sets the type of the e-mail address.
   *
   * @param mailboxType the new mailbox type
   */
  public void setMailboxType(MailboxType mailboxType) {
    if (this.canSetFieldValue(this.mailboxType, mailboxType)) {
      this.mailboxType = mailboxType;
      this.changed();
    }
  }

  /**
   * Gets the Id of the contact the e-mail address represents.
   *
   * @return the id
   */
  public ItemId getId() {
    return id;
  }

  /**
   * Sets the Id of the contact the e-mail address represents. When Id is
   * specified, Address should be set to null.
   *
   * @param id the new id
   */
  public void setId(ItemId id) {

    if (this.canSetFieldValue(this.id, id)) {
      this.id = id;
      this.changed();
    }
  }

  /**
   * Defines an implicit conversion between a string representing an SMTP
   * address and EmailAddress.
   *
   * @param smtpAddress The SMTP address to convert to EmailAddress.
   * @return An EmailAddress initialized with the specified SMTP address.
   */
  public static EmailAddress getEmailAddressFromString(String smtpAddress) {
    return new EmailAddress(smtpAddress);
  }

  /**
   * Try read element from xml.
   *
   * @param reader accepts EwsServiceXmlReader
   * @return true
   * @throws Exception throws Exception
   */
  public boolean tryReadElementFromXml(EwsServiceXmlReader reader)
      throws Exception {
    try {
      if (reader.getLocalName().equals(XmlElementNames.Name)) {
        this.name = reader.readElementValue();
        return true;
      } else if (reader.getLocalName().equals(
          XmlElementNames.EmailAddress)) {
        this.address = reader.readElementValue();
        return true;
      } else if (reader.getLocalName()
          .equals(XmlElementNames.RoutingType)) {
        this.routingType = reader.readElementValue();
        return true;
      } else if (reader.getLocalName()
          .equals(XmlElementNames.MailboxType)) {
        this.mailboxType = reader.readElementValue(MailboxType.class);
        return true;
      } else if (reader.getLocalName().equals(XmlElementNames.ItemId)) {
        this.id = new ItemId();
        this.id.loadFromXml(reader, reader.getLocalName());
        return true;
      } else {
        return false;
      }
    } catch (Exception e) {
      LOG.error(e);
      return false;
    }
  }

  /**
   * Writes elements to XML.
   *
   * @param writer The writer.
   * @throws Exception the exception
   */
  @Override
  public void writeElementsToXml(EwsServiceXmlWriter writer)
      throws Exception {
    writer.writeElementValue(XmlNamespace.Types, XmlElementNames.Name, this
        .getName());
    writer.writeElementValue(XmlNamespace.Types,
        XmlElementNames.EmailAddress, this.getAddress());
    writer.writeElementValue(XmlNamespace.Types,
        XmlElementNames.RoutingType, this.getRoutingType());
    writer.writeElementValue(XmlNamespace.Types,
        XmlElementNames.MailboxType, this.getMailboxType());

    if (this.getId() != null) {
      this.getId().writeToXml(writer, XmlElementNames.ItemId);
    }

  }

  /**
   * Get a string representation for using this instance in a search filter.
   *
   * @return String representation of instance.
   */
  @Override
  public String getSearchString() {
    return this.getAddress();
  }

  /**
   * Returns string that represents the current instance.
   *
   * @return String representation of instance.
   */
  @Override
  public String toString() {
    String addressPart;

    if (null == this.getAddress() || this.getAddress().isEmpty()) {
      return "";
    }

    if (null != this.getRoutingType() && this.getRoutingType().isEmpty()) {
      addressPart = this.getRoutingType() + ":" + this.getAddress();
    } else {
      addressPart = this.getAddress();
    }

    if (null != this.getName() && !this.getName().isEmpty()) {
      return this.getName() + " <" + addressPart + ">";
    } else {
      return addressPart;
    }
  }

  /**
   * Gets the routing type.
   *
   * @return SMTP Routing type
   */
  protected String getSmtpRoutingType() {
    return SmtpRoutingType;
  }

}
