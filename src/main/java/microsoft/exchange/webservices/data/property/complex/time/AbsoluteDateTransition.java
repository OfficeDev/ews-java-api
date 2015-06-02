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
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlSerializationException;

import javax.xml.stream.XMLStreamException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a time zone period transition that occurs on a fixed (absolute)
 * date.
 */
public class AbsoluteDateTransition extends TimeZoneTransition {

  /**
   * The date time.
   */
  private Date dateTime;

  /**
   * Gets the XML element name associated with the transition.
   *
   * @return The XML element name associated with the transition.
   */
  @Override
  protected String getXmlElementName() {
    return XmlElementNames.AbsoluteDateTransition;
  }

  /**
   * Tries to read element from XML.
   *
   * @param reader the reader
   * @return True if element was read.
   * @throws java.text.ParseException the parse exception
   * @throws Exception                the exception
   */
  @Override
  public boolean tryReadElementFromXml(EwsServiceXmlReader reader)
      throws ParseException, Exception {
    boolean result = super.tryReadElementFromXml(reader);

    if (!result) {
      if (reader.getLocalName().equals(XmlElementNames.DateTime)) {
        SimpleDateFormat sdfin = new SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss");
        this.dateTime = sdfin.parse(reader.readElementValue());

        result = true;
      }
    }

    return result;
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

    writer.writeElementValue(XmlNamespace.Types, XmlElementNames.DateTime,
        this.dateTime);
  }

  /**
   * Initializes a new instance of the AbsoluteDateTransition class.
   *
   * @param timeZoneDefinition , The time zone definition the transition will belong to.
   */
  protected AbsoluteDateTransition(TimeZoneDefinition timeZoneDefinition) {
    super(timeZoneDefinition);
  }

  /**
   * Initializes a new instance of the AbsoluteDateTransition class.
   *
   * @param timeZoneDefinition The time zone definition the transition will belong to.
   * @param targetGroup        the target group
   */
  protected AbsoluteDateTransition(TimeZoneDefinition timeZoneDefinition,
      TimeZoneTransitionGroup targetGroup) {
    super(timeZoneDefinition, targetGroup);
  }

  /**
   * Gets the absolute date and time when the transition occurs.
   *
   * @return the date time
   */
  public Date getDateTime() {
    return dateTime;
  }

  /**
   * Sets the date time.
   *
   * @param dateTime the new date time
   */
  protected void setDateTime(Date dateTime) {
    this.dateTime = dateTime;
  }
}
