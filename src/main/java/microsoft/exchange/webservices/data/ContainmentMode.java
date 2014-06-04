/**************************************************************************
 * copyright file="ContainmentMode.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ContainmentMode.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Defines the containment mode for Contains search filters.
 */
public enum ContainmentMode {

	// The comparison is between the full string and the constant. The property
	// value and the supplied constant are precisely the same.
	/** The Full string. */
	FullString,

	// The comparison is between the string prefix and the constant.
	/** The Prefixed. */
	Prefixed,

	// The comparison is between a substring of the string and the constant.
	/** The Substring. */
	Substring,

	// The comparison is between a prefix on individual words in the string and
	// the constant.
	/** The Prefix on words. */
	PrefixOnWords,

	// The comparison is between an exact phrase in the string and the constant.
	/** The Exact phrase. */
	ExactPhrase
}
