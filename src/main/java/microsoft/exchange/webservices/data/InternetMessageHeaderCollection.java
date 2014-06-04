/**************************************************************************
 * copyright file="InternetMessageHeaderCollection.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the InternetMessageHeaderCollection.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents a collection of Internet message headers.
 */
@EditorBrowsable(state = EditorBrowsableState.Never)
public final class InternetMessageHeaderCollection extends
		ComplexPropertyCollection<InternetMessageHeader> {
	/**
	 *Initializes a new instance of the "InternetMessageHeaderCollection"
	 * class.
	 */
	protected InternetMessageHeaderCollection() {
		super();
	}

	/**
	 * Creates the complex property.
	 * 
	 * @param xmlElementName
	 *            Name of the XML element.
	 * @return InternetMessageHeader instance
	 */
	@Override
	protected InternetMessageHeader createComplexProperty(
			String xmlElementName) {
		return new InternetMessageHeader();
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
			InternetMessageHeader complexProperty) {
		return XmlElementNames.InternetMessageHeader;
	}

	/**
	 * Find a specific header in the collection.
	 * 
	 * @param name
	 *            The name of the header to locate.
	 * @return An InternetMessageHeader representing the header with the
	 *         specified name; null if no header with the specified name was
	 *         found.
	 */
	public InternetMessageHeader find(String name) {
		for (InternetMessageHeader internetMessageHeader : this) {
			if (name.compareTo(internetMessageHeader.getName()) == 0 &&
					 name.equalsIgnoreCase(internetMessageHeader.getName())) {
				return internetMessageHeader;
			}
		}
		return null;
	}

}
