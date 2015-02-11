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

package microsoft.exchange.webservices.data;

import java.util.List;

/**
 * Represents the base response class for item creation operations.
 */
@EditorBrowsable(state = EditorBrowsableState.Never)
abstract class CreateItemResponseBase extends ServiceResponse implements
                                                              IGetObjectInstanceDelegate<ServiceObject> {

  /**
   * The items.
   */
  private List<Item> items;

  /**
   * Initializes a new instance.
   */
  protected CreateItemResponseBase() {
    super();
  }

  /**
   * Gets Item instance.
   *
   * @param service        The service.
   * @param xmlElementName Name of the XML element.
   * @return Item.
   * @throws InstantiationException the instantiation exception
   * @throws IllegalAccessException the illegal access exception
   * @throws Exception              the exception
   */
  protected abstract Item getObjectInstance(ExchangeService service,
                                            String xmlElementName) throws InstantiationException,
                                                                          IllegalAccessException,
                                                                          Exception;

  /**
   * Gets the object instance delegate.
   *
   * @param service        accepts ExchangeService
   * @param xmlElementName accepts String
   * @return object
   * @throws Exception throws Exception
   */
  public ServiceObject getObjectInstanceDelegate(ExchangeService service,
                                                 String xmlElementName) throws Exception {
    return this.getObjectInstance(service, xmlElementName);
  }

  /**
   * Reads response elements from XML.
   *
   * @param reader the reader
   * @throws Exception the exception
   */
  @Override
  protected void readElementsFromXml(EwsServiceXmlReader reader)
      throws Exception {
    super.readElementsFromXml(reader);
    this.items = reader.readServiceObjectsCollectionFromXml(
        XmlElementNames.Items, this, false, /* clearPropertyBag */
        null, /* requestedPropertySet */
        false); /* summaryPropertiesOnly */
  }

  /**
   * Gets the items.
   *
   * @return List of items.
   */
  public List<Item> getItems() {
    return items;
  }

}
