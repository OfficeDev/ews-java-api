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

package microsoft.exchange.webservices.data.autodiscover.response;

import microsoft.exchange.webservices.data.autodiscover.AlternateMailboxCollection;
import microsoft.exchange.webservices.data.autodiscover.ProtocolConnectionCollection;
import microsoft.exchange.webservices.data.autodiscover.WebClientUrlCollection;
import microsoft.exchange.webservices.data.autodiscover.exception.error.UserSettingError;
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.EwsXmlReader;
import microsoft.exchange.webservices.data.core.XmlAttributeNames;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.autodiscover.enumeration.UserSettingName;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.misc.OutParam;
import microsoft.exchange.webservices.data.security.XmlNodeType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents the response to a GetUsersSettings call for an individual user.
 */
public final class GetUserSettingsResponse extends AutodiscoverResponse {

  /**
   * The smtp address.
   */
  private String smtpAddress;

  /**
   * The redirect target.
   */
  private String redirectTarget;

  /**
   * The settings.
   */
  private Map<UserSettingName, Object> settings;

  /**
   * The user setting errors.
   */
  private Collection<UserSettingError> userSettingErrors;

  /**
   * Initializes a new instance of the {@link GetUserSettingsResponse} class.
   */
  public GetUserSettingsResponse() {
    super();
    this.setSmtpAddress(null);
    this.setSettings(new HashMap<UserSettingName, Object>());
    this.setUserSettingErrors(new ArrayList<UserSettingError>());
  }

  /**
   * Tries the get the user setting value.
   *
   * @param cls     Type of user setting.
   * @param setting The setting.
   * @param value   The setting value.
   * @return True if setting was available.
   */
  public <T> boolean tryGetSettingValue(Class<T> cls,
      UserSettingName setting, OutParam<T> value) {
    Object objValue;
    if (this.getSettings().containsKey(setting)) {
      objValue = this.getSettings().get(setting);
      value.setParam((T) objValue);
      return true;
    } else {
      value.setParam(null);
      return false;
    }
  }

  /**
   * Gets the SMTP address this response applies to.
   *
   * @return the smtp address
   */
  public String getSmtpAddress() {
    return this.smtpAddress;
  }

  /**
   * Sets the smtp address.
   *
   * @param value the new smtp address
   */
  public void setSmtpAddress(String value) {
    this.smtpAddress = value;
  }

  /**
   * Gets the redirectionTarget (URL or email address).
   *
   * @return the redirect target
   */
  public String getRedirectTarget() {
    return this.redirectTarget;
  }

  /**
   * Sets the redirectionTarget (URL or email address).
   * @param value redirect target value
   */
  public void setRedirectTarget(String value) {
    this.redirectTarget = value;
  }

  /**
   * Gets the requested settings for the user.
   *
   * @return the settings
   */
  public Map<UserSettingName, Object> getSettings() {
    return this.settings;
  }

  /**
   * Sets the requested settings for the user.
   * @param settings settings map
   */
  public void setSettings(Map<UserSettingName, Object> settings) {
    this.settings = settings;
  }

  /**
   * Gets error information for settings that could not be returned.
   *
   * @return the user setting errors
   */
  public Collection<UserSettingError> getUserSettingErrors() {
    return this.userSettingErrors;
  }

  /**
   * Sets the requested settings for the user.
   * @param value user setting errors
   */
  protected void setUserSettingErrors(Collection<UserSettingError> value) {
    this.userSettingErrors = value;
  }

  /**
   * Loads response from XML.
   *
   * @param reader         The reader.
   * @param endElementName End element name.
   * @throws Exception the exception
   */
  @Override public void loadFromXml(EwsXmlReader reader, String endElementName)
      throws Exception {
    do {
      reader.read();

      if (reader.getNodeType().getNodeType() == XmlNodeType.START_ELEMENT) {
        if (reader.getLocalName()
            .equals(XmlElementNames.RedirectTarget)) {

          this.setRedirectTarget(reader.readElementValue());
        } else if (reader.getLocalName().equals(
            XmlElementNames.UserSettingErrors)) {
          this.loadUserSettingErrorsFromXml(reader);
        } else if (reader.getLocalName().equals(
            XmlElementNames.UserSettings)) {
          this.loadUserSettingsFromXml(reader);
        } else {
          super.loadFromXml(reader, endElementName);
        }
      }
    } while (!reader
        .isEndElement(XmlNamespace.Autodiscover, endElementName));
  }

  /**
   * Loads from XML.
   *
   * @param reader The reader.
   * @throws Exception the exception
   */
  protected void loadUserSettingsFromXml(EwsXmlReader reader)
      throws Exception {
    if (!reader.isEmptyElement()) {
      do {
        reader.read();

        if ((reader.getNodeType().getNodeType() == XmlNodeType.START_ELEMENT) &&
            (reader.getLocalName()
                .equals(XmlElementNames.UserSetting))) {
          String settingClass = reader.readAttributeValue(
              XmlNamespace.XmlSchemaInstance,
              XmlAttributeNames.Type);

          if (settingClass.equals(XmlElementNames.StringSetting)) {
            this.readSettingFromXml(reader);
          } else if (settingClass.equals(XmlElementNames.WebClientUrlCollectionSetting)) {
            this.readSettingFromXml(reader);
          } else if (settingClass.equals(XmlElementNames.AlternateMailboxCollectionSetting)) {
            this.readSettingFromXml(reader);
          } else if (settingClass.equals(XmlElementNames.ProtocolConnectionCollectionSetting)) {
            this.readSettingFromXml(reader);
          } else {
            EwsUtilities.ewsAssert(false, "GetUserSettingsResponse." + "LoadUserSettingsFromXml", String
                .format("%s,%s", "Invalid setting class '%s' returned", settingClass));
            break;
          }
        }
      } while (!reader.isEndElement(XmlNamespace.Autodiscover,
          XmlElementNames.UserSettings));
    } else {
      reader.read();
    }
  }

  /**
   * Reads user setting from XML.
   *
   * @param reader The reader.
   * @throws Exception the exception
   */
  private void readSettingFromXml(EwsXmlReader reader) throws Exception {
    UserSettingName name = null;
    Object value = null;

    do {
      reader.read();

      if (reader.getNodeType().getNodeType() == XmlNodeType.START_ELEMENT) {
        if (reader.getLocalName().equals(XmlElementNames.Name)) {
          name = reader.readElementValue(UserSettingName.class);
        } else if (reader.getLocalName().equals(XmlElementNames.Value)) {
          value = reader.readElementValue();
        } else if (reader.getLocalName().equals(
            XmlElementNames.WebClientUrls)) {

          value = WebClientUrlCollection.loadFromXml(reader);
        } else if (reader.getLocalName().equals(
            XmlElementNames.ProtocolConnections)) {
          value = ProtocolConnectionCollection.loadFromXml(reader);
        } else if (reader.getLocalName().equals(
            XmlElementNames.AlternateMailboxes)) {
          value = AlternateMailboxCollection.loadFromXml(reader);
        }
      }
    } while (!reader.isEndElement(XmlNamespace.Autodiscover,
        XmlElementNames.UserSetting));

    EwsUtilities.ewsAssert(name != null, "GetUserSettingsResponse.ReadSettingFromXml",
                           "Missing name element in user setting");

    this.getSettings().put(name, value);
  }

  /**
   * Loads the user setting errors.
   *
   * @param reader The reader.
   * @throws Exception the exception
   */
  private void loadUserSettingErrorsFromXml(EwsXmlReader reader)
      throws Exception {
    if (!reader.isEmptyElement()) {
      do {
        reader.read();

        if ((reader.getNodeType().getNodeType() == XmlNodeType.START_ELEMENT) &&
            (reader.getLocalName()
                .equals(XmlElementNames.UserSettingError))) {
          UserSettingError error = new UserSettingError();
          error.loadFromXml(reader);
          this.getUserSettingErrors().add(error);
        }
      } while (!reader.isEndElement(XmlNamespace.Autodiscover,
          XmlElementNames.UserSettingErrors));
    } else {
      reader.read();
    }
  }
}
