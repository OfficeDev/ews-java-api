/**************************************************************************
 * copyright file="GroupMemberPropertyDefinition.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the GroupMemberPropertyDefinition.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/***
 * Represents the definition of the GroupMember property.
 * 
 * 
 */
final class GroupMemberPropertyDefinition extends
ServiceObjectPropertyDefinition {

	// / FieldUri of IndexedFieldURI for a group member.
	/** The Constant FIELDURI. */
	private final static String FIELDURI = "distributionlist:Members:Member";

	// / Member key.
	// / Maps to the Index attribute of IndexedFieldURI element.
	/** The key. */
	private String key;

	/**
	 * * Initializes a new instance of the GroupMemberPropertyDefinition class.
	 * 
	 * @param key
	 *            the key
	 */
	public GroupMemberPropertyDefinition(String key) {
		super(FIELDURI);
		this.key = key;
	}

	/***
	 * Initializes a new instance of the GroupMemberPropertyDefinition class
	 * without key.
	 * 
	 */
	protected GroupMemberPropertyDefinition() {
		super(FIELDURI);
	}

	/**
	 * Gets the key.
	 * 
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Sets the key.
	 * 
	 * @param key
	 *            the new key
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/***
	 * Gets the name of the XML element.
	 * 
	 * @return XML element name.
	 */
	protected String getXmlElementName() {
		return XmlElementNames.IndexedFieldURI;
	}

	/**
	 * * Writes the attributes to XML.
	 * 
	 * @param writer
	 *            the writer
	 * @throws ServiceXmlSerializationException
	 *             the service xml serialization exception
	 */
	protected void writeAttributesToXml(EwsServiceXmlWriter writer)
	throws ServiceXmlSerializationException {
		super.writeAttributesToXml(writer);
		writer.writeAttributeValue(XmlAttributeNames.FieldIndex, this.key);
	}

	/***
	 * Gets the property definition's printable name.
	 * 
	 * @return The property definition's printable name.
	 */
	@Override
	protected String getPrintableName() {
		return String.format("%s:%s", FIELDURI, this.key);
	}


	/**
	 * Gets the property type.
	 */
	@Override
	public Class getType()
	{
		return String.class;
	}


}
