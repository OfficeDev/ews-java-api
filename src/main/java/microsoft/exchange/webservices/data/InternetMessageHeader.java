/**************************************************************************
 * copyright file="InternetMessageHeader.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the InternetMessageHeader.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import javax.xml.stream.XMLStreamException;

/***
 * Defines the EwsXmlReader class.
 */
public final class InternetMessageHeader extends ComplexProperty {

	/** The name. */
	private String name;

	/** The value. */
	private String value;

	/**
	 * Initializes a new instance of the EwsXmlReader class.
	 */
	protected InternetMessageHeader() {
	}

	/**
	 * Reads the attributes from XML.
	 * 
	 * @param reader
	 *            the reader
	 * @throws Exception
	 *             the exception
	 */
	protected void readAttributesFromXml(EwsServiceXmlReader reader)
			throws Exception {
		this.name = reader.readAttributeValue(XmlAttributeNames.HeaderName);
	}

	/**
	 * Reads the text value from XML.
	 * 
	 * @param reader
	 *            the reader
	 * @throws javax.xml.stream.XMLStreamException
	 *             the xML stream exception
	 * @throws ServiceXmlDeserializationException
	 *             the service xml deserialization exception
	 */
	protected void readTextValueFromXml(EwsServiceXmlReader reader)
			throws XMLStreamException, ServiceXmlDeserializationException {
		this.value = reader.readValue();
	}

	/**
	 * Writes the attributes to XML.
	 * 
	 * @param writer
	 *            the writer
	 * @throws ServiceXmlSerializationException
	 *             the service xml serialization exception
	 */
	protected void writeAttributesToXml(EwsServiceXmlWriter writer)
			throws ServiceXmlSerializationException {
		writer.writeAttributeValue(XmlAttributeNames.HeaderName, this.name);
	}

	/**
	 * Writes elements to XML.
	 * 
	 * @param writer
	 *            the writer
	 * @throws ServiceXmlSerializationException
	 *             the service xml serialization exception
	 */
	protected void writeElementsToXml(EwsServiceXmlWriter writer)
			throws ServiceXmlSerializationException {
		writer.writeValue(this.value, this.name);
	}

	/**
	 * Obtains a string representation of the header.
	 * 
	 * @return The string representation of the header.
	 */
	public String toString() {
		return String.format("%s=%s", this.name, this.value);
	}

	/**
	 * The name of the header.
	 * 
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * The value of the header.
	 * 
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 * 
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

}
