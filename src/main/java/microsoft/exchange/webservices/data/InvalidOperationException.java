package microsoft.exchange.webservices.data;

/**
 * The Class InvalidOperationException.
 */
public class InvalidOperationException extends Exception {

  /**
   * Constant serialized ID used for compatibility.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new invalid operation exception.
   */
  public InvalidOperationException() {

  }

  /**
   * Instantiates a new invalid operation exception.
   *
   * @param strMessage the str message
   */
  public InvalidOperationException(String strMessage) {
    super(strMessage);
  }
}
