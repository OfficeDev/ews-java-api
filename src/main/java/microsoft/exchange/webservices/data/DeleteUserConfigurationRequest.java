/**************************************************************************
 * copyright file="DeleteUserConfigurationRequest.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the DeleteUserConfigurationRequest.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents a DeleteUserConfiguration request.
 */
class DeleteUserConfigurationRequest extends
		MultiResponseServiceRequest<ServiceResponse> {

	/** The name. */
	private String name;

	/** The parent folder id. */
	private FolderId parentFolderId;

	/**
	 * Validate request.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void validate() throws Exception {
		super.validate();
		EwsUtilities.validateParam(this.name, "name");
		EwsUtilities.validateParam(this.parentFolderId, "parentFolderId");
		this.getParentFolderId().validate(
				this.getService().getRequestedServerVersion());
	}

	/**
	 * Creates the service response.
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
		return new ServiceResponse();
	}

	/**
	 * Gets the request version.
	 * 
	 * @return Earliest Exchange version in which this request is supported.
	 */
	@Override
	protected ExchangeVersion getMinimumRequiredServerVersion() {
		return ExchangeVersion.Exchange2010;
	}

	/**
	 * Gets the expected response message count.
	 * 
	 * @return Number of expected response messages.
	 */
	@Override
	protected int getExpectedResponseMessageCount() {
		return 1;
	}

	/**
	 * Gets the name of the XML element.
	 * 
	 * @return XML element name
	 */
	@Override
	protected String getXmlElementName() {
		return XmlElementNames.DeleteUserConfiguration;
	}

	/**
	 * Gets the name of the response XML element.
	 * 
	 * @return XML element name
	 */
	@Override
	protected String getResponseXmlElementName() {
		return XmlElementNames.DeleteUserConfigurationResponse;
	}

	/**
	 * Gets the name of the response message XML element.
	 * 
	 * @return XML element name
	 */
	@Override
	protected String getResponseMessageXmlElementName() {
		return XmlElementNames.DeleteUserConfigurationResponseMessage;
	}

	/**
	 * Writes XML elements.
	 * 
	 * @param writer
	 *            the writer
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void writeElementsToXml(EwsServiceXmlWriter writer)
			throws Exception {
		// Write UserConfiguationName element
		UserConfiguration.writeUserConfigurationNameToXml(writer,
				XmlNamespace.Messages, this.name, this.parentFolderId);
	}

	/**
	 * Initializes a new instance of the class.
	 * 
	 * @param service
	 *            the service
	 * @throws Exception 
	 */
	protected DeleteUserConfigurationRequest(ExchangeService service)
			throws Exception {
		super(service, ServiceErrorHandling.ThrowOnError);
	}

	/**
	 * Gets  the name. <value>The name.</value>
	 * 
	 * @return the name
	 */
	protected String getName() {
		return this.name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name
	 *            the new name
	 */
	protected void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets  the parent folThe parent folder Id. <value>The parent folder
	 * Id.</value>
	 * 
	 * @return the parent folder id
	 */
	protected FolderId getParentFolderId() {
		return this.parentFolderId;
	}

	/**
	 * Sets the parent folder id.
	 * 
	 * @param parentFolderId
	 *            the new parent folder id
	 */
	protected void setParentFolderId(FolderId parentFolderId) {
		this.parentFolderId = parentFolderId;
	}
}
