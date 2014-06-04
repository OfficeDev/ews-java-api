/**************************************************************************
 * copyright file="TimeSpanPropertyDefinition.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the TimeSpanPropertyDefinition.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.EnumSet;

/**
 * Represents TimeSpan property definition.
 */
class TimeSpanPropertyDefinition extends GenericPropertyDefinition<TimeSpan> {


	/**
	 * Initializes a new instance of the "TimeSpanPropertyDefinition" class.
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
	protected TimeSpanPropertyDefinition(String xmlElementName, String uri,
			EnumSet<PropertyDefinitionFlags> flags, ExchangeVersion version) {
		super(TimeSpan.class, xmlElementName, uri, flags, version);
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
		
			return EwsUtilities.getXSDurationToTimeSpanValue(value); 
		 
	}

	/***
	 * Convert instance to string.
	 * 
	 * @param value
	 *            The value.
	 * @return String representation of property value.
	 */
	@Override
	protected String toString(Object value) {
		return EwsUtilities.getTimeSpanToXSDuration((TimeSpan)value);
	}
}
