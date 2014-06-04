/**************************************************************************
 * copyright file="FolderChange.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the FolderChange.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/***
 * Represents a change on a folder as returned by a synchronization operation.
 */
public final class FolderChange extends Change {
	/***
	 * Initializes a new instance of FolderChange.
	 */
	protected FolderChange() {
		super();
	}

	/***
	 * Creates a FolderId instance.
	 * 
	 * @return A FolderId.
	 */
	@Override
	protected ServiceId createId() {
		return new FolderId();
	}

	/**
	 * * Gets the folder the change applies to. Folder is null when ChangeType
	 * is equal to ChangeType.Delete. In that case, use the FolderId property to
	 * retrieve the Id of the folder that was deleted.
	 * 
	 * @return the folder
	 */
	public Folder getFolder() {
		return (Folder)this.getServiceObject();
	}

	/**
	 * * Gets the folder the change applies to. Folder is null when ChangeType
	 * is equal to ChangeType.Delete. In that case, use the FolderId property to
	 * retrieve the Id of the folder that was deleted.
	 * 
	 * @return the folder id
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public FolderId getFolderId() throws ServiceLocalException {
		return (FolderId) this.getId();
	}

}
