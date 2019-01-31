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

import java.util.EnumSet;

import microsoft.exchange.webservices.data.attribute.Schema;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.property.PropertyDefinitionFlags;
import microsoft.exchange.webservices.data.property.definition.DateTimePropertyDefinition;
import microsoft.exchange.webservices.data.property.definition.PropertyDefinition;

/**
 * Represents the schema for meeting request.
 */
@Schema
public class MeetingResponseSchema extends MeetingMessageSchema {

  /**
   * Field URIs for MeetingRequest.
   */
  private static interface FieldUris {

    /**
     * The Start.
     */
    String ProposedStart = "meetingResponse:ProposedStart";

    /**
     * The End.
     */
    String ProposedEnd = "meetingResponse:ProposedEnd";
  }

  /**
   * Defines the Start property.
   */
  public static final PropertyDefinition Start = AppointmentSchema.Start;

  /**
   * Defines the End property.
   */
  public static final PropertyDefinition End = AppointmentSchema.End;

  /**
   * Defines the Location property.
   */
  public static final PropertyDefinition Location =
      AppointmentSchema.Location;

  /**
   * Defines the Recurrence property.
   */
  public static final PropertyDefinition Recurrence =
      AppointmentSchema.Recurrence;

  /**
   * Defines the AppointmentType property.
   */
  public static final PropertyDefinition AppointmentType =
      AppointmentSchema.AppointmentType;

  // Defines the Start property.
  /**
   * The Constant Start.
   */
  public static final PropertyDefinition ProposedStart =
      new DateTimePropertyDefinition(
          XmlElementNames.ProposedStart, FieldUris.ProposedStart, EnumSet.of(
          PropertyDefinitionFlags.CanSet,
          PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1,
          true);

  // Defines the End property.
  /**
   * The Constant End.
   */
  public static final PropertyDefinition ProposedEnd
     = new DateTimePropertyDefinition(
           XmlElementNames.ProposedEnd, FieldUris.ProposedEnd, EnumSet.of(
           PropertyDefinitionFlags.CanSet,
           PropertyDefinitionFlags.CanFind),
           ExchangeVersion.Exchange2007_SP1,
           true);

  /**
   * This must be after the declaration of property definitions.
   */
  public static final MeetingResponseSchema Instance =
      new MeetingResponseSchema();

  /**
   * Registers property.
   *
   * IMPORTANT NOTE: PROPERTIES MUST BE REGISTERED IN SCHEMA ORDER (i.e. the
   * same order as they are defined in types.xsd)
   */
  @Override
  protected void registerProperties() {
    super.registerProperties();

    this.registerProperty(Start);
    this.registerProperty(End);
    this.registerProperty(Location);
    this.registerProperty(Recurrence);
    this.registerProperty(AppointmentType);
    
    this.registerProperty(ProposedStart);
    this.registerProperty(ProposedEnd);

  }

  /**
   * Initializes a new instance of the class.
   */
  protected MeetingResponseSchema() {
    super();
  }
}
