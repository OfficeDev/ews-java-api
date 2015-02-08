package microsoft.exchange.webservices.data;

/**
 * The Interface ServiceObjectChangedDelegateInterface.
 */
interface IServiceObjectChangedDelegate {

  /**
   * Service object changed.
   *
   * @param serviceObject the service object
   */
  void serviceObjectChanged(ServiceObject serviceObject);

}
