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
import microsoft.exchange.webservices.data.core.ExchangeServerInfo;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.XmlAttributeNames;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.response.ServiceResponse;
import microsoft.exchange.webservices.data.core.enumeration.misc.DateTimePrecision;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.misc.TraceFlags;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.exception.http.EWSHttpException;
import microsoft.exchange.webservices.data.core.exception.http.HttpErrorException;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceLocalException;
import microsoft.exchange.webservices.data.core.exception.service.remote.ServiceRequestException;
import microsoft.exchange.webservices.data.core.exception.service.remote.ServiceResponseException;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceVersionException;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlDeserializationException;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlSerializationException;
import microsoft.exchange.webservices.data.core.exception.xml.XmlException;
import microsoft.exchange.webservices.data.misc.SoapFaultDetails;
import microsoft.exchange.webservices.data.security.XmlNodeType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.stream.XMLStreamException;
import javax.xml.ws.http.HTTPException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.InflaterInputStream;

/**
 * Represents an abstract service request.
 */
public abstract class ServiceRequestBase<T> {

  private static final Log LOG = LogFactory.getLog(ServiceRequestBase.class);

  /**
   * The service.
   */
  private ExchangeService service;

  // Methods for subclasses to override

  /**
   * Gets the name of the XML element.
   *
   * @return XML element name
   */
  public abstract String getXmlElementName();

  /**
   * Gets the name of the response XML element.
   *
   * @return XML element name
   */
  protected abstract String getResponseXmlElementName();

  /**
   * Gets the minimum server version required to process this request.
   *
   * @return Exchange server version.
   */
  protected abstract ExchangeVersion getMinimumRequiredServerVersion();

  /**
   * Parses the response.
   *
   * @param reader The reader.
   * @return the Response Object.
   * @throws Exception the exception
   */
  protected abstract T parseResponse(EwsServiceXmlReader reader) throws Exception;

  /**
   * Writes XML elements.
   *
   * @param writer The writer.
   * @throws Exception the exception
   */
  protected abstract void writeElementsToXml(EwsServiceXmlWriter writer) throws Exception;

  /**
   * Validate request.
   *
   * @throws ServiceLocalException the service local exception
   * @throws Exception             the exception
   */
  protected void validate() throws Exception {
    this.service.validate();
  }

  /**
   * Writes XML body.
   *
   * @param writer The writer.
   * @throws Exception the exception
   */
  protected void writeBodyToXml(EwsServiceXmlWriter writer) throws Exception {
    writer.writeStartElement(XmlNamespace.Messages, this.getXmlElementName());

    this.writeAttributesToXml(writer);
    this.writeElementsToXml(writer);

    writer.writeEndElement(); // m:this.GetXmlElementName()
  }

  /**
   * Writes XML attribute. Subclass will override if it has XML attribute.
   *
   * @param writer The writer.
   * @throws ServiceXmlSerializationException the service xml serialization exception
   */
  protected void writeAttributesToXml(EwsServiceXmlWriter writer) throws ServiceXmlSerializationException {
  }

  /**
   * Initializes a new instance.
   *
   * @param service The service.
   * @throws ServiceVersionException the service version exception
   */
  protected ServiceRequestBase(ExchangeService service) throws ServiceVersionException {
    this.service = service;
    this.throwIfNotSupportedByRequestedServerVersion();
  }

  /**
   * Gets the service.
   *
   * @return The service.
   */
  public ExchangeService getService() {
    return service;
  }

  /**
   * Throw exception if request is not supported in requested server version.
   *
   * @throws ServiceVersionException the service version exception
   */
  protected void throwIfNotSupportedByRequestedServerVersion() throws ServiceVersionException {
    if (this.service.getRequestedServerVersion().ordinal() < this.getMinimumRequiredServerVersion()
        .ordinal()) {
      throw new ServiceVersionException(String.format(
          "The service request %s is only valid for Exchange version %s or later.", this.getXmlElementName(),
          this.getMinimumRequiredServerVersion()));
    }
  }

  // HttpWebRequest-based implementation

  /**
   * Writes XML.
   *
   * @param writer The writer.
   * @throws Exception the exception
   */
  protected void writeToXml(EwsServiceXmlWriter writer) throws Exception {
    writer.writeStartDocument();
    writer.writeStartElement(XmlNamespace.Soap, XmlElementNames.SOAPEnvelopeElementName);
    writer.writeAttributeValue("xmlns", EwsUtilities.getNamespacePrefix(XmlNamespace.Soap),
                               EwsUtilities.getNamespaceUri(XmlNamespace.Soap));
    writer.writeAttributeValue("xmlns", EwsUtilities.EwsXmlSchemaInstanceNamespacePrefix,
                               EwsUtilities.EwsXmlSchemaInstanceNamespace);
    writer.writeAttributeValue("xmlns", EwsUtilities.EwsMessagesNamespacePrefix,
                               EwsUtilities.EwsMessagesNamespace);
    writer.writeAttributeValue("xmlns", EwsUtilities.EwsTypesNamespacePrefix, EwsUtilities.EwsTypesNamespace);
    if (writer.isRequireWSSecurityUtilityNamespace()) {
      writer.writeAttributeValue("xmlns", EwsUtilities.WSSecurityUtilityNamespacePrefix,
                                 EwsUtilities.WSSecurityUtilityNamespace);
    }

    writer.writeStartElement(XmlNamespace.Soap, XmlElementNames.SOAPHeaderElementName);

    if (this.service.getCredentials() != null) {
      this.service.getCredentials().emitExtraSoapHeaderNamespaceAliases(writer.getInternalWriter());
    }

    // Emit the RequestServerVersion header
    writer.writeStartElement(XmlNamespace.Types, XmlElementNames.RequestServerVersion);
    writer.writeAttributeValue(XmlAttributeNames.Version, this.getRequestedServiceVersionString());
    writer.writeEndElement(); // RequestServerVersion

		/*
                 * if ((this.getService().getRequestedServerVersion().ordinal() ==
		 * ExchangeVersion.Exchange2007_SP1.ordinal() ||
		 * this.EmitTimeZoneHeader()) &&
		 * (!this.getService().getExchange2007CompatibilityMode())) {
		 * writer.writeStartElement(XmlNamespace.Types,
		 * XmlElementNames.TimeZoneContext);
		 * 
		 * this.getService().TimeZoneDefinition().WriteToXml(writer);
		 * 
		 * writer.WriteEndElement(); // TimeZoneContext
		 * 
		 * writer.IsTimeZoneHeaderEmitted = true; }
		 */

    if (this.service.getPreferredCulture() != null) {
      writer.writeElementValue(XmlNamespace.Types, XmlElementNames.MailboxCulture,
                               this.service.getPreferredCulture().getDisplayName());
    }

    /** Emit the DateTimePrecision header */

    if (this.getService().getDateTimePrecision().ordinal() != DateTimePrecision.Default.ordinal()) {
      writer.writeElementValue(XmlNamespace.Types, XmlElementNames.DateTimePrecision,
                               this.getService().getDateTimePrecision().toString());
    }
    if (this.service.getImpersonatedUserId() != null) {
      this.service.getImpersonatedUserId().writeToXml(writer);
    }

    if (this.service.getCredentials() != null) {
      this.service.getCredentials()
          .serializeExtraSoapHeaders(writer.getInternalWriter(), this.getXmlElementName());
    }
    this.service.doOnSerializeCustomSoapHeaders(writer.getInternalWriter());

    writer.writeEndElement(); // soap:Header

    writer.writeStartElement(XmlNamespace.Soap, XmlElementNames.SOAPBodyElementName);

    this.writeBodyToXml(writer);

    writer.writeEndElement(); // soap:Body
    writer.writeEndElement(); // soap:Envelope
    writer.flush();
  }

  /**
   * Gets st ring representation of requested server version. In order to support E12 RTM servers,
   * ExchangeService has another flag indicating that we should use "Exchange2007" as the server version
   * string rather than Exchange2007_SP1.
   *
   * @return String representation of requested server version.
   */
  private String getRequestedServiceVersionString() {
    if (this.service.getRequestedServerVersion() == ExchangeVersion.Exchange2007_SP1 && this.service
        .getExchange2007CompatibilityMode()) {
      return "Exchange2007";
    } else {
      return this.service.getRequestedServerVersion().toString();
    }
  }

  /**
   * Gets the response stream (may be wrapped with GZip/Deflate stream to decompress content).
   *
   * @param request HttpWebRequest object from which response stream can be read.
   * @return ResponseStream
   * @throws java.io.IOException Signals that an I/O exception has occurred.
   * @throws EWSHttpException    the EWS http exception
   */
  protected static InputStream getResponseStream(HttpWebRequest request)
      throws IOException, EWSHttpException {
    String contentEncoding = "";

    if (null != request.getContentEncoding()) {
      contentEncoding = request.getContentEncoding().toLowerCase();
    }

    InputStream responseStream;

    if (contentEncoding.contains("gzip")) {
      responseStream = new GZIPInputStream(request.getInputStream());
    } else if (contentEncoding.contains("deflate")) {
      responseStream = new InflaterInputStream(request.getInputStream());
    } else {
      responseStream = request.getInputStream();
    }
    return responseStream;
  }

  /**
   * Traces the response.
   *
   * @param request      the response
   * @param memoryStream the response content in a MemoryStream
   * @throws XMLStreamException the XML stream exception
   * @throws IOException        signals that an I/O exception has occurred
   * @throws EWSHttpException   the EWS http exception
   */
  protected void traceResponse(HttpWebRequest request, ByteArrayOutputStream memoryStream)
      throws XMLStreamException, IOException, EWSHttpException {

    this.service.processHttpResponseHeaders(TraceFlags.EwsResponseHttpHeaders, request);
    String contentType = request.getResponseContentType();

    if (!isNullOrEmpty(contentType) && (contentType.startsWith("text/") || contentType
        .startsWith("application/soap"))) {
      this.service.traceXml(TraceFlags.EwsResponse, memoryStream);
    } else {
      this.service.traceMessage(TraceFlags.EwsResponse, "Non-textual response");
    }

  }

  /**
   * Gets the response error stream.
   *
   * @param request the request
   * @return the response error stream
   * @throws EWSHttpException    the EWS http exception
   * @throws java.io.IOException Signals that an I/O exception has occurred.
   */
  private static InputStream getResponseErrorStream(HttpWebRequest request)
      throws EWSHttpException, IOException {
    String contentEncoding = "";

    if (null != request.getContentEncoding()) {
      contentEncoding = request.getContentEncoding().toLowerCase();
    }

    InputStream responseStream;

    if (contentEncoding.contains("gzip")) {
      responseStream = new GZIPInputStream(request.getErrorStream());
    } else if (contentEncoding.contains("deflate")) {
      responseStream = new InflaterInputStream(request.getErrorStream());
    } else {
      responseStream = request.getErrorStream();
    }
    return responseStream;
  }

  /**
   * Reads the response.
   *
   * @param response HTTP web request
   * @return response response object
   * @throws Exception on error
   */
  protected T readResponse(HttpWebRequest response) throws Exception {
    T serviceResponse;

    if (!response.getResponseContentType().startsWith("text/xml")) {
      throw new ServiceRequestException("The response received from the service didn't contain valid XML.");
    }

    /**
     * If tracing is enabled, we read the entire response into a
     * MemoryStream so that we can pass it along to the ITraceListener. Then
     * we parse the response from the MemoryStream.
     */

    try {
      this.getService().processHttpResponseHeaders(TraceFlags.EwsResponseHttpHeaders, response);

      if (this.getService().isTraceEnabledFor(TraceFlags.EwsResponse)) {
        ByteArrayOutputStream memoryStream = new ByteArrayOutputStream();
        InputStream serviceResponseStream = ServiceRequestBase.getResponseStream(response);

        int data = serviceResponseStream.read();
        while (data != -1) {
          memoryStream.write(data);
          data = serviceResponseStream.read();
        }

        this.traceResponse(response, memoryStream);
        ByteArrayInputStream memoryStreamIn = new ByteArrayInputStream(memoryStream.toByteArray());
        EwsServiceXmlReader ewsXmlReader = new EwsServiceXmlReader(memoryStreamIn, this.getService());
        serviceResponse = this.readResponse(ewsXmlReader);
        serviceResponseStream.close();
        memoryStream.flush();
      } else {
        InputStream responseStream = ServiceRequestBase.getResponseStream(response);
        EwsServiceXmlReader ewsXmlReader = new EwsServiceXmlReader(responseStream, this.getService());
        serviceResponse = this.readResponse(ewsXmlReader);
      }

      return serviceResponse;
    } catch (HTTPException e) {
      if (e.getMessage() != null) {
        this.getService().processHttpResponseHeaders(TraceFlags.EwsResponseHttpHeaders, response);
      }
      throw new ServiceRequestException(String.format("The request failed. %s", e.getMessage()), e);
    } catch (IOException e) {
      throw new ServiceRequestException(String.format("The request failed. %s", e.getMessage()), e);
    } finally { // close the underlying response
      response.close();
    }
  }

  /**
   * Reads the response.
   *
   * @param ewsXmlReader The XML reader.
   * @return Service response.
   * @throws Exception the exception
   */
  protected T readResponse(EwsServiceXmlReader ewsXmlReader) throws Exception {
    T serviceResponse;
    this.readPreamble(ewsXmlReader);
    ewsXmlReader.readStartElement(XmlNamespace.Soap, XmlElementNames.SOAPEnvelopeElementName);
    this.readSoapHeader(ewsXmlReader);
    ewsXmlReader.readStartElement(XmlNamespace.Soap, XmlElementNames.SOAPBodyElementName);

    ewsXmlReader.readStartElement(XmlNamespace.Messages, this.getResponseXmlElementName());

    serviceResponse = this.parseResponse(ewsXmlReader);

    ewsXmlReader.readEndElementIfNecessary(XmlNamespace.Messages, this.getResponseXmlElementName());

    ewsXmlReader.readEndElement(XmlNamespace.Soap, XmlElementNames.SOAPBodyElementName);
    ewsXmlReader.readEndElement(XmlNamespace.Soap, XmlElementNames.SOAPEnvelopeElementName);
    return serviceResponse;
  }

  /**
   * Reads any preamble data not part of the core response.
   *
   * @param ewsXmlReader The EwsServiceXmlReader.
   * @throws Exception on error
   */
  protected void readPreamble(EwsServiceXmlReader ewsXmlReader) throws Exception {
    this.readXmlDeclaration(ewsXmlReader);
  }

  /**
   * Read SOAP header and extract server version.
   *
   * @param reader EwsServiceXmlReader
   * @throws Exception the exception
   */
  private void readSoapHeader(EwsServiceXmlReader reader) throws Exception {
    reader.readStartElement(XmlNamespace.Soap, XmlElementNames.SOAPHeaderElementName);
    do {
      reader.read();

      // Is this the ServerVersionInfo?
      if (reader.isStartElement(XmlNamespace.Types, XmlElementNames.ServerVersionInfo)) {
        this.service.setServerInfo(ExchangeServerInfo.parse(reader));
      }

      // Ignore anything else inside the SOAP header
    } while (!reader.isEndElement(XmlNamespace.Soap, XmlElementNames.SOAPHeaderElementName));
  }

  /**
   * Processes the web exception.
   *
   * @param webException the web exception
   * @param req          HTTP Request object used to send the http request
   * @throws Exception on error
   */
  protected void processWebException(Exception webException, HttpWebRequest req) throws Exception {
    SoapFaultDetails soapFaultDetails;
    if (null != req) {
      this.getService().processHttpResponseHeaders(TraceFlags.EwsResponseHttpHeaders, req);
      if (500 == req.getResponseCode()) {
        if (this.service.isTraceEnabledFor(TraceFlags.EwsResponse)) {
          ByteArrayOutputStream memoryStream = new ByteArrayOutputStream();
          InputStream serviceResponseStream = ServiceRequestBase.getResponseErrorStream(req);
          while (true) {
            int data = serviceResponseStream.read();
            if (-1 == data) {
              break;
            } else {
              memoryStream.write(data);
            }
          }
          memoryStream.flush();
          serviceResponseStream.close();
          this.traceResponse(req, memoryStream);
          ByteArrayInputStream memoryStreamIn = new ByteArrayInputStream(memoryStream.toByteArray());
          EwsServiceXmlReader reader = new EwsServiceXmlReader(memoryStreamIn, this.service);
          soapFaultDetails = this.readSoapFault(reader);
          memoryStream.close();
        } else {
          InputStream serviceResponseStream = ServiceRequestBase.getResponseStream(req);
          EwsServiceXmlReader reader = new EwsServiceXmlReader(serviceResponseStream, this.service);
          soapFaultDetails = this.readSoapFault(reader);
          serviceResponseStream.close();

        }

        if (soapFaultDetails != null) {
          switch (soapFaultDetails.getResponseCode()) {
            case ErrorInvalidServerVersion:
              throw new ServiceVersionException("Exchange Server doesn't support the requested version.");

            case ErrorSchemaValidation:
              // If we're talking to an E12 server
              // (8.00.xxxx.xxx), a schema
              // validation error is the same as
              // a version mismatch error.
              // (Which only will happen if we
              // send a request that's not valid
              // for E12).
              if ((this.service.getServerInfo() != null) && (this.service.getServerInfo().getMajorVersion()
                                                             == 8) && (
                      this.service.getServerInfo().getMinorVersion() == 0)) {
                throw new ServiceVersionException("Exchange Server doesn't support the requested version.");
              }

              break;

            case ErrorIncorrectSchemaVersion:
              // This shouldn't happen. It
              // indicates that a request wasn't
              // valid for the version that was specified.
              EwsUtilities.ewsAssert(false, "ServiceRequestBase.ProcessWebException",
                                     "Exchange server supports " + "requested version "
                                     + "but request was invalid for that version");
              break;

            default:
              // Other error codes will
              // be reported as remote error
              break;
          }

          // General fall-through case:
          // throw a ServiceResponseException
          throw new ServiceResponseException(new ServiceResponse(soapFaultDetails));
        }
      } else {
        this.service.processHttpErrorResponse(req, webException);
      }
    }

  }

  /**
   * Reads the SOAP fault.
   *
   * @param reader The reader.
   * @return SOAP fault details.
   */
  protected SoapFaultDetails readSoapFault(EwsServiceXmlReader reader) {
    SoapFaultDetails soapFaultDetails = null;

    try {
      this.readXmlDeclaration(reader);

      reader.read();
      if (!reader.isStartElement() || (!reader.getLocalName()
          .equals(XmlElementNames.SOAPEnvelopeElementName))) {
        return soapFaultDetails;
      }

      // EWS can sometimes return SOAP faults using the SOAP 1.2
      // namespace. Get the
      // namespace URI from the envelope element and use it for the rest
      // of the parsing.
      // If it's not 1.1 or 1.2, we can't continue.
      XmlNamespace soapNamespace = EwsUtilities.getNamespaceFromUri(reader.getNamespaceUri());
      if (soapNamespace == XmlNamespace.NotSpecified) {
        return soapFaultDetails;
      }

      reader.read();

      // EWS doesn't always return a SOAP header. If this response
      // contains a header element,
      // read the server version information contained in the header.
      if (reader.isStartElement(soapNamespace, XmlElementNames.SOAPHeaderElementName)) {
        do {
          reader.read();

          if (reader.isStartElement(XmlNamespace.Types, XmlElementNames.ServerVersionInfo)) {
            this.service.setServerInfo(ExchangeServerInfo.parse(reader));
          }
        } while (!reader.isEndElement(soapNamespace, XmlElementNames.SOAPHeaderElementName));

        // Queue up the next read
        reader.read();
      }

      // Parse the fault element contained within the SOAP body.
      if (reader.isStartElement(soapNamespace, XmlElementNames.SOAPBodyElementName)) {
        do {
          reader.read();

          // Parse Fault element
          if (reader.isStartElement(soapNamespace, XmlElementNames.SOAPFaultElementName)) {
            soapFaultDetails = SoapFaultDetails.parse(reader, soapNamespace);
          }
        } while (!reader.isEndElement(soapNamespace, XmlElementNames.SOAPBodyElementName));
      }

      reader.readEndElement(soapNamespace, XmlElementNames.SOAPEnvelopeElementName);
    } catch (Exception e) {
      // If response doesn't contain a valid SOAP fault, just ignore
      // exception and
      // return null for SOAP fault details.
      LOG.error(e);
    }

    return soapFaultDetails;
  }

  /**
   * Validates request parameters, and emits the request to the server.
   *
   * @return The response returned by the server.
   * @throws Exception on error
   */
  protected HttpWebRequest validateAndEmitRequest() throws Exception {
    this.validate();

    HttpWebRequest request = this.buildEwsHttpWebRequest();

    try {
      try {
        return this.getEwsHttpWebResponse(request);
      } catch (HttpErrorException e) {
        processWebException(e, request);

        // Wrap exception if the above code block didn't throw
        throw new ServiceRequestException(String.format("The request failed. %s", e.getMessage()), e);
      }
    } catch (Exception e) {
      try {
        request.close();
      } catch (Exception e2) {
        // Ignore exception while closing the request.
      }

      throw e;
    }
  }

  /**
   * Builds the HttpWebRequest object for current service request with exception handling.
   *
   * @return An HttpWebRequest instance
   * @throws Exception on error
   */
  protected HttpWebRequest buildEwsHttpWebRequest() throws Exception {
      HttpWebRequest request = service.prepareHttpWebRequest();
    return buildEwsHttpWebRequest(request);
  }

  /**
   * Builds a HttpWebRequest object from a pooling connection manager for current service request
   * with exception handling.
   * <p>
   * Used for subscriptions.
   * </p>
   * 
   * @return A HttpWebRequest instance
   * @throws Exception on error
   */
  protected HttpWebRequest buildEwsHttpPoolingWebRequest() throws Exception {
    HttpWebRequest request = service.prepareHttpPoolingWebRequest();
    return buildEwsHttpWebRequest(request);
  }

  private HttpWebRequest buildEwsHttpWebRequest(HttpWebRequest request) throws Exception {
    try {

      service.traceHttpRequestHeaders(TraceFlags.EwsRequestHttpHeaders, request);

      ByteArrayOutputStream requestStream = (ByteArrayOutputStream) request.getOutputStream();

      EwsServiceXmlWriter writer = new EwsServiceXmlWriter(service, requestStream);

      boolean needSignature =
          service.getCredentials() != null && service.getCredentials().isNeedSignature();
      writer.setRequireWSSecurityUtilityNamespace(needSignature);

      writeToXml(writer);

      if (needSignature) {
        service.getCredentials().sign(requestStream);
      }

      service.traceXml(TraceFlags.EwsRequest, requestStream);

      return request;
    } catch (IOException e) {
      // Wrap exception.
      throw new ServiceRequestException(String.format("The request failed. %s", e.getMessage()), e);
    }
  }

  /**
   * Gets the IEwsHttpWebRequest object from the specifiedHttpWebRequest object with exception handling
   *
   * @param request The specified HttpWebRequest
   * @return An HttpWebResponse instance
   * @throws Exception on error
   */
  protected HttpWebRequest getEwsHttpWebResponse(HttpWebRequest request) throws Exception {
    try {
      request.executeRequest();

      if (request.getResponseCode() >= 400) {
        throw new HttpErrorException(
            "The remote server returned an error: (" + request.getResponseCode() + ")" +
            request.getResponseText(), request.getResponseCode());
      }
    } catch (IOException e) {
      // Wrap exception.
      throw new ServiceRequestException(String.format("The request failed. %s", e.getMessage()), e);
    }

    return request;
  }

  /**
   * Checks whether input string is null or empty.
   *
   * @param str The input string.
   * @return true if input string is null or empty, otherwise false
   */
  private boolean isNullOrEmpty(String str) {
    return null == str || str.isEmpty();
  }

  /**
   * Try to read the XML declaration. If it's not there, the server didn't return XML.
   *
   * @param reader The reader.
   */
  private void readXmlDeclaration(EwsServiceXmlReader reader) throws Exception {
    try {
      reader.read(new XmlNodeType(XmlNodeType.START_DOCUMENT));
    } catch (XmlException ex) {
      throw new ServiceRequestException("The response received from the service didn't contain valid XML.",
                                        ex);
    } catch (ServiceXmlDeserializationException ex) {
      throw new ServiceRequestException("The response received from the service didn't contain valid XML.",
                                        ex);
    }
  }

}
