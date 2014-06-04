/**************************************************************************
 * copyright file="ConversationId.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ConversationId.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents the Id of a Conversation.
 */
public class ConversationId extends ServiceId {

	/**
	 * Initializes a new instance of the ConversationId class.
	 */
	ConversationId() {
		super();
	}

	/**
	 * Defines an implicit conversion between string and ConversationId.
	 * 
	 * @param uniqueId
	 *            the unique id
	 * @return A ConversationId initialized with the specified unique Id.
	 * @throws Exception
	 *             the exception
	 */
	public static ConversationId getConversationIdFromUniqueId(String uniqueId)
			throws Exception {
		return new ConversationId(uniqueId);
	}

	/**
	 * Defines an implicit conversion between ConversationId and String.
	 * 
	 * @param conversationId
	 *            the conversation id
	 * @return A ConversationId initialized with the specified unique Id.
	 * @throws ArgumentNullException
	 *             the argument null exception
	 */
	public static String getStringFromConversationId(
			ConversationId conversationId) throws ArgumentNullException {
		if (conversationId == null) {
			throw new ArgumentNullException("conversationId");
		}

		if (null == conversationId.getUniqueId()
				|| conversationId.getUniqueId().isEmpty()) {
			return "";
		} else {
			// Ignoring the change key info
			return conversationId.getUniqueId();
		}
	}

	/**
	 * Gets the name of the XML element.
	 * 
	 * @return XML element name.
	 */
	@Override
	protected String getXmlElementName() {
		return XmlElementNames.ConversationId;
	}

	/**
	 * Initializes a new instance of ConversationId.
	 * 
	 * @param uniqueId
	 *            the unique id
	 * @throws Exception
	 *             the exception
	 */
	public ConversationId(String uniqueId) throws Exception {
		super(uniqueId);
	}

	/**
	 * Gets a string representation of the Conversation Id.
	 * 
	 * @return The string representation of the conversation id.
	 */
	@Override
	public String toString() {
		// We have ignored the change key portion
		return this.getUniqueId();
	}
}
