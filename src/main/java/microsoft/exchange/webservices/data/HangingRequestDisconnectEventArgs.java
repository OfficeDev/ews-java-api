package microsoft.exchange.webservices.data;

/**
 * Represents a collection of arguments for the
 * HangingServiceRequestBase.HangingRequestDisconnectHandler
 * delegate method.
 */
public final class HangingRequestDisconnectEventArgs {

  /**
   * Initializes a new instance of the
   * HangingRequestDisconnectEventArgs class.
   *
   * @param reason    The reason.
   * @param exception The exception.
   */
  protected HangingRequestDisconnectEventArgs(
      HangingRequestDisconnectReason reason,
      Exception exception) {
    this.reason = reason;
    this.exception = exception;
  }

  private HangingRequestDisconnectReason reason;

  /**
   * Gets the reason that the user was disconnected.
   *
   * @return reason The reason.
   */
  public HangingRequestDisconnectReason getReason() {
    return reason;
  }

  /**
   * Sets the reason that the user was disconnected.
   *
   * @param value The reason.
   */
  protected void setReason(HangingRequestDisconnectReason value) {
    reason = value;
  }

  private Exception exception;

  /**
   * Gets the exception that caused the disconnection. Can be null.
   *
   * @return exception The Exception.
   */
  public Exception getException() {
    return exception;
  }

  /**
   * Sets the exception that caused the disconnection. Can be null.
   *
   * @param value The Exception.
   */
  protected void setException(Exception value) {
    exception = value;
  }
}
