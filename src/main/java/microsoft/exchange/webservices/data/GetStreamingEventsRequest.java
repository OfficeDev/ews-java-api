/**************************************************************************
 * copyright file="GetStreamingEventsRequest.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the GetStreamingEventsRequest.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import javax.xml.stream.XMLStreamException;

/**
 * Defines the GetStreamingEventsRequest class. 
 */
class GetStreamingEventsRequest extends HangingServiceRequestBase {

	protected final static int HeartbeatFrequencyDefault = 45000; ////45s in ms
	private static int heartbeatFrequency = HeartbeatFrequencyDefault;

	private Iterable<String> subscriptionIds;
	private int connectionTimeout;

	/**
	 * Initializes a new instance of the GetStreamingEventsRequest class.
	 * @param service The service
	 * @param serviceObjectHandler The serviceObjectHandler
	 * @param subscriptionIds The subscriptionIds
	 * @param connectionTimeout The connectionTimeout
	 * @throws microsoft.exchange.webservices.data.ServiceVersionException
	 */
	protected GetStreamingEventsRequest(ExchangeService service,
			IHandleResponseObject serviceObjectHandler,
			Iterable<String> subscriptionIds,int connectionTimeout) 
	throws ServiceVersionException {
		super(service, serviceObjectHandler,
				GetStreamingEventsRequest.heartbeatFrequency);
		this.subscriptionIds = subscriptionIds;
		this.connectionTimeout = connectionTimeout;
	}

	/**
	 *  Gets the name of the XML element.
	 *  @return XmlElementNames
	 */
	@Override
	protected String getXmlElementName() {
		return XmlElementNames.GetStreamingEvents;
	}

	/**
	 * Gets the name of the response XML element.
	 * @return XmlElementNames
	 */
	@Override
	protected String getResponseXmlElementName() {
		return XmlElementNames.GetStreamingEventsResponse;
	}

	/**
	 * Writes the elements to XML writer.
	 * @param writer The writer
	 * @throws javax.xml.stream.XMLStreamException
	 * @throws ServiceXmlSerializationException 
	 */
	@Override
	protected void writeElementsToXml(EwsServiceXmlWriter writer) 
	throws ServiceXmlSerializationException, XMLStreamException {
		writer.writeStartElement(XmlNamespace.Messages, 
				XmlElementNames.SubscriptionIds);

		for (String id : this.subscriptionIds) {
			writer.writeElementValue(
					XmlNamespace.Types,
					XmlElementNames.SubscriptionId,
					id);
		}

		writer.writeEndElement();

		writer.writeElementValue(
				XmlNamespace.Messages,
				XmlElementNames.ConnectionTimeout,
				this.connectionTimeout);
	}
	
	/**
	 * Gets the request version.
	 * @return ExchangeVersion
	 */
	@Override
	protected ExchangeVersion getMinimumRequiredServerVersion() {
		return ExchangeVersion.Exchange2010_SP1;
	}

	/**
	 * Parses the response.
	 * @param reader The reader
	 * @return response
	 * @throws Exception 
	 */
	@Override
	protected Object parseResponse(EwsServiceXmlReader reader) 
	throws Exception {
		reader.readStartElement(XmlNamespace.Messages, 
				XmlElementNames.ResponseMessages);

		GetStreamingEventsResponse response = 
			new GetStreamingEventsResponse(this);
		response.loadFromXml(reader, XmlElementNames.
				GetStreamingEventsResponseMessage);

		reader.readEndElementIfNecessary(XmlNamespace.Messages,
				XmlElementNames.ResponseMessages);

		return response;
	}

	/**
	 * region Test hooks
	 * Allow test code to change heartbeat value	
	 */	
	protected static void setHeartbeatFrequency(int heartbeatFrequency) {
		GetStreamingEventsRequest.heartbeatFrequency = heartbeatFrequency;
	}


}
