/**************************************************************************
 * copyright file="GetFolderRequestBase.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the GetFolderRequestBase.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * * Represents an abstract GetFolder request.
 * 
 * @param <TResponse>
 *            the generic type
 */
abstract class GetFolderRequestBase<TResponse extends ServiceResponse> extends
		GetRequest<Folder, TResponse> {

	/** The folder ids. */
	private FolderIdWrapperList folderIds = new FolderIdWrapperList();

	/**
	 * * Initializes a new instance of the class.
	 * 
	 * @param service
	 *            the service
	 * @param errorHandlingMode
	 *            the error handling mode
	 * @throws Exception 
	 */
	protected GetFolderRequestBase(ExchangeService service,
			ServiceErrorHandling errorHandlingMode)
			throws Exception {
		super(service, errorHandlingMode);
	}

	/**
	 * * Validate request.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	protected void validate() throws Exception {
		super.validate();
		EwsUtilities.validateParamCollection(this.getFolderIds().iterator(),
				"FolderIds");
		this.getFolderIds().validate(
				this.getService().getRequestedServerVersion());
	}

	/***
	 * Gets the expected response message count.
	 * 
	 * @return Number of expected response messages
	 */
	protected int getExpectedResponseMessageCount() {
		return this.getFolderIds().getCount();
	}

	/***
	 * Gets the type of the service object this request applies to.
	 * 
	 * @return The type of service object the request applies to
	 */
	protected ServiceObjectType getServiceObjectType() {
		return ServiceObjectType.Folder;
	}

	/**
	 * * Writes XML elements.
	 * 
	 * @param writer
	 *            the writer
	 * @throws Exception
	 *             the exception
	 */
	protected void writeElementsToXml(EwsServiceXmlWriter writer)
			throws Exception {
		super.writeElementsToXml(writer);
		this.getFolderIds().writeToXml(writer, XmlNamespace.Messages,
				XmlElementNames.FolderIds);
	}

	/***
	 * Gets the name of the XML element.
	 * 
	 * @return XML element name
	 */
	protected String getXmlElementName() {
		return XmlElementNames.GetFolder;
	}

	/***
	 * Gets the name of the response XML element.
	 * 
	 * @return XML element name
	 */
	protected String getResponseXmlElementName() {
		return XmlElementNames.GetFolderResponse;
	}

	/***
	 * Gets the name of the response message XML element.
	 * 
	 * @return XML element name
	 */
	protected String getResponseMessageXmlElementName() {
		return XmlElementNames.GetFolderResponseMessage;
	}

	/***
	 * Gets the request version.
	 * 
	 * @return Earliest Exchange version in which this request is supported
	 */
	protected ExchangeVersion getMinimumRequiredServerVersion() {
		return ExchangeVersion.Exchange2007_SP1;
	}

	/**
	 * * Gets the folder ids.
	 * 
	 * @return the folder ids
	 */
	public FolderIdWrapperList getFolderIds() {
		return this.folderIds;
	}

}
