/**************************************************************************
 * copyright file="OofReply.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the OofReply.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import javax.xml.stream.XMLStreamException;

/***
 * Represents an Out of Office response.
 */
public final class OofReply {

	/** The culture. */
	private String culture = "en-US";

	/** The message. */
	private String message;

	/**
	 * Writes an empty OofReply to XML.
	 * 
	 * @param writer
	 *            the writer
	 * @param xmlElementName
	 *            the xml element name
	 * @throws javax.xml.stream.XMLStreamException
	 *             the xML stream exception
	 */
	protected static void writeEmptyReplyToXml(EwsServiceXmlWriter writer,
			String xmlElementName) throws XMLStreamException {
		writer.writeStartElement(XmlNamespace.Types, xmlElementName);
		writer.writeEndElement(); // xmlElementName
	}

	/**
	 * Initializes a new instance of the class.
	 */
	public OofReply() {
	}

	/**
	 * Initializes a new instance of the class.
	 * 
	 * @param message
	 *            the message
	 */
	public OofReply(String message) {
		this.message = message;
	}

	/**
	 * Initializes a new instance of the class.
	 * 
	 * @param message
	 *            the message
	 * @return the oof reply from string
	 */
	public static OofReply getOofReplyFromString(String message) {
		return new OofReply(message);
	}

	/**
	 * Gets the string from oof reply.
	 * 
	 * @param oofReply
	 *            the oof reply
	 * @return the string from oof reply
	 * @throws Exception
	 *             the exception
	 */
	public static String getStringFromOofReply(OofReply oofReply)
			throws Exception {
		EwsUtilities.validateParam(oofReply, "oofReply");
		return oofReply.message;
	}

	/**
	 * Loads from XML.
	 * 
	 * @param reader
	 *            the reader
	 * @param xmlElementName
	 *            the xml element name
	 * @throws Exception
	 *             the exception
	 */
	protected void loadFromXml(EwsServiceXmlReader reader, 
			String xmlElementName)
			throws Exception {
		reader.ensureCurrentNodeIsStartElement(XmlNamespace.Types,
				xmlElementName);

		if (reader.hasAttributes()) {
			this.setCulture(reader.readAttributeValue("xml:lang"));
		}

		this.message = reader.readElementValue(XmlNamespace.Types,
				XmlElementNames.Message);

		reader.readEndElement(XmlNamespace.Types, xmlElementName);
	}

	/**
	 * Writes to XML.
	 * 
	 * @param writer
	 *            the writer
	 * @param xmlElementName
	 *            the xml element name
	 * @throws javax.xml.stream.XMLStreamException
	 *             the xML stream exception
	 * @throws ServiceXmlSerializationException
	 *             the service xml serialization exception
	 */
	protected void writeToXml(EwsServiceXmlWriter writer, String xmlElementName)
			throws XMLStreamException, ServiceXmlSerializationException {
		writer.writeStartElement(XmlNamespace.Types, xmlElementName);

		if (this.culture != null) {
			writer.writeAttributeValue("xml", "lang", this.culture);
		}

		writer.writeElementValue(XmlNamespace.Types, XmlElementNames.Message,
				this.message);

		writer.writeEndElement(); // xmlElementName
	}

	/***
	 * Obtains a string representation of the reply.
	 * 
	 * @return A string containing the reply message.
	 */
	public String toString() {
		return this.message;
	}

	/**
	 * Gets the culture of the reply.
	 * 
	 * @return the culture
	 */
	public String getCulture() {
		return this.culture;

	}

	/**
	 * Sets the culture.
	 * 
	 * @param culture
	 *            the new culture
	 */
	public void setCulture(String culture) {
		this.culture = culture;
	}

	/**
	 * Gets  the the reply message.
	 * 
	 * @return the message
	 */
	public String getMessage() {
		return this.message;
	}

	/**
	 * Sets the message.
	 * 
	 * @param message
	 *            the new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
