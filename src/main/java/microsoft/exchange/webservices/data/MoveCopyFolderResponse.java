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

import java.util.List;

/**
 * Represents the base response class for individual folder move and copy
 * operations.
 */
public final class MoveCopyFolderResponse extends ServiceResponse implements
    IGetObjectInstanceDelegate<ServiceObject> {

  /**
   * The folder.
   */
  private Folder folder;

  /**
   * Initializes a new instance of the MoveCopyFolderResponse class.
   */
  protected MoveCopyFolderResponse() {
    super();
  }

  /**
   * Gets Folder instance.
   *
   * @param service        The service.
   * @param xmlElementName Name of the XML element.
   * @return folder
   * @throws Exception the exception
   */
  private Folder getObjectInstance(ExchangeService service,
      String xmlElementName) throws Exception {
    return EwsUtilities.createEwsObjectFromXmlElementName(Folder.class,
        service, xmlElementName);
  }

  /**
   * Reads response elements from XML.
   *
   * @param reader The reader.
   * @throws Exception the exception
   */
  @SuppressWarnings("unchecked")
  @Override
  protected void readElementsFromXml(EwsServiceXmlReader reader)
      throws Exception {
    super.readElementsFromXml(reader);

    List<Folder> folders;
    try {
      folders = reader.readServiceObjectsCollectionFromXml(

          XmlElementNames.Folders, this, false,/* clearPropertyBag */
          null, /* requestedPropertySet */
          false); /* summaryPropertiesOnly */

      this.folder = folders.get(0);
    } catch (ServiceLocalException e) {
      e.printStackTrace();
    }

  }

  /**
   * Gets the new (moved or copied) folder.
   *
   * @return the folder
   */
  public Folder getFolder() {
    return folder;
  }

  /**
   * Gets the object instance delegate.
   *
   * @param service        accepts ExchangeService
   * @param xmlElementName accepts String
   * @return Object
   * @throws Exception throws Exception
   */
  @Override
  public ServiceObject getObjectInstanceDelegate(ExchangeService service,
      String xmlElementName) throws Exception {
    return this.getObjectInstance(service, xmlElementName);
  }

}
