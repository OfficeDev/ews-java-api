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

import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.PropertyBag;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.property.PropertyDefinitionFlags;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.property.complex.ComplexProperty;
import microsoft.exchange.webservices.data.property.complex.ICreateComplexPropertyDelegate;

import java.util.EnumSet;

/**
 * Represents contained property definition.
 *
 * @param <TComplexProperty> The type of the complex property.
 */
public class ContainedPropertyDefinition<TComplexProperty extends ComplexProperty>
    extends ComplexPropertyDefinition<TComplexProperty> {

  /**
   * The contained xml element name.
   */
  private String containedXmlElementName;

  /**
   * Initializes a new instance of. ContainedPropertyDefinition
   *
   * @param xmlElementName           Name of the XML element.
   * @param uri                      The URI.
   * @param containedXmlElementName  Name of the contained XML element.
   * @param flags                    The flags.
   * @param version                  The version.
   * @param propertyCreationDelegate Delegate used to create instances of ComplexProperty.
   */
  public ContainedPropertyDefinition(Class<TComplexProperty> cls, String xmlElementName, String uri,
      String containedXmlElementName, EnumSet<PropertyDefinitionFlags> flags, ExchangeVersion version,
      ICreateComplexPropertyDelegate<TComplexProperty> propertyCreationDelegate) {
    super(cls, xmlElementName, uri, flags, version,
        propertyCreationDelegate);
    this.containedXmlElementName = containedXmlElementName;
  }

  /**
   * Load from XML.
   *
   * @param reader      the reader
   * @param propertyBag the property bag
   * @throws Exception the exception
   */
  @Override
  protected void internalLoadFromXml(EwsServiceXmlReader reader,
      PropertyBag propertyBag) throws Exception {
    reader.readStartElement(XmlNamespace.Types,
        this.containedXmlElementName);
    super.internalLoadFromXml(reader, propertyBag);
    reader.readEndElementIfNecessary(XmlNamespace.Types,
        this.containedXmlElementName);

  }

  /**
   * Writes to XML.
   *
   * @param writer            the writer
   * @param propertyBag       the property bag
   * @param isUpdateOperation the is update operation
   * @throws Exception the exception
   */
  @Override public void writePropertyValueToXml(EwsServiceXmlWriter writer, PropertyBag propertyBag,
      boolean isUpdateOperation)
      throws Exception {

    Object o = propertyBag.getObjectFromPropertyDefinition(this);
    if (o instanceof ComplexProperty) {
      ComplexProperty complexProperty = (ComplexProperty) o;
      writer.writeStartElement(XmlNamespace.Types, this.getXmlElement());
      complexProperty.writeToXml(writer, this.containedXmlElementName);
      writer.writeEndElement(); // this.XmlElementName
    }
  }
}
