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

package microsoft.exchange.webservices.data.property.complex;

import microsoft.exchange.webservices.data.attribute.EditorBrowsable;
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.enumeration.attribute.EditorBrowsableState;
import microsoft.exchange.webservices.data.core.enumeration.property.WellKnownFolderName;

/**
 * Represents a collection of folder Ids.
 */
@EditorBrowsable(state = EditorBrowsableState.Never)
public final class FolderIdCollection extends ComplexPropertyCollection<FolderId> {

  /**
   * Initializes a new instance of the <see cref="FolderIdCollection"/> class.
   */
  protected FolderIdCollection() {
    super();
  }

  /**
   * Creates the complex property.
   *
   * @param xmlElementName Name of the XML element.
   * @return Complex property instance.
   */
  @Override
  /**
   * Creates the complex property.
   * @param xmlElementName Name of the XML element.
   * @return FolderId.
   */
  protected FolderId createComplexProperty(String xmlElementName) {
    return new FolderId();
  }

  /**
   * Adds a folder Id to the collection.
   *
   * @param folderId The folder Id to add.
   * @throws Exception the exception
   */
  public void add(FolderId folderId) throws Exception {
    EwsUtilities.validateParam(folderId, "folderId");
    if (this.contains(folderId)) {
      throw new IllegalArgumentException("The ID is already in the list.");
    }
    this.internalAdd(folderId);
  }

  /**
   * Gets the name of the collection item XML element.
   *
   * @param complexProperty accepts FolderId
   * @return XML element name.
   */
  @Override
  protected String getCollectionItemXmlElementName(FolderId complexProperty) {
    return complexProperty.getXmlElementName();
  }

  /**
   * Adds a well-known folder to the collection.
   *
   * @param folderName the folder name
   * @return A FolderId encapsulating the specified Id.
   */
  public FolderId add(WellKnownFolderName folderName) {
    FolderId folderId = new FolderId(folderName);
    if (this.contains(folderId)) {
      throw new IllegalArgumentException("The ID is already in the list.");
    }
    this.internalAdd(folderId);
    return folderId;
  }

  /**
   * Clears the collection.
   */
  public void clear() {
    this.internalClear();
  }

  /**
   * Removes the folder Id at the specified index.
   *
   * @param index The zero-based index of the folder Id to remove.
   */
  public void removeAt(int index) {
    if (index < 0 || index >= this.getCount()) {
      throw new IndexOutOfBoundsException("index is out of range.");
    }
    this.internalRemoveAt(index);
  }

  /**
   * Removes the specified folder Id from the collection.
   *
   * @param folderId The folder Id to remove from the collection.
   * @return True if the folder id was successfully removed from the
   * collection, false otherwise.
   * @throws Exception the exception
   */
  public boolean remove(FolderId folderId) throws Exception {
    EwsUtilities.validateParam(folderId, "folderId");
    return this.internalRemove(folderId);
  }

  /**
   * Removes the specified well-known folder from the collection.
   *
   * @param folderName The well-knwon folder to remove from the collection.
   * @return True if the well-known folder was successfully removed from the
   * collection, false otherwise.
   */
  public boolean remove(WellKnownFolderName folderName) {
    FolderId folderId = FolderId
        .getFolderIdFromWellKnownFolderName(folderName);
    return this.internalRemove(folderId);
  }

}
