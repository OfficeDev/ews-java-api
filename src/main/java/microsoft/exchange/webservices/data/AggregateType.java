/**************************************************************************
 * copyright file="AggregateType.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the AggregateType.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Defines the type of aggregation to perform.
 */
public enum AggregateType {

	// The maximum value is calculated.
	/** The Minimum. */
	Minimum,

	// The minimum value is calculated.
	/** The Maximum. */
	Maximum
}
