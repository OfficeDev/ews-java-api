/**************************************************************************
 * copyright file="CopyItemRequest.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the CopyItemRequest.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/***
 * Represents a CopyItem request.
 */
public class CopyItemRequest extends MoveCopyItemRequest<MoveCopyItemResponse> {

	/**
	 * * Initializes a new instance of the class.
	 * 
	 * @param service
	 *            the service
	 * @param errorHandlingMode
	 *            the error handling mode
	 * @throws Exception 
	 */
	protected CopyItemRequest(ExchangeService service,
			ServiceErrorHandling errorHandlingMode)
			throws Exception {
		super(service, errorHandlingMode);
	}

	/**
	 * * Creates the service response.
	 * 
	 * @param service
	 *            the service
	 * @param responseIndex
	 *            the response index
	 * @return Service response.
	 */
	@Override
	protected MoveCopyItemResponse createServiceResponse(
			ExchangeService service, int responseIndex) {
		return new MoveCopyItemResponse();
	}

	/***
	 * Gets the name of the XML element.
	 * 
	 * @return XML element name.
	 */
	@Override
	protected String getXmlElementName() {
		return XmlElementNames.CopyItem;
	}

	/***
	 * Gets the name of the response XML element.
	 * 
	 * @return XML element name.
	 */
	@Override
	protected String getResponseXmlElementName() {
		return XmlElementNames.CopyItemResponse;
	}

	/***
	 * Gets the name of the response message XML element.
	 * 
	 * @return XML element name.
	 */
	protected String getResponseMessageXmlElementName() {
		return XmlElementNames.CopyItemResponseMessage;
	}

	/***
	 * Gets the request version.
	 * 
	 * @return Earliest Exchange version in which this request is supported.
	 */
	@Override
	protected ExchangeVersion getMinimumRequiredServerVersion() {
		return ExchangeVersion.Exchange2007_SP1;
	}
}
