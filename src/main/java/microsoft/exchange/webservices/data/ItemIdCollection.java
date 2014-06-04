/**************************************************************************
 * copyright file="ItemIdCollection.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ItemIdCollection class.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents a collection of item Ids.
 */
public final class ItemIdCollection extends ComplexPropertyCollection<ItemId> {
	/**
	 * Initializes a new instance of the <see cref="ItemIdCollection"/> class.
	 */
	protected ItemIdCollection() {
		super();
	}

	/**
	 * Creates the complex property.
	 * @param xmlElementName Name of the XML element.
	 * @return ItemId.
	 */
	@Override
	protected ItemId createComplexProperty(String xmlElementName) {
		return new ItemId();
	}

	/**
	 * Gets the name of the collection item XML element.
	 * @param complexProperty The complex property.
	 * @return XML element name.
	 */
	@Override
	protected String getCollectionItemXmlElementName(ItemId complexProperty) {
		return complexProperty.getXmlElementName();
	}
}