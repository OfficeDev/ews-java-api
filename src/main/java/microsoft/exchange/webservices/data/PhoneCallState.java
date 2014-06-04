/**************************************************************************
 * copyright file="PhoneCallState.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the PhoneCallState.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * The PhoneCallState enumeration.
 */
public enum PhoneCallState {

	// Idle
	/** The Idle. */
	Idle,

	// Connecting
	/** The Connecting. */
	Connecting,

	// Alerted
	/** The Alerted. */
	Alerted,

	// Connected
	/** The Connected. */
	Connected,

	// Disconnected
	/** The Disconnected. */
	Disconnected,

	// Incoming
	/** The Incoming. */
	Incoming,

	// Transferring
	/** The Transferring. */
	Transferring,

	// Forwarding
	/** The Forwarding. */
	Forwarding

}
