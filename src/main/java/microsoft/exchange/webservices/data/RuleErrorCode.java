/**************************************************************************
 * copyright file="RuleErrorCode.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the RuleErrorCode enumeration.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Defines the error codes identifying why a rule failed validation.
 */
 public enum RuleErrorCode
 {
     
	 /**
	  * Active Directory operation failed.
	  */
     ADOperationFailure,

     /**
	  * The e-mail account specified in the
	  *  FromConnectedAccounts predicate was not found.
	  */
     ConnectedAccountNotFound,

     /**
	  * The Rule object in a CreateInboxRuleOperation has an Id. The Ids of new
	  * rules are generated server side and 
	  * should not be provided by the client.
	  */
     CreateWithRuleId,

     /**
	  * The value is empty. An empty value is not allowed for the property.
	  */
     EmptyValueFound,

     /**
	  * There already is a rule with the same priority. 
	  */
     DuplicatedPriority,

     /**
	  * There are multiple operations against the same rule. 
	  * Only one operation per rule is allowed.
	  */
     DuplicatedOperationOnTheSameRule,

     /**
	  * The folder does not exist in the user's mailbox.
	  */
     FolderDoesNotExist,

     /**
	  * The e-mail address is invalid.
	  */
     InvalidAddress,

     /**
	  * The date range is invalid.
	  */
     InvalidDateRange,

     /**
	  * The folder Id is invalid.
	  */
     InvalidFolderId,

     /**
	  * The size range is invalid.
	  */
     InvalidSizeRange,

     /**
	  * The value is invalid.
	  */
     InvalidValue,

     /**
	  * The message classification was not found.
	  */
     MessageClassificationNotFound,

     /**
	  * No action was specified. At least one action must be specified.
	  */
     MissingAction,

     /**
	  * The required parameter is missing.
	  */
     MissingParameter,

     /**
	  * The range value is missing.
	  */
     MissingRangeValue,

     /**
	  * The property cannot be modified.
	  */
     NotSettable,

     /**
	  * The recipient does not exist.
	  */
     RecipientDoesNotExist,

     /**
	  * The rule was not found.
	  */
     RuleNotFound,

     /**
	  * The size is less than zero.
	  */
     SizeLessThanZero,

     /**
	  * The string value is too big.
	  */
     StringValueTooBig,

     /**
	  * The address is unsupported.
	  */
     UnsupportedAddress,

     /**
	  * An unexpected error occured.
	  */
     UnexpectedError,

     /**
	  * The rule is not supported.
	  */
     UnsupportedRule
 }

