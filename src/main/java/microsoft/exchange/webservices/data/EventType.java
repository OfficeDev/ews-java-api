/**************************************************************************
 * copyright file="EventType.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the EventType.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Defines the types of event that can occur in a folder.
 */
public enum EventType {
	// This event is sent to a client application by push notifications to
	// indicate that
	// the subscription is still alive.
	/** The Status. */
	@EwsEnum(schemaName = "StatusEvent")
	Status,

	// This event indicates that a new e-mail message was received.
	/** The New mail. */
	@EwsEnum(schemaName = "NewMailEvent")
	NewMail,

	// This event indicates that an item or folder has been deleted.
	/** The Deleted. */
	@EwsEnum(schemaName = "DeletedEvent")
	Deleted,

	// This event indicates that an item or folder has been modified.
	/** The Modified. */
	@EwsEnum(schemaName = "ModifiedEvent")
	Modified,

	// This event indicates that an item or folder has been moved to another
	// folder.
	/** The Moved. */
	@EwsEnum(schemaName = "MovedEvent")
	Moved,

	// This event indicates that an item or folder has been copied to another
	// folder.
	/** The Copied. */
	@EwsEnum(schemaName = "CopiedEvent")
	Copied,

	// This event indicates that a new item or folder has been created.
	/** The Created. */
	@EwsEnum(schemaName = "CreatedEvent")
	Created,
	
	@RequiredServerVersion(version = ExchangeVersion.Exchange2010_SP1)
	@EwsEnum(schemaName = "FreeBusyChangedEvent")
     FreeBusyChanged
     
}
