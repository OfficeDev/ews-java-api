package microsoft.exchange.webservices.data;

/**
 * The Interface IAction.
 * 
 * @param <T>
 *            The type of the parameter of the 
 *            method that this delegate encapsulates.
 */
public interface IAction<T> {

	/**
	 * Encapsulates a method that takes a single parameter and does not return a
     *     value.
	 * 
	 * @param obj The parameter of the method that this delegate encapsulates.
	 */
	void action(T obj);
}
