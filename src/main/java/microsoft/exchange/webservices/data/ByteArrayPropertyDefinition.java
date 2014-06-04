/**************************************************************************
 * copyright file="ByteArrayPropertyDefinition.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ByteArrayPropertyDefinition.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.EnumSet;

/***
 * Represents byte array property definition.
 * 
 * 
 */
final class ByteArrayPropertyDefinition extends TypedPropertyDefinition {

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
	protected ByteArrayPropertyDefinition(String xmlElementName, String uri,
			EnumSet<PropertyDefinitionFlags> flags, ExchangeVersion version) {
		super(xmlElementName, uri, flags, version);
	}

	/**
	 * Parses the specified value.
	 * 
	 * @param value
	 *            accepts String
	 * @return value
	 */
	@Override
	protected Object parse(String value) {
		return Base64EncoderStream.decode(value);
		// return null;
	}

	/**
	 * Converts byte array property to a string.
	 * 
	 * @param value
	 *            accepts Object
	 * @return value
	 */
	@Override
	protected String toString(Object value) {
		return Base64EncoderStream.encode((byte[])value);
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
		return Byte.class; 
	}

}
