/**************************************************************************
 * copyright file="Item.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the Item.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.EnumSet;
import java.util.ListIterator;

/**
 * Represents a generic item. Properties available on items are defined in the
 * ItemSchema class.
 * 
 */
@Attachable
@ServiceObjectDefinition(xmlElementName = XmlElementNames.Item)
public class Item extends ServiceObject {

	/** The parent attachment. */
	private ItemAttachment parentAttachment;

	/**
	 * Initializes an unsaved local instance of <see cref="Item"/>. To bind to
	 * an existing item, use Item.Bind() instead.
	 * 
	 * @param service
	 *            the service
	 * @throws Exception
	 *             the exception
	 */
	protected Item(ExchangeService service) throws Exception {
		super(service);
	}

	/**
	 * Initializes a new instance of the item class.
	 * 
	 * @param parentAttachment
	 *            The parent attachment.
	 * @throws Exception
	 *             the exception
	 */
	protected Item(ItemAttachment parentAttachment) throws Exception {
		this(parentAttachment.getOwner().getService());
		EwsUtilities.EwsAssert(parentAttachment != null, "Item.ctor",
				"parentAttachment is null");

		this.parentAttachment = parentAttachment;
	}

	/**
	 * Binds to an existing item, whatever its actual type is, and loads the
	 * specified set of properties. Calling this method results in a call to
	 * EWS.
	 * 
	 * @param service
	 *            The service to use to bind to the item.
	 * @param id
	 *            The Id of the item to bind to.
	 * @param propertySet
	 *            The set of properties to load.
	 * @return An Item instance representing the item corresponding to the
	 *         specified Id.
	 * @throws Exception
	 *             the exception
	 */
	public static Item bind(ExchangeService service, ItemId id,
			PropertySet propertySet) throws Exception {
		return service.bindToItem(Item.class, id, propertySet);
	}

	/**
	 * Binds to an existing item, whatever its actual type is, and loads the
	 * specified set of properties. Calling this method results in a call to
	 * EWS.
	 * 
	 * @param service
	 *            The service to use to bind to the item.
	 * @param id
	 *            The Id of the item to bind to.
	 * @return An Item instance representing the item corresponding to the
	 *         specified Id.
	 * @throws Exception
	 *             the exception
	 */
	public static Item bind(ExchangeService service, ItemId id)
			throws Exception {
		return Item.bind(service, id, PropertySet.getFirstClassProperties());
	}

	/**
	 * Internal method to return the schema associated with this type of object.
	 * 
	 * @return The schema associated with this type of object.
	 */
	@Override
	protected ServiceObjectSchema getSchema() {
		return ItemSchema.getInstance();
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
	 * Throws exception if this is attachment.
	 * 
	 * @throws InvalidOperationException
	 *             the invalid operation exception
	 */
	protected void throwIfThisIsAttachment() throws InvalidOperationException {
		if (this.isAttachment()) {
			throw new InvalidOperationException(
					Strings.OperationDoesNotSupportAttachments);
		}
	}

	/**
	 * The property definition for the Id of this object.
	 * 
	 * @return A PropertyDefinition instance.
	 */
	protected PropertyDefinition getIdPropertyDefinition() {
		return ItemSchema.Id;
	}

	/**
	 * The property definition for the Id of this object.
	 * 
	 * @param propertySet
	 *            the property set
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void internalLoad(PropertySet propertySet) throws Exception {
		this.throwIfThisIsNew();
		this.throwIfThisIsAttachment();

		ArrayList<Item> itemArry = new ArrayList<Item>();
		itemArry.add(this);
		this.getService().internalLoadPropertiesForItems(itemArry, propertySet,
				ServiceErrorHandling.ThrowOnError);
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
	 * @throws ServiceLocalException
	 *             the service local exception
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void internalDelete(DeleteMode deleteMode,
			SendCancellationsMode sendCancellationsMode,
			AffectedTaskOccurrence affectedTaskOccurrences)
			throws ServiceLocalException, Exception {
		this.throwIfThisIsNew();
		this.throwIfThisIsAttachment();

		// If sendCancellationsMode is null, use the default value that's
		// appropriate for item type.
		if (sendCancellationsMode == null) {
			sendCancellationsMode = this.getDefaultSendCancellationsMode();
		}

		// If affectedTaskOccurrences is null, use the default value that's
		// appropriate for item type.
		if (affectedTaskOccurrences == null) {
			affectedTaskOccurrences = this.getDefaultAffectedTaskOccurrences();
		}

		this.getService().deleteItem(this.getId(), deleteMode,
				sendCancellationsMode, affectedTaskOccurrences);
	}

	/**
	 * Create item.
	 * 
	 * @param parentFolderId
	 *            the parent folder id
	 * @param messageDisposition
	 *            the message disposition
	 * @param sendInvitationsMode
	 *            the send invitations mode
	 * @throws Exception
	 *             the exception
	 */
	protected void internalCreate(FolderId parentFolderId,
			MessageDisposition messageDisposition,
			SendInvitationsMode sendInvitationsMode) throws Exception {
		this.throwIfThisIsNotNew();
		this.throwIfThisIsAttachment();

		if (this.isNew() || this.isDirty()) {
			this.getService().createItem(
					this,
					parentFolderId,
					messageDisposition,
					sendInvitationsMode != null ? sendInvitationsMode : this
							.getDefaultSendInvitationsMode());

			this.getAttachments().save();
		}
	}

	/**
	 * Update item.
	 * 
	 * @param parentFolderId
	 *            the parent folder id
	 * @param conflictResolutionMode
	 *            the conflict resolution mode
	 * @param messageDisposition
	 *            the message disposition
	 * @param sendInvitationsOrCancellationsMode
	 *            the send invitations or cancellations mode
	 * @return Updated item.
	 * @throws ServiceResponseException
	 *             the service response exception
	 * @throws Exception
	 *             the exception
	 */
	protected Item internalUpdate(
			FolderId parentFolderId,
			ConflictResolutionMode conflictResolutionMode,
			MessageDisposition messageDisposition,
			SendInvitationsOrCancellationsMode sendInvitationsOrCancellationsMode)
			throws ServiceResponseException, Exception {
		this.throwIfThisIsNew();
		this.throwIfThisIsAttachment();

		Item returnedItem = null;

		if (this.isDirty() && this.getPropertyBag().getIsUpdateCallNecessary()) {
			returnedItem = this
					.getService()
					.updateItem(
							this,
							parentFolderId,
							conflictResolutionMode,
							messageDisposition,
							sendInvitationsOrCancellationsMode != null ? sendInvitationsOrCancellationsMode
									: this
											.getDefaultSendInvitationsOrCancellationsMode());
		}
		if (this.hasUnprocessedAttachmentChanges()) {
			// Validation of the item and its attachments occurs in
			// UpdateItems.
			// If we didn't update the item we still need to validate
			// attachments.
			this.getAttachments().validate();
			this.getAttachments().save();

		}

		return returnedItem;
	}

	/**
	 * Gets a value indicating whether this instance has unprocessed attachment
	 * collection changes.
	 * 
	 * @throws ServiceLocalException
	 */
	protected boolean hasUnprocessedAttachmentChanges()
			throws ServiceLocalException {
		return this.getAttachments().hasUnprocessedChanges();

	}

	/**
	 * Gets the parent attachment of this item.
	 * 
	 * @return the parent attachment
	 */
	protected ItemAttachment getParentAttachment() {
		return this.parentAttachment;
	}

	/**
	 * Gets Id of the root item for this item.
	 * 
	 * @return the root item id
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	protected ItemId getRootItemId() throws ServiceLocalException {

		if (this.isAttachment()) {
			return this.getParentAttachment().getOwner().getRootItemId();
		} else {
			return this.getId();
		}
	}

	/**
	 * Deletes the item. Calling this method results in a call to EWS.
	 * 
	 * @param deleteMode
	 *            the delete mode
	 * @throws ServiceLocalException
	 *             the service local exception
	 * @throws Exception
	 *             the exception
	 */
	public void delete(DeleteMode deleteMode) throws ServiceLocalException,
			Exception {
		this.internalDelete(deleteMode, null, null);
	}

	/**
	 * Saves this item in a specific folder. Calling this method results in at
	 * least one call to EWS. Mutliple calls to EWS might be made if attachments
	 * have been added.
	 * 
	 * @param parentFolderId
	 *            the parent folder id
	 * @throws Exception
	 *             the exception
	 */
	public void save(FolderId parentFolderId) throws Exception {
		EwsUtilities.validateParam(parentFolderId, "parentFolderId");
		this.internalCreate(parentFolderId, MessageDisposition.SaveOnly, null);
	}

	/**
	 * Saves this item in a specific folder. Calling this method results in at
	 * least one call to EWS. Mutliple calls to EWS might be made if attachments
	 * have been added.
	 * 
	 * @param parentFolderName
	 *            the parent folder name
	 * @throws Exception
	 *             the exception
	 */
	public void save(WellKnownFolderName parentFolderName) throws Exception {
		this.internalCreate(new FolderId(parentFolderName),
				MessageDisposition.SaveOnly, null);
	}

	/**
	 * Saves this item in the default folder based on the item's type (for
	 * example, an e-mail message is saved to the Drafts folder). Calling this
	 * method results in at least one call to EWS. Mutliple calls to EWS might
	 * be made if attachments have been added.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public void save() throws Exception {
		this.internalCreate(null, MessageDisposition.SaveOnly, null);
	}

	/**
	 * Applies the local changes that have been made to this item. Calling this
	 * method results in at least one call to EWS. Mutliple calls to EWS might
	 * be made if attachments have been added or removed.
	 * 
	 * @param conflictResolutionMode
	 *            the conflict resolution mode
	 * @throws ServiceResponseException
	 *             the service response exception
	 * @throws Exception
	 *             the exception
	 */
	public void update(ConflictResolutionMode conflictResolutionMode)
			throws ServiceResponseException, Exception {
		this.internalUpdate(null /* parentFolder */, conflictResolutionMode,
				MessageDisposition.SaveOnly, null);
	}

	/**
	 * Creates a copy of this item in the specified folder. Calling this method
	 * results in a call to EWS. Copy returns null if the copy operation is
	 * across two mailboxes or between a mailbox and a public folder.
	 * 
	 * @param destinationFolderId
	 *            the destination folder id
	 * @return The copy of this item.
	 * @throws Exception
	 *             the exception
	 */
	public Item copy(FolderId destinationFolderId) throws Exception {

		this.throwIfThisIsNew();
		this.throwIfThisIsAttachment();

		EwsUtilities.validateParam(destinationFolderId, "destinationFolderId");

		return this.getService().copyItem(this.getId(), destinationFolderId);
	}

	/**
	 * Creates a copy of this item in the specified folder. Calling this method
	 * results in a call to EWS. Copy returns null if the copy operation is
	 * across two mailboxes or between a mailbox and a public folder.
	 * 
	 * @param destinationFolderName
	 *            the destination folder name
	 * @return The copy of this item.
	 * @throws Exception
	 *             the exception
	 */
	public Item copy(WellKnownFolderName destinationFolderName)
			throws Exception {
		return this.copy(new FolderId(destinationFolderName));
	}

	/**
	 * Moves this item to a the specified folder. Calling this method results in
	 * a call to EWS. Move returns null if the move operation is across two
	 * mailboxes or between a mailbox and a public folder.
	 * 
	 * @param destinationFolderId
	 *            the destination folder id
	 * @return The moved copy of this item.
	 * @throws Exception
	 *             the exception
	 */
	public Item move(FolderId destinationFolderId) throws Exception {
		this.throwIfThisIsNew();
		this.throwIfThisIsAttachment();

		EwsUtilities.validateParam(destinationFolderId, "destinationFolderId");

		return this.getService().moveItem(this.getId(), destinationFolderId);
	}

	/**
	 * Moves this item to a the specified folder. Calling this method results in
	 * a call to EWS. Move returns null if the move operation is across two
	 * mailboxes or between a mailbox and a public folder.
	 * 
	 * @param destinationFolderName
	 *            the destination folder name
	 * @return The moved copy of this item.
	 * @throws Exception
	 *             the exception
	 */
	public Item move(WellKnownFolderName destinationFolderName)
			throws Exception {
		return this.move(new FolderId(destinationFolderName));
	}

	/**
	 * Sets the extended property.
	 * 
	 * @param extendedPropertyDefinition
	 *            the extended property definition
	 * @param value
	 *            the value
	 * @throws Exception
	 *             the exception
	 */
	public void setExtendedProperty(
			ExtendedPropertyDefinition extendedPropertyDefinition, Object value)
			throws Exception {
		this.getExtendedProperties().setExtendedProperty(
				extendedPropertyDefinition, value);
	}

	/**
	 * Removes an extended property.
	 * 
	 * @param extendedPropertyDefinition
	 *            the extended property definition
	 * @return True if property was removed.
	 * @throws Exception
	 *             the exception
	 */
	public boolean removeExtendedProperty(
			ExtendedPropertyDefinition extendedPropertyDefinition)
			throws Exception {
		return this.getExtendedProperties().removeExtendedProperty(
				extendedPropertyDefinition);
	}

	/**
	 * Validates this instance.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void validate() throws Exception {
		super.validate();
		this.getAttachments().validate();
	}

	/**
	 * Gets a value indicating whether a time zone SOAP header should be emitted
	 * in a CreateItem or UpdateItem request so this item can be property saved
	 * or updated.
	 * 
	 * @param isUpdateOperation
	 *            Indicates whether the operation being petrformed is an update
	 *            operation.
	 * @return true if a time zone SOAP header should be emitted;
	 *         otherwise,false
	 */
	protected boolean getIsTimeZoneHeaderRequired(boolean isUpdateOperation)
			throws Exception {
		// Starting E14SP2, attachment will be sent along with CreateItem
		// requests.
		// if the attachment used to require the Timezone header, CreateItem
		// request should do so too.
		//

		if (!isUpdateOperation
				&& (this.getService().getRequestedServerVersion().ordinal() >= ExchangeVersion.Exchange2010_SP2
						.ordinal())) {

			ListIterator<Attachment> items = this.getAttachments().getItems()
					.listIterator();

			while (items.hasNext()) {

				ItemAttachment itemAttachment = (ItemAttachment) items.next();

				if ((itemAttachment.getItem() != null)
						&& itemAttachment
								.getItem()
								.getIsTimeZoneHeaderRequired(false /* isUpdateOperation */)) {
					return true;
				}
			}
		}

		/*
		 * for (ItemAttachment itemAttachment :
		 * this.getAttachments().OfType<ItemAttachment>().getc) { if
		 * ((itemAttachment.Item != null) &&
		 * itemAttachment.Item.GetIsTimeZoneHeaderRequired(false /* //
		 * isUpdateOperation )) { return true; } }
		 */

		return super.getIsTimeZoneHeaderRequired(isUpdateOperation);
	}

	// region Properties

	/**
	 * Gets a value indicating whether the item is an attachment.
	 * 
	 * @return true, if is attachment
	 */
	public boolean isAttachment() {
		return this.parentAttachment != null;
	}

	/**
	 * Gets a value indicating whether this object is a real store item, or if
	 * it's a local object that has yet to be saved.
	 * 
	 * @return the checks if is new
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public boolean getIsNew() throws ServiceLocalException {

		// Item attachments don't have an Id, need to check whether the
		// parentAttachment is new or not.
		if (this.isAttachment()) {
			return this.getParentAttachment().isNew();
		} else {
			return super.isNew();
		}
	}

	/**
	 * Gets the Id of this item.
	 * 
	 * @return the id
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public ItemId getId() throws ServiceLocalException {
		return (ItemId) this.getPropertyBag().getObjectFromPropertyDefinition(
				this.getIdPropertyDefinition());
	}

	/**
	 * Get the MIME content of this item.
	 * 
	 * @return the mime content
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public MimeContent getMimeContent() throws ServiceLocalException {
		return (MimeContent) this.getPropertyBag()
				.getObjectFromPropertyDefinition(ItemSchema.MimeContent);
	}

	/**
	 * Sets the mime content.
	 * 
	 * @param value
	 *            the new mime content
	 * @throws Exception
	 *             the exception
	 */
	public void setMimeContent(MimeContent value) throws Exception {
		this.getPropertyBag().setObjectFromPropertyDefinition(
				ItemSchema.MimeContent, value);
	}

	/**
	 * Gets the Id of the parent folder of this item.
	 * 
	 * @return the parent folder id
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public FolderId getParentFolderId() throws ServiceLocalException {
		return (FolderId) this.getPropertyBag()
				.getObjectFromPropertyDefinition(ItemSchema.ParentFolderId);
	}

	/**
	 * Gets the sensitivity of this item.
	 * 
	 * @return the sensitivity
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public Sensitivity getSensitivity() throws ServiceLocalException {
		return (Sensitivity) this.getPropertyBag()
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
	 * Gets a list of the attachments to this item.
	 * 
	 * @return the attachments
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public AttachmentCollection getAttachments() throws ServiceLocalException {
		return (AttachmentCollection) this.getPropertyBag()
				.getObjectFromPropertyDefinition(ItemSchema.Attachments);
	}

	/**
	 * Gets the time when this item was received.
	 * 
	 * @return the date time received
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public Date getDateTimeReceived() throws ServiceLocalException {
		return (Date) this.getPropertyBag().getObjectFromPropertyDefinition(
				ItemSchema.DateTimeReceived);
	}

	/**
	 * Gets the size of this item.
	 * 
	 * @return the size
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public int getSize() throws ServiceLocalException {
		return ((Integer) this.getPropertyBag()
				.getObjectFromPropertyDefinition(ItemSchema.Size)).intValue();
	}

	/**
	 * Gets the list of categories associated with this item.
	 * 
	 * @return the categories
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public StringList getCategories() throws ServiceLocalException {
		return (StringList) this.getPropertyBag()
				.getObjectFromPropertyDefinition(ItemSchema.Categories);
	}

	/**
	 * Sets the categories.
	 * 
	 * @param value
	 *            the new categories
	 * @throws Exception
	 *             the exception
	 */
	public void setCategories(StringList value) throws Exception {
		this.getPropertyBag().setObjectFromPropertyDefinition(
				ItemSchema.Categories, value);
	}

	/**
	 * Gets the culture associated with this item.
	 * 
	 * @return the culture
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public String getCulture() throws ServiceLocalException {
		return (String) this.getPropertyBag().getObjectFromPropertyDefinition(
				ItemSchema.Culture);
	}

	/**
	 * Sets the culture.
	 * 
	 * @param value
	 *            the new culture
	 * @throws Exception
	 *             the exception
	 */
	public void setCulture(String value) throws Exception {
		this.getPropertyBag().setObjectFromPropertyDefinition(
				ItemSchema.Culture, value);
	}

	/**
	 * Gets the importance of this item.
	 * 
	 * @return the importance
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public Importance getImportance() throws ServiceLocalException {
		return (Importance) this.getPropertyBag()
				.getObjectFromPropertyDefinition(ItemSchema.Importance);
	}

	/**
	 * Sets the importance.
	 * 
	 * @param value
	 *            the new importance
	 * @throws Exception
	 *             the exception
	 */
	public void setImportance(Importance value) throws Exception {
		this.getPropertyBag().setObjectFromPropertyDefinition(
				ItemSchema.Importance, value);
	}

	/**
	 * Gets the In-Reply-To reference of this item.
	 * 
	 * @return the in reply to
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public String getInReplyTo() throws ServiceLocalException {
		return (String) this.getPropertyBag().getObjectFromPropertyDefinition(
				ItemSchema.InReplyTo);
	}

	/**
	 * Sets the in reply to.
	 * 
	 * @param value
	 *            the new in reply to
	 * @throws Exception
	 *             the exception
	 */
	public void setInReplyTo(String value) throws Exception {
		this.getPropertyBag().setObjectFromPropertyDefinition(
				ItemSchema.InReplyTo, value);
	}

	/**
	 * Gets a value indicating whether the message has been submitted to be
	 * sent.
	 * 
	 * @return the checks if is submitted
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public boolean getIsSubmitted() throws ServiceLocalException {
		return ((Boolean) this.getPropertyBag()
				.getObjectFromPropertyDefinition(ItemSchema.IsSubmitted))
				.booleanValue();
	}

	/**
	 * Gets a value indicating whether the message has been submitted to be
	 * sent.
	 * 
	 * @return the checks if is associated
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public boolean getIsAssociated() throws ServiceLocalException {
		return ((Boolean) this.getPropertyBag()
				.getObjectFromPropertyDefinition(ItemSchema.IsAssociated))
				.booleanValue();
	}

	/**
	 * Gets a value indicating whether the message has been submitted to be
	 * sent.
	 * 
	 * @return the checks if is draft
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public boolean getIsDraft() throws ServiceLocalException {
		return ((Boolean) this.getPropertyBag()
				.getObjectFromPropertyDefinition(ItemSchema.IsDraft))
				.booleanValue();
	}

	/**
	 * Gets a value indicating whether the item has been sent by the current
	 * authenticated user.
	 * 
	 * @return the checks if is from me
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public boolean getIsFromMe() throws ServiceLocalException {
		return ((Boolean) this.getPropertyBag()
				.getObjectFromPropertyDefinition(ItemSchema.IsFromMe))
				.booleanValue();
	}

	/**
	 * Gets a value indicating whether the item is a resend of another item.
	 * 
	 * @return the checks if is resend
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public boolean getIsResend() throws ServiceLocalException {
		return ((Boolean) this.getPropertyBag()
				.getObjectFromPropertyDefinition(ItemSchema.IsResend))
				.booleanValue();
	}

	/**
	 * Gets a value indicating whether the item has been modified since it was
	 * created.
	 * 
	 * @return the checks if is unmodified
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public boolean getIsUnmodified() throws ServiceLocalException {
		return ((Boolean) this.getPropertyBag()
				.getObjectFromPropertyDefinition(ItemSchema.IsUnmodified))
				.booleanValue();

	}

	/**
	 * Gets a list of Internet headers for this item.
	 * 
	 * @return the internet message headers
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public InternetMessageHeaderCollection getInternetMessageHeaders()
			throws ServiceLocalException {
		return (InternetMessageHeaderCollection) this.getPropertyBag()
				.getObjectFromPropertyDefinition(
						ItemSchema.InternetMessageHeaders);
	}

	/**
	 * Gets the date and time this item was sent.
	 * 
	 * @return the date time sent
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public Date getDateTimeSent() throws ServiceLocalException {
		return (Date) this.getPropertyBag().getObjectFromPropertyDefinition(
				ItemSchema.DateTimeSent);
	}

	/**
	 * Gets the date and time this item was created.
	 * 
	 * @return the date time created
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public Date getDateTimeCreated() throws ServiceLocalException {
		return (Date) this.getPropertyBag().getObjectFromPropertyDefinition(
				ItemSchema.DateTimeCreated);
	}

	/**
	 * Gets a value indicating which response actions are allowed on this item.
	 * Examples of response actions are Reply and Forward.
	 * 
	 * @return the allowed response actions
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public EnumSet<ResponseActions> getAllowedResponseActions()
			throws ServiceLocalException {
		return (EnumSet<ResponseActions>) this.getPropertyBag()
				.getObjectFromPropertyDefinition(
						ItemSchema.AllowedResponseActions);
	}

	/**
	 * Gets the date and time when the reminder is due for this item.
	 * 
	 * @return the reminder due by
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public Date getReminderDueBy() throws ServiceLocalException {
		return (Date) this.getPropertyBag().getObjectFromPropertyDefinition(
				ItemSchema.ReminderDueBy);
	}

	/**
	 * Sets the reminder due by.
	 * 
	 * @param value
	 *            the new reminder due by
	 * @throws Exception
	 *             the exception
	 */
	public void setReminderDueBy(Date value) throws Exception {
		this.getPropertyBag().setObjectFromPropertyDefinition(
				ItemSchema.ReminderDueBy, value);
	}

	/**
	 * Gets a value indicating whether a reminder is set for this item.
	 * 
	 * @return the checks if is reminder set
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public boolean getIsReminderSet() throws ServiceLocalException {
		return ((Boolean) this.getPropertyBag()
				.getObjectFromPropertyDefinition(ItemSchema.IsReminderSet))
				.booleanValue();
	}

	/**
	 * Sets the checks if is reminder set.
	 * 
	 * @param value
	 *            the new checks if is reminder set
	 * @throws Exception
	 *             the exception
	 */
	public void setIsReminderSet(Boolean value) throws Exception {
		this.getPropertyBag().setObjectFromPropertyDefinition(
				ItemSchema.IsReminderSet, value);
	}

	/**
	 * Gets the number of minutes before the start of this item when the
	 * reminder should be triggered.
	 * 
	 * @return the reminder minutes before start
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public int getReminderMinutesBeforeStart() throws ServiceLocalException {
		return ((Integer) this.getPropertyBag()
				.getObjectFromPropertyDefinition(
						ItemSchema.ReminderMinutesBeforeStart)).intValue();
	}

	/**
	 * Sets the reminder minutes before start.
	 * 
	 * @param value
	 *            the new reminder minutes before start
	 * @throws Exception
	 *             the exception
	 */
	public void setReminderMinutesBeforeStart(int value) throws Exception {
		this.getPropertyBag().setObjectFromPropertyDefinition(
				ItemSchema.ReminderMinutesBeforeStart, value);
	}

	/**
	 * Gets a text summarizing the Cc receipients of this item.
	 * 
	 * @return the display cc
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public String getDisplayCc() throws ServiceLocalException {
		return (String) this.getPropertyBag().getObjectFromPropertyDefinition(
				ItemSchema.DisplayCc);
	}

	/**
	 * Gets a text summarizing the To recipients of this item.
	 * 
	 * @return the display to
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public String getDisplayTo() throws ServiceLocalException {
		return (String) this.getPropertyBag().getObjectFromPropertyDefinition(
				ItemSchema.DisplayTo);
	}

	/**
	 * Gets a value indicating whether the item has attachments.
	 * 
	 * @return the checks for attachments
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public boolean getHasAttachments() throws ServiceLocalException {
		return ((Boolean) this.getPropertyBag()
				.getObjectFromPropertyDefinition(ItemSchema.HasAttachments))
				.booleanValue();
	}

	/**
	 * Gets the body of this item.
	 * 
	 * @return MessageBody
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public MessageBody getBody() throws ServiceLocalException {
		return (MessageBody) this.getPropertyBag()
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
	 * Gets the custom class name of this item.
	 * 
	 * @return the item class
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public String getItemClass() throws ServiceLocalException {
		return (String) this.getPropertyBag().getObjectFromPropertyDefinition(
				ItemSchema.ItemClass);
	}

	/**
	 * Sets the item class.
	 * 
	 * @param value
	 *            the new item class
	 * @throws Exception
	 *             the exception
	 */
	public void setItemClass(String value) throws Exception {
		this.getPropertyBag().setObjectFromPropertyDefinition(
				ItemSchema.ItemClass, value);
	}

	/**
	 * Gets the subject of this item.
	 * 
	 * @param subject
	 *            the new subject
	 * @throws Exception
	 *             the exception
	 */
	protected void setSubject(String subject) throws Exception {
		this.setSubject((Object) subject);
	}

	/**
	 * Sets the subject.
	 * 
	 * @param subject
	 *            the new subject
	 * @throws Exception
	 *             the exception
	 */
	public void setSubject(Object subject) throws Exception {
		this.getPropertyBag().setObjectFromPropertyDefinition(
				ItemSchema.Subject, subject);
	}

	/**
	 * Gets the subject.
	 * 
	 * @return the subject
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public String getSubject() throws ServiceLocalException {
		return (String) this.getPropertyBag().getObjectFromPropertyDefinition(
				ItemSchema.Subject);
	}

	/**
	 * Gets the query string that should be appended to the Exchange Web client
	 * URL to open this item using the appropriate read form in a web browser.
	 * 
	 * @return the web client read form query string
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public String getWebClientReadFormQueryString()
			throws ServiceLocalException {
		return (String) this.getPropertyBag().getObjectFromPropertyDefinition(
				ItemSchema.WebClientReadFormQueryString);
	}

	/**
	 * Gets the query string that should be appended to the Exchange Web client
	 * URL to open this item using the appropriate read form in a web browser.
	 * 
	 * @return the web client edit form query string
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public String getWebClientEditFormQueryString()
			throws ServiceLocalException {
		return (String) this.getPropertyBag().getObjectFromPropertyDefinition(
				ItemSchema.WebClientEditFormQueryString);
	}

	/**
	 * Gets a list of extended properties defined on this item.
	 * 
	 * @return the extended properties
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	@Override
	public ExtendedPropertyCollection getExtendedProperties()
			throws ServiceLocalException {
		return (ExtendedPropertyCollection) this.getPropertyBag()
				.getObjectFromPropertyDefinition(
						ServiceObjectSchema.extendedProperties);
	}

	/**
	 * Gets a value indicating the effective rights the current authenticated
	 * user has on this item.
	 * 
	 * @return the effective rights
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public EnumSet<EffectiveRights> getEffectiveRights()
			throws ServiceLocalException {
		return (EnumSet<EffectiveRights>) this.getPropertyBag()
				.getObjectFromPropertyDefinition(ItemSchema.EffectiveRights);
	}

	/**
	 * Gets the name of the user who last modified this item.
	 * 
	 * @return the last modified name
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public String getLastModifiedName() throws ServiceLocalException {
		return (String) this.getPropertyBag().getObjectFromPropertyDefinition(
				ItemSchema.LastModifiedName);
	}

	/**
	 * Gets the date and time this item was last modified.
	 * 
	 * @return the last modified time
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public Date getLastModifiedTime() throws ServiceLocalException {
		return (Date) this.getPropertyBag().getObjectFromPropertyDefinition(
				ItemSchema.LastModifiedTime);
	}

	/**
	 * Gets the Id of the conversation this item is part of.
	 * 
	 * @return the conversation id
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public ConversationId getConversationId() throws ServiceLocalException {
		return (ConversationId) this.getPropertyBag()
				.getObjectFromPropertyDefinition(ItemSchema.ConversationId);
	}

	/**
	 * Gets the body part that is unique to the conversation this item is part
	 * of.
	 * 
	 * @return the unique body
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public UniqueBody getUniqueBody() throws ServiceLocalException {
		return (UniqueBody) this.getPropertyBag()
				.getObjectFromPropertyDefinition(ItemSchema.UniqueBody);
	}

	/**
	 * Gets the default setting for how to treat affected task occurrences on
	 * Delete. Subclasses will override this for different default behavior.
	 * 
	 * @return the default affected task occurrences
	 */
	protected AffectedTaskOccurrence getDefaultAffectedTaskOccurrences() {
		return null;
	}

	/**
	 * Gets the default setting for sending cancellations on Delete. Subclasses
	 * will override this for different default behavior.
	 * 
	 * @return the default send cancellations mode
	 */
	protected SendCancellationsMode getDefaultSendCancellationsMode() {
		return null;
	}

	/**
	 * Gets the default settings for sending invitations on Save. Subclasses
	 * will override this for different default behavior.
	 * 
	 * @return the default send invitations mode
	 */
	protected SendInvitationsMode getDefaultSendInvitationsMode() {
		return null;
	}

	/**
	 * Gets the default settings for sending invitations or cancellations on
	 * Update. Subclasses will override this for different default behavior.
	 * 
	 * @return the default send invitations or cancellations mode
	 */
	protected SendInvitationsOrCancellationsMode getDefaultSendInvitationsOrCancellationsMode() {
		return null;
	}

}
