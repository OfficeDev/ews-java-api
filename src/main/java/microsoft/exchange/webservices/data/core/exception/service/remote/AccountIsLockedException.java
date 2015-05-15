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

package microsoft.exchange.webservices.data.core.exception.service.remote;

import microsoft.exchange.webservices.data.core.exception.service.remote.ServiceRemoteException;

import java.net.URI;

/**
 * Represents an error that occurs when the account that is
 * being accessed is locked and requires user interaction to be unlocked.
 */
public class AccountIsLockedException extends ServiceRemoteException {

  /**
   * Constant serialized ID used for compatibility.
   */
  private static final long serialVersionUID = 1L;

  private URI accountUnlockUrl;


  /**
   * Initializes a new instance of the AccountIsLockedException class.
   *
   * @param message          Error message text.
   * @param accountUnlockUrl URL for client to visit to unlock account.
   */
  public AccountIsLockedException(String message, URI accountUnlockUrl,
      Exception innerException) {

    super(message, innerException);
    this.setAccountUnlockUrl(accountUnlockUrl);
  }

  /**
   * Gets the URL of a web page where the user
   * can navigate to unlock his or her account.
   */
  public URI getAccountUnlockUrl() {
    return accountUnlockUrl;
  }


  /**
   * Sets the URL of a web page where the
   * user can navigate to unlock his or her account.
   */
  private void setAccountUnlockUrl(URI value) {
    this.accountUnlockUrl = value;
  }
}
