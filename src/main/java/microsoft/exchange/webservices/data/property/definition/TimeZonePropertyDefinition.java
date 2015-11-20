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

package microsoft.exchange.webservices.data.property.definition;

import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.PropertyBag;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.property.PropertyDefinitionFlags;
import microsoft.exchange.webservices.data.property.complex.time.TimeZoneDefinition;

import java.util.EnumSet;
import java.util.TimeZone;

/**
 * Represents a property definition for property of type TimeZoneInfo.
 */
public class TimeZonePropertyDefinition extends PropertyDefinition {

  /**
   * Initializes a new instance of the TimeZonePropertyDefinition class.
   *
   * @param xmlElementName the xml element name
   * @param uri            the uri
   * @param flags          the flags
   * @param version        the version
   */
  public TimeZonePropertyDefinition(String xmlElementName, String uri, EnumSet<PropertyDefinitionFlags> flags,
      ExchangeVersion version) {
    super(xmlElementName, uri, flags, version);
  }

  /**
   * Loads from XML.
   *
   * @param reader      the reader
   * @param propertyBag the property bag
   * @throws Exception the exception
   */
  public void loadPropertyValueFromXml(EwsServiceXmlReader reader, PropertyBag propertyBag) throws Exception {
    TimeZoneDefinition timeZoneDefinition = new TimeZoneDefinition();
    timeZoneDefinition.loadFromXml(reader, this.getXmlElement());
    propertyBag.setObjectFromPropertyDefinition(this, timeZoneDefinition);
  }

  /**
   * Writes to XML.
   *
   * @param writer            the writer
   * @param propertyBag       the property bag
   * @param isUpdateOperation the is update operation
   * @throws Exception the exception
   */
  public void writePropertyValueToXml(EwsServiceXmlWriter writer, PropertyBag propertyBag,
      boolean isUpdateOperation) throws Exception {
    TimeZoneDefinition timeZoneDefinition = propertyBag.getObjectFromPropertyDefinition(this);

    if (timeZoneDefinition != null) {
      // We emit time zone property only if we have not emitted the time
      // zone SOAP header
      // or if this time zone is different from that of the service
      // through which the request
      // is being emitted.
      if (!writer.isTimeZoneHeaderEmitted())// || value !=
      // writer.getService().getTimeZone())
      {
        timeZoneDefinition.writeToXml(writer, this.getXmlElement());
      }
    }
  }

  /**
   * Gets the property type.
   */
  @Override
  public Class<TimeZone> getType() {
    return TimeZone.class;
  }
}
