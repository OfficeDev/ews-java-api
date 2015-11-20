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

/**
 * Defines the conflict types that can be returned in meeting time suggestions.
 */
public enum ConflictType {

  // There is a conflict with an indicidual attendee.
  /**
   * The Individual attendee conflict.
   */
  IndividualAttendeeConflict,

  // There is a conflict with at least one member of a group.
  /**
   * The Group conflict.
   */
  GroupConflict,

  // There is a conflict with at least one member of a group, but the group
  // was too big for detailed information to be returned.
  /**
   * The Group too big conflict.
   */
  GroupTooBigConflict,

  // There is a conflict with an unresolvable attendee or an attendee that is
  // not a user, group, or contact.
  /**
   * The Unknown attendee conflict.
   */
  UnknownAttendeeConflict

}
