/**************************************************************************
 * copyright file="EmailMessageSchema.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the EmailMessageSchema.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.EnumSet;

/***
 * Represents the schema for e-mail messages.
 * 
 */
@Schema
public class EmailMessageSchema extends ItemSchema {

	/**
	 * The Interface FieldUris.
	 */
	private static interface FieldUris {

		/** The Conversation index. */
		String ConversationIndex = "message:ConversationIndex";

		/** The Conversation topic. */
		String ConversationTopic = "message:ConversationTopic";

		/** The Internet message id. */
		String InternetMessageId = "message:InternetMessageId";

		/** The Is read. */
		String IsRead = "message:IsRead";

		/** The Is response requested. */
		String IsResponseRequested = "message:IsResponseRequested";

		/** The Is read receipt requested. */
		String IsReadReceiptRequested = "message:IsReadReceiptRequested";

		/** The Is delivery receipt requested. */
		String IsDeliveryReceiptRequested = 
			"message:IsDeliveryReceiptRequested";

		/** The References. */
		String References = "message:References";

		/** The Reply to. */
		String ReplyTo = "message:ReplyTo";

		/** The From. */
		String From = "message:From";

		/** The Sender. */
		String Sender = "message:Sender";

		/** The To recipients. */
		String ToRecipients = "message:ToRecipients";

		/** The Cc recipients. */
		String CcRecipients = "message:CcRecipients";

		/** The Bcc recipients. */
		String BccRecipients = "message:BccRecipients";

		/** The Received by. */
		String ReceivedBy = "message:ReceivedBy";

		/** The Received representing. */
		String ReceivedRepresenting = "message:ReceivedRepresenting";
	}

	/***
	 * Defines the ToRecipients property.
	 */
	public static final PropertyDefinition ToRecipients = 
		new ComplexPropertyDefinition<EmailAddressCollection>(
				EmailAddressCollection.class,
			XmlElementNames.ToRecipients,
			FieldUris.ToRecipients,
			EnumSet.of(PropertyDefinitionFlags.AutoInstantiateOnRead,
					PropertyDefinitionFlags.CanSet,
					PropertyDefinitionFlags.CanUpdate,
					PropertyDefinitionFlags.CanDelete),
			ExchangeVersion.Exchange2007_SP1,
			new ICreateComplexPropertyDelegate
			<EmailAddressCollection>() {
				@Override
				public EmailAddressCollection createComplexProperty() {
					return new EmailAddressCollection();
				};
			});

	/***
	 * Defines the BccRecipients property.
	 */
	public static final PropertyDefinition BccRecipients = 
		new ComplexPropertyDefinition<EmailAddressCollection>(
				EmailAddressCollection.class,
			XmlElementNames.BccRecipients,
			FieldUris.BccRecipients,
			EnumSet.of(PropertyDefinitionFlags.AutoInstantiateOnRead,
					PropertyDefinitionFlags.CanSet,
					PropertyDefinitionFlags.CanUpdate,
					PropertyDefinitionFlags.CanDelete),
			ExchangeVersion.Exchange2007_SP1,
			new ICreateComplexPropertyDelegate
			<EmailAddressCollection>() {
				@Override
				public EmailAddressCollection createComplexProperty() {
					return new EmailAddressCollection();
				};
			});

	/***
	 * Defines the CcRecipients property.
	 */
	public static final PropertyDefinition CcRecipients = 
		new ComplexPropertyDefinition<EmailAddressCollection>(
				EmailAddressCollection.class,
			XmlElementNames.CcRecipients,
			FieldUris.CcRecipients,
			EnumSet.of(PropertyDefinitionFlags.AutoInstantiateOnRead,
					PropertyDefinitionFlags.CanSet,
					PropertyDefinitionFlags.CanUpdate,
					PropertyDefinitionFlags.CanDelete),
			ExchangeVersion.Exchange2007_SP1,
			new ICreateComplexPropertyDelegate
			<EmailAddressCollection>() {
				@Override
				public EmailAddressCollection createComplexProperty() {
					return new EmailAddressCollection();
				};
			});

	/***
	 * Defines the ConversationIndex property.
	 */
	public static final PropertyDefinition ConversationIndex = 
		new ByteArrayPropertyDefinition(
			XmlElementNames.ConversationIndex, FieldUris.ConversationIndex,
			EnumSet.of(PropertyDefinitionFlags.CanFind),
			ExchangeVersion.Exchange2007_SP1);

	/***
	 * Defines the ConversationTopic property.
	 */
	public static final PropertyDefinition ConversationTopic = 
		new StringPropertyDefinition(
			XmlElementNames.ConversationTopic, FieldUris.ConversationTopic,
			EnumSet.of(PropertyDefinitionFlags.CanFind),
			ExchangeVersion.Exchange2007_SP1);

	/***
	 * Defines the From property.
	 */
	public static final PropertyDefinition From =
		new ContainedPropertyDefinition<EmailAddress>(
				EmailAddress.class,
			XmlElementNames.From, FieldUris.From, XmlElementNames.Mailbox,
			EnumSet.of(PropertyDefinitionFlags.CanSet,
					PropertyDefinitionFlags.CanUpdate,
					PropertyDefinitionFlags.CanDelete,
					PropertyDefinitionFlags.CanFind),
			ExchangeVersion.Exchange2007_SP1,
			new ICreateComplexPropertyDelegate<EmailAddress>() {
				@Override
				public EmailAddress createComplexProperty() {
					return new EmailAddress();
				};
			});

	/***
	 * Defines the IsDeliveryReceiptRequested property.
	 */
	public static final PropertyDefinition IsDeliveryReceiptRequested = 
		new BoolPropertyDefinition(
			XmlElementNames.IsDeliveryReceiptRequested,
			FieldUris.IsDeliveryReceiptRequested, EnumSet.of(
					PropertyDefinitionFlags.CanSet,
					PropertyDefinitionFlags.CanUpdate,
					PropertyDefinitionFlags.CanDelete,
					PropertyDefinitionFlags.CanFind),
			ExchangeVersion.Exchange2007_SP1);

	/***
	 * Defines the IsRead property.
	 */
	public static final PropertyDefinition IsRead = new BoolPropertyDefinition(
			XmlElementNames.IsRead, FieldUris.IsRead, EnumSet.of(
					PropertyDefinitionFlags.CanSet,
					PropertyDefinitionFlags.CanUpdate,
					PropertyDefinitionFlags.CanFind),
			ExchangeVersion.Exchange2007_SP1);

	/***
	 * Defines the IsReadReceiptRequested property.
	 */
	public static final PropertyDefinition IsReadReceiptRequested = 
		new BoolPropertyDefinition(
			XmlElementNames.IsReadReceiptRequested,
			FieldUris.IsReadReceiptRequested, EnumSet.of(
					PropertyDefinitionFlags.CanSet,
					PropertyDefinitionFlags.CanUpdate,
					PropertyDefinitionFlags.CanDelete,
					PropertyDefinitionFlags.CanFind),
			ExchangeVersion.Exchange2007_SP1);

	/***
	 * Defines the IsResponseRequested property.
	 */
	public static final PropertyDefinition IsResponseRequested = 
		new BoolPropertyDefinition(
			XmlElementNames.IsResponseRequested, FieldUris.IsResponseRequested,
			EnumSet.of(PropertyDefinitionFlags.CanSet,
					PropertyDefinitionFlags.CanUpdate,
					PropertyDefinitionFlags.CanDelete,
					PropertyDefinitionFlags.CanFind),
			ExchangeVersion.Exchange2007_SP1, true); // isNullable

	/***
	 * Defines the InternetMessageId property.
	 */
	public static final PropertyDefinition InternetMessageId = 
		new StringPropertyDefinition(
			XmlElementNames.InternetMessageId, FieldUris.InternetMessageId,
			EnumSet.of(PropertyDefinitionFlags.CanFind),
			ExchangeVersion.Exchange2007_SP1);

	/***
	 * Defines the References property.
	 */
	public static final PropertyDefinition References = 
		new StringPropertyDefinition(
			XmlElementNames.References, FieldUris.References, EnumSet.of(
					PropertyDefinitionFlags.CanSet,
					PropertyDefinitionFlags.CanUpdate,
					PropertyDefinitionFlags.CanDelete,
					PropertyDefinitionFlags.CanFind),
			ExchangeVersion.Exchange2007_SP1);

	/***
	 * Defines the ReplyTo property.
	 */
	public static final PropertyDefinition ReplyTo = 
		new ComplexPropertyDefinition<EmailAddressCollection>(
				EmailAddressCollection.class,
			XmlElementNames.ReplyTo,
			FieldUris.ReplyTo,
			EnumSet.of(PropertyDefinitionFlags.AutoInstantiateOnRead,
					PropertyDefinitionFlags.CanSet,
					PropertyDefinitionFlags.CanUpdate,
					PropertyDefinitionFlags.CanDelete),
			ExchangeVersion.Exchange2007_SP1,
			new ICreateComplexPropertyDelegate
			<EmailAddressCollection>() {
				@Override
				public EmailAddressCollection createComplexProperty() {
					return new EmailAddressCollection();
				}
			});

	/***
	 * Defines the Sender property.
	 */
	public static final PropertyDefinition Sender = 
		new ContainedPropertyDefinition<EmailAddress>(
				EmailAddress.class,
			XmlElementNames.Sender, FieldUris.Sender, XmlElementNames.Mailbox,
			EnumSet.of(PropertyDefinitionFlags.CanSet,
					PropertyDefinitionFlags.CanFind),
			ExchangeVersion.Exchange2007_SP1,
			new ICreateComplexPropertyDelegate<EmailAddress>() {
				@Override
				public EmailAddress createComplexProperty() {
					return new EmailAddress();
				}
			});

	/***
	 * Defines the ReceivedBy property.
	 */
	public static final PropertyDefinition ReceivedBy =
		new ContainedPropertyDefinition<EmailAddress>(
				EmailAddress.class,
			XmlElementNames.ReceivedBy, FieldUris.ReceivedBy,
			XmlElementNames.Mailbox, EnumSet
					.of(PropertyDefinitionFlags.CanFind),
			ExchangeVersion.Exchange2007_SP1,
			new ICreateComplexPropertyDelegate<EmailAddress>() {
				@Override
				public EmailAddress createComplexProperty() {
					return new EmailAddress();
				}
			});

	/***
	 * Defines the ReceivedRepresenting property.
	 */
	public static final PropertyDefinition ReceivedRepresenting = 
		new ContainedPropertyDefinition<EmailAddress>(
				EmailAddress.class,
			XmlElementNames.ReceivedRepresenting,
			FieldUris.ReceivedRepresenting, XmlElementNames.Mailbox, EnumSet
					.of(PropertyDefinitionFlags.CanFind),
			ExchangeVersion.Exchange2007_SP1,
			new ICreateComplexPropertyDelegate<EmailAddress>() {
				@Override
				public EmailAddress createComplexProperty() {
					return new EmailAddress();
				}
			});

	/** The Constant Instance. */
	protected static final EmailMessageSchema Instance = 
		new EmailMessageSchema();

	/**
	 * Gets the single instance of EmailMessageSchema.
	 * 
	 * @return single instance of EmailMessageSchema
	 */
	public static EmailMessageSchema getInstance() {
		return Instance;
	}

	/***
	 * Registers properties. IMPORTANT NOTE: PROPERTIES MUST BE REGISTERED IN
	 * SCHEMA ORDER (i.e. the same order as they are defined in types.xsd)
	 */
	@Override
	protected void registerProperties() {
		super.registerProperties();
		this.registerProperty(Sender);
		this.registerProperty(ToRecipients);
		this.registerProperty(CcRecipients);
		this.registerProperty(BccRecipients);
		this.registerProperty(IsReadReceiptRequested);
		this.registerProperty(IsDeliveryReceiptRequested);
		this.registerProperty(ConversationIndex);
		this.registerProperty(ConversationTopic);
		this.registerProperty(From);
		this.registerProperty(InternetMessageId);
		this.registerProperty(IsRead);
		this.registerProperty(IsResponseRequested);
		this.registerProperty(References);
		this.registerProperty(ReplyTo);
		this.registerProperty(ReceivedBy);
		this.registerProperty(ReceivedRepresenting);
	}

	/**
	 * * Initializes a new instance.
	 */
	protected EmailMessageSchema() {
		super();
	}
}
