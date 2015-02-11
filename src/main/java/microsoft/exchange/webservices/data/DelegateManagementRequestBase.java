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

package microsoft.exchange.webservices.data;

/**
 * Represents an abstract delegate management request.
 *
 * @param <TResponse> The type of the response.
 */
abstract class DelegateManagementRequestBase
    <TResponse extends DelegateManagementResponse>
    extends SimpleServiceRequestBase {

  /**
   * The mailbox.
   */
  private Mailbox mailbox;

  /**
   * Initializes a new instance of the class.
   *
   * @param service the service
   */
  protected DelegateManagementRequestBase(ExchangeService service)
      throws Exception {
    super(service);
  }

  /**
   * Validate request.
   *
   * @throws microsoft.exchange.webservices.data.ServiceLocalException the service local exception
   * @throws Exception                                                 the exception
   */
  @Override
  protected void validate() throws ServiceLocalException, Exception {
    super.validate();
    EwsUtilities.validateParam(this.getMailbox(), "Mailbox");
  }

  /**
   * Writes XML elements.
   *
   * @param writer the writer
   * @throws Exception the exception
   */
  @Override
  protected void writeElementsToXml(EwsServiceXmlWriter writer)
      throws Exception {
    this.getMailbox().writeToXml(writer, XmlNamespace.Messages,
                                 XmlElementNames.Mailbox);
  }

  /**
   * Creates the response.
   *
   * @return Response object.
   */
  protected abstract TResponse createResponse();

  /**
   * Parses the response.
   *
   * @param reader the reader
   * @return Response object.
   * @throws Exception the exception
   */
  @Override
  protected Object parseResponse(EwsServiceXmlReader reader)
      throws Exception {
    DelegateManagementResponse response = this.createResponse();
    response.loadFromXml(reader, this.getResponseXmlElementName());
    return response;
  }

  /**
   * Executes this request.
   *
   * @return Response object.
   * @throws Exception the exception
   */
  protected TResponse execute() throws Exception {
    TResponse serviceResponse = (TResponse) this.internalExecute();
    serviceResponse.throwIfNecessary();
    return serviceResponse;
  }

  /**
   * Gets  the mailbox. <value>The mailbox.</value>
   *
   * @return the mailbox
   */
  public Mailbox getMailbox() {
    return this.mailbox;
  }

  /**
   * Sets the mailbox.
   *
   * @param mailbox the new mailbox
   */
  public void setMailbox(Mailbox mailbox) {
    this.mailbox = mailbox;
  }
}
