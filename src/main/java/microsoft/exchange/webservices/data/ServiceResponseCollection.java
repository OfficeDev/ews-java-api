/**************************************************************************
 * copyright file="ServiceResponseCollection.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ServiceResponseCollection.java.
 **************************************************************************/

package microsoft.exchange.webservices.data;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

/***
 * Represents a strongly typed list of service responses.
 * 
 * @param <TResponse>
 *            The type of response stored in the list.
 */
public final class ServiceResponseCollection<TResponse extends ServiceResponse>
		implements Iterable<TResponse> {

	/** The responses. */
	private Vector<TResponse> responses = new Vector<TResponse>();

	/** The overall result. */
	private ServiceResult overallResult = ServiceResult.Success;

	/**
	 * * Initializes a new instance.
	 */
	protected ServiceResponseCollection() {

	}

	/***
	 * Adds specified response.
	 * 
	 * @param response
	 *            The response.
	 */
	protected void add(TResponse response) {

		EwsUtilities.EwsAssert(response != null, "EwsResponseList.Add",
				"response is null");
		if (response.getResult().ordinal() > this.overallResult.ordinal()) {
			this.overallResult = response.getResult();
		}
		this.responses.add(response);
	}

	/***
	 * Gets the total number of responses in the list.
	 * 
	 * @return total number of responses in the list.
	 */
	public int getCount() {
		return this.responses.size();
	}

	/**
	 * * Gets the response at the specified index.
	 * 
	 * @param index
	 *            The zero-based index of the response to get.
	 * @return The response at the specified index.
	 * @throws IndexOutOfBoundsException
	 *             the index out of bounds exception
	 */
	public TResponse getResponseAtIndex(int index)
			throws IndexOutOfBoundsException {
		if (index < 0 || index >= this.getCount()) {
			throw new IndexOutOfBoundsException("Index out of Range");
		}
		return this.responses.get(index);
	}

	/**
	 * * Gets a value indicating the overall result of the request that
	 * generated this response collection. If all of the responses have their
	 * Result property set to Success, OverallResult returns Success. If at
	 * least one response has its Result property set to Warning and all other
	 * responses have their Result property set to Success, OverallResult
	 * returns Warning. If at least one response has a its Result set to Error,
	 * OverallResult returns Error.
	 * 
	 * @return the overall result
	 */
	public ServiceResult getOverallResult() {
		return this.overallResult;
	}

	/**
	 * Returns an iterator over a set of elements of type T.
	 * 
	 * @return an Iterator.
	 */
	@Override
	public Iterator<TResponse> iterator() {
		return responses.iterator();
	}

	/**
	 * Gets the enumerator.
	 * 
	 * @return the enumerator
	 */
	public Enumeration<TResponse> getEnumerator() {
		return this.responses.elements();
	}
}
