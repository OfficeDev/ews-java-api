/**************************************************************************
 * copyright file="Month.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the Month.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Defines months of the year.
 */
public enum Month {

	// January.
	/** The January. */
	January(1),

	// February.
	/** The February. */
	February(2),

	// March.
	/** The March. */
	March(3),

	// April.
	/** The April. */
	April(4),

	// May.
	/** The May. */
	May(5),

	// June.
	/** The June. */
	June(6),

	// July.
	/** The July. */
	July(7),

	// August.
	/** The August. */
	August(8),

	// September.
	/** The September. */
	September(9),

	// October.
	/** The October. */
	October(10),

	// November.
	/** The November. */
	November(11),

	// December.
	/** The December. */
	December(12);

	/** The month. */
	@SuppressWarnings("unused")
	private final int month;

	/**
	 * Instantiates a new month.
	 * 
	 * @param month
	 *            the month
	 */
	Month(int month) {
		this.month = month;
	}
}
