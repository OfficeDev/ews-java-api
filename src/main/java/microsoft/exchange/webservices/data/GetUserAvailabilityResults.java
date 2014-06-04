/**************************************************************************
 * copyright file="GetUserAvailabilityResults.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the GetUserAvailabilityResults.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.Collection;

/**
 * Represents the results of a GetUserAvailability operation.
 * 
 */
public final class GetUserAvailabilityResults {

	/** The attendees availability. */
	private ServiceResponseCollection<AttendeeAvailability>
			attendeesAvailability;

	/** The suggestions response. */
	private SuggestionsResponse suggestionsResponse;

	/**
	 * Initializes a new instance of the GetUserAvailabilityResults class.
	 */
	protected GetUserAvailabilityResults() {
	}

	/**
	 * Gets  the suggestions response for the requested meeting time.
	 * 
	 * @return the suggestions response
	 */
	protected SuggestionsResponse getSuggestionsResponse() {
		return this.suggestionsResponse;
	}

	/**
	 * Sets the suggestions response.
	 * 
	 * @param value
	 *            the new suggestions response
	 */
	protected void setSuggestionsResponse(SuggestionsResponse value) {
		this.suggestionsResponse = value;
	}

	/**
	 * Gets a collection of AttendeeAvailability objects representing
	 * availability information for each of the specified attendees.
	 * 
	 * @return the attendees availability
	 */
	public ServiceResponseCollection<AttendeeAvailability> 
			getAttendeesAvailability() {
		return this.attendeesAvailability;
	}

	/**
	 * Sets the attendees availability.
	 * 
	 * @param value
	 *            the new attendees availability
	 */
	protected void setAttendeesAvailability(
			ServiceResponseCollection<AttendeeAvailability> value) {
		this.attendeesAvailability = value;
	}

	/**
	 * Gets a collection of suggested meeting times for the specified time
	 * period.
	 * 
	 * @return the suggestions
	 * @throws microsoft.exchange.webservices.data.ServiceResponseException
	 *             the service response exception
	 */
	public Collection<Suggestion> getSuggestions()
			throws ServiceResponseException {
		if (this.suggestionsResponse == null) {
			return null;
		} else {
			this.suggestionsResponse.throwIfNecessary();

			return this.suggestionsResponse.getSuggestions();
		}

	}
}
