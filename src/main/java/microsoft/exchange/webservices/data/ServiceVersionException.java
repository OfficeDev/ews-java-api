package microsoft.exchange.webservices.data;

/**
 * Represents an error that occurs when a request cannot be handled due to a
 * service version mismatch.
 */
public final class ServiceVersionException extends ServiceLocalException {

  /**
   * Constant serialized ID used for compatibility.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Initializes a new instance of the class.
   */
  public ServiceVersionException() {
    super();
  }

  /**
   * Initializes a new instance of the class.
   *
   * @param message the message
   */
  public ServiceVersionException(String message) {
    super(message);
  }

  /**
   * Instantiates a new service version exception.
   *
   * @param message        the message
   * @param innerException the inner exception
   */
  public ServiceVersionException(String message, Exception innerException) {
    super(message, innerException);
  }

}
