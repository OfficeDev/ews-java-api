/**************************************************************************
 * copyright file="UpdateFolderResponse.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the UpdateFolderResponse.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents response to UpdateFolder request.
 * 
 * 
 */
final class UpdateFolderResponse extends ServiceResponse implements
		IGetObjectInstanceDelegate<ServiceObject> {

	/** The folder. */
	private Folder folder;

	/**
	 * Initializes a new instance of the UpdateFolderResponse class.
	 * 
	 * @param folder
	 *            The folder
	 */
	protected UpdateFolderResponse(Folder folder) {
		super();
		EwsUtilities.EwsAssert(folder != null, "UpdateFolderResponse.ctor",
				"folder is null");

		this.folder = folder;
	}

	/**
	 * Reads response elements from XML.
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

		reader.readServiceObjectsCollectionFromXml(XmlElementNames.Folders,
				this, false, /* clearPropertyBag */
				null, /* requestedPropertySet */
				false); /* summaryPropertiesOnly */
	}

	/**
	 * Clears the change log of the updated folder if the update succeeded.
	 */
	@Override
	protected void loaded() {
		if (this.getResult() == ServiceResult.Success) {
			this.folder.clearChangeLog();
		}
	}

	/**
	 * Gets Folder instance.
	 * 
	 * @param session
	 *            The session
	 * @param xmlElementName
	 *            Name of the XML element.
	 * @return Folder
	 */
	private Folder getObjectInstance(ExchangeService session,
			String xmlElementName) {
		return this.folder;
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
