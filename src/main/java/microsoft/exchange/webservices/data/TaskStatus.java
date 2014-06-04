/**************************************************************************
 * copyright file="TaskStatus.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the TaskStatus.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Defines the execution status of a task.
 */
public enum TaskStatus {

	// The execution of the task is not started.
	/** The Not started. */
	NotStarted,

	// The execution of the task is in progress.
	/** The In progress. */
	InProgress,

	// The execution of the task is completed.
	/** The Completed. */
	Completed,

	// The execution of the task is waiting on others.
	/** The Waiting on others. */
	WaitingOnOthers,

	// The execution of the task is deferred.
	/** The Deferred. */
	Deferred

}
