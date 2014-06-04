/**************************************************************************
 * copyright file="ExtendedPropertyDefinition.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ExtendedPropertyDefinition.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.UUID;

/***
 * Represents the definition of an extended property.
 * 
 */
public final class ExtendedPropertyDefinition extends PropertyDefinitionBase {

	/** The property set. */
	private DefaultExtendedPropertySet propertySet;

	/** The property set id. */
	private UUID propertySetId;

	/** The tag. */
	private Integer tag;

	/** The name. */
	private String name;

	/** The id. */
	private Integer id;

	/** The mapi type. */
	private MapiPropertyType mapiType;

	/** The Constant FieldFormat. */
	private final static String FieldFormat = "%s: %s ";

	/** The Property set field name. */
	private static final String PropertySetFieldName = "PropertySet";

	/** The Property set id field name. */
	private static final String PropertySetIdFieldName = "PropertySetId";

	/** The Tag field name. */
	private static final String TagFieldName = "Tag";

	/** The Name field name. */
	private static final String NameFieldName = "Name";

	/** The Id field name. */
	private static final String IdFieldName = "Id";

	/** The Mapi type field name. */
	private static final String MapiTypeFieldName = "MapiType";

	/**
	 * * Initializes a new instance.
	 */
	protected ExtendedPropertyDefinition() {
		super();
		this.mapiType = MapiPropertyType.String;
	}

	/**
	 * * Initializes a new instance.
	 * 
	 * @param mapiType
	 *            The MAPI type of the extended property.
	 */
	protected ExtendedPropertyDefinition(MapiPropertyType mapiType) {
		this();
		this.mapiType = mapiType;
	}

	/**
	 * * Initializes a new instance.
	 * 
	 * @param tag
	 *            The tag of the extended property.
	 * @param mapiType
	 *            The MAPI type of the extended property.
	 */
	public ExtendedPropertyDefinition(int tag, MapiPropertyType mapiType) {
		this(mapiType);
		if (tag < 0) {
			throw new IllegalArgumentException("Argument out of range : tag " +
					Strings.TagValueIsOutOfRange);
		}
		this.tag = tag;
	}

	/**
	 * * Initializes a new instance.
	 * 
	 * @param propertySet
	 *            The extended property set of the extended property.
	 * @param name
	 *            The name of the extended property.
	 * @param mapiType
	 *            The MAPI type of the extended property.
	 * @throws Exception
	 *             the exception
	 */
	public ExtendedPropertyDefinition(DefaultExtendedPropertySet propertySet,
			String name, MapiPropertyType mapiType) throws Exception {
		this(mapiType);
		EwsUtilities.validateParam(name, "name");

		this.propertySet = propertySet;
		this.name = name;
	}

	/**
	 * * Initializes a new instance.
	 * 
	 * @param propertySet
	 *            The property set of the extended property.
	 * @param id
	 *            The Id of the extended property.
	 * @param mapiType
	 *            The MAPI type of the extended property.
	 */
	public ExtendedPropertyDefinition(DefaultExtendedPropertySet propertySet,
			int id, MapiPropertyType mapiType) {
		this(mapiType);
		this.propertySet = propertySet;
		this.id = id;
	}

	/**
	 * * Initializes a new instance.
	 * 
	 * @param propertySetId
	 *            The property set Id of the extended property.
	 * @param name
	 *            The name of the extended property.
	 * @param mapiType
	 *            The MAPI type of the extended property.
	 * @throws Exception
	 *             the exception
	 */
	public ExtendedPropertyDefinition(UUID propertySetId, String name,
			MapiPropertyType mapiType) throws Exception {
		this(mapiType);
		EwsUtilities.validateParam(name, "name");

		this.propertySetId = propertySetId;
		this.name = name;
	}

	/**
	 * * Initializes a new instance.
	 * 
	 * @param propertySetId
	 *            The property set Id of the extended property.
	 * @param id
	 *            The Id of the extended property.
	 * @param mapiType
	 *            The MAPI type of the extended property.
	 */
	public ExtendedPropertyDefinition(UUID propertySetId, int id,
			MapiPropertyType mapiType) {
		this(mapiType);
		this.propertySetId = propertySetId;
		this.id = id;
	}
	
	/**
	 * Determines whether two specified instances of ExtendedPropertyDefinition
	 * are equal.
	 * 
	 * @param extPropDef1
	 *            First extended property definition.
	 * @param extPropDef2
	 *            Second extended property definition.
	 * @return True if extended property definitions are equal.
	 */
/*	protected static boolean isEqualTo(ExtendedPropertyDefinition extPropDef1,
			ExtendedPropertyDefinition extPropDef2) {
		return (extPropDef1 == extPropDef2) ||
		 ((Object)extPropDef1 != null &&
				 (Object)extPropDef2 != null &&
				 extPropDef1.getId() == extPropDef2.getId() &&
				 extPropDef1.getMapiType() == extPropDef2.getMapiType() &&
				 extPropDef1.getTag().intValue() == extPropDef2.getTag().intValue() &&
				 extPropDef1.getName().equals(extPropDef2.getName()) &&
				 extPropDef1.getPropertySet() == extPropDef2.getPropertySet() &&
				  extPropDef1.propertySetId
				.equals(extPropDef2.propertySetId));
	}*/
	
	protected static boolean isEqualTo(ExtendedPropertyDefinition extPropDef1,
			ExtendedPropertyDefinition extPropDef2) {
		return (extPropDef1 == extPropDef2)
				|| ((Object) extPropDef1 != null
						&& (Object) extPropDef2 != null
						&& ((extPropDef1.getId() == extPropDef2.getId()) || (extPropDef1
								.getId() != null && extPropDef1.getId().equals(
								extPropDef2.getId())))
						&& extPropDef1.getMapiType() == extPropDef2
								.getMapiType()
						&& ((extPropDef1.getTag() == extPropDef2.getTag()) || (extPropDef1
								.getTag() != null && extPropDef1.getTag()
								.equals(extPropDef2.getTag())))
						&& ((extPropDef1.getName() == extPropDef2.getName()) || (extPropDef1
								.getName() != null && extPropDef1.getName()
								.equals(extPropDef2.getName())))
						&& extPropDef1.getPropertySet() == extPropDef2
								.getPropertySet() && ((extPropDef1.propertySetId == extPropDef2.propertySetId) || (extPropDef1.propertySetId != null && extPropDef1.propertySetId
						.equals(extPropDef2.propertySetId))));
	}

	/***
	 * Gets the name of the XML element.
	 * 
	 * @return XML element name.
	 */
	@Override
	protected String getXmlElementName() {
		return XmlElementNames.ExtendedFieldURI;
	}

	/***
	 * Gets the minimum Exchange version that supports this extended property.
	 * 
	 * @return The version.
	 */
	@Override
	public ExchangeVersion getVersion() {
		return ExchangeVersion.Exchange2007_SP1;
	}

	/**
	 * * Writes the attributes to XML.
	 * 
	 * @param writer
	 *            The writer.
	 * @throws ServiceXmlSerializationException
	 *             the service xml serialization exception
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
	 * * Loads from XML.
	 * 
	 * @param reader
	 *            The reader.
	 * @throws Exception
	 *             the exception
	 */
	protected void loadFromXml(EwsServiceXmlReader reader) throws Exception {
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

			this.tag=Integer.getInteger(attributeValue,16);
			//	this.tag = Integer.parseInt(attributeValue, 16);
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
	 * @param obj
	 *            the obj
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
	@Override
	protected String getPrintableName() {
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
	 * * Formats the field.
	 * 
	 * @param <T>
	 *            Type of the field.
	 * @param name
	 *            The name.
	 * @param fieldValue
	 *            The field value.
	 * @return the string
	 */
	protected <T> String formatField(String name, T fieldValue) {
		return (fieldValue != null) ? String.format(FieldFormat, name,
				fieldValue.toString()) : "";
	}

	/***
	 * Gets the property set of the extended property.
	 * 
	 * @return property set of the extended property.
	 */
	public DefaultExtendedPropertySet getPropertySet() {
		return this.propertySet;
	}

	/***
	 * Gets the property set Id or the extended property.
	 * 
	 * @return property set Id or the extended property.
	 */
	public UUID getPropertySetId() {
		return this.propertySetId;
	}

	/***
	 * Gets the extended property's tag.
	 * 
	 * @return The extended property's tag.
	 */
	public Integer getTag() {
		return this.tag;
	}

	/***
	 * Gets the name of the extended property.
	 * 
	 * @return The name of the extended property.
	 */
	public String getName() {
		return this.name;
	}

	/***
	 * Gets the Id of the extended property.
	 * 
	 * @return The Id of the extended property.
	 */
	public Integer getId() {
		return this.id;
	}

	/***
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
	public Class getType()
	{	
		return MapiTypeConverter.getMapiTypeConverterMap().
				get(getMapiType()).getType(); 		
	}
}
