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

package microsoft.exchange.webservices.data.core.service.response;

import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.service.item.EmailMessage;
import microsoft.exchange.webservices.data.core.service.item.Item;
import microsoft.exchange.webservices.data.core.service.schema.EmailMessageSchema;
import microsoft.exchange.webservices.data.core.service.schema.ItemSchema;
import microsoft.exchange.webservices.data.core.service.schema.ResponseMessageSchema;
import microsoft.exchange.webservices.data.core.service.schema.ResponseObjectSchema;
import microsoft.exchange.webservices.data.core.service.schema.ServiceObjectSchema;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.service.ResponseMessageType;
import microsoft.exchange.webservices.data.property.complex.EmailAddressCollection;
import microsoft.exchange.webservices.data.property.complex.MessageBody;

/**
 * The Class ResponseMessage.
 */
public final class ResponseMessage extends ResponseObject<EmailMessage> {

  /**
   * Represents the base class for e-mail related response (Reply, Reply all
   * and Forward).
   */
  private ResponseMessageType responseType;

  /**
   * Initializes a new instance of the class.
   *
   * @param referenceItem the reference item
   * @param responseType  the response type
   * @throws Exception the exception
   */
  public ResponseMessage(Item referenceItem, ResponseMessageType responseType)
      throws Exception {
    super(referenceItem);
    this.responseType = responseType;
  }

  /**
   * Internal method to return the schema associated with this type of object.
   *
   * @return The schema associated with this type of object.
   */
  @Override public ServiceObjectSchema getSchema() {
    return ResponseMessageSchema.Instance;
  }

  /**
   * Gets the minimum required server version.
   *
   * @return Earliest Exchange version in which this service object type is
   * supported.
   */
  @Override public ExchangeVersion getMinimumRequiredServerVersion() {
    return ExchangeVersion.Exchange2007_SP1;
  }

  /**
   * This methods lets subclasses of ServiceObject override the default
   * mechanism by which the XML element name associated with their type is
   * retrieved.
   *
   * @return The XML element name associated with this type. If this method
   * returns null or empty, the XML element name associated with this
   * type is determined by the EwsObjectDefinition attribute that
   * decorates the type,if present.
   */
  protected String getXmlElementNameOverride() {

    if (this.responseType == ResponseMessageType.Reply) {
      return XmlElementNames.ReplyToItem;
    } else if (this.responseType == ResponseMessageType.ReplyAll) {
      return XmlElementNames.ReplyAllToItem;
    } else if (this.responseType == ResponseMessageType.Forward) {
      return XmlElementNames.ForwardItem;
    } else {
      EwsUtilities.ewsAssert(false, "ResponseMessage.GetXmlElementNameOverride",
                             "An unexpected value for responseType could not be handled.");
      return null; // Because the compiler wants it
    }

  }

  /**
   * Gets a value indicating the type of response this object represents.
   *
   * @return the response type
   */
  public ResponseMessageType getResponseType() {
    return this.responseType;
  }

  /**
   * Gets  the body of the response.
   *
   * @return the body
   * @throws Exception the exception
   */
  public MessageBody getBody() throws Exception {
    return (MessageBody) this
        .getObjectFromPropertyDefinition(ItemSchema.Body);
  }

  /**
   * Sets the body.
   *
   * @param value the new body
   * @throws Exception the exception
   */
  public void setBody(MessageBody value) throws Exception {
    this.getPropertyBag().setObjectFromPropertyDefinition(ItemSchema.Body,
        value);
  }

  /**
   * Gets a list of recipients the response will be sent to.
   *
   * @return the to recipients
   * @throws Exception the exception
   */
  public EmailAddressCollection getToRecipients() throws Exception {
    return (EmailAddressCollection) this
        .getObjectFromPropertyDefinition(
            EmailMessageSchema.ToRecipients);
  }

  /**
   * Gets a list of recipients the response will be sent to as Cc.
   *
   * @return the cc recipients
   * @throws Exception the exception
   */
  public EmailAddressCollection getCcRecipients() throws Exception {
    return (EmailAddressCollection) this
        .getObjectFromPropertyDefinition(
            EmailMessageSchema.CcRecipients);
  }

  /**
   * Gets a list of recipients the response will be sent to as Cc.
   *
   * @return the bcc recipients
   * @throws Exception the exception
   */
  public EmailAddressCollection getBccRecipients() throws Exception {
    return (EmailAddressCollection) this
        .getObjectFromPropertyDefinition(
            EmailMessageSchema.BccRecipients);
  }

  /**
   * Gets  the subject of this response.
   *
   * @return the subject
   * @throws Exception the exception
   */
  public String getSubject() throws Exception {
    return (String) this
        .getObjectFromPropertyDefinition(EmailMessageSchema.Subject);
  }

  /**
   * Sets the subject.
   *
   * @param value the new subject
   * @throws Exception the exception
   */
  public void setSubject(String value) throws Exception {
    this.getPropertyBag().setObjectFromPropertyDefinition(
        EmailMessageSchema.Subject, value);
  }

  /**
   * Gets the body prefix of this response. The body prefix will be
   * prepended to the original message's body when the response is created.
   *
   * @return the body prefix
   * @throws Exception the exception
   */
  public MessageBody getBodyPrefix() throws Exception {
    return (MessageBody) this
        .getObjectFromPropertyDefinition(
            ResponseObjectSchema.BodyPrefix);
  }

  /**
   * Sets the body prefix.
   *
   * @param value the new body prefix
   * @throws Exception the exception
   */
  public void setBodyPrefix(MessageBody value) throws Exception {
    this.getPropertyBag().setObjectFromPropertyDefinition(
        ResponseObjectSchema.BodyPrefix, value);
  }

}
