/**************************************************************************
 * copyright file="DayOfTheWeek.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the DayOfTheWeek.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.Calendar;

/**
 * Specifies the day of the week. For the standard days of the week (Sunday,
 * Monday...) the DayOfTheWeek enum value is the same as the System.DayOfWeek
 * enum type. These values can be safely cast between the two enum types. The
 * special days of the week (Day, Weekday and WeekendDay) are used for monthly
 * and yearly recurrences and cannot be cast to System.DayOfWeek values.
 */
public enum DayOfTheWeek {

	// Sunday
	/** The Sunday. */
	Sunday(Calendar.SUNDAY),

	// Monday
	/** The Monday. */
	Monday(Calendar.MONDAY),

	// Tuesday
	/** The Tuesday. */
	Tuesday(Calendar.TUESDAY),

	// Wednesday
	/** The Wednesday. */
	Wednesday(Calendar.WEDNESDAY),

	// Thursday
	/** The Thursday. */
	Thursday(Calendar.THURSDAY),

	// Friday
	/** The Friday. */
	Friday(Calendar.FRIDAY),

	// Saturday
	/** The Saturday. */
	Saturday(Calendar.SATURDAY),

	// Any day of the week
	/** The Day. */
	Day(),

	// Any day of the usual business week (Monday-Friday)
	/** The Weekday. */
	Weekday(),

	// Any weekend day (Saturday or Sunday)
	/** The Weekend day. */
	WeekendDay;

	/** The day of week. */
	@SuppressWarnings("unused")
	private int dayOfWeek = 0;

	/**
	 * Instantiates a new day of the week.
	 * 
	 * @param dayOfWeek
	 *            the day of week
	 */
	DayOfTheWeek(int dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	/**
	 * Instantiates a new day of the week.
	 */
	DayOfTheWeek() {

	}
}
