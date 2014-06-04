/**************************************************************************
 * copyright file="GetPhoneCallResponse.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the GetPhoneCallResponse.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 *Represents the response to a GetPhoneCall operation.
 * 
 */
final class GetPhoneCallResponse extends ServiceResponse {

	/** The phone call. */
	private PhoneCall phoneCall;

	/**
	 * Initializes a new instance of the GetPhoneCallResponse class.
	 * 
	 * @param service
	 *            the service
	 */
	protected GetPhoneCallResponse(ExchangeService service) {
		super();
		EwsUtilities.EwsAssert(service != null, "GetPhoneCallResponse.ctor",
				"service is null");

		this.phoneCall = new PhoneCall(service);
	}

	/**
	 * Reads response elements from XML.
	 * 
	 * @param reader
	 *            the reader
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void readElementsFromXml(EwsServiceXmlReader reader)
			throws Exception {
		reader.readStartElement(XmlNamespace.Messages,
				XmlElementNames.PhoneCallInformation);
		this.phoneCall.loadFromXml(reader, XmlNamespace.Messages,
				XmlElementNames.PhoneCallInformation);
		reader.readEndElementIfNecessary(XmlNamespace.Messages,
				XmlElementNames.PhoneCallInformation);
	}

	/**
	 * Gets the phone call.
	 * 
	 * @return the phone call
	 */
	protected PhoneCall getPhoneCall() {
		return phoneCall;
	}

}
