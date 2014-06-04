/**************************************************************************
 * copyright file="PhoneCall.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the PhoneCall.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents a phone call.
 * 
 */
public final class PhoneCall extends ComplexProperty {
	
	/** The Constant successfullResponseText. */
	private final static String SuccessfullResponseText = "OK";

	/** The Constant successfullResponseCode. */
	private final static int SuccessfullResponseCode = 200;

	/** The service. */
	private ExchangeService service;

	/** The state. */
	private PhoneCallState state;

	/** The connection failure cause. */
	private ConnectionFailureCause connectionFailureCause;

	/** The sip response text. */
	private String sipResponseText;

	/** The sip response code. */
	private int sipResponseCode;

	/** The id. */
	private PhoneCallId id;	

	/**
	 * PhoneCall Constructor.
	 * 
	 * @param service
	 *            the service
	 */
	protected PhoneCall(ExchangeService service) {
		EwsUtilities.EwsAssert(service != null, "PhoneCall.ctor",
				"service is null");

		this.service = service;
		this.state = PhoneCallState.Connecting;
		this.connectionFailureCause = ConnectionFailureCause.None;
		this.sipResponseText = PhoneCall.SuccessfullResponseText;
		this.sipResponseCode = PhoneCall.SuccessfullResponseCode;
	}

	/**
	 * PhoneCall Constructor.
	 * 
	 * @param service
	 *            the service
	 * @param id
	 *            the id
	 */
	protected PhoneCall(ExchangeService service, PhoneCallId id) {
		this(service);
		this.id = id;
	}

	/**
	 * Refreshes the state of this phone call.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public void refresh() throws Exception {
		PhoneCall phoneCall = service.getUnifiedMessaging()
				.getPhoneCallInformation(this.id);
		this.state = phoneCall.getState();
		this.connectionFailureCause = phoneCall.getConnectionFailureCause();
		this.sipResponseText = phoneCall.getSipResponseText();
		this.sipResponseCode = phoneCall.getSipResponseCode();
	}

	/**
	 * Disconnects this phone call.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public void disconnect() throws Exception {
		// If call is already disconnected, throw exception
		//
		if (this.state == PhoneCallState.Disconnected) {
			throw new ServiceLocalException(
					Strings.PhoneCallAlreadyDisconnected);
		}

		this.service.getUnifiedMessaging().disconnectPhoneCall(this.id);
		this.state = PhoneCallState.Disconnected;
	}

	/**
	 * Tries to read an element from XML.
	 * 
	 * @param reader
	 *            the reader
	 * @return True if element was read.
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected boolean tryReadElementFromXml(EwsServiceXmlReader reader)
			throws Exception {
		if (reader.getLocalName().equals(XmlElementNames.PhoneCallState)) {
			this.state = reader.readElementValue(PhoneCallState.class);
			return true;
		} else if (reader.getLocalName().equals(
				XmlElementNames.ConnectionFailureCause)) {
			this.connectionFailureCause = reader
					.readElementValue(ConnectionFailureCause.class);
			return true;
		} else if (reader.getLocalName()
				.equals(XmlElementNames.SIPResponseText)) {
			this.sipResponseText = reader.readElementValue();
			return true;
		} else if (reader.getLocalName()
				.equals(XmlElementNames.SIPResponseCode)) {
			this.sipResponseCode = reader.readElementValue(Integer.class);
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Gets a value indicating the last known state of this phone call.
	 * 
	 * @return the state
	 */
	public PhoneCallState getState() {
		return state;
	}

	/**
	 * Gets the SIP response text of this phone call.
	 * 
	 * @return the sip response text
	 */
	public String getSipResponseText() {
		return sipResponseText;
	}

	/**
	 * Gets the SIP response code of this phone call.
	 * 
	 * @return the sip response code
	 */
	public int getSipResponseCode() {
		return sipResponseCode;
	}

	/**
	 * Gets a value indicating the reason why this phone call failed to connect.
	 * 
	 * @return the connection failure cause
	 */
	public ConnectionFailureCause getConnectionFailureCause() {
		return connectionFailureCause;
	}

}
