/**************************************************************************
 Exchange Web Services Java API
 Copyright (c) Microsoft Corporation
 All rights reserved.
 MIT License
 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in
 all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 THE SOFTWARE.
 **************************************************************************/

package microsoft.exchange.webservices.data;

/**
 * The Class HttpProxyCredentials.
 */
class HttpProxyCredentials {

  /**
   * The user name.
   */
  private static String userName;

  /**
   * The password.
   */
  private static String password;

  /**
   * The domain.
   */
  private static String domain;

  /**
   * The is proxy set.
   */
  private static boolean isProxySet;

  /**
   * Sets the credentials.
   *
   * @param user the user
   * @param pwd  the password
   * @param dmn  the domain
   */
  public static void setCredentials(String user, String pwd, String dmn) {
    userName = user;
    password = pwd;
    domain = dmn;
    isProxySet = true;

  }

  /**
   * Clear proxy credentials.
   */
  public static void clearProxyCredentials() {
    isProxySet = false;
  }

  /**
   * Checks if is proxy set.
   *
   * @return true, if is proxy set
   */
  public static boolean isProxySet() {
    return isProxySet;
  }

  /**
   * Gets the user name.
   *
   * @return the user name
   */
  public static String getUserName() {
    if (isProxySet) {
      return userName;
    } else {
      return null;
    }
  }

  /**
   * Gets the password.
   *
   * @return the password
   */
  public static String getPassword() {
    if (isProxySet) {
      return password;
    } else {
      return null;
    }

  }

  /**
   * Gets the domain.
   *
   * @return the domain
   */
  public static String getDomain() {
    if (isProxySet) {
      return domain;
    } else {
      return null;
    }

  }
}
