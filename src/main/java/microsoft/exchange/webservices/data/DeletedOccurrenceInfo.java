/**************************************************************************
 * copyright file="DeletedOccurrenceInfo.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the DeletedOccurrenceInfo.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.Date;

import javax.xml.stream.XMLStreamException;

/**
 * Encapsulates information on the deleted occurrence of a recurring
 * appointment.
 */
public class DeletedOccurrenceInfo extends ComplexProperty {
	/**
	 * The original start date and time of the deleted occurrence. The EWS
	 * schema contains a Start property for deleted occurrences but it's really
	 * the original start date and time of the occurrence.
	 */
	private Date originalStart;

	/**
	 * Initializes a new instance of the "DeletedOccurrenceInfo" class.
	 */
	protected DeletedOccurrenceInfo() {
	}

	/**
	 * Tries to read element from XML.
	 * 
	 * @param reader
	 *            The reader.
	 * @return True if element was read.
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected boolean tryReadElementFromXml(EwsServiceXmlReader reader)
			throws Exception {
		if (reader.getLocalName().equalsIgnoreCase(XmlElementNames.Start)) {
			try {
				this.originalStart = reader.readElementValueAsDateTime();
			} catch (ServiceXmlDeserializationException e) {				
				e.printStackTrace();
			} catch (XMLStreamException e) {				
				e.printStackTrace();
			}
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Gets the original start date and time of the deleted occurrence.
	 * 
	 * @return the original start
	 */
	public Date getOriginalStart() {
		return this.originalStart;
	}

}
