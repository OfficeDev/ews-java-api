/**************************************************************************
 * copyright file="DisconnectPhoneCallRequest.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the DisconnectPhoneCallRequest.java.
 **************************************************************************/

package microsoft.exchange.webservices.data;

/**
 * Represents a DisconnectPhoneCall request.
 * 
 */
final class DisconnectPhoneCallRequest extends SimpleServiceRequestBase {

	/** The id. */
	private PhoneCallId id;

	/**
	 * Initializes a new instance of the DisconnectPhoneCallRequest class.
	 * 
	 * @param service
	 *            the service
	 * @throws Exception 
	 */
	protected DisconnectPhoneCallRequest(ExchangeService service)
			throws Exception {
		super(service);
	}

	/**
	 * Gets the name of the XML element.
	 * 
	 * @return XML element name.
	 */
	@Override
	protected String getXmlElementName() {
		return XmlElementNames.DisconnectPhoneCall;
	}

	/**
	 * Writes XML elements.
	 * 
	 * @param writer
	 *            the writer
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void writeElementsToXml(EwsServiceXmlWriter writer)
			throws Exception {
		this.id.writeToXml(writer, XmlNamespace.Messages,
				XmlElementNames.PhoneCallId);
	}

	/**
	 * Gets the name of the response XML element.
	 * 
	 * @return XML element name.
	 */
	@Override
	protected String getResponseXmlElementName() {
		return XmlElementNames.DisconnectPhoneCallResponse;
	}

	/**
	 * Parses the response.
	 * 
	 * @param reader
	 *            the reader
	 * @return Response object.
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected Object parseResponse(EwsServiceXmlReader reader) 
		throws Exception {
		ServiceResponse serviceResponse = new ServiceResponse();
		serviceResponse.loadFromXml(reader,
				XmlElementNames.DisconnectPhoneCallResponse);
		return serviceResponse;
	}

	/**
	 * Gets the request version.
	 * 
	 * @return Earliest Exchange version in which this request is supported.
	 */
	@Override
	protected ExchangeVersion getMinimumRequiredServerVersion() {
		return ExchangeVersion.Exchange2010;
	}

	/**
	 * Executes this request.
	 * 
	 * @return Service response.
	 * @throws Exception
	 *             the exception
	 */
	protected ServiceResponse execute() throws Exception {
		ServiceResponse serviceResponse = (ServiceResponse)this
				.internalExecute();
		serviceResponse.throwIfNecessary();
		return serviceResponse;
	}

	/**
	 * Gets  the Id of the phone call.
	 * 
	 * @return the id
	 */
	protected PhoneCallId getId() {
		return this.id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id
	 *            the new id
	 */
	protected void setId(PhoneCallId id) {
		this.id = id;
	}

}
