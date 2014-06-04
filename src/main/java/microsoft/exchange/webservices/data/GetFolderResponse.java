/**************************************************************************
 * copyright file="GetFolderResponse.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the GetFolderResponse.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.List;

/**
 * Represents the response to an individual folder retrieval operation.
 * 
 */
public final class GetFolderResponse extends ServiceResponse implements
		IGetObjectInstanceDelegate<ServiceObject> {

	/** The folder. */
	private Folder folder;

	/** The property set. */
	private PropertySet propertySet;

	/**
	 * Initializes a new instance of the GetFolderResponse class.
	 * 
	 * @param folder
	 *            The folder.
	 * @param propertySet
	 *            The property set from the request.
	 */
	protected GetFolderResponse(Folder folder, PropertySet propertySet) {
		super();
		this.folder = folder;
		this.propertySet = propertySet;
		EwsUtilities.EwsAssert(this.propertySet != null,
				"GetFolderResponse.ctor", "PropertySet should not be null");
	}

	/**
	 * Reads response elements from XML.
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
		List<Folder> folders = reader.readServiceObjectsCollectionFromXml(
				XmlElementNames.Folders, this, true, /* clearPropertyBag */
				this.propertySet, /* requestedPropertySet */
				false); /* summaryPropertiesOnly */
		this.folder = folders.get(0);
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
	 * Gets the folder instance.
	 * 
	 * @param service
	 *            The service.
	 * @param xmlElementName
	 *            Name of the XML element.
	 * @return folder
	 * @throws Exception
	 *             the exception
	 */
	private Folder getObjectInstance(ExchangeService service,
			String xmlElementName) throws Exception {
		if (this.getFolder() != null) {
			return this.getFolder();
		} else {
			return EwsUtilities.createEwsObjectFromXmlElementName(Folder.class,
					service, xmlElementName);
		}
	}

	/**
	 * Gets the folder that was retrieved.
	 * 
	 * @return folder
	 */
	public Folder getFolder() {
		return this.folder;
	}

}
