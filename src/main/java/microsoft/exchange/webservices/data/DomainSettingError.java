/**************************************************************************
 * copyright file="DomainSettingError.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the DomainSettingError.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents an error from a GetDomainSettings request.
 * 
 */
public final class DomainSettingError {

	/** The error code. */
	private AutodiscoverErrorCode errorCode;

	/** The error message. */
	private String errorMessage;

	/** The setting name. */
	private String settingName;

	/**
	 * Initializes a new instance of the <see cref="DomainSettingError"/> class.
	 */

	DomainSettingError() {
	}

	/**
	 * Loads from XML.
	 * 
	 * @param reader
	 *            The reader.
	 * @throws Exception
	 *             the exception
	 */
	void loadFromXml(EwsXmlReader reader) throws Exception {
		do {
			reader.read();

			if (reader.getNodeType().getNodeType() == XMLNodeType.START_ELEMENT) {
				if (reader.getLocalName().equals(XmlElementNames.ErrorCode)) {
					this.errorCode = reader
							.readElementValue(AutodiscoverErrorCode.class);
				} else if (reader.getLocalName().equals(
						XmlElementNames.ErrorMessage)) {
					this.errorMessage = reader.readElementValue();
				} else if (reader.getLocalName().equals(
						XmlElementNames.SettingName)) {
					this.settingName = reader.readElementValue();
				}
			}
		} while (!reader.isEndElement(XmlNamespace.Autodiscover,
				XmlElementNames.DomainSettingError));
	}

	/**
	 * Gets the error code.
	 * 
	 * @return The error code.
	 */

	public AutodiscoverErrorCode getErrorCode() {
		return this.errorCode;
	}

	/**
	 * Gets the error message.
	 * 
	 * @return The error message.
	 */

	public String getErrorMessage() {
		return this.errorMessage;
	}

	/**
	 * Gets the name of the setting.
	 * 
	 * @return The name of the setting.
	 */
	public String getSettingName() {
		return this.settingName;
	}

}
