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

package microsoft.exchange.webservices.data.notification;

import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.notification.EventType;
import microsoft.exchange.webservices.data.property.complex.FolderId;
import microsoft.exchange.webservices.data.property.complex.ItemId;

import java.util.Date;

/**
 * Represents an event that applies to an item.
 */
public final class ItemEvent extends NotificationEvent {

  /**
   * Id of the item this event applies to.
   */
  private ItemId itemId;

  /**
   * Id of the item that moved or copied. This is only meaningful when
   * EventType is equal to either EventType.Moved or EventType.Copied. For all
   * other event types, it's null.
   */
  private ItemId oldItemId;

  /**
   * Initializes a new instance.
   *
   * @param eventType the event type
   * @param timestamp the timestamp
   */
  protected ItemEvent(EventType eventType, Date timestamp) {
    super(eventType, timestamp);
  }

  /**
   * Load from XML.
   *
   * @param reader the reader
   * @throws Exception the exception
   */
  protected void internalLoadFromXml(EwsServiceXmlReader reader)
      throws Exception {
    super.internalLoadFromXml(reader);

    this.itemId = new ItemId();
    this.itemId.loadFromXml(reader, reader.getLocalName());

    reader.read();

    setParentFolderId(new FolderId());

    getParentFolderId().loadFromXml(reader, XmlElementNames.ParentFolderId);

    EventType eventType = getEventType();
    switch (eventType) {
      case Moved:
      case Copied:
        reader.read();

        this.oldItemId = new ItemId();
        this.oldItemId.loadFromXml(reader, reader.getLocalName());

        reader.read();

        setOldParentFolderId(new FolderId());
        getOldParentFolderId().loadFromXml(reader, reader.getLocalName());
        break;

      default:
        break;
    }
  }

  /**
   * Gets the Id of the item this event applies to.
   *
   * @return itemId
   */
  public ItemId getItemId() {
    return itemId;
  }

  /**
   * Gets the Id of the item that was moved or copied. OldItemId is only
   * meaningful when EventType is equal to either EventType.Moved or
   * EventType.Copied. For all other event types, OldItemId is null.
   *
   * @return the old item id
   */
  public ItemId getOldItemId() {
    return oldItemId;
  }

}
