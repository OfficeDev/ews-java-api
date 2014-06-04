/**************************************************************************
 * copyright file="PostReplySchema.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the PostReplySchema.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents PostReply schema definition.
 * 
 */
final class PostReplySchema extends ServiceObjectSchema {

	// This must be declared after the property definitions
	/** The Constant Instance. */
	static final PostReplySchema Instance = new PostReplySchema();

	/**
	 * Registers properties.
	 * 
	 * IMPORTANT NOTE: PROPERTIES MUST BE REGISTERED IN SCHEMA ORDER (i.e. the
	 * same order as they are defined in types.xsd)
	 */
	@Override
	protected void registerProperties() {
		super.registerProperties();

		this.registerProperty(ItemSchema.Subject);
		this.registerProperty(ItemSchema.Body);
		this.registerProperty(ResponseObjectSchema.ReferenceItemId);
		this.registerProperty(ResponseObjectSchema.BodyPrefix);
	}
}