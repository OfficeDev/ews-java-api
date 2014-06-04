/**************************************************************************
 * copyright file="Folder.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the Folder.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.ArrayList;
import java.util.EnumSet;

/**
 * Represents a generic folder.
 * 
 * 
 */
@ServiceObjectDefinition(xmlElementName = XmlElementNames.Folder)
public class Folder extends ServiceObject {

	/**
	 * Initializes an unsaved local instance of <see cref="Folder"/>.
	 * 
	 * @param service
	 *            EWS service to which this object belongs.
	 * @throws Exception
	 *             the exception
	 */
	public Folder(ExchangeService service) throws Exception {
		super(service);
	}

	/**
	 * Binds to an existing folder, whatever its actual type is, and loads the
	 * specified set of properties. Calling this method results in a call to
	 * EWS.
	 * 
	 * @param service
	 *            The service to use to bind to the folder.
	 * @param id
	 *            The Id of the folder to bind to.
	 * @param propertySet
	 *            The set of properties to load.
	 * @return A Folder instance representing the folder corresponding to the
	 *         specified Id.
	 * @throws Exception
	 *             the exception
	 */
	public static Folder bind(ExchangeService service, FolderId id,
			PropertySet propertySet) throws Exception {
		return service.bindToFolder(Folder.class, id, propertySet);
	}

	/**
	 * Binds to an existing folder, whatever its actual type is, and loads the
	 * specified set of properties. Calling this method results in a call to
	 * EWS.
	 * 
	 * @param service
	 *            , The service to use to bind to the folder.
	 * @param id
	 *            , The Id of the folder to bind to.
	 * @return A Folder instance representing the folder corresponding to the
	 *         specified Id.
	 * @throws Exception
	 *             the exception
	 */
	public static Folder bind(ExchangeService service, FolderId id)
			throws Exception {
		return Folder.bind(service, id, PropertySet.getFirstClassProperties());
	}

	/**
	 * Binds to an existing folder, whatever its actual type is, and loads the
	 * specified set of properties. Calling this method results in a call to
	 * EWS.
	 * 
	 * @param service
	 *            The service to use to bind to the folder.
	 * @param name
	 *            The name of the folder to bind to.
	 * @param propertySet
	 *            The set of properties to load.
	 * @return A Folder instance representing the folder corresponding to the
	 *         specified Id.
	 * @throws Exception
	 *             the exception
	 */
	public static Folder bind(ExchangeService service,
			WellKnownFolderName name, PropertySet propertySet) 
			throws Exception {
		return Folder.bind(service, new FolderId(name), propertySet);
	}

	/**
	 * * Binds to an existing folder, whatever its actual type is, and loads the
	 * specified set of properties. Calling this method results in a call to
	 * EWS.
	 * 
	 * @param service
	 *            The service to use to bind to the folder.
	 * @param name
	 *            The name of the folder to bind to.
	 * @return the folder
	 * @throws Exception
	 *             the exception
	 */
	public static Folder bind(ExchangeService service, WellKnownFolderName name)
			throws Exception {
		return Folder.bind(service, new FolderId(name), PropertySet
				.getFirstClassProperties());
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
		
		// Validate folder permissions
		try {
			if (this.getPropertyBag().contains(FolderSchema.Permissions)) {
				this.getPermissions().validate();
			}
		} catch (ServiceLocalException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Internal method to return the schema associated with this type of object.
	 * 
	 * @return The schema associated with this type of object.
	 */
	@Override
	protected ServiceObjectSchema getSchema() {
		return FolderSchema.Instance;
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
	 * Gets the name of the change XML element.
	 * 
	 * @return Xml element name
	 */
	@Override
	protected String getChangeXmlElementName() {
		return XmlElementNames.FolderChange;
	}

	/**
	 * Gets the name of the set field XML element.
	 * 
	 * @return Xml element name
	 */
	@Override
	protected String getSetFieldXmlElementName() {
		return XmlElementNames.SetFolderField;
	}

	/**
	 * Gets the name of the delete field XML element.
	 * 
	 * @return Xml element name
	 */
	@Override
	protected String getDeleteFieldXmlElementName() {
		return XmlElementNames.DeleteFolderField;
	}

	/**
	 * Loads the specified set of properties on the object.
	 * 
	 * @param propertySet
	 *            The properties to load.
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void internalLoad(PropertySet propertySet) throws Exception {
		this.throwIfThisIsNew();

		this.getService().loadPropertiesForFolder(this, propertySet);
	}

	/**
	 * Deletes the object.
	 * 
	 * @param deleteMode
	 *            the delete mode
	 * @param sendCancellationsMode
	 *            Indicates whether meeting cancellation messages should be
	 *            sent.
	 * @param affectedTaskOccurrences
	 *            Indicate which occurrence of a recurring task should be
	 *            deleted.
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void internalDelete(DeleteMode deleteMode,
			SendCancellationsMode sendCancellationsMode,
			AffectedTaskOccurrence affectedTaskOccurrences) throws Exception {
		try {
			this.throwIfThisIsNew();
		} catch (InvalidOperationException e) {
			e.printStackTrace();
		}

		this.getService().deleteFolder(this.getId(), deleteMode);
	}

	/**
	 * Deletes the folder. Calling this method results in a call to EWS.
	 * 
	 * @param deleteMode
	 *            the delete mode
	 * @throws Exception
	 *             the exception
	 */
	public void delete(DeleteMode deleteMode) throws Exception {
		this.internalDelete(deleteMode, null, null);
	}
	
	/**
	 * Empties the folder. Calling this method results in a call to EWS.
	 * 
	 * @param deletemode
	 *            the delete mode	 
	 * @throws deleteSubFolders
	 *             Indicates whether sub-folders should also be deleted.
	 * @throws Exception 
	 */
	public void empty(DeleteMode deletemode,boolean deleteSubFolders) 
	throws Exception {
		this.throwIfThisIsNew();
		this.getService().emptyFolder(this.getId(), 
				deletemode, deleteSubFolders);
	}

	/**
	 * Saves this folder in a specific folder. Calling this method results in a
	 * call to EWS.
	 * 
	 * @param parentFolderId
	 *            The Id of the folder in which to save this folder.
	 * @throws Exception
	 *             the exception
	 */
	public void save(FolderId parentFolderId) throws Exception {
		this.throwIfThisIsNotNew();

		EwsUtilities.validateParam(parentFolderId, "parentFolderId");

		if (this.isDirty()) {
			this.getService().createFolder(this, parentFolderId);
		}
	}

	/**
	 * Saves this folder in a specific folder. Calling this method results in a
	 * call to EWS.
	 * 
	 * @param parentFolderName
	 *            The name of the folder in which to save this folder.
	 * @throws Exception
	 *             the exception
	 */
	public void save(WellKnownFolderName parentFolderName) throws Exception {
		this.save(new FolderId(parentFolderName));
	}

	/**
	 * Applies the local changes that have been made to this folder. Calling
	 * this method results in a call to EWS.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public void update() throws Exception {
		if (this.isDirty()) {
			if (this.getPropertyBag().getIsUpdateCallNecessary()) {
				this.getService().updateFolder(this);
			}
		}
	}

	/**
	 * Copies this folder into a specific folder. Calling this method results in
	 * a call to EWS.
	 * 
	 * @param destinationFolderId
	 *            The Id of the folder in which to copy this folder.
	 * @return A Folder representing the copy of this folder.
	 * @throws Exception
	 *             the exception
	 */
	public Folder copy(FolderId destinationFolderId) throws Exception {
		this.throwIfThisIsNew();

		EwsUtilities.validateParam(destinationFolderId, "destinationFolderId");

		return this.getService().copyFolder(this.getId(), destinationFolderId);
	}

	/**
	 * Copies this folder into the specified folder. Calling this method results
	 * in a call to EWS.
	 * 
	 * @param destinationFolderName
	 *            The name of the folder in which to copy this folder.
	 * @return A Folder representing the copy of this folder.
	 * @throws Exception
	 *             the exception
	 */
	public Folder copy(WellKnownFolderName destinationFolderName)
			throws Exception {
		return this.copy(new FolderId(destinationFolderName));
	}

	/**
	 * Moves this folder to a specific folder. Calling this method results in a
	 * call to EWS.
	 * 
	 * @param destinationFolderId
	 *            The Id of the folder in which to move this folder.
	 * @return A new folder representing this folder in its new location. After
	 *         Move completes, this folder does not exist anymore.
	 * @throws Exception
	 *             the exception
	 */
	public Folder move(FolderId destinationFolderId) throws Exception {
		this.throwIfThisIsNew();

		EwsUtilities.validateParam(destinationFolderId, "destinationFolderId");

		return this.getService().moveFolder(this.getId(), destinationFolderId);
	}

	/**
	 * Moves this folder to a specific folder. Calling this method results in a
	 * call to EWS.
	 * 
	 * @param destinationFolderName
	 *            The name of the folder in which to move this folder.
	 * @return A new folder representing this folder in its new location. After
	 *         Move completes, this folder does not exist anymore.
	 * @throws Exception
	 *             the exception
	 */
	public Folder move(WellKnownFolderName destinationFolderName)
			throws Exception {
		return this.move(new FolderId(destinationFolderName));
	}

	/**
	 * Find items.
	 * 
	 * @param <TItem>
	 *            The type of the item.
	 * @param queryString
	 *            query string to be used for indexed search
	 * @param view
	 *            The view controlling the number of items returned.
	 * @param groupBy
	 *            The group by.
	 * @return FindItems response collection.
	 * @throws Exception
	 *             the exception
	 */
	<TItem extends Item> ServiceResponseCollection<FindItemResponse<TItem>> 
			internalFindItems(String queryString, 
					ViewBase view, Grouping groupBy)
			throws Exception {
		ArrayList<FolderId> folderIdArry = new ArrayList<FolderId>();
		folderIdArry.add(this.getId());

		this.throwIfThisIsNew();
		return this.getService().findItems(folderIdArry, 
				null, /* searchFilter */
		queryString, view, groupBy, ServiceErrorHandling.ThrowOnError);

	}

	/**
	 * Find items.
	 * 
	 * @param <TItem>
	 *            The type of the item.
	 * @param searchFilter
	 *            The search filter. Available search filter classes include
	 *            SearchFilter.IsEqualTo, SearchFilter.ContainsSubstring and
	 *            SearchFilter.SearchFilterCollection
	 * @param view
	 *            The view controlling the number of items returned.
	 * @param groupBy
	 *            The group by.
	 * @return FindItems response collection.
	 * @throws Exception
	 *             the exception
	 */
	<TItem extends Item> ServiceResponseCollection<FindItemResponse<TItem>> 
			internalFindItems(SearchFilter searchFilter, 
			ViewBase view, Grouping groupBy)
			throws Exception {
		ArrayList<FolderId> folderIdArry = new ArrayList<FolderId>();
		folderIdArry.add(this.getId());
		this.throwIfThisIsNew();

		return this.getService().findItems(folderIdArry, searchFilter, 
				null, /* queryString */
		view, groupBy, ServiceErrorHandling.ThrowOnError);
	}

	/**
	 * Find items.
	 * 
	 * @param searchFilter
	 *            The search filter. Available search filter classes include
	 *            SearchFilter.IsEqualTo, SearchFilter.ContainsSubstring and
	 *            SearchFilter.SearchFilterCollection
	 * @param view
	 *            The view controlling the number of items returned.
	 * @return FindItems results collection.
	 * @throws Exception
	 *             the exception
	 */
	public FindItemsResults<Item> findItems(SearchFilter searchFilter,
			ItemView view) throws Exception {
		EwsUtilities.validateParamAllowNull(searchFilter, "searchFilter");

		ServiceResponseCollection<FindItemResponse<Item>> responses = this
				.internalFindItems(searchFilter, view, null /* groupBy */);

		return responses.getResponseAtIndex(0).getResults();
	}

	/**
	 * Find items.
	 * 
	 * @param queryString
	 *            query string to be used for indexed search
	 * @param view
	 *            The view controlling the number of items returned.
	 * @return FindItems results collection.
	 * @throws Exception
	 *             the exception
	 */
	public FindItemsResults<Item> findItems(String queryString, ItemView view)
			throws Exception {
		EwsUtilities.validateParamAllowNull(queryString, "queryString");

		ServiceResponseCollection<FindItemResponse<Item>> responses = this
				.internalFindItems(queryString, view, null /* groupBy */);

		return responses.getResponseAtIndex(0).getResults();
	}

	/**
	 * Find items.
	 * 
	 * @param view
	 *            The view controlling the number of items returned.
	 * @return FindItems results collection.
	 * @throws Exception
	 *             the exception
	 */
	public FindItemsResults<Item> findItems(ItemView view) throws Exception {
		ServiceResponseCollection<FindItemResponse<Item>> responses = this
				.internalFindItems((SearchFilter)null, view,
						null /* groupBy */);

		return responses.getResponseAtIndex(0).getResults();
	}

	/**
	 * Find items.
	 * 
	 * @param searchFilter
	 *            The search filter. Available search filter classes include
	 *            SearchFilter.IsEqualTo, SearchFilter.ContainsSubstring and
	 *            SearchFilter.SearchFilterCollection
	 * @param view
	 *            The view controlling the number of items returned.
	 * @param groupBy
	 *            The group by.
	 * @return A collection of grouped items representing the contents of this
	 *         folder.
	 * @throws Exception
	 *             the exception
	 */
	public GroupedFindItemsResults<Item> findItems(SearchFilter searchFilter,
			ItemView view, Grouping groupBy) throws Exception {
		EwsUtilities.validateParam(groupBy, "groupBy");
		EwsUtilities.validateParamAllowNull(searchFilter, "searchFilter");

		ServiceResponseCollection<FindItemResponse<Item>> responses = this
				.internalFindItems(searchFilter, view, groupBy);

		return responses.getResponseAtIndex(0).getGroupedFindResults();
	}

	/**
	 * Find items.
	 * 
	 * @param queryString
	 *            query string to be used for indexed search
	 * @param view
	 *            The view controlling the number of items returned.
	 * @param groupBy
	 *            The group by.
	 * @return A collection of grouped items representing the contents of this
	 *         folder.
	 * @throws Exception
	 *             the exception
	 */
	public GroupedFindItemsResults<Item> findItems(String queryString,
			ItemView view, Grouping groupBy) throws Exception {
		EwsUtilities.validateParam(groupBy, "groupBy");

		ServiceResponseCollection<FindItemResponse<Item>> responses = this
				.internalFindItems(queryString, view, groupBy);

		return responses.getResponseAtIndex(0).getGroupedFindResults();
	}

	/**
	 * Obtains a list of folders by searching the sub-folders of this folder.
	 * Calling this method results in a call to EWS.
	 * 
	 * @param view
	 *            The view controlling the number of folders returned.
	 * @return An object representing the results of the search operation.
	 * @throws Exception
	 *             the exception
	 */
	public FindFoldersResults findFolders(FolderView view) throws Exception {
		this.throwIfThisIsNew();

		return this.getService().findFolders(this.getId(), view);
	}

	/**
	 * Obtains a list of folders by searching the sub-folders of this folder.
	 * Calling this method results in a call to EWS.
	 * 
	 * @param searchFilter
	 *            The search filter. Available search filter classes include
	 *            SearchFilter.IsEqualTo, SearchFilter.ContainsSubstring and
	 *            SearchFilter.SearchFilterCollection
	 * @param view
	 *            The view controlling the number of folders returned.
	 * @return An object representing the results of the search operation.
	 * @throws Exception
	 *             the exception
	 */
	public FindFoldersResults findFolders(SearchFilter searchFilter,
			FolderView view) throws Exception {
		this.throwIfThisIsNew();

		return this.getService().findFolders(this.getId(), searchFilter, view);
	}

	/**
	 * Obtains a grouped list of items by searching the contents of this folder.
	 * Calling this method results in a call to EWS.
	 * 
	 * @param view
	 *            The view controlling the number of folders returned.
	 * @param groupBy
	 *            The grouping criteria.
	 * @return A collection of grouped items representing the contents of this
	 *         folder.
	 * @throws Exception
	 *             the exception
	 */
	public GroupedFindItemsResults<Item> findItems(ItemView view,
			Grouping groupBy) throws Exception {
		EwsUtilities.validateParam(groupBy, "groupBy");

		return this.findItems((SearchFilter)null, view, groupBy);
	}

	/**
	 * Get the property definition for the Id property.
	 * 
	 * @return the id property definition
	 */
	@Override
	protected PropertyDefinition getIdPropertyDefinition() {
		return FolderSchema.Id;
	}

	/**
	 * Sets the extended property.
	 * 
	 * @param extendedPropertyDefinition
	 *            The extended property definition.
	 * @param value
	 *            The value.
	 * @throws Exception
	 *             the exception
	 */
	public void setExtendedProperty(
			ExtendedPropertyDefinition extendedPropertyDefinition, Object value)
			throws Exception {
		this.getExtendedProperties().setExtendedProperty(
				extendedPropertyDefinition, value);
	}

	/**
	 * Removes an extended property.
	 * 
	 * @param extendedPropertyDefinition
	 *            The extended property definition.
	 * @return True if property was removed.
	 * @throws Exception
	 *             the exception
	 */
	public boolean removeExtendedProperty(
			ExtendedPropertyDefinition extendedPropertyDefinition)
			throws Exception {
		return this.getExtendedProperties().removeExtendedProperty(
				extendedPropertyDefinition);
	}

	/**
	 * True if property was removed.
	 * 
	 * @return Extended properties collection.
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected ExtendedPropertyCollection getExtendedProperties()
			throws Exception {
		return this.getExtendedPropertiesForService();
	}

	/**
	 * Gets the Id of the folder.
	 * 
	 * @return the id
	 */
	public FolderId getId() {
		try {
			return (FolderId)(this.getPropertyBag()
					.getObjectFromPropertyDefinition(this
							.getIdPropertyDefinition()));
		} catch (ServiceLocalException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Gets the Id of this folder's parent folder.
	 * 
	 * @return the parent folder id
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public FolderId getParentFolderId() throws ServiceLocalException {
		return (FolderId) this.getPropertyBag()
				.getObjectFromPropertyDefinition(FolderSchema.ParentFolderId);
	}

	/**
	 * Gets the number of child folders this folder has.
	 * 
	 * @return the child folder count
	 * @throws NumberFormatException
	 *             the number format exception
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public int getChildFolderCount() throws NumberFormatException,
			ServiceLocalException {
		return (Integer.parseInt(this.getPropertyBag()
				.getObjectFromPropertyDefinition(FolderSchema.ChildFolderCount)
				.toString()));
	}

	/**
	 * Gets the display name of the folder.
	 * 
	 * @return the display name
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public String getDisplayName() throws ServiceLocalException {
		return (String) (this.getPropertyBag()
				.getObjectFromPropertyDefinition(FolderSchema.DisplayName));

	}

	/**
	 * Sets the display name of the folder.
	 * 
	 * @param value
	 *            Name of the folder
	 * @throws Exception
	 *             the exception
	 */
	public void setDisplayName(String value) throws Exception {
		this.getPropertyBag().setObjectFromPropertyDefinition(
				FolderSchema.DisplayName, value);
	}

	/**
	 * Gets the custom class name of this folder.
	 * 
	 * @return the folder class
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public String getFolderClass() throws ServiceLocalException {
		return (String) this.getPropertyBag().getObjectFromPropertyDefinition(
				FolderSchema.FolderClass);
	}
	
	/**
	 * Sets the custom class name of this folder.
	 * 
	 * @param value
	 *            name of the folder
	 * @throws Exception
	 *             the exception
	 */
	public void setFolderClass(String value) throws Exception {
		this.getPropertyBag().setObjectFromPropertyDefinition(
				FolderSchema.FolderClass, value);
	}

	/**
	 * Gets the total number of items contained in the folder.
	 * 
	 * @return the total count
	 * @throws NumberFormatException
	 *             the number format exception
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public int getTotalCount() throws NumberFormatException,
			ServiceLocalException {
		return (Integer.parseInt(this.getPropertyBag()
				.getObjectFromPropertyDefinition(FolderSchema.TotalCount)
				.toString()));
	}

	/**
	 * Gets a list of extended properties associated with the folder.
	 * 
	 * @return the extended properties for service
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	// changed the name of method as another method with same name exists
	public ExtendedPropertyCollection getExtendedPropertiesForService()
			throws ServiceLocalException {
		return (ExtendedPropertyCollection) this.getPropertyBag()
				.getObjectFromPropertyDefinition(
						ServiceObjectSchema.extendedProperties);
	}

	/**
	 * Gets the Email Lifecycle Management (ELC) information associated with the
	 * folder.
	 * 
	 * @return the managed folder information
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public ManagedFolderInformation getManagedFolderInformation()
			throws ServiceLocalException {
		return (ManagedFolderInformation) this.getPropertyBag()
				.getObjectFromPropertyDefinition(
						FolderSchema.ManagedFolderInformation);
	}

	/**
	 * Gets a value indicating the effective rights the current authenticated
	 * user has on the folder.
	 * 
	 * @return the effective rights
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	@SuppressWarnings("unchecked")
	public EnumSet<EffectiveRights> getEffectiveRights() throws ServiceLocalException {
		return (EnumSet<EffectiveRights>) this.getPropertyBag()
				.getObjectFromPropertyDefinition(FolderSchema.EffectiveRights);
	}

	/**
	 * Gets a list of permissions for the folder.
	 * 
	 * @return the permissions
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public FolderPermissionCollection getPermissions()
			throws ServiceLocalException {
		return (FolderPermissionCollection) this.getPropertyBag()
				.getObjectFromPropertyDefinition(FolderSchema.Permissions);
	}

	/**
	 * Gets the number of unread items in the folder.
	 * 
	 * @return the unread count
	 * @throws NumberFormatException
	 *             the number format exception
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public int getUnreadCount() throws NumberFormatException,
			ServiceLocalException {
		return (Integer.parseInt(this.getPropertyBag()
				.getObjectFromPropertyDefinition(FolderSchema.UnreadCount)
				.toString()));
	}

}
