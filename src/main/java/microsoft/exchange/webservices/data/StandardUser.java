/**************************************************************************
 * copyright file="StandardUser.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the StandardUser.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Defines a standard delegate user.
 */
public enum StandardUser {

	// The Default delegate user, used to define default delegation permissions.
	/** The Default. */
	Default,

	// The Anonymous delegate user, used to define delegate permissions for
	// unauthenticated users.
	/** The Anonymous. */
	Anonymous

}
