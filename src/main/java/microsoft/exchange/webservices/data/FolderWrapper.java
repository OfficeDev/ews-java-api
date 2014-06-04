/**************************************************************************
 * copyright file="FolderWrapper.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the FolderWrapper.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/***
 *Represents a folder Id provided by a Folder object.
 */
class FolderWrapper extends AbstractFolderIdWrapper {

	/**
	 * The Folder object providing the Id.
	 */
	private Folder folder;

	/**
	 * Initializes a new instance of FolderWrapper.
	 * 
	 * @param folder
	 *            the folder
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	protected FolderWrapper(Folder folder) throws ServiceLocalException {
		EwsUtilities.EwsAssert(folder != null, "FolderWrapper.ctor",
				"folder is null");
		EwsUtilities.EwsAssert(!folder.isNew(), "FolderWrapper.ctor",
				"folder does not have an Id");
		this.folder = folder;
	}

	/**
	 * Obtains the Folder object associated with the wrapper.
	 * 
	 * @return The Folder object associated with the wrapper
	 */
	public Folder getFolder() {
		return this.folder;
	}

	/**
	 * Writes the Id encapsulated in the wrapper to XML.
	 * 
	 * @param writer
	 *            the writer
	 * @return The Folder object associated with the wrapper
	 * @throws Exception
	 *             the exception
	 */
	protected void writeToXml(EwsServiceXmlWriter writer) throws Exception {
		this.folder.getId().writeToXml(writer);
	}
}
