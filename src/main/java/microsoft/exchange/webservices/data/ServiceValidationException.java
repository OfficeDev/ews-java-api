package microsoft.exchange.webservices.data;

/**
 * Represents an error that occurs when a validation check fails.
 */
public final class ServiceValidationException extends ServiceLocalException {

  /**
   * Constant serialized ID used for compatibility.
   */
  private static final long serialVersionUID = 1L;

  /**
   * ServiceValidationException Constructor.
   */
  public ServiceValidationException() {
    super();
  }

  /**
   * ServiceValidationException Constructor.
   *
   * @param message the message
   */
  public ServiceValidationException(String message) {
    super(message);
  }

  /**
   * Instantiates a new service validation exception.
   *
   * @param message        the message
   * @param innerException the inner exception
   */
  public ServiceValidationException(String message,
      Exception innerException) {
    super(message, innerException);

  }

}
