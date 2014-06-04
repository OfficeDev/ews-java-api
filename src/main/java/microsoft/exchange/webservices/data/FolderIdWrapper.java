/**************************************************************************
 * copyright file="FolderIdWrapper.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the FolderIdWrapper.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/***
 * Represents a folder Id provided by a FolderId object.
 */
class FolderIdWrapper extends AbstractFolderIdWrapper {

	/**
	 * The FolderId object providing the Id.
	 */
	private FolderId folderId;

	/**
	 * Initializes a new instance of FolderIdWrapper.
	 * 
	 * @param folderId
	 *            the folder id
	 */
	protected FolderIdWrapper(FolderId folderId) {
		EwsUtilities.EwsAssert(folderId != null, "FolderIdWrapper.ctor",
				"folderId is null");
		this.folderId = folderId;
	}

	/**
	 * Writes the Id encapsulated in the wrapper to XML.
	 * 
	 * @param writer
	 *            the writer
	 * @throws Exception
	 *             the exception
	 */
	protected void writeToXml(EwsServiceXmlWriter writer) 
			throws Exception {
		this.folderId.writeToXml(writer);
	}

	/**
	 * Validates folderId against specified version.
	 * 
	 * @param version
	 *            the version
	 * @throws ServiceVersionException
	 *             the service version exception
	 */
	protected void validate(ExchangeVersion version)
			throws ServiceVersionException {
		this.folderId.validate(version);
	}
}
