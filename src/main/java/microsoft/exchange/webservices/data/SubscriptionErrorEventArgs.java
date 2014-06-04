/**************************************************************************
 * copyright file="SubscriptionErrorEventArgs.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the SubscriptionErrorEventArgs class.
 **************************************************************************/
package microsoft.exchange.webservices.data;


/**
 * Provides data to a StreamingSubscriptionConnection's 
 * OnSubscriptionError and OnDisconnect events.
 */
public class SubscriptionErrorEventArgs { //TODO extends EventObject {

	private StreamingSubscription subscription;
	private Exception exception;

	/**
	 *     Initializes a new instance of the SubscriptionErrorEventArgs class.
	 *     @param subscription The subscription for which an error occurred.
	 *      If subscription is null, the error applies to the entire connection.
	 *     @param exception The exception representing the error.
	 *      If exception is null, the connection 
	 *      was cleanly closed by the server.
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
	protected  void setSubscription(StreamingSubscription value) {
		this.subscription = value;

	}
	
	/**
	 * Gets the exception representing the error. If Exception is null,
	 *  the connection was cleanly closed by the server.
	 */
	public Exception getException() {
		return this.exception;

	}
	/**
	 * Sets the exception representing the error. If Exception is null,
	 *  the connection was cleanly closed by the server.
	 */
	protected  void setException(Exception value) {
		this.exception = value;

	}
}
