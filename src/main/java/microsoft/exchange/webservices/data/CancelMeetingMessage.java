/**************************************************************************
 * copyright file="CancelMeetingMessage.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the CancelMeetingMessage.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/***
 * Represents a meeting cancellation message.
 */
@ServiceObjectDefinition(xmlElementName = XmlElementNames.CancelCalendarItem, returnedByServer = false)
public final class CancelMeetingMessage extends
		CalendarResponseMessageBase<MeetingCancellation> {

	/**
	 * * Initializes a new instance of the class.
	 * 
	 * @param referenceItem
	 *            the reference item
	 * @throws Exception
	 *             the exception
	 */
	protected CancelMeetingMessage(Item referenceItem) throws Exception {
		super(referenceItem);
	}

	/***
	 * Gets the minimum required server version.
	 * 
	 * @return Earliest Exchange version in which this service object type is
	 *         supported.
	 */
	@Override
	protected ExchangeVersion getMinimumRequiredServerVersion() {
		return ExchangeVersion.Exchange2007_SP1;
	}

	/***
	 * Gets the minimum required server version.
	 * 
	 * @return Earliest Exchange version in which this service object type is
	 *         supported.
	 */
	@Override
	protected ServiceObjectSchema getSchema() {
		return CancelMeetingMessageSchema.Instance;
	}

	/**
	 * * Gets the body of the response.
	 * 
	 * @return the body
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public MessageBody getBody() throws ServiceLocalException {
		return (MessageBody) this.getPropertyBag()
				.getObjectFromPropertyDefinition(
						CancelMeetingMessageSchema.Body);
	}

	/**
	 * Sets the body.
	 * 
	 * @param value
	 *            the new body
	 * @throws Exception
	 *             the exception
	 */
	public void setBody(MessageBody value) throws Exception {
		this.getPropertyBag().setObjectFromPropertyDefinition(
				CancelMeetingMessageSchema.Body, value);
	}

}