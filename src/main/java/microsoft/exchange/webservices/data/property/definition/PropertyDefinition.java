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
import microsoft.exchange.webservices.data.core.service.schema.ServiceObjectSchema;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.property.PropertyDefinitionFlags;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * Represents the definition of a folder or item property.
 */
public abstract class PropertyDefinition extends
    ServiceObjectPropertyDefinition {

  /**
   * The xml element name.
   */
  private String xmlElementName;

  /**
   * The flags.
   */
  private EnumSet<PropertyDefinitionFlags> flags;

  /**
   * The name.
   */
  private String name;

  /**
   * The version.
   */
  private ExchangeVersion version;

  /**
   * Initializes a new instance.
   *
   * @param xmlElementName Name of the XML element.
   * @param uri            The URI.
   * @param version        The version.
   */
  protected PropertyDefinition(String xmlElementName, String uri,
      ExchangeVersion version) {
    super(uri);
    this.xmlElementName = xmlElementName;
    this.flags = EnumSet.of(PropertyDefinitionFlags.None);
    this.version = version;
  }

  /**
   * Initializes a new instance.
   *
   * @param xmlElementName Name of the XML element.
   * @param flags          The flags.
   * @param version        The version.
   */
  protected PropertyDefinition(String xmlElementName,
      EnumSet<PropertyDefinitionFlags> flags, ExchangeVersion version) {
    super();
    this.xmlElementName = xmlElementName;
    this.flags = flags;
    this.version = version;
  }

  /**
   * Initializes a new instance.
   *
   * @param xmlElementName Name of the XML element.
   * @param uri            The URI.
   * @param flags          The flags.
   * @param version        The version.
   */
  protected PropertyDefinition(String xmlElementName, String uri,
      EnumSet<PropertyDefinitionFlags> flags, ExchangeVersion version) {
    this(xmlElementName, uri, version);
    this.flags = flags;
  }

  /**
   * Determines whether the specified flag is set.
   *
   * @param flag The flag.
   * @return true if the specified flag is set; otherwise, false.
   */
  public boolean hasFlag(PropertyDefinitionFlags flag) {
    return this.hasFlag(flag, null);
  }

  /**
   * Determines whether the specified flag is set.
   *
   * @param flag The flag.
   * @return true if the specified flag is set; otherwise, false.
   */
  public boolean hasFlag(PropertyDefinitionFlags flag, ExchangeVersion version) {
    return this.flags.contains(flag);
  }

  /**
   * Registers associated internal property.
   *
   * @param properties The list in which to add the associated property.
   */
  protected void registerAssociatedInternalProperties(
      List<PropertyDefinition> properties) {
  }

  /**
   * Gets a list of associated internal property.
   *
   * @return A list of PropertyDefinition objects. This is a hack. It is here
   * (currently) solely to help the API register the MeetingTimeZone
   * property definition that is internal.
   */
  public List<PropertyDefinition> getAssociatedInternalProperties() {
    List<PropertyDefinition> properties = new
        ArrayList<PropertyDefinition>();
    this.registerAssociatedInternalProperties(properties);
    return properties;
  }

  /**
   * Gets the minimum Exchange version that supports this property.
   *
   * @return The version.
   */
  public ExchangeVersion getVersion() {
    return version;
  }

  /**
   * Gets a value indicating whether this property definition is for a
   * nullable type.
   *
   * @return always true
   */
  public boolean isNullable() {
    return true;
  }

  /**
   * Loads from XML.
   *
   * @param reader      The reader.
   * @param propertyBag The property bag.
   * @throws Exception the exception
   */
  public abstract void loadPropertyValueFromXml(EwsServiceXmlReader reader, PropertyBag propertyBag)
      throws Exception;

  /**
   * Writes the property value to XML.
   *
   * @param writer            the writer
   * @param propertyBag       the property bag
   * @param isUpdateOperation indicates whether the context is an update operation
   * @throws Exception the exception
   */
  public abstract void writePropertyValueToXml(EwsServiceXmlWriter writer, PropertyBag propertyBag,
      boolean isUpdateOperation) throws Exception;

  /**
   * Gets the name of the XML element.
   *
   * @return The name of the XML element.
   */
  public String getXmlElement() {
    return this.xmlElementName;
  }

  /**
   * Gets the name of the property.
   *
   * @return Name of the property.
   */
  public String getName() {

    if (null == this.name || this.name.isEmpty()) {
      ServiceObjectSchema.initializeSchemaPropertyNames();
    }
    return name;
  }

  /**
   * Sets the name of the property.
   *
   * @param name name of the property
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the property definition's printable name.
   *
   * @return The property definition's printable name.
   */
  @Override public String getPrintableName() {
    return this.getName();
  }
}
