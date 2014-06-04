/**************************************************************************
 * copyright file="GetEventsRequest.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the GetEventsRequest.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import javax.xml.stream.XMLStreamException;

/**
 * GetEvents request.
 * 
 */
class GetEventsRequest extends MultiResponseServiceRequest<GetEventsResponse> {

	/** The subscription id. */
	private String subscriptionId;

	/** The watermark. */
	private String watermark;

	/**
	 * Initializes a new instance.
	 * 
	 * @param service
	 *            the service
	 * @throws Exception 
	 */
	protected GetEventsRequest(ExchangeService service)
			throws Exception {
		super(service, ServiceErrorHandling.ThrowOnError);
	}

	/**
	 * Creates the service response.
	 * 
	 * @param service
	 *            the service
	 * @param responseIndex
	 *            the response index
	 * @return Service response
	 */
	@Override
	protected GetEventsResponse createServiceResponse(ExchangeService service,
			int responseIndex) {
		return new GetEventsResponse();
	}

	/**
	 * Gets the expected response message count. 	 
	 * @return Response count
	 */
	@Override
	protected int getExpectedResponseMessageCount() {
		return 1;
	}

	/**
	 * Gets the name of the XML element.
	 * 
	 * @return XML element name
	 */
	@Override
	protected String getXmlElementName() {
		return XmlElementNames.GetEvents;
	}

	/**
	 * Gets the name of the response XML element.
	 * 
	 * @return XML element name
	 */
	@Override
	protected String getResponseXmlElementName() {
		return XmlElementNames.GetEventsResponse;
	}

	/**
	 * Gets the name of the response message XML element.
	 * 
	 * @return XML element name
	 */
	@Override
	protected String getResponseMessageXmlElementName() {
		return XmlElementNames.GetEventsResponseMessage;
	}

	/**
	 * Validates the request.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	protected void validate() throws Exception {
		super.validate();
		EwsUtilities.validateNonBlankStringParam(this.
				getSubscriptionId(), "SubscriptionId");
		EwsUtilities.validateNonBlankStringParam(this.
				getWatermark(), "Watermark");
	}

	/**
	 * Writes the elements to XML writer.
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
		writer.writeElementValue(XmlNamespace.Messages,
				XmlElementNames.Watermark, this.getWatermark());
	}

	/**
	 * Gets the request version.
	 * 
	 * @return Earliest Exchange version in which this request is supported
	 */
	@Override
	protected ExchangeVersion getMinimumRequiredServerVersion() {
		return ExchangeVersion.Exchange2007_SP1;
	}

	/**
	 * gets the subscriptionId.
	 * 
	 * @return the subscriptionId.
	 * 
	 */
	public String getSubscriptionId() {
		return subscriptionId;
	}

	/**
	 * Sets the subscriptionId.
	 * 
	 * @param subscriptionId
	 *            the subscriptionId.
	 */
	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	/**
	 * gets the watermark.
	 * 
	 * @return the watermark.
	 * 
	 */
	public String getWatermark() {
		return watermark;
	}

	/**
	 * Sets the watermark.
	 * 
	 * @param watermark
	 *            the new watermark
	 */
	public void setWatermark(String watermark) {
		this.watermark = watermark;
	}
}
