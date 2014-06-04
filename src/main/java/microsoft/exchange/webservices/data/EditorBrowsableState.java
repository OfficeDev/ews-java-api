/**************************************************************************
 * copyright file="EditorBrowsableState.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the EditorBrowsableState.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * The Enum EditorBrowsableState.
 */
 enum EditorBrowsableState {

	// Summary:
	// The property or method is always browsable from within an editor.
	/** The Always. */
	Always,
	//
	// Summary:
	// The property or method is never browsable from within an editor.
	/** The Never. */
	Never,
	//
	// Summary:
	// The property or method is a feature that only advanced users should see.
	// An editor can either show or hide such properties.
	/** The Advanced. */
	Advanced,
}
