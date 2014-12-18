/**************************************************************************
 Exchange Web Services Java API
 Copyright (c) Microsoft Corporation
 All rights reserved.
 MIT License
 Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the ""Software""), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 THE SOFTWARE IS PROVIDED *AS IS*, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 **************************************************************************/

package microsoft.exchange.webservices.data;

import java.util.Date;

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
   * @param parentAttachment the parent attachment
   * @throws Exception the exception
   */
  protected MeetingMessage(ItemAttachment parentAttachment) throws Exception {
    super(parentAttachment);
  }

  /**
   * Initializes a new instance of the "MeetingMessage" class.
   *
   * @param service EWS service to which this object belongs.
   * @throws Exception the exception
   */
  protected MeetingMessage(ExchangeService service) throws Exception {
    super(service);
  }

  /**
   * Binds to an existing meeting message and loads the specified set of
   * properties. Calling this method results in a call to EWS.
   *
   * @param service     The service to use to bind to the meeting message.
   * @param id          The Id of the meeting message to bind to.
   * @param propertySet The set of properties to load.
   * @return A MeetingMessage instance representing the meeting message
   * corresponding to the specified Id.
   * @throws Exception the exception
   */
  public static MeetingMessage bind(ExchangeService service, ItemId id,
      PropertySet propertySet) throws Exception {
    return (MeetingMessage) service.bindToItem(id, propertySet);
  }

  /**
   * Binds to an existing meeting message and loads its first class
   * properties. Calling this method results in a call to EWS.
   *
   * @param service The service to use to bind to the meeting message.
   * @param id      The Id of the meeting message to bind to.
   * @return A MeetingMessage instance representing the meeting message
   * corresponding to the specified Id.
   * @throws Exception the exception
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
   * supported.
   */
  @Override
  protected ExchangeVersion getMinimumRequiredServerVersion() {
    return ExchangeVersion.Exchange2007_SP1;
  }

  /**
   * Gets the associated appointment ID.
   *
   * @return the associated appointment ID.
   * @throws ServiceLocalException the service local exception
   */
  public ItemId getAssociatedAppointmentId()
      throws ServiceLocalException {
    return (ItemId) this.getPropertyBag()
        .getObjectFromPropertyDefinition(
            MeetingMessageSchema.AssociatedAppointmentId);
  }

  /**
   * Gets whether the meeting message has been processed.
   *
   * @return whether the meeting message has been processed.
   * @throws ServiceLocalException the service local exception
   */
  public Boolean getHasBeenProcessed()
      throws ServiceLocalException {
    return (Boolean) this.getPropertyBag()
        .getObjectFromPropertyDefinition(
            MeetingMessageSchema.HasBeenProcessed);
  }

  /**
   * Gets the response type indicated by this meeting message.
   *
   * @return the response type indicated by this meeting message.
   * @throws ServiceLocalException the service local exception
   */
  public MeetingResponseType getResponseType()
      throws ServiceLocalException {
    return (MeetingResponseType) this.getPropertyBag()
        .getObjectFromPropertyDefinition(
            MeetingMessageSchema.ResponseType);
  }

  /**
   * Gets the ICalendar Uid.
   *
   * @return the ical uid
   * @throws ServiceLocalException the service local exception
   */
  public String getICalUid() throws ServiceLocalException {
    return (String) this.getPropertyBag().getObjectFromPropertyDefinition(
        MeetingMessageSchema.ICalUid);
  }

  /**
   * Gets the ICalendar RecurrenceId.
   *
   * @return the ical recurrence id
   * @throws ServiceLocalException the service local exception
   */
  public Date getICalRecurrenceId() throws ServiceLocalException {
    return (Date) this.getPropertyBag().getObjectFromPropertyDefinition(
        MeetingMessageSchema.ICalRecurrenceId);
  }

  /**
   * Gets the ICalendar DateTimeStamp.
   *
   * @return the ical date time stamp
   * @throws ServiceLocalException the service local exception
   */
  public Date getICalDateTimeStamp() throws ServiceLocalException {
    return (Date) this.getPropertyBag().getObjectFromPropertyDefinition(
        MeetingMessageSchema.ICalDateTimeStamp);
  }

  /**
   * Gets the IsDelegated property.
   *
   * @return True if delegated; false otherwise.
   * @throws ServiceLocalException the service local exception
   */
  public Boolean getIsDelegated() throws ServiceLocalException {
    return (Boolean) this.getPropertyBag().getObjectFromPropertyDefinition(
        MeetingMessageSchema.IsDelegated);
  }

  /**
   * Gets the IsOutOfDate property.
   *
   * @return True if out of date; false otherwise.
   * @throws ServiceLocalException the service local exception
   */
  public Boolean getIsOutOfDate() throws ServiceLocalException {
    return (Boolean) this.getPropertyBag().getObjectFromPropertyDefinition(
        MeetingMessageSchema.IsOutOfDate);
  }

}
