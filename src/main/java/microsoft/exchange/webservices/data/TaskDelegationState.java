/**************************************************************************
 * copyright file="TaskDelegationState.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the TaskDelegationState.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * This maps to the bogus TaskDelegationState in the EWS schema.
 *  The schema enum has 6 values, but XSO should never return anything but
 *  values between 0 and 3, so we should be safe without mappings for
 *  EWS's Declined and Max values
 */
/**
 * Defines the delegation state of a task.
 */
public enum TaskDelegationState {

	// The task is not delegated
	/** The No delegation. */
	NoDelegation, // Maps to NoMatch

	// The task's delegation state is unknown.
	/** The Unknown. */
	Unknown, // Maps to OwnNew

	// The task was delegated and the delegation was accepted.
	/** The Accepted. */
	Accepted, // Maps to Owned

	// The task was delegated but the delegation was declined.
	/** The Declined. */
	Declined
	// Maps to Accepted

	// The original Declined value has no mapping
	// The original Max value has no mapping

}
