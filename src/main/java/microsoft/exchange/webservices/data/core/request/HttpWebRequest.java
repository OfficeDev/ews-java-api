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

import microsoft.exchange.webservices.data.EWSConstants;
import microsoft.exchange.webservices.data.core.WebProxy;
import microsoft.exchange.webservices.data.core.exception.http.EWSHttpException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Map;

/**
 * The Class HttpWebRequest.
 */
public abstract class HttpWebRequest {

  /**
   * The url.
   */
  private URL url;

  /**
   * The pre authenticate.
   */
  private boolean preAuthenticate;

  /**
   * The timeout.
   */
  private int timeout;

  /**
   * The content type.
   */
  private String contentType = "text/xml; charset=utf-8";

  /**
   * The accept.
   */
  private String accept = "text/xml";

  /**
   * The user agent.
   */
  private String userAgent = "EWS SDK";

  /**
   * The allow auto redirect.
   */
  private boolean allowAutoRedirect;

  /**
   * The keep alive.
   */
  private boolean keepAlive = true;

  /**
   * The accept gzip encoding.
   */
  private boolean acceptGzipEncoding;

  /**
   * The use default credential.
   */
  private boolean useDefaultCredentials;

  private boolean allowAuthentication = true;

  /**
   * The user name.
   */
  private String username;

  /**
   * The password.
   */
  private String password;

  /**
   * The domain.
   */
  private String domain;

  /**
   * The request Method.
   */
  private String requestMethod = "POST";

  /**
   * The request headers.
   */
  private Map<String, String> headers;

  /**
   * The Web Proxy.
   */
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
   * @param proxy The Web Proxy
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
    return getUrl().getProtocol().equalsIgnoreCase(EWSConstants.HTTP_SCHEME);
  }

  /**
   * Checks if is https scheme.
   *
   * @return true, if is https scheme
   */
  public boolean isHttpsScheme() {
    return getUrl().getProtocol().equalsIgnoreCase(EWSConstants.HTTPS_SCHEME);
  }

  /**
   * Gets the user name.
   *
   * @return the user name
   */
  public String getUsername() {
    return username;
  }

  /**
   * Sets the user name.
   *
   * @param username the new user name
   */
  public void setUsername(String username) {
    this.username = username;
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
   * @param password the new password
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
   * @param domain the new domain
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
   * @param url the new url
   */
  public void setUrl(URL url) {
    this.url = url;
  }

  /**
   * Whether to use preemptive authentication. Currently not implemented, though.
   */
  public boolean isPreAuthenticate() {
    return preAuthenticate;
  }

  /**
   * Whether to use preemptive authentication. Currently not implemented, though.
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
   * @param timeout the new timeout
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
   * @param contentType the new content type
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
   * @param accept the new accept
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
   * @param userAgent the new user agent
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
   * @param allowAutoRedirect the new allow auto redirect
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
   * @param keepAlive the new keep alive
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
   * @param acceptGzipEncoding the new accept gzip encoding
   */
  public void setAcceptGzipEncoding(boolean acceptGzipEncoding) {
    this.acceptGzipEncoding = acceptGzipEncoding;
  }

  /**
   * Checks if is use default credential.
   *
   * @return true, if is use default credential
   */
  public boolean isUseDefaultCredentials() {
    return useDefaultCredentials;
  }

  /**
   * Sets the use default credential.
   *
   * @param useDefaultCredentials the new use default credential
   */
  public void setUseDefaultCredentials(boolean useDefaultCredentials) {
    this.useDefaultCredentials = useDefaultCredentials;
  }

  /**
   * Whether web service authentication is allowed.
   * This can be set to {@code false} to disallow sending credential with this request.
   *
   * This is useful for the autodiscover request to the legacy HTTP url, because this single request doesn't
   * require authentication and we don't want to send credential over HTTP.
   *
   * @return {@code true} if authentication is allowed.
   */
  public boolean isAllowAuthentication() {
    return allowAuthentication;
  }

  /**
   * Whether web service authentication is allowed.
   * This can be set to {@code false} to disallow sending credential with this request.
   *
   * This is useful for the autodiscover request to the legacy HTTP url, because this single request doesn't
   * require authentication and we don't want to send credential over HTTP.
   *
   * Default is {@code true}.
   *
   * @param allowAuthentication {@code true} if authentication is allowed.
   */
  public void setAllowAuthentication(boolean allowAuthentication) {
    this.allowAuthentication = allowAuthentication;
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
   * @param requestMethod the request method type.
   */
  public void setRequestMethod(String requestMethod) {
    this.requestMethod = requestMethod;
  }

  /**
   * Gets the Headers.
   *
   * @return the content type
   */
  public Map<String, String> getHeaders() {
    return headers;
  }

  /**
   * Sets the Headers.
   *
   * @param headers The headers
   */
  public void setHeaders(Map<String, String> headers) {
    this.headers = headers;
  }

  /**
   * Sets the credential.
   *
   * @param domain user domain
   * @param user   user name
   * @param pwd    password
   */
  public void setCredentials(String domain, String user, String pwd) {
    this.domain = domain;
    this.username = user;
    this.password = pwd;
  }

  /**
   * Gets the input stream.
   *
   * @return the input stream
   * @throws EWSHttpException the eWS http exception
   * @throws IOException the IO exception
   */
  public abstract InputStream getInputStream() throws EWSHttpException, IOException;

  /**
   * Gets the error stream.
   *
   * @return the error stream
   * @throws EWSHttpException the eWS http exception
   */
  public abstract InputStream getErrorStream() throws EWSHttpException;

  /**
   * Gets the output stream.
   *
   * @return the output stream
   * @throws EWSHttpException the eWS http exception
   */
  public abstract OutputStream getOutputStream() throws EWSHttpException;

  /**
   * Close.
   */
  public abstract void close() throws IOException;

  /**
   * Prepare connection.
   */
  public abstract void prepareConnection();

  /**
   * Gets the response headers.
   *
   * @return the response headers
   * @throws EWSHttpException the eWS http exception
   */
  public abstract Map<String, String> getResponseHeaders()
      throws EWSHttpException;

  /**
   * Gets the content encoding.
   *
   * @return the content encoding
   * @throws EWSHttpException the EWS http exception
   */
  public abstract String getContentEncoding() throws EWSHttpException;

  /**
   * Gets the response content type.
   *
   * @return the response content type
   * @throws EWSHttpException the EWS http exception
   */
  public abstract String getResponseContentType() throws EWSHttpException;

  /**
   * Gets the response code.
   *
   * @return the response code
   * @throws EWSHttpException the EWS http exception
   */
  public abstract int getResponseCode() throws EWSHttpException;

  /**
   * Gets the response message.
   *
   * @return the response message
   * @throws EWSHttpException the EWS http exception
   */
  public abstract String getResponseText() throws EWSHttpException;

  /**
   * Gets the response header field.
   *
   * @param headerName the header name
   * @return the response header field
   * @throws EWSHttpException the EWS http exception
   */
  public abstract String getResponseHeaderField(String headerName)
      throws EWSHttpException;

  /**
   * Gets the request property.
   *
   * @return the request property
   * @throws EWSHttpException the EWS http exception
   */
  public abstract Map<String, String> getRequestProperty()
      throws EWSHttpException;

  /**
   * Executes Request by sending request xml data to server.
   *
   * @throws EWSHttpException    the EWS http exception
   * @throws java.io.IOException the IO Exception
   */
  public abstract int executeRequest() throws EWSHttpException, IOException;

}
