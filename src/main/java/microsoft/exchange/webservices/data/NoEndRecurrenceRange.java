/**************************************************************************
 * copyright file="NoEndRecurrenceRange.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the NoEndRecurrenceRange.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.Date;

/**
 * Represents recurrence range with no end date.
 * 
 */
final class NoEndRecurrenceRange extends RecurrenceRange {

	/**
	 * Initializes a new instance.
	 */
	public NoEndRecurrenceRange() {
		super();
	}

	/**
	 * Initializes a new instance.
	 * 
	 * @param startDate
	 *            the start date
	 */
	public NoEndRecurrenceRange(Date startDate) {
		super(startDate);
	}

	/**
	 * Gets the name of the XML element.
	 * 
	 * @return The name of the XML element
	 */
	protected String getXmlElementName() {
		return XmlElementNames.NoEndRecurrence;
	}

	/**
	 * Setups the recurrence.
	 * 
	 * @param recurrence
	 *            the new up recurrence
	 * @throws Exception
	 *             the exception
	 */
	protected void setupRecurrence(Recurrence recurrence) throws Exception {
		super.setupRecurrence(recurrence);

		recurrence.neverEnds();
	}

}
