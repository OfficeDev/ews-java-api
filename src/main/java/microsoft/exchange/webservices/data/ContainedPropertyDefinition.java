/**************************************************************************
 * copyright file="ContainedPropertyDefinition.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ContainedPropertyDefinition.java.
 **************************************************************************/

package microsoft.exchange.webservices.data;

import java.util.EnumSet;

/***
 * Represents contained property definition.
 * 
 * 
 * @param <TComplexProperty>
 *            The type of the complex property.
 */
class ContainedPropertyDefinition<TComplexProperty extends ComplexProperty>
		extends ComplexPropertyDefinition<TComplexProperty> {

	private Class<TComplexProperty> instance;
	/** The contained xml element name. */
	private String containedXmlElementName;

	/**
	 * Initializes a new instance of. ContainedPropertyDefinition
	 * 
	 * @param xmlElementName
	 *            Name of the XML element.
	 * @param uri
	 *            The URI.
	 * @param containedXmlElementName
	 *            Name of the contained XML element.
	 * @param flags
	 *            The flags.
	 * @param version
	 *            The version.
	 * @param propertyCreationDelegate
	 *            Delegate used to create instances of ComplexProperty.
	 */
	protected ContainedPropertyDefinition(
			Class<TComplexProperty> cls,
			String xmlElementName,
			String uri,
			String containedXmlElementName,
			EnumSet<PropertyDefinitionFlags> flags,
			ExchangeVersion version,
			ICreateComplexPropertyDelegate<TComplexProperty>
			propertyCreationDelegate) {
		super(cls, xmlElementName, uri, flags, version, 
				propertyCreationDelegate);
		this.containedXmlElementName = containedXmlElementName;
	}

	/**
	 * * Load from XML.
	 * 
	 * @param reader
	 *            the reader
	 * @param propertyBag
	 *            the property bag
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void internalLoadFromXml(EwsServiceXmlReader reader,
			PropertyBag propertyBag) throws Exception {
		reader.readStartElement(XmlNamespace.Types,
				this.containedXmlElementName);
		super.internalLoadFromXml(reader, propertyBag);
		reader.readEndElementIfNecessary(XmlNamespace.Types,
				this.containedXmlElementName);

	}

	/**
	 * * Writes to XML.
	 * 
	 * @param writer
	 *            the writer
	 * @param propertyBag
	 *            the property bag
	 * @param isUpdateOperation
	 *            the is update operation
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void writePropertyValueToXml(EwsServiceXmlWriter writer,
			PropertyBag propertyBag, boolean isUpdateOperation)
			throws Exception {

		Object o = propertyBag.getObjectFromPropertyDefinition(this);
		if (o instanceof ComplexProperty) {
			ComplexProperty complexProperty = (ComplexProperty)o;
			writer.writeStartElement(XmlNamespace.Types, this.getXmlElement());
			complexProperty.writeToXml(writer, this.containedXmlElementName);
			writer.writeEndElement(); // this.XmlElementName
		}
	}
}
