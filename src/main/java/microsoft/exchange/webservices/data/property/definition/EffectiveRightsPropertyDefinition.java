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
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.service.EffectiveRights;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.property.PropertyDefinitionFlags;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;

import java.util.EnumSet;

/**
 * Represents effective rights property definition.
 */
public final class EffectiveRightsPropertyDefinition extends PropertyDefinition {

  /**
   * Initializes a new instance of the EffectiveRightsPropertyDefinition.
   *
   * @param xmlElementName the xml element name
   * @param uri            the uri
   * @param flags          the flags
   * @param version        the version
   */
  public EffectiveRightsPropertyDefinition(String xmlElementName, String uri,
      EnumSet<PropertyDefinitionFlags> flags, ExchangeVersion version) {
    super(xmlElementName, uri, flags, version);

  }

  /**
   * Loads from XML.
   *
   * @param reader      the reader
   * @param propertyBag the property bag
   * @throws Exception the exception
   */
  public void loadPropertyValueFromXml(EwsServiceXmlReader reader, PropertyBag propertyBag) throws Exception {
    EnumSet<EffectiveRights> value = EnumSet.noneOf(EffectiveRights.class);
    value.add(EffectiveRights.None);

    reader.ensureCurrentNodeIsStartElement(XmlNamespace.Types, this
        .getXmlElement());

    if (!reader.isEmptyElement()) {
      do {
        reader.read();

        if (reader.isStartElement()) {

          if (reader.getLocalName().equals(
              XmlElementNames.CreateAssociated)) {

            if (reader.readElementValue(Boolean.class)) {
              value.add(EffectiveRights.CreateAssociated);
            }
          } else if (reader.getLocalName().equals(
              XmlElementNames.CreateContents)) {

            if (reader.readElementValue(Boolean.class)) {
              value.add(EffectiveRights.CreateContents);
            }
          } else if (reader.getLocalName().equals(
              XmlElementNames.CreateHierarchy)) {

            if (reader.readElementValue(Boolean.class)) {
              value.add(EffectiveRights.CreateHierarchy);
            }
          } else if (reader.getLocalName().equals(
              XmlElementNames.Delete)) {

            if (reader.readElementValue(Boolean.class)) {
              value.add(EffectiveRights.Delete);
            }
          } else if (reader.getLocalName().equals(
              XmlElementNames.Modify)) {

            if (reader.readElementValue(Boolean.class)) {
              value.add(EffectiveRights.Modify);
            }
          } else if (reader.getLocalName().equals(XmlElementNames.Read)) {
            if (reader.readElementValue(Boolean.class)) {
              value.add(EffectiveRights.Read);
            } else if (reader.getLocalName().equals(XmlElementNames.ViewPrivateItems)) {
              if (reader.readElementValue(Boolean.class)) {
                value.add(EffectiveRights.ViewPrivateItems);
              }
            }

          }
        }

      } while (!reader.isEndElement(XmlNamespace.Types, this
          .getXmlElement()));
    }
    propertyBag.setObjectFromPropertyDefinition(this, value);
  }

  /**
   * Writes to XML.
   *
   * @param writer            the writer
   * @param propertyBag       the property bag
   * @param isUpdateOperation the is update operation
   */
  public void writePropertyValueToXml(EwsServiceXmlWriter writer, PropertyBag propertyBag,
      boolean isUpdateOperation) {
    // EffectiveRights is a read-only property, no need to implement this.
  }

  /**
   * Gets the property type.
   */
  @Override
  public Class<EffectiveRights> getType() {
    return EffectiveRights.class;
  }
}
