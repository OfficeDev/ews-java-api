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

/**
 * Identifies the user configuration properties to retrieve.
 */
public enum UserConfigurationProperties {

  // Retrieve the Id property.
  /**
   * The Id.
   */
  Id(1),

  // Retrieve the Dictionary property.
  /**
   * The Dictionary.
   */
  Dictionary(2),

  // Retrieve the XmlData property.
  /**
   * The Xml data.
   */
  XmlData(4),

  // Retrieve the BinaryData property.
  /**
   * The Binary data.
   */
  BinaryData(8),

  // Retrieve all properties.
  /**
   * The All.
   */
  All(UserConfigurationProperties.Id, UserConfigurationProperties.Dictionary,
      UserConfigurationProperties.XmlData,
      UserConfigurationProperties.BinaryData);

  /**
   * The config properties.
   */
  @SuppressWarnings("unused")
  private int configProperties = 0;

  /**
   * Instantiates a new user configuration properties.
   *
   * @param configProperties the config properties
   */
  UserConfigurationProperties(int configProperties) {
    this.configProperties = configProperties;
  }

  /**
   * Instantiates a new user configuration properties.
   *
   * @param id         the id
   * @param dictionary the dictionary
   * @param xmlData    the xml data
   * @param binaryData the binary data
   */
  UserConfigurationProperties(UserConfigurationProperties id,
      UserConfigurationProperties dictionary,
      UserConfigurationProperties xmlData,
      UserConfigurationProperties binaryData) {

  }

}
