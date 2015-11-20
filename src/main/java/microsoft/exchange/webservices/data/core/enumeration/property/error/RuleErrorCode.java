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

package microsoft.exchange.webservices.data.core.enumeration.property.error;

/**
 * Defines the error codes identifying why a rule failed validation.
 */
public enum RuleErrorCode {

  /**
   * Active Directory operation failed.
   */
  ADOperationFailure,

  /**
   * The e-mail account specified in the
   * FromConnectedAccounts predicate was not found.
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

