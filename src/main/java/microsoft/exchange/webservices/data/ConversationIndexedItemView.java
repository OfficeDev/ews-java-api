/**************************************************************************
 * copyright file="ConversationIndexedItemView.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ConversationIndexedItemView class.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import javax.xml.stream.XMLStreamException;

/**
 * Represents the view settings in a folder search operation.
 */
public final class ConversationIndexedItemView extends PagedView {

	private OrderByCollection orderBy = new OrderByCollection();


	/**
	 * Gets the type of service object this view applies to.
	 * @return A ServiceObjectType value.
	 */
	@Override
	protected  ServiceObjectType getServiceObjectType() {
		return ServiceObjectType.Conversation;
	}

	/**
	 * Writes the attributes to XML.
	 * @param writer The writer.
	 */
	@Override
	protected void writeAttributesToXml(EwsServiceXmlWriter writer) {
		// Do nothing
	}

	/**
	 * Gets the name of the view XML element.
	 * @return XML element name.
	 */
	@Override
	protected String getViewXmlElementName() {
		return XmlElementNames.IndexedPageItemView;
	}

	/**
	 * Validates this view.
	 * @param request The request using this view.
	 */
	@Override
	protected void internalValidate(ServiceRequestBase request) 
	throws ServiceVersionException, ServiceValidationException {
		super.internalValidate(request);
	}

	/**
	 * Internals the write search settings to XML.
	 * @param writer The writer.
	 * @param groupBy The group by.
	 */
	@Override
	protected void internalWriteSearchSettingsToXml(EwsServiceXmlWriter writer, 
			Grouping groupBy) throws ServiceXmlSerializationException, 
			XMLStreamException {
		super.internalWriteSearchSettingsToXml(writer, groupBy);
	}

	/**
	 * Writes OrderBy property to XML.
	 * @param writer The writer
	 */
	@Override
	protected void writeOrderByToXml(EwsServiceXmlWriter writer) 
	throws ServiceXmlSerializationException, XMLStreamException {
		this.orderBy.writeToXml(writer, XmlElementNames.SortOrder);
	}

	/**
	 * Writes to XML.
	 * @param writer The writer
	 */
	protected void writeToXml(EwsServiceXmlWriter writer) throws Exception {
		writer.writeStartElement(XmlNamespace.Messages, 
				this.getViewXmlElementName());

		this.internalWriteViewToXml(writer);

		writer.writeEndElement(); // this.GetViewXmlElementName()
	}

	/**
	 * Initializes a new instance of the <see cref="ItemView"/> class.
	 * @param pageSize The maximum number of elements the search operation should return.
	 */
	public ConversationIndexedItemView(int pageSize) { 
		super(pageSize);     
	}

	/**
	 * Initializes a new instance of the ItemView class.
	 * @param pageSize The maximum number of elements the search operation should return.
	 * @param offset The offset of the view from the base point.
	 */
	public ConversationIndexedItemView(int pageSize, int offset) {   
		super(pageSize, offset);
		this.setOffset(offset);
	}

	/**
	 * Initializes a new instance of the ItemView class.
	 * @param pageSize The maximum number of elements the search operation should return.
	 * @param offset The offset of the view from the base point.
	 * @param offsetBasePoint The base point of the offset.
	 */
	public ConversationIndexedItemView(
			int pageSize,
			int offset,
			OffsetBasePoint offsetBasePoint) {
		super(pageSize, offset, offsetBasePoint);

	}

	/**
	 * Gets the properties against which the returned items should be ordered.
	 */
	public OrderByCollection getOrderBy() {
		return this.orderBy; 
	}
}
