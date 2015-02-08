package microsoft.exchange.webservices.data;

/**
 * The Interface ISelfValidate.
 */
interface ISelfValidate {

  /**
   * Validate.
   *
   * @throws ServiceValidationException the service validation exception
   * @throws Exception                  the exception
   */
  void validate() throws ServiceValidationException, Exception;
}
