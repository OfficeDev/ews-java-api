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

import javax.xml.stream.XMLStreamException;

/**
 * Represents a GetInboxRules request.
 */
final class GetInboxRulesRequest extends SimpleServiceRequestBase {

  /**
   * The smtp address of the mailbox from which to get the inbox rules.
   */
  private String mailboxSmtpAddress;

  /**
   * Initializes a new instance of the GetInboxRulesRequest class.
   *
   * @param service The service.
   * @throws Exception
   */
  protected GetInboxRulesRequest(ExchangeService service) throws Exception {
    super(service);
  }

  /**
   * Gets or sets the address of the mailbox from which to get the inbox rules.
   *
   * @return the mailboxSmtpAddress
   */
  protected String getmailboxSmtpAddress() {
    return this.mailboxSmtpAddress;
  }

  /**
   * sets the address of the mailbox from which to get the inbox rules.
   */
  protected void setmailboxSmtpAddress(String value) {
    this.mailboxSmtpAddress = value;
  }

  /**
   * Gets the name of the XML element.
   *
   * @return XML element name.
   */
  @Override
  protected String getXmlElementName() {
    return XmlElementNames.GetInboxRules;
  }

  /**
   * Writes XML elements.
   *
   * @param writer The writer.
   * @throws javax.xml.stream.XMLStreamException
   * @throws ServiceXmlSerializationException
   */
  @Override
  protected void writeElementsToXml(EwsServiceXmlWriter writer)
      throws ServiceXmlSerializationException, XMLStreamException {
    if (!(this.mailboxSmtpAddress == null ||
        this.mailboxSmtpAddress.isEmpty())) {
      writer.writeElementValue(
          XmlNamespace.Messages,
          XmlElementNames.MailboxSmtpAddress,
          this.mailboxSmtpAddress);
    }
  }

  /**
   * Gets the name of the response XML element.
   *
   * @return XML element name.
   */
  @Override
  protected String getResponseXmlElementName() {
    return XmlElementNames.GetInboxRulesResponse;
  }

  /**
   * Parses the response.
   *
   * @param reader The reader.
   * @return Response object.
   * @throws Exception
   */
  @Override
  protected Object parseResponse(EwsServiceXmlReader reader)
      throws Exception {
    GetInboxRulesResponse response = new GetInboxRulesResponse();
    response.loadFromXml(reader, XmlElementNames.GetInboxRulesResponse);
    return response;
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
  protected GetInboxRulesResponse execute()
      throws ServiceLocalException, Exception {
    GetInboxRulesResponse serviceResponse =
        (GetInboxRulesResponse) this.internalExecute();
    serviceResponse.throwIfNecessary();
    return serviceResponse;
  }
}
