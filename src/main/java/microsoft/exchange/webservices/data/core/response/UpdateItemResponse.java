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
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.service.ServiceObject;
import microsoft.exchange.webservices.data.core.service.item.Item;
import microsoft.exchange.webservices.data.core.enumeration.service.ServiceResult;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;

/**
 * The Class UpdateItemResponse.
 */
public final class UpdateItemResponse extends ServiceResponse implements
                                                              IGetObjectInstanceDelegate<ServiceObject> {

  /**
   * Represents the response to an individual item update operation.
   */
  private Item item;

  /**
   * The returned item.
   */
  private Item returnedItem;

  /**
   * The conflict count.
   */
  private int conflictCount;

  /**
   * Initializes a new instance of the class.
   *
   * @param item the item
   */
  public UpdateItemResponse(Item item) {
    super();
    EwsUtilities.ewsAssert(item != null, "UpdateItemResponse.ctor", "item is null");
    this.item = item;
  }

  /**
   * Reads response elements from XML.
   *
   * @param reader the reader
   * @throws Exception the exception
   */
  @Override
  protected void readElementsFromXml(EwsServiceXmlReader reader) throws Exception {
    super.readElementsFromXml(reader);

    reader.readServiceObjectsCollectionFromXml(XmlElementNames.Items, this,
        false, null, false);

    if (!reader.getService().getExchange2007CompatibilityMode()) {
      reader.readStartElement(XmlNamespace.Messages,
          XmlElementNames.ConflictResults);
      this.conflictCount = reader.readElementValue(Integer.class,
          XmlNamespace.Types, XmlElementNames.Count);
      reader.readEndElement(XmlNamespace.Messages,
          XmlElementNames.ConflictResults);
    }

    // If UpdateItem returned an item that has the same Id as the item that
    // is being updated, this is a "normal" UpdateItem operation, and we
    // need
    // to update the ChangeKey of the item being updated with the one that
    // was
    // returned. Also set returnedItem to indicate that no new item was
    // returned.
    //
    // Otherwise, this in a "special" UpdateItem operation, such as a
    // recurring
    // task marked as complete (the returned item in that case is the
    // one-off
    // task that represents the completed instance).
    //
    // Note that there can be no returned item at all, as in an UpdateItem
    // call
    // with MessageDisposition set to SendOnly or SendAndSaveCopy.
    if (this.returnedItem != null) {
      if (this.item.getId().getUniqueId().equals(
          this.returnedItem.getId().getUniqueId())) {
        this.item.getId().setChangeKey(
            this.returnedItem.getId().getChangeKey());
        this.returnedItem = null;
      }
    }
  }

  /*
   * (non-Javadoc)
   *
   * @seemicrosoft.exchange.webservices.GetObjectInstanceDelegateInterface#
   * getObjectInstanceDelegate(microsoft.exchange.webservices.ExchangeService,
   * java.lang.String)
   */
  public ServiceObject getObjectInstanceDelegate(ExchangeService service,
      String xmlElementName) throws Exception {
    return this.getObjectInstance(service, xmlElementName);
  }

  /**
   * Clears the change log of the created folder if the creation succeeded.
   */
  @Override
  protected void loaded() {
    if (this.getResult() == ServiceResult.Success) {
      this.item.clearChangeLog();
    }
  }

  /**
   * Gets Item instance.
   *
   * @param service        the service
   * @param xmlElementName the xml element name
   * @return Item
   * @throws Exception the exception
   */
  private Item getObjectInstance(ExchangeService service,
      String xmlElementName) throws Exception {
    this.returnedItem = EwsUtilities.createEwsObjectFromXmlElementName(
        Item.class, service, xmlElementName);
    return this.returnedItem;
  }

  /**
   * Gets the item that was returned by the update operation. ReturnedItem
   * is set only when a recurring Task is marked as complete or when its
   * recurrence pattern changes.
   *
   * @return the returned item
   */
  public Item getReturnedItem() {
    return this.returnedItem;
  }

  /**
   * Gets the number of property conflicts that were resolved during the
   * update operation.
   *
   * @return the conflict count
   */
  public int getConflictCount() {
    return this.conflictCount;
  }

}
