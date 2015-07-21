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
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.service.error.ServiceErrorHandling;
import microsoft.exchange.webservices.data.core.response.GetServerTimeZonesResponse;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlSerializationException;

import javax.xml.stream.XMLStreamException;

/**
 * Represents a GetServerTimeZones request.
 */
public final class GetServerTimeZonesRequest extends
    MultiResponseServiceRequest<GetServerTimeZonesResponse> {

  /**
   * The ids.
   */
  private Iterable<String> ids;

  /**
   * Gets the XML element name associated with the transition.
   *
   * @throws Exception the exception
   */
  @Override
  protected void validate() throws Exception {
    super.validate();
    if (this.ids != null) {
      EwsUtilities.validateParamCollection(this.getIds().iterator(), "Ids");
    }
  }

  /**
   * Initializes a new instance of the "GetServerTimeZonesRequest" class.
   *
   * @param service the service
   * @throws Exception
   */
  public GetServerTimeZonesRequest(ExchangeService service)
      throws Exception {
    super(service, ServiceErrorHandling.ThrowOnError);
  }

  /**
   * Creates the service response.
   *
   * @param service       the service
   * @param responseIndex the response index
   * @return Service response.
   */
  @Override
  protected GetServerTimeZonesResponse createServiceResponse(
      ExchangeService service, int responseIndex) {
    return new GetServerTimeZonesResponse();
  }

  /**
   * Gets the name of the response message XML element.
   *
   * @return XML element name,
   */
  @Override
  protected String getResponseMessageXmlElementName() {
    return XmlElementNames.GetServerTimeZonesResponseMessage;
  }

  /**
   * Gets the expected response message count.
   *
   * @return Number of expected response messages.
   */
  @Override
  protected int getExpectedResponseMessageCount() {
    return 1;
  }

  /**
   * Gets the name of the XML element.
   *
   * @return XML element name,
   */
  @Override public String getXmlElementName() {
    return XmlElementNames.GetServerTimeZones;
  }

  /**
   * Gets the name of the response XML element.
   *
   * @return XML element name,
   */
  @Override
  protected String getResponseXmlElementName() {
    return XmlElementNames.GetServerTimeZonesResponse;
  }

  /**
   * Gets the minimum server version required to process this request.
   *
   * @return Exchange server version.
   */
  @Override
  protected ExchangeVersion getMinimumRequiredServerVersion() {
    return ExchangeVersion.Exchange2010;
  }

  /**
   * Writes XML elements.
   *
   * @param writer the writer
   * @throws ServiceXmlSerializationException the service xml serialization exception
   * @throws XMLStreamException the XML stream exception
   */
  @Override
  protected void writeElementsToXml(EwsServiceXmlWriter writer)
      throws ServiceXmlSerializationException, XMLStreamException {
    if (this.getIds() != null) {
      writer
          .writeStartElement(XmlNamespace.Messages,
              XmlElementNames.Ids);

      for (String id : this.getIds()) {
        writer.writeElementValue(XmlNamespace.Types,
            XmlElementNames.Id, id);
      }

      writer.writeEndElement(); // Ids
    }
  }

  /**
   * Gets  the ids of the time zones that should be returned by the
   * server.
   *
   * @return the ids
   */
  protected Iterable<String> getIds() {
    return this.ids;
  }

  /**
   * Sets the ids.
   *
   * @param ids the new ids
   */
  protected void setIds(Iterable<String> ids) {
    this.ids = ids;
  }
}
