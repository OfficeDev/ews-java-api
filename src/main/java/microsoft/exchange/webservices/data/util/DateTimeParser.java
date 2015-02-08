package microsoft.exchange.webservices.data.util;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

public class DateTimeParser {

  private final DateTimeFormatter[] dateTimeFormats = {
      DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ssZ").withZoneUTC(),
      DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ").withZoneUTC(),
      DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss").withZoneUTC(),
      DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS").withZoneUTC(),
      DateTimeFormat.forPattern("yyyy-MM-ddZ").withZoneUTC(),
      DateTimeFormat.forPattern("yyyy-MM-dd").withZoneUTC()
  };

  private final DateTimeFormatter[] dateFormats = {
      DateTimeFormat.forPattern("yyyy-MM-ddZ").withZoneUTC(),
      DateTimeFormat.forPattern("yyyy-MM-dd").withZoneUTC()
  };


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

      DateTimeFormatter[] formats = dateOnly ? dateFormats : dateTimeFormats;
      for (DateTimeFormatter format : formats) {
        try {
          return format.parseDateTime(value).toDate();
        } catch (IllegalArgumentException e) {
          // Ignore and try the next pattern.
        }
      }
    }

    throw new IllegalArgumentException(
        String.format("Date String %s not in valid UTC/local format", originalValue));
  }
}
