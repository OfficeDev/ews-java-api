/**************************************************************************
 Exchange Web Services Java API
 Copyright (c) Microsoft Corporation
 All rights reserved.
 MIT License
 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in
 all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 THE SOFTWARE.
 **************************************************************************/

package microsoft.exchange.webservices.data;

import java.util.EnumSet;

/**
 * The Class GetUserConfigurationRequest.
 */
class GetUserConfigurationRequest extends
    MultiResponseServiceRequest<GetUserConfigurationResponse> {

  /**
   * The name.
   */
  private String name;

  /**
   * The parent folder id.
   */
  private FolderId parentFolderId;

  /**
   * The properties.
   */
  private EnumSet<UserConfigurationProperties> properties;

  /**
   * The user configuration.
   */
  private UserConfiguration userConfiguration;

  /**
   * Validate request.
   *
   * @throws microsoft.exchange.webservices.data.ServiceLocalException the service local exception
   * @throws Exception                                                 the exception
   */
  @Override
  protected void validate() throws ServiceLocalException, Exception {
    super.validate();

    EwsUtilities.validateParam(this.name, "name");
    EwsUtilities.validateParam(this.parentFolderId, "parentFolderId");
    this.getParentFolderId().validate(
        this.getService().getRequestedServerVersion());
  }

  /**
   * Creates the service response.
   *
   * @param service       the service
   * @param responseIndex the response index
   * @return Service response.
   * @throws Exception the exception
   */
  @Override
  protected GetUserConfigurationResponse createServiceResponse(
      ExchangeService service, int responseIndex) throws Exception {
    // In the case of UserConfiguration.Load(), this.userConfiguration is
    // set.
    if (this.userConfiguration == null) {
      this.userConfiguration = new UserConfiguration(service,
          this.properties);
      this.userConfiguration.setName(this.name);
      this.userConfiguration.setParentFolderId(this.parentFolderId);
    }

    return new GetUserConfigurationResponse(this.userConfiguration);
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
    return XmlElementNames.GetUserConfiguration;
  }

  /**
   * Gets the name of the response XML element.
   *
   * @return XML element name
   */
  @Override
  protected String getResponseXmlElementName() {
    return XmlElementNames.GetUserConfigurationResponse;
  }

  /**
   * Gets the name of the response message XML element.
   *
   * @return XML element name
   */
  @Override
  protected String getResponseMessageXmlElementName() {
    return XmlElementNames.GetUserConfigurationResponseMessage;
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
    final String EnumDelimiter = ",";

    // Write UserConfiguationName element
    UserConfiguration.writeUserConfigurationNameToXml(writer,
        XmlNamespace.Messages, this.name, this.parentFolderId);

    // Write UserConfigurationProperties element
    writer.writeElementValue(XmlNamespace.Messages,
        XmlElementNames.UserConfigurationProperties, this.properties
            .toString().replace(EnumDelimiter, "").
                replace("[", "").replace("]", ""));
  }

  /**
   * Initializes a new instance of the class.
   *
   * @param service the service
   * @throws Exception
   */
  protected GetUserConfigurationRequest(ExchangeService service)
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
   * @param name the new name
   */
  protected void setName(String name) {
    this.name = name;
  }

  /**
   * Gets  the parent folder Id. <value>The parent folder Id.</value>
   *
   * @return the parent folder id
   */
  protected FolderId getParentFolderId() {
    return this.parentFolderId;
  }

  /**
   * Sets the parent folder id.
   *
   * @param parentFolderId the new parent folder id
   */
  protected void setParentFolderId(FolderId parentFolderId) {
    this.parentFolderId = parentFolderId;
  }

  /**
   * Gets  the user configuration. <value>The user
   * configuration.</value>
   *
   * @return the user configuration
   */
  protected UserConfiguration getUserConfiguration() {
    return this.userConfiguration;
  }

  /**
   * Sets the user configuration.
   *
   * @param userConfiguration the new user configuration
   */
  protected void setUserConfiguration(UserConfiguration userConfiguration) {
    this.userConfiguration = userConfiguration;
    this.name = this.userConfiguration.getName();
    this.parentFolderId = this.userConfiguration.getParentFolderId();
  }

  /**
   * Gets the properties.
   *
   * @return the properties
   */
  protected EnumSet<UserConfigurationProperties> getProperties() {
    return this.properties;
  }

  /**
   * Sets the properties.
   *
   * @param properties the new properties
   */
  protected void setProperties(
      EnumSet<UserConfigurationProperties> properties) {
    this.properties = properties;
  }

}
