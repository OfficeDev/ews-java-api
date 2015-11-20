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

import microsoft.exchange.webservices.data.core.service.folder.Folder;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Represents the results of a folder search operation.
 */
public final class FindFoldersResults implements Iterable<Folder> {

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
   * The folder.
   */
  private ArrayList<Folder> folders = new ArrayList<Folder>();

  /**
   * Initializes a new instance of the <see cref="FindFoldersResults"/> class.
   */
  public FindFoldersResults() {

  }

  /**
   * Gets the total number of folder matching the search criteria available
   * in the searched folder.
   *
   * @return the total count
   */
  public int getTotalCount() {
    return totalCount;
  }

  /**
   * Sets the total number of folder.
   *
   * @param totalCount the new total count
   */
  public void setTotalCount(int totalCount) {
    this.totalCount = totalCount;
  }

  /**
   * Gets the offset that should be used with FolderView to retrieve the next
   * page of folder in a FindFolders operation.
   *
   * @return the next page offset
   */
  public Integer getNextPageOffset() {
    return nextPageOffset;
  }

  /**
   * Sets the offset that should be used with FolderView to retrieve the next
   * page of folder in a FindFolders operation.
   *
   * @param nextPageOffset the new next page offset
   */
  public void setNextPageOffset(Integer nextPageOffset) {
    this.nextPageOffset = nextPageOffset;
  }

  /**
   * Gets a value indicating whether more folder matching the search
   * criteria. are available in the searched folder.
   *
   * @return true, if is more available
   */
  public boolean isMoreAvailable() {
    return moreAvailable;
  }

  /**
   * Sets a value indicating whether more folder matching the search
   * criteria. are available in the searched folder.
   *
   * @param moreAvailable the new more available
   */
  public void setMoreAvailable(boolean moreAvailable) {
    this.moreAvailable = moreAvailable;
  }

  /**
   * Gets a collection containing the folder that were found by the search
   * operation.
   *
   * @return the folder
   */
  public ArrayList<Folder> getFolders() {
    return folders;
  }

  /**
   * Returns an iterator that iterates through a collection.
   *
   * @return the iterator
   */
  @Override
  public Iterator<Folder> iterator() {
    return this.folders.iterator();
  }

}
