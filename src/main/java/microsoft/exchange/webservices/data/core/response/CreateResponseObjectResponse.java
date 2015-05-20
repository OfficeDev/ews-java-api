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

package microsoft.exchange.webservices.data.core.response;

import microsoft.exchange.webservices.data.attribute.EditorBrowsable;
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.service.item.Item;
import microsoft.exchange.webservices.data.core.enumeration.attribute.EditorBrowsableState;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Represents response to generic Create request.
 */
@EditorBrowsable(state = EditorBrowsableState.Never) public final class CreateResponseObjectResponse extends CreateItemResponseBase {

  private static final Log LOG = LogFactory.getLog(CreateResponseObjectResponse.class);

  /**
   * Gets Item instance.
   *
   * @param service        The service.
   * @param xmlElementName Name of the XML element.
   * @return Item.
   * @throws Exception the exception
   */
  @Override
  protected Item getObjectInstance(ExchangeService service,
      String xmlElementName) throws Exception {
    try {
      return EwsUtilities.createEwsObjectFromXmlElementName(Item.class, service, xmlElementName);
    } catch (InstantiationException e) {
      LOG.error(e);
      return null;
    } catch (IllegalAccessException e) {
      LOG.error(e);
      return null;
    }
  }

  /**
   * Initializes a new instance of the CreateResponseObjectResponse class.
   */
  public CreateResponseObjectResponse() {
    super();
  }

}
