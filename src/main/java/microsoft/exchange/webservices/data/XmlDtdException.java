package microsoft.exchange.webservices.data;

/**
 * Exception class for banned xml parsing
 */
class XmlDtdException extends XmlException {

  /**
   * Constant serialized ID used for compatibility.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Gets the xml exception message.
   */

  @Override
  public String getMessage() {
    return "For security reasons DTD is prohibited in this XML document.";
  }
}
