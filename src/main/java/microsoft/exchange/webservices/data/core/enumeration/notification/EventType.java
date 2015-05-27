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

package microsoft.exchange.webservices.data.core.enumeration.notification;

import microsoft.exchange.webservices.data.attribute.EwsEnum;
import microsoft.exchange.webservices.data.attribute.RequiredServerVersion;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;

/**
 * Defines the types of event that can occur in a folder.
 */
public enum EventType {
  // This event is sent to a client application by push notification to
  // indicate that
  // the subscription is still alive.
  /**
   * The Status.
   */
  @EwsEnum(schemaName = "StatusEvent")
  Status,

  // This event indicates that a new e-mail message was received.
  /**
   * The New mail.
   */
  @EwsEnum(schemaName = "NewMailEvent")
  NewMail,

  // This event indicates that an item or folder has been deleted.
  /**
   * The Deleted.
   */
  @EwsEnum(schemaName = "DeletedEvent")
  Deleted,

  // This event indicates that an item or folder has been modified.
  /**
   * The Modified.
   */
  @EwsEnum(schemaName = "ModifiedEvent")
  Modified,

  // This event indicates that an item or folder has been moved to another
  // folder.
  /**
   * The Moved.
   */
  @EwsEnum(schemaName = "MovedEvent")
  Moved,

  // This event indicates that an item or folder has been copied to another
  // folder.
  /**
   * The Copied.
   */
  @EwsEnum(schemaName = "CopiedEvent")
  Copied,

  // This event indicates that a new item or folder has been created.
  /**
   * The Created.
   */
  @EwsEnum(schemaName = "CreatedEvent")
  Created,

  @RequiredServerVersion(version = ExchangeVersion.Exchange2010_SP1)
  @EwsEnum(schemaName = "FreeBusyChangedEvent")
  FreeBusyChanged

}
