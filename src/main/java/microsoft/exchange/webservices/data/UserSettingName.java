/**************************************************************************
 * copyright file="UserSettingName.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the UserSettingName.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * The Enum UserSettingName.
 */
public enum UserSettingName {

	// The display name of the user.
	/** The User display name. */
	UserDisplayName,

	// The legacy distinguished name of the user.
	/** The User dn. */
	UserDN,

	// The deployment Id of the user.
	/** The User deployment id. */
	UserDeploymentId,

	// The fully qualified domain name of the mailbox server.
	/** The Internal mailbox server. */
	InternalMailboxServer,

	// The fully qualified domain name of the RPC client server.
	/** The Internal rpc client server. */
	InternalRpcClientServer,

	// The legacy distinguished name of the mailbox server.
	/** The Internal mailbox server dn. */
	InternalMailboxServerDN,

	// The internal URL of the Exchange Control Panel.
	/** The Internal ecp url. */
	InternalEcpUrl,

	// The internal URL of the Exchange Control Panel for VoiceMail
	// Customization.
	/** The Internal ecp voicemail url. */
	InternalEcpVoicemailUrl,

	// The internal URL of the Exchange Control Panel for Email Subscriptions.
	/** The Internal ecp email subscriptions url. */
	InternalEcpEmailSubscriptionsUrl,

	// The internal URL of the Exchange Control Panel for Text Messaging.
	/** The Internal ecp text messaging url. */
	InternalEcpTextMessagingUrl,

	// The internal URL of the Exchange Control Panel for Delivery Reports.
	/** The Internal ecp delivery report url. */
	InternalEcpDeliveryReportUrl,

	/// The internal URL of the Exchange Control Panel for RetentionPolicy Tags.
	/** The Internal ecp retention policy tags url. */
	InternalEcpRetentionPolicyTagsUrl,

	/// The internal URL of the Exchange Control Panel for Publishing.
	/** The Internal ecp publishing url. */
	InternalEcpPublishingUrl,

	// The internal URL of the Exchange Web Services.
	/** The Internal ews url. */
	InternalEwsUrl,

	// The internal URL of the Offline Address Book.
	/** The Internal oab url. */
	InternalOABUrl,

	// The internal URL of the Unified Messaging services.
	/** The Internal um url. */
	InternalUMUrl,

	// The internal URLs of the Exchange web client.
	/** The Internal web client urls. */
	InternalWebClientUrls,

	// The distinguished name of the mailbox database of the user's mailbox.
	/** The Mailbox dn. */
	MailboxDN,

	// The name of the Public Folders server.
	/** The Public folder server. */
	PublicFolderServer,

	// The name of the Active Directory server.
	/** The Active directory server. */
	ActiveDirectoryServer,

	// The name of the RPC over HTTP server.
	/** The External mailbox server. */
	ExternalMailboxServer,

	// Indicates whether the RPC over HTTP server requires SSL.
	/** The External mailbox server requires ssl. */
	ExternalMailboxServerRequiresSSL,

	// The authentication methods supported by the RPC over HTTP server.
	/** The External mailbox server authentication methods. */
	ExternalMailboxServerAuthenticationMethods,

	// The URL fragment of the Exchange Control Panel for VoiceMail
	// Customization.
	/** The Ecp voicemail url fragment. */
	EcpVoicemailUrlFragment,

	// The URL fragment of the Exchange Control Panel for Email Subscriptions.
	/** The Ecp email subscriptions url fragment. */
	EcpEmailSubscriptionsUrlFragment,

	// The URL fragment of the Exchange Control Panel for Text Messaging.
	/** The Ecp text messaging url fragment. */
	EcpTextMessagingUrlFragment,

	// The URL fragment of the Exchange Control Panel for Delivery Reports.
	/** The Ecp delivery report url fragment. */
	EcpDeliveryReportUrlFragment,

	/// The URL fragment of the Exchange Control Panel for RetentionPolicy Tags.
	/**The Ecp retention policy tags url fragment. */
	EcpRetentionPolicyTagsUrlFragment,

	/// The URL fragment of the Exchange Control Panel for Publishing.
	/**The Ecp publishing url fragment. */
	EcpPublishingUrlFragment,

	// The external URL of the Exchange Control Panel.
	/** The External ecp url. */
	ExternalEcpUrl,

	// The external URL of the Exchange Control Panel for VoiceMail
	// Customization.
	/** The External ecp voicemail url. */
	ExternalEcpVoicemailUrl,

	// The external URL of the Exchange Control Panel for Email Subscriptions.
	/** The External ecp email subscriptions url. */
	ExternalEcpEmailSubscriptionsUrl,

	// The external URL of the Exchange Control Panel for Text Messaging.
	/** The External ecp text messaging url. */
	ExternalEcpTextMessagingUrl,

	// The external URL of the Exchange Control Panel for Delivery Reports.
	/** The External ecp delivery report url. */
	ExternalEcpDeliveryReportUrl,

	/// The external URL of the Exchange Control Panel for RetentionPolicy Tags.
	/** The External ecp retention policy tags url. */
	ExternalEcpRetentionPolicyTagsUrl,

	/// The external URL of the Exchange Control Panel for Publishing.
	/** The External ecp publishing url. */
	ExternalEcpPublishingUrl,

	// The external URL of the Exchange Web Services.
	/** The External ews url. */
	ExternalEwsUrl,

	// The external URL of the Offline Address Book.
	/** The External oab url. */
	ExternalOABUrl,

	// The external URL of the Unified Messaging services.
	/** The External um url. */
	ExternalUMUrl,

	// The external URLs of the Exchange web client.
	/** The External web client urls. */
	ExternalWebClientUrls,

	// Indicates that cross-organization sharing is enabled.
	/** The Cross organization sharing enabled. */
	CrossOrganizationSharingEnabled,

	// Collection of alternate mailboxes.
	/** The Alternate mailboxes. */
	AlternateMailboxes,

	// The version of the Client Access Server serving the request (e.g.
	// 14.XX.YYY.ZZZ)
	/** The Cas version. */
	CasVersion,

	// Comma-separated list of schema versions supported by Exchange Web
	// Services. The schema version values
	// will be the same as the values of the ExchangeServerVersion enumeration.
	/** The Ews supported schemas. */
	EwsSupportedSchemas,

	// The internal connection settings list for pop protocol
	/** The Internal pop3 connections. */
	InternalPop3Connections,

	// The external connection settings list for pop protocol
	/** The External pop3 connections. */
	ExternalPop3Connections,

	// The internal connection settings list for imap4 protocol
	/** The Internal imap4 connections. */
	InternalImap4Connections,

	// The external connection settings list for imap4 protocol
	/** The External imap4 connections. */
	ExternalImap4Connections,

	// The internal connection settings list for smtp protocol
	/** The Internal smtp connections. */
	InternalSmtpConnections,

	// The external connection settings list for smtp protocol
	/** The External smtp connections. */
	ExternalSmtpConnections,

	/// If set, then clients can call the server via XTC
	/** The Exchange Rpc Url. */
	ExchangeRpcUrl,

	/// The version of the Exchange Web Services
	///server ExternalEwsUrl is pointing to.
	/** The External Ews Version. */
	ExternalEwsVersion,
	
	/** Mobile Mailbox policy settings.*/
   
    MobileMailboxPolicy,
}
