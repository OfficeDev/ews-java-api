/**************************************************************************
 * copyright file="ComplexPropertyDefinitionBase.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ComplexPropertyDefinitionBase.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.EnumSet;

/**
 * Represents abstract complex property definition.
 * 
 */
abstract class ComplexPropertyDefinitionBase extends PropertyDefinition {

	/**
	 * * Initializes a new instance.
	 * 
	 * @param xmlElementName
	 *            Name of the XML element.
	 * @param flags
	 *            The flags.
	 * @param version
	 *            The version.
	 */
	protected ComplexPropertyDefinitionBase(String xmlElementName,
			EnumSet<PropertyDefinitionFlags> flags, ExchangeVersion version) {
		super(xmlElementName, flags, version);
	}

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
	protected ComplexPropertyDefinitionBase(String xmlElementName, String uri,
			ExchangeVersion version) {
		super(xmlElementName, uri, version);
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
	protected ComplexPropertyDefinitionBase(String xmlElementName, String uri,
			EnumSet<PropertyDefinitionFlags> flags, ExchangeVersion version) {
		super(xmlElementName, uri, flags, version);
	}

	/***
	 * Creates the property instance.
	 * 
	 * @param owner
	 *            The owner.
	 * @return ComplexProperty.
	 */
	protected abstract ComplexProperty createPropertyInstance(
			ServiceObject owner);

	/**
	 * * Internals the load from XML.
	 * 
	 * @param reader
	 *            The reader.
	 * @param propertyBag
	 *            The property bag.
	 * @throws Exception
	 *             the exception
	 */
	protected void internalLoadFromXml(EwsServiceXmlReader reader,
			PropertyBag propertyBag) throws Exception {
		OutParam<Object> complexProperty = new OutParam<Object>();

		boolean justCreated = getPropertyInstance(propertyBag,complexProperty);
		if (!justCreated && this.hasFlag(PropertyDefinitionFlags.UpdateCollectionItems, propertyBag.getOwner().getService().getRequestedServerVersion()))
		{
			ComplexProperty c = (ComplexProperty)complexProperty.getParam();
			if (complexProperty.getParam() instanceof ComplexProperty) {
				c.updateFromXml(reader, reader.getLocalName());
			}
				
				
			
		}
		else{
		ComplexProperty c = (ComplexProperty)complexProperty.getParam();
		c.loadFromXml(reader, reader.getLocalName());
		}
		/*if (!propertyBag.tryGetValue(this, complexProperty) ||
				 !this.hasFlag(PropertyDefinitionFlags.ReuseInstance)) {
			complexProperty.setParam(this.createPropertyInstance(propertyBag
					.getOwner()));
		}
		if (complexProperty.getParam() instanceof ComplexProperty) {
			ComplexProperty c = (ComplexProperty)complexProperty.getParam();
			c.loadFromXml(reader, reader.getLocalName());
		}*/
		propertyBag.setObjectFromPropertyDefinition(this, complexProperty
				.getParam());
	}


	

    /** 
     * Gets the property instance. 
     *@param propertyBag The property bag. 
     *@param complexProperty The property instance. 
     *@return True if the instance is newly created.
     * 
	 */
    private boolean getPropertyInstance(PropertyBag propertyBag, OutParam<Object> complexProperty)
    {
        boolean retValue = false;
        if (!propertyBag.tryGetValue(this, complexProperty) || !this.hasFlag(PropertyDefinitionFlags.ReuseInstance,propertyBag.getOwner().getService().getRequestedServerVersion()));
        {
        	complexProperty.setParam(this.createPropertyInstance(propertyBag
					.getOwner()));
            retValue = true;
        }
        return retValue;
    
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
		reader.ensureCurrentNodeIsStartElement(XmlNamespace.Types, this
				.getXmlElement());

		if (!reader.isEmptyElement() || reader.hasAttributes()) {
			this.internalLoadFromXml(reader, propertyBag);
		}
		reader.readEndElementIfNecessary(XmlNamespace.Types, this
				.getXmlElement());
	}

	/**
	 * * Writes to XML.
	 * 
	 * @param writer
	 *            The writer.
	 * @param propertyBag
	 *            The property bag.
	 * @param isUpdateOperation
	 *            Indicates whether the context is an update operation.
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void writePropertyValueToXml(EwsServiceXmlWriter writer,
			PropertyBag propertyBag, boolean isUpdateOperation)
			throws Exception {
		ComplexProperty complexProperty = (ComplexProperty)propertyBag
				.getObjectFromPropertyDefinition(this);
		if (complexProperty != null) {
			complexProperty.writeToXml(writer, this.getXmlElement());
		}
	}
}
