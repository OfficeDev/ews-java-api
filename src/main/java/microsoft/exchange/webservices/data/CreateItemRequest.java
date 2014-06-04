/**************************************************************************
 * copyright file="CreateItemRequest.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the CreateItemRequest.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/***
 * Represents a CreateItem request.
 * 
 * 
 */
final class CreateItemRequest extends
		CreateItemRequestBase<Item, ServiceResponse> {

	/**
	 * * Initializes a new instance.
	 * 
	 * @param service
	 *            The service.
	 * @param errorHandlingMode
	 *            Indicates how errors should be handled.
	 * @throws Exception 
	 */
	protected CreateItemRequest(ExchangeService service,
			ServiceErrorHandling errorHandlingMode)
			throws Exception {
		super(service, errorHandlingMode);
	}

	/**
	 * * Creates the service response.
	 * 
	 * @param service
	 *            the service
	 * @param responseIndex
	 *            the response index
	 * @return the service response
	 */
	@Override
	protected ServiceResponse createServiceResponse(ExchangeService service,
			int responseIndex) {
		return new CreateItemResponse((Item)EwsUtilities
				.getEnumeratedObjectAt(this.getItems(), responseIndex));
	}

	/**
	 * * Validate request..
	 * 
	 * @throws ServiceLocalException
	 *             the service local exception
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void validate() throws ServiceLocalException, Exception {
		super.validate();
	//	Iterable<Item> items = this.getItems();
		// Validate each item.
		for (Item item : this.getItems()) {
			item.validate();
		}
	}

	/**
	 * * Gets the request version. Returns earliest Exchange version in which
	 * this request is supported.
	 * 
	 * @return the minimum required server version
	 */
	@Override
	protected ExchangeVersion getMinimumRequiredServerVersion() {
		return ExchangeVersion.Exchange2007_SP1;
	}

}
