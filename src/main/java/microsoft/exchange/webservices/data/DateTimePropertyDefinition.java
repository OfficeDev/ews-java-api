/**************************************************************************
 * copyright file="DateTimePropertyDefinition.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the DateTimePropertyDefinition.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.Date;
import java.util.EnumSet;

/***
 * Represents DateTime property definition.
 * 
 */
class DateTimePropertyDefinition extends PropertyDefinition {

	/** The is nullable. */
	private boolean isNullable;

	/**
	 * * Initializes a new instance of the DateTimePropertyDefinition class.
	 * 
	 * @param xmlElementName
	 *            the xml element name
	 * @param uri
	 *            the uri
	 * @param version
	 *            the version
	 */
	protected DateTimePropertyDefinition(String xmlElementName, String uri,
			ExchangeVersion version) {
		super(xmlElementName, uri, version);
	}

	/**
	 * * Initializes a new instance of the DateTimePropertyDefinition class.
	 * 
	 * @param xmlElementName
	 *            the xml element name
	 * @param uri
	 *            the uri
	 * @param flags
	 *            the flags
	 * @param version
	 *            the version
	 */
	protected DateTimePropertyDefinition(String xmlElementName, String uri,
			EnumSet<PropertyDefinitionFlags> flags, ExchangeVersion version) {
		super(xmlElementName, uri, flags, version);
	}

	/**
	 * * Initializes a new instance of the DateTimePropertyDefinition class.
	 * 
	 * @param xmlElementName
	 *            the xml element name
	 * @param uri
	 *            the uri
	 * @param flags
	 *            the flags
	 * @param version
	 *            the version
	 * @param isNullable
	 *            the is nullable
	 */
	protected DateTimePropertyDefinition(String xmlElementName, String uri,
			EnumSet<PropertyDefinitionFlags> flags, ExchangeVersion version,
			boolean isNullable) {
		super(xmlElementName, uri, flags, version);
		this.isNullable = isNullable;
	}

	/**
	 * * Loads from XML.
	 * 
	 * @param reader
	 *            the reader
	 * @param propertyBag
	 *            the property bag
	 * @throws Exception
	 *             the exception
	 */
	protected void loadPropertyValueFromXml(EwsServiceXmlReader reader,
			PropertyBag propertyBag) throws Exception {
		String value = reader.readElementValue(XmlNamespace.Types,
				getXmlElement());
		propertyBag.setObjectFromPropertyDefinition(this, reader.getService()
				.convertUniversalDateTimeStringToDate(value));
	}


	/***
	 * Writes the property value to XML.
	 * 
	 * @param writer
	 *            accepts EwsServiceXmlWriter
	 * @param propertyBag
	 *            accepts PropertyBag
	 * @param isUpdateOperation
	 *            accepts boolean whether the context is an update operation.
	 * @throws Exception
	 *             throws Exception
	 */
    protected void writePropertyValueToXml(EwsServiceXmlWriter writer,
			PropertyBag propertyBag, boolean isUpdateOperation)
        throws Exception {
		Object value = propertyBag.getObjectFromPropertyDefinition(this);

		if (value != null) {
			writer.writeStartElement(XmlNamespace.Types, getXmlElement());
			// No need of changing the date time zone to UTC as Java takes
			// default timezone as UTC
			Date dateTime = (Date)value;
			writer.writeValue(EwsUtilities.dateTimeToXSDateTime(dateTime),
					getName());

			writer.writeEndElement();
		}
	}

	/**
	 * * Gets a value indicating whether this property definition is for a
	 * nullable type (ref, int?, bool?...).
	 * 
	 * @return true, if is nullable
	 */
	protected boolean isNullable() {
		return isNullable;
	}
	
	/**
	 * Gets the property type.
	 */
	@Override
	public Class getType() {
		 return Date.class;
		
	}
}
