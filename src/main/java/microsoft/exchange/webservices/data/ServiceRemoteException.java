/**************************************************************************
 * copyright file="ServiceRemoteException.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ServiceRemoteException.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents an error that occurs when a service operation fails remotely.
 * 
 */
public class ServiceRemoteException extends Exception {

	/**
	 * ServiceRemoteException Constructor.
	 */
	public ServiceRemoteException() {
		super();
	}

	/**
	 * ServiceRemoteException Constructor.
	 * 
	 * @param message
	 *            the message
	 */
	public ServiceRemoteException(String message) {
		super(message);
	}

	/**
	 * ServiceRemoteException Constructor.
	 * 
	 * @param message
	 *            the message
	 * @param innerException
	 *            the inner exception
	 */
	public ServiceRemoteException(String message, Exception innerException) {
		super(message, innerException);
	}
}
