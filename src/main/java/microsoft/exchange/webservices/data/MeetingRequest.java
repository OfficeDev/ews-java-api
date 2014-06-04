/**************************************************************************
 * copyright file="MeetingRequest.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the MeetingRequest class.
 **************************************************************************/

package microsoft.exchange.webservices.data;

import java.util.Date;

/**
 *  Represents a meeting request that an attendee can accept
 *   or decline. Properties available on meeting 
 *   requests are defined in the MeetingRequestSchema class.
 *
 */
@ServiceObjectDefinition(xmlElementName = XmlElementNames.MeetingRequest)
public class MeetingRequest extends MeetingMessage implements
		ICalendarActionProvider {

	/**
	 * Initializes a new instance of the class.
	 * 
	 * @param parentAttachment
	 *            The parent attachment
	 * @throws Exception
	 *             throws Exception
	 */
	protected MeetingRequest(ItemAttachment parentAttachment) throws Exception {
		super(parentAttachment);
	}

	/**
	 * Initializes a new instance of the class.
	 * 
	 * @param service
	 *            EWS service to which this object belongs.
	 * @throws Exception
	 *             throws Exception
	 */
	protected MeetingRequest(ExchangeService service) throws Exception {
		super(service);
	}

	/**
	 * Binds to an existing meeting response and loads the specified set of
	 * properties. Calling this method results in a call to EWS.
	 * 
	 * @param service
	 *            The service to use to bind to the meeting request.
	 * @param id
	 *            The Id of the meeting request to bind to.
	 * @param propertySet
	 *            The set of properties to load.
	 * @return A MeetingResponse instance representing the meeting request
	 *         corresponding to the specified Id.
	 */
	public static MeetingRequest bind(ExchangeService service, ItemId id,
			PropertySet propertySet) {
		try {
			return service.bindToItem(MeetingRequest.class, id, propertySet);
		} catch (Exception e) {		
			e.printStackTrace();
			return  null;
		}
	}

	/**
	 * Binds to an existing meeting response and loads the specified set of
	 * properties. Calling this method results in a call to EWS.
	 * 
	 * @param service
	 *            The service to use to bind to the meeting request.
	 * @param id
	 *            The Id of the meeting request to bind to.
	 * @return A MeetingResponse instance representing the meeting request
	 *         corresponding to the specified Id.
	 */
	public static MeetingRequest bind(ExchangeService service, ItemId id) {
		return MeetingRequest.bind(service, id, PropertySet
				.getFirstClassProperties());
	}

	/**
	 * Internal method to return the schema associated with this type of object.
	 * 
	 * @return The schema associated with this type of object.
	 */
	@Override
	protected ServiceObjectSchema getSchema() {
		return MeetingRequestSchema.Instance;
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
	 * Creates a local meeting acceptance message that can be customized and
	 * sent.
	 * 
	 * @param tentative
	 *            Specifies whether the meeting will be tentatively accepted.
	 * @return An AcceptMeetingInvitationMessage representing the meeting
	 * acceptance message.
	 */
	public AcceptMeetingInvitationMessage createAcceptMessage(boolean 
			tentative) {
		try {
			return new AcceptMeetingInvitationMessage(this, tentative);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Creates a local meeting declination message that can be customized and
	 * sent.
	 *
	 * @return A DeclineMeetingInvitation representing the meeting declination
	 * message.
	 */
	public DeclineMeetingInvitationMessage createDeclineMessage() {
		try {
			return new DeclineMeetingInvitationMessage(this);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Accepts the meeting. Calling this method results in a call to EWS.
	 * 
	 * @param sendResponse
	 *            Indicates whether to send a response to the organizer.
	 * @return A CalendarActionResults object containing the various items that
	 *         were created or modified as a results of this operation.
	 * @throws Exception
	 *             throws Exception
	 */
	public CalendarActionResults accept(boolean sendResponse) throws Exception {
		return this.internalAccept(false, sendResponse);
	}

	/**
	 * Tentatively accepts the meeting. Calling this method results in a call to
	 * EWS.
	 * 
	 * @param sendResponse
	 *            Indicates whether to send a response to the organizer.
	 * @return A CalendarActionResults object containing the various items that
	 *         were created or modified as a results of this operation.
	 * @throws Exception
	 *             throws Exception
	 */
	public CalendarActionResults acceptTentatively(boolean sendResponse) 
	throws Exception {
		return this.internalAccept(true, sendResponse);
	}

	/**
	 * Accepts the meeting.
	 * 
	 * @param tentative
	 *            True if tentative accept.
	 * @param sendResponse
	 *            Indicates whether to send a response to the organizer.
	 * @return A CalendarActionResults object containing the various items that
	 *         were created or modified as a results of this operation.
	 * @throws Exception
	 *             throws Exception
	 */
	protected CalendarActionResults internalAccept(boolean tentative, 
			boolean sendResponse) throws Exception {
		AcceptMeetingInvitationMessage accept = this
				.createAcceptMessage(tentative);

		if (sendResponse) {
			return accept.calendarSendAndSaveCopy();
		} else {
			return  accept.calendarSave();

		}
	}

	/**
	 * Declines the meeting invitation. Calling this method results in a call to
	 * EWS.
	 * 
	 * @param sendResponse
	 *            Indicates whether to send a response to the organizer.
	 * @return A CalendarActionResults object containing the various items that
	 *         were created or modified as a results of this operation.
	 * @throws Exception
	 *             throws Exception
	 */
	public CalendarActionResults decline(boolean sendResponse)
	throws Exception {
		DeclineMeetingInvitationMessage decline = this.createDeclineMessage();

		if (sendResponse) {
			return  decline.calendarSendAndSaveCopy();
		} else {
			return  decline.calendarSave();
		}
	}
	
	/**
	 * Gets the type of this meeting request.
	 *
	 * @return the meeting request type
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public MeetingRequestType getMeetingRequestType()
			throws ServiceLocalException {
		return (MeetingRequestType) this.getPropertyBag()
				.getObjectFromPropertyDefinition(
						MeetingRequestSchema.MeetingRequestType);
	}

	/**
	 * Gets the a value representing the intended free/busy status of the
	 * meeting.
	 *
	 * @return the intended free busy status
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public LegacyFreeBusyStatus getIntendedFreeBusyStatus()
			throws ServiceLocalException {
		return (LegacyFreeBusyStatus) this.getPropertyBag()
				.getObjectFromPropertyDefinition(
						MeetingRequestSchema.IntendedFreeBusyStatus);

	}

	/**
	 * Gets the start time of the appointment.
	 *
	 * @return the start
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public Date getStart() throws ServiceLocalException {
		return (Date) this.getPropertyBag().getObjectFromPropertyDefinition(
				AppointmentSchema.Start);
	}

	/**
	 * Gets the end time of the appointment.
	 *
	 * @return the end
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public Date getEnd() throws ServiceLocalException {
		return (Date) this.getPropertyBag().getObjectFromPropertyDefinition(
				AppointmentSchema.End);
	}

	/**
	 * Gets the original start time of the appointment.
	 *
	 * @return the original start
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public Date getOriginalStart() throws ServiceLocalException {
		return (Date) this.getPropertyBag().getObjectFromPropertyDefinition(
				AppointmentSchema.OriginalStart);
	}

	/**
	 * Gets a value indicating whether this appointment is an all day event.
	 *
	 * @return the checks if is all day event
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public boolean getIsAllDayEvent() throws ServiceLocalException {
		return this.getPropertyBag().getObjectFromPropertyDefinition(
				AppointmentSchema.IsAllDayEvent) != null;
	}

	/**
	 * Gets a value indicating the free/busy status of the owner of this
	 * appointment.
	 *
	 * @return the legacy free busy status
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public LegacyFreeBusyStatus legacyFreeBusyStatus()
			throws ServiceLocalException {
		return (LegacyFreeBusyStatus) this.getPropertyBag()
				.getObjectFromPropertyDefinition(
						AppointmentSchema.LegacyFreeBusyStatus);
	}

	/**
	 * Gets  the location of this appointment.
	 *
	 * @return the location
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public String getLocation() throws ServiceLocalException {
		return (String) this.getPropertyBag().getObjectFromPropertyDefinition(
				AppointmentSchema.Location);
	}

	/**
	 * Gets a text indicating when this appointment occurs. The text returned by
	 * When is localized using the Exchange Server culture or using the culture
	 * specified in the PreferredCulture property of the ExchangeService object
	 * this appointment is bound to.
	 *
	 * @return the when
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public String getWhen() throws ServiceLocalException {
		return (String) this.getPropertyBag().getObjectFromPropertyDefinition(
				AppointmentSchema.When);
	}

	/**
	 * Gets a value indicating whether the appointment is a meeting.
	 *
	 * @return the checks if is meeting
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public boolean getIsMeeting() throws ServiceLocalException {
		return this.getPropertyBag().getObjectFromPropertyDefinition(
				AppointmentSchema.IsMeeting) != null;
	}

	/**
	 * Gets a value indicating whether the appointment has been cancelled.
	 *
	 * @return the checks if is cancelled
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public boolean getIsCancelled() throws ServiceLocalException {
		return this.getPropertyBag().getObjectFromPropertyDefinition(
				AppointmentSchema.IsCancelled) != null;
	}

	/**
	 * Gets a value indicating whether the appointment is recurring.
	 *
	 * @return the checks if is recurring
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public boolean getIsRecurring() throws ServiceLocalException {
		return this.getPropertyBag().getObjectFromPropertyDefinition(
				AppointmentSchema.IsRecurring) != null;
	}

	/**
	 * Gets a value indicating whether the meeting request has already been
	 * sent.
	 *
	 * @return the meeting request was sent
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public boolean getMeetingRequestWasSent() throws ServiceLocalException {
		return this.getPropertyBag().getObjectFromPropertyDefinition(
				AppointmentSchema.MeetingRequestWasSent) != null;
	}

	/**
	 * Gets a value indicating the type of this appointment.
	 *
	 * @return the appointment type
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public AppointmentType getAppointmentType() throws ServiceLocalException {
		return (AppointmentType) this.getPropertyBag()
				.getObjectFromPropertyDefinition(
						AppointmentSchema.AppointmentType);
	}

	/**
	 * Gets a value indicating what was the last response of the user that
	 * loaded this meeting.
	 *
	 * @return the my response type
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public MeetingResponseType getMyResponseType()
	throws ServiceLocalException {
		return (MeetingResponseType) this.getPropertyBag()
				.getObjectFromPropertyDefinition(
						AppointmentSchema.MyResponseType);
	}

	/**
	 * Gets the organizer of this meeting.
	 *
	 * @return the organizer
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public EmailAddress getOrganizer() throws ServiceLocalException {
		return (EmailAddress) this.getPropertyBag()
				.getObjectFromPropertyDefinition(AppointmentSchema.Organizer);
	}

	/**
	 * Gets a list of required attendees for this meeting.
	 *
	 * @return the required attendees
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public AttendeeCollection getRequiredAttendees()
			throws ServiceLocalException {
		return (AttendeeCollection) this.getPropertyBag()
				.getObjectFromPropertyDefinition(
						AppointmentSchema.RequiredAttendees);
	}

	/**
	 * Gets a list of optional attendeed for this meeting.
	 *
	 * @return the optional attendees
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public AttendeeCollection getOptionalAttendees()
			throws ServiceLocalException {
		return (AttendeeCollection) this.getPropertyBag()
				.getObjectFromPropertyDefinition(
						AppointmentSchema.OptionalAttendees);
	}

	/**
	 * Gets a list of resources for this meeting.
	 *
	 * @return the resources
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public AttendeeCollection getResources() throws ServiceLocalException {
		return (AttendeeCollection) this.getPropertyBag()
				.getObjectFromPropertyDefinition(AppointmentSchema.Resources);
	}

	/**
	 * Gets the number of calendar entries that conflict with
	 *  this appointment in the authenticated user's calendar.
	 *
	 * @return the conflicting meeting count
	 * @throws NumberFormatException
	 *             the number format exception
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public int getConflictingMeetingCount() throws NumberFormatException,
			ServiceLocalException {
		return (Integer.parseInt(this.getPropertyBag()
				.getObjectFromPropertyDefinition(
						AppointmentSchema.ConflictingMeetingCount).toString()));
	}

	/**
	 * Gets the number of calendar entries that are adjacent to 
	 * this appointment in the authenticated user's calendar.
	 *
	 * @return the adjacent meeting count
	 * @throws NumberFormatException
	 *             the number format exception
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public int getAdjacentMeetingCount() throws NumberFormatException,
			ServiceLocalException {
		return (Integer.parseInt(this.getPropertyBag()
				.getObjectFromPropertyDefinition(
						AppointmentSchema.AdjacentMeetingCount).toString()));
	}

	/**
	 * Gets a list of meetings that conflict with 
	 * this appointment in the authenticated user's calendar.
	 *
	 * @return the conflicting meetings
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public ItemCollection<Appointment> getConflictingMeetings()
			throws ServiceLocalException {
		return (ItemCollection<Appointment>) this.getPropertyBag()
				.getObjectFromPropertyDefinition(
						AppointmentSchema.ConflictingMeetings);
	}

	/**
	 * Gets a list of meetings that are adjacent with this 
	 * appointment in the authenticated user's calendar.
	 *
	 * @return the adjacent meetings
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public ItemCollection<Appointment> getAdjacentMeetings()
			throws ServiceLocalException {
		return (ItemCollection<Appointment>) this.getPropertyBag()
				.getObjectFromPropertyDefinition(
						AppointmentSchema.AdjacentMeetings);
	}

	/**
	 * Gets the duration of this appointment.
	 *
	 * @return the duration
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public TimeSpan getDuration() throws ServiceLocalException {
		return (TimeSpan) this.getPropertyBag()
				.getObjectFromPropertyDefinition(AppointmentSchema.Duration);
	}

	/**
	 * Gets the name of the time zone this appointment is defined in.
	 *
	 * @return the time zone
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public String getTimeZone() throws ServiceLocalException {
		return (String) this.getPropertyBag().getObjectFromPropertyDefinition(
				AppointmentSchema.TimeZone);
	}

	/**
	 * Gets the time when the attendee replied to the meeting request.
	 *
	 * @return the appointment reply time
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public Date getAppointmentReplyTime() throws ServiceLocalException {
		return (Date) this.getPropertyBag().getObjectFromPropertyDefinition(
				AppointmentSchema.AppointmentReplyTime);
	}

	/**
	 * Gets the sequence number of this appointment.
	 *
	 * @return the appoijntment sequence number
	 * @throws NumberFormatException
	 *             the number format exception
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public int getAppoijntmentSequenceNumber() throws NumberFormatException,
			ServiceLocalException {
		return (Integer
				.parseInt(this.getPropertyBag()
						.getObjectFromPropertyDefinition(
								AppointmentSchema.AppointmentSequenceNumber)
						.toString()));
	}

	/**
	 * Gets the state of this appointment.
	 *
	 * @return the appointment state
	 * @throws NumberFormatException
	 *             the number format exception
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public int getAppointmentState() throws NumberFormatException,
			ServiceLocalException {
		return (Integer.parseInt(this.getPropertyBag()
				.getObjectFromPropertyDefinition(
						AppointmentSchema.AppointmentState).toString()));
	}

	/**
	 * Gets the recurrence pattern for this meeting request.
	 *
	 * @return the recurrence
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public Recurrence getRecurrence() throws ServiceLocalException {
		return (Recurrence) this.getPropertyBag()
				.getObjectFromPropertyDefinition(AppointmentSchema.Recurrence);
	}

	/**
	 * Gets an OccurrenceInfo identifying the first occurrence of this meeting.
	 *
	 * @return the first occurrence
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public OccurrenceInfo getFirstOccurrence() throws ServiceLocalException {
		return (OccurrenceInfo) this.getPropertyBag()
				.getObjectFromPropertyDefinition(
						AppointmentSchema.FirstOccurrence);
	}

	/**
	 * Gets an OccurrenceInfo identifying the last occurrence of this meeting.
	 *
	 * @return the last occurrence
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public OccurrenceInfo getLastOccurrence() throws ServiceLocalException {
		return (OccurrenceInfo) this.getPropertyBag()
				.getObjectFromPropertyDefinition(
						AppointmentSchema.FirstOccurrence);
	}

	/**
	 * Gets a list of modified occurrences for this meeting.
	 *
	 * @return the modified occurrences
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public OccurrenceInfoCollection getModifiedOccurrences()
			throws ServiceLocalException {
		return (OccurrenceInfoCollection) this.getPropertyBag()
				.getObjectFromPropertyDefinition(
						AppointmentSchema.ModifiedOccurrences);
	}

	/**
	 * Gets a list of deleted occurrences for this meeting.
	 *
	 * @return the deleted occurrences
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public DeletedOccurrenceInfoCollection getDeletedOccurrences()
			throws ServiceLocalException {
		return (DeletedOccurrenceInfoCollection) this.getPropertyBag()
				.getObjectFromPropertyDefinition(
						AppointmentSchema.DeletedOccurrences);
	}

	/**
	 * Gets  time zone of the start property of this meeting request.
	 *
	 * @return the start time zone
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public TimeZoneDefinition getStartTimeZone() throws ServiceLocalException {
		return (TimeZoneDefinition) this.getPropertyBag()
				.getObjectFromPropertyDefinition(
						AppointmentSchema.StartTimeZone);
	}

	/**
	 * Gets  time zone of the end property of this meeting request.
	 *
	 * @return the end time zone
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public TimeZoneDefinition getEndTimeZone() throws ServiceLocalException {
		return (TimeZoneDefinition) this.getPropertyBag()
				.getObjectFromPropertyDefinition(AppointmentSchema.EndTimeZone);
	}

	/**
	 * Gets the type of conferencing that will be used during the meeting.
	 *
	 * @return the conference type
	 * @throws NumberFormatException
	 *             the number format exception
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public int getConferenceType() throws NumberFormatException,
			ServiceLocalException {
		return (Integer.parseInt(this.getPropertyBag()
				.getObjectFromPropertyDefinition(
						AppointmentSchema.ConferenceType).toString()));
	}

	/**
	 * Gets a value indicating whether new time 
	 * proposals are allowed for attendees of this meeting.
	 *
	 * @return the allow new time proposal
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public boolean getAllowNewTimeProposal() throws ServiceLocalException {
		return (Boolean) this.getPropertyBag().getObjectFromPropertyDefinition(
				AppointmentSchema.AllowNewTimeProposal);
	}

	/**
	 * Gets a value indicating whether this is an online meeting.
	 *
	 * @return the checks if is online meeting
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public boolean getIsOnlineMeeting() throws ServiceLocalException {
		return (Boolean) this.getPropertyBag().getObjectFromPropertyDefinition(
				AppointmentSchema.IsOnlineMeeting);
	}

	/**
	 * Gets the URL of the meeting workspace. A meeting 
	 * workspace is a shared Web site for 
	 * planning meetings and tracking results.
	 *
	 * @return the meeting workspace url
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public String getMeetingWorkspaceUrl() throws ServiceLocalException {
		return (String) this.getPropertyBag().getObjectFromPropertyDefinition(
				AppointmentSchema.MeetingWorkspaceUrl);
	}

	/**
	 * Gets the URL of the Microsoft NetShow online meeting.
	 *
	 * @return the net show url
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public String getNetShowUrl() throws ServiceLocalException {
		return (String) this.getPropertyBag().getObjectFromPropertyDefinition(
				AppointmentSchema.NetShowUrl);
	}
}
