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

import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.ICustomXmlUpdateSerializer;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.service.ServiceObject;
import microsoft.exchange.webservices.data.core.service.item.Contact;
import microsoft.exchange.webservices.data.core.service.schema.ContactGroupSchema;
import microsoft.exchange.webservices.data.core.enumeration.property.EmailAddressKey;
import microsoft.exchange.webservices.data.core.enumeration.property.MailboxType;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceLocalException;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceValidationException;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlSerializationException;
import microsoft.exchange.webservices.data.property.definition.GroupMemberPropertyDefinition;
import microsoft.exchange.webservices.data.property.definition.PropertyDefinition;

import javax.xml.stream.XMLStreamException;

import java.util.Iterator;
import java.util.List;

/**
 * Represents a collection of members of GroupMember type.
 */
public final class GroupMemberCollection extends ComplexPropertyCollection<GroupMember> implements
                                                                                        ICustomXmlUpdateSerializer {
  /**
   * If the collection is cleared, then store PDL members collection is
   * updated with "SetItemField". If the collection is not cleared, then store
   * PDL members collection is updated with "AppendToItemField".
   */
  private boolean collectionIsCleared = false;

  /**
   * Initializes a new instance.
   */
  public GroupMemberCollection() {
    super();
  }

  /**
   * Retrieves the XML element name corresponding to the provided
   * GroupMember object.
   *
   * @param member the member
   * @return The XML element name corresponding to the provided GroupMember
   * object
   */
  @Override
  protected String getCollectionItemXmlElementName(GroupMember member) {
    return XmlElementNames.Member;
  }

  /**
   * * Finds the member with the specified key in the collection.Members that
   * have not yet been saved do not have a key.
   *
   * @param key the key
   * @return The member with the specified key
   * @throws Exception the exception
   */
  public GroupMember find(String key) throws Exception {
    EwsUtilities.validateParam(key, "key");

    for (GroupMember item : this.getItems()) {
      if (item.getKey().equals(key)) {
        return item;
      }
    }

    return null;
  }

  /**
   * Clears the collection.
   */
  public void clear() {
    // mark the whole collection for deletion
    this.internalClear();
    this.collectionIsCleared = true;
  }

  /**
   * Adds a member to the collection.
   *
   * @param member the member
   * @throws Exception the exception
   */
  public void add(GroupMember member) throws Exception {
    EwsUtilities.validateParam(member, "member");
    EwsUtilities.ewsAssert(member.getKey() == null, "GroupMemberCollection.Add", "member.Key is not null.");
    EwsUtilities.ewsAssert(!this.contains(member), "GroupMemberCollection.Add",
                           "The member is already in the collection");

    this.internalAdd(member);
  }

  /**
   * Adds multiple members to the collection.
   *
   * @param members the members
   * @throws Exception the exception
   */
  public void addRange(Iterator<GroupMember> members) throws Exception {
    EwsUtilities.validateParam(members, "members");
    while (members.hasNext()) {
      this.add(members.next());

    }
  }

  /**
   * Adds a member linked to a Contact Group.
   *
   * @param contactGroupId the contact group id
   * @throws Exception the exception
   */
  public void addContactGroup(ItemId contactGroupId) throws Exception {
    this.add(new GroupMember(contactGroupId));
  }

  /**
   * Adds a member linked to a specific contact?s e-mail address.
   *
   * @param contactId     the contact id
   * @param addressToLink the address to link
   * @throws Exception the exception
   */
  public void addPersonalContact(ItemId contactId, String addressToLink)
      throws Exception {
    this.add(new GroupMember(contactId, addressToLink));
  }

  /**
   * Adds a member linked to a contact?s first available e-mail address.
   *
   * @param contactId the contact id
   * @throws Exception the exception
   */
  public void addPersonalContact(ItemId contactId) throws Exception {
    this.addPersonalContact(contactId, null);
  }

  /**
   * Adds a member linked to an Active Directory user.
   *
   * @param smtpAddress the smtp address
   * @throws ServiceLocalException the service local exception
   * @throws Exception             the exception
   */
  public void addDirectoryUser(String smtpAddress)
      throws ServiceLocalException, Exception {
    this.addDirectoryUser(smtpAddress, new EmailAddress()
        .getSmtpRoutingType());
  }

  /**
   * Adds a member linked to an Active Directory user.
   *
   * @param address     the address
   * @param routingType the routing type
   * @throws ServiceLocalException the service local exception
   * @throws Exception             the exception
   */
  public void addDirectoryUser(String address, String routingType)
      throws ServiceLocalException, Exception {
    this.add(new GroupMember(address, routingType, MailboxType.Mailbox));
  }

  /**
   * Adds a member linked to an Active Directory contact.
   *
   * @param smtpAddress the smtp address
   * @throws ServiceLocalException the service local exception
   * @throws Exception             the exception
   */
  public void addDirectoryContact(String smtpAddress)
      throws ServiceLocalException, Exception {
    this.addDirectoryContact(smtpAddress, new EmailAddress()
        .getSmtpRoutingType());
  }

  /**
   * Adds a member linked to an Active Directory contact.
   *
   * @param address     the address
   * @param routingType the routing type
   * @throws ServiceLocalException the service local exception
   * @throws Exception             the exception
   */
  public void addDirectoryContact(String address, String routingType)
      throws ServiceLocalException, Exception {
    this.add(new GroupMember(address, routingType, MailboxType.Contact));
  }

  /**
   * Adds a member linked to a Public Group.
   *
   * @param smtpAddress the smtp address
   * @throws ServiceLocalException the service local exception
   * @throws Exception             the exception
   */
  public void addPublicGroup(String smtpAddress)
      throws ServiceLocalException, Exception {
    this.add(new GroupMember(smtpAddress, new EmailAddress()
        .getSmtpRoutingType(), MailboxType.PublicGroup));
  }

  /**
   * Adds a member linked to a mail-enabled Public Folder.
   *
   * @param smtpAddress the smtp address
   * @throws ServiceLocalException the service local exception
   * @throws Exception             the exception
   */
  public void addDirectoryPublicFolder(String smtpAddress)
      throws ServiceLocalException, Exception {
    this.add(new GroupMember(smtpAddress, new EmailAddress()
        .getSmtpRoutingType(), MailboxType.PublicFolder));
  }

  /**
   * Adds a one-off member.
   *
   * @param displayName the display name
   * @param address     the address
   * @param routingType the routing type
   * @throws Exception the exception
   */
  public void addOneOff(String displayName,
      String address, String routingType)
      throws Exception {
    this.add(new GroupMember(displayName, address, routingType));
  }

  /**
   * Adds a one-off member.
   *
   * @param displayName the display name
   * @param smtpAddress the smtp address
   * @throws Exception the exception
   */
  public void addOneOff(String displayName, String smtpAddress)
      throws Exception {
    this.addOneOff(displayName, smtpAddress, new EmailAddress()
        .getSmtpRoutingType());
  }

  /**
   * Adds a member that is linked to a specific e-mail address of a contact.
   *
   * @param contact         the contact
   * @param emailAddressKey the email address key
   * @throws Exception the exception
   */
  public void addContactEmailAddress(Contact contact,
      EmailAddressKey emailAddressKey) throws Exception {
    this.add(new GroupMember(contact, emailAddressKey));
  }

  /**
   * Removes a member at the specified index.
   *
   * @param index the index
   */
  public void removeAt(int index) {
    if (index < 0 || index >= this.getCount()) {
      throw new IllegalArgumentException("index", new Throwable("index is out of range."));

    }

    this.internalRemoveAt(index);
  }

  /**
   * Removes a member from the collection.
   *
   * @param member the member
   * @return True if the group member was successfully removed from the
   * collection, false otherwise.
   */
  public boolean remove(GroupMember member) {
    return this.internalRemove(member);
  }

  /**
   * Writes the update to XML.
   *
   * @param writer             the writer
   * @param ownerObject        the owner object
   * @param propertyDefinition the property definition
   * @return True if property generated serialization.
   * @throws Exception the exception
   */
  public boolean writeSetUpdateToXml(EwsServiceXmlWriter writer,
      ServiceObject ownerObject, PropertyDefinition propertyDefinition)
      throws Exception {
    if (this.collectionIsCleared) {

      if (!this.getAddedItems().isEmpty()) { // not visible

        // Delete the whole members collection
        this.writeDeleteMembersCollectionToXml(writer);
      } else {
        // The collection is cleared, so Set
        this.writeSetOrAppendMembersToXml(writer, this.getAddedItems(),
            true);
      }
    } else {
      // The collection is not cleared, i.e. dl.Members.Clear() is not
      // called.
      // Append AddedItems.
      this.writeSetOrAppendMembersToXml(writer, this.getAddedItems(),
          false);

      // Since member replacement is not supported by server
      // Delete old ModifiedItems, then recreate new instead.
      this.writeDeleteMembersToXml(writer, this.getModifiedItems());
      this.writeSetOrAppendMembersToXml(writer, this.getModifiedItems(),
          false);

      // Delete RemovedItems.
      this.writeDeleteMembersToXml(writer, this.getRemovedItems());
    }

    return true;
  }

  /**
   * Writes the deletion update to XML.
   *
   * @param writer    the writer
   * @param ewsObject the ews object
   * @return True if property generated serialization.
   */
  public boolean writeDeleteUpdateToXml(EwsServiceXmlWriter writer,
      ServiceObject ewsObject) {
    return false;
  }

  /**
   * Creates a GroupMember object from an XML element name.
   *
   * @param xmlElementName the xml element name
   * @return An GroupMember object
   */
  protected GroupMember createComplexProperty(String xmlElementName) {
    return new GroupMember();
  }

  /**
   * Clears the change log.
   */
  public void clearChangeLog() {
    super.clearChangeLog();
    this.collectionIsCleared = false;
  }

  /**
   * Delete the whole members collection.
   *
   * @param writer the writer
   * @throws XMLStreamException the XML stream exception
   * @throws ServiceXmlSerializationException the service xml serialization exception
   */
  private void writeDeleteMembersCollectionToXml(EwsServiceXmlWriter writer)
      throws XMLStreamException, ServiceXmlSerializationException {
    writer.writeStartElement(XmlNamespace.Types,
        XmlElementNames.DeleteItemField);
    ContactGroupSchema.Members.writeToXml(writer);
    writer.writeEndElement();
  }

  /**
   * Generate XML to delete individual members.
   *
   * @param writer  the writer
   * @param members the members
   * @throws XMLStreamException the XML stream exception
   * @throws ServiceXmlSerializationException the service xml serialization exception
   */
  private void writeDeleteMembersToXml(EwsServiceXmlWriter writer,
      List<GroupMember> members) throws XMLStreamException,
      ServiceXmlSerializationException {
    if (!members.isEmpty()) {
      GroupMemberPropertyDefinition memberPropDef =
          new GroupMemberPropertyDefinition();

      for (GroupMember member : members) {
        writer.writeStartElement(XmlNamespace.Types,
            XmlElementNames.DeleteItemField);

        memberPropDef.setKey(member.getKey());
        memberPropDef.writeToXml(writer);

        writer.writeEndElement(); // DeleteItemField
      }
    }
  }

  /**
   * Write set or append members to xml.
   *
   * @param writer  the writer
   * @param members the members
   * @param setMode the set mode
   * @throws Exception the exception
   */
  private void writeSetOrAppendMembersToXml(EwsServiceXmlWriter writer,
      List<GroupMember> members, boolean setMode) throws Exception {
    if (!members.isEmpty()) {
      writer.writeStartElement(XmlNamespace.Types,
          setMode ? XmlElementNames.SetItemField
              : XmlElementNames.AppendToItemField);

      ContactGroupSchema.Members.writeToXml(writer);

      writer.writeStartElement(XmlNamespace.Types,
          XmlElementNames.DistributionList);
      writer.writeStartElement(XmlNamespace.Types,
          XmlElementNames.Members);

      for (GroupMember member : members) {
        member.writeToXml(writer, XmlElementNames.Member);
      }

      writer.writeEndElement(); // Members
      writer.writeEndElement(); // Group
      writer.writeEndElement(); // setMode ? SetItemField :
      // AppendItemField
    }
  }

  /**
   * Validates this instance.
   *
   * @throws Exception
   */
  @Override
  protected void internalValidate() throws Exception {
    super.internalValidate();

    for (GroupMember groupMember : this.getModifiedItems()) {
      if (!(groupMember.getKey() == null || groupMember.getKey().isEmpty())) {
        throw new ServiceValidationException("The contact group's Members property must be reloaded before "
                                             + "newly-added members can be updated.");
      }
    }
  }
}
