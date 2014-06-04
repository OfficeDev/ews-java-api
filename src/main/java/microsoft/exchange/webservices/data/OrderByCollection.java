/**************************************************************************
 * copyright file="OrderByCollection.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the OrderByCollection.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.stream.XMLStreamException;

/**
 * Represents an ordered collection of property definitions qualified with a
 * sort direction.
 * 
 */
public final class OrderByCollection implements
		Iterable<Map<PropertyDefinitionBase, SortDirection>> {

	/** The prop def sort order pair list. */
	private List<Map<PropertyDefinitionBase, 
			SortDirection>> propDefSortOrderPairList;

	/***
	 * Initializes a new instance of the OrderByCollection class.
	 */
	protected OrderByCollection() {
		this.propDefSortOrderPairList =	new 
				ArrayList<Map<PropertyDefinitionBase, SortDirection>>();
	}

	/**
	 * * Adds the specified property definition / sort direction pair to the
	 * collection.
	 * 
	 * @param propertyDefinition
	 *            the property definition
	 * @param sortDirection
	 *            the sort direction
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public void add(PropertyDefinitionBase propertyDefinition,
			SortDirection sortDirection) throws ServiceLocalException {
		if (this.contains(propertyDefinition)) {
			throw new ServiceLocalException(String.format(
					Strings.PropertyAlreadyExistsInOrderByCollection,
					propertyDefinition.getPrintableName()));
		}
		Map propertyDefinitionSortDirectionPair = new 
				HashMap<PropertyDefinitionBase, SortDirection>();
		propertyDefinitionSortDirectionPair.put(propertyDefinition,
				sortDirection);
		this.propDefSortOrderPairList.add(propertyDefinitionSortDirectionPair);
	}

	/***
	 * Removes all elements from the collection.
	 */
	public void clear() {
		this.propDefSortOrderPairList.clear();
	}

	/**
	 * * Determines whether the collection contains the specified property
	 * definition.
	 * 
	 * @param propertyDefinition
	 *            the property definition
	 * @return True if the collection contains the specified property
	 *         definition; otherwise, false.
	 */
	protected boolean contains(PropertyDefinitionBase propertyDefinition) {
		for (Map propDefSortOrderPair : propDefSortOrderPairList) {
			return propDefSortOrderPair.containsKey(propertyDefinition);
		}
		return false;
	}

	/**
	 * * Gets the number of elements contained in the collection.
	 * 
	 * @return the int
	 */
	public int count() {
		return this.propDefSortOrderPairList.size();
	}

	/**
	 * * Removes the specified property definition from the collection.
	 * 
	 * @param propertyDefinition
	 *            the property definition
	 * @return True if the property definition is successfully removed;
	 *         otherwise, false
	 */
	public boolean remove(PropertyDefinitionBase propertyDefinition) {
		List<Map<PropertyDefinitionBase, SortDirection>> removeList = new 
				ArrayList<Map<PropertyDefinitionBase, SortDirection>>();
		for (Map propDefSortOrderPair : propDefSortOrderPairList) {
			if (propDefSortOrderPair.containsKey(propertyDefinition)) {
				removeList.add(propDefSortOrderPair);
			}
		}
		this.propDefSortOrderPairList.removeAll(removeList);
		return removeList.size() > 0;
	}

	/**
	 * * Removes the element at the specified index from the collection.
	 * 
	 * @param index
	 *            the index
	 */
	public void removeAt(int index) {
		this.propDefSortOrderPairList.remove(index);
	}

	/**
	 * * Tries to get the value for a property definition in the collection.
	 * 
	 * @param propertyDefinition
	 *            the property definition
	 * @param sortDirection
	 *            the sort direction
	 * @return True if collection contains property definition, otherwise false.
	 */
	public boolean tryGetValue(PropertyDefinitionBase propertyDefinition,
			OutParam sortDirection) {
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
	 * * Writes to XML.
	 * 
	 * @param writer
	 *            the writer
	 * @param xmlElementName
	 *            the xml element name
	 * @throws javax.xml.stream.XMLStreamException
	 *             the xML stream exception
	 * @throws ServiceXmlSerializationException
	 *             the service xml serialization exception
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
	 * * Gets the element at the specified index from the collection.
	 * 
	 * @param index
	 *            the index
	 * @return the property definition sort direction pair
	 */
	public Map<PropertyDefinitionBase, 
			SortDirection> getPropertyDefinitionSortDirectionPair(
			int index) {
		return this.propDefSortOrderPairList.get(index);
	}

	/***
	 * Returns an enumerator that iterates through the collection.
	 * 
	 * @return A Iterator that can be used to iterate through the collection.
	 */
	public Iterator<Map<PropertyDefinitionBase, 
			SortDirection>>	getEnumerator() {
		return (this.propDefSortOrderPairList.iterator());
	}

}
