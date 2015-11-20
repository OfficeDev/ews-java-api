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

import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.service.item.Item;
import microsoft.exchange.webservices.data.core.enumeration.service.ServiceResult;

/**
 * Represents the response to an individual item creation operation.
 */
public final class CreateItemResponse extends CreateItemResponseBase {

  /**
   * The item.
   */
  private Item item;

  /**
   * Gets Item instance.
   *
   * @param service        The service.
   * @param xmlElementName Name of the XML element.
   * @return the object instance
   */
  @Override
  protected Item getObjectInstance(ExchangeService service,
      String xmlElementName) {
    return this.item;
  }

  /**
   * Initializes a new instance.
   *
   * @param item The item.
   */
  public CreateItemResponse(Item item) {
    super();
    this.item = item;
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
}
