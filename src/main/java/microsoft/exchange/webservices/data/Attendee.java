/**************************************************************************
 * copyright file="Attendee.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the Attendee.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.Date;

/***
 * Represents an attendee to a meeting.
 * 
 * 
 */

public final class Attendee extends EmailAddress {

	/** The response type. */
	private MeetingResponseType responseType;

	/** The last response time. */
	private Date lastResponseTime;

	/***
	 * Initializes a new instance of the Attendee class.
	 */
	public Attendee() {
		super();
	}

	/**
	 * * Initializes a new instance of the Attendee class.
	 * 
	 * @param smtpAddress
	 *            the smtp address
	 * @throws Exception
	 *             the exception
	 */
	public Attendee(String smtpAddress) throws Exception {
		super(smtpAddress);
		EwsUtilities.validateParam(smtpAddress, "smtpAddress");
	}

	/**
	 * * Initializes a new instance of the Attendee class.
	 * 
	 * @param name
	 *            the name
	 * @param smtpAddress
	 *            the smtp address
	 */
	public Attendee(String name, String smtpAddress) {
		super(name, smtpAddress);
	}

	/**
	 * * Initializes a new instance of the Attendee class.
	 * 
	 * @param name
	 *            the name
	 * @param smtpAddress
	 *            the smtp address
	 * @param routingType
	 *            the routing type
	 */
	public Attendee(String name, String smtpAddress, String routingType) {
		super(name, smtpAddress, routingType);
	}

	/**
	 * * Initializes a new instance of the Attendee class.
	 * 
	 * @param mailbox
	 *            the mailbox
	 * @throws Exception
	 *             the exception
	 */
	public Attendee(EmailAddress mailbox) throws Exception {
		super(mailbox);
	}

	/**
	 * * Gets the type of response the attendee gave to the meeting invitation
	 * it received.
	 * 
	 * @return the response type
	 */
	public MeetingResponseType getResponseType() {
		return responseType;
	}

	/**
	 * Gets the last response time.
	 * 
	 * @return the last response time
	 */
	public Date getLastResponseTime() {
		return lastResponseTime;
	}

	/**
	 * * Tries to read element from XML.
	 * 
	 * @param reader
	 *            the reader
	 * @return True if element was read.
	 * @throws Exception
	 *             the exception
	 */
	protected boolean tryReadElementFromXml(EwsServiceXmlReader reader)
			throws Exception {
		if (reader.getLocalName().equalsIgnoreCase(XmlElementNames.Mailbox)) {
			this.loadFromXml(reader, reader.getLocalName());
			return true;
		} else if (reader.getLocalName().equalsIgnoreCase(
				XmlElementNames.ResponseType)) {
			this.responseType = reader
					.readElementValue(MeetingResponseType.class);
			return true;
		} else if (reader.getLocalName().equalsIgnoreCase(
				XmlElementNames.LastResponseTime)) {
			this.lastResponseTime = reader.readElementValueAsDateTime();
			return true;
		} else {
			return super.tryReadElementFromXml(reader);
		}
	}

	/**
	 * * Writes the elements to XML.
	 * 
	 * @param writer
	 *            the writer
	 * @throws Exception
	 *             the exception
	 */
	protected void writeElementsToXml(EwsServiceXmlWriter writer)
			throws Exception {
		writer.writeStartElement(this.getNamespace(), XmlElementNames.Mailbox);
		super.writeElementsToXml(writer);
		writer.writeEndElement();
	}
}
