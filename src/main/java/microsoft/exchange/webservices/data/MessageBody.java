/**************************************************************************
 * copyright file="MessageBody.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the MessageBody.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import javax.xml.stream.XMLStreamException;

/***
 * Represents the body of a message.
 * 
 */
public final class MessageBody extends ComplexProperty {

	/** The body type. */
	private BodyType bodyType;

	/** The text. */
	private String text;

	/**
	 * * Initializes a new instance.
	 */
	public MessageBody() {

	}

	/**
	 * * Initializes a new instance.
	 * 
	 * @param bodyType
	 *            The type of the message body's text.
	 * @param text
	 *            The text of the message body.
	 */
	public MessageBody(BodyType bodyType, String text) {
		this();
		this.bodyType = bodyType;
		this.text = text;
	}

	/**
	 * * Initializes a new instance.
	 * 
	 * @param text
	 *            The text of the message body, assumed to be HTML.
	 */
	public MessageBody(String text) {
		this(BodyType.HTML, text);
	}

	/***
	 * Defines an implicit conversation between a string and MessageBody.
	 * 
	 * @param textBody
	 *            The string to convert to MessageBody, assumed to be HTML.
	 * @return A MessageBody initialized with the specified string.
	 */
	public static MessageBody getMessageBodyFromText(String textBody) {
		return new MessageBody(BodyType.HTML, textBody);
	}

	/**
	 * * Defines an implicit conversion of MessageBody into a string.
	 * 
	 * @param messageBody
	 *            The MessageBody to convert to a string.
	 * @return A string containing the text of the MessageBody.
	 * @throws Exception
	 *             the exception
	 */
	public static String getStringFromMessageBody(MessageBody messageBody)
			throws Exception {
		EwsUtilities.validateParam(messageBody, "messageBody");
		return messageBody.text;
	}

	/**
	 * * Reads attributes from XML.
	 * 
	 * @param reader
	 *            The reader.
	 * @throws Exception
	 *             the exception
	 */
	protected void readAttributesFromXml(EwsServiceXmlReader reader)
			throws Exception {
		this.bodyType = reader.readAttributeValue(BodyType.class,
				XmlAttributeNames.BodyType);
	}

	/**
	 * * Reads text value from XML.
	 * 
	 * @param reader
	 *            The reader.
	 * @throws javax.xml.stream.XMLStreamException
	 *             the xML stream exception
	 * @throws ServiceXmlDeserializationException
	 *             the service xml deserialization exception
	 */
	@Override
	protected void readTextValueFromXml(EwsServiceXmlReader reader)
			throws XMLStreamException, ServiceXmlDeserializationException {
		this.text = reader.readValue();
	}

	/**
	 * * Writes attributes to XML.
	 * 
	 * @param writer
	 *            The writer.
	 * @throws ServiceXmlSerializationException
	 *             the service xml serialization exception
	 */
	@Override
	protected void writeAttributesToXml(EwsServiceXmlWriter writer)
			throws ServiceXmlSerializationException {
		writer.writeAttributeValue(XmlAttributeNames.BodyType, this
				.getBodyType());
	}

	/**
	 * * Writes elements to XML.
	 * 
	 * @param writer
	 *            The writer.
	 * @throws ServiceXmlSerializationException
	 *             the service xml serialization exception
	 */
	@Override
	protected void writeElementsToXml(EwsServiceXmlWriter writer)
			throws ServiceXmlSerializationException {
		if (null != this.text && !this.text.isEmpty()) {
			writer.writeValue(this.getText(), XmlElementNames.Body);
		}
	}

	/***
	 * Gets the type of the message body's text.
	 * 
	 * @return BodyType enum
	 */
	public BodyType getBodyType() {
		return this.bodyType;
	}

	/***
	 * Sets the type of the message body's text.
	 * 
	 * @param bodyType
	 *            BodyType enum
	 */
	public void setBodyType(BodyType bodyType) {
		if (this.canSetFieldValue(this.bodyType, bodyType)) {
			this.bodyType = bodyType;
			this.changed();
		}
	}

	/***
	 * Gets the text of the message body.
	 * 
	 * @return message body text
	 */
	private String getText() {
		return this.text;
	}

	/***
	 * Sets the text of the message body.
	 * 
	 * @param text
	 *            message body text
	 */
	public void setText(String text) {
		if (this.canSetFieldValue(this.text, text)) {
			this.text = text;
			this.changed();
		}
	}

	/**
	 * Returns a String that represents the current Object.
	 * 
	 * @return the string
	 */
	@Override
	public String toString() {
		return (this.text == null) ? "" : this.text;
	}
}
