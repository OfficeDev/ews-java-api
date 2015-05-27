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

package microsoft.exchange.webservices.data.misc.id;

import microsoft.exchange.webservices.data.ISelfValidate;
import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.XmlAttributeNames;
import microsoft.exchange.webservices.data.core.enumeration.misc.IdFormat;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlSerializationException;

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
   * Writes the attribute to XML.
   *
   * @param writer the writer
   * @throws ServiceXmlSerializationException the service xml serialization exception
   */
  protected void writeAttributesToXml(EwsServiceXmlWriter writer)
      throws ServiceXmlSerializationException {
    writer.writeAttributeValue(XmlAttributeNames.Format, this.getFormat());
  }

  /**
   * Loads the attribute from XML.
   *
   * @param reader the reader
   * @throws Exception the exception
   */
  public void loadAttributesFromXml(EwsServiceXmlReader reader)
      throws Exception {
    this.setFormat(reader.readAttributeValue(IdFormat.class,
        XmlAttributeNames.Format));
  }

  /**
   * Writes to XML.
   *
   * @param writer the writer
   * @throws ServiceXmlSerializationException the service xml serialization exception
   * @throws XMLStreamException the XML stream exception
   */
  public void writeToXml(EwsServiceXmlWriter writer)
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
