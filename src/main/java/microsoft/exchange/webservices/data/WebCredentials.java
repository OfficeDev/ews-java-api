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
 * WebCredentials is used for password-based authentication schemes such as
 * basic, digest, NTLM, and Kerberos authentication.
 */
public final class WebCredentials extends ExchangeCredentials {

  /**
   * The domain.
   */
  private String domain;

  /**
   * The user.
   */
  private String user;

  /**
   * The pwd.
   */
  private String pwd;

  /**
   * The use default credentials.
   */
  private boolean useDefaultCredentials = true;

  /**
   * Gets the domain.
   *
   * @return the domain
   */
  public String getDomain() {
    return domain;
  }

  /**
   * Gets the user.
   *
   * @return the user
   */
  public String getUser() {
    return user;
  }

  /**
   * Gets the pwd.
   *
   * @return the pwd
   */
  public String getPwd() {
    return pwd;
  }

  /**
   * Checks if is use default credentials.
   *
   * @return true, if is use default credentials
   */
  public boolean isUseDefaultCredentials() {
    return useDefaultCredentials;
  }

  /**
   * Initializes a new instance to use default network credentials.
   */
  public WebCredentials() {
    useDefaultCredentials = true;
    this.user = null;
    this.pwd = null;
    this.domain = null;
  }

  /**
   * Initializes a new instance to use specified credentials.
   *
   * @param userName Account user name.
   * @param password Account password.
   * @param domain   Account domain.
   */
  public WebCredentials(String userName, String password, String domain) {
    if (userName == null || password == null) {
      throw new IllegalArgumentException(
          "User name or password can not be null");
    }

    this.domain = domain;
    this.user = userName;
    this.pwd = password;
    useDefaultCredentials = false;
  }

  /**
   * Initializes a new instance to use specified credentials.
   *
   * @param username The user name.
   * @param password The password.
   */
  public WebCredentials(String username, String password) {
    this(username, password, "");
  }

  /**
   * This method is called to apply credentials to a service request before
   * the request is made.
   *
   * @param request The request.
   */
  @Override
  protected void prepareWebRequest(HttpWebRequest request) {
    if (useDefaultCredentials) {
      request.setUseDefaultCredentials(true);
    } else {
      request.setCredentials(domain, user, pwd);
    }
  }
}
