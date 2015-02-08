package microsoft.exchange.webservices.data;

import javax.xml.stream.XMLStreamException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Represents the response to a GetServerTimeZones request.
 */
class GetServerTimeZonesResponse extends ServiceResponse {

  /**
   * The time zones.
   */
  private Collection<TimeZoneDefinition> timeZones =
      new ArrayList<TimeZoneDefinition>();

  /**
   * Initializes a new instance of the class.
   */
  protected GetServerTimeZonesResponse() {
    super();
  }

  /**
   * Reads response elements from XML.
   *
   * @param reader the reader
   * @throws ServiceXmlDeserializationException                        the service xml deserialization exception
   * @throws javax.xml.stream.XMLStreamException                       the xML stream exception
   * @throws InstantiationException                                    the instantiation exception
   * @throws IllegalAccessException                                    the illegal access exception
   * @throws microsoft.exchange.webservices.data.ServiceLocalException the service local exception
   * @throws Exception                                                 the exception
   */
  @Override
  protected void readElementsFromXml(EwsServiceXmlReader reader)
      throws ServiceXmlDeserializationException, XMLStreamException,
      InstantiationException, IllegalAccessException,
      ServiceLocalException, Exception {
    super.readElementsFromXml(reader);

    reader.readStartElement(XmlNamespace.Messages,
        XmlElementNames.TimeZoneDefinitions);

    if (!reader.isEmptyElement()) {
      do {
        reader.read();

        if (reader.isStartElement(XmlNamespace.Types,
            XmlElementNames.TimeZoneDefinition)) {
          TimeZoneDefinition timeZoneDefinition =
              new TimeZoneDefinition();
          timeZoneDefinition.loadFromXml(reader);

          this.timeZones.add(timeZoneDefinition);
        }
      } while (!reader.isEndElement(XmlNamespace.Messages,
          XmlElementNames.TimeZoneDefinitions));
    } else {
      reader.read();
    }
  }

  /**
   * Reads response elements from XML.
   *
   * @return the time zones
   */
  public Collection<TimeZoneDefinition> getTimeZones() {
    return this.timeZones;
  }

}
