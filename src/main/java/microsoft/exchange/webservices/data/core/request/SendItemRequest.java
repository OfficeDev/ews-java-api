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
import microsoft.exchange.webservices.data.core.XmlAttributeNames;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.service.error.ServiceErrorHandling;
import microsoft.exchange.webservices.data.core.response.ServiceResponse;
import microsoft.exchange.webservices.data.core.service.item.Item;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlSerializationException;
import microsoft.exchange.webservices.data.property.complex.FolderId;

/**
 * Represents a SendItem request.
 */
public final class SendItemRequest extends
    MultiResponseServiceRequest<ServiceResponse> {

  /**
   * The item.
   */
  private Iterable<Item> items;

  /**
   * The saved copy destination folder id.
   */
  private FolderId savedCopyDestinationFolderId;

  /**
   * Asserts the valid.
   *
   * @throws Exception the exception
   */
  @Override
  protected void validate() throws Exception {
    super.validate();
    EwsUtilities.validateParam(this.items, "Items");

    if (this.savedCopyDestinationFolderId != null) {
      this.savedCopyDestinationFolderId.validate(this.getService()
          .getRequestedServerVersion());
    }
  }

  /**
   * Creates the service response.
   *
   * @param service       the service
   * @param responseIndex the response index
   * @return Service response.
   */
  @Override
  protected ServiceResponse createServiceResponse(ExchangeService service,
      int responseIndex) {
    return new ServiceResponse();
  }

  /**
   * Gets the expected response message count.
   *
   * @return Number of expected response messages.
   */
  @Override
  protected int getExpectedResponseMessageCount() {
    return EwsUtilities.getEnumeratedObjectCount(this.items.iterator());
  }

  /**
   * Gets the name of the XML element.
   *
   * @return XML element name
   */
  @Override public String getXmlElementName() {
    return XmlElementNames.SendItem;
  }

  /**
   * Gets the name of the response XML element.
   *
   * @return XML element name
   */
  @Override
  protected String getResponseXmlElementName() {
    return XmlElementNames.SendItemResponse;
  }

  /**
   * Gets the name of the response message XML element.
   *
   * @return XML element name
   */
  @Override
  protected String getResponseMessageXmlElementName() {
    return XmlElementNames.SendItemResponseMessage;
  }

  /**
   * Writes the attribute to XML.
   *
   * @param writer the writer
   * @throws ServiceXmlSerializationException the service xml serialization exception
   */
  @Override
  protected void writeAttributesToXml(EwsServiceXmlWriter writer)
      throws ServiceXmlSerializationException {
    super.writeAttributesToXml(writer);

    writer.writeAttributeValue(XmlAttributeNames.SaveItemToFolder,
        this.savedCopyDestinationFolderId != null);
  }

  /**
   * Writes the elements to XML.
   *
   * @param writer the writer
   * @throws Exception the exception
   */
  @Override
  protected void writeElementsToXml(EwsServiceXmlWriter writer) throws Exception {
    writer
        .writeStartElement(XmlNamespace.Messages,
            XmlElementNames.ItemIds);

    for (Item item : this.getItems()) {
      item.getId().writeToXml(writer, XmlElementNames.ItemId);
    }

    writer.writeEndElement(); // ItemIds

    if (this.savedCopyDestinationFolderId != null) {
      writer.writeStartElement(XmlNamespace.Messages,
          XmlElementNames.SavedItemFolderId);
      this.savedCopyDestinationFolderId.writeToXml(writer);
      writer.writeEndElement();
    }
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

  /**
   * Initializes a new instance of the class.
   *
   * @param service           the service
   * @param errorHandlingMode the error handling mode
   * @throws Exception
   */
  public SendItemRequest(ExchangeService service, ServiceErrorHandling errorHandlingMode)
      throws Exception {
    super(service, errorHandlingMode);
  }

  /**
   * Gets the item. <value>The item.</value>
   *
   * @return the item
   */
  public Iterable<Item> getItems() {
    return this.items;
  }

  /**
   * Sets the item.
   *
   * @param items the new item
   */
  public void setItems(Iterable<Item> items) {
    this.items = items;
  }

  /**
   * Gets the saved copy destination folder id.
   *
   * @return the saved copy destination folder id
   */
  public FolderId getSavedCopyDestinationFolderId() {
    return this.savedCopyDestinationFolderId;
  }

  /**
   * Sets the saved copy destination folder id.
   *
   * @param savedCopyDestinationFolderId the new saved copy destination folder id
   */
  public void setSavedCopyDestinationFolderId(
      FolderId savedCopyDestinationFolderId) {
    this.savedCopyDestinationFolderId = savedCopyDestinationFolderId;
  }

}
