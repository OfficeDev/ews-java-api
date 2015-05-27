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
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.property.time.DayOfTheWeek;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlSerializationException;
import microsoft.exchange.webservices.data.misc.TimeSpan;
import microsoft.exchange.webservices.data.property.complex.ComplexProperty;

import javax.xml.stream.XMLStreamException;

/**
 * Represents a custom time zone time change.
 */
final class LegacyAvailabilityTimeZoneTime extends ComplexProperty {

  /**
   * The delta.
   */
  private TimeSpan delta;

  /**
   * The year.
   */
  private int year;

  /**
   * The month.
   */
  private int month;

  /**
   * The day order.
   */
  private int dayOrder;

  /**
   * The day of the week.
   */
  private DayOfTheWeek dayOfTheWeek;

  /**
   * The time of day.
   */
  private TimeSpan timeOfDay;

  /**
   * Initializes a new instance of the LegacyAvailabilityTimeZoneTime class.
   */
  protected LegacyAvailabilityTimeZoneTime() {
    super();
  }

  /**
   * initializes a new instance of the LegacyAvailabilityTimeZoneTime class.
   *
   * @param reader
   *            the reader
   * @return true, if successful
   * @throws Exception
   *             the exception
   */
        /*
	 * protected LegacyAvailabilityTimeZoneTime(TimeZone.TransitionTime
	 * transitionTime, TimeSpan delta) { this(); this.delta = delta;
	 * 
	 * if (transitionTime.IsFixedDateRule) { // TimeZoneInfo doesn't support an
	 * actual year. Fixed date transitions occur at the same // date every year
	 * the adjustment rule the transition belongs to applies. The best thing //
	 * we can do here is use the current year. this.year = Date.Today.Year;
	 * this.month = transitionTime.Month; this.dayOrder = transitionTime.Day;
	 * this.timeOfDay = transitionTime.TimeOfDay.TimeOfDay; } else { // For
	 * floating rules, the mapping is direct. this.year = 0; this.month =
	 * transitionTime.Month; this.dayOfTheWeek =
	 * EwsUtilities.SystemToEwsDayOfTheWeek(transitionTime.DayOfWeek);
	 * this.dayOrder = transitionTime.Week; this.timeOfDay =
	 * transitionTime.TimeOfDay.TimeOfDay; } }
	 */

  /**
   * Converts this instance to TimeZoneInfo.TransitionTime. returns
   * TimeZoneInfo.TransitionTime
   *
   */
	/*
	 * protected TimeZone.TransitionTime toTransitionTime() { if (this.year ==
	 * 0) { return TimeZone.TransitionTime.createFloatingDateRule( new Date(
	 * Date.MinValue.Year, DateTime.MinValue.Month, DateTime.MinValue.Day,
	 * this.timeOfDay.Hours, this.timeOfDay.Minutes, this.timeOfDay.Seconds),
	 * this.month, this.dayOrder,
	 * EwsUtilities.ewsToSystemDayOfWeek(this.dayOfTheWeek)); } else { return
	 * TimeZone.TransitionTime.createFixedDateRule( new
	 * Date(this.timeOfDay.Ticks), this.month, this.dayOrder); } }
	 */

  /**
   * Tries to read element from XML.
   *
   * @param reader accepts EwsServiceXmlReader
   * @return True if element was read.
   * @throws Exception throws Exception
   */
  @Override
  public boolean tryReadElementFromXml(EwsServiceXmlReader reader)
      throws Exception {
    if (reader.getLocalName().equals(XmlElementNames.Bias)) {
      this.delta = new TimeSpan((long)
          reader.readElementValue(Integer.class) * 60 * 1000);
      return true;
    } else if (reader.getLocalName().equals(XmlElementNames.Time)) {
      this.timeOfDay = TimeSpan.parse(reader.readElementValue());
      return true;
    } else if (reader.getLocalName().equals(XmlElementNames.DayOrder)) {
      this.dayOrder = reader.readElementValue(Integer.class);
      return true;
    } else if (reader.getLocalName().equals(XmlElementNames.DayOfWeek)) {
      this.dayOfTheWeek = reader.readElementValue(DayOfTheWeek.class);
      return true;
    } else if (reader.getLocalName().equals(XmlElementNames.Month)) {
      this.month = reader.readElementValue(Integer.class);
      return true;
    } else if (reader.getLocalName().equals(XmlElementNames.Year)) {
      this.year = reader.readElementValue(Integer.class);
      return true;
    } else {
      return false;
    }
  }

  /**
   * Writes the elements to XML.
   *
   * @param writer the writer
   * @throws ServiceXmlSerializationException the service xml serialization exception
   * @throws XMLStreamException the XML stream exception
   */
  @Override
  public void writeElementsToXml(EwsServiceXmlWriter writer)
      throws ServiceXmlSerializationException, XMLStreamException {
    writer.writeElementValue(XmlNamespace.Types, XmlElementNames.Bias,
        (int) this.delta.getMinutes());

    writer.writeElementValue(XmlNamespace.Types, XmlElementNames.Time,
        EwsUtilities.timeSpanToXSTime(this.timeOfDay));

    writer.writeElementValue(XmlNamespace.Types, XmlElementNames.DayOrder,
        this.dayOrder);

    writer.writeElementValue(XmlNamespace.Types, XmlElementNames.Month, this.month);

    // Only write DayOfWeek if this is a recurring time change
    if (this.getYear() == 0) {
      writer.writeElementValue(XmlNamespace.Types,
          XmlElementNames.DayOfWeek, this.dayOfTheWeek);
    }

    // Only emit year if it's non zero, otherwise AS returns
    // "Request is invalid"
    if (this.getYear() != 0) {
      writer.writeElementValue(XmlNamespace.Types, XmlElementNames.Year,
          this.getYear());
    }
  }

  /**
   * Gets if current time presents DST transition time
   *
   * @return month
   */
  protected boolean getHasTransitionTime() {
    return this.month >= 1 && this.month <= 12;
  }


  /**
   * Gets  the delta.
   *
   * @return the delta
   */
  protected TimeSpan getDelta() {
    return this.delta;
  }

  /**
   * Sets the delta.
   *
   * @param delta the new delta
   */
  protected void setDelta(TimeSpan delta) {
    this.delta = delta;
  }

  /**
   * Gets  the time of day.
   *
   * @return the time of day
   */
  protected TimeSpan getTimeOfDay() {
    return this.timeOfDay;
  }

  /**
   * Sets the time of day.
   *
   * @param timeOfDay the new time of day
   */
  protected void setTimeOfDay(TimeSpan timeOfDay) {
    this.timeOfDay = timeOfDay;
  }

  /**
   * Gets  a value that represents: - The day of the month when Year is
   * non zero, - The index of the week in the month if Year is equal to zero.
   *
   * @return the day order
   */
  protected int getDayOrder() {
    return this.dayOrder;
  }

  /**
   * Sets the day order.
   *
   * @param dayOrder the new day order
   */
  protected void setDayOrder(int dayOrder) {
    this.dayOrder = dayOrder;
  }

  /**
   * Gets  the month.
   *
   * @return the month
   */
  protected int getMonth() {
    return this.month;
  }

  /**
   * Sets the month.
   *
   * @param month the new month
   */
  protected void setMonth(int month) {
    this.month = month;
  }

  /**
   * Gets  the day of the week.
   *
   * @return the day of the week
   */
  protected DayOfTheWeek getDayOfTheWeek() {
    return this.dayOfTheWeek;
  }

  /**
   * Sets the day of the week.
   *
   * @param dayOfTheWeek the new day of the week
   */
  protected void setDayOfTheWeek(DayOfTheWeek dayOfTheWeek) {
    this.dayOfTheWeek = dayOfTheWeek;
  }

  /**
   * Gets  the year. If Year is 0, the time change occurs every year
   * according to a recurring pattern; otherwise, the time change occurs at
   * the date specified by Day, Month, Year.
   *
   * @return the year
   */
  protected int getYear() {
    return this.year;
  }

  /**
   * Sets the year.
   *
   * @param year the new year
   */
  protected void setYear(int year) {
    this.year = year;
  }

}
