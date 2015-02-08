package microsoft.exchange.webservices.data;

/**
 * The Interface ILazyMember.
 *
 * @param <T> the generic type
 */
interface ILazyMember<T> {

  /**
   * Creates the instance.
   *
   * @return the t
   */
  T createInstance();
}
