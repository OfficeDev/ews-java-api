/**************************************************************************
 * copyright file="RecurringAppointmentMasterId.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the RecurringAppointmentMasterId.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents the Id of an occurrence of a recurring appointment.
 */
public final class RecurringAppointmentMasterId extends ItemId {

	/**
	 * Represents the Id of an occurrence of a recurring appointment.
	 * 
	 * @param occurrenceId
	 *            the occurrence id
	 * @throws Exception
	 *             the exception
	 */
	public RecurringAppointmentMasterId(String occurrenceId) throws Exception {
		super(occurrenceId);
	}

	/**
	 * Gets the name of the XML element.
	 * 
	 * @return XML element name.
	 */
	@Override
	protected String getXmlElementName() {
		return XmlElementNames.RecurringMasterItemId;
	}

	/**
	 * Writes attributes to XML.
	 * 
	 * @param writer
	 *            the writer
	 * @throws ServiceXmlSerializationException
	 *             the service xml serialization exception
	 */
	@Override
	protected void writeAttributesToXml(EwsServiceXmlWriter writer)
			throws ServiceXmlSerializationException {
		writer.writeAttributeValue(XmlAttributeNames.OccurrenceId, this
				.getUniqueId());
		writer.writeAttributeValue(XmlAttributeNames.ChangeKey, this
				.getChangeKey());
	}

}
