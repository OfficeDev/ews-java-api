/**************************************************************************
 * copyright file="UnifiedMessaging.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the UnifiedMessaging.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents the Unified Messaging functionalities.
 * 
 */
public final class UnifiedMessaging {

	/** The service. */
	private ExchangeService service;

	/**
	 * Constructor.
	 * 
	 * @param service
	 *            the service
	 */
	protected UnifiedMessaging(ExchangeService service) {
		this.service = service;
	}

	/**
	 * Calls a phone and reads a message to the person who picks up.
	 * 
	 * @param itemId
	 *            the item id
	 * @param dialString
	 *            the dial string
	 * @return An object providing status for the phone call.
	 * @throws Exception
	 *             the exception
	 */
	public PhoneCall playOnPhone(ItemId itemId, String dialString)
			throws Exception {
		EwsUtilities.validateParam(itemId, "itemId");
		EwsUtilities.validateParam(dialString, "dialString");

		PlayOnPhoneRequest request = new PlayOnPhoneRequest(service);
		request.setDialString(dialString);
		request.setItemId(itemId);
		PlayOnPhoneResponse serviceResponse = request.execute();

		PhoneCall callInformation = new PhoneCall(service, serviceResponse
				.getPhoneCallId());

		return callInformation;
	}

	/**
	 * Retrieves information about a current phone call.
	 * 
	 * @param id
	 *            the id
	 * @return An object providing status for the phone call.
	 * @throws Exception
	 *             the exception
	 */
	protected PhoneCall getPhoneCallInformation(PhoneCallId id)
			throws Exception {
		GetPhoneCallRequest request = new GetPhoneCallRequest(service);
		request.setId(id);
		GetPhoneCallResponse response = request.execute();

		return response.getPhoneCall();
	}

	/**
	 * Disconnects a phone call.
	 * 
	 * @param id
	 *            the id
	 * @throws Exception
	 *             the exception
	 */
	protected void disconnectPhoneCall(PhoneCallId id) throws Exception {
		DisconnectPhoneCallRequest request = new DisconnectPhoneCallRequest(
				service);
		request.setId(id);
		request.execute();
	}
}
