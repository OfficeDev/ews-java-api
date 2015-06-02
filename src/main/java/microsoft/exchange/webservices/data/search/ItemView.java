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
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.XmlAttributeNames;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.request.ServiceRequestBase;
import microsoft.exchange.webservices.data.core.enumeration.search.ItemTraversal;
import microsoft.exchange.webservices.data.core.enumeration.search.OffsetBasePoint;
import microsoft.exchange.webservices.data.core.enumeration.service.ServiceObjectType;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceValidationException;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceVersionException;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlSerializationException;

import javax.xml.stream.XMLStreamException;

/**
 * Represents the view settings in a folder search operation.
 */
public final class ItemView extends PagedView {

  /**
   * The traversal.
   */
  private ItemTraversal traversal = ItemTraversal.Shallow;

  /**
   * The order by.
   */
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
   * @param request the request
   * @throws ServiceVersionException    the service version exception
   * @throws ServiceValidationException the service validation exception
   */
  @Override public void internalValidate(ServiceRequestBase request)
      throws ServiceVersionException, ServiceValidationException {
    super.internalValidate(request);

    EwsUtilities.validateEnumVersionValue(this.traversal, request.getService().getRequestedServerVersion());
  }

  /**
   * Writes the attribute to XML.
   *
   * @param writer the writer
   * @throws ServiceXmlSerializationException the service xml serialization exception
   */
  @Override public void writeAttributesToXml(EwsServiceXmlWriter writer)
      throws ServiceXmlSerializationException {
    writer.writeAttributeValue(XmlAttributeNames.Traversal, this.traversal);
  }

  /**
   * Internals the write search settings to XML.
   *
   * @param writer  the writer
   * @param groupBy the group by
   * @throws XMLStreamException the XML stream exception
   * @throws ServiceXmlSerializationException the service xml serialization exception
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
   * @param writer the writer
   * @throws XMLStreamException the XML stream exception
   * @throws ServiceXmlSerializationException the service xml serialization exception
   */
  @Override public void writeOrderByToXml(EwsServiceXmlWriter writer)
      throws XMLStreamException, ServiceXmlSerializationException {
    this.orderBy.writeToXml(writer, XmlElementNames.SortOrder);
  }

  /**
   * Initializes a new instance of the ItemView class.
   *
   * @param pageSize the page size
   */
  public ItemView(int pageSize) {
    super(pageSize);
  }

  /**
   * Initializes a new instance of the ItemView class.
   *
   * @param pageSize the page size
   * @param offset   the offset
   */
  public ItemView(int pageSize, int offset) {
    super(pageSize, offset);
    this.setOffset(offset);
  }

  /**
   * Initializes a new instance of the ItemView class.
   *
   * @param pageSize        the page size
   * @param offset          the offset
   * @param offsetBasePoint the offset base point
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
   * @param value the new traversal
   */
  public void setTraversal(ItemTraversal value) {
    this.traversal = value;
  }

  /**
   * Gets the property against which the returned item should be ordered.
   *
   * @return the order by
   */
  public OrderByCollection getOrderBy() {
    return this.orderBy;
  }
}
