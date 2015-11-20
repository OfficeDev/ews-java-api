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

import microsoft.exchange.webservices.data.core.response.ServiceResponse;
import microsoft.exchange.webservices.data.core.enumeration.misc.error.ServiceError;

/**
 * Represents a remote service exception that has a single response.
 */
public class ServiceResponseException extends ServiceRemoteException {

  /**
   * Constant serialized ID used for compatibility.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Error details Value keys.
   */
  private static final String ExceptionClassKey = "ExceptionClass";

  /**
   * The Exception message key.
   */
  private static final String ExceptionMessageKey = "ExceptionMessage";

  /**
   * The Stack trace key.
   */
  private static final String StackTraceKey = "StackTrace";

  /**
   * ServiceResponse when service operation failed remotely.
   */
  private ServiceResponse response;

  /**
   * Initializes a new instance.
   *
   * @param response the response
   */
  public ServiceResponseException(ServiceResponse response) {
    this.response = response;
  }

  /**
   * Gets the ServiceResponse for the exception.
   *
   * @return the response
   */
  public ServiceResponse getResponse() {
    return response;
  }

  /**
   * Gets the service error code.
   *
   * @return the error code
   */
  public ServiceError getErrorCode() {
    return this.response.getErrorCode();
  }

  /**
   * Gets a message that describes the current exception.
   *
   * @return The error message that explains the reason for the exception.
   */

  public String getMessage() {

    // Bug E14:134792 -- Special case for Internal Server Error. If the
    // server returned
    // stack trace information, include it in the exception message.
    if (this.response.getErrorCode() == ServiceError.ErrorInternalServerError) {
      String exceptionClass;
      String exceptionMessage;
      String stackTrace;

      if (this.response.getErrorDetails().containsKey(ExceptionClassKey) &&
          this.response.getErrorDetails().containsKey(
              ExceptionMessageKey) &&
          this.response.getErrorDetails().containsKey(
              StackTraceKey)) {
        exceptionClass = this.response.getErrorDetails().get(
            ExceptionClassKey);
        exceptionMessage = this.response.getErrorDetails().get(
            ExceptionMessageKey);
        stackTrace = this.response.getErrorDetails().get(StackTraceKey);

        // return
        return String.format("%s -- Server Error: %s: %s %s", this.response
                .getErrorMessage(), exceptionClass,
            exceptionMessage, stackTrace);
      }
    }

    return this.response.getErrorMessage();
  }
}
