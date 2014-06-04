/**************************************************************************
 * copyright file="AlternateMailboxCollection.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the AlternateMailboxCollection.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.ArrayList;
import java.util.List;

/***
 * Represents a user setting that is a collection of alternate mailboxes.
 * 
 */
public final class AlternateMailboxCollection {	

	private ArrayList<AlternateMailbox> entries;

	/**
	 * Initializes a new instance of the class
	 */
	protected AlternateMailboxCollection() {
		this.setEntries(new ArrayList<AlternateMailbox>());
	}

	/**
	 * Loads instance of AlternateMailboxCollection from XML.
	 * 
	 * @param reader
	 *            the reader
	 * @return AlternateMailboxCollection
	 * @throws Exception
	 *             the exception
	 */
	protected static AlternateMailboxCollection loadFromXml(EwsXmlReader reader)
	throws Exception {
		AlternateMailboxCollection instance = new AlternateMailboxCollection();

		do {
			reader.read();

			if ((reader.getNodeType().getNodeType() == XMLNodeType.START_ELEMENT) &&
					(reader.getLocalName()
							.equals(XmlElementNames.AlternateMailbox))) {
				instance.getEntries().add(
						AlternateMailbox.loadFromXml(reader));
			}
		} while (!reader.isEndElement(XmlNamespace.Autodiscover,
				XmlElementNames.AlternateMailbox));

		return instance;
	}

	/**
	 * Gets the collection of alternate mailboxes.
	 */
	public List<AlternateMailbox> getEntries() {
		return this.entries;
	}

	private void  setEntries(ArrayList<AlternateMailbox> value) {
		this.entries = value;
	}
}
