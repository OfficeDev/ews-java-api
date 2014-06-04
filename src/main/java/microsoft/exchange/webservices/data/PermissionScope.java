/**************************************************************************
 * copyright file="PermissionScope.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the PermissionScope.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Defines the scope of a user's permission on a folders.
 */
public enum PermissionScope {

	// The user does not have the associated permission.
	/** The None. */
	None,

	// The user has the associated permission on items that it owns.
	/** The Owned. */
	Owned,

	// The user has the associated permission on all items.
	/** The All. */
	All

}
