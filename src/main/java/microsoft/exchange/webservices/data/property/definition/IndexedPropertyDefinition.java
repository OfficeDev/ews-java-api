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

package microsoft.exchange.webservices.data.property.definition;

import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.XmlAttributeNames;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlSerializationException;

/**
 * Represents an indexed property definition.
 */
public final class IndexedPropertyDefinition extends
    ServiceObjectPropertyDefinition {

  // Index attribute of IndexedFieldURI element.
  /**
   * The index.
   */
  private String index;

  /**
   * Initializes a new instance of the IndexedPropertyDefinition class.
   *
   * @param uri   The FieldURI attribute of the IndexedFieldURI element.
   * @param index The Index attribute of the IndexedFieldURI element.
   */
  public IndexedPropertyDefinition(String uri, String index) {
    super(uri);
    this.index = index;
  }

  /**
   * Determines whether two specified instances of IndexedPropertyDefinition
   * are equal.
   *
   * @param idxPropDef1 First indexed property definition.
   * @param idxPropDef2 Second indexed property definition.
   * @return True if indexed property definitions are equal.
   */
  protected static boolean isEqualTo(IndexedPropertyDefinition idxPropDef1,
      IndexedPropertyDefinition idxPropDef2) {
    return (idxPropDef1 == idxPropDef2) ||
        (idxPropDef1 != null &&
            idxPropDef2 != null &&
            idxPropDef1.getUri().equalsIgnoreCase(
                idxPropDef2.getUri()) && idxPropDef1.index
            .equalsIgnoreCase(idxPropDef2.index));
  }

  /**
   * Gets the index of the property.
   *
   * @return The index string of the property.
   */
  public String getIndex() {
    return this.index;
  }

  /**
   * Writes the attribute to XML.
   *
   * @param writer the writer
   * @throws ServiceXmlSerializationException the service xml serialization exception
   */
  @Override
  protected void writeAttributesToXml(EwsServiceXmlWriter writer)
      throws ServiceXmlSerializationException {
    super.writeAttributesToXml(writer);
    writer.writeAttributeValue(XmlAttributeNames.FieldIndex, this
        .getIndex());
  }

  /**
   * Gets the name of the XML element.
   *
   * @return XML element name.
   */
  @Override
  protected String getXmlElementName() {
    return XmlElementNames.IndexedFieldURI;
  }

  /**
   * Gets the property definition's printable name.
   *
   * @return The property definition's printable name.
   */
  @Override public String getPrintableName() {
    return String.format("%s:%s", this.getUri(), this.getIndex());
  }


  /**
   * Determines whether a given indexed property definition is equal to this
   * indexed property definition.
   *
   * @param obj The
   *            object to check for equality.
   * @return True if the property definitions define the same indexed
   * property.
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj instanceof IndexedPropertyDefinition) {
      return IndexedPropertyDefinition.isEqualTo(
          (IndexedPropertyDefinition) obj, this);
    } else {
      return false;
    }
  }

  /**
   * Serves as a hash function for a particular type.
   *
   * @return A hash code for the current System.Object
   */
  @Override
  public int hashCode() {
    return this.getUri().hashCode() ^ this.getIndex().hashCode();
  }

  /**
   * Gets the property type.
   */
  @Override
  public Class<String> getType() {
    return String.class;
  }

}
