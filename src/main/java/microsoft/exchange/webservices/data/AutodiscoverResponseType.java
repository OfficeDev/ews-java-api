/**************************************************************************
 * copyright file="AutodiscoverResponseType.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the AutodiscoverResponseType.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Defines the types of response the Autodiscover service can return.
 */
enum AutodiscoverResponseType {

	// The request returned an error.
	/** The Error. */
	Error,
	// A URL redirection is necessary.
	/** The Redirect url. */
	RedirectUrl,
	// An address redirection is necessary.
	/** The Redirect address. */
	RedirectAddress,
	// The request succeeded.
	/** The Success. */
	Success
}
