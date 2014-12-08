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

/**
 * Represents a folder Id provided by a FolderId object.
 */
class FolderIdWrapper extends AbstractFolderIdWrapper {

  /**
   * The FolderId object providing the Id.
   */
  private FolderId folderId;

  /**
   * Initializes a new instance of FolderIdWrapper.
   *
   * @param folderId the folder id
   */
  protected FolderIdWrapper(FolderId folderId) {
    EwsUtilities.EwsAssert(folderId != null, "FolderIdWrapper.ctor",
        "folderId is null");
    this.folderId = folderId;
  }

  /**
   * Writes the Id encapsulated in the wrapper to XML.
   *
   * @param writer the writer
   * @throws Exception the exception
   */
  protected void writeToXml(EwsServiceXmlWriter writer)
      throws Exception {
    this.folderId.writeToXml(writer);
  }

  /**
   * Validates folderId against specified version.
   *
   * @param version the version
   * @throws ServiceVersionException the service version exception
   */
  protected void validate(ExchangeVersion version)
      throws ServiceVersionException {
    this.folderId.validate(version);
  }
}
