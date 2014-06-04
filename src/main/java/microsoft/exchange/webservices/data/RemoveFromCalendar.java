/**************************************************************************
 * copyright file="RemoveFromCalendar.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the RemoveFromCalendar.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.List;

/**
 * Represents a response object created to remove a calendar item from a meeting
 * cancellation.
 * 
 * 
 */
@ServiceObjectDefinition(xmlElementName = XmlElementNames.RemoveItem, returnedByServer = false)
 class RemoveFromCalendar extends ServiceObject {

	/** The reference item. */
	private Item referenceItem;

	/**
	 * Initializes a new instance of the RemoveFromCalendar class.
	 * 
	 * @param referenceItem
	 *            The reference item
	 * @throws Exception
	 *             the exception
	 */
	RemoveFromCalendar(Item referenceItem) throws Exception {
		super(referenceItem.getService());
		EwsUtilities.EwsAssert(referenceItem != null,
				"RemoveFromCalendar.ctor", "referenceItem is null");

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
	 *            The properties to load.
	 */
	@Override
	protected void internalLoad(PropertySet propertySet) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Deletes the object.
	 * 
	 * @param deleteMode
	 *            The deletion mode.
	 * @param sendCancellationsMode
	 *            Indicates whether meeting cancellation messages should be
	 *            sent.
	 * @param affectedTaskOccurrences
	 *            Indicate which occurrence of a recurring task should be
	 *            deleted.
	 */
	@Override
	protected void internalDelete(DeleteMode deleteMode,
			SendCancellationsMode sendCancellationsMode,
			AffectedTaskOccurrence affectedTaskOccurrences) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Create response object.
	 * 
	 * @param parentFolderId
	 *            The parent folder id.
	 * @param messageDisposition
	 *            The message disposition.
	 * @return A list of items that were created or modified as a results of
	 *         this operation.
	 * @throws Exception
	 *             the exception
	 */
	protected List<Item> internalCreate(FolderId parentFolderId,
			MessageDisposition messageDisposition) throws Exception {
		((ItemId)this.getPropertyBag().getObjectFromPropertyDefinition(
				ResponseObjectSchema.ReferenceItemId))
				.assign(this.referenceItem.getId());

		return this.getService().internalCreateResponseObject(this,
				parentFolderId, messageDisposition);
	}

}