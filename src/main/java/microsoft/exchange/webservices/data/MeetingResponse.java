/**************************************************************************
 * copyright file="MeetingResponse.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the MeetingResponse.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents a response to a meeting request. Properties available on meeting
 * messages are defined in the MeetingMessageSchema class.
 * 
 */
@ServiceObjectDefinition(xmlElementName = XmlElementNames.MeetingResponse)
public class MeetingResponse extends MeetingMessage {

	/**
	 * Initializes a new instance of the class.
	 * 
	 * @param parentAttachment
	 *            The parentAttachment
	 * @throws Exception
	 *             the exception
	 */
	protected MeetingResponse(ItemAttachment parentAttachment)
		throws Exception {
		super(parentAttachment);
	}

	/**
	 * Initializes a new instance of the class.
	 * 
	 * @param service
	 *            EWS service to which this object belongs.
	 * @throws Exception
	 *             the exception
	 */
	protected MeetingResponse(ExchangeService service) throws Exception {
		super(service);
	}

	/**
	 * Binds to an existing meeting response and loads the specified set of
	 * properties. Calling this method results in a call to EWS.
	 * 
	 * @param service
	 *            The service to use to bind to the meeting response.
	 * @param id
	 *            The Id of the meeting response to bind to.
	 * @param propertySet
	 *            The set of properties to load.
	 * @return A MeetingResponse instance representing the meeting response
	 *         corresponding to the specified Id.
	 */
	public static MeetingResponse bind(ExchangeService service, ItemId id,
			PropertySet propertySet) {
		try {
			return service.bindToItem(MeetingResponse.class, id, propertySet);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Binds to an existing meeting response and loads the specified set of
	 * properties. Calling this method results in a call to EWS.
	 * 
	 * @param service
	 *            The service to use to bind to the meeting response.
	 * @param id
	 *            The Id of the meeting response to bind to.
	 * @return A MeetingResponse instance representing the meeting response
	 *         corresponding to the specified Id.
	 */
	public static MeetingResponse bind(ExchangeService service, ItemId id) {
		return MeetingResponse.bind(service, id, PropertySet
				.getFirstClassProperties());
	}

	/**
	 * Gets the minimum required server version.
	 * 
	 * @return Earliest Exchange version in which this service object type is
	 *         supported.
	 */
	@Override
	protected ExchangeVersion getMinimumRequiredServerVersion() {
		return ExchangeVersion.Exchange2007_SP1;
	}
}
