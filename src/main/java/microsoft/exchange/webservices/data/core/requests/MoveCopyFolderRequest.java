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

package microsoft.exchange.webservices.data.core.requests;

import microsoft.exchange.webservices.data.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.EwsUtilities;
import microsoft.exchange.webservices.data.ExchangeService;
import microsoft.exchange.webservices.data.Folder;
import microsoft.exchange.webservices.data.ServiceResponse;
import microsoft.exchange.webservices.data.XmlElementNames;
import microsoft.exchange.webservices.data.enumerations.ServiceErrorHandling;
import microsoft.exchange.webservices.data.enumerations.XmlNamespace;
import microsoft.exchange.webservices.data.misc.FolderIdWrapperList;

/**
 * Represents an abstract Move/Copy Folder request.
 *
 * @param <TResponse> The type of response
 */
abstract class MoveCopyFolderRequest<TResponse extends ServiceResponse> extends
    MoveCopyRequest<Folder, TResponse> {

  /**
   * The folder ids.
   */
  private FolderIdWrapperList folderIds = new FolderIdWrapperList();

  /**
   * Validates request.
   *
   * @throws Exception the exception
   */
  @Override
  protected void validate() throws Exception {
    super.validate();
    EwsUtilities.validateParamCollection(this.getFolderIds().iterator(), "FolderIds");
    this.getFolderIds().validate(
        this.getService().getRequestedServerVersion());
  }

  /**
   * Initializes a new instance of the <see
   * cref="MoveCopyFolderRequest&lt;TResponse&gt;"/> class.
   *
   * @param service           The service.
   * @param errorHandlingMode Indicates how errors should be handled.
   * @throws Exception
   */
  protected MoveCopyFolderRequest(ExchangeService service,
      ServiceErrorHandling errorHandlingMode)
      throws Exception {
    super(service, errorHandlingMode);
  }

  /**
   * Writes the ids as XML.
   *
   * @param writer the writer
   */
  @Override
  protected void writeIdsToXml(EwsServiceXmlWriter writer) {
    try {
      this.folderIds.writeToXml(writer, XmlNamespace.Messages,
          XmlElementNames.FolderIds);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Gets the expected response message count.
   *
   * @return Number of expected response messages.
   */
  @Override
  protected int getExpectedResponseMessageCount() {
    return this.getFolderIds().getCount();
  }

  /**
   * Gets the folder ids.
   *
   * @return The folder ids.
   */
  protected FolderIdWrapperList getFolderIds() {
    return this.folderIds;
  }

}
