/**************************************************************************
 * copyright file="SimplePropertyBag.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the SimplePropertyBag.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

//IEnumerable<KeyValuePair<TKey, object>>
/**
 * Represents a simple property bag.
 * 
 * @param <TKey>
 *            The type of key
 */
class SimplePropertyBag<TKey> implements Iterable<HashMap<TKey, Object>> {

	/** The items. */
	private Map<TKey, Object> items = new HashMap<TKey, Object>();

	/** The removed items. */
	private List<TKey> removedItems = new ArrayList<TKey>();

	/** The added items. */
	private List<TKey> addedItems = new ArrayList<TKey>();

	/** The modified items. */
	private List<TKey> modifiedItems = new ArrayList<TKey>();

	/**
	 * * Add item to change list.
	 * 
	 * @param key
	 *            the key
	 * @param changeList
	 *            the change list
	 */
	private void internalAddItemToChangeList(TKey key, List changeList) {
		if (!changeList.contains(key)) {
			changeList.add(key);
		}
	}
	
	/***
	 * Triggers dispatch of the change event.
	 */
	private void changed() {
		if (!onChange.isEmpty()) {
			for (IPropertyBagChangedDelegate change : onChange) {
				change.propertyBagChanged(this);
			}
		}
	}

	/**
	 * * Remove item.
	 * 
	 * @param key
	 *            the key
	 */
	private void internalRemoveItem(TKey key) {
		OutParam value = new OutParam();
		if (this.tryGetValue(key, value)) {
			this.items.remove(key);
			this.removedItems.add(key);
			this.changed();
		}
	}
	

	/**
	 * * Gets the added items. <value>The added items.</value>
	 * 
	 * @return the added items
	 */
	protected Iterable<TKey> getAddedItems() {
		return this.addedItems;
	}

	/**
	 * * Gets the removed items. <value>The removed items.</value>
	 * 
	 * @return the removed items
	 */
	protected Iterable<TKey> getRemovedItems() {
		return this.removedItems;
	}

	/**
	 * * Gets the modified items. <value>The modified items.</value>
	 * 
	 * @return the modified items
	 */
	protected Iterable<TKey> getModifiedItems() {
		return this.modifiedItems;
	}

	/**
	 * * Initializes a new instance of the class.
	 */
	public SimplePropertyBag() {
	}

	/***
	 * Clears the change log.
	 */
	public void clearChangeLog() {
		this.removedItems.clear();
		this.addedItems.clear();
		this.modifiedItems.clear();
	}

	/**
	 * * Determines whether the specified key is in the property bag.
	 * 
	 * @param key
	 *            the key
	 * @return true, if successful if the specified key exists; otherwise, .
	 */
	public boolean containsKey(TKey key) {
		return this.items.containsKey(key);
	}

	/**
	 * * Tries to get value.
	 * 
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 * @return True if value exists in property bag.
	 */
	public boolean tryGetValue(TKey key, OutParam<Object> value) {
		if (this.items.containsKey(key)) {
			value.setParam(this.items.get(key));
			return true;
		} else {
			value.setParam(null);
			return false;
		}
	}

	/**
	 * Gets the simple property bag.
	 * 
	 * @param key
	 *            the key
	 * @return the simple property bag
	 */
	public Object getSimplePropertyBag(TKey key) {
		OutParam value = new OutParam();
		if (this.tryGetValue(key, value)) {
			return value.getParam();
		} else {
			return null;
		}
	}

	/**
	 * Sets the simple property bag.
	 * 
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 */
	public void setSimplePropertyBag(TKey key, Object value) {
		if (value == null) {
			this.internalRemoveItem(key);
		} else {
			// If the item was to be deleted, the deletion becomes an update.
			if (this.removedItems.remove(key)) {
				internalAddItemToChangeList(key, this.modifiedItems);
			} else {
				// If the property value was not set, we have a newly set
				// property.
				if (!this.containsKey(key)) {
					internalAddItemToChangeList(key, this.addedItems);
				} else {
					// The last case is that we have a modified property.
					if (!this.modifiedItems.contains(key)) {
						internalAddItemToChangeList(key, this.modifiedItems);
					}
				}
			}

			this.items.put(key, value);
			this.changed();
		}
	}

	/***
	 * Occurs when Changed.
	 */
	private List<IPropertyBagChangedDelegate> onChange = 
		new ArrayList<IPropertyBagChangedDelegate>();

	/***
	 * Set event to happen when property changed.
	 * 
	 * @param change
	 *            change event
	 */
	public void addOnChangeEvent(IPropertyBagChangedDelegate change) {
		onChange.add(change);
	}

	/***
	 * Remove the event from happening when property changed.
	 * 
	 * @param change
	 *            change event
	 */
	public void removeChangeEvent(IPropertyBagChangedDelegate change) {
		onChange.remove(change);
	}

	/**
     * Returns an iterator over a set of elements of type T.
     * 
     * @return an Iterator.
     */
	@Override
	public Iterator<HashMap<TKey, Object>> iterator() {
		return (Iterator<HashMap<TKey, Object>>)this.items.keySet().iterator();
	}

}
