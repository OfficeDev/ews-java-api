/**************************************************************************
 * copyright file="DelegateFolderPermissionLevel.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the DelegateFolderPermissionLevel.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Defines a delegate user's permission level on a specific folder.
 */
public enum DelegateFolderPermissionLevel {

	// The delegate has no permission.
	/** The None. */
	None,

	// The delegate has Editor permissions.
	/** The Editor. */
	Editor,

	// The delegate has Reviewer permissions.
	/** The Reviewer. */
	Reviewer,

	// The delegate has Author permissions.
	/** The Author. */
	Author,

	// The delegate has custom permissions.
	/** The Custom. */
	Custom
}
