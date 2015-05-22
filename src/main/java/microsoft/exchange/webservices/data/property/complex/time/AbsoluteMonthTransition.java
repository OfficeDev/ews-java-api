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
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlSerializationException;
import microsoft.exchange.webservices.data.misc.TimeSpan;

import javax.xml.stream.XMLStreamException;

/**
 * Represents the base class for all recurring time zone period transitions.
 */
abstract class AbsoluteMonthTransition extends TimeZoneTransition {

  /**
   * The time offset.
   */
  private TimeSpan timeOffset;

  /**
   * The month.
   */
  private int month;

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
    if (super.tryReadElementFromXml(reader)) {
      return true;
    } else {
      if (reader.getLocalName().equals(XmlElementNames.TimeOffset)) {
        this.timeOffset = EwsUtilities.getXSDurationToTimeSpan(reader.readElementValue());
        return true;
      } else if (reader.getLocalName().equals(XmlElementNames.Month)) {
        this.month = reader.readElementValue(Integer.class);

        EwsUtilities.ewsAssert(this.month > 0 && this.month <= 12,
            "AbsoluteMonthTransition.TryReadElementFromXml",
            "month is not in the valid 1 - 12 range.");

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

    writer.writeElementValue(XmlNamespace.Types,
        XmlElementNames.TimeOffset, EwsUtilities
            .getTimeSpanToXSDuration(this.timeOffset));

    writer.writeElementValue(XmlNamespace.Types, XmlElementNames.Month,
        this.month);
  }

  /**
   * Initializes a new instance of the AbsoluteMonthTransition class.
   *
   * @param timeZoneDefinition the time zone definition
   */
  protected AbsoluteMonthTransition(TimeZoneDefinition timeZoneDefinition) {
    super(timeZoneDefinition);
  }

  /**
   * Initializes a new instance of the AbsoluteMonthTransition class.
   *
   * @param timeZoneDefinition the time zone definition
   * @param targetPeriod       the target period
   */
  protected AbsoluteMonthTransition(TimeZoneDefinition timeZoneDefinition,
      TimeZonePeriod targetPeriod) {
    super(timeZoneDefinition, targetPeriod);
  }

  /**
   * Gets the time offset from midnight when the transition occurs.
   *
   * @return the time offset
   */
  protected TimeSpan getTimeOffset() {
    return this.timeOffset;
  }

  /**
   * Gets the month when the transition occurs.
   *
   * @return the month
   */
  protected int getMonth() {
    return this.month;
  }

}
