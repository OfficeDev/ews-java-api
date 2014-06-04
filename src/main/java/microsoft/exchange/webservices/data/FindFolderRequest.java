/**************************************************************************
 * copyright file="FindFolderRequest.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the FindFolderRequest.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents a FindFolder request.
 * 
 */
final class FindFolderRequest extends FindRequest<FindFolderResponse> {

	/**
	 * Initializes a new instance of the FindFolderRequest class.
	 * 
	 * @param exchangeService
	 *            The Service
	 * @param errorHandlingMode
	 *            Indicates how errors should be handled.
	 * @throws Exception 
	 */
	protected FindFolderRequest(ExchangeService exchangeService,
			ServiceErrorHandling errorHandlingMode)
			throws Exception {
		super(exchangeService, errorHandlingMode);
	}

	/**
	 * Creates the service response.
	 * 
	 * @param service
	 *            The service
	 * @param responseIndex
	 *            Index of the response. Service response.
	 * @return the find folder response
	 */
	@Override
	protected FindFolderResponse createServiceResponse(ExchangeService service,
			int responseIndex) {
		return new FindFolderResponse(this.getView().getPropertySetOrDefault());
	}

	/**
	 * Gets the name of the XML element.
	 * 
	 * @return XML element name.
	 */
	@Override
	protected String getXmlElementName() {
		return XmlElementNames.FindFolder;
	}

	/**
	 * Gets the name of the response XML element.
	 * 
	 * @return XML element name.
	 */
	@Override
	protected String getResponseXmlElementName() {
		return XmlElementNames.FindFolderResponse;
	}

	/**
	 * Gets the name of the response message XML element.
	 * 
	 * @return XML element name.
	 */
	@Override
	protected String getResponseMessageXmlElementName() {
		return XmlElementNames.FindFolderResponseMessage;
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
