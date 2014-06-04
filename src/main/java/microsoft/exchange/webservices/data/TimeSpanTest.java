/**************************************************************************
 * copyright file="TimeSpanTest.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the TimeSpanTest.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * The Class TimeSpanTest.
 */
public class TimeSpanTest {
	// public sat;

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		Calendar calendar = new GregorianCalendar(2008, Calendar.OCTOBER, 10);
		timeSpanToXSDuration(calendar);
	}

	/**
	 * Time span to xs duration.
	 * 
	 * @param timeSpan
	 *            the time span
	 * @return the string
	 */
	public static String timeSpanToXSDuration(Calendar timeSpan) {
		String offsetStr = (timeSpan.SECOND < 0) ? "-" : "";
		String obj = String.format("%s %s %s %s %s ", offsetStr, Math
				.abs(timeSpan.DAY_OF_MONTH), Math.abs(timeSpan.HOUR_OF_DAY),
				Math.abs(timeSpan.MINUTE), Math.abs(timeSpan.SECOND) + "." +
						 Math.abs(timeSpan.MILLISECOND));

		return obj;
	}
}
