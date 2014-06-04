/**************************************************************************
 * copyright file="DeleteFolderRequest.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the DeleteFolderRequest.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents a DeleteFolder request.
 * 
 */
final class DeleteFolderRequest extends DeleteRequest<ServiceResponse> {

	/** The folder ids. */
	private FolderIdWrapperList folderIds = new FolderIdWrapperList();

	/**
	 * Initializes a new instance of the DeleteFolderRequest class.
	 * 
	 * @param service
	 *            the service
	 * @param errorHandlingMode
	 *            the error handling mode
	 * @throws Exception 
	 */
	protected DeleteFolderRequest(ExchangeService service,
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
		EwsUtilities.validateParam(this.getFolderIds(), "FolderIds");
		this.getFolderIds().validate(
				this.getService().getRequestedServerVersion());
	}

	/**
	 * Gets the expected response message count.
	 * 
	 * @return Number of expected response messages.
	 */
	@Override
	protected int getExpectedResponseMessageCount() {
		return this.getFolderIds().getCount();
	}

	/**
	 * Creates the service response.
	 * 
	 * @param service
	 *            The service.
	 * @param responseIndex
	 *            Index of the response.
	 * @return Service object.
	 */
	@Override
	protected ServiceResponse createServiceResponse(ExchangeService service,
			int responseIndex) {
		return new ServiceResponse();
	}

	/**
	 * Gets the name of the XML element.
	 * 
	 * @return Xml element name
	 */
	@Override
	protected String getXmlElementName() {
		return XmlElementNames.DeleteFolder;
	}

	/**
	 *Gets the name of the response XML element.
	 * 
	 * @return Xml element name
	 */
	@Override
	protected String getResponseXmlElementName() {
		return XmlElementNames.DeleteFolderResponse;
	}

	/**
	 *Gets the name of the response message XML element.
	 * 
	 * @return Xml element name
	 */
	@Override
	protected String getResponseMessageXmlElementName() {
		return XmlElementNames.DeleteFolderResponseMessage;
	}

	/**
	 * Writes XML elements.
	 * 
	 * @param writer
	 *            The writer
	 */
	@Override
	protected void writeElementsToXml(EwsServiceXmlWriter writer) {
		try {
			this.getFolderIds().writeToXml(writer, XmlNamespace.Messages,
					XmlElementNames.FolderIds);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	 * Gets the folder ids.
	 * 
	 * @return The folder ids.
	 */
	protected FolderIdWrapperList getFolderIds() {
		return this.folderIds;
	}

}
