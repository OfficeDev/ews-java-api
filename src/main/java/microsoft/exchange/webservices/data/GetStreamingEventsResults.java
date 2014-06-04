/**************************************************************************
 * copyright file="GetStreamingEventsResults.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the GetStreamingEventsResults class.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Represents a collection of notification events.
 */
 final class GetStreamingEventsResults {

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
	protected GetStreamingEventsResults() {
	}

	/**
	 * Loads from XML.
	 * @param reader The reader.
	 * @throws Exception 
	 */
	protected void loadFromXml(EwsServiceXmlReader reader) throws Exception {
		reader.readStartElement(XmlNamespace.Messages,
				XmlElementNames.Notification);

		do {
			NotificationGroup notifications = new NotificationGroup();
			notifications.subscriptionId = reader.readElementValue(
					XmlNamespace.Types, 
					XmlElementNames.SubscriptionId);
			notifications.events = new ArrayList<NotificationEvent>();

			synchronized(this) {
				this.events.add(notifications);
			}

			do {
				reader.read();

				if (reader.isStartElement()) {
					String eventElementName = reader.getLocalName();
					EventType eventType;
					if (GetEventsResults.getXmlElementNameToEventTypeMap().containsKey(eventElementName))
					{
						eventType = GetEventsResults.getXmlElementNameToEventTypeMap().
						get(eventElementName);						
						if (eventType == EventType.Status) {
							// We don't need to return status events
							reader.readEndElementIfNecessary(XmlNamespace.Types, 
									eventElementName);
						}
						else {
							this.loadNotificationEventFromXml(
									reader,
									eventElementName,
									eventType, 
									notifications);
						}
					}
					else {
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
	 * @param reader The reader.
	 * @param eventElementName Name of the event XML element.
	 * @param eventType Type of the event.
	 * @param notifications Collection of notifications
	 * @throws Exception 
	 */
	private void loadNotificationEventFromXml(
			EwsServiceXmlReader reader,
			String eventElementName,
			EventType eventType,
			NotificationGroup notifications) throws Exception {
		Date timestamp = reader.readElementValue(Date.class,XmlNamespace.Types,
				XmlElementNames.TimeStamp);

		NotificationEvent notificationEvent;

		reader.read();

		if (reader.getLocalName() == XmlElementNames.FolderId) {
			notificationEvent = new FolderEvent(eventType, timestamp);
		}
		else {
			notificationEvent = new ItemEvent(eventType, timestamp);
		}

		notificationEvent.loadFromXml(reader, eventElementName);
		notifications.events.add(notificationEvent);
	}

	/**
	 * Gets the notification collection.
	 * @value The notification collection.
	 */
	protected Collection<NotificationGroup> getNotifications() {
		return this.events;
	}
}

