/**************************************************************************
 * copyright file="DelegateInformation.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the DelegateInformation.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/***
 * Represents the results of a GetDelegates operation.
 */
public final class DelegateInformation {

	/** The delegate user responses. */
	private Collection<DelegateUserResponse> delegateUserResponses;

	/** The meeting reqests delivery scope. */
	private MeetingRequestsDeliveryScope meetingReqestsDeliveryScope;

	/**
	 * * Initializes a DelegateInformation object.
	 * 
	 * @param delegateUserResponses
	 *            the delegate user responses
	 * @param meetingReqestsDeliveryScope
	 *            the meeting reqests delivery scope
	 */
	protected DelegateInformation(
			List<DelegateUserResponse> delegateUserResponses,
			MeetingRequestsDeliveryScope meetingReqestsDeliveryScope) {
		this.delegateUserResponses = new ArrayList<DelegateUserResponse>(
				delegateUserResponses);
		this.meetingReqestsDeliveryScope = meetingReqestsDeliveryScope;
	}

	/**
	 * Gets a list of responses for each of the delegate users concerned by the
	 * operation.
	 * 
	 * @return the delegate user responses
	 */
	public Collection<DelegateUserResponse> getDelegateUserResponses() {
		return delegateUserResponses;
	}

	/**
	 * Gets a value indicating if and how meeting requests are delivered to
	 * delegates.
	 * 
	 * @return the meeting reqests delivery scope
	 */
	public MeetingRequestsDeliveryScope getMeetingReqestsDeliveryScope() {
		return meetingReqestsDeliveryScope;
	}

}
