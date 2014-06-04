/**************************************************************************
 * copyright file="CreateFolderRequest.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the CreateFolderRequest.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.Collection;

/**
 * Represents a CreateFolder request.
 * 
 * 
 */
final class CreateFolderRequest extends CreateRequest<Folder, ServiceResponse> {

	/**
	 * Initializes a new instance of the CreateFolderRequest class.
	 * 
	 * @param service
	 *            The service
	 * @param errorHandlingMode
	 *            Indicates how errors should be handled.
	 * @throws Exception 
	 */
	protected CreateFolderRequest(ExchangeService service,
			ServiceErrorHandling errorHandlingMode)
			throws Exception {
		super(service, errorHandlingMode);
	}

	/**
	 * Validate request.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void validate() throws Exception {
		super.validate();
		EwsUtilities.validateParam(this.getFolders(), "Folders");

		// Validate each folder.
		for (Folder folder : this.getFolders()) {
			folder.validate();
		}
	}

	/**
	 * Creates the service response.
	 * 
	 * @param service
	 *            the service
	 * @param responseIndex
	 *            Index of the response.
	 * @return Service response.
	 */
	@Override
	protected ServiceResponse createServiceResponse(ExchangeService service,
			int responseIndex) {
		return new CreateFolderResponse((Folder)EwsUtilities
				.getEnumeratedObjectAt(this.getFolders(), responseIndex));
	}

	/**
	 * Gets the name of the XML element.
	 * 
	 * @return Xml element name
	 */
	@Override
	protected String getXmlElementName() {
		return XmlElementNames.CreateFolder;
	}

	/**
	 *Gets the name of the response XML element.
	 * 
	 * @return Xml element name
	 */
	@Override
	protected String getResponseXmlElementName() {
		return XmlElementNames.CreateFolderResponse;
	}

	/**
	 * Gets the name of the response message XML element.
	 * 
	 * @return Xml element name
	 */
	@Override
	protected String getResponseMessageXmlElementName() {
		return XmlElementNames.CreateFolderResponseMessage;
	}

	/**
	 * Gets the name of the parent folder XML element.
	 * 
	 * @return Xml element name
	 */
	@Override
	protected String getParentFolderXmlElementName() {
		return XmlElementNames.ParentFolderId;
	}

	/**
	 * Gets the name of the object collection XML element.
	 * 
	 * @return Xml element name
	 */
	@Override
	protected String getObjectCollectionXmlElementName() {
		return XmlElementNames.Folders;
	}

	/**
	 * Gets the request version. Earliest Exchange version in which this request
	 * is supported.
	 * 
	 * @return the minimum required server version
	 */
	@Override
	protected ExchangeVersion getMinimumRequiredServerVersion() {
		return ExchangeVersion.Exchange2007_SP1;
	}

	/**
	 * Gets the folders.
	 * 
	 * @return the folders
	 */
	public Iterable<Folder> getFolders() {
		return this.getObjects();
	}

	/**
	 * Sets the folders.
	 * 
	 * @param folder
	 *            the new folders
	 */
	public void setFolders(Iterable<Folder> folder) {
		this.setObjects((Collection<Folder>)folder);
	}

}
