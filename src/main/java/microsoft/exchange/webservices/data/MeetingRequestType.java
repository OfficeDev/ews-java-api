/**************************************************************************
 * copyright file="MeetingRequestType.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the MeetingRequestType.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Defines the type of a meeting request.
 */
public enum MeetingRequestType {

	// Undefined meeting request type.
	/** The None. */
	None,

	// The meeting request is an update to the original meeting.
	/** The Full update. */
	FullUpdate,

	// The meeting request is an information update.
	/** The Informational update. */
	InformationalUpdate,

	// The meeting request is for a new meeting.
	/** The New meeting request. */
	NewMeetingRequest,

	// The meeting request is outdated.
	/** The Outdated. */
	Outdated,

	// The meeting update is a silent update to an existing meeting.
	/** The Silent update. */
	SilentUpdate,

	// The meeting update was forwarded to a delegate, and this copy is
	// informational.
	/** The Principal wants copy. */
	PrincipalWantsCopy

}
