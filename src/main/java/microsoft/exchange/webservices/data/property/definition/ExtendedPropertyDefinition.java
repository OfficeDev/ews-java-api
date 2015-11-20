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
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.XmlAttributeNames;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.property.DefaultExtendedPropertySet;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.property.MapiPropertyType;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlSerializationException;
import microsoft.exchange.webservices.data.misc.MapiTypeConverter;

import java.util.UUID;

/**
 * Represents the definition of an extended property.
 */
public final class ExtendedPropertyDefinition extends PropertyDefinitionBase {

  /**
   * The property set.
   */
  private DefaultExtendedPropertySet propertySet;

  /**
   * The property set id.
   */
  private UUID propertySetId;

  /**
   * The tag.
   */
  private Integer tag;

  /**
   * The name.
   */
  private String name;

  /**
   * The id.
   */
  private Integer id;

  /**
   * The mapi type.
   */
  private MapiPropertyType mapiType;

  /**
   * The Constant FieldFormat.
   */
  private final static String FieldFormat = "%s: %s ";

  /**
   * The Property set field name.
   */
  private static final String PropertySetFieldName = "PropertySet";

  /**
   * The Property set id field name.
   */
  private static final String PropertySetIdFieldName = "PropertySetId";

  /**
   * The Tag field name.
   */
  private static final String TagFieldName = "Tag";

  /**
   * The Name field name.
   */
  private static final String NameFieldName = "Name";

  /**
   * The Id field name.
   */
  private static final String IdFieldName = "Id";

  /**
   * The Mapi type field name.
   */
  private static final String MapiTypeFieldName = "MapiType";

  /**
   * Initializes a new instance.
   */
  public ExtendedPropertyDefinition() {
    super();
    this.mapiType = MapiPropertyType.String;
  }

  /**
   * Initializes a new instance.
   *
   * @param mapiType The MAPI type of the extended property.
   */
  protected ExtendedPropertyDefinition(MapiPropertyType mapiType) {
    this();
    this.mapiType = mapiType;
  }

  /**
   * Initializes a new instance.
   *
   * @param tag      The tag of the extended property.
   * @param mapiType The MAPI type of the extended property.
   */
  public ExtendedPropertyDefinition(int tag, MapiPropertyType mapiType) {
    this(mapiType);
    if (tag < 0) {
      throw new IllegalArgumentException("Argument out of range : tag " + "The extended property tag value must be in the range of 0 to 65,535.");
    }
    this.tag = tag;
  }

  /**
   * Initializes a new instance.
   *
   * @param propertySet The extended property set of the extended property.
   * @param name        The name of the extended property.
   * @param mapiType    The MAPI type of the extended property.
   * @throws Exception the exception
   */
  public ExtendedPropertyDefinition(DefaultExtendedPropertySet propertySet,
      String name, MapiPropertyType mapiType) throws Exception {
    this(mapiType);
    EwsUtilities.validateParam(name, "name");

    this.propertySet = propertySet;
    this.name = name;
  }

  /**
   * Initializes a new instance.
   *
   * @param propertySet The property set of the extended property.
   * @param id          The Id of the extended property.
   * @param mapiType    The MAPI type of the extended property.
   */
  public ExtendedPropertyDefinition(DefaultExtendedPropertySet propertySet,
      int id, MapiPropertyType mapiType) {
    this(mapiType);
    this.propertySet = propertySet;
    this.id = id;
  }

  /**
   * Initializes a new instance.
   *
   * @param propertySetId The property set Id of the extended property.
   * @param name          The name of the extended property.
   * @param mapiType      The MAPI type of the extended property.
   * @throws Exception the exception
   */
  public ExtendedPropertyDefinition(UUID propertySetId, String name,
      MapiPropertyType mapiType) throws Exception {
    this(mapiType);
    EwsUtilities.validateParam(name, "name");

    this.propertySetId = propertySetId;
    this.name = name;
  }

  /**
   * Initializes a new instance.
   *
   * @param propertySetId The property set Id of the extended property.
   * @param id            The Id of the extended property.
   * @param mapiType      The MAPI type of the extended property.
   */
  public ExtendedPropertyDefinition(UUID propertySetId, int id,
      MapiPropertyType mapiType) {
    this(mapiType);
    this.propertySetId = propertySetId;
    this.id = id;
  }

  /**
   * Determines whether two specified instances of ExtendedPropertyDefinition are equal.
   *
   * @param extPropDef1 First extended property definition.
   * @param extPropDef2 Second extended property definition.
   * @return True if extended property definitions are equal.
   */
  protected static boolean isEqualTo(ExtendedPropertyDefinition extPropDef1,
      ExtendedPropertyDefinition extPropDef2) {
    if (extPropDef1 == extPropDef2) {
      return true;
    }

    if (extPropDef1 == null || extPropDef2 == null) {
      return false;
    }

    if (extPropDef1.getId() != null) {
      if (!extPropDef1.getId().equals(extPropDef2.getId())) {
        return false;
      }
    } else if (extPropDef2.getId() != null) {
      return false;
    }

    if (extPropDef1.getMapiType() != extPropDef2.getMapiType()) {
      return false;
    }

    if (extPropDef1.getTag() != null) {
      if (!extPropDef1.getTag().equals(extPropDef2.getTag())) {
        return false;
      }
    } else if (extPropDef2.getTag() != null) {
      return false;
    }

    if (extPropDef1.getName() != null) {
      if (!extPropDef1.getName().equals(extPropDef2.getName())) {
        return false;
      }
    } else if (extPropDef2.getName() != null) {
      return false;
    }

    if (extPropDef1.getPropertySet() != extPropDef2.getPropertySet()) {
      return false;
    }

    if (extPropDef1.propertySetId != null) {
      if (!extPropDef1.propertySetId.equals(extPropDef2.propertySetId)) {
        return false;
      }
    } else if (extPropDef2.propertySetId != null) {
      return false;
    }

    return true;
  }

  /**
   * Gets the name of the XML element.
   *
   * @return XML element name.
   */
  @Override
  protected String getXmlElementName() {
    return XmlElementNames.ExtendedFieldURI;
  }

  /**
   * Gets the minimum Exchange version that supports this extended property.
   *
   * @return The version.
   */
  @Override
  public ExchangeVersion getVersion() {
    return ExchangeVersion.Exchange2007_SP1;
  }

  /**
   * Writes the attribute to XML.
   *
   * @param writer The writer.
   * @throws ServiceXmlSerializationException the service xml serialization exception
   */
  @Override
  protected void writeAttributesToXml(EwsServiceXmlWriter writer)
      throws ServiceXmlSerializationException {
    if (this.propertySet != null) {
      writer.writeAttributeValue(
          XmlAttributeNames.DistinguishedPropertySetId,
          this.propertySet);
    }
    if (this.propertySetId != null) {
      writer.writeAttributeValue(XmlAttributeNames.PropertySetId,
          this.propertySetId.toString());
    }
    if (this.tag != null) {
      writer.writeAttributeValue(XmlAttributeNames.PropertyTag, this.tag);
    }
    if (null != this.name && !this.name.isEmpty()) {
      writer.writeAttributeValue(XmlAttributeNames.PropertyName,
          this.name);
    }
    if (this.id != null) {
      writer.writeAttributeValue(XmlAttributeNames.PropertyId, this.id);
    }
    writer.writeAttributeValue(XmlAttributeNames.PropertyType,
        this.mapiType);
  }

  /**
   * Loads from XML.
   *
   * @param reader The reader.
   * @throws Exception the exception
   */
  public void loadFromXml(EwsServiceXmlReader reader) throws Exception {
    String attributeValue;

    attributeValue = reader
        .readAttributeValue(XmlAttributeNames.
            DistinguishedPropertySetId);
    if (null != attributeValue && !attributeValue.isEmpty()) {
      this.propertySet = DefaultExtendedPropertySet
          .valueOf(attributeValue);
    }

    attributeValue = reader
        .readAttributeValue(XmlAttributeNames.PropertySetId);
    if (null != attributeValue && !attributeValue.isEmpty()) {
      this.propertySetId = UUID.fromString(attributeValue);
    }

    attributeValue = reader
        .readAttributeValue(XmlAttributeNames.PropertyTag);
    if (null != attributeValue && !attributeValue.isEmpty()) {

      this.tag = Integer.decode(attributeValue);
    }

    this.name = reader.readAttributeValue(XmlAttributeNames.PropertyName);
    attributeValue = reader
        .readAttributeValue(XmlAttributeNames.PropertyId);
    if (null != attributeValue && !attributeValue.isEmpty()) {
      this.id = Integer.parseInt(attributeValue);
    }

    this.mapiType = reader.readAttributeValue(MapiPropertyType.class,
        XmlAttributeNames.PropertyType);
  }


  /**
   * Determines whether two specified instances of ExtendedPropertyDefinition
   * are equal.
   *
   * @param obj the obj
   * @return True if extended property definitions are equal.
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj instanceof ExtendedPropertyDefinition) {
      return ExtendedPropertyDefinition.isEqualTo(this,
          (ExtendedPropertyDefinition) obj);
    } else {
      return false;
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    return this.getPrintableName().hashCode();
  }

  /**
   * Gets the property definition's printable name.
   *
   * @return The property definition's printable name.
   */
  @Override public String getPrintableName() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    sb.append(formatField(NameFieldName, this.getName()));
    sb.append(formatField(MapiTypeFieldName, this.getMapiType()));
    sb.append(formatField(IdFieldName, this.getId()));
    sb.append(formatField(PropertySetFieldName, this.getPropertySet()));
    sb.append(formatField(PropertySetIdFieldName, this.getPropertySetId()));
    sb.append(formatField(TagFieldName, this.getTag()));
    sb.append("}");
    return sb.toString();
  }

  /**
   * Formats the field.
   *
   * @param <T>        Type of the field.
   * @param name       The name.
   * @param fieldValue The field value.
   * @return the string
   */
  protected <T> String formatField(String name, T fieldValue) {
    return (fieldValue != null) ? String.format(FieldFormat, name,
        fieldValue.toString()) : "";
  }

  /**
   * Gets the property set of the extended property.
   *
   * @return property set of the extended property.
   */
  public DefaultExtendedPropertySet getPropertySet() {
    return this.propertySet;
  }

  /**
   * Gets the property set Id or the extended property.
   *
   * @return property set Id or the extended property.
   */
  public UUID getPropertySetId() {
    return this.propertySetId;
  }

  /**
   * Gets the extended property's tag.
   *
   * @return The extended property's tag.
   */
  public Integer getTag() {
    return this.tag;
  }

  /**
   * Gets the name of the extended property.
   *
   * @return The name of the extended property.
   */
  public String getName() {
    return this.name;
  }

  /**
   * Gets the Id of the extended property.
   *
   * @return The Id of the extended property.
   */
  public Integer getId() {
    return this.id;
  }

  /**
   * Gets the MAPI type of the extended property.
   *
   * @return The MAPI type of the extended property.
   */
  public MapiPropertyType getMapiType() {
    return this.mapiType;
  }

  /**
   * Gets the property type.
   */
  @Override
  public Class<?> getType() {
    return MapiTypeConverter.getMapiTypeConverterMap().
        get(getMapiType()).getType();
  }
}
