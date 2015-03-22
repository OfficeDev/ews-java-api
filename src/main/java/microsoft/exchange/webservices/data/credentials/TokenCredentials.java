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

package microsoft.exchange.webservices.data.credentials;

import microsoft.exchange.webservices.data.EwsUtilities;
import microsoft.exchange.webservices.data.HttpWebRequest;

import java.net.URISyntaxException;

/**
 * TokenCredentials provides credentials if you already have a token.
 */
public final class TokenCredentials extends WSSecurityBasedCredentials {

  /**
   * Initializes a new instance of the TokenCredentials class.
   *
   * @param securityToken The token.
   * @throws microsoft.exchange.webservices.data.ArgumentNullException the argument null exception
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
