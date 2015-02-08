package microsoft.exchange.webservices.data;

/**
 * Represents an exception from an autodiscover error response.
 */
public class AutodiscoverResponseException extends ServiceRemoteException {

  /**
   * Constant serialized ID used for compatibility.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Error code when Autodiscover service operation failed remotely.
   */
  private AutodiscoverErrorCode errorCode;

  /**
   * Initializes a new instance of the class.
   *
   * @param errorCode the error code
   * @param message   the message
   */
  protected AutodiscoverResponseException(AutodiscoverErrorCode errorCode,
      String message) {
    super(message);
    this.errorCode = errorCode;
  }

  /**
   * Gets the ErrorCode for the exception.
   *
   * @return the error code
   */
  public AutodiscoverErrorCode getErrorCode() {
    return this.errorCode;
  }
}
