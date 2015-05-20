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
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.attribute.EditorBrowsableState;

/**
 * Represents a collection of Internet message headers.
 */
@EditorBrowsable(state = EditorBrowsableState.Never)
public final class InternetMessageHeaderCollection extends ComplexPropertyCollection<InternetMessageHeader> {
  /**
   * Initializes a new instance of the "InternetMessageHeaderCollection"
   * class.
   */
  public InternetMessageHeaderCollection() {
    super();
  }

  /**
   * Creates the complex property.
   *
   * @param xmlElementName Name of the XML element.
   * @return InternetMessageHeader instance
   */
  @Override
  protected InternetMessageHeader createComplexProperty(
      String xmlElementName) {
    return new InternetMessageHeader();
  }

  /**
   * Gets the name of the collection item XML element.
   *
   * @param complexProperty The complex property.
   * @return XML element name.
   */
  @Override
  protected String getCollectionItemXmlElementName(
      InternetMessageHeader complexProperty) {
    return XmlElementNames.InternetMessageHeader;
  }

  /**
   * Find a specific header in the collection.
   *
   * @param name The name of the header to locate.
   * @return An InternetMessageHeader representing the header with the
   * specified name; null if no header with the specified name was
   * found.
   */
  public InternetMessageHeader find(String name) {
    for (InternetMessageHeader internetMessageHeader : this) {
      if (name.compareTo(internetMessageHeader.getName()) == 0 &&
          name.equalsIgnoreCase(internetMessageHeader.getName())) {
        return internetMessageHeader;
      }
    }
    return null;
  }

}
