/**************************************************************************
 * copyright file="ImAddressEntry.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ImAddressEntry.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import javax.xml.stream.XMLStreamException;

/**
 * Represents an entry of an ImAddressDictionary.
 */
@EditorBrowsable(state = EditorBrowsableState.Never)
public final class ImAddressEntry extends 
DictionaryEntryProperty<ImAddressKey> {

	/** The im address. */
	private String imAddress;

	/**
	 * Initializes a new instance of the "ImAddressEntry" class.
	 */
	protected ImAddressEntry() {
		super(ImAddressKey.class);
	}

	/**
	 * Initializes a new instance of the ="ImAddressEntry" class.
	 * 
	 * @param key
	 *            The key.
	 * @param imAddress
	 *            The im address.
	 */
	protected ImAddressEntry(ImAddressKey key, String imAddress) {
		super(ImAddressKey.class, key);
		this.imAddress = imAddress;
	}

	/**
	 * Gets the Instant Messaging address of the entry.
	 * 
	 * @return imAddress
	 */
	public String getImAddress() {
		return this.imAddress;
	}

	/**
	 * Sets the Instant Messaging address of the entry.
	 * 
	 * @param value
	 *            the new im address
	 */
	public void setImAddress(Object value) {

		this.canSetFieldValue((String)this.imAddress, value);
	}

	/**
	 * Reads the text value from XML.
	 * 
	 * @param reader
	 *            accepts EwsServiceXmlReader
	 * @throws javax.xml.stream.XMLStreamException
	 *             the xML stream exception
	 * @throws ServiceXmlDeserializationException
	 *             the service xml deserialization exception
	 */
	@Override
	protected void readTextValueFromXml(EwsServiceXmlReader reader)
			throws XMLStreamException, ServiceXmlDeserializationException {
		this.imAddress = reader.readValue();
	}

	/**
	 * Writes elements to XML.
	 * 
	 * @param writer
	 *            The writer.
	 * @throws ServiceXmlSerializationException
	 *             the service xml serialization exception
	 */
	protected void writeElementsToXml(EwsServiceXmlWriter writer)
			throws ServiceXmlSerializationException {
		writer.writeValue(this.imAddress, XmlElementNames.ImAddress);
	}
}
