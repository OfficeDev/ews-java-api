/**************************************************************************
 * copyright file="StreamingSubscription.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the StreamingSubscription class.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents a streaming subscription.
 */
public final class StreamingSubscription extends SubscriptionBase{

	/**
	 * Initializes a new instance of the 
	 * <see cref="StreamingSubscription"/> class.
	 * @param service The service.
	 */
	private ExchangeService service;
	protected StreamingSubscription(ExchangeService service)
	throws Exception {  
		super(service);

	}

	/**
	 * Unsubscribes from the streaming subscription.
	 */
	public void unsubscribe() throws Exception {
		this.getService().unsubscribe(this.getId());
	}

	/**
	 *  Begins an asynchronous request to unsubscribe from the streaming subscription. 
	 * @param callback The AsyncCallback delegate.
	 * @param state An object that contains state information for this request.
	 * @return An IAsyncResult that references the asynchronous request.
	 * @throws Exception 
	 */
    public IAsyncResult beginUnsubscribe(AsyncCallback callback, Object state) throws Exception
    {
        return this.getService().beginUnsubscribe(callback, state, this.getId());
    }

    /**
     * Ends an asynchronous request to unsubscribe from the streaming subscription.
     * @param asyncresult An IAsyncResult that references the asynchronous request.
     * @throws Exception 
     */
    public void endUnsubscribe(IAsyncResult asyncResult) throws Exception
    {
        this.getService().endUnsubscribe(asyncResult);
    }
	/**
	 * Gets the service used to create this subscription.
	 */
	public  ExchangeService getService() {
		return super.getService();
	}


	/**
	 * Gets a value indicating whether this subscription uses watermarks.
	 */
	@Override
	protected  boolean getUsesWatermark() {
		return false;
	}

}

