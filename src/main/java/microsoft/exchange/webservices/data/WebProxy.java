/**************************************************************************
 * copyright file="WebProxy.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the WebProxy.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/***
 * WebProxy is used for setting proxy details for proxy authentication schemes such as
 * basic, digest, NTLM, and Kerberos authentication.
 * 
 */

public class WebProxy {

	/** proxy host. */
	private String host;
	
	/** proxy post. */
	private int port;

	/***
	 * Initializes a new instance to use specified proxy details.
	 * 
	 * @param host
	 *            proxy host.
	 * @param port
	 *            proxy port.
	 */
	public WebProxy(String host, int port) {
		this.host = host;
		this.port = port;
	}
	
	/***
	 * Initializes a new instance to use specified proxy with default port 80.
	 * 
	 * @param host
	 *            proxy host.
	 */
	public WebProxy(String host) {
		this.host = host;
		this.port = 80;
	}
	
	/*public WebProxy(ProxyHost httpproxy) throws UnknownHostException {
		this.host = httpproxy.getHostName();
		this.port = httpproxy.getPort();
	}	*/
	
	/**
	 * Gets the Proxy Host.
	 * 
	 * @return the host
	 */
	protected String getHost() {
		return this.host;
	}
	
	/**
	 * Gets the Proxy Port.
	 * 
	 * @return the port
	 */
	protected int getPort() {
		return this.port;
	}

	/***
	 * This method is used to set proxy credentials to a Web Request before
	 * the request is made.
	 * 
	 * @param user
	 *            The proxy username.
	 * @param pwd
	 *            The proxy password.
	 * @param domain
	 *            The proxy domain.
	 */
	public void setCredentials(String user, String pwd, String domain) {
		HttpProxyCredentials.setCredentials(user, pwd, domain);
		HttpProxyCredentials.isProxySet();
	}
}
