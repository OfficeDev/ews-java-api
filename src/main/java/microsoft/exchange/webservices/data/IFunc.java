package microsoft.exchange.webservices.data;

/**
 * The Interface Func.
 *
 * @param <T>       the generic type
 * @param <TResult> the generic type
 */
interface IFunc<T, TResult> {

  /**
   * Func.
   *
   * @param arg the arg
   * @return the t result
   */
  TResult func(T arg);
}
