/**************************************************************************
 * copyright file="AbsoluteDayOfMonthTransition.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the AbsoluteDayOfMonthTransition.java.
 **************************************************************************/

package microsoft.exchange.webservices.data;

import javax.xml.stream.XMLStreamException;

/**
 * Represents a time zone period transition that occurs on a specific day of a
 * specific month.
 */
class AbsoluteDayOfMonthTransition extends AbsoluteMonthTransition {

	/** The day of month. */
	private int dayOfMonth;

	/**
	 * Gets the XML element name associated with the transition.
	 * 
	 * @return The XML element name associated with the transition.
	 */
	@Override
	protected String getXmlElementName() {
		return XmlElementNames.RecurringDateTransition;
	}

	/**
	 * Tries to read element from XML.
	 * 
	 * @param reader
	 *            returns True if element was read.
	 * @return true
	 * @throws Exception
	 *             throws Exception
	 */
	@Override
	protected boolean tryReadElementFromXml(EwsServiceXmlReader reader)
			throws Exception {
		if (super.tryReadElementFromXml(reader)) {
			return true;
		} else {
			if (reader.getLocalName().equals(XmlElementNames.Day)) {
				this.dayOfMonth = reader.readElementValue(Integer.class);

				EwsUtilities.EwsAssert(this.dayOfMonth > 0
						&& this.dayOfMonth <= 31,
						"AbsoluteDayOfMonthTransition.TryReadElementFromXml",
						"dayOfMonth is not in the valid 1 - 31 range.");

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

		writer.writeElementValue(XmlNamespace.Types, XmlElementNames.Day,
				this.dayOfMonth);
	}

	/**
	 * Initializes a new instance of the AbsoluteDayOfMonthTransition class.
	 * 
	 * @param timeZoneDefinition
	 *            the time zone definition
	 */
	protected AbsoluteDayOfMonthTransition(TimeZoneDefinition timeZoneDefinition) {
		super(timeZoneDefinition);
	}

	/**
	 * Initializes a new instance of the AbsoluteDayOfMonthTransition class.
	 * 
	 * @param timeZoneDefinition
	 *            the time zone definition
	 * @param targetPeriod
	 *            the target period
	 */

	protected AbsoluteDayOfMonthTransition(
			TimeZoneDefinition timeZoneDefinition, TimeZonePeriod targetPeriod) {
		super(timeZoneDefinition, targetPeriod);
	}

	/**
	 * Gets the day of then month when this transition occurs.
	 * 
	 * @return the day of month
	 */
	protected int getDayOfMonth() {
		return this.dayOfMonth;
	}
}
