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
 * Defines the legacy free/busy status associated with an appointment.
 */
public enum LegacyFreeBusyStatus {

  // The time slot associated with the appointment appears as free.
  /**
   * The Free.
   */
  Free(0),

  // The time slot associated with the appointment appears as tentative.
  /**
   * The Tentative.
   */
  Tentative(1),

  // The time slot associated with the appointment appears as busy.
  /**
   * The Busy.
   */
  Busy(2),

  // The time slot associated with the appointment appears as Out of Office.
  /**
   * The OOF.
   */
  OOF(3),

  // No free/busy status is associated with the appointment.
  /**
   * The No data.
   */
  NoData(4);

  /**
   * The busy status.
   */
  private final int busyStatus;

  /**
   * Instantiates a new legacy free busy status.
   *
   * @param busyStatus the busy status
   */
  LegacyFreeBusyStatus(int busyStatus) {
    this.busyStatus = busyStatus;
  }

  public int getBusyStatus() {
    return busyStatus;
  }

}
