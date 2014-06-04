/**************************************************************************
 * copyright file="AbstractFolderIdWrapper.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the AbstractFolderIdWrapper.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents the abstraction of a folder Id.
 */
abstract class AbstractFolderIdWrapper {

	/**
	 * Obtains the Folder object associated with the wrapper.
	 * 
	 * @return The Folder object associated with the wrapper.
	 */
	public Folder getFolder() {
		return null;
	}

	/**
	 * Initializes a new instance of AbstractFolderIdWrapper.
	 */
	protected AbstractFolderIdWrapper() {
	}

	/**
	 * Writes the Id encapsulated in the wrapper to XML.
	 * 
	 * @param writer
	 *            the writer
	 * @throws Exception
	 *             the exception
	 */
	protected abstract void writeToXml(EwsServiceXmlWriter writer)
			throws Exception;

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
	}
}
