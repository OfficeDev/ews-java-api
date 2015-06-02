/*
 * The MIT License
 * Copyright (c) 2012 Microsoft Corporation
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package microsoft.exchange.webservices.data.misc;

import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.service.item.Appointment;
import microsoft.exchange.webservices.data.core.service.item.Item;
import microsoft.exchange.webservices.data.core.service.item.MeetingCancellation;
import microsoft.exchange.webservices.data.core.service.item.MeetingRequest;
import microsoft.exchange.webservices.data.core.service.item.MeetingResponse;

/**
 * Represents the results of an action performed on a calendar item or meeting
 * message, such as accepting, tentatively accepting or declining a meeting
 * request.
 */
public final class CalendarActionResults {

  /**
   * The appointment.
   */
  private Appointment appointment;

  /**
   * The meeting request.
   */
  private MeetingRequest meetingRequest;

  /**
   * The meeting response.
   */
  private MeetingResponse meetingResponse;

  /**
   * The meeting cancellation.
   */
  private MeetingCancellation meetingCancellation;

  /**
   * Initializes a new instance of the class.
   *
   * @param items the item
   */
  public CalendarActionResults(Iterable<Item> items) {
    this.appointment = EwsUtilities.findFirstItemOfType(Appointment.class, items);
    this.meetingRequest = EwsUtilities.findFirstItemOfType(
        MeetingRequest.class, items);
    this.meetingResponse = EwsUtilities.findFirstItemOfType(
        MeetingResponse.class, items);
    this.meetingCancellation = EwsUtilities.findFirstItemOfType(
        MeetingCancellation.class, items);
  }

  /**
   * Gets the meeting that was accepted, tentatively accepted or declined.
   * <p/>
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
