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

import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.misc.AsyncCallback;
import microsoft.exchange.webservices.data.misc.IAsyncResult;

/**
 * Represents a streaming subscription.
 */
public final class StreamingSubscription extends SubscriptionBase {

  public StreamingSubscription(ExchangeService service) throws Exception {
    super(service);
  }

  /**
   * Unsubscribes from the streaming subscription.
   */
  public void unsubscribe() throws Exception {
    this.getService().unsubscribe(this.getId());
  }

  /**
   * Begins an asynchronous request to unsubscribe from the streaming subscription.
   *
   * @param callback The AsyncCallback delegate.
   * @param state    An object that contains state information for this request.
   * @return An IAsyncResult that references the asynchronous request.
   * @throws Exception
   */
  public IAsyncResult beginUnsubscribe(AsyncCallback callback, Object state) throws Exception {
    return this.getService().beginUnsubscribe(callback, state, this.getId());
  }

  /**
   * Ends an asynchronous request to unsubscribe from the streaming subscription.
   *
   * @param asyncResult An IAsyncResult that references the asynchronous request.
   */
  public void endUnsubscribe(IAsyncResult asyncResult) throws Exception {
    this.getService().endUnsubscribe(asyncResult);
  }

  /**
   * Gets the service used to create this subscription.
   */
  public ExchangeService getService() {
    return super.getService();
  }


  /**
   * Gets a value indicating whether this subscription uses watermarks.
   */
  @Override
  protected boolean getUsesWatermark() {
    return false;
  }
}
