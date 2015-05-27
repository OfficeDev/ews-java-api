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

import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.PropertySet;
import microsoft.exchange.webservices.data.core.XmlAttributeNames;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.service.error.ServiceErrorHandling;
import microsoft.exchange.webservices.data.core.response.GetAttachmentResponse;
import microsoft.exchange.webservices.data.core.enumeration.property.BodyType;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlSerializationException;
import microsoft.exchange.webservices.data.property.complex.Attachment;
import microsoft.exchange.webservices.data.property.definition.PropertyDefinitionBase;

import javax.xml.stream.XMLStreamException;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a GetAttachment request.
 */
public final class GetAttachmentRequest extends
    MultiResponseServiceRequest<GetAttachmentResponse> {

  /**
   * The attachments.
   */
  private List<Attachment> attachments = new ArrayList<Attachment>();

  /**
   * The additional property.
   */
  private List<PropertyDefinitionBase> additionalProperties =
      new ArrayList<PropertyDefinitionBase>();

  /**
   * The body type.
   */
  private BodyType bodyType;

  /**
   * Initializes a new instance of the GetAttachmentRequest class.
   *
   * @param service           The service.
   * @param errorHandlingMode Indicates how errors should be handled.
   * @throws Exception
   */
  public GetAttachmentRequest(ExchangeService service, ServiceErrorHandling errorHandlingMode)
      throws Exception {
    super(service, errorHandlingMode);
  }

  /**
   * Validate request.
   *
   * @throws Exception the exception
   */
  @Override
  protected void validate() throws Exception {
    super.validate();
    EwsUtilities.validateParamCollection(this.getAttachments().iterator(), "Attachments");
    for (int i = 0; i < this.getAdditionalProperties().size(); i++) {
      EwsUtilities.validateParam(this.getAdditionalProperties().get(i),
          String.format("AdditionalProperties[%d]", i));
    }
  }

  /**
   * Creates the service response.
   *
   * @param service       The service.
   * @param responseIndex Index of the response.
   * @return Service response.
   */
  @Override
  protected GetAttachmentResponse createServiceResponse(
      ExchangeService service, int responseIndex) {
    return new GetAttachmentResponse(this.getAttachments().get(
        responseIndex));
  }

  /**
   * Gets the expected response message count.
   *
   * @return Number of expected response messages.
   */
  @Override
  protected int getExpectedResponseMessageCount() {
    return this.getAttachments().size();
  }

  /**
   * Gets the name of the XML element.
   *
   * @return XML element name
   */
  @Override public String getXmlElementName() {
    return XmlElementNames.GetAttachment;
  }

  /**
   * Gets the name of the response XML element.
   *
   * @return XML element name
   */
  @Override
  protected String getResponseXmlElementName() {
    return XmlElementNames.GetAttachmentResponse;
  }

  /**
   * Gets the name of the response message XML element.
   *
   * @return XML element name
   */
  @Override
  protected String getResponseMessageXmlElementName() {
    return XmlElementNames.GetAttachmentResponseMessage;
  }

  /**
   * Writes XML elements.
   *
   * @param writer the writer
   * @throws XMLStreamException the XML stream exception
   * @throws ServiceXmlSerializationException the service xml serialization exception
   */
  @Override
  protected void writeElementsToXml(EwsServiceXmlWriter writer)
      throws XMLStreamException, ServiceXmlSerializationException {
    if ((this.getBodyType() != null)
        || this.getAdditionalProperties().size() > 0) {
      writer.writeStartElement(XmlNamespace.Messages,
          XmlElementNames.AttachmentShape);

      if (this.getBodyType() != null) {
        writer.writeElementValue(XmlNamespace.Types,
            XmlElementNames.BodyType, this.getBodyType());
      }

      if (this.getAdditionalProperties().size() > 0) {
        PropertySet.writeAdditionalPropertiesToXml(writer, this.getAdditionalProperties().iterator());
      }

      writer.writeEndElement(); // AttachmentShape
    }

    writer.writeStartElement(XmlNamespace.Messages,
        XmlElementNames.AttachmentIds);

    for (Attachment attachment : this.getAttachments()) {
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

  /**
   * Gets the additional property.
   *
   * @return the additional property
   */
  public List<PropertyDefinitionBase> getAdditionalProperties() {
    return this.additionalProperties;
  }

  /**
   * Gets  the type of the body.
   *
   * @return the body type
   */
  public BodyType getBodyType() {

    return this.bodyType;

  }

  /**
   * Sets the body type.
   *
   * @param bodyType the new body type
   */
  public void setBodyType(BodyType bodyType) {
    this.bodyType = bodyType;
  }

}
