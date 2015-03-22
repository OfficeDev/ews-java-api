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

package microsoft.exchange.webservices.data;

import microsoft.exchange.webservices.data.enumerations.XmlNamespace;
import microsoft.exchange.webservices.data.exceptions.ServiceLocalException;
import microsoft.exchange.webservices.data.exceptions.ServiceXmlDeserializationException;
import microsoft.exchange.webservices.data.properties.complex.timeZones.TimeZoneDefinition;

import javax.xml.stream.XMLStreamException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Represents the response to a GetServerTimeZones request.
 */
class GetServerTimeZonesResponse extends ServiceResponse {

  /**
   * The time zones.
   */
  private Collection<TimeZoneDefinition> timeZones =
      new ArrayList<TimeZoneDefinition>();

  /**
   * Initializes a new instance of the class.
   */
  protected GetServerTimeZonesResponse() {
    super();
  }

  /**
   * Reads response elements from XML.
   *
   * @param reader the reader
   * @throws ServiceXmlDeserializationException                        the service xml deserialization exception
   * @throws javax.xml.stream.XMLStreamException                       the xML stream exception
   * @throws InstantiationException                                    the instantiation exception
   * @throws IllegalAccessException                                    the illegal access exception
   * @throws microsoft.exchange.webservices.data.exceptions.ServiceLocalException the service local exception
   * @throws Exception                                                 the exception
   */
  @Override
  protected void readElementsFromXml(EwsServiceXmlReader reader)
      throws ServiceXmlDeserializationException, XMLStreamException,
      InstantiationException, IllegalAccessException, ServiceLocalException, Exception {
    super.readElementsFromXml(reader);

    reader.readStartElement(XmlNamespace.Messages,
        XmlElementNames.TimeZoneDefinitions);

    if (!reader.isEmptyElement()) {
      do {
        reader.read();

        if (reader.isStartElement(XmlNamespace.Types,
            XmlElementNames.TimeZoneDefinition)) {
          TimeZoneDefinition timeZoneDefinition =
              new TimeZoneDefinition();
          timeZoneDefinition.loadFromXml(reader);

          this.timeZones.add(timeZoneDefinition);
        }
      } while (!reader.isEndElement(XmlNamespace.Messages,
          XmlElementNames.TimeZoneDefinitions));
    } else {
      reader.read();
    }
  }

  /**
   * Reads response elements from XML.
   *
   * @return the time zones
   */
  public Collection<TimeZoneDefinition> getTimeZones() {
    return this.timeZones;
  }

}
