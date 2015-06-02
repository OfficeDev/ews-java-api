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

package microsoft.exchange.webservices.data.core.service.item;

import microsoft.exchange.webservices.data.core.service.response.AcceptMeetingInvitationMessage;
import microsoft.exchange.webservices.data.core.service.response.DeclineMeetingInvitationMessage;
import microsoft.exchange.webservices.data.misc.CalendarActionResults;

/**
 * Interface defintion of a group of methods that are common to item that
 * return CalendarActionResults.
 */
public interface ICalendarActionProvider {

  /**
   * Implements the Accept method.
   *
   * @param sendResponse Indicates whether to send a response to the organizer.
   * @return A CalendarActionResults object containing the various item that
   * were created or modified as a result of this operation.
   * @throws Exception the exception
   */
  CalendarActionResults accept(boolean sendResponse) throws Exception;

  /**
   * Implements the AcceptTentatively method.
   *
   * @param sendResponse Indicates whether to send a response to the organizer.
   * @return A CalendarActionResults object containing the various item that
   * were created or modified as a result of this operation.
   * @throws Exception the exception
   */
  CalendarActionResults acceptTentatively(boolean sendResponse)
      throws Exception;

  /**
   * Implements the Decline method.
   *
   * @param sendResponse Indicates whether to send a response to the organizer.
   * @return A CalendarActionResults object containing the various item that
   * were created or modified as a result of this operation.
   * @throws Exception the exception
   */
  CalendarActionResults decline(boolean sendResponse) throws Exception;

  /**
   * Implements the CreateAcceptMessage method.
   *
   * @param tentative Indicates whether the new AcceptMeetingInvitationMessage
   *                  should represent a Tentative accept response (as opposed to an
   *                  Accept response).
   * @return A new AcceptMeetingInvitationMessage.
   * @throws Exception the exception
   */
  AcceptMeetingInvitationMessage createAcceptMessage(boolean tentative)
      throws Exception;

  /**
   * Implements the DeclineMeetingInvitationMessage method.
   *
   * @return A new DeclineMeetingInvitationMessage.
   * @throws Exception the exception
   */
  DeclineMeetingInvitationMessage createDeclineMessage() throws Exception;

}
