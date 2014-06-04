/**************************************************************************
 * copyright file="LegacyFreeBusyStatus.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the LegacyFreeBusyStatus.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Defines the legacy free/busy status associated with an appointment.
 */
public enum LegacyFreeBusyStatus {

	// The time slot associated with the appointment appears as free.
	/** The Free. */
	Free(0),

	// The time slot associated with the appointment appears as tentative.
	/** The Tentative. */
	Tentative(1),

	// The time slot associated with the appointment appears as busy.
	/** The Busy. */
	Busy(2),

	// The time slot associated with the appointment appears as Out of Office.
	/** The OOF. */
	OOF(3),

	// No free/busy status is associated with the appointment.
	/** The No data. */
	NoData(4);

	/** The busy status. */
	@SuppressWarnings("unused")
	private final int busyStatus;

	/**
	 * Instantiates a new legacy free busy status.
	 * 
	 * @param busyStatus
	 *            the busy status
	 */
	LegacyFreeBusyStatus(int busyStatus) {
		this.busyStatus = busyStatus;
	}
	
	int getBusyStatus() {
	    return busyStatus;
	  }

}
