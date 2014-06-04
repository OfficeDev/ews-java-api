/**************************************************************************
 * copyright file="FlaggedForAction.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the FlaggedForAction.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Defines the follow-up actions that may be stamped on a message.
 */
public enum FlaggedForAction {
	
	/**
	 * The message is flagged with any action.
	 */
    Any,

    /**
     * The recipient is requested to call the sender.
     */
    Call,

    /**
     * The recipient is requested not to forward the message.
     */
    DoNotForward,

    /**
     * The recipient is requested to follow up on the message.
     */
    FollowUp,

    /** 
     * The recipient received the message for information.
     */
    FYI,

    /**
     * The recipient is requested to forward the message.
     */
    Forward,

    /**
     * The recipient is informed that a response to the message is not required.
     */
    NoResponseNecessary,

    /**
     * The recipient is requested to read the message.
     */
    Read,

    /**
     * The recipient is requested to reply to the sender of the message.
     */
    Reply,

    /**
     * The recipient is requested to reply to everyone the message was sent to.
     */
    ReplyToAll,

    /**
     * The recipient is requested to review the message.
     */
    Review

}
