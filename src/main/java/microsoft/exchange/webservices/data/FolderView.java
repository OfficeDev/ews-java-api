/**************************************************************************
 * copyright file="FolderView.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the FolderView.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents the view settings in a folder search operation.
 * 
 * 
 */
public final class FolderView extends PagedView {

	/** The traversal. */
	private FolderTraversal traversal = FolderTraversal.Shallow;

	/**
	 * Gets the name of the view XML element.
	 * 
	 * @return Xml Element name
	 */
	@Override
	protected String getViewXmlElementName() {
		return XmlElementNames.IndexedPageFolderView;
	}

	/**
	 * Gets the type of service object this view applies to.
	 * 
	 * @return A ServiceObjectType value.
	 */
	@Override
	protected ServiceObjectType getServiceObjectType() {
		return ServiceObjectType.Folder;
	}

	/**
	 * Writes the attributes to XML.
	 * 
	 * @param writer
	 *            The writer
	 */
	@Override
	protected void writeAttributesToXml(EwsServiceXmlWriter writer) {
		try {
			writer.writeAttributeValue(XmlAttributeNames.Traversal, this
					.getTraversal());
		} catch (ServiceXmlSerializationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initializes a new instance of the FolderView class.
	 * 
	 * @param pageSize
	 *            The maximum number of elements the search operation should
	 *            return.
	 */
	public FolderView(int pageSize) {
		super(pageSize);
	}

	/**
	 * Initializes a new instance of the FolderView class.
	 * 
	 * @param pageSize
	 *            The maximum number of elements the search operation should
	 *            return.
	 * @param offset
	 *            The offset of the view from the base point.
	 */
	public FolderView(int pageSize, int offset) {
		super(pageSize, offset);
	}

	/**
	 * Initializes a new instance of the FolderView class.
	 * 
	 * @param pageSize
	 *            The maximum number of elements the search operation should
	 *            return.
	 * @param offset
	 *            The offset of the view from the base point.
	 * @param offsetBasePoint
	 *            The base point of the offset.
	 */
	public FolderView(int pageSize, int offset, 
			OffsetBasePoint offsetBasePoint) {
		super(pageSize, offset, offsetBasePoint);
	}

	/**
	 * Gets the search traversal mode. Defaults to FolderTraversal.Shallow.
	 * 
	 * @return the traversal
	 */
	public FolderTraversal getTraversal() {
		return traversal;
	}

	/**
	 * Sets the search traversal mode. Defaults to FolderTraversal.Shallow.
	 * 
	 * @param traversal
	 *            the new traversal
	 */
	public void setTraversal(FolderTraversal traversal) {
		this.traversal = traversal;
	}
}
