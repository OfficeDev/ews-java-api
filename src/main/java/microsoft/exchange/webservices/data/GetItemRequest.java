/**************************************************************************
 * copyright file="GetItemRequest.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the GetItemRequest.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/***
 * Represents an abstract GetItem request.
 */
final class GetItemRequest extends GetItemRequestBase<GetItemResponse> {

	/**
	 * * Initializes a new instance of the class.
	 * 
	 * @param service
	 *            the service
	 * @param errorHandlingMode
	 *            the error handling mode
	 * @throws Exception 
	 */
	protected GetItemRequest(ExchangeService service,
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
	 * @return Service response
	 */
	protected GetItemResponse createServiceResponse(ExchangeService service,
			int responseIndex) {
		return new GetItemResponse(this.getItemIds().getItemIdWrapperList(
				responseIndex), this.getPropertySet());
	}

}
