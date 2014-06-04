/**************************************************************************
 * copyright file="ContactSource.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ContactSource.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Defines the source of a contact or group.
 */
public enum ContactSource {
	// The contact or group is stored in the Global Address List
	/** The Active directory. */
	ActiveDirectory,

	// The contact or group is stored in Exchange.
	/** The Store. */
	Store

}
