/**************************************************************************
 Exchange Web Services Java API
 Copyright (c) Microsoft Corporation
 All rights reserved.
 MIT License
 Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the ""Software""), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 THE SOFTWARE IS PROVIDED *AS IS*, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 **************************************************************************/

package microsoft.exchange.webservices.data;

/**
 * Represents a GetPhoneCall request.
 */
final class GetPhoneCallRequest extends SimpleServiceRequestBase {

  /**
   * The id.
   */
  private PhoneCallId id;

  /**
   * Initializes a new instance of the GetPhoneCallRequest class.
   *
   * @param service the service
   * @throws Exception
   */
  protected GetPhoneCallRequest(ExchangeService service)
      throws Exception {
    super(service);
  }

  /**
   * Gets the name of the XML element.
   *
   * @return XML element name.
   */
  @Override
  protected String getXmlElementName() {
    return XmlElementNames.GetPhoneCall;
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
    this.id.writeToXml(writer, XmlNamespace.Messages,
        XmlElementNames.PhoneCallId);
  }

  /**
   * Gets the name of the response XML element.
   *
   * @return XML element name.
   */
  @Override
  protected String getResponseXmlElementName() {
    return XmlElementNames.GetPhoneCallResponse;
  }

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
    GetPhoneCallResponse response = new GetPhoneCallResponse(getService());
    response.loadFromXml(reader, XmlElementNames.GetPhoneCallResponse);
    return response;
  }

  /**
   * Gets the request version.
   *
   * @return Earliest Exchange version in which this request is supported.
   */
  @Override
  protected ExchangeVersion getMinimumRequiredServerVersion() {
    return ExchangeVersion.Exchange2010;
  }

  /**
   * Executes this request.
   *
   * @return Service response.
   * @throws Exception the exception
   */
  protected GetPhoneCallResponse execute() throws Exception {
    GetPhoneCallResponse serviceResponse = (GetPhoneCallResponse) this
        .internalExecute();
    serviceResponse.throwIfNecessary();
    return serviceResponse;
  }

  /**
   * Gets the Id of the phone call.
   *
   * @return the id
   */
  protected PhoneCallId getId() {
    return id;
  }

  /**
   * Sets the id.
   *
   * @param id the new id
   */
  protected void setId(PhoneCallId id) {
    this.id = id;
  }

}
