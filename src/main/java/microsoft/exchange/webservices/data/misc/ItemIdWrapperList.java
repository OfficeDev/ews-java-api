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

package microsoft.exchange.webservices.data.misc;

import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.service.item.Item;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceLocalException;
import microsoft.exchange.webservices.data.property.complex.ItemId;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a list a abstracted item Ids.
 */
public class ItemIdWrapperList implements Iterable<AbstractItemIdWrapper> {

  /**
   * The item ids.
   */
  private List<AbstractItemIdWrapper> itemIds =
      new ArrayList<AbstractItemIdWrapper>();

  /**
   * Initializes a new instance of the class.
   */
  public ItemIdWrapperList() {
  }

  /**
   * Adds the specified item.
   *
   * @param item the item
   * @throws ServiceLocalException the service local exception
   */
  protected void add(Item item) throws ServiceLocalException {
    this.itemIds.add(new ItemWrapper(item));

  }

  /**
   * Adds the specified item.
   *
   * @param items the item
   * @throws ServiceLocalException the service local exception
   */
  public void addRangeItem(Iterable<Item> items)
      throws ServiceLocalException {
    for (Item item : items) {
      this.add(item);
    }
  }

  /**
   * Adds the range.
   *
   * @param itemIds the item ids
   */
  public void addRange(Iterable<ItemId> itemIds) {
    for (ItemId itemId : itemIds) {
      this.add(itemId);
    }
  }

  /**
   * Adds the specified item id.
   *
   * @param itemId the item id
   */
  protected void add(ItemId itemId) {
    this.itemIds.add(new ItemIdWrapper(itemId));

  }

  /**
   * Writes to XML.
   *
   * @param writer         the writer
   * @param ewsNamesapce   the ews namesapce
   * @param xmlElementName the xml element name
   * @throws Exception the exception
   */
  public void writeToXml(EwsServiceXmlWriter writer, XmlNamespace ewsNamesapce, String xmlElementName) throws Exception {
    if (this.getCount() > 0) {
      writer.writeStartElement(ewsNamesapce, xmlElementName);

      for (AbstractItemIdWrapper itemIdWrapper : this.itemIds) {
        itemIdWrapper.writeToXml(writer);
      }

      writer.writeEndElement();
    }
  }

  /**
   * Gets the count.
   *
   * @return the count
   */
  public int getCount() {
    return this.itemIds.size();
  }

  /**
   * Gets the item at the specified index.
   *
   * @param i the i
   * @return the item id wrapper list
   */
  public Item getItemIdWrapperList(int i) {
    return this.itemIds.get(i).getItem();
  }

  /**
   * Gets an Iterator that iterates through the elements of the collection.
   *
   * @return An IEnumerator for the collection
   */
  @Override
  public Iterator<AbstractItemIdWrapper> iterator() {

    return itemIds.iterator();
  }
}
