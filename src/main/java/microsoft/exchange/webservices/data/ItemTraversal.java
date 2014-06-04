/**************************************************************************
 * copyright file="ItemTraversal.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ItemTraversal.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Defines the scope of FindItems operations.
 */
public enum ItemTraversal {

	// All non deleted items in the specified folder are retrieved.
	/** The Shallow. */
	Shallow,

	// Only soft-deleted items are retrieved.
	/** The Soft deleted. */
	SoftDeleted,

	// Only associated items are retrieved (Exchange 2010 or later).
	/** The Associated. */
	@RequiredServerVersion(version = ExchangeVersion.Exchange2010)
	Associated
}
