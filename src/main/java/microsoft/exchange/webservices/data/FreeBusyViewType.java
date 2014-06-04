/**************************************************************************
 * copyright file="FreeBusyViewType.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the FreeBusyViewType.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Defines the type of free/busy information returned by a GetUserAvailability
 * operation.
 */
public enum FreeBusyViewType {

	// No view could be returned. This value cannot be specified in a call to
	// GetUserAvailability.
	/** The None. */
	None,

	// Represents an aggregated free/busy stream. In cross-forest scenarios in
	// which the target user in one forest
	// does not have an Availability service configured, the Availability
	// service of the requestor retrieves the
	// target users free/busy information from the free/busy public folder.
	// Because public folders only store
	// free/busy information in merged form, MergedOnly is the only available
	// information.
	/** The Merged only. */
	MergedOnly,

	// Represents the legacy status information: free, busy, tentative, and OOF.
	// This also includes the start/end
	// times of the appointments. This view is richer than the legacy free/busy
	// view because individual meeting
	// start and end times are provided instead of an aggregated free/busy
	// stream.
	/** The Free busy. */
	FreeBusy,

	// Represents all the properties in FreeBusy with a stream of merged
	// free/busy availability information.
	/** The Free busy merged. */
	FreeBusyMerged,

	// Represents the legacy status information: free, busy, tentative, and OOF;
	// the start/end times of the
	// appointments; and various properties of the appointment such as subject,
	// location, and importance.
	// This requested view will return the maximum amount of information for
	// which the requesting user is privileged.
	// If merged free/busy information only is available, as with requesting
	// information for users in a Microsoft
	// Exchange Server 2003 forest, MergedOnly will be returned. Otherwise,
	// FreeBusy or Detailed will be returned.
	/** The Detailed. */
	Detailed,

	// Represents all the properties in Detailed with a stream of merged
	// free/busy availability
	// information. If only merged free/busy information is available, for
	// example if the mailbox exists on a computer
	// running Exchange 2003, MergedOnly will be returned. Otherwise,
	// FreeBusyMerged or DetailedMerged will be returned.
	/** The Detailed merged. */
	DetailedMerged

}
