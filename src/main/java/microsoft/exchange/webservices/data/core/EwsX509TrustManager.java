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

/**
 * EwsX509TrustManager is used for SSL handshake.
 *
 */
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

class EwsX509TrustManager implements X509TrustManager {
  /**
   * The Standard TrustManager.
   */
  private X509TrustManager standardTrustManager = null;

  /**
   * Constructor for EasyX509TrustManager.
   */
  public EwsX509TrustManager(KeyStore keystore, TrustManager trustManager)
      throws NoSuchAlgorithmException, KeyStoreException {
    super();
    if (trustManager == null) {
      TrustManagerFactory factory =
          TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
      factory.init(keystore);
      TrustManager[] trustmanagers = factory.getTrustManagers();
      if (trustmanagers.length == 0) {
        throw new NoSuchAlgorithmException("no trust manager found");
      }
      this.standardTrustManager = (X509TrustManager) trustmanagers[0];
    } else {
      standardTrustManager = (X509TrustManager) trustManager;
    }
  }

  /**
   * @see javax.net.ssl.X509TrustManager#checkClientTrusted(java.security.cert.X509Certificate[], String authType)
   */
  public void checkClientTrusted(X509Certificate[] certificates, String authType)
      throws CertificateException {
    standardTrustManager.checkClientTrusted(certificates, authType);
  }

  /**
   * @see javax.net.ssl.X509TrustManager#checkServerTrusted(java.security.cert.X509Certificate[], String authType)
   */
  public void checkServerTrusted(X509Certificate[] certificates, String authType)
      throws CertificateException {

    if ((certificates != null) && (certificates.length == 1)) {
      certificates[0].checkValidity();
    } else {
      standardTrustManager.checkServerTrusted(certificates, authType);
    }
  }

  /**
   * @see javax.net.ssl.X509TrustManager#getAcceptedIssuers()
   */
  public X509Certificate[] getAcceptedIssuers() {
    return this.standardTrustManager.getAcceptedIssuers();
  }
}
