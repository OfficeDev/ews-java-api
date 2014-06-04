/**************************************************************************
 * copyright file="ConnectingIdType.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ConnectingIdType.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Defines the type of Id of a ConnectingId object.
 * 
 */
public enum ConnectingIdType {

	// / The connecting Id is a principal name.
	/** The Principal name. */
	PrincipalName,

	// / The Id is an SID.
	/** The SID. */
	SID,

	// / The Id is an SMTP address.
	/** The Smtp address. */
	SmtpAddress
}
