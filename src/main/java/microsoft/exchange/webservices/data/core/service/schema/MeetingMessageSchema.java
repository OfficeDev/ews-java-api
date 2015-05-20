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
import microsoft.exchange.webservices.data.core.enumeration.property.MeetingResponseType;
import microsoft.exchange.webservices.data.core.enumeration.property.PropertyDefinitionFlags;
import microsoft.exchange.webservices.data.property.complex.ICreateComplexPropertyDelegate;
import microsoft.exchange.webservices.data.property.complex.ItemId;
import microsoft.exchange.webservices.data.property.definition.BoolPropertyDefinition;
import microsoft.exchange.webservices.data.property.definition.ComplexPropertyDefinition;
import microsoft.exchange.webservices.data.property.definition.GenericPropertyDefinition;
import microsoft.exchange.webservices.data.property.definition.PropertyDefinition;

import java.util.EnumSet;

/**
 * Represents the schema for meeting messages.
 */
@Schema
public class MeetingMessageSchema extends EmailMessageSchema {

  /**
   * Field URIs for MeetingMessage.
   */
  private static interface FieldUris {

    /**
     * The Associated calendar item id.
     */
    String AssociatedCalendarItemId = "meeting:AssociatedCalendarItemId";

    /**
     * The Is delegated.
     */
    String IsDelegated = "meeting:IsDelegated";

    /**
     * The Is out of date.
     */
    String IsOutOfDate = "meeting:IsOutOfDate";

    /**
     * The Has been processed.
     */
    String HasBeenProcessed = "meeting:HasBeenProcessed";

    /**
     * The Response type.
     */
    String ResponseType = "meeting:ResponseType";
  }


  /**
   * Defines the AssociatedAppointmentId property.
   */
  public static final PropertyDefinition AssociatedAppointmentId =
      new ComplexPropertyDefinition<ItemId>(
          //	ItemId.class,
          XmlElementNames.AssociatedCalendarItemId,
          FieldUris.AssociatedCalendarItemId,
          ExchangeVersion.Exchange2007_SP1,
          new ICreateComplexPropertyDelegate<ItemId>() {
            @Override
            public ItemId createComplexProperty() {
              return new ItemId();
            }
          });

  /**
   * Defines the IsDelegated property.
   */
  public static final PropertyDefinition IsDelegated =
      new BoolPropertyDefinition(
          XmlElementNames.IsDelegated, FieldUris.IsDelegated, EnumSet
          .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the IsOutOfDate property.
   */
  public static final PropertyDefinition IsOutOfDate =
      new BoolPropertyDefinition(
          XmlElementNames.IsOutOfDate, FieldUris.IsOutOfDate,
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the HasBeenProcessed property.
   */
  public static final PropertyDefinition HasBeenProcessed =
      new BoolPropertyDefinition(
          XmlElementNames.HasBeenProcessed, FieldUris.HasBeenProcessed,
          EnumSet.of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the ResponseType property.
   */
  public static final PropertyDefinition ResponseType =
      new GenericPropertyDefinition<MeetingResponseType>(
          MeetingResponseType.class,
          XmlElementNames.ResponseType, FieldUris.ResponseType, EnumSet
          .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the ICalendar Uid property.
   */
  public static final PropertyDefinition ICalUid = AppointmentSchema.ICalUid;

  /**
   * Defines the ICalendar RecurrenceId property.
   */
  public static final PropertyDefinition ICalRecurrenceId =
      AppointmentSchema.ICalRecurrenceId;

  /**
   * Defines the ICalendar DateTimeStamp property.
   */
  public static final PropertyDefinition ICalDateTimeStamp =
      AppointmentSchema.ICalDateTimeStamp;

  /**
   * This must be after the declaration of property definitions.
   */
  protected static final MeetingMessageSchema Instance =
      new MeetingMessageSchema();

  /**
   * Gets the single instance of MeetingMessageSchema.
   *
   * @return single instance of MeetingMessageSchema
   */
  public static MeetingMessageSchema getInstance() {
    return Instance;
  }

  /**
   * Registers property.
   * <p/>
   * IMPORTANT NOTE: PROPERTIES MUST BE REGISTERED IN SCHEMA ORDER (i.e. the
   * same order as they are defined in types.xsd)
   */
  @Override
  protected void registerProperties() {
    super.registerProperties();

    this.registerProperty(AssociatedAppointmentId);
    this.registerProperty(IsDelegated);
    this.registerProperty(IsOutOfDate);
    this.registerProperty(HasBeenProcessed);
    this.registerProperty(ResponseType);
    this.registerProperty(ICalUid);
    this.registerProperty(ICalRecurrenceId);
    this.registerProperty(ICalDateTimeStamp);
  }

  /**
   * Initializes a new instance of the class.
   */
  protected MeetingMessageSchema() {
    super();
  }

}
