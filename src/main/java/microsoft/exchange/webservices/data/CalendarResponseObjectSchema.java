/**************************************************************************
 * copyright file="CalendarResponseObjectSchema.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the CalendarResponseObjectSchema.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents the schema for CalendarResponseObject.
 */
class CalendarResponseObjectSchema extends ServiceObjectSchema {

	// This must be declared after the property definitions
	/** The Constant Instance. */
	static final CalendarResponseObjectSchema Instance = 
		new CalendarResponseObjectSchema();

	/**
	 * Registers properties.
	 */
	// / IMPORTANT NOTE: PROPERTIES MUST BE REGISTERED IN SCHEMA ORDER (i.e. the
	// same order as they are defined in types.xsd)
	@Override
	protected void registerProperties() {
		super.registerProperties();

		this.registerProperty(ItemSchema.ItemClass);
		this.registerProperty(ItemSchema.Sensitivity);
		this.registerProperty(ItemSchema.Body);
		this.registerProperty(ItemSchema.Attachments);
		this.registerProperty(ItemSchema.InternetMessageHeaders);
		this.registerProperty(EmailMessageSchema.Sender);
		this.registerProperty(EmailMessageSchema.ToRecipients);
		this.registerProperty(EmailMessageSchema.CcRecipients);
		this.registerProperty(EmailMessageSchema.BccRecipients);
		this.registerProperty(EmailMessageSchema.IsReadReceiptRequested);
		this.registerProperty(EmailMessageSchema.IsDeliveryReceiptRequested);
		this.registerProperty(ResponseObjectSchema.ReferenceItemId);
	}
}