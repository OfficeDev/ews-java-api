package microsoft.exchange.webservices.data;

/**
 * Represents an error that occurs when a service operation fails remotely.
 */
public class ServiceRemoteException extends Exception {

  /**
   * Constant serialized ID used for compatibility.
   */
  private static final long serialVersionUID = 1L;

  /**
   * ServiceRemoteException Constructor.
   */
  public ServiceRemoteException() {
    super();
  }

  /**
   * ServiceRemoteException Constructor.
   *
   * @param message the message
   */
  public ServiceRemoteException(String message) {
    super(message);
  }

  /**
   * ServiceRemoteException Constructor.
   *
   * @param message        the message
   * @param innerException the inner exception
   */
  public ServiceRemoteException(String message, Exception innerException) {
    super(message, innerException);
  }
}
