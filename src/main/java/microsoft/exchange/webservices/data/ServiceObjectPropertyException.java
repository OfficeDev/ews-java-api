/**************************************************************************
 * copyright file="ServiceObjectPropertyException.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ServiceObjectPropertyException.java.
 **************************************************************************/

package microsoft.exchange.webservices.data;

/***
 * Represents an error that occurs when an operation on a property fails.
 * 
 * 
 */
public class ServiceObjectPropertyException extends PropertyException {

	/** The property definition. */
	private PropertyDefinitionBase propertyDefinition;

	/***
	 * ServiceObjectPropertyException constructor.
	 * 
	 * @param propertyDefinition
	 *            The definition of the property that is at the origin of the
	 *            exception.
	 */
	public ServiceObjectPropertyException(
			PropertyDefinitionBase propertyDefinition) {
		super(propertyDefinition.getPrintableName());
		this.propertyDefinition = propertyDefinition;
	}

	/***
	 * ServiceObjectPropertyException constructor.
	 * 
	 * @param message
	 *            Error message text.
	 * @param propertyDefinition
	 *            The definition of the property that is at the origin of the
	 *            exception.
	 */
	public ServiceObjectPropertyException(String message,
			PropertyDefinitionBase propertyDefinition) {
		super(message, propertyDefinition.getPrintableName());
		this.propertyDefinition = propertyDefinition;
	}

	/**
	 * * ServiceObjectPropertyException constructor.
	 * 
	 * @param message
	 *            Error message text.
	 * @param propertyDefinition
	 *            The definition of the property that is at the origin of the
	 *            exception.
	 * @param innerException
	 *            the inner exception
	 */
	public ServiceObjectPropertyException(String message,
			PropertyDefinitionBase propertyDefinition, 
			Exception innerException) {
		super(message, propertyDefinition.getPrintableName(), innerException);
		this.propertyDefinition = propertyDefinition;
	}

	/***
	 * The definition of the property that is at the origin of the exception.
	 * 
	 * @return The definition of the property.
	 */
	public PropertyDefinitionBase getPropertyDefinition() {
		return propertyDefinition;
	}

}
