/**************************************************************************
 * copyright file="SuppressReadReceipt.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the SuppressReadReceipt.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents a response object created to supress read receipts for an item.
 */
@ServiceObjectDefinition(xmlElementName = XmlElementNames.SuppressReadReceipt, returnedByServer = false)
 final class SuppressReadReceipt extends ServiceObject {

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
	protected SuppressReadReceipt(Item referenceItem) throws Exception {
		super(referenceItem.getService());
		EwsUtilities.EwsAssert(referenceItem != null,
				"SuppressReadReceipt.ctor", "referenceItem is null");

		referenceItem.throwIfThisIsNew();
		this.referenceItem = referenceItem;
	}

	/**
	 * Internal method to return the schema associated with this type of object.
	 * 
	 * @return The schema associated with this type of object.
	 */
	@Override
	protected ServiceObjectSchema getSchema() {
		return ResponseObjectSchema.Instance;
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
	 * Loads the specified set of properties on the object.
	 * 
	 * @param propertySet
	 *            the property set
	 */
	@Override
	protected void internalLoad(PropertySet propertySet) {
		throw new UnsupportedOperationException();
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
	 */
	@Override
	protected void internalDelete(DeleteMode deleteMode,
			SendCancellationsMode sendCancellationsMode,
			AffectedTaskOccurrence affectedTaskOccurrences) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Create the response object.
	 * 
	 * @param parentFolderId
	 *            the parent folder id
	 * @param messageDisposition
	 *            the message disposition
	 * @throws Exception
	 *             the exception
	 */
	protected void internalCreate(FolderId parentFolderId,
			MessageDisposition messageDisposition) throws Exception {
		((ItemId)this.getPropertyBag().getObjectFromPropertyDefinition(
				ResponseObjectSchema.ReferenceItemId))
				.assign(this.referenceItem.getId());
		this.getService().internalCreateResponseObject(this, parentFolderId,
				messageDisposition);
	}
}
