/**************************************************************************
 * copyright file="MeetingRequestsDeliveryScope.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the MeetingRequestsDeliveryScope.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Defines how meeting requests are sent to delegates.
 */
public enum MeetingRequestsDeliveryScope {

	// Meeting requests are sent to delegates only.
	/** The Delegates only. */
	DelegatesOnly,

	// Meeting requests are sent to delegates and to the owner of the mailbox.
	/** The Delegates and me. */
	DelegatesAndMe,

	// Meeting requests are sent to delegates and informational messages are
	// sent to the owner of the mailbox.
	/** The Delegates and send information to me. */
	DelegatesAndSendInformationToMe,

	//Meeting requests are not sent to delegates.  This value is
	//supported only for Exchange 2010 SP1 or later
    //server versions.
	@RequiredServerVersion(version = ExchangeVersion.Exchange2010_SP1)	
	NoForward
}
