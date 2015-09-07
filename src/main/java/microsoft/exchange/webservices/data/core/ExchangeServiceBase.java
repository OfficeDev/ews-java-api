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

package microsoft.exchange.webservices.data.core;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import microsoft.exchange.webservices.data.EWSConstants;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.misc.TraceFlags;
import microsoft.exchange.webservices.data.core.exception.http.EWSHttpException;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceLocalException;
import microsoft.exchange.webservices.data.core.exception.service.remote.AccountIsLockedException;
import microsoft.exchange.webservices.data.core.request.HttpClientWebRequest;
import microsoft.exchange.webservices.data.core.request.HttpWebRequest;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.misc.EwsTraceListener;
import microsoft.exchange.webservices.data.misc.ITraceListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.AuthenticationStrategy;
import org.apache.http.client.CookieStore;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

/**
 * Represents an abstract binding to an Exchange Service.
 */
public abstract class ExchangeServiceBase implements Closeable {
  
  private static final Log LOG = LogFactory.getLog(ExchangeService.class);

  /**
   * The credential.
   */
  private ExchangeCredentials credentials;

  /**
   * The use default credential.
   */
  private boolean useDefaultCredentials;

  /**
   * The binary secret.
   */
  private static byte[] binarySecret;

  /**
   * The timeout.
   */
  private int timeout = 100000;

  /**
   * The trace enabled.
   */
  private boolean traceEnabled;

  /**
   * The trace flags.
   */
  private EnumSet<TraceFlags> traceFlags = EnumSet.allOf(TraceFlags.class);

  /**
   * The trace listener.
   */
  private ITraceListener traceListener = new EwsTraceListener();

  /**
   * The pre authenticate.
   */
  private boolean preAuthenticate;

  /**
   * The user agent.
   */
  private String userAgent = ExchangeServiceBase.defaultUserAgent;

  /**
   * The accept gzip encoding.
   */
  private boolean acceptGzipEncoding = true;

  /**
   * The requested server version.
   */
  private ExchangeVersion requestedServerVersion = ExchangeVersion.Exchange2010_SP2;

  /**
   * The server info.
   */
  private ExchangeServerInfo serverInfo;

  private Map<String, String> httpHeaders = new HashMap<String, String>();

  private Map<String, String> httpResponseHeaders = new HashMap<String, String>();

  private WebProxy webProxy;

  protected CloseableHttpClient httpClient;

  protected HttpClientContext httpContext;

  protected CloseableHttpClient	httpPoolingClient;
  
  private int maximumPoolingConnections = 10;


//  protected HttpClientWebRequest request = null;

  // protected static HttpStatusCode AccountIsLocked = (HttpStatusCode)456;

  /**
   * Default UserAgent.
   */
  private static String defaultUserAgent = "ExchangeServicesClient/" + EwsUtilities.getBuildVersion();

  /**
   * Initializes a new instance.
   *
   * This constructor performs the initialization of the HTTP connection manager, so it should be called by
   * every other constructor.
   */
  protected ExchangeServiceBase() {
    setUseDefaultCredentials(true);
    initializeHttpClient();
    initializeHttpContext();
  }

  protected ExchangeServiceBase(ExchangeVersion requestedServerVersion) {
    this();
    this.requestedServerVersion = requestedServerVersion;
  }

  protected ExchangeServiceBase(ExchangeServiceBase service, ExchangeVersion requestedServerVersion) {
    this(requestedServerVersion);
    this.useDefaultCredentials = service.getUseDefaultCredentials();
    this.credentials = service.getCredentials();
    this.traceEnabled = service.isTraceEnabled();
    this.traceListener = service.getTraceListener();
    this.traceFlags = service.getTraceFlags();
    this.timeout = service.getTimeout();
    this.preAuthenticate = service.isPreAuthenticate();
    this.userAgent = service.getUserAgent();
    this.acceptGzipEncoding = service.getAcceptGzipEncoding();
    this.httpHeaders = service.getHttpHeaders();
  }

  private void initializeHttpClient() {
    Registry<ConnectionSocketFactory> registry = createConnectionSocketFactoryRegistry();
    HttpClientConnectionManager httpConnectionManager = new BasicHttpClientConnectionManager(registry);
    AuthenticationStrategy authStrategy = new CookieProcessingTargetAuthenticationStrategy();

    httpClient = HttpClients.custom()
      .setConnectionManager(httpConnectionManager)
      .setTargetAuthenticationStrategy(authStrategy)
      .build();
  }

  private void initializeHttpPoolingClient() {
    Registry<ConnectionSocketFactory> registry = createConnectionSocketFactoryRegistry();
    PoolingHttpClientConnectionManager httpConnectionManager = new PoolingHttpClientConnectionManager(registry);
    httpConnectionManager.setMaxTotal(maximumPoolingConnections);
    httpConnectionManager.setDefaultMaxPerRoute(maximumPoolingConnections);
    AuthenticationStrategy authStrategy = new CookieProcessingTargetAuthenticationStrategy();

    httpPoolingClient = HttpClients.custom()
        .setConnectionManager(httpConnectionManager)
        .setTargetAuthenticationStrategy(authStrategy)
        .build();
  }

  /**
   * Sets the maximum number of connections for the pooling connection manager which is used for
   * subscriptions.
   * <p>
   * Default is 10.
   * </p>
   * 
   * @param maximumPoolingConnections Maximum number of pooling connections
   */
  public void setMaximumPoolingConnections(int maximumPoolingConnections) {
    if (maximumPoolingConnections < 1)
      throw new IllegalArgumentException("maximumPoolingConnections must be 1 or greater");
    this.maximumPoolingConnections = maximumPoolingConnections;
  }

  /**
   * Create registry with configured {@link ConnectionSocketFactory} instances.
   * Override this method to change how to work with different schemas.
   *
   * @return registry object
   */
  protected Registry<ConnectionSocketFactory> createConnectionSocketFactoryRegistry() {
    try {
      return RegistryBuilder.<ConnectionSocketFactory>create()
        .register(EWSConstants.HTTP_SCHEME, new PlainConnectionSocketFactory())
        .register(EWSConstants.HTTPS_SCHEME, EwsSSLProtocolSocketFactory.build(null))
        .build();
    } catch (GeneralSecurityException e) {
      throw new RuntimeException(
        "Could not initialize ConnectionSocketFactory instances for HttpClientConnectionManager", e
      );
    }
  }

  /**
   * (Re)initializes the HttpContext object. This removes any existing state (mainly cookies). Use an own
   * cookie store, instead of the httpClient's global store, so cookies get reset on reinitialization
   */
  private void initializeHttpContext() {
    CookieStore cookieStore = new BasicCookieStore();
    httpContext = HttpClientContext.create();
    httpContext.setCookieStore(cookieStore);
  }

  @Override
  public void close() {
    try {
      httpClient.close();
    } catch (IOException e) {
      LOG.debug(e);
    }

    if (httpPoolingClient != null) {
      try {
        httpPoolingClient.close();
      } catch (IOException e) {
        LOG.debug(e);
      }
    }
  }

  // Event handlers

  /**
   * Calls the custom SOAP header serialisation event handlers, if defined.
   *
   * @param writer The XmlWriter to which to write the custom SOAP headers.
   */
  public void doOnSerializeCustomSoapHeaders(XMLStreamWriter writer) {
    EwsUtilities
        .ewsAssert(writer != null, "ExchangeService.DoOnSerializeCustomSoapHeaders", "writer is null");

    if (null != getOnSerializeCustomSoapHeaders() &&
        !getOnSerializeCustomSoapHeaders().isEmpty()) {
      for (ICustomXmlSerialization customSerialization : getOnSerializeCustomSoapHeaders()) {
        customSerialization.CustomXmlSerialization(writer);
      }
    }
  }

  // Utilities

  /**
   * Creates an HttpWebRequest instance and initialises it with the
   * appropriate parameters, based on the configuration of this service
   * object.
   *
   * @param url                The URL that the HttpWebRequest should target.
   * @param acceptGzipEncoding If true, ask server for GZip compressed content.
   * @param allowAutoRedirect  If true, redirection response will be automatically followed.
   * @return An initialised instance of HttpWebRequest.
   * @throws ServiceLocalException       the service local exception
   * @throws java.net.URISyntaxException the uRI syntax exception
   */
  protected HttpWebRequest prepareHttpWebRequestForUrl(URI url, boolean acceptGzipEncoding,
      boolean allowAutoRedirect) throws ServiceLocalException, URISyntaxException {
    // Verify that the protocol is something that we can handle
    String scheme = url.getScheme();
    if (!scheme.equalsIgnoreCase(EWSConstants.HTTP_SCHEME)
      && !scheme.equalsIgnoreCase(EWSConstants.HTTPS_SCHEME)) {
      String strErr = String.format("Protocol %s isn't supported for service request.", scheme);
      throw new ServiceLocalException(strErr);
    }

    HttpClientWebRequest request = new HttpClientWebRequest(httpClient, httpContext);
    prepareHttpWebRequestForUrl(url, acceptGzipEncoding, allowAutoRedirect, request);

    return request;
  }

  /**
   * Creates an HttpWebRequest instance from a pooling connection manager and initialises it with
   * the appropriate parameters, based on the configuration of this service object.
   * <p>
   * This is used for subscriptions.
   * </p>
   *
   * @param url The URL that the HttpWebRequest should target.
   * @param acceptGzipEncoding If true, ask server for GZip compressed content.
   * @param allowAutoRedirect If true, redirection response will be automatically followed.
   * @return An initialised instance of HttpWebRequest.
   * @throws ServiceLocalException the service local exception
   * @throws java.net.URISyntaxException the uRI syntax exception
   */
  protected HttpWebRequest prepareHttpPoolingWebRequestForUrl(URI url, boolean acceptGzipEncoding,
      boolean allowAutoRedirect) throws ServiceLocalException, URISyntaxException {
    // Verify that the protocol is something that we can handle
    String scheme = url.getScheme();
    if (!scheme.equalsIgnoreCase(EWSConstants.HTTP_SCHEME)
        && !scheme.equalsIgnoreCase(EWSConstants.HTTPS_SCHEME)) {
      String strErr = String.format("Protocol %s isn't supported for service request.", scheme);
      throw new ServiceLocalException(strErr);
    }

    if (httpPoolingClient == null) {
      initializeHttpPoolingClient();
    }

    HttpClientWebRequest request = new HttpClientWebRequest(httpPoolingClient, httpContext);
    prepareHttpWebRequestForUrl(url, acceptGzipEncoding, allowAutoRedirect, request);

    return request;
  }

  private void prepareHttpWebRequestForUrl(URI url, boolean acceptGzipEncoding, boolean allowAutoRedirect,
      HttpClientWebRequest request) throws ServiceLocalException, URISyntaxException {
    try {
      request.setUrl(url.toURL());
    } catch (MalformedURLException e) {
      String strErr = String.format("Incorrect format : %s", url);
      throw new ServiceLocalException(strErr);
    }

    request.setPreAuthenticate(preAuthenticate);
    request.setTimeout(timeout);
    request.setContentType("text/xml; charset=utf-8");
    request.setAccept("text/xml");
    request.setUserAgent(userAgent);
    request.setAllowAutoRedirect(allowAutoRedirect);
    request.setAcceptGzipEncoding(acceptGzipEncoding);
    request.setHeaders(getHttpHeaders());
    request.setProxy(getWebProxy());
    prepareCredentials(request);

    request.prepareConnection();

    httpResponseHeaders.clear();
  }

  protected void prepareCredentials(HttpWebRequest request) throws ServiceLocalException, URISyntaxException {
    request.setUseDefaultCredentials(useDefaultCredentials);
    if (!useDefaultCredentials) {
      if (credentials == null) {
        throw new ServiceLocalException("Credentials are required to make a service request.");
      }

      // Make sure that credential have been authenticated if required
      credentials.preAuthenticate();

      // Apply credential to the request
      credentials.prepareWebRequest(request);
    }
  }

  /**
   * This method doesn't handle 500 ISE errors. This is handled by the caller since
   * 500 ISE typically indicates that a SOAP fault has occurred and the handling of
   * a SOAP fault is currently service specific.
   *
   * @param httpWebResponse HTTP web response
   * @param webException web exception
   * @param responseHeadersTraceFlag trace flag for response headers
   * @param responseTraceFlag trace flag for respone
   * @throws Exception on error
   */
  protected void internalProcessHttpErrorResponse(HttpWebRequest httpWebResponse, Exception webException,
      TraceFlags responseHeadersTraceFlag, TraceFlags responseTraceFlag) throws Exception {
    EwsUtilities.ewsAssert(500 != httpWebResponse.getResponseCode(),
        "ExchangeServiceBase.InternalProcessHttpErrorResponse",
        "InternalProcessHttpErrorResponse does not handle 500 ISE errors, the caller is supposed to handle this.");

    this.processHttpResponseHeaders(responseHeadersTraceFlag, httpWebResponse);

    // E14:321785 -- Deal with new HTTP error code indicating that account is locked.
    // The "unlock" URL is returned as the status description in the response.
    if (httpWebResponse.getResponseCode() == 456) {
      String location = httpWebResponse.getResponseContentType();

      URI accountUnlockUrl = null;
      if (checkURIPath(location)) {
        accountUnlockUrl = new URI(location);
      }

      final String message = String.format("This account is locked. Visit %s to unlock it.", accountUnlockUrl);
      this.traceMessage(responseTraceFlag, message);
      throw new AccountIsLockedException(message, accountUnlockUrl, webException);
    }
  }

  /**
   * @param location file path
   * @return false if location is null,true if this abstract pathname is absolute
   */
  public static boolean checkURIPath(String location) {
    if (location == null) {
      return false;
    }
    final File file = new File(location);
    return file.isAbsolute();
  }

  /**
   * @param httpWebResponse HTTP web response
   * @param webException web exception
   * @throws Exception on error
   */
  protected abstract void processHttpErrorResponse(HttpWebRequest httpWebResponse, Exception webException)
      throws Exception;

  /**
   * Determines whether tracing is enabled for specified trace flag(s).
   *
   * @param traceFlags The trace flags.
   * @return True if tracing is enabled for specified trace flag(s).
   */
  public boolean isTraceEnabledFor(TraceFlags traceFlags) {
    return this.isTraceEnabled() && this.traceFlags.contains(traceFlags);
  }

  /**
   * Logs the specified string to the TraceListener if tracing is enabled.
   *
   * @param traceType kind of trace entry
   * @param logEntry the entry to log
   * @throws XMLStreamException the XML stream exception
   * @throws IOException signals that an I/O exception has occurred
   */
  public void traceMessage(TraceFlags traceType, String logEntry) throws XMLStreamException, IOException {
    if (this.isTraceEnabledFor(traceType)) {
      String traceTypeStr = traceType.toString();
      String logMessage = EwsUtilities.formatLogMessage(traceTypeStr, logEntry);
      this.traceListener.trace(traceTypeStr, logMessage);
    }
  }

  /**
   * Logs the specified XML to the TraceListener if tracing is enabled.
   *
   * @param traceType Kind of trace entry.
   * @param stream    The stream containing XML.
   */
  public void traceXml(TraceFlags traceType, ByteArrayOutputStream stream) {
    if (this.isTraceEnabledFor(traceType)) {
      String traceTypeStr = traceType.toString();
      String logMessage = EwsUtilities.formatLogMessageWithXmlContent(traceTypeStr, stream);
      this.traceListener.trace(traceTypeStr, logMessage);
    }
  }

  /**
   * Traces the HTTP request headers.
   *
   * @param traceType Kind of trace entry.
   * @param request   The request
   * @throws EWSHttpException EWS http exception
   * @throws URISyntaxException URI syntax error
   * @throws IOException signals that an I/O exception has occurred
   * @throws XMLStreamException the XML stream exception
   */
  public void traceHttpRequestHeaders(TraceFlags traceType, HttpWebRequest request)
      throws URISyntaxException, EWSHttpException, XMLStreamException, IOException {
    if (this.isTraceEnabledFor(traceType)) {
      String traceTypeStr = traceType.toString();
      String headersAsString = EwsUtilities.formatHttpRequestHeaders(request);
      String logMessage = EwsUtilities.formatLogMessage(traceTypeStr, headersAsString);
      this.traceListener.trace(traceTypeStr, logMessage);
    }
  }

  /**
   * Traces the HTTP response headers.
   *
   * @param traceType kind of trace entry
   * @param request the HttpRequest object
   * @throws XMLStreamException the XML stream exception
   * @throws IOException signals that an I/O exception has occurred
   * @throws EWSHttpException the EWS http exception
   */
  private void traceHttpResponseHeaders(TraceFlags traceType, HttpWebRequest request)
      throws XMLStreamException, IOException, EWSHttpException {
    if (this.isTraceEnabledFor(traceType)) {
      String traceTypeStr = traceType.toString();
      String headersAsString = EwsUtilities.formatHttpResponseHeaders(request);
      String logMessage = EwsUtilities.formatLogMessage(traceTypeStr, headersAsString);
      this.traceListener.trace(traceTypeStr, logMessage);
    }
  }

  /**
   * Converts the date time to universal date time string.
   *
   * @param dt the date
   * @return String representation of DateTime in yyyy-MM-ddTHH:mm:ssZ format.
   */
  public String convertDateTimeToUniversalDateTimeString(Date dt) {
    String utcPattern = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    DateFormat utcFormatter = new SimpleDateFormat(utcPattern);
    utcFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
    return utcFormatter.format(dt);
  }

  /**
   * Sets the user agent to a custom value
   *
   * @param userAgent User agent string to set on the service
   */
  protected void setCustomUserAgent(String userAgent) {
    this.userAgent = userAgent;
  }

  /**
   * Validates this instance.
   *
   * @throws ServiceLocalException the service local exception
   */
  public void validate() throws ServiceLocalException {
  }

  /**
   * Gets a value indicating whether tracing is enabled.
   *
   * @return True is tracing is enabled
   */
  public boolean isTraceEnabled() {
    return this.traceEnabled;
  }

  /**
   * Sets a value indicating whether tracing is enabled.
   *
   * @param traceEnabled true to enable tracing
   */
  public void setTraceEnabled(boolean traceEnabled) {
    this.traceEnabled = traceEnabled;
    if (this.traceEnabled && (this.traceListener == null)) {
      this.traceListener = new EwsTraceListener();
    }
  }

  /**
   * Gets the trace flags.
   *
   * @return Set of trace flags.
   */
  public EnumSet<TraceFlags> getTraceFlags() {
    return traceFlags;
  }

  /**
   * Sets the trace flags.
   *
   * @param traceFlags A set of trace flags
   */
  public void setTraceFlags(EnumSet<TraceFlags> traceFlags) {
    this.traceFlags = traceFlags;
  }

  /**
   * Gets the trace listener.
   *
   * @return The trace listener.
   */
  public ITraceListener getTraceListener() {
    return traceListener;
  }

  /**
   * Sets the trace listener.
   *
   * @param traceListener the trace listener.
   */
  public void setTraceListener(ITraceListener traceListener) {
    this.traceListener = traceListener;
    this.traceEnabled = (traceListener != null);
  }

  /**
   * Gets the credential used to authenticate with the Exchange Web Services.
   *
   * @return credential
   */
  public ExchangeCredentials getCredentials() {
    return this.credentials;
  }

  /**
   * Sets the credential used to authenticate with the Exchange Web Services.
   * Setting the Credentials property automatically sets the
   * UseDefaultCredentials to false.
   *
   * @param credentials Exchange credential.
   */
  public void setCredentials(ExchangeCredentials credentials) {
    this.credentials = credentials;
    this.useDefaultCredentials = false;

    // Reset the httpContext, to remove any existing authentication cookies from subsequent request
    initializeHttpContext();
  }

  /**
   * Gets a value indicating whether the credential of the user currently
   * logged into Windows should be used to authenticate with the Exchange Web
   * Services.
   *
   * @return true if credential of the user currently logged in are used
   */
  public boolean getUseDefaultCredentials() {
    return this.useDefaultCredentials;
  }

  /**
   * Sets a value indicating whether the credential of the user currently
   * logged into Windows should be used to authenticate with the Exchange Web
   * Services. Setting UseDefaultCredentials to true automatically sets the
   * Credentials property to null.
   *
   * @param value the new use default credential
   */
  public void setUseDefaultCredentials(boolean value) {
    this.useDefaultCredentials = value;
    if (value) {
      this.credentials = null;
    }

    // Reset the httpContext, to remove any existing authentication cookies from subsequent request
    initializeHttpContext();
  }
  
  /**
   * Gets the timeout used when sending HTTP request and when receiving HTTP
   * response, in milliseconds.
   *
   * @return timeout in milliseconds
   */
  public int getTimeout() {
    return timeout;
  }

  /**
   * Sets the timeout used when sending HTTP request and when receiving HTTP
   * respones, in milliseconds. Defaults to 100000.
   *
   * @param timeout timeout in milliseconds
   */
  public void setTimeout(int timeout) {
    if (timeout < 1) {
      throw new IllegalArgumentException("Timeout must be greater than zero.");
    }
    this.timeout = timeout;
  }

  /**
   * Gets a value that indicates whether HTTP pre-authentication should be
   * performed.
   *
   * @return true indicates pre-authentication is set
   */
  public boolean isPreAuthenticate() {
    return preAuthenticate;
  }

  /**
   * Sets a value that indicates whether HTTP pre-authentication should be
   * performed.
   *
   * @param preAuthenticate true to enable pre-authentication
   */
  public void setPreAuthenticate(boolean preAuthenticate) {
    this.preAuthenticate = preAuthenticate;
  }

  /**
   * Gets a value indicating whether GZip compression encoding should be
   * accepted. This value will tell the server that the client is able to
   * handle GZip compression encoding. The server will only send Gzip
   * compressed content if it has been configured to do so.
   *
   * @return true if compression is used
   */
  public boolean getAcceptGzipEncoding() {
    return acceptGzipEncoding;
  }

  /**
   * Gets a value indicating whether GZip compression encoding should
   * be accepted. This value will tell the server that the client is able to
   * handle GZip compression encoding. The server will only send Gzip
   * compressed content if it has been configured to do so.
   *
   * @param acceptGzipEncoding true to enable compression
   */
  public void setAcceptGzipEncoding(boolean acceptGzipEncoding) {
    this.acceptGzipEncoding = acceptGzipEncoding;
  }

  /**
   * Gets the requested server version.
   *
   * @return The requested server version.
   */
  public ExchangeVersion getRequestedServerVersion() {
    return this.requestedServerVersion;
  }

  /**
   * Gets the user agent.
   *
   * @return The user agent.
   */
  public String getUserAgent() {
    return this.userAgent;
  }

  /**
   * Sets the user agent.
   *
   * @param userAgent The user agent
   */
  public void setUserAgent(String userAgent) {
    this.userAgent = userAgent + " (" + ExchangeServiceBase.defaultUserAgent + ")";
  }

  /**
   * Gets information associated with the server that processed the last
   * request. Will be null if no request have been processed.
   *
   * @return the server info
   */
  public ExchangeServerInfo getServerInfo() {
    return serverInfo;
  }

  /**
   * Sets information associated with the server that processed the last
   * request.
   *
   * @param serverInfo Server Information
   */
  public void setServerInfo(ExchangeServerInfo serverInfo) {
    this.serverInfo = serverInfo;
  }

  /**
   * Gets the web proxy that should be used when sending request to EWS.
   *
   * @return Proxy
   * the Proxy Information
   */
  public WebProxy getWebProxy() {
    return this.webProxy;
  }

  /**
   * Sets the web proxy that should be used when sending request to EWS.
   * Set this property to null to use the default web proxy.
   *
   * @param value the Proxy Information
   */
  public void setWebProxy(WebProxy value) {
    this.webProxy = value;
  }

  /**
   * Gets a collection of HTTP headers that will be sent with each request to
   * EWS.
   *
   * @return httpHeaders
   */
  public Map<String, String> getHttpHeaders() {
    return this.httpHeaders;
  }

  // Events

  /**
   * Provides an event that applications can implement to emit custom SOAP
   * headers in request that are sent to Exchange.
   */
  private List<ICustomXmlSerialization> OnSerializeCustomSoapHeaders;

  /**
   * Gets the on serialize custom soap headers.
   *
   * @return the on serialize custom soap headers
   */
  public List<ICustomXmlSerialization> getOnSerializeCustomSoapHeaders() {
    return OnSerializeCustomSoapHeaders;
  }

  /**
   * Sets the on serialize custom soap headers.
   *
   * @param onSerializeCustomSoapHeaders the new on serialize custom soap headers
   */
  public void setOnSerializeCustomSoapHeaders(List<ICustomXmlSerialization> onSerializeCustomSoapHeaders) {
    OnSerializeCustomSoapHeaders = onSerializeCustomSoapHeaders;
  }

  /**
   * Traces the HTTP response headers.
   *
   * @param traceType kind of trace entry
   * @param request   The request
   * @throws EWSHttpException EWS http exception
   * @throws IOException signals that an I/O exception has occurred
   * @throws XMLStreamException the XML stream exception
   */
  public void processHttpResponseHeaders(TraceFlags traceType, HttpWebRequest request)
      throws XMLStreamException, IOException, EWSHttpException {
    this.traceHttpResponseHeaders(traceType, request);
    this.saveHttpResponseHeaders(request.getResponseHeaders());
  }

  /**
   * Save the HTTP response headers.
   *
   * @param headers The response headers
   */
  private void saveHttpResponseHeaders(Map<String, String> headers) {
    this.httpResponseHeaders.clear();

    for (String key : headers.keySet()) {
      this.httpResponseHeaders.put(key, headers.get(key));
    }
  }

  /**
   * Gets a collection of HTTP headers from the last response.
   * @return HTTP response headers
   */
  public Map<String, String> getHttpResponseHeaders() {
    return this.httpResponseHeaders;
  }

  /**
   * Gets the session key.
   * @return session key
   */
  public static byte[] getSessionKey() {
    // this has to be computed only once.
    synchronized (ExchangeServiceBase.class) {
      if (ExchangeServiceBase.binarySecret == null) {
        Random randomNumberGenerator = new Random();
        ExchangeServiceBase.binarySecret = new byte[256 / 8];
        randomNumberGenerator.nextBytes(binarySecret);
      }

      return ExchangeServiceBase.binarySecret;
    }
  }
}
