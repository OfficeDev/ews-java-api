/**************************************************************************
 Exchange Web Services Java API
 Copyright (c) Microsoft Corporation
 All rights reserved.
 MIT License
 Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the ""Software""), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 THE SOFTWARE IS PROVIDED *AS IS*, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 **************************************************************************/

package microsoft.exchange.webservices.data;

/**
 * Represents the base class for all responses that can be sent.
 *
 */
import java.util.List;

/**
 * The Class ResponseObject.
 *
 * @param <TMessage> the generic type
 */
@EditorBrowsable(state = EditorBrowsableState.Never)
public abstract class ResponseObject<TMessage extends EmailMessage> extends
    ServiceObject {

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
  protected ResponseObject(Item referenceItem) throws Exception {
    super(referenceItem.getService());
    EwsUtilities.EwsAssert(referenceItem != null, "ResponseObject.ctor",
        "referenceItem is null");
    referenceItem.throwIfThisIsNew();
    this.referenceItem = referenceItem;
  }

  /**
   * Internal method to return the schema associated with this type of object.
   *
   * @return The schema associated with this type of object.
   */
  @Override
  protected ServiceObjectSchema getSchema() {
    return ResponseObjectSchema.Instance;
  }

  /**
   * Loads the specified set of properties on the object.
   *
   * @param propertySet the property set
   */
  @Override
  protected void internalLoad(PropertySet propertySet) {
    throw new UnsupportedOperationException();
  }

  /**
   * Deletes the object.
   *
   * @param deleteMode              the delete mode
   * @param sendCancellationsMode   the send cancellations mode
   * @param affectedTaskOccurrences the affected task occurrences
   */
  @Override
  protected void internalDelete(DeleteMode deleteMode,
      SendCancellationsMode sendCancellationsMode,
      AffectedTaskOccurrence affectedTaskOccurrences) {
    throw new UnsupportedOperationException();
  }

  /**
   * Create the response object.
   *
   * @param destinationFolderId the destination folder id
   * @param messageDisposition  the message disposition
   * @return The list of items returned by EWS.
   * @throws Exception the exception
   */
  protected List<Item> internalCreate(FolderId destinationFolderId,
      MessageDisposition messageDisposition) throws Exception {
    ((ItemId) this.getPropertyBag().getObjectFromPropertyDefinition(
        ResponseObjectSchema.ReferenceItemId))
        .assign(this.referenceItem.getId());
    return this.getService().internalCreateResponseObject(this,
        destinationFolderId, messageDisposition);
  }

  /**
   * Saves the response in the specified folder. Calling this method results
   * in a call to EWS.
   *
   * @param destinationFolderId the destination folder id
   * @return A TMessage that represents the response.
   * @throws Exception the exception
   */
  public TMessage save(FolderId destinationFolderId) throws Exception {
    EwsUtilities.validateParam(destinationFolderId, "destinationFolderId");
    return (TMessage) this.internalCreate(destinationFolderId,
        MessageDisposition.SaveOnly).get(0);
  }

  /**
   * Saves the response in the specified folder. Calling this method results
   * in a call to EWS.
   *
   * @param destinationFolderName the destination folder name
   * @return A TMessage that represents the response.
   * @throws Exception the exception
   */
  public TMessage save(WellKnownFolderName destinationFolderName)
      throws Exception {
    return (TMessage) this.internalCreate(
        new FolderId(destinationFolderName),
        MessageDisposition.SaveOnly).get(0);
  }

  /**
   * Saves the response in the Drafts folder. Calling this method results in a
   * call to EWS.
   *
   * @return A TMessage that represents the response.
   * @throws Exception the exception
   */
  public TMessage save() throws Exception {
    return (TMessage) this
        .internalCreate(null, MessageDisposition.SaveOnly).get(0);
  }

  /**
   * Sends this response without saving a copy. Calling this method results in
   * a call to EWS.
   *
   * @throws Exception the exception
   */
  public void send() throws Exception {
    this.internalCreate(null, MessageDisposition.SendOnly);
  }

  /**
   * Sends this response and saves a copy in the specified folder. Calling
   * this method results in a call to EWS.
   *
   * @param destinationFolderId the destination folder id
   * @throws Exception the exception
   */
  public void sendAndSaveCopy(FolderId destinationFolderId) throws Exception {
    EwsUtilities.validateParam(destinationFolderId, "destinationFolderId");
    this.internalCreate(destinationFolderId,
        MessageDisposition.SendAndSaveCopy);
  }

  /**
   * Sends this response and saves a copy in the specified folder. Calling
   * this method results in a call to EWS.
   *
   * @param destinationFolderName the destination folder name
   * @throws Exception the exception
   */
  public void sendAndSaveCopy(WellKnownFolderName destinationFolderName)
      throws Exception {
    this.internalCreate(new FolderId(destinationFolderName),
        MessageDisposition.SendAndSaveCopy);
  }

  /**
   * Sends this response and saves a copy in the Sent Items folder. Calling
   * this method results in a call to EWS.
   *
   * @throws Exception the exception
   */
  public void sendAndSaveCopy() throws Exception {
    this.internalCreate(null, MessageDisposition.SendAndSaveCopy);
  }

}
