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

import microsoft.exchange.webservices.data.attribute.ServiceObjectDefinition;
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.PropertySet;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.service.ServiceObject;
import microsoft.exchange.webservices.data.core.service.item.Item;
import microsoft.exchange.webservices.data.core.service.item.PostItem;
import microsoft.exchange.webservices.data.core.service.schema.EmailMessageSchema;
import microsoft.exchange.webservices.data.core.service.schema.ItemSchema;
import microsoft.exchange.webservices.data.core.service.schema.PostReplySchema;
import microsoft.exchange.webservices.data.core.service.schema.ResponseObjectSchema;
import microsoft.exchange.webservices.data.core.service.schema.ServiceObjectSchema;
import microsoft.exchange.webservices.data.core.enumeration.service.calendar.AffectedTaskOccurrence;
import microsoft.exchange.webservices.data.core.enumeration.service.DeleteMode;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.service.MessageDisposition;
import microsoft.exchange.webservices.data.core.enumeration.service.SendCancellationsMode;
import microsoft.exchange.webservices.data.core.enumeration.property.WellKnownFolderName;
import microsoft.exchange.webservices.data.core.exception.misc.InvalidOperationException;
import microsoft.exchange.webservices.data.property.complex.FolderId;
import microsoft.exchange.webservices.data.property.complex.ItemId;
import microsoft.exchange.webservices.data.property.complex.MessageBody;

import java.util.List;

/**
 * Represents a reply to a post item.
 */
@ServiceObjectDefinition(xmlElementName = XmlElementNames.PostReplyItem, returnedByServer = false)
public final class PostReply extends ServiceObject {

  /**
   * The reference item.
   */
  private Item referenceItem;

  /**
   * Initializes a new instance of the class.
   *
   * @param referenceItem the reference item
   * @throws Exception the exception
   */
  public PostReply(Item referenceItem) throws Exception {
    super(referenceItem.getService());
    referenceItem.throwIfThisIsNew();

    this.referenceItem = referenceItem;
  }

  /**
   * Internal method to return the schema associated with this type of object.
   *
   * @return The schema associated with this type of object.
   */
  @Override
  public ServiceObjectSchema getSchema() {
    return PostReplySchema.Instance;
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
   * Create a PostItem response.
   *
   * @param parentFolderId     the parent folder id
   * @param messageDisposition the message disposition
   * @return Created PostItem.
   * @throws Exception the exception
   */
  protected PostItem internalCreate(FolderId parentFolderId,
      MessageDisposition messageDisposition) throws Exception {
    ((ItemId) this
        .getObjectFromPropertyDefinition(
            ResponseObjectSchema.ReferenceItemId))
        .assign(this.referenceItem.getId());

    List<Item> items = this.getService().internalCreateResponseObject(this,
        parentFolderId, messageDisposition);

    PostItem postItem = EwsUtilities.findFirstItemOfType(PostItem.class,
        items);

    // This should never happen. If it does, we have a bug.
    EwsUtilities
        .ewsAssert(postItem != null, "PostReply.InternalCreate",
                   "postItem is null. The CreateItem call did" + " not return the expected PostItem.");

    return postItem;
  }

  /**
   * Loads the specified set of property on the object.
   *
   * @param propertySet the property set
   * @throws InvalidOperationException the invalid operation exception
   */
  @Override
  protected void internalLoad(PropertySet propertySet)
      throws InvalidOperationException {
    throw new InvalidOperationException("Loading this type of object is not supported.");
  }

  /**
   * Deletes the object.
   *
   * @param deleteMode              the delete mode
   * @param sendCancellationsMode   the send cancellations mode
   * @param affectedTaskOccurrences the affected task occurrences
   * @throws InvalidOperationException the invalid operation exception
   */
  @Override
  protected void internalDelete(DeleteMode deleteMode,
      SendCancellationsMode sendCancellationsMode,
      AffectedTaskOccurrence affectedTaskOccurrences)
      throws InvalidOperationException {
    throw new InvalidOperationException("Deleting this type of object isn't authorized.");
  }

  /**
   * Saves the post reply in the same folder as the original post item.
   * Calling this method results in a call to EWS.
   *
   * @return A PostItem representing the posted reply
   * @throws Exception the exception
   */
  public PostItem save() throws Exception {
    return this.internalCreate(null, null);
  }

  /**
   * Saves the post reply in the same folder as the original post item.
   * Calling this method results in a call to EWS.
   *
   * @param destinationFolderId the destination folder id
   * @return A PostItem representing the posted reply
   * @throws Exception the exception
   */
  public PostItem save(FolderId destinationFolderId) throws Exception {
    EwsUtilities.validateParam(destinationFolderId, "destinationFolderId");
    return this.internalCreate(destinationFolderId, null);
  }

  /**
   * Saves the post reply in a specified folder. Calling this method results
   * in a call to EWS.
   *
   * @param destinationFolderName the destination folder name
   * @return A PostItem representing the posted reply.
   * @throws Exception the exception
   */
  public PostItem save(WellKnownFolderName destinationFolderName)
      throws Exception {
    return this.internalCreate(new FolderId(destinationFolderName), null);
  }

  /**
   * Gets the subject of the post reply.
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
   * Gets the body of the post reply.
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
   * Gets the body prefix that should be prepended to the original
   * post item's body.
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
