/**************************************************************************
 * copyright file="ItemCollection.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ItemCollection.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a collection of items.
 * 
 * @param <TItem>
 *            the generic type. The type of item the collection contains.
 */
@EditorBrowsable(state = EditorBrowsableState.Never)
public final class ItemCollection<TItem extends Item> extends ComplexProperty
		implements Iterable<TItem> {

	/** The items. */
	private List<TItem> items = new ArrayList<TItem>();

	/**
	 * Initializes a new instance of the "ItemCollection&lt;TItem&gt;" class.
	 */
	protected ItemCollection() {
		super();
	}

	/**
	 * Loads from XML.
	 * 
	 * @param reader
	 *            The reader.
	 * @param localElementName
	 *            Name of the local element.
	 * @throws Exception
	 *             the exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void loadFromXml(EwsServiceXmlReader reader,
			String localElementName) throws Exception {
		reader.ensureCurrentNodeIsStartElement(XmlNamespace.Types,
				localElementName);
		if (!reader.isEmptyElement()) {
			do {
				reader.read();

				if (reader.getNodeType().getNodeType() == XMLNodeType.START_ELEMENT) {
					TItem item = (TItem)EwsUtilities
							.createEwsObjectFromXmlElementName(Item.class,
									reader.getService(), reader.getLocalName());

					if (item == null) {
						reader.skipCurrentElement();
					} else {
						try {
							item.loadFromXml(reader, 
									true /* clearPropertyBag */);
						} catch (ServiceObjectPropertyException e) {							
							e.printStackTrace();
						} catch (ServiceVersionException e) {						
							e.printStackTrace();
						}

						this.items.add(item);
					}
				}
			} while (!reader.isEndElement(XmlNamespace.Types, 
					localElementName));
		}else {
			reader.read();
		}
	}

	/**
	 * Gets the total number of items in the collection.
	 * 
	 * @return the count
	 */
	public int getCount() {
		return this.items.size();
	}

	/**
	 * Gets the item at the specified index.
	 * 
	 * @param index
	 *            The zero-based index of the item to get.
	 * @return The item at the specified index.
	 */
	public TItem getItem(int index) {

		if (index < 0 || index >= this.getCount()) {
			throw new ArrayIndexOutOfBoundsException(Strings.IndexIsOutOfRange);
		}
		return this.items.get(index);
	}

	/**
	 * Gets an iterator that iterates through the elements of the collection.
	 * 
	 * @return An Iterator for the collection.
	 */
	public Iterator<TItem> getIterator() {
		return this.items.iterator();
	}

	/**
     * Returns an iterator over a set of elements of type T.
     * 
     * @return an Iterator.
     */
	@Override
	public Iterator<TItem> iterator() {
		return this.items.iterator();
	}

}
