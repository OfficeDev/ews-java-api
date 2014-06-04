package microsoft.exchange.webservices.data;

public class XmlException extends Exception {

	/**
	 * Instantiates a new argument exception.
	 */
	public XmlException() {
		super();
		
	}

	/**
	 * Instantiates a new argument exception.
	 * 
	 * @param arg0
	 *            the arg0
	 */
	public XmlException(final String arg0) {
		super(arg0);
		
	}

	/**
	 * ServiceXmlDeserializationException Constructor.
	 * 
	 * @param message
	 *            the message
	 * @param innerException
	 *            the inner exception
	 */
	public XmlException(String message, Exception innerException) {
		super(message, innerException);
	}
}
