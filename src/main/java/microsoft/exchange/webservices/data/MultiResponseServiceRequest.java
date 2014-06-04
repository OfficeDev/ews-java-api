/**************************************************************************
 * copyright file="MultiResponseServiceRequest.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the MultiResponseServiceRequest.java.
 **************************************************************************/

package microsoft.exchange.webservices.data;

import java.util.concurrent.FutureTask;

/***
 * Represents a service request that can have multiple responses.
 * 
 * @param <TResponse>
 *            The type of the response.
 */
abstract class MultiResponseServiceRequest<TResponse extends ServiceResponse>
		extends SimpleServiceRequestBase {

	/** The error handling mode. */
	private ServiceErrorHandling errorHandlingMode;

	/**
	 * * Parses the response.
	 * 
	 * @param reader
	 *            The reader.
	 * @return Response object.
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected Object parseResponse(EwsServiceXmlReader reader)
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
			// responses are added
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
						Strings.TooFewServiceReponsesReturned, this
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
	 * * Creates the service response.
	 * 
	 * @param service
	 *            The service.
	 * @param responseIndex
	 *            Index of the response.
	 * @return Service response.
	 * @throws Exception
	 *             the exception
	 */
	protected abstract TResponse createServiceResponse(ExchangeService service,
			int responseIndex) throws Exception;

	/***
	 * Gets the name of the response message XML element.
	 * 
	 * @return XML element name
	 */
	protected abstract String getResponseMessageXmlElementName();

	/***
	 * Gets the expected response message count.
	 * 
	 * @return Number of expected response messages.
	 */
	protected abstract int getExpectedResponseMessageCount();

	/**
	 * * Initializes a new instance.
	 * 
	 * @param service
	 *            The service.
	 * @param errorHandlingMode
	 *            Indicates how errors should be handled.
	 * @throws Exception 
	 */
	protected MultiResponseServiceRequest(ExchangeService service,
			ServiceErrorHandling errorHandlingMode)
			throws Exception {
		super(service);
		this.errorHandlingMode = errorHandlingMode;
	}

	/**
	 * * Executes this request.
	 * 
	 * @return Service response collection.
	 * @throws Exception
	 *             the exception
	 */
	protected ServiceResponseCollection<TResponse> execute() throws Exception {
		ServiceResponseCollection<TResponse> serviceResponses = 
			(ServiceResponseCollection<TResponse>)this
				.internalExecute();

		if (this.errorHandlingMode == ServiceErrorHandling.ThrowOnError) {
			EwsUtilities.EwsAssert(serviceResponses.getCount() == 1,
					"MultiResponseServiceRequest.Execute",
					"ServiceErrorHandling.ThrowOnError " + "error handling " + 
							"is only valid for singleton request");

			serviceResponses.getResponseAtIndex(0).throwIfNecessary();
		}

		return serviceResponses;
	}
	
	
    /** 
    * Ends executing this async request.
    * 
    * @param asyncResultThe async result 
    * @returns Service response collection.
    */
    @SuppressWarnings("unchecked")
	protected ServiceResponseCollection<TResponse> endExecute(IAsyncResult asyncResult) throws Exception
    {
        ServiceResponseCollection<TResponse> serviceResponses = (ServiceResponseCollection<TResponse>)this.endInternalExecute(asyncResult);

        if (this.errorHandlingMode == ServiceErrorHandling.ThrowOnError)
        {
            EwsUtilities.EwsAssert(
                serviceResponses.getCount() == 1,
                "MultiResponseServiceRequest.Execute",
                "ServiceErrorHandling.ThrowOnError error handling is only valid for singleton request");

             serviceResponses.getResponseAtIndex(0).throwIfNecessary();
        }

        return serviceResponses;
    }


	/***
	 * Gets a value indicating how errors should be handled.
	 * 
	 * @return A value indicating how errors should be handled.
	 */
	protected ServiceErrorHandling getErrorHandlingMode() {
		return this.errorHandlingMode;
	}

}
