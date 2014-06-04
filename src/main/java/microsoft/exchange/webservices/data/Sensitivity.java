/**************************************************************************
 * copyright file="Sensitivity.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the Sensitivity.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Defines the sensitivity of an item.
 */
public enum Sensitivity {

	// The item has a normal sensitivity.
	/** The Normal. */
	Normal,

	// The item is personal.
	/** The Personal. */
	Personal,

	// The item is private.
	/** The Private. */
	Private,

	// The item is confidential.
	/** The Confidential. */
	Confidential

}
