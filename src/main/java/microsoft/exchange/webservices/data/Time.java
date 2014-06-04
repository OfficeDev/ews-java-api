/**************************************************************************
 * copyright file="Time.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the Time.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.Date;

/**
 * Represents time.
 */
final class Time {
	
	/** The hours. */
	private int hours;
	
	/** The minutes. */
	private int minutes;
	
	/** The seconds. */
	private int seconds;

	/**
	 * Initializes a new instance of Time.
	 */
	protected Time() {
	}

	/**
	 * Initializes a new instance of Time.
	 * 
	 * @param minutes
	 *            The number of minutes since 12:00AM.
	 * @throws ArgumentException
	 *             the argument exception
	 */

	protected Time(int minutes) throws ArgumentException {
		this();
		if (minutes < 0 || minutes >= 1440) {
			throw new ArgumentException(String.format("%s,%s",
					Strings.MinutesMustBeBetween0And1439, "minutes"));
		}

		this.hours = minutes / 60;
		this.minutes = minutes % 60;
		this.seconds = 0;
	}

	/**
	 * Initializes a new instance of Time.
	 * 
	 * @param dateTime
	 *            the date time
	 * @throws ArgumentException
	 *             the argument exception
	 */
	protected Time(Date dateTime) throws ArgumentException {
		this.setHours(dateTime.getHours());
		this.setMinutes(dateTime.getMinutes());
		this.setSeconds(dateTime.getSeconds());
	}

	/**
	 * Initializes a new instance of Time.
	 * 
	 * @param hours
	 *            the hours
	 * @param minutes
	 *            the minutes
	 * @param seconds
	 *            the seconds
	 */
	protected Time(int hours, int minutes, int seconds) {
		this();
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
	}

	/**
	 * Convert Time to XML Schema time.
	 * 
	 * @return String in XML Schema time format
	 */

	protected String toXSTime() {
		return String.format("%s,%s,%s,%s","{0:00}:{1:00}:{2:00}",
				this.getHours(), this
				.getMinutes(), this.getSeconds());
	}

	/**
	 * Converts the time into a number of minutes since 12:00AM.
	 * 
	 * @return The number of minutes since 12:00AM the time represents.
	 */

	protected int convertToMinutes() {
		return this.getMinutes() + (this.getHours() * 60);
	}

	/**
	 * Gets  the hours.
	 *
	 * @return the hours
	 */
	protected int getHours() {
		return this.hours;
	}

	/**
	 * sets the hours.
	 * 
	 * @param value
	 *            the new hours
	 * @throws ArgumentException
	 *             the argument exception
	 */

	protected void setHours(int value) throws ArgumentException {
		if (value >= 0 && value < 24) {
			this.hours = value;
		} else {
			throw new ArgumentException(Strings.HourMustBeBetween0And23);
		}
	}

	/**
	 * Gets the minutes.
	 *
	 * @return the minutes
	 */
	protected int getMinutes() {
		return this.minutes;
	}

	/**
	 * Sets the minutes.
	 * 
	 * @param value
	 *            the new minutes
	 * @throws ArgumentException
	 *             the argument exception
	 */
	protected void setMinutes(int value) throws ArgumentException {
		if (value >= 0 && value < 60) {
			this.minutes = value;
		} else {
			throw new ArgumentException(Strings.MinuteMustBeBetween0And59);
		}
	}

	/**
	 * Gets the seconds.
	 *
	 * @return the seconds
	 */
	protected int getSeconds() {
		return this.seconds;
	}

	/**
	 * Sets the seconds.
	 * 
	 * @param value
	 *            the new seconds
	 * @throws ArgumentException
	 *             the argument exception
	 */
	protected void setSeconds(int value) throws ArgumentException {
		if (value >= 0 && value < 60) {
			this.seconds = value;
		} else {
			throw new ArgumentException(Strings.SecondMustBeBetween0And59);
		}
	}
}
