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

package microsoft.exchange.webservices.data.autodiscover.exception;

import microsoft.exchange.webservices.data.autodiscover.exception.error.AutodiscoverError;
import microsoft.exchange.webservices.data.core.exception.service.remote.ServiceRemoteException;

/**
 * Represents an exception that is thrown when the Autodiscover service returns
 * an error.
 */
public class AutodiscoverRemoteException extends ServiceRemoteException {

  /**
   * Constant serialized ID used for compatibility.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The error.
   */
  private AutodiscoverError error;

  /**
   * Initializes a new instance of the class.
   *
   * @param error the error
   */
  public AutodiscoverRemoteException(AutodiscoverError error) {
    super();
    this.error = error;
  }

  /**
   * Initializes a new instance of the class.
   *
   * @param message the message
   * @param error   the error
   */
  public AutodiscoverRemoteException(String message, AutodiscoverError error) {
    super(message);
    this.error = error;
  }

  /**
   * Initializes a new instance of the class.
   *
   * @param message        the message
   * @param error          the error
   * @param innerException the inner exception
   */
  public AutodiscoverRemoteException(String message, AutodiscoverError error,
      Exception innerException) {
    super(message, innerException);
    this.error = error;
  }

  /**
   * Gets the error.
   *
   * @return the error
   */
  public AutodiscoverError getError() {
    return this.error;
  }
}
