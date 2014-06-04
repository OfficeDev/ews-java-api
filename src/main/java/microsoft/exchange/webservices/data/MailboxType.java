/**************************************************************************
 * copyright file="MailboxType.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the MailboxType.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Defines the type of an EmailAddress object.
 */
public enum MailboxType {

	// Unknown mailbox type (Exchange 2010 or later).
	/** The Unknown. */
	@RequiredServerVersion(version = ExchangeVersion.Exchange2010)
	Unknown,

	// The EmailAddress represents a one-off contact (Exchange 2010 or later).
	/** The One off. */
	@RequiredServerVersion(version = ExchangeVersion.Exchange2010)
	OneOff,

	// The EmailAddress represents a mailbox.
	/** The Mailbox. */
	Mailbox,

	// The EmailAddress represents a public folder.
	/** The Public folder. */
	@RequiredServerVersion(version = ExchangeVersion.Exchange2007_SP1)
	PublicFolder,

	// The EmailAddress represents a Public Group.
	/** The Public group. */
	@EwsEnum(schemaName = "PublicDL")
	PublicGroup,

	// The EmailAddress represents a Contact Group.
	/** The Contact group. */
	@EwsEnum(schemaName = "PrivateDL")
	ContactGroup,

	// The EmailAddress represents a store contact or AD mail contact.
	/** The Contact. */
	Contact,

}
