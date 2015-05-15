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

package microsoft.exchange.webservices.data.misc;

import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.request.HttpWebRequest;
import microsoft.exchange.webservices.data.core.request.ServiceRequestBase;
import microsoft.exchange.webservices.data.core.request.SimpleServiceRequestBase;
import microsoft.exchange.webservices.data.core.request.WaitHandle;
import microsoft.exchange.webservices.data.core.exception.misc.ArgumentException;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class AsyncRequestResult implements IAsyncResult {

  ServiceRequestBase serviceRequest;
  HttpWebRequest webRequest;
  AsyncCallback wasasyncCallback;
  IAsyncResult webAsyncResult;
  Object asyncState;
  Future<?> task;

  AsyncRequestResult(Future<?> task) {
    this.task = task;
  }


  public AsyncRequestResult(ServiceRequestBase serviceRequest,
      HttpWebRequest webRequest, Future<?> task,
      Object asyncState) throws Exception {
    EwsUtilities.validateParam(serviceRequest, "serviceRequest");
    EwsUtilities.validateParam(webRequest, "webRequest");
    EwsUtilities.validateParam(task, "task");
    this.serviceRequest = serviceRequest;
    this.webRequest = webRequest;
    this.asyncState = asyncState;
    this.task = task;

  }

  public void setServiceRequestBase(ServiceRequestBase serviceRequest) {
    this.serviceRequest = serviceRequest;
  }

  private ServiceRequestBase getServiceRequest() {
    return this.serviceRequest;
  }

  public void setHttpWebRequest(HttpWebRequest webRequest) {
    this.webRequest = webRequest;
  }

  public HttpWebRequest getHttpWebRequest() {
    return this.webRequest;
  }

  public FutureTask<?> getTask() {
    return (FutureTask<?>) this.task;
  }

  public static <T extends SimpleServiceRequestBase> T extractServiceRequest(
      ExchangeService exchangeService, Future<?> asyncResult) throws Exception {
    EwsUtilities.validateParam(asyncResult, "asyncResult");
    AsyncRequestResult asyncRequestResult = (AsyncRequestResult) asyncResult;
    if (asyncRequestResult == null) {
      /**
       * String.InvalidAsyncResult is copied from the error message
       * HttpWebRequest.EndGetResponse() Just use this simple string for
       * all kinds of invalid IAsyncResult parameters.
       */
      throw new ArgumentException("Invalid AsyncResult.",
          "asyncResult");
    }
    // Validate the serivce request.
    if (asyncRequestResult.serviceRequest == null) {
      throw new ArgumentException("Invalid AsyncResult.",
          "asyncResult");
    }
    // Validate the service object
    if (!asyncRequestResult.serviceRequest.getService().equals(
        exchangeService)) {
      throw new ArgumentException("Invalid AsyncResult.",
          "asyncResult");
    }
    T serviceRequest = (T) asyncRequestResult.getServiceRequest();
    // Validate the request type
    if (serviceRequest == null) {
      throw new ArgumentException("Invalid AsyncResult.",
          "asyncResult");
    }
    return serviceRequest;

  }


  @Override
  public boolean cancel(boolean arg0) {
    // TODO Auto-generated method stub
    return false;
  }



  @Override
  public Object get(long timeout, TimeUnit unit)
      throws InterruptedException, ExecutionException,
      TimeoutException {
    // TODO Auto-generated method stub
    return null;
  }


  @Override
  public boolean isCancelled() {
    // TODO Auto-generated method stub
    return false;
  }


  @Override
  public boolean isDone() {
    // TODO Auto-generated method stub
    return false;
  }


  @Override
  public Object getAsyncState() {
    // TODO Auto-generated method stub
    return null;
  }


  @Override
  public WaitHandle getAsyncWaitHanle() {
    // TODO Auto-generated method stub
    return null;
  }


  @Override
  public boolean getCompleteSynchronously() {
    // TODO Auto-generated method stub
    return false;
  }


  @Override
  public boolean getIsCompleted() {
    // TODO Auto-generated method stub
    return false;
  }


  @Override
  public Object get() throws InterruptedException, ExecutionException {
    // TODO Auto-generated method stub
    return this.task.get();
  }



}
