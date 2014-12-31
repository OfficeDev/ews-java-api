/**************************************************************************
 Exchange Web Services Java API
 Copyright (c) Microsoft Corporation
 All rights reserved.
 MIT License
 Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the ""Software""), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 THE SOFTWARE IS PROVIDED *AS IS*, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 **************************************************************************/

package microsoft.exchange.webservices.data;

import javax.xml.stream.XMLStreamException;

/**
 * Represents the base class for all property definitions.
 */
public abstract class PropertyDefinitionBase {

  /**
   * Initializes a new instance.
   */
  protected PropertyDefinitionBase() {

  }

  /**
   * Tries to load from XML.
   *
   * @param reader             The reader.
   * @param propertyDefinition The property definition.
   * @return True if property was loaded.
   * @throws Exception the exception
   */
  protected static boolean tryLoadFromXml(EwsServiceXmlReader reader,
      OutParam<PropertyDefinitionBase> propertyDefinition)
      throws Exception {
    String strLocalName = reader.getLocalName();
    if (strLocalName.equals(XmlElementNames.FieldURI)) {
      PropertyDefinitionBase p = ServiceObjectSchema
          .findPropertyDefinition(reader
              .readAttributeValue(XmlAttributeNames.FieldURI));
      propertyDefinition.setParam(p);
      return true;
    } else if (strLocalName.equals(XmlElementNames.IndexedFieldURI)) {
      reader.skipCurrentElement();
      return true;
    } else if (strLocalName.equals(XmlElementNames.ExtendedFieldURI)) {
      ExtendedPropertyDefinition p = new ExtendedPropertyDefinition();
      p.loadFromXml(reader);
      propertyDefinition.setParam(p);
      return true;
    } else {
      return false;
    }

  }

  /**
   * Gets the name of the XML element.
   *
   * @return XML element name.
   */
  protected abstract String getXmlElementName();

  /**
   * Writes the attributes to XML.
   *
   * @param writer The writer.
   * @throws ServiceXmlSerializationException the service xml serialization exception
   */
  protected abstract void writeAttributesToXml(EwsServiceXmlWriter writer)
      throws ServiceXmlSerializationException;

  /**
   * Gets the minimum Exchange version that supports this property.
   *
   * @return The version.
   */
  public abstract ExchangeVersion getVersion();

  /**
   * Gets the property definition's printable name.
   *
   * @return The property definition's printable name.
   */
  protected abstract String getPrintableName();

  /**
   * Gets the type of the property.
   */
  public abstract Class<?> getType();

  /**
   * Writes to XML.
   *
   * @param writer The writer.
   * @throws javax.xml.stream.XMLStreamException the xML stream exception
   * @throws ServiceXmlSerializationException    the service xml serialization exception
   */
  protected void writeToXml(EwsServiceXmlWriter writer)
      throws XMLStreamException, ServiceXmlSerializationException {
    writer.writeStartElement(XmlNamespace.Types, this.getXmlElementName());
    this.writeAttributesToXml(writer);
    writer.writeEndElement();
  }

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Object#toString()
   */
  @Override
  /**
   * Returns a string that represents the current object.
   * @return A string that represents the current object.
   */
  public String toString() {
    return this.getPrintableName();
  }
}
