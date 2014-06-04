/**************************************************************************
 * copyright file="MeetingMessageSchema.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the MeetingMessageSchema.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.EnumSet;

/**
 * Represents the schema for meeting messages.
 * 
 */
@Schema
public class MeetingMessageSchema extends EmailMessageSchema {

	/**
	 * Field URIs for MeetingMessage.
	 */
	private static interface FieldUris {

		/** The Associated calendar item id. */
		String AssociatedCalendarItemId = "meeting:AssociatedCalendarItemId";

		/** The Is delegated. */
		String IsDelegated = "meeting:IsDelegated";

		/** The Is out of date. */
		String IsOutOfDate = "meeting:IsOutOfDate";

		/** The Has been processed. */
		String HasBeenProcessed = "meeting:HasBeenProcessed";

		/** The Response type. */
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
				};
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

	/** This must be after the declaration of property definitions. */
	protected static final MeetingMessageSchema Instance = 
		new MeetingMessageSchema();

	/**
	 * Registers properties.
	 * 
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
