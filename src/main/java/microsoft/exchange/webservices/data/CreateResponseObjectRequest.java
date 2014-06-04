/**************************************************************************
 * copyright file="CreateResponseObjectRequest.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the CreateResponseObjectRequest.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 *Represents a CreateItem request for a response object.
 */
final class CreateResponseObjectRequest extends
		CreateItemRequestBase<ServiceObject, CreateResponseObjectResponse> {

	/**
	 * Initializes a new instance of the CreateResponseObjectRequest class.
	 * 
	 * @param service
	 *            The Service
	 * @param errorHandlingMode
	 *            Indicates how errors should be handled.
	 * @throws Exception 
	 */
	CreateResponseObjectRequest(ExchangeService service,
			ServiceErrorHandling errorHandlingMode)
			throws Exception {
		super(service, errorHandlingMode);
	}

	/**
	 * Creates the service response.
	 * 
	 * @param service
	 *            the service
	 * @param responseIndex
	 *            the response index
	 * @return Service object.
	 */
	@Override
	protected CreateResponseObjectResponse createServiceResponse(
			ExchangeService service, int responseIndex) {
		return new CreateResponseObjectResponse();
	}

	/**
	 * Gets the request version.
	 * 
	 * @return Earliest Exchange version in which this request is supported.
	 */
	@Override
	protected ExchangeVersion getMinimumRequiredServerVersion() {
		return ExchangeVersion.Exchange2007_SP1;
	}
}
