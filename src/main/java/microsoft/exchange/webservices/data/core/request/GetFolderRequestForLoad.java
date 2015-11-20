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

import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.enumeration.service.error.ServiceErrorHandling;
import microsoft.exchange.webservices.data.core.response.GetFolderResponse;
import microsoft.exchange.webservices.data.core.response.ServiceResponse;

/**
 * Represents a GetFolder request specialized to return ServiceResponse.
 */
public final class GetFolderRequestForLoad extends
    GetFolderRequestBase<ServiceResponse> {

  /**
   * Initializes a new instance of the GetFolderRequestForLoad class.
   *
   * @param exchangeService the exchange service
   * @param throwonerror    the throwonerror
   * @throws Exception
   */
  public GetFolderRequestForLoad(ExchangeService exchangeService, ServiceErrorHandling throwonerror) throws Exception {
    super(exchangeService, throwonerror);
  }

  /**
   * Creates the service response.
   *
   * @param service       The Service
   * @param responseIndex the response index
   * @return Service response.
   */
  @Override
  protected ServiceResponse createServiceResponse(ExchangeService service,
      int responseIndex) {
    return new GetFolderResponse(this.getFolderIds()
        .getFolderIdWrapperList(responseIndex).getFolder(), this
        .getPropertySet());
  }

}
