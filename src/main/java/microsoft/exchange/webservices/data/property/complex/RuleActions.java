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
import microsoft.exchange.webservices.data.core.enumeration.property.Importance;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.misc.MobilePhone;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Represents the set of actions available for a rule.
 */
public final class RuleActions extends ComplexProperty {

  /**
   * SMS recipient address type.
   */
  private static final String MobileType = "MOBILE";

  /**
   * The AssignCategories action.
   */
  private StringList assignCategories;

  /**
   * The CopyToFolder action.
   */
  private FolderId copyToFolder;

  /**
   * The Delete action.
   */
  private boolean delete;

  /**
   * The ForwardAsAttachmentToRecipients action.
   */
  private EmailAddressCollection forwardAsAttachmentToRecipients;

  /**
   * The ForwardToRecipients action.
   */
  private EmailAddressCollection forwardToRecipients;

  /**
   * The MarkImportance action.
   */
  private Importance markImportance;

  /**
   * The MarkAsRead action.
   */
  private boolean markAsRead;

  /**
   * The MoveToFolder action.
   */
  private FolderId moveToFolder;

  /**
   * The PermanentDelete action.
   */
  private boolean permanentDelete;

  /**
   * The RedirectToRecipients action.
   */
  private EmailAddressCollection redirectToRecipients;

  /**
   * The SendSMSAlertToRecipients action.
   */
  private Collection<MobilePhone> sendSMSAlertToRecipients;

  /**
   * The ServerReplyWithMessage action.
   */
  private ItemId serverReplyWithMessage;

  /**
   * The StopProcessingRules action.
   */
  private boolean stopProcessingRules;

  /**
   * Initializes a new instance of the RulePredicates class.
   */
  protected RuleActions() {
    super();
    this.assignCategories = new StringList();
    this.forwardAsAttachmentToRecipients =
        new EmailAddressCollection(XmlElementNames.Address);
    this.forwardToRecipients =
        new EmailAddressCollection(XmlElementNames.Address);
    this.redirectToRecipients =
        new EmailAddressCollection(XmlElementNames.Address);
    this.sendSMSAlertToRecipients = new ArrayList<MobilePhone>();
  }

  /**
   * Gets the categories that should be stamped on incoming messages.
   * To disable stamping incoming messages with categories, set
   * AssignCategories to null.
   */
  public StringList getAssignCategories() {

    return this.assignCategories;

  }

  /**
   * Gets or sets the Id of the folder incoming messages should be copied to.
   * To disable copying incoming messages
   * to a folder, set CopyToFolder to null.
   */
  public FolderId getCopyToFolder() {
    return this.copyToFolder;
  }

  public void setCopyToFolder(FolderId value) {
    if (this.canSetFieldValue(this.copyToFolder, value)) {
      this.copyToFolder = value;
      this.changed();
    }
  }

  /**
   * Gets or sets a value indicating whether incoming messages should be
   * automatically moved to the Deleted Items folder.
   */
  public boolean getDelete() {
    return this.delete;
  }

  public void setDelete(boolean value) {
    if (this.canSetFieldValue(this.delete, value)) {
      this.delete = value;
      this.changed();
    }

  }

  /**
   * Gets the e-mail addresses to which incoming messages should be
   * forwarded as attachments. To disable forwarding incoming messages
   * as attachments, empty the ForwardAsAttachmentToRecipients list.
   */
  public EmailAddressCollection getForwardAsAttachmentToRecipients() {
    return this.forwardAsAttachmentToRecipients;
  }

  /**
   * Gets the e-mail addresses to which
   * incoming messages should be forwarded.
   * To disable forwarding incoming messages,
   * empty the ForwardToRecipients list.
   */
  public EmailAddressCollection getForwardToRecipients() {
    return this.forwardToRecipients;

  }

  /**
   * Gets or sets the importance that should be stamped on incoming
   * messages. To disable the stamping of incoming messages with an
   * importance, set MarkImportance to null.
   */
  public Importance getMarkImportance() {
    return this.markImportance;
  }

  public void setMarkImportance(Importance value) {
    if (this.canSetFieldValue(this.markImportance, value)) {
      this.markImportance = value;
      this.changed();
    }
  }

  /**
   * Gets or sets a value indicating whether
   * incoming messages should be marked as read.
   */
  public boolean getMarkAsRead() {
    return this.markAsRead;
  }

  public void setMarkAsRead(boolean value) {
    if (this.canSetFieldValue(this.markAsRead, value)) {
      this.markAsRead = value;
      this.changed();
    }
  }

  /**
   * Gets or sets the Id of the folder to which incoming messages should be
   * moved. To disable the moving of incoming messages to a folder, set
   * CopyToFolder to null.
   */
  public FolderId getMoveToFolder() {
    return this.moveToFolder;
  }

  public void setMoveToFolder(FolderId value) {
    if (this.canSetFieldValue(this.moveToFolder, value)) {
      this.moveToFolder = value;
      this.changed();
    }

  }

  /**
   * Gets or sets a value indicating whether incoming messages should be
   * permanently deleted. When a message is permanently deleted, it is never
   * saved into the recipient's mailbox. To delete a message after it has
   * saved into the recipient's mailbox. To delete a message after it has
   */
  public boolean getPermanentDelete() {
    return this.permanentDelete;
  }

  public void setPermanentDelete(boolean value) {
    if (this.canSetFieldValue(this.permanentDelete, value)) {
      this.permanentDelete = value;
      this.changed();
    }
  }

  /**
   * Gets the e-mail addresses to which incoming messages should be
   * redirecteded. To disable redirection of incoming messages, empty
   * the RedirectToRecipients list. Unlike forwarded mail, redirected mail
   * maintains the original sender and recipients.
   */
  public EmailAddressCollection getRedirectToRecipients() {
    return this.redirectToRecipients;

  }

  /**
   * Gets the phone numbers to which an SMS alert should be sent. To disable
   * sending SMS alerts for incoming messages, empty the
   * SendSMSAlertToRecipients list.
   */
  public Collection<MobilePhone> getSendSMSAlertToRecipients() {
    return this.sendSMSAlertToRecipients;

  }

  /**
   * Gets or sets the Id of the template message that should be sent
   * as a reply to incoming messages. To disable automatic replies, set
   * ServerReplyWithMessage to null.
   */
  public ItemId getServerReplyWithMessage() {
    return this.serverReplyWithMessage;
  }

  public void setServerReplyWithMessage(ItemId value) {
    if (this.canSetFieldValue(this.serverReplyWithMessage, value)) {
      this.serverReplyWithMessage = value;
      this.changed();
    }
  }

  /**
   * Gets or sets a value indicating whether
   * subsequent rules should be evaluated.
   */
  public boolean getStopProcessingRules() {
    return this.stopProcessingRules;
  }

  public void setStopProcessingRules(boolean value) {
    if (this.canSetFieldValue(this.stopProcessingRules, value)) {
      this.stopProcessingRules = value;
      this.changed();
    }

  }

  /**
   * Tries to read element from XML.
   *
   * @param reader The reader.
   * @return True if element was read.
   * @throws Exception
   */
  @Override
  public boolean tryReadElementFromXml(EwsServiceXmlReader
      reader) throws Exception {
    if (reader.getLocalName().equals(XmlElementNames.CopyToFolder)) {
      reader.readStartElement(XmlNamespace.NotSpecified,
          XmlElementNames.FolderId);
      this.copyToFolder = new FolderId();
      this.copyToFolder.loadFromXml(reader, XmlElementNames.FolderId);
      reader.readEndElement(XmlNamespace.NotSpecified,
          XmlElementNames.CopyToFolder);
      return true;
    } else if (reader.getLocalName().equals(XmlElementNames.AssignCategories)) {
      this.assignCategories.loadFromXml(reader,
          reader.getLocalName());
      return true;
    } else if (reader.getLocalName().equals(XmlElementNames.Delete)) {
      this.delete = reader.readElementValue(Boolean.class);
      return true;
    } else if (reader.getLocalName().equals(XmlElementNames.ForwardAsAttachmentToRecipients)) {
      this.forwardAsAttachmentToRecipients.loadFromXml(reader,
          reader.getLocalName());
      return true;
    } else if (reader.getLocalName().equals(XmlElementNames.ForwardToRecipients)) {
      this.forwardToRecipients.loadFromXml(reader, reader.getLocalName());
      return true;
    } else if (reader.getLocalName().equals(XmlElementNames.MarkImportance)) {
      this.markImportance = reader.readElementValue(Importance.class);
      return true;
    } else if (reader.getLocalName().equals(XmlElementNames.MarkAsRead)) {
      this.markAsRead = reader.readElementValue(Boolean.class);
      return true;
    } else if (reader.getLocalName().equals(XmlElementNames.MoveToFolder)) {
      reader.readStartElement(XmlNamespace.NotSpecified,
          XmlElementNames.FolderId);
      this.moveToFolder = new FolderId();
      this.moveToFolder.loadFromXml(reader, XmlElementNames.FolderId);
      reader.readEndElement(XmlNamespace.NotSpecified,
          XmlElementNames.MoveToFolder);
      return true;
    } else if (reader.getLocalName().equals(XmlElementNames.PermanentDelete)) {
      this.permanentDelete = reader.readElementValue(Boolean.class);
      return true;
    } else if (reader.getLocalName().equals(XmlElementNames.RedirectToRecipients)) {
      this.redirectToRecipients.loadFromXml(reader,
          reader.getLocalName());
      return true;
    } else if (reader.getLocalName().equals(XmlElementNames.SendSMSAlertToRecipients)) {
      EmailAddressCollection smsRecipientCollection =
          new EmailAddressCollection(XmlElementNames.Address);
      smsRecipientCollection.loadFromXml(reader, reader.getLocalName());
      this.sendSMSAlertToRecipients = convertSMSRecipientsFromEmailAddressCollectionToMobilePhoneCollection(
          smsRecipientCollection);
      return true;
    } else if (reader.getLocalName().equals(XmlElementNames.ServerReplyWithMessage)) {
      this.serverReplyWithMessage = new ItemId();
      this.serverReplyWithMessage.loadFromXml(reader,
          reader.getLocalName());
      return true;
    } else if (reader.getLocalName().equals(XmlElementNames.StopProcessingRules)) {
      this.stopProcessingRules = reader.readElementValue(Boolean.class);
      return true;
    } else {
      return false;
    }

  }

  /**
   * Writes elements to XML.
   *
   * @param writer The writer.
   * @throws Exception
   */
  @Override
  public void writeElementsToXml(EwsServiceXmlWriter writer)
      throws Exception {
    if (this.getAssignCategories().getSize() > 0) {
      this.getAssignCategories().writeToXml(writer,
          XmlElementNames.AssignCategories);
    }

    if (this.getCopyToFolder() != null) {
      writer.writeStartElement(XmlNamespace.Types,
          XmlElementNames.CopyToFolder);
      this.getCopyToFolder().writeToXml(writer);
      writer.writeEndElement();
    }

    if (this.getDelete() != false) {
      writer.writeElementValue(
          XmlNamespace.Types,
          XmlElementNames.Delete,
          this.getDelete());
    }

    if (this.getForwardAsAttachmentToRecipients().getCount() > 0) {
      this.getForwardAsAttachmentToRecipients().writeToXml(writer,
          XmlElementNames.ForwardAsAttachmentToRecipients);
    }

    if (this.getForwardToRecipients().getCount() > 0) {
      this.getForwardToRecipients().writeToXml(writer,
          XmlElementNames.ForwardToRecipients);
    }

    if (this.getMarkImportance() != null) {
      writer.writeElementValue(
          XmlNamespace.Types,
          XmlElementNames.MarkImportance,
          this.getMarkImportance());
    }

    if (this.getMarkAsRead() != false) {
      writer.writeElementValue(
          XmlNamespace.Types,
          XmlElementNames.MarkAsRead,
          this.getMarkAsRead());
    }

    if (this.getMoveToFolder() != null) {
      writer.writeStartElement(XmlNamespace.Types,
          XmlElementNames.MoveToFolder);
      this.getMoveToFolder().writeToXml(writer);
      writer.writeEndElement();
    }

    if (this.getPermanentDelete() != false) {
      writer.writeElementValue(
          XmlNamespace.Types,
          XmlElementNames.PermanentDelete,
          this.getPermanentDelete());
    }

    if (this.getRedirectToRecipients().getCount() > 0) {
      this.getRedirectToRecipients().writeToXml(writer,
          XmlElementNames.RedirectToRecipients);
    }

    if (this.getSendSMSAlertToRecipients().size() > 0) {
      EmailAddressCollection emailCollection =
          convertSMSRecipientsFromMobilePhoneCollectionToEmailAddressCollection(
              this.getSendSMSAlertToRecipients());
      emailCollection.writeToXml(writer,
          XmlElementNames.SendSMSAlertToRecipients);
    }

    if (this.getServerReplyWithMessage() != null) {
      this.getServerReplyWithMessage().writeToXml(writer,
          XmlElementNames.ServerReplyWithMessage);
    }

    if (this.getStopProcessingRules() != false) {
      writer.writeElementValue(
          XmlNamespace.Types,
          XmlElementNames.StopProcessingRules,
          this.getStopProcessingRules());
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
    EwsUtilities.validateParam(this.forwardAsAttachmentToRecipients, "ForwardAsAttachmentToRecipients");
    EwsUtilities.validateParam(this.forwardToRecipients,
        "ForwardToRecipients");
    EwsUtilities.validateParam(this.redirectToRecipients,
        "RedirectToRecipients");
    for (MobilePhone sendSMSAlertToRecipient : this.sendSMSAlertToRecipients) {
      EwsUtilities.validateParam(sendSMSAlertToRecipient,
          "SendSMSAlertToRecipient");
    }
  }

  /**
   * Convert the SMS recipient list from
   * EmailAddressCollection type to MobilePhone collection type.
   *
   * @return A MobilePhone collection object
   * containing all SMS recipient in MobilePhone type.
   */
  private static Collection<MobilePhone> convertSMSRecipientsFromEmailAddressCollectionToMobilePhoneCollection(
      EmailAddressCollection emailCollection) {
    Collection<MobilePhone> mobilePhoneCollection =
        new ArrayList<MobilePhone>();
    for (EmailAddress emailAddress : emailCollection) {
      mobilePhoneCollection.add(new MobilePhone(emailAddress.getName(),
          emailAddress.getAddress()));
    }

    return mobilePhoneCollection;
  }

  /**
   * Convert the SMS recipient list from MobilePhone
   * collection type to EmailAddressCollection type.
   *
   * @return An EmailAddressCollection object
   * containing recipients with "MOBILE" address type.
   */
  private static EmailAddressCollection convertSMSRecipientsFromMobilePhoneCollectionToEmailAddressCollection(
      Collection<MobilePhone> recipientCollection) {
    EmailAddressCollection emailCollection =
        new EmailAddressCollection(XmlElementNames.Address);
    for (MobilePhone recipient : recipientCollection) {
      EmailAddress emailAddress = new EmailAddress(
          recipient.getName(),
          recipient.getPhoneNumber(),
          RuleActions.MobileType);
      emailCollection.add(emailAddress);
    }

    return emailCollection;
  }

}

