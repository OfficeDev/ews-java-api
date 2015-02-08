package microsoft.exchange.webservices.data;

/**
 * Represents a UpdateInboxRulesRequest request.
 */
final class UpdateInboxRulesRequest extends SimpleServiceRequestBase {
  /**
   * The smtp address of the mailbox from which to get the inbox rules.
   */
  private String mailboxSmtpAddress;

  /**
   * Remove OutlookRuleBlob or not.
   */
  private boolean removeOutlookRuleBlob;

  /**
   * InboxRule operation collection.
   */
  private Iterable<RuleOperation> inboxRuleOperations;

  /**
   * Initializes a new instance of the
   * <see cref="UpdateInboxRulesRequest"/> class.
   *
   * @param service The service.
   */
  protected UpdateInboxRulesRequest(ExchangeService service)
      throws Exception {
    super(service);
  }

  /**
   * Gets the name of the XML element.
   *
   * @return XML element name.
   */
  @Override
  protected String getXmlElementName() {
    return XmlElementNames.UpdateInboxRules;
  }

  /**
   * Writes XML elements.
   *
   * @param writer The writer.
   */
  @Override
  protected void writeElementsToXml(EwsServiceXmlWriter writer)
      throws Exception {
    if (!(mailboxSmtpAddress == null || mailboxSmtpAddress.isEmpty())) {
      writer.writeElementValue(
          XmlNamespace.Messages,
          XmlElementNames.MailboxSmtpAddress,
          this.mailboxSmtpAddress);
    }

    writer.writeElementValue(
        XmlNamespace.Messages,
        XmlElementNames.RemoveOutlookRuleBlob,
        this.removeOutlookRuleBlob);
    writer.writeStartElement(XmlNamespace.Messages,
        XmlElementNames.Operations);
    for (RuleOperation operation : this.inboxRuleOperations) {
      operation.writeToXml(writer, operation.getXmlElementName());
    }
    writer.writeEndElement();
  }

  /**
   * Gets the name of the response XML element.
   *
   * @return XML element name.
   */
  @Override
  protected String getResponseXmlElementName() {
    return XmlElementNames.UpdateInboxRulesResponse;
  }

  /**
   * Parses the response.
   *
   * @param reader The reader.
   * @return Response object.
   * @throws Exception
   */
  @Override
  protected Object parseResponse(EwsServiceXmlReader reader)
      throws Exception {
    UpdateInboxRulesResponse response = new UpdateInboxRulesResponse();
    response.loadFromXml(reader, XmlElementNames.UpdateInboxRulesResponse);
    return response;
  }

  /**
   * Gets the request version.
   *
   * @return Earliest Exchange version in which this request is supported.
   */
  @Override
  protected ExchangeVersion getMinimumRequiredServerVersion() {
    return ExchangeVersion.Exchange2010_SP1;
  }

  /**
   * Validate request.
   */
  @Override
  protected void validate() throws Exception {
    if (this.inboxRuleOperations == null) {
      throw new IllegalArgumentException(
          "RuleOperations cannot be null." + "Operations");
    }

    int operationCount = 0;
    for (RuleOperation operation : this.inboxRuleOperations) {
      EwsUtilities.validateParam(operation, "RuleOperation");
      operationCount++;
    }

    if (operationCount == 0) {
      throw new IllegalArgumentException(
          "RuleOperations cannot be empty." + "Operations");
    }

    this.getService().validate();
  }

  /**
   * Executes this request.
   *
   * @return Service response.
   * @throws Exception
   * @throws microsoft.exchange.webservices.data.ServiceLocalException
   */
  protected UpdateInboxRulesResponse execute()
      throws ServiceLocalException, Exception {
    UpdateInboxRulesResponse serviceResponse =
        (UpdateInboxRulesResponse) this.internalExecute();
    if (serviceResponse.getResult() == ServiceResult.Error) {
      throw new UpdateInboxRulesException(serviceResponse,
          this.inboxRuleOperations);
    }
    return serviceResponse;
  }

  /**
   * Gets the address of the mailbox in which to update the inbox rules.
   */
  protected String getMailboxSmtpAddress() {
    return this.mailboxSmtpAddress;
  }

  /**
   * Sets the address of the mailbox in which to update the inbox rules.
   */
  protected void setMailboxSmtpAddress(String value) {
    this.mailboxSmtpAddress = value;
  }

  /**
   * Gets a value indicating whether or not to
   * remove OutlookRuleBlob from the rule collection.
   */
  protected boolean getRemoveOutlookRuleBlob() {
    return this.removeOutlookRuleBlob;
  }

  /**
   * Sets a value indicating whether or not to
   * remove OutlookRuleBlob from the rule collection.
   */
  protected void setRemoveOutlookRuleBlob(boolean value) {
    this.removeOutlookRuleBlob = value;
  }


  /**
   * Gets the RuleOperation collection.
   */
  protected Iterable<RuleOperation> getInboxRuleOperations() {
    return this.inboxRuleOperations;
  }

  /**
   * Sets the RuleOperation collection.
   */
  protected void setInboxRuleOperations(Iterable<RuleOperation> value) {
    this.inboxRuleOperations = value;
  }

}
