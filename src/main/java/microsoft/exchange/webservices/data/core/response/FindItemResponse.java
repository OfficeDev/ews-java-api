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

package microsoft.exchange.webservices.data.core.response;

import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.PropertySet;
import microsoft.exchange.webservices.data.core.XmlAttributeNames;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.service.item.Item;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlDeserializationException;
import microsoft.exchange.webservices.data.search.FindItemsResults;
import microsoft.exchange.webservices.data.search.GroupedFindItemsResults;
import microsoft.exchange.webservices.data.search.ItemGroup;
import microsoft.exchange.webservices.data.security.XmlNodeType;

import javax.xml.stream.XMLStreamException;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the response to a item search operation.
 *
 * @param <TItem> The type of item that the opeartion returned.
 */
public final class FindItemResponse
    <TItem extends Item> extends ServiceResponse {

  /**
   * The results.
   */
  private FindItemsResults<TItem> results;

  /**
   * The is grouped.
   */
  private boolean isGrouped;

  /**
   * The grouped find results.
   */
  private GroupedFindItemsResults<TItem> groupedFindResults;

  /**
   * The property set.
   */
  private PropertySet propertySet;

  /**
   * Initializes a new instance of the FindItemResponse class.
   *
   * @param isGrouped   if set to true if grouped.
   * @param propertySet The property Set
   */
  public FindItemResponse(boolean isGrouped, PropertySet propertySet) {
    super();
    this.isGrouped = isGrouped;
    this.propertySet = propertySet;

    EwsUtilities
        .ewsAssert(this.propertySet != null, "FindItemResponse.ctor", "PropertySet should not be null");
  }

  /**
   * Reads response elements from XML.
   *
   * @param reader ,The reader
   * @throws Exception the exception
   */
  @Override
  protected void readElementsFromXml(EwsServiceXmlReader reader)
      throws Exception {
    reader.readStartElement(XmlNamespace.Messages,
        XmlElementNames.RootFolder);

    int totalItemsInView = reader.readAttributeValue(Integer.class,
        XmlAttributeNames.TotalItemsInView);
    boolean moreItemsAvailable = !reader.readAttributeValue(Boolean.class,
        XmlAttributeNames.IncludesLastItemInRange);

    // Ignore IndexedPagingOffset attribute if moreItemsAvailable is false.
    Integer nextPageOffset = moreItemsAvailable ? reader
        .readNullableAttributeValue(Integer.class,
            XmlAttributeNames.IndexedPagingOffset) : null;

    if (!this.isGrouped) {
      this.results = new FindItemsResults<TItem>();
      this.results.setTotalCount(totalItemsInView);
      this.results.setNextPageOffset(nextPageOffset);
      this.results.setMoreAvailable(moreItemsAvailable);
      internalReadItemsFromXml(reader, this.propertySet, this.results
          .getItems());
    } else {
      this.groupedFindResults = new GroupedFindItemsResults<TItem>();
      this.groupedFindResults.setTotalCount(totalItemsInView);
      this.groupedFindResults.setNextPageOffset(nextPageOffset);
      this.groupedFindResults.setMoreAvailable(moreItemsAvailable);

      reader.readStartElement(XmlNamespace.Types, XmlElementNames.Groups);

      if (!reader.isEmptyElement()) {
        do {
          reader.read();

          if (reader.isStartElement(XmlNamespace.Types,
              XmlElementNames.GroupedItems)) {
            String groupIndex = reader.readElementValue(
                XmlNamespace.Types, XmlElementNames.GroupIndex);

            ArrayList<TItem> itemList = new ArrayList<TItem>();
            internalReadItemsFromXml(reader, this.propertySet,
                itemList);

            reader.readEndElement(XmlNamespace.Types,
                XmlElementNames.GroupedItems);

            this.groupedFindResults.getItemGroups().add(
                new ItemGroup<TItem>(groupIndex, itemList));
          }
        } while (!reader.isEndElement(XmlNamespace.Types,
            XmlElementNames.Groups));
      } else {
        reader.read();
      }
    }

    reader
        .readEndElement(XmlNamespace.Messages,
            XmlElementNames.RootFolder);
  }

  /**
   * Read item from XML.
   *
   * @param reader the reader
   * @param propertySet the property set
   * @param destinationList the list in which to add the read item
   * @throws XMLStreamException the XML stream exception
   * @throws ServiceXmlDeserializationException the service xml deserialization exception
   * @throws Exception the exception
   */
  private void internalReadItemsFromXml(EwsServiceXmlReader reader,
      PropertySet propertySet, List<TItem> destinationList)
      throws XMLStreamException, ServiceXmlDeserializationException,
      Exception {
    EwsUtilities.ewsAssert(destinationList != null, "FindItemResponse.InternalReadItemsFromXml",
                           "destinationList is null.");

    reader.readStartElement(XmlNamespace.Types, XmlElementNames.Items);
    if (!reader.isEmptyElement()) {
      do {
        reader.read();

        if (reader.getNodeType().nodeType == XmlNodeType.START_ELEMENT) {
          Item item = EwsUtilities.createEwsObjectFromXmlElementName(
              Item.class, reader.getService(), reader
                  .getLocalName());

          if (item == null) {
            reader.skipCurrentElement();
          } else {
            item.loadFromXml(reader, true, /* clearPropertyBag */
                propertySet, true /* summaryPropertiesOnly */);

            destinationList.add((TItem) item);
          }
        }
      } while (!reader.isEndElement(XmlNamespace.Types,
          XmlElementNames.Items));
    } else {
      reader.read();
    }

  }

  /**
   * Gets a grouped list of item matching the specified search criteria that
   * were found in Exchange. ItemGroups is null if the search operation did
   * not specify grouping options.
   *
   * @return the grouped find results
   */
  public GroupedFindItemsResults<TItem> getGroupedFindResults() {
    return groupedFindResults;
  }

  /**
   * Gets the results of the search operation.
   *
   * @return the results
   */
  public FindItemsResults<TItem> getResults() {
    return results;
  }

}
