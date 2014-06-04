/**************************************************************************
 * copyright file="CopyFolderRequest.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the CopyFolderRequest.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents a CopyFolder request.
 * 
 * 
 */
class CopyFolderRequest extends MoveCopyFolderRequest<MoveCopyFolderResponse> {

	/**
	 * Initializes a new instance of the CopyFolderRequest class.
	 * 
	 * @param service
	 *            the service
	 * @param errorHandlingMode
	 *            the error handling mode
	 * @throws Exception 
	 */
	protected CopyFolderRequest(ExchangeService service,
			ServiceErrorHandling errorHandlingMode)
			throws Exception {
		super(service, errorHandlingMode);
	}

	/**
	 * Creates the service response.
	 * 
	 * @param service
	 *            The Service
	 * @param responseIndex
	 *            Index of the response.
	 * @return Service response.
	 * 
	 */
	@Override
	protected MoveCopyFolderResponse createServiceResponse(
			ExchangeService service, int responseIndex) {
		return new MoveCopyFolderResponse();
	}

	/**
	 * Gets the name of the XML element.
	 * 
	 * @return XML element name
	 */
	@Override
	protected String getXmlElementName() {
		return XmlElementNames.CopyFolder;
	}

	/**
	 * Gets the name of the response XML element.
	 * 
	 * @return XML element name
	 */
	@Override
	protected String getResponseXmlElementName() {
		return XmlElementNames.CopyFolderResponse;
	}

	/**
	 * Gets the name of the response message XML element.
	 * 
	 * @return XML element name.
	 */
	@Override
	protected String getResponseMessageXmlElementName() {
		return XmlElementNames.CopyFolderResponseMessage;
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
