/**************************************************************************
 * copyright file="ResponseMessageType.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ResponseMessageType.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Defines the type of a ResponseMessage object.
 */
public enum ResponseMessageType {

	// The ResponseMessage is a reply to the sender of a message.
	/** The Reply. */
	Reply,

	// The ResponseMessage is a reply to the sender and all the recipients of a
	// message.
	/** The Reply all. */
	ReplyAll,

	// The ResponseMessage is a forward.
	/** The Forward. */
	Forward

}
