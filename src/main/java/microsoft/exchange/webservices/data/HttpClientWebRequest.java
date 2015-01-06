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

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.NTCredentials;
import org.apache.http.client.CookieStore;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.*;
import org.apache.http.impl.conn.DefaultSchemePortResolver;

import javax.net.ssl.TrustManager;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;


/**
 * HttpClientWebRequest is used for making request to the server through
 * NTLM Authentication by using Apache HttpClient 3.1 and JCIFS Library.
 */
class HttpClientWebRequest extends HttpWebRequest {

  /**
   * The Http Client.
   */
  private CloseableHttpClient client = null;
  private CookieStore cookieStore = null;

  /**
   * The Http Method.
   */
  private HttpPost httpPostReq = null;
  private HttpResponse response = null;

  /**
   * The TrustManager.
   */
  private TrustManager trustManger = null;

  private HttpClientConnectionManager httpClientConnMng = null;


  /**
   * Instantiates a new http native web request.
   */
  public HttpClientWebRequest(HttpClientConnectionManager simpleHttpConnMng) {
    this.httpClientConnMng = simpleHttpConnMng;
  }

  /**
   * Releases the connection by Closing.
   */
  @Override
  public void close() {
    ExecutorService es = CallableSingleTon.getExecutor();
    es.shutdown();
    if (null != httpPostReq) {
      httpPostReq.releaseConnection();
      //postMethod.abort();
    }
    httpPostReq = null;
  }

  /**
   * Prepare connection
   *
   * @throws microsoft.exchange.webservices.data.EWSHttpException the eWS http exception
   */
  @Override
  public void prepareConnection() throws EWSHttpException {
    try {
      HttpClientBuilder builder = HttpClients.custom();
      builder.setConnectionManager(this.httpClientConnMng);

      //create the cookie store
      if (cookieStore == null) {
        cookieStore = new BasicCookieStore();
      }
      builder.setDefaultCookieStore(cookieStore);

      if (getProxy() != null) {
        HttpHost proxy = new HttpHost(getProxy().getHost(), getProxy().getPort());
        builder.setProxy(proxy);

        if (HttpProxyCredentials.isProxySet()) {
          NTCredentials cred =
              new NTCredentials(HttpProxyCredentials.getUserName(), HttpProxyCredentials.getPassword(), "",
                  HttpProxyCredentials.getDomain());
          CredentialsProvider credsProvider = new BasicCredentialsProvider();
          credsProvider.setCredentials(new AuthScope(proxy), cred);
          builder.setDefaultCredentialsProvider(credsProvider);
        }
      }
      if (getUserName() != null) {
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider
            .setCredentials(AuthScope.ANY, new NTCredentials(getUserName(), getPassword(), "", getDomain()));
        builder.setDefaultCredentialsProvider(credsProvider);
      }

      //fix socket config
      SocketConfig sc = SocketConfig.custom().setSoTimeout(getTimeout()).build();
      builder.setDefaultSocketConfig(sc);

      RequestConfig.Builder rcBuilder = RequestConfig.custom();
      rcBuilder.setAuthenticationEnabled(true);
      rcBuilder.setConnectionRequestTimeout(getTimeout());
      rcBuilder.setConnectTimeout(getTimeout());
      rcBuilder.setRedirectsEnabled(isAllowAutoRedirect());
      rcBuilder.setSocketTimeout(getTimeout());
	  
	  // fix issue #144 + #160: if we used NTCredentials from above: these are NT credentials
      if (getUserName() != null) {
    	ArrayList<String> authPrefs = new ArrayList<String>();
    	authPrefs.add(AuthSchemes.NTLM);
    	rcBuilder.setTargetPreferredAuthSchemes(authPrefs).build();
      }
      //
      builder.setDefaultRequestConfig(rcBuilder.build());

      httpPostReq = new HttpPost(getUrl().toString());
      httpPostReq.addHeader("Content-type", getContentType());
      //httpPostReq.setDoAuthentication(true);
      httpPostReq.addHeader("User-Agent", getUserAgent());
      httpPostReq.addHeader("Accept", getAccept());
      httpPostReq.addHeader("Keep-Alive", "300");
      httpPostReq.addHeader("Connection", "Keep-Alive");

      if (isAcceptGzipEncoding()) {
        httpPostReq.addHeader("Accept-Encoding", "gzip,deflate");
      }

      if (getHeaders().size() > 0) {
        for (Map.Entry<String, String> httpHeader : getHeaders().entrySet()) {
          httpPostReq.addHeader(httpHeader.getKey(), httpHeader.getValue());
        }
      }

      //create the client
      client = builder.build();
    } catch (Exception er) {
      er.printStackTrace();
    }
  }

  /**
   * Prepare asynchronous connection.
   *
   * @throws microsoft.exchange.webservices.data.EWSHttpException throws EWSHttpException
   */
  public void prepareAsyncConnection() throws EWSHttpException {
    try {
      //ssl config
      HttpClientBuilder builder = HttpClients.custom();
      builder.setConnectionManager(this.httpClientConnMng);
      builder.setSchemePortResolver(new DefaultSchemePortResolver());

      EwsSSLProtocolSocketFactory factory = EwsSSLProtocolSocketFactory.build(trustManger);
      builder.setSSLSocketFactory(factory);
      builder.setSslcontext(factory.getContext());

      //create the cookie store
      if (cookieStore == null) {
        cookieStore = new BasicCookieStore();
      }
      builder.setDefaultCookieStore(cookieStore);

      CredentialsProvider credsProvider = new BasicCredentialsProvider();
      credsProvider
          .setCredentials(AuthScope.ANY, new NTCredentials(getUserName(), getPassword(), "", getDomain()));
      builder.setDefaultCredentialsProvider(credsProvider);

      //fix socket config
      SocketConfig sc = SocketConfig.custom().setSoTimeout(getTimeout()).build();
      builder.setDefaultSocketConfig(sc);

      RequestConfig.Builder rcBuilder = RequestConfig.custom();
      rcBuilder.setConnectionRequestTimeout(getTimeout());
      rcBuilder.setConnectTimeout(getTimeout());
      rcBuilder.setSocketTimeout(getTimeout());
      builder.setDefaultRequestConfig(rcBuilder.build());

      //HttpClientParams.setRedirecting(client.getParams(), isAllowAutoRedirect()); by default it follows redirects
      //create the client and execute requests
      client = builder.build();
      httpPostReq = new HttpPost(getUrl().toString());
      response = client.execute(httpPostReq);
    } catch (IOException e) {
      client = null;
      httpPostReq = null;
      throw new EWSHttpException("Unable to open connection to " + this.getUrl());
    } catch (Exception e) {
      client = null;
      httpPostReq = null;
      e.printStackTrace();
      throw new EWSHttpException("SSL problem " + this.getUrl());
    }
  }

  /**
   * Method for getting the cookie values.
   */
  public List<Cookie> getCookies() {
    return this.cookieStore.getCookies();
  }

  /**
   * Method for setting the cookie values.
   */
  public void setUserCookie(List<Cookie> rcookies) {
    if (rcookies != null && rcookies.size() > 0) {
      if (this.cookieStore == null) {
        this.cookieStore = new BasicCookieStore();
      }
      for (Cookie c : rcookies) {
        this.cookieStore.addCookie(c);
      }
    }
  }



  /**
   * Gets the input stream.
   *
   * @return the input stream
   * @throws microsoft.exchange.webservices.data.EWSHttpException the eWS http exception
   * @throws java.io.IOException
   */
  @Override
  public InputStream getInputStream() throws EWSHttpException, IOException {
    throwIfResponseIsNull();
    BufferedInputStream bufferedInputStream = null;
    try {
      bufferedInputStream = new BufferedInputStream(response.getEntity().getContent());
    } catch (IOException e) {
      throw new EWSHttpException("Connection Error " + e);
    }
    return bufferedInputStream;
  }

  /**
   * Gets the error stream.
   *
   * @return the error stream
   * @throws microsoft.exchange.webservices.data.EWSHttpException the eWS http exception
   */
  @Override
  public InputStream getErrorStream() throws EWSHttpException {
    throwIfResponseIsNull();
    BufferedInputStream bufferedInputStream = null;
    try {
      bufferedInputStream = new BufferedInputStream(response.getEntity().getContent());
    } catch (Exception e) {
      throw new EWSHttpException("Connection Error " + e);
    }
    return bufferedInputStream;
  }

  /**
   * Gets the output stream.
   *
   * @return the output stream
   * @throws microsoft.exchange.webservices.data.EWSHttpException the eWS http exception
   */
  @Override
  public OutputStream getOutputStream() throws EWSHttpException {
    OutputStream os = null;
    throwIfRequestIsNull();
    os = new ByteArrayOutputStream();

    httpPostReq.setEntity(new ByteArrayOSRequestEntity(os));
    return os;
  }

  /**
   * Gets the response headers.
   *
   * @return the response headers
   * @throws microsoft.exchange.webservices.data.EWSHttpException the eWS http exception
   */
  @Override
  public Map<String, String> getResponseHeaders()
      throws EWSHttpException {
    throwIfResponseIsNull();
    Map<String, String> map = new HashMap<String, String>();

    Header[] hM = response.getAllHeaders();
    for (Header header : hM) {
      // RFC2109: Servers may return multiple Set-Cookie headers
      // Need to append the cookies before they are added to the map
      if (header.getName().equals("Set-Cookie")) {
        String cookieValue = "";
        if (map.containsKey("Set-Cookie")) {
          cookieValue += map.get("Set-Cookie");
          cookieValue += ",";
        }
        cookieValue += header.getValue();
        map.put("Set-Cookie", cookieValue);
      } else {
        map.put(header.getName(), header.getValue());
      }
    }

    return map;
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * microsoft.exchange.webservices.HttpWebRequest#getResponseHeaderField(
   * java.lang.String)
   */
  @Override
  public String getResponseHeaderField(String headerName)
      throws EWSHttpException {
    throwIfResponseIsNull();
    Header hM = response.getFirstHeader(headerName);
    return hM != null ? hM.getValue() : null;
  }

  /**
   * Gets the content encoding.
   *
   * @return the content encoding
   * @throws microsoft.exchange.webservices.data.EWSHttpException the eWS http exception
   */
  @Override
  public String getContentEncoding() throws EWSHttpException {
    throwIfResponseIsNull();
    return response.getFirstHeader("content-encoding") != null ?
        response.getFirstHeader("content-encoding").getValue() :
        null;
  }

  /**
   * Gets the response content type.
   *
   * @return the response content type
   * @throws microsoft.exchange.webservices.data.EWSHttpException the eWS http exception
   */
  @Override
  public String getResponseContentType() throws EWSHttpException {
    throwIfResponseIsNull();
    return response.getFirstHeader("Content-type") != null ?
        response.getFirstHeader("Content-type").getValue() :
        null;
  }

  /**
   * Executes Request by sending request xml data to server.
   *
   * @throws microsoft.exchange.webservices.data.EWSHttpException the eWS http exception
   * @throws java.io.IOException                                  the IO Exception
   */
  @Override
  public int executeRequest() throws EWSHttpException, IOException {
    throwIfRequestIsNull();
    response = client.execute(httpPostReq);
    return response.getStatusLine().getStatusCode(); // ?? don't know what is wanted in return
  }

  /**
   * Gets the response code.
   *
   * @return the response code
   * @throws microsoft.exchange.webservices.data.EWSHttpException the eWS http exception
   */
  @Override
  public int getResponseCode() throws EWSHttpException {
    throwIfResponseIsNull();
    return response.getStatusLine().getStatusCode();
  }

  /**
   * Gets the response message.
   *
   * @return the response message
   * @throws microsoft.exchange.webservices.data.EWSHttpException the eWS http exception
   */
  public String getResponseText() throws EWSHttpException {
    throwIfResponseIsNull();
    return response.getStatusLine().getReasonPhrase();
  }

  /**
   * Throw if conn is null.
   *
   * @throws EWSHttpException the eWS http exception
   */
  private void throwIfRequestIsNull() throws EWSHttpException {
    if (null == httpPostReq) {
      throw new EWSHttpException("Connection not established");
    }
  }

  private void throwIfResponseIsNull() throws EWSHttpException {
    if (null == response) {
      throw new EWSHttpException("Connection not established");
    }
  }

  /**
   * Gets the request properties.
   *
   * @return the request properties
   * @throws microsoft.exchange.webservices.data.EWSHttpException the eWS http exception
   */
  public Map<String, String> getRequestProperty() throws EWSHttpException {
    throwIfRequestIsNull();
    Map<String, String> map = new HashMap<String, String>();

    Header[] hM = httpPostReq.getAllHeaders();
    for (Header header : hM) {
      map.put(header.getName(), header.getValue());
    }
    return map;
  }
}
