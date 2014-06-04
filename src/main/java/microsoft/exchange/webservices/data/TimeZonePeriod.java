/**************************************************************************
 * copyright file="TimeZonePeriod.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the TimeZonePeriod.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents a time zone period as defined in the EWS schema.
 * 
 */
class TimeZonePeriod extends ComplexProperty {

	/** The Constant StandardPeriodId. */
	protected final static String StandardPeriodId = "Std";

	/** The Constant StandardPeriodName. */
	protected final static String StandardPeriodName = "Standard";

	/** The Constant DaylightPeriodId. */
	protected final static String DaylightPeriodId = "Dlt";

	/** The Constant DaylightPeriodName. */
	protected final static String DaylightPeriodName = "Daylight";

	/** The bias. */
	private TimeSpan bias;

	/** The name. */
	private String name;

	/** The id. */
	private String id;

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
		this.id = reader.readAttributeValue(XmlAttributeNames.Id);
		this.name = reader.readAttributeValue(XmlAttributeNames.Name);
		this.bias = EwsUtilities.getXSDurationToTimeSpan(reader
				.readAttributeValue(XmlAttributeNames.Bias));
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
		writer.writeAttributeValue(XmlAttributeNames.Bias, EwsUtilities
				.getTimeSpanToXSDuration(this.bias));
		writer.writeAttributeValue(XmlAttributeNames.Name, this.name);
		writer.writeAttributeValue(XmlAttributeNames.Id, this.id);
	}

	/**
	 * Loads from XML.
	 * 
	 * @param reader
	 *            the reader
	 * @throws Exception
	 *             the exception
	 */
	protected void loadFromXml(EwsServiceXmlReader reader) throws Exception {
		this.loadFromXml(reader, XmlElementNames.Period);
	}

	/**
	 * Writes to XML.
	 * 
	 * @param writer
	 *            the writer
	 * @throws Exception
	 *             the exception
	 */
	protected void writeToXml(EwsServiceXmlWriter writer) throws Exception {
		this.writeToXml(writer, XmlElementNames.Period);
	}

	/**
	 * Initializes a new instance of the TimeZonePeriod class.
	 */
	protected TimeZonePeriod() {
		super();
	}

	/**
	 * Gets a value indicating whether this period represents the Standard
	 * period.
	 * 
	 * @return true if this instance is standard period; otherwise, false
	 */
	protected boolean isStandardPeriod() {
		return this.name.equals(TimeZonePeriod.StandardPeriodName);
	}

	/**
	 * Gets the bias to UTC associated with this period.
	 * 
	 * @return the bias
	 */
	protected TimeSpan getBias() {
		return bias;
	}

	/**
	 * Sets the bias.
	 * 
	 * @param bias
	 *            the new bias
	 */
	protected void setBias(TimeSpan bias) {
		this.bias = bias;
	}

	/**
	 * Gets the name of this period.
	 * 
	 * @return the name
	 */
	protected String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name
	 *            the new name
	 */
	protected void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the id of this period.
	 * 
	 * @return the id
	 */
	protected String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id
	 *            the new id
	 */
	protected void setId(String id) {
		this.id = id;
	}

}
