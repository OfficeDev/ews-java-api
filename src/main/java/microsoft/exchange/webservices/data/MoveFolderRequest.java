/**************************************************************************
 * copyright file="MoveFolderRequest.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the MoveFolderRequest.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents a MoveFolder request.
 * 
 * 
 */
class MoveFolderRequest extends MoveCopyFolderRequest<MoveCopyFolderResponse> {

	/**
	 * Initializes a new instance of the MoveFolderRequest class.
	 * 
	 * @param service
	 *            The service.
	 * @param errorHandlingMode
	 *            Indicates how errors should be handled.
	 * @throws Exception 
	 */
	protected MoveFolderRequest(ExchangeService service,
			ServiceErrorHandling errorHandlingMode)
			throws Exception {
		super(service, errorHandlingMode);
	}

	/**
	 *Creates the service response.
	 * 
	 * @param service
	 *            The service.
	 *@param responseIndex
	 *            Index of the response.
	 *@return Service response.
	 */
	@Override
	protected MoveCopyFolderResponse createServiceResponse(
			ExchangeService service, int responseIndex) {
		return new MoveCopyFolderResponse();
	}

	/**
	 * Gets the name of the XML element.
	 * 
	 * @return Xml element name.
	 */
	@Override
	protected String getXmlElementName() {
		return XmlElementNames.MoveFolder;
	}

	/**
	 * Gets the name of the response XML element.
	 * 
	 * @return Xml element name.
	 */
	@Override
	protected String getResponseXmlElementName() {
		return XmlElementNames.MoveFolderResponse;
	}

	/**
	 * Gets the name of the response message XML element.
	 * 
	 * @return Xml element name.
	 */
	@Override
	protected String getResponseMessageXmlElementName() {
		return XmlElementNames.MoveFolderResponseMessage;
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

}
