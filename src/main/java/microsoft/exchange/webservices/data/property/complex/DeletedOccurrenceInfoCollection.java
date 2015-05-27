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
 * Represents a collection of deleted occurrence objects.
 */
@EditorBrowsable(state = EditorBrowsableState.Never)
public final class DeletedOccurrenceInfoCollection extends ComplexPropertyCollection<DeletedOccurrenceInfo> {

  /**
   * Initializes a new instance of the OccurrenceInfoCollection class.
   */
  public DeletedOccurrenceInfoCollection() {
  }

  /**
   * Creates the complex property.
   *
   * @param xmlElementName the xml element name
   * @return OccurenceInfo instance.
   */
  @Override
  protected DeletedOccurrenceInfo createComplexProperty(
      String xmlElementName) {
    if (xmlElementName.equalsIgnoreCase(XmlElementNames.DeletedOccurrence)) {
      return new DeletedOccurrenceInfo();
    } else {
      return null;
    }
  }

  /**
   * Gets the name of the collection item XML element.
   *
   * @param complexProperty the complex property
   * @return XML element name.
   */
  @Override
  protected String getCollectionItemXmlElementName(
      DeletedOccurrenceInfo complexProperty) {
    return XmlElementNames.Occurrence;
  }
}
