/**************************************************************************
 * copyright file="OofState.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the OofState.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Defines a user's Out of Office Assistant status.
 */
public enum OofState {

	// The assistant is diabled.
	/** The Disabled. */
	Disabled,

	// The assistant is enabled.
	/** The Enabled. */
	Enabled,

	// The assistant is scheduled.
	/** The Scheduled. */
	Scheduled
}
