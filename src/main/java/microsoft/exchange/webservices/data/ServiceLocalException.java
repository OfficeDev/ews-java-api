package microsoft.exchange.webservices.data;

/**
 * Represents an error that occurs when a service operation fails locally (e.g.
 * validation error).
 */
public class ServiceLocalException extends Exception {

  /**
   * Constant serialized ID used for compatibility.
   */
  private static final long serialVersionUID = 1L;

  /**
   * ServiceLocalException Constructor.
   */
  public ServiceLocalException() {
    super();
  }

  /**
   * ServiceLocalException Constructor.
   *
   * @param message the message
   */
  public ServiceLocalException(String message) {
    super(message);
  }

  /**
   * ServiceLocalException Constructor.
   *
   * @param message        the message
   * @param innerException the inner exception
   */
  public ServiceLocalException(String message, Exception innerException) {
    super(message, innerException);
  }

}
