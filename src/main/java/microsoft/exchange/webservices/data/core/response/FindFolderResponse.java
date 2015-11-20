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
import microsoft.exchange.webservices.data.core.PropertySet;
import microsoft.exchange.webservices.data.core.XmlAttributeNames;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.service.folder.Folder;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.search.FindFoldersResults;
import microsoft.exchange.webservices.data.security.XmlNodeType;

/**
 * Represents the response to a folder search operation.
 */
public final class FindFolderResponse extends ServiceResponse {

  /**
   * The results.
   */
  private FindFoldersResults results = new FindFoldersResults();

  /**
   * The property set.
   */
  private PropertySet propertySet;

  /**
   * Reads response elements from XML.
   *
   * @param reader The reader
   * @throws Exception the exception
   */
  @Override
  protected void readElementsFromXml(EwsServiceXmlReader reader)
      throws Exception {
    reader.readStartElement(XmlNamespace.Messages,
        XmlElementNames.RootFolder);

    this.results.setTotalCount(reader.readAttributeValue(Integer.class,
        XmlAttributeNames.TotalItemsInView));
    this.results.setMoreAvailable(!reader.readAttributeValue(Boolean.class,
        XmlAttributeNames.IncludesLastItemInRange));

    // Ignore IndexedPagingOffset attribute if MoreAvailable is false.
    this.results.setNextPageOffset(results.isMoreAvailable() ? reader
        .readNullableAttributeValue(Integer.class,
            XmlAttributeNames.IndexedPagingOffset) : null);

    reader.readStartElement(XmlNamespace.Types, XmlElementNames.Folders);
    if (!reader.isEmptyElement()) {
      do {
        reader.read();

        if (reader.getNodeType().nodeType == XmlNodeType.START_ELEMENT) {
          Folder folder = EwsUtilities
              .createEwsObjectFromXmlElementName(Folder.class, reader.getService(), reader.getLocalName());

          if (folder == null) {
            reader.skipCurrentElement();
          } else {
            folder.loadFromXml(reader, true, /* clearPropertyBag */
                this.propertySet, true /* summaryPropertiesOnly */);

            this.results.getFolders().add(folder);
          }
        }
      } while (!reader.isEndElement(XmlNamespace.Types,
          XmlElementNames.Folders));
    } else {
      reader.read();
    }

    reader
        .readEndElement(XmlNamespace.Messages,
            XmlElementNames.RootFolder);
  }

  /**
   * Initializes a new instance of the FindFolderResponse class.
   *
   * @param propertySet The property set from, the request.
   */
  public FindFolderResponse(PropertySet propertySet) {
    super();
    this.propertySet = propertySet;

    EwsUtilities.ewsAssert(this.propertySet != null, "FindFolderResponse.ctor",
                           "PropertySet should not be null");
  }

  /**
   * Gets the results of the search operation.
   *
   * @return the results
   */
  public FindFoldersResults getResults() {
    return this.results;
  }

}
