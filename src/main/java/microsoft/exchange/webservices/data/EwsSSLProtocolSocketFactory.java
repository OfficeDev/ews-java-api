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

package microsoft.exchange.webservices.data;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.TrustManager;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

/**
 * <p>
 * EwsSSLProtocolSocketFactory can be used to creats SSL {@link java.net.Socket}s
 * that accept self-signed certificates.
 * </p>
 * <p>
 * This socket factory SHOULD NOT be used for productive systems
 * due to security reasons, unless it is a concious decision and
 * you are perfectly aware of security implications of accepting
 * self-signed certificates
 * </p>
 * <p/>
 * <p>
 * Example of using custom protocol socket factory for a specific host:
 * <pre>
 *     Protocol easyhttps = new Protocol("https", new EasySSLProtocolSocketFactory(), 443);
 *
 *     URI uri = new URI("https://localhost/", true);
 *     // use relative url only
 *     GetMethod httpget = new GetMethod(uri.getPathQuery());
 *     HostConfiguration hc = new HostConfiguration();
 *     hc.setHost(uri.getHost(), uri.getPort(), easyhttps);
 *     HttpClient client = new HttpClient();
 *     client.executeMethod(hc, httpget);
 *     </pre>
 * </p>
 * <p>
 * Example of using custom protocol socket factory per default instead of the standard one:
 * <pre>
 *     Protocol easyhttps = new Protocol("https", new EasySSLProtocolSocketFactory(), 443);
 *     Protocol.registerProtocol("https", easyhttps);
 *
 *     HttpClient client = new HttpClient();
 *     GetMethod httpget = new GetMethod("https://localhost/");
 *     client.executeMethod(httpget);
 *     </pre>
 * </p>
 *
 * <p>
 * DISCLAIMER: HttpClient developers DO NOT actively support this component.
 * The component is provided as a reference material, which may be inappropriate
 * for use without additional customization.
 * </p>
 */

public class EwsSSLProtocolSocketFactory extends SSLConnectionSocketFactory {

  /**
   * The SSL Context.
   */
  private SSLContext sslcontext = null;

  /**
   * Constructor for EasySSLProtocolSocketFactory.
   *
   * @throws SSLException
   */
  public EwsSSLProtocolSocketFactory(SSLContext context) {
    super(context, SSLConnectionSocketFactory.STRICT_HOSTNAME_VERIFIER);
    this.sslcontext = context;
  }



  public SSLContext getContext() {
    return this.sslcontext;
  }



  public static EwsSSLProtocolSocketFactory build(TrustManager trustManager)
      throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException {
    SSLContext sslContext = SSLContexts.createDefault();
    sslContext.init(
        null,
        new TrustManager[] {new EwsX509TrustManager(null, trustManager)},
        null
    );
    return new EwsSSLProtocolSocketFactory(sslContext);
  }

  public boolean equals(Object obj) {
    return ((obj != null) && obj.getClass().equals(EwsSSLProtocolSocketFactory.class));
  }

  public int hashCode() {
    return EwsSSLProtocolSocketFactory.class.hashCode();
  }
}
