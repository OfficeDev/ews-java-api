/**************************************************************************
 * copyright file="MeetingTimeZonePropertyDefinition.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the MeetingTimeZonePropertyDefinition.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.EnumSet;

/***
 * Represents the definition for the meeting time zone property.
 */
class MeetingTimeZonePropertyDefinition extends PropertyDefinition {

	/**
	 * Initializes a new instance of the MeetingTimeZonePropertyDefinition
	 * class.
	 * 
	 * @param xmlElementName
	 *            the xml element name
	 * @param uri
	 *            the uri
	 * @param flags
	 *            the flags
	 * @param version
	 *            the version
	 */
	protected MeetingTimeZonePropertyDefinition(String xmlElementName,
			String uri, EnumSet<PropertyDefinitionFlags> flags,
			ExchangeVersion version) {
		super(xmlElementName, uri, flags, version);

	}

	/**
	 * Loads from XML.
	 * 
	 * @param reader
	 *            the reader
	 * @param propertyBag
	 *            the property bag
	 * @throws Exception
	 *             the exception
	 */
	protected final void loadPropertyValueFromXml(EwsServiceXmlReader reader,
			PropertyBag propertyBag) throws Exception {
		MeetingTimeZone meetingTimeZone = new MeetingTimeZone();
		meetingTimeZone.loadFromXml(reader, this.getXmlElement());

		propertyBag.setObjectFromPropertyDefinition(
				AppointmentSchema.StartTimeZone, meetingTimeZone
						.toTimeZoneInfo());
	}

	/**
	 * Writes to XML.
	 * 
	 * @param writer
	 *            the writer
	 * @param propertyBag
	 *            the property bag
	 * @param isUpdateOperation
	 *            the is update operation
	 * @throws Exception
	 *             the exception
	 */
	protected void writePropertyValueToXml(EwsServiceXmlWriter writer,
			PropertyBag propertyBag, boolean isUpdateOperation)
			throws Exception {
		MeetingTimeZone value = (MeetingTimeZone)propertyBag
				.getObjectFromPropertyDefinition(this);

		if (value != null) {
			value.writeToXml(writer, this.getXmlElement());
		}
	}
	
	/**
	 * Gets the property type.
	 */
	@Override
    public Class getType() {
         return MeetingTimeZone.class; 
    }
}
