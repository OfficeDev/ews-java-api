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
import microsoft.exchange.webservices.data.core.response.SubscribeResponse;
import microsoft.exchange.webservices.data.core.enumeration.notification.EventType;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceValidationException;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlSerializationException;
import microsoft.exchange.webservices.data.misc.FolderIdWrapperList;
import microsoft.exchange.webservices.data.notification.SubscriptionBase;

import javax.xml.stream.XMLStreamException;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class SubscribeRequest.
 *
 * @param <TSubscription> the generic type
 */
abstract class SubscribeRequest<TSubscription extends SubscriptionBase> extends
    MultiResponseServiceRequest<SubscribeResponse<TSubscription>> {

  /**
   * The folder ids.
   */
  private FolderIdWrapperList folderIds = new FolderIdWrapperList();

  /**
   * The event types.
   */
  private List<EventType> eventTypes = new ArrayList<EventType>();

  /**
   * The watermark.
   */
  private String watermark;

  /**
   * Validate request.
   *
   * @throws Exception the exception
   */
  @Override
  protected void validate() throws Exception {
    super.validate();
    EwsUtilities.validateParam(this.getFolderIds(), "FolderIds");
    EwsUtilities.validateParamCollection(this.getEventTypes().iterator(),
        "EventTypes");
    this.getFolderIds().validate(
        this.getService().getRequestedServerVersion());
    // Check that caller isn't trying
    //to subscribe to Status events.
    if (this.getEventTypes().contains(EventType.Status)) {
      throw new ServiceValidationException("Status events can't be subscribed to.");
    }

    // If Watermark was specified, make sure it's not a blank string.
    if (!(this.getWatermark() == null ||
        this.getWatermark().isEmpty())) {
      EwsUtilities.validateNonBlankStringParam(this.
          getWatermark(), "Watermark");
    }

    for (EventType eventType : this.getEventTypes()) {
      EwsUtilities.validateEnumVersionValue(eventType,
          this.getService().getRequestedServerVersion());
    }

  }

  /**
   * Gets the name of the subscription XML element.
   *
   * @return XML element name
   */
  protected abstract String getSubscriptionXmlElementName();

  /**
   * Gets the expected response message count.
   *
   * @return Number of expected response messages.
   */
  @Override
  protected int getExpectedResponseMessageCount() {
    return 1;
  }

  /**
   * Gets the name of the XML element.
   *
   * @return XML element name
   */
  @Override public String getXmlElementName() {
    return XmlElementNames.Subscribe;
  }

  /**
   * Gets the name of the response XML element.
   *
   * @return XML element name
   */
  @Override
  protected String getResponseXmlElementName() {
    return XmlElementNames.SubscribeResponse;
  }

  /**
   * Gets the name of the response message XML element.
   *
   * @return XML element name
   */
  @Override
  protected String getResponseMessageXmlElementName() {
    return XmlElementNames.SubscribeResponseMessage;
  }

  /**
   * Internal method to write XML elements.
   *
   * @param writer the writer
   * @throws XMLStreamException the XML stream exception
   * @throws ServiceXmlSerializationException the service xml serialization exception
   */
  protected abstract void internalWriteElementsToXml(
      EwsServiceXmlWriter writer) throws XMLStreamException, ServiceXmlSerializationException;

  /**
   * Writes XML elements.
   *
   * @param writer the writer
   * @throws Exception the exception
   */
  @Override
  protected void writeElementsToXml(EwsServiceXmlWriter writer)
      throws Exception {
    writer.writeStartElement(XmlNamespace.Messages, this
        .getSubscriptionXmlElementName());

    if (this.getFolderIds().getCount() == 0) {
      writer.writeAttributeValue(XmlAttributeNames.SubscribeToAllFolders,
          true);
    }

    this.getFolderIds().writeToXml(writer, XmlNamespace.Types,
        XmlElementNames.FolderIds);

    writer
        .writeStartElement(XmlNamespace.Types,
            XmlElementNames.EventTypes);
    for (EventType eventType : this.getEventTypes()) {
      writer.writeElementValue(XmlNamespace.Types,
          XmlElementNames.EventType, eventType);
    }
    writer.writeEndElement();

    if (!(this.getWatermark() == null || this.getWatermark().isEmpty())) {
      writer.writeElementValue(XmlNamespace.Types,
          XmlElementNames.Watermark, this.getWatermark());
    }

    this.internalWriteElementsToXml(writer);

    writer.writeEndElement();
  }

  /**
   * Instantiates a new subscribe request.
   *
   * @param service the service
   * @throws Exception
   */
  protected SubscribeRequest(ExchangeService service)
      throws Exception {
    super(service, ServiceErrorHandling.ThrowOnError);
    this.setFolderIds(new FolderIdWrapperList());
    this.setEventTypes(new ArrayList<EventType>());
  }

  /**
   * Gets the folder ids.
   *
   * @return the folder ids
   */
  public FolderIdWrapperList getFolderIds() {
    return this.folderIds;
  }

  /**
   * Sets the folder ids.
   */
  private void setFolderIds(FolderIdWrapperList value) {
    this.folderIds = value;
  }

  /**
   * Gets the event types.
   *
   * @return the event types
   */
  public List<EventType> getEventTypes() {
    return this.eventTypes;
  }

  /**
   * set the EventTypes
   */
  private void setEventTypes(List<EventType> value) {
    this.eventTypes = value;
  }

  /**
   * Gets the watermark.
   *
   * @return the watermark
   */
  public String getWatermark() {
    return this.watermark;
  }

  /**
   * Sets the watermark.
   *
   * @param watermark the new watermark
   */
  public void setWatermark(String watermark) {
    this.watermark = watermark;
  }

  @Override
	protected HttpWebRequest buildEwsHttpWebRequest() throws Exception
	{
		return super.buildEwsHttpPoolingWebRequest();
	}
}
