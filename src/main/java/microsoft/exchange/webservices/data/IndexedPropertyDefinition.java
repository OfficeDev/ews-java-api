/**************************************************************************
 * copyright file="IndexedPropertyDefinition.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the IndexedPropertyDefinition.java.
 **************************************************************************/

package microsoft.exchange.webservices.data;

/***
 * Represents an indexed property definition.
 * 
 * 
 */
public final class IndexedPropertyDefinition extends
ServiceObjectPropertyDefinition {

	// Index attribute of IndexedFieldURI element.
	/** The index. */
	private String index;

	/***
	 * Initializes a new instance of the IndexedPropertyDefinition class.
	 * 
	 * @param uri
	 *            The FieldURI attribute of the IndexedFieldURI element.
	 * @param index
	 *            The Index attribute of the IndexedFieldURI element.
	 */
	protected IndexedPropertyDefinition(String uri, String index) {
		super(uri);
		this.index = index;
	}

	/***
	 * Determines whether two specified instances of IndexedPropertyDefinition
	 * are equal.
	 * 
	 * @param idxPropDef1
	 *            First indexed property definition.
	 * @param idxPropDef2
	 *            Second indexed property definition.
	 * @return True if indexed property definitions are equal.
	 */
	protected static boolean isEqualTo(IndexedPropertyDefinition idxPropDef1,
			IndexedPropertyDefinition idxPropDef2) {
		return (idxPropDef1 == idxPropDef2) ||
		 ((Object)idxPropDef1 != null &&
				 (Object)idxPropDef2 != null &&
				 idxPropDef1.getUri().equalsIgnoreCase(
						idxPropDef2.getUri()) && idxPropDef1.index
						.equalsIgnoreCase(idxPropDef2.index));
	}

	/***
	 * Gets the index of the property.
	 * 
	 * @return The index string of the property.
	 */
	public String getIndex() {
		return this.index;
	}

	/**
	 * * Writes the attributes to XML.
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
		writer.writeAttributeValue(XmlAttributeNames.FieldIndex, this
				.getIndex());
	}

	/***
	 * Gets the name of the XML element.
	 * 
	 * @return XML element name.
	 */
	@Override
	protected String getXmlElementName() {
		return XmlElementNames.IndexedFieldURI;
	}

	/***
	 * Gets the property definition's printable name.
	 * 
	 * @return The property definition's printable name.
	 */
	@Override
	protected String getPrintableName() {
		return String.format("%s:%s", this.getUri(), this.getIndex());
	}


	/***
	 * Determines whether a given indexed property definition is equal to this
	 * indexed property definition.
	 * 
	 * @param obj The
	 *            object to check for equality.
	 * @return True if the properties definitions define the same indexed
	 *         property.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof IndexedPropertyDefinition) {
			return IndexedPropertyDefinition.isEqualTo(
					(IndexedPropertyDefinition) obj, this);
		} else {
			return false;
		}
	}

	/***
	 * Serves as a hash function for a particular type.
	 * 
	 * @return A hash code for the current System.Object
	 */
	@Override
	public int hashCode() {
		return this.getUri().hashCode() ^ this.getIndex().hashCode();
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
