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
import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.service.folder.CalendarFolder;
import microsoft.exchange.webservices.data.core.service.folder.Folder;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceLocalException;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceValidationException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Represents a collection of folder permissions.
 */
public final class FolderPermissionCollection extends ComplexPropertyCollection<FolderPermission> {

  private static final Log LOG = LogFactory.getLog(FolderPermissionCollection.class);

  /**
   * The is calendar folder.
   */
  private boolean isCalendarFolder;

  /**
   * The unknown entries.
   */
  private Collection<String> unknownEntries = new ArrayList<String>();

  /**
   * Initializes a new instance of the FolderPermissionCollection class.
   *
   * @param owner the owner
   */
  public FolderPermissionCollection(Folder owner) {
    super();
    this.isCalendarFolder = owner instanceof CalendarFolder;
  }

  /**
   * Gets the name of the inner collection XML element.
   *
   * @return the inner collection xml element name
   */
  private String getInnerCollectionXmlElementName() {
    return this.isCalendarFolder ? XmlElementNames.CalendarPermissions :
        XmlElementNames.Permissions;
  }

  /**
   * Gets the name of the collection item XML element.
   *
   * @return the collection item xml element name
   */
  private String getCollectionItemXmlElementName() {
    return this.isCalendarFolder ? XmlElementNames.CalendarPermission :
        XmlElementNames.Permission;
  }

  /**
   * Gets the name of the collection item XML element.
   *
   * @param complexProperty the complex property
   * @return the collection item xml element name
   */
  @Override
  protected String getCollectionItemXmlElementName(
      FolderPermission complexProperty) {
    return this.getCollectionItemXmlElementName();
  }

  /**
   * Loads from XML.
   *
   * @param reader           the reader
   * @param localElementName the local element name
   * @throws Exception the exception
   */
  @Override public void loadFromXml(EwsServiceXmlReader reader, String localElementName) throws Exception {
    reader.ensureCurrentNodeIsStartElement(XmlNamespace.Types,
        localElementName);

    reader.readStartElement(XmlNamespace.Types, this
        .getInnerCollectionXmlElementName());
    super.loadFromXml(reader, this.getInnerCollectionXmlElementName());
    reader.readEndElementIfNecessary(XmlNamespace.Types, this
        .getInnerCollectionXmlElementName());

    reader.read();

    if (reader.isStartElement(XmlNamespace.Types,
        XmlElementNames.UnknownEntries)) {
      do {
        reader.read();

        if (reader.isStartElement(XmlNamespace.Types,
            XmlElementNames.UnknownEntry)) {
          this.unknownEntries.add(reader.readElementValue());
        }
      } while (!reader.isEndElement(XmlNamespace.Types,
          XmlElementNames.UnknownEntries));
    }
  }

  /**
   * Validates this instance.
   */
  public void validate() {
    for (int permissionIndex = 0; permissionIndex < this.getItems().size(); permissionIndex++) {
      FolderPermission permission = this.getItems().get(permissionIndex);
      try {
        permission.validate(this.isCalendarFolder, permissionIndex);
      } catch (ServiceValidationException e) {
        LOG.error(e);
      } catch (ServiceLocalException e) {
        LOG.error(e);
      }
    }
  }

  /**
   * Writes the elements to XML.
   *
   * @param writer the writer
   * @throws Exception the exception
   */
  @Override
  public void writeElementsToXml(EwsServiceXmlWriter writer)
      throws Exception {
    writer.writeStartElement(XmlNamespace.Types, this
        .getInnerCollectionXmlElementName());
    for (FolderPermission folderPermission : this) {
      folderPermission.writeToXml(writer, this
              .getCollectionItemXmlElementName(folderPermission),
          this.isCalendarFolder);
    }
    writer.writeEndElement(); // this.InnerCollectionXmlElementName
  }

  /**
   * Creates the complex property.
   *
   * @param xmlElementName the xml element name
   * @return FolderPermission instance.
   */
  @Override
  protected FolderPermission createComplexProperty(String xmlElementName) {
    return new FolderPermission();
  }

  /**
   * Adds a permission to the collection.
   *
   * @param permission the permission
   */
  public void add(FolderPermission permission) {
    this.internalAdd(permission);
  }

  /**
   * Adds the specified permissions to the collection.
   *
   * @param permissions the permissions
   * @throws Exception the exception
   */
  public void addFolderRange(Iterator<FolderPermission> permissions)
      throws Exception {
    EwsUtilities.validateParam(permissions, "permissions");

    if (null != permissions) {
      while (permissions.hasNext()) {
        this.add(permissions.next());
      }
    }
  }

  /**
   * Clears this collection.
   */
  public void clear() {
    this.internalClear();
  }

  /**
   * Removes a permission from the collection.
   *
   * @param permission the permission
   * @return True if the folder permission was successfully removed from the
   * collection, false otherwise.
   */
  public boolean remove(FolderPermission permission) {
    return this.internalRemove(permission);
  }

  /**
   * Removes a permission from the collection.
   *
   * @param index the index
   */
  public void removeAt(int index) {
    this.internalRemoveAt(index);
  }

  /**
   * Gets a list of unknown user Ids in the collection.
   *
   * @return the unknown entries
   */
  public Collection<String> getUnknownEntries() {
    return this.unknownEntries;
  }
}
