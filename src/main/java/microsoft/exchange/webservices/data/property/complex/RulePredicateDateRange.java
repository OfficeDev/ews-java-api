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
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceValidationException;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlSerializationException;

import javax.xml.stream.XMLStreamException;

import java.util.Date;

/**
 * Represents the date and time range within which messages have been received.
 */
public final class RulePredicateDateRange extends ComplexProperty {

  /**
   * The end DateTime.
   */
  private Date start;

  /**
   * The end DateTime.
   */
  private Date end;

  /**
   * Initializes a new instance of the RulePredicateDateRange class.
   */
  protected RulePredicateDateRange() {
    super();
  }

  /**
   * Gets or sets the range start date and time.
   * If Start is set to null, no start date applies.
   */
  public Date getStart() {
    return this.start;
  }

  public void setStart(Date value) {
    if (this.canSetFieldValue(this.start, value)) {
      this.start = value;
      this.changed();
    }
  }

  /**
   * Gets or sets the range end date and time.
   * If End is set to null, no end date applies.
   */
  public Date getEnd() {
    return this.end;
  }

  public void setEnd(Date value) {
    if (this.canSetFieldValue(this.end, value)) {
      this.end = value;
      this.changed();
    }
  }

  /**
   * Tries to read element from XML.
   *
   * @param reader The reader.
   * @return True if element was read.
   */
  @Override
  public boolean tryReadElementFromXml(EwsServiceXmlReader
      reader) throws Exception {
    if (reader.getLocalName().equalsIgnoreCase(XmlElementNames.StartDateTime)) {
      this.start = reader.readElementValueAsDateTime();
      return true;
    } else if (reader.getLocalName().equalsIgnoreCase(XmlElementNames.EndDateTime)) {
      this.end = reader.readElementValueAsDateTime();
      return true;
    } else {
      return false;
    }
  }

  /**
   * Writes elements to XML.
   *
   * @param writer the writer
   * @throws XMLStreamException the XML stream exception
   */
  @Override
  public void writeElementsToXml(EwsServiceXmlWriter writer)
      throws ServiceXmlSerializationException, XMLStreamException {
    if (this.getStart() != null) {
      writer.writeElementValue(XmlNamespace.Types,
          XmlElementNames.StartDateTime, this.getStart());
    }
    if (this.getEnd() != null) {
      writer.writeElementValue(XmlNamespace.Types,
          XmlElementNames.EndDateTime, this.getEnd());
    }
  }

  /**
   * Validates this instance.
   */
  @Override
  protected void internalValidate()
      throws ServiceValidationException, Exception {
    super.internalValidate();
    if (this.start != null &&
        this.end != null &&
        this.start.after(this.end)) {
      throw new ServiceValidationException(
          "Start date time cannot be bigger than end date time.");
    }
  }
}
