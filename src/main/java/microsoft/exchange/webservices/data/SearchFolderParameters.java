/**************************************************************************
 * copyright file="SearchFolderParameters.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the SearchFolderParameters.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/***
 * Represents the parameters associated with a search folder.
 * 
 */
public final class SearchFolderParameters extends ComplexProperty implements
		IComplexPropertyChangedDelegate {

	/** The traversal. */
	private SearchFolderTraversal traversal;

	/** The root folder ids. */
	private FolderIdCollection rootFolderIds = new FolderIdCollection();

	/** The search filter. */
	private SearchFilter searchFilter;

	/***
	 * Initializes a new instance of the SearchFolderParameters class.
	 */
	protected SearchFolderParameters() {
		super();
		this.rootFolderIds.addOnChangeEvent(this);
	}

	/**
	 * Complex property changed.
	 * 
	 * @param complexProperty
	 *            the complex property
	 */
	@Override
	public void complexPropertyChanged(ComplexProperty complexProperty) {
		this.propertyChanged(complexProperty);
	}

	/**
	 * * Property changed.
	 * 
	 * @param complexProperty
	 *            the complex property
	 */
	private void propertyChanged(ComplexProperty complexProperty) {
		this.changed();
	}

	/**
	 * * Tries to read element from XML.
	 * 
	 * @param reader
	 *            the reader
	 * @return True if element was read.
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected boolean tryReadElementFromXml(EwsServiceXmlReader reader)
			throws Exception {
		if (reader.getLocalName().equalsIgnoreCase(
				XmlElementNames.BaseFolderIds)) {
			this.rootFolderIds.internalClear();
			this.rootFolderIds.loadFromXml(reader, reader.getLocalName());
			return true;
		} else if (reader.getLocalName().equalsIgnoreCase(
				XmlElementNames.Restriction)) {
			reader.read();
			this.searchFilter = SearchFilter.loadFromXml(reader);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * * Reads the attributes from XML.
	 * 
	 * @param reader
	 *            the reader
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void readAttributesFromXml(EwsServiceXmlReader reader)
			throws Exception {
		this.traversal = reader.readAttributeValue(SearchFolderTraversal.class,
				XmlAttributeNames.Traversal);
	}

	/**
	 * * Writes the attributes to XML.
	 * 
	 * @param writer
	 *            the writer
	 * @throws ServiceXmlSerializationException
	 *             the service xml serialization exception
	 */
	@Override
	protected void writeAttributesToXml(EwsServiceXmlWriter writer)
			throws ServiceXmlSerializationException {
		writer.writeAttributeValue(XmlAttributeNames.Traversal, this.traversal);
	}

	/**
	 * * Writes elements to XML.
	 * 
	 * @param writer
	 *            the writer
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void writeElementsToXml(EwsServiceXmlWriter writer)
			throws Exception {
		if (this.searchFilter != null) {
			writer.writeStartElement(XmlNamespace.Types,
					XmlElementNames.Restriction);
			this.searchFilter.writeToXml(writer);
			writer.writeEndElement(); // Restriction
		}

		this.rootFolderIds.writeToXml(writer, XmlElementNames.BaseFolderIds);
	}

	/**
	 * * Validates this instance.
	 * @throws Exception 
	 */
	public void validate() throws Exception {
		// Search folder must have at least one root folder id.
		if (this.rootFolderIds.getCount() == 0) {
			throw new ServiceValidationException(
					Strings.SearchParametersRootFolderIdsEmpty);
		}

		// Validate the search filter
		if (this.searchFilter != null) {
			this.searchFilter.internalValidate();
		}
	}

	/**
	 * * Gets the traversal mode for the search folder.
	 * 
	 * @return the traversal
	 */
	public SearchFolderTraversal getTraversal() {
		return traversal;
	}

	/**
	 * Sets the traversal.
	 * 
	 * @param traversal
	 *            the new traversal
	 */
	public void setTraversal(SearchFolderTraversal traversal) {
		if (this.canSetFieldValue(this.traversal, traversal)) {
			this.traversal = traversal;
			this.changed();
		}
	}

	/**
	 * Gets the list of root folders the search folder searches in.
	 * 
	 * @return the root folder ids
	 */
	public FolderIdCollection getRootFolderIds() {
		return rootFolderIds;
	}

	/**
	 * Gets the search filter associated with the search folder.
	 * Available search filter classes include SearchFilter.IsEqualTo,
	 * SearchFilter.ContainsSubstring and SearchFilter.SearchFilterCollection.
	 * 
	 * @return the search filter
	 */
	public SearchFilter getSearchFilter() {
		return searchFilter;
	}

	/**
	 * Sets the search filter.
	 * 
	 * @param searchFilter
	 *            the new search filter
	 */
	public void setSearchFilter(SearchFilter searchFilter) {

		if (this.searchFilter != null) {
			this.searchFilter.removeChangeEvent(this);
		}

		if (this.canSetFieldValue(this.searchFilter, searchFilter)) {
			this.searchFilter = searchFilter;
			this.changed();
		}
		if (this.searchFilter != null) {
			this.searchFilter.addOnChangeEvent(this);
		}
	}

}
