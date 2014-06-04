/**************************************************************************
 * copyright file="AcceptMeetingInvitationMessage.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the AcceptMeetingInvitationMessage.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents a meeting acceptance message.
 * 
 * 
 */
public final class AcceptMeetingInvitationMessage extends
		CalendarResponseMessage<MeetingResponse> {

	/** The tentative. */
	private boolean tentative;

	/**
	 * Initializes a new instance of the AcceptMeetingInvitationMessage class.
	 * 
	 * @param referenceItem
	 *            the reference item
	 * @param tentative
	 *            the tentative
	 * @throws Exception
	 *             the exception
	 */
	protected AcceptMeetingInvitationMessage(Item referenceItem,
			boolean tentative) throws Exception {
		super(referenceItem);
		this.tentative = tentative;
	}

	/**
	 * This methods lets subclasses of ServiceObject override the default
	 * mechanism by which the XML element name associated with their type is
	 * retrieved.
	 * 
	 * @return The XML element name associated with this type. If this method
	 *         returns null or empty, the XML element name associated with this
	 *         type is determined by the EwsObjectDefinition attribute that
	 *         decorates the type, if present.
	 * 
	 *         Item and folder classes that can be returned by EWS MUST rely on
	 *         the EwsObjectDefinition attribute for XML element name
	 *         determination.
	 */
	@Override
	protected String getXmlElementName() {
		// getXmlElementOverride is pvt and getXmlElementName returns
		// getXmlElementOverride
		if (this.tentative) {
			return XmlElementNames.TentativelyAcceptItem;
		} else {
			return XmlElementNames.AcceptItem;
		}
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

	/**
	 * Gets the tentative.
	 * 
	 * @return Gets a value indicating whether the associated meeting is
	 *         tentatively accepted.
	 */
	public boolean getTentative() {
		return this.tentative;
	}

}