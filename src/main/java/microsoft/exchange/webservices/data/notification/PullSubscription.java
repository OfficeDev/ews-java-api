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
 * Represents a pull subscription.
 */
public final class PullSubscription extends SubscriptionBase {
  /**
   * The more events available.
   */
  private boolean moreEventsAvailable;

  /**
   * Initializes a new instance.
   *
   * @param service the service
   * @throws Exception the exception
   */
  public PullSubscription(ExchangeService service) throws Exception {
    super(service);
  }

  /**
   * Obtains a collection of events that occurred on the subscribed folder
   * since the point in time defined by the Watermark property. When GetEvents
   * succeeds, Watermark is updated.
   *
   * @return Returns a collection of events that occurred since the last
   * watermark
   * @throws Exception the exception
   */
  public GetEventsResults getEvents() throws Exception {
    GetEventsResults results = getService().getEvents(this.getId(),
        this.getWaterMark());
    this.setWaterMark(results.getNewWatermark());
    this.moreEventsAvailable = results.isMoreEventsAvailable();
    return results;
  }

  /**
   * Begins an asynchronous request to obtain a collection of events that occurred on the subscribed
   * folder since the point in time defined by the Watermark property
   *
   * @param callback The AsyncCallback delegate
   * @param state    An object that contains state information for this request
   * @return An IAsyncResult that references the asynchronous request
   * @throws Exception
   */
  public IAsyncResult beginGetEvents(AsyncCallback callback, Object state) throws Exception {
    return this.getService().beginGetEvents(callback, state, this.getId(), this.getWaterMark());
  }

  /**
   * Ends an asynchronous request to obtain a collection of events that occurred on the subscribed
   * folder since the point in time defined by the Watermark property. When EndGetEvents succeeds,
   * Watermark is updated.
   *
   * @param asyncResult An IAsyncResult that references the asynchronous request.
   * @return Returns a collection of events that occurred since the last watermark.
   * @throws Exception
   */
  public GetEventsResults endGetEvents(IAsyncResult asyncResult) throws Exception {
    GetEventsResults results = this.getService().endGetEvents(asyncResult);

    this.setWaterMark(results.getNewWatermark());
    this.moreEventsAvailable = results.isMoreEventsAvailable();

    return results;
  }

  /**
   * Unsubscribes from the pull subscription.
   *
   * @throws Exception the exception
   */
  public void unsubscribe() throws Exception {
    getService().unsubscribe(getId());
  }

  /**
   * Begins an asynchronous request to unsubscribe from the pull subscription.
   *
   * @param callback The AsyncCallback delegate.
   * @param state    An object that contains state information for this request
   * @return An IAsyncResult that references the asynchronous request
   * @throws Exception
   */
  public IAsyncResult beginUnsubscribe(AsyncCallback callback, Object state) throws Exception {
    return this.getService().beginUnsubscribe(callback, state, this.getId());
  }

  /**
   * Ends an asynchronous request to unsubscribe from the pull subscription.
   *
   * @param asyncResult An IAsyncResult that references the asynchronous request.
   * @throws Exception
   */
  public void endUnsubscribe(IAsyncResult asyncResult) throws Exception {
    this.getService().endUnsubscribe(asyncResult);
  }

  /**
   * Gets a value indicating whether more events are available on the server.
   * MoreEventsAvailable is undefined (null) until GetEvents is called.
   *
   * @return true, if is more events available
   */
  public boolean isMoreEventsAvailable() {
    return moreEventsAvailable;
  }
}
