package microsoft.exchange.webservices.data;

/**
 * Represents a UpdateUserConfiguration request.
 */
public class UpdateUserConfigurationRequest extends
    MultiResponseServiceRequest<ServiceResponse> {

  /**
   * The user configuration.
   */
  protected UserConfiguration userConfiguration;

  /**
   * Validate request.
   *
   * @throws microsoft.exchange.webservices.data.ServiceLocalException the service local exception
   * @throws Exception                                                 the exception
   */
  @Override
  protected void validate() throws ServiceLocalException, Exception {
    super.validate();
    EwsUtilities.validateParam(this.userConfiguration, "userConfiguration");
  }

  /**
   * Creates the service response.
   *
   * @param service       the service
   * @param responseIndex the response index
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
    return XmlElementNames.UpdateUserConfiguration;
  }

  /**
   * Gets the name of the response XML element.
   *
   * @return XML element name
   */
  @Override
  protected String getResponseXmlElementName() {
    return XmlElementNames.UpdateUserConfigurationResponse;
  }

  /**
   * Gets the name of the response message XML element.
   *
   * @return XML element name
   */
  @Override
  protected String getResponseMessageXmlElementName() {
    return XmlElementNames.UpdateUserConfigurationResponseMessage;
  }

  /**
   * Writes XML elements.
   *
   * @param writer the writer
   * @throws Exception the exception
   */
  @Override
  protected void writeElementsToXml(EwsServiceXmlWriter writer)
      throws Exception {
    // Write UserConfiguation element
    this.userConfiguration.writeToXml(writer, XmlNamespace.Messages,
        XmlElementNames.UserConfiguration);
  }

  /**
   * Initializes a new instance of the class.
   *
   * @param service the service
   * @throws Exception
   */
  protected UpdateUserConfigurationRequest(ExchangeService service)
      throws Exception {
    super(service, ServiceErrorHandling.ThrowOnError);
  }

  /**
   * Gets the user configuration. <value>The user
   * configuration.</value>
   *
   * @return the user configuration
   */
  public UserConfiguration getUserConfiguration() {
    return this.userConfiguration;
  }

  /**
   * Sets the user configuration.
   *
   * @param userConfiguration the new user configuration
   */
  public void setUserConfiguration(UserConfiguration userConfiguration) {
    this.userConfiguration = userConfiguration;
  }
}
