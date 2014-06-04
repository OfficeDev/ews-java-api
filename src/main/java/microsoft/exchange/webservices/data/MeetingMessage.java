/**************************************************************************
 * copyright file="MeetingMessage.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the MeetingMessage.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents a meeting-related message. Properties available on meeting
 * messages are defined in the MeetingMessageSchema class.
 */

@ServiceObjectDefinition(xmlElementName = XmlElementNames.MeetingMessage)
@EditorBrowsable(state = EditorBrowsableState.Never)
public class MeetingMessage extends EmailMessage {

	/**
	 * Initializes a new instance of the "MeetingMessage" class.
	 * 
	 * @param parentAttachment
	 *            the parent attachment
	 * @throws Exception
	 *             the exception
	 */
	protected MeetingMessage(ItemAttachment parentAttachment) throws Exception {
		super(parentAttachment);
	}

	/**
	 * Initializes a new instance of the "MeetingMessage" class.
	 * 
	 * @param service
	 *            EWS service to which this object belongs.
	 * @throws Exception
	 *             the exception
	 */
	protected MeetingMessage(ExchangeService service) throws Exception {
		super(service);
	}

	/**
	 * Binds to an existing meeting message and loads the specified set of
	 * properties. Calling this method results in a call to EWS.
	 * 
	 * @param service
	 *            The service to use to bind to the meeting message.
	 * @param id
	 *            The Id of the meeting message to bind to.
	 * @param propertySet
	 *            The set of properties to load.
	 * @return A MeetingMessage instance representing the meeting message
	 *         corresponding to the specified Id.
	 * @throws Exception
	 *             the exception
	 */
	public static MeetingMessage bind(ExchangeService service, ItemId id,
			PropertySet propertySet) throws Exception {
		return (MeetingMessage)service.bindToItem(id, propertySet);
	}

	/**
	 * Binds to an existing meeting message and loads its first class
	 * properties. Calling this method results in a call to EWS.
	 * 
	 * @param service
	 *            The service to use to bind to the meeting message.
	 * @param id
	 *            The Id of the meeting message to bind to.
	 * @return A MeetingMessage instance representing the meeting message
	 *         corresponding to the specified Id.
	 * @throws Exception
	 *             the exception
	 */
	public static MeetingMessage bind(ExchangeService service, ItemId id)
			throws Exception {
		return MeetingMessage.bind(service, id, PropertySet
				.getFirstClassProperties());
	}

	/**
	 * Internal method to return the schema associated with this type of object.
	 * 
	 * @return The schema associated with this type of object.
	 */
	@Override
	protected ServiceObjectSchema getSchema() {
		return MeetingMessageSchema.getInstance();
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
