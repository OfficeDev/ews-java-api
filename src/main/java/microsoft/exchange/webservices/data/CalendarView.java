/**************************************************************************
 * copyright file="CalendarView.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the CalendarView.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.Date;

/**
 * Represents a date range view of appointments in calendar folder search
 * operations.
 */
public final class CalendarView extends ViewBase {

	/** The traversal. */
	private ItemTraversal traversal = ItemTraversal.Shallow;

	/** The max items returned. */
	private Integer maxItemsReturned;

	/** The start date. */
	private Date startDate;

	/** The end date. */
	private Date endDate;

	/**
	 * Writes the attributes to XML.
	 * 
	 * @param writer
	 *            the writer
	 * @throws ServiceXmlSerializationException
	 *             the service xml serialization exception
	 */
	protected void writeAttributesToXml(EwsServiceXmlWriter writer)
			throws ServiceXmlSerializationException {
		writer.writeAttributeValue(XmlAttributeNames.Traversal, this
				.getTraversal());
	}

	/**
	 * Writes the search settings to XML.
	 * 
	 * @param writer
	 *            the writer
	 * @param groupBy
	 *            the group by
	 */
	protected void internalWriteSearchSettingsToXml(EwsServiceXmlWriter writer,
			Grouping groupBy) {
		// No search settings for calendar views.
	}

	/**
	 * Writes OrderBy property to XML.
	 * 
	 * @param writer
	 *            the writer
	 */
	protected void writeOrderByToXml(EwsServiceXmlWriter writer) {
		// No OrderBy for calendar views.
	}

	/**
	 * Gets the type of service object this view applies to.
	 * 
	 * @return A ServiceObjectType value.
	 */
	protected ServiceObjectType getServiceObjectType() {
		return ServiceObjectType.Item;
	}

	/**
	 * Initializes a new instance of CalendarView.
	 * 
	 * @param startDate
	 *            the start date
	 * @param endDate
	 *            the end date
	 */
	public CalendarView(Date startDate, Date endDate) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
	}

	/**
	 * Initializes a new instance of CalendarView.
	 * 
	 * @param startDate
	 *            the start date
	 * @param endDate
	 *            the end date
	 * @param maxItemsReturned
	 *            the max items returned
	 */
	public CalendarView(Date startDate, Date endDate, int maxItemsReturned) {
		this(startDate, endDate);
		this.maxItemsReturned = maxItemsReturned;
	}

	/**
	 * Validate instance.
	 * 
	 * @param request
	 *            the request
	 * @throws ServiceVersionException
	 *             the service version exception
	 * @throws ServiceValidationException
	 *             the service validation exception
	 */
	protected void internalValidate(ServiceRequestBase request)
			throws ServiceVersionException, ServiceValidationException {
		super.internalValidate(request);

		if (this.endDate.compareTo(this.startDate) < 0) {
			throw new ServiceValidationException(
					Strings.EndDateMustBeGreaterThanStartDate);
		}
	}

	/**
	 * Write to XML.
	 * 
	 * @param writer
	 *            the writer
	 * @throws Exception
	 *             the exception
	 */
	protected void internalWriteViewToXml(EwsServiceXmlWriter writer)
			throws Exception {
		super.internalWriteViewToXml(writer);

		writer.writeAttributeValue(XmlAttributeNames.StartDate, this.startDate);
		writer.writeAttributeValue(XmlAttributeNames.EndDate, this.endDate);
	}

	/**
	 * Gets the name of the view XML element.
	 * 
	 * @return XML element name
	 */
	protected String getViewXmlElementName() {
		return XmlElementNames.CalendarView;
	}

	/**
	 * Gets the maximum number of items or folders the search operation should
	 * return.
	 * 
	 * @return The maximum number of items the search operation should return.
	 */
	protected Integer getMaxEntriesReturned() {
		return this.maxItemsReturned;
	}

	/**
	 * Gets the start date.
	 * 
	 * @return the start date
	 */
	public Date getStartDate() {
		return this.startDate;
	}

	/**
	 * Sets the start date.
	 * 
	 * @param startDate
	 *            the new start date
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * Gets the end date.
	 * 
	 * @return the end date
	 */
	public Date getEndDate() {
		return this.endDate;
	}

	/**
	 * Sets the end date.
	 * 
	 * @param endDate
	 *            the new end date
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * The maximum number of items the search operation should return.
	 * 
	 * @return the max items returned
	 */
	public Integer getMaxItemsReturned() {

		return this.maxItemsReturned;
	}

	/**
	 * Sets the max items returned.
	 * 
	 * @param maxItemsReturned
	 *            the new max items returned
	 * @throws ArgumentException
	 *             the argument exception
	 */
	public void setMaxItemsReturned(Integer maxItemsReturned)
			throws ArgumentException {
		if (maxItemsReturned != null) {
			if (maxItemsReturned.intValue() <= 0) {
				throw new ArgumentException(Strings.ValueMustBeGreaterThanZero);
			}
		}

		this.maxItemsReturned = maxItemsReturned;
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
	 * @param traversal
	 *            the new traversal
	 */
	public void setTraversal(ItemTraversal traversal) {
		this.traversal = traversal;
	}

}
