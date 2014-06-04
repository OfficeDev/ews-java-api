/**************************************************************************
 * copyright file="ItemChange.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ItemChange.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/***
 * Represents a change on an item as returned by a synchronization operation.
 */
public final class ItemChange extends Change {

	/** The is read. */
	private boolean isRead;

	/***
	 * Initializes a new instance of ItemChange.
	 */
	protected ItemChange() {
		super();
	}

	/***
	 * Creates an ItemId instance.
	 * 
	 * @return A ItemId.
	 */
	@Override
	protected ServiceId createId() {
		return new ItemId();
	}

	/**
	 * * Gets the item the change applies to. Item is null when ChangeType is
	 * equal to either ChangeType.Delete or ChangeType.ReadFlagChange. In those
	 * cases, use the ItemId property to retrieve the Id of the item that was
	 * deleted or whose IsRead property changed.
	 * 
	 * @return the item
	 */
	public Item getItem() {
		return (Item)this.getServiceObject();
	}

	/**
	 * * Gets the IsRead property for the item that the change applies to.
	 * IsRead is only valid when ChangeType is equal to
	 * ChangeType.ReadFlagChange.
	 * 
	 * @return the checks if is read
	 */
	public boolean getIsRead() {
		return this.isRead;
	}

	/**
	 * Sets the checks if is read.
	 * 
	 * @param isRead
	 *            the new checks if is read
	 */
	protected void setIsRead(boolean isRead) {
		this.isRead = isRead;
	}

	/**
	 * * Gets the Id of the item the change applies to.
	 * 
	 * @return the item id
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public ItemId getItemId() throws ServiceLocalException {
		return (ItemId) this.getId();
	}

}
