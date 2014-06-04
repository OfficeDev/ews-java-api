/**************************************************************************
 * copyright file="CreateFolderResponse.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the CreateFolderResponse.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.List;

/**
 * Represents the response to an individual folder creation operation.
 * 
 */
final class CreateFolderResponse extends ServiceResponse implements
		IGetObjectInstanceDelegate<ServiceObject> {

	/** The folder. */
	private Folder folder;

	/**
	 * Initializes a new instance of the CreateFolderResponse class.
	 * 
	 * @param folder
	 *            The folder.
	 */
	CreateFolderResponse(Folder folder) {
		super();
		this.folder = folder;
	}

	/**
	 * Gets the object instance.
	 * 
	 * @param service
	 *            The service.
	 * @param xmlElementName
	 *            Name of the XML element.
	 * @return Folder
	 * @throws Exception
	 *             the exception
	 */
	private Folder getObjectInstance(ExchangeService service,
			String xmlElementName) throws Exception {
		if (this.folder != null) {
			return this.folder;
		} else {
			return EwsUtilities.createEwsObjectFromXmlElementName(Folder.class,
					service, xmlElementName);
		}
	}

	/**
	 * Reads response elements from XML.
	 * 
	 * @param reader
	 *            The reader
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void readElementsFromXml(EwsServiceXmlReader reader)
			throws Exception {
		super.readElementsFromXml(reader);

		List<Folder> folders = reader.readServiceObjectsCollectionFromXml(
				XmlElementNames.Folders, this, false, /* clearPropertyBag */
				null, /* requestedPropertySet */
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
	 * Clears the change log of the created folder if the creation succeeded.
	 */
	@Override
	protected void loaded() {
		if (this.getResult() == ServiceResult.Success) {
			this.folder.clearChangeLog();
		}
	}

}
