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

import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.enumeration.service.error.ServiceErrorHandling;
import microsoft.exchange.webservices.data.core.response.CreateItemResponse;
import microsoft.exchange.webservices.data.core.response.ServiceResponse;
import microsoft.exchange.webservices.data.core.service.item.Item;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceLocalException;

/**
 * Represents a CreateItem request.
 */
public final class CreateItemRequest extends
    CreateItemRequestBase<Item, ServiceResponse> {

  /**
   * Initializes a new instance.
   *
   * @param service           The service.
   * @param errorHandlingMode Indicates how errors should be handled.
   * @throws Exception
   */
  public CreateItemRequest(ExchangeService service, ServiceErrorHandling errorHandlingMode)
      throws Exception {
    super(service, errorHandlingMode);
  }

  /**
   * Creates the service response.
   *
   * @param service       the service
   * @param responseIndex the response index
   * @return the service response
   */
  @Override
  protected ServiceResponse createServiceResponse(ExchangeService service,
      int responseIndex) {
    return new CreateItemResponse((Item) EwsUtilities
        .getEnumeratedObjectAt(this.getItems(), responseIndex));
  }

  /**
   * Validate request..
   *
   * @throws ServiceLocalException the service local exception
   * @throws Exception             the exception
   */
  @Override
  protected void validate() throws ServiceLocalException, Exception {
    super.validate();
    //	Iterable<Item> item = this.getItems();
    // Validate each item.
    for (Item item : this.getItems()) {
      item.validate();
    }
  }

  /**
   * Gets the request version. Returns earliest Exchange version in which
   * this request is supported.
   *
   * @return the minimum required server version
   */
  @Override
  protected ExchangeVersion getMinimumRequiredServerVersion() {
    return ExchangeVersion.Exchange2007_SP1;
  }

}
