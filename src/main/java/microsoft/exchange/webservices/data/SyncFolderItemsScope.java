/**************************************************************************
 * copyright file="SyncFolderItemsScope.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the SyncFolderItemsScope.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Determines items to be included in a SyncFolderItems response.
 */
public enum SyncFolderItemsScope {

	// Include only normal items in the response.
	/** The Normal items. */
	NormalItems,

	// Include normal and associated items in the response.
	/** The Normal and associated items. */
	NormalAndAssociatedItems
}
