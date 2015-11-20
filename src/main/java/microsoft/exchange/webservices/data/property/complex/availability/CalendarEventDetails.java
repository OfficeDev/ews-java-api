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
import microsoft.exchange.webservices.data.property.complex.ComplexProperty;

/**
 * Represents the details of a calendar event as returned by the
 * GetUserAvailability operation.
 */
public final class CalendarEventDetails extends ComplexProperty {

  /**
   * The store id.
   */
  private String storeId;

  /**
   * The subject.
   */
  private String subject;

  /**
   * The location.
   */
  private String location;

  /**
   * The is meeting.
   */
  private boolean isMeeting;

  /**
   * The is recurring.
   */
  private boolean isRecurring;

  /**
   * The is exception.
   */
  private boolean isException;

  /**
   * The is reminder set.
   */
  private boolean isReminderSet;

  /**
   * The is private.
   */
  private boolean isPrivate;

  /**
   * Initializes a new instance of the CalendarEventDetails class.
   */
  protected CalendarEventDetails() {
    super();
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
    if (reader.getLocalName().equals(XmlElementNames.ID)) {
      this.storeId = reader.readElementValue();
      return true;
    } else if (reader.getLocalName().equals(XmlElementNames.Subject)) {
      this.subject = reader.readElementValue();
      return true;
    } else if (reader.getLocalName().equals(XmlElementNames.Location)) {
      this.location = reader.readElementValue();
      return true;
    } else if (reader.getLocalName().equals(XmlElementNames.IsMeeting)) {
      this.isMeeting = reader.readElementValue(Boolean.class);
      return true;
    } else if (reader.getLocalName().equals(XmlElementNames.IsRecurring)) {
      this.isRecurring = reader.readElementValue(Boolean.class);
      return true;
    } else if (reader.getLocalName().equals(XmlElementNames.IsException)) {
      this.isException = reader.readElementValue(Boolean.class);
      return true;
    } else if (reader.getLocalName().equals(XmlElementNames.IsReminderSet)) {

      this.isReminderSet = reader.readElementValue(Boolean.class);
      return true;
    } else if (reader.getLocalName().equals(XmlElementNames.IsPrivate)) {
      this.isPrivate = reader.readElementValue(Boolean.class);
      return true;
    } else {
      return false;
    }

  }

  /**
   * Gets the store Id of the calendar event.
   *
   * @return the store id
   */
  public String getStoreId() {
    return this.storeId;
  }

  /**
   * Gets the subject of the calendar event.
   *
   * @return the subject
   */
  public String getSubject() {
    return subject;
  }

  /**
   * Gets the location of the calendar event.
   *
   * @return the location
   */
  public String getLocation() {
    return location;
  }

  /**
   * Gets a value indicating whether the calendar event is a meeting.
   *
   * @return true, if is meeting
   */
  public boolean isMeeting() {
    return isMeeting;
  }

  /**
   * Gets a value indicating whether the calendar event is recurring.
   *
   * @return true, if is recurring
   */
  public boolean isRecurring() {
    return isRecurring;
  }

  /**
   * Gets a value indicating whether the calendar event is an exception in a
   * recurring series.
   *
   * @return true, if is exception
   */
  public boolean isException() {
    return isException;
  }

  /**
   * Gets a value indicating whether the calendar event has a reminder set.
   *
   * @return true, if is reminder set
   */
  public boolean isReminderSet() {
    return isReminderSet;
  }

  /**
   * Gets a value indicating whether the calendar event is private.
   *
   * @return true, if is private
   */
  public boolean isPrivate() {
    return isPrivate;
  }

}
