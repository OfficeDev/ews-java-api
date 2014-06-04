/**************************************************************************
 * copyright file="ConversationActionType.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ConversationActionType.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Defines actions applicable to Conversation.
 */
public enum ConversationActionType {
	
	/**
	 * Categorizes every current and future message in the conversation
	 */
    AlwaysCategorize,

    /**
     * Deletes every current and future message in the conversation
     */
    AlwaysDelete,

    /**
     * Moves every current and future message in the conversation
     */
    AlwaysMove,

    /**
     * Deletes current item in context folder in the conversation
     */
    Delete,

    /**
     * Moves current item in context folder in the conversation
     */
    Move,

    /**
     * Copies current item in context folder in the conversation
     */
    Copy,

    /**
     * Marks current item in context folder in the conversation with
     * provided read state
     */
    SetReadState,

}
