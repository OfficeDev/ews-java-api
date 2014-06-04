/**************************************************************************
 * copyright file="WebAsyncCallStateAnchor.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the WebAsyncCallStateAnchor.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

public class WebAsyncCallStateAnchor {
	
	ServiceRequestBase serviceRequest;
	HttpWebRequest webRequest;
	AsyncCallback asyncCallback;
	Object asyncState;
	
	/***
	 * Construtctor
	 */
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
	
	public void setAsyncCallback(AsyncCallback asyncCallback){
		this.asyncCallback = asyncCallback;
	}
	
	public AsyncCallback getAsyncCallback(){
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