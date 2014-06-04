/**************************************************************************
 * copyright file="TaskMode.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the TaskMode.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Defines the modes of a Task.
 */
public enum TaskMode {

	// The task is normal
	/** The Normal. */
	Normal(0),

	// The task is a task assignment request
	/** The Request. */
	Request(1),

	// The task assignment request was accepted
	/** The Request accepted. */
	RequestAccepted(2),

	// The task assignment request was declined
	/** The Request declined. */
	RequestDeclined(3),

	// The task has been updated
	/** The Update. */
	Update(4),

	// The task is self delegated
	/** The Self delegated. */
	SelfDelegated(5);

	/** The task mode. */
	@SuppressWarnings("unused")
	private final int taskMode;

	/**
	 * Instantiates a new task mode.
	 * 
	 * @param taskMode
	 *            the task mode
	 */
	TaskMode(int taskMode) {
		this.taskMode = taskMode;
	}
}
