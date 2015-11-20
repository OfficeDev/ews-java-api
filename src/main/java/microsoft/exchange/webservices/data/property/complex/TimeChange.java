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

package microsoft.exchange.webservices.data.property.complex;

import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.XmlAttributeNames;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlSerializationException;
import microsoft.exchange.webservices.data.misc.Time;
import microsoft.exchange.webservices.data.misc.TimeSpan;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.xml.bind.DatatypeConverter;

/**
 * Represents a change of time for a time zone.
 */
public final class TimeChange extends ComplexProperty {

  private static final Log LOG = LogFactory.getLog(TimeChange.class);

  /**
   * The time zone name.
   */
  private String timeZoneName;

  /**
   * The offset.
   */
  private TimeSpan offset;

  /**
   * The time.
   */
  private Time time;

  /**
   * The absolute date.
   */
  private Date absoluteDate;

  /**
   * The recurrence.
   */
  private TimeChangeRecurrence recurrence;

  /**
   * Initializes a new instance of the "TimeChange" class.
   */
  public TimeChange() {
    super();
  }

  /**
   * Initializes a new instance of the <see cref="TimeChange"/> class.
   *
   * @param offset The offset since the beginning of the year when the change
   *               occurs.
   */
  public TimeChange(TimeSpan offset) {
    this();
    this.offset = offset;
  }

  /**
   * Initializes a new instance of the "TimeChange" class.
   *
   * @param offset The offset since the beginning of the year when the change
   *               occurs.
   * @param time   The time at which the change occurs.
   */
  public TimeChange(TimeSpan offset, Time time) {
    this(offset);
    this.time = time;
  }

  /**
   * Gets the name of the associated time zone.
   *
   * @return the timeZoneName
   */
  public String getTimeZoneName() {
    return timeZoneName;
  }

  /**
   * Sets the name of the associated time zone.
   *
   * @param timeZoneName the timeZoneName to set
   */
  public void setTimeZoneName(String timeZoneName) {
    this.timeZoneName = timeZoneName;
  }

  /**
   * Gets the offset since the beginning of the year when the change occurs.
   *
   * @return the offset
   */
  public TimeSpan getOffset() {
    return offset;
  }

  /**
   * Sets the offset since the beginning of the year when the change occurs.
   *
   * @param offset the offset to set
   */
  public void setOffset(TimeSpan offset) {
    this.offset = offset;
  }

  /**
   * Gets the time.
   *
   * @return the time
   */
  public Time getTime() {
    return time;
  }

  /**
   * Sets the time.
   *
   * @param time the time to set
   */
  public void setTime(Time time) {
    this.time = time;
  }

  /**
   * Gets the absolute date.
   *
   * @return the absoluteDate
   */
  public Date getAbsoluteDate() {
    return absoluteDate;
  }

  /**
   * Sets the absolute date.
   *
   * @param absoluteDate the absoluteDate to set
   */
  public void setAbsoluteDate(Date absoluteDate) {
    this.absoluteDate = absoluteDate;
    if (absoluteDate != null) {
      this.recurrence = null;
    }
  }

  /**
   * Gets the recurrence.
   *
   * @return the recurrence
   */
  public TimeChangeRecurrence getRecurrence() {
    return recurrence;
  }

  /**
   * Sets the recurrence.
   *
   * @param recurrence the recurrence to set
   */
  public void setRecurrence(TimeChangeRecurrence recurrence) {
    this.recurrence = recurrence;
    if (this.recurrence != null) {
      this.absoluteDate = null;
    }
  }

  /**
   * Tries to read element from XML.
   *
   * @param reader accepts EwsServiceXmlReader
   * @return True if element was read
   * @throws Exception throws Exception
   */
  @Override
  public boolean tryReadElementFromXml(EwsServiceXmlReader reader)
      throws Exception {

    if (reader.getLocalName().equalsIgnoreCase(XmlElementNames.Offset)) {
      this.offset = EwsUtilities.getXSDurationToTimeSpan(reader.readElementValue());
      return true;
    } else if (reader.getLocalName().equalsIgnoreCase(
        XmlElementNames.RelativeYearlyRecurrence)) {
      this.recurrence = new TimeChangeRecurrence();
      this.recurrence.loadFromXml(reader, reader.getLocalName());
      return true;
    } else if (reader.getLocalName().equalsIgnoreCase(
        XmlElementNames.AbsoluteDate)) {
      Calendar cal = DatatypeConverter.parseDate(reader.readElementValue());
      cal.setTimeZone(TimeZone.getTimeZone("UTC"));
      this.absoluteDate = cal.getTime();
      return true;
    } else if (reader.getLocalName().equalsIgnoreCase(XmlElementNames.Time)) {
      Calendar cal = DatatypeConverter.parseTime(reader.readElementValue());
      this.time = new Time(cal.getTime());
      return true;
    } else {
      return false;
    }
  }

  /**
   * Reads the attribute from XML.
   *
   * @param reader accepts EwsServiceXmlReader
   * @throws Exception throws Exception
   */
  @Override
  public void readAttributesFromXml(EwsServiceXmlReader reader)
      throws Exception {
    this.timeZoneName = reader
        .readAttributeValue(XmlAttributeNames.TimeZoneName);
  }

  /**
   * Writes the attribute to XML.
   *
   * @param writer accepts EwsServiceXmlWriter
   */
  @Override
  public void writeAttributesToXml(EwsServiceXmlWriter writer) {
    try {
      writer.writeAttributeValue(XmlAttributeNames.TimeZoneName,
          this.timeZoneName);
    } catch (ServiceXmlSerializationException e) {
      LOG.error(e);
    }
  }

  /**
   * Writes elements to XML.
   *
   * @param writer accepts EwsServiceXmlWriter
   * @throws Exception throws Exception
   */
  @Override
  public void writeElementsToXml(EwsServiceXmlWriter writer)
      throws Exception {
    if (this.offset != null) {
      writer.writeElementValue(XmlNamespace.Types,
          XmlElementNames.Offset, EwsUtilities
              .getTimeSpanToXSDuration(this.getOffset()));
    }

    if (this.recurrence != null) {
      this.recurrence.writeToXml(writer,
          XmlElementNames.RelativeYearlyRecurrence);
    }

    if (this.absoluteDate != null) {
      writer.writeElementValue(XmlNamespace.Types,
          XmlElementNames.AbsoluteDate, EwsUtilities
              .dateTimeToXSDate(this.getAbsoluteDate()));
    }

    if (this.time != null) {
      writer.writeElementValue(XmlNamespace.Types, XmlElementNames.Time,
          this.getTime().toXSTime());
    }
  }

}
