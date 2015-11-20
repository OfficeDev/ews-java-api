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

import microsoft.exchange.webservices.data.ISelfValidate;
import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlSerializationException;

import javax.xml.stream.XMLStreamException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Represents a time period.
 */
public class TimeWindow implements ISelfValidate {

  /**
   * The start time.
   */
  private Date startTime;

  /**
   * The end time.
   */
  private Date endTime;

  /**
   * Initializes a new instance of the "TimeWindow" class.
   */
  public TimeWindow() {
  }

  /**
   * Initializes a new instance of the "TimeWindow" class.
   *
   * @param startTime the start time
   * @param endTime   the end time
   */
  public TimeWindow(Date startTime, Date endTime) {
    this();
    this.startTime = startTime;
    this.endTime = endTime;
  }

  /**
   * Gets the start time.
   *
   * @return the start time
   */
  public Date getStartTime() {
    return startTime;
  }

  /**
   * Sets the start time.
   *
   * @param startTime the new start time
   */
  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }

  /**
   * Gets the end time.
   *
   * @return the end time
   */
  public Date getEndTime() {
    return endTime;
  }

  /**
   * Sets the end time.
   *
   * @param endTime the new end time
   */
  public void setEndTime(Date endTime) {
    this.endTime = endTime;
  }

  /**
   * Loads from XML.
   *
   * @param reader the reader
   * @throws Exception the exception
   */
  public void loadFromXml(EwsServiceXmlReader reader) throws Exception {
    reader.ensureCurrentNodeIsStartElement(XmlNamespace.Types,
        XmlElementNames.Duration);

    this.startTime = reader.readElementValueAsDateTime(XmlNamespace.Types,
        XmlElementNames.StartTime);
    this.endTime = reader.readElementValueAsDateTime(XmlNamespace.Types,
        XmlElementNames.EndTime);

    reader.readEndElement(XmlNamespace.Types, XmlElementNames.Duration);
  }

  /**
   * Writes to XML.
   *
   * @param writer         the writer
   * @param xmlElementName the xml element name
   * @param startTime      the start time
   * @param endTime        the end time
   * @throws XMLStreamException the XML stream exception
   * @throws ServiceXmlSerializationException the service xml serialization exception
   */
  private static void writeToXml(EwsServiceXmlWriter writer,
      String xmlElementName, Object startTime, Object endTime)
      throws XMLStreamException, ServiceXmlSerializationException {
    writer.writeStartElement(XmlNamespace.Types, xmlElementName);

    writer.writeElementValue(XmlNamespace.Types, XmlElementNames.StartTime,
        startTime);

    writer.writeElementValue(XmlNamespace.Types, XmlElementNames.EndTime,
        endTime);

    writer.writeEndElement(); // xmlElementName
  }

  /**
   * Writes to XML without scoping the dates and without emitting times.
   *
   * @param writer         the writer
   * @param xmlElementName the xml element name
   * @throws XMLStreamException the XML stream exception
   * @throws ServiceXmlSerializationException the service xml serialization exception
   */
  protected void writeToXmlUnscopedDatesOnly(EwsServiceXmlWriter writer,
      String xmlElementName) throws XMLStreamException, ServiceXmlSerializationException {
    final String DateOnlyFormat = "yyyy-MM-dd'T'00:00:00";

    DateFormat formatter = new SimpleDateFormat(DateOnlyFormat);
    formatter.setTimeZone(TimeZone.getTimeZone("UTC"));

    String start = formatter.format(this.startTime);
    String end = formatter.format(this.endTime);
    TimeWindow.writeToXml(writer, xmlElementName, start, end);
  }

  /**
   * Writes to XML.
   *
   * @param writer         the writer
   * @param xmlElementName the xml element name
   * @throws XMLStreamException the XML stream exception
   * @throws ServiceXmlSerializationException the service xml serialization exception
   */
  public void writeToXml(EwsServiceXmlWriter writer, String xmlElementName)
      throws XMLStreamException, ServiceXmlSerializationException {
    TimeWindow.writeToXml(writer, xmlElementName, startTime, endTime);
  }

  /**
   * Gets the duration.
   *
   * @return the duration
   */
  public long getDuration() {
    return this.endTime.getTime() - this.startTime.getTime();
  }

  /**
   * Validates this instance.
   */
  public void validate() {
  }
}
