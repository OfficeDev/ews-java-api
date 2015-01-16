/**************************************************************************
 Exchange Web Services Java API
 Copyright (c) Microsoft Corporation
 All rights reserved.
 MIT License
 Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the ""Software""), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 THE SOFTWARE IS PROVIDED *AS IS*, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 **************************************************************************/

package microsoft.exchange.webservices.data.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateTimeParser {

  private final SimpleDateFormat[] dateTimeFormats = {
      new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX"),
      new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX"),
      new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"),
      new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS"),
      new SimpleDateFormat("yyyy-MM-ddX"),
      new SimpleDateFormat("yyyy-MM-dd")
  };

  private final SimpleDateFormat[] dateFormats = {
      new SimpleDateFormat("yyyy-MM-ddX"),
      new SimpleDateFormat("yyyy-MM-dd")
  };


  public DateTimeParser() {
    // Set default timezone of the formats to UTC, which will be used when the date string doesn't supply a
    // timezone itself.

    for (SimpleDateFormat format : dateTimeFormats) {
      format.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    for (SimpleDateFormat format : dateFormats) {
      format.setTimeZone(TimeZone.getTimeZone("UTC"));
    }
  }

  /**
   * Converts a date time string to local date time.
   *
   * Note: this method also allows dates without times, in which case the time will be 00:00:00 in the
   * supplied timezone. UTC timezone will be assumed if no timezone is supplied.
   *
   * @param value The string value to parse.
   * @return The parsed {@link Date}.
   *
   * @throws java.lang.IllegalArgumentException If string can not be parsed.
   */
  public Date convertDateTimeStringToDate(String value) {
    return parseInternal(value, false);
  }

  /**
   * Converts a date string to local date time.
   *
   * UTC timezone will be assumed if no timezone is supplied.
   *
   * @param value The string value to parse.
   * @return The parsed {@link Date}.
   *
   * @throws java.lang.IllegalArgumentException If string can not be parsed.
   */
  public Date convertDateStringToDate(String value) {
    return parseInternal(value, true);
  }

  private Date parseInternal(String value, boolean dateOnly) {
    String originalValue = value;

    if (value == null || value.isEmpty()) {
      return null;
    } else {
      if (value.endsWith("z")) {
        // This seems to be an edge case. Let's uppercase the Z to be sure.
        value = value.substring(0, value.length() - 1) + "Z";
      }

      SimpleDateFormat[] formats = dateOnly ? dateFormats : dateTimeFormats;
      for (SimpleDateFormat format : formats) {
        try {
          return format.parse(value);
        } catch (ParseException e) {
          // Ignore and try the next pattern.
        }
      }
    }

    throw new IllegalArgumentException(
        String.format("Date String %s not in valid UTC/local format", originalValue));
  }
}
