/**************************************************************************
 * copyright file="ServiceObjectPropertyDefinition.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ServiceObjectPropertyDefinition.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/***
 * Represents a property definition for a service object.
 * 
 * 
 */
public abstract class ServiceObjectPropertyDefinition extends
		PropertyDefinitionBase {

	/** The uri. */
	private String uri;

	/***
	 * Gets the name of the XML element.
	 * 
	 * @return the name of the XML element.
	 */
	@Override
	protected String getXmlElementName() {
		return XmlElementNames.FieldURI;
	}

	/***
	 * Gets the minimum Exchange version that supports this property.
	 * 
	 * @return The minimum Exchange version that supports this property.
	 */
	@Override
	public ExchangeVersion getVersion() {
		return ExchangeVersion.Exchange2007_SP1;
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
		writer.writeAttributeValue(XmlAttributeNames.FieldURI, this.getUri());
	}

	/***
	 * Initializes a new instance.
	 */
	protected ServiceObjectPropertyDefinition() {

	}

	/***
	 * Initializes a new instance.
	 * 
	 * @param uri
	 *            The URI.
	 */
	protected ServiceObjectPropertyDefinition(String uri) {
		super();
		EwsUtilities.EwsAssert(!(uri == null || uri.isEmpty()),
				"ServiceObjectPropertyDefinition.ctor", "uri is null or empty");
		this.uri = uri;
	}

	/***
	 * Gets the URI of the property definition.
	 * 
	 * @return The URI of the property definition.
	 */
	protected String getUri() {
		return uri;
	}
}
