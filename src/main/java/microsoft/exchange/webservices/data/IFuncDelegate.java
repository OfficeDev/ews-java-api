package microsoft.exchange.webservices.data;

/**
 * The Interface FuncDelegate.
 *
 * @param <TResult> the generic type
 */
interface IFuncDelegate<TResult> {

  /**
   * Func.
   *
   * @return the t result
   * @throws FormatException the format exception
   */
  TResult func() throws FormatException;
}
