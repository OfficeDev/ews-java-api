/**************************************************************************
 * copyright file="DeletedOccurrenceInfoCollection.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the DeletedOccurrenceInfoCollection.java.
 **************************************************************************/

package microsoft.exchange.webservices.data;

/***
 * Represents a collection of deleted occurrence objects.
 */
@EditorBrowsable(state = EditorBrowsableState.Never)
public final class DeletedOccurrenceInfoCollection extends
		ComplexPropertyCollection<DeletedOccurrenceInfo> {

	/***
	 * Initializes a new instance of the OccurrenceInfoCollection class.
	 */
	protected DeletedOccurrenceInfoCollection() {
	}

	/**
	 * * Creates the complex property.
	 * 
	 * @param xmlElementName
	 *            the xml element name
	 * @return OccurenceInfo instance.
	 */
	@Override
	protected DeletedOccurrenceInfo createComplexProperty(
			String xmlElementName) {
		if (xmlElementName.equalsIgnoreCase(XmlElementNames.DeletedOccurrence)) {
			return new DeletedOccurrenceInfo();
		} else {
			return null;
		}
	}

	/**
	 * * Gets the name of the collection item XML element.
	 * 
	 * @param complexProperty
	 *            the complex property
	 * @return XML element name.
	 */
	@Override
	protected String getCollectionItemXmlElementName(
			DeletedOccurrenceInfo complexProperty) {
		return XmlElementNames.Occurrence;
	}
}
