/**************************************************************************
 * copyright file="ArgumentNullException.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ArgumentNullException.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * The Class ArgumentNullException.
 */
public class ArgumentNullException extends Exception {

	/**
	 * Instantiates a new argument null exception.
	 */
	public ArgumentNullException() {
		super();
		
	}

	/**
	 * Instantiates a new argument null exception.
	 * 
	 * @param arg0
	 *            the arg0
	 * @param arg1
	 *            the arg1
	 */
	public ArgumentNullException(final String arg0, final Throwable arg1) {
		super(arg0, arg1);
		
	}

	/**
	 * Instantiates a new argument null exception.
	 * 
	 * @param arg0
	 *            the arg0
	 */
	public ArgumentNullException(final String arg0) {
		super(arg0);
	
	}

	/**
	 * Instantiates a new argument null exception.
	 * 
	 * @param arg0
	 *            the arg0
	 */
	public ArgumentNullException(final Throwable arg0) {
		super(arg0);
	
	}
}
