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

package microsoft.exchange.webservices.data.property.complex.recurrence.range;

import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlSerializationException;
import microsoft.exchange.webservices.data.property.complex.recurrence.pattern.Recurrence;

import javax.xml.stream.XMLStreamException;

import java.util.Date;

/**
 * The Class NumberedRecurrenceRange.
 */
public final class NumberedRecurrenceRange extends RecurrenceRange {

  /**
   * The number of occurrences.
   */
  private Integer numberOfOccurrences;

  /**
   * Initializes a new instance.
   */
  public NumberedRecurrenceRange() {
    super();
  }

  /**
   * Initializes a new instance.
   *
   * @param startDate           the start date
   * @param numberOfOccurrences the number of occurrences
   */
  public NumberedRecurrenceRange(Date startDate,
      Integer numberOfOccurrences) {
    super(startDate);
    this.numberOfOccurrences = numberOfOccurrences;
  }

  /**
   * Gets the name of the XML element.
   *
   * @return The name of the XML element
   */
  public String getXmlElementName() {
    return XmlElementNames.NumberedRecurrence;
  }

  /**
   * Setups the recurrence.
   *
   * @param recurrence the new up recurrence
   * @throws Exception the exception
   */
  public void setupRecurrence(Recurrence recurrence) throws Exception {
    super.setupRecurrence(recurrence);
    recurrence.setNumberOfOccurrences(this.numberOfOccurrences);
  }

  /**
   * Writes the elements to XML..
   *
   * @param writer the writer
   * @throws XMLStreamException the XML stream exception
   * @throws ServiceXmlSerializationException the service xml serialization exception
   */
  public void writeElementsToXml(EwsServiceXmlWriter writer)
      throws XMLStreamException, ServiceXmlSerializationException {
    super.writeElementsToXml(writer);

    if (this.numberOfOccurrences != null) {
      writer.writeElementValue(XmlNamespace.Types,
          XmlElementNames.NumberOfOccurrences,
          this.numberOfOccurrences);
    }
  }

  /**
   * Tries to read element from XML.
   *
   * @param reader the reader
   * @return True if element was read
   * @throws Exception the exception
   */
  public boolean tryReadElementFromXml(EwsServiceXmlReader reader)
      throws Exception {
    if (super.tryReadElementFromXml(reader)) {
      return true;
    } else {
      if (reader.getLocalName().equals(
          XmlElementNames.NumberOfOccurrences)) {
        this.numberOfOccurrences = reader
            .readElementValue(Integer.class);
        return true;
      } else {
        return false;
      }
    }
  }

  /**
   * Gets the number of occurrences.
   *
   * @return numberOfOccurrences
   */

  public Integer getNumberOfOccurrences() {
    return this.numberOfOccurrences;
  }

  /**
   * sets the number of occurrences.
   *
   * @param value the new number of occurrences
   */
  public void setNumberOfOccurrences(Integer value) {
    this.canSetFieldValue(this.numberOfOccurrences, value);

  }

}
