/**************************************************************************
 * copyright file="DeleteMode.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the DeleteMode.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents deletion modes.
 */
public enum DeleteMode {

	// The item or folder will be permanently deleted.
	/** The Hard delete. */
	HardDelete,

	// The item or folder will be moved to the dumpster. Items and folders in
	// the dumpster can be recovered.
	/** The Soft delete. */
	SoftDelete,

	// The item or folder will be moved to the mailbox' Deleted Items folder.
	/** The Move to deleted items. */
	MoveToDeletedItems

}
