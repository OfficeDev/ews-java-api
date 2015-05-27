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

package microsoft.exchange.webservices.data.core.request;

import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.service.error.ServiceErrorHandling;
import microsoft.exchange.webservices.data.core.response.ServiceResponse;
import microsoft.exchange.webservices.data.core.service.item.Item;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.service.ServiceObjectType;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceLocalException;
import microsoft.exchange.webservices.data.misc.ItemIdWrapperList;

/**
 * Represents an abstract GetItem request.
 *
 * @param <TResponse> the generic type
 */
abstract class GetItemRequestBase<TResponse extends ServiceResponse> extends GetRequest<Item, TResponse> {

  /**
   * The item ids.
   */
  private ItemIdWrapperList itemIds = new ItemIdWrapperList();

  /**
   * Initializes a new instance of the class.
   *
   * @param service           the service
   * @param errorHandlingMode the error handling mode
   * @throws Exception
   */
  protected GetItemRequestBase(ExchangeService service,
      ServiceErrorHandling errorHandlingMode)
      throws Exception {
    super(service, errorHandlingMode);
  }

  /**
   * Validate request.
   *
   * @throws ServiceLocalException the service local exception
   * @throws Exception             the exception
   */
  protected void validate() throws ServiceLocalException, Exception {
    super.validate();
    EwsUtilities.validateParamCollection(this.getItemIds().iterator(), "ItemIds");
  }

  /**
   * Gets the expected response message count.
   *
   * @return Number of expected response messages
   */
  protected int getExpectedResponseMessageCount() {
    return this.itemIds.getCount();
  }

  /**
   * Gets the type of the service object this request applies to.
   *
   * @return The type of service object the request applies to
   */
  protected ServiceObjectType getServiceObjectType() {
    return ServiceObjectType.Item;
  }

  /**
   * Writes XML elements.
   *
   * @param writer the writer
   * @throws Exception the exception
   */
  protected void writeElementsToXml(EwsServiceXmlWriter writer)
      throws Exception {
    super.writeElementsToXml(writer);

    this.itemIds.writeToXml(writer, XmlNamespace.Messages,
        XmlElementNames.ItemIds);
  }

  /**
   * Gets the name of the XML element.
   *
   * @return XML element name
   */
  public String getXmlElementName() {
    return XmlElementNames.GetItem;
  }

  /**
   * Gets the name of the XML element.
   *
   * @return XML element name
   */
  protected String getResponseXmlElementName() {
    return XmlElementNames.GetItemResponse;
  }

  /**
   * Gets the name of the XML element.
   *
   * @return XML element name
   */
  protected String getResponseMessageXmlElementName() {
    return XmlElementNames.GetItemResponseMessage;
  }

  /**
   * Gets the request version.
   *
   * @return Earliest Exchange version in which this request is supported
   */
  protected ExchangeVersion getMinimumRequiredServerVersion() {
    return ExchangeVersion.Exchange2007_SP1;
  }

  /**
   * Gets the item ids.
   *
   * @return the item ids
   */
  public ItemIdWrapperList getItemIds() {
    return this.itemIds;
  }
}
