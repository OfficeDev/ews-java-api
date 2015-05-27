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
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.response.SubscribeResponse;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.exception.misc.ArgumentException;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlSerializationException;
import microsoft.exchange.webservices.data.notification.PullSubscription;

import javax.xml.stream.XMLStreamException;

/**
 * Represents a "pull" Subscribe request.
 */
public class SubscribeToPullNotificationsRequest extends
    SubscribeRequest<PullSubscription> {

  /**
   * The timeout.
   */
  private int timeout = 30;

  /**
   * Instantiates a new subscribe to pull notification request.
   *
   * @param service the service
   * @throws Exception the exception
   */
  public SubscribeToPullNotificationsRequest(ExchangeService service)
      throws Exception {

    super(service);

  }

  /**
   * Gets the timeout.
   *
   * @return the timeout
   */
  public int getTimeout() {
    return this.timeout;
  }

  /**
   * Sets the time out.
   *
   * @param timeout the new time out
   */
  public void setTimeOut(int timeout) {
    this.timeout = timeout;
  }

  /**
   * Validate request.
   *
   * @throws Exception the exception
   */
  protected void validate() throws Exception {
    super.validate();
    if ((this.getTimeout() < 1) || (this.getTimeout() > 1440)) {
      throw new ArgumentException(String.format(
          "%d is not a valid timeout value. Valid values range from 1 to 1440.", this.getTimeout()));
    }
  }

  /**
   * Creates the service response.
   *
   * @param service       The service.
   * @param responseIndex Index of the response.
   * @return Service response.
   * @throws Exception the exception
   */
  @Override
  protected SubscribeResponse<PullSubscription> createServiceResponse(
      ExchangeService service, int responseIndex) throws Exception {
    return new SubscribeResponse<PullSubscription>(new PullSubscription(
        service));
  }

  /**
   * Gets the minimum server version required to process this request.
   *
   * @return Exchange server version.
   */
  @Override
  protected ExchangeVersion getMinimumRequiredServerVersion() {
    return ExchangeVersion.Exchange2007_SP1;
  }

  /**
   * Gets the name of the subscription XML element.
   *
   * @return XML element name
   */
  @Override
  protected String getSubscriptionXmlElementName() {
    return XmlElementNames.PullSubscriptionRequest;
  }

  /**
   * Reads response elements from XML.
   *
   * @param writer the writer
   * @throws XMLStreamException the XML stream exception
   * @throws ServiceXmlSerializationException the service xml serialization exception
   */
  @Override
  protected void internalWriteElementsToXml(EwsServiceXmlWriter writer)
      throws XMLStreamException, ServiceXmlSerializationException {
    writer.writeElementValue(XmlNamespace.Types, XmlElementNames.Timeout,
        this.getTimeout());

  }
}
