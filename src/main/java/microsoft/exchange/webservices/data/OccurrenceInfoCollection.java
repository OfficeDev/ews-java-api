/**************************************************************************
 * copyright file="OccurrenceInfoCollection.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the OccurrenceInfoCollection.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents a collection of OccurrenceInfo objects.
 */
@EditorBrowsable(state = EditorBrowsableState.Never)
public final class OccurrenceInfoCollection extends
		ComplexPropertyCollection<OccurrenceInfo> {

	/**
	 * Initializes a new instance of the <see cref="OccurrenceInfoCollection"/>
	 * class.
	 */
	protected OccurrenceInfoCollection() {
	}

	/**
	 * Creates the complex property.
	 * 
	 * @param xmlElementName
	 *            Name of the XML element
	 * @return OccuranceInfo instance
	 */
	@Override
	protected OccurrenceInfo createComplexProperty(String xmlElementName) {
		if (xmlElementName.equals(XmlElementNames.Occurrence)) {
			return new OccurrenceInfo();
		} else {
			return null;
		}
	}

	/**
	 * Gets the name of the collection item XML element.
	 * 
	 * @param complexProperty
	 *            The complex property.
	 * @return XML element name.
	 */
	@Override
	protected String getCollectionItemXmlElementName(
			OccurrenceInfo complexProperty) {
		return XmlElementNames.Occurrence;
	}

}
