/**************************************************************************
 * copyright file="UserConfigurationProperties.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the UserConfigurationProperties.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Identifies the user configuration properties to retrieve.
 */
public enum UserConfigurationProperties {

	// Retrieve the Id property.
	/** The Id. */
	Id(1),

	// Retrieve the Dictionary property.
	/** The Dictionary. */
	Dictionary(2),

	// Retrieve the XmlData property.
	/** The Xml data. */
	XmlData(4),

	// Retrieve the BinaryData property.
	/** The Binary data. */
	BinaryData(8),

	// Retrieve all properties.
	/** The All. */
	All(UserConfigurationProperties.Id, UserConfigurationProperties.Dictionary,
			UserConfigurationProperties.XmlData,
			UserConfigurationProperties.BinaryData);

	/** The config properties. */
	@SuppressWarnings("unused")
	private int configProperties = 0;

	/**
	 * Instantiates a new user configuration properties.
	 * 
	 * @param configProperties
	 *            the config properties
	 */
	UserConfigurationProperties(int configProperties) {
		this.configProperties = configProperties;
	}

	/**
	 * Instantiates a new user configuration properties.
	 * 
	 * @param id
	 *            the id
	 * @param dictionary
	 *            the dictionary
	 * @param xmlData
	 *            the xml data
	 * @param binaryData
	 *            the binary data
	 */
	UserConfigurationProperties(UserConfigurationProperties id,
			UserConfigurationProperties dictionary,
			UserConfigurationProperties xmlData,
			UserConfigurationProperties binaryData) {

	}

}
