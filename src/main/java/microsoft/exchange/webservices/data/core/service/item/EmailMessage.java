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

package microsoft.exchange.webservices.data.core.service.item;

import microsoft.exchange.webservices.data.attribute.Attachable;
import microsoft.exchange.webservices.data.attribute.ServiceObjectDefinition;
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.PropertySet;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.service.response.ResponseMessage;
import microsoft.exchange.webservices.data.core.service.response.SuppressReadReceipt;
import microsoft.exchange.webservices.data.core.service.schema.EmailMessageSchema;
import microsoft.exchange.webservices.data.core.service.schema.ServiceObjectSchema;
import microsoft.exchange.webservices.data.core.enumeration.service.ConflictResolutionMode;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.service.MessageDisposition;
import microsoft.exchange.webservices.data.core.enumeration.service.ResponseMessageType;
import microsoft.exchange.webservices.data.core.enumeration.property.WellKnownFolderName;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceLocalException;
import microsoft.exchange.webservices.data.property.complex.EmailAddress;
import microsoft.exchange.webservices.data.property.complex.EmailAddressCollection;
import microsoft.exchange.webservices.data.property.complex.FolderId;
import microsoft.exchange.webservices.data.property.complex.ItemAttachment;
import microsoft.exchange.webservices.data.property.complex.ItemId;
import microsoft.exchange.webservices.data.property.complex.MessageBody;

import java.util.Arrays;

/**
 * Represents an e-mail message. Properties available on e-mail messages are
 * defined in the EmailMessageSchema class.
 */
@Attachable
@ServiceObjectDefinition(xmlElementName = XmlElementNames.Message)
public class EmailMessage extends Item {

  /**
   * Initializes an unsaved local instance of EmailMessage. To bind to an
   * existing e-mail message, use EmailMessage.Bind() instead.
   *
   * @param service The ExchangeService object to which the e-mail message will be
   *                bound.
   * @throws Exception the exception
   */
  public EmailMessage(ExchangeService service) throws Exception {
    super(service);
  }

  /**
   * Initializes a new instance of the "EmailMessage" class.
   *
   * @param parentAttachment The parent attachment.
   * @throws Exception the exception
   */
  public EmailMessage(ItemAttachment parentAttachment) throws Exception {
    super(parentAttachment);
  }

  /**
   * Binds to an existing e-mail message and loads the specified set of
   * property.Calling this method results in a call to EWS.
   *
   * @param service     the service
   * @param id          the id
   * @param propertySet the property set
   * @return An EmailMessage instance representing the e-mail message
   * corresponding to the specified Id
   * @throws Exception the exception
   */
  public static EmailMessage bind(ExchangeService service, ItemId id,
      PropertySet propertySet) throws Exception {
    return service.bindToItem(EmailMessage.class, id, propertySet);

  }

  /**
   * Binds to an existing e-mail message and loads its first class
   * property.Calling this method results in a call to EWS.
   *
   * @param service the service
   * @param id      the id
   * @return An EmailMessage instance representing the e-mail message
   * corresponding to the specified Id
   * @throws Exception the exception
   */
  public static EmailMessage bind(ExchangeService service, ItemId id)
      throws Exception {
    return EmailMessage.bind(service, id, PropertySet
        .getFirstClassProperties());
  }

  /**
   * Method to return the schema associated with this type of object.
   *
   * @return The schema associated with this type of object.
   */
  @Override public ServiceObjectSchema getSchema() {
    return EmailMessageSchema.Instance;
  }

  /**
   * Gets the minimum required server version.
   *
   * @return Earliest Exchange version in which this service object type is
   * supported.
   */
  @Override public ExchangeVersion getMinimumRequiredServerVersion() {
    return ExchangeVersion.Exchange2007_SP1;
  }

  /**
   * Send message.
   *
   * @param parentFolderId     The parent folder id.
   * @param messageDisposition The message disposition.
   * @throws Exception the exception
   */
  private void internalSend(FolderId parentFolderId,
      MessageDisposition messageDisposition) throws Exception {
    this.throwIfThisIsAttachment();

    if (this.isNew()) {
      if ((this.getAttachments().getCount() == 0) ||
          (messageDisposition == MessageDisposition.SaveOnly)) {
        this.internalCreate(parentFolderId, messageDisposition, null);
      } else {
        // Bug E14:80316 -- If the message has attachments, save as a
        // draft (and add attachments) before sending.
        this.internalCreate(null, // null means use the Drafts folder in
            // the mailbox of the authenticated
            // user.
            MessageDisposition.SaveOnly, null);

        this.getService().sendItem(this, parentFolderId);
      }
    } else if (this.isDirty()) {
      // Validate and save attachments before sending.
      this.getAttachments().validate();
      this.getAttachments().save();

      if (this.getPropertyBag().getIsUpdateCallNecessary()) {
        this.internalUpdate(parentFolderId,
            ConflictResolutionMode.AutoResolve, messageDisposition,
            null);
      } else {
        this.getService().sendItem(this, parentFolderId);
      }
    } else {
      this.getService().sendItem(this, parentFolderId);
    }

    // this.internalCreate(parentFolderId, messageDisposition, null);
  }

  /**
   * Creates a reply response to the message.
   *
   * @param replyAll the reply all
   * @return A ResponseMessage representing the reply response that can
   * subsequently be modified and sent.
   * @throws Exception the exception
   */
  public ResponseMessage createReply(boolean replyAll) throws Exception {
    this.throwIfThisIsNew();

    return new ResponseMessage(this,
        replyAll ? ResponseMessageType.ReplyAll :
            ResponseMessageType.Reply);
  }

  /**
   * Creates a forward response to the message.
   *
   * @return A ResponseMessage representing the forward response that can
   * subsequently be modified and sent.
   * @throws Exception the exception
   */
  public ResponseMessage createForward() throws Exception {
    this.throwIfThisIsNew();
    return new ResponseMessage(this, ResponseMessageType.Forward);
  }

  /**
   * Replies to the message. Calling this method results in a call to EWS.
   *
   * @param bodyPrefix the body prefix
   * @param replyAll   the reply all
   * @throws Exception the exception
   */
  public void reply(MessageBody bodyPrefix, boolean replyAll)
      throws Exception {
    ResponseMessage responseMessage = this.createReply(replyAll);
    responseMessage.setBodyPrefix(bodyPrefix);
    responseMessage.sendAndSaveCopy();
  }

  /**
   * Forwards the message. Calling this method results in a call to EWS.
   *
   * @param bodyPrefix   the body prefix
   * @param toRecipients the to recipients
   * @throws Exception the exception
   */
  public void forward(MessageBody bodyPrefix, EmailAddress... toRecipients)
      throws Exception {
    if (null != toRecipients) {
      forward(bodyPrefix, Arrays.asList(toRecipients));
    }
  }

  /**
   * Forwards the message. Calling this method results in a call to EWS.
   *
   * @param bodyPrefix   the body prefix
   * @param toRecipients the to recipients
   * @throws Exception the exception
   */
  public void forward(MessageBody bodyPrefix,
      Iterable<EmailAddress> toRecipients) throws Exception {
    ResponseMessage responseMessage = this.createForward();

    responseMessage.setBodyPrefix(bodyPrefix);
    responseMessage.getToRecipients()
        .addEmailRange(toRecipients.iterator());

    responseMessage.sendAndSaveCopy();
  }

  /**
   * Sends this e-mail message. Calling this method results in at least one
   * call to EWS.
   *
   * @throws Exception the exception
   */
  public void send() throws Exception {
    internalSend(null, MessageDisposition.SendOnly);
  }

  /**
   * Sends this e-mail message and saves a copy of it in the specified
   * folder. SendAndSaveCopy does not work if the message has unsaved
   * attachments. In that case, the message must first be saved and then sent.
   * Calling this method results in a call to EWS.
   *
   * @param destinationFolderId the destination folder id
   * @throws Exception the exception
   */
  public void sendAndSaveCopy(FolderId destinationFolderId) throws Exception {
    EwsUtilities.validateParam(destinationFolderId, "destinationFolderId");
    this.internalSend(destinationFolderId,
        MessageDisposition.SendAndSaveCopy);
  }

  /**
   * Sends this e-mail message and saves a copy of it in the specified
   * folder. SendAndSaveCopy does not work if the message has unsaved
   * attachments. In that case, the message must first be saved and then sent.
   * Calling this method results in a call to EWS.
   *
   * @param destinationFolderName the destination folder name
   * @throws Exception the exception
   */
  public void sendAndSaveCopy(WellKnownFolderName destinationFolderName)
      throws Exception {
    this.internalSend(new FolderId(destinationFolderName),
        MessageDisposition.SendAndSaveCopy);
  }

  /**
   * Sends this e-mail message and saves a copy of it in the Sent Items
   * folder. SendAndSaveCopy does not work if the message has unsaved
   * attachments. In that case, the message must first be saved and then sent.
   * Calling this method results in a call to EWS.
   *
   * @throws Exception the exception
   */
  public void sendAndSaveCopy() throws Exception {
    this.internalSend(new FolderId(WellKnownFolderName.SentItems),
        MessageDisposition.SendAndSaveCopy);
  }

  /**
   * Suppresses the read receipt on the message. Calling this method results
   * in a call to EWS.
   *
   * @throws Exception the exception
   */
  public void suppressReadReceipt() throws Exception {
    this.throwIfThisIsNew();
    new SuppressReadReceipt(this).internalCreate(null, null);
  }

  /**
   * Gets the list of To recipients for the e-mail message.
   *
   * @return The list of To recipients for the e-mail message.
   * @throws ServiceLocalException the service local exception
   */
  public EmailAddressCollection getToRecipients()
      throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        EmailMessageSchema.ToRecipients);
  }

  /**
   * Gets the list of Bcc recipients for the e-mail message.
   *
   * @return the bcc recipients
   * @throws ServiceLocalException the service local exception
   */
  public EmailAddressCollection getBccRecipients()
      throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        EmailMessageSchema.BccRecipients);
  }

  /**
   * Gets the list of Cc recipients for the e-mail message.
   *
   * @return the cc recipients
   * @throws ServiceLocalException the service local exception
   */
  public EmailAddressCollection getCcRecipients()
      throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        EmailMessageSchema.CcRecipients);
  }

  /**
   * Gets the conversation topic of the e-mail message.
   *
   * @return the conversation topic
   * @throws ServiceLocalException the service local exception
   */
  public String getConversationTopic() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        EmailMessageSchema.ConversationTopic);
  }

  /**
   * Gets the conversation index of the e-mail message.
   *
   * @return the conversation index
   * @throws ServiceLocalException the service local exception
   */
  public byte[] getConversationIndex() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        EmailMessageSchema.ConversationIndex);
  }

  /**
   * Gets  the "on behalf" sender of the e-mail message.
   *
   * @return the from
   * @throws ServiceLocalException the service local exception
   */
  public EmailAddress getFrom() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        EmailMessageSchema.From);
  }

  /**
   * Sets the from.
   *
   * @param value the new from
   * @throws Exception the exception
   */
  public void setFrom(EmailAddress value) throws Exception {
    this.getPropertyBag().setObjectFromPropertyDefinition(
        EmailMessageSchema.From, value);
  }

  /**
   * Gets  a value indicating whether this is an associated message.
   *
   * @return the checks if is associated
   * @throws ServiceLocalException the service local exception
   */
  public boolean getIsAssociated() throws ServiceLocalException {
    return super.getIsAssociated();
  }

  // The "new" keyword is used to expose the setter only on Message types,
  // because
  // EWS only supports creation of FAI Message types. IsAssociated is a
  // readonly
  // property of the Item type but it is used by the CreateItem web method for
  // creating
  // associated messages.

  /**
   * Sets the checks if is associated.
   *
   * @param value the new checks if is associated
   * @throws Exception the exception
   */
  public void setIsAssociated(boolean value) throws Exception {
    this.getPropertyBag().setObjectFromPropertyDefinition(
        EmailMessageSchema.IsAssociated, value);
  }

  /**
   * Gets a value indicating whether a read receipt is requested for
   * the e-mail message.
   *
   * @return the checks if is delivery receipt requested
   * @throws ServiceLocalException the service local exception
   */
  public Boolean getIsDeliveryReceiptRequested()
      throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        EmailMessageSchema.IsDeliveryReceiptRequested);
  }

  /**
   * Sets the checks if is delivery receipt requested.
   *
   * @param value the new checks if is delivery receipt requested
   * @throws Exception the exception
   */
  public void setIsDeliveryReceiptRequested(Boolean value) throws Exception {
    this.getPropertyBag().setObjectFromPropertyDefinition(
        EmailMessageSchema.IsDeliveryReceiptRequested, value);
  }

  /**
   * Gets  a value indicating whether the e-mail message is read.
   *
   * @return the checks if is read
   * @throws ServiceLocalException the service local exception
   */
  public Boolean getIsRead() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        EmailMessageSchema.IsRead);
  }

  /**
   * Sets the checks if is read.
   *
   * @param value the new checks if is read
   * @throws Exception the exception
   */
  public void setIsRead(Boolean value) throws Exception {
    this.getPropertyBag().setObjectFromPropertyDefinition(
        EmailMessageSchema.IsRead, value);
  }

  /**
   * Gets a value indicating whether a read receipt is requested for
   * the e-mail message.
   *
   * @return the checks if is read receipt requested
   * @throws ServiceLocalException the service local exception
   */
  public Boolean getIsReadReceiptRequested() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        EmailMessageSchema.IsReadReceiptRequested);
  }

  /**
   * Sets the checks if is read receipt requested.
   *
   * @param value the new checks if is read receipt requested
   * @throws Exception the exception
   */
  public void setIsReadReceiptRequested(Boolean value) throws Exception {
    this.getPropertyBag().setObjectFromPropertyDefinition(
        EmailMessageSchema.IsReadReceiptRequested, value);
  }

  /**
   * Gets  a value indicating whether a response is requested for the
   * e-mail message.
   *
   * @return the checks if is response requested
   * @throws ServiceLocalException the service local exception
   */
  public Boolean getIsResponseRequested() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        EmailMessageSchema.IsResponseRequested);
  }

  /**
   * Sets the checks if is response requested.
   *
   * @param value the new checks if is response requested
   * @throws Exception the exception
   */
  public void setIsResponseRequested(Boolean value) throws Exception {
    this.getPropertyBag().setObjectFromPropertyDefinition(
        EmailMessageSchema.IsResponseRequested, value);
  }

  /**
   * Gets the Internat Message Id of the e-mail message.
   *
   * @return the internet message id
   * @throws ServiceLocalException the service local exception
   */
  public String getInternetMessageId() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        EmailMessageSchema.InternetMessageId);
  }

  /**
   * Gets  the references of the e-mail message.
   *
   * @return the references
   * @throws ServiceLocalException the service local exception
   */
  public String getReferences() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        EmailMessageSchema.References);
  }

  /**
   * Sets the references.
   *
   * @param value the new references
   * @throws Exception the exception
   */
  public void setReferences(String value) throws Exception {
    this.getPropertyBag().setObjectFromPropertyDefinition(
        EmailMessageSchema.References, value);
  }

  /**
   * Gets a list of e-mail addresses to which replies should be addressed.
   *
   * @return the reply to
   * @throws ServiceLocalException the service local exception
   */
  public EmailAddressCollection getReplyTo() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        EmailMessageSchema.ReplyTo);
  }

  /**
   * Gets  the sender of the e-mail message.
   *
   * @return the sender
   * @throws ServiceLocalException the service local exception
   */
  public EmailAddress getSender() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        EmailMessageSchema.Sender);
  }

  /**
   * Sets the sender.
   *
   * @param value the new sender
   * @throws Exception the exception
   */
  public void setSender(EmailAddress value) throws Exception {
    this.getPropertyBag().setObjectFromPropertyDefinition(
        EmailMessageSchema.Sender, value);
  }

  /**
   * Gets the ReceivedBy property of the e-mail message.
   *
   * @return the received by
   * @throws ServiceLocalException the service local exception
   */
  public EmailAddress getReceivedBy() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        EmailMessageSchema.ReceivedBy);
  }

  /**
   * Gets the ReceivedRepresenting property of the e-mail message.
   *
   * @return the received representing
   * @throws ServiceLocalException the service local exception
   */
  public EmailAddress getReceivedRepresenting() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        EmailMessageSchema.ReceivedRepresenting);
  }
}
