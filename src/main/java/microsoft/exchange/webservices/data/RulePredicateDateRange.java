/**************************************************************************
 * copyright file="RulePredicateDateRange.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the RulePredicateDateRange class.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.Date;

import javax.xml.stream.XMLStreamException;

/**
 * Represents the date and time range within which messages have been received.
 */
public final class RulePredicateDateRange extends ComplexProperty {

	/**
	 * The end DateTime.
	 */
	private Date start;

	/**
	 * The end DateTime.
	 */
	private Date end;

	/**
	 * Initializes a new instance of the RulePredicateDateRange class.
	 */
	protected RulePredicateDateRange() {
		super();     
	}

	/**
	 * Gets or sets the range start date and time. 
	 * If Start is set to null, no start date applies.
	 */
	public Date getStart() {
		return this.start;
	}
	public void setStart(Date value) {
		if (this.canSetFieldValue(this.start, value)) {
			this.start = value;
			this.changed();
		}
	}

	/**
	 * Gets or sets the range end date and time. 
	 * If End is set to null, no end date applies.
	 */
	public Date getEnd() {
		return this.end;
	}
	public void setEnd(Date value) {
		if (this.canSetFieldValue(this.end, value)) {
			this.end = value;
			this.changed();
		}
	}

	/**
	 * Tries to read element from XML.
	 * @param reader The reader.
	 * @return True if element was read.
	 */
	@Override
	protected  boolean tryReadElementFromXml(EwsServiceXmlReader 
			reader) throws Exception {
		if (reader.getLocalName().equalsIgnoreCase(XmlElementNames.StartDateTime)) {
			this.start = reader.readElementValueAsDateTime();
			return true;
		}
		else if (reader.getLocalName().equalsIgnoreCase(XmlElementNames.EndDateTime))
		{
			this.end = reader.readElementValueAsDateTime();
			return true;
		}
		else { 
			return false;
		}
	}

	/**
	 * Writes elements to XML.
	 * @param writer The writer.
	 */
	@Override
	protected void writeElementsToXml(EwsServiceXmlWriter writer) 
	throws ServiceXmlSerializationException, XMLStreamException {
		if (this.getStart()!=null) {
			writer.writeElementValue(XmlNamespace.Types, 
					XmlElementNames.StartDateTime, this.getStart());
		}
		if (this.getEnd()!=null) {
			writer.writeElementValue(XmlNamespace.Types,
					XmlElementNames.EndDateTime, this.getEnd());
		}
	}

	/**
	 * Validates this instance.
	 */
	@Override
	protected void internalValidate() 
	throws ServiceValidationException, Exception {
		super.internalValidate();
		if (this.start!=null &&
				this.end!=null &&
				this.start.after(this.end)) {
			throw new ServiceValidationException(
					"Start date time cannot be bigger than end date time.");
		}
	}
}
