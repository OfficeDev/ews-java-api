/**************************************************************************
 * copyright file="ItemWrapper.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ItemWrapper.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/***
 * Represents an item Id provided by a ItemBase object.
 */
class ItemWrapper extends AbstractItemIdWrapper {

	/***
	 *The ItemBase object providing the Id.
	 */
	private Item item;

	/**
	 * * Initializes a new instance of ItemWrapper.
	 * 
	 * @param item
	 *            the item
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	protected ItemWrapper(Item item) throws ServiceLocalException {
		EwsUtilities
				.EwsAssert(item != null, "ItemWrapper.ctor", "item is null");
		EwsUtilities.EwsAssert(!item.isNew(), "ItemWrapper.ctor",
				"item does not have an Id");
		this.item = item;
	}

	/***
	 *Obtains the ItemBase object associated with the wrapper.
	 * 
	 * @return The ItemBase object associated with the wrapper
	 */
	public Item getItem() {
		return this.item;
	}

	/**
	 * * Writes the Id encapsulated in the wrapper to XML.
	 * 
	 * @param writer
	 *            the writer
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void writeToXml(EwsServiceXmlWriter writer) throws Exception {
		this.item.getId().writeToXml(writer);

	}
}
