/**************************************************************************
 * copyright file="ProtocolConnectionCollection.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ProtocolConnectionCollection.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.ArrayList;

/**
 * Represents a user setting that is a collection of protocol connection.
 * 
 */
public final class ProtocolConnectionCollection {

	/** The connections. */
	private ArrayList<ProtocolConnection> connections;

	/**
	 * Initializes a new instance of the <see
	 * cref="ProtocolConnectionCollection"/> class.
	 */
	ProtocolConnectionCollection() {
		this.connections = new ArrayList<ProtocolConnection>();
	}

	/**
	 * Read user setting with ProtocolConnectionCollection value.
	 * 
	 * @param reader
	 *            EwsServiceXmlReader
	 * @return the protocol connection collection
	 * @throws Exception
	 *             the exception
	 */
	static ProtocolConnectionCollection LoadFromXml(EwsXmlReader reader)
			throws Exception {
		ProtocolConnectionCollection value = new ProtocolConnectionCollection();
		ProtocolConnection connection = null;

		do {
			reader.read();

			if (reader.getNodeType().getNodeType() == XMLNodeType.START_ELEMENT) {
				if (reader.getLocalName().equals(
						XmlElementNames.ProtocolConnection)) {
					connection = ProtocolConnection.loadFromXml(reader);
					if (connection != null) {
						value.getConnections().add(connection);
					}
				}
			}
		} while (!reader.isEndElement(XmlNamespace.Autodiscover,
				XmlElementNames.ProtocolConnections));

		return value;
	}

	/**
	 * Gets the Connections.
	 * 
	 * @return the connections
	 */
	public ArrayList<ProtocolConnection> getConnections() {
		return this.connections;
	}

	/**
	 * Sets the connections.
	 * 
	 * @param value
	 *            the new connections
	 */
	void setConnections(ArrayList<ProtocolConnection> value) {
		this.connections = value;
	}
}
