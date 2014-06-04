/**************************************************************************
 * copyright file="FolderPermissionReadAccess.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the FolderPermissionReadAccess.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Defines a user's read access permission on items in a non-calendar folder.
 */
public enum FolderPermissionReadAccess {

	// The user has no read access on the items in the folder.
	/** The None. */
	None,

	// The user can read the start and end date and time of appointments. (Can
	// only be applied to Calendar folders).
	/** The Time only. */
	TimeOnly,

	// The user can read the start and end date and time, subject and location
	// of appointments. (Can only be applied to Calendar folders).
	/** The Time and subject and location. */
	TimeAndSubjectAndLocation,

	// The user has access to the full details of items.
	/** The Full details. */
	FullDetails
}
