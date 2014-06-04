/**************************************************************************
 * copyright file="UnsubscribeRequest.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the UnsubscribeRequest.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import javax.xml.stream.XMLStreamException;

/**
 * The Class UnsubscribeRequest.
 */
class UnsubscribeRequest extends MultiResponseServiceRequest<ServiceResponse> {

	/** The subscription id. */
	private String subscriptionId;

	/**
	 * Instantiates a new unsubscribe request.
	 * 
	 * @param service
	 *            the service
	 * @throws Exception 
	 */
	protected UnsubscribeRequest(ExchangeService service)
			throws Exception {
		super(service, ServiceErrorHandling.ThrowOnError);
	}

	/**
	 * Creates service response.
	 * 
	 * @param service
	 *            the service
	 * @param responseIndex
	 *            the response index
	 * @return Service response.
	 */
	@Override
	protected ServiceResponse createServiceResponse(ExchangeService service,
			int responseIndex) {
		return new ServiceResponse();
	}

	/**
	 * Gets the expected response message count.
	 * 
	 * @return Number of expected response messages.
	 */
	@Override
	protected int getExpectedResponseMessageCount() {
		return 1;
	}

	/**
	 * Gets the name of the XML element.
	 * 
	 * @return Xml element name.
	 */
	@Override
	protected String getXmlElementName() {
		return XmlElementNames.Unsubscribe;
	}

	/**
	 * Gets the name of the response XML element.
	 * 
	 * @return Xml element name.
	 */
	@Override
	protected String getResponseXmlElementName() {
		return XmlElementNames.UnsubscribeResponse;
	}

	/**
	 * Gets the name of the response message XML element.
	 * 
	 * @return Xml element name.
	 */
	@Override
	protected String getResponseMessageXmlElementName() {
		return XmlElementNames.UnsubscribeResponseMessage;
	}

	/**
	 * Validate the request.
	 * 
	 * @throws ServiceLocalException
	 *             the service local exception
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void validate() throws ServiceLocalException, Exception {
		super.validate();
		EwsUtilities.validateNonBlankStringParam(this.
				getSubscriptionId(), "SubscriptionId");

	}

	/**
	 * Writes XML elements.
	 * 
	 * @param writer
	 *            the writer
	 * @throws javax.xml.stream.XMLStreamException
	 *             the xML stream exception
	 * @throws ServiceXmlSerializationException
	 *             the service xml serialization exception
	 */
	@Override
	protected void writeElementsToXml(EwsServiceXmlWriter writer)
			throws XMLStreamException, ServiceXmlSerializationException {
		writer.writeElementValue(XmlNamespace.Messages,
				XmlElementNames.SubscriptionId, this.getSubscriptionId());
	}

	/**
	 * Gets the request version.
	 * 
	 * @return Earliest Exchange version in which this request is supported.
	 */
	@Override
	protected ExchangeVersion getMinimumRequiredServerVersion() {
		return ExchangeVersion.Exchange2007_SP1;
	}

	/**
	 * Gets the subscription id.
	 * 
	 * @return the subscription id
	 */
	public String getSubscriptionId() {
		return this.subscriptionId;
	}

	/**
	 * Sets the subscription id.
	 * 
	 * @param subscriptionId
	 *            the new subscription id
	 */
	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

}
