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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.stream.XMLStreamException;
import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.XmlAttributeNames;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlDeserializationException;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlSerializationException;
import microsoft.exchange.webservices.data.util.FileUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

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
  private InputStream contentReader;

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
    this.contentReader = new ByteArrayInputStream(content);
  }

  public MimeContent(InputStream reader) {
    this();
    this.contentReader = reader;
  }

  /**
   * Reads attribute from XML.
   *
   * @param reader the reader
   * @throws Exception the exception
   */
  @Override
  public void readAttributesFromXml(EwsServiceXmlReader reader)
      throws Exception {
    this.characterSet = reader.readAttributeValue(String.class,
                                                  XmlAttributeNames.CharacterSet);
  }

  /**
   * Reads text value from XML.
   *
   * @param reader the reader
   * @throws XMLStreamException the XML stream exception
   * @throws ServiceXmlDeserializationException the service xml deserialization exception
   */
  @Override
  public void readTextValueFromXml(EwsServiceXmlReader reader)
      throws XMLStreamException, ServiceXmlDeserializationException {
    this.contentReader = new ByteArrayInputStream(Base64.decodeBase64(reader.readValue()));
  }

  /**
   * Writes attribute to XML.
   *
   * @param writer the writer
   * @throws ServiceXmlSerializationException the service xml serialization exception
   */
  @Override
  public void writeAttributesToXml(EwsServiceXmlWriter writer)
      throws ServiceXmlSerializationException {
    writer.writeAttributeValue(XmlAttributeNames.CharacterSet,
                               this.characterSet);
  }

  /**
   * Writes elements to XML.
   *
   * @param writer the writer
   */
  public void writeElementsToXml(EwsServiceXmlWriter writer) throws XMLStreamException {
    if (this.contentReader != null) {
      try {
        writer.writeBase64ElementValue(this.contentReader);
      } catch (IOException e) {
        throw new XMLStreamException(e);
      } finally {
        IOUtils.closeQuietly(contentReader);
        contentReader = null;
      }
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
    if (contentReader != null) {
      final ByteArrayOutputStream output = new ByteArrayOutputStream(4096);
      try {
        FileUtils.copyLarge(contentReader, output);
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        IOUtils.closeQuietly(output);
      }

      return output.toByteArray();
    }

    return null;
  }

  /**
   * Sets the content.
   *
   * @param content the new content
   */
  public void setContent(byte[] content) {
    this.contentReader = new ByteArrayInputStream(content);
  }

  /**
   * Writes attribute to XML.
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
        return Base64.encodeBase64String(this.getContent());
      }
    }
  }

}
