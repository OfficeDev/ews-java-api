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

import microsoft.exchange.webservices.data.attribute.EditorBrowsable;
import microsoft.exchange.webservices.data.core.service.item.EmailMessage;
import microsoft.exchange.webservices.data.core.service.item.Item;
import microsoft.exchange.webservices.data.core.service.schema.CalendarResponseObjectSchema;
import microsoft.exchange.webservices.data.core.service.schema.EmailMessageSchema;
import microsoft.exchange.webservices.data.core.service.schema.ItemSchema;
import microsoft.exchange.webservices.data.core.service.schema.ServiceObjectSchema;
import microsoft.exchange.webservices.data.core.enumeration.attribute.EditorBrowsableState;
import microsoft.exchange.webservices.data.core.enumeration.property.Sensitivity;
import microsoft.exchange.webservices.data.property.complex.AttachmentCollection;
import microsoft.exchange.webservices.data.property.complex.EmailAddress;
import microsoft.exchange.webservices.data.property.complex.EmailAddressCollection;
import microsoft.exchange.webservices.data.property.complex.InternetMessageHeaderCollection;
import microsoft.exchange.webservices.data.property.complex.MessageBody;

/**
 * Represents the base class for accept, tentatively accept and decline response
 * messages.
 *
 * @param <TMessage> The type of message that is created when this response message is
 *                   saved.
 */
@EditorBrowsable(state = EditorBrowsableState.Never)
public abstract class CalendarResponseMessage<TMessage extends EmailMessage>
    extends CalendarResponseMessageBase<TMessage> {

  /**
   * Initializes a new instance of the CalendarResponseMessage class.
   *
   * @param referenceItem The reference item
   * @throws Exception the exception
   */
  protected CalendarResponseMessage(Item referenceItem) throws Exception {
    super(referenceItem);
  }

  /**
   * Internal method to return the schema associated with this type of object.
   *
   * @return The schema associated with this type of object.
   */
  @Override public ServiceObjectSchema getSchema() {
    return CalendarResponseObjectSchema.Instance;
  }

  /**
   * Gets the body of the response.
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
   * Gets a list of recipients this response will be sent to as Bcc.
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
   * Gets the item class.
   *
   * @return the item class
   * @throws Exception the exception
   */
  protected String getItemClass() throws Exception {
    return (String) this
        .getObjectFromPropertyDefinition(ItemSchema.ItemClass);
  }

  /**
   * Sets the item class.
   *
   * @param value the new item class
   * @throws Exception the exception
   */
  protected void setItemClass(String value) throws Exception {
    this.getPropertyBag().setObjectFromPropertyDefinition(
        ItemSchema.ItemClass, value);
  }

  /**
   * Gets the sensitivity of this response.
   *
   * @return the sensitivity
   * @throws Exception the exception
   */
  public Sensitivity getSensitivity() throws Exception {
    return (Sensitivity) this
        .getObjectFromPropertyDefinition(ItemSchema.Sensitivity);
  }

  /**
   * Sets the sensitivity.
   *
   * @param value the new sensitivity
   * @throws Exception the exception
   */
  public void setSensitivity(Sensitivity value) throws Exception {
    this.getPropertyBag().setObjectFromPropertyDefinition(
        ItemSchema.Sensitivity, value);
  }

  /**
   * Gets a list of attachments to this response.
   *
   * @return the attachments
   * @throws Exception the exception
   */
  public AttachmentCollection getAttachments() throws Exception {
    return (AttachmentCollection) this
        .getObjectFromPropertyDefinition(ItemSchema.Attachments);
  }

  /**
   * Gets the internet message headers.
   *
   * @return the internet message headers
   * @throws Exception the exception
   */
  protected InternetMessageHeaderCollection getInternetMessageHeaders()
      throws Exception {
    return (InternetMessageHeaderCollection) this
        .getObjectFromPropertyDefinition(
            ItemSchema.InternetMessageHeaders);
  }

  /**
   * Gets the sender of this response.
   *
   * @return the sender
   * @throws Exception the exception
   */
  public EmailAddress getSender() throws Exception {
    return (EmailAddress) this
        .getObjectFromPropertyDefinition(EmailMessageSchema.Sender);
  }

  /**
   * Sets the sender.
   *
   * @param value the new sender
   * @throws Exception the exception
   */
  public void setSender(EmailAddress value) throws Exception {
    this.getPropertyBag().setObjectFromPropertyDefinition(
        EmailMessageSchema.Sender, value);
  }
}
