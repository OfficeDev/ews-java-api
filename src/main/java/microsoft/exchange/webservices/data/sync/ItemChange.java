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

package microsoft.exchange.webservices.data.sync;

import microsoft.exchange.webservices.data.core.service.item.Item;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceLocalException;
import microsoft.exchange.webservices.data.property.complex.ItemId;
import microsoft.exchange.webservices.data.property.complex.ServiceId;

/**
 * Represents a change on an item as returned by a synchronization operation.
 */
public final class ItemChange extends Change {

  /**
   * The is read.
   */
  private boolean isRead;

  /**
   * Initializes a new instance of ItemChange.
   */
  public ItemChange() {
    super();
  }

  /**
   * Creates an ItemId instance.
   *
   * @return A ItemId.
   */
  @Override public ServiceId createId() {
    return new ItemId();
  }

  /**
   * Gets the item the change applies to. Item is null when ChangeType is
   * equal to either ChangeType.Delete or ChangeType.ReadFlagChange. In those
   * cases, use the ItemId property to retrieve the Id of the item that was
   * deleted or whose IsRead property changed.
   *
   * @return the item
   */
  public Item getItem() {
    return (Item) this.getServiceObject();
  }

  /**
   * Gets the IsRead property for the item that the change applies to.
   * IsRead is only valid when ChangeType is equal to
   * ChangeType.ReadFlagChange.
   *
   * @return the checks if is read
   */
  public boolean getIsRead() {
    return this.isRead;
  }

  /**
   * Sets the checks if is read.
   *
   * @param isRead the new checks if is read
   */
  public void setIsRead(boolean isRead) {
    this.isRead = isRead;
  }

  /**
   * Gets the Id of the item the change applies to.
   *
   * @return the item id
   * @throws ServiceLocalException the service local exception
   */
  public ItemId getItemId() throws ServiceLocalException {
    return (ItemId) this.getId();
  }

}
