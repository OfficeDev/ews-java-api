/**************************************************************************
 * copyright file="CancelMeetingMessageSchema.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the CancelMeetingMessageSchema.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.EnumSet;

/***
 * Represents a meeting cancellation message.
 */
class CancelMeetingMessageSchema extends ServiceObjectSchema {

	/** The Constant Body. */
	public static final PropertyDefinition Body = 
		new ComplexPropertyDefinition<MessageBody>(
				MessageBody.class,
			XmlElementNames.NewBodyContent, EnumSet
					.of(PropertyDefinitionFlags.CanSet),
			ExchangeVersion.Exchange2007_SP1,
			new ICreateComplexPropertyDelegate<MessageBody>() {
				public MessageBody createComplexProperty() {
					return new MessageBody();
				};
			});

	/** * This must be declared after the property definitions. */
	static final CancelMeetingMessageSchema Instance = 
		new CancelMeetingMessageSchema();

	/***
	 * Registers properties. IMPORTANT NOTE: PROPERTIES MUST BE REGISTERED IN
	 * SCHEMA ORDER (i.e. the same order as they are defined in types.xsd)
	 */
	@Override
	protected void registerProperties() {
		super.registerProperties();

		this.registerProperty(EmailMessageSchema.IsReadReceiptRequested);
		this.registerProperty(EmailMessageSchema.IsDeliveryReceiptRequested);
		this.registerProperty(ResponseObjectSchema.ReferenceItemId);
		this.registerProperty(CancelMeetingMessageSchema.Body);
	}
}