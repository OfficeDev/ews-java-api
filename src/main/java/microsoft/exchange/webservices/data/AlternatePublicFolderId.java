/**************************************************************************
 * copyright file="AlternatePublicFolderId.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the AlternatePublicFolderId.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents the Id of a public folder expressed in a specific format.
 */
public class AlternatePublicFolderId extends AlternateIdBase {

	/**
	 * Name of schema type used for AlternatePublicFolderId element.
	 */
	protected final static String SchemaTypeName = 
		"AlternatePublicFolderIdType";
	
	private String folderId;	

	/**
	 * Initializes a new instance of AlternatePublicFolderId.
	 */
	public AlternatePublicFolderId() {
		super();
	}

	/**
	 * Initializes a new instance of AlternatePublicFolderId.
	 * 
	 * @param format
	 *            the format
	 * @param folderId
	 *            the folder id
	 */
	public AlternatePublicFolderId(IdFormat format, String folderId) {
		super(format);
		this.setFolderId(folderId);
	}

	/**
	 * The Id of the public folder.
	 * 
	 * @return the folder id
	 */
	public String getFolderId() {		
		return this.folderId;

	}

	/**
	 * Sets the folder id.
	 * 
	 * @param folderId
	 *            the new folder id
	 */
	public void setFolderId(String folderId) {
		this.folderId = folderId;
	}

	/**
	 * Gets the name of the XML element.
	 * 
	 * @return XML element name
	 */
	@Override
	protected String getXmlElementName() {
		return XmlElementNames.AlternatePublicFolderId;
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
		writer.writeAttributeValue(XmlAttributeNames.FolderId, this
				.getFolderId());
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
		this.setFolderId(reader.readAttributeValue(XmlAttributeNames.FolderId));
	}

}
