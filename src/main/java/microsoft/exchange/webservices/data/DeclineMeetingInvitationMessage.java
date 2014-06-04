/**************************************************************************
 * copyright file="DeclineMeetingInvitationMessage.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the DeclineMeetingInvitationMessage.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents a meeting declination message.
 * 
 * 
 */
@ServiceObjectDefinition(xmlElementName = XmlElementNames.DeclineItem, returnedByServer = false)
public final class DeclineMeetingInvitationMessage extends
		CalendarResponseMessage<MeetingResponse> {

	/**
	 * Initializes a new instance of the DeclineMeetingInvitationMessage class.
	 * 
	 * @param referenceItem
	 *            the reference item
	 * @throws Exception
	 *             the exception
	 */
	protected DeclineMeetingInvitationMessage(Item referenceItem)
			throws Exception {
		super(referenceItem);
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