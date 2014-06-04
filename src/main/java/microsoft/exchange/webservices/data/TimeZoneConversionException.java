/**************************************************************************
 * copyright file="TimeZoneConversionException.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the TimeZoneConversionException.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents an error that occurs when a date and time cannot be converted from
 * one time zone to another.
 */
public class TimeZoneConversionException extends ServiceLocalException {

	/**
	 * ServiceLocalException Constructor.
	 */
	public TimeZoneConversionException() {
		super();
	}

	/**
	 * ServiceLocalException Constructor.
	 * 
	 * @param message
	 *            the message
	 */
	public TimeZoneConversionException(String message) {
		super(message);
	}

	/**
	 * ServiceLocalException Constructor.
	 * 
	 * @param message
	 *            the message
	 * @param innerException
	 *            the inner exception
	 */
	public TimeZoneConversionException(String message,
			Exception innerException) {
		super(message, innerException);
	}

}
