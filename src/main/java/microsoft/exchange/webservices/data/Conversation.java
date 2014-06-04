/**************************************************************************

 * copyright file="Conversation.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the Conversation.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Represents a collection of Conversation related properties.
 * Properties available on this object are defined 
 * in the ConversationSchema class.
 */
@ServiceObjectDefinition(xmlElementName = XmlElementNames.Conversation)
public class Conversation extends ServiceObject {

	/**
	 * Initializes an unsaved local instance of Conversation. 
	 * @param service The service
	 * The ExchangeService object to which the item will be bound.
	 * @throws Exception 
	 */
	protected Conversation(ExchangeService service) throws Exception {
		super(service);
	}

	/**
	 * Internal method to return the schema associated with this type of object
	 * @return The schema associated with this type of object.
	 */
	@Override
	protected  ServiceObjectSchema getSchema(){		
		return ConversationSchema.Instance;		
	}

	/**
	 * Gets the minimum required server version.
	 * @return Earliest Exchange version in which
	 *  this service object type is supported.
	 */
	@Override
	protected ExchangeVersion getMinimumRequiredServerVersion(){
		return ExchangeVersion.Exchange2010_SP1;
	}

	/**
	 * The property definition for the Id of this object.
	 * @return A PropertyDefinition instance.
	 */
	@Override
	protected PropertyDefinition getIdPropertyDefinition(){		
		return ConversationSchema.Id;		
	}

	/**
	 * This method is not supported in this object.
	 * Loads the specified set of properties on the object.
	 * @param propertySet The propertySet
	 * The properties to load.
	 */
	@Override
	protected void internalLoad(PropertySet propertySet) {
		throw new UnsupportedOperationException();
	}

	/**
	 * This is not supported in this object.
	 * Deletes the object.
	 * @param deleteMode The deleteMode
	 * The deletion mode.
	 * @param sendCancellationsMode The sendCancellationsMode
	 * Indicates whether meeting cancellation messages should be sent.
	 * @param affectedTaskOccurrences The affectedTaskOccurrences
	 * Indicate which occurrence of a recurring task should be deleted.
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
	 * @return XML element name
	 */
	@Override
	protected String getChangeXmlElementName() {
		throw new UnsupportedOperationException();
	}

	/**
	 * This method is not supported in this object.
	 * Gets the name of the delete field XML element.
	 * @return XML element name
	 */
	@Override
	protected String getDeleteFieldXmlElementName(){
		throw new UnsupportedOperationException();
	}

	/**
	 * This method is not supported in this object.
	 * Gets the name of the set field XML element.
	 * @return XML element name
	 */
	@Override
	protected String getSetFieldXmlElementName(){
		throw new UnsupportedOperationException();
	}

	/**
	 * This method is not supported in this object.
	 * Gets a value indicating whether a time zone 
	 * SOAP header should be emitted in a CreateItem
	 * or UpdateItem request so this item can be property saved or updated.
	 * @param isUpdateOperation Indicates whether
	 *  the operation being petrformed is an update operation.
	 * @return true if a time zone SOAP header
	 *  should be emitted; otherwise, false.
	 */
	@Override
	protected boolean getIsTimeZoneHeaderRequired(boolean isUpdateOperation){
		throw new UnsupportedOperationException();
	}

	/**
	 * This method is not supported in this object.
	 * Gets the extended properties collection.
	 * @return Extended properties collection.
	 */
	@Override
	protected ExtendedPropertyCollection getExtendedProperties(){
		throw new UnsupportedOperationException();
	}

	/** Sets up a conversation so that any item
	 *  received within that conversation is always categorized.
	 * Calling this method results in a call to EWS.
	 *@param categories The categories that should be stamped on items in the conversation.
	 *@param processSynchronously Indicates whether the method should 
	 *return only once enabling this rule and stamping existing items 
	 * in the conversation is completely done. 
	 * If processSynchronously is false, the method returns immediately.
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
	 *  received within that conversation is no longer categorized.
	 * Calling this method results in a call to EWS.
	 * @param processSynchronously Indicates whether the method should
	 *  return only once disabling this rule and 
	 *  removing the categories from existing items 
	 * in the conversation is completely done. If processSynchronously 
	 * is false, the method returns immediately.
	 * @throws Exception 
	 * @throws IndexOutOfBoundsException 
	 * @throws ServiceResponseException 
	 */
	public void disableAlwaysCategorizeItems(boolean processSynchronously) 
	throws ServiceResponseException, IndexOutOfBoundsException, Exception {
		ArrayList<ConversationId> convArry = new ArrayList<ConversationId>();
		convArry.add(this.getId());
		 this.getService().disableAlwaysCategorizeItemsInConversations(
				 convArry,processSynchronously).
				 getResponseAtIndex(0).throwIfNecessary();
	}

	/**
	 * Sets up a conversation so that any item received 
	 * within that conversation is always moved to Deleted Items folder.
	 * Calling this method results in a call to EWS.
	 * @param processSynchronously Indicates whether the method should 
	 * return only once enabling this rule and deleting existing items 
	 * in the conversation is completely done. If processSynchronously
	 *  is false, the method returns immediately.
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
	 * @param processSynchronously Indicates whether the method should return 
	 * only once disabling this rule and restoring the items
	 * in the conversation is completely done. If processSynchronously 
	 * is false, the method returns immediately.
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
	 * @param destinationFolderId The Id of the folder to which conversation items should be moved.
	 * @param processSynchronously Indicates whether the method should return only 
	 * once enabling this rule
	 * and moving existing items in the conversation is completely done.  
	 * If processSynchronously is false, the method returns immediately.
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
	 * @param processSynchronously Indicates whether the method should return only 
	 * once disabling this
	 * rule is completely done. If processSynchronously
	 *  is false, the method returns immediately.
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
	 * Deletes items in the specified conversation.
	 * Calling this method results in a call to EWS.
	 * @param contextFolderId The Id of the folder items must belong 
	 * to in order to be deleted. If contextFolderId is
	 * null, items across the entire mailbox are deleted.
	 * @param deleteMode The deletion mode.
	 * @throws Exception 
	 * @throws IndexOutOfBoundsException 
	 * @throws ServiceResponseException 
	 */
	public void deleteItems(FolderId contextFolderId,DeleteMode deleteMode) 
	throws ServiceResponseException, IndexOutOfBoundsException, Exception {
		HashMap<ConversationId, Date> m = new HashMap();
		m.put(this.getId(),this.getGlobalLastDeliveryTime());
		
		List f = new ArrayList<HashMap<ConversationId,Date>>();
		f.add(m);
		
		this.getService().deleteItemsInConversations(
				f,
				contextFolderId,
				deleteMode).getResponseAtIndex(0).throwIfNecessary();
	}


	/**
	 * Moves items in the specified conversation to a specific folder.
	 * Calling this method results in a call to EWS.
	 * @param contextFolderId The Id of the folder items must belong to 
	 * in order to be moved. If contextFolderId is null,
	 * items across the entire mailbox are moved.
	 * @param destinationFolderId The Id of the destination folder.
	 * @throws Exception 
	 * @throws IndexOutOfBoundsException 
	 * @throws ServiceResponseException 
	 */
	public void moveItemsInConversation(
			FolderId contextFolderId,
			FolderId destinationFolderId) throws ServiceResponseException, 
			IndexOutOfBoundsException, Exception {
		HashMap<ConversationId, Date> m = new HashMap();
		m.put(this.getId(),this.getGlobalLastDeliveryTime());
		
		List f = new ArrayList<HashMap<ConversationId,Date>>();
		f.add(m);
		
		this.getService().moveItemsInConversations(
				f,contextFolderId,destinationFolderId).
				getResponseAtIndex(0).throwIfNecessary();
	}

	/**
	 * Copies items in the specified conversation to a specific folder.
	 *  Calling this method results in a call to EWS.
	 * @param contextFolderId The Id of the folder items must belong to in 
	 * order to be copied. If contextFolderId
	 * is null, items across the entire mailbox are copied.
	 * @param destinationFolderId The Id of the destination folder.
	 * @throws Exception 
	 * @throws IndexOutOfBoundsException 
	 * @throws ServiceResponseException 
	 */
	public void copyItemsInConversation(
			FolderId contextFolderId,
			FolderId destinationFolderId) throws ServiceResponseException, 
			IndexOutOfBoundsException, Exception {
		HashMap<ConversationId, Date> m = new HashMap();
		m.put(this.getId(),this.getGlobalLastDeliveryTime());
		
		List f = new ArrayList<HashMap<ConversationId,Date>>();
		f.add(m);
		
		this.getService().copyItemsInConversations(
				f,contextFolderId, destinationFolderId).
				getResponseAtIndex(0).throwIfNecessary();
	}

	/**
	 * Sets the read state of items in the specified conversation. 
	 * Calling this method results in a call to EWS.
	 * @param contextFolderId The Id of the folder items must 
	 * belong to in order for their read state to
	 * be set. If contextFolderId is null, the read states of 
	 * items across the entire mailbox are set.
	 * @param isRead if set to <c>true</c>, conversation items are marked as read; 
	 * otherwise they are marked as unread.
	 * @throws Exception 
	 * @throws IndexOutOfBoundsException 
	 * @throws ServiceResponseException 
	 */
	public void setReadStateForItemsInConversation(
			FolderId contextFolderId,
			boolean isRead) throws ServiceResponseException,
			IndexOutOfBoundsException, Exception {
		HashMap<ConversationId, Date> m = new HashMap();
		m.put(this.getId(),this.getGlobalLastDeliveryTime());
		
		List f = new ArrayList<HashMap<ConversationId,Date>>();
		f.add(m);
		
		this.getService().setReadStateForItemsInConversations(
				f,contextFolderId,isRead).
				getResponseAtIndex(0).throwIfNecessary();
	}

	/**
	 * Gets the Id of this Conversation.
	 * @return Id
	 * @throws ServiceLocalException 
	 * @throws Exception 
	 */
	public ConversationId getId() throws ServiceLocalException {		
		return (ConversationId)this.getPropertyBag().
		getObjectFromPropertyDefinition(this.getIdPropertyDefinition());
		
	}

	/**
	 * Gets the topic of this Conversation.
	 * @return value
	 * @throws ArgumentException 
	 */
	public String getTopic() throws ArgumentException {		
		String returnValue = "";

		/**This property need not be present hence the
		 *  property bag may not contain it.
		 *Check for the presence of this property before accessing it.
		 */
		if (this.getPropertyBag().contains(ConversationSchema.Topic)){
			OutParam<String> out = new OutParam();       
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
	 * @return String
	 * @throws Exception 
	 */
	public StringList getUniqueRecipients() throws Exception {		
		return (StringList)this.getPropertyBag().
		getObjectFromPropertyDefinition(ConversationSchema.UniqueRecipients); 
	}

	/**
	 * Gets a list of all the people who have received 
	 * messages in this conversation across all folders in the mailbox.
	 * @return String
	 * @throws Exception 
	 */
	public StringList getGlobalUniqueRecipients() throws Exception {		
		return (StringList)this.getPropertyBag().
		getObjectFromPropertyDefinition(ConversationSchema.
				GlobalUniqueRecipients);
		
	}

	/**
	 * Gets a list of all the people who have sent messages
	 *  that are currently unread in this conversation in 
	 *  the current folder only.
	 * @return unreadSenders
	 * @throws ArgumentException 
	 */
	public StringList getUniqueUnreadSenders() throws ArgumentException {
		StringList unreadSenders = null;

		/**This property need not be present hence
		 *  the property bag may not contain it.
		 *Check for the presence of this property before accessing it.
		 */		
		if (this.getPropertyBag().contains(ConversationSchema.UniqueUnreadSenders)){
			OutParam<StringList> out = new OutParam();       
			this.getPropertyBag().tryGetPropertyType(StringList.class,
                    ConversationSchema.UniqueUnreadSenders,
                    out);
			unreadSenders = out.getParam();
		}

		return unreadSenders;
	}


	/**
	 * Gets a list of all the people who have sent
	 *  messages that are currently unread in this 
	 *  conversation across all folders in the mailbox.
	 * @return unreadSenders
	 * @throws ArgumentException 
	 */
	public StringList getGlobalUniqueUnreadSenders() throws ArgumentException {
		StringList unreadSenders = null;

		// This property need not be present hence 
		//the property bag may not contain it.
		// Check for the presence of this property before accessing it.		
		if (this.getPropertyBag().contains(ConversationSchema.GlobalUniqueUnreadSenders))
		{
			OutParam<StringList> out = new OutParam();       
			this.getPropertyBag().tryGetPropertyType(StringList.class,
                    ConversationSchema.GlobalUniqueUnreadSenders,
                    out);
			unreadSenders = out.getParam();
		}

		return unreadSenders;
	}

	/**
	 * Gets a list of all the people who have sent
	 *  messages in this conversation in the current folder only.
	 * @return String
	 * @throws Exception 
	 */
	public StringList getUniqueSenders() throws Exception {		
		return (StringList)this.getPropertyBag().
		getObjectFromPropertyDefinition(ConversationSchema.UniqueSenders);
		
	}

	/**
	 * Gets a list of all the people who have sent messages
	 *  in this conversation across all folders in the mailbox.
	 * @return String
	 * @throws Exception 
	 */
	public StringList getGlobalUniqueSenders() throws Exception {		
		return (StringList)this.getPropertyBag().
		getObjectFromPropertyDefinition(ConversationSchema.GlobalUniqueSenders);
	}

	/**
	 * Gets the delivery time of the message that was last 
	 * received in this conversation in the current folder only.
	 * @return Date
	 * @throws Exception 
	 */
	public Date getLastDeliveryTime() throws Exception {
		return (Date)this.getPropertyBag().
		getObjectFromPropertyDefinition(ConversationSchema.LastDeliveryTime);
		
	}

	/**
	 * Gets the delivery time of the message that was last 
	 * received in this conversation across all folders in the mailbox.
	 * @return Date
	 * @throws Exception 
	 */
	public Date getGlobalLastDeliveryTime() throws Exception {
		
		return (Date)this.getPropertyBag().
		getObjectFromPropertyDefinition(ConversationSchema.
				GlobalLastDeliveryTime);
		
	}

	/**
	 * Gets a list summarizing the categories stamped on 
	 * messages in this conversation, in the current folder only.
	 * @return value
	 * @throws ArgumentException 
	 */
	public StringList getCategories() throws ArgumentException {
		StringList returnValue = null;

		/**This property need not be present hence 
		 * the property bag may not contain it.
		 * Check for the presence of this property before accessing it.
		 */		
		if (this.getPropertyBag().contains(ConversationSchema.Categories)){
			OutParam<StringList> out = new OutParam();       
			this.getPropertyBag().tryGetPropertyType(StringList.class,
                    ConversationSchema.Categories,
                    out);
			returnValue = out.getParam();
		}
		return returnValue; 
	}

	/**
	 * Gets a list summarizing the categories stamped on 
	 * messages in this conversation, across all folders in the mailbox.
	 * @return returnValue
	 * @throws ArgumentException 
	 */
	public StringList getGlobalCategories() throws ArgumentException {
		StringList returnValue = null;

		// This property need not be present hence the 
		//property bag may not contain it.
		// Check for the presence of this property before accessing it.		
		if (this.getPropertyBag().contains(ConversationSchema.GlobalCategories)){
			OutParam<StringList> out = new OutParam();       
			this.getPropertyBag().tryGetPropertyType(StringList.class,
                    ConversationSchema.GlobalCategories,
                    out);
			returnValue = out.getParam();
		}
		return returnValue; 
	}

	/**
	 * Gets the flag status for this conversation, calculated
	 *  by aggregating individual messages flag status in the current folder.
	 * @return returnValue 
	 * @throws ArgumentException 
	 */	
	public ConversationFlagStatus getFlagStatus() throws ArgumentException {
		ConversationFlagStatus returnValue = ConversationFlagStatus.NotFlagged;

		// This property need not be present hence the
		//property bag may not contain it.
		// Check for the presence of this property before accessing it.
		if (this.getPropertyBag().contains(ConversationSchema.FlagStatus)){
			OutParam<ConversationFlagStatus> out = new OutParam();       
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
	 * individual messages flag status across all folders in the mailbox.
	 * @return returnValue
	 * @throws ArgumentException 
	 */	
	public ConversationFlagStatus getGlobalFlagStatus() 
	throws ArgumentException {
		ConversationFlagStatus returnValue = ConversationFlagStatus.NotFlagged;

		// This property need not be present hence the
		//property bag may not contain it.
		// Check for the presence of this property before accessing it.
		if (this.getPropertyBag().contains(ConversationSchema.GlobalFlagStatus)){	
			OutParam<ConversationFlagStatus> out = new OutParam();       
			this.getPropertyBag().tryGetPropertyType(
					ConversationFlagStatus.class,
                    ConversationSchema.GlobalFlagStatus,
                    out);
			returnValue = out.getParam();
		}

		return returnValue;
	}

	/**
	 *  Gets a value indicating if at least one message in this 
	 *  conversation, in the current folder only, has an attachment.
	 * @return Value
	 * @throws ServiceLocalException 
	 */
	public boolean getHasAttachments() throws ServiceLocalException {		
		return ((Boolean)this.getPropertyBag().
				getObjectFromPropertyDefinition(
				ConversationSchema.HasAttachments)).booleanValue();
	}

	/**
	 * Gets a value indicating if at least one message 
	 * in this conversation, across all folders in the mailbox,
	 *  has an attachment.
	 * @return boolean
	 * @throws ServiceLocalException 
	 */
	public boolean getGlobalHasAttachments() throws ServiceLocalException {		
		return ((Boolean)this.getPropertyBag().
				getObjectFromPropertyDefinition(
				ConversationSchema.GlobalHasAttachments)).booleanValue();
		
	}

	/**
	 * Gets the total number of messages in this conversation 
	 * in the current folder only.
	 * @return integer
	 * @throws ServiceLocalException 
	 */
	public int getMessageCount() throws ServiceLocalException {		
		return ((Integer)this.getPropertyBag().
				getObjectFromPropertyDefinition(
				ConversationSchema.MessageCount)).intValue();
	}

	/**
	 * Gets the total number of messages in this 
	 * conversation across all folders in the mailbox.
	 * @return integer
	 * @throws ServiceLocalException 
	 */
	public int getGlobalMessageCount() throws ServiceLocalException {
		
		return ((Integer)this.getPropertyBag().
				getObjectFromPropertyDefinition(
				ConversationSchema.GlobalMessageCount)).intValue();
		
	}

	/**
	 * Gets the total number of unread messages in this 
	 * conversation in the current folder only.
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
			OutParam<Integer> out = new OutParam();       
			this.getPropertyBag().tryGetPropertyType(Integer.class,
                    ConversationSchema.UnreadCount,
                    out);
			returnValue = out.getParam().intValue();
		}

		return returnValue;
	}

	/**
	 * Gets the total number of unread messages in this
	 *  conversation across all folders in the mailbox.
	 * @return returnValue
	 * @throws ArgumentException 
	 */
	public int getGlobalUnreadCount() throws ArgumentException {
		int returnValue = 0;

		if (this.getPropertyBag().contains(ConversationSchema.GlobalUnreadCount)) {
			OutParam<Integer> out = new OutParam();       
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
	 * @return integer
	 * @throws ServiceLocalException 
	 */
	public int getSize() throws ServiceLocalException {		
		return ((Integer)this.getPropertyBag().
				getObjectFromPropertyDefinition(
				ConversationSchema.Size)).intValue();
	}

	/**
	 * Gets the size of this conversation, calculated by 
	 * adding the sizes of all messages in the conversation 
	 * across all folders in the mailbox.
	 * @return integer
	 * @throws ServiceLocalException 
	 */
	public int getGlobalSize() throws ServiceLocalException {		
		return ((Integer)this.getPropertyBag().
				getObjectFromPropertyDefinition(
				ConversationSchema.GlobalSize)).intValue();
	}

	/**
	 * Gets a list summarizing the classes of the items 
	 * in this conversation, in the current folder only.
	 * @return string
	 * @throws Exception 
	 */
	public StringList getItemClasses() throws Exception {		
		return (StringList)this.getPropertyBag().
				getObjectFromPropertyDefinition(
				ConversationSchema.ItemClasses);
	}

	/**
	 * Gets a list summarizing the classes of the items 
	 * in this conversation, across all folders in the mailbox.
	 * @return string
	 * @throws Exception 
	 */
	public StringList getGlobalItemClasses() throws Exception {
		return (StringList)this.getPropertyBag().
				getObjectFromPropertyDefinition(
				ConversationSchema.GlobalItemClasses);
		
	}

	/**
	 * Gets the importance of this conversation, calculated by 
	 * aggregating individual messages importance in the current folder only.
	 * @return important
	 * @throws Exception 
	 */
	public Importance getImportance() throws Exception {		
		return (Importance)this.getPropertyBag().
				getObjectFromPropertyDefinition(
				ConversationSchema.Importance); 
	}

	/**
	 * Gets the importance of this conversation, calculated by 
	 * aggregating individual messages importance across all
	 *  folders in the mailbox.
	 * @return important
	 * @throws Exception 
	 */
	public Importance getGlobalImportance() throws Exception {		
		return (Importance)this.getPropertyBag().
				getObjectFromPropertyDefinition(
				ConversationSchema.GlobalImportance);
	}

	/**
	 * Gets the Ids of the messages in this conversation, 
	 * in the current folder only.
	 * @return Id
	 * @throws Exception 
	 */
	public ItemIdCollection getItemIds() throws Exception {
		return (ItemIdCollection)this.getPropertyBag().
				getObjectFromPropertyDefinition(
				ConversationSchema.ItemIds);		
	}

	/**
	 * Gets the Ids of the messages in this conversation, 
	 * across all folders in the mailbox. 
	 * @return Id
	 * @throws Exception 
	 */
	public ItemIdCollection getGlobalItemIds() throws Exception {
		return (ItemIdCollection)this.getPropertyBag().
				getObjectFromPropertyDefinition(
				ConversationSchema.GlobalItemIds); 
	}

}