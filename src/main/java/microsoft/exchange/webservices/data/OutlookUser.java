/**************************************************************************
 * copyright file="OutlookUser.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the OutlookUser.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Represents the user Outlook configuration settings apply to.
 * 
 */
@EditorBrowsable(state = EditorBrowsableState.Never)
final class OutlookUser {

	/**
	 * Converters to translate Outlook user settings.
	 * Each entry maps to a lambda expression used to 
	 * get the matching property from the OutlookUser instance. 
	 */
    private static LazyMember<Map<UserSettingName,IFunc<OutlookUser, String>>> 
    converterDictionary = 
    	new LazyMember<Map<UserSettingName,IFunc<OutlookUser, String>>>(
    			new ILazyMember<Map<UserSettingName,IFunc<OutlookUser,String>>>() {
					public Map<UserSettingName,IFunc<OutlookUser,String>> createInstance() {
						Map<UserSettingName,IFunc<OutlookUser, String>> results = 
							new HashMap<UserSettingName,IFunc<OutlookUser, String>>();
			            results.put(UserSettingName.UserDisplayName,  
			            		new IFunc<OutlookUser, String>() { 
							public String func(OutlookUser arg) {
								return arg.displayName;
							}
						});
			            results.put(UserSettingName.UserDN, 
			            		new IFunc<OutlookUser, String>() { 
							public String func(OutlookUser arg) {
								return arg.legacyDN;
							}
						});
			            results.put(UserSettingName.UserDeploymentId, 
			            		new IFunc<OutlookUser, String>() { 
							public String func(OutlookUser arg) {
								return arg.deploymentId;
							}
						});
			            return results;
					}
				});

	/** The display name. */
	private String displayName;

	/** The legacy dn. */
	private String legacyDN;

	/** The deployment id. */
	private String deploymentId;

	/**
	 * Initializes a new instance of the OutlookUser class.
	 */
	protected OutlookUser() {
	}

	/**
	 * Load from XML.
	 * 
	 * @param reader
	 *            The reader.
	 * @throws Exception
	 *             the exception
	 */
	protected void loadFromXml(EwsXmlReader reader) throws Exception {
		
		do {
			reader.read();

			if (reader.getNodeType().getNodeType() == XMLNodeType.START_ELEMENT) {
				if (reader.getLocalName().equals(XmlElementNames.DisplayName)) {
					this.displayName = reader.readElementValue();
				} else if (reader.getLocalName().equals(
						XmlElementNames.LegacyDN)) {
					this.legacyDN = reader.readElementValue();
				} else if (reader.getLocalName().equals(
						XmlElementNames.DeploymentId)) {
					this.deploymentId = reader.readElementValue();
				} else {
					reader.skipCurrentElement();

				}
			}
		} while (!reader.isEndElement(XmlNamespace.NotSpecified,
				XmlElementNames.User));
	}

	/**
	 * Convert OutlookUser to GetUserSettings response.
	 * 
	 * @param requestedSettings
	 *            The requested settings.
	 * @param response
	 *             The response.
	 */
    protected void convertToUserSettings(
        List<UserSettingName> requestedSettings,
        GetUserSettingsResponse response) {
        // In English: collect converters that are 
    	//contained in the requested settings.
    	Map<UserSettingName,IFunc<OutlookUser,String>> 
    	converterQuery = new HashMap<UserSettingName,
							IFunc<OutlookUser,String>>(); 
    	for (Entry<UserSettingName, IFunc<OutlookUser, String>> map : converterDictionary.getMember().entrySet()) {
    		if(requestedSettings.contains(map.getKey())) {
				converterQuery.put(map.getKey(),map.getValue());
			}
		}

        for (Entry<UserSettingName,IFunc<OutlookUser,String>> kv : converterQuery.entrySet())
        {
            String value = kv.getValue().func(this);
            if (!(value == null || value.isEmpty())) {
                response.getSettings().put(kv.getKey(), value);
            }
        }
    }

    /**
	 * Gets the available user settings.
	 * 
	 * @return The available user settings.
	 */
    protected static Iterable<UserSettingName> getAvailableUserSettings() {
        return converterDictionary.getMember().keySet();
    }
}
