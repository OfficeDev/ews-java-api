/**************************************************************************
 * copyright file="SendInvitationsMode.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the SendInvitationsMode.java.
 **************************************************************************/

package microsoft.exchange.webservices.data;

/**
 * Defines if/how meeting invitations are sent.
 */
public enum SendInvitationsMode {

	// No meeting invitation is sent.
	/** The Send to none. */
	SendToNone,

	// Meeting invitations are sent to all attendees.
	/** The Send only to all. */
	SendOnlyToAll,

	// Meeting invitations are sent to all attendees and a copy of the
	// invitation message is saved.
	/** The Send to all and save copy. */
	SendToAllAndSaveCopy

}
