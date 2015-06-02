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

package microsoft.exchange.webservices.data.core.enumeration.property.time;

/**
 * Defines months of the year.
 */
public enum Month {

  // January.
  /**
   * The January.
   */
  January(1),

  // February.
  /**
   * The February.
   */
  February(2),

  // March.
  /**
   * The March.
   */
  March(3),

  // April.
  /**
   * The April.
   */
  April(4),

  // May.
  /**
   * The May.
   */
  May(5),

  // June.
  /**
   * The June.
   */
  June(6),

  // July.
  /**
   * The July.
   */
  July(7),

  // August.
  /**
   * The August.
   */
  August(8),

  // September.
  /**
   * The September.
   */
  September(9),

  // October.
  /**
   * The October.
   */
  October(10),

  // November.
  /**
   * The November.
   */
  November(11),

  // December.
  /**
   * The December.
   */
  December(12);

  /**
   * The month.
   */
  private final int month;

  /**
   * Instantiates a new month.
   *
   * @param month the month
   */
  Month(int month) {
    this.month = month;
  }
}
