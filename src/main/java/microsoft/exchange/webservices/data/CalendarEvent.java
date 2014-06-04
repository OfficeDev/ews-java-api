/**************************************************************************
 * copyright file="CalendarEvent.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the CalendarEvent.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.Date;

/**
 *Represents an event in a calendar.
 * 
 */
public final class CalendarEvent extends ComplexProperty {

	/** The start time. */
	private Date startTime;

	/** The end time. */
	private Date endTime;

	/** The free busy status. */
	private LegacyFreeBusyStatus freeBusyStatus;

	/** The details. */
	private CalendarEventDetails details;

	/**
	 * Initializes a new instance of the CalendarEvent class.
	 */
	protected CalendarEvent() {
		super();
	}

	/**
	 * Gets the start date and time of the event.
	 * 
	 * @return the start time
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * Gets the end date and time of the event.
	 * 
	 * @return the end time
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * Gets the free/busy status associated with the event.
	 * 
	 * @return the free busy status
	 */
	public LegacyFreeBusyStatus getFreeBusyStatus() {
		return freeBusyStatus;
	}

	/**
	 * Gets the details of the calendar event. Details is null if the user
	 * requsting them does no have the appropriate rights.
	 * 
	 * @return the details
	 */
	public CalendarEventDetails getDetails() {
		return details;
	}

	/**
	 * Attempts to read the element at the reader's current position.
	 * 
	 * @param reader
	 *            the reader
	 * @return True if the element was read, false otherwise.
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected boolean tryReadElementFromXml(EwsServiceXmlReader reader)
			throws Exception {
		if (reader.getLocalName().equals(XmlElementNames.StartTime)) {
			this.startTime = reader
					.readElementValueAsUnbiasedDateTimeScopedToServiceTimeZone();
			return true;
		} else if (reader.getLocalName().equals(XmlElementNames.EndTime)) {
			this.endTime = reader
					.readElementValueAsUnbiasedDateTimeScopedToServiceTimeZone();
			return true;
		} else if (reader.getLocalName().equals(XmlElementNames.BusyType)) {
			this.freeBusyStatus = reader
					.readElementValue(LegacyFreeBusyStatus.class);
			return true;
		}
		if (reader.getLocalName().equals(XmlElementNames.CalendarEventDetails)) {
			this.details = new CalendarEventDetails();
			this.details.loadFromXml(reader, reader.getLocalName());
			return true;
		} else {
			return false;
		}

	}
}
