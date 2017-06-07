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

package microsoft.exchange.webservices.data.util;

import org.apache.commons.lang3.StringUtils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

import static java.time.temporal.ChronoField.INSTANT_SECONDS;

public final class DateTimeUtils {

  private static final DateTimeFormatter[] DATE_TIME_FORMATS = createDateTimeFormats();
  private static final DateTimeFormatter[] DATE_FORMATS = createDateFormats();


  private DateTimeUtils() {
    throw new UnsupportedOperationException();
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
  public static Date convertDateTimeStringToDate(String value) {
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
  public static Date convertDateStringToDate(String value) {
    return parseInternal(value, true);
  }


  private static Date parseInternal(String value, boolean dateOnly) {
    String originalValue = value;

    if (StringUtils.isEmpty(value)) {
      return null;
    } else {
      if (value.endsWith("z")) {
        // This seems to be an edge case. Let's uppercase the Z to be sure.
        value = value.substring(0, value.length() - 1) + "Z";
      }

      final DateTimeFormatter[] formats = dateOnly ? DATE_FORMATS : DATE_TIME_FORMATS;
      for (final DateTimeFormatter format : formats) {
        try {
          TemporalAccessor temporalAccessor = format.parse(value);

          Instant instant = null;

          if (!temporalAccessor.isSupported(INSTANT_SECONDS)) {
            // Only date
            ZoneOffset zoneOffset = ZoneOffset.UTC;

            if (temporalAccessor.isSupported(ChronoField.OFFSET_SECONDS)) {
              // Have date with timezone offset
              zoneOffset = ZoneOffset.ofTotalSeconds(temporalAccessor.get(ChronoField.OFFSET_SECONDS));
            }

            instant = LocalDate.from(temporalAccessor).atTime(OffsetTime.of(LocalTime.MIDNIGHT, zoneOffset)).toInstant();

          } else {
            // Date and time
            instant = Instant.from(temporalAccessor);
          }

          return Date.from(instant);

        } catch (DateTimeParseException e) {
          // Ignore and try the next pattern.
        }
      }
    }

    throw new IllegalArgumentException(
        String.format("Date String %s not in valid UTC/local format", originalValue));
  }

  private static DateTimeFormatter[] createDateTimeFormats() {
    return new DateTimeFormatter[] {
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZZZZZ"),
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ"),
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ"),
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ"),
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSZZZZZ"),
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSZ"),
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss").withZone(ZoneOffset.UTC),
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS").withZone(ZoneOffset.UTC),
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS").withZone(ZoneOffset.UTC),
        DateTimeFormatter.ofPattern("yyyy-MM-ddZZZZZ"),
        DateTimeFormatter.ofPattern("yyyy-MM-ddZ"),
        DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneOffset.UTC)
    };
  }

  private static DateTimeFormatter[] createDateFormats() {
    return new DateTimeFormatter[] {
        DateTimeFormatter.ofPattern("yyyy-MM-ddZZZZZ"),
        DateTimeFormatter.ofPattern("yyyy-MM-ddZ"),
        DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneOffset.UTC)
    };
  }

}
