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
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.service.error.ServiceErrorHandling;
import microsoft.exchange.webservices.data.core.response.ExecuteDiagnosticMethodResponse;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlSerializationException;
import org.w3c.dom.Node;

import javax.xml.stream.XMLStreamException;

/**
 * Defines the ExecuteDiagnosticMethodRequest class.
 */
public final class ExecuteDiagnosticMethodRequest extends
    MultiResponseServiceRequest<ExecuteDiagnosticMethodResponse> {

  private Node xmlNode;
  private String verb;

  /**
   * Initializes a new instance of the ExecuteDiagnosticMethodRequest class.
   *
   * @throws Exception
   */
  public ExecuteDiagnosticMethodRequest(ExchangeService service)
      throws Exception {
    super(service, ServiceErrorHandling.ThrowOnError);
  }

  /**
   * Gets the name of the XML element.
   *
   * @return XmlElementNames
   */
  @Override public String getXmlElementName() {
    return XmlElementNames.ExecuteDiagnosticMethod;
  }

  /**
   * Writes XML elements.
   *
   * @param writer The writer
   * @throws XMLStreamException the XML stream exception
   * @throws ServiceXmlSerializationException
   */
  @Override
  protected void writeElementsToXml(EwsServiceXmlWriter writer)
      throws ServiceXmlSerializationException, XMLStreamException {
    writer.writeElementValue(XmlNamespace.Messages,
        XmlElementNames.Verb, this.getVerb());

    writer.writeStartElement(XmlNamespace.Messages,
        XmlElementNames.Parameter);
    writer.writeNode(this.getParameter());
    writer.writeEndElement();
  }

  /**
   * Gets the name of the response XML element.
   *
   * @return XML element name
   */
  @Override
  protected String getResponseXmlElementName() {
    return XmlElementNames.ExecuteDiagnosticMethodResponse;
  }

  /**
   * Gets the request version.
   *
   * @return Earliest Exchange version in which this request is supported.
   */
  @Override
  protected ExchangeVersion getMinimumRequiredServerVersion() {
    /** Set to 2007_SP1 because only test code
     * will be using this method (it's marked internal.
     * If it were marked for 2010_SP1, test cases
     * would have to create new ExchangeService instances
     * when using this method for tests running under older versions.
     */
    return ExchangeVersion.Exchange2007_SP1;
  }

  /**
   * Gets the verb of the method to execute.
   */
  protected String getVerb() {
    return verb;
  }

  /**
   * Sets the verb of the method to execute.
   */
  public void setVerb(String value) {
    this.verb = value;
  }

  /**
   * Gets the parameter to the executing method.
   */
  protected Node getParameter() {
    return xmlNode;
  }

  /**
   * Sets the parameter to the executing method.
   */
  public void setParameter(Node value) {
    this.xmlNode = value;
  }

  /**
   * Creates the service response.
   *
   * @param service       The service
   * @param responseIndex Index of the response
   * @return Service response
   */
  @Override
  protected ExecuteDiagnosticMethodResponse createServiceResponse(ExchangeService service,
      int responseIndex) {
    return new ExecuteDiagnosticMethodResponse(service);
  }

  /**
   * Gets the name of the response message XML element.
   *
   * @return XmlElementNames
   */
  @Override
  protected String getResponseMessageXmlElementName() {
    return XmlElementNames.ExecuteDiagnosticMethodResponseMEssage;
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
}
