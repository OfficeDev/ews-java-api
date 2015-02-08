package microsoft.exchange.webservices.data;

import java.net.URISyntaxException;

/**
 * TokenCredentials provides credentials if you already have a token.
 */
public final class TokenCredentials extends WSSecurityBasedCredentials {

  /**
   * Initializes a new instance of the TokenCredentials class.
   *
   * @param securityToken The token.
   * @throws ArgumentNullException the argument null exception
   */
  public TokenCredentials(String securityToken) throws Exception {
    super(securityToken);
    EwsUtilities.validateParam(securityToken, "securityToken");

  }

  /**
   * This method is called to apply credentials to a service request before
   * the request is made.
   *
   * @param request The request.
   * @throws java.net.URISyntaxException the uRI syntax exception
   */
  @Override
  protected void prepareWebRequest(HttpWebRequest request)
      throws URISyntaxException {
    this.setEwsUrl(request.getUrl().toURI());
  }
}
