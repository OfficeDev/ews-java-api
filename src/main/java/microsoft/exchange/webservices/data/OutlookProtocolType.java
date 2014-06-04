/**************************************************************************
 * copyright file="OutlookProtocolType.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the OutlookProtocolType.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Defines supported Outlook protocls.
 */
enum OutlookProtocolType {

	// The Remote Procedure Call (RPC) protocol.
	/** The Rpc. */
	Rpc,

	// The Remote Procedure Call (RPC) over HTTP protocol.
	/** The Rpc over http. */
	RpcOverHttp,

	// The Web protocol.
	/** The Web. */
	Web,

	// The protocol is unknown.
	/** The Unknown. */
	Unknown

}
