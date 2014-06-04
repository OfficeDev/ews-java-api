/**************************************************************************
 * copyright file="MeetingResponseType.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the MeetingResponseType.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Defines the types of response given to a meeting request.
 */
public enum MeetingResponseType {

	// The response type is inknown.
	/** The Unknown. */
	Unknown,

	// There was no response. The authenticated is the organizer of the meeting.
	/** The Organizer. */
	Organizer,

	// The meeting was tentatively accepted.
	/** The Tentative. */
	Tentative,

	// The meeting was accepted.
	/** The Accept. */
	Accept,

	// The meeting was declined.
	/** The Decline. */
	Decline,

	// No response was received for the meeting.
	/** The No response received. */
	NoResponseReceived

}
