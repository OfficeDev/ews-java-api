/**************************************************************************
 * copyright file="UniqueBody.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the UniqueBody.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import javax.xml.stream.XMLStreamException;

/**
 * Represents the body part of an item that is unique to the conversation the
 * item is part of.
 * 
 */
public final class UniqueBody extends ComplexProperty {

	/** The body type. */
	private BodyType bodyType;

	/** The text. */
	private String text;

	/**
	 * Initializes a new instance.
	 */
	protected UniqueBody() {
	}

	/**
	 * Defines an implicit conversion of UniqueBody into a string.
	 * 
	 * @param messageBody
	 *            the message body
	 * @return string containing the text of the UniqueBody
	 * @throws Exception
	 *             the exception
	 */
	public static String getStringFromUniqueBody(UniqueBody messageBody)
			throws Exception {
		EwsUtilities.validateParam(messageBody, "messageBody");
		return messageBody.text;
	}

	/**
	 * Reads attributes from XML.
	 * 
	 * @param reader
	 *            the reader
	 * @throws Exception
	 *             the exception
	 */
	protected void readAttributesFromXml(EwsServiceXmlReader reader)
			throws Exception {
		this.bodyType = reader.readAttributeValue(BodyType.class,
				XmlAttributeNames.BodyType);
	}

	/**
	 * Reads attributes from XML.
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
		this.text = reader.readValue();
	}

	/**
	 * Reads attributes from XML.
	 * 
	 * @param writer
	 *            the writer
	 * @throws ServiceXmlSerializationException
	 *             the service xml serialization exception
	 */
	protected void writeAttributesToXml(EwsServiceXmlWriter writer)
			throws ServiceXmlSerializationException {
		writer.writeAttributeValue(XmlAttributeNames.BodyType, this.bodyType);
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
		if (!(this.text == null || this.text.isEmpty())) {
			writer.writeValue(this.text, XmlElementNames.UniqueBody);
		}
	}

	/**
	 * Gets the type of the unique body's text.
	 * 
	 * @return bodytype
	 */
	public BodyType getBodyType() {
		return this.bodyType;
	}

	/**
	 * Gets the text of the unique body.
	 * 
	 * @return text
	 */
	public String getText() {
		return this.text;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return (this.getText() == null) ? "" : this.getText();
	}

}