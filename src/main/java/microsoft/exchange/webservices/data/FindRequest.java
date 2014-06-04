/**************************************************************************
 * copyright file="FindRequest.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the FindRequest.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents an abstract Find request.
 * 
 * @param <TResponse>
 *            The type of the response.
 */
abstract class FindRequest<TResponse extends ServiceResponse> extends
		MultiResponseServiceRequest<TResponse> {

	/** The parent folder ids. */
	private FolderIdWrapperList parentFolderIds = new FolderIdWrapperList();

	/** The search filter. */
	private SearchFilter searchFilter;

	/** The query string. */
	private String queryString;

	/** The view. */
	private ViewBase view;

	/**
	 * Initializes a new instance of the FindRequest class.
	 * 
	 * @param service
	 *            The service.
	 * @param errorHandlingMode
	 *            Indicates how errors should be handled.
	 * @throws Exception 
	 */
	protected FindRequest(ExchangeService service,
			ServiceErrorHandling errorHandlingMode)
			throws Exception {
		super(service, errorHandlingMode);
	}

	/**
	 * Validate request.
	 * 
	 * @throws ServiceLocalException
	 *             the service local exception
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void validate() throws ServiceLocalException, Exception {
		super.validate();

		this.getView().internalValidate(this);

		// query string parameter is only valid for Exchange2010 or higher
		//
		if (!(this.queryString == null || this.queryString.isEmpty())
				&& this.getService().getRequestedServerVersion().ordinal() <
						ExchangeVersion.Exchange2010.ordinal()) {
			throw new ServiceVersionException(String.format(
					Strings.ParameterIncompatibleWithRequestVersion,
					"queryString", ExchangeVersion.Exchange2010));
		}

		if ((!(this.queryString == null || this.queryString.isEmpty()))
				&& this.searchFilter != null) {
			throw new ServiceLocalException(
					Strings.BothSearchFilterAndQueryStringCannotBeSpecified);
		}
	}

	/**
	 * Gets the expected response message count.
	 * 
	 * @return XML element name.
	 */
	@Override
	protected int getExpectedResponseMessageCount() {
		return this.getParentFolderIds().getCount();
	}

	/**
	 * Gets the group by clause.
	 * 
	 * @return The group by clause, null if the request does not have or support
	 *         grouping.
	 */
	protected Grouping getGroupBy() {
		return null;
	}

	/**
	 * Writes XML attributes.
	 * 
	 * @param writer
	 *            The Writer
	 * @throws ServiceXmlSerializationException
	 *             the service xml serialization exception
	 */
	@Override
	protected void writeAttributesToXml(EwsServiceXmlWriter writer)
			throws ServiceXmlSerializationException {
		super.writeAttributesToXml(writer);

		this.getView().writeAttributesToXml(writer);
	}

	/**
	 * Writes XML elements.
	 * 
	 * @param writer
	 *            The Writer
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void writeElementsToXml(EwsServiceXmlWriter writer)
			throws Exception {
		this.getView().writeToXml(writer, this.getGroupBy());

		if (this.getSearchFilter() != null) {
			writer.writeStartElement(XmlNamespace.Messages,
					XmlElementNames.Restriction);
			this.getSearchFilter().writeToXml(writer);
			writer.writeEndElement(); // Restriction
		}

		this.getView().writeOrderByToXml(writer);

		try {
			this.getParentFolderIds().writeToXml(writer, XmlNamespace.Messages,
					XmlElementNames.ParentFolderIds);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (!(this.queryString == null || this.queryString.isEmpty())) {
			writer.writeElementValue(XmlNamespace.Messages,
					XmlElementNames.QueryString, this.queryString);
		}
	}

	/**
	 * Gets the parent folder ids.
	 * 
	 * @return the parent folder ids
	 */
	public FolderIdWrapperList getParentFolderIds() {
		return this.parentFolderIds;
	}

	/**
	 * Gets the search filter. Available search filter classes include
	 * SearchFilter.IsEqualTo, SearchFilter.ContainsSubstring and
	 * SearchFilter.SearchFilterCollection. If SearchFilter is null, no search
	 * filters are applied.
	 * 
	 * @return the search filter
	 */
	public SearchFilter getSearchFilter() {
		return searchFilter;
	}

	/**
	 * Sets the search filter. Available search filter classes include
	 * SearchFilter.IsEqualTo, SearchFilter.ContainsSubstring and
	 * SearchFilter.SearchFilterCollection. If SearchFilter is null, no search
	 * filters are applied.
	 * 
	 * @param searchFilter
	 *            the new search filter
	 */
	public void setSearchFilter(SearchFilter searchFilter) {
		this.searchFilter = searchFilter;
	}

	/**
	 * Gets the query string for indexed search.
	 * 
	 * @return the query string
	 */
	public String getQueryString() {
		return queryString;
	}

	/**
	 * Sets the query string for indexed search.
	 * 
	 * @param queryString
	 *            the new query string
	 */
	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	/**
	 * Gets the view controlling the number of items or folders returned.
	 * 
	 * @return the view
	 */
	public ViewBase getView() {
		return view;
	}

	/**
	 * Sets the view controlling the number of items or folders returned.
	 * 
	 * @param view
	 *            the new view
	 */
	public void setView(ViewBase view) {
		this.view = view;
	}
}
