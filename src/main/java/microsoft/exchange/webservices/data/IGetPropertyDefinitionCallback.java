package microsoft.exchange.webservices.data;

/**
 * The Interface GetPropertyDefinitionCallbackInterface.
 */
interface IGetPropertyDefinitionCallback {

  /**
   * Gets the property definition callback.
   *
   * @param version the version
   * @return the property definition callback
   */
  PropertyDefinition getPropertyDefinitionCallback(ExchangeVersion version);
}
