/**************************************************************************
 * copyright file="AppointmentType.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the AppointmentType.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Defines the type of an appointment.
 */
public enum AppointmentType {
	// The appointment is non-recurring.
	/** The Single. */
	Single,

	// The appointment is an occurrence of a recurring appointment.
	/** The Occurrence. */
	Occurrence,

	// The appointment is an exception of a recurring appointment.
	/** The Exception. */
	Exception,

	// The appointment is the recurring master of a series.
	/** The Recurring master. */
	RecurringMaster
}
