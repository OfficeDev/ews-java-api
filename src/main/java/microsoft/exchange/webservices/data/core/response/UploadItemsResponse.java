package microsoft.exchange.webservices.data.core.response;

import microsoft.exchange.webservices.data.core.*;
import microsoft.exchange.webservices.data.core.enumeration.misc.*;
import microsoft.exchange.webservices.data.property.complex.*;

public class UploadItemsResponse extends ServiceResponse {

  private ItemId itemId;

  @Override
  protected void readElementsFromXml(EwsServiceXmlReader reader) throws Exception {
    super.readElementsFromXml(reader);

    reader.read();

    itemId = new ItemId();
    itemId.setNamespace(XmlNamespace.Messages);
    itemId.loadFromXml(reader, XmlElementNames.ItemId);
  }

  public ItemId getItemId() {
    return this.itemId;
  }
}
