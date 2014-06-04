/**************************************************************************
 * copyright file="PushSubscription.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the PushSubscription.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents a push subscriptions..
 * 
 */
public final class PushSubscription extends SubscriptionBase {

	/**
	 * Initializes a new instance.
	 * 
	 * @param service
	 *            the service
	 * @throws Exception
	 *             the exception
	 */
	protected PushSubscription(ExchangeService service) throws Exception {
		super(service);
	}
}
