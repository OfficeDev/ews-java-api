/**************************************************************************
 * copyright file="MoveCopyFolderResponse.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the MoveCopyFolderResponse.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.List;

/**
 * Represents the base response class for individual folder move and copy
 * operations.
 * 
 * 
 */
public final class MoveCopyFolderResponse extends ServiceResponse implements
		IGetObjectInstanceDelegate<ServiceObject> {

	/** The folder. */
	private Folder folder;

	/**
	 * Initializes a new instance of the MoveCopyFolderResponse class.
	 */
	protected MoveCopyFolderResponse() {
		super();
	}

	/**
	 * Gets Folder instance.
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
		return EwsUtilities.createEwsObjectFromXmlElementName(Folder.class,
				service, xmlElementName);
	}

	/**
	 * Reads response elements from XML.
	 * 
	 * @param reader
	 *            The reader.
	 * @throws Exception
	 *             the exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void readElementsFromXml(EwsServiceXmlReader reader)
			throws Exception {
		super.readElementsFromXml(reader);

		List<Folder> folders;
		try {
			folders = reader.readServiceObjectsCollectionFromXml(

			XmlElementNames.Folders, this, false,/* clearPropertyBag */
			null, /* requestedPropertySet */
			false); /* summaryPropertiesOnly */

			this.folder = folders.get(0);
		} catch (ServiceLocalException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Gets the new (moved or copied) folder.
	 * 
	 * @return the folder
	 */
	public Folder getFolder() {
		return folder;
	}

	/**
	 * Gets the object instance delegate.
	 * 
	 * @param service
	 *            accepts ExchangeService
	 * @param xmlElementName
	 *            accepts String
	 * @return Object
	 * @throws Exception
	 *             throws Exception
	 */
	@Override
	public ServiceObject getObjectInstanceDelegate(ExchangeService service,
			String xmlElementName) throws Exception {
		return this.getObjectInstance(service, xmlElementName);
	}

}
