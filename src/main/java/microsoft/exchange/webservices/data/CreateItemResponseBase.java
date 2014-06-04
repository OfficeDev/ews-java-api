/**************************************************************************
 * copyright file="CreateItemResponseBase.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the CreateItemResponseBase.java.
 **************************************************************************/

package microsoft.exchange.webservices.data;

import java.util.List;

/***
 * Represents the base response class for item creation operations.
 * 
 * 
 */
@EditorBrowsable(state = EditorBrowsableState.Never)
abstract class CreateItemResponseBase extends ServiceResponse implements
		IGetObjectInstanceDelegate<ServiceObject> {

	/** The items. */
	private List<Item> items;

	/**
	 * * Gets Item instance.
	 * 
	 * @param service
	 *            The service.
	 * @param xmlElementName
	 *            Name of the XML element.
	 * @return Item.
	 * @throws InstantiationException
	 *             the instantiation exception
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 * @throws Exception
	 *             the exception
	 */
	protected abstract Item getObjectInstance(ExchangeService service,
			String xmlElementName) throws InstantiationException,
			IllegalAccessException, Exception;

	/**
	 * Gets the object instance delegate.
	 * 
	 * @param service
	 *            accepts ExchangeService
	 * @param xmlElementName
	 *            accepts String
	 * @return object
	 * @throws Exception
	 *             throws Exception
	 */
	public ServiceObject getObjectInstanceDelegate(ExchangeService service,
			String xmlElementName) throws Exception {
		return this.getObjectInstance(service, xmlElementName);
	}

	/**
	 * * Initializes a new instance.
	 */
	protected CreateItemResponseBase() {
		super();
	}

	/**
	 * * Reads response elements from XML.
	 * 
	 * @param reader
	 *            the reader
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void readElementsFromXml(EwsServiceXmlReader reader)
			throws Exception {
		super.readElementsFromXml(reader);
		this.items = reader.readServiceObjectsCollectionFromXml(
				XmlElementNames.Items, this, false, /* clearPropertyBag */
				null, /* requestedPropertySet */
				false); /* summaryPropertiesOnly */
	}

	/***
	 * Gets the items.
	 * 
	 * @return List of items.
	 */
	public List<Item> getItems() {
		return items;
	}

}
