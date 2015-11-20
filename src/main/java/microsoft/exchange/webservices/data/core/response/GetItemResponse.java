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
import microsoft.exchange.webservices.data.core.PropertySet;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.service.ServiceObject;
import microsoft.exchange.webservices.data.core.service.item.Item;

import java.util.List;

/**
 * Represents a response to an individual item retrieval operation.
 */
public final class GetItemResponse extends ServiceResponse implements
                                                           IGetObjectInstanceDelegate<ServiceObject> {

  /**
   * The item.
   */
  private Item item;

  /**
   * The property set.
   */
  private PropertySet propertySet;

  /**
   * Initializes a new instance of the class.
   *
   * @param item        the item
   * @param propertySet the property set
   */
  public GetItemResponse(Item item, PropertySet propertySet) {
    super();
    this.item = item;
    this.propertySet = propertySet;
    EwsUtilities.ewsAssert(this.propertySet != null, "GetItemResponse.ctor", "PropertySet should not be null");
  }

  /**
   * Reads response elements from XML.
   *
   * @param reader the reader
   * @throws InstantiationException the instantiation exception
   * @throws IllegalAccessException the illegal access exception
   * @throws Exception              the exception
   */
  protected void readElementsFromXml(EwsServiceXmlReader reader)
      throws InstantiationException, IllegalAccessException, Exception {
    super.readElementsFromXml(reader);

    List<Item> items = reader.readServiceObjectsCollectionFromXml(
        XmlElementNames.Items, this,
        true, /* clearPropertyBag */
        this.propertySet, /* requestedPropertySet */
        false); /* summaryPropertiesOnly */

    this.item = items.get(0);
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
    if (this.getItem() != null) {
      return this.getItem();
    } else {
      return EwsUtilities.createEwsObjectFromXmlElementName(Item.class,
          service, xmlElementName);

    }
  }

  /**
   * Gets the item that was retrieved.
   *
   * @return the item
   */
  public Item getItem() {
    return this.item;
  }

  /**
   * Gets the object instance delegate.
   *
   * @param service        accepts ExchangeService
   * @param xmlElementName accepts String
   * @return Name
   * @throws Exception throws exception
   */
  @Override
  public ServiceObject getObjectInstanceDelegate(ExchangeService service,
      String xmlElementName) throws Exception {
    return getObjectInstance(service, xmlElementName);
  }
}
