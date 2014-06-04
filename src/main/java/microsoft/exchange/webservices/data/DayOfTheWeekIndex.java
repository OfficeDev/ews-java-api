/**************************************************************************
 * copyright file="DayOfTheWeekIndex.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the DayOfTheWeekIndex.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Defines the index of a week day within a month.
 */
public enum DayOfTheWeekIndex {

	// The first specific day of the week in the month. For example, the first
	// Tuesday of the month.
	/** The First. */
	First,

	// The second specific day of the week in the month. For example, the second
	// Tuesday of the month.
	/** The Second. */
	Second,

	// The third specific day of the week in the month. For example, the third
	// Tuesday of the month.
	/** The Third. */
	Third,

	// The fourth specific day of the week in the month. For example, the fourth
	// Tuesday of the month.
	/** The Fourth. */
	Fourth,

	// The last specific day of the week in the month. For example, the last
	// Tuesday of the month.
	/** The Last. */
	Last
}
