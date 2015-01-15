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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class RecurringAppointmentDateParsingTest extends BaseTest {

  /**
   * Parsing the api recurrence date-String, comparing old and new parsing code
   * 
   * @throws ParseException
   */
  @Test
  public void parseAPIRecurrenceDateFormat() throws ParseException {
    String dateString = "2015-01-08Z";
    Date newParsing = exchangeServiceBaseMock.convertDateTimeStringToDate(dateString, false);
    Date oldParsing = convertStartDateToUnspecifiedDateTime(dateString);
    assertEquals(oldParsing, newParsing);
  }

  /**
   * parsing "localPattern2" using no default timezone
   */
  @Test
  public void parseLocalPattern2NoDefault() {
    String dateString = "2015-01-08T10:11:12"; // "yyyy-MM-dd'T'HH:mm:ss"
    Date parsed = exchangeServiceBaseMock.convertDateTimeStringToDate(dateString, false);
    Calendar calendar = new GregorianCalendar();
    calendar.setTime(parsed);
    assertEquals(2015, calendar.get(Calendar.YEAR));
    assertEquals(0, calendar.get(Calendar.MONTH));
    assertEquals(8, calendar.get(Calendar.DATE));
    assertEquals(10, calendar.get(Calendar.HOUR));
    assertEquals(11, calendar.get(Calendar.MINUTE));
    assertEquals(12, calendar.get(Calendar.SECOND));
  }

  /**
   * parsing "localPattern2" using utc as default timezone
   */
  @Test
  public void parseLocalPattern2DefaultToUTC() {
    String dateString = "2015-01-08T10:11:12"; // "yyyy-MM-dd'T'HH:mm:ss"
    Date parsed = exchangeServiceBaseMock.convertDateTimeStringToDate(dateString, true);
    Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("Europe/Paris"));
    calendar.setTime(parsed);
    assertEquals(2015, calendar.get(Calendar.YEAR));
    assertEquals(0, calendar.get(Calendar.MONTH));
    assertEquals(8, calendar.get(Calendar.DATE));
    assertEquals(11, calendar.get(Calendar.HOUR));
    assertEquals(11, calendar.get(Calendar.MINUTE));
    assertEquals(12, calendar.get(Calendar.SECOND));
  }

  /**
   * Parsing a previously unparsable recurrence date using the new parser.
   * Timezone -1
   */
  @Test
  public void parsePreviouslyUnbarsableRecurrenceDateFormat() {
    String dateString = "2015-01-08-01:00";
    Date parsed = exchangeServiceBaseMock.convertDateTimeStringToDate(dateString, false);
    assertNotNull(parsed);
    Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("Europe/Paris"));
    calendar.setTime(parsed);
    assertEquals(2015, calendar.get(Calendar.YEAR));
    assertEquals(0, calendar.get(Calendar.MONTH));
    assertEquals(8, calendar.get(Calendar.DATE));
    assertEquals(2, calendar.get(Calendar.HOUR));
    assertEquals(0, calendar.get(Calendar.MINUTE));
  }

  /**
   * Verifying different timezone parsed with new recurrence parser. Timezone +1
   */
  @Test
  public void parsePreviouslyUnbarsableRecurrenceDateFormat2() {
    String dateString = "2015-01-08+01:00";
    Date parsed = exchangeServiceBaseMock.convertDateTimeStringToDate(dateString, false);
    assertNotNull(parsed);
    Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("Europe/Paris"));
    calendar.setTime(parsed);
    assertEquals(2015, calendar.get(Calendar.YEAR));
    assertEquals(0, calendar.get(Calendar.MONTH));
    assertEquals(8, calendar.get(Calendar.DATE));
    assertEquals(0, calendar.get(Calendar.HOUR));
    assertEquals(0, calendar.get(Calendar.MINUTE));
  }

  /**
   * Parsing timezone -1 using "unmodified" utc parser
   */
  @Test
  public void parseAsUTC() {
    String dateString = "2015-01-08-01:00";
    Date parsed = exchangeServiceBaseMock.convertUniversalDateTimeStringToDate(dateString);
    assertNotNull(parsed);
    Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("Europe/Paris"));
    calendar.setTime(parsed);
    assertEquals(2015, calendar.get(Calendar.YEAR));
    assertEquals(0, calendar.get(Calendar.MONTH));
    assertEquals(8, calendar.get(Calendar.DATE));
    assertEquals(2, calendar.get(Calendar.HOUR));
    assertEquals(0, calendar.get(Calendar.MINUTE));

  }

  /**
   * Parse failing String do demo exception
   */
  @Test(expected=IllegalArgumentException.class)
  public void parseInvalidString() {
    String dateString = "2015-01-08-01:01Z";
    exchangeServiceBaseMock.convertUniversalDateTimeStringToDate(dateString);    
  }
  
  /**
   * Legacy code copied here to allow for unit-testing
   *
   * @param value
   * @return
   * @throws ParseException
   */
  protected Date convertStartDateToUnspecifiedDateTime(String value) throws ParseException {
    if (value == null || value.isEmpty()) {
      return null;
    } else {
      DateFormat df = new SimpleDateFormat("yyyy-MM-dd'Z'");
      return df.parse(value);
    }
  }
}
