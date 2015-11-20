/*
 * The MIT License
 * Copyright (c) 2012 Microsoft Corporation
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package microsoft.exchange.webservices.data.core;

import microsoft.exchange.webservices.data.credential.WebProxyCredentials;

/**
 * WebProxy is used for setting proxy details for proxy authentication schemes such as
 * basic, digest, NTLM, and Kerberos authentication.
 */
public class WebProxy {

  private String host;

  private int port;

  private WebProxyCredentials credentials;


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

  /**
   * Initializes a new instance to use specified proxy with default port 80.
   *
   * @param host proxy host.
   * @param credentials the credential to use for the proxy.
   */
  public WebProxy(String host, WebProxyCredentials credentials) {
    this.host = host;
    this.credentials = credentials;
  }

  /**
   * Initializes a new instance to use specified proxy details.
   *
   * @param host proxy host.
   * @param port proxy port.
   * @param credentials the credential to use for the proxy.
   */
  public WebProxy(String host, int port, WebProxyCredentials credentials) {
    this.host = host;
    this.port = port;
    this.credentials = credentials;
  }

  /**
   * Gets the Proxy Host.
   *
   * @return the host
   */
  public String getHost() {
    return this.host;
  }

  /**
   * Gets the Proxy Port.
   *
   * @return the port
   */
  public int getPort() {
    return this.port;
  }

  public boolean hasCredentials() {
    return credentials != null;
  }

  /**
   * Gets the Proxy Credentials.
   *
   * @return the proxy credential
   */
  public WebProxyCredentials getCredentials() {
    return credentials;
  }
}
