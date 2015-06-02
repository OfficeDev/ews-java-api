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

import microsoft.exchange.webservices.data.attribute.RequiredServerVersion;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;

/**
 * Defines how meeting request are sent to delegates.
 */
public enum MeetingRequestsDeliveryScope {

  // Meeting request are sent to delegates only.
  /**
   * The Delegates only.
   */
  DelegatesOnly,

  // Meeting request are sent to delegates and to the owner of the mailbox.
  /**
   * The Delegates and me.
   */
  DelegatesAndMe,

  // Meeting request are sent to delegates and informational messages are
  // sent to the owner of the mailbox.
  /**
   * The Delegates and send information to me.
   */
  DelegatesAndSendInformationToMe,

  //Meeting request are not sent to delegates.  This value is
  //supported only for Exchange 2010 SP1 or later
  //server versions.
  @RequiredServerVersion(version = ExchangeVersion.Exchange2010_SP1)
  NoForward
}
