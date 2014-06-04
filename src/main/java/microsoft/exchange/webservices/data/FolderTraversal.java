/**************************************************************************
 * copyright file="FolderTraversal.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the FolderTraversal.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Defines the scope of FindFolders operations.
 */
public enum FolderTraversal {

	// Only direct sub-folders are retrieved.
	/** The Shallow. */
	Shallow,

	// The entire hierarchy of sub-folders is retrieved.
	/** The Deep. */
	Deep,

	// Only soft deleted folders are retrieved.
	/** The Soft deleted. */
	SoftDeleted

}
