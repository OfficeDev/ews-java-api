/**************************************************************************
 * copyright file="SendCancellationsMode.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the SendCancellationsMode.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Defines how meeting cancellations should be sent to attendees when an
 * appointment is deleted.
 */
public enum SendCancellationsMode {

	// No meeting cancellation is sent.
	/** The Send to none. */
	SendToNone,

	// Meeting cancellations are sent to all attendees.
	/** The Send only to all. */
	SendOnlyToAll,

	// Meeting cancellations are sent to all attendees and a copy of the meeting
	// is saved in the organizer's Sent Items folder.
	/** The Send to all and save copy. */
	SendToAllAndSaveCopy,

}
