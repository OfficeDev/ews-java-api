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

import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.misc.OutParam;

/**
 * Represents information for a managed folder.
 */
public final class ManagedFolderInformation extends ComplexProperty {

  /**
   * The can delete.
   */
  private Boolean canDelete;

  /**
   * The can rename or move.
   */
  private Boolean canRenameOrMove;

  /**
   * The must display comment.
   */
  private Boolean mustDisplayComment;

  /**
   * The has quota.
   */
  private Boolean hasQuota;

  /**
   * The is managed folder root.
   */
  private Boolean isManagedFoldersRoot;

  /**
   * The managed folder id.
   */
  private String managedFolderId;

  /**
   * The comment.
   */
  private String comment;

  /**
   * The storage quota.
   */
  private Integer storageQuota;

  /**
   * The folder size.
   */
  private Integer folderSize;

  /**
   * The home page.
   */
  private String homePage;

  /**
   * Initializes a new instance of the ManagedFolderInformation class.
   */
  public ManagedFolderInformation() {
    super();
  }

  /**
   * Tries to read element from XML.
   *
   * @param reader the reader
   * @return True if element was read.
   * @throws Exception the exception
   */
  public boolean tryReadElementFromXml(EwsServiceXmlReader reader)
      throws Exception {
    if (reader.getLocalName().equalsIgnoreCase(XmlElementNames.CanDelete)) {
      this.canDelete = reader.readValue(Boolean.class);
      return true;
    } else if (reader.getLocalName().equalsIgnoreCase(
        XmlElementNames.CanRenameOrMove)) {
      this.canRenameOrMove = reader.readValue(Boolean.class);
      return true;
    } else if (reader.getLocalName().equalsIgnoreCase(
        XmlElementNames.MustDisplayComment)) {
      this.mustDisplayComment = reader.readValue(Boolean.class);
      return true;
    } else if (reader.getLocalName().equalsIgnoreCase(
        XmlElementNames.HasQuota)) {
      this.hasQuota = reader.readValue(Boolean.class);
      return true;
    } else if (reader.getLocalName().equalsIgnoreCase(
        XmlElementNames.IsManagedFoldersRoot)) {
      this.isManagedFoldersRoot = reader.readValue(Boolean.class);
      return true;
    } else if (reader.getLocalName().equalsIgnoreCase(
        XmlElementNames.ManagedFolderId)) {
      this.managedFolderId = reader.readValue();
      return true;
    } else if (reader.getLocalName().equalsIgnoreCase(
        XmlElementNames.Comment)) {
      OutParam<String> value = new OutParam<String>();
      reader.tryReadValue(value);
      this.comment = value.getParam();
      return true;
    } else if (reader.getLocalName().equalsIgnoreCase(
        XmlElementNames.StorageQuota)) {
      this.storageQuota = reader.readValue(Integer.class);
      return true;
    } else if (reader.getLocalName().equalsIgnoreCase(
        XmlElementNames.FolderSize)) {
      this.folderSize = reader.readValue(Integer.class);
      return true;
    } else if (reader.getLocalName().equalsIgnoreCase(
        XmlElementNames.HomePage)) {
      OutParam<String> value = new OutParam<String>();
      reader.tryReadValue(value);
      this.homePage = value.getParam();
      return true;
    } else {
      return false;
    }

  }

  /**
   * Gets a value indicating whether the user can delete objects in the
   * folder.
   *
   * @return the can delete
   */
  public Boolean getCanDelete() {
    return this.canDelete;
  }

  /**
   * Gets a value indicating whether the user can rename or move objects in
   * the folder.
   *
   * @return the can rename or move
   */
  public Boolean getCanRenameOrMove() {
    return canRenameOrMove;
  }

  /**
   * Gets a value indicating whether the client application must display the
   * Comment property to the user.
   *
   * @return the must display comment
   */
  public Boolean getMustDisplayComment() {
    return mustDisplayComment;
  }

  /**
   * Gets a value indicating whether the folder has a quota.
   *
   * @return the checks for quota
   */
  public Boolean getHasQuota() {
    return hasQuota;
  }

  /**
   * Gets a value indicating whether the folder is the root of the managed
   * folder hierarchy.
   *
   * @return the checks if is managed folder root
   */
  public Boolean getIsManagedFoldersRoot() {
    return isManagedFoldersRoot;
  }

  /**
   * Gets the Managed Folder Id of the folder.
   *
   * @return the managed folder id
   */
  public String getManagedFolderId() {
    return managedFolderId;
  }

  /**
   * Gets the comment associated with the folder.
   *
   * @return the comment
   */
  public String getComment() {
    return comment;
  }

  /**
   * Gets the storage quota of the folder.
   *
   * @return the storage quota
   */
  public Integer getStorageQuota() {
    return storageQuota;
  }

  /**
   * Gets the size of the folder.
   *
   * @return the folder size
   */
  public Integer getFolderSize() {
    return folderSize;
  }

  /**
   * Gets the home page associated with the folder.
   *
   * @return the home page
   */
  public String getHomePage() {
    return homePage;
  }

}
