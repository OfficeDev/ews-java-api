/*
 * The MIT License
 * Copyright (c) 2012 Microsoft Corporation
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package microsoft.exchange.webservices.data.search;

import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.XmlAttributeNames;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.request.ServiceRequestBase;
import microsoft.exchange.webservices.data.core.enumeration.search.ItemTraversal;
import microsoft.exchange.webservices.data.core.enumeration.service.ServiceObjectType;
import microsoft.exchange.webservices.data.core.exception.misc.ArgumentException;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceValidationException;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceVersionException;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlSerializationException;

import java.util.Date;

/**
 * Represents a date range view of appointments in calendar folder search
 * operations.
 */
public final class CalendarView extends ViewBase {

  /**
   * The traversal.
   */
  private ItemTraversal traversal = ItemTraversal.Shallow;

  /**
   * The max item returned.
   */
  private Integer maxItemsReturned;

  /**
   * The start date.
   */
  private Date startDate;

  /**
   * The end date.
   */
  private Date endDate;

  /**
   * Writes the attribute to XML.
   *
   * @param writer the writer
   * @throws ServiceXmlSerializationException the service xml serialization exception
   */
  public void writeAttributesToXml(EwsServiceXmlWriter writer)
      throws ServiceXmlSerializationException {
    writer.writeAttributeValue(XmlAttributeNames.Traversal, this
        .getTraversal());
  }

  /**
   * Writes the search settings to XML.
   *
   * @param writer  the writer
   * @param groupBy the group by
   */
  protected void internalWriteSearchSettingsToXml(EwsServiceXmlWriter writer,
      Grouping groupBy) {
    // No search settings for calendar views.
  }

  /**
   * Writes OrderBy property to XML.
   *
   * @param writer the writer
   */
  public void writeOrderByToXml(EwsServiceXmlWriter writer) {
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
   * @param startDate the start date
   * @param endDate   the end date
   */
  public CalendarView(Date startDate, Date endDate) {
    super();
    this.startDate = startDate;
    this.endDate = endDate;
  }

  /**
   * Initializes a new instance of CalendarView.
   *
   * @param startDate        the start date
   * @param endDate          the end date
   * @param maxItemsReturned the max item returned
   */
  public CalendarView(Date startDate, Date endDate, int maxItemsReturned) {
    this(startDate, endDate);
    this.maxItemsReturned = maxItemsReturned;
  }

  /**
   * Validate instance.
   *
   * @param request the request
   * @throws ServiceVersionException    the service version exception
   * @throws ServiceValidationException the service validation exception
   */
  public void internalValidate(ServiceRequestBase request)
      throws ServiceVersionException, ServiceValidationException {
    super.internalValidate(request);

    if (this.endDate.compareTo(this.startDate) < 0) {
      throw new ServiceValidationException("EndDate must be greater than StartDate.");
    }
  }

  /**
   * Write to XML.
   *
   * @param writer the writer
   * @throws Exception the exception
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
   * Gets the maximum number of item or folder the search operation should
   * return.
   *
   * @return The maximum number of item the search operation should return.
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
   * @param startDate the new start date
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
   * @param endDate the new end date
   */
  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  /**
   * The maximum number of item the search operation should return.
   *
   * @return the max item returned
   */
  public Integer getMaxItemsReturned() {

    return this.maxItemsReturned;
  }

  /**
   * Sets the max item returned.
   *
   * @param maxItemsReturned the new max item returned
   * @throws ArgumentException the argument exception
   */
  public void setMaxItemsReturned(Integer maxItemsReturned)
      throws ArgumentException {
    if (maxItemsReturned != null) {
      if (maxItemsReturned.intValue() <= 0) {
        throw new ArgumentException("The value must be greater than 0.");
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
   * @param traversal the new traversal
   */
  public void setTraversal(ItemTraversal traversal) {
    this.traversal = traversal;
  }

}
