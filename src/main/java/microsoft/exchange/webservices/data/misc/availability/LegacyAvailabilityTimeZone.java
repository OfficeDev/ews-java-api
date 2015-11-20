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

package microsoft.exchange.webservices.data.misc.availability;

import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.property.time.DayOfTheWeek;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.misc.TimeSpan;
import microsoft.exchange.webservices.data.property.complex.ComplexProperty;
import microsoft.exchange.webservices.data.property.complex.time.TimeZoneDefinition;

import java.util.UUID;

/**
 * Represents a time zone as used by GetUserAvailabilityRequest.
 */
public final class LegacyAvailabilityTimeZone extends ComplexProperty {

  /**
   * The bias.
   */
  private TimeSpan bias;

  /**
   * The standard time.
   */
  private LegacyAvailabilityTimeZoneTime standardTime;

  /**
   * The daylight time.
   */
  private LegacyAvailabilityTimeZoneTime daylightTime;

  /**
   * Initializes a new instance of the LegacyAvailabilityTimeZone class.
   */
  public LegacyAvailabilityTimeZone() {
    super();
    this.bias = new TimeSpan(0);
    // If there are no adjustment rules (which is the
    //case for UTC), we have to come up with two
    // dummy time changes which both have a delta of
    //zero and happen at two hard coded dates. This
    // simulates a time zone in which there are no time changes.
    this.daylightTime = new LegacyAvailabilityTimeZoneTime();
    this.daylightTime.setDelta(new TimeSpan(0));
    this.daylightTime.setDayOrder(1);
    this.daylightTime.setDayOfTheWeek(DayOfTheWeek.Sunday);
    this.daylightTime.setMonth(10);
    this.daylightTime.setTimeOfDay(new TimeSpan(2 * 60 * 60 * 1000));
    this.daylightTime.setYear(0);

    this.standardTime = new LegacyAvailabilityTimeZoneTime();
    this.standardTime.setDelta(new TimeSpan(0));
    this.standardTime.setDayOrder(1);
    this.standardTime.setDayOfTheWeek(DayOfTheWeek.Sunday);
    this.standardTime.setMonth(3);
    this.standardTime.setTimeOfDay(new TimeSpan(2 * 60 * 60 * 1000));
    this.daylightTime.setYear(0);
  }

  /**
   * To time zone info.
   *
   * @return the time zone
   */
  public TimeZoneDefinition toTimeZoneInfo() {

		/*NumberFormat formatter = new DecimalFormat("00");
		String timeZoneId = this.bias.isNegative() ? "GMT+"+formatter.
		format(this.bias.getHours())+":"+
		formatter.format(this.bias.getMinutes()) : 
		"GMT-"+formatter.format(this.bias.getHours())+":"+
		formatter.format(this.bias.getMinutes());
*/
    TimeZoneDefinition timeZoneDefinition = new TimeZoneDefinition();
    timeZoneDefinition.id = UUID.randomUUID().toString();
    timeZoneDefinition.name = "Custom time zone";
    return timeZoneDefinition;
  }

  /**
   * Tries to read element from XML.
   *
   * @param reader the reader
   * @return True if element was read.
   * @throws Exception the exception
   */
  @Override
  public boolean tryReadElementFromXml(EwsServiceXmlReader reader)
      throws Exception {
    if (reader.getLocalName().equals(XmlElementNames.Bias)) {
      this.bias = new TimeSpan((long)
          reader.readElementValue(Integer.class) * 60 * 1000);
      return true;
    } else if (reader.getLocalName().equals(XmlElementNames.StandardTime)) {
      this.standardTime = new LegacyAvailabilityTimeZoneTime();
      this.standardTime.loadFromXml(reader, reader.getLocalName());
      return true;
    } else if (reader.getLocalName().equals(XmlElementNames.DaylightTime)) {
      this.daylightTime = new LegacyAvailabilityTimeZoneTime();
      this.daylightTime.loadFromXml(reader, reader.getLocalName());
      return true;
    } else {

      return false;
    }

  }

  /**
   * Writes the elements to XML.
   *
   * @param writer the writer
   * @throws Exception the exception
   */
  @Override
  public void writeElementsToXml(EwsServiceXmlWriter writer)
      throws Exception {
    writer.writeElementValue(
        XmlNamespace.Types,
        XmlElementNames.Bias,
        (int) this.bias.getTotalMinutes());

    this.standardTime.writeToXml(writer, XmlElementNames.StandardTime);
    this.daylightTime.writeToXml(writer, XmlElementNames.DaylightTime);
  }
}
