/**************************************************************************
 * copyright file="MeetingAttendeeType.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the MeetingAttendeeType.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Defines the type of a meeting attendee.
 */
public enum MeetingAttendeeType {

	// The attendee is the organizer of the meeting.
	/** The Organizer. */
	Organizer,

	// The attendee is required.
	/** The Required. */
	Required,

	// The attendee is optional.
	/** The Optional. */
	Optional,

	// The attendee is a room.
	/** The Room. */
	Room,

	// The attendee is a resource.
	/** The Resource. */
	Resource

}
