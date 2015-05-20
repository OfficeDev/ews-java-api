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
import microsoft.exchange.webservices.data.core.response.PlayOnPhoneResponse;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.property.complex.ItemId;

/**
 * Represents a PlayOnPhone request.
 */
public final class PlayOnPhoneRequest extends SimpleServiceRequestBase<PlayOnPhoneResponse> {

  /**
   * The item id.
   */
  private ItemId itemId;

  /**
   * The dial string.
   */
  private String dialString;

  /**
   * Initializes a new instance of the PlayOnPhoneRequest class.
   *
   * @param service the service
   * @throws Exception
   */
  public PlayOnPhoneRequest(ExchangeService service)
      throws Exception {
    super(service);
  }

  /**
   * Gets the name of the XML element.
   *
   * @return XML element name.
   */
  @Override public String getXmlElementName() {
    return XmlElementNames.PlayOnPhone;
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
    this.itemId.writeToXml(writer, XmlNamespace.Messages,
        XmlElementNames.ItemId);
    writer.writeElementValue(XmlNamespace.Messages,
        XmlElementNames.DialString, dialString);
  }

  /**
   * Gets the name of the response XML element.
   *
   * @return XML element name,
   */
  @Override
  protected String getResponseXmlElementName() {
    return XmlElementNames.PlayOnPhoneResponse;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected PlayOnPhoneResponse parseResponse(EwsServiceXmlReader reader)
      throws Exception {
    PlayOnPhoneResponse serviceResponse = new PlayOnPhoneResponse(this
        .getService());
    serviceResponse
        .loadFromXml(reader, XmlElementNames.PlayOnPhoneResponse);
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
  public PlayOnPhoneResponse execute() throws Exception {
    PlayOnPhoneResponse serviceResponse = internalExecute();
    serviceResponse.throwIfNecessary();
    return serviceResponse;
  }

  /**
   * Gets the item id of the message to play.
   *
   * @return the item id
   */
  protected ItemId getItemId() {
    return this.itemId;
  }

  /**
   * Sets the item id.
   *
   * @param itemId the new item id
   */
  public void setItemId(ItemId itemId) {
    this.itemId = itemId;
  }

  /**
   * Gets  the dial string.
   *
   * @return the dial string
   */
  protected String getDialString() {
    return this.dialString;
  }

  /**
   * Sets the dial string.
   *
   * @param dialString the new dial string
   */
  public void setDialString(String dialString) {
    this.dialString = dialString;
  }

}
