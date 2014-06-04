/**************************************************************************
 * copyright file="SyncFolderItemsResponse.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the SyncFolderItemsResponse.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/***
 * Represents the response to a folder items synchronization operation.
 */
public final class SyncFolderItemsResponse extends
		SyncResponse<Item, ItemChange> {

	/**
	 * * Initializes a new instance of the class.
	 * 
	 * @param propertySet
	 *            the property set
	 */
	protected SyncFolderItemsResponse(PropertySet propertySet) {
		super(propertySet);
	}

	/***
	 * Gets the name of the includes last in range XML element.
	 * 
	 * @return XML element name.
	 */
	@Override
	protected String getIncludesLastInRangeXmlElementName() {
		return XmlElementNames.IncludesLastItemInRange;
	}

	/***
	 * Creates an item change instance.
	 * 
	 * @return ItemChange instance
	 */
	@Override
	protected ItemChange createChangeInstance() {
		return new ItemChange();
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
		return true;
	}
}
