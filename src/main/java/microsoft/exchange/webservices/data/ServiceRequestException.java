package microsoft.exchange.webservices.data;

/**
 * The Class ServiceRequestException.
 */
public class ServiceRequestException extends ServiceRemoteException {

  /**
   * Constant serialized ID used for compatibility.
   */
  private static final long serialVersionUID = 1L;

  /**
   * ServiceRequestException Constructor.
   */
  public ServiceRequestException() {
    super();
  }

  /**
   * ServiceRequestException Constructor.
   *
   * @param message the message
   */
  public ServiceRequestException(String message) {
    super(message);
  }

  /**
   * ServiceRequestException Constructor.
   *
   * @param message        the message
   * @param innerException the inner exception
   */
  public ServiceRequestException(String message, Exception innerException) {
    super(message, innerException);
  }
}
