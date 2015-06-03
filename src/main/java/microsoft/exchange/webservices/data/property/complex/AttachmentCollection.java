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

package microsoft.exchange.webservices.data.property.complex;

import microsoft.exchange.webservices.data.attribute.EditorBrowsable;
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.response.CreateAttachmentResponse;
import microsoft.exchange.webservices.data.core.response.DeleteAttachmentResponse;
import microsoft.exchange.webservices.data.core.response.ServiceResponseCollection;
import microsoft.exchange.webservices.data.core.service.ServiceObject;
import microsoft.exchange.webservices.data.core.service.item.Item;
import microsoft.exchange.webservices.data.core.enumeration.attribute.EditorBrowsableState;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.service.ServiceResult;
import microsoft.exchange.webservices.data.core.exception.service.remote.CreateAttachmentException;
import microsoft.exchange.webservices.data.core.exception.service.remote.DeleteAttachmentException;
import microsoft.exchange.webservices.data.core.exception.misc.InvalidOperationException;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceLocalException;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceValidationException;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;

/**
 * Represents an item's attachment collection.
 */
@EditorBrowsable(state = EditorBrowsableState.Never)
public final class AttachmentCollection extends ComplexPropertyCollection<Attachment>
    implements IOwnedProperty {

  // The item owner that owns this attachment collection
  /**
   * The owner.
   */
  private Item owner;

  /**
   * Initializes a new instance of AttachmentCollection.
   */
  public AttachmentCollection() {
    super();
  }

  /**
   * The owner of this attachment collection.
   *
   * @return the owner
   */
  public ServiceObject getOwner() {
    return this.owner;
  }

  /**
   * The owner of this attachment collection.
   *
   * @param value accepts ServiceObject
   */
  public void setOwner(ServiceObject value) {
    Item item = (Item) value;
    EwsUtilities.ewsAssert(item != null, "AttachmentCollection.IOwnedProperty.set_Owner",
                           "value is not a descendant of ItemBase");

    this.owner = item;
  }

  /**
   * Adds a file attachment to the collection.
   *
   * @param fileName the file name
   * @return A FileAttachment instance.
   */
  public FileAttachment addFileAttachment(String fileName) {
    return this.addFileAttachment(new File(fileName).getName(), fileName);
  }

  /**
   * Adds a file attachment to the collection.
   *
   * @param name     accepts String display name of the new attachment.
   * @param fileName accepts String name of the file representing the content of
   *                 the attachment.
   * @return A FileAttachment instance.
   */
  public FileAttachment addFileAttachment(String name, String fileName) {
    FileAttachment fileAttachment = new FileAttachment(this.owner);
    fileAttachment.setName(name);
    fileAttachment.setFileName(fileName);

    this.internalAdd(fileAttachment);

    return fileAttachment;
  }

  /**
   * Adds a file attachment to the collection.
   *
   * @param name          accepts String display name of the new attachment.
   * @param contentStream accepts InputStream stream from which to read the content of
   *                      the attachment.
   * @return A FileAttachment instance.
   */
  public FileAttachment addFileAttachment(String name,
      InputStream contentStream) {
    FileAttachment fileAttachment = new FileAttachment(this.owner);
    fileAttachment.setName(name);
    fileAttachment.setContentStream(contentStream);

    this.internalAdd(fileAttachment);

    return fileAttachment;
  }

  /**
   * Adds a file attachment to the collection.
   *
   * @param name    the name
   * @param content accepts byte byte arrays representing the content of the
   *                attachment.
   * @return FileAttachment
   */
  public FileAttachment addFileAttachment(String name, byte[] content) {
    FileAttachment fileAttachment = new FileAttachment(this.owner);
    fileAttachment.setName(name);
    fileAttachment.setContent(content);

    this.internalAdd(fileAttachment);

    return fileAttachment;
  }

  /**
   * Adds an item attachment to the collection.
   *
   * @param <TItem> the generic type
   * @param cls     the cls
   * @return An ItemAttachment instance.
   * @throws Exception the exception
   */
  public <TItem extends Item> GenericItemAttachment<TItem> addItemAttachment(
      Class<TItem> cls) throws Exception {
    if (cls.getDeclaredFields().length == 0) {
      throw new InvalidOperationException(String.format(
          "Items of type %s are not supported as attachments.", cls
              .getName()));
    }

    GenericItemAttachment<TItem> itemAttachment =
        new GenericItemAttachment<TItem>(
            this.owner);
    itemAttachment.setTItem((TItem) EwsUtilities.createItemFromItemClass(
        itemAttachment, cls, true));

    this.internalAdd(itemAttachment);

    return itemAttachment;
  }

  /**
   * Removes all attachments from this collection.
   */
  public void clear() {
    this.internalClear();
  }

  /**
   * Removes the attachment at the specified index.
   *
   * @param index Index of the attachment to remove.
   */
  public void removeAt(int index) {
    if (index < 0 || index >= this.getCount()) {
      throw new IllegalArgumentException("parameter \'index\' : " + "index is out of range.");
    }

    this.internalRemoveAt(index);
  }

  /**
   * Removes the specified attachment.
   *
   * @param attachment The attachment to remove.
   * @return True if the attachment was successfully removed from the
   * collection, false otherwise.
   * @throws Exception the exception
   */
  public boolean remove(Attachment attachment) throws Exception {
    EwsUtilities.validateParam(attachment, "attachment");

    return this.internalRemove(attachment);
  }

  /**
   * Instantiate the appropriate attachment type depending on the current XML
   * element name.
   *
   * @param xmlElementName The XML element name from which to determine the type of
   *                       attachment to create.
   * @return An Attachment instance.
   */
  @Override
  protected Attachment createComplexProperty(String xmlElementName) {
    if (xmlElementName.equals(XmlElementNames.FileAttachment)) {
      return new FileAttachment(this.owner);
    } else if (xmlElementName.equals(XmlElementNames.ItemAttachment)) {
      return new ItemAttachment(this.owner);
    } else {
      return null;
    }
  }

  /**
   * Determines the name of the XML element associated with the
   * complexProperty parameter.
   *
   * @param complexProperty The attachment object for which to determine the XML element
   *                        name with.
   * @return The XML element name associated with the complexProperty
   * parameter.
   */
  @Override
  protected String getCollectionItemXmlElementName(Attachment
      complexProperty) {
    if (complexProperty instanceof FileAttachment) {
      return XmlElementNames.FileAttachment;
    } else {
      return XmlElementNames.ItemAttachment;
    }
  }

  /**
   * Saves this collection by creating new attachment and deleting removed
   * ones.
   *
   * @throws Exception the exception
   */
  public void save() throws Exception {
    ArrayList<Attachment> attachments =
        new ArrayList<Attachment>();

    for (Attachment attachment : this.getRemovedItems()) {
      if (!attachment.isNew()) {
        attachments.add(attachment);
      }
    }

    // If any, delete them by calling the DeleteAttachment web method.
    if (attachments.size() > 0) {
      this.internalDeleteAttachments(attachments);
    }

    attachments.clear();

    // Retrieve a list of attachments that have to be created.
    for (Attachment attachment : this) {
      if (attachment.isNew()) {
        attachments.add(attachment);
      }
    }

    // If there are any, create them by calling the CreateAttachment web
    // method.
    if (attachments.size() > 0) {
      if (this.owner.isAttachment()) {
        this.internalCreateAttachments(this.owner.getParentAttachment()
            .getId(), attachments);
      } else {
        this.internalCreateAttachments(
            this.owner.getId().getUniqueId(), attachments);
      }
    }


    // Process all of the item attachments in this collection.
    for (Attachment attachment : this) {
      ItemAttachment itemAttachment = (ItemAttachment)
          ((attachment instanceof
              ItemAttachment) ? attachment :
              null);
      if (itemAttachment != null) {
        // Bug E14:80864: Make sure item was created/loaded before
        // trying to create/delete sub-attachments
        if (itemAttachment.getItem() != null) {
          // Create/delete any sub-attachments
          itemAttachment.getItem().getAttachments().save();

          // Clear the item's change log
          itemAttachment.getItem().clearChangeLog();
        }
      }
    }

    super.clearChangeLog();
  }

  /**
   * Determines whether there are any unsaved attachment collection changes.
   *
   * @return True if attachment adds or deletes haven't been processed yet.
   * @throws ServiceLocalException
   */
  public boolean hasUnprocessedChanges() throws ServiceLocalException {
    // Any new attachments?
    for (Attachment attachment : this) {
      if (attachment.isNew()) {
        return true;
      }
    }

    // Any pending deletions?
    for (Attachment attachment : this.getRemovedItems()) {
      if (!attachment.isNew()) {
        return true;
      }
    }


    Collection<ItemAttachment> itemAttachments =
        new ArrayList<ItemAttachment>();
    for (Object event : this.getItems()) {
      if (event instanceof ItemAttachment) {
        itemAttachments.add((ItemAttachment) event);
      }
    }

    // Recurse: process item attachments to check
    // for new or deleted sub-attachments.
    for (ItemAttachment itemAttachment : itemAttachments) {
      if (itemAttachment.getItem() != null) {
        if (itemAttachment.getItem().getAttachments().hasUnprocessedChanges()) {
          return true;
        }
      }
    }

    return false;
  }

  /**
   * Disables the change log clearing mechanism. Attachment collections are
   * saved separately from the item they belong to.
   */
  @Override public void clearChangeLog() {
    // Do nothing
  }

  /**
   * Validates this instance.
   *
   * @throws Exception the exception
   */
  public void validate() throws Exception {
    // Validate all added attachments
    if (this.owner.isNew()
        && this.owner.getService().getRequestedServerVersion()
        .ordinal() >= ExchangeVersion.Exchange2010_SP2
        .ordinal()) {
      boolean contactPhotoFound = false;
      for (int attachmentIndex = 0; attachmentIndex < this.getAddedItems()
          .size(); attachmentIndex++) {
        final Attachment attachment = this.getAddedItems().get(attachmentIndex);
        if (attachment != null) {
          if (attachment.isNew() && attachment instanceof FileAttachment) {
            // At the server side, only the last attachment with
            // IsContactPhoto is kept, all other IsContactPhoto
            // attachments are removed. CreateAttachment will generate
            // AttachmentId for each of such attachments (although
            // only the last one is valid).
            //
            // With E14 SP2 CreateItemWithAttachment, such request will only
            // return 1 AttachmentId; but the client
            // expects to see all, so let us prevent such "invalid" request
            // in the first place.
            //
            // The IsNew check is to still let CreateAttachmentRequest allow
            // multiple IsContactPhoto attachments.
            //
            if (((FileAttachment) attachment).isContactPhoto()) {
              if (contactPhotoFound) {
                throw new ServiceValidationException("Multiple contact photos in attachment.");
              }
              contactPhotoFound = true;
            }
          }
          attachment.validate(attachmentIndex);
        }
      }
    }
  }


  /**
   * Calls the DeleteAttachment web method to delete a list of attachments.
   *
   * @param attachments the attachments
   * @throws Exception the exception
   */
  private void internalDeleteAttachments(Iterable<Attachment> attachments)
      throws Exception {
    ServiceResponseCollection<DeleteAttachmentResponse> responses =
        this.owner
            .getService().deleteAttachments(attachments);
    Enumeration<DeleteAttachmentResponse> enumerator = responses
        .getEnumerator();
    while (enumerator.hasMoreElements()) {
      DeleteAttachmentResponse response = enumerator.nextElement();
      // We remove all attachments that were successfully deleted from the
      // change log. We should never
      // receive a warning from EWS, so we ignore them.
      if (response.getResult() != ServiceResult.Error) {
        this.removeFromChangeLog(response.getAttachment());
      }
    }

    // TODO : Should we throw for warnings as well?
    if (responses.getOverallResult() == ServiceResult.Error) {
      throw new DeleteAttachmentException(responses, "At least one attachment couldn't be deleted.");
    }
  }

  /**
   * Calls the CreateAttachment web method to create a list of attachments.
   *
   * @param parentItemId the parent item id
   * @param attachments  the attachments
   * @throws Exception the exception
   */
  private void internalCreateAttachments(String parentItemId,
      Iterable<Attachment> attachments) throws Exception {
    ServiceResponseCollection<CreateAttachmentResponse> responses =
        this.owner
            .getService().createAttachments(parentItemId, attachments);

    Enumeration<CreateAttachmentResponse> enumerator = responses
        .getEnumerator();
    while (enumerator.hasMoreElements()) {
      CreateAttachmentResponse response = enumerator.nextElement();
      // We remove all attachments that were successfully created from the
      // change log. We should never
      // receive a warning from EWS, so we ignore them.
      if (response.getResult() != ServiceResult.Error) {
        this.removeFromChangeLog(response.getAttachment());
      }
    }

    // TODO : Should we throw for warnings as well?
    if (responses.getOverallResult() == ServiceResult.Error) {
      throw new CreateAttachmentException(responses, "At least one attachment couldn't be created.");
    }
  }

}
