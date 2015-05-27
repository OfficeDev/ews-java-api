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

package microsoft.exchange.webservices.data.core.enumeration.misc.error;

/**
 * Defines the error codes that can be returned by the Exchange Web Services.
 */
public enum ServiceError {

  // NoError. Indicates that an error has not occurred.
  /**
   * The No error.
   */
  NoError,

  // ErrorAccessDenied
  /**
   * The Error access denied.
   */
  ErrorAccessDenied,

  // ErrorAccessModeSpecified
  /**
   * The impersonation authentication header should not be included.
   */
  ErrorAccessModeSpecified,

  // ErrorAccountDisabled
  /**
   * The Error account disabled.
   */
  ErrorAccountDisabled,

  // ErrorAddDelegatesFailed
  /**
   * The Error add delegates failed.
   */
  ErrorAddDelegatesFailed,

  // ErrorAddressSpaceNotFound
  /**
   * ErrorAddressSpaceNotFound
   */
  ErrorAddressSpaceNotFound,

  // ErrorADOperation
  /**
   * The Error ad operation.
   */
  ErrorADOperation,

  // ErrorADSessionFilter
  /**
   * The Error ad session filter.
   */
  ErrorADSessionFilter,

  // ErrorADUnavailable
  /**
   * The Error ad unavailable.
   */
  ErrorADUnavailable,

  // ErrorAffectedTaskOccurrencesRequired
  /**
   * The Error affected task occurrences required.
   */
  ErrorAffectedTaskOccurrencesRequired,

  /**
   * The conversation action alwayscategorize or alwaysmove or alwaysdelete
   * has failed.
   */
  ErrorApplyConversationActionFailed,

  /**
   * The item has attachment at more than the maximum supported nest level.
   */
  ErrorAttachmentNestLevelLimitExceeded,

  // ErrorAttachmentSizeLimitExceeded
  /**
   * The Error attachment size limit exceeded.
   */
  ErrorAttachmentSizeLimitExceeded,

  // ErrorAutoDiscoverFailed
  /**
   * The Error auto discover failed.
   */
  ErrorAutoDiscoverFailed,

  // ErrorAvailabilityConfigNotFound
  /**
   * The Error availability config not found.
   */
  ErrorAvailabilityConfigNotFound,

  // ErrorBatchProcessingStopped
  /**
   * The Error batch processing stopped.
   */
  ErrorBatchProcessingStopped,

  // ErrorCalendarCannotMoveOrCopyOccurrence
  /**
   * The Error calendar cannot move or copy occurrence.
   */
  ErrorCalendarCannotMoveOrCopyOccurrence,

  // ErrorCalendarCannotUpdateDeletedItem
  /**
   * The Error calendar cannot update deleted item.
   */
  ErrorCalendarCannotUpdateDeletedItem,

  // ErrorCalendarCannotUseIdForOccurrenceId
  /**
   * The Error calendar cannot use id for occurrence id.
   */
  ErrorCalendarCannotUseIdForOccurrenceId,

  // ErrorCalendarCannotUseIdForRecurringMasterId
  /**
   * The Error calendar cannot use id for recurring master id.
   */
  ErrorCalendarCannotUseIdForRecurringMasterId,

  // ErrorCalendarDurationIsTooLong
  /**
   * The Error calendar duration is too long.
   */
  ErrorCalendarDurationIsTooLong,

  // ErrorCalendarEndDateIsEarlierThanStartDate
  /**
   * The Error calendar end date is earlier than start date.
   */
  ErrorCalendarEndDateIsEarlierThanStartDate,

  // ErrorCalendarFolderIsInvalidForCalendarView
  /**
   * The Error calendar folder is invalid for calendar view.
   */
  ErrorCalendarFolderIsInvalidForCalendarView,

  // ErrorCalendarInvalidAttributeValue
  /**
   * The Error calendar invalid attribute value.
   */
  ErrorCalendarInvalidAttributeValue,

  // ErrorCalendarInvalidDayForTimeChangePattern
  /**
   * The Error calendar invalid day for time change pattern.
   */
  ErrorCalendarInvalidDayForTimeChangePattern,

  // ErrorCalendarInvalidDayForWeeklyRecurrence
  /**
   * The Error calendar invalid day for weekly recurrence.
   */
  ErrorCalendarInvalidDayForWeeklyRecurrence,

  // ErrorCalendarInvalidPropertyState
  /**
   * The Error calendar invalid property state.
   */
  ErrorCalendarInvalidPropertyState,

  // ErrorCalendarInvalidPropertyValue
  /**
   * The Error calendar invalid property value.
   */
  ErrorCalendarInvalidPropertyValue,

  // ErrorCalendarInvalidRecurrence
  /**
   * The Error calendar invalid recurrence.
   */
  ErrorCalendarInvalidRecurrence,

  // ErrorCalendarInvalidTimeZone
  /**
   * The Error calendar invalid time zone.
   */
  ErrorCalendarInvalidTimeZone,

  // ErrorCalendarIsCancelledForAccept
  /**
   * The Error calendar is cancelled for accept.
   */
  ErrorCalendarIsCancelledForAccept,

  // ErrorCalendarIsCancelledForDecline
  /**
   * The Error calendar is cancelled for decline.
   */
  ErrorCalendarIsCancelledForDecline,

  // ErrorCalendarIsCancelledForRemove
  /**
   * The Error calendar is cancelled for remove.
   */
  ErrorCalendarIsCancelledForRemove,

  // ErrorCalendarIsCancelledForTentative
  /**
   * The Error calendar is cancelled for tentative.
   */
  ErrorCalendarIsCancelledForTentative,

  // ErrorCalendarIsDelegatedForAccept
  /**
   * The Error calendar is delegated for accept.
   */
  ErrorCalendarIsDelegatedForAccept,

  // ErrorCalendarIsDelegatedForDecline
  /**
   * The Error calendar is delegated for decline.
   */
  ErrorCalendarIsDelegatedForDecline,

  // ErrorCalendarIsDelegatedForRemove
  /**
   * The Error calendar is delegated for remove.
   */
  ErrorCalendarIsDelegatedForRemove,

  // ErrorCalendarIsDelegatedForTentative
  /**
   * The Error calendar is delegated for tentative.
   */
  ErrorCalendarIsDelegatedForTentative,

  // ErrorCalendarIsNotOrganizer
  /**
   * The Error calendar is not organizer.
   */
  ErrorCalendarIsNotOrganizer,

  // ErrorCalendarIsOrganizerForAccept
  /**
   * The Error calendar is organizer for accept.
   */
  ErrorCalendarIsOrganizerForAccept,

  // ErrorCalendarIsOrganizerForDecline
  /**
   * The Error calendar is organizer for decline.
   */
  ErrorCalendarIsOrganizerForDecline,

  // ErrorCalendarIsOrganizerForRemove
  /**
   * The Error calendar is organizer for remove.
   */
  ErrorCalendarIsOrganizerForRemove,

  // ErrorCalendarIsOrganizerForTentative
  /**
   * The Error calendar is organizer for tentative.
   */
  ErrorCalendarIsOrganizerForTentative,

  // ErrorCalendarMeetingRequestIsOutOfDate
  /**
   * The Error calendar meeting request is out of date.
   */
  ErrorCalendarMeetingRequestIsOutOfDate,

  // ErrorCalendarOccurrenceIndexIsOutOfRecurrenceRange
  /**
   * The Error calendar occurrence index is out of recurrence range.
   */
  ErrorCalendarOccurrenceIndexIsOutOfRecurrenceRange,

  // ErrorCalendarOccurrenceIsDeletedFromRecurrence
  /**
   * The Error calendar occurrence is deleted from recurrence.
   */
  ErrorCalendarOccurrenceIsDeletedFromRecurrence,

  // ErrorCalendarOutOfRange
  /**
   * The Error calendar out of range.
   */
  ErrorCalendarOutOfRange,

  // ErrorCalendarViewRangeTooBig
  /**
   * The Error calendar view range too big.
   */
  ErrorCalendarViewRangeTooBig,

  // ErrorCallerIsInvalidADAccount
  /**
   * The Error caller is invalid ad account.
   */
  ErrorCallerIsInvalidADAccount,

  // ErrorCannotCreateCalendarItemInNonCalendarFolder
  /**
   * The Error cannot create calendar item in non calendar folder.
   */
  ErrorCannotCreateCalendarItemInNonCalendarFolder,

  // ErrorCannotCreateContactInNonContactFolder
  /**
   * The Error cannot create contact in non contact folder.
   */
  ErrorCannotCreateContactInNonContactFolder,

  // ErrorCannotCreatePostItemInNonMailFolder
  /**
   * The Error cannot create post item in non mail folder.
   */
  ErrorCannotCreatePostItemInNonMailFolder,

  // ErrorCannotCreateTaskInNonTaskFolder
  /**
   * The Error cannot create task in non task folder.
   */
  ErrorCannotCreateTaskInNonTaskFolder,

  // ErrorCannotDeleteObject
  /**
   * The Error cannot delete object.
   */
  ErrorCannotDeleteObject,

  // ErrorCannotDeleteTaskOccurrence
  /**
   * The Error cannot delete task occurrence.
   */
  ErrorCannotDeleteTaskOccurrence,

  /**
   * Folder cannot be emptied.
   */
  ErrorCannotEmptyFolder,

  // ErrorCannotOpenFileAttachment
  /**
   * The Error cannot open file attachment.
   */
  ErrorCannotOpenFileAttachment,

  // ErrorCannotSetCalendarPermissionOnNonCalendarFolder
  /**
   * The Error cannot set calendar permission on non calendar folder.
   */
  ErrorCannotSetCalendarPermissionOnNonCalendarFolder,

  // ErrorCannotSetNonCalendarPermissionOnCalendarFolder
  /**
   * The Error cannot set non calendar permission on calendar folder.
   */
  ErrorCannotSetNonCalendarPermissionOnCalendarFolder,

  // ErrorCannotSetPermissionUnknownEntries
  /**
   * The Error cannot set permission unknown entries.
   */
  ErrorCannotSetPermissionUnknownEntries,

  // ErrorCannotUseFolderIdForItemId
  /**
   * The Error cannot use folder id for item id.
   */
  ErrorCannotUseFolderIdForItemId,

  // ErrorCannotUseItemIdForFolderId
  /**
   * The Error cannot use item id for folder id.
   */
  ErrorCannotUseItemIdForFolderId,

  // ErrorChangeKeyRequired
  /**
   * The Error change key required.
   */
  ErrorChangeKeyRequired,

  // ErrorChangeKeyRequiredForWriteOperations
  /**
   * The Error change key required for write operations.
   */
  ErrorChangeKeyRequiredForWriteOperations,

  /**
   * ErrorClientDisconnected
   */
  ErrorClientDisconnected,

  // ErrorConnectionFailed
  /**
   * The Error connection failed.
   */
  ErrorConnectionFailed,

  // ErrorContainsFilterWrongType
  /**
   * The Error contains filter wrong type.
   */
  ErrorContainsFilterWrongType,

  // ErrorContentConversionFailed
  /**
   * The Error content conversion failed.
   */
  ErrorContentConversionFailed,

  // ErrorCorruptData
  /**
   * The Error corrupt data.
   */
  ErrorCorruptData,

  // ErrorCreateItemAccessDenied
  /**
   * The Error create item access denied.
   */
  ErrorCreateItemAccessDenied,

  // ErrorCreateManagedFolderPartialCompletion
  /**
   * The Error create managed folder partial completion.
   */
  ErrorCreateManagedFolderPartialCompletion,

  // ErrorCreateSubfolderAccessDenied
  /**
   * The Error create subfolder access denied.
   */
  ErrorCreateSubfolderAccessDenied,

  // ErrorCrossMailboxMoveCopy
  /**
   * The Error cross mailbox move copy.
   */
  ErrorCrossMailboxMoveCopy,

  // ErrorCrossSiteRequest
  /**
   * The Error cross site request.
   */
  ErrorCrossSiteRequest,

  // ErrorDataSizeLimitExceeded
  /**
   * The Error data size limit exceeded.
   */
  ErrorDataSizeLimitExceeded,

  // ErrorDataSourceOperation
  /**
   * The Error data source operation.
   */
  ErrorDataSourceOperation,

  // ErrorDelegateAlreadyExists
  /**
   * The Error delegate already exists.
   */
  ErrorDelegateAlreadyExists,

  // ErrorDelegateCannotAddOwner
  /**
   * The Error delegate cannot add owner.
   */
  ErrorDelegateCannotAddOwner,

  // ErrorDelegateMissingConfiguration
  /**
   * The Error delegate missing configuration.
   */
  ErrorDelegateMissingConfiguration,

  // ErrorDelegateNoUser
  /**
   * The Error delegate no user.
   */
  ErrorDelegateNoUser,

  // ErrorDelegateValidationFailed
  /**
   * The Error delegate validation failed.
   */
  ErrorDelegateValidationFailed,

  // ErrorDeleteDistinguishedFolder
  /**
   * The Error delete distinguished folder.
   */
  ErrorDeleteDistinguishedFolder,

  // ErrorDeleteItemsFailed
  /**
   * The Error delete item failed.
   */
  ErrorDeleteItemsFailed,

  // ErrorDistinguishedUserNotSupported
  /**
   * The Error distinguished user not supported.
   */
  ErrorDistinguishedUserNotSupported,

  // ErrorDistributionListMemberNotExist
  /**
   * The Error distribution list member not exist.
   */
  ErrorDistributionListMemberNotExist,

  // ErrorDuplicateInputFolderNames
  /**
   * The Error duplicate input folder names.
   */
  ErrorDuplicateInputFolderNames,

  // ErrorDuplicateSOAPHeader
  /**
   * The Error duplicate soap header.
   */
  ErrorDuplicateSOAPHeader,

  // ErrorDuplicateUserIdsSpecified
  /**
   * The Error duplicate user ids specified.
   */
  ErrorDuplicateUserIdsSpecified,

  // ErrorEmailAddressMismatch
  /**
   * The Error email address mismatch.
   */
  ErrorEmailAddressMismatch,

  // ErrorEventNotFound
  /**
   * The Error event not found.
   */
  ErrorEventNotFound,

  // ErrorExceededConnectionCount
  /**
   * The Error exceeded connection count.
   */
  ErrorExceededConnectionCount,

  // ErrorExceededFindCountLimmit
  /**
   * The Error exceeded find count limit.
   */
  ErrorExceededFindCountLimit,

  // ErrorExceededSubscritionCount
  /**
   * The Error exceeded subscription count.
   */
  ErrorExceededSubscriptionCount,

  // ErrorExpiredSubscription
  /**
   * The Error expired subscription.
   */
  ErrorExpiredSubscription,

  // ErrorFolderCorrupt
  /**
   * The Error folder corrupt.
   */
  ErrorFolderCorrupt,

  // ErrorFolderExists
  /**
   * The Error folder exists.
   */
  ErrorFolderExists,

  // ErrorFolderNotFound
  /**
   * The specified folder could not be found in the store.
   */
  ErrorFolderNotFound,

  // ErrorFolderPropertRequestFailed
  /**
   * ErrorFolderPropertRequestFailed
   */
  ErrorFolderPropertRequestFailed,

  // ErrorFolderSave
  /**
   * The folder save operation did not succeed.
   */
  ErrorFolderSave,

  // ErrorFolderSaveFailed
  /**
   * The save operation failed or partially succeeded.
   */
  ErrorFolderSaveFailed,

  // ErrorFolderSavePropertyError
  /**
   * The folder save operation failed due to invalid property values.
   */
  ErrorFolderSavePropertyError,

  // ErrorFreeBusyDLLimitReached
  /**
   * ErrorFreeBusyDLLimitReached
   */
  ErrorFreeBusyDLLimitReached,

  // ErrorFreeBusyGenerationFailed
  /**
   * ErrorFreeBusyGenerationFailed
   */
  ErrorFreeBusyGenerationFailed,

  // ErrorGetServerSecurityDescriptorFailed
  /**
   * ErrorGetServerSecurityDescriptorFailed
   */
  ErrorGetServerSecurityDescriptorFailed,

  // ErrorImpersonateUserDenied
  /**
   * The account does not have permission to impersonate the requested user.
   */
  ErrorImpersonateUserDenied,

  // ErrorImpersonationDenied
  /**
   * ErrorImpersonationDenied
   */
  ErrorImpersonationDenied,

  // ErrorImpersonationFailed
  /**
   * Impersonation failed.
   */
  ErrorImpersonationFailed,

  // ErrorInboxRulesValidationError
  /**
   * ErrorInboxRulesValidationError
   */
  ErrorInboxRulesValidationError,

  // ErrorIncorrectSchemaVersion
  /**
   * The request is valid but does not specify the correct server version in
   * the RequestServerVersion SOAP header. Ensure that the
   * RequestServerVersion SOAP header is set with the correct
   * RequestServerVersionValue.
   */
  ErrorIncorrectSchemaVersion,

  // ErrorIncorrectUpdatePropertyCount
  /**
   * An object within a change description must contain one and only one
   * property to modify.
   */
  ErrorIncorrectUpdatePropertyCount,

  // ErrorIndividualMailboxLimitReached
  /**
   * ErrorIndividualMailboxLimitReached
   */
  ErrorIndividualMailboxLimitReached,

  // ErrorInsufficientResources
  /**
   * Resources are unavailable. Try again later.
   */
  ErrorInsufficientResources,

  // ErrorInternalServerError
  /**
   * An internal server error occurred. The operation failed.
   */
  ErrorInternalServerError,

  // ErrorInternalServerTransientError
  /**
   * An internal server error occurred. Try again later.
   */
  ErrorInternalServerTransientError,

  // ErrorInvalidAccessLevel
  /**
   * ErrorInvalidAccessLevel
   */
  ErrorInvalidAccessLevel,

  // ErrorInvalidArgument
  /**
   * ErrorInvalidArgument
   */
  ErrorInvalidArgument,

  // ErrorInvalidAttachmentId
  /**
   * The specified attachment Id is invalid.
   */
  ErrorInvalidAttachmentId,

  // ErrorInvalidAttachmentSubfilter
  /**
   * Attachment subfilters must have a single TextFilter therein.
   */
  ErrorInvalidAttachmentSubfilter,

  // ErrorInvalidAttachmentSubfilterTextFilter
  /**
   * Attachment subfilters must have a single TextFilter on the display name
   * only.
   */
  ErrorInvalidAttachmentSubfilterTextFilter,

  // ErrorInvalidAuthorizationContext
  /**
   * ErrorInvalidAuthorizationContext
   */
  ErrorInvalidAuthorizationContext,

  // ErrorInvalidChangeKey
  /**
   * The change key is invalid.
   */
  ErrorInvalidChangeKey,

  // ErrorInvalidClientSecurityContext
  /**
   * ErrorInvalidClientSecurityContext
   */
  ErrorInvalidClientSecurityContext,

  // ErrorInvalidCompleteDate
  /**
   * CompleteDate cannot be set to a date in the future.
   */
  ErrorInvalidCompleteDate,

  // ErrorInvalidContactEmailAddress
  /**
   * The e-mail address that was supplied isn't valid.
   */
  ErrorInvalidContactEmailAddress,

  // ErrorInvalidContactEmailIndex
  /**
   * The e-mail index supplied isn't valid.
   */
  ErrorInvalidContactEmailIndex,

  // ErrorInvalidCrossForestCredentials
  /**
   * ErrorInvalidCrossForestCredentials
   */
  ErrorInvalidCrossForestCredentials,

  /**
   * Invalid Delegate Folder Permission.
   */
  ErrorInvalidDelegatePermission,

  /**
   * One or more UserId parameters are invalid. Make sure that the
   * PrimarySmtpAddress, Sid and DisplayName property refer to the same user
   * when specified.
   */
  ErrorInvalidDelegateUserId,

  /**
   * An ExchangeImpersonation SOAP header must contain a user principal name,
   * user SID, or primary SMTP address.
   */
  ErrorInvalidExchangeImpersonationHeaderData,

  /**
   * Second operand in Excludes expression must be uint compatible.
   */
  ErrorInvalidExcludesRestriction,

  /**
   * FieldURI can only be used in Contains expressions.
   */
  ErrorInvalidExpressionTypeForSubFilter,

  /**
   * The extended property attribute combination is invalid.
   */
  ErrorInvalidExtendedProperty,

  /**
   * The extended property value is inconsistent with its type.
   */
  ErrorInvalidExtendedPropertyValue,

  /**
   * The original sender of the message (initiator field in the sharing
   * metadata) is not valid.
   */
  ErrorInvalidExternalSharingInitiator,

  /**
   * The sharing message is not intended for this caller.
   */
  ErrorInvalidExternalSharingSubscriber,

  /**
   * The organization is either not federated, or it's configured incorrectly.
   */
  ErrorInvalidFederatedOrganizationId,

  /**
   * Folder Id is invalid.
   */
  ErrorInvalidFolderId,

  /**
   * ErrorInvalidFolderTypeForOperation
   */
  ErrorInvalidFolderTypeForOperation,

  /**
   * Invalid fractional paging offset values.
   */
  ErrorInvalidFractionalPagingParameters,

  /**
   * ErrorInvalidFreeBusyViewType
   */
  ErrorInvalidFreeBusyViewType,

  /**
   * Either DataType or SharedFolderId must be specified, but not both.
   */
  ErrorInvalidGetSharingFolderRequest,

  // ErrorInvalidId
  /**
   * The Error invalid id.
   */
  ErrorInvalidId,

  /**
   * Id must be non-empty.
   */
  ErrorInvalidIdEmpty,

  /**
   * Id is malformed.
   */
  ErrorInvalidIdMalformed,

  /**
   * The EWS Id is in EwsLegacyId format which is not supported by the
   * Exchange version specified by your request. Please use the ConvertId
   * method to convert from EwsLegacyId to EwsId format.
   */
  ErrorInvalidIdMalformedEwsLegacyIdFormat,

  /**
   * Moniker exceeded allowable length.
   */
  ErrorInvalidIdMonikerTooLong,

  /**
   * The Id does not represent an item attachment.
   */
  ErrorInvalidIdNotAnItemAttachmentId,

  /**
   * ResolveNames returned an invalid Id.
   */
  ErrorInvalidIdReturnedByResolveNames,

  /**
   * Id exceeded allowable length.
   */
  ErrorInvalidIdStoreObjectIdTooLong,

  /**
   * Too many attachment levels.
   */
  ErrorInvalidIdTooManyAttachmentLevels,

  /**
   * The Id Xml is invalid.
   */
  ErrorInvalidIdXml,

  /**
   * The specified indexed paging values are invalid.
   */
  ErrorInvalidIndexedPagingParameters,

  /**
   * Only one child node is allowed when setting an Internet Message Header.
   */
  ErrorInvalidInternetHeaderChildNodes,

  /**
   * Item type is invalid for AcceptItem action.
   */
  ErrorInvalidItemForOperationAcceptItem,

  /**
   * Item type is invalid for CancelCalendarItem action.
   */
  ErrorInvalidItemForOperationCancelItem,

  /**
   * Item type is invalid for CreateItem operation.
   */
  ErrorInvalidItemForOperationCreateItem,

  /**
   * Item type is invalid for CreateItemAttachment operation.
   */
  ErrorInvalidItemForOperationCreateItemAttachment,

  /**
   * Item type is invalid for DeclineItem operation.
   */
  ErrorInvalidItemForOperationDeclineItem,

  /**
   * ExpandDL operation does not support this item type.
   */
  ErrorInvalidItemForOperationExpandDL,

  /**
   * Item type is invalid for RemoveItem operation.
   */
  ErrorInvalidItemForOperationRemoveItem,

  /**
   * Item type is invalid for SendItem operation.
   */
  ErrorInvalidItemForOperationSendItem,

  /**
   * The item of this type is invalid for TentativelyAcceptItem action.
   */
  ErrorInvalidItemForOperationTentative,

  /**
   * The logon type isn't valid.
   */
  ErrorInvalidLogonType,

  /**
   * Mailbox is invalid. Verify the specified Mailbox property.
   */
  ErrorInvalidMailbox,

  /**
   * The Managed Folder property is corrupt or otherwise invalid.
   */
  ErrorInvalidManagedFolderProperty,

  /**
   * The managed folder has an invalid quota.
   */
  ErrorInvalidManagedFolderQuota,

  /**
   * The managed folder has an invalid storage limit value.
   */
  ErrorInvalidManagedFolderSize,

  /**
   * ErrorInvalidMergedFreeBusyInterval
   */
  ErrorInvalidMergedFreeBusyInterval,

  /**
   * The specified value is not a valid name for name resolution.
   */
  ErrorInvalidNameForNameResolution,

  /**
   * ErrorInvalidNetworkServiceContext
   */
  ErrorInvalidNetworkServiceContext,

  /**
   * ErrorInvalidOofParameter
   */
  ErrorInvalidOofParameter,

  /**
   * ErrorInvalidOperation
   */
  ErrorInvalidOperation,

  /**
   * ErrorInvalidOrganizationRelationshipForFreeBusy
   */
  ErrorInvalidOrganizationRelationshipForFreeBusy,

  /**
   * MaxEntriesReturned must be greater than zero.
   */
  ErrorInvalidPagingMaxRows,

  /**
   * Cannot create a subfolder within a SearchFolder.
   */
  ErrorInvalidParentFolder,

  /**
   * PercentComplete must be an integer between 0 and 100.
   */
  ErrorInvalidPercentCompleteValue,

  /**
   * The permission settings were not valid.
   */
  ErrorInvalidPermissionSettings,

  /**
   * The phone call ID isn't valid.
   */
  ErrorInvalidPhoneCallId,

  /**
   * The phone number isn't valid.
   */
  ErrorInvalidPhoneNumber,

  /**
   * The append action is not supported for this property.
   */
  ErrorInvalidPropertyAppend,

  /**
   * The delete action is not supported for this property.
   */
  ErrorInvalidPropertyDelete,

  /**
   * Property cannot be used in Exists expression. Use IsEqualTo instead.
   */
  ErrorInvalidPropertyForExists,

  /**
   * Property is not valid for this operation.
   */
  ErrorInvalidPropertyForOperation,

  /**
   * Property is not valid for this object type.
   */
  ErrorInvalidPropertyRequest,

  /**
   * Set action is invalid for property.
   */
  ErrorInvalidPropertySet,

  // / <summary>
  // / Update operation is invalid for property of a sent message.
  // / </summary>
  ErrorInvalidPropertyUpdateSentMessage,

  // / <summary>
  // / The proxy security context is invalid.
  // / </summary>
  ErrorInvalidProxySecurityContext,

  // / <summary>
  // / SubscriptionId is invalid. Subscription is not a pull subscription.
  // / </summary>
  ErrorInvalidPullSubscriptionId,

  // / <summary>
  // / URL specified for push subscription is invalid.
  // / </summary>
  ErrorInvalidPushSubscriptionUrl,

  // / <summary>
  // / One or more recipients are invalid.
  // / </summary>
  ErrorInvalidRecipients,

  // / <summary>
  // / Recipient subfilters are only supported when
  // /there are two expressions within a single
  // / AND filter.
  // / </summary>
  ErrorInvalidRecipientSubfilter,

  // / <summary>
  // / Recipient subfilter must have a comparison filter
  // /that tests equality to recipient type
  // / or attendee type.
  // / </summary>
  ErrorInvalidRecipientSubfilterComparison,

  // / <summary>
  // / Recipient subfilters must have a text filter
  // /and a comparison filter in that order.
  // / </summary>
  ErrorInvalidRecipientSubfilterOrder,

  // / <summary>
  // / Recipient subfilter must have a TextFilter on the SMTP address only.
  // / </summary>
  ErrorInvalidRecipientSubfilterTextFilter,

  // / <summary>
  // / The reference item does not support the requested operation.
  // / </summary>
  ErrorInvalidReferenceItem,

  // / <summary>
  // / The request is invalid.
  // / </summary>
  ErrorInvalidRequest,

  // / <summary>
  // / The restriction is invalid.
  // / </summary>
  ErrorInvalidRestriction,

  // / <summary>
  // / The routing type format is invalid.
  // / </summary>
  ErrorInvalidRoutingType,

  // / <summary>
  // / ErrorInvalidScheduledOofDuration
  // / </summary>
  ErrorInvalidScheduledOofDuration,

  // / <summary>
  // / The mailbox that was requested doesn't support
  // /the specified RequestServerVersion.
  // / </summary>
  ErrorInvalidSchemaVersionForMailboxVersion,

  // / <summary>
  // / ErrorInvalidSecurityDescriptor
  // / </summary>
  ErrorInvalidSecurityDescriptor,

  // / <summary>
  // / Invalid combination of SaveItemToFolder
  // /attribute and SavedItemFolderId element.
  // / </summary>
  ErrorInvalidSendItemSaveSettings,

  // / <summary>
  // / Invalid serialized access token.
  // / </summary>
  ErrorInvalidSerializedAccessToken,

  // / <summary>
  // / The specified server version is invalid.
  // / </summary>
  ErrorInvalidServerVersion,

  // / <summary>
  // / The sharing message metadata is not valid.
  // / </summary>
  ErrorInvalidSharingData,

  // / <summary>
  // / The sharing message is not valid.
  // / </summary>
  ErrorInvalidSharingMessage,

  // / <summary>
  // / A SID with an invalid format was encountered.
  // / </summary>
  ErrorInvalidSid,

  // / <summary>
  // / The SIP address isn't valid.
  // / </summary>
  ErrorInvalidSIPUri,

  // / <summary>
  // / The SMTP address format is invalid.
  // / </summary>
  ErrorInvalidSmtpAddress,

  // / <summary>
  // / Invalid subFilterType.
  // / </summary>
  ErrorInvalidSubfilterType,

  // / <summary>
  // / SubFilterType is not attendee type.
  // / </summary>
  ErrorInvalidSubfilterTypeNotAttendeeType,

  // / <summary>
  // / SubFilterType is not recipient type.
  // / </summary>
  ErrorInvalidSubfilterTypeNotRecipientType,

  // / <summary>
  // / Subscription is invalid.
  // / </summary>
  ErrorInvalidSubscription,

  // / <summary>
  // / A subscription can only be established on
  // /a single public folder or on folder from a
  // / single mailbox.
  // / </summary>
  ErrorInvalidSubscriptionRequest,

  // / <summary>
  // / Synchronization state data is corrupt or otherwise invalid.
  // / </summary>
  ErrorInvalidSyncStateData,

  // / <summary>
  // / ErrorInvalidTimeInterval
  // / </summary>
  ErrorInvalidTimeInterval,

  // / <summary>
  // / A UserId was not valid.
  // / </summary>
  ErrorInvalidUserInfo,

  // / <summary>
  // / ErrorInvalidUserOofSettings
  // / </summary>
  ErrorInvalidUserOofSettings,

  // / <summary>
  // / The impersonation principal name is invalid.
  // / </summary>
  ErrorInvalidUserPrincipalName,

  // / <summary>
  // / The user SID is invalid or does not map
  // /to a user in the Active Directory.
  // / </summary>
  ErrorInvalidUserSid,

  // / <summary>
  // / ErrorInvalidUserSidMissingUPN
  // / </summary>
  ErrorInvalidUserSidMissingUPN,

  // / <summary>
  // / The specified value is invalid for property.
  // / </summary>
  ErrorInvalidValueForProperty,

  // / <summary>
  // / The watermark is invalid.
  // / </summary>
  ErrorInvalidWatermark,

  // / <summary>
  // / A valid IP gateway couldn't be found.
  // / </summary>
  ErrorIPGatewayNotFound,

  // / <summary>
  // / The send or update operation could not be
  // /performed because the change key passed in the
  // / request does not match the current change key for the item.
  // / </summary>
  ErrorIrresolvableConflict,

  // / <summary>
  // / The item is corrupt.
  // / </summary>
  ErrorItemCorrupt,

  // / <summary>
  // / The specified object was not found in the store.
  // / </summary>
  ErrorItemNotFound,

  // / <summary>
  // / One or more of the property requested for
  // /this item could not be retrieved.
  // / </summary>
  ErrorItemPropertyRequestFailed,

  // / <summary>
  // / The item save operation did not succeed.
  // / </summary>
  ErrorItemSave,

  // / <summary>
  // / Item save operation did not succeed.
  // / </summary>
  ErrorItemSavePropertyError,

  // / <summary>
  // / ErrorLegacyMailboxFreeBusyViewTypeNotMerged
  // / </summary>
  ErrorLegacyMailboxFreeBusyViewTypeNotMerged,

  // / <summary>
  // / ErrorLocalServerObjectNotFound
  // / </summary>
  ErrorLocalServerObjectNotFound,

  // / <summary>
  // / ErrorLogonAsNetworkServiceFailed
  // / </summary>
  ErrorLogonAsNetworkServiceFailed,

  // / <summary>
  // / Unable to access an account or mailbox.
  // / </summary>
  ErrorMailboxConfiguration,

  // / <summary>
  // / ErrorMailboxDataArrayEmpty
  // / </summary>
  ErrorMailboxDataArrayEmpty,

  // / <summary>
  // / ErrorMailboxDataArrayTooBig
  // / </summary>
  ErrorMailboxDataArrayTooBig,

  // / <summary>
  // / ErrorMailboxFailover
  // / </summary>
  ErrorMailboxFailover,

  // / <summary>
  // / ErrorMailboxLogonFailed
  // / </summary>
  ErrorMailboxLogonFailed,

  // / <summary>
  // / Mailbox move in progress. Try again later.
  // / </summary>
  ErrorMailboxMoveInProgress,

  // / <summary>
  // / The mailbox database is temporarily unavailable.
  // / </summary>
  ErrorMailboxStoreUnavailable,

  // / <summary>
  // / ErrorMailRecipientNotFound
  // / </summary>
  ErrorMailRecipientNotFound,

  // / <summary>
  // / MailTips aren't available for your organization.
  // / </summary>
  ErrorMailTipsDisabled,

  // / <summary>
  // / The specified Managed Folder already exists in the mailbox.
  // / </summary>
  ErrorManagedFolderAlreadyExists,

  // / <summary>
  // / Unable to find the specified managed folder in the Active Directory.
  // / </summary>
  ErrorManagedFolderNotFound,

  // / <summary>
  // / Failed to create or bind to the folder: Managed Folders
  // / </summary>
  ErrorManagedFoldersRootFailure,

  // / <summary>
  // / ErrorMeetingSuggestionGenerationFailed
  // / </summary>
  ErrorMeetingSuggestionGenerationFailed,

  // / <summary>
  // / MessageDisposition attribute is required.
  // / </summary>
  ErrorMessageDispositionRequired,

  // / <summary>
  // / The message exceeds the maximum supported size.
  // / </summary>
  ErrorMessageSizeExceeded,

  // / <summary>
  // / The domain specified in the tracking request doesn't exist.
  // / </summary>
  ErrorMessageTrackingNoSuchDomain,

  // / <summary>
  // / The log search service can't track this message.
  // / </summary>
  ErrorMessageTrackingPermanentError,

  // / <summary>
  // / The log search service isn't currently
  // /available. Please try again later.
  // / </summary>
  ErrorMessageTrackingTransientError,

  // / <summary>
  // / MIME content conversion failed.
  // / </summary>
  ErrorMimeContentConversionFailed,

  // / <summary>
  // / Invalid MIME content.
  // / </summary>
  ErrorMimeContentInvalid,

  // / <summary>
  // / Invalid base64 string for MIME content.
  // / </summary>
  ErrorMimeContentInvalidBase64String,

  // / <summary>
  // / The subscription has missed events,
  // /but will continue service on this connection.
  // / </summary>
  ErrorMissedNotificationEvents,

  // / <summary>
  // / ErrorMissingArgument
  // / </summary>
  ErrorMissingArgument,

  // / <summary>
  // / When making a request as an account that does
  // /not have a mailbox, you must specify the
  // / mailbox primary SMTP address for any distinguished folder Ids.
  // / </summary>
  ErrorMissingEmailAddress,

  // / <summary>
  // / When making a request with an account that does not
  // /have a mailbox, you must specify the
  // / primary SMTP address for an existing mailbox.
  // / </summary>
  ErrorMissingEmailAddressForManagedFolder,

  // / <summary>
  // / EmailAddress or ItemId must be included in the request.
  // / </summary>
  ErrorMissingInformationEmailAddress,

  // / <summary>
  // / ReferenceItemId must be included in the request.
  // / </summary>
  ErrorMissingInformationReferenceItemId,

  // / <summary>
  // / SharingFolderId must be included in the request.
  // / </summary>
  ErrorMissingInformationSharingFolderId,

  // / <summary>
  // / An item must be specified when creating an item attachment.
  // / </summary>
  ErrorMissingItemForCreateItemAttachment,

  // / <summary>
  // / The managed folder Id is missing.
  // / </summary>
  ErrorMissingManagedFolderId,

  // / <summary>
  // / A message needs to have at least one recipient.
  // / </summary>
  ErrorMissingRecipients,

  // / <summary>
  // / Missing information for delegate user. You must
  // /either specify a valid SMTP address or
  // / SID.
  // / </summary>
  ErrorMissingUserIdInformation,

  // / <summary>
  // / Only one access mode header may be specified.
  // / </summary>
  ErrorMoreThanOneAccessModeSpecified,

  // / <summary>
  // / The move or copy operation failed.
  // / </summary>
  ErrorMoveCopyFailed,

  // / <summary>
  // / Cannot move distinguished folder.
  // / </summary>
  ErrorMoveDistinguishedFolder,

  // / <summary>
  // / Multiple results were found.
  // / </summary>
  ErrorNameResolutionMultipleResults,

  // / <summary>
  // / User must have a mailbox for name resolution operations.
  // / </summary>
  ErrorNameResolutionNoMailbox,

  // / <summary>
  // / No results were found.
  // / </summary>
  ErrorNameResolutionNoResults,

  // / <summary>
  // / Another connection was opened against this subscription.
  // / </summary>
  ErrorNewEventStreamConnectionOpened,

  // / <summary>
  // / Exchange Web Services are not currently available
  // /for this request because there are no
  // / available Client Access Services Servers in the target AD Site.
  // / </summary>
  ErrorNoApplicableProxyCASServersAvailable,

  // / <summary>
  // / ErrorNoCalendar
  // / </summary>
  ErrorNoCalendar,

  // / <summary>
  // / Exchange Web Services aren't available for this
  // /request because there is no Client Access
  // / server with the necessary configuration in the
  // /Active Directory site where the mailbox is
  // / stored. If the problem continues, click Help.
  // / </summary>
  ErrorNoDestinationCASDueToKerberosRequirements,

  // / <summary>
  // / Exchange Web Services aren't currently available
  // /for this request because an SSL
  // / connection couldn't be established to the Client
  // /Access server that should be used for
  // / mailbox access. If the problem continues, click Help.
  // / </summary>
  ErrorNoDestinationCASDueToSSLRequirements,

  // / <summary>
  // / Exchange Web Services aren't currently available
  // /for this request because the Client
  // / Access server used for proxying has an older
  // /version of Exchange installed than the
  // / Client Access server in the mailbox Active Directory site.
  // / </summary>
  ErrorNoDestinationCASDueToVersionMismatch,

  // / <summary>
  // / You cannot specify the FolderClass when creating a non-generic folder.
  // / </summary>
  ErrorNoFolderClassOverride,

  // / <summary>
  // / ErrorNoFreeBusyAccess
  // / </summary>
  ErrorNoFreeBusyAccess,

  // / <summary>
  // / Mailbox does not exist.
  // / </summary>
  ErrorNonExistentMailbox,

  // / <summary>
  // / The primary SMTP address must be specified when referencing a mailbox.
  // / </summary>
  ErrorNonPrimarySmtpAddress,

  // / <summary>
  // / Custom property cannot be specified using
  // /property tags. The GUID and Id/Name
  // / combination must be used instead.
  // / </summary>
  ErrorNoPropertyTagForCustomProperties,

  // / <summary>
  // / ErrorNoPublicFolderReplicaAvailable
  // / </summary>
  ErrorNoPublicFolderReplicaAvailable,

  // / <summary>
  // / There are no public folder servers available.
  // / </summary>
  ErrorNoPublicFolderServerAvailable,

  // / <summary>
  // / Exchange Web Services are not currently available
  // /for this request because none of the
  // / Client Access Servers in the destination site could process the
  // request.
  // / </summary>
  ErrorNoRespondingCASInDestinationSite,

  // / <summary>
  // / Policy does not allow granting of permissions to external users.
  // / </summary>
  ErrorNotAllowedExternalSharingByPolicy,

  // / <summary>
  // / The user is not a delegate for the mailbox.
  // / </summary>
  ErrorNotDelegate,

  // / <summary>
  // / There was not enough memory to complete the request.
  // / </summary>
  ErrorNotEnoughMemory,

  // / <summary>
  // / The sharing message is not supported.
  // / </summary>
  ErrorNotSupportedSharingMessage,

  // / <summary>
  // / Operation would change object type, which is not permitted.
  // / </summary>
  ErrorObjectTypeChanged,

  // / <summary>
  // / Modified occurrence is crossing or overlapping adjacent occurrence.
  // / </summary>
  ErrorOccurrenceCrossingBoundary,

  // / <summary>
  // / One occurrence of the recurring calendar item
  // /overlaps with another occurrence of the
  // / same calendar item.
  // / </summary>
  ErrorOccurrenceTimeSpanTooBig,

  // / <summary>
  // / Operation not allowed with public folder root.
  // / </summary>
  ErrorOperationNotAllowedWithPublicFolderRoot,

  // / <summary>
  // / Organization is not federated.
  // / </summary>
  ErrorOrganizationNotFederated,

  // / <summary>
  // / ErrorOutlookRuleBlobExists
  // / </summary>
  ErrorOutlookRuleBlobExists,

  // / <summary>
  // / You must specify the parent folder Id for this operation.
  // / </summary>
  ErrorParentFolderIdRequired,

  // / <summary>
  // / The specified parent folder could not be found.
  // / </summary>
  ErrorParentFolderNotFound,

  // / <summary>
  // / Password change is required.
  // / </summary>
  ErrorPasswordChangeRequired,

  // / <summary>
  // / Password has expired. Change password.
  // / </summary>
  ErrorPasswordExpired,

  // / <summary>
  // / Policy does not allow granting permission level to user.
  // / </summary>
  ErrorPermissionNotAllowedByPolicy,

  // / <summary>
  // / Dialing restrictions are preventing the phone number
  // /that was entered from being dialed.
  // / </summary>
  ErrorPhoneNumberNotDialable,

  // / <summary>
  // / Property update did not succeed.
  // / </summary>
  ErrorPropertyUpdate,

  // / <summary>
  // / At least one property failed validation.
  // / </summary>
  ErrorPropertyValidationFailure,

  // / <summary>
  // / Subscription related request failed because EWS
  // /could not contact the appropriate CAS
  // / server for this request. If this problem persists,
  // /recreate the subscription.
  // / </summary>
  ErrorProxiedSubscriptionCallFailure,

  // / <summary>
  // / Request failed because EWS could not contact
  // /the appropriate CAS server for this request.
  // / </summary>
  ErrorProxyCallFailed,

  // / <summary>
  // / Exchange Web Services (EWS) is not available for
  // /this mailbox because the user account
  // / associated with the mailbox is a member of
  // /too many groups. EWS limits the group
  // / membership it can proxy between Client Access Service Servers to 3000.
  // / </summary>
  ErrorProxyGroupSidLimitExceeded,

  // / <summary>
  // / ErrorProxyRequestNotAllowed
  // / </summary>
  ErrorProxyRequestNotAllowed,

  // / <summary>
  // / ErrorProxyRequestProcessingFailed
  // / </summary>
  ErrorProxyRequestProcessingFailed,

  // / <summary>
  // / Exchange Web Services are not currently
  // /available for this mailbox because it could not
  // / determine the Client Access Services Server to use for the mailbox.
  // / </summary>
  ErrorProxyServiceDiscoveryFailed,

  // / <summary>
  // / Proxy token has expired.
  // / </summary>
  ErrorProxyTokenExpired,

  // / <summary>
  // / ErrorPublicFolderRequestProcessingFailed
  // / </summary>
  ErrorPublicFolderRequestProcessingFailed,

  // / <summary>
  // / ErrorPublicFolderServerNotFound
  // / </summary>
  ErrorPublicFolderServerNotFound,

  // / <summary>
  // / The search folder has a restriction that is too long to return.
  // / </summary>
  ErrorQueryFilterTooLong,

  // / <summary>
  // / Mailbox has exceeded maximum mailbox size.
  // / </summary>
  ErrorQuotaExceeded,

  // / <summary>
  // / Unable to retrieve events for this subscription.
  // /The subscription must be recreated.
  // / </summary>
  ErrorReadEventsFailed,

  // / <summary>
  // / Unable to suppress read receipt. Read receipts are not pending.
  // / </summary>
  ErrorReadReceiptNotPending,

  // / <summary>
  // / Recurrence end date can not exceed Sep 1, 4500 00:00:00.
  // / </summary>
  ErrorRecurrenceEndDateTooBig,

  // / <summary>
  // / Recurrence has no occurrences in the specified range.
  // / </summary>
  ErrorRecurrenceHasNoOccurrence,

  // / <summary>
  // / Failed to remove one or more delegates.
  // / </summary>
  ErrorRemoveDelegatesFailed,

  // / <summary>
  // / ErrorRequestAborted
  // / </summary>
  ErrorRequestAborted,

  // / <summary>
  // / ErrorRequestStreamTooBig
  // / </summary>
  ErrorRequestStreamTooBig,

  // / <summary>
  // / Required property is missing.
  // / </summary>
  ErrorRequiredPropertyMissing,

  // / <summary>
  // / Cannot perform ResolveNames for non-contact folder.
  // / </summary>
  ErrorResolveNamesInvalidFolderType,

  // / <summary>
  // / Only one contacts folder can be specified in request.
  // / </summary>
  ErrorResolveNamesOnlyOneContactsFolderAllowed,

  // / <summary>
  // / The response failed schema validation.
  // / </summary>
  ErrorResponseSchemaValidation,

  // / <summary>
  // / The restriction or sort order is too complex for this operation.
  // / </summary>
  ErrorRestrictionTooComplex,

  // / <summary>
  // / Restriction contained too many elements.
  // / </summary>
  ErrorRestrictionTooLong,

  // / <summary>
  // / ErrorResultSetTooBig
  // / </summary>
  ErrorResultSetTooBig,

  // / <summary>
  // / ErrorRulesOverQuota
  // / </summary>
  ErrorRulesOverQuota,

  // / <summary>
  // / The folder in which item were to be saved could not be found.
  // / </summary>
  ErrorSavedItemFolderNotFound,

  // / <summary>
  // / The request failed schema validation.
  // / </summary>
  ErrorSchemaValidation,

  // / <summary>
  // / The search folder is not initialized.
  // / </summary>
  ErrorSearchFolderNotInitialized,

  // / <summary>
  // / The user account which was used to submit this request
  // /does not have the right to send
  // / mail on behalf of the specified sending account.
  // / </summary>
  ErrorSendAsDenied,

  // / <summary>
  // / SendMeetingCancellations attribute is required for Calendar item.
  // / </summary>
  ErrorSendMeetingCancellationsRequired,

  // / <summary>
  // / The SendMeetingInvitationsOrCancellations attribute
  // /is required for calendar item.
  // / </summary>
  ErrorSendMeetingInvitationsOrCancellationsRequired,

  // ErrorSendMeetingInvitationsRequired
  /**
   * The SendMeetingInvitations attribute is required for calendar item.
   */
  ErrorSendMeetingInvitationsRequired,

  // ErrorSentMeetingRequestUpdate
  /**
   * The meeting request has already been sent and might not be updated.
   */
  ErrorSentMeetingRequestUpdate,

  // ErrorSentTaskRequestUpdate
  /**
   * The task request has already been sent and may not be updated.
   */
  ErrorSentTaskRequestUpdate,

  // ErrorServerBusy
  /**
   * The server cannot service this request right now. Try again later.
   */
  ErrorServerBusy,

  // ErrorServiceDiscoveryFailed
  /**
   * ErrorServiceDiscoveryFailed
   */
  ErrorServiceDiscoveryFailed,

  // ErrorSharingNoExternalEwsAvailable
  /**
   * No external Exchange Web Service URL available.
   */
  ErrorSharingNoExternalEwsAvailable,

  // ErrorSharingSynchronizationFailed
  /**
   * Failed to synchronize the sharing folder.
   */
  ErrorSharingSynchronizationFailed,

  // ErrorStaleObject
  /**
   * The current ChangeKey is required for this operation.
   */
  ErrorStaleObject,

  // ErrorSubmissionQuotaExceeded
  /**
   * The message couldn't be sent because the sender's submission quota was
   * exceeded. Please try again later.
   */
  ErrorSubmissionQuotaExceeded,

  // ErrorSubscriptionAccessDenied
  /**
   * Access is denied. Only the subscription owner may access the
   * subscription.
   */
  ErrorSubscriptionAccessDenied,

  // ErrorSubscriptionDelegateAccessNotSupported
  /**
   * Subscriptions are not supported for delegate user access.
   */
  ErrorSubscriptionDelegateAccessNotSupported,

  // ErrorSubscriptionNotFound
  /**
   * The specified subscription was not found.
   */
  ErrorSubscriptionNotFound,

  // ErrorSubscriptionUnsubscribed
  /**
   * The StreamingSubscription was unsubscribed while the current connection
   * was servicing it.
   */
  ErrorSubscriptionUnsubscribed,

  // ErrorSyncFolderNotFound
  /**
   * The folder to be synchronized could not be found.
   */
  ErrorSyncFolderNotFound,

  // ErrorTimeIntervalTooBig
  /**
   * ErrorTimeIntervalTooBig
   */
  ErrorTimeIntervalTooBig,

  // ErrorTimeoutExpired
  /**
   * ErrorTimeoutExpired
   */
  ErrorTimeoutExpired,

  // ErrorTimeZone
  /**
   * The time zone isn't valid.
   */
  ErrorTimeZone,

  // ErrorToFolderNotFound
  /**
   * The specified target folder could not be found.
   */
  ErrorToFolderNotFound,

  // ErrorTokenSerializationDenied
  /**
   * The requesting account does not have permission to serialize tokens.
   */
  ErrorTokenSerializationDenied,

  // ErrorUnableToGetUserOofSettings
  /**
   * ErrorUnableToGetUserOofSettings
   */
  ErrorUnableToGetUserOofSettings,

  // ErrorUnifiedMessagingDialPlanNotFound
  /**
   * A dial plan could not be found.
   */
  ErrorUnifiedMessagingDialPlanNotFound,

  // ErrorUnifiedMessagingRequestFailed
  /**
   * The UnifiedMessaging request failed.
   */
  ErrorUnifiedMessagingRequestFailed,

  // ErrorUnifiedMessagingServerNotFound
  /**
   * A connection couldn't be made to the Unified Messaging server.
   */
  ErrorUnifiedMessagingServerNotFound,

  // ErrorUnsupportedCulture
  /**
   * The specified item culture is not supported on this server.
   */
  ErrorUnsupportedCulture,

  // ErrorUnsupportedMapiPropertyType
  /**
   * The MAPI property type is not supported.
   */
  ErrorUnsupportedMapiPropertyType,

  // ErrorUnsupportedMimeConversion
  /**
   * MIME conversion is not supported for this item type.
   */
  ErrorUnsupportedMimeConversion,

  // ErrorUnsupportedPathForQuery
  /**
   * The property can not be used with this type of restriction.
   */
  ErrorUnsupportedPathForQuery,

  // ErrorUnsupportedPathForSortGroup
  /**
   * The property can not be used for sorting or grouping results.
   */
  ErrorUnsupportedPathForSortGroup,

  // ErrorUnsupportedPropertyDefinition
  /**
   * PropertyDefinition is not supported in searches.
   */
  ErrorUnsupportedPropertyDefinition,

  // ErrorUnsupportedQueryFilter
  /**
   * QueryFilter type is not supported.
   */
  ErrorUnsupportedQueryFilter,

  // ErrorUnsupportedRecurrence
  /**
   * The specified recurrence is not supported.
   */
  ErrorUnsupportedRecurrence,

  // ErrorUnsupportedSubFilter
  /**
   * Unsupported subfilter type.
   */
  ErrorUnsupportedSubFilter,

  // ErrorUnsupportedTypeForConversion
  /**
   * Unsupported type for restriction conversion.
   */
  ErrorUnsupportedTypeForConversion,

  // ErrorUpdateDelegatesFailed
  /**
   * Failed to update one or more delegates.
   */
  ErrorUpdateDelegatesFailed,

  // ErrorUpdatePropertyMismatch
  /**
   * Property for update does not match property in object.
   */
  ErrorUpdatePropertyMismatch,

  // ErrorUserNotAllowedByPolicy
  /**
   * Policy does not allow granting permissions to user.
   */
  ErrorUserNotAllowedByPolicy,

  // ErrorUserNotUnifiedMessagingEnabled
  /**
   * The user isn't enabled for Unified Messaging
   */
  ErrorUserNotUnifiedMessagingEnabled,

  // ErrorUserWithoutFederatedProxyAddress
  /**
   * The user doesn't have an SMTP proxy address from a federated domain.
   */
  ErrorUserWithoutFederatedProxyAddress,

  // ErrorValueOutOfRange
  /**
   * The value is out of range.
   */
  ErrorValueOutOfRange,

  // ErrorVirusDetected
  /**
   * Virus detected in the message.
   */
  ErrorVirusDetected,

  // ErrorVirusMessageDeleted
  /**
   * The item has been deleted as a result of a virus scan.
   */
  ErrorVirusMessageDeleted,

  // ErrorVoiceMailNotImplemented
  /**
   * The Voice Mail distinguished folder is not implemented.
   */
  ErrorVoiceMailNotImplemented,

  // ErrorWebRequestInInvalidState
  /**
   * ErrorWebRequestInInvalidState
   */
  ErrorWebRequestInInvalidState,

  // ErrorWin32InteropError
  /**
   * ErrorWin32InteropError
   */
  ErrorWin32InteropError,

  // ErrorWorkingHoursSaveFailed
  /**
   * ErrorWorkingHoursSaveFailed
   */
  ErrorWorkingHoursSaveFailed,

  // ErrorWorkingHoursXmlMalformed
  /**
   * ErrorWorkingHoursXmlMalformed
   */
  ErrorWorkingHoursXmlMalformed,

  // ErrorWrongServerVersion
  /**
   * The Client Access server version doesn't match the Mailbox server version
   * of the resource that was being accessed. To determine the correct URL to
   * use to access the resource, use Autodiscover with the address of the
   * resource.
   */
  ErrorWrongServerVersion,

  // ErrorWrongServerVersionDelegate
  /**
   * The mailbox of the authenticating user and the mailbox of the resource
   * being accessed must have the same Mailbox server version.
   */
  ErrorWrongServerVersionDelegate,

}
