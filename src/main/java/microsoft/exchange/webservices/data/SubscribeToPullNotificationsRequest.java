/**************************************************************************
 * copyright file="SubscribeToPullNotificationsRequest.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the SubscribeToPullNotificationsRequest.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import javax.xml.stream.XMLStreamException;

/**
 * Represents a "pull" Subscribe request.
 */
class SubscribeToPullNotificationsRequest extends
		SubscribeRequest<PullSubscription> {

	/** The timeout. */
	private int timeout = 30;

	/**
	 * Instantiates a new subscribe to pull notifications request.
	 * 
	 * @param service
	 *            the service
	 * @throws Exception
	 *             the exception
	 */
	protected SubscribeToPullNotificationsRequest(ExchangeService service)
			throws Exception {

		super(service);

	}

	/**
	 * Gets the timeout.
	 * 
	 * @return the timeout
	 */
	public int getTimeout() {
		return this.timeout;
	}

	/**
	 * Sets the time out.
	 * 
	 * @param timeout
	 *            the new time out
	 */
	public void setTimeOut(int timeout) {
		this.timeout = timeout;
	}

	/**
	 * * Validate request.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	protected void validate() throws Exception {
		super.validate();
		if ((this.getTimeout() < 1) || (this.getTimeout() > 1440)) {
			throw new ArgumentException(String.format(
					Strings.InvalidTimeoutValue, this.getTimeout()));
		}
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
	@Override
	protected SubscribeResponse<PullSubscription> createServiceResponse(
			ExchangeService service, int responseIndex) throws Exception {
		return new SubscribeResponse<PullSubscription>(new PullSubscription(
				service));
	}

	/***
	 * Gets the minimum server version required to process this request.
	 * 
	 * @return Exchange server version.
	 */
	@Override
	protected ExchangeVersion getMinimumRequiredServerVersion() {
		return ExchangeVersion.Exchange2007_SP1;
	}

	/**
	 * Gets the name of the subscription XML element.
	 * 
	 * @return XML element name
	 */
	@Override
	protected String getSubscriptionXmlElementName() {
		return XmlElementNames.PullSubscriptionRequest;
	}

	/**
	 * * Reads response elements from XML.
	 * 
	 * @param writer
	 *            the writer
	 * @throws javax.xml.stream.XMLStreamException
	 *             the xML stream exception
	 * @throws microsoft.exchange.webservices.data.ServiceXmlSerializationException
	 *             the service xml serialization exception
	 */
	@Override
	protected void internalWriteElementsToXml(EwsServiceXmlWriter writer)
			throws XMLStreamException, ServiceXmlSerializationException {
		writer.writeElementValue(XmlNamespace.Types, XmlElementNames.Timeout,
				this.getTimeout());

	}
}
