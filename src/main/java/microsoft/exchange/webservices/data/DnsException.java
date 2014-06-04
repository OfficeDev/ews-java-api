/**************************************************************************
 * copyright file="DnsException.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the DnsException.java.
 **************************************************************************/

package microsoft.exchange.webservices.data;

/**
 * Defines DnsException class.
 */
class DnsException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The exception. */
	String exception;

	/**
	 * Instantiates a new dns exception.
	 * 
	 * @param exceptionMessage
	 *            the exception message
	 */
	protected DnsException(String exceptionMessage) {
		exception = exceptionMessage;

	}

	/**
	 * Gets the error.
	 * 
	 * @return the error
	 */
	protected String getError() {
		return exception;
	}
}
