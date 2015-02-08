package microsoft.exchange.webservices.data;

import javax.xml.stream.XMLStreamException;

/**
 * Represents the base class for Id expressed in a specific format.
 */
public abstract class AlternateIdBase implements ISelfValidate {

  /**
   * Id format.
   */
  private IdFormat format;

  /**
   * Initializes a new instance of the class.
   */
  protected AlternateIdBase() {
  }

  /**
   * Initializes a new instance of the class.
   *
   * @param format the format
   */
  protected AlternateIdBase(IdFormat format) {
    super();
    this.format = format;
  }

  /**
   * Gets the format in which the Id in expressed.
   *
   * @return the format
   */
  public IdFormat getFormat() {
    return this.format;
  }

  /**
   * Sets the format.
   *
   * @param format the new format
   */
  public void setFormat(IdFormat format) {
    this.format = format;
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
   * @param writer the writer
   * @throws ServiceXmlSerializationException the service xml serialization exception
   */
  protected void writeAttributesToXml(EwsServiceXmlWriter writer)
      throws ServiceXmlSerializationException {
    writer.writeAttributeValue(XmlAttributeNames.Format, this.getFormat());
  }

  /**
   * Loads the attributes from XML.
   *
   * @param reader the reader
   * @throws Exception the exception
   */
  protected void loadAttributesFromXml(EwsServiceXmlReader reader)
      throws Exception {
    this.setFormat(reader.readAttributeValue(IdFormat.class,
        XmlAttributeNames.Format));
  }

  /**
   * Writes to XML.
   *
   * @param writer the writer
   * @throws ServiceXmlSerializationException    the service xml serialization exception
   * @throws javax.xml.stream.XMLStreamException the xML stream exception
   */
  protected void writeToXml(EwsServiceXmlWriter writer)
      throws ServiceXmlSerializationException, XMLStreamException {
    writer.writeStartElement(XmlNamespace.Types, this.getXmlElementName());
    this.writeAttributesToXml(writer);
    writer.writeEndElement(); // this.GetXmlElementName()
  }

  /**
   * Validate this instance.
   *
   * @throws Exception
   */
  protected void internalValidate() throws Exception {
    // nothing to do.
  }

  /**
   * Validates this instance.
   *
   * @throws Exception
   */
  public void validate() throws Exception {
    this.internalValidate();
  }

}
