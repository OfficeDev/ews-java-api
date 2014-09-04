/**************************************************************************
 * copyright file="PropertySet.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the PropertySet.java.
 **************************************************************************/

package microsoft.exchange.webservices.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.stream.XMLStreamException;

/***
 *Represents a set of item or folder properties. Property sets are used to
 * indicate what properties of an item or folder should be loaded when binding
 * to an existing item or folder or when loading an item or folder's properties.
 * 
 */
public final class PropertySet implements ISelfValidate,
		Iterable<PropertyDefinitionBase> {

	/** The Constant IdOnly. */
	public static final PropertySet IdOnly = PropertySet.
			createReadonlyPropertySet(BasePropertySet.IdOnly);

	/**
	 * Returns a predefined property set that only includes the Id property.
	 * 
	 * @return Returns a predefined property set that only includes the Id
	 *         property.
	 */
	private static PropertySet getIdOnly() {
		return IdOnly;
	}

	/** The Constant FirstClassProperties. */
	public static final PropertySet FirstClassProperties = PropertySet.
			createReadonlyPropertySet(BasePropertySet.FirstClassProperties);

	/**
	 * Returns a predefined property set that includes the first class
	 * properties of an item or folder.
	 * 
	 * @return A predefined property set that includes the first class
	 *         properties of an item or folder.
	 */
	public static PropertySet getFirstClassProperties() {
		return FirstClassProperties;
	}

	/**
	 * Maps BasePropertySet values to EWS's BaseShape values.
	 */
	private static LazyMember<Map<BasePropertySet, String>> defaultPropertySetMap = 
		new  LazyMember<Map<BasePropertySet, String>>(new 
					ILazyMember<Map<BasePropertySet, String>>() {
				@Override
				public Map<BasePropertySet, String> createInstance() {
					Map<BasePropertySet, String> result = new 
					HashMap<BasePropertySet, String>();
					result.put(BasePropertySet.IdOnly, BasePropertySet.IdOnly
							.getBaseShapeValue());
					result.put(BasePropertySet.FirstClassProperties,
							BasePropertySet.FirstClassProperties
									.getBaseShapeValue());
					return result;
				}
			});
	/**
	 * The base property set this property set is based upon.
	 */
	private BasePropertySet basePropertySet;

	/**
	 * The list of additional properties included in this property set.
	 */
	private List<PropertyDefinitionBase> additionalProperties = new 
			ArrayList<PropertyDefinitionBase>();

	/**
	 * The requested body type for get and find operations. If null, the
	 * "best body" is returned.
	 */
	private BodyType requestedBodyType;

	/**
	 * Value indicating whether or not the server should filter HTML content.
	 */
	private Boolean filterHtml;

	/**
	 * Value indicating whether or not the server 
	 * should convert HTML code page to UTF8.
	 */
	private Boolean convertHtmlCodePageToUTF8;
	
	/**
	 * Value indicating whether or not this PropertySet can be modified.
	 */
	private boolean isReadOnly;

	/**
	 * Initializes a new instance of PropertySet.
	 * 
	 * @param basePropertySet
	 *            The base property set to base the property set upon.
	 * @param additionalProperties
	 *            Additional properties to include in the property set. Property
	 *            definitions are available as static members from schema
	 *            classes (for example, EmailMessageSchema.Subject,
	 *            AppointmentSchema.Start, ContactSchema.GivenName, etc.)
	 */
	public PropertySet(BasePropertySet basePropertySet,
			PropertyDefinitionBase... additionalProperties) {
		this.basePropertySet = basePropertySet;
		if (null != additionalProperties) {
			for (PropertyDefinitionBase property : additionalProperties) {
				this.additionalProperties.add(property);
			}
		}
	}

	/**
	 * Initializes a new instance of PropertySet.
	 * 
	 * @param basePropertySet
	 *            The base property set to base the property set upon.
	 * @param additionalProperties
	 *            Additional properties to include in the property set. Property
	 *            definitions are available as static members from schema
	 *            classes (for example, EmailMessageSchema.Subject,
	 *            AppointmentSchema.Start, ContactSchema.GivenName, etc.)
	 */
	public PropertySet(BasePropertySet basePropertySet,
			Iterator<PropertyDefinitionBase> additionalProperties) {
		this.basePropertySet = basePropertySet;
		if (null != additionalProperties) {
			while (additionalProperties.hasNext()) {
				this.additionalProperties.add(additionalProperties.next());
			}
		}
	}

	/**
	 * Initializes a new instance of PropertySet based upon
	 * BasePropertySet.IdOnly.
	 */
	public PropertySet() {
		this.basePropertySet = BasePropertySet.IdOnly;
	}

	/**
	 * Initializes a new instance of PropertySet.
	 * 
	 * @param basePropertySet
	 *            The base property set to base the property set upon.
	 */
	public PropertySet(BasePropertySet basePropertySet) {
		this.basePropertySet = basePropertySet;
	}

	/**
	 * Initializes a new instance of PropertySet based upon
	 * BasePropertySet.IdOnly.
	 * 
	 * @param additionalProperties
	 *            Additional properties to include in the property set. Property
	 *            definitions are available as static members from schema
	 *            classes (for example, EmailMessageSchema.Subject,
	 *            AppointmentSchema.Start, ContactSchema.GivenName, etc.)
	 */
	public PropertySet(PropertyDefinitionBase... additionalProperties) {
		this(BasePropertySet.IdOnly, additionalProperties);
	}

	/**
	 * Initializes a new instance of PropertySet based upon
	 * BasePropertySet.IdOnly.
	 * 
	 * @param additionalProperties
	 *            Additional properties to include in the property set. Property
	 *            definitions are available as static members from schema
	 *            classes (for example, EmailMessageSchema.Subject,
	 *            AppointmentSchema.Start, ContactSchema.GivenName, etc.)
	 */
	public PropertySet(Iterator<PropertyDefinitionBase> additionalProperties) {
		this(BasePropertySet.IdOnly, additionalProperties);
	}

	/**
	 * Implements an implicit conversion between 
	 * PropertySet and BasePropertySet.	
	 * @param basePropertySet The BasePropertySet value to convert from.
	 * @return A PropertySet instance based on the specified base property set.
	 */	
    public static PropertySet getPropertySetFromBasePropertySet(BasePropertySet 
    		basePropertySet) {
        return new PropertySet(basePropertySet);
    }

    
	/**
	 * Adds the specified property to the property set.
	 * 
	 * @param property
	 *            The property to add.
	 * @throws Exception
	 *             the exception
	 */
	public void add(PropertyDefinitionBase property) throws Exception {
		this.throwIfReadonly();
		EwsUtilities.validateParam(property, "property");

		if (!this.additionalProperties.contains(property)) {
			this.additionalProperties.add(property);
		}
	}

	/**
	 * Adds the specified properties to the property set.
	 * 
	 * @param properties
	 *            The properties to add.
	 * @throws Exception
	 *             the exception
	 */
	public void addRange(Iterable<PropertyDefinitionBase> properties)
			throws Exception {
		this.throwIfReadonly();
		Iterator<PropertyDefinitionBase> property = properties.iterator();
		EwsUtilities.validateParamCollection(property, "properties");

		for (Iterator<PropertyDefinitionBase> it = properties.iterator(); it
				.hasNext();) {
			this.add(it.next());
		}
	}

	/**
	 * Remove all explicitly added properties from the property set.
	 */
	public void clear() {
		this.throwIfReadonly();
		this.additionalProperties.clear();
	}

	/**
	 * Creates a read-only PropertySet.
	 * 
	 * @param basePropertySet
	 *            The base property set.
	 * @return PropertySet
	 */
	private static PropertySet createReadonlyPropertySet(
			BasePropertySet basePropertySet) {
		PropertySet propertySet = new PropertySet(basePropertySet);
		propertySet.isReadOnly = true;
		return propertySet;
	}

	/**
	 * Throws if readonly property set.
	 */
	private void throwIfReadonly() {
		if (this.isReadOnly) {
			throw new UnsupportedOperationException(
					Strings.PropertySetCannotBeModified);
		}
	}

	/**
	 * Determines whether the specified property has been explicitly added to
	 * this property set using the Add or AddRange methods.
	 * 
	 * @param property
	 *            The property.
	 * @return true if this property set contains the specified property
	 *         otherwise, false
	 */
	public boolean contains(PropertyDefinitionBase property) {
		return this.additionalProperties.contains(property);
	}

	/**
	 * Removes the specified property from the set.
	 * 
	 * @param property
	 *            The property to remove.
	 * @return true if the property was successfully removed, false otherwise.
	 */
	public boolean remove(PropertyDefinitionBase property) {
		this.throwIfReadonly();
		return this.additionalProperties.remove(property);
	}

	/**
	 * Gets the base property set, the property set is based upon.
	 * 
	 * @return the base property set
	 */
	public BasePropertySet getBasePropertySet() {
		return this.basePropertySet;
	}

	/**
	 *Maps BasePropertySet values to EWS's BaseShape values.
	 * 
	 * @return the base property set
	 */
	public static LazyMember<Map<BasePropertySet, String>> getDefaultPropertySetMap() {
	 return PropertySet.defaultPropertySetMap;
	
	}
	/**
	 * Sets the base property set, the property set is based upon.
	 * 
	 * @param basePropertySet
	 *            Base property set.
	 */
	public void setBasePropertySet(BasePropertySet basePropertySet) {
		this.throwIfReadonly();
		this.basePropertySet = basePropertySet;
	}

	/**
	 * Gets type of body that should be loaded on items. If RequestedBodyType
	 * is null, body is returned as HTML if available, plain text otherwise.
	 * 
	 * @return the requested body type
	 */
	public BodyType getRequestedBodyType() {
		return this.requestedBodyType;
	}

	/**
	 * Sets type of body that should be loaded on items. If RequestedBodyType is
	 * null, body is returned as HTML if available, plain text otherwise.
	 * 
	 * @param requestedBodyType
	 *            Type of body that should be loaded on items.
	 */
	public void setRequestedBodyType(BodyType requestedBodyType) {
		this.throwIfReadonly();
		this.requestedBodyType = requestedBodyType;
	}

	/**
	 * Gets the number of explicitly added properties in this set.
	 * 
	 * @return the count
	 */
	public int getCount() {
		return this.additionalProperties.size();
	}

	/**
	 * Gets value indicating whether or not to filter potentially unsafe HTML
	 * content from message bodies.
	 * 
	 * @return the filter html content
	 */
	public Boolean getFilterHtmlContent() {
		return this.filterHtml;
	}

	/**
	 * Sets value indicating whether or not to filter potentially unsafe HTML
	 * content from message bodies.
	 * 
	 * @param filterHtml
	 *            true to filter otherwise false.
	 */
	public void setFilterHtmlContent(Boolean filterHtml) {
		this.throwIfReadonly();
		this.filterHtml = filterHtml;
	}
	
	

	
	/**
	 * Gets value indicating whether or not to convert
	 *  HTML code page to UTF8 encoding.
	 */
	public Boolean getConvertHtmlCodePageToUTF8() {
		return this.convertHtmlCodePageToUTF8;

	}

	/**
	 * Sets value indicating whether or not to 
	 * convert HTML code page to UTF8 encoding.
	 */
	public void setConvertHtmlCodePageToUTF8(Boolean value) {
		this.throwIfReadonly();
		this.convertHtmlCodePageToUTF8 = value;

	}
	
	
	/**
	 * Gets the PropertyDefinitionBase at the specified index.
	 * 
	 * @param index
	 *            Index.
	 * @return the property definition base at
	 */
	public PropertyDefinitionBase getPropertyDefinitionBaseAt(int index) {
		return this.additionalProperties.get(index);
	}


	/**
	 * Validate.
	 * 
	 * @throws ServiceValidationException
	 *             the service validation exception
	 */
	@Override
	public void validate() throws ServiceValidationException {
		this.internalValidate();
	}

	/**
	 * Writes additonal properties to XML.
	 * 
	 * @param writer
	 *            The writer to write to.
	 * @param propertyDefinitions
	 *            The property definitions to write.
	 * @throws javax.xml.stream.XMLStreamException
	 *             the xML stream exception
	 * @throws ServiceXmlSerializationException
	 *             the service xml serialization exception
	 */
	protected static void writeAdditionalPropertiesToXml(
			EwsServiceXmlWriter writer,
			Iterator<PropertyDefinitionBase> propertyDefinitions)
			throws XMLStreamException, ServiceXmlSerializationException {
		writer.writeStartElement(XmlNamespace.Types,
				XmlElementNames.AdditionalProperties);

		while (propertyDefinitions.hasNext()) {
			PropertyDefinitionBase propertyDefinition = propertyDefinitions
					.next();
			propertyDefinition.writeToXml(writer);
		}

		writer.writeEndElement();
	}

	/**
	 * Validates this property set.
	 * 
	 * @throws ServiceValidationException
	 *             the service validation exception
	 */
	protected void internalValidate() throws ServiceValidationException {
		for (int i = 0; i < this.additionalProperties.size(); i++) {
			if (this.additionalProperties.get(i) == null) {
				throw new ServiceValidationException(String.format(
						Strings.AdditionalPropertyIsNull, i));
			}
		}
	}

	/**
	 * Validates this property set instance for request to ensure that: 1.
	 * Properties are valid for the request server version 2. If only summary
	 * properties are legal for this request (e.g. FindItem) then only summary
	 * properties were specified.
	 * 
	 * @param request
	 *            The request.
	 * @param summaryPropertiesOnly
	 *            if set to true then only summary properties are allowed.
	 * @throws ServiceVersionException
	 *             the service version exception
	 * @throws ServiceValidationException
	 *             the service validation exception
	 */
	protected void validateForRequest(ServiceRequestBase request,
			boolean summaryPropertiesOnly) throws ServiceVersionException,
			ServiceValidationException {
		for (PropertyDefinitionBase propDefBase : this.additionalProperties) {
			if (propDefBase instanceof PropertyDefinition) {
				PropertyDefinition propertyDefinition = 
					(PropertyDefinition) propDefBase;
				if (propertyDefinition.getVersion().ordinal() > request
						.getService().getRequestedServerVersion().ordinal()) {
					throw new ServiceVersionException(String.format(
							Strings.PropertyIncompatibleWithRequestVersion,
							propertyDefinition.getName(), propertyDefinition
							.getVersion()));
				}

				if (summaryPropertiesOnly &&
						 !propertyDefinition.hasFlag(
						 		PropertyDefinitionFlags.CanFind,request.
								getService().getRequestedServerVersion())) {
					throw new ServiceValidationException(String.format(
							"ServiceValidationException :%s,%s,%s",
							Strings.NonSummaryPropertyCannotBeUsed,
							propertyDefinition.getName(), request
							.getXmlElementName()));
				}
			}
		}
		if (this.getFilterHtmlContent()!= null)
		{
			if (request.getService().getRequestedServerVersion().compareTo(ExchangeVersion.Exchange2010)< 0)
			{
				throw new ServiceVersionException(
						String.format(
								Strings.PropertyIncompatibleWithRequestVersion,
								"FilterHtmlContent",
								ExchangeVersion.Exchange2010));
			}
		}

		if (this.getConvertHtmlCodePageToUTF8()!= null)
		{
			if (request.getService().getRequestedServerVersion().compareTo(ExchangeVersion.Exchange2010_SP1) < 0)
			{
				throw new ServiceVersionException(
						String.format(
								Strings.PropertyIncompatibleWithRequestVersion,
								"ConvertHtmlCodePageToUTF8",
								ExchangeVersion.Exchange2010_SP1));
			}
		}
	}
	
	/**
	 * Writes the property set to XML.
	 * 
	 * @param writer
	 *            The writer to write to.
	 * @param serviceObjectType
	 *            The type of service object the property set is emitted for.
	 * @throws javax.xml.stream.XMLStreamException
	 *             the xML stream exception
	 * @throws ServiceXmlSerializationException
	 *             the service xml serialization exception
	 */
	protected void writeToXml(EwsServiceXmlWriter writer,
			ServiceObjectType serviceObjectType) throws XMLStreamException,
			ServiceXmlSerializationException {
		writer
				.writeStartElement(
						XmlNamespace.Messages,
						serviceObjectType == ServiceObjectType.Item ? 
								XmlElementNames.ItemShape
								: XmlElementNames.FolderShape);

		writer.writeElementValue(XmlNamespace.Types, XmlElementNames.BaseShape,
				this.getBasePropertySet().getBaseShapeValue());

		if (serviceObjectType == ServiceObjectType.Item) {
			if (this.getRequestedBodyType() != null) {
				writer.writeElementValue(XmlNamespace.Types,
						XmlElementNames.BodyType, this.getRequestedBodyType());
			}

			if (this.getFilterHtmlContent() != null) {
				writer.writeElementValue(XmlNamespace.Types,
						XmlElementNames.FilterHtmlContent, this
								.getFilterHtmlContent());
			}
			if ((this.getConvertHtmlCodePageToUTF8()!=null) &&
					writer.getService().getRequestedServerVersion().
					compareTo(ExchangeVersion.Exchange2010_SP1) >= 0)
			{
				writer.writeElementValue(
						XmlNamespace.Types,
						XmlElementNames.ConvertHtmlCodePageToUTF8,
						this.getConvertHtmlCodePageToUTF8());
			}
		}

		if (this.additionalProperties.size() > 0) {
			writeAdditionalPropertiesToXml(writer, this.additionalProperties
					.iterator());
		}

		writer.writeEndElement(); // Item/FolderShape
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<PropertyDefinitionBase> iterator() {
		return this.additionalProperties.iterator();
	}

}
