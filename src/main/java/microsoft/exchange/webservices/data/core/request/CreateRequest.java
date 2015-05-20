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
import microsoft.exchange.webservices.data.core.enumeration.service.error.ServiceErrorHandling;
import microsoft.exchange.webservices.data.core.response.ServiceResponse;
import microsoft.exchange.webservices.data.core.service.ServiceObject;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.property.complex.FolderId;

import java.util.Collection;

/**
 * Represents an abstract Create request.
 *
 * @param <TServiceObject> The type of the service object.
 * @param <TResponse>      The type of the response.
 */
abstract class CreateRequest<TServiceObject extends ServiceObject,
    TResponse extends ServiceResponse>
    extends MultiResponseServiceRequest<TResponse> {

  /**
   * The parent folder id.
   */
  private FolderId parentFolderId;

  /**
   * The objects.
   */
  private Collection<TServiceObject> objects;

  /**
   * Initializes a new instance.
   *
   * @param service           The service.
   * @param errorHandlingMode Indicates how errors should be handled.
   * @throws Exception
   */
  protected CreateRequest(ExchangeService service,
      ServiceErrorHandling errorHandlingMode)
      throws Exception {
    super(service, errorHandlingMode);
  }

  /**
   * Validates the request.
   *
   * @throws Exception the exception
   */
  @Override
  protected void validate() throws Exception {
    super.validate();
    if (this.getParentFolderId() != null) {
      this.getParentFolderId().validate(
          this.getService().getRequestedServerVersion());
    }
  }

  /**
   * Gets the expected response message count.
   *
   * @return the expected response message count
   */
  @Override
  protected int getExpectedResponseMessageCount() {
    return EwsUtilities.getEnumeratedObjectCount(this.objects.iterator());
  }

  /**
   * Gets the name of the parent folder XML element.
   *
   * @return The name of the parent folder XML element.
   */
  protected abstract String getParentFolderXmlElementName();

  /**
   * Gets the name of the object collection XML element.
   *
   * @return The name of the object collection XML element.
   */
  protected abstract String getObjectCollectionXmlElementName();

  /*
   * (non-Javadoc)
   *
   * @see
   * microsoft.exchange.webservices.ServiceRequestBase#writeElementsToXml(
   * microsoft.exchange.webservices.EwsServiceXmlWriter)
   */
  @Override
  protected void writeElementsToXml(EwsServiceXmlWriter writer)
      throws Exception {
    if (this.parentFolderId != null) {
      writer.writeStartElement(XmlNamespace.Messages, this
          .getParentFolderXmlElementName());
      this.getParentFolderId().writeToXml(writer);
      writer.writeEndElement();
    }

    writer.writeStartElement(XmlNamespace.Messages, this
        .getObjectCollectionXmlElementName());
    if (null != this.objects) {
      for (ServiceObject obj : this.objects) {
        obj.writeToXml(writer);
      }
    }
    writer.writeEndElement();

  }

  /**
   * Gets the service objects.
   *
   * @return Iterator
   */
  protected Iterable<TServiceObject> getObjects() {
    return this.objects;
  }

  /**
   * Sets the service objects.
   *
   * @param value Iterator<TServiceObject>
   */
  protected void setObjects(Collection<TServiceObject> value) {
    this.objects = value;
  }

  /**
   * Gets the parent folder id.
   *
   * @return FolderId.
   */
  public FolderId getParentFolderId() {
    return this.parentFolderId;
  }

  /**
   * Sets the parent folder id.
   *
   * @param value FolderId.
   */
  public void setParentFolderId(FolderId value) {
    this.parentFolderId = value;
  }
}
