/**************************************************************************
 * copyright file="MeetingTimeZone.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the MeetingTimeZone.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 *Represents a time zone in which a meeting is defined.
 */
final class MeetingTimeZone extends ComplexProperty {

	/** The name. */
	private String name;

	/** The base offset. */
	private TimeSpan baseOffset;

	/** The standard. */
	private TimeChange standard;

	/** The daylight. */
	private TimeChange daylight;

	/**
	 * Initializes a new instance of the MeetingTimeZone class.
	 * 
	 * @param timeZone
	 *            The time zone used to initialize this instance.
	 */
	protected MeetingTimeZone(TimeZoneDefinition timeZone) {
		// Unfortunately, MeetingTimeZone does not support all the time
		// transition types
		// supported by TimeZoneInfo. That leaves us unable to accurately
		// convert TimeZoneInfo
		// into MeetingTimeZone. So we don't... Instead, we emit the time zone's
		// Id and
		// hope the server will find a match (which it should).
		this.name = timeZone.getId();
	}

	/**
	 * Initializes a new instance of the MeetingTimeZone class.
	 * */
	public MeetingTimeZone() {
		super();
	}

	/**
	 * Initializes a new instance of the MeetingTimeZone class.
	 * 
	 * @param name
	 *            The name of the time zone.
	 */
	public MeetingTimeZone(String name) {
		this();
		this.name = name;
	}

	/**
	 * Gets the minimum required server version.
	 * 
	 * @param reader
	 *            the reader
	 * @return Earliest Exchange version in which this service object type is
	 *         supported.
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected boolean tryReadElementFromXml(EwsServiceXmlReader reader)
			throws Exception {
		if (reader.getLocalName().equals(XmlElementNames.BaseOffset)) {
			this.baseOffset = EwsUtilities.getXSDurationToTimeSpan(reader
					.readElementValue());
			return true;
		} else if (reader.getLocalName().equals(XmlElementNames.Standard)) {
			this.standard = new TimeChange();
			this.standard.loadFromXml(reader, reader.getLocalName());
			return true;
		} else if (reader.getLocalName().equals(XmlElementNames.Daylight)) {
			this.daylight = new TimeChange();
			this.daylight.loadFromXml(reader, reader.getLocalName());
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Reads the attributes from XML.
	 * 
	 * @param reader
	 *            the reader
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void readAttributesFromXml(EwsServiceXmlReader reader)
			throws Exception {
		this.name = reader.readAttributeValue(XmlAttributeNames.TimeZoneName);
	}

	/**
	 * Writes the attributes to XML.
	 * 
	 * @param writer
	 *            the writer
	 * @throws ServiceXmlSerializationException
	 *             the service xml serialization exception
	 */
	@Override
	protected void writeAttributesToXml(EwsServiceXmlWriter writer)
			throws ServiceXmlSerializationException {
		writer.writeAttributeValue(XmlAttributeNames.TimeZoneName, this
				.getName());
	}

	/**
	 * Writes the attributes to XML.
	 * 
	 * @param writer
	 *            the writer
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void writeElementsToXml(EwsServiceXmlWriter writer)
			throws Exception {
		if (this.baseOffset != null) {
			writer.writeElementValue(XmlNamespace.Types,
					XmlElementNames.BaseOffset, EwsUtilities
							.getTimeSpanToXSDuration(this.getBaseOffset()));
		}

		if (this.getStandard() != null) {
			this.getStandard().writeToXml(writer, XmlElementNames.Standard);
		}

		if (this.getDaylight() != null) {
			this.getDaylight().writeToXml(writer, XmlElementNames.Daylight);
		}
	}

	/**
	 * Converts this meeting time zone into a TimeZoneInfo structure.
	 * 
	 * @return the time zone
	 */
	protected TimeZoneDefinition toTimeZoneInfo() {
		TimeZoneDefinition result = null;

		try {
			result = new TimeZoneDefinition(); 
			//TimeZone.getTimeZone(this.getName());
			result.setId(this.getName());
		} catch (Exception e) {
			// Could not find a time zone with that Id on the local system.
			e.printStackTrace();
		}

		// Again, we cannot accurately convert MeetingTimeZone into TimeZoneInfo
		// because TimeZoneInfo doesn't support absolute date transitions. So if
		// there is no system time zone that has a matching Id, we return null.
		return result;
	}

	/**
	 * Gets  the name of the time zone.
	 * 
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param value
	 *            the new name
	 */
	public void setName(String value) {
		if (this.canSetFieldValue(this.name, value)) {
			this.name = value;
			this.changed();
		}
	}

	/**
	 * Gets the base offset of the time zone from the UTC time zone.
	 * 
	 * @return the base offset
	 */
	public TimeSpan getBaseOffset() {
		return this.baseOffset;
	}

	/**
	 * Sets the base offset.
	 * 
	 * @param value
	 *            the new base offset
	 */
	public void setBaseOffset(TimeSpan value) {
		if (this.canSetFieldValue(this.name, value)) {
			this.baseOffset = value;
			this.changed();
		}
	}

	/**
	 * Gets  a TimeChange defining when the time changes to Standard
	 * Time.
	 * 
	 * @return the standard
	 */
	public TimeChange getStandard() {
		return this.standard;
	}

	/**
	 * Sets the standard.
	 * 
	 * @param value
	 *            the new standard
	 */
	public void setStandard(TimeChange value) {
		if (this.canSetFieldValue(this.standard, value)) {
			this.standard = value;
			this.changed();
		}
	}

	/**
	 * Gets  a TimeChange defining when the time changes to Daylight
	 * Saving Time.
	 * 
	 * @return the daylight
	 */
	public TimeChange getDaylight() {
		return this.daylight;
	}

	/**
	 * Sets the daylight.
	 * 
	 * @param value
	 *            the new daylight
	 */
	public void setDaylight(TimeChange value) {
		if (this.canSetFieldValue(this.daylight, value)) {
			this.daylight = value;
			this.changed();
		}
	}

}
