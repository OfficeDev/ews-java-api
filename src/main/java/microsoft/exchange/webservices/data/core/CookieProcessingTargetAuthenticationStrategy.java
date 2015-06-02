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

import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.auth.MalformedChallengeException;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.protocol.RequestAddCookies;
import org.apache.http.client.protocol.ResponseProcessCookies;
import org.apache.http.impl.client.TargetAuthenticationStrategy;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.util.Map;

/**
 * TargetAuthenticationStrategy that also processes the cookies in HTTP 401 response. While not fully
 * according to the RFC's, this is often necessary to ensure good load balancing behaviour (e.g., TMG server
 * requires it for authentication, where it sends a HTTP 401 with a new Cookie after 5 minutes of inactivity)
 */
public class CookieProcessingTargetAuthenticationStrategy extends TargetAuthenticationStrategy {
  ResponseProcessCookies responseProcessCookies = new ResponseProcessCookies();
  RequestAddCookies requestAddCookies = new RequestAddCookies();

  @Override
  public Map<String, Header> getChallenges(HttpHost authhost, HttpResponse response, HttpContext context)
      throws MalformedChallengeException {
    try {
      // Get the request from the context
      HttpClientContext clientContext = HttpClientContext.adapt(context);
      HttpRequest request = clientContext.getRequest();

      // Save new cookies in the context
      responseProcessCookies.process(response, context);

      // Remove existing cookies and set the new cookies in the request
      request.removeHeaders("Cookie");
      requestAddCookies.process(request, context);
    } catch (HttpException e) {
      throw new RuntimeException(e); // Looking at the source of responseProcessCookies this never happens
    } catch (IOException e) {
      throw new RuntimeException(e); // Looking at the source of responseProcessCookies this never happens
    }

    return super.getChallenges(authhost, response, context);
  }

}
