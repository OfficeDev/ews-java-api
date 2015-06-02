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

package microsoft.exchange.webservices.data.core.enumeration.property;

import microsoft.exchange.webservices.data.attribute.EwsEnum;
import microsoft.exchange.webservices.data.attribute.RequiredServerVersion;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;

/**
 * Defines the type of an EmailAddress object.
 */
public enum MailboxType {

  // Unknown mailbox type (Exchange 2010 or later).
  /**
   * The Unknown.
   */
  @RequiredServerVersion(version = ExchangeVersion.Exchange2010)
  Unknown,

  // The EmailAddress represents a one-off contact (Exchange 2010 or later).
  /**
   * The One off.
   */
  @RequiredServerVersion(version = ExchangeVersion.Exchange2010)
  OneOff,

  // The EmailAddress represents a mailbox.
  /**
   * The Mailbox.
   */
  Mailbox,

  // The EmailAddress represents a public folder.
  /**
   * The Public folder.
   */
  @RequiredServerVersion(version = ExchangeVersion.Exchange2007_SP1)
  PublicFolder,

  // The EmailAddress represents a Public Group.
  /**
   * The Public group.
   */
  @EwsEnum(schemaName = "PublicDL")
  PublicGroup,

  // The EmailAddress represents a Contact Group.
  /**
   * The Contact group.
   */
  @EwsEnum(schemaName = "PrivateDL")
  ContactGroup,

  // The EmailAddress represents a store contact or AD mail contact.
  /**
   * The Contact.
   */
  Contact,

}
