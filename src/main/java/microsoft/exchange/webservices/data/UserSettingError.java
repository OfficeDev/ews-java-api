/**************************************************************************
 * copyright file="UserSettingError.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the UserSettingError.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents an error from a GetUserSettings request.
 * 
 */
public final class UserSettingError {

	/** The error code. */
	private AutodiscoverErrorCode errorCode;

	/** The error message. */
	private String errorMessage;

	/** The setting name. */
	private String settingName;

	/**
	 * Initializes a new instance of the "UserSettingError" class.
	 */
	protected UserSettingError() {
	}
	
	/**
	 * Initializes a new instance of the "UserSettingError" class.
	 * @param errorCode
	 * 				The error code
	 * @param errorMessage
	 * 				The error message
	 * @param settingName
	 * 				Name of the setting
	 */
	protected UserSettingError(AutodiscoverErrorCode errorCode,
			String errorMessage,String settingName) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.settingName = settingName;
	}


	/**
	 * Loads from XML.
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
				if (reader.getLocalName().equals(XmlElementNames.ErrorCode)) {
					this.setErrorCode(reader
							.readElementValue(AutodiscoverErrorCode.class));
				} else if (reader.getLocalName().equals(
						XmlElementNames.ErrorMessage)) {
					this.setErrorMessage(reader.readElementValue());
				} else if (reader.getLocalName().equals(
						XmlElementNames.SettingName)) {
					this.setSettingName(reader.readElementValue());
				}
			}
		} while (!reader.isEndElement(XmlNamespace.Autodiscover,
				XmlElementNames.UserSettingError));
	}

	/**
	 * Gets the error code.
	 * 
	 * @return The error code.
	 */
	public AutodiscoverErrorCode getErrorCode() {
		return errorCode;
	}
	
	protected void setErrorCode(AutodiscoverErrorCode errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * Gets the error message.
	 * 
	 * @return The error message.
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
	
	protected void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}


	/**
	 * Gets the name of the setting.
	 * 
	 * @return The name of the setting.
	 */
	public String getSettingName() {
		return settingName;
	}
	
	protected void setSettingName(String settingName) {
		this.settingName = settingName;
	}

}
