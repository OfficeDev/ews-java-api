/**************************************************************************
 * copyright file="AsyncRequestResult.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the AsyncRequestResult.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class AsyncRequestResult implements IAsyncResult{
	
	ServiceRequestBase serviceRequest;
	HttpWebRequest webRequest;
	AsyncCallback wasasyncCallback;
	IAsyncResult webAsyncResult;
	Object asyncState;
	Future task;
	
	 AsyncRequestResult(Future task){
		this.task =task;
	}


		public AsyncRequestResult(ServiceRequestBase serviceRequest,
				HttpWebRequest webRequest, Future task,
				Object asyncState) throws Exception {
			EwsUtilities.validateParam(serviceRequest, "serviceRequest");
			EwsUtilities.validateParam(webRequest, "webRequest");
			EwsUtilities.validateParam(task,"task");
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
		
		public FutureTask getTask(){
			return (FutureTask)this.task;
		}
		public static   <T extends SimpleServiceRequestBase>  T extractServiceRequest(
				ExchangeService exchangeService, Future asyncResult) throws Exception {
			EwsUtilities.validateParam(asyncResult, "asyncResult");
			AsyncRequestResult asyncRequestResult = (AsyncRequestResult)asyncResult;
			if (asyncRequestResult == null) {
				/**
				 * String.InvalidAsyncResult is copied from the error message
				 * HttpWebRequest.EndGetResponse() Just use this simple string for
				 * all kinds of invalid IAsyncResult parameters.
				 */
				throw new ArgumentException(Strings.InvalidAsyncResult,
						"asyncResult");
			}
			// Validate the serivce request.
			if (asyncRequestResult.serviceRequest == null) {
				throw new ArgumentException(Strings.InvalidAsyncResult,
						"asyncResult");
			}
			// Validate the service object
			if (!asyncRequestResult.serviceRequest.getService().equals(
					exchangeService)) {
				throw new ArgumentException(Strings.InvalidAsyncResult,
						"asyncResult");
			}
			T serviceRequest =(T) asyncRequestResult.getServiceRequest();
			// Validate the request type
			if (serviceRequest == null) {
				throw new ArgumentException(Strings.InvalidAsyncResult,
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
