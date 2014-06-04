/**************************************************************************
 * copyright file="OutlookConfigurationSettings.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the OutlookConfigurationSettings.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLStreamException;

/**
 * Represents Outlook configuration settings.
 * 
 */
final class OutlookConfigurationSettings extends ConfigurationSettingsBase {

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


	/** The user. */
	private OutlookUser user;

	/** The account. */
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
	@Override
	protected String getNamespace() {
		return "http://schemas.microsoft.com/exchange/" +
		"autodiscover/outlook/responseschema/2006a";
	}

	/**
	 * Makes this instance a redirection response.
	 * 
	 * @param redirectUrl
	 *            The redirect URL.
	 */
	@Override
	protected void makeRedirectionResponse(URI redirectUrl) {
		this.account = new OutlookAccount();
		this.account.setRedirectTarget(redirectUrl.toString());
		this.account.setResponseType(AutodiscoverResponseType.RedirectUrl);
	}

	/**
	 * Tries to read the current XML element.
	 * 
	 * @param reader
	 *            The reader.
	 * @return True is the current element was read, false otherwise.
	 * @throws ServiceXmlDeserializationException
	 *             the service xml deserialization exception
	 * @throws javax.xml.stream.XMLStreamException
	 *             the xML stream exception
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected boolean tryReadCurrentXmlElement(EwsXmlReader reader)
	throws ServiceXmlDeserializationException, XMLStreamException,
	Exception {
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
	 * @param smtpAddress
	 *            SMTP address requested.
	 * @param requestedSettings
	 * 			  The requested settings.
	 * @return GetUserSettingsResponse
	 */
	@Override
    protected  GetUserSettingsResponse convertSettings(String smtpAddress, 
			List<UserSettingName> requestedSettings) {
        GetUserSettingsResponse response = new GetUserSettingsResponse();
        response.setSmtpAddress(smtpAddress);

        if (this.getError() != null)
        {
            response.setErrorCode(AutodiscoverErrorCode.InternalServerError);
            response.setErrorMessage(this.getError().getMessage());
        }
        else 
        {
            switch (this.getResponseType())
            {
                case Success:
                    response.setErrorCode(AutodiscoverErrorCode.NoError);
                    response.setErrorMessage("");
                    this.user.convertToUserSettings(requestedSettings, response);
                    this.account.convertToUserSettings(requestedSettings, response);
                    this.reportUnsupportedSettings(requestedSettings, response);
                    break;
                case Error:
                    response.setErrorCode(AutodiscoverErrorCode.InternalServerError);
                    response.setErrorMessage(Strings.InvalidAutodiscoverServiceResponse);
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
                    EwsUtilities.EwsAssert(
                        false,
                        "OutlookConfigurationSettings.ConvertSettings",
                        "An unexpected error has occured. " +
                        "This code path should never be reached.");
                    break;
            }
        }
        return response;
    }
	
	/**
	 * Reports any requested user settings that aren't 
	 * supported by the Outlook provider.
	 * 
	 * @param requestedSettings
	 *            The requested settings.
	 * @param response
	 * 			  The response.
	 */
    private void reportUnsupportedSettings(List<UserSettingName> requestedSettings, 
			GetUserSettingsResponse response) {
        // In English: find settings listed in requestedSettings that are not supported by the Legacy provider.
    	
    	//TODO need to check Iterable
        List<UserSettingName> invalidSettingQuery = 
				new ArrayList<UserSettingName>();
        for (UserSettingName userSettingName : requestedSettings) {
			if(!OutlookConfigurationSettings.isAvailableUserSetting(userSettingName))
			{
				invalidSettingQuery.add(userSettingName);
			}
		}
        /* from setting in requestedSettings
           where !OutlookConfigurationSettings.IsAvailableUserSetting(setting)
           select setting;*/

        // Add any unsupported settings to the UserSettingsError collection.
        for (UserSettingName invalidSetting : invalidSettingQuery)
        {
            UserSettingError settingError = new UserSettingError();
            settingError.setErrorCode(AutodiscoverErrorCode.InvalidSetting);
            settingError.setSettingName(invalidSetting.toString());
            settingError.setErrorMessage(String.format(Strings.AutodiscoverInvalidSettingForOutlookProvider, 
                invalidSetting.toString()));
            response.getUserSettingErrors().add(settingError);
        }
    }

    /**
	 * Gets the type of the response.
	 * 
	 * @return The type of the response.
	 */
    @Override
    protected AutodiscoverResponseType getResponseType()
    {
            if (this.account != null)
            {
                return this.account.getResponseType();
            }
            else
            {
                return AutodiscoverResponseType.Error;
            }
    }

    /**
	 * Gets the redirect target.
	 * 
	 * @return String 
	 * 			  the redirect target.
	 */
    @Override
    protected String getRedirectTarget()
    {
        return this.account.getRedirectTarget();
    }

}
