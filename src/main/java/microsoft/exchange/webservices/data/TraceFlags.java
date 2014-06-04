/**************************************************************************
 * copyright file="TraceFlags.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the TraceFlags.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/***
 * Defines flags to control tracing details.
 * 
 */
public enum TraceFlags {
	/*
	 * No tracing.
	 */
	/** The None. */
	None,
	/*
	 * Trace EWS request messages.
	 */
	/** The Ews request. */
	EwsRequest,
	/*
	 * Trace EWS response messages.
	 */
	/** The Ews response. */
	EwsResponse,
	/*
	 * Trace EWS response HTTP headers.
	 */
	/** The Ews response http headers. */
	EwsResponseHttpHeaders,
	/*
	 * Trace Autodiscover request messages.
	 */
	/** The Autodiscover request. */
	AutodiscoverRequest,
	/*
	 * Trace Autodiscover response messages.
	 */
	/** The Autodiscover response. */
	AutodiscoverResponse,
	/*
	 * Trace Autodiscover response HTTP headers.
	 */
	/** The Autodiscover response http headers. */
	AutodiscoverResponseHttpHeaders,
	/*
	 * Trace Autodiscover configuration logic.
	 */
	/** The Autodiscover configuration. */
	AutodiscoverConfiguration,

	/*
	 * Trace messages used in debugging the Exchange Web Services Managed API
	 */
	/** The Debug Message. */
	DebugMessage,

	/*
	 * Trace EWS request HTTP headers.
	 */
	/** The Ews Request Http Headers. */
	EwsRequestHttpHeaders,

	/*
	 * Trace Autodiscover request HTTP headers.
	 */
	/** The Autodiscover Request HttpHeaders */
	AutodiscoverRequestHttpHeaders,

}
