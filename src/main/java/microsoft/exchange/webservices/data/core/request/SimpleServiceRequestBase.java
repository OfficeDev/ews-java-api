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

import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.WebAsyncCallStateAnchor;
import microsoft.exchange.webservices.data.enumeration.TraceFlags;
import microsoft.exchange.webservices.data.exception.ServiceLocalException;
import microsoft.exchange.webservices.data.exception.ServiceRequestException;
import microsoft.exchange.webservices.data.misc.AsyncCallback;
import microsoft.exchange.webservices.data.misc.AsyncExecutor;
import microsoft.exchange.webservices.data.misc.AsyncRequestResult;
import microsoft.exchange.webservices.data.misc.CallableMethod;
import microsoft.exchange.webservices.data.misc.IAsyncResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * Defines the SimpleServiceRequestBase class.
 */
public abstract class SimpleServiceRequestBase<T> extends ServiceRequestBase<T> {

  private static final Log log = LogFactory.getLog(SimpleServiceRequestBase.class);

  /**
   * Initializes a new instance of the SimpleServiceRequestBase class.
   */
  protected SimpleServiceRequestBase(ExchangeService service)
      throws Exception {
    super(service);
  }

  /**
   * Executes this request.
   *
   * @throws Exception
   * @throws microsoft.exchange.webservices.data.exception.ServiceLocalException
   */
  protected T internalExecute() throws ServiceLocalException, Exception {
    HttpWebRequest response = null;

    try {
      response = this.validateAndEmitRequest();
      return this.readResponse(response);
    } catch (IOException ex) {
      // Wrap exception.
      throw new ServiceRequestException(String.
          format("The request failed. %s", ex.getMessage()), ex);
    } catch (Exception e) {
      if (response != null) {
        this.getService().processHttpResponseHeaders(TraceFlags.
            EwsResponseHttpHeaders, response);
      }

      throw new ServiceRequestException(String.format("The request failed. %s", e.getMessage()), e);
    }
  }

  /**
   * Ends executing this async request.
   *
   * @param asyncResult The async result
   * @return Service response object.
   */
  protected T endInternalExecute(IAsyncResult asyncResult) throws Exception {
    HttpWebRequest response = (HttpWebRequest) asyncResult.get();
    return this.readResponse(response);
  }

  /**
   * Begins executing this async request.
   *
   * @param callback The AsyncCallback delegate.
   * @param state    An object that contains state information for this request.
   * @return An IAsyncResult that references the asynchronous request.
   */
  public AsyncRequestResult beginExecute(AsyncCallback callback, Object state) throws Exception {
    this.validate();

    HttpWebRequest request = this.buildEwsHttpWebRequest();

    WebAsyncCallStateAnchor wrappedState = new WebAsyncCallStateAnchor(
        this, request, callback /* user callback */, state /*user state*/);

    AsyncExecutor es = new AsyncExecutor();
    Callable<?> cl = new CallableMethod(request);
    Future<?> task = es.submit(cl, callback);
    es.shutdown();
    AsyncRequestResult ft = new AsyncRequestResult(this, request, task, null);

    // ct.setAsyncRequest();
    // webAsyncResult =
    // request.beginGetResponse(SimpleServiceRequestBase.webRequestAsyncCallback(webAsyncResult),
    // wrappedState);
    return ft;
    // return new AsyncRequestResult(this, request, webAsyncResult, state /*
    // user state */);
  }
}
