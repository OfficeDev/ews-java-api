package microsoft.exchange.webservices.data;

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
   * @param credentials the credentials to use for the proxy.
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
   * @param credentials the credentials to use for the proxy.
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

  public boolean hasCredentials() {
    return credentials != null;
  }

  /**
   * Gets the Proxy Credentials.
   *
   * @return the proxy credentials
   */
  public WebProxyCredentials getCredentials() {
    return credentials;
  }
}
