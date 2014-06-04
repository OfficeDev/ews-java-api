/**************************************************************************
 * copyright file="AttendeeInfo.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the AttendeeInfo.java.
 **************************************************************************/

package microsoft.exchange.webservices.data;

/**
 * Represents information about an attendee for which to request availability
 * information.
 */
public final class AttendeeInfo implements ISelfValidate {

	/** The smtp address. */
	private String smtpAddress;

	/** The attendee type. */
	private MeetingAttendeeType attendeeType = MeetingAttendeeType.Required;

	/** The exclude conflicts. */
	private boolean excludeConflicts;

	/**
	 * Initializes a new instance of the AttendeeInfo class.
	 */
	public AttendeeInfo() {
	}

	/**
	 * Initializes a new instance of the AttendeeInfo class.
	 * 
	 * @param smtpAddress
	 *            the smtp address
	 * @param attendeeType
	 *            the attendee type
	 * @param excludeConflicts
	 *            the exclude conflicts
	 */
	public AttendeeInfo(String smtpAddress, MeetingAttendeeType attendeeType,
			boolean excludeConflicts) {
		this();
		this.smtpAddress = smtpAddress;
		this.attendeeType = attendeeType;
		this.excludeConflicts = excludeConflicts;
	}

	/**
	 * Initializes a new instance of the AttendeeInfo class.
	 * 
	 * @param smtpAddress
	 *            the smtp address
	 */
	public AttendeeInfo(String smtpAddress) {
		this(smtpAddress, MeetingAttendeeType.Required, false);
		this.smtpAddress = smtpAddress;
	}

	/**
	 * Defines an implicit conversion between a string representing an SMTP
	 * address and AttendeeInfo.
	 * 
	 * @param smtpAddress
	 *            the smtp address
	 * @return An AttendeeInfo initialized with the specified SMTP address.
	 */
	public static AttendeeInfo getAttendeeInfoFromString(String smtpAddress) {
		return new AttendeeInfo(smtpAddress);
	}

	/**
	 * Writes to XML.
	 * 
	 * @param writer
	 *            the writer
	 * @throws Exception
	 *             the exception
	 */
	protected void writeToXml(EwsServiceXmlWriter writer) throws Exception {
		writer.writeStartElement(XmlNamespace.Types,
				XmlElementNames.MailboxData);

		writer.writeStartElement(XmlNamespace.Types, XmlElementNames.Email);
		writer.writeElementValue(XmlNamespace.Types, XmlElementNames.Address,
				this.smtpAddress);
		writer.writeEndElement(); // Email

		writer.writeElementValue(XmlNamespace.Types,
				XmlElementNames.AttendeeType, this.attendeeType);

		writer.writeElementValue(XmlNamespace.Types,
				XmlElementNames.ExcludeConflicts, this.excludeConflicts);

		writer.writeEndElement(); // MailboxData
	}

	/**
	 * Gets the SMTP address of this attendee.
	 * 
	 * @return the smtp address
	 */
	public String getSmtpAddress() {
		return smtpAddress;
	}

	/**
	 * Sets the smtp address.
	 * 
	 * @param smtpAddress
	 *            the new smtp address
	 */
	public void setSmtpAddress(String smtpAddress) {
		this.smtpAddress = smtpAddress;
	}

	/**
	 * Gets the type of this attendee.
	 * 
	 * @return the attendee type
	 */
	public MeetingAttendeeType getAttendeeType() {
		return attendeeType;
	}

	/**
	 * Sets the attendee type.
	 * 
	 * @param attendeeType
	 *            the new attendee type
	 */
	public void setAttendeeType(MeetingAttendeeType attendeeType) {
		this.attendeeType = attendeeType;
	}

	/**
	 * Gets a value indicating whether times when this attendee is not
	 * available should be returned.
	 * 
	 * @return true, if is exclude conflicts
	 */
	public boolean isExcludeConflicts() {
		return excludeConflicts;
	}

	/**
	 * Sets the exclude conflicts.
	 * 
	 * @param excludeConflicts
	 *            the new exclude conflicts
	 */
	public void setExcludeConflicts(boolean excludeConflicts) {
		this.excludeConflicts = excludeConflicts;
	}

	/**
	 * Validates this instance.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public void validate() throws Exception {
		EwsUtilities.validateParam(this.smtpAddress, "SmtpAddress");
	}
}
