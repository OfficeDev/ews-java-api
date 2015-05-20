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
import microsoft.exchange.webservices.data.core.service.ServiceObject;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.property.complex.FolderId;

/**
 * Represents an abstract Move/Copy request.
 *
 * @param <TServiceObject> The type of the service object.
 * @param <TResponse>      The type of the response.
 */
abstract class MoveCopyRequest<TServiceObject extends ServiceObject,
    TResponse extends ServiceResponse> extends
    MultiResponseServiceRequest<TResponse> {

  /**
   * The destination folder id.
   */
  private FolderId destinationFolderId;

  /**
   * Validates request.
   *
   * @throws Exception the exception
   */
  @Override
  protected void validate() throws Exception {
    EwsUtilities.validateParam(this.getDestinationFolderId(), "DestinationFolderId");
    this.getDestinationFolderId().validate(
        this.getService().getRequestedServerVersion());
  }

  /**
   * Initializes a new instance of the MoveCopyRequest class.
   *
   * @param service           The Service
   * @param errorHandlingMode Indicates how errors should be handled.
   * @throws Exception
   */
  protected MoveCopyRequest(ExchangeService service,
      ServiceErrorHandling errorHandlingMode)
      throws Exception {
    super(service, errorHandlingMode);
  }

  /**
   * Writes the ids as XML.
   *
   * @param writer The Writer
   * @throws Exception the exception
   */
  protected abstract void writeIdsToXml(EwsServiceXmlWriter writer)
      throws Exception;

  /**
   * Writes XML elements.
   *
   * @param writer the writer
   * @throws Exception the exception
   */
  @Override
  protected void writeElementsToXml(EwsServiceXmlWriter writer)
      throws Exception {
    writer.writeStartElement(XmlNamespace.Messages,
        XmlElementNames.ToFolderId);
    this.getDestinationFolderId().writeToXml(writer);
    writer.writeEndElement();

    this.writeIdsToXml(writer);
  }

  /**
   * Gets the destination folder id.
   *
   * @return the destination folder id
   */
  public FolderId getDestinationFolderId() {
    return this.destinationFolderId;
  }

  /**
   * Sets the destination folder id.
   *
   * @param destinationFolderId the new destination folder id
   */
  public void setDestinationFolderId(FolderId destinationFolderId) {
    this.destinationFolderId = destinationFolderId;
  }

}
