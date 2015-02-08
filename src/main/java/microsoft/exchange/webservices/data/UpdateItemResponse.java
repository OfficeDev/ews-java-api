package microsoft.exchange.webservices.data;

import javax.xml.stream.XMLStreamException;

/**
 * The Class UpdateItemResponse.
 */
public final class UpdateItemResponse extends ServiceResponse implements
    IGetObjectInstanceDelegate<ServiceObject> {

  /**
   * Represents the response to an individual item update operation.
   */
  private Item item;

  /**
   * The returned item.
   */
  private Item returnedItem;

  /**
   * The conflict count.
   */
  private int conflictCount;

  /**
   * Initializes a new instance of the class.
   *
   * @param item the item
   */
  protected UpdateItemResponse(Item item) {
    super();
    EwsUtilities.EwsAssert(item != null, "UpdateItemResponse.ctor",
        "item is null");
    this.item = item;
  }

  /**
   * Reads response elements from XML.
   *
   * @param reader the reader
   * @throws ServiceXmlDeserializationException  the service xml deserialization exception
   * @throws javax.xml.stream.XMLStreamException the xML stream exception
   * @throws InstantiationException              the instantiation exception
   * @throws IllegalAccessException              the illegal access exception
   * @throws Exception                           the exception
   */
  @Override
  protected void readElementsFromXml(EwsServiceXmlReader reader)
      throws ServiceXmlDeserializationException, XMLStreamException,
      InstantiationException, IllegalAccessException, Exception {
    super.readElementsFromXml(reader);

    reader.readServiceObjectsCollectionFromXml(XmlElementNames.Items, this,
        false, null, false);

    if (!reader.getService().getExchange2007CompatibilityMode()) {
      reader.readStartElement(XmlNamespace.Messages,
          XmlElementNames.ConflictResults);
      this.conflictCount = reader.readElementValue(Integer.class,
          XmlNamespace.Types, XmlElementNames.Count);
      reader.readEndElement(XmlNamespace.Messages,
          XmlElementNames.ConflictResults);
    }

    // If UpdateItem returned an item that has the same Id as the item that
    // is being updated, this is a "normal" UpdateItem operation, and we
    // need
    // to update the ChangeKey of the item being updated with the one that
    // was
    // returned. Also set returnedItem to indicate that no new item was
    // returned.
    //
    // Otherwise, this in a "special" UpdateItem operation, such as a
    // recurring
    // task marked as complete (the returned item in that case is the
    // one-off
    // task that represents the completed instance).
    //
    // Note that there can be no returned item at all, as in an UpdateItem
    // call
    // with MessageDisposition set to SendOnly or SendAndSaveCopy.
    if (this.returnedItem != null) {
      if (this.item.getId().getUniqueId().equals(
          this.returnedItem.getId().getUniqueId())) {
        this.item.getId().setChangeKey(
            this.returnedItem.getId().getChangeKey());
        this.returnedItem = null;
      }
    }
  }

  /*
   * (non-Javadoc)
   *
   * @seemicrosoft.exchange.webservices.GetObjectInstanceDelegateInterface#
   * getObjectInstanceDelegate(microsoft.exchange.webservices.ExchangeService,
   * java.lang.String)
   */
  public ServiceObject getObjectInstanceDelegate(ExchangeService service,
      String xmlElementName) throws Exception {
    return this.getObjectInstance(service, xmlElementName);
  }

  /**
   * Clears the change log of the created folder if the creation succeeded.
   */
  @Override
  protected void loaded() {
    if (this.getResult() == ServiceResult.Success) {
      this.item.clearChangeLog();
    }
  }

  /**
   * Gets Item instance.
   *
   * @param service        the service
   * @param xmlElementName the xml element name
   * @return Item
   * @throws Exception the exception
   */
  private Item getObjectInstance(ExchangeService service,
      String xmlElementName) throws Exception {
    this.returnedItem = EwsUtilities.createEwsObjectFromXmlElementName(
        Item.class, service, xmlElementName);
    return this.returnedItem;
  }

  /**
   * Gets the item that was returned by the update operation. ReturnedItem
   * is set only when a recurring Task is marked as complete or when its
   * recurrence pattern changes.
   *
   * @return the returned item
   */
  public Item getReturnedItem() {
    return this.returnedItem;
  }

  /**
   * Gets the number of property conflicts that were resolved during the
   * update operation.
   *
   * @return the conflict count
   */
  public int getConflictCount() {
    return this.conflictCount;
  }

}
