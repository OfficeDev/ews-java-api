/*
 * The MIT License
 * Copyright (c) 2012 Microsoft Corporation
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package microsoft.exchange.webservices.data.core;

import microsoft.exchange.webservices.data.ISelfValidate;
import microsoft.exchange.webservices.data.core.service.ServiceObject;
import microsoft.exchange.webservices.data.core.service.item.Item;
import microsoft.exchange.webservices.data.core.enumeration.property.BasePropertySet;
import microsoft.exchange.webservices.data.core.enumeration.property.PropertyDefinitionFlags;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.exception.misc.ArgumentException;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceLocalException;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceObjectPropertyException;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceVersionException;
import microsoft.exchange.webservices.data.misc.OutParam;
import microsoft.exchange.webservices.data.property.complex.ComplexProperty;
import microsoft.exchange.webservices.data.property.complex.IComplexPropertyChanged;
import microsoft.exchange.webservices.data.property.complex.IComplexPropertyChangedDelegate;
import microsoft.exchange.webservices.data.property.complex.IOwnedProperty;
import microsoft.exchange.webservices.data.property.definition.ComplexPropertyDefinitionBase;
import microsoft.exchange.webservices.data.property.definition.PropertyDefinition;
import microsoft.exchange.webservices.data.security.XmlNodeType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Represents a property bag keyed on PropertyDefinition objects.
 */
public class PropertyBag implements IComplexPropertyChanged, IComplexPropertyChangedDelegate {

  /**
   * The owner.
   */
  private ServiceObject owner;

  /**
   * The is dirty.
   */
  private boolean isDirty;

  /**
   * The loading.
   */
  private boolean loading;

  /**
   * The only summary property requested.
   */
  private boolean onlySummaryPropertiesRequested;

  /**
   * The loaded property.
   */
  private List<PropertyDefinition> loadedProperties =
      new ArrayList<PropertyDefinition>();

  /**
   * The property.
   */
  private Map<PropertyDefinition, Object> properties =
      new HashMap<PropertyDefinition, Object>();

  /**
   * The deleted property.
   */
  private Map<PropertyDefinition, Object> deletedProperties =
      new HashMap<PropertyDefinition, Object>();

  /**
   * The modified property.
   */
  private List<PropertyDefinition> modifiedProperties =
      new ArrayList<PropertyDefinition>();

  /**
   * The added property.
   */
  private List<PropertyDefinition> addedProperties =
      new ArrayList<PropertyDefinition>();

  /**
   * The requested property set.
   */
  private PropertySet requestedPropertySet;

  /**
   * Initializes a new instance of PropertyBag.
   *
   * @param owner The owner of the bag.
   */
  public PropertyBag(ServiceObject owner) {
    EwsUtilities.ewsAssert(owner != null, "PropertyBag.ctor", "owner is null");

    this.owner = owner;
  }

  /**
   * Gets a Map holding the bag's property.
   *
   * @return A Map holding the bag's property.
   */
  public Map<PropertyDefinition, Object> getProperties() {
    return this.properties;
  }

  /**
   * Gets the owner of this bag.
   *
   * @return The owner of this bag.
   */
  public ServiceObject getOwner() {
    return this.owner;
  }

  /**
   * Indicates if a bag has pending changes.
   *
   * @return True if the bag has pending changes, false otherwise.
   */
  public boolean getIsDirty() {
    int changes = this.modifiedProperties.size() +
        this.deletedProperties.size() + this.addedProperties.size();
    return changes > 0 || this.isDirty;
  }

  /**
   * Adds the specified property to the specified change list if it is not
   * already present.
   *
   * @param propertyDefinition The property to add to the change list.
   * @param changeList         The change list to add the property to.
   */
  protected static void addToChangeList(
      PropertyDefinition propertyDefinition,
      List<PropertyDefinition> changeList) {
    if (!changeList.contains(propertyDefinition)) {
      changeList.add(propertyDefinition);
    }
  }

  /**
   * Checks if is property loaded.
   *
   * @param propertyDefinition the property definition
   * @return true, if is property loaded
   */
  public boolean isPropertyLoaded(PropertyDefinition propertyDefinition) {
    // Is the property loaded?
    if (this.loadedProperties.contains(propertyDefinition)) {
      return true;
    } else {
      // Was the property requested?
      return this.isRequestedProperty(propertyDefinition);
    }
  }

  /**
   * Checks if is requested property.
   *
   * @param propertyDefinition the property definition
   * @return true, if is requested property
   */
  private boolean isRequestedProperty(PropertyDefinition propertyDefinition) {
    // If no requested property set, then property wasn't requested.
    if (this.requestedPropertySet == null) {
      return false;
    }

    // If base property set is all first-class property, use the
    // appropriate list of
    // property definitions to see if this property was requested.
    // Otherwise, property had
    // to be explicitly requested and needs to be listed in
    // AdditionalProperties.
    if (this.requestedPropertySet.getBasePropertySet() == BasePropertySet.FirstClassProperties) {
      List<PropertyDefinition> firstClassProps =
          this.onlySummaryPropertiesRequested ? this
              .getOwner().getSchema().getFirstClassSummaryProperties() :
              this.getOwner().getSchema().getFirstClassProperties();

      return firstClassProps.contains(propertyDefinition) ||
          this.requestedPropertySet.contains(propertyDefinition);
    } else {
      return this.requestedPropertySet.contains(propertyDefinition);
    }
  }

  /**
   * Determines whether the specified property has been updated.
   *
   * @param propertyDefinition The property definition.
   * @return true if the specified property has been updated; otherwise,
   * false.
   */
  public boolean isPropertyUpdated(PropertyDefinition propertyDefinition) {
    return this.modifiedProperties.contains(propertyDefinition) ||
        this.addedProperties.contains(propertyDefinition);
  }

  /**
   * Tries to get a property value based on a property definition.
   *
   * @param propertyDefinition    The property definition.
   * @param propertyValueOutParam The property value.
   * @return True if property was retrieved.
   */
  protected boolean tryGetProperty(PropertyDefinition propertyDefinition,
      OutParam<Object> propertyValueOutParam) {
    OutParam<ServiceLocalException> serviceExceptionOutParam =
        new OutParam<ServiceLocalException>();
    propertyValueOutParam.setParam(this.getPropertyValueOrException(
        propertyDefinition, serviceExceptionOutParam));
    return serviceExceptionOutParam.getParam() == null;
  }

  /**
   * Tries to get a property value based on a property definition.
   *
   * @param <T>                the types of the property
   * @param propertyDefinition the property definition
   * @param propertyValue      the property value
   * @return true if property was retrieved
   * @throws ArgumentException on validation error
   */
  public <T> boolean tryGetPropertyType(Class<T> cls, PropertyDefinition propertyDefinition,
      OutParam<T> propertyValue) throws ArgumentException {
    // Verify that the type parameter and
    //property definition's type are compatible.
    if (!cls.isAssignableFrom(propertyDefinition.getType())) {
      String errorMessage = String.format(
          "Property definition type '%s' and type parameter '%s' aren't compatible.",
          propertyDefinition.getType().getSimpleName(),
          cls.getSimpleName());
      throw new ArgumentException(errorMessage, "propertyDefinition");
    }

    OutParam<Object> value = new OutParam<Object>();
    boolean result = this.tryGetProperty(propertyDefinition, value);
    if (result) {
      propertyValue.setParam((T) value.getParam());
    } else {
      propertyValue.setParam(null);
    }

    return result;
  }


  /**
   * Gets the property value.
   *
   * @param propertyDefinition       The property definition.
   * @param serviceExceptionOutParam Exception that would be raised if there's an error retrieving
   *                                 the property.
   * @return Property value. May be null.
   */
  private <T> T getPropertyValueOrException(
      PropertyDefinition propertyDefinition,
      OutParam<ServiceLocalException> serviceExceptionOutParam) {
    OutParam<T> propertyValueOutParam = new OutParam<T>();
    propertyValueOutParam.setParam(null);
    serviceExceptionOutParam.setParam(null);

    if (propertyDefinition.getVersion().ordinal() > this.getOwner()
        .getService().getRequestedServerVersion().ordinal()) {
      serviceExceptionOutParam.setParam(new ServiceVersionException(
          String.format("The property %s is valid only for Exchange %s or later versions.",
              propertyDefinition.getName(), propertyDefinition
                  .getVersion())));
      return null;
    }

    if (this.tryGetValue(propertyDefinition, propertyValueOutParam)) {
      // If the requested property is in the bag, return it.
      return propertyValueOutParam.getParam();
    } else {
      if (propertyDefinition
          .hasFlag(PropertyDefinitionFlags.AutoInstantiateOnRead)) {
        EwsUtilities
            .ewsAssert(propertyDefinition instanceof ComplexPropertyDefinitionBase,
                "PropertyBag.get_this[]",
                "propertyDefinition is " +
                    "marked with AutoInstantiateOnRead " +
                    "but is not a descendant " +
                    "of ComplexPropertyDefinitionBase");

        // The requested property is an auto-instantiate-on-read
        // property
        ComplexPropertyDefinitionBase complexPropertyDefinition =
            (ComplexPropertyDefinitionBase) propertyDefinition;
        ComplexProperty propertyValue = complexPropertyDefinition
            .createPropertyInstance(getOwner());

        // XXX: It could be dangerous to return complex value instead of <T>
        propertyValueOutParam.setParam((T) propertyValue);
        if (propertyValue != null) {
          this.initComplexProperty(propertyValue);
          this.properties.put(propertyDefinition, propertyValue);
        }
      } else {
        // If the property is not the Id (we need to let developers read
        // the Id when it's null) and if has
        // not been loaded, we throw.
        if (propertyDefinition != this.getOwner()
            .getIdPropertyDefinition()) {
          if (!this.isPropertyLoaded(propertyDefinition)) {
            serviceExceptionOutParam
                .setParam(new ServiceObjectPropertyException(
                    "You must load or assign this property before you can read its value.",
                    propertyDefinition));
            return null;
          }

          // Non-nullable property (int, bool, etc.) must be
          // assigned or loaded; cannot return null value.
          if (!propertyDefinition.isNullable()) {
            String errorMessage = this
                .isRequestedProperty(propertyDefinition) ? "This property was requested, but it wasn't returned by the server."
                                                         : "You must assign this property before you can read its value.";
            serviceExceptionOutParam
                .setParam(new ServiceObjectPropertyException(
                    errorMessage, propertyDefinition));
          }
        }
      }
      return propertyValueOutParam.getParam();
    }
  }

  /**
   * Sets the isDirty flag to true and triggers dispatch of the change event
   * to the owner of the property bag. Changed must be called whenever an
   * operation that changes the state of this property bag is performed (e.g.
   * adding or removing a property).
   */
  public void changed() {
    this.isDirty = true;
    this.getOwner().changed();
  }

  /**
   * Determines whether the property bag contains a specific property.
   *
   * @param propertyDefinition The property to check against.
   * @return True if the specified property is in the bag, false otherwise.
   */
  public boolean contains(PropertyDefinition propertyDefinition) {
    return this.properties.containsKey(propertyDefinition);
  }



  /**
   * Tries to retrieve the value of the specified property.
   *
   * @param propertyDefinition the property for which to retrieve a value
   * @param propertyValueOutParam if the method succeeds, contains the value of the property
   * @return true if the value could be retrieved, false otherwise
   */
  public <T> boolean tryGetValue(PropertyDefinition propertyDefinition, OutParam<T> propertyValueOutParam) {
    if (this.properties.containsKey(propertyDefinition)) {
      T param = (T) properties.get(propertyDefinition);
      propertyValueOutParam.setParam(param);
      return true;
    } else {
      propertyValueOutParam.setParam(null);
      return false;
    }
  }

  /**
   * Handles a change event for the specified property.
   *
   * @param complexProperty The property that changes.
   */
  protected void propertyChanged(ComplexProperty complexProperty) {
    Iterator<Entry<PropertyDefinition, Object>> it = this.properties
        .entrySet().iterator();
    while (it.hasNext()) {
      Entry<PropertyDefinition, Object> keyValuePair = it.next();
      if (keyValuePair.getValue().equals(complexProperty)) {
        if (!this.deletedProperties.containsKey(keyValuePair.getKey())) {
          addToChangeList(keyValuePair.getKey(),
              this.modifiedProperties);
          this.changed();
        }
      }
    }
  }

  /**
   * Deletes the property from the bag.
   *
   * @param propertyDefinition The property to delete.
   */
  protected void deleteProperty(PropertyDefinition propertyDefinition) {
    if (!this.deletedProperties.containsKey(propertyDefinition)) {
      Object propertyValue = null;

      if (this.properties.containsKey(propertyDefinition)) {
        propertyValue = this.properties.get(propertyDefinition);
      }

      this.properties.remove(propertyDefinition);
      this.modifiedProperties.remove(propertyDefinition);
      this.deletedProperties.put(propertyDefinition, propertyValue);

      if (propertyValue instanceof ComplexProperty) {
        ComplexProperty complexProperty =
            (ComplexProperty) propertyValue;
        complexProperty.addOnChangeEvent(this);
      }
    }
  }

  /**
   * Clears the bag.
   */
  protected void clear() {
    this.clearChangeLog();
    this.properties.clear();
    this.loadedProperties.clear();
    this.requestedPropertySet = null;
  }

  /**
   * Clears the bag's change log.
   */
  public void clearChangeLog() {
    this.deletedProperties.clear();
    this.modifiedProperties.clear();
    this.addedProperties.clear();

    Iterator<Entry<PropertyDefinition, Object>> it = this.properties
        .entrySet().iterator();
    while (it.hasNext()) {
      Entry<PropertyDefinition, Object> keyValuePair = it.next();
      if (keyValuePair.getValue() instanceof ComplexProperty) {
        ComplexProperty complexProperty = (ComplexProperty) keyValuePair
            .getValue();
        complexProperty.clearChangeLog();
      }
    }

    this.isDirty = false;
  }

  /**
   * Loads property from XML and inserts them in the bag.
   *
   * @param reader                         The reader from which to read the property.
   * @param clear                          Indicates whether the bag should be cleared before property
   *                                       are loaded.
   * @param requestedPropertySet           The requested property set.
   * @param onlySummaryPropertiesRequested Indicates whether summary or full property were requested.
   * @throws Exception the exception
   */
  public void loadFromXml(EwsServiceXmlReader reader, boolean clear, PropertySet requestedPropertySet,
      boolean onlySummaryPropertiesRequested) throws Exception {
    if (clear) {
      this.clear();
    }

    // Put the property bag in "loading" mode. When in loading mode, no
    // checking is done
    // when setting property values.
    this.loading = true;

    this.requestedPropertySet = requestedPropertySet;
    this.onlySummaryPropertiesRequested = onlySummaryPropertiesRequested;

    try {
      do {
        reader.read();

        if (reader.getNodeType().getNodeType() == XmlNodeType.START_ELEMENT) {
          OutParam<PropertyDefinition> propertyDefinitionOut =
              new OutParam<PropertyDefinition>();
          PropertyDefinition propertyDefinition;

          if (this.getOwner().schema().tryGetPropertyDefinition(
              reader.getLocalName(), propertyDefinitionOut)) {
            propertyDefinition = propertyDefinitionOut.getParam();
            propertyDefinition.loadPropertyValueFromXml(reader,
                this);

            this.loadedProperties.add(propertyDefinition);
          } else {
            reader.skipCurrentElement();
          }
        }
      } while (!reader.isEndElement(XmlNamespace.Types, this.getOwner()
          .getXmlElementName()));

      this.clearChangeLog();
    } finally {
      this.loading = false;
    }
  }

  /**
   * Writes the bag's property to XML.
   *
   * @param writer The writer to write the property to.
   * @throws Exception the exception
   */
  public void writeToXml(EwsServiceXmlWriter writer) throws Exception {
    writer.writeStartElement(XmlNamespace.Types, this.getOwner()
        .getXmlElementName());

    Iterator<PropertyDefinition> it = this.getOwner().getSchema()
        .iterator();
    while (it.hasNext()) {
      PropertyDefinition propertyDefinition = it.next();
      // The following test should not be necessary since the property bag
      // prevents
      // property to be set if they don't have the CanSet flag, but it
      // doesn't hurt...
      if (propertyDefinition
          .hasFlag(PropertyDefinitionFlags.CanSet, writer.getService().getRequestedServerVersion())) {
        if (this.contains(propertyDefinition)) {
          propertyDefinition.writePropertyValueToXml(writer, this,
              false /* isUpdateOperation */);
        }
      }
    }

    writer.writeEndElement();
  }

  /**
   * Writes the EWS update operations corresponding to the changes that
   * occurred in the bag to XML.
   *
   * @param writer The writer to write the updates to.
   * @throws Exception the exception
   */
  public void writeToXmlForUpdate(EwsServiceXmlWriter writer)
      throws Exception {
    writer.writeStartElement(XmlNamespace.Types, this.getOwner()
        .getChangeXmlElementName());

    this.getOwner().getId().writeToXml(writer);

    writer.writeStartElement(XmlNamespace.Types, XmlElementNames.Updates);

    for (PropertyDefinition propertyDefinition : this.addedProperties) {
      this.writeSetUpdateToXml(writer, propertyDefinition);
    }

    for (PropertyDefinition propertyDefinition : this.modifiedProperties) {
      this.writeSetUpdateToXml(writer, propertyDefinition);
    }

    Iterator<Entry<PropertyDefinition, Object>> it = this.deletedProperties
        .entrySet().iterator();
    while (it.hasNext()) {
      Entry<PropertyDefinition, Object> property = it.next();
      this.writeDeleteUpdateToXml(writer, property.getKey(), property
          .getValue());
    }

    writer.writeEndElement();
    writer.writeEndElement();
  }

  /**
   * Determines whether an EWS UpdateItem/UpdateFolder call is necessary to
   * save the changes that occurred in the bag.
   *
   * @return True if an UpdateItem/UpdateFolder call is necessary, false
   * otherwise.
   */
  public boolean getIsUpdateCallNecessary() {
    List<PropertyDefinition> propertyDefinitions =
        new ArrayList<PropertyDefinition>();
    propertyDefinitions.addAll(this.addedProperties);
    propertyDefinitions.addAll(this.modifiedProperties);
    propertyDefinitions.addAll(this.deletedProperties.keySet());
    for (PropertyDefinition propertyDefinition : propertyDefinitions) {
      if (propertyDefinition.hasFlag(PropertyDefinitionFlags.CanUpdate)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Initializes a ComplexProperty instance. When a property is inserted into
   * the bag, it needs to be initialized in order for changes that occur on
   * that property to be properly detected and dispatched.
   *
   * @param complexProperty The ComplexProperty instance to initialize.
   */
  private void initComplexProperty(ComplexProperty complexProperty) {
    if (complexProperty != null) {
      complexProperty.addOnChangeEvent(this);
      if (complexProperty instanceof IOwnedProperty) {
        IOwnedProperty ownedProperty = (IOwnedProperty) complexProperty;
        ownedProperty.setOwner(this.getOwner());
      }
    }
  }

  /**
   * Writes an EWS SetUpdate opeartion for the specified property.
   *
   * @param writer             The writer to write the update to.
   * @param propertyDefinition The property fro which to write the update.
   * @throws Exception the exception
   */
  private void writeSetUpdateToXml(EwsServiceXmlWriter writer,
      PropertyDefinition propertyDefinition) throws Exception {
    // The following test should not be necessary since the property bag
    // prevents
    // property to be updated if they don't have the CanUpdate flag, but
    // it
    // doesn't hurt...
    if (propertyDefinition.hasFlag(PropertyDefinitionFlags.CanUpdate)) {
      Object propertyValue = this
          .getObjectFromPropertyDefinition(propertyDefinition);

      boolean handled = false;

      if (propertyValue instanceof ICustomXmlUpdateSerializer) {
        ICustomXmlUpdateSerializer updateSerializer =
            (ICustomXmlUpdateSerializer) propertyValue;
        handled = updateSerializer.writeSetUpdateToXml(writer, this
            .getOwner(), propertyDefinition);
      }

      if (!handled) {
        writer.writeStartElement(XmlNamespace.Types, this.getOwner()
            .getSetFieldXmlElementName());

        propertyDefinition.writeToXml(writer);

        writer.writeStartElement(XmlNamespace.Types, this.getOwner()
            .getXmlElementName());
        propertyDefinition
            .writePropertyValueToXml(writer, this,
                true /* isUpdateOperation */);
        writer.writeEndElement();

        writer.writeEndElement();
      }
    }
  }

  /**
   * Writes an EWS DeleteUpdate opeartion for the specified property.
   *
   * @param writer             The writer to write the update to.
   * @param propertyDefinition The property fro which to write the update.
   * @param propertyValue      The current value of the property.
   * @throws Exception the exception
   */
  private void writeDeleteUpdateToXml(EwsServiceXmlWriter writer,
      PropertyDefinition propertyDefinition, Object propertyValue)
      throws Exception {
    // The following test should not be necessary since the property bag
    // prevents
    // property to be deleted (set to null) if they don't have the
    // CanDelete flag,
    // but it doesn't hurt...
    if (propertyDefinition.hasFlag(PropertyDefinitionFlags.CanDelete)) {
      boolean handled = false;

      if (propertyValue instanceof ICustomXmlUpdateSerializer) {
        ICustomXmlUpdateSerializer updateSerializer =
            (ICustomXmlUpdateSerializer) propertyValue;
        handled = updateSerializer.writeDeleteUpdateToXml(writer, this
            .getOwner());
      }

      if (!handled) {
        writer.writeStartElement(XmlNamespace.Types, this.getOwner()
            .getDeleteFieldXmlElementName());
        propertyDefinition.writeToXml(writer);
        writer.writeEndElement();
      }
    }
  }

  /**
   * Validate property bag instance.
   *
   * @throws Exception the exception
   */
  public void validate() throws Exception {
    for (PropertyDefinition propertyDefinition : this.addedProperties) {
      this.validatePropertyValue(propertyDefinition);
    }

    for (PropertyDefinition propertyDefinition : this.modifiedProperties) {
      this.validatePropertyValue(propertyDefinition);
    }
  }

  /**
   * Validates the property value.
   *
   * @param propertyDefinition The property definition.
   * @throws Exception the exception
   */
  private void validatePropertyValue(PropertyDefinition propertyDefinition)
      throws Exception {
    OutParam<Object> propertyValueOut = new OutParam<Object>();
    if (this.tryGetProperty(propertyDefinition, propertyValueOut)) {
      Object propertyValue = propertyValueOut.getParam();

      if (propertyValue instanceof ISelfValidate) {
        ISelfValidate validatingValue = (ISelfValidate) propertyValue;
        validatingValue.validate();
      }
    }
  }

  /**
   * Gets the value of a property.
   *
   * @param propertyDefinition The property to get or set.
   * @return An object representing the value of the property.
   * @throws ServiceLocalException ServiceVersionException will be raised if this property
   *                               requires a later version of Exchange.
   *                               ServiceObjectPropertyException will be raised for get if
   *                               property hasn't been assigned or loaded, raised for set if
   *                               property cannot be updated or deleted.
   */
  public <T> T getObjectFromPropertyDefinition(PropertyDefinition propertyDefinition)
      throws ServiceLocalException {
    OutParam<ServiceLocalException> serviceExceptionOut =
        new OutParam<ServiceLocalException>();
    T propertyValue = getPropertyValueOrException(propertyDefinition, serviceExceptionOut);

    ServiceLocalException serviceException = serviceExceptionOut.getParam();
    if (serviceException != null) {
      throw serviceException;
    }
    return propertyValue;
  }

  /**
   * Gets the value of a property.
   *
   * @param propertyDefinition The property to get or set.
   * @param object             An object representing the value of the property.
   * @throws Exception the exception
   */
  public void setObjectFromPropertyDefinition(PropertyDefinition propertyDefinition, Object object)
      throws Exception {
    if (propertyDefinition.getVersion().ordinal() > this.getOwner()
        .getService().getRequestedServerVersion().ordinal()) {
      throw new ServiceVersionException(String.format(
          "The property %s is valid only for Exchange %s or later versions.",
          propertyDefinition.getName(), propertyDefinition
              .getVersion()));
    }

    // If the property bag is not in the loading state, we need to verify
    // whether
    // the property can actually be set or updated.
    if (!this.loading) {
      // If the owner is new and if the property cannot be set, throw.
      if (this.getOwner().isNew()
          && !propertyDefinition
          .hasFlag(PropertyDefinitionFlags.CanSet, this.getOwner()
              .getService().getRequestedServerVersion())) {
        throw new ServiceObjectPropertyException("This property is read-only and can't be set.", propertyDefinition);
      }

      if (!this.getOwner().isNew()) {
        // If owner is an item attachment, property cannot be updated
        // (EWS doesn't support updating item attachments)

        if ((this.getOwner() instanceof Item)) {
          Item ownerItem = (Item) this.getOwner();
          if (ownerItem.isAttachment()) {
            throw new ServiceObjectPropertyException("Item attachments can't be updated.",
                propertyDefinition);
          }
        }

        // If the property cannot be deleted, throw.
        if (object == null
            && !propertyDefinition
            .hasFlag(PropertyDefinitionFlags.CanDelete)) {
          throw new ServiceObjectPropertyException("This property can't be deleted.",
              propertyDefinition);
        }

        // If the property cannot be updated, throw.
        if (!propertyDefinition
            .hasFlag(PropertyDefinitionFlags.CanUpdate)) {
          throw new ServiceObjectPropertyException("This property can't be updated.",
              propertyDefinition);
        }
      }
    }

    // If the value is set to null, delete the property.
    if (object == null) {
      this.deleteProperty(propertyDefinition);
    } else {
      ComplexProperty complexProperty = null;
      Object currentValue = null;

      if (this.properties.containsKey(propertyDefinition)) {
        currentValue = this.properties.get(propertyDefinition);

        if (currentValue instanceof ComplexProperty) {
          complexProperty = (ComplexProperty) currentValue;
          complexProperty.removeChangeEvent(this);
        }
      }

      // If the property was to be deleted, the deletion becomes an
      // update.
      if (this.deletedProperties.containsKey(propertyDefinition)) {
        this.deletedProperties.remove(propertyDefinition);
        addToChangeList(propertyDefinition, this.modifiedProperties);
      } else {
        // If the property value was not set, we have a newly set
        // property.
        if (!this.properties.containsKey(propertyDefinition)) {
          addToChangeList(propertyDefinition, this.addedProperties);
        } else {
          // The last case is that we have a modified property.
          if (!this.modifiedProperties.contains(propertyDefinition)) {
            addToChangeList(propertyDefinition,
                this.modifiedProperties);
          }
        }
      }

      if (object instanceof ComplexProperty) {
        this.initComplexProperty((ComplexProperty) object);
      }
      this.properties.put(propertyDefinition, object);
      this.changed();
    }

  }

  /*
   * (non-Javadoc)
   *
   * @seemicrosoft.exchange.webservices.ComplexPropertyChangedInterface#
   * complexPropertyChanged(microsoft.exchange.webservices.ComplexProperty)
   */
  @Override
  public void complexPropertyChanged(ComplexProperty complexProperty) {
    this.propertyChanged(complexProperty);
  }
}
