/**************************************************************************
 * copyright file="ResponseMessageSchema.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ResponseMessageSchema.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents ResponseMessage schema definition.
 */
class ResponseMessageSchema extends ServiceObjectSchema {

	/** This must be declared after the property definitions. */
	static final ResponseMessageSchema Instance = new ResponseMessageSchema();

	/**
	 * Registers properties. IMPORTANT NOTE: PROPERTIES MUST BE REGISTERED IN
	 * SCHEMA ORDER (i.e. the same order as they are defined in types.xsd)
	 */
	@Override
	protected void registerProperties() {
		super.registerProperties();

		this.registerProperty(ItemSchema.Subject);
		this.registerProperty(ItemSchema.Body);
		this.registerProperty(EmailMessageSchema.ToRecipients);
		this.registerProperty(EmailMessageSchema.CcRecipients);
		this.registerProperty(EmailMessageSchema.BccRecipients);
		this.registerProperty(EmailMessageSchema.IsReadReceiptRequested);
		this.registerProperty(EmailMessageSchema.IsDeliveryReceiptRequested);
		this.registerProperty(ResponseObjectSchema.ReferenceItemId);
		this.registerProperty(ResponseObjectSchema.BodyPrefix);
	}
}
