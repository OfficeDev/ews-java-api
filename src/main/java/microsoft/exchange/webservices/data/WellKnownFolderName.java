/**************************************************************************
 * copyright file="WellKnownFolderName.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the WellKnownFolderName.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Defines well known folder names.
 */
public enum WellKnownFolderName {
	// The Calendar folder.
	/** The Calendar. */
	Calendar,

	// The Contacts folder.
	/** The Contacts. */
	Contacts,

	// The Deleted Items folder
	/** The Deleted items. */
	DeletedItems,

	// The Drafts folder.
	/** The Drafts. */
	Drafts,

	// The Inbox folder.
	/** The Inbox. */
	Inbox,

	// The Journal folder.
	/** The Journal. */
	Journal,

	// The Notes folder.
	/** The Notes. */
	Notes,

	// The Outbox folder.
	/** The Outbox. */
	Outbox,

	// The Sent Items folder.
	/** The Sent items. */
	SentItems,

	// The Tasks folder.
	/** The Tasks. */
	Tasks,

	// The message folder root.
	/** The Msg folder root. */
	MsgFolderRoot,

	// The root of the Public Folders hierarchy.
	/** The Public folders root. */
	@RequiredServerVersion(version = ExchangeVersion.Exchange2007_SP1)
	PublicFoldersRoot,

	// The root of the mailbox.
	/** The Root. */
	Root,

	// The Junk E-mail folder.
	/** The Junk email. */
	JunkEmail,

	// The Search Folders folder, also known as the Finder folder.
	/** The Search folders. */
	SearchFolders,

	// The Voicemail folder.
	/** The Voice mail. */
	VoiceMail,
	
	/**  The Dumpster 2.0 root folder.*/
	 
    @RequiredServerVersion(version = ExchangeVersion.Exchange2010_SP1)
    RecoverableItemsRoot,

    /** The Dumpster 2.0 soft deletions folder.*/
    @RequiredServerVersion(version = ExchangeVersion.Exchange2010_SP1)
    RecoverableItemsDeletions,

    /** The Dumpster 2.0 versions folder.*/
    @RequiredServerVersion(version = ExchangeVersion.Exchange2010_SP1)
    RecoverableItemsVersions,

    /** The Dumpster 2.0 hard deletions folder.*/
    @RequiredServerVersion(version = ExchangeVersion.Exchange2010_SP1)
    RecoverableItemsPurges,

    /** The root of the archive mailbox.*/
    @RequiredServerVersion(version = ExchangeVersion.Exchange2010_SP1)
    ArchiveRoot,

    /** The message folder root in the archive mailbox.*/
    @RequiredServerVersion(version = ExchangeVersion.Exchange2010_SP1)
    ArchiveMsgFolderRoot,

    /** The Deleted Items folder in the archive mailbox.*/
    @RequiredServerVersion(version = ExchangeVersion.Exchange2010_SP1)
    ArchiveDeletedItems,

    /** The Dumpster 2.0 root folder in the archive mailbox.*/ 
    @RequiredServerVersion(version = ExchangeVersion.Exchange2010_SP1)
    ArchiveRecoverableItemsRoot,

    /** The Dumpster 2.0 soft deletions folder in the archive mailbox.*/
    @RequiredServerVersion(version = ExchangeVersion.Exchange2010_SP1)
    ArchiveRecoverableItemsDeletions,

    /** The Dumpster 2.0 versions folder in the archive mailbox.*/
    @RequiredServerVersion(version = ExchangeVersion.Exchange2010_SP1)
    ArchiveRecoverableItemsVersions,

    /** The Dumpster 2.0 hard deletions folder in the archive mailbox.*/
    @RequiredServerVersion(version = ExchangeVersion.Exchange2010_SP1)
    ArchiveRecoverableItemsPurges,


}
