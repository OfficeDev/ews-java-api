/**************************************************************************
 * copyright file="ItemIdWrapper.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ItemIdWrapper.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/***
 * Represents an item Id provided by a ItemId object.
 */
class ItemIdWrapper extends AbstractItemIdWrapper {

	/***
	 *The ItemId object providing the Id.
	 */
	private ItemId itemId;

	/**
	 * * Initializes a new instance of ItemIdWrapper.
	 * 
	 * @param itemId
	 *            the item id
	 */
	protected ItemIdWrapper(ItemId itemId) {
		EwsUtilities.EwsAssert(itemId != null, "ItemIdWrapper.ctor",
				"itemId is null");
		this.itemId = itemId;
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
		this.itemId.writeToXml(writer);
	}

}
