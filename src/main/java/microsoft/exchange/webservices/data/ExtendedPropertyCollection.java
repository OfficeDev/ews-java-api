/**************************************************************************
 * copyright file="ExtendedPropertyCollection.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ExtendedPropertyCollection.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLStreamException;

/**
 * Represents a collection of extended properties.
 * 
 */
@EditorBrowsable(state = EditorBrowsableState.Never)
public final class ExtendedPropertyCollection extends
		ComplexPropertyCollection<ExtendedProperty> implements
		ICustomXmlUpdateSerializer {

	/**
	 * Creates the complex property.
	 * 
	 * @param xmlElementName
	 *            Name of the XML element.
	 * @return Complex property instance.
	 */
	@Override
	protected ExtendedProperty createComplexProperty(String xmlElementName) {
		// This method is unused in this class, so just return null.
		return null;
	}

	/**
	 * Gets the name of the collection item XML element.
	 * 
	 * @param complexProperty
	 *            The complex property.
	 * @return XML element name.
	 */
	@Override
	protected String getCollectionItemXmlElementName(
			ExtendedProperty complexProperty) {
		// This method is unused in this class, so just return null.
		return null;
	}

	/**
	 * Loads from XML.
	 * 
	 * @param reader
	 *            The reader.
	 * @param localElementName
	 *            Name of the local element.
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void loadFromXml(EwsServiceXmlReader reader,
			String localElementName) throws Exception {
		ExtendedProperty extendedProperty = new ExtendedProperty();
		extendedProperty.loadFromXml(reader, reader.getLocalName());
		this.internalAdd(extendedProperty);
	}

	/**
	 * Writes to XML.
	 * 
	 * @param writer
	 *            The writer.
	 * @param xmlElementName
	 *            Name of the XML element.
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void writeToXml(EwsServiceXmlWriter writer, String xmlElementName)
			throws Exception {
		for (ExtendedProperty extendedProperty : this) {
			extendedProperty.writeToXml(writer,
					XmlElementNames.ExtendedProperty);
		}
	}

	/**
	 * Gets existing or adds new extended property.
	 * 
	 * @param propertyDefinition
	 *            The property definition.
	 * @return ExtendedProperty.
	 * @throws Exception
	 *             the exception
	 */
	private ExtendedProperty getOrAddExtendedProperty(
			ExtendedPropertyDefinition propertyDefinition) throws Exception {
		ExtendedProperty extendedProperty = null;
		OutParam<ExtendedProperty> extendedPropertyOut = 
			new OutParam<ExtendedProperty>();
		if (!this.tryGetProperty(propertyDefinition, extendedPropertyOut)) {
			extendedProperty = new ExtendedProperty(propertyDefinition);
			this.internalAdd(extendedProperty);
		} else {
			extendedProperty = extendedPropertyOut.getParam();
		}
		return extendedProperty;
	}

	/**
	 * Sets an extended property.
	 * 
	 * @param propertyDefinition
	 *            The property definition.
	 * @param value
	 *            The value.
	 * @throws Exception
	 *             the exception
	 */
	protected void setExtendedProperty(
			ExtendedPropertyDefinition propertyDefinition, Object value)
			throws Exception {
		ExtendedProperty extendedProperty = this
				.getOrAddExtendedProperty(propertyDefinition);
		extendedProperty.setValue(value);
	}

	/**
	 * Removes a specific extended property definition from the collection.
	 * 
	 * @param propertyDefinition
	 *            The definition of the extended property to remove.
	 * @return True if the property matching the extended property definition
	 *         was successfully removed from the collection, false otherwise.
	 * @throws Exception
	 *             the exception
	 */
	protected boolean removeExtendedProperty(
			ExtendedPropertyDefinition propertyDefinition) throws Exception {
		EwsUtilities.validateParam(propertyDefinition, "propertyDefinition");

		ExtendedProperty extendedProperty = null;
		OutParam<ExtendedProperty> extendedPropertyOut = 
			new OutParam<ExtendedProperty>();
		if (this.tryGetProperty(propertyDefinition, extendedPropertyOut)) {
			extendedProperty = extendedPropertyOut.getParam();
			return this.internalRemove(extendedProperty);
		} else {
			return false;
		}
	}

	/**
	 * Tries to get property.
	 * 
	 * @param propertyDefinition
	 *            The property definition.
	 * @param extendedPropertyOut
	 *            The extended property.
	 * @return True of property exists in collection.
	 */
	private boolean tryGetProperty(
			ExtendedPropertyDefinition propertyDefinition,
			OutParam<ExtendedProperty> extendedPropertyOut) {
		boolean found = false;
		extendedPropertyOut.setParam(null);
		for (ExtendedProperty prop : this.getItems()) {
			if (prop.getPropertyDefinition().equals(propertyDefinition)) {
				found = true;
				extendedPropertyOut.setParam(prop);
				break;
			}
		}
		return found;
	}

	/**
	 * Tries to get property value.
	 * 
	 * @param propertyDefinition
	 *            The property definition.
	 * @param propertyValueOut
	 *            The property value.
	 * @return True if property exists in collection.
	 * @throws microsoft.exchange.webservices.data.ArgumentException
	 */	
	protected <T> boolean tryGetValue(Class<T> cls,
			ExtendedPropertyDefinition propertyDefinition,
			OutParam<T> propertyValueOut) throws ArgumentException {
		ExtendedProperty extendedProperty = null;
		OutParam<ExtendedProperty> extendedPropertyOut = 
			new OutParam<ExtendedProperty>();
		if (this.tryGetProperty(propertyDefinition, extendedPropertyOut)) {
			extendedProperty = extendedPropertyOut.getParam();
			 if (cls.isAssignableFrom(propertyDefinition.getType())){
				 String errorMessage = String.format(
			                Strings.PropertyDefinitionTypeMismatch,
			                EwsUtilities.getPrintableTypeName(propertyDefinition.getType()),
			                EwsUtilities.getPrintableTypeName(cls));
			            throw new ArgumentException(errorMessage, "propertyDefinition");
			 }		
			propertyValueOut.setParam((T)extendedProperty.getValue());
			return true;
		} else {
			propertyValueOut.setParam(null);
			return false;
		}
	}
	
		
	/**
	 * Writes the update to XML.
	 * 
	 * @param writer
	 *            The writer.
	 * @param ewsObject
	 *            The ews object.
	 * @param propertyDefinition
	 *            Property definition.
	 * @return True if property generated serialization.
	 * @throws Exception
	 *             the exception
	 */
	@Override
	public boolean writeSetUpdateToXml(EwsServiceXmlWriter writer,
			ServiceObject ewsObject, PropertyDefinition propertyDefinition)
			throws Exception {
		List<ExtendedProperty> propertiesToSet = 
			new ArrayList<ExtendedProperty>();

		propertiesToSet.addAll(this.getAddedItems());
		propertiesToSet.addAll(this.getModifiedItems());

		for (ExtendedProperty extendedProperty : propertiesToSet) {
			writer.writeStartElement(XmlNamespace.Types, ewsObject
					.getSetFieldXmlElementName());
			extendedProperty.getPropertyDefinition().writeToXml(writer);

			writer.writeStartElement(XmlNamespace.Types, ewsObject
					.getXmlElementName());
			extendedProperty.writeToXml(writer,
					XmlElementNames.ExtendedProperty);
			writer.writeEndElement();

			writer.writeEndElement();
		}

		for (ExtendedProperty extendedProperty : this.getRemovedItems()) {
			writer.writeStartElement(XmlNamespace.Types, ewsObject
					.getDeleteFieldXmlElementName());
			extendedProperty.getPropertyDefinition().writeToXml(writer);
			writer.writeEndElement();
		}

		return true;
	}

	/**
	 * Writes the deletion update to XML.
	 * 
	 * @param writer
	 *            The writer.
	 * @param ewsObject
	 *            The ews object.
	 * @return True if property generated serialization.
	 * @throws javax.xml.stream.XMLStreamException
	 *             the xML stream exception
	 * @throws ServiceXmlSerializationException
	 *             the service xml serialization exception
	 */
	@Override
	public boolean writeDeleteUpdateToXml(EwsServiceXmlWriter writer,
			ServiceObject ewsObject) throws XMLStreamException,
			ServiceXmlSerializationException {
		for (ExtendedProperty extendedProperty : this.getItems()) {
			writer.writeStartElement(XmlNamespace.Types, ewsObject
					.getDeleteFieldXmlElementName());
			extendedProperty.getPropertyDefinition().writeToXml(writer);
			writer.writeEndElement();
		}

		return true;
	}
}
