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
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.property.LegacyFreeBusyStatus;
import microsoft.exchange.webservices.data.core.enumeration.service.MeetingRequestType;
import microsoft.exchange.webservices.data.core.enumeration.property.PropertyDefinitionFlags;
import microsoft.exchange.webservices.data.property.definition.GenericPropertyDefinition;
import microsoft.exchange.webservices.data.property.definition.PropertyDefinition;

import java.util.EnumSet;

/**
 * Represents the schema for meeting request.
 */
@Schema
public class MeetingRequestSchema extends MeetingMessageSchema {

  /**
   * Field URIs for MeetingRequest.
   */
  private static interface FieldUris {

    /**
     * The Meeting request type.
     */
    String MeetingRequestType = "meetingRequest:MeetingRequestType";

    /**
     * The Intended free busy status.
     */
    String IntendedFreeBusyStatus = "meetingRequest:IntendedFreeBusyStatus";
  }


  /**
   * Defines the MeetingRequestType property.
   */
  public static final PropertyDefinition MeetingRequestType =
      new GenericPropertyDefinition<microsoft.exchange.webservices.data.core.enumeration.service.MeetingRequestType>(
          MeetingRequestType.class,
          XmlElementNames.MeetingRequestType, FieldUris.MeetingRequestType,
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the IntendedFreeBusyStatus property.
   */
  public static final PropertyDefinition IntendedFreeBusyStatus =
      new GenericPropertyDefinition<microsoft.exchange.webservices.data.core.enumeration.property.LegacyFreeBusyStatus>(
          LegacyFreeBusyStatus.class,
          XmlElementNames.IntendedFreeBusyStatus,
          FieldUris.IntendedFreeBusyStatus, EnumSet
          .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the Start property.
   */
  public static final PropertyDefinition Start = AppointmentSchema.Start;

  /**
   * Defines the End property.
   */
  public static final PropertyDefinition End = AppointmentSchema.End;

  /**
   * Defines the OriginalStart property.
   */
  public static final PropertyDefinition OriginalStart =
      AppointmentSchema.OriginalStart;

  /**
   * Defines the IsAllDayEvent property.
   */
  public static final PropertyDefinition IsAllDayEvent =
      AppointmentSchema.IsAllDayEvent;

  /**
   * Defines the LegacyFreeBusyStatus property.
   */
  public static final PropertyDefinition LegacyFreeBusyStatus =
      AppointmentSchema.LegacyFreeBusyStatus;

  /**
   * Defines the Location property.
   */
  public static final PropertyDefinition Location =
      AppointmentSchema.Location;

  /**
   * Defines the When property.
   */
  public static final PropertyDefinition When = AppointmentSchema.When;

  /**
   * Defines the IsMeeting property.
   */
  public static final PropertyDefinition IsMeeting =
      AppointmentSchema.IsMeeting;

  /**
   * Defines the IsCancelled property.
   */
  public static final PropertyDefinition IsCancelled =
      AppointmentSchema.IsCancelled;

  /**
   * Defines the IsRecurring property.
   */
  public static final PropertyDefinition IsRecurring =
      AppointmentSchema.IsRecurring;

  /**
   * Defines the MeetingRequestWasSent property.
   */
  public static final PropertyDefinition MeetingRequestWasSent =
      AppointmentSchema.MeetingRequestWasSent;

  /**
   * Defines the AppointmentType property.
   */
  public static final PropertyDefinition AppointmentType =
      AppointmentSchema.AppointmentType;

  /**
   * Defines the MyResponseType property.
   */
  public static final PropertyDefinition MyResponseType =
      AppointmentSchema.MyResponseType;

  /**
   * Defines the Organizer property.
   */
  public static final PropertyDefinition Organizer =
      AppointmentSchema.Organizer;

  /**
   * Defines the RequiredAttendees property.
   */
  public static final PropertyDefinition RequiredAttendees =
      AppointmentSchema.RequiredAttendees;

  /**
   * Defines the OptionalAttendees property.
   */
  public static final PropertyDefinition OptionalAttendees =
      AppointmentSchema.OptionalAttendees;

  /**
   * Defines the Resources property.
   */
  public static final PropertyDefinition Resources =
      AppointmentSchema.Resources;

  /**
   * Defines the ConflictingMeetingCount property.
   */
  public static final PropertyDefinition ConflictingMeetingCount =
      AppointmentSchema.ConflictingMeetingCount;

  /**
   * Defines the AdjacentMeetingCount property.
   */
  public static final PropertyDefinition AdjacentMeetingCount =
      AppointmentSchema.AdjacentMeetingCount;

  /**
   * Defines the ConflictingMeetings property.
   */
  public static final PropertyDefinition ConflictingMeetings =
      AppointmentSchema.ConflictingMeetings;

  /**
   * Defines the AdjacentMeetings property.
   */
  public static final PropertyDefinition AdjacentMeetings =
      AppointmentSchema.AdjacentMeetings;

  /**
   * Defines the Duration property.
   */
  public static final PropertyDefinition Duration =
      AppointmentSchema.Duration;

  /**
   * Defines the TimeZone property.
   */
  public static final PropertyDefinition TimeZone =
      AppointmentSchema.TimeZone;

  /**
   * Defines the AppointmentReplyTime property.
   */
  public static final PropertyDefinition AppointmentReplyTime =
      AppointmentSchema.AppointmentReplyTime;

  /**
   * Defines the AppointmentSequenceNumber property.
   */
  public static final PropertyDefinition AppointmentSequenceNumber =
      AppointmentSchema.AppointmentSequenceNumber;

  /**
   * Defines the AppointmentState property.
   */
  public static final PropertyDefinition AppointmentState =
      AppointmentSchema.AppointmentState;

  /**
   * Defines the Recurrence property.
   */
  public static final PropertyDefinition Recurrence =
      AppointmentSchema.Recurrence;

  /**
   * Defines the FirstOccurrence property.
   */
  public static final PropertyDefinition FirstOccurrence =
      AppointmentSchema.FirstOccurrence;
  /**
   * Defines the LastOccurrence property.
   */
  public static final PropertyDefinition LastOccurrence =
      AppointmentSchema.LastOccurrence;

  /**
   * Defines the ModifiedOccurrences property.
   */
  public static final PropertyDefinition ModifiedOccurrences =
      AppointmentSchema.ModifiedOccurrences;

  /**
   * Defines the Duration property.
   */
  public static final PropertyDefinition DeletedOccurrences =
      AppointmentSchema.DeletedOccurrences;

  /**
   * Defines the MeetingTimeZone property.
   */
  static final PropertyDefinition MeetingTimeZone =
      AppointmentSchema.MeetingTimeZone;

  /**
   * Defines the StartTimeZone property.
   */
  public static final PropertyDefinition StartTimeZone =
      AppointmentSchema.StartTimeZone;

  /**
   * Defines the EndTimeZone property.
   */
  public static final PropertyDefinition EndTimeZone =
      AppointmentSchema.EndTimeZone;

  /**
   * Defines the ConferenceType property.
   */
  public static final PropertyDefinition ConferenceType =
      AppointmentSchema.ConferenceType;

  /**
   * Defines the AllowNewTimeProposal property.
   */
  public static final PropertyDefinition AllowNewTimeProposal =
      AppointmentSchema.AllowNewTimeProposal;

  /**
   * Defines the IsOnlineMeeting property.
   */
  public static final PropertyDefinition IsOnlineMeeting =
      AppointmentSchema.IsOnlineMeeting;

  /**
   * Defines the MeetingWorkspaceUrl property.
   */
  public static final PropertyDefinition MeetingWorkspaceUrl =
      AppointmentSchema.MeetingWorkspaceUrl;

  /**
   * Defines the NetShowUrl property.
   */
  public static final PropertyDefinition NetShowUrl =
      AppointmentSchema.NetShowUrl;

  /**
   * This must be after the declaration of property definitions.
   */
  public static final MeetingRequestSchema Instance =
      new MeetingRequestSchema();

  /**
   * Registers property.
   * <p/>
   * IMPORTANT NOTE: PROPERTIES MUST BE REGISTERED IN SCHEMA ORDER (i.e. the
   * same order as they are defined in types.xsd)
   */
  @Override
  protected void registerProperties() {
    super.registerProperties();

    this.registerProperty(MeetingRequestType);
    this.registerProperty(IntendedFreeBusyStatus);

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
  }

  /**
   * Initializes a new instance of the class.
   */
  protected MeetingRequestSchema() {
    super();
  }
}
