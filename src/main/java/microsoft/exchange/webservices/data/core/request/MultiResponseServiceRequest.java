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

import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.service.error.ServiceErrorHandling;
import microsoft.exchange.webservices.data.core.response.ServiceResponse;
import microsoft.exchange.webservices.data.core.response.ServiceResponseCollection;
import microsoft.exchange.webservices.data.core.enumeration.service.ServiceResult;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.exception.service.remote.ServiceResponseException;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlDeserializationException;
import microsoft.exchange.webservices.data.misc.IAsyncResult;

/**
 * Represents a service request that can have multiple response.
 *
 * @param <TResponse> The type of the response.
 */
public abstract class MultiResponseServiceRequest<TResponse extends ServiceResponse>
    extends SimpleServiceRequestBase<ServiceResponseCollection<TResponse>> {

  /**
   * The error handling mode.
   */
  private ServiceErrorHandling errorHandlingMode;

  /**
   * {@inheritDoc}
   */
  @Override
  protected ServiceResponseCollection<TResponse> parseResponse(EwsServiceXmlReader reader)
      throws Exception {
    ServiceResponseCollection<TResponse> serviceResponses =
        new ServiceResponseCollection<TResponse>();

    reader.readStartElement(XmlNamespace.Messages,
        XmlElementNames.ResponseMessages);

    for (int i = 0; i < this.getExpectedResponseMessageCount(); i++) {
      // Read ahead to see if we've reached the end of the response
      // messages early.
      reader.read();
      if (reader.isEndElement(XmlNamespace.Messages,
          XmlElementNames.ResponseMessages)) {
        break;
      }

      TResponse response = this.createServiceResponse(
          reader.getService(), i);

      response.loadFromXml(reader, this
          .getResponseMessageXmlElementName());

      // Add the response to the list after it has been deserialized
      // because the response list updates an overall result as individual
      // response are added
      // to it.
      serviceResponses.add(response);
    }
    // Bug E14:131334 -- if there's a general error in batch processing,
    // the server will return a single response message containing the error
    // (for example, if the SavedItemFolderId is bogus in a batch CreateItem
    // call). In this case, throw a ServiceResponsException. Otherwise this
    // is an unexpected server error.
    if (serviceResponses.getCount() < this
        .getExpectedResponseMessageCount()) {
      if ((serviceResponses.getCount() == 1) &&
          (serviceResponses.getResponseAtIndex(0).getResult() ==
              ServiceResult.Error)) {
        throw new ServiceResponseException(serviceResponses
            .getResponseAtIndex(0));
      } else {
        throw new ServiceXmlDeserializationException(String.format(
            "The service was expected to return %s response of type '%d', but %d response were received.", this
                .getResponseMessageXmlElementName(), this
                .getExpectedResponseMessageCount(),
            serviceResponses.getCount()));
      }
    }

    reader.readEndElementIfNecessary(XmlNamespace.Messages,
        XmlElementNames.ResponseMessages);

    return serviceResponses;
  }

  /**
   * Creates the service response.
   *
   * @param service       The service.
   * @param responseIndex Index of the response.
   * @return Service response.
   * @throws Exception the exception
   */
  protected abstract TResponse createServiceResponse(ExchangeService service,
      int responseIndex) throws Exception;

  /**
   * Gets the name of the response message XML element.
   *
   * @return XML element name
   */
  protected abstract String getResponseMessageXmlElementName();

  /**
   * Gets the expected response message count.
   *
   * @return Number of expected response messages.
   */
  protected abstract int getExpectedResponseMessageCount();

  /**
   * Initializes a new instance.
   *
   * @param service           The service.
   * @param errorHandlingMode Indicates how errors should be handled.
   * @throws Exception
   */
  protected MultiResponseServiceRequest(ExchangeService service,
      ServiceErrorHandling errorHandlingMode)
      throws Exception {
    super(service);
    this.errorHandlingMode = errorHandlingMode;
  }

  /**
   * Executes this request.
   *
   * @return Service response collection.
   * @throws Exception the exception
   */
  public ServiceResponseCollection<TResponse> execute() throws Exception {
    ServiceResponseCollection<TResponse> serviceResponses = internalExecute();

    if (this.errorHandlingMode == ServiceErrorHandling.ThrowOnError) {
      EwsUtilities.ewsAssert(serviceResponses.getCount() == 1, "MultiResponseServiceRequest.Execute",
                             "ServiceErrorHandling.ThrowOnError " + "error handling " +
                             "is only valid for singleton request");

      serviceResponses.getResponseAtIndex(0).throwIfNecessary();
    }

    return serviceResponses;
  }

  /**
   * Ends executing this async request.
   *
   * @param asyncResult The async result
   * @return Service response collection.
   * @throws Exception on error
   */
  public ServiceResponseCollection<TResponse> endExecute(IAsyncResult asyncResult) throws Exception {
    ServiceResponseCollection<TResponse> serviceResponses = endInternalExecute(asyncResult);

    if (this.errorHandlingMode == ServiceErrorHandling.ThrowOnError) {
      EwsUtilities.ewsAssert(serviceResponses.getCount() == 1, "MultiResponseServiceRequest.Execute",
                             "ServiceErrorHandling.ThrowOnError error handling is only valid for singleton request");

      serviceResponses.getResponseAtIndex(0).throwIfNecessary();
    }

    return serviceResponses;
  }

  /**
   * Gets a value indicating how errors should be handled.
   *
   * @return A value indicating how errors should be handled.
   */
  protected ServiceErrorHandling getErrorHandlingMode() {
    return this.errorHandlingMode;
  }

}
