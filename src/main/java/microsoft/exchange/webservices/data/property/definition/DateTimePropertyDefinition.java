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
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.PropertyBag;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.property.PropertyDefinitionFlags;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.util.DateTimeUtils;

import java.util.Date;
import java.util.EnumSet;

/**
 * Represents DateTime property definition.
 */
public class DateTimePropertyDefinition extends PropertyDefinition {

  /**
   * The is nullable.
   */
  private boolean isNullable;

  /**
   * Initializes a new instance of the DateTimePropertyDefinition class.
   *
   * @param xmlElementName the xml element name
   * @param uri            the uri
   * @param version        the version
   */
  public DateTimePropertyDefinition(String xmlElementName, String uri, ExchangeVersion version) {
    super(xmlElementName, uri, version);
  }

  /**
   * Initializes a new instance of the DateTimePropertyDefinition class.
   *
   * @param xmlElementName the xml element name
   * @param uri            the uri
   * @param flags          the flags
   * @param version        the version
   */
  public DateTimePropertyDefinition(String xmlElementName, String uri, EnumSet<PropertyDefinitionFlags> flags,
      ExchangeVersion version) {
    super(xmlElementName, uri, flags, version);
  }

  /**
   * Initializes a new instance of the DateTimePropertyDefinition class.
   *
   * @param xmlElementName the xml element name
   * @param uri            the uri
   * @param flags          the flags
   * @param version        the version
   * @param isNullable     the is nullable
   */
  public DateTimePropertyDefinition(String xmlElementName, String uri, EnumSet<PropertyDefinitionFlags> flags,
      ExchangeVersion version, boolean isNullable) {
    super(xmlElementName, uri, flags, version);
    this.isNullable = isNullable;
  }

  /**
   * Loads from XML.
   *
   * @param reader      the reader
   * @param propertyBag the property bag
   * @throws Exception the exception
   */
  public void loadPropertyValueFromXml(EwsServiceXmlReader reader, PropertyBag propertyBag)
      throws Exception {
    String value = reader.readElementValue(XmlNamespace.Types, getXmlElement());
    propertyBag.setObjectFromPropertyDefinition(this, DateTimeUtils.convertDateTimeStringToDate(value));
  }


  /**
   * Writes the property value to XML.
   *
   * @param writer            accepts EwsServiceXmlWriter
   * @param propertyBag       accepts PropertyBag
   * @param isUpdateOperation accepts boolean whether the context is an update operation.
   * @throws Exception throws Exception
   */
  public void writePropertyValueToXml(EwsServiceXmlWriter writer, PropertyBag propertyBag,
      boolean isUpdateOperation)
      throws Exception {
    Object value = propertyBag.getObjectFromPropertyDefinition(this);

    if (value != null) {
      writer.writeStartElement(XmlNamespace.Types, getXmlElement());
      // No need of changing the date time zone to UTC as Java takes
      // default timezone as UTC
      Date dateTime = (Date) value;
      writer.writeValue(EwsUtilities.dateTimeToXSDateTime(dateTime),
          getName());

      writer.writeEndElement();
    }
  }

  /**
   * Gets a value indicating whether this property definition is for a
   * nullable type (ref, int?, bool?...).
   *
   * @return true, if is nullable
   */
  public boolean isNullable() {
    return isNullable;
  }

  /**
   * Gets the property type.
   */
  @Override
  public Class<Date> getType() {
    return Date.class;

  }
}
