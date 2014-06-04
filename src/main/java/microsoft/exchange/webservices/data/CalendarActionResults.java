/**************************************************************************
 * copyright file="CalendarActionResults.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the CalendarActionResults.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents the results of an action performed on a calendar item or meeting
 * message, such as accepting, tentatively accepting or declining a meeting
 * request.
 * 
 * 
 */
public final class CalendarActionResults {

	/** The appointment. */
	private Appointment appointment;

	/** The meeting request. */
	private MeetingRequest meetingRequest;

	/** The meeting response. */
	private MeetingResponse meetingResponse;

	/** The meeting cancellation. */
	private MeetingCancellation meetingCancellation;

	/**
	 * Initializes a new instance of the class.
	 * 
	 * @param items
	 *            the items
	 */
	CalendarActionResults(Iterable<Item> items) {
		this.appointment = EwsUtilities.findFirstItemOfType(Appointment.class,
				items);
		this.meetingRequest = EwsUtilities.findFirstItemOfType(
				MeetingRequest.class, items);
		this.meetingResponse = EwsUtilities.findFirstItemOfType(
				MeetingResponse.class, items);
		this.meetingCancellation = EwsUtilities.findFirstItemOfType(
				MeetingCancellation.class, items);
	}

	/**
	 * Gets the meeting that was accepted, tentatively accepted or declined.
	 * 
	 * When a meeting is accepted or tentatively accepted via an Appointment
	 * object, EWS recreates the meeting, and Appointment represents that new
	 * version. When a meeting is accepted or tentatively accepted via a
	 * MeetingRequest object, EWS creates an associated meeting in the
	 * attendee's calendar and Appointment represents that meeting. When
	 * declining a meeting via an Appointment object, EWS moves the appointment
	 * to the attendee's Deleted Items folder and Appointment represents that
	 * moved copy. When declining a meeting via a MeetingRequest object, EWS
	 * creates an associated meeting in the attendee's Deleted Items folder, and
	 * Appointment represents that meeting. When a meeting is declined via
	 * either an Appointment or a MeetingRequest object from the Deleted Items
	 * folder, Appointment is null.
	 * 
	 * @return appointment
	 */
	public Appointment getAppointment() {
		return this.appointment;
	}

	/**
	 * Gets the meeting request that was moved to the Deleted Items folder as a
	 * result of an attendee accepting, tentatively accepting or declining a
	 * meeting request. If the meeting request is accepted, tentatively accepted
	 * or declined from the Deleted Items folder, it is permanently deleted and
	 * MeetingRequest is null.
	 * 
	 * @return meetingRequest
	 */
	public MeetingRequest getMeetingRequest() {
		return this.meetingRequest;
	}

	/**
	 * Gets the copy of the response that is sent to the organizer of a meeting
	 * when the meeting is accepted, tentatively accepted or declined by an
	 * attendee. MeetingResponse is null if the attendee chose not to send a
	 * response.
	 * 
	 * @return meetingResponse
	 */
	public MeetingResponse getMeetingResponse() {
		return this.meetingResponse;
	}

	/**
	 * Gets the copy of the meeting cancellation message sent by the organizer
	 * to the attendees of a meeting when the meeting is cancelled.
	 * 
	 * @return meetingCancellation
	 */
	public MeetingCancellation getMeetingCancellation() {
		return this.meetingCancellation;
	}

}