package microsoft.exchange.webservices.data;

import javax.xml.stream.XMLStreamException;

/**
 * Represents grouping options in item search operations.
 */
public final class Grouping implements ISelfValidate {

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
   * @param writer The Writer
   * @throws javax.xml.stream.XMLStreamException the xML stream exception
   * @throws ServiceXmlSerializationException    the service xml serialization exception
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
      e.printStackTrace();
    }

  }
}
