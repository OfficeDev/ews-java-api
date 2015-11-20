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

package microsoft.exchange.webservices.data.misc;

import microsoft.exchange.webservices.data.core.exception.misc.ArgumentException;

import java.util.Calendar;
import java.util.Date;

/**
 * Represents time.
 */
public final class Time {

  /**
   * The hours.
   */
  private int hours;

  /**
   * The minutes.
   */
  private int minutes;

  /**
   * The seconds.
   */
  private int seconds;

  /**
   * Initializes a new instance of Time.
   */
  protected Time() {
  }

  /**
   * Initializes a new instance of Time.
   *
   * @param minutes The number of minutes since 12:00AM.
   * @throws ArgumentException the argument exception
   */

  protected Time(int minutes) throws ArgumentException {
    this();
    if (minutes < 0 || minutes >= 1440) {
      throw new ArgumentException(String.format("%s,%s", "minutes must be between 0 and 1439, inclusive.", "minutes"));
    }

    this.hours = minutes / 60;
    this.minutes = minutes % 60;
    this.seconds = 0;
  }

  /**
   * Initializes a new instance of Time.
   *
   * @param dateTime the date time
   * @throws ArgumentException the argument exception
   */
  public Time(Date dateTime) throws ArgumentException {
    if (dateTime != null) {
      Calendar cal = Calendar.getInstance();
      cal.setTime(dateTime);
      this.setHours(cal.get(Calendar.HOUR));
      this.setMinutes(cal.get(Calendar.MINUTE));
      this.setSeconds(cal.get(Calendar.SECOND));
    }
  }

  /**
   * Initializes a new instance of Time.
   *
   * @param hours   the hours
   * @param minutes the minutes
   * @param seconds the seconds
   */
  protected Time(int hours, int minutes, int seconds) {
    this();
    this.hours = hours;
    this.minutes = minutes;
    this.seconds = seconds;
  }

  /**
   * Convert Time to XML Schema time.
   *
   * @return String in XML Schema time format
   */

  public String toXSTime() {
    return String.format("%s,%s,%s,%s", "{0:00}:{1:00}:{2:00}",
        this.getHours(), this
            .getMinutes(), this.getSeconds());
  }

  /**
   * Converts the time into a number of minutes since 12:00AM.
   *
   * @return The number of minutes since 12:00AM the time represents.
   */

  protected int convertToMinutes() {
    return this.getMinutes() + (this.getHours() * 60);
  }

  /**
   * Gets  the hours.
   *
   * @return the hours
   */
  protected int getHours() {
    return this.hours;
  }

  /**
   * sets the hours.
   *
   * @param value the new hours
   * @throws ArgumentException the argument exception
   */

  protected void setHours(int value) throws ArgumentException {
    if (value >= 0 && value < 24) {
      this.hours = value;
    } else {
      throw new ArgumentException("Hour must be between 0 and 23.");
    }
  }

  /**
   * Gets the minutes.
   *
   * @return the minutes
   */
  protected int getMinutes() {
    return this.minutes;
  }

  /**
   * Sets the minutes.
   *
   * @param value the new minutes
   * @throws ArgumentException the argument exception
   */
  protected void setMinutes(int value) throws ArgumentException {
    if (value >= 0 && value < 60) {
      this.minutes = value;
    } else {
      throw new ArgumentException("Minute must be between 0 and 59.");
    }
  }

  /**
   * Gets the seconds.
   *
   * @return the seconds
   */
  protected int getSeconds() {
    return this.seconds;
  }

  /**
   * Sets the seconds.
   *
   * @param value the new seconds
   * @throws ArgumentException the argument exception
   */
  protected void setSeconds(int value) throws ArgumentException {
    if (value >= 0 && value < 60) {
      this.seconds = value;
    } else {
      throw new ArgumentException("Second must be between 0 and 59.");
    }
  }
}
