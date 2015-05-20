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

package microsoft.exchange.webservices.data.core.request;

import microsoft.exchange.webservices.data.core.enumeration.misc.HangingRequestDisconnectReason;

/**
 * Represents a collection of arguments for the
 * HangingServiceRequestBase.HangingRequestDisconnectHandler
 * delegate method.
 */
public final class HangingRequestDisconnectEventArgs {

  /**
   * Initializes a new instance of the
   * HangingRequestDisconnectEventArgs class.
   *
   * @param reason    The reason.
   * @param exception The exception.
   */
  public HangingRequestDisconnectEventArgs(HangingRequestDisconnectReason reason, Exception exception) {
    this.reason = reason;
    this.exception = exception;
  }

  private HangingRequestDisconnectReason reason;

  /**
   * Gets the reason that the user was disconnected.
   *
   * @return reason The reason.
   */
  public HangingRequestDisconnectReason getReason() {
    return reason;
  }

  /**
   * Sets the reason that the user was disconnected.
   *
   * @param value The reason.
   */
  protected void setReason(HangingRequestDisconnectReason value) {
    reason = value;
  }

  private Exception exception;

  /**
   * Gets the exception that caused the disconnection. Can be null.
   *
   * @return exception The Exception.
   */
  public Exception getException() {
    return exception;
  }

  /**
   * Sets the exception that caused the disconnection. Can be null.
   *
   * @param value The Exception.
   */
  protected void setException(Exception value) {
    exception = value;
  }
}
