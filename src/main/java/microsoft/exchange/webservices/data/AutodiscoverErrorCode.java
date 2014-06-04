/**************************************************************************
 * copyright file="AutodiscoverErrorCode.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the AutodiscoverErrorCode.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Defines the error codes that can be returned by the Autodiscover service.
 */
public enum AutodiscoverErrorCode {

	// There was no Error.
	/** The No error. */
	NoError,

	// The caller must follow the e-mail address redirection that was returned
	// by Autodiscover.
	/** The Redirect address. */
	RedirectAddress,

	// The caller must follow the URL redirection that was returned by
	// Autodiscover.
	/** The Redirect url. */
	RedirectUrl,

	// The user that was passed in the request is invalid.
	/** The Invalid user. */
	InvalidUser,

	// The request is invalid.
	/** The Invalid request. */
	InvalidRequest,

	// A specified setting is invalid.
	/** The Invalid setting. */
	InvalidSetting,

	// A specified setting is not available.
	/** The Setting is not available. */
	SettingIsNotAvailable,

	// The server is too busy to process the request.
	/** The Server busy. */
	ServerBusy,

	// The requested domain is not valid.
	/** The Invalid domain. */
	InvalidDomain,

	// The organization is not federated.
	/** The Not federated. */
	NotFederated,

	// Internal server error.
	/** The Internal server error. */
	InternalServerError,
}
