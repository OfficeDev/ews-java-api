/**************************************************************************
 * copyright file="MoveCopyFolderRequest.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the MoveCopyFolderRequest.java.
 **************************************************************************/

package microsoft.exchange.webservices.data;

/**
 * 
 * Represents an abstract Move/Copy Folder request.
 * 
 * @param <TResponse>
 *            The type of response
 */
abstract class MoveCopyFolderRequest<TResponse extends ServiceResponse> extends
		MoveCopyRequest<Folder, TResponse> {

	/** The folder ids. */
	private FolderIdWrapperList folderIds = new FolderIdWrapperList();

	/**
	 * Validates request.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void validate() throws Exception {
		super.validate();
		EwsUtilities.validateParamCollection(this.getFolderIds().iterator(),
				"FolderIds");
		this.getFolderIds().validate(
				this.getService().getRequestedServerVersion());
	}

	/**
	 * Initializes a new instance of the <see
	 * cref="MoveCopyFolderRequest&lt;TResponse&gt;"/> class.
	 * 
	 * @param service
	 *            The service.
	 * @param errorHandlingMode
	 *            Indicates how errors should be handled.
	 * @throws Exception 
	 */
	protected MoveCopyFolderRequest(ExchangeService service,
			ServiceErrorHandling errorHandlingMode)
			throws Exception {
		super(service, errorHandlingMode);
	}

	/**
	 * Writes the ids as XML.
	 * 
	 * @param writer
	 *            the writer
	 */
	@Override
	protected void writeIdsToXml(EwsServiceXmlWriter writer) {
		try {
			this.folderIds.writeToXml(writer, XmlNamespace.Messages,
					XmlElementNames.FolderIds);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets the expected response message count.
	 * 
	 * @return Number of expected response messages.
	 */
	@Override
	protected int getExpectedResponseMessageCount() {
		return this.getFolderIds().getCount();
	}

	/**
	 * Gets the folder ids.
	 * 
	 * @return The folder ids.
	 */
	protected FolderIdWrapperList getFolderIds() {
		return this.folderIds;
	}

}
