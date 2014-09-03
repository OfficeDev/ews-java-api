/**************************************************************************
 * copyright file="HttpWebRequest.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the HttpWebRequest.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import javax.net.ssl.TrustManager;

import org.apache.commons.httpclient.HttpException;

/**
 * The Class HttpWebRequest.
 */
 abstract class HttpWebRequest {

	/** The url. */
	private URL url;

	/** The pre authenticate. */
	private boolean preAuthenticate;

	/** The timeout. */
	private int timeout;

	/** The content type. */
	private String contentType = "text/xml; charset=utf-8";

	/** The accept. */
	private String accept = "text/xml";

	/** The user agent. */
	private String userAgent = "EWS SDK";

	/** The allow auto redirect. */
	private boolean allowAutoRedirect;

	/** The keep alive. */
	private boolean keepAlive = true;

	/** The accept gzip encoding. */
	private boolean acceptGzipEncoding;

	/** The use default credentials. */
	private boolean useDefaultCredentials;

	/** The user name. */
	private String userName;
	
	/** The password. */
	private String password;

	/** The domain. */
	private String domain;

	/** The request Method. */
	private String requestMethod = "POST";
	
	/** The request headers. */
	private Map<String,String> headers; 
	
	/** The Web Proxy. */
	private WebProxy proxy;

	/**
	 * Gets the Web Proxy.
	 * 
	 * @return the proxy
	 */
	public WebProxy getProxy() {
		return proxy;
	}

	/**
	 * Sets the Web Proxy.
	 * 
	 * @param proxy
	 * 			The Web Proxy
	 */
	public void setProxy(WebProxy proxy) {
		this.proxy = proxy;
	}

	/**
	 * Checks if is http scheme.
	 * 
	 * @return true, if is http scheme
	 */
	public boolean isHttpScheme() {

		if (getUrl().getProtocol().equalsIgnoreCase(EWSConstants.HTTP_SCHEME)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if is https scheme.
	 * 
	 * @return true, if is https scheme
	 */
	public boolean isHttpsScheme() {
		if (getUrl().getProtocol().equalsIgnoreCase(EWSConstants.HTTPS_SCHEME)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Gets the user name.
	 * 
	 * @return the user name
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets the user name.
	 * 
	 * @param userName
	 *            the new user name
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Gets the password.
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 * 
	 * @param password
	 *            the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the domain.
	 * 
	 * @return the domain
	 */
	public String getDomain() {
		return domain;
	}

	/**
	 * Sets the domain.
	 * 
	 * @param domain
	 *            the new domain
	 */
	public void setDomain(String domain) {
		this.domain = domain;
	}

	/**
	 * Gets the url.
	 * 
	 * @return the url
	 */
	public URL getUrl() {

		return url;
	}

	/**
	 * Sets the url.
	 * 
	 * @param url
	 *            the new url
	 */
	public void setUrl(URL url) {
		this.url = url;
	}

	/**
	 * Checks if is pre authenticate.
	 * 
	 * @return true, if is pre authenticate
	 */
	public boolean isPreAuthenticate() {
		return preAuthenticate;
	}

	/**
	 * Sets the pre authenticate.
	 * 
	 * @param preAuthenticate
	 *            the new pre authenticate
	 */
	public void setPreAuthenticate(boolean preAuthenticate) {
		this.preAuthenticate = preAuthenticate;
	}

	/**
	 * Gets the timeout.
	 * 
	 * @return the timeout
	 */
	public int getTimeout() {
		return timeout;
	}

	/**
	 * Sets the timeout.
	 * 
	 * @param timeout
	 *            the new timeout
	 */
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	/**
	 * Gets the content type.
	 * 
	 * @return the content type
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * Sets the content type.
	 * 
	 * @param contentType
	 *            the new content type
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	/**
	 * Gets the accept.
	 * 
	 * @return the accept
	 */
	public String getAccept() {
		return accept;
	}

	/**
	 * Sets the accept.
	 * 
	 * @param accept
	 *            the new accept
	 */
	public void setAccept(String accept) {
		this.accept = accept;
	}

	/**
	 * Gets the user agent.
	 * 
	 * @return the user agent
	 */
	public String getUserAgent() {
		return userAgent;
	}

	/**
	 * Sets the user agent.
	 * 
	 * @param userAgent
	 *            the new user agent
	 */
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	/**
	 * Checks if is allow auto redirect.
	 * 
	 * @return true, if is allow auto redirect
	 */
	public boolean isAllowAutoRedirect() {
		return allowAutoRedirect;
	}

	/**
	 * Sets the allow auto redirect.
	 * 
	 * @param allowAutoRedirect
	 *            the new allow auto redirect
	 */
	public void setAllowAutoRedirect(boolean allowAutoRedirect) {
		this.allowAutoRedirect = allowAutoRedirect;
	}

	/**
	 * Checks if is keep alive.
	 * 
	 * @return true, if is keep alive
	 */
	public boolean isKeepAlive() {
		return keepAlive;
	}

	/**
	 * Sets the keep alive.
	 * 
	 * @param keepAlive
	 *            the new keep alive
	 */
	public void setKeepAlive(boolean keepAlive) {
		this.keepAlive = keepAlive;
	}

	/**
	 * Checks if is accept gzip encoding.
	 * 
	 * @return true, if is accept gzip encoding
	 */
	public boolean isAcceptGzipEncoding() {
		return acceptGzipEncoding;
	}

	/**
	 * Sets the accept gzip encoding.
	 * 
	 * @param acceptGzipEncoding
	 *            the new accept gzip encoding
	 */
	public void setAcceptGzipEncoding(boolean acceptGzipEncoding) {
		this.acceptGzipEncoding = acceptGzipEncoding;
	}

	/**
	 * Checks if is use default credentials.
	 * 
	 * @return true, if is use default credentials
	 */
	public boolean isUseDefaultCredentials() {
		return useDefaultCredentials;
	}

	/**
	 * Sets the use default credentials.
	 * 
	 * @param useDefaultCredentials
	 *            the new use default credentials
	 */
	public void setUseDefaultCredentials(boolean useDefaultCredentials) {
		this.useDefaultCredentials = useDefaultCredentials;
	}

	/**
	 * Gets the request method type.
	 * 
	 * @return the request method type.
	 */
	public String getRequestMethod() {
		return requestMethod;
	}

	/**
	 * Sets the request method type.
	 * 
	 * @param requestMethod
	 *            the request method type.
	 */
	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}
	
	/**
	 * Gets the Headers.
	 * 
	 * @return the content type
	 */
	public Map<String,String> getHeaders() {
		return headers;
	}

	/**
	 * Sets the Headers.
	 * 
	 * @param contentType
	 *            the new content type
	 */
	public void setHeaders(Map<String,String> headers) {
		this.headers = headers;
	}

	/**
	 * Sets the credentails.
	 * 
	 * @param emailAddress
	 *            the email-id
	 * @param pwd
	 *            the password
	 */
	public void setCredentails(String domain, String user, String pwd) {
		this.domain = domain;
		this.userName = user;
		this.password = pwd;
	}

	/**
	 * Gets the input stream.
	 * 
	 * @return the input stream
	 * @throws EWSHttpException
	 *             the eWS http exception
	 * @throws java.io.IOException
	 */
	public abstract InputStream getInputStream() throws EWSHttpException, IOException;

	/**
	 * Gets the error stream.
	 * 
	 * @return the error stream
	 * @throws EWSHttpException
	 *             the eWS http exception
	 */
	public abstract InputStream getErrorStream() throws EWSHttpException;

	/**
	 * Gets the output stream.
	 * 
	 * @return the output stream
	 * @throws EWSHttpException
	 *             the eWS http exception
	 */
	public abstract OutputStream getOutputStream() throws EWSHttpException;

	/**
	 * Close.
	 */
	public abstract void close();

	/**
	 * Prepare connection.
	 * 
	 * @throws EWSHttpException
	 *             the eWS http exception
	 */
	public abstract void prepareConnection() throws EWSHttpException;

	/**
	 * Gets the response headers.
	 * 
	 * @return the response headers
	 * @throws EWSHttpException
	 *             the eWS http exception
	 */
	public abstract Map<String, String> getResponseHeaders()
			throws EWSHttpException;

	/**
	 * Gets the content encoding.
	 * 
	 * @return the content encoding
	 * @throws EWSHttpException
	 *             the eWS http exception
	 */
	public abstract String getContentEncoding() throws EWSHttpException;

	/**
	 * Gets the response content type.
	 * 
	 * @return the response content type
	 * @throws EWSHttpException
	 *             the eWS http exception
	 */
	public abstract String getResponseContentType() throws EWSHttpException;

	/**
	 * Gets the response code.
	 * 
	 * @return the response code
	 * @throws EWSHttpException
	 *             the eWS http exception
	 */
	public abstract int getResponseCode() throws EWSHttpException;
	
	/**
	 * Gets the response message.
	 * 
	 * @return the response message
	 * @throws EWSHttpException
	 *             the eWS http exception
	 */
	public abstract String getResponseText() throws EWSHttpException;

	/**
	 * Gets the response header field.
	 * 
	 * @param headerName
	 *            the header name
	 * @return the response header field
	 * @throws EWSHttpException
	 *             the eWS http exception
	 */
	public abstract String getResponseHeaderField(String headerName)
			throws EWSHttpException;

	/**
	 * Prepare asynchronous connection.
	 * 
	 * @throws EWSHttpException
	 *             the eWS http exception
	 */
	public abstract void prepareAsyncConnection() throws EWSHttpException, UnsupportedEncodingException;
	
	/**
	 * Gets the request properties.
	 * 
	 * @return the request properties
	 * @throws EWSHttpException
	 *             the eWS http exception
	 */
	public abstract Map<String, String> getRequestProperty()
	throws EWSHttpException;
	
	/**
	 * Sets the Client Certificates.
	 * 
	 * @param trustManager
	 * 			the X509TrustManager
	 * @throws EWSHttpException
	 *             the eWS http exception
	 */
	public abstract void setClientCertificates(TrustManager trustManager) 
	throws EWSHttpException;

	/**
	 * Executes Request by sending request xml data to server.
	 * 
	 * @throws EWSHttpException
	 *             the eWS http exception
	 * @throws HttpException
	 *             the http exception
	 * @throws java.io.IOException
	 *             the IO Exception
	 */
	public abstract int executeRequest() throws EWSHttpException, HttpErrorException, IOException;

	public IAsyncResult beginGetResponse(Object webRequestAsyncCallback,
			WebAsyncCallStateAnchor wrappedState) {
		// TODO Auto-generated method stub
		return null;
	}

	

	public ByteArrayOutputStream endGetRequestStream(
			IAsyncResult result) {
		// TODO Auto-generated method stub
		return new ByteArrayOutputStream();
	}
	
	

 
	
}
