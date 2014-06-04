/**************************************************************************
 * copyright file="IntPropertyDefinition.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the IntPropertyDefinition.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.EnumSet;

/**
 * Represents Integer property defintion.
 */
class IntPropertyDefinition extends GenericPropertyDefinition<Integer> {

	/**
	 * Initializes a new instance of the "IntPropertyDefinition" class.
	 * 
	 * @param xmlElementName
	 *            Name of the XML element.
	 * @param uri
	 *            The URI.
	 * @param version
	 *            The version.
	 */
	protected IntPropertyDefinition(String xmlElementName, String uri,
			ExchangeVersion version) {
		super(Integer.class, xmlElementName, uri, version);
	}

	/**
	 * Initializes a new instance of the "IntPropertyDefinition" class.
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
	protected IntPropertyDefinition(String xmlElementName, String uri,
			EnumSet<PropertyDefinitionFlags> flags, ExchangeVersion version) {
		super(Integer.class, xmlElementName, uri, flags, version);
	}

	/**
	 * Initializes a new instance of the "IntPropertyDefinition" class.
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
	protected IntPropertyDefinition(String xmlElementName, String uri,
			EnumSet<PropertyDefinitionFlags> flags, ExchangeVersion version,
			boolean isNullable) {
		super(Integer.class, xmlElementName, uri, flags, version, isNullable);
	}

	
}
