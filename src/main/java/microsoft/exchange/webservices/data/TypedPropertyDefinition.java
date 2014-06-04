/**************************************************************************
 * copyright file="TypedPropertyDefinition.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the TypedPropertyDefinition.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.text.ParseException;
import java.util.EnumSet;

import javax.xml.stream.XMLStreamException;

/***
 * Represents typed property definition.
 * 
 * 
 */
abstract class TypedPropertyDefinition extends PropertyDefinition {

	/** The is nullable. */
	private boolean isNullable;

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
	protected TypedPropertyDefinition(String xmlElementName, String uri,
			ExchangeVersion version) {
		super(xmlElementName, uri, version);
		this.isNullable = false;
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
	protected TypedPropertyDefinition(String xmlElementName, String uri,
			EnumSet<PropertyDefinitionFlags> flags, ExchangeVersion version) {
		super(xmlElementName, uri, flags, version);
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
	protected TypedPropertyDefinition(String xmlElementName, String uri,
			EnumSet<PropertyDefinitionFlags> flags, ExchangeVersion version,
			boolean isNullable) {
		super(xmlElementName, uri, flags, version);
		this.isNullable = isNullable;
	}

	/***
	 * Parses the specified value.
	 * 
	 * @param value
	 *            The value.
	 * @return Typed value.
	 * @throws java.text.ParseException
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	protected abstract Object parse(String value) throws InstantiationException, 
			IllegalAccessException, ParseException;

	/**
	 * * Gets a value indicating whether this property definition is for a
	 * nullable type.
	 * 
	 * @return always true
	 */
	@Override
	protected boolean isNullable() {
		return this.isNullable;
	}

	/***
	 * Convert instance to string.
	 * 
	 * @param value
	 *            The value.
	 * @return String representation of property value.
	 */
	protected String toString(Object value) {
		return value.toString();
	}

	/**
	 * * Loads from XML.
	 * 
	 * @param reader
	 *            The reader.
	 * @param propertyBag
	 *            The property bag.
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void loadPropertyValueFromXml(EwsServiceXmlReader reader,
			PropertyBag propertyBag) throws Exception {
		String value = reader.readElementValue(XmlNamespace.Types, this
				.getXmlElement());

		if (value != null && !value.isEmpty()) {
			propertyBag
					.setObjectFromPropertyDefinition(this, this.parse(value));
		}
	}

	/**
	 * * Writes the property value to XML.
	 * 
	 * @param writer
	 *            The writer.
	 * @param propertyBag
	 *            The property bag.
	 * @param isUpdateOperation
	 *            Indicates whether the context is an update operation.
	 * @throws javax.xml.stream.XMLStreamException
	 *             the xML stream exception
	 * @throws ServiceXmlSerializationException
	 *             the service xml serialization exception
	 * @throws microsoft.exchange.webservices.data.ServiceLocalException
	 *             the service local exception
	 */
	@Override
	protected void writePropertyValueToXml(EwsServiceXmlWriter writer,
			PropertyBag propertyBag, boolean isUpdateOperation)
			throws XMLStreamException, ServiceXmlSerializationException,
			ServiceLocalException {
		Object value = propertyBag.getObjectFromPropertyDefinition(this);

		if (value != null) {
			writer.writeElementValue(XmlNamespace.Types, this.getXmlElement(),
					this.getName(), value);
		}

	}
}
