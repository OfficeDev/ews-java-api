/**************************************************************************
 * copyright file="ServiceErrorHandling.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ServiceErrorHandling.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Defines the type of error handling used for service method calls.
 */
enum ServiceErrorHandling {

	// Service method should return the error(s).
	/** The Return errors. */
	ReturnErrors,

	// Service method should throw exception when error occurs.
	/** The Throw on error. */
	ThrowOnError
}
