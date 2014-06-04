/**************************************************************************
 * copyright file="GetItemResponse.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the GetItemResponse.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.List;

/***
 * Represents a response to an individual item retrieval operation.
 */
public final class GetItemResponse extends ServiceResponse implements
		IGetObjectInstanceDelegate {

	/** The item. */
	private Item item;

	/** The property set. */
	private PropertySet propertySet;

	/**
	 * * Initializes a new instance of the class.
	 * 
	 * @param item
	 *            the item
	 * @param propertySet
	 *            the property set
	 */
	protected GetItemResponse(Item item, PropertySet propertySet) {
		super();
		this.item = item;
		this.propertySet = propertySet;
		EwsUtilities.EwsAssert(this.propertySet != null,
				"GetItemResponse.ctor", "PropertySet should not be null");
	}

	/**
	 * * Reads response elements from XML.
	 * 
	 * @param reader
	 *            the reader
	 * @throws InstantiationException
	 *             the instantiation exception
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 * @throws Exception
	 *             the exception
	 */
	@SuppressWarnings("unchecked")
	protected void readElementsFromXml(EwsServiceXmlReader reader)
			throws InstantiationException, IllegalAccessException, Exception {
		super.readElementsFromXml(reader);

		List<Item> items = reader.readServiceObjectsCollectionFromXml(
				XmlElementNames.Items, this,
				true, /* clearPropertyBag */
				this.propertySet, /* requestedPropertySet */
				false); /* summaryPropertiesOnly */

		this.item = items.get(0);
	}

	/**
	 * * Gets Item instance.
	 * 
	 * @param service
	 *            the service
	 * @param xmlElementName
	 *            the xml element name
	 * @return Item
	 * @throws Exception
	 *             the exception
	 */
	@SuppressWarnings("unused")
	private Item getObjectInstance(ExchangeService service,
			String xmlElementName) throws Exception {
		if (this.getItem() != null) {
			return this.getItem();
		} else {
			return EwsUtilities.createEwsObjectFromXmlElementName(Item.class,
					service, xmlElementName);

		}
	}

	/**
	 * Gets the item that was retrieved.
	 * 
	 * @return the item
	 */
	public Item getItem() {
		return this.item;
	}

	/**
	 * Gets the object instance delegate.
	 * 
	 * @param service
	 *            accepts ExchangeService
	 * @param xmlElementName
	 *            accepts String
	 * @return Name
	 * @throws Exception
	 *             throws exception
	 */
	@Override
	public ServiceObject getObjectInstanceDelegate(ExchangeService service,
			String xmlElementName) throws Exception {
		return getObjectInstance(service, xmlElementName);
	}
}
