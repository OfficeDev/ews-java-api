/**************************************************************************
 * copyright file="CreateUserConfigurationRequest.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the CreateUserConfigurationRequest.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents a CreateUserConfiguration request.
 * 
 */
class CreateUserConfigurationRequest extends
		MultiResponseServiceRequest<ServiceResponse> {

	/** The user configuration. */
	protected UserConfiguration userConfiguration;

	/**
	 * Validate request.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void validate() throws Exception {
		super.validate();
		EwsUtilities.validateParam(this.userConfiguration, "userConfiguration");
	}

	/**
	 * Creates the service response.
	 * 
	 * @param service
	 *            The service.
	 * @param responseIndex
	 *            Index of the response.
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
	 * @return XML element name,
	 */
	@Override
	protected String getXmlElementName() {
		return XmlElementNames.CreateUserConfiguration;
	}

	/**
	 * Gets the name of the response XML element.
	 * 
	 * @return XML element name,
	 */
	@Override
	protected String getResponseXmlElementName() {
		return XmlElementNames.CreateUserConfigurationResponse;
	}

	/**
	 * Gets the name of the response message XML element.
	 * 
	 * @return XML element name,
	 */
	@Override
	protected String getResponseMessageXmlElementName() {
		return XmlElementNames.CreateUserConfigurationResponseMessage;
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
		// Write UserConfiguation element
		this.userConfiguration.writeToXml(writer, XmlNamespace.Messages,
				XmlElementNames.UserConfiguration);
	}

	/**
	 * Initializes a new instance of the <see
	 * cref="CreateUserConfigurationRequest"/> class.
	 * 
	 * @param service
	 *            The service.
	 * @throws Exception 
	 */
	protected CreateUserConfigurationRequest(ExchangeService service)
			throws Exception {
		super(service, ServiceErrorHandling.ThrowOnError);
	}

	/**
	 * Gets  the user configuration.
	 * 
	 * @return The userConfiguration.
	 */
	public UserConfiguration getUserConfiguration() {
		return this.userConfiguration;

	}

	/**
	 * Sets the user configuration.
	 * 
	 * @param value
	 *            the new user configuration
	 */
	public void setUserConfiguration(UserConfiguration value) {
		this.userConfiguration = value;
	}
}
