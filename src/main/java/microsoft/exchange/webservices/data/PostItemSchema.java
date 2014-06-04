/**************************************************************************
 * copyright file="PostItemSchema.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the PostItemSchema.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.EnumSet;

/**
 * Represents the schema for post items.
 * 
 */
@Schema
public final class PostItemSchema extends ItemSchema {

	/**
	 * Field URIs for PostItem.
	 */
	private static interface FieldUris {

		/** The Posted time. */
		String PostedTime = "postitem:PostedTime";
	}

	/**
	 * Defines the ConversationIndex property.
	 */
	public static final PropertyDefinition ConversationIndex = 
		EmailMessageSchema.ConversationIndex;

	/**
	 * Defines the ConversationTopic property.
	 */
	public static final PropertyDefinition ConversationTopic =
		EmailMessageSchema.ConversationTopic;

	/**
	 * Defines the From property.
	 */
	public static final PropertyDefinition From = EmailMessageSchema.From;

	/**
	 * Defines the InternetMessageId property.
	 */
	public static final PropertyDefinition InternetMessageId = 
		EmailMessageSchema.InternetMessageId;

	/**
	 * Defines the IsRead property.
	 */
	public static final PropertyDefinition IsRead = EmailMessageSchema.IsRead;

	/**
	 * Defines the PostedTime property.
	 */
	public static final PropertyDefinition PostedTime = 
		new DateTimePropertyDefinition(
			XmlElementNames.PostedTime, FieldUris.PostedTime, EnumSet
					.of(PropertyDefinitionFlags.CanFind),
			ExchangeVersion.Exchange2007_SP1);

	/**
	 * Defines the References property.
	 */
	public static final PropertyDefinition References = 
		EmailMessageSchema.References;

	/**
	 * Defines the Sender property.
	 */
	public static final PropertyDefinition Sender = EmailMessageSchema.Sender;

	// This must be after the declaration of property definitions
	/** The Constant Instance. */
	protected static final PostItemSchema Instance = new PostItemSchema();

	/**
	 * Registers properties.
	 * 
	 * IMPORTANT NOTE: PROPERTIES MUST BE REGISTERED IN SCHEMA ORDER (i.e. the
	 * same order as they are defined in types.xsd)
	 */
	@Override
	protected void registerProperties() {
		super.registerProperties();

		this.registerProperty(ConversationIndex);
		this.registerProperty(ConversationTopic);
		this.registerProperty(From);
		this.registerProperty(InternetMessageId);
		this.registerProperty(IsRead);
		this.registerProperty(PostedTime);
		this.registerProperty(References);
		this.registerProperty(Sender);
	}

	/**
	 * Initializes a new instance of the PostItemSchema class.
	 */
	protected PostItemSchema() {
		super();
	}
}