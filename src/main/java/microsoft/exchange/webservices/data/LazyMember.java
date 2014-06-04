/**************************************************************************
 * copyright file="LazyMember.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the LazyMember.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Wrapper class for lazy members. Does lazy initialization of member on first
 * access.
 * 
 * @param <T>
 *            Type of the lazy member
 * 
 *            If we find ourselves creating a whole bunch of these in our code,
 *            we need to rethink this. Each lazy member holds the actual member,
 *            a lock object, a boolean flag and a delegate. That can turn into a
 *            whole lot of overhead
 */
class LazyMember<T> {

	/** The lazy member. */
	private T lazyMember;

	/** The initialized. */
	private boolean initialized = false;

	/** The lazy implementation. */
	private ILazyMember<T> lazyImplementation;

	/**
	 * Public accessor for the lazy member. Lazy initializes the member on first
	 * access
	 * 
	 * @return the member
	 */
	public T getMember() {
		if (!this.initialized) {
			synchronized (this) {
				if (!this.initialized) {
					this.lazyMember = lazyImplementation.createInstance();
				}
				this.initialized = true;
			}
		}
		return lazyMember;
	}

	/**
	 * Constructor.
	 * 
	 * @param lazyImplementation
	 *            The initialization delegate to call for the item on first
	 *            access
	 */
	public LazyMember(ILazyMember<T> lazyImplementation) {
		this.lazyImplementation = lazyImplementation;
	}
}
