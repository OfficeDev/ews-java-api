/**************************************************************************
 * copyright file="GetItemRequestForLoad.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the GetItemRequestForLoad.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/***
 * Represents a GetItem request specialized to return ServiceResponse.
 */
final class GetItemRequestForLoad extends GetItemRequestBase<ServiceResponse> {

	/**
	 * * Initializes a new instance of the class.
	 * 
	 * @param service
	 *            the service
	 * @param errorHandlingMode
	 *            the error handling mode
	 * @throws Exception 
	 */
	protected GetItemRequestForLoad(ExchangeService service,
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
	 * @return Service response.
	 */
	@Override
	protected ServiceResponse createServiceResponse(ExchangeService service,
			int responseIndex) {
		return new GetItemResponse(this.getItemIds().getItemIdWrapperList(
				responseIndex), this.getPropertySet());

	}
}
