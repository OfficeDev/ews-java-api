/**************************************************************************
 * copyright file="OofExternalAudience.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the OofExternalAudience.java.
 **************************************************************************/

package microsoft.exchange.webservices.data;

/**
 * Defines the external audience of an Out of Office notification.
 */
public enum OofExternalAudience {

	// No external recipients should receive Out of Office notifications.
	/** The None. */
	None,

	// Only recipients that are in the user's Contacts frolder should receive
	// Out of Office notifications.
	/** The Known. */
	Known,

	// All recipients should receive Out of Office notifications.
	/** The All. */
	All
}
