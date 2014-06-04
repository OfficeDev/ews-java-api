/**************************************************************************
 * copyright file="OccurrenceInfo.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the OccurrenceInfo.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.Date;

/**
 * Encapsulates information on the occurrence of a recurring appointment.
 * 
 */
public final class OccurrenceInfo extends ComplexProperty {

	/** The item id. */
	private ItemId itemId;

	/** The start. */
	private Date start;

	/** The end. */
	private Date end;

	/** The original start. */
	private Date originalStart;

	/**
	 * Initializes a new instance of the OccurrenceInfo class.
	 */
	protected OccurrenceInfo() {
	}

	/**
	 * Tries to read element from XML.
	 * 
	 * @param reader
	 *            the reader
	 * @return true, if successful
	 * @throws Exception
	 *             the exception
	 */
	protected boolean tryReadElementFromXml(EwsServiceXmlReader reader)
			throws Exception {
		if (reader.getLocalName().equals(XmlElementNames.ItemId)) {

			this.itemId = new ItemId();
			this.itemId.loadFromXml(reader, reader.getLocalName());
			return true;
		} else if (reader.getLocalName().equals(XmlElementNames.Start)) {

			this.start = reader.readElementValueAsDateTime();
			return true;
		} else if (reader.getLocalName().equals(XmlElementNames.End)) {

			this.end = reader.readElementValueAsDateTime();
			return true;
		} else if (reader.getLocalName().equals(XmlElementNames.OriginalStart)) {

			this.originalStart = reader.readElementValueAsDateTime();
			return true;
		} else {

			return false;
		}
	}

	/**
	 * Gets the Id of the occurrence.
	 * 
	 * @return the item id
	 */
	public ItemId getItemId() {
		return itemId;
	}

	/**
	 * Gets the start date and time of the occurrence.
	 * 
	 * @return the start
	 */
	public Date getStart() {
		return start;
	}

	/**
	 * Gets the end date and time of the occurrence.
	 * 
	 * @return the end
	 */
	public Date getEnd() {
		return end;
	}

	/**
	 * Gets the original start date and time of the occurrence.
	 * 
	 * @return the original start
	 */
	public Date getOriginalStart() {
		return originalStart;
	}

}
