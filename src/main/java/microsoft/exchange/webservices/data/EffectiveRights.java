/**************************************************************************
 * copyright file="EffectiveRights.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the EffectiveRights.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Defines the effective user rights associated with an item or folder.
 */
public enum EffectiveRights {

	// The user has no acces right on the item or folder.
	/** The None. */
	None(0),

	// The user can create associated items (FAI)
	/** The Create associated. */
	CreateAssociated(1),

	// The user can create items.
	/** The Create contents. */
	CreateContents(2),

	// The user can create sub-folders.

	/** The Create hierarchy. */
	CreateHierarchy(4),

	// The user can delete items and/or folders.
	/** The Delete. */
	Delete(8),

	// The user can modify the properties of items and/or folders.
	/** The Modify. */
	Modify(16),

	// The user can read the contents of items.
	/** The Read. */
	Read(32),
	
	/// The user can view private items.
	/** The View Private Items. */
	ViewPrivateItems(64);
	

	/** The effective rights. */
	@SuppressWarnings("unused")
	private final int effectiveRights;

	/**
	 * Instantiates a new effective rights.
	 * 
	 * @param effectiveRights
	 *            the effective rights
	 */
	EffectiveRights(int effectiveRights) {
		this.effectiveRights = effectiveRights;
	}

}
