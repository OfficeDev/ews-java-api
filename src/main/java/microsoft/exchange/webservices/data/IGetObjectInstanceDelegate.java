/**************************************************************************
 * copyright file="IGetObjectInstanceDelegate.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the IGetObjectInstanceDelegate.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * The Interface GetObjectInstanceDelegateInterface.
 * 
 * @param <T>
 *            the generic type
 */
interface IGetObjectInstanceDelegate<T extends ServiceObject> {

	/**
	 * Gets the object instance delegate.
	 * 
	 * @param service
	 *            the service
	 * @param xmlElementName
	 *            the xml element name
	 * @return the object instance delegate
	 * @throws Exception
	 *             the exception
	 */
	T getObjectInstanceDelegate(ExchangeService service, String xmlElementName)
			throws Exception;
}
