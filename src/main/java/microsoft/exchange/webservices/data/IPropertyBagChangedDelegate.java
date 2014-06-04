/**************************************************************************
 * copyright file="IPropertyBagChangedDelegate.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the IPropertyBagChangedDelegate.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * The Interface PropertyBagChangedDelegateInterface.
 * 
 * @param <TKey>
 *            the generic type
 */ 

 interface IPropertyBagChangedDelegate<TKey> {
	/**
	 * Property bag changed.
	 * 
	 * @param simplePropertyBag
	 *            the simple property bag
	 */
	void propertyBagChanged(SimplePropertyBag<TKey> simplePropertyBag);
}
