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

package microsoft.exchange.webservices.data.property.complex;

import microsoft.exchange.webservices.data.attribute.EditorBrowsable;
import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.ICustomXmlUpdateSerializer;
import microsoft.exchange.webservices.data.core.service.ServiceObject;
import microsoft.exchange.webservices.data.core.enumeration.attribute.EditorBrowsableState;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceLocalException;
import microsoft.exchange.webservices.data.property.definition.PropertyDefinition;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a collection of property that can be sent to and retrieved from
 * EWS.
 *
 * @param <TComplexProperty> ComplexProperty type.
 */
@EditorBrowsable(state = EditorBrowsableState.Never)
public abstract class ComplexPropertyCollection
    <TComplexProperty extends ComplexProperty>
    extends ComplexProperty implements ICustomXmlUpdateSerializer,
    Iterable<TComplexProperty>, IComplexPropertyChangedDelegate<TComplexProperty> {

  /**
   * The item.
   */
  private final List<TComplexProperty> items = new ArrayList<TComplexProperty>();

  /**
   * The added item.
   */
  private final List<TComplexProperty> addedItems =
      new ArrayList<TComplexProperty>();

  /**
   * The modified item.
   */
  private final List<TComplexProperty> modifiedItems =
      new ArrayList<TComplexProperty>();

  /**
   * The removed item.
   */
  private final List<TComplexProperty> removedItems =
      new ArrayList<TComplexProperty>();

  /**
   * Creates the complex property.
   *
   * @param xmlElementName Name of the XML element.
   * @return Complex property instance.
   */
  protected abstract TComplexProperty createComplexProperty(
      String xmlElementName);

  /**
   * Gets the name of the collection item XML element.
   *
   * @param complexProperty The complex property.
   * @return XML element name.
   */
  protected abstract String getCollectionItemXmlElementName(
      TComplexProperty complexProperty);

  /**
   * Initializes a new instance of. ComplexPropertyCollection
   */
  protected ComplexPropertyCollection() {
    super();
  }

  /**
   * Item changed.
   *
   * @param property The complex property.
   */
  protected void itemChanged(final TComplexProperty property) {
    EwsUtilities.ewsAssert(
      property != null, "ComplexPropertyCollection.ItemChanged",
      "The complexProperty argument must be not null"
    );

    if (!this.addedItems.contains(property)) {
      if (!this.modifiedItems.contains(property)) {
        this.modifiedItems.add(property);
        this.changed();
      }
    }
  }

  /**
   * Loads from XML.
   *
   * @param reader           The reader.
   * @param localElementName Name of the local element.
   */
  @Override public void loadFromXml(EwsServiceXmlReader reader, String localElementName) throws Exception {
    this.loadFromXml(
        reader,
        XmlNamespace.Types,
        localElementName);
  }

  /**
   * Loads from XML.
   *
   * @param reader           The reader.
   * @param xmlNamespace     The XML namespace.
   * @param localElementName Name of the local element.
   */
  @Override public void loadFromXml(EwsServiceXmlReader reader, XmlNamespace xmlNamespace,
      String localElementName) throws Exception {
    reader.ensureCurrentNodeIsStartElement(xmlNamespace,
        localElementName);
    if (!reader.isEmptyElement()) {
      do {
        reader.read();

        if (reader.isStartElement()) {
          TComplexProperty complexProperty = this
              .createComplexProperty(reader.getLocalName());

          if (complexProperty != null) {
            complexProperty.loadFromXml(reader, reader
                .getLocalName());
            this.internalAdd(complexProperty, true);
          } else {
            reader.skipCurrentElement();
          }
        }
      } while (!reader.isEndElement(xmlNamespace, localElementName));
    } else {
      reader.read();
    }
  }

  /**
   * Loads from XML to update itself.
   *
   * @param reader         The reader.
   * @param xmlNamespace   The XML namespace.
   * @param xmlElementName Name of the XML element.
   */
  public void updateFromXml(
      EwsServiceXmlReader reader,
      XmlNamespace xmlNamespace,
      String xmlElementName) throws Exception {
    reader.ensureCurrentNodeIsStartElement(xmlNamespace, xmlElementName);

    if (!reader.isEmptyElement()) {
      int index = 0;
      do {
        reader.read();

        if (reader.isStartElement()) {
          TComplexProperty complexProperty = this.createComplexProperty(reader.getLocalName());
          TComplexProperty actualComplexProperty = this.getPropertyAtIndex(index++);

          if (complexProperty == null || !complexProperty.equals(actualComplexProperty)) {
            throw new ServiceLocalException("Property type incompatible when updating collection.");
          }

          actualComplexProperty.updateFromXml(reader, xmlNamespace, reader.getLocalName());
        }
      }
      while (!reader.isEndElement(xmlNamespace, xmlElementName));
    }
  }

  /**
   * Writes to XML.
   *
   * @param writer         The writer.
   * @param xmlNamespace   The XML namespace.
   * @param xmlElementName Name of the XML element.
   */
  @Override public void writeToXml(EwsServiceXmlWriter writer, XmlNamespace xmlNamespace,
      String xmlElementName) throws Exception {
    if (this.shouldWriteToXml()) {
      super.writeToXml(
          writer,
          xmlNamespace,
          xmlElementName);
    }
  }

  /**
   * Determine whether we should write collection to XML or not.
   *
   * @return True if collection contains at least one element.
   */
  public boolean shouldWriteToXml() {
    //Only write collection if it has at least one element.
    return this.getCount() > 0;
  }

  /**
   * Writes elements to XML.
   *
   * @param writer The writer.
   * @throws Exception the exception
   */
  @Override
  public void writeElementsToXml(EwsServiceXmlWriter writer)
      throws Exception {
    for (TComplexProperty complexProperty : this) {
      complexProperty.writeToXml(writer, this
          .getCollectionItemXmlElementName(complexProperty));
    }
  }

  /**
   * Clears the change log.
   */
  @Override public void clearChangeLog() {
    this.removedItems.clear();
    this.addedItems.clear();
    this.modifiedItems.clear();
  }

  /**
   * Removes from change log.
   *
   * @param complexProperty The complex property.
   */
  protected void removeFromChangeLog(TComplexProperty complexProperty) {
    this.removedItems.remove(complexProperty);
    this.modifiedItems.remove(complexProperty);
    this.addedItems.remove(complexProperty);
  }

  /**
   * Gets the item.
   *
   * @return The item.
   */
  public List<TComplexProperty> getItems() {
    return this.items;
  }

  /**
   * Gets the added item.
   *
   * @return The added item.
   */
  protected List<TComplexProperty> getAddedItems() {
    return this.addedItems;
  }

  /**
   * Gets the modified item.
   *
   * @return The modified item.
   */
  protected List<TComplexProperty> getModifiedItems() {
    return this.modifiedItems;
  }

  /**
   * Gets the removed item.
   *
   * @return The removed item.
   */
  protected List<TComplexProperty> getRemovedItems() {
    return this.removedItems;
  }

  /**
   * Add complex property.
   *
   * @param complexProperty The complex property.
   */
  protected void internalAdd(TComplexProperty complexProperty) {
    this.internalAdd(complexProperty, false);
  }

  /**
   * Add complex property.
   *
   * @param complexProperty The complex property.
   * @param loading         If true, collection is being loaded.
   */
  private void internalAdd(TComplexProperty complexProperty,
      boolean loading) {
    EwsUtilities.ewsAssert(complexProperty != null, "ComplexPropertyCollection.InternalAdd",
                           "complexProperty is null");

    if (!this.items.contains(complexProperty)) {
      this.items.add(complexProperty);
      if (!loading) {
        this.removedItems.remove(complexProperty);
        this.addedItems.add(complexProperty);
      }
      complexProperty.addOnChangeEvent(this);
      this.changed();
    }
  }

  /**
   * Complex property changed.
   *
   * @param complexProperty accepts ComplexProperty
   */
  @Override
  public void complexPropertyChanged(final TComplexProperty complexProperty) {
    this.itemChanged(complexProperty);
  }

  /**
   * Clear collection.
   */
  protected void internalClear() {
    while (this.getCount() > 0) {
      this.internalRemoveAt(0);
    }
  }

  /**
   * Remote entry at index.
   *
   * @param index The index.
   */
  protected void internalRemoveAt(int index) {
    EwsUtilities.ewsAssert(index >= 0 && index < this.getCount(),
                           "ComplexPropertyCollection.InternalRemoveAt", "index is out of range.");

    this.internalRemove(this.items.get(index));
  }

  /**
   * Remove specified complex property.
   *
   * @param complexProperty The complex property.
   * @return True if the complex property was successfully removed from the
   * collection, false otherwise.
   */
  protected boolean internalRemove(TComplexProperty complexProperty) {
    EwsUtilities.ewsAssert(complexProperty != null, "ComplexPropertyCollection.InternalRemove",
                           "complexProperty is null");

    if (this.items.remove(complexProperty)) {
      complexProperty.removeChangeEvent(this);
      if (!this.addedItems.contains(complexProperty)) {
        this.removedItems.add(complexProperty);
      } else {
        this.addedItems.remove(complexProperty);
      }
      this.modifiedItems.remove(complexProperty);
      this.changed();
      return true;
    } else {
      return false;
    }
  }

  /**
   * Determines whether a specific property is in the collection.
   *
   * @param complexProperty The property to locate in the collection.
   * @return True if the property was found in the collection, false
   * otherwise.
   */
  public boolean contains(TComplexProperty complexProperty) {
    return this.items.contains(complexProperty);
  }

  /**
   * Searches for a specific property and return its zero-based index within
   * the collection.
   *
   * @param complexProperty The property to locate in the collection.
   * @return The zero-based index of the property within the collection.
   */
  public int indexOf(TComplexProperty complexProperty) {
    return this.items.indexOf(complexProperty);
  }

  /**
   * Gets the total number of property in the collection.
   *
   * @return the count
   */
  public int getCount() {
    return this.items.size();
  }

  /**
   * Gets the property at the specified index.
   *
   * @param index the index
   * @return index The property at the specified index.
   * @throws IllegalArgumentException thrown if if index is out of range.
   */
  public TComplexProperty getPropertyAtIndex(int index)
      throws IllegalArgumentException {
    if (index < 0 || index >= this.getCount()) {
      throw new IllegalArgumentException(
          String.format("index %d is out of range [0..%d[.", index, this.getCount())
      );
    }
    return this.items.get(index);
  }

  /**
   * Gets an enumerator that iterates through the elements of the collection.
   *
   * @return An Iterator for the collection.
   */
  @Override
  public Iterator<TComplexProperty> iterator() {
    return this.items.iterator();
  }

  /**
   * Write set update to xml.
   *
   * @param writer             accepts EwsServiceXmlWriter
   * @param ewsObject          accepts ServiceObject
   * @param propertyDefinition accepts PropertyDefinition
   * @return true
   * @throws Exception the exception
   */
  @Override
  public boolean writeSetUpdateToXml(EwsServiceXmlWriter writer,
      ServiceObject ewsObject, PropertyDefinition propertyDefinition)
      throws Exception {
    // If the collection is empty, delete the property.
    if (this.getCount() == 0) {
      writer.writeStartElement(XmlNamespace.Types, ewsObject
          .getDeleteFieldXmlElementName());
      propertyDefinition.writeToXml(writer);
      writer.writeEndElement();
      return true;
    }
    // Otherwise, use the default XML serializer.
    else {
      return false;
    }
  }

  /**
   * Writes the deletion update to XML.
   *
   * @param writer    The writer.
   * @param ewsObject The ews object.
   * @return True if property generated serialization.
   * @throws Exception the exception
   */
  @Override
  public boolean writeDeleteUpdateToXml(EwsServiceXmlWriter writer,
      ServiceObject ewsObject) throws Exception {
    // Use the default XML serializer.
    return false;
  }
}
