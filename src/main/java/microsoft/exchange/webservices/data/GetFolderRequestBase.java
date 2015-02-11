/**
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
package microsoft.exchange.webservices.data;

/**
 * Represents an abstract GetFolder request.
 *
 * @param <TResponse> the generic type
 */
abstract class GetFolderRequestBase<TResponse extends ServiceResponse> extends
    GetRequest<Folder, TResponse> {

  /**
   * The folder ids.
   */
  private FolderIdWrapperList folderIds = new FolderIdWrapperList();

  /**
   * Initializes a new instance of the class.
   *
   * @param service           the service
   * @param errorHandlingMode the error handling mode
   * @throws Exception
   */
  protected GetFolderRequestBase(ExchangeService service,
      ServiceErrorHandling errorHandlingMode)
      throws Exception {
    super(service, errorHandlingMode);
  }

  /**
   * Validate request.
   *
   * @throws Exception the exception
   */
  protected void validate() throws Exception {
    super.validate();
    EwsUtilities.validateParamCollection(this.getFolderIds().iterator(),
        "FolderIds");
    this.getFolderIds().validate(
        this.getService().getRequestedServerVersion());
  }

  /**
   * Gets the expected response message count.
   *
   * @return Number of expected response messages
   */
  protected int getExpectedResponseMessageCount() {
    return this.getFolderIds().getCount();
  }

  /**
   * Gets the type of the service object this request applies to.
   *
   * @return The type of service object the request applies to
   */
  protected ServiceObjectType getServiceObjectType() {
    return ServiceObjectType.Folder;
  }

  /**
   * Writes XML elements.
   *
   * @param writer the writer
   * @throws Exception the exception
   */
  protected void writeElementsToXml(EwsServiceXmlWriter writer)
      throws Exception {
    super.writeElementsToXml(writer);
    this.getFolderIds().writeToXml(writer, XmlNamespace.Messages,
        XmlElementNames.FolderIds);
  }

  /**
   * Gets the name of the XML element.
   *
   * @return XML element name
   */
  protected String getXmlElementName() {
    return XmlElementNames.GetFolder;
  }

  /**
   * Gets the name of the response XML element.
   *
   * @return XML element name
   */
  protected String getResponseXmlElementName() {
    return XmlElementNames.GetFolderResponse;
  }

  /**
   * Gets the name of the response message XML element.
   *
   * @return XML element name
   */
  protected String getResponseMessageXmlElementName() {
    return XmlElementNames.GetFolderResponseMessage;
  }

  /**
   * Gets the request version.
   *
   * @return Earliest Exchange version in which this request is supported
   */
  protected ExchangeVersion getMinimumRequiredServerVersion() {
    return ExchangeVersion.Exchange2007_SP1;
  }

  /**
   * Gets the folder ids.
   *
   * @return the folder ids
   */
  public FolderIdWrapperList getFolderIds() {
    return this.folderIds;
  }

}
