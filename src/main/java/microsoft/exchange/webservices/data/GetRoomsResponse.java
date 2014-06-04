/**************************************************************************
 * copyright file="GetRoomsResponse.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the GetRoomsResponse.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.ArrayList;
import java.util.Collection;

/***
 * Represents the response to a GetRooms operation.
 */
final class GetRoomsResponse extends ServiceResponse {

	/** The rooms. */
	private Collection<EmailAddress> rooms = new ArrayList<EmailAddress>();

	/**
	 * * Initializes a new instance of the class.
	 */
	protected GetRoomsResponse() {
		super();
	}

	/**
	 * * Gets collection for all rooms returned.
	 * 
	 * @return the rooms
	 */
	public Collection<EmailAddress> getRooms() {
		return this.rooms;
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
		this.rooms.clear();
		super.readElementsFromXml(reader);

		reader.readStartElement(XmlNamespace.Messages, XmlElementNames.Rooms);

		if (!reader.isEmptyElement()) {
			// Because we don't have an element for count of returned object,
			// we have to test the element to determine if it is StartElement of
			// return object or EndElement
			reader.read();
			while (reader.isStartElement(XmlNamespace.Types,
					XmlElementNames.Room)) {
				reader.read(); // skip the start <Room>

				EmailAddress emailAddress = new EmailAddress();
				emailAddress.loadFromXml(reader, XmlElementNames.RoomId);
				this.rooms.add(emailAddress);

				reader.readEndElement(XmlNamespace.Types, XmlElementNames.Room);
				reader.read();
			}

			reader.ensureCurrentNodeIsEndElement(XmlNamespace.Messages,
					XmlElementNames.Rooms);
		} else {
			reader.read();
		}
	}
}
