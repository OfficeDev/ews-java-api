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

import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.XmlAttributeNames;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.service.error.ServiceErrorHandling;
import microsoft.exchange.webservices.data.core.response.ConvertIdResponse;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.misc.IdFormat;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlSerializationException;
import microsoft.exchange.webservices.data.misc.id.AlternateIdBase;

import javax.xml.stream.XMLStreamException;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a ConvertId request.
 */
public final class ConvertIdRequest extends
    MultiResponseServiceRequest<ConvertIdResponse> {

  /**
   * The destination format.
   */
  private IdFormat destinationFormat = IdFormat.EwsId;

  /**
   * The ids.
   */
  private List<AlternateIdBase> ids = new ArrayList<AlternateIdBase>();

  /**
   * Initializes a new instance of the class.
   *
   * @param service           the service
   * @param errorHandlingMode the error handling mode
   * @throws Exception
   */
  public ConvertIdRequest(ExchangeService service, ServiceErrorHandling errorHandlingMode)
      throws Exception {
    super(service, errorHandlingMode);
  }

  /**
   * Initializes a new instance of the class.
   *
   * @param service       the service
   * @param responseIndex the response index
   * @return the convert id response
   */
  @Override
  protected ConvertIdResponse createServiceResponse(ExchangeService service,
      int responseIndex) {
    return new ConvertIdResponse();
  }

  /**
   * Gets the name of the response XML element.
   *
   * @return XML element name.
   */
  @Override
  protected String getResponseXmlElementName() {
    return XmlElementNames.ConvertIdResponse;
  }

  /**
   * Gets the name of the response message XML element.
   *
   * @return XML element name.
   */
  @Override
  protected String getResponseMessageXmlElementName() {
    return XmlElementNames.ConvertIdResponseMessage;
  }

  /**
   * Gets the expected response message count.
   *
   * @return Number of expected response messages.
   */
  @Override
  protected int getExpectedResponseMessageCount() {
    return this.ids.size();
  }

  /**
   * Gets the name of the XML element.
   *
   * @return XML element name.
   */
  @Override public String getXmlElementName() {
    return XmlElementNames.ConvertId;
  }

  /**
   * Validate request.
   *
   * @throws Exception the exception
   */
  @Override
  protected void validate() throws Exception {
    super.validate();
    EwsUtilities.validateParamCollection(this.ids.iterator(), "Ids");
  }

  /**
   * Writes XML elements.
   *
   * @param writer the writer
   * @throws XMLStreamException the XML stream exception
   * @throws ServiceXmlSerializationException the service xml serialization exception
   */
  @Override
  protected void writeElementsToXml(EwsServiceXmlWriter writer)
      throws XMLStreamException, ServiceXmlSerializationException {
    writer.writeAttributeValue(XmlAttributeNames.DestinationFormat,
        this.destinationFormat);
    writer.writeStartElement(XmlNamespace.Messages,
        XmlElementNames.SourceIds);
    for (AlternateIdBase alternateId : this.ids) {
      alternateId.writeToXml(writer);
    }

    writer.writeEndElement(); // SourceIds
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
   * Gets the destination format.
   *
   * @return the destination format
   */
  public IdFormat getDestinationFormat() {
    return this.destinationFormat;
  }

  /**
   * Sets the destination format.
   *
   * @param destinationFormat the new destination format
   */
  public void setDestinationFormat(IdFormat destinationFormat) {
    this.destinationFormat = destinationFormat;
  }

  /**
   * Gets the ids.
   *
   * @return the ids
   */
  public List<AlternateIdBase> getIds() {
    return this.ids;
  }
}
