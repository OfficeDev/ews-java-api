/**************************************************************************
 * copyright file="ServiceObject.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ServiceObject.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/***
 * Represents the base abstract class for all item and folder types.
 * 
 */
public abstract class ServiceObject {

	/** The lock object. */
	private Object lockObject = new Object();

	/** The service. */
	private ExchangeService service;

	/** The property bag. */
	private PropertyBag propertyBag;

	/** The xml element name. */
	private String xmlElementName;

	/**
	 * Triggers dispatch of the change event.
	 */
	protected void changed() {

		for (IServiceObjectChangedDelegate change : this.onChange) {
			change.serviceObjectChanged(this);
		}
	}

	/**
	 * Throws exception if this is a new service object.
	 * 
	 * @throws InvalidOperationException
	 *             the invalid operation exception
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	protected void throwIfThisIsNew() throws InvalidOperationException,
	ServiceLocalException {
		if (this.isNew()) {
			throw new InvalidOperationException(Strings.
					ServiceObjectDoesNotHaveId);
		}
	}

	/**
	 * Throws exception if this is not a new service object.
	 * 
	 * @throws InvalidOperationException
	 *             the invalid operation exception
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	protected void throwIfThisIsNotNew() throws InvalidOperationException,
			ServiceLocalException {
		if (!this.isNew()) {
			throw new InvalidOperationException(Strings.
					ServiceObjectAlreadyHasId);
		}
	}

	// / This methods lets subclasses of ServiceObject override the default
	// mechanism
	// / by which the XML element name associated with their type is retrieved.

	/**
	 * This methods lets subclasses of ServiceObject override the default
	 * mechanism by which the XML element name associated with their type is
	 * retrieved.
	 * 
	 * @return String
	 */
	protected String getXmlElementNameOverride() {
		return null;
	}

	/**
	 * GetXmlElementName retrieves the XmlElementName of this type based on the
	 * EwsObjectDefinition attribute that decorates it, if present.
	 * 
	 * @return The XML element name associated with this type.
	 */
	protected String getXmlElementName() {
		if (this.isNullOrEmpty(this.xmlElementName)) {
			this.xmlElementName = this.getXmlElementNameOverride();
			if (this.isNullOrEmpty(this.xmlElementName)) {
				synchronized (this.lockObject) {

					ServiceObjectDefinition annotation = this.getClass()
							.getAnnotation(ServiceObjectDefinition.class);
					if (null != annotation) {
						this.xmlElementName = annotation.xmlElementName();
					}
				}
			}
		}
		EwsUtilities
				.EwsAssert(
						!isNullOrEmpty(this.xmlElementName),
						"EwsObject.GetXmlElementName",
						String
								.format(
										"The class %s does not have an " +
										"associated XML element name.",
										this.getClass().getName()));

		return this.xmlElementName;
	}

	/**
	 * Gets the name of the change XML element.
	 * 
	 * @return the change xml element name
	 */
	protected String getChangeXmlElementName() {
		return XmlElementNames.ItemChange;
	}

	/**
	 * Gets the name of the set field XML element.
	 * 
	 * @return String
	 */
	protected String getSetFieldXmlElementName() {
		return XmlElementNames.SetItemField;
	}

	/**
	 * Gets the name of the delete field XML element.
	 * 
	 * @return String
	 */
	protected String getDeleteFieldXmlElementName() {
		return XmlElementNames.DeleteItemField;
	}

	/**
	 * Gets a value indicating whether a time zone SOAP header should be emitted
	 * in a CreateItem or UpdateItem request so this item can be property saved
	 * or updated.
	 * 
	 * @param isUpdateOperation
	 *            the is update operation
	 * @return boolean
	 * @throws ServiceLocalException 
	 * @throws Exception 
	 */
	protected boolean getIsTimeZoneHeaderRequired(boolean isUpdateOperation) throws ServiceLocalException, Exception {
		return false;
	}

	/**
	 * Determines whether properties defined with
	 * ScopedDateTimePropertyDefinition require custom time zone scoping.
	 * 
	 * @return boolean
	 */
	protected boolean getIsCustomDateTimeScopingRequired() {
		return false;
	}

	/**
	 * The property bag holding property values for this object.
	 * 
	 * @return the property bag
	 */
	protected PropertyBag getPropertyBag() {
		return this.propertyBag;
	}

	/**
	 * Internal constructor.
	 * 
	 * @param service
	 *            the service
	 * @throws Exception
	 *             the exception
	 */
	protected ServiceObject(ExchangeService service) throws Exception {
		EwsUtilities.validateParam(service, "service");
		EwsUtilities.validateServiceObjectVersion(this, service
				.getRequestedServerVersion());
		this.service = service;
		this.propertyBag = new PropertyBag(this);
	}

	/**
	 * Gets the schema associated with this type of object.
	 * 
	 * @return ServiceObjectSchema
	 */
	public ServiceObjectSchema schema() {
		return this.getSchema();
	}

	/**
	 * Internal method to return the schema associated with this type of object.
	 * 
	 * @return the schema
	 */
	protected abstract ServiceObjectSchema getSchema();

	/**
	 * Gets the minimum required server version.
	 * 
	 * @return the minimum required server version
	 */
	protected abstract ExchangeVersion getMinimumRequiredServerVersion();

	/**
	 * Loads service object from XML.
	 * 
	 * @param reader
	 *            the reader
	 * @param clearPropertyBag
	 *            the clear property bag
	 * @throws Exception
	 *             the exception
	 */
	protected void loadFromXml(EwsServiceXmlReader reader,
			boolean clearPropertyBag) throws Exception {

		this.getPropertyBag().loadFromXml(reader, clearPropertyBag, 
				null, // propertySet
				false); // summaryPropertiesOnly

	}

	// / Validates this instance.
	/**
	 * Validate.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	protected void validate() throws Exception {
		this.getPropertyBag().validate();
	}

	// / Loads service object from XML.
	/**
	 * Load from xml.
	 * 
	 * @param reader
	 *            the reader
	 * @param clearPropertyBag
	 *            the clear property bag
	 * @param requestedPropertySet
	 *            the requested property set
	 * @param summaryPropertiesOnly
	 *            the summary properties only
	 * @throws Exception
	 *             the exception
	 */
	protected void loadFromXml(EwsServiceXmlReader reader,
			boolean clearPropertyBag, PropertySet requestedPropertySet,
			boolean summaryPropertiesOnly) throws Exception {

		this.getPropertyBag().loadFromXml(reader, clearPropertyBag,
				requestedPropertySet, summaryPropertiesOnly);

	}

	// Clears the object's change log.
	/**
	 * Clear change log.
	 */
	protected void clearChangeLog() {

		this.getPropertyBag().clearChangeLog();
	}

	// / Writes service object as XML.
	/**
	 * Write to xml.
	 * 
	 * @param writer
	 *            the writer
	 * @throws Exception
	 *             the exception
	 */
	protected void writeToXml(EwsServiceXmlWriter writer) throws Exception {

		this.getPropertyBag().writeToXml(writer);
	}

	// Writes service object for update as XML.
	/**
	 * Write to xml for update.
	 * 
	 * @param writer
	 *            the writer
	 * @throws Exception
	 *             the exception
	 */
	protected void writeToXmlForUpdate(EwsServiceXmlWriter writer)
			throws Exception {

		this.getPropertyBag().writeToXmlForUpdate(writer);
	}

	// / Loads the specified set of properties on the object.
	/**
	 * Internal load.
	 * 
	 * @param propertySet
	 *            the property set
	 * @throws Exception
	 *             the exception
	 */
	protected abstract void internalLoad(PropertySet propertySet)
			throws Exception;

	// / Deletes the object.
	/**
	 * Internal delete.
	 * 
	 * @param deleteMode
	 *            the delete mode
	 * @param sendCancellationsMode
	 *            the send cancellations mode
	 * @param affectedTaskOccurrences
	 *            the affected task occurrences
	 * @throws Exception
	 *             the exception
	 */
	protected abstract void internalDelete(DeleteMode deleteMode,
			SendCancellationsMode sendCancellationsMode,
			AffectedTaskOccurrence affectedTaskOccurrences) throws Exception;

	// / Loads the specified set of properties. Calling this method results in a
	// call to EWS.
	/**
	 * Load.
	 * 
	 * @param propertySet
	 *            the property set
	 * @throws Exception
	 *             the exception
	 */
	public void load(PropertySet propertySet) throws Exception {
		this.internalLoad(propertySet);
	}

	// Loads the first class properties. Calling this method results in a call
	// to EWS.
	/**
	 * Load.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public void load() throws Exception {
		this.internalLoad(PropertySet.getFirstClassProperties());
	}

	/**
	 * * Gets the value of specified property in this instance.
	 * 
	 * @param propertyDefinition
	 *            Definition of the property to get.
	 * @return The value of specified property in this instance.
	 * @throws Exception
	 *             the exception
	 */
	public Object getObjectFromPropertyDefinition(
			PropertyDefinitionBase propertyDefinition) throws Exception {
		OutParam<Object> propertyValue = new OutParam<Object>();
		PropertyDefinition propDef = (PropertyDefinition)propertyDefinition;
		
		if (propDef != null)
        {
            return this.getPropertyBag().getObjectFromPropertyDefinition(propDef);
        }
        else
        {
            ExtendedPropertyDefinition extendedPropDef = (ExtendedPropertyDefinition)propertyDefinition;
            if (extendedPropDef != null)
            {
                if (this.tryGetExtendedProperty(Object.class, extendedPropDef, propertyValue))
                {
                    return propertyValue;
                }
                else
                {
                    throw new ServiceObjectPropertyException(Strings.MustLoadOrAssignPropertyBeforeAccess, propertyDefinition);
                }
            }
            else
            {
                // E14:226103 -- Other subclasses of PropertyDefinitionBase are not supported.
                throw new NotSupportedException(String.format(
                    Strings.OperationNotSupportedForPropertyDefinitionType,
                    propertyDefinition.getType().getName()));
            }
        }
	}

	/**
	 * Try to get the value of a specified extended property in this instance.
	 * 
	 * @param propertyDefinition
	 *            the property definition
	 * @param propertyValue
	 *            the property value
	 * @return true, if successful
	 * @throws Exception
	 *             the exception
	 */
	protected <T> boolean tryGetExtendedProperty(Class<T> cls,
			ExtendedPropertyDefinition propertyDefinition,
			OutParam<T> propertyValue) throws Exception {
		ExtendedPropertyCollection propertyCollection = this
				.getExtendedProperties();
	
		if ((propertyCollection != null)&& 
				propertyCollection.tryGetValue(cls, propertyDefinition,propertyValue)) {
			return true;
		} else {
			propertyValue.setParam(null);
			return false;
		}
	}

	/**
	 * Try to get the value of a specified property in this instance. 
	 * 
	 * @param propertyDefinition
	 *            The property definition.
	 * @param propertyValue
	 *            The property value
	 * @return True if property retrieved, false otherwise.
	 * @throws Exception 
	 */
    public boolean tryGetProperty(PropertyDefinitionBase propertyDefinition, OutParam<Object> propertyValue) throws Exception
    {
        return this.tryGetProperty(Object.class, propertyDefinition, propertyValue);
    }

	/**
	 * Try to get the value of a specified property in this instance.
	 * 
	 * @param propertyDefinition
	 *            the property definition
	 * @param propertyValue
	 *            the property value
	 * @return true, if successful
	 * @throws Exception
	 *             the exception
	 */
	public <T> boolean tryGetProperty(Class<T> cls, PropertyDefinitionBase propertyDefinition,
			OutParam<T> propertyValue) throws Exception {
		
		PropertyDefinition propDef = (PropertyDefinition)propertyDefinition;
        if (propDef != null)
        {
            return this.getPropertyBag().tryGetPropertyType(cls, propDef, propertyValue);
        }
        else
        {
            ExtendedPropertyDefinition extPropDef = (ExtendedPropertyDefinition)propertyDefinition;
            if (extPropDef != null)
            {
                return this.tryGetExtendedProperty(cls, extPropDef, propertyValue);
            }
            else
            {
                // E14:226103 -- Other subclasses of PropertyDefinitionBase are not supported.
                throw new NotSupportedException(String.format(
                    Strings.OperationNotSupportedForPropertyDefinitionType,
                    propertyDefinition.getType().getName()));
            }
        }
	}

	/**
	 * Gets the collection of loaded property definitions.
	 * 
	 * @return the loaded property definitions
	 * @throws Exception
	 *             the exception
	 */
	public Collection<PropertyDefinitionBase> getLoadedPropertyDefinitions()
			throws Exception {

		Collection<PropertyDefinitionBase> propDefs = 
			new ArrayList<PropertyDefinitionBase>();
		for (PropertyDefinition propDef : this.getPropertyBag().getProperties()
				.keySet()) {
			propDefs.add(propDef);
		}

		if (this.getExtendedProperties() != null) {
			for (ExtendedProperty extProp : getExtendedProperties()) {
				propDefs.add(extProp.getPropertyDefinition());
			}
		}

		return propDefs;
	}

	/**
	 * Gets the service.
	 * 
	 * @return the service
	 */
	public ExchangeService getService() {
		return service;
	}

	/**
	 * Sets the service.
	 * 
	 * @param service
	 *            the new service
	 */
	protected void setService(ExchangeService service) {
		this.service = service;
	}

	// / The property definition for the Id of this object.
	/**
	 * Gets the id property definition.
	 * 
	 * @return the id property definition
	 */
	protected PropertyDefinition getIdPropertyDefinition() {
		return null;
	}

	// / The unique Id of this object.
	/**
	 * Gets the id.
	 * 
	 * @return the id
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	protected ServiceId getId() throws ServiceLocalException {
		PropertyDefinition idPropertyDefinition = this
				.getIdPropertyDefinition();

		OutParam<Object> serviceId = new OutParam<Object>();

		if (idPropertyDefinition != null) {
			this.getPropertyBag().tryGetValue(idPropertyDefinition, serviceId);
		}

		return (ServiceId) serviceId.getParam();
	}

	// / Indicates whether this object is a real store item, or if it's a local
	// object
	// / that has yet to be saved.
	/**
	 * Checks if is new.
	 * 
	 * @return true, if is new
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public boolean isNew() throws ServiceLocalException {

		ServiceId id = this.getId();

		return id == null ? true : !id.isValid();

	}

	// / Gets a value indicating whether the object has been modified and should
	// be saved.
	/**
	 * Checks if is dirty.
	 * 
	 * @return true, if is dirty
	 */
	public boolean isDirty() {

		return this.getPropertyBag().getIsDirty();

	}

	// Gets the extended properties collection.
	/**
	 * Gets the extended properties.
	 * 
	 * @return the extended properties
	 * @throws Exception
	 *             the exception
	 */
	protected ExtendedPropertyCollection getExtendedProperties()
			throws Exception {
		return null;
	}

	/**
	 * Checks is the string is null or empty.
	 * 
	 * @param namespacePrefix
	 *            the namespace prefix
	 * @return true, if is null or empty
	 */
	private boolean isNullOrEmpty(String namespacePrefix) {
		return (namespacePrefix == null || namespacePrefix.isEmpty());

	}

	/** The on change. */
	private List<IServiceObjectChangedDelegate> onChange = 
		new ArrayList<IServiceObjectChangedDelegate>();

	/**
	 * Adds the service object changed event.
	 * 
	 * @param change
	 *            the change
	 */
	public void addServiceObjectChangedEvent(
			IServiceObjectChangedDelegate change) {
		this.onChange.add(change);
	}

	/**
	 * Removes the service object changed event.
	 * 
	 * @param change
	 *            the change
	 */
	public void removeServiceObjectChangedEvent(
			IServiceObjectChangedDelegate change) {
		this.onChange.remove(change);
	}

	/**
	 * Clear service object changed event.
	 */
	public void clearServiceObjectChangedEvent() {
		this.onChange.clear();
	}

}
