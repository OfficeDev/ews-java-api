/**************************************************************************
 * copyright file="ServiceObjectType.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ServiceObjectType.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Defines the type of a service object.
 */
public enum ServiceObjectType {

	// The object is a folder.
	/** The Folder. */
	Folder,

	// The object is an item.
	/** The Item. */
	Item,
	
	/// Data represents a conversation
	/** The Conversation. */
    Conversation
}
