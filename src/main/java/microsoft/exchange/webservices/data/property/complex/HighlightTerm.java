package microsoft.exchange.webservices.data.property.complex;

import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.XmlElementNames;

public class HighlightTerm extends ComplexProperty {

  private String scope;

  private String value;

  public HighlightTerm() {

  }

  /**
   * Tries to read element from XML.
   *
   * @param reader The reader.
   * @return true, if successful
   * @throws Exception the exception
   */
  @Override
  public boolean tryReadElementFromXml(EwsServiceXmlReader reader) throws Exception {

    if (reader.getLocalName().equals(XmlElementNames.HighlightTermScope)) {
      this.scope = reader.readElementValue();
      return true;
    } else if (reader.getLocalName().equals(XmlElementNames.HighlightTermValue)) {
      this.value = reader.readElementValue();
      return true;
    } else {
      return false;
    }
  }

}
