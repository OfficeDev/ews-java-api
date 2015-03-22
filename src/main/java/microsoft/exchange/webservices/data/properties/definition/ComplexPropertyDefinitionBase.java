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

package microsoft.exchange.webservices.data.properties.definition;

import microsoft.exchange.webservices.data.ComplexProperty;
import microsoft.exchange.webservices.data.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.OutParam;
import microsoft.exchange.webservices.data.PropertyBag;
import microsoft.exchange.webservices.data.ServiceObject;
import microsoft.exchange.webservices.data.enumerations.ExchangeVersion;
import microsoft.exchange.webservices.data.enumerations.PropertyDefinitionFlags;
import microsoft.exchange.webservices.data.enumerations.XmlNamespace;

import java.util.EnumSet;

/**
 * Represents abstract complex property definition.
 */
abstract class ComplexPropertyDefinitionBase extends PropertyDefinition {

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
  protected abstract ComplexProperty createPropertyInstance(
      ServiceObject owner);

  /**
   * Internals the load from XML.
   *
   * @param reader      The reader.
   * @param propertyBag The property bag.
   * @throws Exception the exception
   */
  protected void internalLoadFromXml(EwsServiceXmlReader reader,
      PropertyBag propertyBag) throws Exception {
    OutParam<Object> complexProperty = new OutParam<Object>();

    boolean justCreated = getPropertyInstance(propertyBag, complexProperty);
    if (!justCreated && this.hasFlag(PropertyDefinitionFlags.UpdateCollectionItems,
        propertyBag.getOwner().getService().getRequestedServerVersion())) {
      ComplexProperty c = (ComplexProperty) complexProperty.getParam();
      if (complexProperty.getParam() instanceof ComplexProperty) {
        c.updateFromXml(reader, reader.getLocalName());
      }



    } else {
      ComplexProperty c = (ComplexProperty) complexProperty.getParam();
      c.loadFromXml(reader, reader.getLocalName());
    }
                /*if (!propertyBag.tryGetValue(this, complexProperty) ||
                                 !this.hasFlag(PropertyDefinitionFlags.ReuseInstance)) {
			complexProperty.setParam(this.createPropertyInstance(propertyBag
					.getOwner()));
		}
		if (complexProperty.getParam() instanceof ComplexProperty) {
			ComplexProperty c = (ComplexProperty)complexProperty.getParam();
			c.loadFromXml(reader, reader.getLocalName());
		}*/
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
  private boolean getPropertyInstance(PropertyBag propertyBag, OutParam<Object> complexProperty) {
    boolean retValue = false;
    if (!propertyBag.tryGetValue(this, complexProperty) || !this
        .hasFlag(PropertyDefinitionFlags.ReuseInstance,
            propertyBag.getOwner().getService().getRequestedServerVersion())) {
      complexProperty.setParam(this.createPropertyInstance(propertyBag
          .getOwner()));
      retValue = true;
    }
    return retValue;

  }

  /**
   * Loads from XML.
   *
   * @param reader      The reader.
   * @param propertyBag The property bag.
   * @throws Exception the exception
   */
  @Override
  protected void loadPropertyValueFromXml(EwsServiceXmlReader reader,
      PropertyBag propertyBag) throws Exception {
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
  @Override
  protected void writePropertyValueToXml(EwsServiceXmlWriter writer,
      PropertyBag propertyBag, boolean isUpdateOperation)
      throws Exception {
    ComplexProperty complexProperty = (ComplexProperty) propertyBag
        .getObjectFromPropertyDefinition(this);
    if (complexProperty != null) {
      complexProperty.writeToXml(writer, this.getXmlElement());
    }
  }
}
