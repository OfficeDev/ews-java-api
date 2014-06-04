/**************************************************************************
 * copyright file="ICalendarActionProvider.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ICalendarActionProvider.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Interface defintion of a group of methods that are common to items that
 * return CalendarActionResults.
 */
interface ICalendarActionProvider {

	/**
	 * Implements the Accept method.
	 * 
	 * @param sendResponse
	 *            Indicates whether to send a response to the organizer.
	 * @return A CalendarActionResults object containing the various items that
	 *         were created or modified as a result of this operation.
	 * @throws Exception
	 *             the exception
	 */
	CalendarActionResults accept(boolean sendResponse) throws Exception;

	/**
	 * Implements the AcceptTentatively method.
	 * 
	 * @param sendResponse
	 *            Indicates whether to send a response to the organizer.
	 * @return A CalendarActionResults object containing the various items that
	 *         were created or modified as a result of this operation.
	 * @throws Exception
	 *             the exception
	 */
	CalendarActionResults acceptTentatively(boolean sendResponse)
			throws Exception;

	/**
	 * Implements the Decline method.
	 * 
	 * @param sendResponse
	 *            Indicates whether to send a response to the organizer.
	 * @return A CalendarActionResults object containing the various items that
	 *         were created or modified as a result of this operation.
	 * @throws Exception
	 *             the exception
	 */
	CalendarActionResults decline(boolean sendResponse) throws Exception;

	/**
	 * Implements the CreateAcceptMessage method.
	 * 
	 * @param tentative
	 *            Indicates whether the new AcceptMeetingInvitationMessage
	 *            should represent a Tentative accept response (as opposed to an
	 *            Accept response).
	 * @return A new AcceptMeetingInvitationMessage.
	 * @throws Exception
	 *             the exception
	 */
	AcceptMeetingInvitationMessage createAcceptMessage(boolean tentative)
			throws Exception;

	/**
	 * Implements the DeclineMeetingInvitationMessage method.
	 * 
	 * @return A new DeclineMeetingInvitationMessage.
	 * @throws Exception
	 *             the exception
	 */
	DeclineMeetingInvitationMessage createDeclineMessage() throws Exception;

}
