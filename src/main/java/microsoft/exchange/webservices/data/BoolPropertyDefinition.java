/**************************************************************************
 * copyright file="BoolPropertyDefinition.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the BoolPropertyDefinition.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.EnumSet;

/**
 * * Represents Boolean property definition.
 */
final class BoolPropertyDefinition extends GenericPropertyDefinition<Boolean> {

	/**
	 * * Initializes a new instance.
	 * 
	 * @param xmlElementName
	 *            Name of the XML element.
	 * @param uri
	 *            The URI.
	 * @param version
	 *            The version.
	 */
	protected BoolPropertyDefinition(String xmlElementName, String uri,
			ExchangeVersion version) {
		super(Boolean.class,xmlElementName, uri, version);
	}

	/**
	 * * Initializes a new instance.
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
	protected BoolPropertyDefinition(String xmlElementName, String uri,
			EnumSet<PropertyDefinitionFlags> flags, ExchangeVersion version) {
		super(Boolean.class,xmlElementName, uri, flags, version);
	}

	/**
	 * * Initializes a new instance.
	 * 
	 * @param xmlElementName
	 *            Name of the XML element.
	 * @param uri
	 *            The URI.
	 * @param flags
	 *            The flags.
	 * @param version
	 *            The version.
	 * @param isNullable
	 *            Indicates that this property definition is for a nullable
	 *            property.
	 */
	protected BoolPropertyDefinition(String xmlElementName, String uri,
			EnumSet<PropertyDefinitionFlags> flags, ExchangeVersion version,
			boolean isNullable) {
		super(Boolean.class,xmlElementName, uri, flags, version, isNullable);
	}
	
	/***
	 * Convert instance to string.
	 * 
	 * @param value
	 *            The value.
	 * @return String representation of property value.
	 */
	@Override
	/**
	 * Convert instance to string.
	 * @param value The value.
	 * @returns String representation of Boolean property.
	 */
	protected String toString(Object value) {
		return EwsUtilities.boolToXSBool((Boolean)value);
	}
}
