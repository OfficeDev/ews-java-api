/*
 * The MIT License
 * Copyright (c) 2012 Microsoft Corporation
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package microsoft.exchange.webservices.data.core;

import microsoft.exchange.webservices.data.misc.OutParam;
import microsoft.exchange.webservices.data.property.complex.IPropertyBagChangedDelegate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Represents a simple property bag.
 *
 * @param <TKey> The type of key
 */
public class SimplePropertyBag<TKey> implements Iterable<HashMap<TKey, Object>> {

  /**
   * The item.
   */
  private Map<TKey, Object> items = new HashMap<TKey, Object>();

  /**
   * The removed item.
   */
  private List<TKey> removedItems = new ArrayList<TKey>();

  /**
   * The added item.
   */
  private List<TKey> addedItems = new ArrayList<TKey>();

  /**
   * The modified item.
   */
  private List<TKey> modifiedItems = new ArrayList<TKey>();

  /**
   * Add item to change list.
   *
   * @param key        the key
   * @param changeList the change list
   */
  private void internalAddItemToChangeList(TKey key, List<TKey> changeList) {
    if (!changeList.contains(key)) {
      changeList.add(key);
    }
  }

  /**
   * Triggers dispatch of the change event.
   */
  private void changed() {
    if (!onChange.isEmpty()) {
      for (IPropertyBagChangedDelegate<TKey> change : onChange) {
        change.propertyBagChanged(this);
      }
    }
  }

  /**
   * Remove item.
   *
   * @param key the key
   */
  private void internalRemoveItem(TKey key) {
    OutParam<Object> value = new OutParam<Object>();
    if (this.tryGetValue(key, value)) {
      this.items.remove(key);
      this.removedItems.add(key);
      this.changed();
    }
  }


  /**
   * Gets the added item. <value>The added item.</value>
   *
   * @return the added item
   */
  public Iterable<TKey> getAddedItems() {
    return this.addedItems;
  }

  /**
   * Gets the removed item. <value>The removed item.</value>
   *
   * @return the removed item
   */
  public Iterable<TKey> getRemovedItems() {
    return this.removedItems;
  }

  /**
   * Gets the modified item. <value>The modified item.</value>
   *
   * @return the modified item
   */
  public Iterable<TKey> getModifiedItems() {
    return this.modifiedItems;
  }

  /**
   * Initializes a new instance of the class.
   */
  public SimplePropertyBag() {
  }

  /**
   * Clears the change log.
   */
  public void clearChangeLog() {
    this.removedItems.clear();
    this.addedItems.clear();
    this.modifiedItems.clear();
  }

  /**
   * Determines whether the specified key is in the property bag.
   *
   * @param key the key
   * @return true, if successful if the specified key exists; otherwise, .
   */
  public boolean containsKey(TKey key) {
    return this.items.containsKey(key);
  }

  /**
   * Tries to get value.
   *
   * @param key   the key
   * @param value the value
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
   * @param key the key
   * @return the simple property bag
   */
  public Object getSimplePropertyBag(TKey key) {
    OutParam<Object> value = new OutParam<Object>();
    if (this.tryGetValue(key, value)) {
      return value.getParam();
    } else {
      return null;
    }
  }

  /**
   * Sets the simple property bag.
   *
   * @param key   the key
   * @param value the value
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

  /**
   * Occurs when Changed.
   */
  private List<IPropertyBagChangedDelegate<TKey>> onChange =
      new ArrayList<IPropertyBagChangedDelegate<TKey>>();

  /**
   * Set event to happen when property changed.
   *
   * @param change change event
   */
  public void addOnChangeEvent(IPropertyBagChangedDelegate<TKey> change) {
    onChange.add(change);
  }

  /**
   * Remove the event from happening when property changed.
   *
   * @param change change event
   */
  public void removeChangeEvent(IPropertyBagChangedDelegate<TKey> change) {
    onChange.remove(change);
  }

  /**
   * Returns an iterator over a set of elements of type T.
   *
   * @return an Iterator.
   */
  @Override
  public Iterator<HashMap<TKey, Object>> iterator() {
    return (Iterator<HashMap<TKey, Object>>) this.items.keySet().iterator();
  }

}
