/**************************************************************************
 * copyright file="ItemId.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ItemId.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/***
 * Represents the Id of an Exchange item.
 * 
 */
public class ItemId extends ServiceId {

	/**
	 * * Initializes a new instance.
	 */
	protected ItemId() {
		super();
	}

	/**
	 * * Defines an implicit conversion between string and ItemId.
	 * 
	 * @param uniqueId
	 *            The unique Id to convert to ItemId.
	 * @return An ItemId initialized with the specified unique Id.
	 * @throws Exception
	 *             the exception
	 */
	public static ItemId getItemIdFromString(String uniqueId) throws Exception {
		return new ItemId(uniqueId);
	}

	/**
	 * * Initializes a new instance of ItemId.
	 * 
	 * @param uniqueId
	 *            The unique Id used to initialize the ItemId.
	 * @throws Exception
	 *             the exception
	 */
	public ItemId(String uniqueId) throws Exception {
		super(uniqueId);
	}

	/***
	 * Gets the name of the XML element.
	 * 
	 * @return XML element name.
	 */
	@Override
	protected String getXmlElementName() {
		return XmlElementNames.ItemId;
	}
}
