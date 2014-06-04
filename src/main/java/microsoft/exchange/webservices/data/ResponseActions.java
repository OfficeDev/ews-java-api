/**************************************************************************
 * copyright file="ResponseActions.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ResponseActions.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Defines the response actions that can be taken on an item.
 */
@Flags
public enum ResponseActions {

	// No action can be taken.
	/** The None. */
	None(0),

	// The item can be accepted.
	/** The Accept. */
	Accept(1),

	// The item can be tentatively accepted.
	/** The Tentatively accept. */
	TentativelyAccept(2),

	// The item can be declined.
	/** The Decline. */
	Decline(4),

	// The item can be replied to.

	/** The Reply. */
	Reply(8),

	// The item can be replied to.
	/** The Reply all. */
	ReplyAll(16),

	// The item can be forwarded.
	/** The Forward. */
	Forward(32),

	// The item can be cancelled.
	/** The Cancel. */
	Cancel(64),

	// The item can be removed from the calendar.
	/** The Remove from calendar. */
	RemoveFromCalendar(128),

	// The item's read receipt can be suppressed.
	/** The Suppress read receipt. */
	SuppressReadReceipt(256),

	// A reply to the item can be posted.
	/** The Post reply. */
	PostReply(512);

	/** The response act. */
	@SuppressWarnings("unused")
	private final int responseAct;

	/**
	 * Instantiates a new response actions.
	 * 
	 * @param responseAct
	 *            the response act
	 */
	ResponseActions(int responseAct) {
		this.responseAct = responseAct;
	}
}
