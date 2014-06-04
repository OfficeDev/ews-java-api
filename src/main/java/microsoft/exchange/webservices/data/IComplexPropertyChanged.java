/**************************************************************************
 * copyright file="IComplexPropertyChanged.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the IComplexPropertyChanged.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/***
 * Indicates that a complex property changed.
 * 
 */
interface IComplexPropertyChanged {
	/***
	 * Indicates that a complex property changed.
	 * 
	 * @param complexProperty
	 *            Complex property.
	 */
	void complexPropertyChanged(ComplexProperty complexProperty);

}
