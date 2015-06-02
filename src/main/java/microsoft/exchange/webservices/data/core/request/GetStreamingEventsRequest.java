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

import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.response.GetStreamingEventsResponse;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceVersionException;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlSerializationException;

import javax.xml.stream.XMLStreamException;

/**
 * Defines the GetStreamingEventsRequest class.
 */
public class GetStreamingEventsRequest extends HangingServiceRequestBase<GetStreamingEventsResponse> {

  protected final static int HeartbeatFrequencyDefault = 45000; ////45s in ms
  private static int heartbeatFrequency = HeartbeatFrequencyDefault;

  private Iterable<String> subscriptionIds;
  private int connectionTimeout;

  /**
   * Initializes a new instance of the GetStreamingEventsRequest class.
   *
   * @param service              The service
   * @param serviceObjectHandler The serviceObjectHandler
   * @param subscriptionIds      The subscriptionIds
   * @param connectionTimeout    The connectionTimeout
   * @throws ServiceVersionException
   */
  public GetStreamingEventsRequest(ExchangeService service, IHandleResponseObject serviceObjectHandler,
      Iterable<String> subscriptionIds, int connectionTimeout)
      throws ServiceVersionException {
    super(service, serviceObjectHandler,
        GetStreamingEventsRequest.heartbeatFrequency);
    this.subscriptionIds = subscriptionIds;
    this.connectionTimeout = connectionTimeout;
  }

  /**
   * Gets the name of the XML element.
   *
   * @return XmlElementNames
   */
  @Override public String getXmlElementName() {
    return XmlElementNames.GetStreamingEvents;
  }

  /**
   * Gets the name of the response XML element.
   *
   * @return XmlElementNames
   */
  @Override
  protected String getResponseXmlElementName() {
    return XmlElementNames.GetStreamingEventsResponse;
  }

  /**
   * Writes the elements to XML writer.
   *
   * @param writer the writer
   * @throws XMLStreamException the XML stream exception
   * @throws ServiceXmlSerializationException
   */
  @Override
  protected void writeElementsToXml(EwsServiceXmlWriter writer)
      throws ServiceXmlSerializationException, XMLStreamException {
    writer.writeStartElement(XmlNamespace.Messages,
        XmlElementNames.SubscriptionIds);

    for (String id : this.subscriptionIds) {
      writer.writeElementValue(
          XmlNamespace.Types,
          XmlElementNames.SubscriptionId,
          id);
    }

    writer.writeEndElement();

    writer.writeElementValue(
        XmlNamespace.Messages,
        XmlElementNames.ConnectionTimeout,
        this.connectionTimeout);
  }

  /**
   * Gets the request version.
   *
   * @return ExchangeVersion
   */
  @Override
  protected ExchangeVersion getMinimumRequiredServerVersion() {
    return ExchangeVersion.Exchange2010_SP1;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected GetStreamingEventsResponse parseResponse(EwsServiceXmlReader reader)
      throws Exception {
    reader.readStartElement(XmlNamespace.Messages,
        XmlElementNames.ResponseMessages);

    GetStreamingEventsResponse response =
        new GetStreamingEventsResponse(this);
    response.loadFromXml(reader, XmlElementNames.
        GetStreamingEventsResponseMessage);

    reader.readEndElementIfNecessary(XmlNamespace.Messages,
        XmlElementNames.ResponseMessages);

    return response;
  }

  /**
   * region Test hooks
   * Allow test code to change heartbeat value
   */
  protected static void setHeartbeatFrequency(int heartbeatFrequency) {
    GetStreamingEventsRequest.heartbeatFrequency = heartbeatFrequency;
  }

  @Override
	protected HttpWebRequest buildEwsHttpWebRequest() throws Exception
	{
		return super.buildEwsHttpPoolingWebRequest();
	}
}
