/**************************************************************************
 * copyright file="SyncFolderHierarchyResponse.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the SyncFolderHierarchyResponse.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/***
 * Represents the response to a folder synchronization operation.
 */
public final class SyncFolderHierarchyResponse extends
		SyncResponse<Folder, FolderChange> {

	/**
	 * * Represents the response to a folder synchronization operation.
	 * 
	 * @param propertySet
	 *            the property set
	 */
	protected SyncFolderHierarchyResponse(PropertySet propertySet) {
		super(propertySet);
	}

	/***
	 * Gets the name of the includes last in range XML element.
	 * 
	 * @return XML element name.
	 */
	@Override
	protected String getIncludesLastInRangeXmlElementName() {
		return XmlElementNames.IncludesLastFolderInRange;
	}

	/***
	 * Creates a folder change instance.
	 * 
	 * @return FolderChange instance
	 */
	@Override
	protected FolderChange createChangeInstance() {
		return new FolderChange();
	}

	/**
	 * * Gets a value indicating whether this request returns full or summary
	 * properties. <value> <c>true</c> if summary properties only; otherwise,
	 * <c>false</c>. </value>
	 * 
	 * @return the summary properties only
	 */
	@Override
	protected boolean getSummaryPropertiesOnly() {
		return false;
	}
}
