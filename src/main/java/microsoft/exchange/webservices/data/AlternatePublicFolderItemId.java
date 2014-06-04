/**************************************************************************
 * copyright file="AlternatePublicFolderItemId.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the AlternatePublicFolderItemId.java.
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
	 * @param format
	 *            the format
	 * @param folderId
	 *            the folder id
	 * @param itemId
	 *            the item id
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
	 * @param itemId
	 *            the new item id
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
	 * @param writer
	 *            the writer
	 * @throws ServiceXmlSerializationException
	 *             the service xml serialization exception
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
	 * @param reader
	 *            the reader
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void loadAttributesFromXml(EwsServiceXmlReader reader)
			throws Exception {
		super.loadAttributesFromXml(reader);
		this.itemId = reader.readAttributeValue(XmlAttributeNames.ItemId);
	}

}
