/**************************************************************************
 * copyright file="MoveCopyItemResponse.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the MoveCopyItemResponse.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.List;

/***
 * Represents a response to a Move or Copy operation.
 */
public final class MoveCopyItemResponse extends ServiceResponse implements
		IGetObjectInstanceDelegate<ServiceObject> {

	/** The item. */
	private Item item;

	/***
	 * Represents a response to a Move or Copy operation.
	 */
	protected MoveCopyItemResponse() {
		super();
	}

	/**
	 * * Gets Item instance.
	 * 
	 * @param service
	 *            the service
	 * @param xmlElementName
	 *            the xml element name
	 * @return the object instance
	 * @throws Exception
	 *             the exception
	 */
	private Item getObjectInstance(ExchangeService service,
			String xmlElementName) throws Exception {
		return EwsUtilities.createEwsObjectFromXmlElementName(Item.class,
				service, xmlElementName);
	}

	/**
	 * * Reads response elements from XML.
	 * 
	 * @param reader
	 *            the reader
	 * @throws Exception
	 *             the exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void readElementsFromXml(EwsServiceXmlReader reader)
			throws Exception {
		super.readElementsFromXml(reader);
		List<Item> items = reader.readServiceObjectsCollectionFromXml(
				XmlElementNames.Items, this, false, /* clearPropertyBag */
				null, /* requestedPropertySet */
				false); /* summaryPropertiesOnly */

		// We only receive the copied or moved items if the copy or move
		// operation was within
		// a single mailbox. No item is returned if the operation is
		// cross-mailbox, from a
		// mailbox to a public folder or from a public folder to a mailbox.
		if (items.size() > 0) {
			this.item = items.get(0);
		}
	}

	/**
	 * Gets the object instance delegate.
	 * 
	 * @param service
	 *            the service
	 * @param xmlElementName
	 *            the xml element name
	 * @return the object instance delegate
	 * @throws Exception
	 *             the exception
	 */
	@Override
	public ServiceObject getObjectInstanceDelegate(ExchangeService service,
			String xmlElementName) throws Exception {
		return this.getObjectInstance(service, xmlElementName);
	}

	/**
	 * * Gets the copied or moved item. Item is null if the copy or move
	 * operation was between two mailboxes or between a mailbox and a public
	 * folder.
	 * 
	 * @return the item
	 */
	public Item getItem() {
		return this.item;
	}

}
