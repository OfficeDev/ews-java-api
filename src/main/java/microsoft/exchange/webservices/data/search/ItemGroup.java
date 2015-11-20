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

package microsoft.exchange.webservices.data.search;

import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.service.item.Item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Represents a group of item as returned by grouped item search operations.
 *
 * @param <TItem> the generic type
 */
public final class ItemGroup<TItem extends Item> {

  /**
   * The group index.
   */
  private String groupIndex;

  /**
   * The item.
   */
  private Collection<TItem> items;

  /**
   * Initializes a new instance of the class.
   *
   * @param groupIndex the group index
   * @param items      the item
   */
  public ItemGroup(String groupIndex, List<TItem> items) {
    EwsUtilities.ewsAssert(groupIndex != null, "ItemGroup.ctor", "groupIndex is null");
    EwsUtilities
        .ewsAssert(items != null, "ItemGroup.ctor", "item is null");

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
   * Gets a collection of the item in this group.
   *
   * @return the item
   */
  public Collection<TItem> getItems() {
    return this.items;
  }

  /**
   * Sets a collection of the item in this group.
   */
  private void setItems(Collection<TItem> value) {
    this.items = value;
  }
}
