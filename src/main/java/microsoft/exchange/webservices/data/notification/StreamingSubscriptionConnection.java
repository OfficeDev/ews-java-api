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

import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.exception.misc.ArgumentNullException;
import microsoft.exchange.webservices.data.core.request.GetStreamingEventsRequest;
import microsoft.exchange.webservices.data.core.request.HangingRequestDisconnectEventArgs;
import microsoft.exchange.webservices.data.core.request.HangingServiceRequestBase;
import microsoft.exchange.webservices.data.core.response.GetStreamingEventsResponse;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.misc.error.ServiceError;
import microsoft.exchange.webservices.data.core.enumeration.service.ServiceResult;
import microsoft.exchange.webservices.data.core.exception.misc.ArgumentException;
import microsoft.exchange.webservices.data.core.exception.misc.ArgumentOutOfRangeException;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceLocalException;
import microsoft.exchange.webservices.data.core.exception.service.remote.ServiceResponseException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a connection to an ongoing stream of events.
 */
public final class StreamingSubscriptionConnection implements Closeable,
                                                              HangingServiceRequestBase.IHandleResponseObject,
    HangingServiceRequestBase.IHangingRequestDisconnectHandler {

  private static final Log LOG = LogFactory.getLog(StreamingSubscriptionConnection.class);

  /**
   * Mapping of streaming id to subscriptions currently on the connection.
   */
  private Map<String, StreamingSubscription> subscriptions;

  /**
   * connection lifetime, in minutes
   */
  private int connectionTimeout;

  /**
   * ExchangeService instance used to make the EWS call.
   */
  private ExchangeService session;

  /**
   * Value indicating whether the class is disposed.
   */
  private boolean isDisposed;

  /**
   * Currently used instance of a GetStreamingEventsRequest connected to EWS.
   */
  private GetStreamingEventsRequest currentHangingRequest;


  public interface INotificationEventDelegate {
    /**
     * Represents a delegate that is invoked when notification are received
     * from the server
     *
     * @param sender The StreamingSubscriptionConnection instance that received
     *               the events.
     * @param args   The event data.
     */
    void notificationEventDelegate(Object sender, NotificationEventArgs args);
  }


  /**
   * Notification events Occurs when notification are received from the
   * server.
   */
  private List<INotificationEventDelegate> onNotificationEvent = new ArrayList<INotificationEventDelegate>();

  /**
   * Set event to happen when property Notify.
   *
   * @param notificationEvent notification event
   */
  public void addOnNotificationEvent(
      INotificationEventDelegate notificationEvent) {
    onNotificationEvent.add(notificationEvent);
  }

  /**
   * Remove the event from happening when property Notify.
   *
   * @param notificationEvent notification event
   */
  public void removeNotificationEvent(
      INotificationEventDelegate notificationEvent) {
    onNotificationEvent.remove(notificationEvent);
  }

  /**
   * Clears notification events list.
   */
  public void clearNotificationEvent() {
    onNotificationEvent.clear();
  }

  public interface ISubscriptionErrorDelegate {

    /**
     * Represents a delegate that is invoked when an error occurs within a
     * streaming subscription connection.
     *
     * @param sender The StreamingSubscriptionConnection instance within which
     *               the error occurred.
     * @param args   The event data.
     */
    void subscriptionErrorDelegate(Object sender,
        SubscriptionErrorEventArgs args);
  }


  /**
   * Subscription events Occur when a subscription encounters an error.
   */
  private List<ISubscriptionErrorDelegate> onSubscriptionError = new ArrayList<ISubscriptionErrorDelegate>();

  /**
   * Set event to happen when property subscriptionError.
   *
   * @param subscriptionError subscription event
   */
  public void addOnSubscriptionError(
      ISubscriptionErrorDelegate subscriptionError) {
    onSubscriptionError.add(subscriptionError);
  }

  /**
   * Remove the event from happening when property subscription.
   *
   * @param subscriptionError subscription event
   */
  public void removeSubscriptionError(
      ISubscriptionErrorDelegate subscriptionError) {
    onSubscriptionError.remove(subscriptionError);
  }

  /**
   * Clears subscription events list.
   */
  public void clearSubscriptionError() {
    onSubscriptionError.clear();
  }

  /**
   * Disconnect events Occurs when a streaming subscription connection is
   * disconnected from the server.
   */
  private List<ISubscriptionErrorDelegate> onDisconnect = new ArrayList<ISubscriptionErrorDelegate>();

  /**
   * Set event to happen when property disconnect.
   *
   * @param disconnect disconnect event
   */
  public void addOnDisconnect(ISubscriptionErrorDelegate disconnect) {
    onDisconnect.add(disconnect);
  }

  /**
   * Remove the event from happening when property disconnect.
   *
   * @param disconnect disconnect event
   */
  public void removeDisconnect(ISubscriptionErrorDelegate disconnect) {
    onDisconnect.remove(disconnect);
  }

  /**
   * Clears disconnect events list.
   */
  public void clearDisconnect() {
    onDisconnect.clear();
  }

  /**
   * Initializes a new instance of the StreamingSubscriptionConnection class.
   *
   * @param service  The ExchangeService instance this connection uses to connect
   *                 to the server.
   * @param lifetime The maximum time, in minutes, the connection will remain open.
   *                 Lifetime must be between 1 and 30.
   * @throws Exception
   */
  public StreamingSubscriptionConnection(ExchangeService service, int lifetime)
      throws Exception {
    EwsUtilities.validateParam(service, "service");

    EwsUtilities.validateClassVersion(service,
        ExchangeVersion.Exchange2010_SP1, this.getClass().getName());

    if (lifetime < 1 || lifetime > 30) {
      throw new ArgumentOutOfRangeException("lifetime");
    }

    this.session = service;
    this.subscriptions = new HashMap<String, StreamingSubscription>();
    this.connectionTimeout = lifetime;
  }

  /**
   * Initializes a new instance of the StreamingSubscriptionConnection class.
   *
   * @param service       The ExchangeService instance this connection uses to connect
   *                      to the server.
   * @param subscriptions Iterable subcriptions
   * @param lifetime      The maximum time, in minutes, the connection will remain open.
   *                      Lifetime must be between 1 and 30.
   * @throws Exception
   */
  public StreamingSubscriptionConnection(ExchangeService service,
      Iterable<StreamingSubscription> subscriptions, int lifetime)
      throws Exception {
    this(service, lifetime);
    EwsUtilities.validateParamCollection(subscriptions.iterator(), "subscriptions");
    for (StreamingSubscription subscription : subscriptions) {
      this.subscriptions.put(subscription.getId(), subscription);
    }
  }

  /**
   * Adds a subscription to this connection.
   *
   * @param subscription The subscription to add.
   * @throws Exception Thrown when AddSubscription is called while connected.
   */
  public void addSubscription(StreamingSubscription subscription)
      throws Exception {
    this.throwIfDisposed();
    EwsUtilities.validateParam(subscription, "subscription");
    this.validateConnectionState(false, "Subscriptions can't be added to an open connection.");

    synchronized (this) {
      if (this.subscriptions.containsKey(subscription.getId())) {
        return;
      }
      this.subscriptions.put(subscription.getId(), subscription);
    }
  }

  /**
   * Removes the specified streaming subscription from the connection.
   *
   * @param subscription The subscription to remove.
   * @throws Exception Thrown when RemoveSubscription is called while connected.
   */
  public void removeSubscription(StreamingSubscription subscription)
      throws Exception {
    this.throwIfDisposed();

    EwsUtilities.validateParam(subscription, "subscription");

    this.validateConnectionState(false, "Subscriptions can't be removed from an open connection.");

    synchronized (this) {
      this.subscriptions.remove(subscription.getId());
    }
  }

  /**
   * Opens this connection so it starts receiving events from the server.This
   * results in a long-standing call to EWS.
   *
   * @throws Exception
   * @throws ServiceLocalException Thrown when Open is called while connected.
   */
  public void open() throws ServiceLocalException, Exception {
    synchronized (this) {
      this.throwIfDisposed();

      this.validateConnectionState(false, "The connection has already opened.");

      if (this.subscriptions.size() == 0) {
        throw new ServiceLocalException(
            "You must add at least one subscription to this connection before it can be opened.");
      }

      this.currentHangingRequest = new GetStreamingEventsRequest(
          this.session, this, this.subscriptions.keySet(),
          this.connectionTimeout);

      this.currentHangingRequest.addOnDisconnectEvent(this);

      this.currentHangingRequest.internalExecute();
    }
  }

  /**
   * Called when the request is disconnected.
   *
   * @param sender The sender.
   * @param args   The Microsoft.Exchange.WebServices.Data.
   *               HangingRequestDisconnectEventArgs instance containing the
   *               event data.
   */
  private void onRequestDisconnect(Object sender,
      HangingRequestDisconnectEventArgs args) {
    this.internalOnDisconnect(args.getException());
  }

  /**
   * Closes this connection so it stops receiving events from the server.This
   * terminates a long-standing call to EWS.
   */
  public void close() {
    synchronized (this) {
      try {
        this.throwIfDisposed();

        this.validateConnectionState(true, "The connection is already closed.");

        // Further down in the stack, this will result in a
        // call to our OnRequestDisconnect event handler,
        // doing the necessary cleanup.
        this.currentHangingRequest.disconnect();
      } catch (Exception e) {
        LOG.error(e);
      }
    }
  }

  /**
   * Internal helper method called when the request disconnects.
   *
   * @param ex The exception that caused the disconnection. May be null.
   */
  private void internalOnDisconnect(Exception ex) {
    if (!onDisconnect.isEmpty()) {
      for (ISubscriptionErrorDelegate disconnect : onDisconnect) {
        disconnect.subscriptionErrorDelegate(this,
            new SubscriptionErrorEventArgs(null, ex));
      }
    }
    this.currentHangingRequest = null;
  }

  /**
   * Gets a value indicating whether this connection is opened
   *
   * @throws Exception
   */
  public boolean getIsOpen() throws Exception {

    this.throwIfDisposed();
    if (this.currentHangingRequest == null) {
      return false;
    } else {
      return this.currentHangingRequest.isConnected();
    }

  }

  /**
   * Validates the state of the connection.
   *
   * @param isConnectedExpected Value indicating whether we expect to be currently connected.
   * @param errorMessage        The error message.
   * @throws Exception
   */
  private void validateConnectionState(boolean isConnectedExpected,
      String errorMessage) throws Exception {
    if ((isConnectedExpected && !this.getIsOpen())
        || (!isConnectedExpected && this.getIsOpen())) {
      throw new ServiceLocalException(errorMessage);
    }
  }

  /**
   * Handles the service response object.
   *
   * @param response The response.
   * @throws ArgumentException
   */
  private void handleServiceResponseObject(Object response)
      throws ArgumentException {
    GetStreamingEventsResponse gseResponse = (GetStreamingEventsResponse) response;

    if (gseResponse == null) {
      throw new ArgumentNullException("GetStreamingEventsResponse must not be null",
                                      "GetStreamingEventsResponse");
    } else {
      if (gseResponse.getResult() == ServiceResult.Success
          || gseResponse.getResult() == ServiceResult.Warning) {
        if (gseResponse.getResults().getNotifications().size() > 0) {
          // We got notification; dole them out.
          this.issueNotificationEvents(gseResponse);
        } else {
          // // This was just a heartbeat, nothing to do here.
        }
      } else if (gseResponse.getResult() == ServiceResult.Error) {
        if (gseResponse.getErrorSubscriptionIds() == null
            || gseResponse.getErrorSubscriptionIds().size() == 0) {
          // General error
          this.issueGeneralFailure(gseResponse);
        } else {
          // subscription-specific errors
          this.issueSubscriptionFailures(gseResponse);
        }
      }
    }
  }

  /**
   * Issues the subscription failures.
   *
   * @param gseResponse The GetStreamingEvents response.
   */
  private void issueSubscriptionFailures(
      GetStreamingEventsResponse gseResponse) {
    ServiceResponseException exception = new ServiceResponseException(
        gseResponse);

    for (String id : gseResponse.getErrorSubscriptionIds()) {
      StreamingSubscription subscription = null;

      synchronized (this) {
        // Client can do any good or bad things in the below event
        // handler
        if (this.subscriptions != null
            && this.subscriptions.containsKey(id)) {
          subscription = this.subscriptions.get(id);
        }

      }
      if (subscription != null) {
        SubscriptionErrorEventArgs eventArgs = new SubscriptionErrorEventArgs(
            subscription, exception);

        if (!onSubscriptionError.isEmpty()) {
          for (ISubscriptionErrorDelegate subError : onSubscriptionError) {
            subError.subscriptionErrorDelegate(this, eventArgs);
          }
        }
      }
      if (gseResponse.getErrorCode() != ServiceError.ErrorMissedNotificationEvents) {
        // Client can do any good or bad things in the above event
        // handler
        synchronized (this) {
          if (this.subscriptions != null
              && this.subscriptions.containsKey(id)) {
            // We are no longer servicing the subscription.
            this.subscriptions.remove(id);
          }
        }
      }
    }
  }

  /**
   * Issues the general failure.
   *
   * @param gseResponse The GetStreamingEvents response.
   */
  private void issueGeneralFailure(GetStreamingEventsResponse gseResponse) {
    SubscriptionErrorEventArgs eventArgs = new SubscriptionErrorEventArgs(
        null, new ServiceResponseException(gseResponse));

    if (!onSubscriptionError.isEmpty()) {
      for (ISubscriptionErrorDelegate subError : onSubscriptionError) {
        subError.subscriptionErrorDelegate(this, eventArgs);
      }
    }
  }

  /**
   * Issues the notification events.
   *
   * @param gseResponse The GetStreamingEvents response.
   */
  private void issueNotificationEvents(GetStreamingEventsResponse gseResponse) {

    for (GetStreamingEventsResults.NotificationGroup events : gseResponse
        .getResults().getNotifications()) {
      StreamingSubscription subscription = null;

      synchronized (this) {
        // Client can do any good or bad things in the below event
        // handler
        if (this.subscriptions != null
            && this.subscriptions
            .containsKey(events.subscriptionId)) {
          subscription = this.subscriptions
              .get(events.subscriptionId);
        }
      }
      if (subscription != null) {
        NotificationEventArgs eventArgs = new NotificationEventArgs(
            subscription, events.events);

        if (!onNotificationEvent.isEmpty()) {
          for (INotificationEventDelegate notifyEvent : onNotificationEvent) {
            notifyEvent.notificationEventDelegate(this, eventArgs);
          }
        }
      }
    }
  }

  /**
   * Frees resources associated with this StreamingSubscriptionConnection.
   */
  public void dispose() {
    synchronized (this) {
      if (!this.isDisposed) {
        if (this.currentHangingRequest != null) {
          this.currentHangingRequest = null;
        }

        this.subscriptions = null;
        this.session = null;

        this.isDisposed = true;
      }
    }
  }

  /**
   * Throws if disposed.
   *
   * @throws Exception
   */
  private void throwIfDisposed() throws Exception {
    if (this.isDisposed) {
      throw new Exception(this.getClass().getName());
    }
  }

  @Override
  public void handleResponseObject(Object response) throws ArgumentException {
    this.handleServiceResponseObject(response);
  }

  @Override
  public void hangingRequestDisconnectHandler(Object sender,
      HangingRequestDisconnectEventArgs args) {
    this.onRequestDisconnect(sender, args);
  }

}
