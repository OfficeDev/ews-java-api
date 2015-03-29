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

package microsoft.exchange.webservices.data.autodiscover.enumeration;

/**
 * Defines the error codes that can be returned by the Autodiscover service.
 */
public enum AutodiscoverErrorCode {

  // There was no Error.
  /**
   * The No error.
   */
  NoError,

  // The caller must follow the e-mail address redirection that was returned
  // by Autodiscover.
  /**
   * The Redirect address.
   */
  RedirectAddress,

  // The caller must follow the URL redirection that was returned by
  // Autodiscover.
  /**
   * The Redirect url.
   */
  RedirectUrl,

  // The user that was passed in the request is invalid.
  /**
   * The Invalid user.
   */
  InvalidUser,

  // The request is invalid.
  /**
   * The Invalid request.
   */
  InvalidRequest,

  // A specified setting is invalid.
  /**
   * The Invalid setting.
   */
  InvalidSetting,

  // A specified setting is not available.
  /**
   * The Setting is not available.
   */
  SettingIsNotAvailable,

  // The server is too busy to process the request.
  /**
   * The Server busy.
   */
  ServerBusy,

  // The requested domain is not valid.
  /**
   * The Invalid domain.
   */
  InvalidDomain,

  // The organization is not federated.
  /**
   * The Not federated.
   */
  NotFederated,

  // Internal server error.
  /**
   * The Internal server error.
   */
  InternalServerError,
}
