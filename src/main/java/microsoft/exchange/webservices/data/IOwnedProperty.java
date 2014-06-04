/**************************************************************************
 * copyright file="IOwnedProperty.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the IOwnedProperty.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/***
 * Complex properties that implement that interface are owned by an instance of
 * EwsObject. For this reason, they also cannot be shared.
 * 
 */
interface IOwnedProperty {

	/***
	 * Gets the owner.
	 * 
	 * @return The owner.
	 */
	ServiceObject getOwner();

	/***
	 * Sets the owner.
	 * 
	 * @param obj
	 *            The owner.
	 */
	void setOwner(ServiceObject obj);
}
