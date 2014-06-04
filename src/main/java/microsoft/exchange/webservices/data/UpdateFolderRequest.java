/**************************************************************************
 * copyright file="UpdateFolderRequest.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the UpdateFolderRequest.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.ArrayList;

/**
 * Represents an UpdateFolder request.
 * 
 * 
 */
final class UpdateFolderRequest extends
		MultiResponseServiceRequest<ServiceResponse> {

	/** The folders. */
	private ArrayList<Folder> folders = new ArrayList<Folder>();

	/**
	 * Initializes a new instance of the UpdateFolderRequest class.
	 * 
	 * @param service
	 *            The Servcie
	 * @param errorHandlingMode
	 *            Indicates how errors should be handled.
	 * @throws Exception 
	 */
	protected UpdateFolderRequest(ExchangeService service,
			ServiceErrorHandling errorHandlingMode)
			throws Exception {
		super(service, errorHandlingMode);
	}

	/**
	 * validates request.
	 * 
	 * @throws ServiceLocalException
	 *             the service local exception
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void validate() throws ServiceLocalException, Exception {
		super.validate();
		EwsUtilities.validateParamCollection(this
				.getFolders().iterator(), "Folders");
		for (int i = 0; i < this.getFolders().size(); i++) {
			Folder folder = this.getFolders().get(i);

			if ((folder == null) || folder.isNew()) {
				throw new IllegalArgumentException(String.format(
						Strings.FolderToUpdateCannotBeNullOrNew, i));
			}

			folder.validate();
		}
	}

	/**
	 * Creates the service response.
	 * 
	 * @param session
	 *            The session
	 * @param responseIndex
	 *            Index of the response.
	 * @return Service response.
	 */
	@Override
	protected ServiceResponse createServiceResponse(ExchangeService session,
			int responseIndex) {
		return new UpdateFolderResponse(this.getFolders().get(responseIndex));
	}

	/**
	 *Gets the name of the XML element.
	 * 
	 * @return Xml element name.
	 */
	@Override
	protected String getXmlElementName() {
		return XmlElementNames.UpdateFolder;
	}

	/**
	 * Gets the name of the response XML element.
	 * 
	 * @return Xml element name.
	 */
	@Override
	protected String getResponseXmlElementName() {
		return XmlElementNames.UpdateFolderResponse;
	}

	/**
	 * Gets the name of the response message XML element.
	 * 
	 * @return Xml element name.
	 */
	@Override
	protected String getResponseMessageXmlElementName() {
		return XmlElementNames.UpdateFolderResponseMessage;
	}

	/**
	 * Gets the expected response message count.
	 * 
	 * @return Number of expected response messages.
	 */
	@Override
	protected int getExpectedResponseMessageCount() {
		return this.getFolders().size();
	}

	/**
	 * Writes to xml.
	 * 
	 * @param writer
	 *            the writer
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void writeElementsToXml(EwsServiceXmlWriter writer)
			throws Exception {
		writer.writeStartElement(XmlNamespace.Messages,
				XmlElementNames.FolderChanges);

		for (Folder folder : this.folders) {
			folder.writeToXmlForUpdate(writer);
		}

		writer.writeEndElement();
	}

	/**
	 * Gets the request version.
	 * 
	 * @return Earliest Exchange version in which this request is supported.
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
	public ArrayList<Folder> getFolders() {
		return this.folders;
	}
}
