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

package microsoft.exchange.webservices.data.autodiscover.request;

import microsoft.exchange.webservices.data.EWSConstants;
import microsoft.exchange.webservices.data.autodiscover.AutodiscoverService;
import microsoft.exchange.webservices.data.autodiscover.enumeration.AutodiscoverErrorCode;
import microsoft.exchange.webservices.data.autodiscover.exception.AutodiscoverResponseException;
import microsoft.exchange.webservices.data.autodiscover.response.AutodiscoverResponse;
import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.EwsXmlReader;
import microsoft.exchange.webservices.data.core.ExchangeServerInfo;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.request.HttpWebRequest;
import microsoft.exchange.webservices.data.core.response.ServiceResponse;
import microsoft.exchange.webservices.data.core.enumeration.misc.TraceFlags;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.exception.http.EWSHttpException;
import microsoft.exchange.webservices.data.core.exception.service.remote.ServiceRemoteException;
import microsoft.exchange.webservices.data.core.exception.service.remote.ServiceRequestException;
import microsoft.exchange.webservices.data.core.exception.service.remote.ServiceResponseException;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlDeserializationException;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlSerializationException;
import microsoft.exchange.webservices.data.misc.SoapFaultDetails;
import microsoft.exchange.webservices.data.security.XmlNodeType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.stream.XMLStreamException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.zip.GZIPInputStream;
import java.util.zip.InflaterInputStream;

/**
 * Represents the base class for all requested made to the Autodiscover service.
 */
public abstract class AutodiscoverRequest {

  private static final Log LOG = LogFactory.getLog(AutodiscoverRequest.class);

  /**
   * The service.
   */
  private AutodiscoverService service;

  /**
   * The url.
   */
  private URI url;

  /**
   * Initializes a new instance of the AutodiscoverResponse class.
   *
   * @param service Autodiscover service associated with this request.
   * @param url     URL of Autodiscover service.
   */
  protected AutodiscoverRequest(AutodiscoverService service, URI url) {
    this.service = service;
    this.url = url;
  }

  /**
   * Determines whether response is a redirection.
   *
   * @param request the request
   * @return True if redirection response.
   * @throws EWSHttpException the EWS http exception
   */
  public static boolean isRedirectionResponse(HttpWebRequest request)
      throws EWSHttpException {
    return ((request.getResponseCode() == 301)
        || (request.getResponseCode() == 302)
        || (request.getResponseCode() == 307) || (request
        .getResponseCode() == 303));
  }

  /**
   * Validates the request.
   *
   * @throws Exception the exception
   */
  protected void validate() throws Exception {
    this.getService().validate();
  }

  /**
   * Executes this instance.
   *
   * @return the autodiscover response
   * @throws Exception the exception
   */
  protected AutodiscoverResponse internalExecute() throws Exception {
    this.validate();
    HttpWebRequest request = null;
    try {
      request = this.service.prepareHttpWebRequestForUrl(this.url);
      this.service.traceHttpRequestHeaders(
          TraceFlags.AutodiscoverRequestHttpHeaders, request);

      boolean needSignature = this.getService().getCredentials() != null
          && this.getService().getCredentials().isNeedSignature();
      boolean needTrace = this.getService().isTraceEnabledFor(
          TraceFlags.AutodiscoverRequest);

      OutputStream urlOutStream = request.getOutputStream();
      // OutputStreamWriter out = new OutputStreamWriter(request
      // .getOutputStream());

      ByteArrayOutputStream memoryStream = new ByteArrayOutputStream();
      EwsServiceXmlWriter writer = new EwsServiceXmlWriter(this
          .getService(), memoryStream);
      writer.setRequireWSSecurityUtilityNamespace(needSignature);
      this.writeSoapRequest(this.url, writer);

      if (needSignature) {
        this.service.getCredentials().sign(memoryStream);
      }

      if (needTrace) {
        memoryStream.flush();
        this.service.traceXml(TraceFlags.AutodiscoverRequest,
            memoryStream);
      }
      memoryStream.writeTo(urlOutStream);
      urlOutStream.flush();
      urlOutStream.close();
      memoryStream.close();
      // out.write(memoryStream.toString());
      // out.close();
      request.executeRequest();
      request.getResponseCode();
      if (AutodiscoverRequest.isRedirectionResponse(request)) {
        AutodiscoverResponse response = this
            .createRedirectionResponse(request);
        if (response != null) {
          return response;
        } else {
          throw new ServiceRemoteException("The service returned an invalid redirection response.");
        }
      }

      memoryStream = new ByteArrayOutputStream();
      InputStream serviceResponseStream = request.getInputStream();

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

      if (this.service.isTraceEnabled()) {
        this.service.traceResponse(request, memoryStream);
      }
      ByteArrayInputStream memoryStreamIn = new ByteArrayInputStream(
          memoryStream.toByteArray());
      EwsXmlReader ewsXmlReader = new EwsXmlReader(memoryStreamIn);

      // WCF may not generate an XML declaration.
      ewsXmlReader.read();
      if (ewsXmlReader.getNodeType().getNodeType() == XmlNodeType.START_DOCUMENT) {
        ewsXmlReader.readStartElement(XmlNamespace.Soap,
            XmlElementNames.SOAPEnvelopeElementName);
      } else if ((ewsXmlReader.getNodeType().getNodeType() != XmlNodeType.START_ELEMENT)
          || (!ewsXmlReader.getLocalName().equals(
          XmlElementNames.SOAPEnvelopeElementName))
          || (!ewsXmlReader.getNamespaceUri().equals(
          EwsUtilities.getNamespaceUri(XmlNamespace.Soap)))) {
        throw new ServiceXmlDeserializationException("The Autodiscover service response was invalid.");
      }

      this.readSoapHeaders(ewsXmlReader);

      AutodiscoverResponse response = this.readSoapBody(ewsXmlReader);

      ewsXmlReader.readEndElement(XmlNamespace.Soap,
          XmlElementNames.SOAPEnvelopeElementName);

      if (response.getErrorCode() == AutodiscoverErrorCode.NoError) {
        return response;
      } else {
        throw new AutodiscoverResponseException(
            response.getErrorCode(), response.getErrorMessage());
      }

    } catch (XMLStreamException ex) {
      this.service.traceMessage(TraceFlags.AutodiscoverConfiguration,
          String.format("XML parsing error: %s", ex.getMessage()));

      // Wrap exception
      throw new ServiceRequestException(String.format("The request failed. %s", ex.getMessage()), ex);
    } catch (IOException ex) {
      this.service.traceMessage(TraceFlags.AutodiscoverConfiguration,
          String.format("I/O error: %s", ex.getMessage()));

      // Wrap exception
      throw new ServiceRequestException(String.format("The request failed. %s", ex.getMessage()), ex);
    } catch (Exception ex) {
      // HttpWebRequest httpWebResponse = (HttpWebRequest)ex;

      if (null != request && request.getResponseCode() == 7) {
        if (AutodiscoverRequest.isRedirectionResponse(request)) {
          this.service
              .processHttpResponseHeaders(
                  TraceFlags.AutodiscoverResponseHttpHeaders,
                  request);

          AutodiscoverResponse response = this
              .createRedirectionResponse(request);
          if (response != null) {
            return response;
          }
        } else {
          this.processWebException(ex, request);
        }
      }

      // Wrap exception if the above code block didn't throw
      throw new ServiceRequestException(String.format("The request failed. %s", ex.getMessage()), ex);
    } finally {
      try {
        if (request != null) {
          request.close();
        }
      } catch (Exception e) {
        // do nothing
      }
    }
  }

  /**
   * Processes the web exception.
   *
   * @param exception WebException
   * @param req       HttpWebRequest
   */
  private void processWebException(Exception exception, HttpWebRequest req) {
    if (null != req) {
      try {
        if (500 == req.getResponseCode()) {
          if (this.service
              .isTraceEnabledFor(
                  TraceFlags.AutodiscoverRequest)) {
            ByteArrayOutputStream memoryStream =
                new ByteArrayOutputStream();
            InputStream serviceResponseStream = AutodiscoverRequest
                .getResponseStream(req);
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
            this.service.traceResponse(req, memoryStream);
            ByteArrayInputStream memoryStreamIn =
                new ByteArrayInputStream(
                    memoryStream.toByteArray());
            EwsXmlReader reader = new EwsXmlReader(memoryStreamIn);
            this.readSoapFault(reader);
            memoryStream.close();
          } else {
            InputStream serviceResponseStream = AutodiscoverRequest
                .getResponseStream(req);
            EwsXmlReader reader = new EwsXmlReader(
                serviceResponseStream);
            SoapFaultDetails soapFaultDetails = this.readSoapFault(reader);
            serviceResponseStream.close();

            if (soapFaultDetails != null) {
              throw new ServiceResponseException(
                  new ServiceResponse(soapFaultDetails));
            }
          }
        } else {
          this.service.processHttpErrorResponse(req, exception);
        }
      } catch (Exception e) {
        LOG.error(e);
      }
    }
  }

  /**
   * Create a redirection response.
   *
   * @param httpWebResponse the HTTP web response
   * @return AutodiscoverResponse autodiscoverResponse object
   * @throws XMLStreamException the XML stream exception
   * @throws IOException signals that an I/O exception has occurred
   * @throws EWSHttpException the EWS http exception
   */
  private AutodiscoverResponse createRedirectionResponse(
      HttpWebRequest httpWebResponse) throws XMLStreamException,
      IOException, EWSHttpException {
    String location = httpWebResponse.getResponseHeaderField("Location");
    if (!(location == null || location.isEmpty())) {
      try {
        URI redirectionUri = new URI(location);
        String scheme = redirectionUri.getScheme();

        if (scheme.equalsIgnoreCase(EWSConstants.HTTP_SCHEME)
            || scheme.equalsIgnoreCase(EWSConstants.HTTPS_SCHEME)) {
          AutodiscoverResponse response = this.createServiceResponse();
          response.setErrorCode(AutodiscoverErrorCode.RedirectUrl);
          response.setRedirectionUrl(redirectionUri);
          return response;
        }

        this.service
            .traceMessage(
                TraceFlags.AutodiscoverConfiguration,
                String
                    .format(
                        "Invalid redirection" +
                            " URL '%s' " +
                            "returned by Autodiscover " +
                            "service.",
                        redirectionUri.toString()));

      } catch (URISyntaxException ex) {
        this.service
            .traceMessage(
                TraceFlags.AutodiscoverConfiguration,
                String
                    .format(
                        "Invalid redirection " +
                            "location '%s' " +
                            "returned by Autodiscover " +
                            "service.",
                        location));
      }
    } else {
      this.service
          .traceMessage(
              TraceFlags.AutodiscoverConfiguration,
              "Redirection response returned by Autodiscover " +
                  "service without redirection location.");
    }

    return null;
  }

  /**
   * Reads the SOAP fault.
   *
   * @param reader The reader.
   * @return SOAP fault details.
   */
  private SoapFaultDetails readSoapFault(EwsXmlReader reader) {
    SoapFaultDetails soapFaultDetails = null;

    try {

      reader.read();
      if (reader.getNodeType().getNodeType() == XmlNodeType.START_DOCUMENT) {
        reader.read();
      }
      if (!reader.isStartElement()
          || (!reader.getLocalName().equals(
          XmlElementNames.SOAPEnvelopeElementName))) {
        return null;
      }

      // Get the namespace URI from the envelope element and use it for
      // the rest of the parsing.
      // If it's not 1.1 or 1.2, we can't continue.
      XmlNamespace soapNamespace = EwsUtilities
          .getNamespaceFromUri(reader.getNamespaceUri());
      if (soapNamespace == XmlNamespace.NotSpecified) {
        return null;
      }

      reader.read();

      // Skip SOAP header.
      if (reader.isStartElement(soapNamespace,
          XmlElementNames.SOAPHeaderElementName)) {
        do {
          reader.read();
        } while (!reader.isEndElement(soapNamespace,
            XmlElementNames.SOAPHeaderElementName));

        // Queue up the next read
        reader.read();
      }

      // Parse the fault element contained within the SOAP body.
      if (reader.isStartElement(soapNamespace,
          XmlElementNames.SOAPBodyElementName)) {
        do {
          reader.read();

          // Parse Fault element
          if (reader.isStartElement(soapNamespace,
              XmlElementNames.SOAPFaultElementName)) {
            soapFaultDetails = SoapFaultDetails.parse(reader,
                soapNamespace);
          }
        } while (!reader.isEndElement(soapNamespace,
            XmlElementNames.SOAPBodyElementName));
      }

      reader.readEndElement(soapNamespace,
          XmlElementNames.SOAPEnvelopeElementName);
    } catch (Exception e) {
      // If response doesn't contain a valid SOAP fault, just ignore
      // exception and
      // return null for SOAP fault details.
      LOG.error(e);
    }

    return soapFaultDetails;
  }

  /**
   * Writes the autodiscover SOAP request.
   *
   * @param requestUrl request URL
   * @param writer writer object
   * @throws XMLStreamException the XML stream exception
   * @throws ServiceXmlSerializationException the service xml serialization exception
   */
  protected void writeSoapRequest(URI requestUrl,
      EwsServiceXmlWriter writer) throws XMLStreamException, ServiceXmlSerializationException {

    if (writer.isRequireWSSecurityUtilityNamespace()) {
      writer.writeAttributeValue("xmlns",
          EwsUtilities.WSSecurityUtilityNamespacePrefix,
          EwsUtilities.WSSecurityUtilityNamespace);
    }
    writer.writeStartDocument();
    writer.writeStartElement(XmlNamespace.Soap,
        XmlElementNames.SOAPEnvelopeElementName);
    writer.writeAttributeValue("xmlns", EwsUtilities
        .getNamespacePrefix(XmlNamespace.Soap), EwsUtilities
        .getNamespaceUri(XmlNamespace.Soap));
    writer.writeAttributeValue("xmlns",
        EwsUtilities.AutodiscoverSoapNamespacePrefix,
        EwsUtilities.AutodiscoverSoapNamespace);
    writer.writeAttributeValue("xmlns",
        EwsUtilities.WSAddressingNamespacePrefix,
        EwsUtilities.WSAddressingNamespace);
    writer.writeAttributeValue("xmlns",
        EwsUtilities.EwsXmlSchemaInstanceNamespacePrefix,
        EwsUtilities.EwsXmlSchemaInstanceNamespace);

    writer.writeStartElement(XmlNamespace.Soap,
        XmlElementNames.SOAPHeaderElementName);

    if (this.service.getCredentials() != null) {
      this.service.getCredentials().emitExtraSoapHeaderNamespaceAliases(
          writer.getInternalWriter());
    }

    writer.writeElementValue(XmlNamespace.Autodiscover,
        XmlElementNames.RequestedServerVersion, this.service
            .getRequestedServerVersion().toString());

    writer.writeElementValue(XmlNamespace.WSAddressing,
        XmlElementNames.Action, this.getWsAddressingActionName());

    writer.writeElementValue(XmlNamespace.WSAddressing, XmlElementNames.To,
        requestUrl.toString());

    this.writeExtraCustomSoapHeadersToXml(writer);

    if (this.service.getCredentials() != null) {
      this.service.getCredentials().serializeWSSecurityHeaders(
          writer.getInternalWriter());
    }

    this.service.doOnSerializeCustomSoapHeaders(writer.getInternalWriter());

    writer.writeEndElement(); // soap:Header

    writer.writeStartElement(XmlNamespace.Soap,
        XmlElementNames.SOAPBodyElementName);

    this.writeBodyToXml(writer);

    writer.writeEndElement(); // soap:Body
    writer.writeEndElement(); // soap:Envelope
    writer.flush();
    writer.dispose();
  }

  /**
   * Write extra headers.
   *
   * @param writer the writer
   * @throws ServiceXmlSerializationException the service xml serialization exception
   * @throws XMLStreamException the XML stream exception
   */
  protected void writeExtraCustomSoapHeadersToXml(EwsServiceXmlWriter writer)
      throws XMLStreamException, ServiceXmlSerializationException {
    // do nothing here.
    // currently used only by GetUserSettingRequest to emit the BinarySecret header.
  }


  /**
   * Writes XML body.
   *
   * @param writer the writer
   * @throws ServiceXmlSerializationException the service xml serialization exception
   * @throws XMLStreamException the XML stream exception
   */
  protected void writeBodyToXml(EwsServiceXmlWriter writer)
      throws ServiceXmlSerializationException, XMLStreamException {
    writer.writeStartElement(XmlNamespace.Autodiscover, this
        .getRequestXmlElementName());

    this.writeAttributesToXml(writer);
    this.writeElementsToXml(writer);

    writer.writeEndElement(); // m:this.GetXmlElementName()
  }

  /**
   * Gets the response stream (may be wrapped with GZip/Deflate stream to
   * decompress content).
   *
   * @param request the request
   * @return ResponseStream
   * @throws EWSHttpException the EWS http exception
   * @throws IOException signals that an I/O exception has occurred.
   */
  protected static InputStream getResponseStream(HttpWebRequest request)
      throws EWSHttpException, IOException {
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
   * Read SOAP header.
   *
   * @param reader EwsXmlReader.
   * @throws Exception the exception
   */
  protected void readSoapHeaders(EwsXmlReader reader) throws Exception {
    reader.readStartElement(XmlNamespace.Soap,
        XmlElementNames.SOAPHeaderElementName);
    do {
      reader.read();

      this.readSoapHeader(reader);
    } while (!reader.isEndElement(XmlNamespace.Soap,
        XmlElementNames.SOAPHeaderElementName));
  }

  /**
   * Reads a single SOAP header.
   *
   * @param reader EwsXmlReader
   * @throws Exception on error
   */
  protected void readSoapHeader(EwsXmlReader reader) throws Exception {
    // Is this the ServerVersionInfo?
    if (reader.isStartElement(XmlNamespace.Autodiscover,
        XmlElementNames.ServerVersionInfo)) {
      this.service.setServerInfo(this.readServerVersionInfo(reader));
    }
  }

  /**
   * Read ServerVersionInfo SOAP header.
   *
   * @param reader EwsXmlReader.
   * @return ExchangeServerInfo ExchangeServerInfo object
   * @throws Exception the exception
   */
  private ExchangeServerInfo readServerVersionInfo(EwsXmlReader reader)
      throws Exception {
    ExchangeServerInfo serverInfo = new ExchangeServerInfo();
    do {
      reader.read();

      if (reader.isStartElement()) {
        if (reader.getLocalName().equals(XmlElementNames.MajorVersion)) {
          serverInfo.setMajorVersion(reader
              .readElementValue(Integer.class));
        } else if (reader.getLocalName().equals(
            XmlElementNames.MinorVersion)) {
          serverInfo.setMinorVersion(reader
              .readElementValue(Integer.class));
        } else if (reader.getLocalName().equals(
            XmlElementNames.MajorBuildNumber)) {
          serverInfo.setMajorBuildNumber(reader
              .readElementValue(Integer.class));
        } else if (reader.getLocalName().equals(
            XmlElementNames.MinorBuildNumber)) {
          serverInfo.setMinorBuildNumber(reader
              .readElementValue(Integer.class));
        } else if (reader.getLocalName()
            .equals(XmlElementNames.Version)) {
          serverInfo.setVersionString(reader.readElementValue());
        }
      }
    } while (!reader.isEndElement(XmlNamespace.Autodiscover,
        XmlElementNames.ServerVersionInfo));

    return serverInfo;
  }

  /**
   * Read SOAP body.
   *
   * @param reader EwsXmlReader.
   * @return AutodiscoverResponse AutodiscoverResponse object
   * @throws Exception the exception
   */
  protected AutodiscoverResponse readSoapBody(EwsXmlReader reader) throws Exception {
    reader.readStartElement(XmlNamespace.Soap,
        XmlElementNames.SOAPBodyElementName);
    AutodiscoverResponse responses = this.loadFromXml(reader);
    reader.readEndElement(XmlNamespace.Soap,
        XmlElementNames.SOAPBodyElementName);
    return responses;
  }

  /**
   * Loads response from XML.
   *
   * @param reader The reader.
   * @return AutodiscoverResponse object
   * @throws Exception the exception
   */
  protected AutodiscoverResponse loadFromXml(EwsXmlReader reader) throws Exception {
    String elementName = this.getResponseXmlElementName();
    reader.readStartElement(XmlNamespace.Autodiscover, elementName);
    AutodiscoverResponse response = this.createServiceResponse();
    response.loadFromXml(reader, elementName);
    return response;
  }

  /**
   * Gets the name of the request XML element.
   *
   * @return RequestXmlElementName gets XmlElementName.
   */
  protected abstract String getRequestXmlElementName();

  /**
   * Gets the name of the response XML element.
   *
   * @return ResponseXmlElementName gets XmlElementName.
   */
  protected abstract String getResponseXmlElementName();

  /**
   * Gets the WS-Addressing action name.
   *
   * @return WsAddressingActionName gets WsAddressingActionName.
   */
  protected abstract String getWsAddressingActionName();

  /**
   * Creates the service response.
   *
   * @return AutodiscoverResponse AutodiscoverResponse object.
   */
  protected abstract AutodiscoverResponse createServiceResponse();

  /**
   * Writes attribute to request XML.
   *
   * @param writer The writer.
   * @throws ServiceXmlSerializationException the service xml serialization exception
   */
  protected abstract void writeAttributesToXml(EwsServiceXmlWriter writer)
      throws ServiceXmlSerializationException;

  /**
   * Writes elements to request XML.
   *
   * @param writer the writer
   * @throws XMLStreamException the XML stream exception
   * @throws ServiceXmlSerializationException the service xml serialization exception
   */
  protected abstract void writeElementsToXml(EwsServiceXmlWriter writer)
      throws XMLStreamException, ServiceXmlSerializationException;

  /**
   * Gets the Service.
   *
   * @return AutodiscoverService AutodiscoverService object.
   */
  protected AutodiscoverService getService() {
    return this.service;
  }

  /**
   * Gets the URL.
   *
   * @return url URL Object.
   */
  protected URI getUrl() {
    return this.url;
  }
}
