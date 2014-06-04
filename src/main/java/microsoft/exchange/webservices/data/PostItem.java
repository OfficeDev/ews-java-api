/**************************************************************************
 * copyright file="PostItem.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the PostItem.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Represents a post item. Properties available on post items are defined in the
 * PostItemSchema class.
 * 
 */
@Attachable
@ServiceObjectDefinition(xmlElementName = XmlElementNames.PostItem)
public final class PostItem extends Item {

	/**
	 * Initializes an unsaved local instance of PostItem.To bind to an existing
	 * post item, use PostItem.Bind() instead.
	 * 
	 * @param service
	 *            the service
	 * @throws Exception
	 *             the exception
	 */
	public PostItem(ExchangeService service) throws Exception {
		super(service);
	}

	/**
	 * Initializes a new instance of the class.
	 * 
	 * @param parentAttachment
	 *            the parent attachment
	 * @throws Exception
	 *             the exception
	 */
	protected PostItem(ItemAttachment parentAttachment) throws Exception {
		super(parentAttachment);
	}

	/**
	 * Binds to an existing post item and loads the specified set of properties.
	 * Calling this method results in a call to EWS.
	 * 
	 * @param service
	 *            the service
	 * @param id
	 *            the id
	 * @param propertySet
	 *            the property set
	 * @return An PostItem instance representing the post item corresponding to
	 *         the specified Id.
	 * @throws Exception
	 *             the exception
	 */
	public static PostItem bind(ExchangeService service, ItemId id,
			PropertySet propertySet) throws Exception {
		return service.bindToItem(PostItem.class, id, propertySet);
	}

	/**
	 * Binds to an existing post item and loads its first class properties.
	 * calling this method results in a call to EWS.
	 * 
	 * @param service
	 *            the service
	 * @param id
	 *            the id
	 * @return An PostItem instance representing the post item corresponding to
	 *         the specified Id.
	 * @throws Exception
	 *             the exception
	 */
	public static PostItem bind(ExchangeService service, ItemId id)
			throws Exception {
		return PostItem
				.bind(service, id, PropertySet.getFirstClassProperties());
	}

	/**
	 * Internal method to return the schema associated with this type of object.
	 * 
	 * @return The schema associated with this type of object.
	 */
	@Override
	protected ServiceObjectSchema getSchema() {
		return PostItemSchema.Instance;
	}

	/**
	 * Internal method to return the schema associated with this type of object.
	 * 
	 * @return The schema associated with this type of object.
	 */
	@Override
	protected ExchangeVersion getMinimumRequiredServerVersion() {
		return ExchangeVersion.Exchange2007_SP1;
	}

	/**
	 * Creates a post reply to this post item.
	 * 
	 * @return A PostReply that can be modified and saved.
	 * @throws Exception
	 *             the exception
	 */
	public PostReply createPostReply() throws Exception {
		this.throwIfThisIsNew();
		return new PostReply(this);
	}

	/**
	 * Posts a reply to this post item. Calling this method results in a call to
	 * EWS.
	 * 
	 * @param bodyPrefix
	 *            the body prefix
	 * @throws Exception
	 *             the exception
	 */
	public void postReply(MessageBody bodyPrefix) throws Exception {
		PostReply postReply = this.createPostReply();
		postReply.setBodyPrefix(bodyPrefix);
		postReply.save();
	}

	/**
	 * Creates a e-mail reply response to the post item.
	 * 
	 * @param replyAll
	 *            the reply all
	 * @return A ResponseMessage representing the e-mail reply response that can
	 *         subsequently be modified and sent.
	 * @throws Exception
	 *             the exception
	 */
	public ResponseMessage createReply(boolean replyAll) throws Exception {
		this.throwIfThisIsNew();
		return new ResponseMessage(this,
				replyAll ? ResponseMessageType.ReplyAll :
						 ResponseMessageType.Reply);
	}

	/**
	 * Replies to the post item. Calling this method results in a call to EWS.
	 * 
	 * @param bodyPrefix
	 *            the body prefix
	 * @param replyAll
	 *            the reply all
	 * @throws Exception
	 *             the exception
	 */
	public void reply(MessageBody bodyPrefix, boolean replyAll)
			throws Exception {
		ResponseMessage responseMessage = this.createReply(replyAll);
		responseMessage.setBodyPrefix(bodyPrefix);
		responseMessage.sendAndSaveCopy();
	}

	/**
	 * Creates a forward response to the post item.
	 * 
	 * @return A ResponseMessage representing the forward response that can
	 *         subsequently be modified and sent.
	 * @throws Exception
	 *             the exception
	 */
	public ResponseMessage createForward() throws Exception {
		this.throwIfThisIsNew();
		return new ResponseMessage(this, ResponseMessageType.Forward);
	}

	/**
	 * Forwards the post item. Calling this method results in a call to EWS.
	 * 
	 * @param bodyPrefix
	 *            the body prefix
	 * @param toRecipients
	 *            the to recipients
	 * @throws Exception
	 *             the exception
	 */
	public void forward(MessageBody bodyPrefix, EmailAddress... toRecipients)
			throws Exception {
		List<EmailAddress> list = new ArrayList<EmailAddress>();
		for (EmailAddress address : toRecipients) {
			list.add(address);
		}
		this.forward(bodyPrefix, list);
	}

	/**
	 * Forwards the post item. Calling this method results in a call to EWS.
	 * 
	 * @param bodyPrefix
	 *            the body prefix
	 * @param toRecipients
	 *            the to recipients
	 * @throws Exception
	 *             the exception
	 */
	public void forward(MessageBody bodyPrefix,
			Iterable<EmailAddress> toRecipients) throws Exception {
		ResponseMessage responseMessage = this.createForward();
		responseMessage.setBodyPrefix(bodyPrefix);
		responseMessage.getToRecipients()
				.addEmailRange(toRecipients.iterator());

		responseMessage.sendAndSaveCopy();
	}

	// Properties
	/**
	 * Gets the conversation index of the post item.
	 * 
	 * @return the conversation index
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public byte[] getConversationIndex() throws ServiceLocalException {
		return (byte[]) this.getPropertyBag().getObjectFromPropertyDefinition(
				EmailMessageSchema.ConversationIndex);
	}

	/**
	 * Gets the conversation topic of the post item.
	 * 
	 * @return the conversation topic
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public String getConversationTopic() throws ServiceLocalException {
		return (String) this.getPropertyBag().getObjectFromPropertyDefinition(
				EmailMessageSchema.ConversationTopic);
	}

	/**
	 * Gets the "on behalf" poster of the post item.
	 * 
	 * @return the from
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public EmailAddress getFrom() throws ServiceLocalException {
		return (EmailAddress) this.getPropertyBag()
				.getObjectFromPropertyDefinition(EmailMessageSchema.From);
	}

	/**
	 * Sets the from.
	 * 
	 * @param value
	 *            the new from
	 * @throws Exception
	 *             the exception
	 */
	public void setFrom(EmailAddress value) throws Exception {
		this.getPropertyBag().setObjectFromPropertyDefinition(
				EmailMessageSchema.From, value);
	}

	/**
	 * Gets the Internet message Id of the post item.
	 * 
	 * @return the internet message id
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public String getInternetMessageId() throws ServiceLocalException {
		return (String) this.getPropertyBag().getObjectFromPropertyDefinition(
				EmailMessageSchema.InternetMessageId);
	}

	/**
	 * Gets a value indicating whether the post item is read.
	 * 
	 * @return the checks if is read
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public Boolean getIsRead() throws ServiceLocalException {
		return (Boolean) this.getPropertyBag().getObjectFromPropertyDefinition(
				EmailMessageSchema.IsRead);
	}

	/**
	 * Sets the checks if is read.
	 * 
	 * @param value
	 *            the new checks if is read
	 * @throws Exception
	 *             the exception
	 */
	public void setIsRead(Boolean value) throws Exception {
		this.getPropertyBag().setObjectFromPropertyDefinition(
				EmailMessageSchema.IsRead, value);
	}

	/**
	 * Gets the the date and time when the post item was posted.
	 * 
	 * @return the posted time
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public Date getPostedTime() throws ServiceLocalException {
		return (Date) this.getPropertyBag().getObjectFromPropertyDefinition(
				PostItemSchema.PostedTime);
	}

	/**
	 * Gets the references of the post item.
	 * 
	 * @return the references
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public String getReferences() throws ServiceLocalException {
		return (String) this.getPropertyBag().getObjectFromPropertyDefinition(
				EmailMessageSchema.References);
	}

	/**
	 * Sets the checks if is read.
	 * 
	 * @param value
	 *            the new checks if is read
	 * @throws Exception
	 *             the exception
	 */
	public void setIsRead(String value) throws Exception {
		this.getPropertyBag().setObjectFromPropertyDefinition(
				EmailMessageSchema.References, value);
	}

	/**
	 * Gets the sender (poster) of the post item.
	 * 
	 * @return the sender
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public EmailAddress getSender() throws ServiceLocalException {
		return (EmailAddress) this.getPropertyBag()
				.getObjectFromPropertyDefinition(EmailMessageSchema.Sender);
	}

	/**
	 * Sets the sender.
	 * 
	 * @param value
	 *            the new sender
	 * @throws Exception
	 *             the exception
	 */
	public void setSender(EmailAddress value) throws Exception {
		this.getPropertyBag().setObjectFromPropertyDefinition(
				EmailMessageSchema.Sender, value);
	}

}
