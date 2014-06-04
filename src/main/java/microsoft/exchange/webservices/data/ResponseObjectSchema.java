/**************************************************************************
 * copyright file="ResponseObjectSchema.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ResponseObjectSchema.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.EnumSet;

/**
 * Represents ResponseObject schema definition.
 */
class ResponseObjectSchema extends ServiceObjectSchema {

	/** The Reference item id. */
	public static PropertyDefinition ReferenceItemId = 
		new ComplexPropertyDefinition<ItemId>(
				ItemId.class,
			XmlElementNames.ReferenceItemId, EnumSet.of(
					PropertyDefinitionFlags.AutoInstantiateOnRead,
					PropertyDefinitionFlags.CanSet),
			ExchangeVersion.Exchange2007_SP1,
			new ICreateComplexPropertyDelegate<ItemId>() {
				public ItemId createComplexProperty() {
					return new ItemId();
				};
			});

	/** The Body prefix. */
	public static final PropertyDefinition BodyPrefix = 
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

	/** This must be declared after the property definitions. */
	protected static final ResponseObjectSchema Instance = 
		new ResponseObjectSchema();

	/**
	 * Registers properties. IMPORTANT NOTE: PROPERTIES MUST BE REGISTERED IN
	 * SCHEMA ORDER (i.e. the same order as they are defined in types.xsd)
	 */
	@Override
	protected void registerProperties() {
		super.registerProperties();
		this.registerProperty(ResponseObjectSchema.ReferenceItemId);
	}

}