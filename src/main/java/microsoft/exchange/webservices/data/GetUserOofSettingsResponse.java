/**************************************************************************
 * copyright file="GetUserOofSettingsResponse.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the GetUserOofSettingsResponse class.
 **************************************************************************/

package microsoft.exchange.webservices.data;

/**
 * Represents response to GetUserOofSettings request.
 */
public class GetUserOofSettingsResponse extends ServiceResponse {

	/** The oof settings. */
	private OofSettings oofSettings;

	/**
	 * Initializes a new instance of the class.
	 */
	protected GetUserOofSettingsResponse() {
		super();
	}

	/**
	 * Gets  the OOF settings. <value>The oof settings.</value>
	 * 
	 * @return the oof settings
	 */
	public OofSettings getOofSettings() {
		return this.oofSettings;
	}

	/**
	 * Sets the oof settings.
	 * 
	 * @param value
	 *            the new oof settings
	 */
	protected void setOofSettings(OofSettings value) {
		this.oofSettings = value;
	}

}
