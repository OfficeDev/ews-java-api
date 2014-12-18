/**************************************************************************
 Exchange Web Services Java API
 Copyright (c) Microsoft Corporation
 All rights reserved.
 MIT License
 Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the ""Software""), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 THE SOFTWARE IS PROVIDED *AS IS*, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 **************************************************************************/

package microsoft.exchange.webservices.data;

/**
 * Represents a DeleteItem request.
 */
final class DeleteItemRequest extends DeleteRequest<ServiceResponse> {

  /**
   * The item ids.
   */
  private ItemIdWrapperList itemIds = new ItemIdWrapperList();

  /**
   * The affected task occurrences.
   */
  private AffectedTaskOccurrence affectedTaskOccurrences;

  /**
   * The send cancellations mode.
   */
  private SendCancellationsMode sendCancellationsMode;

  /**
   * Initializes a new instance of the class.
   *
   * @param service           the service
   * @param errorHandlingMode the error handling mode
   * @throws Exception
   */
  DeleteItemRequest(ExchangeService service,
      ServiceErrorHandling errorHandlingMode)
      throws Exception {
    super(service, errorHandlingMode);
  }

  /**
   * Validate request.
   *
   * @throws Exception the exception
   */
  @Override
  protected void validate() throws Exception {
    super.validate();
    EwsUtilities.validateParam(this.itemIds, "ItemIds");
  }

  /**
   * Gets the expected response message count.
   *
   * @return Number of expected response messages
   */
  @Override
  protected int getExpectedResponseMessageCount() {
    return this.itemIds.getCount();
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
   * Gets the name of the XML element.
   *
   * @return XML element name
   */
  @Override
  protected String getXmlElementName() {
    return XmlElementNames.DeleteItem;
  }

  /**
   * Gets the name of the response XML element.
   *
   * @return XML element name
   */
  @Override
  protected String getResponseXmlElementName() {
    return XmlElementNames.DeleteItemResponse;
  }

  /**
   * Gets the name of the response message XML element.
   *
   * @return XML element name
   */
  @Override
  protected String getResponseMessageXmlElementName() {
    return XmlElementNames.DeleteItemResponseMessage;
  }

  /**
   * Writes XML attributes.
   *
   * @param writer the writer
   * @throws ServiceXmlSerializationException the service xml serialization exception
   */
  @Override
  protected void writeAttributesToXml(EwsServiceXmlWriter writer)
      throws ServiceXmlSerializationException {
    super.writeAttributesToXml(writer);

    if (this.affectedTaskOccurrences != null) {
      writer.writeAttributeValue(
          XmlAttributeNames.AffectedTaskOccurrences, this
              .getAffectedTaskOccurrences());
    }

    if (this.sendCancellationsMode != null) {
      writer.writeAttributeValue(
          XmlAttributeNames.SendMeetingCancellations, this
              .getSendCancellationsMode());
    }
  }

  /**
   * Writes XML elements.
   *
   * @param writer the writer
   * @throws Exception the exception
   */
  @Override
  protected void writeElementsToXml(EwsServiceXmlWriter writer)
      throws Exception {
    this.itemIds.writeToXml(writer, XmlNamespace.Messages,
        XmlElementNames.ItemIds);
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
   * Gets the item ids.
   *
   * @return the item ids
   */
  ItemIdWrapperList getItemIds() {
    return this.itemIds;
  }

  /**
   * Gets the affected task occurrences.
   *
   * @return the affected task occurrences
   */
  AffectedTaskOccurrence getAffectedTaskOccurrences() {
    return this.affectedTaskOccurrences;
  }

  /**
   * Sets the affected task occurrences.
   *
   * @param affectedTaskOccurrences the new affected task occurrences
   */
  void setAffectedTaskOccurrences(
      AffectedTaskOccurrence affectedTaskOccurrences) {
    this.affectedTaskOccurrences = affectedTaskOccurrences;
  }

  /**
   * Gets the send cancellations.
   *
   * @return the send cancellations mode
   */
  SendCancellationsMode getSendCancellationsMode() {
    return this.sendCancellationsMode;
  }

  /**
   * Sets the send cancellations mode.
   *
   * @param sendCancellationsMode the new send cancellations mode
   */
  void setSendCancellationsMode(SendCancellationsMode sendCancellationsMode) {
    this.sendCancellationsMode = sendCancellationsMode;
  }

}
