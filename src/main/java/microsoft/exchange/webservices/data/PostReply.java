/**************************************************************************
 * copyright file="PostReply.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the PostReply.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.List;

/**
 * Represents a reply to a post item.
 */
@ServiceObjectDefinition(xmlElementName = XmlElementNames.PostReplyItem, returnedByServer = false)
public final class PostReply extends ServiceObject{

	/** The reference item. */
	private Item referenceItem;

	/**
	 * Initializes a new instance of the class.
	 * 
	 * @param referenceItem
	 *            the reference item
	 * @throws Exception
	 *             the exception
	 */
	protected PostReply(Item referenceItem) throws Exception {
		super(referenceItem.getService());
		EwsUtilities.EwsAssert(referenceItem != null, "PostReply.ctor",
				"referenceItem is null");
		referenceItem.throwIfThisIsNew();

		this.referenceItem = referenceItem;
	}

	/**
	 * Internal method to return the schema associated with this type of object.
	 * 
	 * @return The schema associated with this type of object.
	 */
	@Override
	public ServiceObjectSchema getSchema() {
		return PostReplySchema.Instance;
	}

	/**
	 * Gets the minimum required server version.
	 * 
	 * @return Earliest Exchange version in which this service object type is
	 *         supported.
	 */
	@Override
	protected ExchangeVersion getMinimumRequiredServerVersion() {
		return ExchangeVersion.Exchange2007_SP1;
	}

	/**
	 * Create a PostItem response.
	 * 
	 * @param parentFolderId
	 *            the parent folder id
	 * @param messageDisposition
	 *            the message disposition
	 * @return Created PostItem.
	 * @throws Exception
	 *             the exception
	 */
	protected PostItem internalCreate(FolderId parentFolderId,
			MessageDisposition messageDisposition) throws Exception {
		((ItemId)this
				.getObjectFromPropertyDefinition(
						ResponseObjectSchema.ReferenceItemId))
				.assign(this.referenceItem.getId());

		List<Item> items = this.getService().internalCreateResponseObject(this,
				parentFolderId, messageDisposition);

		PostItem postItem = EwsUtilities.findFirstItemOfType(PostItem.class,
				items);

		// This should never happen. If it does, we have a bug.
		EwsUtilities
				.EwsAssert(postItem != null, "PostReply.InternalCreate",
						"postItem is null. The CreateItem call did" +
						" not return the expected PostItem.");

		return postItem;
	}

	/**
	 * Loads the specified set of properties on the object.
	 * 
	 * @param propertySet
	 *            the property set
	 * @throws InvalidOperationException
	 *             the invalid operation exception
	 */
	@Override
	protected void internalLoad(PropertySet propertySet)
			throws InvalidOperationException {
		throw new InvalidOperationException(
				Strings.LoadingThisObjectTypeNotSupported);
	}

	/**
	 * Deletes the object.
	 * 
	 * @param deleteMode
	 *            the delete mode
	 * @param sendCancellationsMode
	 *            the send cancellations mode
	 * @param affectedTaskOccurrences
	 *            the affected task occurrences
	 * @throws InvalidOperationException
	 *             the invalid operation exception
	 */
	@Override
	protected void internalDelete(DeleteMode deleteMode,
			SendCancellationsMode sendCancellationsMode,
			AffectedTaskOccurrence affectedTaskOccurrences)
			throws InvalidOperationException {
		throw new InvalidOperationException(
				Strings.DeletingThisObjectTypeNotAuthorized);
	}

	/**
	 * Saves the post reply in the same folder as the original post item.
	 * Calling this method results in a call to EWS.
	 * 
	 * @return A PostItem representing the posted reply
	 * @throws Exception
	 *             the exception
	 */
	public PostItem save() throws Exception {
		return (PostItem) this.internalCreate(null, null);
	}

	/**
	 * Saves the post reply in the same folder as the original post item.
	 * Calling this method results in a call to EWS.
	 * 
	 * @param destinationFolderId
	 *            the destination folder id
	 * @return A PostItem representing the posted reply
	 * @throws Exception
	 *             the exception
	 */
	public PostItem save(FolderId destinationFolderId) throws Exception {
		EwsUtilities.validateParam(destinationFolderId, "destinationFolderId");
		return (PostItem) this.internalCreate(destinationFolderId, null);
	}

	/**
	 * Saves the post reply in a specified folder. Calling this method results
	 * in a call to EWS.
	 * 
	 * @param destinationFolderName
	 *            the destination folder name
	 * @return A PostItem representing the posted reply.
	 * @throws Exception
	 *             the exception
	 */
	public PostItem save(WellKnownFolderName destinationFolderName)
			throws Exception {
		return (PostItem) this.internalCreate(new FolderId(
				destinationFolderName), null);
	}

	/**
	 * Gets the subject of the post reply.
	 * 
	 * @return the subject
	 * @throws Exception
	 *             the exception
	 */
	public String getSubject() throws Exception {
		return (String) this
				.getObjectFromPropertyDefinition(EmailMessageSchema.Subject);
	}

	/**
	 * Sets the subject.
	 * 
	 * @param value
	 *            the new subject
	 * @throws Exception
	 *             the exception
	 */
	public void setSubject(String value) throws Exception {
		this.getPropertyBag().setObjectFromPropertyDefinition(
				EmailMessageSchema.Subject, value);
	}

	/**
	 * Gets the body of the post reply.
	 * 
	 * @return the body
	 * @throws Exception
	 *             the exception
	 */
	public MessageBody getBody() throws Exception {
		return (MessageBody) this
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
	 * Gets the body prefix that should be prepended to the original
	 * post item's body.
	 * 
	 * @return the body prefix
	 * @throws Exception
	 *             the exception
	 */
	public MessageBody getBodyPrefix() throws Exception {
		return (MessageBody) this
				.getObjectFromPropertyDefinition(
						ResponseObjectSchema.BodyPrefix);
	}

	/**
	 * Sets the body prefix.
	 * 
	 * @param value
	 *            the new body prefix
	 * @throws Exception
	 *             the exception
	 */
	public void setBodyPrefix(MessageBody value) throws Exception {
		this.getPropertyBag().setObjectFromPropertyDefinition(
				ResponseObjectSchema.BodyPrefix, value);
	}

}
