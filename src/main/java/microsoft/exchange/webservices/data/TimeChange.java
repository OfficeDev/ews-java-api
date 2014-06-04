/**************************************************************************
 * copyright file="TimeChange.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the TimeChange.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a change of time for a time zone.
 */
final class TimeChange extends ComplexProperty {

	/** The time zone name. */
	private String timeZoneName;

	/** The offset. */
	private TimeSpan offset;

	/** The time. */
	private Time time;

	/** The absolute date. */
	private Date absoluteDate;

	/** The recurrence. */
	private TimeChangeRecurrence recurrence;

	/**
	 *Initializes a new instance of the "TimeChange" class.
	 */
	public TimeChange() {
		super();
	}

	/**
	 * Initializes a new instance of the <see cref="TimeChange"/> class.
	 * 
	 * @param offset
	 *            The offset since the beginning of the year when the change
	 *            occurs.
	 */
	public TimeChange(TimeSpan offset) {
		this();
		this.offset = offset;
	}

	/**
	 * Initializes a new instance of the "TimeChange" class.
	 * 
	 * @param offset
	 *            The offset since the beginning of the year when the change
	 *            occurs.
	 * @param time
	 *            The time at which the change occurs.
	 */
	public TimeChange(TimeSpan offset, Time time) {
		this(offset);
		this.time = time;
	}

	/**
	 * Gets the name of the associated time zone.
	 * 
	 * @return the timeZoneName
	 */
	public String getTimeZoneName() {
		return timeZoneName;
	}

	/**
	 * Sets the name of the associated time zone.
	 * 
	 * @param timeZoneName
	 *            the timeZoneName to set
	 */
	public void setTimeZoneName(String timeZoneName) {
		this.timeZoneName = timeZoneName;
	}

	/**
	 * Gets the offset since the beginning of the year when the change occurs.
	 * 
	 * @return the offset
	 */
	public TimeSpan getOffset() {
		return offset;
	}

	/**
	 * Sets the offset since the beginning of the year when the change occurs.
	 * 
	 * @param offset
	 *            the offset to set
	 */
	public void setOffset(TimeSpan offset) {
		this.offset = offset;
	}

	/**
	 * Gets the time.
	 * 
	 * @return the time
	 */
	public Time getTime() {
		return time;
	}

	/**
	 * Sets the time.
	 * 
	 * @param time
	 *            the time to set
	 */
	public void setTime(Time time) {
		this.time = time;
	}

	/**
	 * Gets the absolute date.
	 * 
	 * @return the absoluteDate
	 */
	public Date getAbsoluteDate() {
		return absoluteDate;
	}

	/**
	 * Sets the absolute date.
	 * 
	 * @param absoluteDate
	 *            the absoluteDate to set
	 */
	public void setAbsoluteDate(Date absoluteDate) {
		this.absoluteDate = absoluteDate;
		if (absoluteDate != null) {
			this.recurrence = null;
		}
	}

	/**
	 * Gets the recurrence.
	 * 
	 * @return the recurrence
	 */
	public TimeChangeRecurrence getRecurrence() {
		return recurrence;
	}

	/**
	 * Sets the recurrence.
	 * 
	 * @param recurrence
	 *            the recurrence to set
	 */
	public void setRecurrence(TimeChangeRecurrence recurrence) {
		this.recurrence = recurrence;
		if (this.recurrence != null) {
			this.absoluteDate = null;
		}
	}

	/***
	 * Tries to read element from XML.
	 * 
	 * @param reader
	 *            accepts EwsServiceXmlReader
	 * @return True if element was read
	 * @throws Exception
	 *             throws Exception
	 */
	@Override
	protected boolean tryReadElementFromXml(EwsServiceXmlReader reader)
			throws Exception {

		if (reader.getLocalName().equalsIgnoreCase(XmlElementNames.Offset)) {
			this.offset = EwsUtilities.getXSDurationToTimeSpan(reader
					.readElementValue());
			return true;
		} else if (reader.getLocalName().equalsIgnoreCase(
				XmlElementNames.RelativeYearlyRecurrence)) {
			this.recurrence = new TimeChangeRecurrence();
			this.recurrence.loadFromXml(reader, reader.getLocalName());
			return true;
		} else if (reader.getLocalName().equalsIgnoreCase(
				XmlElementNames.AbsoluteDate)) {
			SimpleDateFormat sdfin = new SimpleDateFormat(
					"yyyy-MM-dd'T'HH:mm:ss");
			Date tempDate = sdfin.parse(reader.readElementValue());
			this.absoluteDate = tempDate;
			return true;
		} else if (reader.getLocalName().equalsIgnoreCase(XmlElementNames.Time)) {
			SimpleDateFormat sdfin = new SimpleDateFormat(
					"yyyy-MM-dd'T'HH:mm:ss");
			Date tempDate = sdfin.parse(reader.readElementValue());
			this.time = new Time(tempDate);
			return true;
		} else {
			return false;
		}
	}

	/***
	 * Reads the attributes from XML.
	 * 
	 * @param reader
	 *            accepts EwsServiceXmlReader
	 * @throws Exception
	 *             throws Exception
	 */
	@Override
	protected void readAttributesFromXml(EwsServiceXmlReader reader)
			throws Exception {
		this.timeZoneName = reader
				.readAttributeValue(XmlAttributeNames.TimeZoneName);
	}

	/***
	 * Writes the attributes to XML.
	 * 
	 * @param writer
	 *            accepts EwsServiceXmlWriter
	 */
	@Override
	protected void writeAttributesToXml(EwsServiceXmlWriter writer) {
		try {
			writer.writeAttributeValue(XmlAttributeNames.TimeZoneName,
					this.timeZoneName);
		} catch (ServiceXmlSerializationException e) {
			e.printStackTrace();
		}
	}

	/***
	 * Writes elements to XML.
	 * 
	 * @param writer
	 *            accepts EwsServiceXmlWriter
	 * @throws Exception
	 *             throws Exception
	 */
	@Override
	protected void writeElementsToXml(EwsServiceXmlWriter writer)
			throws Exception {
		if (this.offset != null) {
			writer.writeElementValue(XmlNamespace.Types,
					XmlElementNames.Offset, EwsUtilities
							.getTimeSpanToXSDuration(this.getOffset()));
		}

		if (this.recurrence != null) {
			this.recurrence.writeToXml(writer,
					XmlElementNames.RelativeYearlyRecurrence);
		}

		if (this.absoluteDate != null) {	
			writer.writeElementValue(XmlNamespace.Types,
					XmlElementNames.AbsoluteDate, EwsUtilities
							.dateTimeToXSDate(this.getAbsoluteDate()));
		}

		if (this.time != null) {
			writer.writeElementValue(XmlNamespace.Types, XmlElementNames.Time,
					this.getTime().toXSTime());
		}
	}

}
