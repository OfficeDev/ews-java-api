/**************************************************************************
 * copyright file="SubscribeToPushNotificationsRequest.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the SubscribeToPushNotificationsRequest.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.net.URI;

import javax.xml.stream.XMLStreamException;

/**
 * The Class SubscribeToPushNotificationsRequest.
 */
class SubscribeToPushNotificationsRequest extends
		SubscribeRequest<PushSubscription> {

	/** The frequency. */
	private int frequency = 30;

	/** The url. */
	private URI url;

	/**
	 * Instantiates a new subscribe to push notifications request.
	 * 
	 * @param service
	 *            the service
	 * @throws Exception 
	 */
	protected SubscribeToPushNotificationsRequest(ExchangeService service)
			throws Exception {
		super(service);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see microsoft.exchange.webservices.SubscribeRequest#validate()
	 */
	@Override
	protected void validate() throws Exception {
		super.validate();
		EwsUtilities.validateParam(this.getUrl(), "Url");
		if ((this.getFrequency() < 1) || (this.getFrequency() > 1440)) {
			throw new ArgumentException(String.format(
					Strings.InvalidFrequencyValue, this.getFrequency()));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * microsoft.exchange.webservices.SubscribeRequest
	 * #getSubscriptionXmlElementName
	 * ()
	 */
	@Override
	protected String getSubscriptionXmlElementName() {
		return XmlElementNames.PushSubscriptionRequest;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * microsoft.exchange.webservices.SubscribeRequest
	 * #internalWriteElementsToXml
	 * (microsoft.exchange.webservices.EwsServiceXmlWriter)
	 */
	@Override
	protected void internalWriteElementsToXml(EwsServiceXmlWriter writer)
			throws XMLStreamException, ServiceXmlSerializationException {
		writer.writeElementValue(XmlNamespace.Types,
				XmlElementNames.StatusFrequency, this.getFrequency());
		writer.writeElementValue(XmlNamespace.Types, XmlElementNames.URL, this
				.getUrl().toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seemicrosoft.exchange.webservices.MultiResponseServiceRequest#
	 * createServiceResponse(microsoft.exchange.webservices.ExchangeService,
	 * int)
	 */
	@Override
	protected SubscribeResponse<PushSubscription> createServiceResponse(
			ExchangeService service, int responseIndex) throws Exception {
		return new SubscribeResponse<PushSubscription>(new PushSubscription(
				service));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seemicrosoft.exchange.webservices.ServiceRequestBase#
	 * getMinimumRequiredServerVersion()
	 */
	@Override
	protected ExchangeVersion getMinimumRequiredServerVersion() {
		return ExchangeVersion.Exchange2007_SP1;
	}

	/**
	 * Gets the frequency.
	 * 
	 * @return the frequency
	 */
	public int getFrequency() {
		return this.frequency;
	}

	/**
	 * Sets the frequency.
	 * 
	 * @param frequency
	 *            the new frequency
	 */
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	/**
	 * Gets the url.
	 * 
	 * @return the url
	 */
	public URI getUrl() {
		return this.url;
	}

	/**
	 * Sets the url.
	 * 
	 * @param url
	 *            the new url
	 */
	public void setUrl(URI url) {
		this.url = url;
	}

}
