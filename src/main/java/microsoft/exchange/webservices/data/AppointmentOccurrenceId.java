/**************************************************************************
 * copyright file="AppointmentOccurrenceId.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the AppointmentOccurrenceId.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/***
 * Represents the Id of an occurrence of a recurring appointment.
 * 
 */
public final class AppointmentOccurrenceId extends ItemId {

	/**
	 * Index of the occurrence.
	 */
	private int occurrenceIndex;

	/**
	 * Initializes a new instance.
	 * 
	 * @param recurringMasterUniqueId
	 *            the recurring master unique id
	 * @param occurrenceIndex
	 *            the occurrence index
	 * @throws Exception
	 *             the exception
	 */
	public AppointmentOccurrenceId(String recurringMasterUniqueId,
			int occurrenceIndex) throws Exception {
		super(recurringMasterUniqueId);
		this.occurrenceIndex = occurrenceIndex;
	}

	/**
	 * Gets  the index of the occurrence. Note that the occurrence index
	 * starts at one not zero.
	 * 
	 * @return the occurrence index
	 */
	public int getOccurrenceIndex() {
		return occurrenceIndex;
	}

	/**
	 * Sets the occurrence index.
	 * 
	 * @param occurrenceIndex
	 *            the new occurrence index
	 */
	public void setOccurrenceIndex(int occurrenceIndex) {
		if (occurrenceIndex < 1) {
			throw new IllegalArgumentException(
					Strings.OccurrenceIndexMustBeGreaterThanZero);
		}
		this.occurrenceIndex = occurrenceIndex;
	}

	/**
	 * Gets the name of the XML element.
	 * 
	 * @return XML element name
	 */
	@Override
	protected String getXmlElementName() {
		return XmlElementNames.OccurrenceItemId;
	}

	/**
	 * Gets the name of the XML element.
	 * 
	 * @param writer
	 *            the writer
	 * @throws ServiceXmlSerializationException
	 *             the service xml serialization exception
	 */
	protected void writeAttributesToXml(EwsServiceXmlWriter writer)
			throws ServiceXmlSerializationException {
		writer.writeAttributeValue(XmlAttributeNames.RecurringMasterId, this
				.getUniqueId());
		writer.writeAttributeValue(XmlAttributeNames.InstanceIndex, this
				.getOccurrenceIndex());
	}

}
