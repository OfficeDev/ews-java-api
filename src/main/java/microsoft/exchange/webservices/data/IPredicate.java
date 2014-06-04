package microsoft.exchange.webservices.data;

/**
 * The Interface IPredicate.
 * 
 * @param <T>
 *            The type of the object to compare.
 */
interface IPredicate<T> {

	/**
	 * Represents the method that defines a
	 *  set of criteria and determines whether
     *     the specified object meets those criteria.
	 * 
	 * @param obj The object to compare against 
	 * the criteria defined within the method represented
     *     by this delegate.
	 * @return true if obj meets the criteria 
	 * defined within the method represented by this
     *     delegate; otherwise, false.
	 * @throws ServiceLocalException 
	 */
	boolean predicate(T obj) throws ServiceLocalException;
}
