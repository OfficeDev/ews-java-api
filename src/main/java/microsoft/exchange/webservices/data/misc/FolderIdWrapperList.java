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

package microsoft.exchange.webservices.data.misc;

import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.service.folder.Folder;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceLocalException;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceVersionException;
import microsoft.exchange.webservices.data.property.complex.FolderId;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a list a abstracted folder Ids.
 */
public class FolderIdWrapperList implements Iterable<AbstractFolderIdWrapper> {

  /**
   * The ids.
   */
  private List<AbstractFolderIdWrapper> ids = new
      ArrayList<AbstractFolderIdWrapper>();

  /**
   * Adds the specified folder.
   *
   * @param folder the folder
   * @throws ServiceLocalException the service local exception
   */
  public void add(Folder folder) throws ServiceLocalException {
    this.ids.add(new FolderWrapper(folder));
  }

  /**
   * Adds the range.
   *
   * @param folders the folder
   * @throws ServiceLocalException the service local exception
   */
  protected void addRangeFolder(Iterable<Folder> folders)
      throws ServiceLocalException {
    if (folders != null) {
      for (Folder folder : folders) {
        this.add(folder);
      }
    }
  }

  /**
   * Adds the specified folder id.
   *
   * @param folderId the folder id
   */
  public void add(FolderId folderId) {
    this.ids.add(new FolderIdWrapper(folderId));
  }

  /**
   * Adds the range of folder ids.
   *
   * @param folderIds the folder ids
   */
  public void addRangeFolderId(Iterable<FolderId> folderIds) {
    if (folderIds != null) {
      for (FolderId folderId : folderIds) {
        this.add(folderId);
      }
    }
  }

  /**
   * Writes to XML.
   *
   * @param writer         the writer
   * @param ewsNamesapce   the ews namesapce
   * @param xmlElementName the xml element name
   * @throws Exception the exception
   */
  public void writeToXml(EwsServiceXmlWriter writer, XmlNamespace ewsNamesapce, String xmlElementName) throws Exception {
    if (this.getCount() > 0) {
      writer.writeStartElement(ewsNamesapce, xmlElementName);

      for (AbstractFolderIdWrapper folderIdWrapper : this.ids) {
        folderIdWrapper.writeToXml(writer);
      }

      writer.writeEndElement();
    }
  }

  /**
   * Gets the id count.
   *
   * @return the count
   */
  public int getCount() {
    return this.ids.size();
  }

  /**
   * Gets the <see
   * cref="Microsoft.Exchange.WebServices.Data.AbstractFolderIdWrapper"/> at
   * the specified index.
   *
   * @param i the i
   * @return the index
   */
  public AbstractFolderIdWrapper getFolderIdWrapperList(int i) {
    return this.ids.get(i);
  }

  /**
   * Validates list of folderIds against a specified request version.
   *
   * @param version the version
   * @throws ServiceVersionException the service version exception
   */
  public void validate(ExchangeVersion version)
      throws ServiceVersionException {
    for (AbstractFolderIdWrapper folderIdWrapper : this.ids) {
      folderIdWrapper.validate(version);
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Iterable#iterator()
   */
  @Override
  public Iterator<AbstractFolderIdWrapper> iterator() {
    return ids.iterator();
  }

}
