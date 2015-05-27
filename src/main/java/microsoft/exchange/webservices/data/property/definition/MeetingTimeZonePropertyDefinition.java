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
import microsoft.exchange.webservices.data.core.service.schema.AppointmentSchema;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.property.PropertyDefinitionFlags;
import microsoft.exchange.webservices.data.property.complex.MeetingTimeZone;

import java.util.EnumSet;

/**
 * Represents the definition for the meeting time zone property.
 */
public class MeetingTimeZonePropertyDefinition extends PropertyDefinition {

  /**
   * Initializes a new instance of the MeetingTimeZonePropertyDefinition
   * class.
   *
   * @param xmlElementName the xml element name
   * @param uri            the uri
   * @param flags          the flags
   * @param version        the version
   */
  public MeetingTimeZonePropertyDefinition(String xmlElementName, String uri,
      EnumSet<PropertyDefinitionFlags> flags, ExchangeVersion version) {
    super(xmlElementName, uri, flags, version);

  }

  /**
   * Loads from XML.
   *
   * @param reader      the reader
   * @param propertyBag the property bag
   * @throws Exception the exception
   */
  public final void loadPropertyValueFromXml(EwsServiceXmlReader reader, PropertyBag propertyBag) throws Exception {
    MeetingTimeZone meetingTimeZone = new MeetingTimeZone();
    meetingTimeZone.loadFromXml(reader, this.getXmlElement());

    propertyBag.setObjectFromPropertyDefinition(
        AppointmentSchema.StartTimeZone, meetingTimeZone
            .toTimeZoneInfo());
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
      boolean isUpdateOperation)
      throws Exception {
    MeetingTimeZone value = propertyBag.getObjectFromPropertyDefinition(this);

    if (value != null) {
      value.writeToXml(writer, this.getXmlElement());
    }
  }

  /**
   * Gets the property type.
   */
  @Override
  public Class<MeetingTimeZone> getType() {
    return MeetingTimeZone.class;
  }
}
