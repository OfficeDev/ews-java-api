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
import microsoft.exchange.webservices.data.core.response.ServiceResponse;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.messaging.PhoneCallId;

/**
 * Represents a DisconnectPhoneCall request.
 */
public final class DisconnectPhoneCallRequest extends SimpleServiceRequestBase<ServiceResponse> {

  /**
   * The id.
   */
  private PhoneCallId id;

  /**
   * Initializes a new instance of the DisconnectPhoneCallRequest class.
   *
   * @param service the service
   * @throws Exception
   */
  public DisconnectPhoneCallRequest(ExchangeService service)
      throws Exception {
    super(service);
  }

  /**
   * Gets the name of the XML element.
   *
   * @return XML element name.
   */
  @Override public String getXmlElementName() {
    return XmlElementNames.DisconnectPhoneCall;
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
    this.id.writeToXml(writer, XmlNamespace.Messages,
        XmlElementNames.PhoneCallId);
  }

  /**
   * Gets the name of the response XML element.
   *
   * @return XML element name.
   */
  @Override
  protected String getResponseXmlElementName() {
    return XmlElementNames.DisconnectPhoneCallResponse;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected ServiceResponse parseResponse(EwsServiceXmlReader reader)
      throws Exception {
    ServiceResponse serviceResponse = new ServiceResponse();
    serviceResponse.loadFromXml(reader,
        XmlElementNames.DisconnectPhoneCallResponse);
    return serviceResponse;
  }

  /**
   * Gets the request version.
   *
   * @return Earliest Exchange version in which this request is supported.
   */
  @Override
  protected ExchangeVersion getMinimumRequiredServerVersion() {
    return ExchangeVersion.Exchange2010;
  }

  /**
   * Executes this request.
   *
   * @return Service response.
   * @throws Exception the exception
   */
  public ServiceResponse execute() throws Exception {
    ServiceResponse serviceResponse = internalExecute();
    serviceResponse.throwIfNecessary();
    return serviceResponse;
  }

  /**
   * Gets  the Id of the phone call.
   *
   * @return the id
   */
  protected PhoneCallId getId() {
    return this.id;
  }

  /**
   * Sets the id.
   *
   * @param id the new id
   */
  public void setId(PhoneCallId id) {
    this.id = id;
  }

}
