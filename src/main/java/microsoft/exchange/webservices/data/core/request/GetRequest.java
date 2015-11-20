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

import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.PropertySet;
import microsoft.exchange.webservices.data.core.enumeration.service.error.ServiceErrorHandling;
import microsoft.exchange.webservices.data.core.response.ServiceResponse;
import microsoft.exchange.webservices.data.core.service.ServiceObject;
import microsoft.exchange.webservices.data.core.enumeration.service.ServiceObjectType;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceLocalException;

/**
 * Represents an abstract Get request.
 *
 * @param <TServiceObject> the generic type
 * @param <TResponse>      the generic type
 */
abstract class GetRequest<TServiceObject extends ServiceObject,
    TResponse extends ServiceResponse>
    extends MultiResponseServiceRequest<TResponse> {

  /**
   * The property set.
   */
  private PropertySet propertySet;

  /**
   * Initializes a new instance of the class.
   *
   * @param service           the service
   * @param errorHandlingMode the error handling mode
   * @throws Exception
   */
  protected GetRequest(ExchangeService service,
      ServiceErrorHandling errorHandlingMode)
      throws Exception {
    super(service, errorHandlingMode);
  }

  /**
   * Validate request.
   *
   * @throws ServiceLocalException the service local exception
   * @throws Exception             the exception
   */
  protected void validate() throws ServiceLocalException, Exception {
    super.validate();
    EwsUtilities.validateParam(this.propertySet, "PropertySet");
    this.propertySet
        .validateForRequest(this, false /* summaryPropertiesOnly */);
  }

  /**
   * Gets the type of the service object this request applies to.
   *
   * @return The type of service object the request applies to
   */
  protected abstract ServiceObjectType getServiceObjectType();

  /**
   * Gets the type of the service object this request applies to.
   *
   * @param writer the writer
   * @throws Exception
   */
  protected void writeElementsToXml(EwsServiceXmlWriter writer)
      throws Exception {
    this.propertySet.writeToXml(writer, this.getServiceObjectType());
  }

  /**
   * Gets  the property set.
   *
   * @return the property set
   */
  public PropertySet getPropertySet() {
    return this.propertySet;
  }

  /**
   * Sets the property set.
   *
   * @param propertySet the new property set
   */
  public void setPropertySet(PropertySet propertySet) {
    this.propertySet = propertySet;
  }
}
