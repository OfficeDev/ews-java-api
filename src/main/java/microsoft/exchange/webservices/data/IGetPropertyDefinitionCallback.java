/**************************************************************************
 * copyright file="IGetPropertyDefinitionCallback.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the IGetPropertyDefinitionCallback.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * The Interface GetPropertyDefinitionCallbackInterface.
 */
interface IGetPropertyDefinitionCallback {

	/**
	 * Gets the property definition callback.
	 * 
	 * @param version
	 *            the version
	 * @return the property definition callback
	 */
	PropertyDefinition getPropertyDefinitionCallback(ExchangeVersion version);
}