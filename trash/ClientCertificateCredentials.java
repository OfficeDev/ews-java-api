/**************************************************************************
 Exchange Web Services Java API
 Copyright (c) Microsoft Corporation
 All rights reserved.
 MIT License
 Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the ""Software""), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 THE SOFTWARE IS PROVIDED *AS IS*, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
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
