/**************************************************************************
 * copyright file="GetDomainSettingsResponse.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the GetDomainSettingsResponse.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents the response to a GetDomainSettings call for an individual domain.
 * 
 */
public final class GetDomainSettingsResponse extends AutodiscoverResponse {

	/** The domain. */
	private String domain;

	/** The redirect target. */
	private String redirectTarget;

	/** The settings. */
	private Map<DomainSettingName, Object> settings;

	/** The domain setting errors. */
	private Collection<DomainSettingError> domainSettingErrors;

	/**
	 * Initializes a new instance of the <see cref="GetDomainSettingsResponse"/>
	 * class.
	 */
	public GetDomainSettingsResponse() {
		super();
		this.domain = "";
		this.settings = new HashMap<DomainSettingName, Object>();
		this.domainSettingErrors = new ArrayList<DomainSettingError>();
	}

	/**
	 * Gets the domain this response applies to.
	 * 
	 * @return the domain
	 */
	public String getDomain() {
		return this.domain;
	}

	/**
	 * Sets the domain.
	 * 
	 * @param value
	 *            the new domain
	 */
	protected void setDomain(String value) {
		this.domain = value;
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
	 * Gets the requested settings for the domain.
	 * 
	 * @return the settings
	 */
	public Map<DomainSettingName, Object> getSettings() {
		return this.settings;
	}

	/**
	 * Gets error information for settings that could not be returned.
	 * 
	 * @return the domain setting errors
	 */
	public Collection<DomainSettingError> getDomainSettingErrors() {
		return this.domainSettingErrors;
	}

	/**
	 * Loads response from XML.
	 * 
	 * @param reader
	 *            The reader.
	 * @param endElementName
	 *            End element name.
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void loadFromXml(EwsXmlReader reader, String endElementName)
			throws Exception {
		do {
			reader.read();

			if (reader.getNodeType().nodeType == XMLNodeType.START_ELEMENT) {
				if (reader.getLocalName()
						.equals(XmlElementNames.RedirectTarget)) {
					this.redirectTarget = reader.readElementValue();
				} else if (reader.getLocalName().equals(
						XmlElementNames.DomainSettingErrors)) {
					this.loadDomainSettingErrorsFromXml(reader);
				} else if (reader.getLocalName().equals(
						XmlElementNames.DomainSettings)) {
					try {
						this.loadDomainSettingsFromXml(reader);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					super.loadFromXml(reader, endElementName);
					break;
				}
			}
		} while (!reader
				.isEndElement(XmlNamespace.Autodiscover, endElementName));
	}

	/**
	 * Loads from XML.
	 * 
	 * @param reader
	 *            The reader.
	 * @throws Exception
	 *             the exception
	 */
	protected void loadDomainSettingsFromXml(EwsXmlReader reader)
			throws Exception {
		if (!reader.isEmptyElement()) {
			do {
				reader.read();

				if ((reader.getNodeType().nodeType == XMLNodeType.START_ELEMENT) &&
						 (reader.getLocalName()
								.equals(XmlElementNames.DomainSetting))) {
					String settingClass = reader.readAttributeValue(
							XmlNamespace.XmlSchemaInstance,
							XmlAttributeNames.Type);

					if (settingClass
							.equals(XmlElementNames.DomainStringSetting)) {

						this.readSettingFromXml(reader);
					} else {
						EwsUtilities
								.EwsAssert(
										false,
										"GetDomainSettingsResponse." +
										"LoadDomainSettingsFromXml",
										String
												.format(
														"%s,%s",
														"Invalid setting " +
														"class '%s' returned",
														settingClass));
						break;
					}
				}
			} while (!reader.isEndElement(XmlNamespace.Autodiscover,
					XmlElementNames.DomainSettings));
		}else {
			reader.read();
		}
	}

	/**
	 * Reads domain setting from XML.
	 * 
	 * @param reader
	 *            The reader.
	 * @throws Exception
	 *             the exception
	 */
	private void readSettingFromXml(EwsXmlReader reader) throws Exception {
		DomainSettingName name = null;
		Object value = null;

		do {
			reader.read();

			if (reader.getNodeType().nodeType == XMLNodeType.START_ELEMENT) {
				if (reader.getLocalName().equals(
						XmlElementNames.DomainStringSetting)) {
					name = reader.readElementValue(DomainSettingName.class);
				} else if (reader.getLocalName().equals(XmlElementNames.Value)) {
					value = reader.readElementValue();
				}
			}
		} while (!reader.isEndElement(XmlNamespace.Autodiscover,
				XmlElementNames.DomainSetting));

		EwsUtilities.EwsAssert(name != null,
				"GetDomainSettingsResponse.ReadSettingFromXml",
				"Missing name element in domain setting");

		this.settings.put(name, value);
	}

	/**
	 * Loads the domain setting errors.
	 * 
	 * @param reader
	 *            The reader.
	 * @throws Exception
	 *             the exception
	 */
	private void loadDomainSettingErrorsFromXml(EwsXmlReader reader)
			throws Exception {
		if (!reader.isEmptyElement()) {
			do {
				reader.read();

				if ((reader.getNodeType().nodeType == XMLNodeType.START_ELEMENT) &&
						 (reader.getLocalName()
								.equals(XmlElementNames.DomainSettingError))) {
					DomainSettingError error = new DomainSettingError();
					error.loadFromXml(reader);
					domainSettingErrors.add(error);
				}
			} while (!reader.isEndElement(XmlNamespace.Autodiscover,
					XmlElementNames.DomainSettingErrors));
		} else {
			reader.read();
		}
	}
}
