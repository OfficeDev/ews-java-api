/**************************************************************************
 * copyright file="AbstractItemIdWrapper.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the AbstractItemIdWrapper.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/***
 * Represents the abstraction of an item Id.
 */
abstract class AbstractItemIdWrapper {

	/**
	 * * Initializes a new instance of the class.
	 */
	protected AbstractItemIdWrapper() {
	}

	/***
	 *Obtains the ItemBase object associated with the wrapper.
	 * 
	 * @return The ItemBase object associated with the wrapper
	 */
	public Item getItem() {
		return null;
	}

	/**
	 * * Writes the Id encapsulated in the wrapper to XML.
	 * 
	 * @param writer
	 *            the writer
	 * @throws Exception
	 *             the exception
	 */
	protected abstract void writeToXml(EwsServiceXmlWriter writer)
			throws Exception;
}
