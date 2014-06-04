/**************************************************************************
 * copyright file="GetFolderRequest.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the GetFolderRequest.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents a GetFolder request.
 * 
 */
final class GetFolderRequest extends GetFolderRequestBase<GetFolderResponse> {

	// private FolderIdWrapperList folderIds = new FolderIdWrapperList();

	/**
	 * Initializes a new instance of the GetFolderRequest class.
	 * 
	 * @param service
	 *            the service
	 * @param errorHandlingMode
	 *            Indicates how errors should be handled.
	 * @throws Exception 
	 */
	protected GetFolderRequest(ExchangeService service,
			ServiceErrorHandling errorHandlingMode)
			throws Exception {
		super(service, errorHandlingMode);
	}

	/**
	 * Creates the service response.
	 * 
	 * @param service
	 *            The service
	 * @param responseIndex
	 *            Index of the response.
	 * @return Service response.
	 */
	@Override
	protected GetFolderResponse createServiceResponse(ExchangeService service,
			int responseIndex) {
		return new GetFolderResponse(this.getFolderIds()
				.getFolderIdWrapperList(responseIndex).getFolder(), this
				.getPropertySet());
	}

}
