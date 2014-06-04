/**************************************************************************
 * copyright file="GenericPropertyDefinition.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the GenericPropertyDefinition.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.text.ParseException;
import java.util.EnumSet;

/**
 * Represents generic property definition.
 * 
 * @param <T>
 *            the generic type
 * @Param T Property type.
 */
class GenericPropertyDefinition<TPropertyValue> extends 
TypedPropertyDefinition {

	private Class<TPropertyValue> instance;
	/**
	 * Initializes a new instance of the "GenericPropertyDefinition&lt;T&gt;"
	 * class.
	 * 
	 * @param xmlElementName
	 *            Name of the XML element.
	 * @param uri
	 *            The URI.
	 * @param version
	 *            The version.
	 */
	protected GenericPropertyDefinition(Class<TPropertyValue> cls,
			String xmlElementName, String uri,
			ExchangeVersion version) {
		super(xmlElementName, uri, version);	
		this.instance = cls;
	}

	/**
	 * Initializes a new instance of the "GenericPropertyDefinition&lt;T&gt;"
	 * class.
	 * 
	 * @param xmlElementName
	 *            Name of the XML element.
	 * @param uri
	 *            The URI.
	 * @param flags
	 *            The flags.
	 * @param version
	 *            The version.
	 */
	protected GenericPropertyDefinition(Class<TPropertyValue> cls,
			String xmlElementName, String uri,
			EnumSet<PropertyDefinitionFlags> flags, ExchangeVersion version) {
		super(xmlElementName, uri, flags, version);
		this.instance = cls;
	}

	/**
	 * Initializes a new instance of the GenericPropertyDefinition class.
	 * @param xmlElementName Name of the XML element.
	 * @param uri The URI.
	 * @param flags The flags.
	 * @param version The version.
	 * @param isNullable if set to true, property value is nullable.
	 */
	protected GenericPropertyDefinition(
			Class<TPropertyValue> cls,
			String xmlElementName,
			String uri,
			EnumSet<PropertyDefinitionFlags> flags,
			ExchangeVersion version,
			boolean isNullable) {
		super(xmlElementName,uri,flags,version,isNullable);
		this.instance = cls;
	}


	/**
	 * Parses the specified value.
	 * 
	 * @param value
	 *            the value
	 * @return the object
	 * @throws java.text.ParseException
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws java.text.ParseException
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @Param value The value
	 * @returns Double value from parsed value.
	 */	
	@Override
	protected Object parse(String value) throws InstantiationException, 
			IllegalAccessException, ParseException {
		
		return EwsUtilities.parse(instance,value);
	}

	/**
	 * Gets the property type.
	 */
	@Override
	public Class<TPropertyValue> getType() {	
		return instance;
	}
}