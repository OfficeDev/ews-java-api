/**************************************************************************
 Exchange Web Services Java API
 Copyright (c) Microsoft Corporation
 All rights reserved.
 MIT License
 Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the ""Software""), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 THE SOFTWARE IS PROVIDED *AS IS*, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 **************************************************************************/

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
