/**************************************************************************
 Exchange Web Services Java API
 Copyright (c) Microsoft Corporation
 All rights reserved.
 MIT License
 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in
 all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 THE SOFTWARE.
 **************************************************************************/

package microsoft.exchange.webservices.data;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Represents the results of an item search operation.
 *
 * @param <TItem> The type of item returned by the search operation.
 */
public final class FindItemsResults<TItem extends Item> implements
    Iterable<Item> {

  /**
   * The total count.
   */
  private int totalCount;

  /**
   * The next page offset.
   */
  private Integer nextPageOffset;

  /**
   * The more available.
   */
  private boolean moreAvailable;

  /**
   * The items.
   */
  private ArrayList<TItem> items = new ArrayList<TItem>();

  /**
   * Initializes a new instance of the FindItemsResults class.
   */
  protected FindItemsResults() {
  }

  /**
   * Gets the total number of items matching the search criteria available in
   * the searched folder.
   *
   * @return the total count
   */
  public int getTotalCount() {
    return this.totalCount;
  }

  /**
   * Sets the total number of items matching the search criteria available in
   * the searched folder.
   *
   * @param totalCount the new total count
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
   * @param nextPageOffset the new next page offset
   */
  protected void setNextPageOffset(Integer nextPageOffset) {
    this.nextPageOffset = nextPageOffset;
  }

  /**
   * Gets a value indicating whether more items matching the search criteria
   * are available in the searched folder.
   *
   * @return true, if is more available
   */
  public boolean isMoreAvailable() {
    return moreAvailable;
  }

  /**
   * Sets a value indicating whether more items matching the search criteria
   * are available in the searched folder.
   *
   * @param moreAvailable the new more available
   */
  protected void setMoreAvailable(boolean moreAvailable) {
    this.moreAvailable = moreAvailable;
  }

  /**
   * Gets a collection containing the items that were found by the search
   * operation.
   *
   * @return the items
   */
  public ArrayList<TItem> getItems() {
    return this.items;
  }

  /**
   * Returns an iterator that iterates through the collection.
   *
   * @return the iterator
   */
  @Override
  public Iterator<Item> iterator() {
    return (Iterator<Item>) this.items.iterator();
  }

}
