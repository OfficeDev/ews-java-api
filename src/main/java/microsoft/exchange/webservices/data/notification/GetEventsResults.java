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
import microsoft.exchange.webservices.data.core.ILazyMember;
import microsoft.exchange.webservices.data.core.LazyMember;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.notification.EventType;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a collection of notification events.
 */
public final class GetEventsResults {
  /**
   * Watermark in event.
   */
  private String newWatermark;

  /**
   * Subscription id.
   */
  private String subscriptionId;

  /**
   * Previous watermark.
   */
  private String previousWatermark;

  /**
   * True if more events available for this subscription.
   */
  private boolean moreEventsAvailable;

  /**
   * Collection of notification events.
   */
  private Collection<NotificationEvent> events =
      new ArrayList<NotificationEvent>();

  /**
   * Map XML element name to notification event type. If you add a new
   * notification event type, you'll need to add a new entry to the Map here.
   */
  private static LazyMember<Map<String, EventType>>
      xmlElementNameToEventTypeMap =
      new LazyMember<Map<String, EventType>>(
          new ILazyMember<Map<String, EventType>>() {
            @Override
            public Map<String, EventType> createInstance() {
              Map<String, EventType> result =
                  new HashMap<String, EventType>();
              result.put(XmlElementNames.CopiedEvent, EventType.Copied);
              result.put(XmlElementNames.CreatedEvent, EventType.Created);
              result.put(XmlElementNames.DeletedEvent, EventType.Deleted);
              result.put(XmlElementNames.ModifiedEvent,
                  EventType.Modified);
              result.put(XmlElementNames.MovedEvent, EventType.Moved);
              result.put(XmlElementNames.NewMailEvent, EventType.NewMail);
              result.put(XmlElementNames.StatusEvent, EventType.Status);
              result.put(XmlElementNames.FreeBusyChangedEvent,
                  EventType.FreeBusyChanged);
              return result;
            }
          });

  /**
   * Gets the XML element name to event type mapping.
   *
   * @return The XML element name to event type mapping.
   */
  protected static Map<String, EventType> getXmlElementNameToEventTypeMap() {
    return GetEventsResults.xmlElementNameToEventTypeMap.getMember();
  }

  /**
   * Initializes a new instance.
   */
  public GetEventsResults() {
  }

  /**
   * Loads from XML.
   *
   * @param reader the reader
   * @throws Exception the exception
   */
  public void loadFromXml(EwsServiceXmlReader reader) throws Exception {
    reader.readStartElement(XmlNamespace.Messages,
        XmlElementNames.Notification);

    this.subscriptionId = reader.readElementValue(XmlNamespace.Types,
        XmlElementNames.SubscriptionId);
    this.previousWatermark = reader.readElementValue(XmlNamespace.Types,
        XmlElementNames.PreviousWatermark);
    this.moreEventsAvailable = reader.readElementValue(Boolean.class,
        XmlNamespace.Types, XmlElementNames.MoreEvents);

    do {
      reader.read();

      if (reader.isStartElement()) {
        String eventElementName = reader.getLocalName();
        EventType eventType;

        if (xmlElementNameToEventTypeMap.getMember().containsKey(
            eventElementName)) {
          eventType = xmlElementNameToEventTypeMap.getMember().get(
              eventElementName);
          this.newWatermark = reader.readElementValue(
              XmlNamespace.Types, XmlElementNames.Watermark);
          if (eventType == EventType.Status) {
            // We don't need to return status events
            reader.readEndElementIfNecessary(XmlNamespace.Types,
                eventElementName);
          } else {
            this.loadNotificationEventFromXml(reader,
                eventElementName, eventType);
          }
        } else {
          reader.skipCurrentElement();
        }

      }

    } while (!reader.isEndElement(XmlNamespace.Messages,
        XmlElementNames.Notification));
  }

  /**
   * Loads a notification event from XML.
   *
   * @param reader           the reader
   * @param eventElementName the event element name
   * @param eventType        the event type
   * @throws Exception the exception
   */
  private void loadNotificationEventFromXml(EwsServiceXmlReader reader,
      String eventElementName, EventType eventType) throws Exception {
    Date date = reader.readElementValue(Date.class, XmlNamespace.Types,
        XmlElementNames.TimeStamp);

    NotificationEvent notificationEvent;

    reader.read();

    if (reader.getLocalName().equals(XmlElementNames.FolderId)) {
      notificationEvent = new FolderEvent(eventType, date);
    } else {
      notificationEvent = new ItemEvent(eventType, date);
    }

    notificationEvent.loadFromXml(reader, eventElementName);
    this.events.add(notificationEvent);
  }

  /**
   * Gets the Id of the subscription the collection is associated with.
   *
   * @return the subscription id
   */
  protected String getSubscriptionId() {
    return subscriptionId;
  }

  /**
   * Gets the subscription's previous watermark.
   *
   * @return the previous watermark
   */
  protected String getPreviousWatermark() {
    return previousWatermark;
  }

  /**
   * Gets the subscription's new watermark.
   *
   * @return the new watermark
   */
  protected String getNewWatermark() {
    return newWatermark;
  }

  /**
   * Gets a value indicating whether more events are available on the Exchange
   * server.
   *
   * @return true, if is more events available
   */
  protected boolean isMoreEventsAvailable() {
    return moreEventsAvailable;
  }

  /**
   * Gets the collection of folder events.
   *
   * @return the folder events
   */
  public Iterable<FolderEvent> getFolderEvents() {
    Collection<FolderEvent> folderEvents = new ArrayList<FolderEvent>();
    for (Object event : this.events) {
      if (event instanceof FolderEvent) {
        folderEvents.add((FolderEvent) event);
      }
    }
    return folderEvents;
  }

  /**
   * Gets the collection of item events.
   *
   * @return the item events
   */
  public Iterable<ItemEvent> getItemEvents() {
    Collection<ItemEvent> itemEvents = new ArrayList<ItemEvent>();
    for (Object event : this.events) {
      if (event instanceof ItemEvent) {
        itemEvents.add((ItemEvent) event);
      }
    }
    return itemEvents;
  }

  /**
   * Gets the collection of all events.
   *
   * @return the all events
   */
  public Collection<NotificationEvent> getAllEvents() {
    return this.events;
  }
}
