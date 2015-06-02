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

import microsoft.exchange.webservices.data.autodiscover.exception.AutodiscoverLocalException;

/**
 *
 * The Class MaximumRedirectionHopsExceededException.
 *
 * @see microsoft.exchange.webservices.data.autodiscover.AutodiscoverService
 */
public class MaximumRedirectionHopsExceededException extends AutodiscoverLocalException {

  /**
   * Constant serialized ID used for compatibility.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Initializes a new instance of the class.
   */
  public MaximumRedirectionHopsExceededException() {
  }

  /**
   * Initializes a new instance of the class.
   *
   * @param message the message
   */
  public MaximumRedirectionHopsExceededException(String message) {
    super(message);
  }

  /**
   * Initializes a new instance of the class.
   *
   * @param message        the message
   * @param innerException the inner exception
   */
  public MaximumRedirectionHopsExceededException(String message, Exception innerException) {
    super(message, innerException);
  }

}
