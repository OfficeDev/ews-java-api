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
import microsoft.exchange.webservices.data.core.enumeration.notification.EventType;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.property.complex.FolderId;

import java.util.Date;

/**
 * Represents an event as exposed by push and pull notification.
 */
public abstract class NotificationEvent {

  /**
   * Type of this event.
   */
  private EventType eventType;

  /**
   * Date and time when the event occurred.
   */
  private Date timestamp;

  /**
   * Id of parent folder of the item or folder this event applies to.
   */
  private FolderId parentFolderId;

  /**
   * Id of the old parent folder of the item or folder this event applies to.
   * This property is only meaningful when EventType is equal to either
   * EventType.Moved or EventType.Copied. For all other event types,
   * oldParentFolderId will be null
   */
  private FolderId oldParentFolderId;

  /**
   * Initializes a new instance.
   *
   * @param eventType the event type
   * @param timestamp the timestamp
   */
  protected NotificationEvent(EventType eventType, Date timestamp) {
    this.eventType = eventType;
    this.timestamp = timestamp;
  }

  /**
   * Load from XML.
   *
   * @param reader the reader
   * @throws Exception the exception
   */
  protected void internalLoadFromXml(EwsServiceXmlReader reader) throws Exception {
  }

  /**
   * Loads this NotificationEvent from XML.
   *
   * @param reader         the reader
   * @param xmlElementName the xml element name
   * @throws Exception the exception
   */
  protected void loadFromXml(EwsServiceXmlReader reader,
      String xmlElementName)
      throws Exception {
    this.internalLoadFromXml(reader);

    reader.readEndElementIfNecessary(XmlNamespace.Types, xmlElementName);
  }

  /**
   * gets the eventType.
   *
   * @return the eventType.
   */
  public EventType getEventType() {
    return eventType;
  }

  /**
   * gets the timestamp.
   *
   * @return the timestamp.
   */
  public Date getTimestamp() {
    return timestamp;
  }

  /**
   * gets the parentFolderId.
   *
   * @return the parentFolderId.
   */
  public FolderId getParentFolderId() {
    return parentFolderId;
  }

  /**
   * Sets the parentFolderId.
   *
   * @param parentFolderId the new parent folder id
   */
  protected void setParentFolderId(FolderId parentFolderId) {
    this.parentFolderId = parentFolderId;
  }

  /**
   * gets the oldParentFolderId.
   *
   * @return the oldParentFolderId.
   */
  public FolderId getOldParentFolderId() {
    return oldParentFolderId;
  }

  /**
   * Sets the oldParentFolderId.
   *
   * @param oldParentFolderId the new old parent folder id
   */
  protected void setOldParentFolderId(FolderId oldParentFolderId) {

    this.oldParentFolderId = oldParentFolderId;
  }

}
