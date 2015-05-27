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
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.service.error.ServiceErrorHandling;
import microsoft.exchange.webservices.data.core.response.FindFolderResponse;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;

/**
 * Represents a FindFolder request.
 */
public final class FindFolderRequest extends FindRequest<FindFolderResponse> {

  /**
   * Initializes a new instance of the FindFolderRequest class.
   *
   * @param exchangeService   The Service
   * @param errorHandlingMode Indicates how errors should be handled.
   * @throws Exception
   */
  public FindFolderRequest(ExchangeService exchangeService, ServiceErrorHandling errorHandlingMode)
      throws Exception {
    super(exchangeService, errorHandlingMode);
  }

  /**
   * Creates the service response.
   *
   * @param service       The service
   * @param responseIndex Index of the response. Service response.
   * @return the find folder response
   */
  @Override
  protected FindFolderResponse createServiceResponse(ExchangeService service,
      int responseIndex) {
    return new FindFolderResponse(this.getView().getPropertySetOrDefault());
  }

  /**
   * Gets the name of the XML element.
   *
   * @return XML element name.
   */
  @Override public String getXmlElementName() {
    return XmlElementNames.FindFolder;
  }

  /**
   * Gets the name of the response XML element.
   *
   * @return XML element name.
   */
  @Override
  protected String getResponseXmlElementName() {
    return XmlElementNames.FindFolderResponse;
  }

  /**
   * Gets the name of the response message XML element.
   *
   * @return XML element name.
   */
  @Override
  protected String getResponseMessageXmlElementName() {
    return XmlElementNames.FindFolderResponseMessage;
  }

  /**
   * Gets the request version.
   *
   * @return Earliest Exchange version in which this request is supported.
   */
  @Override
  protected ExchangeVersion getMinimumRequiredServerVersion() {
    return ExchangeVersion.Exchange2007_SP1;
  }

}
