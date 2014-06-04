/**************************************************************************
 * copyright file="RelativeDayOfMonthTransition.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the RelativeDayOfMonthTransition.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import javax.xml.stream.XMLStreamException;

/**
 * Represents a time zone period transition that occurs on a relative day of a
 * specific month.
 */
class RelativeDayOfMonthTransition extends AbsoluteMonthTransition {

	/** The day of the week. */
	private DayOfTheWeek dayOfTheWeek;
	
	/** The week index. */
	private int weekIndex;

	/**
	 * Gets the XML element name associated with the transition.
	 * 
	 * @return The XML element name associated with the transition.
	 */
	@Override
	protected String getXmlElementName() {
		return XmlElementNames.RecurringDayTransition;
	}
	
	/**
	 * Tries to read element from XML.
	 * 
	 * @param reader
	 *            accepts EwsServiceXmlReader
	 * @return True if element was read.
	 * @throws Exception
	 *             throws Exception
	 */
	@Override
    protected boolean tryReadElementFromXml(EwsServiceXmlReader reader)
		throws Exception {
		if (super.tryReadElementFromXml(reader)) {
			return true;
		} else {
			if (reader.getLocalName().equals(XmlElementNames.DayOfWeek)) {
				this.dayOfTheWeek = reader.readElementValue(DayOfTheWeek.class);
				return true;
			} else if (reader.getLocalName().equals(XmlElementNames.Occurrence)) {
				this.weekIndex = reader.readElementValue(Integer.class);
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * Writes elements to XML.
	 * 
	 * @param writer
	 *            the writer
	 * @throws ServiceXmlSerializationException
	 *             the service xml serialization exception
	 * @throws javax.xml.stream.XMLStreamException
	 *             the xML stream exception
	 */
	@Override
	protected void writeElementsToXml(EwsServiceXmlWriter writer)
			throws ServiceXmlSerializationException, XMLStreamException {
		super.writeElementsToXml(writer);

		writer.writeElementValue(
				XmlNamespace.Types, 
				XmlElementNames.DayOfWeek,
				this.dayOfTheWeek);

		writer.writeElementValue(
				XmlNamespace.Types,
				XmlElementNames.Occurrence, 
				this.weekIndex);
	}

	/**
	 * Initializes a new instance of the "RelativeDayOfMonthTransition class.
	 * 
	 * @param timeZoneDefinition
	 *            the time zone definition
	 */
	protected RelativeDayOfMonthTransition(
			TimeZoneDefinition timeZoneDefinition) {
		super(timeZoneDefinition);
	}

	/**
	 * Initializes a new instance of the "RelativeDayOfMonthTransition class.
	 * 
	 * @param timeZoneDefinition
	 *            the time zone definition
	 * @param targetPeriod
	 *            the target period
	 */
	protected RelativeDayOfMonthTransition(
			TimeZoneDefinition timeZoneDefinition, 
			TimeZonePeriod targetPeriod) {
		super(timeZoneDefinition, targetPeriod);
	}

	/**
	 * Gets the day of the week when the transition occurs.
	 *
	 * @return the day of the week
	 */
	protected DayOfTheWeek getDayOfTheWeek() {
		return this.dayOfTheWeek;
	}

	/**
	 * Gets the index of the week in the month when the transition occurs.
	 *
	 * @return the week index
	 */
	protected int getWeekIndex() {
		return this.weekIndex;
	}
}
