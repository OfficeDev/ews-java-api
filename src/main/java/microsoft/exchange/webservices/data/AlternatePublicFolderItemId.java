/**************************************************************************
 Exchange Web Services Java API
 Copyright (c) Microsoft Corporation
 All rights reserved.
 MIT License
 Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the ""Software""), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 THE SOFTWARE IS PROVIDED *AS IS*, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 **************************************************************************/

package microsoft.exchange.webservices.data;

/**
 * Represents the Id of a public folder item expressed in a specific format.
 */
public class AlternatePublicFolderItemId extends AlternatePublicFolderId {

  /**
   * Schema type associated with AlternatePublicFolderItemId.
   */
  protected final static String SchemaTypeName =
      "AlternatePublicFolderItemIdType";

  /**
   * Item id.
   */
  private String itemId;

  /**
   * Initializes a new instance of the class.
   */
  public AlternatePublicFolderItemId() {
    super();
  }

  /**
   * Initializes a new instance of the class.
   *
   * @param format   the format
   * @param folderId the folder id
   * @param itemId   the item id
   */
  public AlternatePublicFolderItemId(IdFormat format, String folderId,
      String itemId) {
    super(format, folderId);
    this.itemId = itemId;
  }

  /**
   * Gets The Id of the public folder item.
   *
   * @return the item id
   */
  public String getItemId() {
    return this.itemId;
  }

  /**
   * Sets the item id.
   *
   * @param itemId the new item id
   */
  public void setItemId(String itemId) {
    this.itemId = itemId;
  }

  /**
   * Gets the name of the XML element.
   *
   * @return XML element name.
   */
  @Override
  protected String getXmlElementName() {
    return XmlElementNames.AlternatePublicFolderItemId;
  }

  /**
   * Writes the attributes to XML.
   *
   * @param writer the writer
   * @throws ServiceXmlSerializationException the service xml serialization exception
   */
  @Override
  protected void writeAttributesToXml(EwsServiceXmlWriter writer)
      throws ServiceXmlSerializationException {
    super.writeAttributesToXml(writer);
    writer.writeAttributeValue(XmlAttributeNames.ItemId, this.getItemId());
  }

  /**
   * Loads the attributes from XML.
   *
   * @param reader the reader
   * @throws Exception the exception
   */
  @Override
  protected void loadAttributesFromXml(EwsServiceXmlReader reader)
      throws Exception {
    super.loadAttributesFromXml(reader);
    this.itemId = reader.readAttributeValue(XmlAttributeNames.ItemId);
  }

}
