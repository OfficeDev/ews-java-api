/**************************************************************************
 * copyright file="IComplexPropertyChangedDelegate.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the IComplexPropertyChangedDelegate.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * The Interface ComplexPropertyChangedDelegateInterface.
 */
interface IComplexPropertyChangedDelegate {

	/**
	 * Complex property changed.
	 * 
	 * @param complexProperty
	 *            the complex property
	 */
	void complexPropertyChanged(ComplexProperty complexProperty);
}
