/**************************************************************************
 * copyright file="GetRoomListsResponse.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the GetRoomListsResponse.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/***
 * Represents the response to a GetRoomLists operation.
 */
final class GetRoomListsResponse extends ServiceResponse {

	/** The room lists. */
	private EmailAddressCollection roomLists = new EmailAddressCollection();

	/***
	 * Represents the response to a GetRoomLists operation.
	 */
	protected GetRoomListsResponse() {
		super();
	}

	/**
	 * * Gets all room list returned.
	 * 
	 * @return the room lists
	 */
	public EmailAddressCollection getRoomLists() {
		return this.roomLists;
	}

	/**
	 * * Reads response elements from XML.
	 * 
	 * @param reader
	 *            the reader
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void readElementsFromXml(EwsServiceXmlReader reader)
			throws Exception {
		this.roomLists.clear();
		super.readElementsFromXml(reader);

		reader.readStartElement(XmlNamespace.Messages,
				XmlElementNames.RoomLists);

		if (!reader.isEmptyElement()) {
			// Because we don't have an element for count of returned object,
			// we have to test the element to determine if it is return object
			// or EndElement
			reader.read();
			while (reader.isStartElement(XmlNamespace.Types,
					XmlElementNames.Address)) {
				EmailAddress emailAddress = new EmailAddress();
				emailAddress.loadFromXml(reader, XmlElementNames.Address);
				this.roomLists.add(emailAddress);
				reader.read();
			}
			reader.ensureCurrentNodeIsEndElement(XmlNamespace.Messages,
					XmlElementNames.RoomLists);
		} else {
			reader.read();
		}
		return;
	}

}
