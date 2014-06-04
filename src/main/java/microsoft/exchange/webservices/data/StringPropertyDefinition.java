/**************************************************************************
 * copyright file="StringPropertyDefinition.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the StringPropertyDefinition.java.
 **************************************************************************/

package microsoft.exchange.webservices.data;

import java.util.EnumSet;

/***
 * Represents String property definition.
 * 
 * 
 */
class StringPropertyDefinition extends TypedPropertyDefinition {

	/**
	 * Initializes a new instance of the "StringPropertyDefinition" class.
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
	protected StringPropertyDefinition(String xmlElementName, String uri,
			EnumSet<PropertyDefinitionFlags> flags, ExchangeVersion version) {
		super(xmlElementName, uri, flags, version);
	}

	/***
	 * Parses the specified value.
	 * 
	 * @param value
	 *            The value.
	 * @return Typed value.
	 */
	@Override
	protected Object parse(String value) {
		return value;
	}

	/**
	 * Gets a value indicating whether this property definition is for a
	 * nullable type (ref, int?, bool?...).
	 * 
	 * @return True
	 */
	@Override
	protected boolean isNullable() {
		return true;
	}
	
	/**
	 * Gets the property type.
	 */
	@Override
    public Class getType() {
         return String.class; 
    }
}
