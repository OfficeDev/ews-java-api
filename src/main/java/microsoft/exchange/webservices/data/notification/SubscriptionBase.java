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

package microsoft.exchange.webservices.data.notification;

import microsoft.exchange.webservices.data.attribute.EditorBrowsable;
import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.attribute.EditorBrowsableState;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;

/**
 * Represents the base class for event subscriptions.
 */
@EditorBrowsable(state = EditorBrowsableState.Never)
public abstract class SubscriptionBase {

  /**
   * The service.
   */
  private ExchangeService service;

  /**
   * The id.
   */
  private String id;

  /**
   * The watermark.
   */
  private String watermark;

  /**
   * Instantiates a new subscription base.
   *
   * @param service the service
   * @throws Exception the exception
   */
  protected SubscriptionBase(ExchangeService service) throws Exception {
    EwsUtilities.validateParam(service, "service");
    // EwsUtilities.validateParam(service, "service");

    this.service = service;
  }

  /**
   * Instantiates a new subscription base.
   *
   * @param service the service
   * @param id      the id
   * @throws Exception the exception
   */
  protected SubscriptionBase(ExchangeService service, String id)
      throws Exception {
    this(service);
    EwsUtilities.validateParam(id, "id");

    this.id = id;
  }

  /**
   * Instantiates a new subscription base.
   *
   * @param service   the service
   * @param id        the id
   * @param watermark the watermark
   * @throws Exception the exception
   */
  protected SubscriptionBase(ExchangeService service, String id,
      String watermark) throws Exception {
    this(service, id);
    this.watermark = watermark;
  }

  /**
   * Load from xml.
   *
   * @param reader the reader
   * @throws Exception the exception
   */
  public void loadFromXml(EwsServiceXmlReader reader) throws Exception {
    this.id = reader.readElementValue(XmlNamespace.Messages,
        XmlElementNames.SubscriptionId);
    if (this.getUsesWatermark()) {
      this.watermark = reader.readElementValue(XmlNamespace.Messages,
          XmlElementNames.Watermark);
    }

  }

  /**
   * Gets the session.
   *
   * @return the session
   */
  protected ExchangeService getService() {
    return this.service;
  }

  /**
   * Gets the id.
   *
   * @return the id
   */
  public String getId() {
    return id;
  }

  /**
   * Sets the id.
   *
   * @param id the new id
   */
  protected void setId(String id) {
    this.id = id;
  }

  /**
   * Sets the water mark.
   *
   * @param watermark the new water mark
   */
  protected void setWaterMark(String watermark) {
    this.watermark = watermark;
  }

  /**
   * Gets the water mark.
   *
   * @return the water mark
   */
  public String getWaterMark() {
    return this.watermark;
  }

  /**
   * Gets whether or not this subscription uses watermarks.
   */
  protected boolean getUsesWatermark() {
    return true;
  }

}
