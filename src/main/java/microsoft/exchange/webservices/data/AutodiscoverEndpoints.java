/**************************************************************************
 * copyright file="AutodiscoverEndpoints.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the AutodiscoverEndpoints.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Defines the types of Autodiscover endpoints that are available.
 */
enum AutodiscoverEndpoints {
	
	/** No endpoints available.	 */
	None(0),
	
	/** The "legacy" Autodiscover endpoint. */
	Legacy(1),
	
	/** The SOAP endpoint. */
	Soap(2),
	
	/** The WS-Security endpoint. */
	WsSecurity(4),
	 
    /** The WS-Security/SymmetricKey endpoint.*/
    WSSecuritySymmetricKey(8),

    /** The WS-Security/X509Cert endpoint.*/
    WSSecurityX509Cert(16);
	
	/** The autodis endpts. */
	@SuppressWarnings("unused")
	private final int autodisEndpts;

	/**
	 * Instantiates a new autodiscover endpoints.
	 * 
	 * @param autodisEndpts
	 *            the autodis endpts
	 */
	AutodiscoverEndpoints(int autodisEndpts) {
		this.autodisEndpts = autodisEndpts;
	}
}
