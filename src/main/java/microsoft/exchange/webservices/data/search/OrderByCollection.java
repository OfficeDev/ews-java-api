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

package microsoft.exchange.webservices.data.search;

import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.XmlAttributeNames;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.search.SortDirection;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceLocalException;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlSerializationException;
import microsoft.exchange.webservices.data.misc.OutParam;
import microsoft.exchange.webservices.data.property.definition.PropertyDefinitionBase;

import javax.xml.stream.XMLStreamException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Represents an ordered collection of property definitions qualified with a
 * sort direction.
 */
public final class OrderByCollection implements
    Iterable<Map<PropertyDefinitionBase, SortDirection>> {

  /**
   * The prop def sort order pair list.
   */
  private List<Map<PropertyDefinitionBase,
      SortDirection>> propDefSortOrderPairList;

  /**
   * Initializes a new instance of the OrderByCollection class.
   */
  protected OrderByCollection() {
    this.propDefSortOrderPairList = new
        ArrayList<Map<PropertyDefinitionBase, SortDirection>>();
  }

  /**
   * Adds the specified property definition / sort direction pair to the
   * collection.
   *
   * @param propertyDefinition the property definition
   * @param sortDirection      the sort direction
   * @throws ServiceLocalException the service local exception
   */
  public void add(PropertyDefinitionBase propertyDefinition,
      SortDirection sortDirection) throws ServiceLocalException {
    if (this.contains(propertyDefinition)) {
      throw new ServiceLocalException(String.format("Property %s already exists in OrderByCollection.",
          propertyDefinition.getPrintableName()));
    }
    Map<PropertyDefinitionBase, SortDirection> propertyDefinitionSortDirectionPair = new
        HashMap<PropertyDefinitionBase, SortDirection>();
    propertyDefinitionSortDirectionPair.put(propertyDefinition,
        sortDirection);
    this.propDefSortOrderPairList.add(propertyDefinitionSortDirectionPair);
  }

  /**
   * Removes all elements from the collection.
   */
  public void clear() {
    this.propDefSortOrderPairList.clear();
  }

  /**
   * Determines whether the collection contains the specified property
   * definition.
   *
   * @param propertyDefinition the property definition
   * @return True if the collection contains the specified property
   * definition; otherwise, false.
   */
  protected boolean contains(PropertyDefinitionBase propertyDefinition) {
    for (Map<PropertyDefinitionBase, SortDirection> propDefSortOrderPair : propDefSortOrderPairList) {
      return propDefSortOrderPair.containsKey(propertyDefinition);
    }
    return false;
  }

  /**
   * Gets the number of elements contained in the collection.
   *
   * @return the int
   */
  public int count() {
    return this.propDefSortOrderPairList.size();
  }

  /**
   * Removes the specified property definition from the collection.
   *
   * @param propertyDefinition the property definition
   * @return True if the property definition is successfully removed;
   * otherwise, false
   */
  public boolean remove(PropertyDefinitionBase propertyDefinition) {
    List<Map<PropertyDefinitionBase, SortDirection>> removeList = new
        ArrayList<Map<PropertyDefinitionBase, SortDirection>>();
    for (Map<PropertyDefinitionBase, SortDirection> propDefSortOrderPair : propDefSortOrderPairList) {
      if (propDefSortOrderPair.containsKey(propertyDefinition)) {
        removeList.add(propDefSortOrderPair);
      }
    }
    this.propDefSortOrderPairList.removeAll(removeList);
    return removeList.size() > 0;
  }

  /**
   * Removes the element at the specified index from the collection.
   *
   * @param index the index
   */
  public void removeAt(int index) {
    this.propDefSortOrderPairList.remove(index);
  }

  /**
   * Tries to get the value for a property definition in the collection.
   *
   * @param propertyDefinition the property definition
   * @param sortDirection      the sort direction
   * @return True if collection contains property definition, otherwise false.
   */
  public boolean tryGetValue(PropertyDefinitionBase propertyDefinition,
      OutParam<SortDirection> sortDirection) {
    for (Map<PropertyDefinitionBase, SortDirection> pair : this.propDefSortOrderPairList) {

      if (pair.containsKey(propertyDefinition)) {
        sortDirection.setParam(pair.get(propertyDefinition));
        return true;
      }
    }
    sortDirection.setParam(SortDirection.Ascending); // out parameter has to
    // be set to some
    // value.
    return false;
  }

  /**
   * Writes to XML.
   *
   * @param writer         the writer
   * @param xmlElementName the xml element name
   * @throws XMLStreamException the XML stream exception
   * @throws ServiceXmlSerializationException the service xml serialization exception
   */
  protected void writeToXml(EwsServiceXmlWriter writer, String xmlElementName)
      throws XMLStreamException, ServiceXmlSerializationException {
    if (this.count() > 0) {
      writer.writeStartElement(XmlNamespace.Messages, xmlElementName);

      for (Map<PropertyDefinitionBase, SortDirection> keyValuePair : this.propDefSortOrderPairList) {
        writer.writeStartElement(XmlNamespace.Types,
            XmlElementNames.FieldOrder);

        writer.writeAttributeValue(XmlAttributeNames.Order,
            keyValuePair.values().iterator().next());
        keyValuePair.keySet().iterator().next().writeToXml(writer);

        writer.writeEndElement(); // FieldOrder
      }

      writer.writeEndElement();
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Iterable#iterator()
   */
  @Override
  public Iterator<Map<PropertyDefinitionBase, SortDirection>> iterator() {
    return this.propDefSortOrderPairList.iterator();
  }

  /**
   * Gets the element at the specified index from the collection.
   *
   * @param index the index
   * @return the property definition sort direction pair
   */
  public Map<PropertyDefinitionBase,
      SortDirection> getPropertyDefinitionSortDirectionPair(
      int index) {
    return this.propDefSortOrderPairList.get(index);
  }

  /**
   * Returns an enumerator that iterates through the collection.
   *
   * @return A Iterator that can be used to iterate through the collection.
   */
  public Iterator<Map<PropertyDefinitionBase,
      SortDirection>> getEnumerator() {
    return (this.propDefSortOrderPairList.iterator());
  }

}
