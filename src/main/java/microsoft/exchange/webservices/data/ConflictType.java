/**************************************************************************
 * copyright file="ConflictType.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ConflictType.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Defines the conflict types that can be returned in meeting time suggestions.
 */
public enum ConflictType {

	// There is a conflict with an indicidual attendee.
	/** The Individual attendee conflict. */
	IndividualAttendeeConflict,

	// There is a conflict with at least one member of a group.
	/** The Group conflict. */
	GroupConflict,

	// There is a conflict with at least one member of a group, but the group
	// was too big for detailed information to be returned.
	/** The Group too big conflict. */
	GroupTooBigConflict,

	// There is a conflict with an unresolvable attendee or an attendee that is
	// not a user, group, or contact.
	/** The Unknown attendee conflict. */
	UnknownAttendeeConflict

}
