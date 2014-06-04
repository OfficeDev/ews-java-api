/**************************************************************************
 * copyright file="XmlNamespace.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the XmlNamespace.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Defines the namespaces as used by the EwsXmlReader, EwsServiceXmlReader, and
 * EwsServiceXmlWriter classes.
 * 
 */
enum XmlNamespace {
	/*
	 * The namespace is not specified.
	 */
	/** The Not specified. */
	NotSpecified("", ""),

	/** The Messages. */
	Messages(EwsUtilities.EwsMessagesNamespacePrefix,
			EwsUtilities.EwsMessagesNamespace),

	/** The Types. */
	Types(EwsUtilities.EwsTypesNamespacePrefix, EwsUtilities.EwsTypesNamespace),

	/** The Errors. */
	Errors(EwsUtilities.EwsErrorsNamespacePrefix,
			EwsUtilities.EwsErrorsNamespace),

	/** The Soap. */
	Soap(EwsUtilities.EwsSoapNamespacePrefix, EwsUtilities.EwsSoapNamespace),

	/** The Soap12. */
	Soap12(EwsUtilities.EwsSoapNamespacePrefix, 
			EwsUtilities.EwsSoap12Namespace),

	/** The Xml schema instance. */
	XmlSchemaInstance(EwsUtilities.EwsXmlSchemaInstanceNamespacePrefix,
			EwsUtilities.EwsXmlSchemaInstanceNamespace),

	/** The Passport soap fault. */
	PassportSoapFault(EwsUtilities.PassportSoapFaultNamespacePrefix,
			EwsUtilities.PassportSoapFaultNamespace),

	/** The WS trust february2005. */
	WSTrustFebruary2005(EwsUtilities.WSTrustFebruary2005NamespacePrefix,
			EwsUtilities.WSTrustFebruary2005Namespace),

	/** The WS addressing. */
	WSAddressing(EwsUtilities.WSAddressingNamespacePrefix,
			EwsUtilities.WSAddressingNamespace),

	/** The Autodiscover. */
	Autodiscover(EwsUtilities.AutodiscoverSoapNamespacePrefix,
			EwsUtilities.AutodiscoverSoapNamespace);

	/** The prefix. */
	private String prefix;

	/** The name space uri. */
	private String nameSpaceUri;

	/**
	 * Instantiates a new xml namespace.
	 * 
	 * @param prefix
	 *            the prefix
	 * @param nameSpaceUri
	 *            the name space uri
	 */
	XmlNamespace(String prefix, String nameSpaceUri) {
		this.prefix = prefix;
		this.nameSpaceUri = nameSpaceUri;
	}

	/**
	 * Gets the name space uri.
	 * 
	 * @return the name space uri
	 */
	protected String getNameSpaceUri() {
		return this.nameSpaceUri;
	}

	/**
	 * Gets the name space prefix.
	 * 
	 * @return the name space prefix
	 */
	protected String getNameSpacePrefix() {
		return this.prefix;
	}
}
