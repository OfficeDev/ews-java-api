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

import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.notification.GetEventsResults;

/**
 * Represents the response to a subscription event retrieval operation.
 */
public final class GetEventsResponse extends ServiceResponse {

  /**
   * The results.
   */
  private GetEventsResults results = new GetEventsResults();

  /**
   * Initializes a new instance.
   */
  public GetEventsResponse() {
    super();
  }

  /**
   * Reads response elements from XML.
   *
   * @param reader the reader
   * @throws Exception the exception
   */
  protected void readElementsFromXml(EwsServiceXmlReader reader)
      throws Exception {
    super.readElementsFromXml(reader);
    this.results.loadFromXml(reader);
  }

  /**
   * gets the results.
   *
   * @return the results.
   */
  public GetEventsResults getResults() {
    return results;
  }

}
