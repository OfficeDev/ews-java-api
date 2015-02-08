package microsoft.exchange.webservices.data;

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
