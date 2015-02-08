package microsoft.exchange.webservices.data;

/**
 * Represents an exception that is thrown when the Autodiscover service could
 * not be contacted.
 */
public class AutodiscoverLocalException extends ServiceLocalException {

  /**
   * Constant serialized ID used for compatibility.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Initializes a new instance of the class.
   */
  public AutodiscoverLocalException() {
    super();
  }

  /**
   * Initializes a new instance of the class.
   *
   * @param message the message
   */
  public AutodiscoverLocalException(String message) {
    super(message);
  }

  /**
   * Initializes a new instance of the class.
   *
   * @param message        the message
   * @param innerException the inner exception
   */
  public AutodiscoverLocalException(String message,
      Exception innerException) {
    super(message, innerException);
  }
}
