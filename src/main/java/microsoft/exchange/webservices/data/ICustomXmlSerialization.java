package microsoft.exchange.webservices.data;

import javax.xml.stream.XMLStreamWriter;

/**
 * The Interface CustomXmlSerializationInterface.
 */
interface ICustomXmlSerialization {

  /**
   * Custom xml serialization.
   *
   * @param writer the writer
   */
  void CustomXmlSerialization(XMLStreamWriter writer);

}
