/**************************************************************************
 * copyright file="FormatException.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the FormatException.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * The Class FormatException.
 */
public class FormatException extends Exception {

	/**
	 * Instantiates a new format exception.
	 */
	public FormatException() {
		super();
		
	}

	/**
	 * Instantiates a new format exception.
	 * 
	 * @param arg0
	 *            the arg0
	 * @param arg1
	 *            the arg1
	 */
	public FormatException(final String arg0, final Throwable arg1) {
		super(arg0, arg1);
		
	}

	/**
	 * Instantiates a new format exception.
	 * 
	 * @param arg0
	 *            the arg0
	 */
	public FormatException(final String arg0) {
		super(arg0);
		
	}

	/**
	 * Instantiates a new format exception.
	 * 
	 * @param arg0
	 *            the arg0
	 */
	public FormatException(final Throwable arg0) {
		super(arg0);
	
	}

}
