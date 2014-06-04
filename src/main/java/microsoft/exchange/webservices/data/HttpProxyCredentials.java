/**************************************************************************
 * copyright file="HttpProxyCredentials.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the HttpProxyCredentials.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * The Class HttpProxyCredentials.
 */
class HttpProxyCredentials {

	/** The user name. */
	private static String userName;

	/** The password. */
	private static String password;

	/** The domain. */
	private static String domain;
	
	/** The is proxy set. */
	private static boolean isProxySet;

	/**
	 * Sets the credentials.
	 * 
	 * @param user
	 *            the user
	 * @param pwd
	 *            the password
	 * @param dmn
	 *            the domain
	 */
	public static void setCredentials(String user, String pwd, String dmn) {
		userName = user;
		password = pwd;
		domain = dmn;
		isProxySet = true;

	}

	/**
	 * Clear proxy credentials.
	 */
	public static void clearProxyCredentials() {
		isProxySet = false;
	}

	/**
	 * Checks if is proxy set.
	 * 
	 * @return true, if is proxy set
	 */
	public static boolean isProxySet() {
		return isProxySet;
	}

	/**
	 * Gets the user name.
	 * 
	 * @return the user name
	 */
	public static String getUserName() {
		if (isProxySet) {
			return userName;
		} else {
			return null;
		}
	}

	/**
	 * Gets the password.
	 * 
	 * @return the password
	 */
	public static String getPassword() {
		if (isProxySet) {
			return password;
		} else {
			return null;
		}

	}

	/**
	 * Gets the domain.
	 * 
	 * @return the domain
	 */
	public static String getDomain() {
		if (isProxySet) {
			return domain;
		} else {
			return null;
		}

	}
}
