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
import microsoft.exchange.webservices.data.core.response.UpdateInboxRulesResponse;
import microsoft.exchange.webservices.data.core.enumeration.misc.error.ServiceError;
import microsoft.exchange.webservices.data.property.complex.RuleOperation;
import microsoft.exchange.webservices.data.property.complex.RuleOperationError;
import microsoft.exchange.webservices.data.property.complex.RuleOperationErrorCollection;

/**
 * Represents an exception thrown when an error occurs as a result of calling
 * the UpdateInboxRules operation.
 */
public class UpdateInboxRulesException extends ServiceRemoteException {

  /**
   * Constant serialized ID used for compatibility.
   */
  private static final long serialVersionUID = 1L;

  /**
   * ServiceResponse when service operation failed remotely.
   */
  private ServiceResponse serviceResponse;

  /**
   * Rule operation error collection.
   */
  private RuleOperationErrorCollection errors;

  /**
   * Initializes a new instance of the UpdateInboxRulesException class.
   *
   * @param serviceResponse The rule operation service response.
   * @param ruleOperations  The original operations.
   */
  public UpdateInboxRulesException(UpdateInboxRulesResponse serviceResponse,
      Iterable<RuleOperation> ruleOperations) {
    super();
    this.serviceResponse = serviceResponse;
    this.errors = serviceResponse.getErrors();
    for (RuleOperationError error : this.errors) {
      error.setOperationByIndex(ruleOperations.iterator());
    }
  }

  /**
   * Gets the ServiceResponse for the exception.
   */
  public ServiceResponse getServiceResponse() {
    return this.serviceResponse;
  }

  /**
   * Gets the rule operation error collection.
   */
  public RuleOperationErrorCollection getErrors() {
    return this.errors;
  }

  /**
   * Gets the rule operation error code.
   */
  public ServiceError getErrorCode() {
    return this.serviceResponse.getErrorCode();
  }

  /**
   * Gets the rule operation error message.
   */
  public String getErrorMessage() {
    return this.serviceResponse.getErrorMessage();
  }

}
