/**************************************************************************
 * copyright file="SubscribeToStreamingNotificationsRequest.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the SubscribeToStreamingNotificationsRequest.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/***
 * Defines the SubscribeToStreamingNotificationsRequest class. 
 */
class SubscribeToStreamingNotificationsRequest extends 
SubscribeRequest<StreamingSubscription> {

	/**
	 * Initializes a new instance of the 
	 * SubscribeToStreamingNotificationsRequest class.
	 * @param service The service
	 * @throws Exception 
	 */
	protected SubscribeToStreamingNotificationsRequest(ExchangeService service)
	throws Exception {
		super(service);
	}

	/**
	 * Validate request.
	 * @throws Exception 
	 */
	@Override
	protected  void validate() throws Exception {
		super.validate();

		if (!(this.getWatermark()== null || this.getWatermark().isEmpty())) {
			throw new ArgumentException(
					"Watermarks cannot be used with StreamingSubscriptions.");
		}
	}


	/**
	 * Gets the name of the subscription XML element.
	 * @return XmlElementsNames
	 */
	@Override
	protected String getSubscriptionXmlElementName() {
		return XmlElementNames.StreamingSubscriptionRequest;
	}

	/**
	 * Internals the write elements to XML.
	 * @param writer The writer
	 */
	@Override
	protected void internalWriteElementsToXml(EwsServiceXmlWriter writer) {
	}

	/**
	 * Creates the service response.
	 * @param service The service
	 * @param responseIndex The responseIndex
	 * @return SubscribeResponse
	 * @throws Exception 
	 */
	@Override
	protected SubscribeResponse<StreamingSubscription> createServiceResponse(ExchangeService service, 
			int responseIndex) throws Exception {
		return new SubscribeResponse<StreamingSubscription>(
				new StreamingSubscription(service));
	}

	/**
	 * Gets the request version.
	 * @return ExchangeVersion
	 */
	@Override
	protected ExchangeVersion getMinimumRequiredServerVersion() {
		return ExchangeVersion.Exchange2010_SP1;
	}
}
