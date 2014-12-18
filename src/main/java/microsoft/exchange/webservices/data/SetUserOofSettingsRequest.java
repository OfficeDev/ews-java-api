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
 * Represents a SetUserOofSettings request.
 */
final class SetUserOofSettingsRequest extends SimpleServiceRequestBase {

  /**
   * The smtp address.
   */
  private String smtpAddress;

  /**
   * The oof settings.
   */
  private OofSettings oofSettings;

  /**
   * Gets the name of the XML element.
   *
   * @return XML element name.
   */
  @Override
  protected String getXmlElementName() {
    return XmlElementNames.SetUserOofSettingsRequest;
  }

  /**
   * Validate request.
   *
   * @throws Exception the exception
   */
  @Override
  protected void validate() throws Exception {
    super.validate();

    EwsUtilities.validateParam(this.getSmtpAddress(), "SmtpAddress");
    EwsUtilities.validateParam(this.getOofSettings(), "OofSettings");
  }

  /**
   * Writes the elements to XML.
   *
   * @param writer the writer
   * @throws Exception the exception
   */
  @Override
  protected void writeElementsToXml(EwsServiceXmlWriter writer)
      throws Exception {
    writer.writeStartElement(XmlNamespace.Types, XmlElementNames.Mailbox);
    writer.writeElementValue(XmlNamespace.Types, XmlElementNames.Address,
        this.getSmtpAddress());
    writer.writeEndElement(); // Mailbox

    this.getOofSettings().writeToXml(writer,
        XmlElementNames.UserOofSettings);
  }

  /**
   * Gets the name of the response XML element.
   *
   * @return XML element name
   */
  @Override
  protected String getResponseXmlElementName() {
    return XmlElementNames.SetUserOofSettingsResponse;
  }

  /**
   * Parses the response.
   *
   * @param reader the reader
   * @return Service response
   * @throws Exception the exception
   */
  @Override
  protected Object parseResponse(EwsServiceXmlReader reader)
      throws Exception {
    ServiceResponse serviceResponse = new ServiceResponse();
    serviceResponse.loadFromXml(reader, XmlElementNames.ResponseMessage);
    return serviceResponse;
  }

  /**
   * Gets the request version.
   *
   * @return Earliest Exchange version in which this request is supported.
   */
  @Override
  protected ExchangeVersion getMinimumRequiredServerVersion() {
    return ExchangeVersion.Exchange2007_SP1;
  }

  /**
   * Initializes a new instance of the class.
   *
   * @param service the service
   * @throws Exception
   */
  protected SetUserOofSettingsRequest(ExchangeService service)
      throws Exception {
    super(service);
  }

  /**
   * Executes this request.
   *
   * @return Service response
   * @throws Exception the exception
   */
  protected ServiceResponse execute() throws Exception {
    ServiceResponse serviceResponse = (ServiceResponse) this
        .internalExecute();
    serviceResponse.throwIfNecessary();
    return serviceResponse;
  }

  /**
   * Gets the SMTP address.
   *
   * @return the smtp address
   */
  public String getSmtpAddress() {
    return this.smtpAddress;
  }

  /**
   * Sets the smtp address.
   *
   * @param smtpAddress the new smtp address
   */
  public void setSmtpAddress(String smtpAddress) {
    this.smtpAddress = smtpAddress;
  }

  /**
   * Gets the oof settings.
   *
   * @return the oof settings
   */
  public OofSettings getOofSettings() {
    return this.oofSettings;
  }

  /**
   * Sets the oof settings.
   *
   * @param oofSettings the new oof settings
   */
  public void setOofSettings(OofSettings oofSettings) {
    this.oofSettings = oofSettings;
  }

}
