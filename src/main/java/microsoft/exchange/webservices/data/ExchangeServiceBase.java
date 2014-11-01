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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Dictionary;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;

/**
 * Represents an abstract binding to an Exchange Service.
 */
public abstract class ExchangeServiceBase {

	// Fields

	/**
	 * Prefix for "extended" headers.
	 */
	private static final String ExtendedHeaderPrefix = "X-";

	/** The credentials. */
	private ExchangeCredentials credentials;

	/** The use default credentials. */
	private boolean useDefaultCredentials;
	
	/** The binary secret.*/
    private static byte[] binarySecret;

	/** The timeout. */
	private int timeout = 100000;

	/** The trace enabled. */
	private boolean traceEnabled;

	/** The trace flags. */
	private EnumSet<TraceFlags> traceFlags = EnumSet.allOf(TraceFlags.class);

	/** The trace listener. */
	private ITraceListener traceListener = new EwsTraceListener();

	/** The pre authenticate. */
	private boolean preAuthenticate;

	/** The user agent. */
	private String userAgent = ExchangeServiceBase.defaultUserAgent;

	/** The accept gzip encoding. */
	private boolean acceptGzipEncoding = true;

	/** The requested server version. */
	private ExchangeVersion requestedServerVersion = 
		ExchangeVersion.Exchange2010_SP2;

	/** The server info. */
	private ExchangeServerInfo serverInfo;

	private Map<String, String> httpHeaders = new HashMap<String, String>();
	
	private Map<String, String> httpResponseHeaders = new HashMap<String, String>();

	private TimeZone timeZone;

	private WebProxy webProxy;
	
	private HttpConnectionManager simpleHttpConnectionManager = new MultiThreadedHttpConnectionManager(); 
	
	HttpClientWebRequest request = null;
	
	private Cookie[] cookies = null;

	// protected static HttpStatusCode AccountIsLocked = (HttpStatusCode)456;

	/**
	 * Static members
	 */

	protected HttpConnectionManager getSimpleHttpConnectionManager() {
		return simpleHttpConnectionManager;
	}

	/** Default UserAgent. */
	private static String defaultUserAgent = "ExchangeServicesClient/" + 
	EwsUtilities.getBuildVersion();

	protected ExchangeServiceBase(ExchangeServiceBase service,
			ExchangeVersion requestedServerVersion) {
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
		this.timeZone = service.getTimeZone();
		this.httpHeaders = service.getHttpHeaders();
	}

	/**
	 * @return TimeZone
	 */
	private TimeZone getTimeZone() {
		return this.timeZone;
	}

	/*
	protected ExchangeServiceBase(ExchangeServiceBase service) {

		this(service.getRequestedServerVersion());
		this.useDefaultCredentials = service.getUseDefaultCredentials();
		this.credentials = service.getCredentials();
		this.traceEnabled = service.isTraceEnabled();
		this.traceListener = service.getTraceListener();
		this.traceFlags = service.getTraceFlags();
		this.timeout = service.getTimeout();
		this.preAuthenticate = service.isPreAuthenticate();
		this.userAgent = service.getUserAgent();
		this.acceptGzipEncoding = service.getAcceptGzipEncoding();
	}*/
	
	/**
	 * Initializes a new instance from existing one.
	 * 
	 * @param service
	 *            The other service.
	 * @see microsoft.exchange.webservices.data.ExchangeServiceBase
	 */
	protected ExchangeServiceBase(ExchangeServiceBase service) {
		   this(service, service.getRequestedServerVersion());
    }
    
	protected ExchangeServiceBase() {
		this(TimeZone.getDefault());
	}

	protected ExchangeServiceBase(ExchangeVersion requestedServerVersion, 
			TimeZone timeZone) {
		this(timeZone);
		this.requestedServerVersion = requestedServerVersion;
	}

	protected ExchangeServiceBase(TimeZone timeZone){
		this.timeZone = timeZone;
		this.setUseDefaultCredentials(true);
	}

	// Event handlers

	/**
	 * Calls the custom SOAP header serialisation event handlers, if defined.
	 * 
	 * @param writer
	 *            The XmlWriter to which to write the custom SOAP headers.
	 */
	protected void doOnSerializeCustomSoapHeaders(XMLStreamWriter writer) {
		EwsUtilities.EwsAssert(writer != null,
				"ExchangeService.DoOnSerializeCustomSoapHeaders",
		"writer is null");
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
	 * @param url
	 *            The URL that the HttpWebRequest should target.
	 * @param acceptGzipEncoding
	 *            If true, ask server for GZip compressed content.
	 * @param allowAutoRedirect
	 *            If true, redirection responses will be automatically followed.
	 * @return An initialised instance of HttpWebRequest.
	 * @throws ServiceLocalException
	 *             the service local exception
	 * @throws java.net.URISyntaxException
	 *             the uRI syntax exception
	 */
	protected HttpWebRequest prepareHttpWebRequestForUrl(URI url,
			boolean acceptGzipEncoding, boolean allowAutoRedirect)
	throws ServiceLocalException, URISyntaxException {
		// Verify that the protocol is something that we can handle
		if (!url.getScheme().equalsIgnoreCase("HTTP")
				&& !url.getScheme().equalsIgnoreCase("HTTPS")) {
			String strErr = String.format(Strings.UnsupportedWebProtocol, url.
					getScheme());
			throw new ServiceLocalException(strErr);
		}

		 request = new HttpClientWebRequest(this.simpleHttpConnectionManager);
		try {
			request.setUrl(url.toURL());
		} catch (MalformedURLException e) {
			String strErr = String.format("Incorrect format : %s", url);
			throw new ServiceLocalException(strErr);
		}
		request.setPreAuthenticate(this.preAuthenticate);
		request.setTimeout(this.timeout);
		request.setContentType("text/xml; charset=utf-8");
		request.setAccept("text/xml");
		request.setUserAgent(this.userAgent);
		request.setAllowAutoRedirect(allowAutoRedirect);
		//request.setKeepAlive(true);

		if (acceptGzipEncoding) {
			request.setAcceptGzipEncoding(acceptGzipEncoding);
		}

		if (this.webProxy != null) {
			request.setProxy(this.webProxy);
		}

		//if (this.getHttpHeaders().size() > 0){
			request.setHeaders(this.getHttpHeaders());
		//}
		
		request.setUseDefaultCredentials(useDefaultCredentials);
		if (!useDefaultCredentials) {
			ExchangeCredentials serviceCredentials = this.credentials;
			if (null == serviceCredentials) {
				throw new ServiceLocalException(Strings.CredentialsRequired);
			}
			
			if(this.cookies != null && this.cookies.length > 0){
				request.setUserCookie(this.cookies);
			}
			// Make sure that credentials have been authenticated if required
			serviceCredentials.preAuthenticate();

			// Apply credentials to the request
			serviceCredentials.prepareWebRequest(request);
			
		}
		try {
			request.prepareConnection();
		} catch (Exception e) {
			String strErr = String.format("%s : Connection error ", url);
			throw new ServiceLocalException(strErr);
		}
		
		this.httpResponseHeaders.clear();
		 
		return request;
	}

	/**
	 * This method doesn't handle 500 ISE errors. This is handled by the caller since
     * 500 ISE typically indicates that a SOAP fault has occurred and the handling of
     * a SOAP fault is currently service specific.
	 *
	 * @throws Exception
	 */
	protected void internalProcessHttpErrorResponse(
			HttpWebRequest httpWebResponse,
			Exception webException,
			TraceFlags responseHeadersTraceFlag,
			TraceFlags responseTraceFlag) throws Exception
	{
		EwsUtilities.EwsAssert(
				500 != httpWebResponse.getResponseCode(),
				"ExchangeServiceBase.InternalProcessHttpErrorResponse",
		"InternalProcessHttpErrorResponse does not handle 500 ISE errors," +
		" the caller is supposed to handle this.");

		this.processHttpResponseHeaders( responseHeadersTraceFlag, 
				httpWebResponse);

		// E14:321785 -- Deal with new HTTP 
		// error code indicating that account is locked.
		// The "unlock" URL is returned as 
		// the status description in the response.
		if (httpWebResponse.getResponseCode() == 456)
		{
			String location = httpWebResponse.getResponseContentType();

			URI accountUnlockUrl = null;
			if (checkURIPath(location))
			{
				accountUnlockUrl = new URI(location);
			}

			this.traceMessage(responseTraceFlag, String.format("Account is locked." +
					" Unlock URL is {0}", accountUnlockUrl));

			throw new AccountIsLockedException(
					String.format(Strings.AccountIsLocked, accountUnlockUrl),
					accountUnlockUrl,
					webException);
		}
	}

	/**
	 * @return false if location is null,true if this abstract pathname is
	 *         absolute,
	 */
	public static boolean checkURIPath(String location) {
	    if(location == null) {
	        return false;
	    }
	    final File file = new File(location);
	    return file.isAbsolute();	    
	  }

	/**
	 * @throws Exception
	 */
	protected abstract void processHttpErrorResponse(
			HttpWebRequest httpWebResponse, Exception webException)
			throws Exception;

	/**
	 * Determines whether tracing is enabled for specified trace flag(s).
	 * 
	 * @param traceFlags
	 *            The trace flags.
	 * @return True if tracing is enabled for specified trace flag(s).
	 */
	protected boolean isTraceEnabledFor(TraceFlags traceFlags) {
		return this.isTraceEnabled() && this.traceFlags.contains(traceFlags);
	}

	/**
	 * Logs the specified string to the TraceListener if tracing is enabled.
	 * 
	 * @param traceType
	 *            Kind of trace entry.
	 * @param logEntry
	 *            The entry to log.
	 * @throws javax.xml.stream.XMLStreamException
	 *             the xML stream exception
	 * @throws java.io.IOException
	 *             Signals that an I/O exception has occurred.
	 */
	protected void traceMessage(TraceFlags traceType, String logEntry)
	throws XMLStreamException, IOException {
		if (this.isTraceEnabledFor(traceType)) {
			String traceTypeStr = traceType.toString();
			String logMessage = EwsUtilities.formatLogMessage(traceTypeStr,
					logEntry);
			this.traceListener.trace(traceTypeStr, logMessage);
		}
	}

	/**
	 * Logs the specified XML to the TraceListener if tracing is enabled.
	 * 
	 * @param traceType
	 *            Kind of trace entry.
	 * @param stream
	 *            The stream containing XML.
	 */
	protected void traceXml(TraceFlags traceType,
			ByteArrayOutputStream stream) {
		if (this.isTraceEnabledFor(traceType)) {
			String traceTypeStr = traceType.toString();
			String logMessage = EwsUtilities.formatLogMessageWithXmlContent(
					traceTypeStr, stream);
			this.traceListener.trace(traceTypeStr, logMessage);
		}
	}
	
	/**
	 * Traces the HTTP request headers.
	 * 
	 * @param traceType
	 *            Kind of trace entry.
	 * @param request The request	
	 * @throws microsoft.exchange.webservices.data.EWSHttpException
	 * @throws java.net.URISyntaxException
	 * @throws java.io.IOException
	 * @throws javax.xml.stream.XMLStreamException
	 */	
	protected void traceHttpRequestHeaders(TraceFlags traceType,
			HttpWebRequest request) 
	throws URISyntaxException, EWSHttpException, XMLStreamException, IOException {
        if (this.isTraceEnabledFor(traceType)) {
            String traceTypeStr = traceType.toString();
            String headersAsString = EwsUtilities.
            formatHttpRequestHeaders(request);
            String logMessage = EwsUtilities.
            formatLogMessage(traceTypeStr, headersAsString);
            this.traceListener.trace(traceTypeStr, logMessage);
        }
    }

	/**
	 * Traces the HTTP response headers.
	 * 
	 * @param traceType
	 *            Kind of trace entry.
	 * @param request
	 *            The HttpRequest object.
	 * @throws javax.xml.stream.XMLStreamException
	 *             the xML stream exception
	 * @throws java.io.IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws microsoft.exchange.webservices.data.EWSHttpException
	 *             the eWS http exception
	 */
	private void traceHttpResponseHeaders(TraceFlags traceType,
			HttpWebRequest request) throws XMLStreamException, IOException,
			EWSHttpException {
		if (this.isTraceEnabledFor(traceType)) {
			String traceTypeStr = traceType.toString();
			String headersAsString = EwsUtilities.formatHttpResponseHeaders(request);
			String logMessage = EwsUtilities.formatLogMessage(traceTypeStr,
					headersAsString);
			this.traceListener.trace(traceTypeStr, logMessage);
		}
	}

	/**
	 * Converts the universal date time string to local date time.
	 * 
	 * @param dateString
	 *            The value.
	 * @return DateTime Returned date is always in UTC date.
	 */
	protected Date convertUniversalDateTimeStringToDate(String dateString) {
		String localTimeRegex = "^(.*)([+-]{1}\\d\\d:\\d\\d)$";
		Pattern localTimePattern = Pattern.compile(localTimeRegex);
		String timeRegex = "[0-9]{2,4}-[0-9]{1,2}-[0-9]{1,2}T[0-9]{2}:[0-9]{1,2}:[0-9]{1,2}.[0-9]{1,7}";		
		Pattern timePattern = Pattern.compile(timeRegex);
		String utcPattern = "yyyy-MM-dd'T'HH:mm:ss'Z'";
		String utcPattern1 = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'";
		String localPattern = "yyyy-MM-dd'T'HH:mm:ssz";
		String localPattern1 = "yyyy-MM-dd'Z'";
		String pattern = "yyyy-MM-ddz";
		String localPattern2 = "yyyy-MM-dd'T'HH:mm:ss";
		DateFormat utcFormatter = null;
		Date dt = null;
		String errMsg = String.format(
				"Date String %s not in valid UTC/local format", dateString);
		if (dateString == null || dateString.isEmpty()) {
			return null;
		} else {
			if (dateString.endsWith("Z")) {
				// String in UTC format yyyy-MM-ddTHH:mm:ssZ
				utcFormatter = new SimpleDateFormat(utcPattern);
				utcFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
				try {
					dt = utcFormatter.parse(dateString);
				} catch (ParseException e) {
					utcFormatter = new SimpleDateFormat(pattern);
					utcFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
					//	dateString = dateString.substring(0, 10)+"T12:00:00Z";
					try {
						dt = utcFormatter.parse(dateString);
					} catch (ParseException e1) {
						utcFormatter = new SimpleDateFormat(localPattern1);
						utcFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));

						try {
							dt = utcFormatter.parse(dateString);
						} catch (ParseException ex){
							
							utcFormatter = new SimpleDateFormat(utcPattern1);
							utcFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
						}
							try{
							dt = utcFormatter.parse(dateString);
						}
						catch (ParseException e2)	{
							throw new IllegalArgumentException(errMsg, e);
						}

					}
					// throw new IllegalArgumentException(errMsg,e);
				}
			} else if (dateString.endsWith("z")) {
				// String in UTC format yyyy-MM-ddTHH:mm:ssZ
				utcFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'z'");
				utcFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
				try {
					dt = utcFormatter.parse(dateString);
				} catch (ParseException e) {
					throw new IllegalArgumentException(e);
				}
			} else {
				// dateString is not ending with Z.
				// Check for yyyy-MM-ddTHH:mm:ss+/-HH:mm pattern
				Matcher localTimeMatcher = localTimePattern.matcher(dateString);
				if (localTimeMatcher.find()) {
					System.out.println("Pattern matched");
					String date = localTimeMatcher.group(1);
					String zone = localTimeMatcher.group(2);
					// Add the string GMT between DateTime and TimeZone
					// since 'z' in yyyy-MM-dd'T'HH:mm:ssz matches
					// either format GMT+/-HH:mm or +/-HHmm
					dateString = String.format("%sGMT%s", date, zone);
					try {
						utcFormatter = new SimpleDateFormat(localPattern);
						utcFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
						dt = utcFormatter.parse(dateString);
					} catch (ParseException e) {
						try {
							utcFormatter = new SimpleDateFormat(pattern);
							utcFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
							dt = utcFormatter.parse(dateString);
						} catch (ParseException ex) {
							throw new IllegalArgumentException(ex);
						}
					}
				} else {
					// Invalid format
					utcFormatter = new SimpleDateFormat(localPattern2);
					utcFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
					try {
						dt = utcFormatter.parse(dateString);
					} catch (ParseException e) {
						e.printStackTrace();
						throw new IllegalArgumentException(errMsg);
					}
				}
			}
			return dt;
		}
	}

	/**
	 * Converts xs:dateTime string with either "Z", "-00:00" bias, or ""
	 * suffixes to unspecified StartDate value ignoring the suffix.Needs to fix
	 * E14:232996.
	 * 
	 * @param value
	 *            The string value to parse.
	 * @return The parsed DateTime value.
	 */
	protected Date convertStartDateToUnspecifiedDateTime(String value)
			throws ParseException {
		if (value == null || value.isEmpty()) {
			return null;
		} else {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd'Z'");
			return df.parse(value);
		}
	}

	/**
	 * Converts the date time to universal date time string.
	 * 
	 * @param dt
	 *            the date
	 * @return String representation of DateTime in yyyy-MM-ddTHH:mm:ssZ format.
	 */
	protected String convertDateTimeToUniversalDateTimeString(Date dt) {
		String utcPattern = "yyyy-MM-dd'T'HH:mm:ss'Z'";
		DateFormat utcFormatter = new SimpleDateFormat(utcPattern);
		utcFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		return utcFormatter.format(dt);
	}

	/**
	 * Sets the user agent to a custom value
	 * 
	 * @param userAgent
	 *            User agent string to set on the service
	 */
	protected void setCustomUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	// Constructors

	/**
	 * Initializes a new instance.
	 * 
	 * @param requestedServerVersion
	 *            The requested server version.
	 */
	protected ExchangeServiceBase(ExchangeVersion requestedServerVersion) {
		// Removed because TimeZone class in Java doesn't maintaining the 
		//history of time change rules for a given time zone
		//this(requestedServerVersion, TimeZone.getDefault());
		this.requestedServerVersion = requestedServerVersion;
	}

	

	// Abstract methods

	/**
	 * Validates this instance.
	 * 
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	protected  void validate() throws ServiceLocalException	{
		
		// E14:302056 -- Allow clients to add HTTP request 
		//headers with 'X-' prefix but no others.
		for (Map.Entry<String, String> key : this.httpHeaders.entrySet()) {
			if (!key.getKey().startsWith(ExtendedHeaderPrefix))	{
				throw new ServiceValidationException(String.format(Strings.
						CannotAddRequestHeader, key));
			}
		}
	}

	/**
	 * Gets  the cookie container. <value>The cookie container.</value>
	 * 
	 * @param url
	 *            the url
	 * @param value
	 *            the value
	 * @throws java.io.IOException
	 *             , URISyntaxException
	 * @throws java.net.URISyntaxException
	 *             the uRI syntax exception
	 */
	/*public void setCookie(URL url, String value) throws IOException,
	URISyntaxException {
		CookieHandler handler =CookieHandler.getDefault();
		if (handler != null) {
			Map<String, List<String>> headers =
				new HashMap<String, List<String>>();
			List<String> values = new Vector<String>();
			values.add(value);
			headers.put("Cookie", values);

			handler.put(url.toURI(), headers);
		}
	}*/
	
	/**
	 * Sets the cookie container.
	 * 
	 * @param rcookies the cookies.
	 * @throws microsoft.exchange.webservices.data.EWSHttpException
	 */
	public void setCookie(Cookie[] rcookies) throws EWSHttpException {

		if (rcookies != null && rcookies.length > 0)
			this.cookies = rcookies.clone();

	}

	/*
	 * Gets the cookie.
	 * 
	 * @param url
	 *            the url
	 * @return the cookie
	 * @throws java.io.IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws java.net.URISyntaxException
	 *             the uRI syntax exception
	public String getCookie(URL url) throws IOException, URISyntaxException {
		String cookieValue = null;

		CookieHandler handler = CookieHandler.getDefault();
		if (handler != null) {
			Map<String, List<String>> headers = handler.get(url.toURI(),
					new HashMap<String, List<String>>());
			List<String> values = headers.get("Cookie");
			for (Iterator<String> iter = values.iterator(); iter.hasNext();) {
				String v = iter.next();

				if (cookieValue == null)
					cookieValue = v;
				else
					cookieValue = cookieValue + ";" + v;
			}
		}
		return cookieValue;
	}*/
	
    public Cookie[] getCookies() {
		return this.cookies;
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
	 * @param traceEnabled
	 *            true to enable tracing
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
	 * @param traceFlags
	 *            A set of trace flags
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
	 * @param traceListener
	 *            the trace listener.
	 */
	public void setTraceListener(ITraceListener traceListener) {
		this.traceListener = traceListener;
		this.traceEnabled = (traceListener != null);
	}

	/**
	 * Gets the credentials used to authenticate with the Exchange Web Services.
	 * 
	 * @return credentials
	 */
	public ExchangeCredentials getCredentials() {
		return this.credentials;
	}

	/**
	 * Sets the credentials used to authenticate with the Exchange Web Services.
	 * Setting the Credentials property automatically sets the
	 * UseDefaultCredentials to false.
	 * 
	 * @param credentials
	 *            Exchange credentials.
	 */
	public void setCredentials(ExchangeCredentials credentials) {
		this.credentials = credentials;
		this.useDefaultCredentials = false;
		CookieHandler.setDefault(new CookieManager());
	}

	/**
	 * Gets a value indicating whether the credentials of the user currently
	 * logged into Windows should be used to authenticate with the Exchange Web
	 * Services.
	 * 
	 * @return true if credentials of the user currently logged in are used
	 */
	public boolean getUseDefaultCredentials() {
		return this.useDefaultCredentials;
	}

	/**
	 * Sets a value indicating whether the credentials of the user currently
	 * logged into Windows should be used to authenticate with the Exchange Web
	 * Services. Setting UseDefaultCredentials to true automatically sets the
	 * Credentials property to null.
	 * 
	 * @param value
	 *            the new use default credentials
	 */
	public void setUseDefaultCredentials(boolean value) {
		this.useDefaultCredentials = value;
		if (value) {
			this.credentials = null;
			CookieHandler.setDefault(null);
		}
	}

	/**
	 * Gets the timeout used when sending HTTP requests and when receiving HTTP
	 * responses, in milliseconds.
	 * 
	 * @return timeout in milliseconds
	 */
	public int getTimeout() {
		return timeout;
	}

	/**
	 * Sets the timeout used when sending HTTP requests and when receiving HTTP
	 * respones, in milliseconds. Defaults to 100000.
	 * 
	 * @param timeout
	 *            timeout in milliseconds
	 */
	public void setTimeout(int timeout) {
		if (timeout < 1) {
			throw new IllegalArgumentException(
					Strings.TimeoutMustBeGreaterThanZero);
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
	 * @param preAuthenticate
	 *            true to enable pre-authentication
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
	 * @param acceptGzipEncoding
	 *            true to enable compression
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
	 * @param userAgent
	 *            The user agent
	 */
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent + " ("
		+ ExchangeServiceBase.defaultUserAgent + ")";
	}

	/**
	 * Gets information associated with the server that processed the last
	 * request. Will be null if no requests have been processed.
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
	 * @param serverInfo
	 *            Server Information
	 */
	void setServerInfo(ExchangeServerInfo serverInfo) {
		this.serverInfo = serverInfo;
	}

	/**
	 * Gets the web proxy that should be used when sending requests to EWS.
	 * 
	 * @return Proxy
	 *            the Proxy Information
	 */
    public WebProxy getWebProxy() {
        return this.webProxy;
    }
    
    /**
	 * Sets the web proxy that should be used when sending requests to EWS.
     * Set this property to null to use the default web proxy.
	 * 
	 * @param value
	 *            the Proxy Information
	 */
    public void setWebProxy(WebProxy value) {
        this.webProxy = value; 
    }
	
	/**
	 * Gets a collection of HTTP headers that will be sent with each request to
	 * EWS.
	 * @return httpHeaders
	 */
	public Map<String, String> getHttpHeaders() {
		return this.httpHeaders;
	}

	// Events

	/**
	 * Provides an event that applications can implement to emit custom SOAP
	 * headers in requests that are sent to Exchange.
	 */
	private List<ICustomXmlSerialization> OnSerializeCustomSoapHeaders;

	/**
	 * Gets the on serialize custom soap headers.
	 * 
	 * @return the on serialize custom soap headers
	 */
	public List<ICustomXmlSerialization> 
	getOnSerializeCustomSoapHeaders() {
		return OnSerializeCustomSoapHeaders;
	}

	/**
	 * Sets the on serialize custom soap headers.
	 * 
	 * @param onSerializeCustomSoapHeaders
	 *            the new on serialize custom soap headers
	 */
	public void setOnSerializeCustomSoapHeaders(
			List<ICustomXmlSerialization> 
			onSerializeCustomSoapHeaders) {
		OnSerializeCustomSoapHeaders = onSerializeCustomSoapHeaders;
	}
	
	/**
	 * Traces the HTTP response headers.
	 * @param traceType
	 * 			kind of trace entry
	 * @param request
	 * 			The request
	 * @throws microsoft.exchange.webservices.data.EWSHttpException
	 * @throws java.io.IOException
	 * @throws javax.xml.stream.XMLStreamException
	 */
    protected void processHttpResponseHeaders(TraceFlags traceType, HttpWebRequest request) throws XMLStreamException, IOException, EWSHttpException
    {
        this.traceHttpResponseHeaders(traceType, request);

        this.saveHttpResponseHeaders(request.getResponseHeaders());
    }
    
   /**
    * Save the HTTP response headers.
    * @param headers The response headers
    */
	private void saveHttpResponseHeaders(Map<String, String> headers) {
		EwsUtilities.EwsAssert(this.httpResponseHeaders.size() == 0,
				"ExchangeServiceBase.SaveHttpResponseHeaders",
				"expect no headers in the dictionary yet.");

		this.httpResponseHeaders.clear();

		for (String key : headers.keySet()) {
			this.httpResponseHeaders.put(key, headers.get(key));
		}

		// Save the cookies for subsequent requests
		if (this.request.getCookies() != null) {
			this.cookies = this.request.getCookies().clone();
		}
		
	}
    
    /**
    * Gets a collection of HTTP headers from the last response.
    **/
    public Map<String, String> getHttpResponseHeaders()
    {
      return this.httpResponseHeaders; 
    }

    /**
     * Gets the session key.
     */
    protected static byte[] getSessionKey()
    {
            // this has to be computed only once.
            synchronized(ExchangeServiceBase.class)
            {
                if (ExchangeServiceBase.binarySecret == null)
                {
                    Random randomNumberGenerator = new Random();
                    ExchangeServiceBase.binarySecret = new byte[256 / 8];
                    randomNumberGenerator.nextBytes(binarySecret);
                }

                return ExchangeServiceBase.binarySecret;
        }
    }
}
