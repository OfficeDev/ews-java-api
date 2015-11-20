/*
 * The MIT License
 * Copyright (c) 2012 Microsoft Corporation
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package microsoft.exchange.webservices.data.core.request;

import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.response.UpdateInboxRulesResponse;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.service.ServiceResult;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.exception.service.remote.UpdateInboxRulesException;
import microsoft.exchange.webservices.data.property.complex.RuleOperation;

/**
 * Represents a UpdateInboxRulesRequest request.
 */
public final class UpdateInboxRulesRequest extends SimpleServiceRequestBase<UpdateInboxRulesResponse> {
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
  public UpdateInboxRulesRequest(ExchangeService service)
      throws Exception {
    super(service);
  }

  /**
   * Gets the name of the XML element.
   *
   * @return XML element name.
   */
  @Override public String getXmlElementName() {
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
   * {@inheritDoc}
   */
  @Override
  protected UpdateInboxRulesResponse parseResponse(EwsServiceXmlReader reader)
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
   * @throws Exception on error
   */
  public UpdateInboxRulesResponse execute() throws Exception {
    UpdateInboxRulesResponse serviceResponse = internalExecute();
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
  public void setMailboxSmtpAddress(String value) {
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
  public void setRemoveOutlookRuleBlob(boolean value) {
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
  public void setInboxRuleOperations(Iterable<RuleOperation> value) {
    this.inboxRuleOperations = value;
  }

}
