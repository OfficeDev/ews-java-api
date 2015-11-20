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

import microsoft.exchange.webservices.data.ISelfValidate;
import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.XmlAttributeNames;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.search.AggregateType;
import microsoft.exchange.webservices.data.core.enumeration.search.SortDirection;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlSerializationException;
import microsoft.exchange.webservices.data.property.definition.PropertyDefinitionBase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.stream.XMLStreamException;

/**
 * Represents grouping options in item search operations.
 */
public final class Grouping implements ISelfValidate {

  private static final Log LOG = LogFactory.getLog(Grouping.class);

  /**
   * The sort direction.
   */
  private SortDirection sortDirection = SortDirection.Ascending;

  /**
   * The group on.
   */
  private PropertyDefinitionBase groupOn;

  /**
   * The aggregate on.
   */
  private PropertyDefinitionBase aggregateOn;

  /**
   * The aggregate type.
   */
  private AggregateType aggregateType = AggregateType.Minimum;

  /**
   * Validates this grouping.
   *
   * @throws Exception the exception
   */
  private void internalValidate() throws Exception {
    EwsUtilities.validateParam(this.groupOn, "GroupOn");
    EwsUtilities.validateParam(this.aggregateOn, "AggregateOn");
  }

  /**
   * Initializes a new instance of the "Grouping" class.
   */
  public Grouping() {

  }

  /**
   * Initializes a new instance of the "Grouping" class.
   *
   * @param groupOn       The property to group on
   * @param sortDirection The sort direction.
   * @param aggregateOn   The property to aggregate on.
   * @param aggregateType The type of aggregate to calculate.
   * @throws Exception the exception
   */
  public Grouping(PropertyDefinitionBase groupOn,
      SortDirection sortDirection, PropertyDefinitionBase aggregateOn,
      AggregateType aggregateType) throws Exception {
    this();
    EwsUtilities.validateParam(groupOn, "groupOn");
    EwsUtilities.validateParam(aggregateOn, "aggregateOn");

    this.groupOn = groupOn;
    this.sortDirection = sortDirection;
    this.aggregateOn = aggregateOn;
    this.aggregateType = aggregateType;
  }

  /**
   * Writes to XML.
   *
   * @param writer the writer
   * @throws XMLStreamException the XML stream exception
   * @throws ServiceXmlSerializationException the service xml serialization exception
   */
  protected void writeToXml(EwsServiceXmlWriter writer)
      throws XMLStreamException, ServiceXmlSerializationException {
    writer
        .writeStartElement(XmlNamespace.Messages,
            XmlElementNames.GroupBy);
    writer.writeAttributeValue(XmlAttributeNames.Order, this.sortDirection);

    this.groupOn.writeToXml(writer);

    writer.writeStartElement(XmlNamespace.Types,
        XmlElementNames.AggregateOn);
    writer.writeAttributeValue(XmlAttributeNames.Aggregate,
        this.aggregateType);

    this.aggregateOn.writeToXml(writer);

    writer.writeEndElement(); // AggregateOn

    writer.writeEndElement(); // GroupBy
  }

  /**
   * Gets the Sort Direction.
   *
   * @return the sort direction
   */
  public SortDirection getSortDirection() {
    return sortDirection;
  }

  /**
   * Sets the Sort Direction.
   *
   * @param sortDirection the new sort direction
   */
  public void setSortDirection(SortDirection sortDirection) {
    this.sortDirection = sortDirection;
  }

  /**
   * Gets the property to group on.
   *
   * @return the group on
   */
  public PropertyDefinitionBase getGroupOn() {
    return groupOn;
  }

  /**
   * sets the property to group on.
   *
   * @param groupOn the new group on
   */
  public void setGroupOn(PropertyDefinitionBase groupOn) {
    this.groupOn = groupOn;
  }

  /**
   * Gets the property to aggregateOn.
   *
   * @return the aggregate on
   */
  public PropertyDefinitionBase getAggregateOn() {
    return aggregateOn;
  }

  /**
   * Sets the property to aggregateOn.
   *
   * @param aggregateOn the new aggregate on
   */
  public void setAggregateOn(PropertyDefinitionBase aggregateOn) {
    this.aggregateOn = aggregateOn;
  }

  /**
   * Gets the types of aggregate to calculate.
   *
   * @return the aggregate type
   */
  public AggregateType getAggregateType() {
    return aggregateType;
  }

  /**
   * Sets the types of aggregate to calculate.
   *
   * @param aggregateType the new aggregate type
   */
  public void setAggregateType(AggregateType aggregateType) {
    this.aggregateType = aggregateType;
  }

  /**
   * Implements ISelfValidate.Validate. Validates this grouping.
   */
  @Override
  public void validate() {
    try {
      this.internalValidate();
    } catch (Exception e) {
      LOG.error(e);
    }

  }
}
