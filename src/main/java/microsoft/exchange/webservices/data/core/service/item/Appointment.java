/*
 * The MIT License
 * Copyright (c) 2012 Microsoft Corporation
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package microsoft.exchange.webservices.data.core.service.item;

import microsoft.exchange.webservices.data.attribute.Attachable;
import microsoft.exchange.webservices.data.attribute.ServiceObjectDefinition;
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.PropertySet;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.service.response.AcceptMeetingInvitationMessage;
import microsoft.exchange.webservices.data.core.service.response.CancelMeetingMessage;
import microsoft.exchange.webservices.data.core.service.response.DeclineMeetingInvitationMessage;
import microsoft.exchange.webservices.data.core.service.response.ResponseMessage;
import microsoft.exchange.webservices.data.core.service.schema.AppointmentSchema;
import microsoft.exchange.webservices.data.core.service.schema.ServiceObjectSchema;
import microsoft.exchange.webservices.data.core.enumeration.service.calendar.AppointmentType;
import microsoft.exchange.webservices.data.core.enumeration.service.ConflictResolutionMode;
import microsoft.exchange.webservices.data.core.enumeration.service.DeleteMode;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.property.LegacyFreeBusyStatus;
import microsoft.exchange.webservices.data.core.enumeration.property.MeetingResponseType;
import microsoft.exchange.webservices.data.core.enumeration.service.ResponseMessageType;
import microsoft.exchange.webservices.data.core.enumeration.service.SendCancellationsMode;
import microsoft.exchange.webservices.data.core.enumeration.service.SendInvitationsMode;
import microsoft.exchange.webservices.data.core.enumeration.service.SendInvitationsOrCancellationsMode;
import microsoft.exchange.webservices.data.core.enumeration.property.WellKnownFolderName;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceLocalException;
import microsoft.exchange.webservices.data.misc.CalendarActionResults;
import microsoft.exchange.webservices.data.misc.TimeSpan;
import microsoft.exchange.webservices.data.property.complex.AppointmentOccurrenceId;
import microsoft.exchange.webservices.data.property.complex.AttendeeCollection;
import microsoft.exchange.webservices.data.property.complex.DeletedOccurrenceInfoCollection;
import microsoft.exchange.webservices.data.property.complex.EmailAddress;
import microsoft.exchange.webservices.data.property.complex.FolderId;
import microsoft.exchange.webservices.data.property.complex.ItemAttachment;
import microsoft.exchange.webservices.data.property.complex.ItemCollection;
import microsoft.exchange.webservices.data.property.complex.ItemId;
import microsoft.exchange.webservices.data.property.complex.MessageBody;
import microsoft.exchange.webservices.data.property.complex.OccurrenceInfo;
import microsoft.exchange.webservices.data.property.complex.OccurrenceInfoCollection;
import microsoft.exchange.webservices.data.property.complex.RecurringAppointmentMasterId;
import microsoft.exchange.webservices.data.property.complex.recurrence.pattern.Recurrence;
import microsoft.exchange.webservices.data.property.complex.time.TimeZoneDefinition;

import java.util.Arrays;
import java.util.Date;

/**
 * Represents an appointment or a meeting. Properties available on appointments
 * are defined in the AppointmentSchema class.
 */
@Attachable
@ServiceObjectDefinition(xmlElementName = XmlElementNames.CalendarItem)
public class Appointment extends Item implements ICalendarActionProvider {

  /**
   * Initializes an unsaved local instance of Appointment". To bind to an
   * existing appointment, use Appointment.Bind() instead.
   *
   * @param service The ExchangeService instance to which this appointmtnt is
   *                bound.
   * @throws Exception the exception
   */
  public Appointment(ExchangeService service) throws Exception {
    super(service);
  }

  /**
   * Initializes a new instance of Appointment.
   *
   * @param parentAttachment the parent attachment
   * @param isNew            If true, attachment is new.
   * @throws Exception the exception
   */
  public Appointment(ItemAttachment parentAttachment, boolean isNew)
      throws Exception {
    // If we're running against Exchange 2007, we need to explicitly preset
    // the StartTimeZone property since Exchange 2007 will otherwise scope
    // start and end to UTC.
    super(parentAttachment);
  }

  /**
   * Binds to an existing appointment and loads the specified set of
   * property. Calling this method results in a call to EWS.
   *
   * @param service     the service
   * @param id          the id
   * @param propertySet the property set
   * @return An Appointment instance representing the appointment
   * corresponding to the specified Id.
   * @throws Exception the exception
   */
  public static Appointment bind(ExchangeService service, ItemId id,
      PropertySet propertySet) throws Exception {
    return service.bindToItem(Appointment.class, id, propertySet);
  }

  /**
   * Binds to an existing appointment and loads its first class property.
   * Calling this method results in a call to EWS.
   *
   * @param service the service
   * @param id      the id
   * @return An Appointment instance representing the appointment
   * corresponding to the specified Id.
   * @throws Exception the exception
   */
  public static Appointment bind(ExchangeService service, ItemId id)
      throws Exception {
    return Appointment.bind(service, id, PropertySet.FirstClassProperties);
  }

  /**
   * Binds to an existing appointment and loads its first class property.
   * Calling this method results in a call to EWS.
   *
   * @param service           the service
   * @param recurringMasterId the recurring master id
   * @param occurenceIndex    the occurence index
   * @return An Appointment instance representing the appointment
   * corresponding to the specified Id.
   * @throws Exception the exception
   */
  public static Appointment bindToOccurrence(ExchangeService service,
      ItemId recurringMasterId, int occurenceIndex) throws Exception {
    return Appointment.bindToOccurrence(service, recurringMasterId,
        occurenceIndex, PropertySet.FirstClassProperties);
  }

  /**
   * Binds to an existing appointment and loads its first class property.
   * Calling this method results in a call to EWS.
   *
   * @param service           the service
   * @param recurringMasterId the recurring master id
   * @param occurenceIndex    the occurence index
   * @param propertySet       the property set
   * @return An Appointment instance representing the appointment
   * corresponding to the specified Id.
   * @throws Exception the exception
   */
  public static Appointment bindToOccurrence(ExchangeService service,
      ItemId recurringMasterId, int occurenceIndex,
      PropertySet propertySet) throws Exception {
    AppointmentOccurrenceId occurenceId = new AppointmentOccurrenceId(
        recurringMasterId.getUniqueId(), occurenceIndex);
    return Appointment.bind(service, occurenceId, propertySet);
  }

  /**
   * Binds to the master appointment of a recurring series and loads its first
   * class property. Calling this method results in a call to EWS.
   *
   * @param service      the service
   * @param occurrenceId the occurrence id
   * @return An Appointment instance representing the appointment
   * corresponding to the specified Id.
   * @throws Exception the exception
   */
  public static Appointment bindToRecurringMaster(ExchangeService service,
      ItemId occurrenceId) throws Exception {
    return Appointment.bindToRecurringMaster(service, occurrenceId,
        PropertySet.FirstClassProperties);
  }

  /**
   * Binds to the master appointment of a recurring series and loads its first
   * class property. Calling this method results in a call to EWS.
   *
   * @param service      the service
   * @param occurrenceId the occurrence id
   * @param propertySet  the property set
   * @return An Appointment instance representing the appointment
   * corresponding to the specified Id.
   * @throws Exception the exception
   */
  public static Appointment bindToRecurringMaster(ExchangeService service,
      ItemId occurrenceId, PropertySet propertySet) throws Exception {
    RecurringAppointmentMasterId recurringMasterId =
        new RecurringAppointmentMasterId(
            occurrenceId.getUniqueId());
    return Appointment.bind(service, recurringMasterId, propertySet);
  }

  /**
   * Internal method to return the schema associated with this type of object.
   *
   * @return The schema associated with this type of object
   */
  @Override public ServiceObjectSchema getSchema() {
    return AppointmentSchema.Instance;
  }

  /**
   * Gets the minimum required server version.
   *
   * @return Earliest Exchange version in which this service object type is
   * supported.
   */
  @Override public ExchangeVersion getMinimumRequiredServerVersion() {
    return ExchangeVersion.Exchange2007_SP1;
  }

  /**
   * Determines whether property defined with
   * ScopedDateTimePropertyDefinition require custom time zone scoping.
   *
   * @return if this item type requires custom scoping for scoped date/time
   * property; otherwise, .
   */
  @Override
  protected boolean getIsCustomDateTimeScopingRequired() {
    return true;
  }

  /**
   * Validates this instance.
   *
   * @throws Exception
   */
  @Override public void validate() throws Exception {
    super.validate();

    //  PS # 250452: Make sure that if we're
    //on the Exchange2007_SP1 schema version,
    // if any of the following
    //  property are set or updated:
    //      o   Start
    //      o   End
    //      o   IsAllDayEvent
    //      o   Recurrence
    //  ... then, we must send the MeetingTimeZone element
    // (which is generated from StartTimeZone for
    //  Exchange2007_SP1 request (see
    //StartTimeZonePropertyDefinition.cs).
    // If the StartTimeZone isn't
    //  in the property bag, then throw, because clients must
    // supply the proper time zone - either by
    //  loading it from a currently-existing appointment,
    //or by setting it directly.
    // Otherwise, to dirty
    //  the StartTimeZone property, we just set it to its current value.
    if ((this.getService().getRequestedServerVersion() == ExchangeVersion.Exchange2007_SP1) &&
        !(this.getService().getExchange2007CompatibilityMode())) {
      if (this.getPropertyBag().isPropertyUpdated(AppointmentSchema.Start) ||
          this.getPropertyBag().isPropertyUpdated(AppointmentSchema.End) ||
          this.getPropertyBag().isPropertyUpdated(AppointmentSchema.IsAllDayEvent) ||
          this.getPropertyBag().isPropertyUpdated(AppointmentSchema.Recurrence)) {
        //  If the property isn't in the property bag, throw....
        if (!this.getPropertyBag().contains(AppointmentSchema.StartTimeZone)) {
          throw new ServiceLocalException("StartTimeZone required when setting the Start, End, IsAllDayEvent, "
                                          + "or Recurrence property.  You must load or assign this property "
                                          + "before attempting to update the appointment.");
          //getStartTimeZoneRequired());
        }

        //  Otherwise, set the time zone to its current value to
        // force it to be sent with the request.
        this.setStartTimeZone(this.getStartTimeZone());
      }
    }
  }

  /**
   * Creates a reply response to the organizer and/or attendees of the
   * meeting.
   *
   * @param replyAll the reply all
   * @return A ResponseMessage representing the reply response that can
   * subsequently be modified and sent.
   * @throws Exception the exception
   */
  public ResponseMessage createReply(boolean replyAll) throws Exception {
    this.throwIfThisIsNew();

    return new ResponseMessage(this,
        replyAll ? ResponseMessageType.ReplyAll :
            ResponseMessageType.Reply);
  }

  /**
   * Replies to the organizer and/or the attendees of the meeting. Calling
   * this method results in a call to EWS.
   *
   * @param bodyPrefix the body prefix
   * @param replyAll   the reply all
   * @throws Exception the exception
   */
  public void reply(MessageBody bodyPrefix, boolean replyAll)
      throws Exception {
    ResponseMessage responseMessage = this.createReply(replyAll);

    responseMessage.setBodyPrefix(bodyPrefix);
    responseMessage.sendAndSaveCopy();
  }

  /**
   * Creates a forward message from this appointment.
   *
   * @return A ResponseMessage representing the forward response that can
   * subsequently be modified and sent.
   * @throws Exception the exception
   */
  public ResponseMessage createForward() throws Exception {
    this.throwIfThisIsNew();
    return new ResponseMessage(this, ResponseMessageType.Forward);
  }

  /**
   * Forwards the appointment. Calling this method results in a call to EWS.
   *
   * @param bodyPrefix   the body prefix
   * @param toRecipients the to recipients
   * @throws Exception the exception
   */
  public void forward(MessageBody bodyPrefix, EmailAddress... toRecipients)
      throws Exception {
    if (null != toRecipients) {
      forward(bodyPrefix, Arrays.asList(toRecipients));
    }
  }

  /**
   * Forwards the appointment. Calling this method results in a call to EWS.
   *
   * @param bodyPrefix   the body prefix
   * @param toRecipients the to recipients
   * @throws Exception the exception
   */
  public void forward(MessageBody bodyPrefix,
      Iterable<EmailAddress> toRecipients) throws Exception {
    ResponseMessage responseMessage = this.createForward();

    responseMessage.setBodyPrefix(bodyPrefix);
    responseMessage.getToRecipients()
        .addEmailRange(toRecipients.iterator());

    responseMessage.sendAndSaveCopy();
  }

  /**
   * Saves this appointment in the specified folder. Calling this method
   * results in at least one call to EWS. Mutliple calls to EWS might be made
   * if attachments have been added.
   *
   * @param destinationFolderName the destination folder name
   * @param sendInvitationsMode   the send invitations mode
   * @throws Exception the exception
   */
  public void save(WellKnownFolderName destinationFolderName,
      SendInvitationsMode sendInvitationsMode) throws Exception {
    this.internalCreate(new FolderId(destinationFolderName), null,
        sendInvitationsMode);
  }

  /**
   * Saves this appointment in the specified folder. Calling this method
   * results in at least one call to EWS. Mutliple calls to EWS might be made
   * if attachments have been added.
   *
   * @param destinationFolderId the destination folder id
   * @param sendInvitationsMode the send invitations mode
   * @throws Exception the exception
   */
  public void save(FolderId destinationFolderId,
      SendInvitationsMode sendInvitationsMode) throws Exception {
    EwsUtilities.validateParam(destinationFolderId, "destinationFolderId");

    this.internalCreate(destinationFolderId, null, sendInvitationsMode);
  }

  /**
   * Saves this appointment in the Calendar folder. Calling this method
   * results in at least one call to EWS. Mutliple calls to EWS might be made
   * if attachments have been added.
   *
   * @param sendInvitationsMode the send invitations mode
   * @throws Exception the exception
   */
  public void save(SendInvitationsMode sendInvitationsMode) throws Exception {
    this.internalCreate(null, null, sendInvitationsMode);
  }

  /**
   * Applies the local changes that have been made to this appointment.
   * Calling this method results in at least one call to EWS. Mutliple calls
   * to EWS might be made if attachments have been added or removed.
   *
   * @param conflictResolutionMode             the conflict resolution mode
   * @param sendInvitationsOrCancellationsMode the send invitations or cancellations mode
   * @throws Exception the exception
   */
  public void update(
      ConflictResolutionMode conflictResolutionMode,
      SendInvitationsOrCancellationsMode
          sendInvitationsOrCancellationsMode)
      throws Exception {
    this.internalUpdate(null, conflictResolutionMode, null,
        sendInvitationsOrCancellationsMode);
  }

  /**
   * Deletes this appointment. Calling this method results in a call to EWS.
   *
   * @param deleteMode            the delete mode
   * @param sendCancellationsMode the send cancellations mode
   * @throws Exception the exception
   */
  public void delete(DeleteMode deleteMode,
      SendCancellationsMode sendCancellationsMode) throws Exception {
    this.internalDelete(deleteMode, sendCancellationsMode, null);
  }

  /**
   * Creates a local meeting acceptance message that can be customized and
   * sent.
   *
   * @param tentative the tentative
   * @return An AcceptMeetingInvitationMessage representing the meeting
   * acceptance message.
   * @throws Exception the exception
   */
  public AcceptMeetingInvitationMessage createAcceptMessage(boolean tentative)
      throws Exception {
    return new AcceptMeetingInvitationMessage(this, tentative);
  }

  /**
   * Creates a local meeting acceptance message that can be customized and
   * sent.
   *
   * @return A CancelMeetingMessage representing the meeting cancellation
   * message.
   * @throws Exception the exception
   */
  public CancelMeetingMessage createCancelMeetingMessage() throws Exception {
    return new CancelMeetingMessage(this);
  }

  /**
   * Creates a local meeting declination message that can be customized and
   * sent.
   *
   * @return A DeclineMeetingInvitation representing the meeting declination
   * message.
   * @throws Exception the exception
   */
  public DeclineMeetingInvitationMessage createDeclineMessage()
      throws Exception {
    return new DeclineMeetingInvitationMessage(this);
  }

  /**
   * Accepts the meeting. Calling this method results in a call to EWS.
   *
   * @param sendResponse the send response
   * @return A CalendarActionResults object containing the various item that
   * were created or modified as a results of this operation.
   * @throws Exception the exception
   */
  public CalendarActionResults accept(boolean sendResponse) throws Exception {
    return this.internalAccept(false, sendResponse);
  }

  /**
   * Tentatively accepts the meeting. Calling this method results in a call to
   * EWS.
   *
   * @param sendResponse the send response
   * @return A CalendarActionResults object containing the various item that
   * were created or modified as a results of this operation.
   * @throws Exception the exception
   */
  public CalendarActionResults acceptTentatively(boolean sendResponse)
      throws Exception {
    return this.internalAccept(true, sendResponse);
  }

  /**
   * Accepts the meeting.
   *
   * @param tentative    the tentative
   * @param sendResponse the send response
   * @return A CalendarActionResults object containing the various item that
   * were created or modified as a results of this operation.
   * @throws Exception the exception
   */
  protected CalendarActionResults internalAccept(boolean tentative,
      boolean sendResponse) throws Exception {
    AcceptMeetingInvitationMessage accept = this
        .createAcceptMessage(tentative);

    if (sendResponse) {
      return accept.calendarSendAndSaveCopy();
    } else {
      return accept.calendarSave();
    }
  }

  /**
   * Cancels the meeting and sends cancellation messages to all attendees.
   * Calling this method results in a call to EWS.
   *
   * @return A CalendarActionResults object containing the various item that
   * were created or modified as a results of this operation.
   * @throws Exception the exception
   */
  public CalendarActionResults cancelMeeting() throws Exception {
    return this.createCancelMeetingMessage().calendarSendAndSaveCopy();
  }

  /**
   * Cancels the meeting and sends cancellation messages to all attendees.
   * Calling this method results in a call to EWS.
   *
   * @param cancellationMessageText the cancellation message text
   * @return A CalendarActionResults object containing the various item that
   * were created or modified as a results of this operation.
   * @throws Exception the exception
   */
  public CalendarActionResults cancelMeeting(String cancellationMessageText)
      throws Exception {
    CancelMeetingMessage cancelMsg = this.createCancelMeetingMessage();
    cancelMsg.setBody(new MessageBody(cancellationMessageText));
    return cancelMsg.calendarSendAndSaveCopy();
  }

  /**
   * Declines the meeting invitation. Calling this method results in a call to
   * EWS.
   *
   * @param sendResponse the send response
   * @return A CalendarActionResults object containing the various item that
   * were created or modified as a results of this operation.
   * @throws Exception the exception
   */
  public CalendarActionResults decline(boolean
      sendResponse) throws Exception {
    DeclineMeetingInvitationMessage decline = this.createDeclineMessage();

    if (sendResponse) {
      return decline.calendarSendAndSaveCopy();
    } else {
      return decline.calendarSave();
    }
  }

  /**
   * Gets the default setting for sending cancellations on Delete.
   *
   * @return If Delete() is called on Appointment, we want to send
   * cancellations and save a copy.
   */
  @Override
  protected SendCancellationsMode getDefaultSendCancellationsMode() {
    return SendCancellationsMode.SendToAllAndSaveCopy;
  }

  /**
   * Gets the default settings for sending invitations on Save.
   *
   * @return the default send invitations mode
   */
  @Override
  protected SendInvitationsMode getDefaultSendInvitationsMode() {
    return SendInvitationsMode.SendToAllAndSaveCopy;
  }

  /**
   * Gets the default settings for sending invitations on Save.
   *
   * @return the default send invitations or cancellations mode
   */
  @Override
  protected SendInvitationsOrCancellationsMode
  getDefaultSendInvitationsOrCancellationsMode() {
    return SendInvitationsOrCancellationsMode.SendToAllAndSaveCopy;
  }

  // Properties

  /**
   * Gets the start time of the appointment.
   *
   * @return the start
   * @throws ServiceLocalException the service local exception
   */
  public Date getStart() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        AppointmentSchema.Start);
  }

  /**
   * Sets the start.
   *
   * @param value the new start
   * @throws Exception the exception
   */
  public void setStart(Date value) throws Exception {
    this.getPropertyBag().setObjectFromPropertyDefinition(
        AppointmentSchema.Start, value);
  }

  /**
   * Gets or sets the end time of the appointment.
   *
   * @return the end
   * @throws ServiceLocalException the service local exception
   */
  public Date getEnd() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        AppointmentSchema.End);
  }

  /**
   * Sets the end.
   *
   * @param value the new end
   * @throws Exception the exception
   */
  public void setEnd(Date value) throws Exception {
    this.getPropertyBag().setObjectFromPropertyDefinition(
        AppointmentSchema.End, value);
  }

  /**
   * Gets the original start time of this appointment.
   *
   * @return the original start
   * @throws ServiceLocalException the service local exception
   */
  public Date getOriginalStart() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        AppointmentSchema.OriginalStart);
  }

  /**
   * Gets a value indicating whether this appointment is an all day
   * event.
   *
   * @return the checks if is all day event
   * @throws ServiceLocalException the service local exception
   */
  public Boolean getIsAllDayEvent() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        AppointmentSchema.IsAllDayEvent);
  }

  /**
   * Sets the checks if is all day event.
   *
   * @param value the new checks if is all day event
   * @throws Exception the exception
   */
  public void setIsAllDayEvent(Boolean value) throws Exception {
    this.getPropertyBag().setObjectFromPropertyDefinition(
        AppointmentSchema.IsAllDayEvent, value);
  }

  /**
   * Gets  a value indicating the free/busy status of the owner of this
   * appointment.
   *
   * @return the legacy free busy status
   * @throws ServiceLocalException the service local exception
   */
  public LegacyFreeBusyStatus getLegacyFreeBusyStatus()
      throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        AppointmentSchema.LegacyFreeBusyStatus);
  }

  /**
   * Sets the legacy free busy status.
   *
   * @param value the new legacy free busy status
   * @throws Exception the exception
   */
  public void setLegacyFreeBusyStatus(LegacyFreeBusyStatus value)
      throws Exception {
    this.getPropertyBag().setObjectFromPropertyDefinition(
        AppointmentSchema.LegacyFreeBusyStatus, value);
  }

  /**
   * Gets  the location of this appointment.
   *
   * @return the location
   * @throws ServiceLocalException the service local exception
   */
  public String getLocation() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        AppointmentSchema.Location);
  }

  /**
   * Sets the location.
   *
   * @param value the new location
   * @throws Exception the exception
   */
  public void setLocation(String value) throws Exception {
    this.getPropertyBag().setObjectFromPropertyDefinition(
        AppointmentSchema.Location, value);
  }

  /**
   * Gets a text indicating when this appointment occurs. The text returned by
   * When is localized using the Exchange Server culture or using the culture
   * specified in the PreferredCulture property of the ExchangeService object
   * this appointment is bound to.
   *
   * @return the when
   * @throws ServiceLocalException the service local exception
   */
  public String getWhen() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        AppointmentSchema.When);
  }

  /**
   * Gets a value indicating whether the appointment is a meeting.
   *
   * @return the checks if is meeting
   * @throws ServiceLocalException the service local exception
   */
  public Boolean getIsMeeting() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        AppointmentSchema.IsMeeting);
  }

  /**
   * Gets a value indicating whether the appointment has been cancelled.
   *
   * @return the checks if is cancelled
   * @throws ServiceLocalException the service local exception
   */
  public Boolean getIsCancelled() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        AppointmentSchema.IsCancelled);
  }

  /**
   * Gets a value indicating whether the appointment is recurring.
   *
   * @return the checks if is recurring
   * @throws ServiceLocalException the service local exception
   */
  public Boolean getIsRecurring() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        AppointmentSchema.IsRecurring);
  }

  /**
   * Gets a value indicating whether the meeting request has already been
   * sent.
   *
   * @return the meeting request was sent
   * @throws ServiceLocalException the service local exception
   */
  public Boolean getMeetingRequestWasSent() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        AppointmentSchema.MeetingRequestWasSent);
  }

  /**
   * Gets  a value indicating whether response are requested when
   * invitations are sent for this meeting.
   *
   * @return the checks if is response requested
   * @throws ServiceLocalException the service local exception
   */
  public Boolean getIsResponseRequested() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        AppointmentSchema.IsResponseRequested);
  }

  /**
   * Sets the checks if is response requested.
   *
   * @param value the new checks if is response requested
   * @throws Exception the exception
   */
  public void setIsResponseRequested(Boolean value) throws Exception {
    this.getPropertyBag().setObjectFromPropertyDefinition(
        AppointmentSchema.IsResponseRequested, value);
  }

  /**
   * Gets a value indicating the type of this appointment.
   *
   * @return the appointment type
   * @throws ServiceLocalException the service local exception
   */
  public AppointmentType getAppointmentType() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        AppointmentSchema.AppointmentType);
  }

  /**
   * Gets a value indicating what was the last response of the user that
   * loaded this meeting.
   *
   * @return the my response type
   * @throws ServiceLocalException the service local exception
   */
  public MeetingResponseType getMyResponseType()
      throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        AppointmentSchema.MyResponseType);
  }

  /**
   * Gets the organizer of this meeting. The Organizer property is read-only
   * and is only relevant for attendees. The organizer of a meeting is
   * automatically set to the user that created the meeting.
   *
   * @return the organizer
   * @throws ServiceLocalException the service local exception
   */
  public EmailAddress getOrganizer() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        AppointmentSchema.Organizer);
  }

  /**
   * Gets a list of required attendees for this meeting.
   *
   * @return the required attendees
   * @throws ServiceLocalException the service local exception
   */
  public AttendeeCollection getRequiredAttendees()
      throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        AppointmentSchema.RequiredAttendees);
  }

  /**
   * Gets a list of optional attendeed for this meeting.
   *
   * @return the optional attendees
   * @throws ServiceLocalException the service local exception
   */
  public AttendeeCollection getOptionalAttendees()
      throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        AppointmentSchema.OptionalAttendees);
  }

  /**
   * Gets a list of resources for this meeting.
   *
   * @return the resources
   * @throws ServiceLocalException the service local exception
   */
  public AttendeeCollection getResources() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        AppointmentSchema.Resources);
  }

  /**
   * Gets the number of calendar entries that conflict with this appointment
   * in the authenticated user's calendar.
   *
   * @return the conflicting meeting count
   * @throws ServiceLocalException the service local exception
   */
  public Integer getConflictingMeetingCount() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        AppointmentSchema.ConflictingMeetingCount);
  }

  /**
   * Gets the number of calendar entries that are adjacent to this appointment
   * in the authenticated user's calendar.
   *
   * @return the adjacent meeting count
   * @throws ServiceLocalException the service local exception
   */
  public Integer getAdjacentMeetingCount() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        AppointmentSchema.AdjacentMeetingCount);
  }

  /**
   * Gets a list of meetings that conflict with this appointment in the
   * authenticated user's calendar.
   *
   * @return the conflicting meetings
   * @throws ServiceLocalException the service local exception
   */
  public ItemCollection<Appointment> getConflictingMeetings()
      throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        AppointmentSchema.ConflictingMeetings);
  }

  /**
   * Gets a list of meetings that conflict with this appointment in the
   * authenticated user's calendar.
   *
   * @return the adjacent meetings
   * @throws ServiceLocalException the service local exception
   */
  public ItemCollection<Appointment> getAdjacentMeetings()
      throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
            AppointmentSchema.AdjacentMeetings);
  }

  /**
   * Gets the duration of this appointment.
   *
   * @return the duration
   * @throws ServiceLocalException the service local exception
   */
  public TimeSpan getDuration() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        AppointmentSchema.Duration);
  }

  /**
   * Gets the name of the time zone this appointment is defined in.
   *
   * @return the time zone
   * @throws ServiceLocalException the service local exception
   */
  public String getTimeZone() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        AppointmentSchema.TimeZone);
  }

  /**
   * Gets the time when the attendee replied to the meeting request.
   *
   * @return the appointment reply time
   * @throws ServiceLocalException the service local exception
   */
  public Date getAppointmentReplyTime() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        AppointmentSchema.AppointmentReplyTime);
  }

  /**
   * Gets the sequence number of this appointment.
   *
   * @return the appointment sequence number
   * @throws ServiceLocalException the service local exception
   */
  public Integer getAppointmentSequenceNumber() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        AppointmentSchema.AppointmentSequenceNumber);
  }

  /**
   * Gets the state of this appointment.
   *
   * @return the appointment state
   * @throws ServiceLocalException the service local exception
   */
  public Integer getAppointmentState() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        AppointmentSchema.AppointmentState);
  }

  /**
   * Gets the recurrence pattern for this appointment. Available
   * recurrence pattern classes include Recurrence.DailyPattern,
   * Recurrence.MonthlyPattern and Recurrence.YearlyPattern.
   *
   * @return the recurrence
   * @throws ServiceLocalException the service local exception
   */
  public Recurrence getRecurrence() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        AppointmentSchema.Recurrence);
  }

  /**
   * Sets the recurrence.
   *
   * @param value the new recurrence
   * @throws Exception the exception
   */
  public void setRecurrence(Recurrence value) throws Exception {
    if (value != null) {
      if (value.isRegenerationPattern()) {
        throw new ServiceLocalException("Regeneration pattern can only be used with Task item.");
      }
    }
    this.getPropertyBag().setObjectFromPropertyDefinition(
        AppointmentSchema.Recurrence, value);
  }

  /**
   * Gets an OccurrenceInfo identifying the first occurrence of this meeting.
   *
   * @return the first occurrence
   * @throws ServiceLocalException the service local exception
   */
  public OccurrenceInfo getFirstOccurrence() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
          AppointmentSchema.FirstOccurrence);
  }

  /**
   * Gets an OccurrenceInfo identifying the first occurrence of this meeting.
   *
   * @return the last occurrence
   * @throws ServiceLocalException the service local exception
   */
  public OccurrenceInfo getLastOccurrence() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
          AppointmentSchema.LastOccurrence);
  }

  /**
   * Gets a list of modified occurrences for this meeting.
   *
   * @return the modified occurrences
   * @throws ServiceLocalException the service local exception
   */
  public OccurrenceInfoCollection getModifiedOccurrences()
      throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
          AppointmentSchema.ModifiedOccurrences);
  }

  /**
   * Gets a list of deleted occurrences for this meeting.
   *
   * @return the deleted occurrences
   * @throws ServiceLocalException the service local exception
   */
  public DeletedOccurrenceInfoCollection getDeletedOccurrences()
      throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
          AppointmentSchema.DeletedOccurrences);
  }

  /**
   * Gets the start time zone.
   *
   * @return the start time zone
   * @throws ServiceLocalException the service local exception
   */
  public TimeZoneDefinition getStartTimeZone() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
          AppointmentSchema.StartTimeZone);
  }

  /**
   * Sets the start time zone.
   *
   * @param value the new start time zone
   * @throws Exception the exception
   */
  public void setStartTimeZone(TimeZoneDefinition value) throws Exception {
    this.getPropertyBag().setObjectFromPropertyDefinition(
        AppointmentSchema.StartTimeZone, value);

  }

  /**
   * Gets the start time zone.
   *
   * @return the start time zone
   * @throws ServiceLocalException the service local exception
   */
  public TimeZoneDefinition getEndTimeZone() throws ServiceLocalException {
    return getPropertyBag()
        .getObjectFromPropertyDefinition(AppointmentSchema.EndTimeZone);
  }

  /**
   * Sets the start time zone.
   *
   * @param value the new end time zone
   * @throws Exception the exception
   */
  public void setEndTimeZone(TimeZoneDefinition value) throws Exception {
    this.getPropertyBag().setObjectFromPropertyDefinition(
        AppointmentSchema.EndTimeZone, value);

  }

  /**
   * Gets  the type of conferencing that will be used during the
   * meeting.
   *
   * @return the conference type
   * @throws ServiceLocalException the service local exception
   */
  public Integer getConferenceType() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        AppointmentSchema.ConferenceType);
  }

  /**
   * Sets the conference type.
   *
   * @param value the new conference type
   * @throws Exception the exception
   */
  public void setConferenceType(Integer value) throws Exception {
    this.getPropertyBag().setObjectFromPropertyDefinition(
        AppointmentSchema.ConferenceType, value);
  }

  /**
   * Gets a value indicating whether new time proposals are allowed
   * for attendees of this meeting.
   *
   * @return the allow new time proposal
   * @throws ServiceLocalException the service local exception
   */
  public Boolean getAllowNewTimeProposal() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        AppointmentSchema.AllowNewTimeProposal);
  }

  /**
   * Sets the allow new time proposal.
   *
   * @param value the new allow new time proposal
   * @throws Exception the exception
   */
  public void setAllowNewTimeProposal(Boolean value) throws Exception {
    this.getPropertyBag().setObjectFromPropertyDefinition(
        AppointmentSchema.AllowNewTimeProposal, value);
  }

  /**
   * Gets  a value indicating whether this is an online meeting.
   *
   * @return the checks if is online meeting
   * @throws ServiceLocalException the service local exception
   */
  public Boolean getIsOnlineMeeting() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        AppointmentSchema.IsOnlineMeeting);
  }

  /**
   * Sets the checks if is online meeting.
   *
   * @param value the new checks if is online meeting
   * @throws Exception the exception
   */
  public void setIsOnlineMeeting(Boolean value) throws Exception {
    this.getPropertyBag().setObjectFromPropertyDefinition(
        AppointmentSchema.IsOnlineMeeting, value);
  }

  /**
   * Gets  the URL of the meeting workspace. A meeting workspace is a
   * shared Web site for planning meetings and tracking results.
   *
   * @return the meeting workspace url
   * @throws ServiceLocalException the service local exception
   */
  public String getMeetingWorkspaceUrl() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        AppointmentSchema.MeetingWorkspaceUrl);
  }

  /**
   * Sets the meeting workspace url.
   *
   * @param value the new meeting workspace url
   * @throws Exception the exception
   */
  public void setMeetingWorkspaceUrl(String value) throws Exception {
    this.getPropertyBag().setObjectFromPropertyDefinition(
        AppointmentSchema.MeetingWorkspaceUrl, value);
  }

  /**
   * Gets the URL of the Microsoft NetShow online meeting.
   *
   * @return the net show url
   * @throws ServiceLocalException the service local exception
   */
  public String getNetShowUrl() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        AppointmentSchema.NetShowUrl);
  }

  /**
   * Sets the net show url.
   *
   * @param value the new net show url
   * @throws Exception the exception
   */
  public void setNetShowUrl(String value) throws Exception {
    this.getPropertyBag().setObjectFromPropertyDefinition(
        AppointmentSchema.NetShowUrl, value);
  }

  /**
   * Gets the ICalendar Uid.
   *
   * @return the i cal uid
   * @throws ServiceLocalException the service local exception
   */
  public String getICalUid() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        AppointmentSchema.ICalUid);
  }

  /**
   * Sets the ICalendar Uid.
   *
   * @param value the i cal uid
   * @throws Exception
   *///this.PropertyBag[AppointmentSchema.ICalUid] = value;
  public void setICalUid(String value) throws Exception {
    this.getPropertyBag().setObjectFromPropertyDefinition(
        AppointmentSchema.ICalUid, value);
  }

  /**
   * Gets the ICalendar RecurrenceId.
   *
   * @return the i cal recurrence id
   * @throws ServiceLocalException the service local exception
   */
  public Date getICalRecurrenceId() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        AppointmentSchema.ICalRecurrenceId);
  }

  /**
   * Gets the ICalendar DateTimeStamp.
   *
   * @return the i cal date time stamp
   * @throws ServiceLocalException the service local exception
   */
  public Date getICalDateTimeStamp() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        AppointmentSchema.ICalDateTimeStamp);
  }
}
