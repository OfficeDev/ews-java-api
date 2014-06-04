/**************************************************************************
 * copyright file="ItemView.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ItemView.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import javax.xml.stream.XMLStreamException;

/**
 * Represents the view settings in a folder search operation.
 * 
 */
public final class ItemView extends PagedView {

	/** The traversal. */
	private ItemTraversal traversal = ItemTraversal.Shallow;

	/** The order by. */
	private OrderByCollection orderBy = new OrderByCollection();

	/**
	 * Gets the name of the view XML element.
	 * 
	 * @return XML element name.
	 */
	@Override
	protected String getViewXmlElementName() {
		return XmlElementNames.IndexedPageItemView;
	}

	/**
	 * Gets the type of service object this view applies to.
	 * 
	 * @return A ServiceObjectType value.
	 */
	@Override
	protected ServiceObjectType getServiceObjectType() {
		return ServiceObjectType.Item;
	}

	/**
	 * Validates this view.
	 * 
	 * @param request
	 *            the request
	 * @throws ServiceVersionException
	 *             the service version exception
	 * @throws ServiceValidationException
	 *             the service validation exception
	 */
	@Override
	protected void internalValidate(ServiceRequestBase request)
			throws ServiceVersionException, ServiceValidationException {
		super.internalValidate(request);

		EwsUtilities.validateEnumVersionValue(this.traversal, request
				.getService().getRequestedServerVersion());
	}

	/**
	 * Writes the attributes to XML.
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
	 * Internals the write search settings to XML.
	 * 
	 * @param writer
	 *            the writer
	 * @param groupBy
	 *            the group by
	 * @throws javax.xml.stream.XMLStreamException
	 *             the xML stream exception
	 * @throws ServiceXmlSerializationException
	 *             the service xml serialization exception
	 */
	@Override
	protected void internalWriteSearchSettingsToXml(EwsServiceXmlWriter writer,
			Grouping groupBy) throws XMLStreamException,
			ServiceXmlSerializationException {
		super.internalWriteSearchSettingsToXml(writer, groupBy);
	}

	/**
	 * Writes OrderBy property to XML.
	 * 
	 * @param writer
	 *            the writer
	 * @throws javax.xml.stream.XMLStreamException
	 *             the xML stream exception
	 * @throws ServiceXmlSerializationException
	 *             the service xml serialization exception
	 */
	@Override
	protected void writeOrderByToXml(EwsServiceXmlWriter writer)
			throws XMLStreamException, ServiceXmlSerializationException {
		this.orderBy.writeToXml(writer, XmlElementNames.SortOrder);
	}

	/**
	 * Initializes a new instance of the ItemView class.
	 * 
	 * @param pageSize
	 *            the page size
	 */
	public ItemView(int pageSize) {
		super(pageSize);
	}

	/**
	 * Initializes a new instance of the ItemView class.
	 * 
	 * @param pageSize
	 *            the page size
	 * @param offset
	 *            the offset
	 */
	public ItemView(int pageSize, int offset) {
		super(pageSize, offset);
		this.setOffset(offset);
	}

	/**
	 * Initializes a new instance of the ItemView class.
	 * 
	 * @param pageSize
	 *            the page size
	 * @param offset
	 *            the offset
	 * @param offsetBasePoint
	 *            the offset base point
	 */
	public ItemView(int pageSize, int offset, OffsetBasePoint offsetBasePoint) {
		super(pageSize, offset, offsetBasePoint);
	}

	/**
	 * Gets  the search traversal mode. Defaults to
	 * ItemTraversal.Shallow.
	 * 
	 * @return the traversal
	 */
	public ItemTraversal getTraversal() {
		return this.traversal;
	}

	/**
	 * Sets the traversal.
	 * 
	 * @param value
	 *            the new traversal
	 */
	public void setTraversal(ItemTraversal value) {
		this.traversal = value;
	}

	/**
	 * Gets the properties against which the returned items should be ordered.
	 * 
	 * @return the order by
	 */
	public OrderByCollection getOrderBy() {
		return this.orderBy;
	}
}
