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

package microsoft.exchange.webservices.data.core.requests;

import microsoft.exchange.webservices.data.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.ExchangeService;
import microsoft.exchange.webservices.data.GetRoomListsResponse;
import microsoft.exchange.webservices.data.XmlElementNames;
import microsoft.exchange.webservices.data.enumerations.ExchangeVersion;

/**
 * Represents a GetRoomList request.
 */
final class GetRoomListsRequest extends SimpleServiceRequestBase<GetRoomListsResponse> {

  /**
   * Initializes a new instance of the class.
   *
   * @param service the service
   * @throws Exception
   */
  protected GetRoomListsRequest(ExchangeService service)
      throws Exception {
    super(service);
  }

  /**
   * Gets the name of the XML element.
   *
   * @return XML element name
   */
  @Override
  protected String getXmlElementName() {
    return XmlElementNames.GetRoomListsRequest;
  }

  /**
   * Writes XML elements.
   *
   * @param writer the writer
   */
  @Override
  protected void writeElementsToXml(EwsServiceXmlWriter writer) {
    // Don't have parameter in request
  }

  /**
   * Gets the name of the response XML element.
   *
   * @return XML element name
   */
  @Override
  protected String getResponseXmlElementName() {
    return XmlElementNames.GetRoomListsResponse;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected GetRoomListsResponse parseResponse(EwsServiceXmlReader reader)
      throws Exception {
    GetRoomListsResponse response = new GetRoomListsResponse();
    response.loadFromXml(reader, XmlElementNames.GetRoomListsResponse);
    return response;
  }

  /**
   * Gets the request version.
   *
   * @return Earliest Exchange version in which this request is supported.
   */
  @Override
  protected ExchangeVersion getMinimumRequiredServerVersion() {
    return ExchangeVersion.Exchange2010;
  }

  /**
   * Executes this request.
   *
   * @return Service response
   * @throws Exception the exception
   */
  protected GetRoomListsResponse execute() throws Exception {
    GetRoomListsResponse serviceResponse = (GetRoomListsResponse) this
        .internalExecute();
    serviceResponse.throwIfNecessary();
    return serviceResponse;
  }
}
