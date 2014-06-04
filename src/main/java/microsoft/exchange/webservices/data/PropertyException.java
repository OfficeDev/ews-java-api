/**************************************************************************
 * copyright file="PropertyException.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the PropertyException.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents an error that occurs when an operation on a property fails.
 * 
 */
public class PropertyException extends ServiceLocalException {

	/** The name. */
	private String name;

	/**
	 * Instantiates a new property exception.
	 */
	public PropertyException() {
		super();
	}

	/**
	 * Instantiates a new property exception.
	 * 
	 * @param name
	 *            the name
	 */
	public PropertyException(String name) {
		super();
		this.name = name;
	}

	/**
	 * Instantiates a new property exception.
	 * 
	 * @param message
	 *            the message
	 * @param name
	 *            the name
	 */
	public PropertyException(String message, String name) {
		super(message);
		this.name = name;
	}

	/**
	 * Instantiates a new property exception.
	 * 
	 * @param message
	 *            the message
	 * @param name
	 *            the name
	 * @param innerException
	 *            the inner exception
	 */
	public PropertyException(String message, String name,
			Exception innerException) {
		super(message, innerException);
		this.name = name;
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

}
