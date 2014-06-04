/**************************************************************************
 * copyright file="ItemEvent.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ItemEvent.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.Date;

/**
 * Represents an event that applies to an item.
 * 
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
	 * @param eventType
	 *            the event type
	 * @param timestamp
	 *            the timestamp
	 */
	protected ItemEvent(EventType eventType, Date timestamp) {
		super(eventType, timestamp);
	}

	/**
	 * Load from XML.
	 * 
	 * @param reader
	 *            the reader
	 * @throws Exception
	 *             the exception
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
