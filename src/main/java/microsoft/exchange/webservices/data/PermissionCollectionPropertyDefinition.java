/**************************************************************************
 * copyright file="PermissionCollectionPropertyDefinition.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the PermissionCollectionPropertyDefinition class.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.EnumSet;

/**
 * Represents permission set property definition.
 */
class PermissionSetPropertyDefinition extends ComplexPropertyDefinitionBase{

	/**
	 * Initializes a new instance of the PermissionSetPropertyDefinition class.
	 * @param xmlElementName Name of the XML element.
	 * @param uri The URI.
	 * @param flags The flags.
	 * @param version The version.
	 */
	protected PermissionSetPropertyDefinition(String xmlElementName,String uri,
			EnumSet<PropertyDefinitionFlags> flags,ExchangeVersion version) { 
		super(xmlElementName,uri,flags,version);
	}

	/**
	 * Creates the property instance.
	 * @param owner The owner.
	 * @return ComplexProperty.
	 */
	@Override
	protected  ComplexProperty createPropertyInstance(ServiceObject owner) {
		Folder folder = (Folder)owner;

		EwsUtilities.EwsAssert(
				folder != null,
				"PermissionCollectionPropertyDefinition.CreatePropertyInstance",
		"The owner parameter is not of type Folder or a derived class.");

		return new FolderPermissionCollection(folder);
	}

	/**
	 * Gets the property type.
	 */
	@Override
	public Class getType() {
		return FolderPermissionCollection.class; 
	}
}

