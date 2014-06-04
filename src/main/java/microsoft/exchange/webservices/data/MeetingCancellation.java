/**************************************************************************
 * copyright file="MeetingCancellation.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the MeetingCancellation.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents a meeting cancellation message. Properties available on meeting
 * messages are defined in the MeetingMessageSchema class.
 * 
 */
@ServiceObjectDefinition(xmlElementName = XmlElementNames.MeetingCancellation)
public class MeetingCancellation extends MeetingMessage {

	/**
	 * Initializes a new instance of the class.
	 * 
	 * @param parentAttachment
	 *            The parent attachment.
	 * @throws Exception
	 *             the exception
	 */
	protected MeetingCancellation(ItemAttachment parentAttachment)
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
	protected MeetingCancellation(ExchangeService service) throws Exception {
		super(service);
	}

	/**
	 * Binds to an existing meeting cancellation message and loads the specified
	 * set of properties. Calling this method results in a call to EWS.
	 * 
	 * @param service
	 *            The service to use to bind to the meeting cancellation
	 *            message.
	 * @param id
	 *            The Id of the meeting cancellation message to bind to.
	 * @param propertySet
	 *            The set of properties to load.
	 * @return A MeetingCancellation instance representing the meeting
	 *         cancellation message corresponding to the specified Id.
	 */
	public static MeetingCancellation bind(ExchangeService service, ItemId id,
			PropertySet propertySet) {
		try {
			return service.bindToItem(MeetingCancellation.class, id,
					propertySet);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Binds to an existing meeting cancellation message and loads the specified
	 * set of properties. Calling this method results in a call to EWS.
	 * 
	 * @param service
	 *            The service to use to bind to the meeting cancellation
	 *            message.
	 * @param id
	 *            The Id of the meeting cancellation message to bind to.
	 * @return A MeetingCancellation instance representing the meeting
	 *         cancellation message corresponding to the specified Id.
	 */
	public static MeetingCancellation bind(ExchangeService service, ItemId id) {
		return MeetingCancellation.bind(service, id, PropertySet
				.getFirstClassProperties());
	}

	/**
	 * Removes the meeting associated with the cancellation message from the
	 * user's calendar.
	 * 
	 * @return A CalendarActionResults object containing the various items that
	 *         were created or modified as a results of this operation.
	 * @throws ServiceLocalException
	 *             the service local exception
	 * @throws Exception
	 *             the exception
	 */
	public CalendarActionResults removeMeetingFromCalendar()
			throws ServiceLocalException, Exception {
		return new CalendarActionResults(new RemoveFromCalendar(this)
				.internalCreate(null, null));
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
