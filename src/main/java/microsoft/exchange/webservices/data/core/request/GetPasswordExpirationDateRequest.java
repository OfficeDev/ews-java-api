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
import microsoft.exchange.webservices.data.core.response.GetPasswordExpirationDateResponse;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;

public final class GetPasswordExpirationDateRequest extends SimpleServiceRequestBase<GetPasswordExpirationDateResponse> {

  @Override
  protected ExchangeVersion getMinimumRequiredServerVersion() {
    // TODO Auto-generated method stub
    return ExchangeVersion.Exchange2010_SP1;
  }

  /**
   * Initializes a new instance of the GetPasswordExpirationDateRequest class
   *
   * @throws Exception
   */
  public GetPasswordExpirationDateRequest(ExchangeService service) throws Exception {
    super(service);
  }

  protected String getResponseXmlElementName() {
    return XmlElementNames.GetPasswordExpirationDateResponse;
  }

  /**
   * Gets the name of the XML Element.
   * returns XML element name
   */
  public String getXmlElementName() {
    return XmlElementNames.GetPasswordExpirationDateRequest;
  }

  @Override
  protected void writeElementsToXml(EwsServiceXmlWriter writer) throws Exception {
    writer.writeElementValue(XmlNamespace.Messages,
        XmlElementNames.MailboxSmtpAddress,
        this.getMailboxSmtpAddress());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected GetPasswordExpirationDateResponse parseResponse(EwsServiceXmlReader reader) throws Exception {
    GetPasswordExpirationDateResponse response = new GetPasswordExpirationDateResponse();
    response.loadFromXml(reader, XmlElementNames.GetPasswordExpirationDateResponse);
    return response;
  }

  /**
   * Gets the request version
   * @return Earliest Exchange version in which this request is supported.
   *//*
        protected ExchangeVersion getMinimumRequiredServerVersion(){
		return ExchangeVersion.Exchange2010_SP1;
	}*/

  /**
   * Executes this request.
   *
   * @return Service response.
   */
  public GetPasswordExpirationDateResponse execute() throws Exception {
    GetPasswordExpirationDateResponse serviceResponse = internalExecute();
    serviceResponse.throwIfNecessary();
    return serviceResponse;
  }

  /**
   * Gets mailbox smtp address.
   *
   * @return The mailbox smtp address.
   */
  protected String getMailboxSmtpAddress() {
    return this.mailboxSmtpAddress;
  }

  public void setMailboxSmtpAddress(String mailboxSmtpAddress) {
    this.mailboxSmtpAddress = mailboxSmtpAddress;
  }

  private String mailboxSmtpAddress;
}
