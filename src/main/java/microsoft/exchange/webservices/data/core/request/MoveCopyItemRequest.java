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
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.misc.ItemIdWrapperList;

/**
 * Represents an abstract Move/Copy Item request.
 *
 * @param <TResponse> The type of the response.
 */
public abstract class MoveCopyItemRequest<TResponse extends ServiceResponse>
    extends MoveCopyRequest<Item, TResponse> {
  private ItemIdWrapperList itemIds = new ItemIdWrapperList();
  private Boolean newItemIds;

  /**
   * Validates request.
   *
   * @throws Exception the exception
   */
  @Override
  public void validate() throws Exception {
    super.validate();
    EwsUtilities.validateParam(this.getItemIds(), "ItemIds");
  }

  /**
   * Initializes a new instance of the class.
   *
   * @param service           the service
   * @param errorHandlingMode the error handling mode
   * @throws Exception on error
   */
  protected MoveCopyItemRequest(ExchangeService service,
      ServiceErrorHandling errorHandlingMode)
      throws Exception {
    super(service, errorHandlingMode);
  }

  /**
   * Writes the ids as XML.
   *
   * @param writer the writer
   * @throws Exception the exception
   */
  @Override
  protected void writeIdsToXml(EwsServiceXmlWriter writer) throws Exception {
    this.getItemIds().writeToXml(writer, XmlNamespace.Messages,
        XmlElementNames.ItemIds);
    if (this.getReturnNewItemIds() != null) {
      writer.writeElementValue(
          XmlNamespace.Messages,
          XmlElementNames.ReturnNewItemIds,
          this.getReturnNewItemIds());
    }
  }

  /**
   * Gets the expected response message count.
   *
   * @return Number of expected response messages.
   */
  @Override
  protected int getExpectedResponseMessageCount() {
    return this.getItemIds().getCount();
  }

  /**
   * Gets the item ids.
   *
   * @return the item ids
   */
  public ItemIdWrapperList getItemIds() {
    return this.itemIds;
  }

  protected Boolean getReturnNewItemIds() {
    return this.newItemIds;
  }

  public void setReturnNewItemIds(Boolean value) {
    this.newItemIds = value;
  }
}
