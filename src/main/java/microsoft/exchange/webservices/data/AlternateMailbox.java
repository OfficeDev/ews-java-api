/**************************************************************************
 * copyright file="AlternateMailbox.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the AlternateMailbox.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/***
 * Defines the AlternateMailbox class.
 * 
 */
public final class AlternateMailbox {

	/** The type. */
	private String type;

	/** The display name. */
	private String displayName;

	/** The legacy dn. */
	private String legacyDN;

	/** The server. */
	private String server;

	/**
	 * Initializes a new instance of the AlternateMailbox class.
	 */
	private AlternateMailbox() {
	}

	/**
	 * PLoads AlternateMailbox instance from XML.
	 * 
	 * @param reader
	 *            the reader
	 * @return AlternateMailbox
	 * @throws Exception
	 *             the exception
	 */
	protected static AlternateMailbox loadFromXml(EwsXmlReader reader)
			throws Exception {
		AlternateMailbox altMailbox = new AlternateMailbox();

		do {
			reader.read();

			if (reader.getNodeType().getNodeType() == XMLNodeType.START_ELEMENT) {
				if (reader.getLocalName()
						.equalsIgnoreCase(XmlElementNames.Type)) {
					altMailbox.setType(reader.readElementValue(String.class));
				} else if (reader.getLocalName().equalsIgnoreCase(
						XmlElementNames.DisplayName)) {
					altMailbox.setDisplayName(reader
							.readElementValue(String.class));
				} else if (reader.getLocalName().equalsIgnoreCase(
						XmlElementNames.LegacyDN)) {
					altMailbox.setLegacyDN(reader
							.readElementValue(String.class));
				} else if (reader.getLocalName().equalsIgnoreCase(
						XmlElementNames.Server)) {
					altMailbox.setServer(reader.readElementValue(String.class));
				}
			}
		} while (!reader.isEndElement(XmlNamespace.Autodiscover,
				XmlElementNames.AlternateMailbox));

		return altMailbox;
	}

	/**
	 * Gets the alternate mailbox type.
	 * 
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type.
	 * 
	 * @param type
	 *            the new type
	 */
	protected void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets the alternate mailbox display name.
	 * 
	 * @return the display name
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * Sets the display name.
	 * 
	 * @param displayName
	 *            the new display name
	 */
	protected void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * Gets the alternate mailbox legacy DN.
	 * 
	 * @return the legacy dn
	 */
	public String getLegacyDN() {
		return legacyDN;
	}

	/**
	 * Sets the legacy dn.
	 * 
	 * @param legacyDN
	 *            the new legacy dn
	 */
	protected void setLegacyDN(String legacyDN) {
		this.legacyDN = legacyDN;
	}

	/**
	 * Gets the alernate mailbox server.
	 * 
	 * @return the server
	 */
	public String getServer() {
		return server;
	}

	/**
	 * Sets the server.
	 * 
	 * @param server
	 *            the new server
	 */
	protected void setServer(String server) {
		this.server = server;
	}

}
