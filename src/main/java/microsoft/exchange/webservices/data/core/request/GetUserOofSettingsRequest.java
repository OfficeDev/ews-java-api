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
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.response.GetUserOofSettingsResponse;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.property.OofExternalAudience;
import microsoft.exchange.webservices.data.core.enumeration.misc.error.ServiceError;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlSerializationException;
import microsoft.exchange.webservices.data.property.complex.availability.OofSettings;

import javax.xml.stream.XMLStreamException;

/**
 * Represents a GetUserOofSettings request.
 */
public final class GetUserOofSettingsRequest extends SimpleServiceRequestBase<GetUserOofSettingsResponse> {

  /**
   * The smtp address.
   */
  private String smtpAddress;

  /**
   * Gets the name of the XML element.
   *
   * @return XML element name.
   */
  @Override public String getXmlElementName() {
    return XmlElementNames.GetUserOofSettingsRequest;
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
  }

  /**
   * Validate request.
   *
   * @param writer the writer
   * @throws XMLStreamException the XML stream exception
   * @throws ServiceXmlSerializationException the service xml serialization exception
   */
  @Override
  protected void writeElementsToXml(EwsServiceXmlWriter writer)
      throws XMLStreamException, ServiceXmlSerializationException {
    writer.writeStartElement(XmlNamespace.Types, XmlElementNames.Mailbox);
    writer.writeElementValue(XmlNamespace.Types, XmlElementNames.Address,
        this.getSmtpAddress());
    writer.writeEndElement(); // Mailbox
  }

  /**
   * Gets the name of the response XML element.
   *
   * @return XML element name
   */
  @Override
  protected String getResponseXmlElementName() {
    return XmlElementNames.GetUserOofSettingsResponse;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected GetUserOofSettingsResponse parseResponse(EwsServiceXmlReader reader)
      throws Exception {
    GetUserOofSettingsResponse serviceResponse =
        new GetUserOofSettingsResponse();

    serviceResponse.loadFromXml(reader, XmlElementNames.ResponseMessage);
    if (serviceResponse.getErrorCode() == ServiceError.NoError) {
      reader.readStartElement(XmlNamespace.Types,
          XmlElementNames.OofSettings);

      serviceResponse.setOofSettings(new OofSettings());
      serviceResponse.getOofSettings().loadFromXml(reader,
          reader.getLocalName());

      serviceResponse.getOofSettings().setAllowExternalOof(
          reader.readElementValue(OofExternalAudience.class,
              XmlNamespace.Messages,
              XmlElementNames.AllowExternalOof));
    }

    return serviceResponse;
  }

  /**
   * Gets the request version.
   *
   * @return Earliest Exchange version in which this request is supported
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
  public GetUserOofSettingsRequest(ExchangeService service)
      throws Exception {
    super(service);
  }

  /**
   * Executes this request.
   *
   * @return Service response.
   * @throws Exception the exception
   */
  public GetUserOofSettingsResponse execute() throws Exception {
    GetUserOofSettingsResponse serviceResponse = internalExecute();
    serviceResponse.throwIfNecessary();
    return serviceResponse;
  }

  /**
   * Gets  the SMTP address.
   *
   * @return the smtp address
   */
  protected String getSmtpAddress() {
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

}
