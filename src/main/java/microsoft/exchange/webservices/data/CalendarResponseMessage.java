/**************************************************************************
 * copyright file="CalendarResponseMessage.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the CalendarResponseMessage.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents the base class for accept, tentatively accept and decline response
 * messages.
 * 
 * 
 * @param <TMessage>
 *            The type of message that is created when this response message is
 *            saved.
 */
@EditorBrowsable(state = EditorBrowsableState.Never)
public abstract class CalendarResponseMessage<TMessage extends EmailMessage>
		extends CalendarResponseMessageBase<TMessage> {

	/**
	 * Initializes a new instance of the CalendarResponseMessage class.
	 * 
	 * @param referenceItem
	 *            The reference item
	 * @throws Exception
	 *             the exception
	 */
	protected CalendarResponseMessage(Item referenceItem) throws Exception {
		super(referenceItem);
	}

	/**
	 * Internal method to return the schema associated with this type of object.
	 * 
	 * @return The schema associated with this type of object.
	 */
	@Override
	protected ServiceObjectSchema getSchema() {
		return CalendarResponseObjectSchema.Instance;
	}

	/**
	 * Gets the body of the response.
	 * 
	 * @return the body
	 * @throws Exception
	 *             the exception
	 */
	public MessageBody getBody() throws Exception {
		return (MessageBody)this
				.getObjectFromPropertyDefinition(ItemSchema.Body);
	}

	/**
	 * Sets the body.
	 * 
	 * @param value
	 *            the new body
	 * @throws Exception
	 *             the exception
	 */
	public void setBody(MessageBody value) throws Exception {
		this.getPropertyBag().setObjectFromPropertyDefinition(ItemSchema.Body,
				value);
	}

	/**
	 * Gets a list of recipients the response will be sent to.
	 * 
	 * @return the to recipients
	 * @throws Exception
	 *             the exception
	 */
	public EmailAddressCollection getToRecipients() throws Exception {
		return (EmailAddressCollection)this
				.getObjectFromPropertyDefinition(
						EmailMessageSchema.ToRecipients);
	}

	/**
	 * Gets a list of recipients the response will be sent to as Cc.
	 * 
	 * @return the cc recipients
	 * @throws Exception
	 *             the exception
	 */
	public EmailAddressCollection getCcRecipients() throws Exception {
		return (EmailAddressCollection)this
				.getObjectFromPropertyDefinition(
						EmailMessageSchema.CcRecipients);
	}

	/**
	 * Gets a list of recipients this response will be sent to as Bcc.
	 * 
	 * @return the bcc recipients
	 * @throws Exception
	 *             the exception
	 */
	public EmailAddressCollection getBccRecipients() throws Exception {
		return (EmailAddressCollection)this
				.getObjectFromPropertyDefinition(
						EmailMessageSchema.BccRecipients);
	}

	/**
	 * Gets the item class.
	 * 
	 * @return the item class
	 * @throws Exception
	 *             the exception
	 */
	protected String getItemClass() throws Exception {
		return (String)this
				.getObjectFromPropertyDefinition(ItemSchema.ItemClass);
	}

	/**
	 * Sets the item class.
	 * 
	 * @param value
	 *            the new item class
	 * @throws Exception
	 *             the exception
	 */
	protected void setItemClass(String value) throws Exception {
		this.getPropertyBag().setObjectFromPropertyDefinition(
				ItemSchema.ItemClass, value);
	}

	/**
	 * Gets the sensitivity of this response.
	 * 
	 * @return the sensitivity
	 * @throws Exception
	 *             the exception
	 */
	public Sensitivity getSensitivity() throws Exception {
		return (Sensitivity)this
				.getObjectFromPropertyDefinition(ItemSchema.Sensitivity);
	}

	/**
	 * Sets the sensitivity.
	 * 
	 * @param value
	 *            the new sensitivity
	 * @throws Exception
	 *             the exception
	 */
	public void setSensitivity(Sensitivity value) throws Exception {
		this.getPropertyBag().setObjectFromPropertyDefinition(
				ItemSchema.Sensitivity, value);
	}

	/**
	 * Gets a list of attachments to this response.
	 * 
	 * @return the attachments
	 * @throws Exception
	 *             the exception
	 */
	public AttachmentCollection getAttachments() throws Exception {
		return (AttachmentCollection)this
				.getObjectFromPropertyDefinition(ItemSchema.Attachments);
	}

	/**
	 * Gets the internet message headers.
	 * 
	 * @return the internet message headers
	 * @throws Exception
	 *             the exception
	 */
	protected InternetMessageHeaderCollection getInternetMessageHeaders()
			throws Exception {
		return (InternetMessageHeaderCollection)this
				.getObjectFromPropertyDefinition(
						ItemSchema.InternetMessageHeaders);
	}

	/**
	 * Gets the sender of this response.
	 * 
	 * @return the sender
	 * @throws Exception
	 *             the exception
	 */
	public EmailAddress getSender() throws Exception {
		return (EmailAddress)this
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