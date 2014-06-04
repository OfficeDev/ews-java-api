/**************************************************************************
 * copyright file="ConversationAction.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ConversationAction.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.Date;

/**
 * ConversationAction class that represents 
 * ConversationActionType in the request XML.
 * This class really is meant for representing 
 * single ConversationAction that needs to
 * be taken on a conversation.
 */
class ConversationAction {

	private ConversationActionType action;
	private ConversationId conversationId;
	private boolean processRightAway;

	private boolean enableAlwaysDelete;
	private StringList categories;
	private FolderIdWrapper moveFolderId;
	private FolderIdWrapper contextFolderId;
	private DeleteMode deleteType;
	private Boolean isRead;
	private Date conversationLastSyncTime;

	/**
	 * Gets or sets conversation action
	 * @return action
	 */
	protected ConversationActionType getAction() {
		return this.action;
	}

	/**
	 * Sets or sets conversation action
	 */
	protected void setAction(ConversationActionType value ) {
		this.action = value;
	}

	/**
	 * Gets or sets conversation id
	 * @return conversationId
	 */
	protected ConversationId getConversationId() {
		return this.conversationId;
	}

	/**
	 * Sets or sets conversation id
	 */
	protected void setConversationId(ConversationId value) {
		this.conversationId = value;
	}

	/**
	 * Gets or sets ProcessRightAway
	 * @return processRightAway
	 */
	protected boolean getProcessRightAway() {
		return this.processRightAway;
	}

	/**
	 * Sets or sets ProcessRightAway
	 * @return processRightAway
	 */
	protected void setProcessRightAway(boolean value) {
		this.processRightAway = value;
	}


	/**
	 * Gets or set conversation categories for Always Categorize action
	 * @return categories
	 */
	protected StringList getCategories() {
		return this.categories;
	}

	/**
	 * Sets or set conversation categories for Always Categorize actions
	 */
	protected void setCategories(StringList value) {
		this.categories = value;
	}

	/**
	 * Gets or sets Enable Always Delete value for Always Delete action
	 * @return enableAlwaysDelete
	 */
	protected boolean getEnableAlwaysDelete() {
		return this.enableAlwaysDelete;
	}

	/**
	 * Sets or sets Enable Always Delete value for Always Delete action
	 */
	protected void setEnableAlwaysDelete(boolean value) {
		this.enableAlwaysDelete = value;
	}

	/**
	 * IsRead
	 * @return isRead
	 */
	protected Boolean getIsRead() { 
		return this.isRead; 
	}

	/**
	 * IsRead
	 */
	protected void setIsRead(Boolean value) {
		this.isRead = value;
	}

	/**
	 * DeleteType
	 * @return deleteType
	 */
	protected DeleteMode getDeleteType() {
		return this.deleteType; 
	}

	/**
	 * DeleteType
	 */
	protected void setDeleteType(DeleteMode value) { 
		this.deleteType = value; 
	}

	/**
	 * ConversationLastSyncTime is used in one
	 *  time action to determine the items
	 * on which to take the action.
	 * @return conversationLastSyncTime
	 */
	protected Date getConversationLastSyncTime() {
		return this.conversationLastSyncTime;
	}

	/**
	 * ConversationLastSyncTime is used in 
	 * one time action to determine the items
	 * on which to take the action.
	 */
	protected void setConversationLastSyncTime(Date value) { 
		this.conversationLastSyncTime = value; 
	}

	/**
	 * Gets or sets folder id ContextFolder
	 * @return contextFolderId
	 */
	protected FolderIdWrapper getContextFolderId() {
		return this.contextFolderId;
	}

	/**
	 * Sets or sets folder id ContextFolder
	 */
	protected void setContextFolderId(FolderIdWrapper value) {
		this.contextFolderId = value;
	}

	/**
	 * Gets or sets folder id for Move action
	 * @return moveFolderId
	 */
	protected FolderIdWrapper getDestinationFolderId() {
		return this.moveFolderId;
	}

	/**
	 * Sets or sets folder id for Move action
	 */
	protected void setDestinationFolderId(FolderIdWrapper value) {
		this.moveFolderId = value;
	}

	/**
	 * Gets the name of the XML element.
	 * @return XML element name.
	 */
	protected String getXmlElementName() {
		return XmlElementNames.ApplyConversationAction;
	}

	/**
	 *  Validate request.
	 * @throws Exception 
	 */
	protected void validate() throws Exception {
		EwsUtilities.validateParam(this.conversationId, "conversationId");
	}

	/**
	 * Writes XML elements.
	 * @param writer The writer.
	 * @throws Exception 
	 */
	protected void writeElementsToXml(EwsServiceXmlWriter writer) 
	throws Exception {
		writer.writeStartElement(
				XmlNamespace.Types,
				XmlElementNames.ConversationAction);
		try {
			String actionValue = null;
			if(this.getAction() == ConversationActionType.AlwaysCategorize) {
				actionValue = XmlElementNames.AlwaysCategorize;
			}
			else if(this.getAction() == ConversationActionType.AlwaysDelete) {
				actionValue = XmlElementNames.AlwaysDelete;
			}	
			else if(this.getAction() == ConversationActionType.AlwaysMove) {
				actionValue = XmlElementNames.AlwaysMove;
			}
			else if(this.getAction() == ConversationActionType.Delete) {
				actionValue = XmlElementNames.Delete;
			}
			else if(this.getAction() == ConversationActionType.Copy) {
				actionValue = XmlElementNames.Copy;
			}
			else if(this.getAction() == ConversationActionType.Move) {
				actionValue = XmlElementNames.Move;
			}
			else if(this.getAction() == ConversationActionType.SetReadState) {
				actionValue = XmlElementNames.SetReadState;
			}
			else {
				throw new ArgumentException("ConversationAction");
			}

			// Emit the action element
			writer.writeElementValue(
					XmlNamespace.Types,
					XmlElementNames.Action,
					actionValue);

			// Emit the conversation id element
			this.getConversationId().writeToXml(
					writer,
					XmlNamespace.Types,
					XmlElementNames.ConversationId);

			if (this.getAction() == ConversationActionType.AlwaysCategorize ||
					this.getAction() == ConversationActionType.AlwaysDelete ||
					this.getAction() == ConversationActionType.AlwaysMove) {
				// Emit the ProcessRightAway element
				writer.writeElementValue(
						XmlNamespace.Types,
						XmlElementNames.ProcessRightAway,
						EwsUtilities.boolToXSBool(this.getProcessRightAway()));
			}

			if (this.getAction() == ConversationActionType.AlwaysCategorize) {
				// Emit the categories element
				if (this.getCategories() != null && this.getCategories().getSize() > 0) {
					this.getCategories().writeToXml(
							writer,
							XmlNamespace.Types,
							XmlElementNames.Categories);
				}
			}
			else if (this.getAction() == ConversationActionType.AlwaysDelete) {
				// Emit the EnableAlwaysDelete element
				writer.writeElementValue(
						XmlNamespace.Types,
						XmlElementNames.EnableAlwaysDelete,
						EwsUtilities.boolToXSBool(this.
								getEnableAlwaysDelete()));
			}
			else if (this.getAction() == ConversationActionType.AlwaysMove) {
				// Emit the Move Folder Id
				if (this.getDestinationFolderId() != null) {
					writer.writeStartElement(XmlNamespace.Types, 
							XmlElementNames.DestinationFolderId);
					this.getDestinationFolderId().writeToXml(writer);
					writer.writeEndElement();
				}
			}
			else {
				if (this.getContextFolderId() != null) {
					writer.writeStartElement(
							XmlNamespace.Types,
							XmlElementNames.ContextFolderId);

					this.getContextFolderId().writeToXml(writer);

					writer.writeEndElement();
				}

				if (this.getConversationLastSyncTime()!=null) {
					writer.writeElementValue(
							XmlNamespace.Types,
							XmlElementNames.ConversationLastSyncTime,
							this.getConversationLastSyncTime());
				}

				if (this.getAction() == ConversationActionType.Copy) {
					EwsUtilities.EwsAssert(
							this.getDestinationFolderId() != null,
							"ApplyconversationActionRequest",
					"DestinationFolderId should be set " +
					"when performing copy action");

					writer.writeStartElement(
							XmlNamespace.Types,
							XmlElementNames.DestinationFolderId);
					this.getDestinationFolderId().writeToXml(writer);
					writer.writeEndElement();
				}
				else if (this.getAction() == ConversationActionType.Move) {
					EwsUtilities.EwsAssert(
							this.getDestinationFolderId() != null,
							"ApplyconversationActionRequest",
					"DestinationFolderId should be " +
					"set when performing move action");

					writer.writeStartElement(
							XmlNamespace.Types,
							XmlElementNames.DestinationFolderId);
					this.getDestinationFolderId().writeToXml(writer);
					writer.writeEndElement();
				}
				else if (this.getAction() == ConversationActionType.Delete) {
					EwsUtilities.EwsAssert(
							this.getDeleteType()!=null,
							"ApplyconversationActionRequest",
					"DeleteType should be specified " +
					"when deleting a conversation.");

					writer.writeElementValue(
							XmlNamespace.Types,
							XmlElementNames.DeleteType,
							this.getDeleteType());
				}
				else if (this.getAction() == ConversationActionType.SetReadState) {
					EwsUtilities.EwsAssert(
							this.getIsRead()!=null,
							"ApplyconversationActionRequest",
					"IsRead should be specified when " +
					"marking/unmarking a conversation as read.");

					writer.writeElementValue(
							XmlNamespace.Types,
							XmlElementNames.IsRead,
							this.getIsRead());
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			writer.writeEndElement();
		}
	}

}
