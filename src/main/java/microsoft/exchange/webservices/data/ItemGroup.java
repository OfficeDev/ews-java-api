/**************************************************************************
 * copyright file="ItemGroup.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ItemGroup.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Represents a group of items as returned by grouped item search operations.
 * 
 * @param <TItem>
 *            the generic type
 */
public final class ItemGroup<TItem extends Item> {

	/** The group index. */
	private String groupIndex;

	/** The items. */
	private Collection<TItem> items;

	/**
	 * Initializes a new instance of the class.
	 * 
	 * @param groupIndex
	 *            the group index
	 * @param items
	 *            the items
	 */
	protected ItemGroup(String groupIndex, List<TItem> items) {
		EwsUtilities.EwsAssert(groupIndex != null, "ItemGroup.ctor",
		"groupIndex is null");
		EwsUtilities
		.EwsAssert(items != null, "ItemGroup.ctor", "items is null");

		this.groupIndex = groupIndex;
		this.items = new ArrayList<TItem>(items);
	}

	/**
	 * Gets an index identifying the group.
	 * 
	 * @return the group index
	 */
	public String getGroupIndex() {
		return this.groupIndex;
	}

	/**
	 * Sets an index identifying the group.	 
	 */   
	private void setGroupIndex(String value) {
		this.groupIndex = value;	
	}

	/**
	 * Gets a collection of the items in this group.
	 * 
	 * @return the items
	 */
	public Collection<TItem> getItems() {
		return this.items;
	}

	/**
	 * Sets a collection of the items in this group.
	 * 
	 * @return the items
	 */	
	private void setItems(Collection<TItem> value) {
		this.items = value;
	}
}
