/**************************************************************************
 * copyright file="NotificationEventArgs.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the NotificationEventArgs class.
 **************************************************************************/
package microsoft.exchange.webservices.data;


/**
 * Provides data to a StreamingSubscriptionConnection's 
 * OnNotificationEvent event.
 */
public class NotificationEventArgs { 
	


	private StreamingSubscription subscription;
	private Iterable<NotificationEvent> events;
	/**
	 * Initializes a new instance of the NotificationEventArgs class.
	 * @param subscription The subscription for which notifications have been received.
	 * @param events The events that were received.
	 */
	protected NotificationEventArgs(
			StreamingSubscription subscription,
			Iterable<NotificationEvent> events) {
		this.setSubscription(subscription);
		this.setEvents(events);
	}

	/**
	 * Gets the subscription for which notifications have been received.
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
