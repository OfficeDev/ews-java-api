/**************************************************************************
 * copyright file="FolderEvent.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the FolderEvent.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.Date;

/**
 * Represents an event that applies to a folder.
 * 
 */
public class FolderEvent extends NotificationEvent {

	/** The folder id. */
	private FolderId folderId;

	/** The old folder id. */
	private FolderId oldFolderId;

	/**
	 * The new number of unread messages. This is is only meaningful when
	 * EventType is equal to EventType.Modified. For all other event types, it's
	 * null.
	 */
	private int unreadCount;

	/**
	 * Initializes a new instance.
	 * 
	 * @param eventType
	 *            the event type
	 * @param timestamp
	 *            the timestamp
	 */
	protected FolderEvent(EventType eventType, Date timestamp) {
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

		this.folderId = new FolderId();
		this.folderId.loadFromXml(reader, reader.getLocalName());

		reader.read();

		setParentFolderId(new FolderId());
		getParentFolderId().loadFromXml(reader, XmlElementNames.ParentFolderId);

		switch (getEventType()) {
		case Moved:
		case Copied:
			reader.read();

			this.oldFolderId = new FolderId();
			this.oldFolderId.loadFromXml(reader, reader.getLocalName());

			reader.read();

			setParentFolderId(new FolderId());
			getParentFolderId().loadFromXml(reader, reader.getLocalName());
			break;

		case Modified:
			reader.read();
			if (reader.isStartElement()) {
				reader.ensureCurrentNodeIsStartElement(XmlNamespace.Types,
						XmlElementNames.UnreadCount);
				String str = reader.readValue();
				this.unreadCount = Integer.parseInt(str);
			}
			break;

		default:
			break;
		}
	}

	/**
	 * Gets the Id of the folder this event applies to.
	 * 
	 * @return folderId
	 */
	public FolderId getFolderId() {
		return folderId;
	}

	/**
	 * gets the Id of the folder that was moved or copied. OldFolderId is only
	 * meaningful when EventType is equal to either EventType.Moved or
	 * EventType.Copied. For all other event types, OldFolderId is null.
	 * 
	 * @return oldFolderId
	 */
	public FolderId getOldFolderId() {
		return oldFolderId;
	}

	/**
	 * Gets the new number of unread messages. This is is only meaningful when
	 * EventType is equal to EventType.Modified. For all other event types,
	 * UnreadCount is null.
	 * 
	 * @return unreadCount
	 */
	public int getUnreadCount() {
		return unreadCount;
	}

}
