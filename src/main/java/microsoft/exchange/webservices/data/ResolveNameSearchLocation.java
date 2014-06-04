/**************************************************************************
 * copyright file="ResolveNameSearchLocation.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ResolveNameSearchLocation.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Defines the location where a ResolveName operation searches for contacts.
 */
public enum ResolveNameSearchLocation {

	// The name is resolved against the Global Address List.
	/** The Directory only. */
	DirectoryOnly,

	// The name is resolved against the Global Address List and then against the
	// Contacts folder if no match was found.
	/** The Directory then contacts. */
	DirectoryThenContacts,

	// The name is resolved against the Contacts folder.
	/** The Contacts only. */
	ContactsOnly,

	// The name is resolved against the Contacts folder and then against the
	// Global Address List if no match was found.
	/** The Contacts then directory. */
	ContactsThenDirectory
}
