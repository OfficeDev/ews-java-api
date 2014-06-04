/**************************************************************************
 * copyright file="AutodiscoverResponse.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the AutodiscoverResponse.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.net.URI;

/**
 * Represents the base class for all responses returned by the Autodiscover
 * service.
 * 
 */
public abstract class AutodiscoverResponse {

	/** The error code. */
	private AutodiscoverErrorCode errorCode;

	/** The error message. */
	private String errorMessage;

	/** The redirection url. */
	private URI redirectionUrl;

	/**
	 * * Initializes a new instance of the AutodiscoverResponse class.
	 */
	AutodiscoverResponse() {
		this.errorCode = AutodiscoverErrorCode.NoError;
	}

	/**
	 * * Initializes a new instance of the AutodiscoverResponse class.
	 * 
	 * @param reader
	 *            the reader
	 * @param endElementName
	 *            the end element name
	 * @throws Exception
	 *             the exception
	 */
	protected void loadFromXml(EwsXmlReader reader, String endElementName)
			throws Exception {
		if (reader.getLocalName().equalsIgnoreCase(XmlElementNames.ErrorCode)) {
			this.errorCode = reader
					.readElementValue(AutodiscoverErrorCode.class);
		} else if (reader.getLocalName().equalsIgnoreCase(
				XmlElementNames.ErrorMessage)) {
			this.errorMessage = reader.readElementValue();
		}
	}

	/**
	 * Gets  the error code that was returned by the service.
	 * 
	 * @return the error code
	 */
	public AutodiscoverErrorCode getErrorCode() {
		return errorCode;
	}

	/**
	 * Sets the error code.
	 * 
	 * @param errorCode
	 *            the new error code
	 */
	protected void setErrorCode(AutodiscoverErrorCode errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * Gets the error message that was returned by the service.
	 * 
	 * @return the error message
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * Sets the error message.
	 * 
	 * @param errorMessage
	 *            the new error message
	 */
	protected void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * Gets  the redirection URL.
	 * 
	 * @return the redirection url
	 */
	protected URI getRedirectionUrl() {
		return redirectionUrl;
	}

	/**
	 * Sets the redirection url.
	 * 
	 * @param redirectionUrl
	 *            the new redirection url
	 */
	protected void setRedirectionUrl(URI redirectionUrl) {
		this.redirectionUrl = redirectionUrl;
	}
}
