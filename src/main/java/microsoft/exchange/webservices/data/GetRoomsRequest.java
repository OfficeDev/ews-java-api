/**************************************************************************
 * copyright file="GetRoomsRequest.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the GetRoomsRequest.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents a GetRooms request.
 */
final class GetRoomsRequest extends SimpleServiceRequestBase {

	/**
	 * Represents a GetRooms request.
	 * 
	 * @param service
	 *            the service
	 * @throws Exception 
	 */
	protected GetRoomsRequest(ExchangeService service)
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
		return XmlElementNames.GetRoomsRequest;
	}

	/**
	 * Writes XML elements.
	 * 
	 * @param writer
	 *            the writer
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void writeElementsToXml(EwsServiceXmlWriter writer)
			throws Exception {
		this.getRoomList().writeToXml(writer, XmlNamespace.Messages,
				XmlElementNames.RoomList);
	}

	/**
	 * Gets the name of the response XML element.
	 * 
	 * @return XML element name.
	 */
	@Override
	protected String getResponseXmlElementName() {
		return XmlElementNames.GetRoomsResponse;
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
		GetRoomsResponse response = new GetRoomsResponse();
		response.loadFromXml(reader, XmlElementNames.GetRoomsResponse);
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
	 * @return Service response.
	 * @throws Exception
	 *             the exception
	 */
	protected GetRoomsResponse execute() throws Exception {
		GetRoomsResponse serviceResponse = (GetRoomsResponse)this
				.internalExecute();
		serviceResponse.throwIfNecessary();
		return serviceResponse;
	}

	/**
	 * Gets  the room list to retrieve rooms from.
	 * 
	 * @return the room list
	 */
	protected EmailAddress getRoomList() {
		return this.roomList;
	}

	/**
	 * Sets the room list.
	 * 
	 * @param value
	 *            the new room list
	 */
	protected void setRoomList(EmailAddress value) {
		this.roomList = value;
	}

	/** The room list. */
	private EmailAddress roomList;

}
