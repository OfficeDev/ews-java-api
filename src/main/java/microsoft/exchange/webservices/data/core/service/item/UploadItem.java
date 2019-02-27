package microsoft.exchange.webservices.data.core.service.item;

import microsoft.exchange.webservices.data.core.*;
import microsoft.exchange.webservices.data.core.enumeration.misc.*;
import microsoft.exchange.webservices.data.property.complex.*;

public class UploadItem {

  public enum CreateAction {
    CreateNew,
    Update
  }

  private CreateAction createAction;

  private FolderId parentFolderId;

  private ItemId itemId;

  private byte[] data;

  public void writeToXml(EwsServiceXmlWriter writer) {
    try {
      writer.writeStartElement(XmlNamespace.Types, XmlElementNames.Item);
      writer.writeAttributeValue(XmlAttributeNames.CreateAction, createAction);
      parentFolderId.writeToXml(writer, XmlElementNames.ParentFolderId);
      if (itemId != null) {
        itemId.writeToXml(writer);
      }
      writer.writeStartElement(XmlNamespace.Types, XmlElementNames.Data);
      writer.writeBase64ElementValue(data);
      writer.writeEndElement();
      writer.writeEndElement();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public void validate() throws Exception {
    EwsUtilities.validateParam(createAction, "createAction");
    EwsUtilities.validateParam(parentFolderId, "parentFolderId");
    EwsUtilities.validateParam(data, "data");
    if (createAction == CreateAction.Update) {
      EwsUtilities.validateParam(itemId, "itemId required for updates");
    }
  }

  public CreateAction getCreateAction() {
    return createAction;
  }

  public UploadItem setCreateAction(CreateAction createAction) {
    this.createAction = createAction;
    return this;
  }

  public FolderId getParentFolderId() {
    return parentFolderId;
  }

  public UploadItem setParentFolderId(FolderId parentFolderId) {
    this.parentFolderId = parentFolderId;
    return this;
  }

  public ItemId getItemId() {
    return itemId;
  }

  public UploadItem setItemId(ItemId itemId) {
    this.itemId = itemId;
    return this;
  }

  public byte[] getData() {
    return data;
  }

  public UploadItem setData(byte[] data) {
    this.data = data;
    return this;
  }
}
