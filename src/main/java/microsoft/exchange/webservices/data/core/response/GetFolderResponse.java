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
import microsoft.exchange.webservices.data.core.PropertySet;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.service.ServiceObject;
import microsoft.exchange.webservices.data.core.service.folder.Folder;

import java.util.List;

/**
 * Represents the response to an individual folder retrieval operation.
 */
public final class GetFolderResponse extends ServiceResponse implements
                                                             IGetObjectInstanceDelegate<ServiceObject> {

  /**
   * The folder.
   */
  private Folder folder;

  /**
   * The property set.
   */
  private PropertySet propertySet;

  /**
   * Initializes a new instance of the GetFolderResponse class.
   *
   * @param folder      The folder.
   * @param propertySet The property set from the request.
   */
  public GetFolderResponse(Folder folder, PropertySet propertySet) {
    super();
    this.folder = folder;
    this.propertySet = propertySet;
    EwsUtilities
        .ewsAssert(this.propertySet != null, "GetFolderResponse.ctor", "PropertySet should not be null");
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
    List<Folder> folders = reader.readServiceObjectsCollectionFromXml(
        XmlElementNames.Folders, this, true, /* clearPropertyBag */
        this.propertySet, /* requestedPropertySet */
        false); /* summaryPropertiesOnly */
    this.folder = folders.get(0);
  }

  /**
   * Gets the object instance delegate.
   *
   * @param service        the service
   * @param xmlElementName the xml element name
   * @return the object instance delegate
   * @throws Exception the exception
   */
  @Override
  public ServiceObject getObjectInstanceDelegate(ExchangeService service,
      String xmlElementName) throws Exception {
    return this.getObjectInstance(service, xmlElementName);
  }

  /**
   * Gets the folder instance.
   *
   * @param service        The service.
   * @param xmlElementName Name of the XML element.
   * @return folder
   * @throws Exception the exception
   */
  private Folder getObjectInstance(ExchangeService service,
      String xmlElementName) throws Exception {
    if (this.getFolder() != null) {
      return this.getFolder();
    } else {
      return EwsUtilities.createEwsObjectFromXmlElementName(Folder.class,
          service, xmlElementName);
    }
  }

  /**
   * Gets the folder that was retrieved.
   *
   * @return folder
   */
  public Folder getFolder() {
    return this.folder;
  }

}
