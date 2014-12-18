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

import java.net.URI;
import java.util.List;

/**
 * Represents the base class for configuration settings.
 */
@EditorBrowsable(state = EditorBrowsableState.Never)
abstract class ConfigurationSettingsBase {

  /**
   * The error.
   */
  private AutodiscoverError error;

  /**
   * Initializes a new instance of the ConfigurationSettingsBase class.
   */
  protected ConfigurationSettingsBase() {
  }

  /**
   * Tries to read the current XML element.
   *
   * @param reader the reader
   * @return True is the current element was read, false otherwise.
   * @throws Exception the exception
   */
  protected boolean tryReadCurrentXmlElement(EwsXmlReader reader)
      throws Exception {
    if (reader.getLocalName().equalsIgnoreCase(XmlElementNames.Error)) {
      this.error = AutodiscoverError.parse(reader);

      return true;
    } else {
      return false;
    }
  }

  /**
   * Loads the settings from XML.
   *
   * @param reader the reader
   * @throws Exception the exception
   */
  protected void loadFromXml(EwsXmlReader reader) throws Exception {
    reader.readStartElement(XmlNamespace.NotSpecified,
        XmlElementNames.Autodiscover);
    reader.readStartElement(XmlNamespace.NotSpecified,
        XmlElementNames.Response);

    do {
      reader.read();

      if (reader.isStartElement()) {
        if (!this.tryReadCurrentXmlElement(reader)) {
          reader.skipCurrentElement();
        }
      }
    } while (!reader.isEndElement(XmlNamespace.NotSpecified,
        XmlElementNames.Response));

    reader.readEndElement(XmlNamespace.NotSpecified,
        XmlElementNames.Autodiscover);
  }

  /**
   * Gets the namespace that defines the settings.
   *
   * @return The namespace that defines the settings
   */
  protected abstract String getNamespace();

  /**
   * Makes this instance a redirection response.
   *
   * @param redirectUrl the redirect url
   */
  protected abstract void makeRedirectionResponse(URI redirectUrl);

  /**
   * Gets the type of the response.
   *
   * @return The type of the response.
   */
  protected abstract AutodiscoverResponseType getResponseType();

  /**
   * Gets the redirect target.
   *
   * @return The redirect target.
   */
  protected abstract String getRedirectTarget();

  /**
   * Convert ConfigurationSettings to GetUserSettings response.
   *
   * @param smtpAddress       SMTP address.
   * @param requestedSettings The requested settings.
   * @return GetUserSettingsResponse.
   */
  protected abstract GetUserSettingsResponse convertSettings(
      String smtpAddress,
      List<UserSettingName> requestedSettings);


  /**
   * Gets the error.
   *
   * @return The error.
   */
  protected AutodiscoverError getError() {
    return this.error;
  }
}
