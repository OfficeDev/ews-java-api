/**************************************************************************
 * copyright file="ComplexPropertyDefinition.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ComplexPropertyDefinition.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.EnumSet;

/***
 * Represents base complex property type.
 * 
 * 
 * @param <TComplexProperty>
 *            The type of the complex property.
 */
class ComplexPropertyDefinition<TComplexProperty extends ComplexProperty>
		extends ComplexPropertyDefinitionBase {

	private Class<TComplexProperty> instance;
	/** The property creation delegate. */
	private ICreateComplexPropertyDelegate
	<TComplexProperty> propertyCreationDelegate;

	/**
	 * * Initializes a new instance.
	 * 
	 * @param xmlElementName
	 *            Name of the XML element.
	 * @param flags
	 *            The flags.
	 * @param version
	 *            The version.
	 * @param propertyCreationDelegate
	 *            Delegate used to create instances of ComplexProperty.
	 */
	protected ComplexPropertyDefinition(
			Class<TComplexProperty> cls,
			String xmlElementName,
			EnumSet<PropertyDefinitionFlags> flags,
			ExchangeVersion version,
			ICreateComplexPropertyDelegate<TComplexProperty> 
			propertyCreationDelegate) {
		super(xmlElementName, flags, version);
		this.instance = cls;
		EwsUtilities.EwsAssert(propertyCreationDelegate != null,
				"ComplexPropertyDefinition ctor",
				"CreateComplexPropertyDelegate cannot be null");

		this.propertyCreationDelegate = propertyCreationDelegate;
	}

	/**
	 * * Initializes a new instance.
	 * 
	 * @param xmlElementName
	 *            Name of the XML element.
	 * @param uri
	 *            The URI.
	 * @param version
	 *            The version.
	 * @param propertyCreationDelegate
	 *            Delegate used to create instances of ComplexProperty.
	 */
	protected ComplexPropertyDefinition(
			Class<TComplexProperty> cls,
			String xmlElementName,
			String uri,
			ExchangeVersion version,
			ICreateComplexPropertyDelegate<TComplexProperty> 
			propertyCreationDelegate) {
		super(xmlElementName, uri, version);
		this.instance = cls;
		this.propertyCreationDelegate = propertyCreationDelegate;
	}
	
	protected ComplexPropertyDefinition(
			String xmlElementName,
			String uri,
			ExchangeVersion version,
			ICreateComplexPropertyDelegate<TComplexProperty> 
			propertyCreationDelegate) {
		super(xmlElementName, uri, version);
		this.propertyCreationDelegate = propertyCreationDelegate;
	}

	/**
	 * Instantiates a new complex property definition.
	 * 
	 * @param xmlElementName
	 *            the xml element name
	 * @param uri
	 *            the uri
	 * @param flags
	 *            the flags
	 * @param version
	 *            the version
	 * @param propertyCreationDelegate
	 *            the property creation delegate
	 */
	protected ComplexPropertyDefinition(
			Class<TComplexProperty> cls,
			String xmlElementName,
			String uri,
			EnumSet<PropertyDefinitionFlags> flags,
			ExchangeVersion version,
			ICreateComplexPropertyDelegate<TComplexProperty> 
			propertyCreationDelegate) {
		super(xmlElementName, uri, flags, version);
		this.instance = cls;
		this.propertyCreationDelegate = propertyCreationDelegate;
	}

	
	/**
	 * Instantiates a new complex property definition.
	 * 
	 * @param xmlElementName
	 *            the xml element name
	 * @param attachments
	 *            the attachments
	 * @param flags
	 *            the flags
	 * @param version
	 *            the version
	 * @param propertyCreationDelegate
	 *            the property creation delegate
	 */
	public ComplexPropertyDefinition(
			String attachments,
			String xmlElementName,
			ExchangeVersion version,
			EnumSet<PropertyDefinitionFlags> flags,
			ICreateComplexPropertyDelegate<TComplexProperty> propertyCreationDelegate) {
		// TODO Auto-generated constructor stub
		super(xmlElementName,attachments,flags,version);
		this.propertyCreationDelegate = propertyCreationDelegate;
	}

	/***
	 * Creates the property instance.
	 * 
	 * @param owner
	 *            The owner.
	 * @return ComplexProperty instance.
	 */
	@Override
	protected ComplexProperty createPropertyInstance(ServiceObject owner) {
		TComplexProperty complexProperty = this.propertyCreationDelegate
				.createComplexProperty();
		if (complexProperty instanceof IOwnedProperty) {
			IOwnedProperty ownedProperty = (IOwnedProperty)complexProperty;
			ownedProperty.setOwner(owner);
		}
		return complexProperty;
	}
	
	/**
	 * Gets the property type.
	 */
	@Override
	public Class getType() {
		/*ParameterizedType parameterizedType =
	        (ParameterizedType) getClass().getGenericSuperclass();
	     return (Class) parameterizedType.getActualTypeArguments()[0];

		 instance = ((Class)((ParameterizedType)this.getClass(). 
			       getGenericSuperclass()).getActualTypeArguments()[0]).
			       newInstance(); */
		/*return ((Class)((ParameterizedType)this.getClass(). 
			       getGenericSuperclass()).getActualTypeArguments()[0]).
			       newInstance();*/
		//return ComplexProperty.class;
		return this.instance;
	}
}
