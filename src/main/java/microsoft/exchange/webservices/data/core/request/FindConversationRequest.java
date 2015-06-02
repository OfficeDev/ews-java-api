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

package microsoft.exchange.webservices.data.core.request;

import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.response.FindConversationResponse;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceLocalException;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlSerializationException;
import microsoft.exchange.webservices.data.misc.FolderIdWrapper;
import microsoft.exchange.webservices.data.search.ConversationIndexedItemView;
import microsoft.exchange.webservices.data.search.filter.SearchFilter;

/**
 * Represents a request to a Find Conversation operation
 */
public final class FindConversationRequest extends SimpleServiceRequestBase<FindConversationResponse> {


  private ConversationIndexedItemView view;
  private SearchFilter.IsEqualTo searchFilter;
  private FolderIdWrapper folderId;

  /**
   * @throws Exception
   */
  public FindConversationRequest(ExchangeService service)
      throws Exception {
    super(service);
  }


  /**
   * Gets or sets the view controlling the number of conversations returned.
   */
  protected ConversationIndexedItemView getIndexedItemView() {
    return this.view;
  }

  public void setIndexedItemView(ConversationIndexedItemView value) {
    this.view = value;
  }



  /**
   * Gets or sets the search filter.
   */
  protected SearchFilter.IsEqualTo getConversationViewFilter() {

    return this.searchFilter;
  }

  public void setConversationViewFilter(SearchFilter.IsEqualTo value) {
    this.searchFilter = value;

  }

  /**
   * Gets or sets folder id
   */
  protected FolderIdWrapper getFolderId() {
    return this.folderId;
  }

  public void setFolderId(FolderIdWrapper value) {
    this.folderId = value;
  }


  /**
   * Validate request.
   *
   * @throws Exception
   * @throws ServiceLocalException
   */
  @Override
  protected void validate() throws ServiceLocalException, Exception {
    super.validate();
    this.view.internalValidate(this);
  }


  /**
   * Writes XML attribute.
   *
   * @param writer The writer.
   * @throws ServiceXmlSerializationException
   */
  @Override
  protected void writeAttributesToXml(EwsServiceXmlWriter writer)
      throws ServiceXmlSerializationException {
    super.writeAttributesToXml(writer);
  }


  /**
   * Writes XML attribute.
   *
   * @param writer The writer.
   * @throws Exception
   */
  @Override
  protected void writeElementsToXml(EwsServiceXmlWriter writer)
      throws Exception {
    this.getIndexedItemView().writeToXml(writer);

    if (this.getConversationViewFilter() != null) {
      writer.writeStartElement(XmlNamespace.Messages,
          XmlElementNames.Restriction);
      this.getConversationViewFilter().writeToXml(writer);
      writer.writeEndElement(); // Restriction
    }

    this.getIndexedItemView().writeOrderByToXml(writer);

    writer.writeStartElement(XmlNamespace.Messages,
        XmlElementNames.ParentFolderId);
    this.getFolderId().writeToXml(writer);
    writer.writeEndElement();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected FindConversationResponse parseResponse(EwsServiceXmlReader reader)
      throws Exception {
    FindConversationResponse response = new FindConversationResponse();
    response.loadFromXml(reader,
        XmlElementNames.FindConversationResponse);
    return response;
  }

  /**
   * Gets the name of the XML element.
   *
   * @return XML element name.
   */
  @Override public String getXmlElementName() {
    return XmlElementNames.FindConversation;
  }

  /**
   * Gets the name of the response XML element.
   *
   * @return XML element name.
   */
  @Override
  protected String getResponseXmlElementName() {
    return XmlElementNames.FindConversationResponse;
  }

  /**
   * Gets the request version.
   *
   * @return Earliest Exchange version in which this request is supported.
   */
  @Override
  protected ExchangeVersion getMinimumRequiredServerVersion() {
    return ExchangeVersion.Exchange2010_SP1;
  }

  /**
   * Executes this request.
   *
   * @return Service response.
   * @throws Exception
   * @throws ServiceLocalException
   */
  public FindConversationResponse execute()
      throws ServiceLocalException, Exception {
    FindConversationResponse serviceResponse = internalExecute();
    serviceResponse.throwIfNecessary();
    return serviceResponse;
  }
}

