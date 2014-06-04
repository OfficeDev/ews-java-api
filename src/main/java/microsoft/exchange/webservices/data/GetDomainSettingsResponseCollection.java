/**************************************************************************
 * copyright file="GetDomainSettingsResponseCollection.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the GetDomainSettingsResponseCollection.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents a collection of responses to GetDomainSettings.
 */
public final class GetDomainSettingsResponseCollection extends
		AutodiscoverResponseCollection<GetDomainSettingsResponse> {

	/**
	 * Initializes a new instance of the AutodiscoverResponseCollection class.
	 */
	protected GetDomainSettingsResponseCollection() {
	}

	/**
	 * Create a response instance.
	 * 
	 * @return GetDomainSettingsResponse.
	 */
	@Override
	protected GetDomainSettingsResponse createResponseInstance() {
		return new GetDomainSettingsResponse();
	}

	/**
	 * Gets the name of the response collection XML element.
	 * 
	 * @return Response collection XMl element name.
	 */
	@Override
	protected String getResponseCollectionXmlElementName() {
		return XmlElementNames.DomainResponses;
	}

	/**
	 * Gets the name of the response instance XML element.
	 * 
	 * @return Response instance XMl element name.
	 */
	@Override
	protected String getResponseInstanceXmlElementName() {
		return XmlElementNames.DomainResponse;
	}

}
