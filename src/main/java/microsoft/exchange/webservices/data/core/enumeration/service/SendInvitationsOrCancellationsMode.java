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

package microsoft.exchange.webservices.data.core.enumeration.service;

/**
 * Defines if/how meeting invitations or cancellations should be sent to
 * attendees when an appointment is updated.
 */
public enum SendInvitationsOrCancellationsMode {

  // No meeting invitation/cancellation is sent.
  /**
   * The Send to none.
   */
  SendToNone,

  // Meeting invitations/cancellations are sent to all attendees.
  /**
   * The Send only to all.
   */
  SendOnlyToAll,

  // Meeting invitations/cancellations are sent only to attendees that have
  // been added or modified.
  /**
   * The Send only to changed.
   */
  SendOnlyToChanged,

  // Meeting invitations/cancellations are sent to all attendees and a copy is
  // saved in the organizer's Sent Items folder.
  /**
   * The Send to all and save copy.
   */
  SendToAllAndSaveCopy,

  // Meeting invitations/cancellations are sent only to attendees that have
  // been added or modified and a copy is saved in the organizer's Sent Items
  // folder.
  /**
   * The Send to changed and save copy.
   */
  SendToChangedAndSaveCopy

}
