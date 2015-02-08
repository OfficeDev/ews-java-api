package microsoft.exchange.webservices.data;

import javax.xml.stream.XMLStreamException;

/**
 * Represents a response to a GetUserConfiguration request.
 */
public final class GetUserConfigurationResponse extends ServiceResponse {

  /**
   * The user configuration.
   */
  private UserConfiguration userConfiguration;

  /**
   * Initializes a new instance of the class.
   *
   * @param userConfiguration the user configuration
   */
  protected GetUserConfigurationResponse(
      UserConfiguration userConfiguration) {
    super();
    EwsUtilities.EwsAssert(userConfiguration != null,
        "GetUserConfigurationResponse.ctor",
        "userConfiguration is null");

    this.userConfiguration = userConfiguration;
  }

  /**
   * Reads response elements from XML.
   *
   * @param reader the reader
   * @throws ServiceXmlDeserializationException                        the service xml deserialization exception
   * @throws javax.xml.stream.XMLStreamException                       the xML stream exception
   * @throws InstantiationException                                    the instantiation exception
   * @throws IllegalAccessException                                    the illegal access exception
   * @throws microsoft.exchange.webservices.data.ServiceLocalException the service local exception
   * @throws Exception                                                 the exception
   */
  @Override
  protected void readElementsFromXml(EwsServiceXmlReader reader)
      throws ServiceXmlDeserializationException, XMLStreamException,
      InstantiationException, IllegalAccessException,
      ServiceLocalException, Exception {
    super.readElementsFromXml(reader);
    this.userConfiguration.loadFromXml(reader);
  }

  /**
   * Gets the user configuration that was created.
   *
   * @return the user configuration
   */
  public UserConfiguration getUserConfiguration() {
    return this.userConfiguration;
  }
}
