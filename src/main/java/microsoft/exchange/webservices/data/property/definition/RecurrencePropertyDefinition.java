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
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.property.PropertyDefinitionFlags;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlDeserializationException;
import microsoft.exchange.webservices.data.property.complex.recurrence.pattern.Recurrence;
import microsoft.exchange.webservices.data.property.complex.recurrence.range.EndDateRecurrenceRange;
import microsoft.exchange.webservices.data.property.complex.recurrence.range.NoEndRecurrenceRange;
import microsoft.exchange.webservices.data.property.complex.recurrence.range.NumberedRecurrenceRange;
import microsoft.exchange.webservices.data.property.complex.recurrence.range.RecurrenceRange;
import microsoft.exchange.webservices.data.security.XmlNodeType;

import java.util.EnumSet;

/**
 * Represenrs recurrence property definition.
 */
public class RecurrencePropertyDefinition extends PropertyDefinition {

  /**
   * Initializes a new instance of the RecurrencePropertyDefinition class.
   *
   * @param xmlElementName the xml element name
   * @param uri            the uri
   * @param flags          the flags
   * @param version        the version
   */
  public RecurrencePropertyDefinition(String xmlElementName, String uri,
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
  public void loadPropertyValueFromXml(EwsServiceXmlReader reader, PropertyBag propertyBag) throws Exception {
    reader.ensureCurrentNodeIsStartElement(XmlNamespace.Types,
        XmlElementNames.Recurrence);

    Recurrence recurrence = null;

    reader.read(new XmlNodeType(XmlNodeType.START_ELEMENT)); // This is the
    // pattern
    // element

    if (reader.getLocalName().equals(
        XmlElementNames.RelativeYearlyRecurrence)) {

      recurrence = new Recurrence.RelativeYearlyPattern();
    } else if (reader.getLocalName().equals(
        XmlElementNames.AbsoluteYearlyRecurrence)) {

      recurrence = new Recurrence.YearlyPattern();
    } else if (reader.getLocalName().equals(
        XmlElementNames.RelativeMonthlyRecurrence)) {

      recurrence = new Recurrence.RelativeMonthlyPattern();
    } else if (reader.getLocalName().equals(
        XmlElementNames.AbsoluteMonthlyRecurrence)) {

      recurrence = new Recurrence.MonthlyPattern();
    } else if (reader.getLocalName()
        .equals(XmlElementNames.DailyRecurrence)) {

      recurrence = new Recurrence.DailyPattern();
    } else if (reader.getLocalName().equals(
        XmlElementNames.DailyRegeneration)) {

      recurrence = new Recurrence.DailyRegenerationPattern();
    } else if (reader.getLocalName().equals(
        XmlElementNames.WeeklyRecurrence)) {

      recurrence = new Recurrence.WeeklyPattern();
    } else if (reader.getLocalName().equals(
        XmlElementNames.WeeklyRegeneration)) {

      recurrence = new Recurrence.WeeklyRegenerationPattern();
    } else if (reader.getLocalName().equals(
        XmlElementNames.MonthlyRegeneration)) {

      recurrence = new Recurrence.MonthlyRegenerationPattern();
    } else if (reader.getLocalName().equals(
        XmlElementNames.YearlyRegeneration)) {

      recurrence = new Recurrence.YearlyRegenerationPattern();
    } else {

      throw new ServiceXmlDeserializationException(String.format("Invalid recurrence pattern: (%s).", reader.getLocalName()));
    }

    recurrence.loadFromXml(reader, reader.getLocalName());

    reader.read(new XmlNodeType(XmlNodeType.START_ELEMENT)); // This is the
    // range
    // element

    RecurrenceRange range;

    if (reader.getLocalName().equals(XmlElementNames.NoEndRecurrence)) {

      range = new NoEndRecurrenceRange();
    } else if (reader.getLocalName().equals(
        XmlElementNames.EndDateRecurrence)) {

      range = new EndDateRecurrenceRange();
    } else if (reader.getLocalName().equals(
        XmlElementNames.NumberedRecurrence)) {

      range = new NumberedRecurrenceRange();
    } else {
      throw new ServiceXmlDeserializationException(String.format("Invalid recurrence range: (%s).", reader.getLocalName()));
    }

    range.loadFromXml(reader, reader.getLocalName());
    range.setupRecurrence(recurrence);

    reader.readEndElementIfNecessary(XmlNamespace.Types,
        XmlElementNames.Recurrence);

    propertyBag.setObjectFromPropertyDefinition(this, recurrence);
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
    Recurrence value = propertyBag.getObjectFromPropertyDefinition(this);

    if (value != null) {
      value.writeToXml(writer, XmlElementNames.Recurrence);
    }
  }

  /**
   * Gets the property type.
   */
  @Override
  public Class<Recurrence> getType() {
    return Recurrence.class;
  }

}
