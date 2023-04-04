package microsoft.exchange.webservices.data.core.response;

import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.property.complex.ItemId;

public class UploadItemsResponse extends ServiceResponse {

  private ItemId itemId;

  @Override
  protected void readElementsFromXml(EwsServiceXmlReader reader) throws Exception {
    super.readElementsFromXml(reader);

    reader.read();

    itemId = new ItemId();
    itemId.setNamespace(XmlNamespace.Messages);
    itemId.loadFromXml(reader, XmlElementNames.ItemId);
    itemId.setNamespace(XmlNamespace.Types);
  }

  public ItemId getItemId() {
    return this.itemId;
  }
}
