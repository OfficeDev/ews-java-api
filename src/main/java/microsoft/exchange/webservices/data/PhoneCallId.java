/**************************************************************************
 * copyright file="PhoneCallId.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the PhoneCallId.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents the Id of a phone call.
 * 
 */
final class PhoneCallId extends ComplexProperty {

	/** The id. */
	private String id;

	/**
	 * Initializes a new instance of the PhoneCallId class.
	 */
	protected PhoneCallId() {
	}

	/**
	 * Initializes a new instance of the PhoneCallId class.
	 * 
	 * @param id
	 *            the id
	 */
	protected PhoneCallId(String id) {
		this.id = id;
	}

	/**
	 * Reads attributes from XML.
	 * 
	 * @param reader
	 *            the reader
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void readAttributesFromXml(EwsServiceXmlReader reader)
			throws Exception {
		this.id = reader.readAttributeValue(XmlAttributeNames.Id);
	}

	/**
	 * Writes attributes to XML.
	 * 
	 * @param writer
	 *            the writer
	 * @throws ServiceXmlSerializationException
	 *             the service xml serialization exception
	 */
	@Override
	protected void writeAttributesToXml(EwsServiceXmlWriter writer)
			throws ServiceXmlSerializationException {
		writer.writeAttributeValue(XmlAttributeNames.Id, this.id);
	}

	/**
	 * Writes to XML.
	 * 
	 * @param writer
	 *            the writer
	 * @throws Exception
	 *             the exception
	 */
	protected void writeToXml(EwsServiceXmlWriter writer) throws Exception {
		this.writeToXml(writer, XmlElementNames.PhoneCallId);
	}

	/**
	 * Gets the Id of the phone call.
	 * 
	 * @return the id
	 */
	protected String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id
	 *            the new id
	 */
	protected void setId(String id) {
		this.id = id;
	}

}
