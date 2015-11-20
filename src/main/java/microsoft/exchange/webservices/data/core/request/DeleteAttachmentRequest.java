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

/**
 * Represents a DeleteAttachment request.
 */
import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.XmlAttributeNames;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.service.error.ServiceErrorHandling;
import microsoft.exchange.webservices.data.core.response.DeleteAttachmentResponse;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceLocalException;
import microsoft.exchange.webservices.data.property.complex.Attachment;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class DeleteAttachmentRequest.
 */
public final class DeleteAttachmentRequest extends
    MultiResponseServiceRequest<DeleteAttachmentResponse> {

  private static final Log LOG = LogFactory.getLog(DeleteAttachmentRequest.class);

  /**
   * The attachments.
   */
  private List<Attachment> attachments = new ArrayList<Attachment>();

  /**
   * Initializes a new instance of the DeleteAttachmentRequest class.
   *
   * @param service           the service
   * @param errorHandlingMode the error handling mode
   * @throws Exception
   */
  public DeleteAttachmentRequest(ExchangeService service, ServiceErrorHandling errorHandlingMode)
      throws Exception {
    super(service, errorHandlingMode);
  }

  /**
   * Validate request.
   */
  @Override
  protected void validate() {
    try {
      super.validate();
      EwsUtilities.validateParamCollection(this.getAttachments().iterator(), "Attachments");
      for (int i = 0; i < this.attachments.size(); i++) {
        EwsUtilities.validateParam(this.attachments.get(i).getId(),
            String.format("Attachment[%d].Id ", i));
      }
    } catch (ServiceLocalException e) {
      LOG.error(e);
    } catch (Exception e) {
      LOG.error(e);
    }
  }

  /**
   * Creates the service response.
   *
   * @param service       the service
   * @param responseIndex the response index
   * @return Service object.
   */
  @Override
  protected DeleteAttachmentResponse createServiceResponse(
      ExchangeService service, int responseIndex) {
    return new DeleteAttachmentResponse(
        this.attachments.get(responseIndex));
  }

  /**
   * Gets the expected response message count.
   *
   * @return Number of expected response messages.
   */
  @Override
  protected int getExpectedResponseMessageCount() {
    return this.attachments.size();
  }

  /**
   * Gets the name of the XML element.
   *
   * @return XML element name.
   */
  @Override public String getXmlElementName() {
    return XmlElementNames.DeleteAttachment;
  }

  /**
   * Gets the name of the response XML element.
   *
   * @return XML element name.
   */
  @Override
  protected String getResponseXmlElementName() {
    return XmlElementNames.DeleteAttachmentResponse;
  }

  /**
   * Gets the name of the response message XML element.
   *
   * @return XML element name.
   */
  @Override
  protected String getResponseMessageXmlElementName() {
    return XmlElementNames.DeleteAttachmentResponseMessage;
  }

  /**
   * Writes XML elements.
   *
   * @param writer the writer
   * @throws Exception the exception
   */
  @Override
  protected void writeElementsToXml(EwsServiceXmlWriter writer)
      throws Exception {
    writer.writeStartElement(XmlNamespace.Messages,
        XmlElementNames.AttachmentIds);

    for (Attachment attachment : this.attachments) {
      writer.writeStartElement(XmlNamespace.Types,
          XmlElementNames.AttachmentId);
      writer
          .writeAttributeValue(XmlAttributeNames.Id, attachment
              .getId());
      writer.writeEndElement();
    }

    writer.writeEndElement();
  }

  /**
   * Gets the request version.
   *
   * @return Earliest Exchange version in which this request is supported.
   */
  @Override
  protected ExchangeVersion getMinimumRequiredServerVersion() {
    return ExchangeVersion.Exchange2007_SP1;
  }

  /**
   * Gets the attachments.
   *
   * @return the attachments
   */
  public List<Attachment> getAttachments() {
    return this.attachments;
  }
}
