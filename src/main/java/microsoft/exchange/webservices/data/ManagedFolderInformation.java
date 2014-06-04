/**************************************************************************
 * copyright file="ManagedFolderInformation.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ManagedFolderInformation.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/***
 * Represents information for a managed folder.
 * 
 */
public final class ManagedFolderInformation extends ComplexProperty {

	/** The can delete. */
	private Boolean canDelete;

	/** The can rename or move. */
	private Boolean canRenameOrMove;

	/** The must display comment. */
	private Boolean mustDisplayComment;

	/** The has quota. */
	private Boolean hasQuota;

	/** The is managed folders root. */
	private Boolean isManagedFoldersRoot;

	/** The managed folder id. */
	private String managedFolderId;

	/** The comment. */
	private String comment;

	/** The storage quota. */
	private Integer storageQuota;

	/** The folder size. */
	private Integer folderSize;

	/** The home page. */
	private String homePage;

	/***
	 * Initializes a new instance of the ManagedFolderInformation class.
	 */
	protected ManagedFolderInformation() {
		super();
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
	protected boolean tryReadElementFromXml(EwsServiceXmlReader reader)
			throws Exception {
		if (reader.getLocalName().equalsIgnoreCase(XmlElementNames.CanDelete)) {
			this.canDelete = reader.readValue(Boolean.class);
			return true;
		} else if (reader.getLocalName().equalsIgnoreCase(
				XmlElementNames.CanRenameOrMove)) {
			this.canRenameOrMove = reader.readValue(Boolean.class);
			return true;
		} else if (reader.getLocalName().equalsIgnoreCase(
				XmlElementNames.MustDisplayComment)) {
			this.mustDisplayComment = reader.readValue(Boolean.class);
			return true;
		} else if (reader.getLocalName().equalsIgnoreCase(
				XmlElementNames.HasQuota)) {
			this.hasQuota = reader.readValue(Boolean.class);
			return true;
		} else if (reader.getLocalName().equalsIgnoreCase(
				XmlElementNames.IsManagedFoldersRoot)) {
			this.isManagedFoldersRoot = reader.readValue(Boolean.class);
			return true;
		} else if (reader.getLocalName().equalsIgnoreCase(
				XmlElementNames.ManagedFolderId)) {
			this.managedFolderId = reader.readValue();
			return true;
		} else if (reader.getLocalName().equalsIgnoreCase(
				XmlElementNames.Comment)) {
			OutParam<String> value = new OutParam<String>();
			reader.tryReadValue(value);
			this.comment = value.getParam();
			return true;
		} else if (reader.getLocalName().equalsIgnoreCase(
				XmlElementNames.StorageQuota)) {
			this.storageQuota = reader.readValue(Integer.class);
			return true;
		} else if (reader.getLocalName().equalsIgnoreCase(
				XmlElementNames.FolderSize)) {
			this.folderSize = reader.readValue(Integer.class);
			return true;
		} else if (reader.getLocalName().equalsIgnoreCase(
				XmlElementNames.HomePage)) {
			OutParam<String> value = new OutParam<String>();
			reader.tryReadValue(value);
			this.homePage = value.getParam();
			return true;
		} else {
			return false;
		}

	}

	/**
	 * * Gets a value indicating whether the user can delete objects in the
	 * folder.
	 * 
	 * @return the can delete
	 */
	public Boolean getCanDelete() {
		return this.canDelete;
	}

	/**
	 * Gets a value indicating whether the user can rename or move objects in
	 * the folder.
	 * 
	 * @return the can rename or move
	 */
	public Boolean getCanRenameOrMove() {
		return canRenameOrMove;
	}

	/**
	 * Gets a value indicating whether the client application must display the
	 * Comment property to the user.
	 * 
	 * @return the must display comment
	 */
	public Boolean getMustDisplayComment() {
		return mustDisplayComment;
	}

	/**
	 * Gets a value indicating whether the folder has a quota.
	 * 
	 * @return the checks for quota
	 */
	public Boolean getHasQuota() {
		return hasQuota;
	}

	/**
	 * Gets a value indicating whether the folder is the root of the managed
	 * folder hierarchy.
	 * 
	 * @return the checks if is managed folders root
	 */
	public Boolean getIsManagedFoldersRoot() {
		return isManagedFoldersRoot;
	}

	/**
	 * Gets the Managed Folder Id of the folder.
	 * 
	 * @return the managed folder id
	 */
	public String getManagedFolderId() {
		return managedFolderId;
	}

	/**
	 * Gets the comment associated with the folder.
	 * 
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * Gets the storage quota of the folder.
	 * 
	 * @return the storage quota
	 */
	public Integer getStorageQuota() {
		return storageQuota;
	}

	/**
	 * Gets the size of the folder.
	 * 
	 * @return the folder size
	 */
	public Integer getFolderSize() {
		return folderSize;
	}

	/**
	 * Gets the home page associated with the folder.
	 * 
	 * @return the home page
	 */
	public String getHomePage() {
		return homePage;
	}

}
