/**************************************************************************
 * copyright file="ArgumentOutOfRangeException.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ArgumentOutOfRangeException.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * The Class ArgumentOutOfRangeException.
 */
public class ArgumentOutOfRangeException extends Exception {

	/**
	 * Instantiates a new argument out of range exception.
	 */
	public ArgumentOutOfRangeException() {
		super();
		
	}

	/**
	 * Instantiates a new argument out of range exception.
	 * 
	 * @param arg0
	 *            the arg0
	 */
	public ArgumentOutOfRangeException(final String arg0) {
		super(arg0);
		
	}

	/**
	 * Instantiates a new argument out of range exception.
	 * 
	 * @param arg0
	 *            the arg0
	 * @param arg1
	 *            the arg1
	 */
	public ArgumentOutOfRangeException(final String arg0, final String arg1) {

	}
}
