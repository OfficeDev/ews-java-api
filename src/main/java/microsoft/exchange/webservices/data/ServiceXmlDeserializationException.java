package microsoft.exchange.webservices.data;

/**
 * Represents an error that occurs when the XML for a response cannot be
 * deserialized.
 */
public final class ServiceXmlDeserializationException extends
    ServiceLocalException {

  /**
   * Constant serialized ID used for compatibility.
   */
  private static final long serialVersionUID = 1L;

  /**
   * ServiceXmlDeserializationException Constructor.
   */
  public ServiceXmlDeserializationException() {
    super();
  }

  /**
   * ServiceXmlDeserializationException Constructor.
   *
   * @param message the message
   */
  public ServiceXmlDeserializationException(String message) {
    super(message);
  }

  /**
   * ServiceXmlDeserializationException Constructor.
   *
   * @param message        the message
   * @param innerException the inner exception
   */
  public ServiceXmlDeserializationException(String message,
      Exception innerException) {
    super(message, innerException);
  }

}
