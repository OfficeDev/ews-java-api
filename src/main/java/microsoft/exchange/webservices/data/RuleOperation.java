package microsoft.exchange.webservices.data;

/**
 * Represents an operation to be performed on a rule.
 */
public abstract class RuleOperation extends ComplexProperty {
  protected String xmlElementName;

  /**
   * Initializes a new instance of the <see cref="RuleOperation"/> class.
   */
  protected RuleOperation() {
    super();
  }

  /**
   * Gets the XML element name of the rule operation.
   */
  protected String getXmlElementName() {
    return this.xmlElementName;
  }
}
