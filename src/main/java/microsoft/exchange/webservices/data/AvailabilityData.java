/**************************************************************************
 * copyright file="AvailabilityData.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the AvailabilityData.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Defines the type of data that can be requested via GetUserAvailability.
 */
public enum AvailabilityData {

	// Only return free/busy data.
	/** The Free busy. */
	FreeBusy,

	// Only return suggestions.
	/** The Suggestions. */
	Suggestions,

	// Return both free/busy data and suggestions.
	/** The Free busy and suggestions. */
	FreeBusyAndSuggestions

}
