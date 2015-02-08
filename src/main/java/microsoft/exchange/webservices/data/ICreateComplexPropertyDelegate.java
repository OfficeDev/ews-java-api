package microsoft.exchange.webservices.data;

/**
 * Used to create instances of ComplexProperty.
 *
 * @param <TComplexProperty> Type that extends ComplexProperty
 */
interface ICreateComplexPropertyDelegate
    <TComplexProperty extends ComplexProperty> {

  /**
   * used to create instances of ComplexProperty.
   *
   * @return Complex property instance
   */
  TComplexProperty createComplexProperty();
}
