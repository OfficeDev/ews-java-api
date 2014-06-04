/**************************************************************************
 * copyright file="CreateItemResponse.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the CreateItemResponse.java.
 **************************************************************************/

package microsoft.exchange.webservices.data;

/***
 * Represents the response to an individual item creation operation.
 * 
 * 
 */
final class CreateItemResponse extends CreateItemResponseBase {

	/** The item. */
	private Item item;

	/**
	 * * Gets Item instance.
	 * 
	 * @param service
	 *            The service.
	 * @param xmlElementName
	 *            Name of the XML element.
	 * @return the object instance
	 */
	@Override
	protected Item getObjectInstance(ExchangeService service,
			String xmlElementName) {
		return this.item;
	}

	/**
	 * * Initializes a new instance.
	 * 
	 * @param item
	 *            The item.
	 */
	protected CreateItemResponse(Item item) {
		super();
		this.item = item;
	}

	/***
	 * Clears the change log of the created folder if the creation succeeded.
	 */
	@Override
	protected void loaded() {
		if (this.getResult() == ServiceResult.Success) {
			this.item.clearChangeLog();
		}
	}
}
