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

package microsoft.exchange.webservices.data.property.complex.time;

import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.property.time.DayOfTheWeek;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlSerializationException;

import javax.xml.stream.XMLStreamException;

/**
 * Represents a time zone period transition that occurs on a relative day of a
 * specific month.
 */
class RelativeDayOfMonthTransition extends AbsoluteMonthTransition {

  /**
   * The day of the week.
   */
  private DayOfTheWeek dayOfTheWeek;

  /**
   * The week index.
   */
  private int weekIndex;

  /**
   * Gets the XML element name associated with the transition.
   *
   * @return The XML element name associated with the transition.
   */
  @Override
  protected String getXmlElementName() {
    return XmlElementNames.RecurringDayTransition;
  }

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
    if (super.tryReadElementFromXml(reader)) {
      return true;
    } else {
      if (reader.getLocalName().equals(XmlElementNames.DayOfWeek)) {
        this.dayOfTheWeek = reader.readElementValue(DayOfTheWeek.class);
        return true;
      } else if (reader.getLocalName().equals(XmlElementNames.Occurrence)) {
        this.weekIndex = reader.readElementValue(Integer.class);
        return true;
      } else {
        return false;
      }
    }
  }

  /**
   * Writes elements to XML.
   *
   * @param writer the writer
   * @throws ServiceXmlSerializationException the service xml serialization exception
   * @throws XMLStreamException the XML stream exception
   */
  @Override
  public void writeElementsToXml(EwsServiceXmlWriter writer)
      throws ServiceXmlSerializationException, XMLStreamException {
    super.writeElementsToXml(writer);

    writer.writeElementValue(
        XmlNamespace.Types,
        XmlElementNames.DayOfWeek,
        this.dayOfTheWeek);

    writer.writeElementValue(
        XmlNamespace.Types,
        XmlElementNames.Occurrence,
        this.weekIndex);
  }

  /**
   * Initializes a new instance of the "RelativeDayOfMonthTransition class.
   *
   * @param timeZoneDefinition the time zone definition
   */
  protected RelativeDayOfMonthTransition(
      TimeZoneDefinition timeZoneDefinition) {
    super(timeZoneDefinition);
  }

  /**
   * Initializes a new instance of the "RelativeDayOfMonthTransition class.
   *
   * @param timeZoneDefinition the time zone definition
   * @param targetPeriod       the target period
   */
  protected RelativeDayOfMonthTransition(
      TimeZoneDefinition timeZoneDefinition,
      TimeZonePeriod targetPeriod) {
    super(timeZoneDefinition, targetPeriod);
  }

  /**
   * Gets the day of the week when the transition occurs.
   *
   * @return the day of the week
   */
  protected DayOfTheWeek getDayOfTheWeek() {
    return this.dayOfTheWeek;
  }

  /**
   * Gets the index of the week in the month when the transition occurs.
   *
   * @return the week index
   */
  protected int getWeekIndex() {
    return this.weekIndex;
  }
}
