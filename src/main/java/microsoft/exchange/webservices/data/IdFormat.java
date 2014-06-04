/**************************************************************************
 * copyright file="IdFormat.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the IdFormat.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Defines supported Id formats in ConvertId operations.
 */
public enum IdFormat {

	// The EWS Id format used in Exchange 2007 RTM.
	/** The Ews legacy id. */
	EwsLegacyId,

	// The EWS Id format used in Exchange 2007 SP1 and above.
	/** The Ews id. */
	EwsId,

	// The base64-encoded PR_ENTRYID property.
	/** The Entry id. */
	EntryId,

	// The hexadecimal representation of the PR_ENTRYID property.
	/** The Hex entry id. */
	HexEntryId,

	// The Store Id format.
	/** The Store id. */
	StoreId,

	// The Outlook Web Access Id format.
	/** The Owa id. */
	OwaId
}
