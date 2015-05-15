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

import microsoft.exchange.webservices.data.core.exception.misc.FormatException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * The Class TimeSpan.
 */
public class TimeSpan implements Comparable<TimeSpan>, java.io.Serializable, Cloneable {

  private static final Log LOG = LogFactory.getLog(TimeSpan.class);

  /**
   * Constant serialized ID used for compatibility.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The time.
   */
  private long time = 0;

  /**
   * Constant for milliseconds unit and conversion.
   */
  public static final int MILLISECONDS = 1;

  /**
   * Constant for seconds unit and conversion.
   */
  public static final int SECONDS = MILLISECONDS * 1000;

  /**
   * Constant for minutes unit and conversion.
   */
  public static final int MINUTES = SECONDS * 60;

  /**
   * Constant for hours unit and conversion.
   */
  public static final int HOURS = MINUTES * 60;

  /**
   * Constant for days unit and conversion.
   */
  public static final int DAYS = HOURS * 24;

  /**
   * Represents the Maximum TimeSpan value.
   */
  public static final TimeSpan MAX_VALUE = new TimeSpan(Long.MAX_VALUE);

  /**
   * Represents the Minimum TimeSpan value.
   */
  public static final TimeSpan MIN_VALUE = new TimeSpan(Long.MIN_VALUE);

  /**
   * Represents the TimeSpan with a value of zero.
   */
  public static final TimeSpan ZERO = new TimeSpan(0L);

  /**
   * Creates a new instance of TimeSpan based on the number of milliseconds
   * entered.
   *
   * @param time the number of milliseconds for this TimeSpan.
   */
  public TimeSpan(long time) {
    this.time = time;
  }

  /**
   * Creates a new TimeSpan object based on the unit and value entered.
   *
   * @param units the type of unit to use to create a TimeSpan instance.
   * @param value the number of units to use to create a TimeSpan instance.
   */
  public TimeSpan(int units, long value) {
    this.time = TimeSpan.toMilliseconds(units, value);
  }

	/*
         * public static TimeSpan fromMinutes(int value) { int l = value*60*100;
	 * return l; }
	 */

  /**
   * Subtracts two Date objects creating a new TimeSpan object.
   *
   * @param date1 Date to use as the base value.
   * @param date2 Date to subtract from the base value.
   * @return a TimeSpan object representing the difference bewteen the two
   * Date objects.
   */
  public static TimeSpan subtract(java.util.Date date1,
      java.util.Date date2) {
    return new TimeSpan(date1.getTime() - date2.getTime());
  }

  /**
   * Compares this object with the specified object for order. Returns a
   * negative integer, zero, or a positive integer as this object is less
   * than, equal to, or greater than the specified object. Comparison is based
   * on the number of milliseconds in this TimeSpan.
   *
   * @param o the Object to be compared.
   * @return a negative integer, zero, or a positive integer as this object is
   * less than, equal to, or greater than the specified object.
   */
  public int compareTo(TimeSpan o) {
    TimeSpan compare = (TimeSpan) o;
    if (this.time == compare.time) {
      return 0;
    }
    if (this.time > compare.time) {
      return +1;
    }
    return -1;
  }

  /**
   * Indicates whether some other object is "equal to" this one. Comparison is
   * based on the number of milliseconds in this TimeSpan.
   *
   * @param obj the reference object with which to compare.
   * @return if the obj argument is a TimeSpan object with the exact same
   * number of milliseconds. otherwise.
   */
  public boolean equals(Object obj) {
    if (obj instanceof TimeSpan) {
      TimeSpan compare = (TimeSpan) obj;
      if (this.time == compare.time) {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns a hash code value for the object. This method is supported for
   * the benefit of hashtables such as those provided by
   * <code>java.util.Hashtable</code>. The method uses the same algorithm as
   * found in the Long class.
   *
   * @return a hash code value for this object.
   * @see Object#equals(Object)
   * @see java.util.Hashtable
   */
  public int hashCode() {
    return Long.valueOf(this.time).hashCode();
  }

  /**
   * Returns a string representation of the object in the format.
   * "[-]d.hh:mm:ss.ff" where "-" is an optional sign for negative TimeSpan
   * values, the "d" component is days, "hh" is hours, "mm" is minutes, "ss"
   * is seconds, and "ff" is milliseconds
   *
   * @return a string containing the number of milliseconds.
   */
  public String toString() {
    StringBuffer sb = new StringBuffer();
    long millis = this.time;
    if (millis < 0) {
      sb.append("-");
      millis = -millis;
    }

    long day = millis / TimeSpan.DAYS;

    if (day != 0) {
      sb.append(day);
      sb.append("d.");
      millis = millis % TimeSpan.DAYS;
    }

    sb.append(millis / TimeSpan.HOURS);
    millis = millis % TimeSpan.HOURS;
    sb.append("h:");
    sb.append(millis / TimeSpan.MINUTES);
    millis = millis % TimeSpan.MINUTES;
    sb.append("m:");
    sb.append(millis / TimeSpan.SECONDS);
    sb.append("s");
    millis = millis % TimeSpan.SECONDS;
    if (millis != 0) {
      sb.append(".");
      sb.append(millis);
      sb.append("ms");
    }
    return sb.toString();
  }

  /**
   * Returns a clone of this TimeSpan.
   *
   * @return a clone of this TimeSpan.
   */
  public Object clone() {
    try {
      return super.clone();
    } catch (CloneNotSupportedException e) {
      LOG.error(e);
      throw new InternalError();
    }
  }

  /**
   * Indicates whether the value of the TimeSpan is positive.
   *
   * @return if the value of the TimeSpan is greater than
   * zero.  otherwise.
   */
  public boolean isPositive() {
    return this.compareTo(TimeSpan.ZERO) > 0 ? true : false;
  }

  /**
   * Indicates whether the value of the TimeSpan is negative.
   *
   * @return if the value of the TimeSpan is less than zero.
   * otherwise.
   */
  public boolean isNegative() {
    return this.compareTo(TimeSpan.ZERO) < 0 ? true : false;
  }

  /**
   * Indicates whether the value of the TimeSpan is zero.
   *
   * @return if the value of the TimeSpan is equal to zero.
   * otherwise.
   */
  public boolean isZero() {
    return this.equals(TimeSpan.ZERO);
  }

  /**
   * Gets the number of milliseconds.
   *
   * @return the number of milliseconds.
   */
  public long getMilliseconds() {
    return (((this.time % TimeSpan.HOURS) % TimeSpan.MINUTES) % TimeSpan.MILLISECONDS)
        / TimeSpan.MILLISECONDS;
  }

  /**
   * Gets the number of milliseconds.
   *
   * @return the number of milliseconds.
   */
  public long getTotalMilliseconds() {
    return this.time;
  }

  /**
   * Gets the number of seconds (truncated).
   *
   * @return the number of seconds.
   */
  public long getSeconds() {
    return ((this.time % TimeSpan.HOURS) % TimeSpan.MINUTES) / TimeSpan.SECONDS;
  }

  /**
   * Gets the number of seconds including fractional seconds.
   *
   * @return the number of seconds.
   */
  public double getTotalSeconds() {
    return this.time / 1000.0d;
  }

  /**
   * Gets the number of minutes (truncated).
   *
   * @return the number of minutes.
   */
  public long getMinutes() {
    return (this.time % TimeSpan.HOURS) / TimeSpan.MINUTES;// (this.time/1000)/60;
  }

  /**
   * Gets the number of minutes including fractional minutes.
   *
   * @return the number of minutes.
   */
  public double getTotalMinutes() {
    return (this.time / 1000.0d) / 60.0d;
  }

  /**
   * Gets the number of hours (truncated).
   *
   * @return the number of hours.
   */
  public long getHours() {
    return ((this.time / 1000) / 60) / 60;
  }

  /**
   * Gets the number of hours including fractional hours.
   *
   * @return the number of hours.
   */
  public double getTotalHours() {
    return ((this.time / 1000.0d) / 60.0d) / 60.0d;
  }

  /**
   * Gets the number of days (truncated).
   *
   * @return the number of days.
   */
  public long getDays() {
    return (((this.time / 1000) / 60) / 60) / 24;
  }

  /**
   * Gets the number of days including fractional days.
   *
   * @return the number of days.
   */
  public double getTotalDays() {
    return (((this.time / 1000.0d) / 60.0d) / 60.0d) / 24.0d;
  }

  /**
   * Adds a TimeSpan to this TimeSpan.
   *
   * @param timespan the TimeSpan to add to this TimeSpan.
   */
  public void add(TimeSpan timespan) {
    add(TimeSpan.MILLISECONDS, timespan.time);
  }

  /**
   * Adds a number of units to this TimeSpan.
   *
   * @param units the type of unit to add to this TimeSpan.
   * @param value the number of units to add to this TimeSpan.
   */
  public void add(int units, long value) {
    this.time += TimeSpan.toMilliseconds(units, value);
  }

  /**
   * Compares two TimeSpan objects.
   *
   * @param first  first TimeSpan to use in the compare.
   * @param second second TimeSpan to use in the compare.
   * @return a negative integer, zero, or a positive integer as the first
   * TimeSpan is less than, equal to, or greater than the second
   * TimeSpan.
   */
  public static int compare(TimeSpan first, TimeSpan second) {
    if (first.time == second.time) {
      return 0;
    }
    if (first.time > second.time) {
      return +1;
    }
    return -1;
  }

  /**
   * Returns a TimeSpan whose value is the absolute value of this TimeSpan.
   *
   * @return a TimeSpan whose value is the absolute value of this TimeSpan.
   */
  public TimeSpan duration() {
    return new TimeSpan(Math.abs(this.time));
  }

  /**
   * Returns a TimeSpan whose value is the negated value of this TimeSpan.
   *
   * @return a TimeSpan whose value is the negated value of this TimeSpan.
   */
  public TimeSpan negate() {
    return new TimeSpan(-this.time);
  }

  /**
   * Subtracts a TimeSpan from this TimeSpan.
   *
   * @param timespan the TimeSpan to subtract from this TimeSpan.
   */
  public void subtract(TimeSpan timespan) {
    subtract(TimeSpan.MILLISECONDS, timespan.time);
  }

  /**
   * Subtracts a number of units from this TimeSpan.
   *
   * @param units the type of unit to subtract from this TimeSpan.
   * @param value the number of units to subtract from this TimeSpan.
   */
  public void subtract(int units, long value) {
    add(units, -value);
  }

  /**
   * To milliseconds.
   *
   * @param units the units
   * @param value the value
   * @return the long
   */
  private static long toMilliseconds(int units, long value) {
    long millis;
    switch (units) {
      case TimeSpan.MILLISECONDS:
      case TimeSpan.SECONDS:
      case TimeSpan.MINUTES:
      case TimeSpan.HOURS:
      case TimeSpan.DAYS:
        millis = value * units;
        break;
      default:
        throw new IllegalArgumentException("Unrecognized units: " + units);
    }
    return millis;
  }

  public static TimeSpan parse(String s) throws Exception {
    String str = s.trim();
    String[] st1 = str.split("\\.");
    int days = 0, millsec = 0, totMillSec = 0;
    String data = str;
    switch (st1.length) {
      case 1:
        data = str;
        break;
      case 2:
        if (st1[0].split(":").length > 1) {
          millsec = Integer.parseInt(st1[1]);
          data = st1[0];
        } else {
          days = Integer.parseInt(st1[0]);
          data = st1[1];
        }
        break;
      case 3:
        days = Integer.parseInt(st1[0]);
        data = st1[1];
        millsec = Integer.parseInt(st1[2]);
        break;
      default:
        throw new FormatException("Bad Format");

    }
    String[] st = data.split(":");
    switch (st.length) {
      case 1:
        totMillSec = Integer.parseInt(str) * 24 * 60 * 60 * 1000;
        break;
      case 2:
        totMillSec = (Integer.parseInt(st[0]) * 60 * 60 * 1000) + (Integer.parseInt(st[1]) * 60 * 1000);
        break;
      case 3:
        totMillSec = (Integer.parseInt(st[0]) * 60 * 60 * 1000) + (Integer.parseInt(st[1]) * 60 * 1000) + (
            Integer.parseInt(st[2]) * 1000);
        break;
      case 4:
        totMillSec =
            (Integer.parseInt(st[0]) * 24 * 60 * 60 * 1000) + (Integer.parseInt(st[1]) * 60 * 60 * 1000) + (
                Integer.parseInt(st[2]) * 60 * 1000) + (Integer.parseInt(st[3]) * 1000);
        break;
      default:
        throw new FormatException("Bad Format/Overflow");
    }
    totMillSec += (days * 24 * 60 * 60 * 1000) + millsec;
    return new TimeSpan(totMillSec);
  }

}
