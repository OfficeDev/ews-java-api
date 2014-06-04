/**************************************************************************
 * copyright file="NameResolutionCollection.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the NameResolutionCollection.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a list of suggested name resolutions.
 */
public final class NameResolutionCollection implements 
		Iterable<NameResolution> {

	/** The service. */
	private ExchangeService service;

	/** The includes all resolutions. */
	private boolean includesAllResolutions;

	/** The items. */
	private List<NameResolution> items = new ArrayList<NameResolution>();

	/**
	 * Represents a list of suggested name resolutions.
	 * 
	 * @param service
	 *            the service
	 */
	protected NameResolutionCollection(ExchangeService service) {
		EwsUtilities.EwsAssert(service != null, "NameResolutionSet.ctor",
				"service is null.");
		this.service = service;
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
		reader.readStartElement(XmlNamespace.Messages,
				XmlElementNames.ResolutionSet);
		int totalItemsInView = reader.readAttributeValue(Integer.class,
				XmlAttributeNames.TotalItemsInView);
		this.includesAllResolutions = reader.readAttributeValue(Boolean.class,
				XmlAttributeNames.IncludesLastItemInRange);

		for (int i = 0; i < totalItemsInView; i++) {
			NameResolution nameResolution = new NameResolution(this);
			nameResolution.loadFromXml(reader);
			this.items.add(nameResolution);
		}

		reader.readEndElement(XmlNamespace.Messages,
				XmlElementNames.ResolutionSet);
	}

	/**
	 * Gets the session. <value>The session.</value>
	 * 
	 * @return the session
	 */
	protected ExchangeService getSession() {
		return this.service;
	}

	/**
	 * Gets the total number of elements in the list.
	 * 
	 * @return the count
	 */
	public int getCount() {
		return this.items.size();
	}

	/**
	 * Gets a value indicating whether more suggested resolutions are available.
	 * ResolveName only returns a maximum of 100 name resolutions. When
	 * IncludesAllResolutions is false, there were more than 100 matching names
	 * on the server. To narrow the search, provide a more precise name to
	 * ResolveName.
	 * 
	 * @return the includes all resolutions
	 */
	public boolean getIncludesAllResolutions() {
		return this.includesAllResolutions;
	}

	/**
	 * Gets the name resolution at the specified index.
	 * 
	 * @param index
	 *            the index
	 * @return The name resolution at the speicfied index.
	 * @throws ArgumentOutOfRangeException
	 *             the argument out of range exception
	 */
	public NameResolution nameResolutionCollection(int index)
			throws ArgumentOutOfRangeException {
		if (index < 0 || index >= this.getCount()) {
			throw new ArgumentOutOfRangeException("index",
					Strings.IndexIsOutOfRange);
		}

		return this.items.get(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<NameResolution> iterator() {

		return items.iterator();
	}
}
