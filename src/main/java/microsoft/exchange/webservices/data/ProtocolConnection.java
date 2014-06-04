/**************************************************************************
 * copyright file="ProtocolConnection.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ProtocolConnection.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents the email Protocol connection settings for pop/imap/smtp
 * protocols.
 * 
 */
public final class ProtocolConnection {

	/** The encryption method. */
	private String encryptionMethod;

	/** The hostname. */
	private String hostname;

	/** The port. */
	private int port;

	/**
	 * Initializes a new instance of the <see cref="ProtocolConnection"/> class.
	 */

	protected ProtocolConnection() {
	}

	/**
	 * Read user setting with ProtocolConnection value.
	 * 
	 * @param reader
	 *            EwsServiceXmlReader
	 * @return the protocol connection
	 * @throws Exception
	 *             the exception
	 */
	protected static ProtocolConnection loadFromXml(EwsXmlReader reader)
			throws Exception {
		ProtocolConnection connection = new ProtocolConnection();

		do {
			reader.read();

			if (reader.getNodeType().getNodeType() == XMLNodeType.START_ELEMENT) {
				if (reader.getLocalName().equals(
						XmlElementNames.EncryptionMethod)) {
					connection.setEncryptionMethod(reader
							.readElementValue(String.class));
				} else if (reader.getLocalName().equals(
						XmlElementNames.Hostname)) {
					connection.setHostname(reader
							.readElementValue(String.class));
				} else if (reader.getLocalName().equals(XmlElementNames.Port)) {
					connection.setPort(reader.readElementValue(int.class));
				}
			}
		} while (!reader.isEndElement(XmlNamespace.Autodiscover,
				XmlElementNames.ProtocolConnection));

		return connection;
	}

	/**
	 * Initializes a new instance of the ProtocolConnection class.
	 * 
	 * @param encryptionMethod
	 *            The encryption method.
	 * @param hostname
	 *            The hostname.
	 * @param port
	 *            The port number to use for the portocol.
	 */
	protected ProtocolConnection(String encryptionMethod, String hostname,
			int port) {
		this.encryptionMethod = encryptionMethod;
		this.hostname = hostname;
		this.port = port;
	}

	/**
	 * Gets the encryption method.
	 * 
	 * @return The encryption method.
	 */
	public String getEncryptionMethod() {
		return this.encryptionMethod;
	}

	/**
	 * Sets the encryption method.
	 * 
	 * @param value
	 *            the new encryption method
	 */
	public void setEncryptionMethod(String value) {
		this.encryptionMethod = value;
	}

	/**
	 * Gets the hostname.
	 * 
	 * @return The hostname.
	 */
	public String getHostname() {
		return this.hostname;

	}

	/**
	 * Sets the hostname.
	 * 
	 * @param value
	 *            the new hostname
	 */
	public void setHostname(String value) {
		this.hostname = value;
	}

	/**
	 * Gets the port number.
	 * 
	 * @return The port number.
	 */
	public int getPort() {
		return this.port;
	}

	/**
	 * Sets the port.
	 * 
	 * @param value
	 *            the new port
	 */
	public void setPort(int value) {
		this.port = value;
	}
}
