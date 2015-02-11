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

import javax.xml.stream.XMLStreamException;

/**
 * Represents an entry of a DictionaryProperty object.
 * <p/>
 * All descendants of DictionaryEntryProperty must implement a parameterless
 * constructor. That constructor does not have to be public. That constructor
 * does not have to be public.
 *
 * @param <TKey> the generic type
 */
@EditorBrowsable(state = EditorBrowsableState.Never)
public abstract class DictionaryEntryProperty<TKey> extends ComplexProperty {

  /**
   * The key.
   */
  private TKey key;
  private Class<TKey> instance;

  /**
   * Initializes a new instance of the "DictionaryEntryProperty&lt;TKey&gt;"
   * class.
   */
  protected DictionaryEntryProperty(Class<TKey> cls) {
    this.instance = cls;
  }

  /**
   * Initializes a new instance of the "DictionaryEntryProperty&lt;TKey&gt;"
   * class.
   *
   * @param key The key.
   */
  protected DictionaryEntryProperty(Class<TKey> cls, TKey key) {
    super();
    this.key = key;
    this.instance = cls;
  }

  /**
   * Gets the key.
   *
   * @return the key
   */
  protected TKey getKey() {
    return key;
  }

  /**
   * Sets the key.
   *
   * @param value the value to set
   */
  protected void setKey(TKey value) {
    this.key = value;
  }

  /**
   * Reads the attributes from XML.
   *
   * @param reader accepts EwsServiceXmlReader
   * @throws Exception throws Exception
   */
  @Override
  protected void readAttributesFromXml(EwsServiceXmlReader reader)
      throws Exception {
    this.key = reader.readAttributeValue(instance,
        XmlAttributeNames.Key);
  }

  /**
   * Writes the attributes to XML.
   *
   * @param writer accepts EwsServiceXmlWriter
   * @throws ServiceXmlSerializationException throws ServiceXmlSerializationException
   */
  @Override
  protected void writeAttributesToXml(EwsServiceXmlWriter writer)
      throws ServiceXmlSerializationException {
    writer.writeAttributeValue(XmlAttributeNames.Key, this.getKey());
  }

  /**
   * Writes the set update to XML.
   *
   * @param writer                        The writer.
   * @param ewsObject                     The ews object.
   * @param ownerDictionaryXmlElementName Name of the owner dictionary XML element.
   * @return True if update XML was written.
   * @throws javax.xml.stream.XMLStreamException the xML stream exception
   * @throws ServiceXmlSerializationException    the service xml serialization exception
   */
  protected boolean writeSetUpdateToXml(EwsServiceXmlWriter writer,
      ServiceObject ewsObject, String ownerDictionaryXmlElementName)
      throws XMLStreamException, ServiceXmlSerializationException {
    return false;
  }

  /**
   * Writes the delete update to XML.
   *
   * @param writer    The writer.
   * @param ewsObject The ews object.
   * @return True if update XML was written.
   * @throws javax.xml.stream.XMLStreamException the xML stream exception
   * @throws ServiceXmlSerializationException    the service xml serialization exception
   */
  protected boolean writeDeleteUpdateToXml(EwsServiceXmlWriter writer,
      ServiceObject ewsObject) throws XMLStreamException,
      ServiceXmlSerializationException {
    return false;
  }

}
