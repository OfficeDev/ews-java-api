/**************************************************************************
 * copyright file="Param.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the Param.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * The Class Param.
 * 
 * @param <T>
 *            the generic type
 */
 abstract class Param<T> {

	/** The param. */
	private T param;

	/**
	 * Gets the param.
	 * 
	 * @return the param
	 */
	public T getParam() {
		return param;
	}

	/**
	 * Sets the param.
	 * 
	 * @param param
	 *            the new param
	 */
	public void setParam(T param) {
		this.param = param;
	}

}
