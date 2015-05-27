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

import microsoft.exchange.webservices.data.attribute.EditorBrowsable;
import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.ICustomXmlUpdateSerializer;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.service.ServiceObject;
import microsoft.exchange.webservices.data.core.enumeration.attribute.EditorBrowsableState;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.exception.misc.ArgumentException;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlSerializationException;
import microsoft.exchange.webservices.data.misc.OutParam;
import microsoft.exchange.webservices.data.property.definition.ExtendedPropertyDefinition;
import microsoft.exchange.webservices.data.property.definition.PropertyDefinition;

import javax.xml.stream.XMLStreamException;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a collection of extended property.
 */
@EditorBrowsable(state = EditorBrowsableState.Never)
public final class ExtendedPropertyCollection extends ComplexPropertyCollection<ExtendedProperty> implements
                                                                                                  ICustomXmlUpdateSerializer {

  /**
   * Creates the complex property.
   *
   * @param xmlElementName Name of the XML element.
   * @return Complex property instance.
   */
  @Override
  protected ExtendedProperty createComplexProperty(String xmlElementName) {
    // This method is unused in this class, so just return null.
    return null;
  }

  /**
   * Gets the name of the collection item XML element.
   *
   * @param complexProperty The complex property.
   * @return XML element name.
   */
  @Override
  protected String getCollectionItemXmlElementName(
      ExtendedProperty complexProperty) {
    // This method is unused in this class, so just return null.
    return null;
  }

  /**
   * Loads from XML.
   *
   * @param reader           The reader.
   * @param localElementName Name of the local element.
   * @throws Exception the exception
   */
  @Override public void loadFromXml(EwsServiceXmlReader reader, String localElementName) throws Exception {
    ExtendedProperty extendedProperty = new ExtendedProperty();
    extendedProperty.loadFromXml(reader, reader.getLocalName());
    this.internalAdd(extendedProperty);
  }

  /**
   * Writes to XML.
   *
   * @param writer         The writer.
   * @param xmlElementName Name of the XML element.
   * @throws Exception the exception
   */
  @Override public void writeToXml(EwsServiceXmlWriter writer, String xmlElementName)
      throws Exception {
    for (ExtendedProperty extendedProperty : this) {
      extendedProperty.writeToXml(writer,
          XmlElementNames.ExtendedProperty);
    }
  }

  /**
   * Gets existing or adds new extended property.
   *
   * @param propertyDefinition The property definition.
   * @return ExtendedProperty.
   * @throws Exception the exception
   */
  private ExtendedProperty getOrAddExtendedProperty(
      ExtendedPropertyDefinition propertyDefinition) throws Exception {
    ExtendedProperty extendedProperty = null;
    OutParam<ExtendedProperty> extendedPropertyOut =
        new OutParam<ExtendedProperty>();
    if (!this.tryGetProperty(propertyDefinition, extendedPropertyOut)) {
      extendedProperty = new ExtendedProperty(propertyDefinition);
      this.internalAdd(extendedProperty);
    } else {
      extendedProperty = extendedPropertyOut.getParam();
    }
    return extendedProperty;
  }

  /**
   * Sets an extended property.
   *
   * @param propertyDefinition The property definition.
   * @param value              The value.
   * @throws Exception the exception
   */
  public void setExtendedProperty(ExtendedPropertyDefinition propertyDefinition, Object value)
      throws Exception {
    ExtendedProperty extendedProperty = this
        .getOrAddExtendedProperty(propertyDefinition);
    extendedProperty.setValue(value);
  }

  /**
   * Removes a specific extended property definition from the collection.
   *
   * @param propertyDefinition The definition of the extended property to remove.
   * @return True if the property matching the extended property definition
   * was successfully removed from the collection, false otherwise.
   * @throws Exception the exception
   */
  public boolean removeExtendedProperty(ExtendedPropertyDefinition propertyDefinition) throws Exception {
    EwsUtilities.validateParam(propertyDefinition, "propertyDefinition");

    ExtendedProperty extendedProperty = null;
    OutParam<ExtendedProperty> extendedPropertyOut =
        new OutParam<ExtendedProperty>();
    if (this.tryGetProperty(propertyDefinition, extendedPropertyOut)) {
      extendedProperty = extendedPropertyOut.getParam();
      return this.internalRemove(extendedProperty);
    } else {
      return false;
    }
  }

  /**
   * Tries to get property.
   *
   * @param propertyDefinition  The property definition.
   * @param extendedPropertyOut The extended property.
   * @return True of property exists in collection.
   */
  private boolean tryGetProperty(
      ExtendedPropertyDefinition propertyDefinition,
      OutParam<ExtendedProperty> extendedPropertyOut) {
    boolean found = false;
    extendedPropertyOut.setParam(null);
    for (ExtendedProperty prop : this.getItems()) {
      if (prop.getPropertyDefinition().equals(propertyDefinition)) {
        found = true;
        extendedPropertyOut.setParam(prop);
        break;
      }
    }
    return found;
  }

  /**
   * Tries to get property value.
   *
   * @param propertyDefinition The property definition.
   * @param propertyValueOut   The property value.
   * @return True if property exists in collection.
   * @throws ArgumentException
   */
  public <T> boolean tryGetValue(Class<T> cls, ExtendedPropertyDefinition propertyDefinition,
      OutParam<T> propertyValueOut) throws ArgumentException {
    ExtendedProperty extendedProperty = null;
    OutParam<ExtendedProperty> extendedPropertyOut =
        new OutParam<ExtendedProperty>();
    if (this.tryGetProperty(propertyDefinition, extendedPropertyOut)) {
      extendedProperty = extendedPropertyOut.getParam();
      if (!cls.isAssignableFrom(propertyDefinition.getType())) {
        String errorMessage = String.format(
            "Property definition type '%s' and type parameter '%s' aren't compatible.",
            propertyDefinition.getType().getSimpleName(),
            cls.getSimpleName());
        throw new ArgumentException(errorMessage, "propertyDefinition");
      }
      propertyValueOut.setParam((T) extendedProperty.getValue());
      return true;
    } else {
      propertyValueOut.setParam(null);
      return false;
    }
  }


  /**
   * Writes the update to XML.
   *
   * @param writer             The writer.
   * @param ewsObject          The ews object.
   * @param propertyDefinition Property definition.
   * @return True if property generated serialization.
   * @throws Exception the exception
   */
  @Override
  public boolean writeSetUpdateToXml(EwsServiceXmlWriter writer,
      ServiceObject ewsObject, PropertyDefinition propertyDefinition)
      throws Exception {
    List<ExtendedProperty> propertiesToSet =
        new ArrayList<ExtendedProperty>();

    propertiesToSet.addAll(this.getAddedItems());
    propertiesToSet.addAll(this.getModifiedItems());

    for (ExtendedProperty extendedProperty : propertiesToSet) {
      writer.writeStartElement(XmlNamespace.Types, ewsObject
          .getSetFieldXmlElementName());
      extendedProperty.getPropertyDefinition().writeToXml(writer);

      writer.writeStartElement(XmlNamespace.Types, ewsObject
          .getXmlElementName());
      extendedProperty.writeToXml(writer,
          XmlElementNames.ExtendedProperty);
      writer.writeEndElement();

      writer.writeEndElement();
    }

    for (ExtendedProperty extendedProperty : this.getRemovedItems()) {
      writer.writeStartElement(XmlNamespace.Types, ewsObject
          .getDeleteFieldXmlElementName());
      extendedProperty.getPropertyDefinition().writeToXml(writer);
      writer.writeEndElement();
    }

    return true;
  }

  /**
   * Writes the deletion update to XML.
   *
   * @param writer    the writer
   * @param ewsObject the ews object
   * @return true if property generated serialization
   * @throws XMLStreamException the XML stream exception
   * @throws ServiceXmlSerializationException the service xml serialization exception
   */
  @Override
  public boolean writeDeleteUpdateToXml(EwsServiceXmlWriter writer,
      ServiceObject ewsObject) throws XMLStreamException, ServiceXmlSerializationException {
    for (ExtendedProperty extendedProperty : this.getItems()) {
      writer.writeStartElement(XmlNamespace.Types, ewsObject
          .getDeleteFieldXmlElementName());
      extendedProperty.getPropertyDefinition().writeToXml(writer);
      writer.writeEndElement();
    }

    return true;
  }
}
