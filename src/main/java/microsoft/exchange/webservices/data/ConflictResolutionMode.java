/**************************************************************************
 * copyright file="ConflictResolutionMode.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ConflictResolutionMode.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Defines how conflict resolutions are handled in update operations.
 */
public enum ConflictResolutionMode {

	// Local property changes are discarded.
	/** The Never overwrite. */
	NeverOverwrite,

	// Local property changes are applied to the server unless the server-side
	// copy is more recent than the local copy.
	/** The Auto resolve. */
	AutoResolve,

	// Local property changes overwrite server-side changes.
	/** The Always overwrite. */
	AlwaysOverwrite

}
