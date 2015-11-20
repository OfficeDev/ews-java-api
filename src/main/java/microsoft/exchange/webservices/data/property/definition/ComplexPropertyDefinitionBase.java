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
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.PropertyBag;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.enumeration.property.PropertyDefinitionFlags;
import microsoft.exchange.webservices.data.core.service.ServiceObject;
import microsoft.exchange.webservices.data.misc.OutParam;
import microsoft.exchange.webservices.data.property.complex.ComplexProperty;

import java.util.EnumSet;

/**
 * Represents abstract complex property definition.
 */
public abstract class ComplexPropertyDefinitionBase extends PropertyDefinition {

  /**
   * Initializes a new instance.
   *
   * @param xmlElementName Name of the XML element.
   * @param flags          The flags.
   * @param version        The version.
   */
  protected ComplexPropertyDefinitionBase(String xmlElementName,
      EnumSet<PropertyDefinitionFlags> flags, ExchangeVersion version) {
    super(xmlElementName, flags, version);
  }

  /**
   * Initializes a new instance.
   *
   * @param xmlElementName Name of the XML element.
   * @param uri            The URI.
   * @param version        The version.
   */
  protected ComplexPropertyDefinitionBase(String xmlElementName, String uri,
      ExchangeVersion version) {
    super(xmlElementName, uri, version);
  }

  /**
   * Initializes a new instance.
   *
   * @param xmlElementName Name of the XML element.
   * @param uri            The URI.
   * @param flags          The flags.
   * @param version        The version.
   */
  protected ComplexPropertyDefinitionBase(String xmlElementName, String uri,
      EnumSet<PropertyDefinitionFlags> flags, ExchangeVersion version) {
    super(xmlElementName, uri, flags, version);
  }

  /**
   * Creates the property instance.
   *
   * @param owner The owner.
   * @return ComplexProperty.
   */
  public abstract ComplexProperty createPropertyInstance(ServiceObject owner);

  /**
   * Internals the load from XML.
   *
   * @param reader      The reader.
   * @param propertyBag The property bag.
   * @throws Exception the exception
   */
  protected void internalLoadFromXml(
    final EwsServiceXmlReader reader, final PropertyBag propertyBag
  ) throws Exception {
    final OutParam<ComplexProperty> complexProperty = new OutParam<ComplexProperty>();
    final boolean justCreated = getPropertyInstance(propertyBag, complexProperty);

    if (!justCreated && this.hasFlag(PropertyDefinitionFlags.UpdateCollectionItems,
        propertyBag.getOwner().getService().getRequestedServerVersion())) {
      final ComplexProperty c = complexProperty.getParam();
      c.updateFromXml(reader, reader.getLocalName());
    } else {
      final ComplexProperty c = complexProperty.getParam();
      c.loadFromXml(reader, reader.getLocalName());
    }

    propertyBag.setObjectFromPropertyDefinition(this, complexProperty
        .getParam());
  }



  /**
   * Gets the property instance.
   *
   * @param propertyBag     The property bag.
   * @param complexProperty The property instance.
   * @return True if the instance is newly created.
   */
  private boolean getPropertyInstance(
    final PropertyBag propertyBag, final OutParam<ComplexProperty> complexProperty
  ) {
    final ServiceObject owner = propertyBag.getOwner();
    final ExchangeService service = owner.getService();

    if (!propertyBag.tryGetValue(this, complexProperty)
        || !hasFlag(PropertyDefinitionFlags.ReuseInstance, service.getRequestedServerVersion())) {
      complexProperty.setParam(createPropertyInstance(owner));
      return true;
    }
    return false;
  }

  /**
   * Loads from XML.
   *
   * @param reader      The reader.
   * @param propertyBag The property bag.
   * @throws Exception the exception
   */
  @Override public void loadPropertyValueFromXml(EwsServiceXmlReader reader, PropertyBag propertyBag) throws Exception {
    reader.ensureCurrentNodeIsStartElement(XmlNamespace.Types, this
        .getXmlElement());

    if (!reader.isEmptyElement() || reader.hasAttributes()) {
      this.internalLoadFromXml(reader, propertyBag);
    }
    reader.readEndElementIfNecessary(XmlNamespace.Types, this
        .getXmlElement());
  }

  /**
   * Writes to XML.
   *
   * @param writer            The writer.
   * @param propertyBag       The property bag.
   * @param isUpdateOperation Indicates whether the context is an update operation.
   * @throws Exception the exception
   */
  @Override public void writePropertyValueToXml(EwsServiceXmlWriter writer, PropertyBag propertyBag,
      boolean isUpdateOperation)
      throws Exception {
    ComplexProperty complexProperty =
      propertyBag.getObjectFromPropertyDefinition(this);
    if (complexProperty != null) {
      complexProperty.writeToXml(writer, this.getXmlElement());
    }
  }
}
