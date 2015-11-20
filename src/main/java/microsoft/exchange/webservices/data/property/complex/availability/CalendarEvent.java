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

package microsoft.exchange.webservices.data.property.complex.availability;

import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.property.LegacyFreeBusyStatus;
import microsoft.exchange.webservices.data.property.complex.ComplexProperty;

import java.util.Date;

/**
 * Represents an event in a calendar.
 */
public final class CalendarEvent extends ComplexProperty {

  /**
   * The start time.
   */
  private Date startTime;

  /**
   * The end time.
   */
  private Date endTime;

  /**
   * The free busy status.
   */
  private LegacyFreeBusyStatus freeBusyStatus;

  /**
   * The details.
   */
  private CalendarEventDetails details;

  /**
   * Initializes a new instance of the CalendarEvent class.
   */
  public CalendarEvent() {
    super();
  }

  /**
   * Gets the start date and time of the event.
   *
   * @return the start time
   */
  public Date getStartTime() {
    return startTime;
  }

  /**
   * Gets the end date and time of the event.
   *
   * @return the end time
   */
  public Date getEndTime() {
    return endTime;
  }

  /**
   * Gets the free/busy status associated with the event.
   *
   * @return the free busy status
   */
  public LegacyFreeBusyStatus getFreeBusyStatus() {
    return freeBusyStatus;
  }

  /**
   * Gets the details of the calendar event. Details is null if the user
   * requsting them does no have the appropriate rights.
   *
   * @return the details
   */
  public CalendarEventDetails getDetails() {
    return details;
  }

  /**
   * Attempts to read the element at the reader's current position.
   *
   * @param reader the reader
   * @return True if the element was read, false otherwise.
   * @throws Exception the exception
   */
  @Override
  public boolean tryReadElementFromXml(EwsServiceXmlReader reader)
      throws Exception {
    if (reader.getLocalName().equals(XmlElementNames.StartTime)) {
      this.startTime = reader
          .readElementValueAsUnbiasedDateTimeScopedToServiceTimeZone();
      return true;
    } else if (reader.getLocalName().equals(XmlElementNames.EndTime)) {
      this.endTime = reader
          .readElementValueAsUnbiasedDateTimeScopedToServiceTimeZone();
      return true;
    } else if (reader.getLocalName().equals(XmlElementNames.BusyType)) {
      this.freeBusyStatus = reader
          .readElementValue(LegacyFreeBusyStatus.class);
      return true;
    }
    if (reader.getLocalName().equals(XmlElementNames.CalendarEventDetails)) {
      this.details = new CalendarEventDetails();
      this.details.loadFromXml(reader, reader.getLocalName());
      return true;
    } else {
      return false;
    }

  }
}
