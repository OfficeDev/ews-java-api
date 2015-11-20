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


import microsoft.exchange.webservices.data.attribute.Flags;

/**
 * Defines the response actions that can be taken on an item.
 */
@Flags
public enum ResponseActions {

  // No action can be taken.
  /**
   * The None.
   */
  None(0),

  // The item can be accepted.
  /**
   * The Accept.
   */
  Accept(1),

  // The item can be tentatively accepted.
  /**
   * The Tentatively accept.
   */
  TentativelyAccept(2),

  // The item can be declined.
  /**
   * The Decline.
   */
  Decline(4),

  // The item can be replied to.

  /**
   * The Reply.
   */
  Reply(8),

  // The item can be replied to.
  /**
   * The Reply all.
   */
  ReplyAll(16),

  // The item can be forwarded.
  /**
   * The Forward.
   */
  Forward(32),

  // The item can be cancelled.
  /**
   * The Cancel.
   */
  Cancel(64),

  // The item can be removed from the calendar.
  /**
   * The Remove from calendar.
   */
  RemoveFromCalendar(128),

  // The item's read receipt can be suppressed.
  /**
   * The Suppress read receipt.
   */
  SuppressReadReceipt(256),

  // A reply to the item can be posted.
  /**
   * The Post reply.
   */
  PostReply(512);

  /**
   * The response act.
   */
  private final int responseAct;

  /**
   * Instantiates a new response actions.
   *
   * @param responseAct the response act
   */
  ResponseActions(int responseAct) {
    this.responseAct = responseAct;
  }
}
