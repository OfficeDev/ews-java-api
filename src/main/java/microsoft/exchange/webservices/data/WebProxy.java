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

/**
 * WebProxy is used for setting proxy details for proxy authentication schemes such as
 * basic, digest, NTLM, and Kerberos authentication.
 */
public class WebProxy {

  /**
   * proxy host.
   */
  private String host;

  /**
   * proxy post.
   */
  private int port;

  /**
   * Initializes a new instance to use specified proxy details.
   *
   * @param host proxy host.
   * @param port proxy port.
   */
  public WebProxy(String host, int port) {
    this.host = host;
    this.port = port;
  }

  /**
   * Initializes a new instance to use specified proxy with default port 80.
   *
   * @param host proxy host.
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

  /**
   * This method is used to set proxy credentials to a Web Request before
   * the request is made.
   *
   * @param user   The proxy username.
   * @param pwd    The proxy password.
   * @param domain The proxy domain.
   */
  public void setCredentials(String user, String pwd, String domain) {
    HttpProxyCredentials.setCredentials(user, pwd, domain);
    HttpProxyCredentials.isProxySet();
  }
}
