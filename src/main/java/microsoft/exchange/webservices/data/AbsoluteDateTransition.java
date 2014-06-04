/**************************************************************************
 * copyright file="AbsoluteDateTransition.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the AbsoluteDateTransition.java.
 **************************************************************************/

package microsoft.exchange.webservices.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.stream.XMLStreamException;

/**
 * Represents a time zone period transition that occurs on a fixed (absolute)
 * date.
 */
class AbsoluteDateTransition extends TimeZoneTransition {

	/** The date time. */
	private Date dateTime;

	/**
	 * Gets the XML element name associated with the transition.
	 * 
	 * @return The XML element name associated with the transition.
	 */
	@Override
	protected String getXmlElementName() {
		return XmlElementNames.AbsoluteDateTransition;
	}

	/**
	 * Tries to read element from XML.
	 * 
	 * @param reader
	 *            the reader
	 * @return True if element was read.
	 * @throws java.text.ParseException
	 *             the parse exception
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected boolean tryReadElementFromXml(EwsServiceXmlReader reader)
			throws ParseException, Exception {
		boolean result = super.tryReadElementFromXml(reader);

		if (!result) {
			if (reader.getLocalName().equals(XmlElementNames.DateTime)) {
				SimpleDateFormat sdfin = new SimpleDateFormat(
						"yyyy-MM-dd'T'HH:mm:ss");
				this.dateTime = sdfin.parse(reader.readElementValue());

				result = true;
			}
		}

		return result;
	}

	/**
	 * Writes elements to XML.
	 * 
	 * @param writer
	 *            the writer
	 * @throws ServiceXmlSerializationException
	 *             the service xml serialization exception
	 * @throws javax.xml.stream.XMLStreamException
	 *             the xML stream exception
	 */
	@Override
	protected void writeElementsToXml(EwsServiceXmlWriter writer)
			throws ServiceXmlSerializationException, XMLStreamException {
		super.writeElementsToXml(writer);

		writer.writeElementValue(XmlNamespace.Types, XmlElementNames.DateTime,
				this.dateTime);
	}

	/**
	 * Initializes a new instance of the AbsoluteDateTransition class.
	 * 
	 * @param timeZoneDefinition
	 *            , The time zone definition the transition will belong to.
	 */
	protected AbsoluteDateTransition(TimeZoneDefinition timeZoneDefinition) {
		super(timeZoneDefinition);
	}

	/**
	 * Initializes a new instance of the AbsoluteDateTransition class.
	 * 
	 * @param timeZoneDefinition
	 *            The time zone definition the transition will belong to.
	 * @param targetGroup
	 *            the target group
	 */
	protected AbsoluteDateTransition(TimeZoneDefinition timeZoneDefinition,
			TimeZoneTransitionGroup targetGroup) {
		super(timeZoneDefinition, targetGroup);
	}

	/**
	 * Gets the absolute date and time when the transition occurs.
	 * 
	 * @return the date time
	 */
	protected Date getDateTime() {
		return dateTime;
	}

	/**
	 * Sets the date time.
	 * 
	 * @param dateTime
	 *            the new date time
	 */
	protected void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
}
