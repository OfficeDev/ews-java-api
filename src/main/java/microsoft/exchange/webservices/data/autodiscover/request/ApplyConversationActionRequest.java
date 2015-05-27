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

package microsoft.exchange.webservices.data.autodiscover.request;

import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.request.MultiResponseServiceRequest;
import microsoft.exchange.webservices.data.core.response.ServiceResponse;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.service.error.ServiceErrorHandling;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.misc.ConversationAction;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a request to a Apply Conversation Action operation
 */
public final class ApplyConversationActionRequest extends MultiResponseServiceRequest<ServiceResponse> {

  private List<ConversationAction> conversationActions =
      new ArrayList<ConversationAction>();

  public List<ConversationAction> getConversationActions() {
    return this.conversationActions;
  }

  /**
   * Initializes a new instance of the ApplyConversationActionRequest class
   *
   * @param service           The service
   * @param errorHandlingMode Indicates how errors should be handled
   * @throws Exception on error
   */
  public ApplyConversationActionRequest(ExchangeService service, ServiceErrorHandling errorHandlingMode) throws Exception {
    super(service, errorHandlingMode);
  }

  /**
   * Creates the service response.
   *
   * @param service       The service.
   * @param responseIndex Index of the response.
   * @return Service response.
   */
  @Override
  protected ServiceResponse createServiceResponse(ExchangeService service,
      int responseIndex) {
    return new ServiceResponse();
  }

  /**
   * Gets the expected response message count.
   *
   * @return Number of expected response messages.
   */
  @Override
  protected int getExpectedResponseMessageCount() {
    return this.conversationActions.size();
  }

  /**
   * Validate request.
   *
   * @throws Exception on validation error
   */
  @Override
  protected void validate() throws Exception {
    super.validate();
    EwsUtilities.validateParamCollection(
      conversationActions.iterator(), "conversationActions"
    );

    for (int iAction = 0; iAction < this.getConversationActions().size(); iAction++) {
      this.getConversationActions().get(iAction).validate();
    }
  }


  /**
   * Writes XML elements.
   *
   * @param writer The writer.
   * @throws Exception on validation error
   */
  @Override
  protected void writeElementsToXml(EwsServiceXmlWriter writer) throws Exception {
    writer.writeStartElement(
        XmlNamespace.Messages,
        XmlElementNames.ConversationActions);
    for (int iAction = 0; iAction < this.getConversationActions().size(); iAction++) {
      this.getConversationActions().get(iAction).
          writeElementsToXml(writer);
    }
    writer.writeEndElement();
  }

  /**
   * Gets the name of the XML element.
   *
   * @return XML element name.
   */
  @Override public String getXmlElementName() {
    return XmlElementNames.ApplyConversationAction;
  }

  /**
   * Gets the name of the response XML element.
   *
   * @return XML element name.
   */
  @Override
  protected String getResponseXmlElementName() {
    return XmlElementNames.ApplyConversationActionResponse;
  }

  /**
   * Gets the name of the response message XML element.
   *
   * @return XML element name.
   */
  @Override
  protected String getResponseMessageXmlElementName() {
    return XmlElementNames.ApplyConversationActionResponseMessage;
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
}

