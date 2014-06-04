/**************************************************************************
 * copyright file="EffectiveRightsPropertyDefinition.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the EffectiveRightsPropertyDefinition.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.EnumSet;

/***
 * Represents effective rights property definition.
 */
final class EffectiveRightsPropertyDefinition extends PropertyDefinition {

	/**
	 * Initializes a new instance of the EffectiveRightsPropertyDefinition.
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
	protected EffectiveRightsPropertyDefinition(String xmlElementName,
			String uri, EnumSet<PropertyDefinitionFlags> flags,
			ExchangeVersion version) {
		super(xmlElementName, uri, flags, version);

	}

	/**
	 * Loads from XML.
	 * 
	 * @param reader
	 *            the reader
	 * @param propertyBag
	 *            the property bag
	 * @throws Exception
	 *             the exception
	 */
	protected  void loadPropertyValueFromXml(EwsServiceXmlReader reader,
			PropertyBag propertyBag) throws Exception {
		EnumSet<EffectiveRights> value = EnumSet.noneOf(EffectiveRights.class);
		value.add(EffectiveRights.None);

		reader.ensureCurrentNodeIsStartElement(XmlNamespace.Types, this
				.getXmlElement());

		if (!reader.isEmptyElement()) {
			do {
				reader.read();

				if (reader.isStartElement()) {

					if (reader.getLocalName().equals(
							XmlElementNames.CreateAssociated)) {

						if (reader.readElementValue(Boolean.class)) {
							value.add(EffectiveRights.CreateAssociated);
						}
					} else if (reader.getLocalName().equals(
							XmlElementNames.CreateContents)) {

						if (reader.readElementValue(Boolean.class)) {
							value.add(EffectiveRights.CreateContents);
						}
					} else if (reader.getLocalName().equals(
							XmlElementNames.CreateHierarchy)) {

						if (reader.readElementValue(Boolean.class)) {
							value.add(EffectiveRights.CreateHierarchy);
						}
					} else if (reader.getLocalName().equals(
							XmlElementNames.Delete)) {

						if (reader.readElementValue(Boolean.class)) {
							value.add(EffectiveRights.Delete);
						}
					} else if (reader.getLocalName().equals(
							XmlElementNames.Modify)) {

						if (reader.readElementValue(Boolean.class)) {
							value.add(EffectiveRights.Modify);
						}
					} else if (reader.getLocalName().equals(XmlElementNames.Read)) {
						if (reader.readElementValue(Boolean.class)) {
							value.add(EffectiveRights.Read);
						}else if(reader.getLocalName().equals(XmlElementNames.ViewPrivateItems)){							
							if (reader.readElementValue(Boolean.class)){                           
								value.add(EffectiveRights.ViewPrivateItems);
							}
						}

					}
				}

			} while (!reader.isEndElement(XmlNamespace.Types, this
					.getXmlElement()));
		}
		propertyBag.setObjectFromPropertyDefinition(this, value);
	}

	/**
	 * Writes to XML.
	 * 
	 * @param writer
	 *            the writer
	 * @param propertyBag
	 *            the property bag
	 * @param isUpdateOperation
	 *            the is update operation
	 */
	protected void writePropertyValueToXml(EwsServiceXmlWriter writer,
			PropertyBag propertyBag, boolean isUpdateOperation) {
		// EffectiveRights is a read-only property, no need to implement this.
	}

	/**
	 * Gets the property type.
	 */
	@Override
	public Class getType() {
		return EffectiveRights.class; 
	}
}
