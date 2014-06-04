/**************************************************************************
 * copyright file="BasePropertySet.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the BasePropertySet.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/***
 * Defines base property sets that are used as the base for custom property
 * sets.
 */
public enum BasePropertySet {

	// Only includes the Id of items and folders.
	/** The Id only. */
	IdOnly("IdOnly"),

	// Includes all the first class properties of items and folders.
	/** The First class properties. */
	FirstClassProperties("AllProperties");

	/** The base shape value. */
	private String baseShapeValue;

	/**
	 * Instantiates a new base property set.
	 * 
	 * @param baseShapeValue
	 *            the base shape value
	 */
	BasePropertySet(String baseShapeValue) {
		this.baseShapeValue = baseShapeValue;
	}

	/**
	 * Gets the base shape value.
	 * 
	 * @return the base shape value
	 */
	public String getBaseShapeValue() {
		return this.baseShapeValue;
	}

}
