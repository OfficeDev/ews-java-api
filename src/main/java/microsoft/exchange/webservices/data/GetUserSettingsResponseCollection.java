/**************************************************************************
 * copyright file="GetUserSettingsResponseCollection.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the GetUserSettingsResponseCollection.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents a collection of responses to GetUserSettings.
 */
public final class GetUserSettingsResponseCollection extends
		AutodiscoverResponseCollection<GetUserSettingsResponse> {

	/**
	 * Initializes a new instance of the AutodiscoverResponseCollection class.
	 */
	protected GetUserSettingsResponseCollection() {
	}

	/**
	 * Create a response instance.
	 * 
	 * @return GetUserSettingsResponse.
	 */
	@Override
	protected GetUserSettingsResponse createResponseInstance() {
		return new GetUserSettingsResponse();
	}

	/**
	 * Gets the name of the response collection XML element.
	 * 
	 * @return Response collection XMl element name.
	 */
	@Override
	protected String getResponseCollectionXmlElementName() {
		return XmlElementNames.UserResponses;
	}

	/**
	 * Gets the name of the response instance XML element.
	 * 
	 * @return Response instance XMl element name.
	 */
	@Override
	protected String getResponseInstanceXmlElementName() {
		return XmlElementNames.UserResponse;
	}

}
