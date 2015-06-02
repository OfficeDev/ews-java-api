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

package microsoft.exchange.webservices.data.core.enumeration.property;

import microsoft.exchange.webservices.data.attribute.EwsEnum;

public enum RuleProperty {
  /**
   * The RuleId property of a rule.
   */
  @EwsEnum(schemaName = "RuleId")
  RuleId,


  /**
   * The DisplayName property of a rule.
   */
  @EwsEnum(schemaName = "DisplayName")
  DisplayName,

  /**
   * The Priority property of a rule.
   */
  @EwsEnum(schemaName = "Priority")
  Priority,

  /**
   * The IsNotSupported property of a rule.
   */
  @EwsEnum(schemaName = "IsNotSupported")
  IsNotSupported,

  /**
   * The Actions property of a rule.
   */
  @EwsEnum(schemaName = "Actions")
  Actions,

  /**
   * The Categories property of a rule's set of conditions.
   */
  @EwsEnum(schemaName = "Condition:Categories")
  ConditionCategories,

  /**
   * The ContainsBodyStrings property of a rule's set of conditions.
   */
  @EwsEnum(schemaName = "Condition:ContainsBodyStrings")
  ConditionContainsBodyStrings,

  /**
   * The ContainsHeaderStrings property of a rule's set of conditions.
   */
  @EwsEnum(schemaName = "Condition:ContainsHeaderStrings")
  ConditionContainsHeaderStrings,

  /**
   * The ContainsRecipientStrings property of a rule's set of conditions.
   */
  @EwsEnum(schemaName = "Condition:ContainsRecipientStrings")
  ConditionContainsRecipientStrings,

  /**
   * The ContainsSenderStrings property of a rule's set of conditions.
   */
  @EwsEnum(schemaName = "Condition:ContainsSenderStrings")
  ConditionContainsSenderStrings,

  /**
   * The ContainsSubjectOrBodyStrings property of a rule's set of conditions.
   */
  @EwsEnum(schemaName = "Condition:ContainsSubjectOrBodyStrings")
  ConditionContainsSubjectOrBodyStrings,

  /**
   * The ContainsSubjectStrings property of a rule's set of conditions.
   */
  @EwsEnum(schemaName = "Condition:ContainsSubjectStrings")
  ConditionContainsSubjectStrings,

  /**
   * The FlaggedForAction property of a rule's set of conditions.
   */
  @EwsEnum(schemaName = "Condition:FlaggedForAction")
  ConditionFlaggedForAction,

  /**
   * The FromAddresses property of a rule's set of conditions.
   */
  @EwsEnum(schemaName = "Condition:FromAddresses")
  ConditionFromAddresses,

  /**
   * The FromConnectedAccounts property of a rule's set of conditions.
   */
  @EwsEnum(schemaName = "Condition:FromConnectedAccounts")
  ConditionFromConnectedAccounts,

  /**
   * The HasAttachments property of a rule's set of conditions.
   */
  @EwsEnum(schemaName = "Condition:HasAttachments")
  ConditionHasAttachments,

  /**
   * The Importance property of a rule's set of conditions.
   */
  @EwsEnum(schemaName = "Condition:Importance")
  ConditionImportance,

  /**
   * The IsApprovalRequest property of a rule's set of conditions.
   */
  @EwsEnum(schemaName = "Condition:IsApprovalRequest")
  ConditionIsApprovalRequest,


  /**
   * The IsAutomaticForward property of a rule's set of conditions.
   */
  @EwsEnum(schemaName = "Condition:IsAutomaticForward")
  ConditionIsAutomaticForward,

  /**
   * The IsAutomaticForward property of a rule's set of conditions.
   */
  @EwsEnum(schemaName = "Condition:IsAutomaticReply")
  ConditionIsAutomaticReply,

  /**
   * The IsEncrypted property of a rule's set of conditions.
   */
  @EwsEnum(schemaName = "Condition:IsEncrypted")
  ConditionIsEncrypted,

  /**
   * The IsMeetingRequest property of a rule's set of conditions.
   */
  @EwsEnum(schemaName = "Condition:IsMeetingRequest")
  ConditionIsMeetingRequest,

  /**
   * The IsMeetingResponse property of a rule's set of conditions.
   */
  @EwsEnum(schemaName = "Condition:IsMeetingResponse")
  ConditionIsMeetingResponse,

  /**
   * The IsNonDeliveryReport property of a rule's set of conditions.
   */
  @EwsEnum(schemaName = "Condition:IsNDR")
  ConditionIsNonDeliveryReport,

  /**
   * The IsPermissionControlled property of a rule's set of conditions.
   */
  @EwsEnum(schemaName = "Condition:IsPermissionControlled")
  ConditionIsPermissionControlled,

  /**
   * The IsRead property of a rule's set of conditions.
   */
  @EwsEnum(schemaName = "Condition:IsRead")
  ConditionIsRead,

  /**
   * The IsSigned property of a rule's set of conditions.
   */
  @EwsEnum(schemaName = "Condition:IsSigned")
  ConditionIsSigned,

  /**
   * The IsVoicemail property of a rule's set of conditions.
   */
  @EwsEnum(schemaName = "Condition:IsVoicemail")
  ConditionIsVoicemail,

  /**
   * The IsReadReceipt property of a rule's set of conditions.
   */
  @EwsEnum(schemaName = "Condition:IsReadReceipt")
  ConditionIsReadReceipt,

  /**
   * The ItemClasses property of a rule's set of conditions.
   */
  @EwsEnum(schemaName = "Condition:ItemClasses")
  ConditionItemClasses,

  /**
   * The MessageClassifications property of a rule's set of conditions.
   */
  @EwsEnum(schemaName = "Condition:MessageClassifications")
  ConditionMessageClassifications,

  /**
   * The NotSentToMe property of a rule's set of conditions.
   */
  @EwsEnum(schemaName = "Condition:NotSentToMe")
  ConditionNotSentToMe,

  /**
   * The SentCcMe property of a rule's set of conditions.
   */
  @EwsEnum(schemaName = "Condition:SentCcMe")
  ConditionSentCcMe,

  /**
   * The SentOnlyToMe property of a rule's set of conditions.
   */
  @EwsEnum(schemaName = "Condition:SentOnlyToMe")
  ConditionSentOnlyToMe,

  /**
   * The SentToAddresses property of a rule's set of conditions.
   */
  @EwsEnum(schemaName = "Condition:SentToAddresses")
  ConditionSentToAddresses,

  /**
   * The SentToMe property of a rule's set of conditions.
   */
  @EwsEnum(schemaName = "Condition:SentToMe")
  ConditionSentToMe,

  /**
   * The SentToOrCcMe property of a rule's set of conditions.
   */
  @EwsEnum(schemaName = "Condition:SentToOrCcMe")
  ConditionSentToOrCcMe,

  /**
   * The Sensitivity property of a rule's set of conditions.
   */
  @EwsEnum(schemaName = "Condition:Sensitivity")
  ConditionSensitivity,

  /**
   * The WithinDateRange property of a rule's set of conditions.
   */
  @EwsEnum(schemaName = "Condition:WithinDateRange")
  ConditionWithinDateRange,

  /**
   * The WithinSizeRange property of a rule's set of conditions.
   */
  @EwsEnum(schemaName = "Condition:WithinSizeRange")
  ConditionWithinSizeRange,

  /**
   * The Categories property of a rule's set of exception.
   */
  @EwsEnum(schemaName = "Exception:Categories")
  ExceptionCategories,

  /**
   * The ContainsBodyStrings property of a rule's set of exception.
   */
  @EwsEnum(schemaName = "Exception:ContainsBodyStrings")
  ExceptionContainsBodyStrings,

  /**
   * The ContainsHeaderStrings property of a rule's set of exception.
   */
  @EwsEnum(schemaName = "Exception:ContainsHeaderStrings")
  ExceptionContainsHeaderStrings,

  /**
   * The ContainsRecipientStrings property of a rule's set of exception.
   */
  @EwsEnum(schemaName = "Exception:ContainsRecipientStrings")
  ExceptionContainsRecipientStrings,

  /**
   * The ContainsSenderStrings property of a rule's set of exception.
   */
  @EwsEnum(schemaName = "Exception:ContainsSenderStrings")
  ExceptionContainsSenderStrings,

  /**
   * The ContainsSubjectOrBodyStrings property of a rule's set of exception.
   */
  @EwsEnum(schemaName = "Exception:ContainsSubjectOrBodyStrings")
  ExceptionContainsSubjectOrBodyStrings,

  /**
   * The ContainsSubjectStrings property of a rule's set of exception.
   */
  @EwsEnum(schemaName = "Exception:ContainsSubjectStrings")
  ExceptionContainsSubjectStrings,

  /**
   * The FlaggedForAction property of a rule's set of exception.
   */
  @EwsEnum(schemaName = "Exception:FlaggedForAction")
  ExceptionFlaggedForAction,

  /**
   * The FromAddresses property of a rule's set of exception.
   */
  @EwsEnum(schemaName = "Exception:FromAddresses")
  ExceptionFromAddresses,

  /**
   * The FromConnectedAccounts property of a rule's set of exception.
   */
  @EwsEnum(schemaName = "Exception:FromConnectedAccounts")
  ExceptionFromConnectedAccounts,

  /**
   * The HasAttachments property of a rule's set of exception.
   */
  @EwsEnum(schemaName = "Exception:HasAttachments")
  ExceptionHasAttachments,

  /**
   * The Importance property of a rule's set of exception.
   */
  @EwsEnum(schemaName = "Exception:Importance")
  ExceptionImportance,

  /**
   * The IsApprovalRequest property of a rule's set of exception.
   */
  @EwsEnum(schemaName = "Exception:IsApprovalRequest")
  ExceptionIsApprovalRequest,

  /**
   * The IsAutomaticForward property of a rule's set of exception.
   */
  @EwsEnum(schemaName = "Exception:IsAutomaticForward")
  ExceptionIsAutomaticForward,

  /**
   * The IsAutomaticReply property of a rule's set of exception.
   */
  @EwsEnum(schemaName = "Exception:IsAutomaticReply")
  ExceptionIsAutomaticReply,

  /**
   * The IsEncrypted property of a rule's set of exception.
   */
  @EwsEnum(schemaName = "Exception:IsEncrypted")
  ExceptionIsEncrypted,

  /**
   * The IsMeetingRequest property of a rule's set of exception.
   */
  @EwsEnum(schemaName = "Exception:IsMeetingRequest")
  ExceptionIsMeetingRequest,

  /**
   * The IsMeetingResponse property of a rule's set of exception.
   */
  @EwsEnum(schemaName = "Exception:IsMeetingResponse")
  ExceptionIsMeetingResponse,

  /**
   * The IsNonDeliveryReport property of a rule's set of exception.
   */
  @EwsEnum(schemaName = "Exception:IsNDR")
  ExceptionIsNonDeliveryReport,

  /**
   * The IsPermissionControlled property of a rule's set of exception.
   */
  @EwsEnum(schemaName = "Exception:IsPermissionControlled")
  ExceptionIsPermissionControlled,

  /**
   * The IsRead property of a rule's set of exception.
   */
  @EwsEnum(schemaName = "Exception:IsRead")
  ExceptionIsRead,

  /**
   * The IsSigned property of a rule's set of exception.
   */
  @EwsEnum(schemaName = "Exception:IsSigned")
  ExceptionIsSigned,

  /**
   * The IsVoicemail property of a rule's set of exception.
   */
  @EwsEnum(schemaName = "Exception:IsVoicemail")
  ExceptionIsVoicemail,

  /**
   * The ItemClasses property of a rule's set of exception.
   */
  @EwsEnum(schemaName = "Exception:ItemClasses")
  ExceptionItemClasses,

  /**
   * The MessageClassifications property of a rule's set of exception.
   */
  @EwsEnum(schemaName = "Exception:MessageClassifications")
  ExceptionMessageClassifications,

  /**
   * The NotSentToMe property of a rule's set of exception.
   */
  @EwsEnum(schemaName = "Exception:NotSentToMe")
  ExceptionNotSentToMe,

  /**
   * The SentCcMe property of a rule's set of exception.
   */
  @EwsEnum(schemaName = "Exception:SentCcMe")
  ExceptionSentCcMe,

  /**
   * The SentOnlyToMe property of a rule's set of exception.
   */
  @EwsEnum(schemaName = "Exception:SentOnlyToMe")
  ExceptionSentOnlyToMe,

  /**
   * The SentToAddresses property of a rule's set of exception.
   */
  @EwsEnum(schemaName = "Exception:SentToAddresses")
  ExceptionSentToAddresses,

  /**
   * The SentToMe property of a rule's set of exception.
   */
  @EwsEnum(schemaName = "Exception:SentToMe")
  ExceptionSentToMe,

  /**
   * The SentToOrCcMe property of a rule's set of exception.
   */
  @EwsEnum(schemaName = "Exception:SentToOrCcMe")
  ExceptionSentToOrCcMe,

  /**
   * The Sensitivity property of a rule's set of exception.
   */
  @EwsEnum(schemaName = "Exception:Sensitivity")
  ExceptionSensitivity,

  /**
   * The WithinDateRange property of a rule's set of exception.
   */
  @EwsEnum(schemaName = "Exception:WithinDateRange")
  ExceptionWithinDateRange,

  /**
   * The WithinSizeRange property of a rule's set of exception.
   */
  @EwsEnum(schemaName = "Exception:WithinSizeRange")
  ExceptionWithinSizeRange,

  /**
   * The Categories property in a rule's set of actions.
   */
  @EwsEnum(schemaName = "Action:Categories")
  ActionCategories,

  /**
   * The CopyToFolder property in a rule's set of actions.
   */
  @EwsEnum(schemaName = "Action:CopyToFolder")
  ActionCopyToFolder,

  /**
   * The Delete property in a rule's set of actions.
   */
  @EwsEnum(schemaName = "Action:Delete")
  ActionDelete,

  /**
   * The ForwardAsAttachmentToRecipients property in a rule's set of actions.
   */
  @EwsEnum(schemaName = "Action:ForwardAsAttachmentToRecipients")
  ActionForwardAsAttachmentToRecipients,

  /**
   * The ForwardToRecipients property in a rule's set of actions.
   */
  @EwsEnum(schemaName = "Action:ForwardToRecipients")
  ActionForwardToRecipients,

  /**
   * The Importance property in a rule's set of actions.
   */
  @EwsEnum(schemaName = "Action:Importance")
  ActionImportance,

  /**
   * The MarkAsRead property in a rule's set of actions.
   */
  @EwsEnum(schemaName = "Action:MarkAsRead")
  ActionMarkAsRead,

  /**
   * The MoveToFolder property in a rule's set of actions.
   */
  @EwsEnum(schemaName = "Action:MoveToFolder")
  ActionMoveToFolder,

  /**
   * The PermanentDelete property in a rule's set of actions.
   */
  @EwsEnum(schemaName = "Action:PermanentDelete")
  ActionPermanentDelete,

  /**
   * The RedirectToRecipients property in a rule's set of actions.
   */
  @EwsEnum(schemaName = "Action:RedirectToRecipients")
  ActionRedirectToRecipients,

  /**
   * The SendSMSAlertToRecipients property in a rule's set of actions.
   */
  @EwsEnum(schemaName = "Action:SendSMSAlertToRecipients")
  ActionSendSMSAlertToRecipients,

  /**
   * The ServerReplyWithMessage property in a rule's set of actions.
   */
  @EwsEnum(schemaName = "Action:ServerReplyWithMessage")
  ActionServerReplyWithMessage,

  /**
   * The StopProcessingRules property in a rule's set of actions.
   */
  @EwsEnum(schemaName = "Action:StopProcessingRules")
  ActionStopProcessingRules,

  /**
   * The IsEnabled property of a rule, indicating if the rule is enabled.
   */
  @EwsEnum(schemaName = "IsEnabled")
  IsEnabled,

  /**
   * The IsInError property of a rule, indicating if the rule is in error.
   */
  @EwsEnum(schemaName = "IsInError")
  IsInError,

  /**
   * The Conditions property of a rule, contains all conditions of the rule.
   */
  @EwsEnum(schemaName = "Conditions")
  Conditions,

  /**
   * The Exceptions property of a rule, contains all exception of the rule.
   */
  @EwsEnum(schemaName = "Exceptions")
  Exceptions

}
