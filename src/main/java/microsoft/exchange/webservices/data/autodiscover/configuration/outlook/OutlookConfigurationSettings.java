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

package microsoft.exchange.webservices.data.autodiscover.configuration.outlook;

import microsoft.exchange.webservices.data.autodiscover.configuration.ConfigurationSettingsBase;
import microsoft.exchange.webservices.data.autodiscover.enumeration.AutodiscoverErrorCode;
import microsoft.exchange.webservices.data.autodiscover.enumeration.AutodiscoverResponseType;
import microsoft.exchange.webservices.data.autodiscover.exception.error.UserSettingError;
import microsoft.exchange.webservices.data.autodiscover.response.GetUserSettingsResponse;
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.EwsXmlReader;
import microsoft.exchange.webservices.data.core.ILazyMember;
import microsoft.exchange.webservices.data.core.LazyMember;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.autodiscover.enumeration.UserSettingName;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents Outlook configuration settings.
 */
public final class OutlookConfigurationSettings extends ConfigurationSettingsBase {

  /**
   * All user settings that are available from the Outlook provider.
   */
  private static LazyMember<List<UserSettingName>>
      allOutlookProviderSettings = new LazyMember<List<UserSettingName>>(
      new ILazyMember<List<UserSettingName>>() {
        public List<UserSettingName> createInstance() {

          List<UserSettingName> results =
              new ArrayList<UserSettingName>();
          for (UserSettingName userSettingName : OutlookUser.getAvailableUserSettings()) {
            results.add(userSettingName);
          }
          results.addAll(OutlookProtocol.getAvailableUserSettings());
          results.add(UserSettingName.AlternateMailboxes);
          return results;
        }
      });


  /**
   * The user.
   */
  private OutlookUser user;

  /**
   * The account.
   */
  private OutlookAccount account;

  /**
   * Initializes a new instance of the OutlookConfigurationSettings class.
   */
  public OutlookConfigurationSettings() {
    this.user = new OutlookUser();
    this.account = new OutlookAccount();
  }

  /**
   * Determines whether user setting is available in the
   * OutlookConfiguration or not.
   *
   * @param setting The setting.
   * @return True if user setting is available, otherwise, false.
   */
  protected static boolean isAvailableUserSetting(UserSettingName setting) {
    return allOutlookProviderSettings.getMember().contains(setting);
  }

  /**
   * Gets the namespace that defines the settings.
   *
   * @return The namespace that defines the settings.
   */
  @Override public String getNamespace() {
    return "http://schemas.microsoft.com/exchange/" +
        "autodiscover/outlook/responseschema/2006a";
  }

  /**
   * Makes this instance a redirection response.
   *
   * @param redirectUrl The redirect URL.
   */
  @Override public void makeRedirectionResponse(URI redirectUrl) {
    this.account = new OutlookAccount();
    this.account.setRedirectTarget(redirectUrl.toString());
    this.account.setResponseType(AutodiscoverResponseType.RedirectUrl);
  }

  /**
   * Tries to read the current XML element.
   *
   * @param reader the reader
   * @return true is the current element was read, false otherwise
   * @throws Exception the exception
   */
  @Override
  public boolean tryReadCurrentXmlElement(EwsXmlReader reader) throws Exception {
    if (!super.tryReadCurrentXmlElement(reader)) {
      if (reader.getLocalName().equals(XmlElementNames.User)) {
        this.user.loadFromXml(reader);
        return true;
      } else if (reader.getLocalName().equals(XmlElementNames.Account)) {
        this.account.loadFromXml(reader);
        return true;
      } else {
        reader.skipCurrentElement();
        return false;
      }
    } else {
      return true;
    }
  }

  /**
   * Convert OutlookConfigurationSettings to GetUserSettings response.
   *
   * @param smtpAddress       SMTP address requested.
   * @param requestedSettings The requested settings.
   * @return GetUserSettingsResponse
   */
  @Override public GetUserSettingsResponse convertSettings(String smtpAddress,
      List<UserSettingName> requestedSettings) {
    GetUserSettingsResponse response = new GetUserSettingsResponse();
    response.setSmtpAddress(smtpAddress);

    if (this.getError() != null) {
      response.setErrorCode(AutodiscoverErrorCode.InternalServerError);
      response.setErrorMessage(this.getError().getMessage());
    } else {
      switch (this.getResponseType()) {
        case Success:
          response.setErrorCode(AutodiscoverErrorCode.NoError);
          response.setErrorMessage("");
          this.user.convertToUserSettings(requestedSettings, response);
          this.account.convertToUserSettings(requestedSettings, response);
          this.reportUnsupportedSettings(requestedSettings, response);
          break;
        case Error:
          response.setErrorCode(AutodiscoverErrorCode.InternalServerError);
          response.setErrorMessage("The Autodiscover service response was invalid.");
          break;
        case RedirectAddress:
          response.setErrorCode(AutodiscoverErrorCode.RedirectAddress);
          response.setErrorMessage("");
          response.setRedirectTarget(this.getRedirectTarget());
          break;
        case RedirectUrl:
          response.setErrorCode(AutodiscoverErrorCode.RedirectUrl);
          response.setErrorMessage("");
          response.setRedirectTarget(this.getRedirectTarget());
          break;
        default:
          EwsUtilities.ewsAssert(false, "OutlookConfigurationSettings.ConvertSettings",
                                 "An unexpected error has occured. "
                                 + "This code path should never be reached.");
          break;
      }
    }
    return response;
  }

  /**
   * Reports any requested user settings that aren't
   * supported by the Outlook provider.
   *
   * @param requestedSettings The requested settings.
   * @param response          The response.
   */
  private void reportUnsupportedSettings(List<UserSettingName> requestedSettings,
      GetUserSettingsResponse response) {
    // In English: find settings listed in requestedSettings that are not supported by the Legacy provider.

    //TODO need to check Iterable
    List<UserSettingName> invalidSettingQuery =
        new ArrayList<UserSettingName>();
    for (UserSettingName userSettingName : requestedSettings) {
      if (!OutlookConfigurationSettings.isAvailableUserSetting(userSettingName)) {
        invalidSettingQuery.add(userSettingName);
      }
    }
        /* from setting in requestedSettings
           where !OutlookConfigurationSettings.IsAvailableUserSetting(setting)
           select setting;*/

    // Add any unsupported settings to the UserSettingsError collection.
    for (UserSettingName invalidSetting : invalidSettingQuery) {
      UserSettingError settingError = new UserSettingError();
      settingError.setErrorCode(AutodiscoverErrorCode.InvalidSetting);
      settingError.setSettingName(invalidSetting.toString());
      settingError.setErrorMessage(String.format(
          "The requested setting, '%s', isn't supported by this Autodiscover endpoint.",
          invalidSetting.toString()));
      response.getUserSettingErrors().add(settingError);
    }
  }

  /**
   * Gets the type of the response.
   *
   * @return The type of the response.
   */
  @Override public AutodiscoverResponseType getResponseType() {
    if (this.account != null) {
      return this.account.getResponseType();
    } else {
      return AutodiscoverResponseType.Error;
    }
  }

  /**
   * Gets the redirect target.
   *
   * @return String
   * the redirect target.
   */
  @Override public String getRedirectTarget() {
    return this.account.getRedirectTarget();
  }

}
