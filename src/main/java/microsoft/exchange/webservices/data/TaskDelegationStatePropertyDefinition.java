/**************************************************************************
 * copyright file="TaskDelegationStatePropertyDefinition.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the TaskDelegationStatePropertyDefinition.java.
 **************************************************************************/

package microsoft.exchange.webservices.data;

import java.util.EnumSet;

/**
 * Represents a task delegation property definition.
 */
final class TaskDelegationStatePropertyDefinition extends 
GenericPropertyDefinition<TaskDelegationState> {
	/** The No match. */
	private static final String NoMatch = "NoMatch";

	/** The Own new. */
	private static final String OwnNew = "OwnNew";

	/** The Owned. */
	private static final String Owned = "Owned";

	/** The Accepted. */
	private static final String Accepted = "Accepted";

	/**
	 * Initializes a new instance of the "TaskDelegationStatePropertyDefinition"
	 * class.
	 * 
	 * @param xmlElementName
	 *            Name of the XML element.
	 * @param uri
	 *            The URI.
	 * @param flags
	 *            The flags.
	 * @param version
	 *            The version.
	 */
	protected TaskDelegationStatePropertyDefinition(String xmlElementName,
			String uri, EnumSet<PropertyDefinitionFlags> flags,
			ExchangeVersion version) {
		super(TaskDelegationState.class, xmlElementName, uri, flags, version);
	}

	/**
	 * The Enum Status.
	 */
	public enum Status {

		/** The No match. */
		NoMatch,
		/** The Own new. */
		OwnNew,
		/** The Owned. */
		Owned,
		/** The Accepted. */
		Accepted;
	}

	/***
	 * Parses the specified value.
	 * 
	 * @param value
	 *            The value.
	 * @return Typed value.
	 */
	@Override
	protected Object parse(String value) {
		switch (Status.valueOf(value)) {
		case NoMatch:
			return TaskDelegationState.NoDelegation;
		case OwnNew:
			return TaskDelegationState.Unknown;
		case Owned:
			return TaskDelegationState.Accepted;
		case Accepted:
			return TaskDelegationState.Declined;
		default:
			EwsUtilities.EwsAssert(false,
					"TaskDelegationStatePropertyDefinition.Parse", String
							.format("TaskDelegationStatePropertyDefinition." +
									 "Parse():" +
									 " value %s cannot be handled.", value));

			return null; // To keep the compiler happy
		}
	}

	/***
	 * Convert instance to string.
	 * 
	 * @param value
	 *            The value.
	 * @return String representation of property value.
	 */
	@Override
	protected String toString(Object value) {
		TaskDelegationState taskDelegationState = (TaskDelegationState)value;

		if (taskDelegationState.equals(TaskDelegationState.NoDelegation)) {
			return NoMatch;
		} else if (taskDelegationState.equals(TaskDelegationState.Unknown)) {
			return OwnNew;
		} else if (taskDelegationState.equals(TaskDelegationState.Accepted)) {
			return Owned;
		}
		if (taskDelegationState.equals(TaskDelegationState.Declined)) {
			return Accepted;
		} else {
			EwsUtilities.EwsAssert(false,
					"TaskDelegationStatePropertyDefinition.ToString",
					"Invalid TaskDelegationState value.");
			return null; // To keep the compiler happy
		}

	}

}
