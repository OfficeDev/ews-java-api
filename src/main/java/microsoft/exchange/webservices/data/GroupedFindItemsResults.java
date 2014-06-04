/**************************************************************************
 * copyright file="GroupedFindItemsResults.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the GroupedFindItemsResults.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 
 *Represents the results of an item search operation.
 * 
 * @param <TItem>
 *            The type of item returned by the search operation.
 */
public final class GroupedFindItemsResults<TItem extends Item> implements
		Iterable<ItemGroup<TItem>> {

	/** The total count. */
	private int totalCount;

	/** The next page offset. */
	private Integer nextPageOffset;

	/** The more available. */
	private boolean moreAvailable;

	/**
	 * List of ItemGroups.
	 */
	private ArrayList<ItemGroup<TItem>> itemGroups = 
		new ArrayList<ItemGroup<TItem>>();

	/**
	 * Initializes a new instance of the GroupedFindItemsResults class.
	 */
	protected GroupedFindItemsResults() {
	}

	/**
	 * Gets the total number of items matching the search criteria available in
	 * the searched folder.
	 * 
	 * @return the total count
	 */
	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * Gets the total number of items matching the search criteria available in
	 * the searched folder.
	 * 
	 * @param totalCount
	 *            Total number of items
	 * 
	 */
	protected void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * Gets the offset that should be used with ItemView to retrieve the next
	 * page of items in a FindItems operation.
	 * 
	 * @return the next page offset
	 */
	public Integer getNextPageOffset() {
		return nextPageOffset;
	}

	/**
	 * Sets the offset that should be used with ItemView to retrieve the next
	 * page of items in a FindItems operation.
	 * 
	 * @param nextPageOffset
	 *            the new next page offset
	 */
	protected void setNextPageOffset(Integer nextPageOffset) {
		this.nextPageOffset = nextPageOffset;
	}

	/**
	 * Gets a value indicating whether more items corresponding to the search
	 * criteria are available in the searched folder.
	 * 
	 * @return true, if is more available
	 */
	public boolean isMoreAvailable() {
		return moreAvailable;
	}

	/**
	 * Sets a value indicating whether more items corresponding to the search
	 * criteria are available in the searched folder.
	 * 
	 * @param moreAvailable
	 *            the new more available
	 */
	protected void setMoreAvailable(boolean moreAvailable) {
		this.moreAvailable = moreAvailable;
	}

	/**
	 * Gets the item groups returned by the search operation.
	 * 
	 * @return the item groups
	 */
	public ArrayList<ItemGroup<TItem>> getItemGroups() {
		return itemGroups;
	}

	/**
	 * Returns an iterator that iterates through the collection.
	 * 
	 * @return the iterator
	 */
	@Override
	public Iterator<ItemGroup<TItem>> iterator() {
		return this.itemGroups.iterator();
	}

}
