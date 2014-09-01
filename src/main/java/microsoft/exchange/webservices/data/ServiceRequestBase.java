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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.zip.GZIPInputStream;
import java.util.zip.InflaterInputStream;

import javax.xml.stream.XMLStreamException;
import javax.xml.ws.http.HTTPException;

/**
 * Represents an abstract service request.
 */
abstract class ServiceRequestBase {

	// Private Constants
	// private final String XMLSchemaNamespace =
	// "http://www.w3.org/2001/XMLSchema";
	// private final String XMLSchemaInstanceNamespace =
	// "http://www.w3.org/2001/XMLSchema-instance";

	/** The service. */
	private ExchangeService service;

	// Methods for subclasses to override

	/**
	 * Gets the name of the XML element.
	 * 
	 * @return XML element name
	 */
	protected abstract String getXmlElementName();

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
	 * Writes XML elements.
	 * 
	 * @param writer
	 *            The writer.
	 * @throws javax.xml.stream.XMLStreamException
	 *             the xML stream exception
	 * @throws ServiceXmlSerializationException
	 *             the service xml serialization exception
	 * @throws ServiceLocalException
	 *             the service local exception
	 * @throws InstantiationException
	 *             the instantiation exception
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 * @throws ServiceValidationException
	 *             the service validation exception
	 * @throws Exception
	 *             the exception
	 */
	protected abstract void writeElementsToXml(EwsServiceXmlWriter writer)
			throws XMLStreamException, ServiceXmlSerializationException,
			ServiceLocalException, InstantiationException,
			IllegalAccessException, ServiceValidationException, Exception;

	/**
	 * Parses the response.
	 * 
	 * @param reader
	 *            The reader.
	 * @return Response object.
	 * @throws ServiceXmlDeserializationException
	 *             the service xml deserialization exception
	 * @throws javax.xml.stream.XMLStreamException
	 *             the xML stream exception
	 * @throws InstantiationException
	 *             the instantiation exception
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 * @throws ServiceLocalException
	 *             the service local exception
	 * @throws ServiceResponseException
	 *             the service response exception
	 * @throws IndexOutOfBoundsException
	 *             the index out of bounds exception
	 * @throws Exception
	 *             the exception
	 */
	protected abstract Object parseResponse(EwsServiceXmlReader reader)
			throws ServiceXmlDeserializationException, XMLStreamException,
			InstantiationException, IllegalAccessException,
			ServiceLocalException, ServiceResponseException,
			IndexOutOfBoundsException, Exception;

	/**
	 * Validate request.
	 * 
	 * @throws ServiceLocalException
	 *             the service local exception
	 * @throws Exception
	 *             the exception
	 */
	protected void validate() throws ServiceLocalException, Exception {
		this.service.validate();
	}

	/**
	 * Writes XML body.
	 * 
	 * @param writer
	 *            The writer.
	 * @throws Exception
	 *             the exception
	 */
	protected void writeBodyToXml(EwsServiceXmlWriter writer) throws Exception {
		writer.writeStartElement(XmlNamespace.Messages, this
				.getXmlElementName());

		this.writeAttributesToXml(writer);
		this.writeElementsToXml(writer);

		writer.writeEndElement(); // m:this.GetXmlElementName()
	}

	/**
	 * Writes XML attributes. Subclass will override if it has XML attributes.
	 * 
	 * @param writer
	 *            The writer.
	 * @throws ServiceXmlSerializationException
	 *             the service xml serialization exception
	 */
	protected void writeAttributesToXml(EwsServiceXmlWriter writer)
			throws ServiceXmlSerializationException {
	}

	/**
	 * Initializes a new instance.
	 * 
	 * @param service
	 *            The service.
	 * @throws ServiceVersionException
	 *             the service version exception
	 */
	protected ServiceRequestBase(ExchangeService service)
			throws ServiceVersionException {
		this.service = service;
		this.throwIfNotSupportedByRequestedServerVersion();
	}

	/**
	 * Gets the service.
	 * 
	 * @return The service.
	 */
	protected ExchangeService getService() {
		return service;
	}

	/**
	 * Throw exception if request is not supported in requested server
	 * version.
	 * 
	 * @throws ServiceVersionException
	 *             the service version exception
	 */
	protected void throwIfNotSupportedByRequestedServerVersion()
			throws ServiceVersionException {
		if (this.service.getRequestedServerVersion().ordinal() < this
				.getMinimumRequiredServerVersion().ordinal()) {
			throw new ServiceVersionException(String.format(
					Strings.RequestIncompatibleWithRequestVersion, this
							.getXmlElementName(), this
							.getMinimumRequiredServerVersion()));
		}
	}

	// HttpWebRequest-based implementation

	/**
	 * Writes XML.
	 * 
	 * @param writer
	 *            The writer.
	 * @throws Exception
	 *             the exception
	 */
	protected void writeToXml(EwsServiceXmlWriter writer) throws Exception {
		writer.writeStartDocument();
		writer.writeStartElement(XmlNamespace.Soap,
				XmlElementNames.SOAPEnvelopeElementName);
		writer.writeAttributeValue("xmlns", EwsUtilities
				.getNamespacePrefix(XmlNamespace.Soap), EwsUtilities
				.getNamespaceUri(XmlNamespace.Soap));
		writer.writeAttributeValue("xmlns",
				EwsUtilities.EwsXmlSchemaInstanceNamespacePrefix,
				EwsUtilities.EwsXmlSchemaInstanceNamespace);
		writer.writeAttributeValue("xmlns",
				EwsUtilities.EwsMessagesNamespacePrefix,
				EwsUtilities.EwsMessagesNamespace);
		writer.writeAttributeValue("xmlns",
				EwsUtilities.EwsTypesNamespacePrefix,
				EwsUtilities.EwsTypesNamespace);
		if (writer.isRequireWSSecurityUtilityNamespace()) {
			writer.writeAttributeValue("xmlns",
					EwsUtilities.WSSecurityUtilityNamespacePrefix,
					EwsUtilities.WSSecurityUtilityNamespace);
		}

		writer.writeStartElement(XmlNamespace.Soap,
				XmlElementNames.SOAPHeaderElementName);

		if (this.service.getCredentials() != null) {
			this.service.getCredentials().emitExtraSoapHeaderNamespaceAliases(
					writer.getInternalWriter());
		}

		// Emit the RequestServerVersion header
		writer.writeStartElement(XmlNamespace.Types,
				XmlElementNames.RequestServerVersion);
		writer.writeAttributeValue(XmlAttributeNames.Version, this
				.getRequestedServiceVersionString());
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
			writer.writeElementValue(XmlNamespace.Types,
					XmlElementNames.MailboxCulture, this.service
							.getPreferredCulture().getDisplayName());
		}

		/** Emit the DateTimePrecision header */

		if (this.getService().getDateTimePrecision().ordinal() != DateTimePrecision.Default
				.ordinal()) {
			writer.writeElementValue(XmlNamespace.Types,
					XmlElementNames.DateTimePrecision, this.getService()
							.getDateTimePrecision().toString());
		}
		if (this.service.getImpersonatedUserId() != null) {
			this.service.getImpersonatedUserId().writeToXml(writer);
		}

		if (this.service.getCredentials() != null) {
			this.service.getCredentials().serializeExtraSoapHeaders(
					writer.getInternalWriter(), this.getXmlElementName());
		}
		this.service.doOnSerializeCustomSoapHeaders(writer.getInternalWriter());

		writer.writeEndElement(); // soap:Header

		writer.writeStartElement(XmlNamespace.Soap,
				XmlElementNames.SOAPBodyElementName);

		this.writeBodyToXml(writer);

		writer.writeEndElement(); // soap:Body
		writer.writeEndElement(); // soap:Envelope
		writer.flush();
	}

	/**
	 * Gets st ring representation of requested server version. In order to
	 * support E12 RTM servers, ExchangeService has another flag indicating that
	 * we should use "Exchange2007" as the server version string rather than
	 * Exchange2007_SP1.
	 * 
	 * @return String representation of requested server version.
	 */
	private String getRequestedServiceVersionString() {
		if (this.service.getRequestedServerVersion() == ExchangeVersion.Exchange2007_SP1) {
			return "Exchange2007";
		} else {
			return this.service.getRequestedServerVersion().toString();
		}
	}

	/**
	 * Get the request stream
	 * 
	 *@param request
	 *            The request
	 * @throws java.util.concurrent.ExecutionException
	 * @throws InterruptedException
	 * @return The Request stream
	 */
	private ByteArrayOutputStream getWebRequestStream(Future request)
			throws EWSHttpException, InterruptedException, ExecutionException {
		// In the async case, although we can use async callback to make the
		// entire worflow completely async,
		// there is little perf gain with this approach because of EWS's message
		// nature.
		// The overall latency of BeginGetRequestStream() is same as
		// GetRequestStream() in this case.
		// The overhead to implement a two-step async operation includes wait
		// handle synchronization, exception handling and wrapping.
		// Therefore, we only leverage BeginGetResponse() and EndGetReponse() to
		// provide the async functionality.
		// Reference:
		// http://www.wintellect.com/CS/blogs/jeffreyr/archive/2009/02/08/httpwebrequest-its-request-stream-and-sending-data-in-chunks.aspx
		// return
		// request.endGetRequestStream(request.beginGetRequestStream(request,
		// null));

		return (ByteArrayOutputStream) request.get();
		// return ( ByteArrayOutputStream)request.getOutputStream();

	}

	/**
	 * Gets the response stream (may be wrapped with GZip/Deflate stream to
	 * decompress content).
	 * 
	 * @param request
	 *            HttpWebRequest object from which response stream can be read.
	 * @return ResponseStream
	 * @throws java.io.IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws microsoft.exchange.webservices.data.EWSHttpException
	 *             the eWS http exception
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
	 * @param request
	 *            The response.
	 * @param memoryStream
	 *            The response content in a MemoryStream.
	 * @throws javax.xml.stream.XMLStreamException
	 *             the xML stream exception
	 * @throws java.io.IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws microsoft.exchange.webservices.data.EWSHttpException
	 *             the eWS http exception
	 */
	protected void traceResponse(HttpWebRequest request,
			ByteArrayOutputStream memoryStream) throws XMLStreamException,
			IOException, EWSHttpException {

		this.service.processHttpResponseHeaders(
				TraceFlags.EwsResponseHttpHeaders, request);
		String contentType = request.getResponseContentType();

		if (!isNullOrEmpty(contentType)
				&& (contentType.startsWith("text/") || contentType
						.startsWith("application/soap"))) {
			this.service.traceXml(TraceFlags.EwsResponse, memoryStream);
		} else {
			this.service.traceMessage(TraceFlags.EwsResponse,
					"Non-textual response");
		}

	}

	/**
	 * Gets the response error stream.
	 * 
	 * @param request
	 *            the request
	 * @return the response error stream
	 * @throws microsoft.exchange.webservices.data.EWSHttpException
	 *             the eWS http exception
	 * @throws java.io.IOException
	 *             Signals that an I/O exception has occurred.
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
	 * @param ewsXmlReader
	 *            The XML reader.
	 * @return Service response.
	 * @throws Exception
	 *             the exception
	 */
	protected Object readResponse(EwsServiceXmlReader ewsXmlReader)
			throws Exception {
		Object serviceResponse;
		this.readPreamble(ewsXmlReader);
		ewsXmlReader.readStartElement(XmlNamespace.Soap,
				XmlElementNames.SOAPEnvelopeElementName);
		this.readSoapHeader(ewsXmlReader);
		ewsXmlReader.readStartElement(XmlNamespace.Soap,
				XmlElementNames.SOAPBodyElementName);

		ewsXmlReader.readStartElement(XmlNamespace.Messages, this
				.getResponseXmlElementName());

		serviceResponse = this.parseResponse(ewsXmlReader);

		ewsXmlReader.readEndElementIfNecessary(XmlNamespace.Messages, this
				.getResponseXmlElementName());

		ewsXmlReader.readEndElement(XmlNamespace.Soap,
				XmlElementNames.SOAPBodyElementName);
		ewsXmlReader.readEndElement(XmlNamespace.Soap,
				XmlElementNames.SOAPEnvelopeElementName);
		return serviceResponse;
	}

	/**
	 * Reads any preamble data not part of the core response.
	 * 
	 * @param ewsXmlReader
	 *            The EwsServiceXmlReader.
	 * @throws Exception
	 */
	protected void readPreamble(EwsServiceXmlReader ewsXmlReader)
			throws Exception {
		this.readXmlDeclaration(ewsXmlReader);
	}

	/**
	 * Read SOAP header and extract server version.
	 * 
	 * @param reader
	 *            EwsServiceXmlReader
	 * @throws Exception
	 *             the exception
	 */
	private void readSoapHeader(EwsServiceXmlReader reader) throws Exception {
		reader.readStartElement(XmlNamespace.Soap,
				XmlElementNames.SOAPHeaderElementName);
		do {
			reader.read();

			// Is this the ServerVersionInfo?
			if (reader.isStartElement(XmlNamespace.Types,
					XmlElementNames.ServerVersionInfo)) {
				this.service.setServerInfo(ExchangeServerInfo.parse(reader));
			}

			// Ignore anything else inside the SOAP header
		} while (!reader.isEndElement(XmlNamespace.Soap,
				XmlElementNames.SOAPHeaderElementName));
	}

	/**
	 * Processes the web exception.
	 * 
	 * @param webException
	 *            The web exception.
	 * @param req
	 *            http Request object used to send the http request.
	 * @throws Exception
	 */
	protected void processWebException(Exception webException, HttpWebRequest req)
			throws Exception {
		SoapFaultDetails soapFaultDetails = null;
		if (null != req) {
			this.getService().processHttpResponseHeaders(
					TraceFlags.EwsResponseHttpHeaders, req);
			if (500 == req.getResponseCode()) {
				if (this.service.isTraceEnabledFor(TraceFlags.EwsResponse)) {
					ByteArrayOutputStream memoryStream = new ByteArrayOutputStream();
					InputStream serviceResponseStream = ServiceRequestBase
							.getResponseErrorStream(req);
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
					ByteArrayInputStream memoryStreamIn = new ByteArrayInputStream(
							memoryStream.toByteArray());
					EwsServiceXmlReader reader = new EwsServiceXmlReader(
							memoryStreamIn, this.service);
					soapFaultDetails = this.readSoapFault(reader);
					memoryStream.close();
				} else {
					InputStream serviceResponseStream = ServiceRequestBase
							.getResponseStream(req);
					EwsServiceXmlReader reader = new EwsServiceXmlReader(
							serviceResponseStream, this.service);
					soapFaultDetails = this.readSoapFault(reader);
					serviceResponseStream.close();

				}

				if (soapFaultDetails != null) {
					switch (soapFaultDetails.getResponseCode()) {
					case ErrorInvalidServerVersion:
						throw new ServiceVersionException(
								Strings.ServerVersionNotSupported);

					case ErrorSchemaValidation:
						// If we're talking to an E12 server
						// (8.00.xxxx.xxx), a schema
						// validation error is the same as
						// a version mismatch error.
						// (Which only will happen if we
						// send a request that's not valid
						// for E12).
						if ((this.service.getServerInfo() != null)
								&& (this.service.getServerInfo()
										.getMajorVersion() == 8)
								&& (this.service.getServerInfo()
										.getMinorVersion() == 0)) {
							throw new ServiceVersionException(
									Strings.ServerVersionNotSupported);
						}

						break;

					case ErrorIncorrectSchemaVersion:
						// This shouldn't happen. It
						// indicates that a request wasn't
						// valid for the version that was specified.
						EwsUtilities
								.EwsAssert(
										false,
										"ServiceRequestBase.ProcessWebException",
										"Exchange server supports "
												+ "requested version "
												+ "but request was invalid for that version");
						break;

					default:
						// Other error codes will
						// be reported as remote error
						break;
					}

					// General fall-through case:
					// throw a ServiceResponseException
					throw new ServiceResponseException(new ServiceResponse(
							soapFaultDetails));
				}
			} else {
				this.service.processHttpErrorResponse(req, webException);
			}
		}

	}

	/**
	 * Reads the SOAP fault.
	 * 
	 * @param reader
	 *            The reader.
	 * @return SOAP fault details.
	 */
	protected SoapFaultDetails readSoapFault(EwsServiceXmlReader reader) {
		SoapFaultDetails soapFaultDetails = null;

		try {
			this.readXmlDeclaration(reader);

			reader.read();
			if (!reader.isStartElement()
					|| (!reader.getLocalName().equals(
							XmlElementNames.SOAPEnvelopeElementName))) {
				return soapFaultDetails;
			}

			// EWS can sometimes return SOAP faults using the SOAP 1.2
			// namespace. Get the
			// namespace URI from the envelope element and use it for the rest
			// of the parsing.
			// If it's not 1.1 or 1.2, we can't continue.
			XmlNamespace soapNamespace = EwsUtilities
					.getNamespaceFromUri(reader.getNamespaceUri());
			if (soapNamespace == XmlNamespace.NotSpecified) {
				return soapFaultDetails;
			}

			reader.read();

			// EWS doesn't always return a SOAP header. If this response
			// contains a header element,
			// read the server version information contained in the header.
			if (reader.isStartElement(soapNamespace,
					XmlElementNames.SOAPHeaderElementName)) {
				do {
					reader.read();

					if (reader.isStartElement(XmlNamespace.Types,
							XmlElementNames.ServerVersionInfo)) {
						this.service.setServerInfo(ExchangeServerInfo
								.parse(reader));
					}
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
			e.printStackTrace();
		}

		return soapFaultDetails;
	}

	/**
	 * Validates request parameters, and emits the request to the server.
	 * 
	 * @param request
	 *            The request.
	 * @return The response returned by the server.
	 */
	protected HttpWebRequest validateAndEmitRequest() throws ServiceLocalException, Exception {
		this.validate();

		HttpWebRequest request = this.buildEwsHttpWebRequest();
		return this.getEwsHttpWebResponse(request);
	}

	/**
	 * <summary> Builds the HttpWebRequest object for current service request
	 * with exception handling.
	 * 
	 * @return An HttpWebRequest instance
	 */
	protected HttpWebRequest buildEwsHttpWebRequest() throws Exception {
		HttpWebRequest request = null;

		try {
			request = this.getService().prepareHttpWebRequest();
			AsyncExecutor ae = new AsyncExecutor();

			// ExecutorService es = CallableSingleTon.getExecutor();
			Callable getStream = new GetStream(request, "getOutputStream");
			Future task = ae.submit(getStream, null);
			ae.shutdown();
			this.getService().traceHttpRequestHeaders(TraceFlags.EwsRequestHttpHeaders, request);

			boolean needSignature = this.getService().getCredentials() != null
					&& this.getService().getCredentials().isNeedSignature();
			boolean needTrace = this.getService().isTraceEnabledFor(
					TraceFlags.EwsRequest);

			/*
			 * If tracing is enabled, we generate the request in-memory so that
			 * we can pass it along to the ITraceListener. Then we copy the
			 * stream to the request stream.
			 */

			ByteArrayOutputStream memoryStream = new ByteArrayOutputStream();

			EwsServiceXmlWriter writer = new EwsServiceXmlWriter(this
					.getService(), memoryStream);

			writer.setRequireWSSecurityUtilityNamespace(needSignature);
			this.writeToXml(writer);

			if (needSignature || needTrace) {
				if (needSignature) {
					this.service.getCredentials().sign(memoryStream);
				}

				if (needTrace) {
					this.getService().traceXml(TraceFlags.EwsRequest,
							memoryStream);
				}

				ByteArrayOutputStream serviceRequestStream = this.getWebRequestStream(task);
				EwsUtilities.copyStream(memoryStream, serviceRequestStream);
			} else {
				ByteArrayOutputStream requestStream = this
						.getWebRequestStream(task);

				EwsServiceXmlWriter writer1 = new EwsServiceXmlWriter(this
						.getService(), requestStream);

				this.writeToXml(writer1);
			}

			return request;
		} catch (HTTPException e) {
			if (e.getStatusCode() == WebExceptionStatus.ProtocolError.ordinal() && e.getCause() != null) {
				this.processWebException(e, request);
			}

			// Wrap exception if the above code block didn't throw
			throw new ServiceRequestException(String.format(Strings.ServiceRequestFailed, e.getMessage()), e);
		} catch (IOException e) {
			// Wrap exception.
			throw new ServiceRequestException(String.format(Strings.ServiceRequestFailed, e.getMessage()), e);
		}
	}

	/**
	 * Gets the IEwsHttpWebRequest object from the specifiedHttpWebRequest
	 * object with exception handling
	 * 
	 * @param request The specified HttpWebRequest
	 * @return An HttpWebResponse instance
	 */
	protected HttpWebRequest getEwsHttpWebResponse(HttpWebRequest request) throws Exception {
		int code;

		try {

			code = request.executeRequest();

		} catch (HttpErrorException ex) {
			if (ex.getHttpErrorCode() == WebExceptionStatus.ProtocolError
					.ordinal()
					&& ex.getMessage() != null) {
				this.processWebException(ex, request);
			}

			// Wrap exception if the above code block didn't throw
			throw new ServiceRequestException(String.format(
					Strings.ServiceRequestFailed, ex.getMessage()), ex);
		} catch (IOException e) {
			// Wrap exception.
			throw new ServiceRequestException(String.format(
					Strings.ServiceRequestFailed, e.getMessage()), e);
		}

		return request;
	}

	/**
	 * Checks whether input string is null or empty.
	 * 
	 * @param str
	 *            The input string.
	 * @return true if input string is null or empty, otherwise false
	 */
	private boolean isNullOrEmpty(String str) {
		return null == str || str.isEmpty();
	}

	/**
	 * Try to read the XML declaration. If it's not there, the server didn't
	 * return XML.
	 * 
	 * @param reader
	 *            The reader.
	 * @throws Exception
	 */
	private void readXmlDeclaration(EwsServiceXmlReader reader)
			throws Exception {
		try {
			reader.read(new XmlNodeType(XmlNodeType.START_DOCUMENT));
		} catch (XmlException ex) {
			throw new ServiceRequestException(
					Strings.ServiceResponseDoesNotContainXml, ex);
		} catch (ServiceXmlDeserializationException ex) {
			throw new ServiceRequestException(
					Strings.ServiceResponseDoesNotContainXml, ex);
		}
	}

}
