/**************************************************************************
 Exchange Web Services Java API
 Copyright (c) Microsoft Corporation
 All rights reserved.
 MIT License
 Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the ""Software""), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 THE SOFTWARE IS PROVIDED *AS IS*, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 **************************************************************************/

package microsoft.exchange.webservices.data;


/**
 * Specifies the day of the week. For the standard days of the week (Sunday,
 * Monday...) the DayOfTheWeek enum value is the same as the System.DayOfWeek
 * enum type. These values can be safely cast between the two enum types. The
 * special days of the week (Day, Weekday and WeekendDay) are used for monthly
 * and yearly recurrences and cannot be cast to System.DayOfWeek values.
 */
public enum DayOfTheWeek {

  Sunday,
  Monday,
  Tuesday,
  Wednesday,
  Thursday,
  Friday,
  Saturday,

  // Any day of the week
  /**
   * The Day.
   */
  Day,

  // Any day of the usual business week (Monday-Friday)
  /**
   * The Weekday.
   */
  Weekday,

  // Any weekend day (Saturday or Sunday)
  /**
   * The Weekend day.
   */
  WeekendDay;

}
