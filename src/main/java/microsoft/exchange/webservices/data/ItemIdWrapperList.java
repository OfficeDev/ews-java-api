/**************************************************************************
 * copyright file="ItemIdWrapperList.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ItemIdWrapperList.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/***
 * Represents a list a abstracted item Ids.
 */
class ItemIdWrapperList implements Iterable<AbstractItemIdWrapper> {

	/** The item ids. */
	private List<AbstractItemIdWrapper> itemIds = 
		new ArrayList<AbstractItemIdWrapper>();

	/**
	 * * Initializes a new instance of the class.
	 */
	protected ItemIdWrapperList() {
	}

	/**
	 * * Adds the specified item.
	 * 
	 * @param item
	 *            the item
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	protected void add(Item item) throws ServiceLocalException {
		this.itemIds.add(new ItemWrapper(item));

	}

	/**
	 * * Adds the specified item.
	 * 
	 * @param items
	 *            the items
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	protected void addRangeItem(Iterable<Item> items)
			throws ServiceLocalException {
		for (Item item : items) {
			this.add(item);
		}
	}

	/**
	 * * Adds the range.
	 * 
	 * @param itemIds
	 *            the item ids
	 */
	protected void addRange(Iterable<ItemId> itemIds) {
		for (ItemId itemId : itemIds) {
			this.add(itemId);
		}
	}

	/**
	 * * Adds the specified item id.
	 * 
	 * @param itemId
	 *            the item id
	 */
	protected void add(ItemId itemId) {
		this.itemIds.add(new ItemIdWrapper(itemId));

	}

	/**
	 * * Writes to XML.
	 * 
	 * @param writer
	 *            the writer
	 * @param ewsNamesapce
	 *            the ews namesapce
	 * @param xmlElementName
	 *            the xml element name
	 * @throws Exception
	 *             the exception
	 */
	protected void writeToXml(EwsServiceXmlWriter writer,
			XmlNamespace ewsNamesapce, String xmlElementName) throws Exception {
		if (this.getCount() > 0) {
			writer.writeStartElement(ewsNamesapce, xmlElementName);

			for (AbstractItemIdWrapper itemIdWrapper : this.itemIds) {
				itemIdWrapper.writeToXml(writer);
			}

			writer.writeEndElement();
		}
	}

	/**
	 * * Gets the count.
	 * 
	 * @return the count
	 */
	protected int getCount() {
		return this.itemIds.size();
	}

	/**
	 * * Gets the item at the specified index.
	 * 
	 * @param i
	 *            the i
	 * @return the item id wrapper list
	 */
	protected Item getItemIdWrapperList(int i) {
		return this.itemIds.get(i).getItem();
	}

	/***
	 *Gets an Iterator that iterates through the elements of the collection.
	 * 
	 * @return An IEnumerator for the collection
	 */
	@Override
	public Iterator<AbstractItemIdWrapper> iterator() {

		return itemIds.iterator();
	}
}
