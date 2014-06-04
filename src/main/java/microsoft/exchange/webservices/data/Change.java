/**************************************************************************
 * copyright file="Change.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the Change.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents a change as returned by a synchronization operation.
 */
@EditorBrowsable(state = EditorBrowsableState.Never)
public abstract class Change {

	/**
	 * The type of change.
	 */
	private ChangeType changeType;

	/**
	 * The service object the change applies to.
	 */
	private ServiceObject serviceObject;

	/**
	 * The Id of the service object the change applies to.
	 */
	private ServiceId id;

	/**
	 * Initializes a new instance of Change.
	 */
	protected Change() {
	}

	/**
	 * Initializes a new instance of Change.
	 * 
	 * @return the service id
	 */
	protected abstract ServiceId createId();

	/**
	 * Gets the type of the change.
	 * 
	 * @return the change type
	 */
	public ChangeType getChangeType() {
		return this.changeType;
	}

	/**
	 * sets the type of the change.
	 * 
	 * @param changeType
	 *            the new change type
	 */
	protected void setChangeType(ChangeType changeType) {
		this.changeType = changeType;
	}

	/**
	 * Gets  the service object the change applies to.
	 * 
	 * @return the service object
	 */
	protected ServiceObject getServiceObject() {
		return this.serviceObject;
	}

	/**
	 * Sets the service object.
	 * 
	 * @param serviceObject
	 *            the new service object
	 */
	protected void setServiceObject(ServiceObject serviceObject) {
		this.serviceObject = serviceObject;
	}

	/**
	 * Gets the Id of the service object the change applies to.
	 * 
	 * @return the id
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	protected ServiceId getId() throws ServiceLocalException {
		return this.getServiceObject() != null ? this.getServiceObject()
				.getId() : this.id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id
	 *            the new id
	 */
	protected void setId(ServiceId id) {
		this.id = id;
	}
}
