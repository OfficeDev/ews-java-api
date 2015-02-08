package microsoft.exchange.webservices.data;

import javax.xml.stream.XMLStreamException;

/**
 * Represents the MIME content of an item.
 */
public final class MimeContent extends ComplexProperty {

  /**
   * The character set.
   */
  private String characterSet;

  /**
   * The content.
   */
  private byte[] content;

  /**
   * Initializes a new instance of the class.
   */
  public MimeContent() {
  }

  /**
   * Initializes a new instance of the class.
   *
   * @param characterSet the character set
   * @param content      the content
   */
  public MimeContent(String characterSet, byte[] content) {
    this();
    this.characterSet = characterSet;
    this.content = content;
  }

  /**
   * Reads attributes from XML.
   *
   * @param reader the reader
   * @throws Exception the exception
   */
  @Override
  protected void readAttributesFromXml(EwsServiceXmlReader reader)
      throws Exception {
    this.characterSet = reader.readAttributeValue(String.class,
        XmlAttributeNames.CharacterSet);
  }

  /**
   * Reads text value from XML.
   *
   * @param reader the reader
   * @throws javax.xml.stream.XMLStreamException the xML stream exception
   * @throws ServiceXmlDeserializationException  the service xml deserialization exception
   */
  @Override
  protected void readTextValueFromXml(EwsServiceXmlReader reader)
      throws XMLStreamException, ServiceXmlDeserializationException {
    this.content = Base64EncoderStream.decode(reader.readValue());
  }

  /**
   * Writes attributes to XML.
   *
   * @param writer the writer
   * @throws ServiceXmlSerializationException the service xml serialization exception
   */
  @Override
  protected void writeAttributesToXml(EwsServiceXmlWriter writer)
      throws ServiceXmlSerializationException {
    writer.writeAttributeValue(XmlAttributeNames.CharacterSet,
        this.characterSet);
  }

  /**
   * Writes elements to XML.
   *
   * @param writer the writer
   * @throws javax.xml.stream.XMLStreamException the xML stream exception
   */
  protected void writeElementsToXml(EwsServiceXmlWriter writer)
      throws XMLStreamException {
    if (this.content != null && this.content.length > 0) {
      writer.writeBase64ElementValue(this.content);
    }
  }

  /**
   * Gets  the character set of the content.
   *
   * @return the character set
   */
  public String getCharacterSet() {
    return this.characterSet;
  }

  /**
   * Sets the character set.
   *
   * @param characterSet the new character set
   */
  public void setCharacterSet(String characterSet) {
    this.canSetFieldValue(this.characterSet, characterSet);
  }

  /**
   * Gets  the character set of the content.
   *
   * @return the content
   */
  public byte[] getContent() {
    return this.content;
  }

  /**
   * Sets the content.
   *
   * @param content the new content
   */
  public void setContent(byte[] content) {
    this.canSetFieldValue(this.content, content);
  }

  /**
   * Writes attributes to XML.
   *
   * @return the string
   */
  @Override
  public String toString() {
    if (this.getContent() == null) {
      return "";
    } else {
      try {

        // Try to convert to original MIME content using specified
        // charset. If this fails,
        // return the Base64 representation of the content.
        // Note: Encoding.GetString can throw DecoderFallbackException
        // which is a subclass
        // of ArgumentException.
        String charSet = (this.getCharacterSet() == null ||
            this.getCharacterSet().isEmpty()) ?
            "UTF-8" : this.getCharacterSet();
        return new String(this.getContent(), charSet);
      } catch (Exception e) {
        return Base64EncoderStream.encode(this.getContent());
      }
    }
  }

}
