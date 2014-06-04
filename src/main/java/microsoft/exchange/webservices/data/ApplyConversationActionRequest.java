/**************************************************************************
 * copyright file="ApplyConversationActionRequest.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ApplyConversationActionRequest class.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a request to a Apply Conversation Action operation
 */
final class ApplyConversationActionRequest extends 
MultiResponseServiceRequest<ServiceResponse> {

	private List<ConversationAction> conversationActions =
		new ArrayList<ConversationAction>();

	public List<ConversationAction> getConversationActions() {
		return this.conversationActions; 
	}

	/**
	 * Initializes a new instance of the ApplyConversationActionRequest class
	 * @param service The service.
	 * @param errorHandlingMode Indicates how errors should be handled.
	 * @throws Exception 
	 */
	protected ApplyConversationActionRequest(ExchangeService service, 
			ServiceErrorHandling errorHandlingMode) throws Exception {  
		super(service, errorHandlingMode);
	}

	/**
	 * Creates the service response.
	 * @param service The service.
	 * @param responseIndex Index of the response.
	 * @return Service response.
	 */
	@Override
	protected ServiceResponse createServiceResponse(ExchangeService service, 
			int responseIndex) {
		return new ServiceResponse();
	}

	/**
	 * Gets the expected response message count.
	 * @return Number of expected response messages.
	 */
	@Override
	protected int getExpectedResponseMessageCount() {
		return this.conversationActions.size();
	}

	/**
	 * Validate request.
	 * @throws Exception 
	 */
	@Override
	protected void validate() throws Exception {
		super.validate();
		EwsUtilities.validateParamCollection(this.
				conversationActions.iterator(),
				"conversationActions");
		for (int iAction = 0; iAction < this.getConversationActions().size(); iAction++)
		{
			this.getConversationActions().get(iAction).validate();			
		}
	}


	/**
	 * Writes XML elements.
	 * @param writer The writer.
	 * @throws Exception 
	 */
	@Override
	protected void writeElementsToXml(EwsServiceXmlWriter writer) 
	throws Exception {
		writer.writeStartElement(
				XmlNamespace.Messages,
				XmlElementNames.ConversationActions);
		for (int iAction = 0; iAction < this.getConversationActions().size(); iAction++)
		{
			this.getConversationActions().get(iAction).
			writeElementsToXml(writer);
		}
		writer.writeEndElement();
	}

	/**
	 * Gets the name of the XML element.
	 * @return XML element name.
	 */
	@Override
	protected String getXmlElementName() {
		return XmlElementNames.ApplyConversationAction;
	}

	/**
	 * Gets the name of the response XML element.
	 * @return XML element name.
	 */
	@Override
	protected String getResponseXmlElementName() {
		return XmlElementNames.ApplyConversationActionResponse;
	}

	/**
	 * Gets the name of the response message XML element.
	 * @return XML element name.
	 */
	@Override
	protected String getResponseMessageXmlElementName() {
		return XmlElementNames.ApplyConversationActionResponseMessage;
	}

	/**
	 * Gets the request version.
	 * @return Earliest Exchange version in which this request is supported.
	 */
	@Override
	protected ExchangeVersion getMinimumRequiredServerVersion() {
		return ExchangeVersion.Exchange2010_SP1;
	}
}

