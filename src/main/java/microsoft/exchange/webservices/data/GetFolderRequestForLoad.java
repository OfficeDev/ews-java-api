/**************************************************************************
 * copyright file="GetFolderRequestForLoad.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the GetFolderRequestForLoad.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents a GetFolder request specialized to return ServiceResponse.
 * 
 */
final class GetFolderRequestForLoad extends
		GetFolderRequestBase<ServiceResponse> {

	/**
	 * Initializes a new instance of the GetFolderRequestForLoad class.
	 * 
	 * @param exchangeService
	 *            the exchange service
	 * @param throwonerror
	 *            the throwonerror
	 * @throws Exception 
	 */
	protected GetFolderRequestForLoad(ExchangeService exchangeService,
			ServiceErrorHandling throwonerror) throws Exception {
		super(exchangeService, throwonerror);
	}

	/**
	 * Creates the service response.
	 * 
	 * @param service
	 *            The Service
	 * @param responseIndex
	 *            the response index
	 * @return Service response.
	 */
	@Override
	protected ServiceResponse createServiceResponse(ExchangeService service,
			int responseIndex) {
		return new GetFolderResponse(this.getFolderIds()
				.getFolderIdWrapperList(responseIndex).getFolder(), this
				.getPropertySet());
	}

}
