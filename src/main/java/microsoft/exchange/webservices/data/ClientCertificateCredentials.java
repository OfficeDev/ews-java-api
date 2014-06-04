/**************************************************************************
 * copyright file="ClientCertificateCredentials.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ClientCertificateCredentials.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import javax.net.ssl.TrustManager;

/**
 * ClientCertificateCredentials wraps an instance of X509CertificateCollection used for client certification-based authentication.
 */
public class ClientCertificateCredentials extends ExchangeCredentials {
	
	/**
	 * Collection of client certificates.
	 */
    private TrustManager clientCertificates;

    /**
     * Initializes a new instance of the ClientCertificateCredentials class.
     * @param clientCertificates The clientCertificates
     * @throws Exception 
     */
    public ClientCertificateCredentials(TrustManager clientCertificates) throws Exception
    {
        EwsUtilities.validateParam(clientCertificates, "clientCertificates");

        this.clientCertificates = clientCertificates;
    }

    /**
     * This method is called to apply credentials to a service request before the request is made.
     * @param request The request.
     */
    @Override
    protected  void prepareWebRequest(HttpWebRequest request)
    {
    	// TODO need to check
        //request.ClientCertificates = this.clientCertificates;
    	try {
			request.setClientCertificates(this.clientCertificates);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    /**
     * Gets the client certificates collection.
     * @return clientCertificates
     */
    public TrustManager getClientCertificates()
    {
       return this.clientCertificates; 
    }

}
