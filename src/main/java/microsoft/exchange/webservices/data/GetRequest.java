/**************************************************************************
 * copyright file="GetRequest.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the GetRequest.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * * Represents an abstract Get request.
 * 
 * @param <TServiceObject>
 *            the generic type
 * @param <TResponse>
 *            the generic type
 */
abstract class GetRequest<TServiceObject extends ServiceObject, 
		TResponse extends ServiceResponse>
		extends MultiResponseServiceRequest<TResponse> {

	/** The property set. */
	private PropertySet propertySet;

	/**
	 * * Initializes a new instance of the class.
	 * 
	 * @param service
	 *            the service
	 * @param errorHandlingMode
	 *            the error handling mode
	 * @throws Exception 
	 */
	protected GetRequest(ExchangeService service,
			ServiceErrorHandling errorHandlingMode)
			throws Exception {
		super(service, errorHandlingMode);
	}

	/**
	 * * Validate request.
	 * 
	 * @throws ServiceLocalException
	 *             the service local exception
	 * @throws Exception
	 *             the exception
	 */
	protected void validate() throws ServiceLocalException, Exception {
		super.validate();
		EwsUtilities.validateParam(this.propertySet, "PropertySet");
		this.propertySet
				.validateForRequest(this, false /* summaryPropertiesOnly */);
	}

	/***
	 * Gets the type of the service object this request applies to.
	 * 
	 * @return The type of service object the request applies to
	 */
	protected abstract ServiceObjectType getServiceObjectType();

	/**
	 * * Gets the type of the service object this request applies to.
	 * 
	 * @param writer
	 *            the writer
	 * @return The type of service object the request applies to
	 * @throws Exception
	 *             the exception
	 */
	protected void writeElementsToXml(EwsServiceXmlWriter writer)
			throws Exception {
		this.propertySet.writeToXml(writer, this.getServiceObjectType());
	}

	/**
	 * * Gets  the property set.
	 * 
	 * @return the property set
	 */
	public PropertySet getPropertySet() {
		return this.propertySet;
	}

	/**
	 * Sets the property set.
	 * 
	 * @param propertySet
	 *            the new property set
	 */
	public void setPropertySet(PropertySet propertySet) {
		this.propertySet = propertySet;
	}
}
