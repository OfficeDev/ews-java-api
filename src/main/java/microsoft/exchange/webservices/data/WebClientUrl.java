/**************************************************************************
 * copyright file="WebClientUrl.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the WebClientUrl.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents the URL of the Exchange web client.
 * 
 */
public final class WebClientUrl {

	/** The authentication methods. */
	private String authenticationMethods;

	/** The url. */
	private String url;

	/**
	 * Initializes a new instance of the <see cref="WebClientUrl"/> class.
	 */
	private WebClientUrl() {
	}
	
	/**
	 * Initializes a new instance of the WebClientUrl class.
	 * @param authenticationMethods
	 * 				The authentication methods.
	 * @param url
	 * 				The URL.
	 */
	protected  WebClientUrl(String authenticationMethods,String url){
		this.authenticationMethods = authenticationMethods;
		this.url = url;		
	}


	/**
	 * Loads WebClientUrl instance from XML.
	 * 
	 * @param reader
	 *            The reader.
	 * @return WebClientUrl.
	 * @throws Exception
	 *             the exception
	 */
	protected static WebClientUrl loadFromXml(EwsXmlReader reader)
			throws Exception {
		WebClientUrl webClientUrl = new WebClientUrl();

		do {
			reader.read();

			if (reader.getNodeType().getNodeType() == XMLNodeType.START_ELEMENT) {
				if (reader.getLocalName().equals(
						XmlElementNames.AuthenticationMethods)) {
					webClientUrl.setAuthenticationMethods(reader
							.readElementValue(String.class));
				} else if (reader.getLocalName().equals(XmlElementNames.Url)) {
					webClientUrl.setUrl(reader.readElementValue(String.class));
				}
			}
		} while (!reader.isEndElement(XmlNamespace.Autodiscover,
				XmlElementNames.WebClientUrl));

		return webClientUrl;
	}

	/**
	 * Gets the authentication methods.
	 * 
	 * @return the authentication methods
	 */
	public String getAuthenticationMethods() {
		return this.authenticationMethods;
	}

	/**
	 * Sets the authentication methods.
	 * 
	 * @param value
	 *            the new authentication methods
	 */
	protected void setAuthenticationMethods(String value) {
		this.authenticationMethods = value;
	}

	/**
	 * Gets the URL.
	 * 
	 * @return the url
	 */
	public String getUrl() {
		return this.url;
	}

	/**
	 * Sets the url.
	 * 
	 * @param value
	 *            the new url
	 */
	protected void setUrl(String value) {
		this.url = value;
	}

}
