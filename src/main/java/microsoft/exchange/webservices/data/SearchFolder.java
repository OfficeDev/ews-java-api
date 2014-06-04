/**************************************************************************
 * copyright file="SearchFolder.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the SearchFolder.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents a search folder.
 */
@ServiceObjectDefinition(xmlElementName = XmlElementNames.SearchFolder, returnedByServer = true)
public class SearchFolder extends Folder {

	/**
	 * Binds to an existing search folder and loads the specified set of
	 * properties. Calling this method results in a call to EWS.
	 * 
	 * @param service
	 *            the service
	 * @param id
	 *            the id
	 * @param propertySet
	 *            the property set
	 * @return A SearchFolder instance representing the search folder
	 *         corresponding to the specified Id.
	 * @throws Exception
	 *             the exception
	 */
	public static SearchFolder bind(ExchangeService service, FolderId id,
			PropertySet propertySet) throws Exception {
		return service.bindToFolder(SearchFolder.class, id, propertySet);
	}

	/**
	 * Binds to an existing search folder and loads its first class properties.
	 * Calling this method results in a call to EWS.
	 * 
	 * @param service
	 *            the service
	 * @param id
	 *            the id
	 * @return A SearchFolder instance representing the search folder
	 *         corresponding to the specified Id.
	 * @throws Exception
	 *             the exception
	 */
	public static SearchFolder bind(ExchangeService service, FolderId id)
			throws Exception {
		return SearchFolder.bind(service, id, PropertySet
				.getFirstClassProperties());
	}

	/**
	 * Binds to an existing search folder and loads the specified set of
	 * properties. Calling this method results in a call to EWS.
	 * 
	 * @param service
	 *            the service
	 * @param name
	 *            the name
	 * @param propertySet
	 *            the property set
	 * @return A SearchFolder instance representing the search folder with the
	 *         specified name.
	 * @throws Exception
	 *             the exception
	 */
	public static SearchFolder bind(ExchangeService service,
			WellKnownFolderName name, PropertySet propertySet)
		throws Exception {
		return SearchFolder.bind(service, new FolderId(name), propertySet);
	}

	/**
	 * Binds to an existing search folder and loads the specified set of
	 * properties. Calling this method results in a call to EWS.
	 * 
	 * @param service
	 *            the service
	 * @param name
	 *            the name
	 * @return A SearchFolder instance representing the search folder with the
	 *         specified name.
	 * @throws Exception
	 *             the exception
	 */
	public static SearchFolder bind(ExchangeService service,
			WellKnownFolderName name) throws Exception {
		return SearchFolder.bind(service, new FolderId(name), PropertySet
				.getFirstClassProperties());
	}

	/**
	 * Initializes an unsaved local instance of the class. To bind to an
	 * existing search folder, use SearchFolder.Bind() instead.
	 * 
	 * @param service
	 *            the service
	 * @throws Exception
	 *             the exception
	 */
	public SearchFolder(ExchangeService service) throws Exception {
		super(service);
	}

	/**
	 * Internal method to return the schema associated with this type of object.
	 * 
	 * @return The schema associated with this type of object.
	 */
	@Override
	protected ServiceObjectSchema getSchema() {
		return SearchFolderSchema.Instance;
	}

	/**
	 * Validates this instance.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void validate() throws Exception {
		super.validate();
		if (this.getSearchParameters() != null) {
			this.getSearchParameters().validate();
		}
	}

	/**
	 * Gets the minimum required server version.
	 * 
	 * @return Earliest Exchange version in which this service object type is
	 *         supported.
	 */
	@Override
	protected ExchangeVersion getMinimumRequiredServerVersion() {
		return ExchangeVersion.Exchange2007_SP1;
	}

	/**
	 * Gets the search parameters associated with the search folder.
	 * 
	 * @return the search parameters
	 * @throws Exception
	 *             the exception
	 */
	public SearchFolderParameters getSearchParameters() throws Exception {
		return (SearchFolderParameters)this.getPropertyBag()
				.getObjectFromPropertyDefinition(
						SearchFolderSchema.SearchParameters);
	}

}
