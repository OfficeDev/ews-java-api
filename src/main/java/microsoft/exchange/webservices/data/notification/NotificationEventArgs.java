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

/**
 * Provides data to a StreamingSubscriptionConnection's
 * OnNotificationEvent event.
 */
public class NotificationEventArgs {
  private StreamingSubscription subscription;
  private Iterable<NotificationEvent> events;

  /**
   * Initializes a new instance of the NotificationEventArgs class.
   *
   * @param subscription The subscription for which notification have been received.
   * @param events       The events that were received.
   */
  protected NotificationEventArgs(
      StreamingSubscription subscription,
      Iterable<NotificationEvent> events) {
    this.setSubscription(subscription);
    this.setEvents(events);
  }

  /**
   * Gets the subscription for which notification have been received.
   */
  public StreamingSubscription getSubscription() {
    return this.subscription;

  }

  /**
   * Sets the events that were received.
   */
  protected void setSubscription(StreamingSubscription value) {
    this.subscription = value;
  }

  /**
   * Gets the events that were received.
   */
  public Iterable<NotificationEvent> getEvents() {
    return this.events;

  }

  /**
   * Sets the events that were received.
   */
  protected void setEvents(Iterable<NotificationEvent> value) {
    this.events = value;
  }


}
