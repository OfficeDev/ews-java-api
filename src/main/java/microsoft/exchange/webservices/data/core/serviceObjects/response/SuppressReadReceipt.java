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

package microsoft.exchange.webservices.data.core.serviceObjects.response;

import microsoft.exchange.webservices.data.core.serviceObjects.items.Item;
import microsoft.exchange.webservices.data.attributes.ServiceObjectDefinition;
import microsoft.exchange.webservices.data.core.PropertySet;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.serviceObjects.ServiceObject;
import microsoft.exchange.webservices.data.core.serviceObjects.schemas.ResponseObjectSchema;
import microsoft.exchange.webservices.data.core.serviceObjects.schemas.ServiceObjectSchema;
import microsoft.exchange.webservices.data.enumerations.AffectedTaskOccurrence;
import microsoft.exchange.webservices.data.enumerations.DeleteMode;
import microsoft.exchange.webservices.data.enumerations.ExchangeVersion;
import microsoft.exchange.webservices.data.enumerations.MessageDisposition;
import microsoft.exchange.webservices.data.enumerations.SendCancellationsMode;
import microsoft.exchange.webservices.data.properties.complex.FolderId;
import microsoft.exchange.webservices.data.properties.complex.ItemId;

/**
 * Represents a response object created to supress read receipts for an item.
 */
@ServiceObjectDefinition(xmlElementName = XmlElementNames.SuppressReadReceipt, returnedByServer = false)
public final class SuppressReadReceipt extends ServiceObject {

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
  public SuppressReadReceipt(Item referenceItem) throws Exception {
    super(referenceItem.getService());

    referenceItem.throwIfThisIsNew();
    this.referenceItem = referenceItem;
  }

  /**
   * Internal method to return the schema associated with this type of object.
   *
   * @return The schema associated with this type of object.
   */
  @Override public ServiceObjectSchema getSchema() {
    return ResponseObjectSchema.Instance;
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
   * @param parentFolderId     the parent folder id
   * @param messageDisposition the message disposition
   * @throws Exception the exception
   */
  public void internalCreate(FolderId parentFolderId, MessageDisposition messageDisposition) throws Exception {
    ((ItemId) this.getPropertyBag().getObjectFromPropertyDefinition(
        ResponseObjectSchema.ReferenceItemId))
        .assign(this.referenceItem.getId());
    this.getService().internalCreateResponseObject(this, parentFolderId,
        messageDisposition);
  }
}
