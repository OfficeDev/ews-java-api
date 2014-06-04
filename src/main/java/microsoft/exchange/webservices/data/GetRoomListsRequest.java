/**************************************************************************
 * copyright file="GetRoomListsRequest.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the GetRoomListsRequest.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents a GetRoomList request.
 */
final class GetRoomListsRequest extends SimpleServiceRequestBase {

	/**
	 * Initializes a new instance of the class.
	 * 
	 * @param service
	 *            the service
	 * @throws Exception 
	 */
	protected GetRoomListsRequest(ExchangeService service)
			throws Exception {
		super(service);
	}

	/**
	 * Gets the name of the XML element.
	 * 
	 * @return XML element name
	 */
	@Override
	protected String getXmlElementName() {
		return XmlElementNames.GetRoomListsRequest;
	}

	/**
	 * Writes XML elements.
	 * 
	 * @param writer
	 *            the writer
	 */
	@Override
	protected void writeElementsToXml(EwsServiceXmlWriter writer) {
		// Don't have parameter in request
	}

	/**
	 * Gets the name of the response XML element.
	 * 
	 * @return XML element name
	 */
	@Override
	protected String getResponseXmlElementName() {
		return XmlElementNames.GetRoomListsResponse;
	}

	/**
	 * Parses the response.
	 * 
	 * @param reader
	 *            the reader
	 * @return Response object.
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected Object parseResponse(EwsServiceXmlReader reader)
		throws Exception {
		GetRoomListsResponse response = new GetRoomListsResponse();
		response.loadFromXml(reader, XmlElementNames.GetRoomListsResponse);
		return response;
	}

	/**
	 * Gets the request version.
	 * 
	 * @return Earliest Exchange version in which this request is supported.
	 */
	@Override
	protected ExchangeVersion getMinimumRequiredServerVersion() {
		return ExchangeVersion.Exchange2010;
	}

	/**
	 * Executes this request.
	 * 
	 * @return Service response
	 * @throws Exception
	 *             the exception
	 */
	protected GetRoomListsResponse execute() throws Exception {
		GetRoomListsResponse serviceResponse = (GetRoomListsResponse)this
				.internalExecute();
		serviceResponse.throwIfNecessary();
		return serviceResponse;
	}
}
