/**************************************************************************
 * copyright file="AbsoluteMonthTransition.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the AbsoluteMonthTransition.java.
 **************************************************************************/

package microsoft.exchange.webservices.data;

import javax.xml.stream.XMLStreamException;

/**
 * Represents the base class for all recurring time zone period transitions.
 * 
 */
abstract class AbsoluteMonthTransition extends TimeZoneTransition {

	/** The time offset. */
	private TimeSpan timeOffset;

	/** The month. */
	private int month;

	/**
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
		if (super.tryReadElementFromXml(reader)) {
			return true;
		} else {
			if (reader.getLocalName().equals(XmlElementNames.TimeOffset)) {
				this.timeOffset = EwsUtilities.getXSDurationToTimeSpan(reader
						.readElementValue());
				return true;
			} else if (reader.getLocalName().equals(XmlElementNames.Month)) {
				this.month = reader.readElementValue(Integer.class);

				EwsUtilities.EwsAssert(this.month > 0 && this.month <= 12,
						"AbsoluteMonthTransition.TryReadElementFromXml",
						"month is not in the valid 1 - 12 range.");

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

		writer.writeElementValue(XmlNamespace.Types,
				XmlElementNames.TimeOffset, EwsUtilities
						.getTimeSpanToXSDuration(this.timeOffset));

		writer.writeElementValue(XmlNamespace.Types, XmlElementNames.Month,
				this.month);
	}

	/**
	 * Initializes a new instance of the AbsoluteMonthTransition class.
	 * 
	 * @param timeZoneDefinition
	 *            the time zone definition
	 */
	protected AbsoluteMonthTransition(TimeZoneDefinition timeZoneDefinition) {
		super(timeZoneDefinition);
	}

	/**
	 * Initializes a new instance of the AbsoluteMonthTransition class.
	 * 
	 * @param timeZoneDefinition
	 *            the time zone definition
	 * @param targetPeriod
	 *            the target period
	 */
	protected AbsoluteMonthTransition(TimeZoneDefinition timeZoneDefinition,
			TimeZonePeriod targetPeriod) {
		super(timeZoneDefinition, targetPeriod);
	}

	/**
	 * Gets the time offset from midnight when the transition occurs.
	 * 
	 * @return the time offset
	 */
	protected TimeSpan getTimeOffset() {
		return this.timeOffset;
	}

	/**
	 * Gets the month when the transition occurs.
	 * 
	 * @return the month
	 */
	protected int getMonth() {
		return this.month;
	}

}
