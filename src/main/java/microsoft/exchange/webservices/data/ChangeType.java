/**************************************************************************
 * copyright file="ChangeType.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ChangeType.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Defines the type of change of a synchronization event.
 */
public enum ChangeType {

	// An item or folder was created.
	/** The Create. */
	Create,

	// An item or folder was modified.
	/** The Update. */
	Update,

	// An item or folder was deleted.
	/** The Delete. */
	Delete,

	// An item's IsRead flag was changed.
	/** The Read flag change. */
	ReadFlagChange,
}
