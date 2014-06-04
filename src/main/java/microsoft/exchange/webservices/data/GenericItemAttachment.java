/**************************************************************************
 * copyright file="GenericItemAttachment.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the GenericItemAttachment.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents a strongly typed item attachment.
 * 
 * @param <TItem>
 *            Item type.
 */
public final class GenericItemAttachment<TItem extends Item> extends
		ItemAttachment {

	/**
	 * * Initializes a new instance of the GenericItemAttachment class.
	 * 
	 * @param owner
	 *            the owner
	 */
	protected GenericItemAttachment(Item owner) {
		super(owner);
	}

	/**
	 * * Gets the item associated with the attachment.
	 * 
	 * @return the t item
	 */
	public TItem getTItem() {
		return (TItem)super.getItem();
	}

	/**
	 * Sets the t item.
	 * 
	 * @param value
	 *            the new t item
	 */
	protected void setTItem(TItem value) {
		super.setItem(value);
	}
}
