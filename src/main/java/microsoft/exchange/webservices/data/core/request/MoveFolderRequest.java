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
import microsoft.exchange.webservices.data.core.response.MoveCopyFolderResponse;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;

/**
 * Represents a MoveFolder request.
 */
public class MoveFolderRequest extends MoveCopyFolderRequest<MoveCopyFolderResponse> {

  /**
   * Initializes a new instance of the MoveFolderRequest class.
   *
   * @param service           The service.
   * @param errorHandlingMode Indicates how errors should be handled.
   * @throws Exception
   */
  public MoveFolderRequest(ExchangeService service, ServiceErrorHandling errorHandlingMode)
      throws Exception {
    super(service, errorHandlingMode);
  }

  /**
   * Creates the service response.
   *
   * @param service       The service.
   * @param responseIndex Index of the response.
   * @return Service response.
   */
  @Override
  protected MoveCopyFolderResponse createServiceResponse(
      ExchangeService service, int responseIndex) {
    return new MoveCopyFolderResponse();
  }

  /**
   * Gets the name of the XML element.
   *
   * @return Xml element name.
   */
  @Override public String getXmlElementName() {
    return XmlElementNames.MoveFolder;
  }

  /**
   * Gets the name of the response XML element.
   *
   * @return Xml element name.
   */
  @Override
  protected String getResponseXmlElementName() {
    return XmlElementNames.MoveFolderResponse;
  }

  /**
   * Gets the name of the response message XML element.
   *
   * @return Xml element name.
   */
  @Override
  protected String getResponseMessageXmlElementName() {
    return XmlElementNames.MoveFolderResponseMessage;
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
