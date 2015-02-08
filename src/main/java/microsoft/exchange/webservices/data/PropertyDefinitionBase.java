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
