/**************************************************************************
 * copyright file="GetDomainSettingsRequest.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the GetDomainSettingsRequest.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.net.URI;
import java.util.List;

import javax.xml.stream.XMLStreamException;

/**
 * Represents a GetDomainSettings request.
 * 
 */
class GetDomainSettingsRequest extends AutodiscoverRequest {

	/**
	 * Action Uri of Autodiscover.GetDomainSettings method.
	 */
	private static final String GetDomainSettingsActionUri = 
		EwsUtilities.AutodiscoverSoapNamespace +
		"/Autodiscover/GetDomainSettings";

	/** The domains. */
	private List<String> domains;

	/** The settings. */
	private List<DomainSettingName> settings;

	private ExchangeVersion requestedVersion;

	/**
	 * Initializes a new instance of the <see cref="GetDomainSettingsRequest"/>
	 * class.
	 * 
	 * @param service
	 *            the service
	 * @param url
	 *            the url
	 */
	protected GetDomainSettingsRequest(AutodiscoverService service, URI url) {
		super(service, url);
	}

	/**
	 * Validates the request.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void validate() throws Exception {
		super.validate();

		EwsUtilities.validateParam(this.getDomains(), "domains");
		EwsUtilities.validateParam(this.getSettings(), "settings");

		if (this.getSettings().size() == 0) {
			throw new ServiceValidationException(
					Strings.InvalidAutodiscoverSettingsCount);
		}

		if (domains.size() == 0) {
			throw new ServiceValidationException(
					Strings.InvalidAutodiscoverDomainsCount);
		}

		for (String domain : this.getDomains()) {
			if (domain == null || domain.isEmpty()) {
				throw new ServiceValidationException(
						Strings.InvalidAutodiscoverDomain);
			}
		}
	}

	/**
	 * Executes this instance.
	 * 
	 * @return the gets the domain settings response collection
	 * @throws microsoft.exchange.webservices.data.ServiceLocalException
	 *             the service local exception
	 * @throws Exception
	 *             the exception
	 */
	protected GetDomainSettingsResponseCollection execute()
	throws ServiceLocalException, Exception {
		GetDomainSettingsResponseCollection responses = 
			(GetDomainSettingsResponseCollection) this
			.internalExecute();
		if (responses.getErrorCode() == AutodiscoverErrorCode.NoError) {
			this.PostProcessResponses(responses);
		}
		return responses;
	}

	/**
	 * Post-process responses to GetDomainSettings.
	 * 
	 * @param responses
	 *            The GetDomainSettings responses.
	 */
	private void PostProcessResponses(
			GetDomainSettingsResponseCollection responses) {
		// Note:The response collection may not include all of the requested
		// domains if the request has been throttled.
		for (int index = 0; index < responses.getCount(); index++) {
			responses.getResponses().get(index).setDomain(
					this.getDomains().get(index));
		}
	}

	/**
	 * Gets the name of the request XML element.
	 * 
	 * @return Request XML element name.
	 */
	@Override
	protected String getRequestXmlElementName() {
		return XmlElementNames.GetDomainSettingsRequestMessage;
	}

	/**
	 * Gets the name of the response XML element.
	 * 
	 * @return Response XML element name.
	 */
	@Override
	protected String getResponseXmlElementName() {
		return XmlElementNames.GetDomainSettingsResponseMessage;
	}

	/**
	 * Gets the WS-Addressing action name.
	 * 
	 * @return WS-Addressing action name.
	 */
	@Override
	protected String getWsAddressingActionName() {
		return GetDomainSettingsActionUri;
	}

	/**
	 * Creates the service response.
	 * 
	 * @return AutodiscoverResponse
	 */
	@Override
	protected AutodiscoverResponse createServiceResponse() {
		return new GetDomainSettingsResponseCollection();
	}

	/**
	 * Writes the attributes to XML.
	 * 
	 * @param writer
	 *            The writer.
	 * @throws ServiceXmlSerializationException
	 *             the service xml serialization exception
	 */
	@Override
	protected void writeAttributesToXml(EwsServiceXmlWriter writer)
	throws ServiceXmlSerializationException {
		writer.writeAttributeValue("xmlns",
				EwsUtilities.AutodiscoverSoapNamespacePrefix,
				EwsUtilities.AutodiscoverSoapNamespace);
	}

	/**
	 * Writes request to XML.
	 * 
	 * @param writer
	 *            The writer.
	 * @throws javax.xml.stream.XMLStreamException
	 *             the xML stream exception
	 * @throws ServiceXmlSerializationException
	 *             the service xml serialization exception
	 */
	@Override
	protected void writeElementsToXml(EwsServiceXmlWriter writer)
	throws XMLStreamException, ServiceXmlSerializationException {
		writer.writeStartElement(XmlNamespace.Autodiscover,
				XmlElementNames.Request);

		writer.writeStartElement(XmlNamespace.Autodiscover,
				XmlElementNames.Domains);

		for (String domain : this.getDomains()) {
			if (!(domain == null || domain.isEmpty())) {
				writer.writeElementValue(XmlNamespace.Autodiscover,
						XmlElementNames.Domain, domain);
			}
		}
		writer.writeEndElement(); // Domains

		writer.writeStartElement(XmlNamespace.Autodiscover,
				XmlElementNames.RequestedSettings);
		for (DomainSettingName setting : settings) {
			writer.writeElementValue(XmlNamespace.Autodiscover,
					XmlElementNames.Setting, setting);
		}

		writer.writeEndElement(); // RequestedSettings

		if (this.requestedVersion!= null)
		{
			writer.writeElementValue(XmlNamespace.Autodiscover, 
					XmlElementNames.RequestedVersion, this.requestedVersion);
		}

		writer.writeEndElement(); // Request
	}

	/**
	 * Gets  the domains.
	 * 
	 * @return the domains
	 */
	protected List<String> getDomains() {
		return domains;
	}

	/**
	 * Sets the domains.
	 * 
	 * @param value
	 *            the new domains
	 */
	protected void setDomains(List<String> value) {
		domains = value;
	}

	/**
	 * Gets or sets the settings.
	 * 
	 * @return the settings
	 */
	protected List<DomainSettingName> getSettings() {
		return settings;
	}

	/**
	 * Sets the settings.
	 * 
	 * @param value
	 *            the new settings
	 */
	protected void setSettings(List<DomainSettingName> value) {
		settings = value;
	}

	/**
	 * Gets or sets the requestedVersion.
	 * 
	 * @return the requestedVersion
	 */
	protected ExchangeVersion getRequestedVersion(){
		return requestedVersion;
	}
	/**
	 * Sets the requestedVersion.
	 * 
	 * @param value
	 *            the new requestedVersion
	 */
	protected void setRequestedVersion(ExchangeVersion value)
	{
		requestedVersion=value;
	}

}
