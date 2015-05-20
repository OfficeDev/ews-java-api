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
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Represents a collection of notification events.
 */
public final class GetStreamingEventsResults {

  /**
   * Structure to track a subscription and its associated notification events.
   */
  protected static class NotificationGroup {
    /**
     * Subscription Id
     */
    protected String subscriptionId;

    /**
     * Events in the response associated with the subscription id.
     */
    protected Collection<NotificationEvent> events;
  }


  /**
   * Collection of notification events.
   */
  private Collection<NotificationGroup> events =
      new ArrayList<NotificationGroup>();

  /**
   * Initializes a new instance of the <see cref=
   * "GetStreamingEventsResults"/> class.
   */
  public GetStreamingEventsResults() {
  }

  /**
   * Loads from XML.
   *
   * @param reader The reader.
   * @throws Exception
   */
  public void loadFromXml(EwsServiceXmlReader reader) throws Exception {
    reader.readStartElement(XmlNamespace.Messages,
        XmlElementNames.Notification);

    do {
      NotificationGroup notifications = new NotificationGroup();
      notifications.subscriptionId = reader.readElementValue(
          XmlNamespace.Types,
          XmlElementNames.SubscriptionId);
      notifications.events = new ArrayList<NotificationEvent>();

      synchronized (this) {
        this.events.add(notifications);
      }

      do {
        reader.read();

        if (reader.isStartElement()) {
          String eventElementName = reader.getLocalName();
          EventType eventType;
          if (GetEventsResults.getXmlElementNameToEventTypeMap().containsKey(eventElementName)) {
            eventType = GetEventsResults.getXmlElementNameToEventTypeMap().
                get(eventElementName);
            if (eventType == EventType.Status) {
              // We don't need to return status events
              reader.readEndElementIfNecessary(XmlNamespace.Types,
                  eventElementName);
            } else {
              this.loadNotificationEventFromXml(
                  reader,
                  eventElementName,
                  eventType,
                  notifications);
            }
          } else {
            reader.skipCurrentElement();
          }
        }
      }
      while (!reader.isEndElement(XmlNamespace.Messages,
          XmlElementNames.Notification));

      reader.read();
    }
    while (!reader.isEndElement(XmlNamespace.Messages,
        XmlElementNames.Notifications));
  }

  /**
   * Loads a notification event from XML.
   *
   * @param reader           The reader.
   * @param eventElementName Name of the event XML element.
   * @param eventType        Type of the event.
   * @param notifications    Collection of notification
   * @throws Exception
   */
  private void loadNotificationEventFromXml(
      EwsServiceXmlReader reader,
      String eventElementName,
      EventType eventType,
      NotificationGroup notifications) throws Exception {
    Date timestamp = reader.readElementValue(Date.class, XmlNamespace.Types,
        XmlElementNames.TimeStamp);

    NotificationEvent notificationEvent;

    reader.read();

    if (reader.getLocalName().equals(XmlElementNames.FolderId)) {
      notificationEvent = new FolderEvent(eventType, timestamp);
    } else {
      notificationEvent = new ItemEvent(eventType, timestamp);
    }

    notificationEvent.loadFromXml(reader, eventElementName);
    notifications.events.add(notificationEvent);
  }

  /**
   * Gets the notification collection.
   *
   * @value The notification collection.
   */
  protected Collection<NotificationGroup> getNotifications() {
    return this.events;
  }
}

