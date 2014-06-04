/**************************************************************************
 * copyright file="ServiceValidationException.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ServiceValidationException.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents an error that occurs when a validation check fails.
 * 
 */
public final class ServiceValidationException extends ServiceLocalException {

	/**
	 * ServiceValidationException Constructor.
	 */
	public ServiceValidationException() {
		super();
	}

	/**
	 * ServiceValidationException Constructor.
	 * 
	 * @param message
	 *            the message
	 */
	public ServiceValidationException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new service validation exception.
	 * 
	 * @param message
	 *            the message
	 * @param innerException
	 *            the inner exception
	 */
	public ServiceValidationException(String message,
			Exception innerException) {
		super(message, innerException);

	}

}
