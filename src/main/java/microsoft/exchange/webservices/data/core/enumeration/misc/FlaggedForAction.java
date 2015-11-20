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

package microsoft.exchange.webservices.data.core.enumeration.misc;

/**
 * Defines the follow-up actions that may be stamped on a message.
 */
public enum FlaggedForAction {

  /**
   * The message is flagged with any action.
   */
  Any,

  /**
   * The recipient is requested to call the sender.
   */
  Call,

  /**
   * The recipient is requested not to forward the message.
   */
  DoNotForward,

  /**
   * The recipient is requested to follow up on the message.
   */
  FollowUp,

  /**
   * The recipient received the message for information.
   */
  FYI,

  /**
   * The recipient is requested to forward the message.
   */
  Forward,

  /**
   * The recipient is informed that a response to the message is not required.
   */
  NoResponseNecessary,

  /**
   * The recipient is requested to read the message.
   */
  Read,

  /**
   * The recipient is requested to reply to the sender of the message.
   */
  Reply,

  /**
   * The recipient is requested to reply to everyone the message was sent to.
   */
  ReplyToAll,

  /**
   * The recipient is requested to review the message.
   */
  Review

}
