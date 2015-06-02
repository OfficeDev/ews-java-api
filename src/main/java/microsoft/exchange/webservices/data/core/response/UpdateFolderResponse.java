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

package microsoft.exchange.webservices.data.core.response;

import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.service.ServiceObject;
import microsoft.exchange.webservices.data.core.service.folder.Folder;
import microsoft.exchange.webservices.data.core.enumeration.service.ServiceResult;

/**
 * Represents response to UpdateFolder request.
 */
public final class UpdateFolderResponse extends ServiceResponse implements
                                                         IGetObjectInstanceDelegate<ServiceObject> {

  /**
   * The folder.
   */
  private Folder folder;

  /**
   * Initializes a new instance of the UpdateFolderResponse class.
   *
   * @param folder The folder
   */
  public UpdateFolderResponse(Folder folder) {
    super();
    EwsUtilities.ewsAssert(folder != null, "UpdateFolderResponse.ctor", "folder is null");

    this.folder = folder;
  }

  /**
   * Reads response elements from XML.
   *
   * @param reader the reader
   * @throws Exception the exception
   */
  @Override
  protected void readElementsFromXml(EwsServiceXmlReader reader)
      throws Exception {
    super.readElementsFromXml(reader);

    reader.readServiceObjectsCollectionFromXml(XmlElementNames.Folders,
        this, false, /* clearPropertyBag */
        null, /* requestedPropertySet */
        false); /* summaryPropertiesOnly */
  }

  /**
   * Clears the change log of the updated folder if the update succeeded.
   */
  @Override
  protected void loaded() {
    if (this.getResult() == ServiceResult.Success) {
      this.folder.clearChangeLog();
    }
  }

  /**
   * Gets Folder instance.
   *
   * @param session        The session
   * @param xmlElementName Name of the XML element.
   * @return Folder
   */
  private Folder getObjectInstance(ExchangeService session,
      String xmlElementName) {
    return this.folder;
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
