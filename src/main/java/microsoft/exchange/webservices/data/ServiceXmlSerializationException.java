package microsoft.exchange.webservices.data;

/**
 * Represents an error that occurs when the XML for a request cannot be
 * serialized.
 */
public class ServiceXmlSerializationException extends ServiceLocalException {

  /**
   * Constant serialized ID used for compatibility.
   */
  private static final long serialVersionUID = 1L;

  /**
   * ServiceXmlSerializationException Constructor.
   */
  public ServiceXmlSerializationException() {
    super();
  }

  /**
   * Instantiates a new service xml serialization exception.
   *
   * @param message the message
   */
  public ServiceXmlSerializationException(String message) {
    super(message);

  }

  /**
   * Instantiates a new service xml serialization exception.
   *
   * @param message        the message
   * @param innerException the inner exception
   */
  public ServiceXmlSerializationException(String message,
      Exception innerException) {
    super(message, innerException);
  }

}
