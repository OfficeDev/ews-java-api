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

package microsoft.exchange.webservices.data.core.service.schema;

import microsoft.exchange.webservices.data.attribute.Schema;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.service.item.Appointment;
import microsoft.exchange.webservices.data.core.enumeration.service.calendar.AppointmentType;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.property.LegacyFreeBusyStatus;
import microsoft.exchange.webservices.data.core.enumeration.property.MeetingResponseType;
import microsoft.exchange.webservices.data.core.enumeration.property.PropertyDefinitionFlags;
import microsoft.exchange.webservices.data.property.complex.AttendeeCollection;
import microsoft.exchange.webservices.data.property.complex.DeletedOccurrenceInfoCollection;
import microsoft.exchange.webservices.data.property.complex.EmailAddress;
import microsoft.exchange.webservices.data.property.complex.ICreateComplexPropertyDelegate;
import microsoft.exchange.webservices.data.property.complex.ItemCollection;
import microsoft.exchange.webservices.data.property.complex.OccurrenceInfo;
import microsoft.exchange.webservices.data.property.complex.OccurrenceInfoCollection;
import microsoft.exchange.webservices.data.property.definition.BoolPropertyDefinition;
import microsoft.exchange.webservices.data.property.definition.ComplexPropertyDefinition;
import microsoft.exchange.webservices.data.property.definition.ContainedPropertyDefinition;
import microsoft.exchange.webservices.data.property.definition.DateTimePropertyDefinition;
import microsoft.exchange.webservices.data.property.definition.GenericPropertyDefinition;
import microsoft.exchange.webservices.data.property.definition.IntPropertyDefinition;
import microsoft.exchange.webservices.data.property.definition.MeetingTimeZonePropertyDefinition;
import microsoft.exchange.webservices.data.property.definition.PropertyDefinition;
import microsoft.exchange.webservices.data.property.definition.RecurrencePropertyDefinition;
import microsoft.exchange.webservices.data.property.definition.StartTimeZonePropertyDefinition;
import microsoft.exchange.webservices.data.property.definition.StringPropertyDefinition;
import microsoft.exchange.webservices.data.property.definition.TimeSpanPropertyDefinition;
import microsoft.exchange.webservices.data.property.definition.TimeZonePropertyDefinition;

import java.util.EnumSet;

/**
 * Represents the schema for appointment and meeting request.
 */
@Schema
public class AppointmentSchema extends ItemSchema {

  /**
   * Field URIs for Appointment.
   */
  private static interface FieldUris {

    /**
     * The Start.
     */
    String Start = "calendar:Start";

    /**
     * The End.
     */
    String End = "calendar:End";

    /**
     * The Original start.
     */
    String OriginalStart = "calendar:OriginalStart";

    /**
     * The Is all day event.
     */
    String IsAllDayEvent = "calendar:IsAllDayEvent";

    /**
     * The Legacy free busy status.
     */
    String LegacyFreeBusyStatus = "calendar:LegacyFreeBusyStatus";

    /**
     * The Location.
     */
    String Location = "calendar:Location";

    /**
     * The When.
     */
    String When = "calendar:When";

    /**
     * The Is meeting.
     */
    String IsMeeting = "calendar:IsMeeting";

    /**
     * The Is cancelled.
     */
    String IsCancelled = "calendar:IsCancelled";

    /**
     * The Is recurring.
     */
    String IsRecurring = "calendar:IsRecurring";

    /**
     * The Meeting request was sent.
     */
    String MeetingRequestWasSent = "calendar:MeetingRequestWasSent";

    /**
     * The Is response requested.
     */
    String IsResponseRequested = "calendar:IsResponseRequested";

    /**
     * The Calendar item type.
     */
    String CalendarItemType = "calendar:CalendarItemType";

    /**
     * The My response type.
     */
    String MyResponseType = "calendar:MyResponseType";

    /**
     * The Organizer.
     */
    String Organizer = "calendar:Organizer";

    /**
     * The Required attendees.
     */
    String RequiredAttendees = "calendar:RequiredAttendees";

    /**
     * The Optional attendees.
     */
    String OptionalAttendees = "calendar:OptionalAttendees";

    /**
     * The Resources.
     */
    String Resources = "calendar:Resources";

    /**
     * The Conflicting meeting count.
     */
    String ConflictingMeetingCount = "calendar:ConflictingMeetingCount";

    /**
     * The Adjacent meeting count.
     */
    String AdjacentMeetingCount = "calendar:AdjacentMeetingCount";

    /**
     * The Conflicting meetings.
     */
    String ConflictingMeetings = "calendar:ConflictingMeetings";

    /**
     * The Adjacent meetings.
     */
    String AdjacentMeetings = "calendar:AdjacentMeetings";

    /**
     * The Duration.
     */
    String Duration = "calendar:Duration";

    /**
     * The Time zone.
     */
    String TimeZone = "calendar:TimeZone";

    /**
     * The Appointment reply time.
     */
    String AppointmentReplyTime = "calendar:AppointmentReplyTime";

    /**
     * The Appointment sequence number.
     */
    String AppointmentSequenceNumber = "calendar:AppointmentSequenceNumber";

    /**
     * The Appointment state.
     */
    String AppointmentState = "calendar:AppointmentState";

    /**
     * The Recurrence.
     */
    String Recurrence = "calendar:Recurrence";

    /**
     * The First occurrence.
     */
    String FirstOccurrence = "calendar:FirstOccurrence";

    /**
     * The Last occurrence.
     */
    String LastOccurrence = "calendar:LastOccurrence";

    /**
     * The Modified occurrences.
     */
    String ModifiedOccurrences = "calendar:ModifiedOccurrences";

    /**
     * The Deleted occurrences.
     */
    String DeletedOccurrences = "calendar:DeletedOccurrences";

    /**
     * The Meeting time zone.
     */
    String MeetingTimeZone = "calendar:MeetingTimeZone";

    /**
     * The Start time zone.
     */
    String StartTimeZone = "calendar:StartTimeZone";

    /**
     * The End time zone.
     */
    String EndTimeZone = "calendar:EndTimeZone";

    /**
     * The Conference type.
     */
    String ConferenceType = "calendar:ConferenceType";

    /**
     * The Allow new time proposal.
     */
    String AllowNewTimeProposal = "calendar:AllowNewTimeProposal";

    /**
     * The Is online meeting.
     */
    String IsOnlineMeeting = "calendar:IsOnlineMeeting";

    /**
     * The Meeting workspace url.
     */
    String MeetingWorkspaceUrl = "calendar:MeetingWorkspaceUrl";

    /**
     * The Net show url.
     */
    String NetShowUrl = "calendar:NetShowUrl";

    /**
     * The Uid.
     */
    String Uid = "calendar:UID";

    /**
     * The Recurrence id.
     */
    String RecurrenceId = "calendar:RecurrenceId";

    /**
     * The Date time stamp.
     */
    String DateTimeStamp = "calendar:DateTimeStamp";
  }

  // Defines the StartTimeZone property.
  /**
   * The Constant StartTimeZone.
   */
  public static final PropertyDefinition StartTimeZone =
      new StartTimeZonePropertyDefinition(
          XmlElementNames.StartTimeZone, FieldUris.StartTimeZone, EnumSet.of(
          PropertyDefinitionFlags.CanSet,
          PropertyDefinitionFlags.CanUpdate,
          PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  // Defines the EndTimeZone property.
  /**
   * The Constant EndTimeZone.
   */
  public static final PropertyDefinition EndTimeZone =
      new TimeZonePropertyDefinition(
          XmlElementNames.EndTimeZone, FieldUris.EndTimeZone, EnumSet.of(
          PropertyDefinitionFlags.CanSet,
          PropertyDefinitionFlags.CanUpdate,
          PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2010);

  // Defines the Start property.
  /**
   * The Constant Start.
   */
  public static final PropertyDefinition Start =
      new DateTimePropertyDefinition(
          XmlElementNames.Start, FieldUris.Start, EnumSet.of(
          PropertyDefinitionFlags.CanSet,
          PropertyDefinitionFlags.CanUpdate,
          PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  // Defines the End property.
  /**
   * The Constant End.
   */
  public static final PropertyDefinition End = new DateTimePropertyDefinition(
      XmlElementNames.End, FieldUris.End, EnumSet.of(
      PropertyDefinitionFlags.CanSet,
      PropertyDefinitionFlags.CanUpdate,
      PropertyDefinitionFlags.CanFind),
      ExchangeVersion.Exchange2007_SP1);

  // Defines the OriginalStart property.
  /**
   * The Constant OriginalStart.
   */
  public static final PropertyDefinition OriginalStart =
      new DateTimePropertyDefinition(
          XmlElementNames.OriginalStart, FieldUris.OriginalStart,
          ExchangeVersion.Exchange2007_SP1);

  // Defines the IsAllDayEvent property.
  /**
   * The Constant IsAllDayEvent.
   */
  public static final PropertyDefinition IsAllDayEvent =
      new BoolPropertyDefinition(
          XmlElementNames.IsAllDayEvent, FieldUris.IsAllDayEvent, EnumSet.of(
          PropertyDefinitionFlags.CanSet,
          PropertyDefinitionFlags.CanUpdate,
          PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  // Defines the LegacyFreeBusyStatus property.
  /**
   * The Constant LegacyFreeBusyStatus.
   */
  public static final PropertyDefinition LegacyFreeBusyStatus =
      new GenericPropertyDefinition<LegacyFreeBusyStatus>(
          LegacyFreeBusyStatus.class,
          XmlElementNames.LegacyFreeBusyStatus,
          FieldUris.LegacyFreeBusyStatus, EnumSet.of(
          PropertyDefinitionFlags.CanSet,
          PropertyDefinitionFlags.CanUpdate,
          PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  // Defines the Location property.
  /**
   * The Constant Location.
   */
  public static final PropertyDefinition Location =
      new StringPropertyDefinition(
          XmlElementNames.Location, FieldUris.Location, EnumSet.of(
          PropertyDefinitionFlags.CanSet,
          PropertyDefinitionFlags.CanUpdate,
          PropertyDefinitionFlags.CanDelete,
          PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  // Defines the When property.
  /**
   * The Constant When.
   */
  public static final PropertyDefinition When = new StringPropertyDefinition(
      XmlElementNames.When, FieldUris.When, EnumSet.of(
      PropertyDefinitionFlags.CanSet,
      PropertyDefinitionFlags.CanUpdate,
      PropertyDefinitionFlags.CanDelete,
      PropertyDefinitionFlags.CanFind),
      ExchangeVersion.Exchange2007_SP1);

  // Defines the IsMeeting property.
  /**
   * The Constant IsMeeting.
   */
  public static final PropertyDefinition IsMeeting =
      new BoolPropertyDefinition(
          XmlElementNames.IsMeeting, FieldUris.IsMeeting, EnumSet
          .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  // Defines the IsCancelled property.
  /**
   * The Constant IsCancelled.
   */
  public static final PropertyDefinition IsCancelled =
      new BoolPropertyDefinition(
          XmlElementNames.IsCancelled, FieldUris.IsCancelled, EnumSet
          .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  // Defines the IsRecurring property.
  /**
   * The Constant IsRecurring.
   */
  public static final PropertyDefinition IsRecurring =
      new BoolPropertyDefinition(
          XmlElementNames.IsRecurring, FieldUris.IsRecurring, EnumSet
          .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  // Defines the MeetingRequestWasSent property.
  /**
   * The Constant MeetingRequestWasSent.
   */
  public static final PropertyDefinition MeetingRequestWasSent =
      new BoolPropertyDefinition(
          XmlElementNames.MeetingRequestWasSent,
          FieldUris.MeetingRequestWasSent, EnumSet
          .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  // Defines the IsResponseRequested property.
  /**
   * The Constant IsResponseRequested.
   */
  public static final PropertyDefinition IsResponseRequested =
      new BoolPropertyDefinition(
          XmlElementNames.IsResponseRequested, FieldUris.IsResponseRequested,
          EnumSet.of(PropertyDefinitionFlags.CanSet,
              PropertyDefinitionFlags.CanUpdate,
              PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  // Defines the AppointmentType property.
  /**
   * The Constant AppointmentType.
   */
  public static final PropertyDefinition AppointmentType =
      new GenericPropertyDefinition<AppointmentType>(
          AppointmentType.class,
          XmlElementNames.CalendarItemType, FieldUris.CalendarItemType,
          EnumSet.of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  // Defines the MyResponseType property.
  /**
   * The Constant MyResponseType.
   */
  public static final PropertyDefinition MyResponseType =
      new GenericPropertyDefinition<MeetingResponseType>(
          MeetingResponseType.class,
          XmlElementNames.MyResponseType, FieldUris.MyResponseType, EnumSet
          .of(PropertyDefinitionFlags.CanSet,
              PropertyDefinitionFlags.CanUpdate,
              PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  // Defines the Organizer property.
  /**
   * The Constant Organizer.
   */
  public static final PropertyDefinition Organizer =
      new ContainedPropertyDefinition<EmailAddress>(
          EmailAddress.class,
          XmlElementNames.Organizer, FieldUris.Organizer,
          XmlElementNames.Mailbox, EnumSet
          .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1,
          new ICreateComplexPropertyDelegate<EmailAddress>() {
            public EmailAddress createComplexProperty() {
              return new EmailAddress();
            }
          });

  // Defines the RequiredAttendees property.

  /**
   * The Constant RequiredAttendees.
   */
  public static final PropertyDefinition RequiredAttendees =
      new ComplexPropertyDefinition<AttendeeCollection>(
          AttendeeCollection.class,
          XmlElementNames.RequiredAttendees, FieldUris.RequiredAttendees,
          EnumSet.of(PropertyDefinitionFlags.AutoInstantiateOnRead,
              PropertyDefinitionFlags.CanSet,
              PropertyDefinitionFlags.CanUpdate,
              PropertyDefinitionFlags.CanDelete),
          ExchangeVersion.Exchange2007_SP1,
          new ICreateComplexPropertyDelegate<AttendeeCollection>() {
            public AttendeeCollection createComplexProperty() {
              return new AttendeeCollection();
            }
          });

  // Defines the OptionalAttendees property.
  /**
   * The Constant OptionalAttendees.
   */
  public static final PropertyDefinition OptionalAttendees =
      new ComplexPropertyDefinition<AttendeeCollection>(
          AttendeeCollection.class,
          XmlElementNames.OptionalAttendees, FieldUris.OptionalAttendees,
          EnumSet.of(PropertyDefinitionFlags.AutoInstantiateOnRead,
              PropertyDefinitionFlags.CanSet,
              PropertyDefinitionFlags.CanUpdate,
              PropertyDefinitionFlags.CanDelete),
          ExchangeVersion.Exchange2007_SP1,
          new ICreateComplexPropertyDelegate<AttendeeCollection>() {
            public AttendeeCollection createComplexProperty() {
              return new AttendeeCollection();
            }
          });

  // Defines the Resources property.

  /**
   * The Constant Resources.
   */
  public static final PropertyDefinition Resources =
      new ComplexPropertyDefinition<AttendeeCollection>(
          AttendeeCollection.class,
          XmlElementNames.Resources, FieldUris.Resources, EnumSet.of(
          PropertyDefinitionFlags.AutoInstantiateOnRead,
          PropertyDefinitionFlags.CanSet,
          PropertyDefinitionFlags.CanUpdate,
          PropertyDefinitionFlags.CanDelete),
          ExchangeVersion.Exchange2007_SP1,
          new ICreateComplexPropertyDelegate<AttendeeCollection>() {
            public AttendeeCollection createComplexProperty() {
              return new AttendeeCollection();
            }
          });

  // Defines the ConflictingMeetingCount property.
  /**
   * The Constant ConflictingMeetingCount.
   */
  public static final PropertyDefinition ConflictingMeetingCount =
      new IntPropertyDefinition(
          XmlElementNames.ConflictingMeetingCount,
          FieldUris.ConflictingMeetingCount,
          ExchangeVersion.Exchange2007_SP1);

  // Defines the AdjacentMeetingCount property.
  /**
   * The Constant AdjacentMeetingCount.
   */
  public static final PropertyDefinition AdjacentMeetingCount =
      new IntPropertyDefinition(
          XmlElementNames.AdjacentMeetingCount,
          FieldUris.AdjacentMeetingCount, ExchangeVersion.Exchange2007_SP1);

  // Defines the ConflictingMeetings property.
  /**
   * The Constant ConflictingMeetings.
   */
  public static final PropertyDefinition ConflictingMeetings =
      new ComplexPropertyDefinition<ItemCollection<Appointment>>(
          XmlElementNames.ConflictingMeetings,
          FieldUris.ConflictingMeetings,
          ExchangeVersion.Exchange2007_SP1,
          new ICreateComplexPropertyDelegate
              <ItemCollection<Appointment>>() {
            public ItemCollection<Appointment> createComplexProperty() {
              return new ItemCollection<Appointment>();
            }
          });

  // Defines the AdjacentMeetings property.
  /**
   * The Constant AdjacentMeetings.
   */
  public static final PropertyDefinition AdjacentMeetings =
      new ComplexPropertyDefinition<ItemCollection<Appointment>>(
          XmlElementNames.AdjacentMeetings,
          FieldUris.AdjacentMeetings,
          ExchangeVersion.Exchange2007_SP1,
          new ICreateComplexPropertyDelegate
              <ItemCollection<Appointment>>() {
            public ItemCollection<Appointment> createComplexProperty() {
              return new ItemCollection<Appointment>();
            }
          });

  // Defines the Duration property.
  /**
   * The Constant Duration.
   */
  public static final PropertyDefinition Duration =
      new TimeSpanPropertyDefinition(
          XmlElementNames.Duration, FieldUris.Duration, EnumSet
          .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  // Defines the TimeZone property.
  /**
   * The Constant TimeZone.
   */
  public static final PropertyDefinition TimeZone =
      new StringPropertyDefinition(
          XmlElementNames.TimeZone, FieldUris.TimeZone, EnumSet
          .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  // Defines the AppointmentReplyTime property.
  /**
   * The Constant AppointmentReplyTime.
   */
  public static final PropertyDefinition AppointmentReplyTime =
      new DateTimePropertyDefinition(
          XmlElementNames.AppointmentReplyTime,
          FieldUris.AppointmentReplyTime, EnumSet
          .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  // Defines the AppointmentSequenceNumber property.
  /**
   * The Constant AppointmentSequenceNumber.
   */
  public static final PropertyDefinition AppointmentSequenceNumber =
      new IntPropertyDefinition(
          XmlElementNames.AppointmentSequenceNumber,
          FieldUris.AppointmentSequenceNumber,
          ExchangeVersion.Exchange2007_SP1);

  // Defines the AppointmentState property.
  /**
   * The Constant AppointmentState.
   */
  public static final PropertyDefinition AppointmentState =
      new IntPropertyDefinition(
          XmlElementNames.AppointmentState, FieldUris.AppointmentState,
          EnumSet.of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  // Defines the Recurrence property.
  /**
   * The Constant Recurrence.
   */
  public static final PropertyDefinition Recurrence =
      new RecurrencePropertyDefinition(
          XmlElementNames.Recurrence, FieldUris.Recurrence, EnumSet.of(
          PropertyDefinitionFlags.CanSet,
          PropertyDefinitionFlags.CanUpdate,
          PropertyDefinitionFlags.CanDelete),
          ExchangeVersion.Exchange2007_SP1);

  // Defines the FirstOccurrence property.
  /**
   * The Constant FirstOccurrence.
   */
  public static final PropertyDefinition FirstOccurrence =
      new ComplexPropertyDefinition<OccurrenceInfo>(
          OccurrenceInfo.class,
          XmlElementNames.FirstOccurrence, FieldUris.FirstOccurrence,
          ExchangeVersion.Exchange2007_SP1,
          new ICreateComplexPropertyDelegate<OccurrenceInfo>() {
            public OccurrenceInfo createComplexProperty() {
              return new OccurrenceInfo();
            }
          });

  // Defines the LastOccurrence property.
  /**
   * The Constant LastOccurrence.
   */
  public static final PropertyDefinition LastOccurrence =
      new ComplexPropertyDefinition<OccurrenceInfo>(
          OccurrenceInfo.class,
          XmlElementNames.LastOccurrence, FieldUris.LastOccurrence,
          ExchangeVersion.Exchange2007_SP1,
          new ICreateComplexPropertyDelegate<OccurrenceInfo>() {
            public OccurrenceInfo createComplexProperty() {
              return new OccurrenceInfo();
            }
          });

  // Defines the ModifiedOccurrences property.
  /**
   * The Constant ModifiedOccurrences.
   */
  public static final PropertyDefinition ModifiedOccurrences =
      new ComplexPropertyDefinition<OccurrenceInfoCollection>(
          OccurrenceInfoCollection.class,
          XmlElementNames.ModifiedOccurrences,
          FieldUris.ModifiedOccurrences,
          ExchangeVersion.Exchange2007_SP1,
          new ICreateComplexPropertyDelegate
              <OccurrenceInfoCollection>() {
            public OccurrenceInfoCollection createComplexProperty() {
              return new OccurrenceInfoCollection();
            }
          });

  // Defines the DeletedOccurrences property.
  /**
   * The Constant DeletedOccurrences.
   */
  public static final PropertyDefinition DeletedOccurrences =
      new ComplexPropertyDefinition<DeletedOccurrenceInfoCollection>(
          DeletedOccurrenceInfoCollection.class,
          XmlElementNames.DeletedOccurrences,
          FieldUris.DeletedOccurrences,
          ExchangeVersion.Exchange2007_SP1,
          new ICreateComplexPropertyDelegate
              <DeletedOccurrenceInfoCollection>() {
            public DeletedOccurrenceInfoCollection createComplexProperty() {
              return new DeletedOccurrenceInfoCollection();
            }
          });

  // Defines the MeetingTimeZone property.
  /**
   * The Constant MeetingTimeZone.
   */
  public static final PropertyDefinition MeetingTimeZone =
      new MeetingTimeZonePropertyDefinition(
          XmlElementNames.MeetingTimeZone, FieldUris.MeetingTimeZone, EnumSet
          .of(PropertyDefinitionFlags.CanSet,
              PropertyDefinitionFlags.CanUpdate),
          ExchangeVersion.Exchange2007_SP1);

  // Defines the ConferenceType property.
  /**
   * The Constant ConferenceType.
   */
  public static final PropertyDefinition ConferenceType =
      new IntPropertyDefinition(
          XmlElementNames.ConferenceType, FieldUris.ConferenceType, EnumSet
          .of(PropertyDefinitionFlags.CanSet,
              PropertyDefinitionFlags.CanUpdate,
              PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  // Defines the AllowNewTimeProposal property.
  /**
   * The Constant AllowNewTimeProposal.
   */
  public static final PropertyDefinition AllowNewTimeProposal =
      new BoolPropertyDefinition(
          XmlElementNames.AllowNewTimeProposal,
          FieldUris.AllowNewTimeProposal, EnumSet.of(
          PropertyDefinitionFlags.CanSet,
          PropertyDefinitionFlags.CanUpdate,
          PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  // Defines the IsOnlineMeeting property.
  /**
   * The Constant IsOnlineMeeting.
   */
  public static final PropertyDefinition IsOnlineMeeting =
      new BoolPropertyDefinition(
          XmlElementNames.IsOnlineMeeting, FieldUris.IsOnlineMeeting, EnumSet
          .of(PropertyDefinitionFlags.CanSet,
              PropertyDefinitionFlags.CanUpdate,
              PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  // Defines the MeetingWorkspaceUrl property.
  /**
   * The Constant MeetingWorkspaceUrl.
   */
  public static final PropertyDefinition MeetingWorkspaceUrl =
      new StringPropertyDefinition(
          XmlElementNames.MeetingWorkspaceUrl, FieldUris.MeetingWorkspaceUrl,
          EnumSet.of(PropertyDefinitionFlags.CanSet,
              PropertyDefinitionFlags.CanUpdate,
              PropertyDefinitionFlags.CanDelete,
              PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  // Defines the NetShowUrl property.
  /**
   * The Constant NetShowUrl.
   */
  public static final PropertyDefinition NetShowUrl =
      new StringPropertyDefinition(
          XmlElementNames.NetShowUrl, FieldUris.NetShowUrl, EnumSet.of(
          PropertyDefinitionFlags.CanSet,
          PropertyDefinitionFlags.CanUpdate,
          PropertyDefinitionFlags.CanDelete,
          PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  // Defines the iCalendar Uid property.
  /**
   * The Constant ICalUid.
   */
  public static final PropertyDefinition ICalUid =
      new StringPropertyDefinition(
          XmlElementNames.Uid, FieldUris.Uid, EnumSet
          .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  // Defines the iCalendar RecurrenceId property.
  /**
   * The Constant ICalRecurrenceId.
   */
  public static final PropertyDefinition ICalRecurrenceId =
      new DateTimePropertyDefinition(
          XmlElementNames.RecurrenceId, FieldUris.RecurrenceId, EnumSet
          .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1, true);
  // Defines the iCalendar DateTimeStamp property.
  /**
   * The Constant ICalDateTimeStamp.
   */
  public static final PropertyDefinition ICalDateTimeStamp =
      new DateTimePropertyDefinition(
          XmlElementNames.DateTimeStamp, FieldUris.DateTimeStamp, EnumSet
          .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1, true); // isNullable

  // Instance of schema.
  // This must be after the declaration of property definitions.
  /**
   * The Constant Instance.
   */
  public static final AppointmentSchema Instance = new AppointmentSchema();

  /**
   * Registers property.
   * <p>
   * IMPORTANT NOTE: PROPERTIES MUST BE REGISTERED IN SCHEMA ORDER (i.e. the
   * same order as they are defined in types.xsd)
   * </p>
   */
  @Override
  protected void registerProperties() {
    super.registerProperties();

    this.registerProperty(Start);
    this.registerProperty(End);
    this.registerProperty(OriginalStart);
    this.registerProperty(IsAllDayEvent);
    this.registerProperty(LegacyFreeBusyStatus);
    this.registerProperty(Location);
    this.registerProperty(When);
    this.registerProperty(IsMeeting);
    this.registerProperty(IsCancelled);
    this.registerProperty(IsRecurring);
    this.registerProperty(MeetingRequestWasSent);
    this.registerProperty(IsResponseRequested);
    this.registerProperty(AppointmentType);
    this.registerProperty(MyResponseType);
    this.registerProperty(Organizer);
    this.registerProperty(RequiredAttendees);
    this.registerProperty(OptionalAttendees);
    this.registerProperty(Resources);
    this.registerProperty(ConflictingMeetingCount);
    this.registerProperty(AdjacentMeetingCount);
    this.registerProperty(ConflictingMeetings);
    this.registerProperty(AdjacentMeetings);
    this.registerProperty(Duration);
    this.registerProperty(TimeZone);
    this.registerProperty(AppointmentReplyTime);
    this.registerProperty(AppointmentSequenceNumber);
    this.registerProperty(AppointmentState);
    this.registerProperty(Recurrence);
    this.registerProperty(FirstOccurrence);
    this.registerProperty(LastOccurrence);
    this.registerProperty(ModifiedOccurrences);
    this.registerProperty(DeletedOccurrences);
    this.registerInternalProperty(MeetingTimeZone);
    this.registerProperty(StartTimeZone);
    this.registerProperty(EndTimeZone);
    this.registerProperty(ConferenceType);
    this.registerProperty(AllowNewTimeProposal);
    this.registerProperty(IsOnlineMeeting);
    this.registerProperty(MeetingWorkspaceUrl);
    this.registerProperty(NetShowUrl);
    this.registerProperty(ICalUid);
    this.registerProperty(ICalRecurrenceId);
    this.registerProperty(ICalDateTimeStamp);
  }

  /**
   * Instantiates a new appointment schema.
   */
  AppointmentSchema() {
    super();
  }

}
