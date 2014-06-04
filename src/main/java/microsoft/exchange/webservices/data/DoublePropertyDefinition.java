/**************************************************************************
 * copyright file="DoublePropertyDefinition.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the DoublePropertyDefinition.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.EnumSet;

/**
 * Represents double-precision floating point property definition.
 */
final class  DoublePropertyDefinition extends 
GenericPropertyDefinition<Double>  {

	/**
	 * Initializes a new instance of the "DoublePropertyDefinition" class.
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
	protected DoublePropertyDefinition(String xmlElementName, String uri,
			EnumSet<PropertyDefinitionFlags> flags, ExchangeVersion version) {
		super(Double.class, xmlElementName, uri, flags, version);
	}
	
}
