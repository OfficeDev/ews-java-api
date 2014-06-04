/**************************************************************************
 * copyright file="FolderIdCollection.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the FolderIdCollection.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents a collection of folder Ids.
 */
@EditorBrowsable(state = EditorBrowsableState.Never)
public final class FolderIdCollection extends
		ComplexPropertyCollection<FolderId> {

	/**
	 * Initializes a new instance of the <see cref="FolderIdCollection"/> class.
	 */
	protected FolderIdCollection() {
		super();
	}

	/***
	 * Creates the complex property.
	 * 
	 * @param xmlElementName
	 *            Name of the XML element.
	 * @return Complex property instance.
	 */
	@Override
	/**
	 * Creates the complex property.
	 * @param xmlElementName Name of the XML element.
	 * @return FolderId.
	 */
	protected FolderId createComplexProperty(String xmlElementName) {
		return new FolderId();
	}

	/**
	 * Adds a folder Id to the collection.
	 * 
	 * @param folderId
	 *            The folder Id to add.
	 * @throws Exception
	 *             the exception
	 */
	public void add(FolderId folderId) throws Exception {
		EwsUtilities.validateParam(folderId, "folderId");
		if (this.contains(folderId)) {
			throw new IllegalArgumentException(Strings.IdAlreadyInList);
		}
		this.internalAdd(folderId);
	}

	/**
	 * Gets the name of the collection item XML element.
	 * 
	 * @param complexProperty
	 *            accepts FolderId
	 * @return XML element name.
	 */
	@Override
	protected String getCollectionItemXmlElementName(FolderId complexProperty) {
		return complexProperty.getXmlElementName();
	}

	/**
	 * Adds a well-known folder to the collection.
	 * 
	 * @param folderName
	 *            the folder name
	 * @return A FolderId encapsulating the specified Id.
	 */
	public FolderId add(WellKnownFolderName folderName) {
		FolderId folderId = new FolderId(folderName);
		if (this.contains(folderId)) {
			throw new IllegalArgumentException(Strings.IdAlreadyInList);
		}
		this.internalAdd(folderId);
		return folderId;
	}

	/**
	 * Clears the collection.
	 */
	public void clear() {
		this.internalClear();
	}

	/**
	 * Removes the folder Id at the specified index.
	 * 
	 * @param index
	 *            The zero-based index of the folder Id to remove.
	 */
	public void removeAt(int index) {
		if (index < 0 || index >= this.getCount()) {
			throw new IndexOutOfBoundsException(Strings.IndexIsOutOfRange);
		}
		this.internalRemoveAt(index);
	}

	/**
	 * Removes the specified folder Id from the collection.
	 * 
	 * @param folderId
	 *            The folder Id to remove from the collection.
	 * @return True if the folder id was successfully removed from the
	 *         collection, false otherwise.
	 * @throws Exception
	 *             the exception
	 */
	public boolean remove(FolderId folderId) throws Exception {
		EwsUtilities.validateParam(folderId, "folderId");
		return this.internalRemove(folderId);
	}

	/**
	 * Removes the specified well-known folder from the collection.
	 * 
	 * @param folderName
	 *            The well-knwon folder to remove from the collection.
	 * @return True if the well-known folder was successfully removed from the
	 *         collection, false otherwise.
	 */
	public boolean remove(WellKnownFolderName folderName) {
		FolderId folderId = FolderId
				.getFolderIdFromWellKnownFolderName(folderName);
		return this.internalRemove(folderId);
	}

}
