/*
 * The MIT License
 * Copyright (c) 2012 Microsoft Corporation
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package microsoft.exchange.webservices.data.autodiscover.exception.error;

import microsoft.exchange.webservices.data.autodiscover.enumeration.AutodiscoverErrorCode;
import microsoft.exchange.webservices.data.core.EwsXmlReader;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.security.XmlNodeType;

/**
 * Represents an error from a GetUserSettings request.
 */
public final class UserSettingError {

  /**
   * The error code.
   */
  private AutodiscoverErrorCode errorCode;

  /**
   * The error message.
   */
  private String errorMessage;

  /**
   * The setting name.
   */
  private String settingName;

  /**
   * Initializes a new instance of the "UserSettingError" class.
   */
  public UserSettingError() {
  }

  /**
   * Initializes a new instance of the "UserSettingError" class.
   *
   * @param errorCode    The error code
   * @param errorMessage The error message
   * @param settingName  Name of the setting
   */
  protected UserSettingError(AutodiscoverErrorCode errorCode,
      String errorMessage, String settingName) {
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
    this.settingName = settingName;
  }


  /**
   * Loads from XML.
   *
   * @param reader The reader.
   * @throws Exception the exception
   */
  public void loadFromXml(EwsXmlReader reader) throws Exception {
    do {
      reader.read();

      if (reader.getNodeType().getNodeType() == XmlNodeType.START_ELEMENT) {
        if (reader.getLocalName().equals(XmlElementNames.ErrorCode)) {
          this.setErrorCode(reader
              .readElementValue(AutodiscoverErrorCode.class));
        } else if (reader.getLocalName().equals(
            XmlElementNames.ErrorMessage)) {
          this.setErrorMessage(reader.readElementValue());
        } else if (reader.getLocalName().equals(
            XmlElementNames.SettingName)) {
          this.setSettingName(reader.readElementValue());
        }
      }
    } while (!reader.isEndElement(XmlNamespace.Autodiscover,
        XmlElementNames.UserSettingError));
  }

  /**
   * Gets the error code.
   *
   * @return The error code.
   */
  public AutodiscoverErrorCode getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(AutodiscoverErrorCode errorCode) {
    this.errorCode = errorCode;
  }

  /**
   * Gets the error message.
   *
   * @return The error message.
   */
  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }


  /**
   * Gets the name of the setting.
   *
   * @return The name of the setting.
   */
  public String getSettingName() {
    return settingName;
  }

  public void setSettingName(String settingName) {
    this.settingName = settingName;
  }

}
