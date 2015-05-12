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

import microsoft.exchange.webservices.data.core.request.HttpWebRequest;
import microsoft.exchange.webservices.data.core.request.ServiceRequestBase;
import microsoft.exchange.webservices.data.misc.AsyncCallback;

public class WebAsyncCallStateAnchor {

  ServiceRequestBase serviceRequest;
  HttpWebRequest webRequest;
  AsyncCallback asyncCallback;
  Object asyncState;

  public WebAsyncCallStateAnchor(ServiceRequestBase serviceRequest,
      HttpWebRequest webRequest,
      AsyncCallback asyncCallback,
      Object asyncState)
      throws Exception {
    EwsUtilities.validateParam(serviceRequest, "serviceRequest");
    EwsUtilities.validateParam(webRequest, "webRequest");
    this.serviceRequest = serviceRequest;
    this.webRequest = webRequest;
    this.asyncCallback = asyncCallback;
    this.asyncState = asyncState;
  }

  public ServiceRequestBase getServiceRequest() {
    return this.serviceRequest;
  }

  public void setAsyncCallback(AsyncCallback asyncCallback) {
    this.asyncCallback = asyncCallback;
  }

  public AsyncCallback getAsyncCallback() {
    return this.asyncCallback;
  }

  public void setServiceRequest(ServiceRequestBase wasserviceRequest) {
    serviceRequest = wasserviceRequest;
  }

  public void setHttpWebRequest(HttpWebRequest waswebRequest) {
    webRequest = waswebRequest;
  }

  public HttpWebRequest getHttpWebRequest() {
    return this.webRequest;
  }

  public void setAsynncState(Object wasasyncState) {
    asyncState = wasasyncState;
  }

  public Object getAsyncState() {
    return this.asyncState;
  }
}
