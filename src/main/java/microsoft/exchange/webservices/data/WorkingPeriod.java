/**************************************************************************
 * copyright file="WorkingPeriod.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the WorkingPeriod.java.
 **************************************************************************/

package microsoft.exchange.webservices.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a working period.
 * 
 */
final class WorkingPeriod extends ComplexProperty {

	/** The days of week. */
	private List<DayOfTheWeek> daysOfWeek = new ArrayList<DayOfTheWeek>();

	/** The start time. */
	private long startTime;

	/** The end time. */
	private long endTime;

	/**
	 * Initializes a new instance of the WorkingPeriod class.
	 */
	protected WorkingPeriod() {
		super();
	}

	/**
	 * Tries to read element from XML.
	 * 
	 * @param reader
	 *            the reader
	 * @return true, if successful
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected boolean tryReadElementFromXml(EwsServiceXmlReader reader)
			throws Exception {
		if (reader.getLocalName().equals(XmlElementNames.DayOfWeek)) {
			EwsUtilities.parseEnumValueList(DayOfTheWeek.class,
					this.daysOfWeek, reader.readElementValue(), ' ');
			return true;
		} else if (reader.getLocalName().equals(
				XmlElementNames.StartTimeInMinutes)) {
			this.startTime = reader.readElementValue(Integer.class);
			return true;
		} else if (reader.getLocalName().equals(
				XmlElementNames.EndTimeInMinutes)) {
			this.endTime = reader.readElementValue(Integer.class);
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Gets a collection of work days.
	 * 
	 * @return the days of week
	 */
	protected List<DayOfTheWeek> getDaysOfWeek() {
		return daysOfWeek;
	}

	/**
	 * Gets the start time of the period.
	 * 
	 * @return the start time
	 */
	protected long getStartTime() {
		return startTime;
	}

	/**
	 * Gets the end time of the period.
	 * 
	 * @return the end time
	 */
	protected long getEndTime() {
		return endTime;
	}

}
