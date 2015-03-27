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
 * OnSubscriptionError and OnDisconnect events.
 */
public class SubscriptionErrorEventArgs { //TODO extends EventObject {

  private StreamingSubscription subscription;
  private Exception exception;

  /**
   * Initializes a new instance of the SubscriptionErrorEventArgs class.
   *
   * @param subscription The subscription for which an error occurred.
   *                     If subscription is null, the error applies to the entire connection.
   * @param exception    The exception representing the error.
   *                     If exception is null, the connection
   *                     was cleanly closed by the server.
   */
  protected SubscriptionErrorEventArgs(
      StreamingSubscription subscription,
      Exception exception) {
    //	super(subscription); //TODO need to check for EventObject
    this.setSubscription(subscription);
    this.setException(exception);
  }

  /**
   * Gets the subscription for which an error occurred.
   * If Subscription is null, the error applies to the entire connection.
   */
  public StreamingSubscription getSubscription() {
    return this.subscription;

  }

  /**
   * Sets the subscription for which an error occurred.
   * If Subscription is null, the error applies to the entire connection.
   */
  protected void setSubscription(StreamingSubscription value) {
    this.subscription = value;

  }

  /**
   * Gets the exception representing the error. If Exception is null,
   * the connection was cleanly closed by the server.
   */
  public Exception getException() {
    return this.exception;

  }

  /**
   * Sets the exception representing the error. If Exception is null,
   * the connection was cleanly closed by the server.
   */
  protected void setException(Exception value) {
    this.exception = value;

  }
}
