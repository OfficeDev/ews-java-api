package microsoft.exchange.webservices.data;

import org.w3c.dom.Node;

import javax.xml.stream.XMLStreamException;

/**
 * Defines the ExecuteDiagnosticMethodRequest class.
 */
final class ExecuteDiagnosticMethodRequest extends
    MultiResponseServiceRequest<ExecuteDiagnosticMethodResponse> {

  private Node xmlNode;
  private String verb;

  /**
   * Initializes a new instance of the ExecuteDiagnosticMethodRequest class.
   *
   * @throws Exception
   */
  protected ExecuteDiagnosticMethodRequest(ExchangeService service)
      throws Exception {
    super(service, ServiceErrorHandling.ThrowOnError);
  }

  /**
   * Gets the name of the XML element.
   *
   * @return XmlElementNames
   */
  @Override
  protected String getXmlElementName() {
    return XmlElementNames.ExecuteDiagnosticMethod;
  }

  /**
   * Writes XML elements.
   *
   * @param writer The writer
   * @throws javax.xml.stream.XMLStreamException
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
  protected void setVerb(String value) {
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
  protected void setParameter(Node value) {
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
