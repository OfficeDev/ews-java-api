/**************************************************************************
 * copyright file="PropertyDefinitionFlags.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the PropertyDefinitionFlags.java.
 **************************************************************************/

package microsoft.exchange.webservices.data;

import java.util.EnumSet;

/**
 * defines how a complex property behaves.
 */
public enum PropertyDefinitionFlags {
	
	/**
	 * No specific behavior.
	 */
	None,

	/**
	 * The property is automatically instantiated when it is read.
	 */
	AutoInstantiateOnRead,

	/**
	 * The existing instance of the property is reusable.
	 */
	ReuseInstance,

	/**
	 * The property can be set.
	 */
	CanSet,

	/**
	 * The property can be updated.
	 */
	CanUpdate,

	/**
	 * The property can be deleted.
	 */
	CanDelete,

	/**
	 * The property can be searched.
	 */
	CanFind,

	/** The property must be loaded explicitly. */
	MustBeExplicitlyLoaded,
	
	/**
	 * Only meaningful for "collection" property. With this flag, the item in the collection gets updated, 
	 *  instead of creating and adding new items to the collection.
	 *   Should be used together with the ReuseInstance flag.
	 */
	
    UpdateCollectionItems;
	
}

