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
import microsoft.exchange.webservices.data.core.response.GetEventsResponse;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlSerializationException;

import javax.xml.stream.XMLStreamException;

/**
 * GetEvents request.
 */
public class GetEventsRequest extends MultiResponseServiceRequest<GetEventsResponse> {

  /**
   * The subscription id.
   */
  private String subscriptionId;

  /**
   * The watermark.
   */
  private String watermark;

  /**
   * Initializes a new instance.
   *
   * @param service the service
   * @throws Exception
   */
  public GetEventsRequest(ExchangeService service)
      throws Exception {
    super(service, ServiceErrorHandling.ThrowOnError);
  }

  /**
   * Creates the service response.
   *
   * @param service       the service
   * @param responseIndex the response index
   * @return Service response
   */
  @Override
  protected GetEventsResponse createServiceResponse(ExchangeService service,
      int responseIndex) {
    return new GetEventsResponse();
  }

  /**
   * Gets the expected response message count.
   *
   * @return Response count
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
    return XmlElementNames.GetEvents;
  }

  /**
   * Gets the name of the response XML element.
   *
   * @return XML element name
   */
  @Override
  protected String getResponseXmlElementName() {
    return XmlElementNames.GetEventsResponse;
  }

  /**
   * Gets the name of the response message XML element.
   *
   * @return XML element name
   */
  @Override
  protected String getResponseMessageXmlElementName() {
    return XmlElementNames.GetEventsResponseMessage;
  }

  /**
   * Validates the request.
   *
   * @throws Exception the exception
   */
  protected void validate() throws Exception {
    super.validate();
    EwsUtilities.validateNonBlankStringParam(this.
        getSubscriptionId(), "SubscriptionId");
    EwsUtilities.validateNonBlankStringParam(this.
        getWatermark(), "Watermark");
  }

  /**
   * Writes the elements to XML writer.
   *
   * @param writer the writer
   * @throws XMLStreamException the XML stream exception
   * @throws ServiceXmlSerializationException the service xml serialization exception
   */
  @Override
  protected void writeElementsToXml(EwsServiceXmlWriter writer)
      throws XMLStreamException, ServiceXmlSerializationException {
    writer.writeElementValue(XmlNamespace.Messages,
        XmlElementNames.SubscriptionId, this.getSubscriptionId());
    writer.writeElementValue(XmlNamespace.Messages,
        XmlElementNames.Watermark, this.getWatermark());
  }

  /**
   * Gets the request version.
   *
   * @return Earliest Exchange version in which this request is supported
   */
  @Override
  protected ExchangeVersion getMinimumRequiredServerVersion() {
    return ExchangeVersion.Exchange2007_SP1;
  }

  /**
   * gets the subscriptionId.
   *
   * @return the subscriptionId.
   */
  public String getSubscriptionId() {
    return subscriptionId;
  }

  /**
   * Sets the subscriptionId.
   *
   * @param subscriptionId the subscriptionId.
   */
  public void setSubscriptionId(String subscriptionId) {
    this.subscriptionId = subscriptionId;
  }

  /**
   * gets the watermark.
   *
   * @return the watermark.
   */
  public String getWatermark() {
    return watermark;
  }

  /**
   * Sets the watermark.
   *
   * @param watermark the new watermark
   */
  public void setWatermark(String watermark) {
    this.watermark = watermark;
  }
}
