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
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an array of byte arrays
 */
public class ByteArrayArray extends ComplexProperty {
  final static String ItemXmlElementName = "Base64Binary";
  private List<byte[]> content = new ArrayList<byte[]>();

  public ByteArrayArray() {
  }

  /**
   * Gets the content of the array of byte arrays
   */
  public byte[][] getContent() {
    return (byte[][]) this.content.toArray();
  }

  /**
   * Tries to read element from XML.
   */
  public boolean tryReadElementFromXml(EwsServiceXmlReader reader)
      throws Exception {

    if (reader.getLocalName().equalsIgnoreCase(
        ByteArrayArray.ItemXmlElementName)) {
      this.content.add(reader.readBase64ElementValue());
      return true;
    } else {
      return false;
    }

  }

  /**
   * The Writer
   */
  public void writeElementsToXml(EwsServiceXmlWriter writer)
      throws Exception {
    for (byte[] item : this.content) {
      writer.writeStartElement(XmlNamespace.Types,
          ByteArrayArray.ItemXmlElementName);
      writer.writeBase64ElementValue(item);
      writer.writeEndElement();
    }

  }

}
