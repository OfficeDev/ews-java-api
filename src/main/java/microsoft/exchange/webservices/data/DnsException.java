package microsoft.exchange.webservices.data;

/**
 * Defines DnsException class.
 */
class DnsException extends Exception {

  /**
   * Constant serialized ID used for compatibility.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new dns exception.
   *
   * @param exceptionMessage the exception message
   */
  protected DnsException(String exceptionMessage) {
    super(exceptionMessage);
  }
}
