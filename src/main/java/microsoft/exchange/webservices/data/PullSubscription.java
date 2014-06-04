/**************************************************************************
 * copyright file="PullSubscription.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the PullSubscription.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents a pull subscription.
 * 
 * 
 */
public final class PullSubscription extends SubscriptionBase {

	/** The more events available. */
	private boolean moreEventsAvailable;

	/**
	 * Initializes a new instance.
	 * 
	 * @param service
	 *            the service
	 * @throws Exception
	 *             the exception
	 */
	protected PullSubscription(ExchangeService service) throws Exception {
		super(service);
	}

	/**
	 * Obtains a collection of events that occurred on the subscribed folders
	 * since the point in time defined by the Watermark property. When GetEvents
	 * succeeds, Watermark is updated.
	 * 
	 * @return Returns a collection of events that occurred since the last
	 *         watermark
	 * @throws Exception
	 *             the exception
	 */
	public GetEventsResults getEvents() throws Exception {
		GetEventsResults results = getService().getEvents(this.getId(),
				this.getWaterMark());
		this.setWaterMark(results.getNewWatermark());
		this.moreEventsAvailable = results.isMoreEventsAvailable();
		return results;
	}
	
	/**
	 * Begins an asynchronous request to obtain a collection of events that occurred on the subscribed
	 * folders since the point in time defined by the Watermark property
	 * @param callback
	 * 					 The AsyncCallback delegate
	 * @param state 
	 * 				An object that contains state information for this request
	 * @throws Exception 
	 * @returns An IAsyncResult that references the asynchronous request
	 */
  public IAsyncResult beginGetEvents(AsyncCallback callback, Object state) throws Exception
    {
        return this.getService().beginGetEvents(callback,state, this.getId(), this.getWaterMark());
    }

    /**
	 * Ends an asynchronous request to obtain a collection of events that occurred on the subscribed
	 * folders since the point in time defined by the Watermark property.When EndGetEvents succeeds, Watermark is updated.
	 * @param asyncResult
	 * 					 An IAsyncResult that references the asynchronous request.
	 * @param state 
	 * 				An object that contains state information for this request
     * @throws Exception 
	 * @returns Returns a collection of events that occurred since the last watermark.
	 */
    public GetEventsResults endGetEvents(IAsyncResult asyncResult) throws Exception
    {
        GetEventsResults results = this.getService().endGetEvents(asyncResult);

        this.setWaterMark(results.getNewWatermark());
        this.moreEventsAvailable = results.isMoreEventsAvailable();

        return results;
    }

	/**
	 * Unsubscribes from the pull subscription.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public void unsubscribe() throws Exception {
		getService().unsubscribe(getId());
	}

	
	 /**
	 * Begins an asynchronous request to unsubscribe from the pull subscription. 
	 * @param callback
	 * 					The AsyncCallback delegate.
	 * @param state 
	 * 				An object that contains state information for this request
	 * @throws Exception 
	 * @returns An IAsyncResult that references the asynchronous request
	 */
    public IAsyncResult beginUnsubscribe(AsyncCallback callback, Object state) throws Exception
    {
        return this.getService().beginUnsubscribe(callback,state, this.getId());
    }

    /**
    * Ends an asynchronous request to unsubscribe from the pull subscription. 
    *@param asyncResult An IAsyncResult that references the asynchronous request.
     * @throws Exception 
    */
    public void endUnsubscribe(IAsyncResult asyncResult) throws Exception
    {
        this.getService().endUnsubscribe(asyncResult);
    }

	/**
	 * Gets a value indicating whether more events are available on the server.
	 * MoreEventsAvailable is undefined (null) until GetEvents is called.
	 * 
	 * @return true, if is more events available
	 */
	public boolean isMoreEventsAvailable() {
		return moreEventsAvailable;
	}

}
