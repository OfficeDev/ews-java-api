/**************************************************************************
 * copyright file="ComparisonMode.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ComparisonMode.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Defines the way values are compared in search filters.
 */
public enum ComparisonMode {

	// The comparison is exact.
	/** The Exact. */
	Exact,

	// The comparison ignores casing.
	/** The Ignore case. */
	IgnoreCase,

	// The comparison ignores spacing characters.
	/** The Ignore non spacing characters. */
	IgnoreNonSpacingCharacters,

	// The comparison ignores casing and spacing characters.
	/** The Ignore case and non spacing characters. */
	IgnoreCaseAndNonSpacingCharacters

	// From bug E12:113326
	//
	// Although the following four values are defined in
	// the EWS schema, they are useless
	// as they are all thechnically equivalent to Loose.
	// We are not exposing those values
	// in this API. When we encounter one of these
	// values on an existing search folder
	// restriction, we map it to IgnoreCaseAndNonSpacingCharacters.
	//
	// Loose,
	// LooseAndIgnoreCase,
	// LooseAndIgnoreNonSpace,
	// LooseAndIgnoreCaseAndIgnoreNonSpace
}
