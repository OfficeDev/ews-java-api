package microsoft.exchange.webservices.data;

/**
 * Represents an error that occurs when a date and time cannot be converted from
 * one time zone to another.
 */
public class TimeZoneConversionException extends ServiceLocalException {

  /**
   * Constant serialized ID used for compatibility.
   */
  private static final long serialVersionUID = 1L;

  /**
   * ServiceLocalException Constructor.
   */
  public TimeZoneConversionException() {
    super();
  }

  /**
   * ServiceLocalException Constructor.
   *
   * @param message the message
   */
  public TimeZoneConversionException(String message) {
    super(message);
  }

  /**
   * ServiceLocalException Constructor.
   *
   * @param message        the message
   * @param innerException the inner exception
   */
  public TimeZoneConversionException(String message,
      Exception innerException) {
    super(message, innerException);
  }

}
