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

package microsoft.exchange.webservices.data.core.service.item;

import microsoft.exchange.webservices.data.attribute.ServiceObjectDefinition;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.PropertySet;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.service.ServiceObject;
import microsoft.exchange.webservices.data.core.service.schema.ConversationSchema;
import microsoft.exchange.webservices.data.core.service.schema.ServiceObjectSchema;
import microsoft.exchange.webservices.data.core.enumeration.service.calendar.AffectedTaskOccurrence;
import microsoft.exchange.webservices.data.core.enumeration.service.ConversationFlagStatus;
import microsoft.exchange.webservices.data.core.enumeration.service.DeleteMode;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.property.Importance;
import microsoft.exchange.webservices.data.core.enumeration.service.SendCancellationsMode;
import microsoft.exchange.webservices.data.core.exception.misc.ArgumentException;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceLocalException;
import microsoft.exchange.webservices.data.core.exception.service.remote.ServiceResponseException;
import microsoft.exchange.webservices.data.misc.OutParam;
import microsoft.exchange.webservices.data.property.complex.ConversationId;
import microsoft.exchange.webservices.data.property.complex.ExtendedPropertyCollection;
import microsoft.exchange.webservices.data.property.complex.FolderId;
import microsoft.exchange.webservices.data.property.complex.ItemIdCollection;
import microsoft.exchange.webservices.data.property.complex.StringList;
import microsoft.exchange.webservices.data.property.definition.PropertyDefinition;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Represents a collection of Conversation related property.
 * Properties available on this object are defined
 * in the ConversationSchema class.
 */
@ServiceObjectDefinition(xmlElementName = XmlElementNames.Conversation)
public class Conversation extends ServiceObject {

  /**
   * Initializes an unsaved local instance of Conversation.
   *
   * @param service The service
   *                The ExchangeService object to which the item will be bound.
   * @throws Exception
   */
  public Conversation(ExchangeService service) throws Exception {
    super(service);
  }

  /**
   * Internal method to return the schema associated with this type of object
   *
   * @return The schema associated with this type of object.
   */
  @Override public ServiceObjectSchema getSchema() {
    return ConversationSchema.Instance;
  }

  /**
   * Gets the minimum required server version.
   *
   * @return Earliest Exchange version in which
   * this service object type is supported.
   */
  @Override public ExchangeVersion getMinimumRequiredServerVersion() {
    return ExchangeVersion.Exchange2010_SP1;
  }

  /**
   * The property definition for the Id of this object.
   *
   * @return A PropertyDefinition instance.
   */
  @Override public PropertyDefinition getIdPropertyDefinition() {
    return ConversationSchema.Id;
  }

  /**
   * This method is not supported in this object.
   * Loads the specified set of property on the object.
   *
   * @param propertySet The propertySet
   *                    The property to load.
   */
  @Override
  protected void internalLoad(PropertySet propertySet) {
    throw new UnsupportedOperationException();
  }

  /**
   * This is not supported in this object.
   * Deletes the object.
   *
   * @param deleteMode              The deleteMode
   *                                The deletion mode.
   * @param sendCancellationsMode   The sendCancellationsMode
   *                                Indicates whether meeting cancellation messages should be sent.
   * @param affectedTaskOccurrences The affectedTaskOccurrences
   *                                Indicate which occurrence of a recurring task should be deleted.
   */
  @Override
  protected void internalDelete(DeleteMode deleteMode,
      SendCancellationsMode sendCancellationsMode,
      AffectedTaskOccurrence affectedTaskOccurrences) {
    throw new UnsupportedOperationException();
  }

  /**
   * This method is not supported in this object.
   * Gets the name of the change XML element.
   *
   * @return XML element name
   */
  @Override public String getChangeXmlElementName() {
    throw new UnsupportedOperationException();
  }

  /**
   * This method is not supported in this object.
   * Gets the name of the delete field XML element.
   *
   * @return XML element name
   */
  @Override public String getDeleteFieldXmlElementName() {
    throw new UnsupportedOperationException();
  }

  /**
   * This method is not supported in this object.
   * Gets the name of the set field XML element.
   *
   * @return XML element name
   */
  @Override public String getSetFieldXmlElementName() {
    throw new UnsupportedOperationException();
  }

  /**
   * This method is not supported in this object.
   * Gets a value indicating whether a time zone
   * SOAP header should be emitted in a CreateItem
   * or UpdateItem request so this item can be property saved or updated.
   *
   * @param isUpdateOperation Indicates whether
   *                          the operation being petrformed is an update operation.
   * @return true if a time zone SOAP header
   * should be emitted; otherwise, false.
   */
  @Override
  protected boolean getIsTimeZoneHeaderRequired(boolean isUpdateOperation) {
    throw new UnsupportedOperationException();
  }

  /**
   * This method is not supported in this object.
   * Gets the extended property collection.
   *
   * @return Extended property collection.
   */
  @Override
  protected ExtendedPropertyCollection getExtendedProperties() {
    throw new UnsupportedOperationException();
  }

  /**
   * Sets up a conversation so that any item
   * received within that conversation is always categorized.
   * Calling this method results in a call to EWS.
   *
   * @param categories           The categories that should be stamped on item in the conversation.
   * @param processSynchronously Indicates whether the method should
   *                             return only once enabling this rule and stamping existing item
   *                             in the conversation is completely done.
   *                             If processSynchronously is false, the method returns immediately.
   * @throws Exception
   * @throws IndexOutOfBoundsException
   * @throws ServiceResponseException
   */
  public void enableAlwaysCategorizeItems(Iterable<String> categories,
      boolean processSynchronously) throws ServiceResponseException,
      IndexOutOfBoundsException, Exception {

    ArrayList<ConversationId> convArry = new ArrayList<ConversationId>();
    convArry.add(this.getId());

    this.getService().enableAlwaysCategorizeItemsInConversations(
        convArry,
        categories,
        processSynchronously).getResponseAtIndex(0).throwIfNecessary();
  }

  /**
   * Sets up a conversation so that any item
   * received within that conversation is no longer categorized.
   * Calling this method results in a call to EWS.
   *
   * @param processSynchronously Indicates whether the method should
   *                             return only once disabling this rule and
   *                             removing the categories from existing item
   *                             in the conversation is completely done. If processSynchronously
   *                             is false, the method returns immediately.
   * @throws Exception
   * @throws IndexOutOfBoundsException
   * @throws ServiceResponseException
   */
  public void disableAlwaysCategorizeItems(boolean processSynchronously)
      throws ServiceResponseException, IndexOutOfBoundsException, Exception {
    ArrayList<ConversationId> convArry = new ArrayList<ConversationId>();
    convArry.add(this.getId());
    this.getService().disableAlwaysCategorizeItemsInConversations(
        convArry, processSynchronously).
        getResponseAtIndex(0).throwIfNecessary();
  }

  /**
   * Sets up a conversation so that any item received
   * within that conversation is always moved to Deleted Items folder.
   * Calling this method results in a call to EWS.
   *
   * @param processSynchronously Indicates whether the method should
   *                             return only once enabling this rule and deleting existing item
   *                             in the conversation is completely done. If processSynchronously
   *                             is false, the method returns immediately.
   * @throws Exception
   * @throws IndexOutOfBoundsException
   * @throws ServiceResponseException
   */
  public void enableAlwaysDeleteItems(boolean processSynchronously)
      throws ServiceResponseException, IndexOutOfBoundsException, Exception {
    ArrayList<ConversationId> convArry = new ArrayList<ConversationId>();
    convArry.add(this.getId());
    this.getService().enableAlwaysDeleteItemsInConversations(
        convArry,
        processSynchronously).getResponseAtIndex(0).throwIfNecessary();
  }

  /**
   * Sets up a conversation so that any item received within that
   * conversation is no longer moved to Deleted Items folder.
   * Calling this method results in a call to EWS.
   *
   * @param processSynchronously Indicates whether the method should return
   *                             only once disabling this rule and restoring the item
   *                             in the conversation is completely done. If processSynchronously
   *                             is false, the method returns immediately.
   * @throws Exception
   * @throws IndexOutOfBoundsException
   * @throws ServiceResponseException
   */
  public void disableAlwaysDeleteItems(boolean processSynchronously)
      throws ServiceResponseException, IndexOutOfBoundsException, Exception {
    ArrayList<ConversationId> convArry = new ArrayList<ConversationId>();
    convArry.add(this.getId());
    this.getService().disableAlwaysDeleteItemsInConversations(
        convArry,
        processSynchronously).getResponseAtIndex(0).throwIfNecessary();
  }

  /**
   * Sets up a conversation so that any item received within
   * that conversation is always moved to a specific folder.
   * Calling this method results in a call to EWS.
   *
   * @param destinationFolderId  The Id of the folder to which conversation item should be moved.
   * @param processSynchronously Indicates whether the method should return only
   *                             once enabling this rule
   *                             and moving existing item in the conversation is completely done.
   *                             If processSynchronously is false, the method returns immediately.
   * @throws Exception
   * @throws IndexOutOfBoundsException
   * @throws ServiceResponseException
   */
  public void enableAlwaysMoveItems(FolderId destinationFolderId,
      boolean processSynchronously) throws ServiceResponseException,
      IndexOutOfBoundsException, Exception {
    ArrayList<ConversationId> convArry = new ArrayList<ConversationId>();
    convArry.add(this.getId());
    this.getService().enableAlwaysMoveItemsInConversations(
        convArry,
        destinationFolderId,
        processSynchronously).getResponseAtIndex(0).throwIfNecessary();
  }

  /**
   * Sets up a conversation so that any item received within
   * that conversation is no longer moved to a specific
   * folder. Calling this method results in a call to EWS.
   *
   * @param processSynchronously Indicates whether the method should return only
   *                             once disabling this
   *                             rule is completely done. If processSynchronously
   *                             is false, the method returns immediately.
   * @throws Exception
   * @throws IndexOutOfBoundsException
   * @throws ServiceResponseException
   */
  public void disableAlwaysMoveItemsInConversation(boolean processSynchronously)
      throws ServiceResponseException, IndexOutOfBoundsException, Exception {
    ArrayList<ConversationId> convArry = new ArrayList<ConversationId>();
    convArry.add(this.getId());
    this.getService().disableAlwaysMoveItemsInConversations(
        convArry,
        processSynchronously).getResponseAtIndex(0).throwIfNecessary();
  }

  /**
   * Deletes item in the specified conversation.
   * Calling this method results in a call to EWS.
   *
   * @param contextFolderId The Id of the folder item must belong
   *                        to in order to be deleted. If contextFolderId is
   *                        null, item across the entire mailbox are deleted.
   * @param deleteMode      The deletion mode.
   * @throws Exception
   * @throws IndexOutOfBoundsException
   * @throws ServiceResponseException
   */
  public void deleteItems(FolderId contextFolderId, DeleteMode deleteMode)
      throws ServiceResponseException, IndexOutOfBoundsException, Exception {
    HashMap<ConversationId, Date> m = new HashMap<ConversationId, Date>();
    m.put(this.getId(), this.getGlobalLastDeliveryTime());

    List<HashMap<ConversationId, Date>> f = new ArrayList<HashMap<ConversationId, Date>>();
    f.add(m);

    this.getService().deleteItemsInConversations(
        f,
        contextFolderId,
        deleteMode).getResponseAtIndex(0).throwIfNecessary();
  }


  /**
   * Moves item in the specified conversation to a specific folder.
   * Calling this method results in a call to EWS.
   *
   * @param contextFolderId     The Id of the folder item must belong to
   *                            in order to be moved. If contextFolderId is null,
   *                            item across the entire mailbox are moved.
   * @param destinationFolderId The Id of the destination folder.
   * @throws Exception
   * @throws IndexOutOfBoundsException
   * @throws ServiceResponseException
   */
  public void moveItemsInConversation(
      FolderId contextFolderId,
      FolderId destinationFolderId) throws ServiceResponseException,
      IndexOutOfBoundsException, Exception {
    HashMap<ConversationId, Date> m = new HashMap<ConversationId, Date>();
    m.put(this.getId(), this.getGlobalLastDeliveryTime());

    List<HashMap<ConversationId, Date>> f = new ArrayList<HashMap<ConversationId, Date>>();
    f.add(m);

    this.getService().moveItemsInConversations(
        f, contextFolderId, destinationFolderId).
        getResponseAtIndex(0).throwIfNecessary();
  }

  /**
   * Copies item in the specified conversation to a specific folder.
   * Calling this method results in a call to EWS.
   *
   * @param contextFolderId     The Id of the folder item must belong to in
   *                            order to be copied. If contextFolderId
   *                            is null, item across the entire mailbox are copied.
   * @param destinationFolderId The Id of the destination folder.
   * @throws Exception
   * @throws IndexOutOfBoundsException
   * @throws ServiceResponseException
   */
  public void copyItemsInConversation(
      FolderId contextFolderId,
      FolderId destinationFolderId) throws ServiceResponseException,
      IndexOutOfBoundsException, Exception {
    HashMap<ConversationId, Date> m = new HashMap<ConversationId, Date>();
    m.put(this.getId(), this.getGlobalLastDeliveryTime());

    List<HashMap<ConversationId, Date>> f = new ArrayList<HashMap<ConversationId, Date>>();
    f.add(m);

    this.getService().copyItemsInConversations(
        f, contextFolderId, destinationFolderId).
        getResponseAtIndex(0).throwIfNecessary();
  }

  /**
   * Sets the read state of item in the specified conversation.
   * Calling this method results in a call to EWS.
   *
   * @param contextFolderId The Id of the folder item must
   *                        belong to in order for their read state to
   *                        be set. If contextFolderId is null, the read states of
   *                        item across the entire mailbox are set.
   * @param isRead          if set to <c>true</c>, conversation item are marked as read;
   *                        otherwise they are marked as unread.
   * @throws Exception
   * @throws IndexOutOfBoundsException
   * @throws ServiceResponseException
   */
  public void setReadStateForItemsInConversation(
      FolderId contextFolderId,
      boolean isRead) throws ServiceResponseException,
      IndexOutOfBoundsException, Exception {
    HashMap<ConversationId, Date> m = new HashMap<ConversationId, Date>();
    m.put(this.getId(), this.getGlobalLastDeliveryTime());

    List<HashMap<ConversationId, Date>> f = new ArrayList<HashMap<ConversationId, Date>>();
    f.add(m);

    this.getService().setReadStateForItemsInConversations(
        f, contextFolderId, isRead).
        getResponseAtIndex(0).throwIfNecessary();
  }

  /**
   * Gets the Id of this Conversation.
   *
   * @return Id
   * @throws ServiceLocalException
   */
  public ConversationId getId() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        getIdPropertyDefinition());
  }

  /**
   * Gets the topic of this Conversation.
   *
   * @return value
   * @throws ArgumentException
   */
  public String getTopic() throws ArgumentException {
    String returnValue = "";

    /**This property need not be present hence the
     *  property bag may not contain it.
     *Check for the presence of this property before accessing it.
     */
    if (this.getPropertyBag().contains(ConversationSchema.Topic)) {
      OutParam<String> out = new OutParam<String>();
      this.getPropertyBag().tryGetPropertyType(String.class,
          ConversationSchema.Topic,
          out);
      returnValue = out.getParam();
    }

    return returnValue;
  }

  /**
   * Gets a list of all the people who have received
   * messages in this conversation in the current folder only.
   *
   * @return String
   * @throws Exception
   */
  public StringList getUniqueRecipients() throws Exception {
    return getPropertyBag().getObjectFromPropertyDefinition(
        ConversationSchema.UniqueRecipients);
  }

  /**
   * Gets a list of all the people who have received
   * messages in this conversation across all folder in the mailbox.
   *
   * @return String
   * @throws Exception
   */
  public StringList getGlobalUniqueRecipients() throws Exception {
    return getPropertyBag().getObjectFromPropertyDefinition(
        ConversationSchema.GlobalUniqueRecipients);
  }

  /**
   * Gets a list of all the people who have sent messages
   * that are currently unread in this conversation in
   * the current folder only.
   *
   * @return unreadSenders
   * @throws ArgumentException
   */
  public StringList getUniqueUnreadSenders() throws ArgumentException {
    StringList unreadSenders = null;

    /**This property need not be present hence
     *  the property bag may not contain it.
     *Check for the presence of this property before accessing it.
     */
    if (this.getPropertyBag().contains(ConversationSchema.UniqueUnreadSenders)) {
      OutParam<StringList> out = new OutParam<StringList>();
      this.getPropertyBag().tryGetPropertyType(StringList.class,
          ConversationSchema.UniqueUnreadSenders,
          out);
      unreadSenders = out.getParam();
    }

    return unreadSenders;
  }


  /**
   * Gets a list of all the people who have sent
   * messages that are currently unread in this
   * conversation across all folder in the mailbox.
   *
   * @return unreadSenders
   * @throws ArgumentException
   */
  public StringList getGlobalUniqueUnreadSenders() throws ArgumentException {
    StringList unreadSenders = null;

    // This property need not be present hence
    //the property bag may not contain it.
    // Check for the presence of this property before accessing it.
    if (this.getPropertyBag().contains(ConversationSchema.GlobalUniqueUnreadSenders)) {
      OutParam<StringList> out = new OutParam<StringList>();
      this.getPropertyBag().tryGetPropertyType(StringList.class,
          ConversationSchema.GlobalUniqueUnreadSenders,
          out);
      unreadSenders = out.getParam();
    }

    return unreadSenders;
  }

  /**
   * Gets a list of all the people who have sent
   * messages in this conversation in the current folder only.
   *
   * @return String
   * @throws Exception
   */
  public StringList getUniqueSenders() throws Exception {
    return getPropertyBag().getObjectFromPropertyDefinition(
        ConversationSchema.UniqueSenders);
  }

  /**
   * Gets a list of all the people who have sent messages
   * in this conversation across all folder in the mailbox.
   *
   * @return String
   * @throws Exception
   */
  public StringList getGlobalUniqueSenders() throws Exception {
    return getPropertyBag().getObjectFromPropertyDefinition(
        ConversationSchema.GlobalUniqueSenders);
  }

  /**
   * Gets the delivery time of the message that was last
   * received in this conversation in the current folder only.
   *
   * @return Date
   * @throws Exception
   */
  public Date getLastDeliveryTime() throws Exception {
    return getPropertyBag().getObjectFromPropertyDefinition(
        ConversationSchema.LastDeliveryTime);
  }

  /**
   * Gets the delivery time of the message that was last
   * received in this conversation across all folder in the mailbox.
   *
   * @return Date
   * @throws Exception
   */
  public Date getGlobalLastDeliveryTime() throws Exception {
    return getPropertyBag().getObjectFromPropertyDefinition(
        ConversationSchema.GlobalLastDeliveryTime);
  }

  /**
   * Gets a list summarizing the categories stamped on
   * messages in this conversation, in the current folder only.
   *
   * @return value
   * @throws ArgumentException
   */
  public StringList getCategories() throws ArgumentException {
    StringList returnValue = null;

    /**This property need not be present hence
     * the property bag may not contain it.
     * Check for the presence of this property before accessing it.
     */
    if (this.getPropertyBag().contains(ConversationSchema.Categories)) {
      OutParam<StringList> out = new OutParam<StringList>();
      this.getPropertyBag().tryGetPropertyType(StringList.class,
          ConversationSchema.Categories,
          out);
      returnValue = out.getParam();
    }
    return returnValue;
  }

  /**
   * Gets a list summarizing the categories stamped on
   * messages in this conversation, across all folder in the mailbox.
   *
   * @return returnValue
   * @throws ArgumentException
   */
  public StringList getGlobalCategories() throws ArgumentException {
    StringList returnValue = null;

    // This property need not be present hence the
    //property bag may not contain it.
    // Check for the presence of this property before accessing it.
    if (this.getPropertyBag().contains(ConversationSchema.GlobalCategories)) {
      OutParam<StringList> out = new OutParam<StringList>();
      this.getPropertyBag().tryGetPropertyType(StringList.class,
          ConversationSchema.GlobalCategories,
          out);
      returnValue = out.getParam();
    }
    return returnValue;
  }

  /**
   * Gets the flag status for this conversation, calculated
   * by aggregating individual messages flag status in the current folder.
   *
   * @return returnValue
   * @throws ArgumentException
   */
  public ConversationFlagStatus getFlagStatus() throws ArgumentException {
    ConversationFlagStatus returnValue = ConversationFlagStatus.NotFlagged;

    // This property need not be present hence the
    //property bag may not contain it.
    // Check for the presence of this property before accessing it.
    if (this.getPropertyBag().contains(ConversationSchema.FlagStatus)) {
      OutParam<ConversationFlagStatus> out = new OutParam<ConversationFlagStatus>();
      this.getPropertyBag().tryGetPropertyType(
          ConversationFlagStatus.class,
          ConversationSchema.FlagStatus,
          out);
      returnValue = out.getParam();
    }

    return returnValue;
  }

  /**
   * Gets the flag status for this conversation, calculated by aggregating
   * individual messages flag status across all folder in the mailbox.
   *
   * @return returnValue
   * @throws ArgumentException
   */
  public ConversationFlagStatus getGlobalFlagStatus()
      throws ArgumentException {
    ConversationFlagStatus returnValue = ConversationFlagStatus.NotFlagged;

    // This property need not be present hence the
    //property bag may not contain it.
    // Check for the presence of this property before accessing it.
    if (this.getPropertyBag().contains(ConversationSchema.GlobalFlagStatus)) {
      OutParam<ConversationFlagStatus> out = new OutParam<ConversationFlagStatus>();
      this.getPropertyBag().tryGetPropertyType(
          ConversationFlagStatus.class,
          ConversationSchema.GlobalFlagStatus,
          out);
      returnValue = out.getParam();
    }

    return returnValue;
  }

  /**
   * Gets a value indicating if at least one message in this
   * conversation, in the current folder only, has an attachment.
   *
   * @return Value
   * @throws ServiceLocalException
   */
  public boolean getHasAttachments() throws ServiceLocalException {
    return getPropertyBag().<Boolean>getObjectFromPropertyDefinition(ConversationSchema.HasAttachments);
  }

  /**
   * Gets a value indicating if at least one message
   * in this conversation, across all folder in the mailbox,
   * has an attachment.
   *
   * @return boolean
   * @throws ServiceLocalException
   */
  public boolean getGlobalHasAttachments() throws ServiceLocalException {
    return getPropertyBag().<Boolean>getObjectFromPropertyDefinition(
        ConversationSchema.GlobalHasAttachments);
  }

  /**
   * Gets the total number of messages in this conversation
   * in the current folder only.
   *
   * @return integer
   * @throws ServiceLocalException
   */
  public int getMessageCount() throws ServiceLocalException {
    return getPropertyBag().<Integer>getObjectFromPropertyDefinition(
        ConversationSchema.MessageCount);
  }

  /**
   * Gets the total number of messages in this
   * conversation across all folder in the mailbox.
   *
   * @return integer
   * @throws ServiceLocalException
   */
  public int getGlobalMessageCount() throws ServiceLocalException {
    return getPropertyBag().<Integer>getObjectFromPropertyDefinition(
        ConversationSchema.GlobalMessageCount);
  }

  /**
   * Gets the total number of unread messages in this
   * conversation in the current folder only.
   *
   * @return returnValue
   * @throws ArgumentException
   */
  public int getUnreadCount() throws ArgumentException {
    int returnValue = 0;

    /**This property need not be present hence the
     * property bag may not contain it.
     * Check for the presence of this property before accessing it.
     */
    if (this.getPropertyBag().contains(ConversationSchema.UnreadCount)) {
      OutParam<Integer> out = new OutParam<Integer>();
      this.getPropertyBag().tryGetPropertyType(Integer.class,
          ConversationSchema.UnreadCount,
          out);
      returnValue = out.getParam().intValue();
    }

    return returnValue;
  }

  /**
   * Gets the total number of unread messages in this
   * conversation across all folder in the mailbox.
   *
   * @return returnValue
   * @throws ArgumentException
   */
  public int getGlobalUnreadCount() throws ArgumentException {
    int returnValue = 0;

    if (this.getPropertyBag().contains(ConversationSchema.GlobalUnreadCount)) {
      OutParam<Integer> out = new OutParam<Integer>();
      this.getPropertyBag().tryGetPropertyType(Integer.class,
          ConversationSchema.GlobalUnreadCount,
          out);
      returnValue = out.getParam().intValue();
    }
    return returnValue;

  }


  /**
   * Gets the size of this conversation, calculated by
   * adding the sizes of all messages in the conversation in
   * the current folder only.
   *
   * @return integer
   * @throws ServiceLocalException
   */
  public int getSize() throws ServiceLocalException {
    return getPropertyBag().<Integer>getObjectFromPropertyDefinition(
        ConversationSchema.Size);
  }

  /**
   * Gets the size of this conversation, calculated by
   * adding the sizes of all messages in the conversation
   * across all folder in the mailbox.
   *
   * @return integer
   * @throws ServiceLocalException
   */
  public int getGlobalSize() throws ServiceLocalException {
    return getPropertyBag().<Integer>getObjectFromPropertyDefinition(
        ConversationSchema.GlobalSize);
  }

  /**
   * Gets a list summarizing the classes of the item
   * in this conversation, in the current folder only.
   *
   * @return string
   * @throws Exception
   */
  public StringList getItemClasses() throws Exception {
    return getPropertyBag().getObjectFromPropertyDefinition(
        ConversationSchema.ItemClasses);
  }

  /**
   * Gets a list summarizing the classes of the item
   * in this conversation, across all folder in the mailbox.
   *
   * @return string
   * @throws Exception
   */
  public StringList getGlobalItemClasses() throws Exception {
    return getPropertyBag().getObjectFromPropertyDefinition(
        ConversationSchema.GlobalItemClasses);
  }

  /**
   * Gets the importance of this conversation, calculated by
   * aggregating individual messages importance in the current folder only.
   *
   * @return important
   * @throws Exception
   */
  public Importance getImportance() throws Exception {
    return getPropertyBag().getObjectFromPropertyDefinition(
        ConversationSchema.Importance);
  }

  /**
   * Gets the importance of this conversation, calculated by
   * aggregating individual messages importance across all
   * folder in the mailbox.
   *
   * @return important
   * @throws Exception
   */
  public Importance getGlobalImportance() throws Exception {
    return getPropertyBag().getObjectFromPropertyDefinition(
        ConversationSchema.GlobalImportance);
  }

  /**
   * Gets the Ids of the messages in this conversation,
   * in the current folder only.
   *
   * @return Id
   * @throws Exception
   */
  public ItemIdCollection getItemIds() throws Exception {
    return getPropertyBag().getObjectFromPropertyDefinition(
        ConversationSchema.ItemIds);
  }

  /**
   * Gets the Ids of the messages in this conversation,
   * across all folder in the mailbox.
   *
   * @return Id
   * @throws Exception
   */
  public ItemIdCollection getGlobalItemIds() throws Exception {
    return getPropertyBag().getObjectFromPropertyDefinition(
        ConversationSchema.GlobalItemIds);
  }

}
