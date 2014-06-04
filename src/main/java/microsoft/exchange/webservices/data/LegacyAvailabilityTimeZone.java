/**************************************************************************
 * copyright file="LegacyAvailabilityTimeZone.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the LegacyAvailabilityTimeZone.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.UUID;

/**
 * Represents a time zone as used by GetUserAvailabilityRequest.
 */
final class LegacyAvailabilityTimeZone extends ComplexProperty {
	
	/** The bias. */
	private TimeSpan bias;
	
	/** The standard time. */
	private LegacyAvailabilityTimeZoneTime standardTime;
	
	/** The daylight time. */
	private LegacyAvailabilityTimeZoneTime daylightTime;

	/**
	 * Initializes a new instance of the LegacyAvailabilityTimeZone class.
	 */
	protected LegacyAvailabilityTimeZone() {
		super();
		this.bias = new TimeSpan(0);
		// If there are no adjustment rules (which is the
		//case for UTC), we have to come up with two
        // dummy time changes which both have a delta of
		//zero and happen at two hard coded dates. This
        // simulates a time zone in which there are no time changes.
        this.daylightTime = new LegacyAvailabilityTimeZoneTime();
        this.daylightTime.setDelta(new TimeSpan(0));
        this.daylightTime.setDayOrder(1);
        this.daylightTime.setDayOfTheWeek(DayOfTheWeek.Sunday);
        this.daylightTime.setMonth(10);
        this.daylightTime.setTimeOfDay(new TimeSpan(2*60*60*1000));
        this.daylightTime.setYear(0);

        this.standardTime = new LegacyAvailabilityTimeZoneTime();
        this.standardTime.setDelta(new TimeSpan(0));
        this.standardTime.setDayOrder(1);
        this.standardTime.setDayOfTheWeek(DayOfTheWeek.Sunday);
        this.standardTime.setMonth(3);
        this.standardTime.setTimeOfDay(new TimeSpan(2*60*60*1000));
        this.daylightTime.setYear(0);
	}	

	/**
	 * To time zone info.
	 *
	 * @return the time zone
	 */
	protected TimeZoneDefinition toTimeZoneInfo() {
		
		/*NumberFormat formatter = new DecimalFormat("00");
		String timeZoneId = this.bias.isNegative() ? "GMT+"+formatter.
		format(this.bias.getHours())+":"+
		formatter.format(this.bias.getMinutes()) : 
		"GMT-"+formatter.format(this.bias.getHours())+":"+
		formatter.format(this.bias.getMinutes());
*/		TimeZoneDefinition timeZoneDefinition = new TimeZoneDefinition();
		timeZoneDefinition.id = UUID.randomUUID().toString();
		timeZoneDefinition.name = "Custom time zone";
		return timeZoneDefinition;
	}

	/**
	 * Tries to read element from XML.
	 *
	 * @param reader the reader
	 * @return True if element was read.
	 * @throws Exception the exception
	 */
	@Override
	protected boolean tryReadElementFromXml(EwsServiceXmlReader reader)
			throws Exception {
		if (reader.getLocalName().equals(XmlElementNames.Bias)) {
			this.bias = new TimeSpan((long)
					reader.readElementValue(Integer.class) * 60 * 1000);
			return true;
		} else if (reader.getLocalName().equals(XmlElementNames.StandardTime)) {
			this.standardTime = new LegacyAvailabilityTimeZoneTime();
			this.standardTime.loadFromXml(reader, reader.getLocalName());
			return true;
		} else if (reader.getLocalName().equals(XmlElementNames.DaylightTime)) {
			this.daylightTime = new LegacyAvailabilityTimeZoneTime();
			this.daylightTime.loadFromXml(reader, reader.getLocalName());
			return true;
		} else {

			return false;
		}

	}

	/**
	 * Writes the elements to XML.
	 *
	 * @param writer the writer
	 * @throws Exception the exception
	 */
	@Override
	protected void writeElementsToXml(EwsServiceXmlWriter writer)
			throws Exception {
		writer.writeElementValue(
			XmlNamespace.Types, 
			XmlElementNames.Bias,
			(int)this.bias.getTotalMinutes());

		this.standardTime.writeToXml(writer, XmlElementNames.StandardTime);
		this.daylightTime.writeToXml(writer, XmlElementNames.DaylightTime);
	}
}
