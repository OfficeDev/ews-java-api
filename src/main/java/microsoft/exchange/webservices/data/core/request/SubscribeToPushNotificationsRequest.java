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
import microsoft.exchange.webservices.data.core.response.SubscribeResponse;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.exception.misc.ArgumentException;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlSerializationException;
import microsoft.exchange.webservices.data.notification.PushSubscription;

import javax.xml.stream.XMLStreamException;

import java.net.URI;

/**
 * The Class SubscribeToPushNotificationsRequest.
 */
public class SubscribeToPushNotificationsRequest extends
    SubscribeRequest<PushSubscription> {

  /**
   * The frequency.
   */
  private int frequency = 30;

  /**
   * The url.
   */
  private URI url;

  /**
   * Instantiates a new subscribe to push notification request.
   *
   * @param service the service
   * @throws Exception
   */
  public SubscribeToPushNotificationsRequest(ExchangeService service)
      throws Exception {
    super(service);
  }

  /*
   * (non-Javadoc)
   *
   * @see microsoft.exchange.webservices.SubscribeRequest#validate()
   */
  @Override
  protected void validate() throws Exception {
    super.validate();
    EwsUtilities.validateParam(this.getUrl(), "Url");
    if ((this.getFrequency() < 1) || (this.getFrequency() > 1440)) {
      throw new ArgumentException(String.format(
          "%d is not a valid frequency value. Valid values range from 1 to 1440.", this.getFrequency()));
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * microsoft.exchange.webservices.SubscribeRequest
   * #getSubscriptionXmlElementName
   * ()
   */
  @Override
  protected String getSubscriptionXmlElementName() {
    return XmlElementNames.PushSubscriptionRequest;
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * microsoft.exchange.webservices.SubscribeRequest
   * #internalWriteElementsToXml
   * (microsoft.exchange.webservices.EwsServiceXmlWriter)
   */
  @Override
  protected void internalWriteElementsToXml(EwsServiceXmlWriter writer)
      throws XMLStreamException, ServiceXmlSerializationException {
    writer.writeElementValue(XmlNamespace.Types,
        XmlElementNames.StatusFrequency, this.getFrequency());
    writer.writeElementValue(XmlNamespace.Types, XmlElementNames.URL, this
        .getUrl().toString());
  }

  /*
   * (non-Javadoc)
   *
   * @seemicrosoft.exchange.webservices.MultiResponseServiceRequest#
   * createServiceResponse(microsoft.exchange.webservices.ExchangeService,
   * int)
   */
  @Override
  protected SubscribeResponse<PushSubscription> createServiceResponse(
      ExchangeService service, int responseIndex) throws Exception {
    return new SubscribeResponse<PushSubscription>(new PushSubscription(
        service));
  }

  /*
   * (non-Javadoc)
   *
   * @seemicrosoft.exchange.webservices.ServiceRequestBase#
   * getMinimumRequiredServerVersion()
   */
  @Override
  protected ExchangeVersion getMinimumRequiredServerVersion() {
    return ExchangeVersion.Exchange2007_SP1;
  }

  /**
   * Gets the frequency.
   *
   * @return the frequency
   */
  public int getFrequency() {
    return this.frequency;
  }

  /**
   * Sets the frequency.
   *
   * @param frequency the new frequency
   */
  public void setFrequency(int frequency) {
    this.frequency = frequency;
  }

  /**
   * Gets the url.
   *
   * @return the url
   */
  public URI getUrl() {
    return this.url;
  }

  /**
   * Sets the url.
   *
   * @param url the new url
   */
  public void setUrl(URI url) {
    this.url = url;
  }

}
