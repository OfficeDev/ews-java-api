/**************************************************************************
 * copyright file="FolderPermissionLevel.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the FolderPermissionLevel.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

//TODO : Do we want to include more information about 
//what those levels actually allow users to do?
/**
 * Defines permission levels for calendar folders.
 */
public enum FolderPermissionLevel {

	// No permission is granted.
	/** The None. */
	None,

	// The Owner level.
	/** The Owner. */
	Owner,

	// The Publishing Editor level.
	/** The Publishing editor. */
	PublishingEditor,

	// The Editor level.
	/** The Editor. */
	Editor,

	// The Pusnlishing Author level.
	/** The Publishing author. */
	PublishingAuthor,

	// The Author level.
	/** The Author. */
	Author,

	// The Non-editing Author level.
	/** The Nonediting author. */
	NoneditingAuthor,

	// The Reviewer level.
	/** The Reviewer. */
	Reviewer,

	// The Contributor level.
	/** The Contributor. */
	Contributor,

	// The Free/busy Time Only level. (Can only be applied to Calendar folders).
	/** The Free busy time only. */
	FreeBusyTimeOnly,

	// The Free/busy Time, Subject and Location level. (Can only be applied to
	// Calendar folders).
	/** The Free busy time and subject and location. */
	FreeBusyTimeAndSubjectAndLocation,

	// The Custom level.
	/** The Custom. */
	Custom
}
