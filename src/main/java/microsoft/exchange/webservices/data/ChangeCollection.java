/**************************************************************************
 Exchange Web Services Java API
 Copyright (c) Microsoft Corporation
 All rights reserved.
 MIT License
 Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the ""Software""), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 THE SOFTWARE IS PROVIDED *AS IS*, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 **************************************************************************/

package microsoft.exchange.webservices.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a collection of changes as returned by a synchronization
 * operation.
 *
 * @param <TChange> the generic type
 */
public final class ChangeCollection<TChange extends Change> implements
    Iterable<TChange> {

  /**
   * The changes.
   */
  private List<TChange> changes = new ArrayList<TChange>();

  /**
   * The sync state.
   */
  private String syncState;

  /**
   * The more changes available.
   */
  private boolean moreChangesAvailable;

  /**
   * Initializes a new instance of the class.
   */
  protected ChangeCollection() {
  }

  /**
   * Adds the specified change.
   *
   * @param change the change
   */
  protected void add(TChange change) {
    EwsUtilities.EwsAssert(change != null, "ChangeList.Add",
        "change is null");
    this.changes.add(change);
  }

  /**
   * Gets the number of changes in the collection.
   *
   * @return the count
   */
  public int getCount() {
    return this.changes.size();
  }

  /**
   * Gets an individual change from the change collection.
   *
   * @param index the index
   * @return An single change
   */
  public TChange getChangeAtIndex(int index) {
    if (index < 0 || index >= this.getCount()) {
      throw new IndexOutOfBoundsException(Strings.IndexIsOutOfRange);
    }
    return this.changes.get(index);
  }

  /**
   * Gets the SyncState blob returned by a synchronization operation.
   *
   * @return the sync state
   */
  public String getSyncState() {
    return this.syncState;
  }

  /**
   * Sets the sync state.
   *
   * @param syncState the new sync state
   */
  protected void setSyncState(String syncState) {
    this.syncState = syncState;
  }

  /**
   * Gets the SyncState blob returned by a synchronization operation.
   *
   * @return the more changes available
   */
  public boolean getMoreChangesAvailable() {
    return this.moreChangesAvailable;
  }

  /**
   * Sets the more changes available.
   *
   * @param moreChangesAvailable the new more changes available
   */
  protected void setMoreChangesAvailable(boolean moreChangesAvailable) {
    this.moreChangesAvailable = moreChangesAvailable;
  }

  /**
   * Returns an iterator over a set of elements of type T.
   *
   * @return an Iterator.
   */
  @Override
  public Iterator<TChange> iterator() {
    return this.changes.iterator();
  }

}
