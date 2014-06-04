/**************************************************************************
 * copyright file="AutodiscoverRemoteException.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the AutodiscoverRemoteException.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents an exception that is thrown when the Autodiscover service returns
 * an error.
 */
public class AutodiscoverRemoteException extends ServiceRemoteException {

	/** The error. */
	private AutodiscoverError error;

	/**
	 * Initializes a new instance of the class.
	 * 
	 * @param error
	 *            the error
	 */
	public AutodiscoverRemoteException(AutodiscoverError error) {
		super();
		this.error = error;
	}

	/**
	 * Initializes a new instance of the class.
	 * 
	 * @param message
	 *            the message
	 * @param error
	 *            the error
	 */
	protected AutodiscoverRemoteException(String message,
			AutodiscoverError error) {
		this.error = error;
	}

	/**
	 * Initializes a new instance of the class.
	 * 
	 * @param message
	 *            the message
	 * @param error
	 *            the error
	 * @param innerException
	 *            the inner exception
	 */
	public AutodiscoverRemoteException(String message, AutodiscoverError error,
			Exception innerException) {
		super(message, innerException);
		this.error = error;
	}

	/**
	 * Gets the error. <value>The error.</value>
	 * 
	 * @return the error
	 */
	public AutodiscoverError getError() {
		return this.error;
	}
}
