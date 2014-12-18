/**************************************************************************
 Exchange Web Services Java API
 Copyright (c) Microsoft Corporation
 All rights reserved.
 MIT License
 Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the ""Software""), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 THE SOFTWARE IS PROVIDED *AS IS*, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 **************************************************************************/

package microsoft.exchange.webservices.data;

public abstract class Strings {
  public static String AdditionalPropertyIsNull = "The additional property at index %d is null.";
  public static String AtLeastOneAttachmentCouldNotBeDeleted = "At least one attachment couldn't be deleted.";
  public static String AttachmentCreationFailed = "At least one attachment couldn't be created.";
  public static String CollectionIsEmpty = "The collection is empty.";
  public static String ArgumentIsBlankString = "The string argument contains only white space characters.";
  public static String ValueMustBeGreaterThanZero = "The value must be greater than 0.";
  public static String ClassIncompatibleWithRequestVersion =
      "Class %s is only valid for Exchange version %s or later.";
  public static String CredentialsRequired = "Credentials are required to make a service request.";
  public static String DeletingThisObjectTypeNotAuthorized = "Deleting this type of object isn't authorized.";
  public static String EndDateMustBeGreaterThanStartDate = "EndDate must be greater than StartDate.";
  public static String EnumValueIncompatibleWithRequestVersion =
      "Enumeration value %s in enumeration type %s is only valid for Exchange version %s or later.";
  public static String FolderTypeNotCompatible =
      "The folder type returned by the service (%s) isn't compatible with the requested folder type (%s).";
  public static String FolderToUpdateCannotBeNullOrNew = "Folders[%d] is either null or does not have an Id.";
  public static String HourMustBeBetween0And23 = "Hour must be between 0 and 23.";
  public static String IdAlreadyInList = "The ID is already in the list.";
  public static String IdPropertyMustBeSet = "The Id property must be set.";
  public static String IEnumerableDoesNotContainThatManyObject =
      "The IEnumerable doesn't contain that many objects.";
  public static String IndexIsOutOfRange = "index is out of range.";
  public static String IntervalMustBeGreaterOrEqualToOne = "The interval must be greater than or equal to 1.";
  public static String InvalidMailboxType = "The mailbox type isn't valid.";
  public static String InvalidRecurrencePattern = "Invalid recurrence pattern: (%s).";
  public static String InvalidRecurrenceRange = "Invalid recurrence range: (%s).";
  public static String InvalidFrequencyValue =
      "%d is not a valid frequency value. Valid values range from 1 to 1440.";
  public static String InvalidTimeoutValue =
      "%d is not a valid timeout value. Valid values range from 1 to 1440.";
  public static String ItemToUpdateCannotBeNullOrNew = "Items[%d] is either null or does not have an Id.";
  public static String ItemTypeNotCompatible =
      "The item type returned by the service (%s) isn't compatible with the requested item type (%s).";
  public static String LoadingThisObjectTypeNotSupported = "Loading this type of object is not supported.";
  public static String MaxChangesMustBeBetween1And512 = "MaxChangesReturned must be between 1 and 512.";
  public static String MethodIncompatibleWithRequestVersion =
      "Method %s is only valid for Exchange Server version %s or later.";
  public static String MinuteMustBeBetween0And59 = "Minute must be between 0 and 59.";
  public static String MinutesMustBeBetween0And1439 = "minutes must be between 0 and 1439, inclusive.";
  public static String MustLoadOrAssignPropertyBeforeAccess =
      "You must load or assign this property before you can read its value.";
  public static String NoAppropriateConstructorForItemClass =
      "No appropriate constructor could be found for this item class.";
  public static String NumberOfOccurrencesMustBeGreaterThanZero =
      "NumberOfOccurrences must be greater than 0.";
  public static String ObjectDoesNotHaveId = "This service object doesn't have an ID.";
  public static String ObjectTypeIncompatibleWithRequestVersion =
      "The object type %s is only valid for Exchange Server version %s or later versions.";
  public static String OccurrenceIndexMustBeGreaterThanZero = "OccurrenceIndex must be greater than 0.";
  public static String OffsetMustBeGreaterThanZero = "The offset must be greater than 0.";
  public static String OperationDoesNotSupportAttachments = "This operation isn't supported on attachments.";
  public static String PhoneCallAlreadyDisconnected = "The phone call has already been disconnected.";
  public static String PropertyCannotBeDeleted = "This property can't be deleted.";
  public static String PropertyCannotBeUpdated = "This property can't be updated.";
  public static String PropertyDefinitionPropertyMustBeSet = "The PropertyDefinition property must be set.";
  public static String PropertyIsReadOnly = "This property is read-only and can't be set.";
  public static String PropertyIncompatibleWithRequestVersion =
      "The property %s is valid only for Exchange %s or later versions.";
  public static String AttachmentCollectionNotLoaded = "The attachment collection must be loaded.";
  public static String RegenerationPatternsOnlyValidForTasks =
      "Regeneration patterns can only be used with Task items.";
  public static String RequestIncompatibleWithRequestVersion =
      "The service request %s is only valid for Exchange version %s or later.";
  public static String EqualityComparisonFilterIsInvalid =
      "Either the OtherPropertyDefinition or the Value properties must be set.";
  public static String SearchFilterAtIndexIsInvalid = "The search filter at index %d is invalid.";
  public static String SearchFilterMustBeSet = "The SearchFilter property must be set.";
  public static String SecondMustBeBetween0And59 = "Second must be between 0 and 59.";
  public static String ServiceObjectAlreadyHasId =
      "This operation can't be performed because this service object already has an ID. To update this service object, use the Update() method instead.";
  public static String ServiceObjectDoesNotHaveId =
      "This operation can't be performed because this service object doesn't have an Id.";
  public static String ServiceRequestFailed = "The request failed. %s";
  public static String TagValueIsOutOfRange =
      "The extended property tag value must be in the range of 0 to 65,535.";
  public static String TimeoutMustBeGreaterThanZero = "Timeout must be greater than zero.";
  public static String UnexpectedElement =
      "An element node '%s:%s' of the type %s was expected, but node '%s' of type %s was found.";
  public static String UnexpectedElementType =
      "The expected XML node type was %s, but the actual type is %s.";
  public static String UnexpectedEndOfXmlDocument = "Unexpected end of XML document.";
  public static String ExpectedStartElement =
      "The start element was expected, but node '%s' of type %s was found.";
  public static String ElementNotFound =
      "The element '%s' in namespace '%s' wasn't found at the current position.";
  public static String CurrentPositionNotElementStart =
      "The current position is not the start of an element.";
  public static String ValidationFailed = "Validation failed.";
  public static String ValuePropertyMustBeSet = "The Value property must be set.";
  public static String InvalidEmailAddress = "The e-mail address is formed incorrectly.";
  public static String MaximumRedirectionHopsExceeded = "The maximum redirection hop count has been reached.";
  public static String AutodiscoverError = "The Autodiscover service returned an error.";
  public static String AutodiscoverCouldNotBeLocated = "The Autodiscover service couldn't be located.";
  public static String UnsupportedWebProtocol = "Protocol %s isn't supported for service requests.";
  public static String ServerVersionNotSupported = "Exchange Server doesn't support the requested version.";
  public static String ItemAttachmentCannotBeUpdated = "Item attachments can't be updated.";
  public static String ServiceUrlMustBeSet = "The Url property on the ExchangeService object must be set.";
  public static String NonSummaryPropertyCannotBeUsed = "The property %s can't be used in %s requests.";
  public static String ReadAccessInvalidForNonCalendarFolder =
      "Permission read access value %s cannot be used with non-calendar folder.";
  public static String PermissionLevelInvalidForNonCalendarFolder =
      "Permission level value %s cannot be used with non-calendar folder.";
  public static String ItemAttachmentMustBeNamed = "The name of the item attachment at index %d must be set.";
  public static String ValuePropertyNotLoaded =
      "This property was requested, but it wasn't returned by the server.";
  public static String ValuePropertyNotAssigned =
      "You must assign this property before you can read its value.";
  public static String NullStringArrayElementInvalid = "The array contains at least one null element.";
  public static String ZeroLengthArrayInvalid = "The array must contain at least one element.";
  public static String ObjectTypeNotSupported =
      "Objects of type %s can't be added to the dictionary. The following types are supported: string array, byte array, boolean, byte, DateTime, integer, long, string, unsigned integer, and unsigned long.";
  public static String DeleteInvalidForUnsavedUserConfiguration =
      "This user configuration object can't be deleted because it's never been saved.";
  public static String InvalidElementStringValue =
      "The invalid value '%s' was specified for the '%s' element.";
  public static String InvalidAttributeValue = "The invalid value '%s' was specified for the '%s' attribute.";
  public static String FolderPermissionHasInvalidUserId =
      "The UserId in the folder permission at index %d is invalid. The StandardUser, PrimarySmtpAddress, or SID property must be set.";
  public static String AttachmentCannotBeUpdated = "Attachments can't be updated.";
  public static String FileAttachmentContentIsNotSet =
      "The content of the file attachment at index %d must be set.";
  public static String SearchParametersRootFolderIdsEmpty =
      "SearchParameters must contain at least one folder id.";
  public static String AutodiscoverDidNotReturnEwsUrl =
      "The Autodiscover service didn't return an appropriate URL that can be used for the ExchangeService Autodiscover URL.";
  public static String UserIdForDelegateUserNotSpecified =
      "The UserId in the DelegateUser hasn't been specified.";
  public static String DelegateUserHasInvalidUserId =
      "The UserId in the DelegateUser is invalid. The StandardUser, PrimarySmtpAddress or SID property must be set.";
  public static String DayOfMonthMustBeBetween1And31 = "DayOfMonth must be between 1 and 31.";
  public static String TimeoutMustBeBetween1And1440 = "Timeout must be a value between 1 and 1440.";
  public static String FrequencyMustBeBetween1And1440 = "The frequency must be a value between 1 and 1440.";
  public static String CannotSetPermissionLevelToCustom =
      "The PermissionLevel property can't be set to FolderPermissionLevel.Custom. To define a custom permission, set its individual properties to the values you want.";
  public static String AutodiscoverRedirectBlocked =
      "Autodiscover blocked a potentially insecure redirection to %s. To allow Autodiscover to follow the redirection, use the AutodiscoverUrl(string, AutodiscoverRedirectionUrlValidationCallback) overload.";
  public static String InvalidUser = "Invalid user: '%s'";
  public static String InvalidAutodiscoverRequest = "Invalid Autodiscover request: '%s'";
  public static String AutodiscoverServiceIncompatibleWithRequestVersion =
      "The Autodiscover service only supports %s or a later version.";
  public static String InvalidAutodiscoverSettingsCount = "At least one setting must be requested.";
  public static String InvalidAutodiscoverSmtpAddressesCount = "At least one SMTP address must be requested.";
  public static String InvalidAutodiscoverDomainsCount = "At least one domain name must be requested.";
  public static String AutodiscoverServiceRequestRequiresDomainOrUrl =
      "This Autodiscover request requires that either the Domain or Url be specified.";
  public static String NoSoapOrWsSecurityEndpointAvailable =
      "No appropriate Autodiscover SOAP or WS-Security endpoint is available.";
  public static String InvalidAutodiscoverServiceResponse = "The Autodiscover service response was invalid.";
  public static String InvalidAutodiscoverSmtpAddress = "A valid SMTP address must be specified.";
  public static String InvalidAutodiscoverDomain = "The domain name must be specified.";
  public static String MaxScpHopsExceeded = "The number of SCP URL hops exceeded the limit.";
  public static String UnsupportedTimeZonePeriodTransitionTarget =
      "The time zone transition target isn't supported.";
  public static String PeriodNotFound =
      "Invalid transition. A period with the specified Id couldn't be found: %s";
  public static String TransitionGroupNotFound =
      "Invalid transition. A transition group with the specified ID couldn't be found: %s";
  public static String UnknownTimeZonePeriodTransitionType = "Unknown time zone transition type: %s";
  public static String InvalidOrUnsupportedTimeZoneDefinition =
      "The time zone definition is invalid or unsupported.";
  public static String AttributeValueCannotBeSerialized =
      "Values of type '%s' can't be used for the '%s' attribute.";
  public static String ElementValueCannotBeSerialized =
      "Values of type '%s' can't be used for the '%s' element.";
  public static String SearchFilterComparisonValueTypeIsNotSupported =
      "Values of type '%s' cannot be as comparison values in search filters.";
  public static String TooFewServiceReponsesReturned =
      "The service was expected to return %s responses of type '%d', but %d responses were received.";
  public static String InvalidRedirectionResponseReturned =
      "The service returned an invalid redirection response.";
  public static String WLIDCredentialsCannotBeUsedWithLegacyAutodiscover =
      "WindowsLiveCredentials can't be used with this Autodiscover endpoint.";
  public static String ServerErrorAndStackTraceDetails = "%s -- Server Error: %s: %s %s";
  public static String PropertySetCannotBeModified = "This PropertySet is read-only and can't be modified.";
  public static String ItemIsOutOfDate =
      "The operation can't be performed because the item is out of date. Reload the item and try again.";
  public static String RecurrencePatternMustHaveStartDate =
      "The recurrence pattern's StartDate property must be specified.";
  public static String DayOfMonthMustBeSpecifiedForRecurrencePattern =
      "The recurrence pattern's DayOfMonth property must be specified.";
  public static String DaysOfTheWeekNotSpecified =
      "The recurrence pattern's property DaysOfTheWeek must contain at least one day of the week.";
  public static String DayOfTheWeekMustBeSpecifiedForRecurrencePattern =
      "The recurrence pattern's property DayOfTheWeek must be specified.";
  public static String DayOfWeekIndexMustBeSpecifiedForRecurrencePattern =
      "The recurrence pattern's DayOfWeekIndex property must be specified.";
  public static String MonthMustBeSpecifiedForRecurrencePattern =
      "The recurrence pattern's Month property must be specified.";
  public static String PropertyValueMustBeSpecifiedForRecurrencePattern =
      "The recurrence pattern's %s property must be specified.";
  public static String ParameterIncompatibleWithRequestVersion =
      "The parameter %s is only valid for Exchange Server version %s or a later version.";
  public static String NoError = "No error.";
  public static String InvalidPropertyValueNotInRange = "%s must be between %d and %d.";
  public static String MergedFreeBusyIntervalMustBeSmallerThanTimeWindow =
      "MergedFreeBusyInterval must be smaller than the specified time window.";
  public static String DurationMustBeSpecifiedWhenScheduled =
      "Duration must be specified when State is equal to Scheduled.";
  public static String CannotSubscribeToStatusEvents = "Status events can't be subscribed to.";
  public static String CannotUpdateNewUserConfiguration =
      "This user configuration can't be updated because it's never been saved.";
  public static String CannotSaveNotNewUserConfiguration =
      "Calling Save isn't allowed because this user configuration isn't new. To apply local changes to this user configuration, call Update instead.";
  public static String ArrayMustHaveAtLeastOneElement = "The Array value must have at least one element.";
  public static String ArrayMustHaveSingleDimension = "The array value must have a single dimension.";
  public static String IncompatibleTypeForArray = "Type %s can't be used as an array of type %s.";
  public static String ValueCannotBeConverted = "The value '%s' couldn't be converted to type %s.";
  public static String ValueOfTypeCannotBeConverted =
      "The value '%s' of type %s can't be converted to a value of type %s.";
  public static String BothSearchFilterAndQueryStringCannotBeSpecified =
      "Both search filter and query string can't be specified. One of them must be null.";
  public static String PropertyAlreadyExistsInOrderByCollection =
      "Property %s already exists in OrderByCollection.";
  public static String AutodiscoverInvalidSettingForOutlookProvider =
      "The requested setting, '%s', isn't supported by this Autodiscover endpoint.";
  public static String ServiceResponseDoesNotContainXml =
      "The response received from the service didn't contain valid XML.";
  public static String OperationNotSupportedForPropertyDefinitionType =
      "This operation isn't supported for property definition type %s.";
  public static String PropertyDefinitionTypeMismatch =
      "Property definition type '%s' and type parameter '%s' aren't compatible.";
  public static String CannotAddSubscriptionToLiveConnection =
      "Subscriptions can't be added to an open connection.";
  public static String CannotRemoveSubscriptionFromLiveConnection =
      "Subscriptions can't be removed from an open connection.";
  public static String CannotCallConnectDuringLiveConnection = "The connection has already opened.";
  public static String CannotCallDisconnectWithNoLiveConnection = "The connection is already closed.";
  public static String NoSubscriptionsOnConnection =
      "You must add at least one subscription to this connection before it can be opened.";
  public static String InvalidDomainName = "'%s' is not a valid domain name.";
  public static String CreateItemsDoesNotAllowAttachments =
      "This operation doesn't support items that have attachments.";
  public static String UpdateItemsDoesNotAllowAttachments =
      "This operation can't be performed because attachments have been added or deleted for one or more items.";
  public static String CreateItemsDoesNotHandleExistingItems =
      "This operation can't be performed because at least one item already has an ID.";
  public static String UpdateItemsDoesNotSupportNewOrUnchangedItems =
      "This operation can't be performed because one or more items are new or unmodified.";
  public static String CannotAddRequestHeader =
      "HTTP header '%s' isn't permitted. Only HTTP headers with the 'X-' prefix are permitted.";
  public static String CannotSetDelegateFolderPermissionLevelToCustom =
      "This operation can't be performed because one or more folder permission levels were set to Custom.";
  public static String ContactGroupMemberCannotBeUpdatedWithoutBeingLoadedFirst =
      "The contact group's Members property must be reloaded before newly-added members can be updated.";
  public static String StartTimeZoneRequired =
      "StartTimeZone required when setting the Start, End, IsAllDayEvent, or Recurrence properties.  You must load or assign this property before attempting to update the appointment.";
  public static String AccountIsLocked = "This account is locked. Visit %s to unlock it.";
  public static String AttachmentItemTypeMismatch = "Attachment item type mismatch.";
  public static String PropertyTypeIncompatibleWhenUpdatingCollection =
      "Property type incompatible when updating collection.";
  public static String MultipleContactPhotosInAttachment = "Multiple contact photos in attachment.";
  public static String InvalidAsyncResult = "Invalid AsyncResult.";
  public static String HttpsIsRequired = "Https is required.";

/*
    public static String FolderPermissionLevelMustBeSet = "The permission level of the folder permission at index %s must be set.";
    public static String NewMessagesWithAttachmentsCannotBeSentDirectly = "New messages with attachments can't be sent directly. You must first save the message and then send it.";
    public static String CannotConvertBetweenTimeZones = "Unable to convert %s from %s to %s.";
    public static String InvalidDateTime = "Invalid date and time: %s.";
    public static String ParentFolderDoesNotHaveId = "parentFolder doesn't have an Id.";
    public static String PercentCompleteMustBeBetween0And100 = "PercentComplete must be between 0 and 100.";
    public static String TimeWindowStartTimeMustBeGreaterThanEndTime = "The time window's end time must be greater than its start time.";
    public static String XsDurationCouldNotBeParsed = "The specified xsDuration argument couldn't be parsed.";
    public static String InvalidOrderBy = "At least one of the property definitions in the OrderBy clause is null.";
*/
}
