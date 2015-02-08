package microsoft.exchange.webservices.data;

public class NotSupportedException extends Exception {

  /**
   * Constant serialized ID used for compatibility.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new argument exception.
   */
  public NotSupportedException() {
    super();

  }

  /**
   * Instantiates a new NotSupported exception.
   *
   * @param strMessage the str message
   */
  public NotSupportedException(String strMessage) {
    super(strMessage);
  }
}
