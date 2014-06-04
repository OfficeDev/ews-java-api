/**************************************************************************
 * copyright file="WebCredentials.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the WebCredentials.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/***
 * WebCredentials is used for password-based authentication schemes such as
 * basic, digest, NTLM, and Kerberos authentication.
 * 
 */
public final class WebCredentials extends ExchangeCredentials {

	/** The domain. */
	private String domain;

	/** The user. */
	private String user;

	/** The pwd. */
	private String pwd;

	/** The use default credentials. */
	private boolean useDefaultCredentials = true;

	/**
	 * Gets the domain.
	 * 
	 * @return the domain
	 */
	public String getDomain() {
		return domain;
	}

	/**
	 * Gets the user.
	 * 
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * Gets the pwd.
	 * 
	 * @return the pwd
	 */
	public String getPwd() {
		return pwd;
	}

	/**
	 * Checks if is use default credentials.
	 * 
	 * @return true, if is use default credentials
	 */
	public boolean isUseDefaultCredentials() {
		return useDefaultCredentials;
	}

	/***
	 * Initializes a new instance to use default network credentials.
	 */
	public WebCredentials() {
		useDefaultCredentials = true;
		this.user = null;
		this.pwd = null;
		this.domain = null;
	}

	/***
	 * Initializes a new instance to use specified credentials.
	 * 
	 * @param userName
	 *            Account user name.
	 * @param password
	 *            Account password.
	 * @param domain
	 *            Account domain.
	 */
	public WebCredentials(String userName, String password, String domain) {
		if (userName == null || password == null) {
			throw new IllegalArgumentException(
					"User name or password can not be null");
		}

		this.domain = domain;
		this.user = userName;
		this.pwd = password;
		useDefaultCredentials = false;
	}

	/***
	 * Initializes a new instance to use specified credentials.
	 * 
	 * @param username
	 *            The user name.
	 * @param password
	 *            The password.
	 */
	public WebCredentials(String username, String password) {
		this(username, password, "");
	}

	/***
	 * This method is called to apply credentials to a service request before
	 * the request is made.
	 * 
	 * @param client
	 *            The request.
	 */
	@Override
	protected void prepareWebRequest(HttpWebRequest client) {
		if (useDefaultCredentials) {
			client.setUseDefaultCredentials(true);
		} else {
			client.setCredentails(domain, user, pwd);
		}
	}

}
