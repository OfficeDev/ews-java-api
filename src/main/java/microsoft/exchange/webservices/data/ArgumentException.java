package microsoft.exchange.webservices.data;

/**
 * The Class ArgumentException.
 */
public class ArgumentException extends Exception {

  /**
   * Constant serialized ID used for compatibility.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new argument exception.
   */
  public ArgumentException() {
    super();

  }

  /**
   * Instantiates a new argument exception.
   *
   * @param arg0 the arg0
   */
  public ArgumentException(final String arg0) {
    super(arg0);

  }

  /**
   * ServiceXmlDeserializationException Constructor.
   *
   * @param message        the message
   * @param innerException the inner exception
   */
  public ArgumentException(String message, Exception innerException) {
    super(message, innerException);
  }

  /**
   * Initializes a new instance of the System.
   * ArgumentException class with a specified
   * error message and the name of the
   * parameter that causes this exception.
   *
   * @param message   The error message that explains the reason for the exception.
   * @param paramName The name of the parameter that caused the current exception.
   */
  public ArgumentException(String message, String paramName) {
    super(message + " Parameter that caused " +
        "the current exception :" + paramName);
  }

}
