package microsoft.exchange.webservices.data;

import javax.xml.stream.XMLStreamException;

/**
 * Represents the base response class to subscription creation operations.
 *
 * @param <TSubscription> Subscription type
 */
final class SubscribeResponse<TSubscription extends SubscriptionBase> extends
    ServiceResponse {

  /**
   * The subscription.
   */
  private TSubscription subscription;

  /**
   * Initializes a new instance of the SubscribeResponse&lt;TSubscription
   * class.
   *
   * @param subscription The Subscription
   */
  protected SubscribeResponse(TSubscription subscription) {
    super();
    EwsUtilities.EwsAssert(subscription != null, "SubscribeResponse.ctor",
        "subscription is null");
    this.subscription = subscription;
  }

  /**
   * Reads response elements from XML.
   *
   * @param reader The reader.
   * @throws ServiceXmlDeserializationException  the service xml deserialization exception
   * @throws javax.xml.stream.XMLStreamException the xML stream exception
   * @throws InstantiationException              the instantiation exception
   * @throws IllegalAccessException              the illegal access exception
   * @throws ServiceLocalException               the service local exception
   * @throws Exception                           the exception
   */
  @Override
  protected void readElementsFromXml(EwsServiceXmlReader reader)
      throws ServiceXmlDeserializationException, XMLStreamException,
      InstantiationException, IllegalAccessException,
      ServiceLocalException, Exception {
    super.readElementsFromXml(reader);
    this.subscription.loadFromXml(reader);
  }

  /**
   * Gets the subscription.
   *
   * @return the subscription
   */
  public TSubscription getSubscription() {
    return this.subscription;
  }
}
