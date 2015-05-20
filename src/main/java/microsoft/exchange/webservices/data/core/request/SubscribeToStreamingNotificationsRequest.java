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
import microsoft.exchange.webservices.data.core.exception.misc.ArgumentException;
import microsoft.exchange.webservices.data.notification.StreamingSubscription;

/**
 * Defines the SubscribeToStreamingNotificationsRequest class.
 */
public class SubscribeToStreamingNotificationsRequest extends
    SubscribeRequest<StreamingSubscription> {

  /**
   * Initializes a new instance of the
   * SubscribeToStreamingNotificationsRequest class.
   *
   * @param service The service
   * @throws Exception
   */
  public SubscribeToStreamingNotificationsRequest(ExchangeService service)
      throws Exception {
    super(service);
  }

  /**
   * Validate request.
   *
   * @throws Exception
   */
  @Override
  protected void validate() throws Exception {
    super.validate();

    if (!(this.getWatermark() == null || this.getWatermark().isEmpty())) {
      throw new ArgumentException(
          "Watermarks cannot be used with StreamingSubscriptions.");
    }
  }


  /**
   * Gets the name of the subscription XML element.
   *
   * @return XmlElementsNames
   */
  @Override
  protected String getSubscriptionXmlElementName() {
    return XmlElementNames.StreamingSubscriptionRequest;
  }

  /**
   * Internals the write elements to XML.
   *
   * @param writer The writer
   */
  @Override
  protected void internalWriteElementsToXml(EwsServiceXmlWriter writer) {
  }

  /**
   * Creates the service response.
   *
   * @param service       The service
   * @param responseIndex The responseIndex
   * @return SubscribeResponse
   * @throws Exception
   */
  @Override
  protected SubscribeResponse<StreamingSubscription> createServiceResponse(ExchangeService service,
      int responseIndex) throws Exception {
    return new SubscribeResponse<StreamingSubscription>(
        new StreamingSubscription(service));
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
}
