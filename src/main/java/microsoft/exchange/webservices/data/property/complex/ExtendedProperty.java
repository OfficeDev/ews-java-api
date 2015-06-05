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
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlSerializationException;
import microsoft.exchange.webservices.data.misc.MapiTypeConverter;
import microsoft.exchange.webservices.data.property.definition.ExtendedPropertyDefinition;
import org.apache.commons.lang3.StringUtils;

import javax.xml.stream.XMLStreamException;

import java.util.ArrayList;

/**
 * Represents an extended property.
 */
public final class ExtendedProperty extends ComplexProperty {

  /**
   * The property definition.
   */
  private ExtendedPropertyDefinition propertyDefinition;

  /**
   * The value.
   */
  private Object value;

  /**
   * Initializes a new instance.
   */
  protected ExtendedProperty() {
  }

  /**
   * Initializes a new instance.
   *
   * @param propertyDefinition The definition of the extended property.
   * @throws Exception the exception
   */
  protected ExtendedProperty(ExtendedPropertyDefinition propertyDefinition)
      throws Exception {
    this();
    EwsUtilities.validateParam(propertyDefinition, "propertyDefinition");
    this.propertyDefinition = propertyDefinition;
  }

  /**
   * Tries to read element from XML.
   *
   * @param reader The reader.
   * @return true, if successful
   * @throws Exception the exception
   */
  @Override
  public boolean tryReadElementFromXml(EwsServiceXmlReader reader)
      throws Exception {

    if (reader.getLocalName().equals(XmlElementNames.ExtendedFieldURI)) {
      this.propertyDefinition = new ExtendedPropertyDefinition();
      this.propertyDefinition.loadFromXml(reader);
      return true;
    } else if (reader.getLocalName().equals(XmlElementNames.Value)) {
      EwsUtilities.ewsAssert(this.getPropertyDefinition() != null, "ExtendedProperty.TryReadElementFromXml",
                             "PropertyDefintion is missing");
      String stringValue = reader.readElementValue();
      this.value = MapiTypeConverter.convertToValue(this.getPropertyDefinition().getMapiType(), stringValue);
      return true;
    } else if (reader.getLocalName().equals(XmlElementNames.Values)) {
      EwsUtilities.ewsAssert(this.getPropertyDefinition() != null, "ExtendedProperty.TryReadElementFromXml",
                             "PropertyDefintion is missing");

      StringList stringList = new StringList(XmlElementNames.Value);
      stringList.loadFromXml(reader, reader.getLocalName());
      this.value = MapiTypeConverter.convertToValue(this
          .getPropertyDefinition().getMapiType(), stringList
          .iterator());
      return true;
    } else {
      return false;
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
    this.getPropertyDefinition().writeToXml(writer);

    if (MapiTypeConverter.isArrayType(this.getPropertyDefinition()
        .getMapiType())) {
      ArrayList<?> array = (ArrayList<?>) this.getValue();
      writer
          .writeStartElement(XmlNamespace.Types,
              XmlElementNames.Values);
      for (int index = 0; index < array.size(); index++) {
        writer.writeElementValue(XmlNamespace.Types,
            XmlElementNames.Value, MapiTypeConverter
                .convertToString(this.getPropertyDefinition()
                    .getMapiType(), array.get(index)));
      }
      writer.writeEndElement();
    } else {
      writer.writeElementValue(XmlNamespace.Types, XmlElementNames.Value,
          MapiTypeConverter.convertToString(this
              .getPropertyDefinition().getMapiType(), this
              .getValue()));
    }
  }

  /**
   * Gets the definition of the extended property.
   *
   * @return The definition of the extended property.
   */
  public ExtendedPropertyDefinition getPropertyDefinition() {
    return this.propertyDefinition;
  }

  /**
   * Gets the value of the extended property.
   *
   * @return the value
   */
  public Object getValue() {
    return this.value;
  }

  /**
   * Sets the value of the extended property.
   *
   * @param val value of the extended property
   * @throws Exception the exception
   */
  public void setValue(Object val) throws Exception {
    EwsUtilities.validateParam(val, "value");
    if (this.canSetFieldValue(this.value, MapiTypeConverter.changeType(this
        .getPropertyDefinition().getMapiType(), val))) {
      this.value = MapiTypeConverter.changeType(this
          .getPropertyDefinition().getMapiType(), val);
      this.changed();
    }
  }

  /**
   * Gets the string value.
   *
   * @return String
   */
  private String getStringValue() {
    if (MapiTypeConverter.isArrayType(this.getPropertyDefinition()
        .getMapiType())) {
      ArrayList<?> array = (ArrayList<?>) this.getValue();
      if (array == null) {
        return null;
      } else {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int index = 0; index < array.size(); index++) {
          sb.append(MapiTypeConverter.convertToString(this
              .getPropertyDefinition().getMapiType(), array
              .get(index)));
          sb.append(",");
        }
        sb.append("]");

        return sb.toString();
      }
    } else {
      return MapiTypeConverter.convertToString(this
          .getPropertyDefinition().getMapiType(), this.getValue());
    }
  }

  /**
   * Determines whether the specified <see cref="T:System.Object"/> is equal
   * to the current <see cref="T:System.Object"/> true if the specified <see
   * cref="T:System.Object"/> is equal to the current <see
   * cref="T:System.Object"/>
   *
   * @param obj the obj
   * @return boolean
   */
  @Override
  public boolean equals(final Object obj) {
    if (obj instanceof ExtendedProperty) {
      final ExtendedProperty other = (ExtendedProperty) obj;
      return other.getPropertyDefinition().equals(this.getPropertyDefinition())
        && StringUtils.equals(this.getStringValue(), other.getStringValue());
    }
    return false;
  }

  /**
   * Serves as a hash function for a particular type.
   *
   * @return int
   */
  @Override
  public int hashCode() {
    String printableName = this.getPropertyDefinition() != null ? this
        .getPropertyDefinition().getPrintableName() : "";
    String stringVal = this.getStringValue();
    return (printableName + stringVal).hashCode();
  }
}
