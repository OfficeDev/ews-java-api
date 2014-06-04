/**************************************************************************
 * copyright file="SendInvitationsOrCancellationsMode.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the SendInvitationsOrCancellationsMode.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Defines if/how meeting invitations or cancellations should be sent to
 * attendees when an appointment is updated.
 */
public enum SendInvitationsOrCancellationsMode {

	// No meeting invitation/cancellation is sent.
	/** The Send to none. */
	SendToNone,

	// Meeting invitations/cancellations are sent to all attendees.
	/** The Send only to all. */
	SendOnlyToAll,

	// Meeting invitations/cancellations are sent only to attendees that have
	// been added or modified.
	/** The Send only to changed. */
	SendOnlyToChanged,

	// Meeting invitations/cancellations are sent to all attendees and a copy is
	// saved in the organizer's Sent Items folder.
	/** The Send to all and save copy. */
	SendToAllAndSaveCopy,

	// Meeting invitations/cancellations are sent only to attendees that have
	// been added or modified and a copy is saved in the organizer's Sent Items
	// folder.
	/** The Send to changed and save copy. */
	SendToChangedAndSaveCopy

}
