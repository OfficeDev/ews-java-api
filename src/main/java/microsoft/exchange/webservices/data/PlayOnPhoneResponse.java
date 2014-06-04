/**************************************************************************
 * copyright file="PlayOnPhoneResponse.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the PlayOnPhoneResponse.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents the response to a PlayOnPhone operation.
 */
final class PlayOnPhoneResponse extends ServiceResponse {

	/** The phone call id. */
	private PhoneCallId phoneCallId;

	/**
	 * Initializes a new instance of the PlayOnPhoneResponse class.
	 * 
	 * @param service
	 *            the service
	 */
	protected PlayOnPhoneResponse(ExchangeService service) {
		super();
		EwsUtilities.EwsAssert(service != null, "PlayOnPhoneResponse.ctor",
				"service is null");

		this.phoneCallId = new PhoneCallId();
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
				XmlElementNames.PhoneCallId);
		this.phoneCallId.loadFromXml(reader, XmlNamespace.Messages,
				XmlElementNames.PhoneCallId);
		reader.readEndElementIfNecessary(XmlNamespace.Messages,
				XmlElementNames.PhoneCallId);
	}

	/**
	 * Gets the Id of the phone call.
	 * 
	 * @return the phone call id
	 */
	protected PhoneCallId getPhoneCallId() {
		return phoneCallId;
	}

}
