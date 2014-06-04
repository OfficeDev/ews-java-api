/**************************************************************************
 * copyright file="ConvertIdResponse.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ConvertIdResponse.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents the response to an individual Id conversion operation.
 */
public final class ConvertIdResponse extends ServiceResponse {

	/** The converted id. */
	private AlternateIdBase convertedId;

	/**
	 * Initializes a new instance of the class.
	 */
	protected ConvertIdResponse() {
		super();
	}

	/**
	 * Reads response elements from XML.
	 * 
	 * @param reader
	 *            the reader
	 * @throws InstantiationException
	 *             the instantiation exception
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 * @throws ServiceLocalException
	 *             the service local exception
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void readElementsFromXml(EwsServiceXmlReader reader)
			throws InstantiationException, IllegalAccessException,
			ServiceLocalException, Exception {
		super.readElementsFromXml(reader);
		reader.readStartElement(XmlNamespace.Messages,
				XmlElementNames.AlternateId);
		String alternateIdClass = reader.readAttributeValue(
				XmlNamespace.XmlSchemaInstance, XmlAttributeNames.Type);

		int aliasSeparatorIndex = alternateIdClass.indexOf(':');

		if (aliasSeparatorIndex > -1) {
			alternateIdClass = alternateIdClass
					.substring(aliasSeparatorIndex + 1);
		}

		// Alternate Id classes are responsible fro reading the AlternateId end
		// element when necessary
		if (alternateIdClass.equals(AlternateId.SchemaTypeName)) {
			this.convertedId = new AlternateId();
		} else if (alternateIdClass
				.equals(AlternatePublicFolderId.SchemaTypeName)) {
			this.convertedId = new AlternatePublicFolderId();
		} else if (alternateIdClass
				.equals(AlternatePublicFolderItemId.SchemaTypeName)) {
			this.convertedId = new AlternatePublicFolderItemId();
		} else {
			EwsUtilities
					.EwsAssert(false, "ConvertIdResponse.ReadElementsFromXml",
							String.format("Unknown alternate Id class: %s",
									alternateIdClass));
		}

		this.convertedId.loadAttributesFromXml(reader);
		reader.readEndElement(XmlNamespace.Messages, 
				XmlElementNames.AlternateId);
	}

	/**
	 * Reads response elements from XML.
	 * 
	 * @return the converted id
	 */
	public AlternateIdBase getConvertedId() {
		return this.convertedId;
	}

}
