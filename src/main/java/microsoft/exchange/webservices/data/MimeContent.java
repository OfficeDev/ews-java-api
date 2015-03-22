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

package microsoft.exchange.webservices.data;

import microsoft.exchange.webservices.data.exceptions.ServiceXmlDeserializationException;
import microsoft.exchange.webservices.data.exceptions.ServiceXmlSerializationException;

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
   * @throws microsoft.exchange.webservices.data.exceptions.ServiceXmlDeserializationException  the service xml deserialization exception
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
